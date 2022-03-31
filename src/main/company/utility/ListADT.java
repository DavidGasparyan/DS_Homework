package main.company.utility;

public interface ListADT<T> extends CollectionADT<T> {
  void addFirst(T e);
  T removeFirst();
  void addLast(T e);
  T removeLast();
  T first();
  T last();
  boolean replace(T e, T r);
}
