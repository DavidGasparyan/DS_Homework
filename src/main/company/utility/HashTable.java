package main.company.utility;

public class HashTable<K, V> implements MapADT<K, V>, Iterable<HashTable.HashEntry<K, V>> {
  private final HashEntry<?, ?>[] table;
  private int size = 0;

  public HashTable() {
    table = new HashEntry[20];
  }

  public HashTable(int tableSize) {
    table = new HashEntry[tableSize];
  }

  @Override
  public V put(K key, V value) {
    if (key == null || value == null) {
      throw new IllegalArgumentException("Null value/key is not permitted in hashtable");
    }

    int hashIndex = hash(key);

    final HashEntry<K, V> entry = new HashEntry<>(key, value);

    HashEntry<?, ?> previous = null;
    HashEntry<?, ?> pointer = table[hashIndex];

    // If table is empty insert as first value
    if (pointer == null) {
      table[hashIndex] = entry;

      size ++;
      return entry.value;
    }

    // Traverse full linked list of table
    while(pointer != null) {
      // If element already exists no need to add, just return itself
      if (pointer.key == key || pointer.key.equals(key)) {
        System.out.println("here");
        return null;
      }

      previous = pointer;
      pointer = pointer.next;
    }

    // When current is null and list is fully traversed assign next
    previous.next = entry;
    size ++;

    return entry.value;
  }

  @Override
  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException("Null key is not permitted in hashtable");
    }

    int hashIndex = hash(key);

    if (table[hashIndex] != null) {
      HashEntry<?, ?> pointer = table[hashIndex];

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
    if (key == null) {
      throw new IllegalArgumentException("Null key is not permitted in hashtable");
    }

    int hashIndex = hash(key);

    if (table[hashIndex] != null) {

      HashEntry<?, ?> previous = null;
      HashEntry<?, ?> current = table[hashIndex];

      while(current != null) {

        if (current.key == key || current.key.equals(key)) {
          @SuppressWarnings("unchecked")
          V tempValue = (V) current.value;

          if (previous == null) {
            table[hashIndex] = current.next;
          } else {
            previous.next = current.next;
            // Help garbage collector
            current = null;
          }

          size --;

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
  public int size() {
    return size;
  }

  @Override
  public void empty() {
    for (int i = 0; i < table.length; i ++) {

      // If table pointer is not empty, then empty bound linked list
      if (table[i] != null) {
        HashEntry<?, ?> pointer = table[i];

        // Delete the existing key from the hashtable
        table[i] = null;

        while (pointer != null) {
          HashEntry<?, ?> temp = pointer.next;

          pointer.next = null;
          // Help garbage collector
          pointer = null;

          pointer = temp;
        }
      }
    }

    size = 0;
  }

  public boolean containsValue(V value) {
    Iterator<HashEntry<K, V>> iterator = iterator();

    while(iterator.hasNext()) {
      HashEntry<K, V> entry = iterator.next();

      if (entry.value == value || entry.value.equals(value)) {
        return true;
      }
    }

    return false;
  }

  public boolean containsKey(K key) {
    Iterator<HashEntry<K, V>> iterator = iterator();

    while(iterator.hasNext()) {
      HashEntry<K, V> entry = iterator.next();

      if (entry.key == key || entry.key.equals(key)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void print() {
    for (HashEntry<?, ?> kvHashEntry : table) {

      // If table pointer is not empty, then traverse the linked list
      if (kvHashEntry != null) {
        HashEntry<?, ?> pointer = kvHashEntry;

        while (pointer != null) {
          System.out.println(pointer.value);
          pointer = pointer.next;
        }
      }
    }
  }

  private int hash(K k) {
    // Preform mathematical equivalent of math modulo operation
    return Math.floorMod(k.hashCode(), table.length);
  }

  @Override
  public Iterator<HashEntry<K, V>> iterator() {
    return new HashTableIterator();
  }

  protected class HashTableIterator implements Iterator<HashEntry<K, V>> {
    private int index = -1;
    private HashEntry<?, ?> pointer;

    public HashTableIterator() {
      /*
        If table pointer is not empty, then set the first index
       */
      for (int i = 0; i < table.length; i++) {
        if (table[i] != null) {
          index = i;

          pointer = table[i];

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
        for (int i = index + 1; i < table.length; i++) {
          // If we found hit on a table, set new table pointer and set new entry pointer.
          if (table[i] != null) {
            index = i;

            pointer = table[i];

            return true;
          }
        }

        // If we haven't found anything in the table. Then return

        return false;
      }

      return true;
    }

    @Override
    public HashEntry<K, V> next() {
      @SuppressWarnings("unchecked")
      HashEntry<K, V> temp = (HashEntry<K, V>) pointer;
      pointer = pointer.next;

      return temp;
    }
  }

  public static class HashEntry<K, V> {
    K key;
    V value;
    HashEntry<?, ?> next;

    public HashEntry(K key, V value) {
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

    public HashEntry<K, V> getNext() {
      return (HashEntry<K, V>) next;
    }
  }
}
