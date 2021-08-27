package impl.simple;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import impl.Cache;

/**
 * Kommentar zur Klasse
 * 
 * <pre>
 * Nr.   Name            Datum     Auslöser (ggf. Release) / Beschreibung
 * ----  --------------  --------  ------------------------------------------------
 * *02*
 * *01*  ........        TT.MM.JJ  Neuanlage
 * </pre>
 */
public class LRUCache<K, V> implements Cache<K, V> {

	private Map<K, V> map;

	public LRUCache(int size) {
		map = Collections.synchronizedMap(new LRUCacheImpl<K, V>(size));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see Cache#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean put(K key, V value) {
		return (map.put(key, value) != null);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see Cache#get(java.lang.Object)
	 */
	@Override
	public Optional<V> get(K key) {
		return Optional.ofNullable(map.get(key));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see Cache#size()
	 */
	@Override
	public int size() {
		return map.size();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see Cache#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see Cache#clear()
	 */
	@Override
	public void clear() {
		map.clear();
	}
}
