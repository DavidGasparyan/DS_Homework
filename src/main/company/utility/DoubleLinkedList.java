package main.company.utility;

public class DoubleLinkedList<T> implements ListADT<T>, Iterable<T> {
  private Node first;
  private Node last;
  private int size;

  protected class Node {
    private T item;
    Node next;
    Node back;

    public Node(T item) {
      this.item = item;
      this.next = null;
      this.back = null;
    }

    public Node(T item, Node next, Node back) {
      this.item = item;
      this.next = next;
      this.back = back;
    }
  }

  public DoubleLinkedList() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  @Override
  public boolean isEmpty() {
    return first == null;
  }

  @Override
  public void empty() {
    if (first.next != null) {
      first.next.back = null;
    }

    first.next = null;
  }

  @Override
  public void print() {
    if (first != null) {
      Node tempNode = first;

      while (tempNode != null) {
        System.out.println("Value is: " + tempNode.item);
        tempNode = tempNode.next;
      }
    }
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public void addFirst(T e) {
    if (first == null) {
      last = first = new Node(e);
    } else {
      first = new Node(e, first, null);
    }

    size ++;
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }

    Node tempNode = first;

    if (first == last) {
      first = last = null;
      size = 0;
    } else {
      first = first.next;

      if (first.next != null) {
        first.next.back = null;
      }

    }

    size --;

    return tempNode.item;
  }

  @Override
  public void addLast(T e) {
    if (first == null) {
      last = first = new Node(e);
    } else {
      Node newNode = new Node(e, null, last);
      last.next = newNode;
      last = newNode;
    }

    size ++;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }

    Node tempNode = last;
    Node previousNode = last.back;
    last.back = null;

    previousNode.next = null;
    last = previousNode;

    return tempNode.item;
  }

  @Override
  public T first() {
    if (first != null) {
      return first.item;
    }
    return null;
  }

  @Override
  public T last() {
    if (last != null) {
      return last.item;
    }

    return  null;
  }

  @Override
  public boolean replace(T e, T r) {
    if (first != null) {
      Node tempNode = first;

      while (tempNode != null) {
        if (tempNode.item.equals(e)) {
          tempNode.item = (T) r;
          return true;
        }

        tempNode = tempNode.next;
      }
    }

    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }
}
