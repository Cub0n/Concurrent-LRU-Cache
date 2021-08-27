package simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import impl.Cache;
import impl.simple.LRUCache;

public class TestLRUCacheSimple {

	@Test
	public void addSomeDataToCache_WhenGetData_ThenIsEqualWithCacheElement() {
		Cache<String, String> lruCache = new LRUCache<>(3);
		lruCache.put("1", "test1");
		lruCache.put("2", "test2");
		lruCache.put("3", "test3");
		assertEquals("test1", lruCache.get("1").get());
		assertEquals("test2", lruCache.get("2").get());
		assertEquals("test3", lruCache.get("3").get());
	}

	@Test
	public void addSomeDataToCache() {
		int size = 1000000;
		Cache<Integer, String> lruCache = new LRUCache<>(size);
		IntStream.range(0, size).forEach(i -> lruCache.put(i, "value" + i));
		assertEquals(size, lruCache.size());
	}

	@Test
	public void addDataToCacheToTheNumberOfSize_WhenAddOneMoreData_ThenLeastRecentlyDataWillEvict() {
		Cache<String, String> lruCache = new LRUCache<>(3);
		lruCache.put("1", "test1");
		lruCache.put("2", "test2");
		lruCache.put("3", "test3");
		lruCache.put("4", "test4");
		assertFalse(lruCache.get("1").isPresent());
	}

	@Test
	public void runMultiThreadTask_WhenPutDataInConcurrentToCache_ThenNoDataLost() throws Exception {
		final int size = 1000000;
		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		Cache<Integer, String> cache = new LRUCache<>(size);
		CountDownLatch countDownLatch = new CountDownLatch(size);
		try {
			IntStream.range(0, size).<Runnable> mapToObj(key -> () -> {
				cache.put(key, "value" + key);
				countDownLatch.countDown();
			}).forEach(executorService::submit);
			countDownLatch.await();
		}
		finally {
			executorService.shutdown();
		}
		assertEquals(cache.size(), size);
		//IntStream.range(0, size).forEach(i -> assertEquals("value" + i, cache.get(i).get()));
	}
}