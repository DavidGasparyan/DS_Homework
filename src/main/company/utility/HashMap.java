package main.company.utility;

public class HashMap<K, V> implements MapADT<K, V>, Iterable<HashMap.Entry<K, V>> {
  private final HashTable<K, Entry<?, ?>> bucket;
  private int size = 0;

  public HashMap() {
    bucket = new HashTable<>();
  }

  public HashMap(int initialSize) {
    bucket = new HashTable<>(initialSize);
  }

  @Override
  public V put(K key, V value) {
    Entry<?, ?> entry = bucket.put(key, new Entry<>(key, value));

    size = bucket.size();
    return (V) entry.getValue();
  }

  @Override
  public V get(K key) {
    Entry<?, ?> entry = bucket.get(key);

    // If entry returns null, then it means there is no relationship
    // with such a key
    if (entry == null) {
      return null;
    }

    return (V) entry.getValue();
  }

  @Override
  public V remove(K key) {
    Entry<?, ?> entry = bucket.remove(key);

    size = bucket.size();
    return (V) entry.getValue();
  }

  @Override
  public HashSet<K> keySet() {
    Iterator<HashTable.HashEntry<K, Entry<?, ?>>> iterator = bucket.iterator();
    HashSet<K> hashSet = new HashSet<>();

    while (iterator.hasNext()) {
      HashTable.HashEntry<K, Entry<?, ?>> item = iterator.next();
      hashSet.add(item.getKey());
    }

    return hashSet;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void empty() {
    bucket.empty();
    size = 0;
  }

  @Override
  public void print() {
    Iterator<HashTable.HashEntry<K, Entry<?, ?>>> iterator = bucket.iterator();

    while (iterator.hasNext()) {
      HashTable.HashEntry<K, Entry<?, ?>> item = iterator.next();
      System.out.println(item.getKey() + " " + item.getValue());
    }
  }

  @Override
  public int size() {
    return size;
  }

  private int hash(K key) {
    return Math.floorMod(key.hashCode(), bucket.size());
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return new EntryIterator();
  }

  protected class EntryIterator implements Iterator<Entry<K, V>> {
    Iterator<HashTable.HashEntry<K, Entry<?, ?>>> iterator;

    public EntryIterator() {
      iterator = bucket.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Entry<K, V> next() {
      HashTable.HashEntry<K, Entry<?, ?>> hashentry = iterator.next();
      Entry<?, ?> entry = hashentry.getValue();

      return (Entry<K, V> ) entry;
    }
  }

  public static class Entry<K, V> {
    K key;
    V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }
}
