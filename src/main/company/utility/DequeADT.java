package main.company.utility;

public interface DequeADT<T> extends Iterable<T> {
    void pushFront(T value);
    void pushBack(T value);
    T popFront();
    T popBack();
    T front();
    T back();
    boolean swap(T value1, T value2);
}
