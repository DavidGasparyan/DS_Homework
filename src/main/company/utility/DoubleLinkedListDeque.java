package main.company.utility;

public class DoubleLinkedListDeque<E> implements DequeADT<E> {
    private Node<E> front;
    private Node<E> rear;
    private int size = 0;

    protected class Node<E> {
        public E element;
        public Node<E> next;
        public Node<E> prev;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    DoubleLinkedListDeque() {};

    @Override
    public void pushFront(E value) {
        if (front == null) {
            front = rear = new Node<E>(value, null, null);
        } else {
            Node<E> tempNode = new Node<E>(value, null, front);
            front.prev = tempNode;
            front = tempNode;
        }

        size ++;
    }

    @Override
    public void pushBack(E value) {
        if (front == null) {
            front = rear = new Node<E>(value, null, null);
        } else {
            Node<E> tempNode = new Node<E>(value, rear, null);
            rear.next = tempNode;
            rear = tempNode;
        }

        size ++;
    }

    @Override
    public E popFront() {
        if (front == null) {
            return null;
        }

        Node<E> tempNode = front;

        if (front == rear) {
            rear.prev = null;
            front.next = null;
            front = rear = null;
        } else {
            front = front.next;
            front.prev = null;
        }

        return tempNode.element;
    }

    @Override
    public E popBack() {
        if (rear == null) {
            return null;
        }

        Node<E> tempNode = rear;

        if (front == rear) {
            rear.prev = null;
            front.next = null;
            front = rear = null;
        } else {
            rear = rear.prev;
            rear.next = null;
        }

        return tempNode.element;
    }

    @Override
    public E front() {
        return front.element;
    }

    @Override
    public E back() {
        return rear.element;
    }

    @Override
    public boolean swap(E value1, E value2) {
        if (size < 2) {
            return false;
        }

        Node<E> pointer = front;
        Node<E> node1 = null;
        Node<E> node2 = null;

        while(pointer != null) {
            // Find first occurrence of node holding first value
            if (pointer.element == value1) {
                node1 = pointer;
                break;
            }

            // Increment
            pointer = pointer.next;
        }

        // Reset Pointer
        pointer = front;

        while(pointer != null) {

            // Find first occurrence of holding second value
            if (pointer.element == value2) {
                node2 = pointer;
                break;
            }

            // Increment
            pointer = pointer.next;
        }


        // If it's the case that there is no Node
        // present with a value provided, then return
        if (node1 == null || node2 == null) {
            return false;
        }

        // If both nodes are equal they are already swapped
        if (node1 == node2) {
            return true;
        }

        Node<E> tempNode = node1;

        node1.prev.next = node2;
        node1.next.prev = node2;

        node2.prev.next = tempNode;
        node2.next.prev = tempNode;

        return true;
    }

    public boolean isEmpty() { return (front == null); }

    public int size() { return size; }

    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedListDequeIterator();
    }

    private class DoubleLinkedListDequeIterator implements Iterator<E> {
        private Node<E> pointer = front;

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
