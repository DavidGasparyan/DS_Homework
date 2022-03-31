package main.company.utility;

public class ArrayDeque<E> implements DequeADT<E> {
    private E[] elements;
    private int start = 0;
    private int end = 0;
    private int size = 0;

    public ArrayDeque() {
        elements = (E[]) new Object[10];
    }

    public ArrayDeque(int size) {
        elements = (E[]) new Object[size];
    }

    // Checked works
    @Override
    public void pushFront(E value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (start == end) {
            doubleCapacity();
        }

        elements[end] = value;

        end = ((end + 1) + elements.length) % elements.length;
        size ++;
    }

    // Checked works
    @Override
    public void pushBack(E value) {
        if (value == null) {
            throw new NullPointerException();
        }

        if (start == end) {
            doubleCapacity();
        }

        start = ((start - 1) + elements.length ) % elements.length;

        elements[start] = value;
        size ++;
    }

    @Override
    public E popFront() {
        if (isEmpty()) {
            return null;
        }

        E element = elements[end];
        elements[end] = null;

        end = ((end - 1) + size) % elements.length;

        size --;

        return element;
    }

    @Override
    public E popBack() {
        if (isEmpty()) {
            return null;
        }

        E element = elements[start];
        elements[start] = null;

        start = ((start + 1) + size) % elements.length;

        size --;

        return element;
    }

    // Checked works
    @Override
    public E front() {
        return elements[start];
    }

    // Checked works
    @Override
    public E back() {
        return elements[end];
    }

    @Override
    public boolean swap(E value1, E value2) {
        int val1Position = -1;
        int val2Position = -1;

        for (int i = 0; i < elements.length; i ++) {
            if (elements[i] == value1) {
                val1Position = i;
                break;
            }
        }

        for (int i = 0; i < elements.length; i ++) {
            if (elements[i]  == value2) {
                val2Position = i;
                break;
            }
        }

        if (val1Position != -1 && val2Position != -1) {
            elements[val1Position] = value2;
            elements[val2Position] = value1;

            return true;
        }

        return false;
    }

    // Checked works
    public boolean isEmpty() { return start == -1; }

    // Checked works
    private void doubleCapacity() {
        // Check if deque start and end meet
        assert start == end;
        // Number of elements to the right of the start element pointer
        int length =  elements.length;
        int delta = length - start;

        E [] tempArr = (E[]) new Object[length * 2];

        System.arraycopy(elements, start, tempArr, 0, delta);
        System.arraycopy(elements, 0, tempArr, delta, start);

        elements = tempArr;
        start = 0;
        end = length - 1;
    }

    // Checked works
    public int size() { return size; }

    // Checked works
    public void print() {
        for (E element : elements) {
//            if (element != null) {
                System.out.println(element);
//                System.out.println(start);
//                System.out.println(end);
//            System.out.println(elements.length);
//            }
        }
    }


    // Checked works
    @Override
    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    protected class ArrayDequeIterator implements Iterator<E> {
        private int head = start;

        int remainingSize = size();

        @Override
        public boolean hasNext() {
            return remainingSize > 0;
        }

        @Override
        public E next() {
            assert hasNext();
            E element = elements[head];

            if (head < elements.length - 1) {
                head ++;
            } else {
                head = 0;
            }

            remainingSize--;

            return element;
        }
    }
}
