package main.company.utility;

import java.util.Comparator;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
  Node<E> root;
  private Comparator<E> comparator;
  int size = 0;

  public BinarySearchTree() {
    root = null;

    comparator = new Comparator<E>() {
      @Override
      public int compare(E v1, E v2) {
        return v1.compareTo(v2);
      }
    };
  }

  public BinarySearchTree(Comparator<E> comparator) {
    root = null;
    this.comparator = comparator;
  }

  public Node<E> search(E data) {
    return search(root, data);
  }

  private Node<E> search(Node<E> node, E data) {

    if (node == null || node.data.compareTo(data) == 0) {
      return node;
    }

    if (node.data.compareTo(data) < 0) {
      return search(node.left, data);
    }

    return search(node.right, data);
  }

  public void insert(E data) {
    root = insert(root, data);
  }

  private Node<E> insert(Node<E> node, E data) {
    if (node == null) {
      return new Node<>(data);
    }

    if (node.data.compareTo(data) > 0) {
      node.left = insert(node.left, data);
    } else if (node.data.compareTo(data) < 0){
      node.right = insert(node.right, data);
    }

    return node;
  }

  public void delete(E data) {
    root = delete(root, data);
  }

  private Node<E> delete(Node<E> node, E data) {
    if (node == null) {
      return null;
    }

    if (data.compareTo(node.data) < 0) {
      node.left = delete(node.left, data);
    } else if (data.compareTo(node.data) > 0) {
      node.right = delete(node.right, data);
    } else {

      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }

      node.data = maxValue(node.left);

      node.left = delete(node.left, node.data);
    }

    return node;
  }

  public E maxValue(Node<E> node) {

    while(node.right != null) {
      node = node.right;
    }

    return node.data;
  }

  public void print() {
    print("", root, false);
  }

  private void print(String prefix, Node<E> n, boolean isLeft) {
    if (n != null) {
      print(prefix + "     ", n.right, false);
      System.out.println (prefix + ("|-- ") + n.data);
      print(prefix + "     ", n.left, true);
    }
  }

  public boolean contains(E data) {
    Iterator<E> iterator = new InOrderIterator();

    while(iterator.hasNext()) {
      E e = iterator.next();

      if (e == data || e.equals(data) ) {
        return true;
      }
    }

    return false;
  }

  public Iterator<E> iteratorInOrder() {
    return new InOrderIterator();
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  private class InOrderIterator implements Iterator<E> {
    private final Stack<Node<E>> traversal;

    public InOrderIterator() {
      traversal = new Stack<>();
      moveLeft(root);
    }

    private void moveLeft(Node<E> current) {
      while( current != null ) {
        traversal.push(current);
        current = current.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !traversal.isEmpty();
    }

    @Override
    public E next() {
      assert hasNext();

      Node<E> current = traversal.pop();

      if(current.right != null) {
        moveLeft(current.right);
      }

      return current.data;
    }
  }

  protected static class Node<E> {
    E data;
    Node<E> right;
    Node<E> left;

    public Node(E data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }

    @Override
    public String toString() {
      return data.toString();
    }
  }
}