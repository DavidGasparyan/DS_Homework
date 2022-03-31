package main.company.utility;

public interface MapADT<K, V> extends CollectionADT<K> {

  V put(K key, V value);

  V get(K key);

  V remove(K key);

  HashSet<K> keySet();
}
