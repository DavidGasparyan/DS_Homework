package main.company.utility;

public class HashSet<E> implements SetADT<E> {
  private final Object dummyObject = new Object();
  private final HashTable<E, Object> hashTable;
  private int size = 0;

  public HashSet() {
    hashTable = new HashTable<>();
  }

  public HashSet(int initialSize) {
    hashTable = new HashTable<>(initialSize);
  }

  @Override
  public boolean add(E value) {
    Object temp = hashTable.put(value, dummyObject);

    size = hashTable.size();

    return true;
  }

  @Override
  public boolean remove(E value) {
    Object temp = hashTable.remove(value);

    size = hashTable.size();

    return false;
  }

  @Override
  public boolean contains(E value) {
    return hashTable.containsKey(value);
  }

  @Override
  public boolean equals(SetADT<E> s) {
    Iterator<E> currentIterator = this.iterator();
    
    while (currentIterator.hasNext()) {
      E currentItem = currentIterator.next();

      Iterator<E> providedIterator = s.iterator();

      while (providedIterator.hasNext()) {
        E providedItem = providedIterator.next();

        if (!currentItem.equals(providedItem) || currentItem != providedItem) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public boolean isEmpty() {
    return hashTable.isEmpty();
  }

  @Override
  public void empty() {
    hashTable.empty();
    size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void print() {
    Iterator<HashTable.HashEntry<E, Object>> iterator = hashTable.iterator();

    while (iterator.hasNext()) {
      HashTable.HashEntry<E, Object> item = iterator.next();

      System.out.println(item.getKey());
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new HashSetIterator();
  }

  protected class HashSetIterator implements Iterator<E> {
    Iterator<HashTable.HashEntry<E, Object>> iterator;

    public HashSetIterator() {
      iterator = hashTable.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public E next() {
      HashTable.HashEntry<E, Object> hashentry = iterator.next();

      return hashentry.getKey();
    }
  }
}
