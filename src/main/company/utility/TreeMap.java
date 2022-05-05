package main.company.utility;

import java.util.Comparator;

public class TreeMap<K extends Comparable<? super K>, V> {
  private int size = 0;
  private Entry<K, V> root;
  private final Comparator<? super K> comparator;

  public TreeMap() {
    this.comparator = new Comparator<K>() {
      @Override
      public int compare(K o1, K o2) {
        return o1.compareTo(o2);
      }
    };
  }

  public TreeMap(Comparator<? super K> comparator) {
    this.comparator = comparator;
  }

  public V put(K k, V v) {
    // If key is null do nothing.
    if (k == null) {
      return null;
    }

    // If tree is empty, create one.
    if (root == null) {
      root = new Entry<>(k, v);

      return root.value;
    }

    Entry<K, V> pointer = root;
    Entry<K, V> previous = null;

    // Traverse till you find last leaf node
    while (pointer != null) {
      if (comparator.compare(pointer.key, k) > 0) {
        previous = pointer;
        pointer = pointer.left;
      } else if (comparator.compare(pointer.key, k) < 0) {
        previous = pointer;
        pointer = pointer.right;
      } else {
        // Update value if the key is same
        return pointer.value = v;
      }
    }

    // Find if the entry is right or left one
    if (comparator.compare(previous.key, k) > 0) {
      previous.left = new Entry<>(k, v, previous);
    } else {
      previous.right = new Entry<>(k, v, previous);
    }

    return v;
  };

  public V remove(K key) {
    // If tree is empty return null
    if (root == null || key == null) {
      return null;
    }

    V tempValue = get(key);

    remove(root, key);

    return tempValue;
  }

  private Entry<K, V> remove(Entry<K, V> entry, K key) {
    if (entry == null) {
      return null;
    }

    if (comparator.compare(entry.key, key) > 0) {
      entry.left = remove(entry.left, key);
    } else if (comparator.compare(entry.key, key) < 0) {
      entry.right = remove(entry.right, key);
    } else {
      if (entry.left == null) {
        return entry.right;
      } else if (entry.right == null) {
        return entry.left;
      } else {

        Entry<K, V> smallest = getSmallestEntry(entry.right);

        entry.key = smallest.key;
        entry.value = smallest.value;

        entry.right = remove(entry.right, entry.key);
      }
    }

    return entry;
  }

  public V get(K k) {
    // If tree is empty
    if (root == null || k == null) {
      return null;
    }

    Entry<K, V> pointer = root;

    // While key is not found, traverse till last leaf node.
    while (pointer != null) {
      if (comparator.compare(pointer.key, k) > 0) {
        pointer = pointer.left;
      } else if (comparator.compare(pointer.key, k) < 0) {
        pointer = pointer.right;
      } else {
        // return key if found
        return pointer.value;
      }
    }

    return null;
  }

  public V getSmallestEntry() {
    if (root == null) {
      return null;
    }

    Entry<K, V> pointer = root;

    while(pointer.left != null) {
      pointer = pointer.left;
    }

    return pointer.value;
  }

  private Entry<K, V> getSmallestEntry(Entry<K, V> entry) {
    if (entry == null) {
      return null;
    }

    while(entry.left != null) {
      entry = entry.left;
    }

    return entry;
  }

  public void print() {
    print("", root, false);
  }

  private void print(String prefix, Entry<K, V> n, boolean isLeft) {
    if (n != null) {
      print(prefix + "     ", n.right, false);
      System.out.println (prefix + ("|-- ") + n.value);
      print(prefix + "     ", n.left, true);
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void empty() {

  }

  public int size() {
    return size;
  }

  public Iterator<K> iterator() {
    return new TreeMapIterator();
  }

  public static class Entry<K extends Comparable<? super K>, V> implements Comparable<Entry<K, V>> {
    K key;
    V value;
    Entry<K, V> right;
    Entry<K, V> left;
    Entry<K, V> parent;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.right = null;
      this.left = null;
      this.parent = null;
    }

    Entry(K key, V value, Entry<K, V> parent) {
      this.key = key;
      this.value = value;
      this.right = null;
      this.left = null;
      this.parent = parent;
    }

    @Override
    public int compareTo(Entry<K, V> o) {
      return this.key.compareTo(o.key);
    }
  }

  private class TreeMapIterator implements Iterator<K> {
    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public K next() {
      return null;
    }
  }
}
