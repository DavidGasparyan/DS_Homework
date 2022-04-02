package main.company.utility;

public class HashMap<K, V> implements MapADT<K, V>, Iterable<HashMap.Entry<K, V>> {
  private final Entry<?, ?>[] elementsBucket;
  private int size = 0;

  public HashMap() {
    elementsBucket = new Entry[16];
  }

  public HashMap(int initialSize) {
    elementsBucket = new Entry[initialSize];
  }

  @Override
  public V put(K key, V value) {
    int hashIndex = hash(key);

    final Entry<K, V> entry = new Entry<>(key, value);

    if (elementsBucket[hashIndex] == null) {
      elementsBucket[hashIndex] = entry;

      size ++;

      return entry.value;
    }

    Entry<?, ?> pointer =  elementsBucket[hashIndex];

    while(pointer.next != null) {

      if (pointer.key == key || pointer.key.equals(key)) {
        // Exit if the key already exits, don't update
        // anything and don't increase the size
        return value;
      }

      pointer = pointer.next;
    }

    pointer.next = entry;
    size ++;

    return entry.value;
  }

  @Override
  public V get(K key) {
    int hashIndex = hash(key);

    if (elementsBucket[hashIndex] != null) {
      Entry<?, ?> pointer = elementsBucket[hashIndex];

      while(pointer != null) {

        if (pointer.key == key || pointer.key.equals(key)) {
          return (V) pointer.value;
        }

        pointer = pointer.next;
      }
    }

    return null;
  }

  @Override
  public V remove(K key) {
    int hashIndex = hash(key);

    if (elementsBucket[hashIndex] != null) {

      Entry<?, ?> previous = null;
      Entry<?, ?> current = elementsBucket[hashIndex];

      while (current != null) {

        if (current.key == key || current.key.equals(key)) {
          V tempValue = (V) current.value;

          if (previous == null) {
            elementsBucket[hashIndex] = current.next;
          } else {
            previous.next = current.next;
            // Help garbage collector
            current = null;
          }

          size--;

          return tempValue;
        }

        previous = current;
        current = current.next;
      }
    }

    return null;
  }

  @Override
  public HashSet<K> keySet() {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void empty() {
    for (int i = 0; i < elementsBucket.length; i ++) {

      // If table pointer is not empty, then empty bound linked list
      if (elementsBucket[i] != null) {
        Entry<?, ?> pointer = elementsBucket[i];

        // Delete the existing key from the hashtable
        elementsBucket[i] = null;

        while (pointer != null) {
          Entry<?, ?> temp = pointer.next;

          pointer.next = null;
          // Help garbage collector
          pointer = null;

          pointer = temp;
        }
      }
    }

    size = 0;
  }

  @Override
  public void print() {
    for (Entry<?, ?> element : elementsBucket) {

      // If table pointer is not empty, then traverse the linked list
      if (element != null) {
        Entry<?, ?> pointer = element;

        while (pointer != null) {
          System.out.println(pointer.value);
          pointer = pointer.next;
        }
      }
    }
  }

  @Override
  public int size() {
    return size;
  }

  private int hash(K key) {
    return Math.floorMod(key.hashCode(), elementsBucket.length);
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return new EntryIterator();
  }

  protected class EntryIterator implements Iterator<Entry<K, V>> {
    private int index = -1;
    private Entry<?, ?> pointer;


    public EntryIterator() {
/*
        If table pointer is not empty, then set the first index
       */
      for (int i = 0; i < elementsBucket.length; i++) {
        if (elementsBucket[i] != null) {
          index = i;

          pointer = elementsBucket[i];

          break;
        }
      }
    }

    @Override
    public boolean hasNext() {
      if (index == -1) {
        return false;
      }

      /*
        If current linked list entry is null, then we reached its end.
        Continue table traversing until we find next available index.
       */
      if (pointer == null) {
        for (int i = index + 1; i < elementsBucket.length; i++) {
          // If we found hit on a table, set new table pointer and set new entry pointer.
          if (elementsBucket[i] != null) {
            index = i;

            pointer = elementsBucket[i];

            return true;
          }
        }

        // If we haven't found anything in the table. Then return

        return false;
      }

      return true;
    }

    @Override
    public Entry<K, V> next() {
      @SuppressWarnings("unchecked")
      Entry<K, V> temp = (Entry<K, V>) pointer;
      pointer = pointer.next;

      return temp;
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

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public Entry<K, V> getNext() {
      return (Entry<K, V>) next;
    }
  }

}
