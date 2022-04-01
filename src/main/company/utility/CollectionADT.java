package main.company.utility;

public interface CollectionADT<T> extends Iterable<T> {
  boolean isEmpty();

  void empty();

  void print();

  int size();
}
