package main.company.utility;

import java.util.Comparator;

public class TreeMap<K extends Comparable<K>, V> {
  private final Comparator<K> comparator;
  private int size = 0;
  private Entry<K, V> root;

  public TreeMap() {
    this.comparator = new Comparator<K>() {
      @Override
      public int compare(K o1, K o2) {
        return o1.compareTo(o2);
      }
    };
  }

  public TreeMap(Comparator<K> comparator) {
    this.comparator = comparator;
  }

//  public E put() {
//
//  }
//
//  public E get() {
//
//  }
//
//  public E remove() {
//
//  }
//
//  public E getSmallestEntry() {
//
//  }
//
//  public boolean isTreeComplete() {
//
//  }
//
//  public boolean isTreeLeftSkewed() {
//
//  }
//
//  public TreeSet<E> getSetOfUniqueValues(E e, Comparator<E> comparator) {
//
//  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void empty() {

  }

  public void print() {

  }

  public int size() {
    return size;
  }

  public Iterator<K> iterator() {
    return new TreeMapIterator();
  }

  public static class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> right;
    Entry<K, V> left;
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
