package impl.simple;

import java.util.LinkedHashMap;
import java.util.Map;

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
final class LRUCacheImpl<K, V> extends LinkedHashMap<K, V> {

	private int maxCacheSize = 10000;

	/**
	 * Default constructor for an LRU Cache The default capacity is 10000
	 */
	LRUCacheImpl() {
		this(0, 10000, 0.75f, true);
	}

	/**
	 * Constructs a LRUCache with a maximum capacity
	 *
	 * @param maximumCacheSize
	 */
	LRUCacheImpl(int maximumCacheSize) {
		this(0, maximumCacheSize, 0.75f, true);
	}

	/**
	 * Constructs an empty <code>LRUCache</code> instance with the specified initial capacity, maximumCacheSize,load
	 * factor and ordering mode.
	 *
	 * @param initialCapacity
	 *            the initial capacity.
	 * @param maximumCacheSize
	 * @param loadFactor
	 *            the load factor.
	 * @param accessOrder
	 *            the ordering mode - <code>true</code> for access-order, <code>false</code> for insertion-order.
	 * @throws IllegalArgumentException
	 *             if the initial capacity is negative or the load factor is non-positive.
	 */
	private LRUCacheImpl(int initialCapacity, int maximumCacheSize, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
		this.maxCacheSize = maximumCacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return (size() > maxCacheSize);
	}
}
