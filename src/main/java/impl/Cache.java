package impl;

import java.util.Optional;

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
public interface Cache<K, V> {

	boolean put(K key, V value);

	Optional<V> get(K key);

	int size();

	boolean isEmpty();

	void clear();
}
