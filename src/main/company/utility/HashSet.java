package main.company.utility;

public class HashSet<E> implements SetADT<E>, Iterable<HashSet.Entry<E>> {
  private final Object dummyObject = new Object();
  private HashTable<E, Object> hashTable;
  private int size = 0;

  public HashSet() {
    hashTable = new HashTable<>();
  }

  public HashSet(int initialSize) {
    hashTable = new HashTable<>(initialSize);
  }

  @Override
  public boolean add(E value) {
    return hashTable.put(value, dummyObject) == null;
  }

  @Override
  public boolean remove(E value) {
    return hashTable.remove(value) == dummyObject;
  }

  @Override
  public boolean contains(E value) {
//    return hashTable.contains(value);
    return false;
  }

  @Override
  public boolean equals(SetADT<E> s) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return hashTable.isEmpty();
  }

  @Override
  public void empty() {
    hashTable.empty();
  }

  @Override
  public void print() {
//    hashTable.
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Iterator<Entry<E>> iterator() {
    return null;
  }

  public static class Entry<E> {
    E element;
    Entry<E> next;

    public Entry(E e) {
      element = e;
      next = null;
    }
  }
}
