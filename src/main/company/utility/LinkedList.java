package main.company.utility;

public class LinkedList<E> implements ListADT<E> {
  private Node<E> first;
  private Node<E> last;
  private int size=0;

  private static class Node<E> {
    Node<E> next;
    E element;

    public Node(E element) {
      this.next = null;
      this.element = element;
    }

    public Node(E element, Node<E> next) {
      this.next = next;
      this.element = element;
    }

    @Override
    public String toString() {
      return "Node {" + "element=" + element + '}';
    }
  }

  public LinkedList() {
  }

  @Override
  public E first() {
    if (first != null) {
      return first.element;
    }

    return  null;
  }

  @Override
  public E last() {
    if (last != null) {
      return last.element;
    }

    return  null;
  }

  public void addFirst(E element) {
    final Node<E> newNode = new Node<E>(element, first);

    if (first == null) {
      first = last = newNode;
    } else {
      first = newNode;
    }

    size++;
  }

  public void addLast(E element) {
    final Node<E> newNode = new Node<E>(element, null);

    if (first == null) {
      first = last = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }

    size++;
  }

  public E removeFirst() {
    // If the list is empty just return null;
    if (first == null) {
      return null;
    }

    final E element = first.element;
    final Node<E> next = first.next;

    first = next;

    if (next == null) {
      last = null;
    }

    size--;

    return element;
  }

  public E removeLast() {
    // If the list is empty just return null;
    if (first == null) {
      return null;
    }

    final E element = last.element;
    final Node<E> lastNode = last;

    Node<E> previousNode = null;
    Node<E> nextNode = first;

    while (nextNode != lastNode) {
      previousNode = nextNode;
      nextNode = nextNode.next;
    }

    last = previousNode;

    if (previousNode == null) {
      first = null;
    } else {
      previousNode.next = null;
    }

    size--;

    return element;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public E get(int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> tempNode = first;

    while (index > 0) {
      tempNode = tempNode.next;
      index--;
    }

    return tempNode.element;
  }

  public void set(E element, int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0) {
      addFirst(element);
      return;
    }

    Node<E> previousNode = null;
    Node<E> nextNode = first;

    while (index > 0) {
      previousNode = nextNode;
      nextNode = nextNode.next;
      index--;
    }

    previousNode.next = new Node<>(element, nextNode);
  }

  public void print() {
    Node<E> firstNode = first;

    while (firstNode != null) {
      System.out.println(firstNode.element);
      firstNode = firstNode.next;
    }
  }

  public void insertBefore(E element, int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0) {
      addFirst(element);
      return;
    }

    Node<E> previousNode = first;
    Node<E> currentNode = first.next;

    while (index > 1) {
      previousNode = currentNode;
      currentNode = currentNode.next;
      index--;
    }

    previousNode.next = new Node<>(element, currentNode);
    size++;
  }

  public void insertAfter(E element, int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0 && first == null) {
      addFirst(element);
    }

    Node<E> currentNode = first;
    Node<E> nextNode = first.next;

    while (index > 0) {
      currentNode = nextNode;
      nextNode = nextNode.next;
      index--;
    }

    currentNode.next = new Node<>(element, nextNode);
    size++;
  }

  public void remove(int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0) {
      removeFirst();
      return;
    }

    Node<E> beforeNode = first;
    Node<E> currentNode = first.next;

    while (index > 1) {
      beforeNode = currentNode;
      currentNode = currentNode.next;
      index--;
    }

    beforeNode.next = currentNode.next;
    size--;
  }

  @Override
  public boolean replace(E e, E r)  {
    if (first != null) {
      Node<E> tempNode = first;

      while (tempNode != null) {
        if (tempNode.element.equals(e)) {
          tempNode.element = r;
          return true;
        }

        tempNode = tempNode.next;
      }
    }

    return false;
  }

  public void empty() {
    for (Node<E> pointer = last; pointer != null; ) {
      Node<E> next = pointer.next;

      pointer.element = null;
      pointer.next = null;

      pointer = next;
    }

    first = last = null;
    size = 0;
  }

  public Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  public Iterator<E> reverseIterator() {
    return new ReverseIterator();
  }

  protected class ReverseIterator implements Iterator<E> {
    int index = size;

    @Override
    public boolean hasNext() {
      return index > 0;
    }

    @Override
    public E next() {
      if (hasNext()) {
        int tempIndex = index;
        Node<E> tempNode = first;

        while (tempIndex > 1) {
          tempNode = tempNode.next;
          tempIndex--;
        }

        index--;

        return tempNode.element;
      }

      return null;
    }
  }

  protected class LinkedListIterator implements Iterator<E> {
    private Node<E> pointer = first;

    @Override
    public boolean hasNext() {
      return pointer != null;
    }

    @Override
    public E next() {
      if (hasNext()) {
        Node<E> tempNode = pointer;
        pointer = pointer.next;

        return tempNode.element;
      }

      return null;
    }
  }
}

