package main.company.utility;

public class HashTable<K, V> implements MapADT<K, V> {
  private final HashEntry<K, V>[] table;


  @SuppressWarnings("unchecked")
  public HashTable() {
    table = new HashEntry[20];
  }

  @Override
  public V put(K key, V value) {
    int hashIndex = hash(key);

    final HashEntry<K, V> entry = new HashEntry<>(key, value);

    // If table is empty insert as first value
    if (table[hashIndex] == null) {

      table[hashIndex] = entry;

      return entry.value;
    }

    HashEntry<K, V> pointer = table[hashIndex];

    while(pointer.next != null) {
      // If element already exists no need to add, just return itself
      if (pointer.value == value || pointer.value.equals(value)) {
        return value;
      }

      pointer = pointer.next;
    }

    // When current is null and list is fully traversed assign next if last element
    pointer.next = entry;

    return entry.value;
  }

  @Override
  public V get(K key) {
    int hashIndex = hash(key);

    if (table[hashIndex] != null) {
      HashEntry<K, V> pointer = table[hashIndex];

      while(pointer.next != null) {

        if (pointer.key == key || pointer.key.equals(key)) {
          return pointer.value;
        }

        pointer = pointer.next;
      }
    }

    return null;
  }

  @Override
  public V remove(K key) {
    int hashIndex = hash(key);

    if (table[hashIndex] != null) {

      HashEntry<K, V> previous = null;
      HashEntry<K, V> current = table[hashIndex];

      while(current != null) {

        if (current.key == key || current.key.equals(key)) {
          V tempValue = current.value;

          if (previous == null) {
            table[hashIndex] = current.next;
          } else {
            previous.next = current.next;
            // Help garbage collector
            current = null;
          }

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
    for (HashEntry<K, V> kvHashEntry : table) {
      if(kvHashEntry != null) {
        return false;
      }
    }

    return true;
  }

  @Override
  public void empty() {
    for (HashEntry<K, V> kvHashEntry : table) {

      // If table pointer is not empty, then empty bound linked list
      if (kvHashEntry != null) {
        HashEntry<K, V> pointer = kvHashEntry;

        while (pointer != null) {
          HashEntry<K, V> temp = pointer.next;

          pointer.next = null;
          // Help garbage collector
          pointer = null;

          pointer = temp;
        }
      }
    }
  }

  @Override
  public void print() {
    for (HashEntry<K, V> kvHashEntry : table) {

      // If table pointer is not empty, then traverse the linked list
      if (kvHashEntry != null) {
        HashEntry<K, V> pointer = kvHashEntry;

        while (pointer != null) {
          System.out.println(pointer.value);
          pointer = pointer.next;
        }
      }
    }
  }

  private int hash(K k) {
    // Hashcode can be negative, that is why we need extra hashing
    return k.hashCode() % table.length;
  }

  @Override
  public Iterator<K> iterator() {
    return new HashTableIterator();
  }

  protected class HashTableIterator implements Iterator<K> {
    private int index = -1;
    private HashEntry<K, V> pointer;
    private HashEntry<K, V> entry;

    public HashTableIterator() {
      /*
        If table pointer is not empty, then set the first index
       */
      for (int i = 0; i < table.length; i++) {
        if (table[i] != null) {
          index = i;
          entry = pointer = table[i];
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
            entry = pointer = table[i];

            return true;
          }
        }

        // If we haven't found anything in the table. Then return

        return false;
      }

      entry = pointer;
      pointer = pointer.next;

      return true;
    }

    @Override
    public K next() {
      return entry.key;
    }
  }

  protected static class HashEntry<K, V> {
    K key;
    V value;
    HashEntry<K, V> next;

    public HashEntry(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
  }
}
