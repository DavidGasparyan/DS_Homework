package main.company.utility;

public interface SetADT<T> extends CollectionADT<T> {
  boolean add(T value);

  boolean remove(T value);

  boolean contains(T value);

  boolean equals(SetADT<T> s);
}
