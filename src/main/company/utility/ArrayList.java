package main.company.utility;

public class ArrayList<T> implements ListADT<T> {
  private Object[] arr;
  private int size;

  public ArrayList() {
    arr = new Object[10];
    size = 0;
  }

  public ArrayList(int length) {
    if (length == 0) {
      length = 10;
    }

    arr = new Object[length];
    size = 0;
  }

  @Override
  public void addFirst(Object e) {
    size ++;
    resize();
    Object[] tempArr = new Object[arr.length];
    tempArr[0] = e;

    for (int i = 0; i < size; i++) {
      tempArr[i + 1] = arr[i];
    }

    arr = tempArr;
  }

  @Override
  public T removeFirst() {
    if (size == 0) {
      return null;
    }

    Object[] tempArr = new Object[arr.length];
    Object tempItem = arr[0];

    for (int i = 0; i < size; i++) {
      tempArr[i] = arr[i + 1];
    }

    arr = tempArr;

    size --;

    return (T) tempItem;
  }

  @Override
  public void addLast(Object e) {
    resize();
    arr[size] = e;
    size ++;
  }

  @Override
  public T removeLast() {
    if (size == 0) {
      return null;
    }

    Object tempItem = arr[size - 1];
    arr[size - 1] = null;
    size --;

    return (T) tempItem;
  }

  @Override
  public T first(){
    if (size != 0) {
      return (T) arr[0];
    }

    return null;
  }

  @Override
  public T last() {
    if (size != 0) {
      return (T) arr[size - 1];
    }

    return  null;
  }

  @Override
  public boolean replace(Object e, Object r) {
    if (size != 0) {
      for (int i = 0; i < size; i++) {
        if (arr[i].equals(e)) {
          arr[i] = r;
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void empty() {
    for (int i = 0; i < size; i ++) {
      arr[i] = null;
    }

    size = 0;
  }

  @Override
  public void print() {
    for (Object o : arr) {
      if (o != null) {
        System.out.println(o);
      }
    }
  }

  @Override
  public int size() {
    return 0;
  }

  private void resize() {
    if (size < arr.length) return;

    Object[] tempArray = new Object[size * 2];

    System.arraycopy(arr, 0, tempArray, 0, size);

//    for (int i = 0; i < size; i ++) {
//      temp[i] = arr[i];
//    }

    arr = tempArray;
  }

  public Iterator<T> iterator() {
    return new ArrayListIterator();
  }

  public Iterator<T> iteratorOdd() {
    return new ArrayListIteratorOdd();
  }

  private class ArrayListIterator implements Iterator<T> {
    private int index = 0;

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      if (hasNext()) {
        return (T) arr[index ++];
      }

      return null;
    }
  }

  private class ArrayListIteratorOdd implements Iterator<T> {
    private int index = 1;

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      if (hasNext()) {
        int tempIndex = index;
        index += 2;
        return (T) arr[tempIndex];
      }

      return null;
    }
  }
}
