package main.company.utility;

public class HashMap<K, V> implements MapADT<K, V>, Iterable<HashMap.Entry<K, V>> {
  private HashTable<K, V> hashTable;
  private int size = 0;

  public HashMap() {
    hashTable = new HashTable<>();
  }

  public HashMap(int initialSize) {
    hashTable = new HashTable<>(initialSize);
  }

  @Override
  public V put(K key, V value) {
    if (key == null || value == null) return null;

    return hashTable.put(key, value);
  }

  @Override
  public V get(K key) {
    if (key == null) return null;

    return hashTable.get(key);
  }

  @Override
  public V remove(K key) {
    if (key == null ) return null;

    return hashTable.remove(key);
  }

  @Override
  public HashSet<K> keySet() {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return hashTable.isEmpty();
  }

  @Override
  public void empty() { hashTable.empty(); }

  @Override
  public void print() { hashTable.print(); }

  @Override
  public int size() { return hashTable.size(); }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return null;
  }

  public Iterator<Entry<K, V>> entryIterator() {
    return new EntryIterator();
  }

  protected class EntryIterator implements Iterator<Entry<K, V>> {

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public Entry<K, V> next() {
      return null;
    }
  }

  public static class Entry<K, V> {
    K key;
    V value;
    Entry<?, ?> next;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
  }

}
