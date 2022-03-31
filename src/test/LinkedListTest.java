package test;

import main.company.utility.Iterator;
import main.company.utility.LinkedList;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

  private LinkedList<Integer> linkedList;

  @Test
  @DisplayName("Is instanced with new base.LinkedList()")
  void isInstantiatedWithNew() {
    new LinkedList<>();
  }

  @Nested
  @DisplayName("when new")
  class WhenNew {

    @BeforeEach
    void createNewStack() {
      linkedList = new LinkedList<>();
    }

    @Test
    @DisplayName("size is 0 for newly created linked list")
    void assertSizeZero() {
      assertEquals(0, linkedList.size());
    }

    @Test
    @DisplayName("when first element removed on empty list null must be returned")
    void isNullReturnedAfterFirstRemoved() {
      assertNull(linkedList.removeFirst());
    }

    @Test
    @DisplayName("when first element removed on empty list null must be returned")
    void isNullReturnedAfterLastRemoved() {
      assertNull(linkedList.removeLast());
    }

    @Test
    @DisplayName("is empty")
    void isEmpty() {
      assertTrue(linkedList.isEmpty());
    }

    @Nested
    @DisplayName("after pushing an element")
    class AfterAddingFirst {
      int element = (int) (Math.random() * 100);;

      @BeforeEach
      void addFirstElement() {
        linkedList.addFirst(element);
      }

      @Test
      @DisplayName("it is no longer empty")
      void isNotEmpty() {
        assertFalse(linkedList.isEmpty());
      }

      @Test
      @DisplayName("size is equal to 1 after pushing 1 element")
      void assertSizeNotZero() {
        assertEquals(1, linkedList.size());
      }

      @Test
      @DisplayName("get the element after adding first")
      void getElement() {
        assertEquals(element, linkedList.get(0));
      }

      @Test
      @DisplayName("returns the element when removed first and is empty")
      void returnElementWhenRemovedFirst() {
        assertEquals(element, linkedList.removeFirst());
        assertTrue(linkedList.isEmpty());
      }

      @Test
      @DisplayName("size is equal to 0 after removing 1 element")
      void assertSizeZeroWhenRemovedFirst() {
        assertEquals(element, linkedList.removeFirst());
        assertEquals(0, linkedList.size());
      }
    }

    @Nested
    @DisplayName("after pushing an element")
    class AfterAddingLast {
      int element = (int) (Math.random() * 100);

      @BeforeEach
      void addFirstElement() {
        linkedList.addLast(element);
      }

      @Test
      @DisplayName("it is no longer empty")
      void isNotEmpty() {
        assertFalse(linkedList.isEmpty());
      }

      @Test
      @DisplayName("size is equal to 1 after pushing 1 element")
      void assertSizeNotZero() {
        assertEquals(1, linkedList.size());
      }

      @Test
      @DisplayName("get the element after adding last")
      void getElement() {
        assertEquals(element, linkedList.get(0));
      }

      @Test
      @DisplayName("returns the element when removed first and is empty")
      void returnElementWhenRemovedLast() {
        assertEquals(element, linkedList.removeLast());
        assertTrue(linkedList.isEmpty());
      }

      @Test
      @DisplayName("size is equal to 0 after removing 1 element")
      void assertSizeZeroWhenRemovedLast() {
        assertEquals(element, linkedList.removeFirst());
        assertEquals(0, linkedList.size());
      }
    }

    @Nested
    @DisplayName("after pushing an element")
    class GetAfterAddingManyElements {
      int [] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

      @Test
      @Description("Add all elements to the head")
      void getElementsAfterAddingToListHead() {

        for(int i: intList) {
          linkedList.addFirst(i);
          assertEquals(i, linkedList.get(0));
        }

        for(int i = 0; i < intList.length; i ++) {
          assertEquals(intList[intList.length - 1 - i], linkedList.get(i));
        }
      }

      @Test
      @Description("Add all elements to the tail")
      void getElementsAfterAddingToListTail() {

        for(int i: intList) {
          linkedList.addLast(i);
          assertEquals(i, linkedList.get(i - 1));
        }

        for(int i = 0; i < intList.length; i ++) {
          assertEquals(intList[i], linkedList.get(i));
        }
      }

      @Test
      @Description("If index is negative throw out of bounds exception")
      void exceptionTestOnIndexNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-10));
      }

      @Test
      @Description("If index is greater than list size throw out of bounds exception")
      void exceptionTestOnIndexGreaterSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(10));
      }


    }

    @Nested
    @DisplayName("after setting an element")
    class GetAfterSettingElement {
      int [] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

      @BeforeEach
      void populateList() {
        for (int item: intList) {
          linkedList.addLast(item);
        }
      }

      @Test
      @Description("Set element with negative index value")
      void setNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.set(500, -10));
      }

      @Test
      @Description("Set element out of size range of the list")
      void setIndexOutOfListSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.set(500, 15));
      }

      @Test
      @Description("Set element at index")
      void setElementAtIndex() {
        linkedList.set(800, 0);
        assertEquals(800, linkedList.get(0));

        linkedList.set(999, 1);
        assertEquals(999, linkedList.get(1));

        linkedList.set(-500, 2);
        assertEquals(-500, linkedList.get(2));

        linkedList.set(123123, 3);
        assertEquals(123123, linkedList.get(3));

        linkedList.set(3, 4);
        assertEquals(3, linkedList.get(4));

        linkedList.set(8880, 5);
        assertEquals(8880, linkedList.get(5));

        linkedList.set(555, 6);
        assertEquals(555, linkedList.get(6));

        linkedList.set(13123800, 7);
        assertEquals(13123800, linkedList.get(7));

        linkedList.set(812312300, 8);
        assertEquals(812312300, linkedList.get(8));

        linkedList.set(81231200, 9);
        assertEquals(81231200, linkedList.get(9));

        linkedList.set(190283912, 10);
        assertEquals(190283912, linkedList.get(10));
      }
    }

    @Nested
    @DisplayName("after setting an element")
    class GetAfterSettingElementBeforeAndAfter {

      @Test
      @Description("Set element with negative index value")
      void setNegativeIndexBefore() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.insertBefore(500, -10));
      }

      @Test
      @Description("Set element out of size range of the list")
      void setIndexBeforeOutOfListSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.insertBefore(500, 15));
      }

      @Test
      @Description("Set element with negative index value")
      void setNegativeIndexAfter() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.insertAfter(500, -10));
      }

      @Test
      @Description("Set element out of size range of the list")
      void setIndexAfterOutOfListSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.insertAfter(500, 15));
      }

      @Test
      @Description("Set before element on zero index on empty list")
      void setIndexBeforeOnEmptyListIndexZero() {
        linkedList.insertAfter(800, 0);
        assertEquals(800, linkedList.get(0));
      }

      @Test
      @Description("Set after element on zero index on empty list")
      void setIndexAfterOnEmptyListIndexZero() {
        linkedList.insertAfter(800, 0);
        assertEquals(800, linkedList.get(0));
      }

      @Nested
      class FillList {
        int [] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        @BeforeEach
        void populateList() {
          for (int item: intList) {
            linkedList.addLast(item);
          }
        }

        @Test
        @Description("Set element at index")
        void setElementBeforeIndex() {
//      int [] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

          linkedList.insertBefore(800, 0);
//      int [] intList = {800, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
          assertEquals(800, linkedList.get(0));
          assertEquals(1, linkedList.get(1));

          assertEquals(11, linkedList.size());

          linkedList.insertBefore(999, 2);
//      int [] intList = {800, 0, 1, 999, 2, 3, 4, 5, 6, 7, 8, 9, 10};
          assertEquals(800, linkedList.get(0));
          assertEquals(1, linkedList.get(1));
          assertEquals(999, linkedList.get(2));

          assertEquals(12, linkedList.size());

          linkedList.insertBefore(-500, 4);
//      int [] intList = {800, 1, 999, 2, -555, 3, 4, 5, 6, 7, 8, 9, 10};
          assertEquals(800, linkedList.get(0));
          assertEquals(1, linkedList.get(1));
          assertEquals(999, linkedList.get(2));
          assertEquals(2, linkedList.get(3));
          assertEquals(-500, linkedList.get(4));

          assertEquals(13, linkedList.size());

          linkedList.insertBefore(123123, 7);
//      int [] intList = {800, 1, 999, 2, -555, 3, 4, 123123 5, 6, 7, 8, 9, 10};
          assertEquals(800, linkedList.get(0));
          assertEquals(1, linkedList.get(1));
          assertEquals(999, linkedList.get(2));
          assertEquals(2, linkedList.get(3));
          assertEquals(-500, linkedList.get(4));
          assertEquals(3, linkedList.get(5));
          assertEquals(4, linkedList.get(6));
          assertEquals(123123, linkedList.get(7));

          assertEquals(14, linkedList.size());

          linkedList.insertBefore(8880, 10);
//      int [] intList = {800, 1, 999, 2, -555, 3, 4, 123123, 5, 6, 8880, 7, 8, 9, 10};
          assertEquals(800, linkedList.get(0));
          assertEquals(1, linkedList.get(1));
          assertEquals(999, linkedList.get(2));
          assertEquals(2, linkedList.get(3));
          assertEquals(-500, linkedList.get(4));
          assertEquals(3, linkedList.get(5));
          assertEquals(4, linkedList.get(6));
          assertEquals(123123, linkedList.get(7));
          assertEquals(5, linkedList.get(8));
          assertEquals(6, linkedList.get(9));
          assertEquals(8880, linkedList.get(10));

          assertEquals(15, linkedList.size());

        }

        @Test
        @Description("Set element at index")
        void setElementAfterIndex() {
//      int [] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

          linkedList.insertAfter(800, 0);
//      int [] intList = {1, 800, 2, 3, 4, 5, 6, 7, 8, 9, 10};
          assertEquals(1, linkedList.get(0));
          assertEquals(800, linkedList.get(1));

          assertEquals(11, linkedList.size());

          linkedList.insertAfter(999, 2);
//      int [] intList = {1, 800, 2, 999, 3, 4, 5, 6, 7, 8, 9, 10};
          assertEquals(1, linkedList.get(0));
          assertEquals(800, linkedList.get(1));
          assertEquals(2, linkedList.get(2));
          assertEquals(999, linkedList.get(3));

          assertEquals(12, linkedList.size());

          linkedList.insertAfter(-500, 4);
//      int [] intList = {1, 800, 2, 999, 3, -500, 4, 5, 6, 7, 8, 9, 10};
          assertEquals(1, linkedList.get(0));
          assertEquals(800, linkedList.get(1));
          assertEquals(2, linkedList.get(2));
          assertEquals(999, linkedList.get(3));
          assertEquals(3, linkedList.get(4));
          assertEquals(-500, linkedList.get(5));

          assertEquals(13, linkedList.size());

          linkedList.insertAfter(123123, 7);
//      int [] intList = {1, 800, 2, 999, 3, -500, 4, 5, 123123, 6, 7, 8, 9, 10};
          assertEquals(1, linkedList.get(0));
          assertEquals(800, linkedList.get(1));
          assertEquals(2, linkedList.get(2));
          assertEquals(999, linkedList.get(3));
          assertEquals(3, linkedList.get(4));
          assertEquals(-500, linkedList.get(5));
          assertEquals(4, linkedList.get(6));
          assertEquals(5, linkedList.get(7));
          assertEquals(123123, linkedList.get(8));

          assertEquals(14, linkedList.size());

          linkedList.insertAfter(8880, 10);
//      int [] intList = {1, 800, 2, 999, 3, -500, 4, 5, 123123, 6, 7, 8880, 8, 9, 10};
          assertEquals(1, linkedList.get(0));
          assertEquals(800, linkedList.get(1));
          assertEquals(2, linkedList.get(2));
          assertEquals(999, linkedList.get(3));
          assertEquals(3, linkedList.get(4));
          assertEquals(-500, linkedList.get(5));
          assertEquals(4, linkedList.get(6));
          assertEquals(5, linkedList.get(7));
          assertEquals(123123, linkedList.get(8));
          assertEquals(6, linkedList.get(9));
          assertEquals(7, linkedList.get(10));
          assertEquals(8880, linkedList.get(11));

          assertEquals(15, linkedList.size());

        }
      }
    }

    @Nested
    @DisplayName("Removal opeartions")
    class TestRemovalOperations {

      @Test
      @Description("Remove element with negative index value")
      void setNegativeIndexBefore() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(-10));
      }

      @Test
      @Description("Remove element out of size range of the list")
      void setIndexBeforeOutOfListSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(15));
      }

      int [] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

      @Nested
      class FillList {
        @BeforeEach
        void populateList() {
          for (int item: intList) {
            linkedList.addLast(item);
          }
        }

        @Test
        @DisplayName("Removing elements")
        void removeElements() {
          linkedList.remove(9);
          assertEquals(9, linkedList.size());
          assertEquals(1, linkedList.get(0));
          assertEquals(2, linkedList.get(1));
          assertEquals(3, linkedList.get(2));
          assertEquals(4, linkedList.get(3));
          assertEquals(5, linkedList.get(4));
          assertEquals(6, linkedList.get(5));
          assertEquals(7, linkedList.get(6));
          assertEquals(8, linkedList.get(7));
          assertEquals(9, linkedList.get(8));

          linkedList.remove(5);
          assertEquals(8, linkedList.size());
          assertEquals(1, linkedList.get(0));
          assertEquals(2, linkedList.get(1));
          assertEquals(3, linkedList.get(2));
          assertEquals(4, linkedList.get(3));
          assertEquals(5, linkedList.get(4));
          assertEquals(7, linkedList.get(5));
          assertEquals(8, linkedList.get(6));
          assertEquals(9, linkedList.get(7));

          linkedList.remove(2);
          assertEquals(7, linkedList.size());
          assertEquals(1, linkedList.get(0));
          assertEquals(2, linkedList.get(1));
          assertEquals(4, linkedList.get(2));
          assertEquals(5, linkedList.get(3));
          assertEquals(7, linkedList.get(4));
          assertEquals(8, linkedList.get(5));
          assertEquals(9, linkedList.get(6));

          linkedList.remove(4);
          linkedList.remove(0);
          assertEquals(5, linkedList.size());
          assertEquals(2, linkedList.get(0));
          assertEquals(4, linkedList.get(1));
          assertEquals(5, linkedList.get(2));
          assertEquals(8, linkedList.get(3));
          assertEquals(9, linkedList.get(4));

          linkedList.remove(0);
          linkedList.remove(0);
          linkedList.remove(0);
          linkedList.remove(0);
          assertEquals(1, linkedList.size());
          assertEquals(9, linkedList.get(0));
        }
      }
    }

    @Nested
    class TestIterator {
      Iterator<Integer> iterator;

      @BeforeEach
      @Description("List iterator instance")
      void iterator() {
        iterator = linkedList.iterator();
      }

      @Test
      @Description("Return null if iterator next on empty list")
      void returnNullOnEmptyListNext() {
        assertNull(iterator.next());
      }

      @Test
      @Description("Return null if iterator next on empty list")
      void isNextElementAvailable() {
        assertFalse(iterator.hasNext());
      }

      @Test
      @Description("Check if size is 0")
      void checkSizeZero() {
        assertEquals(0, linkedList.size());
      }

      @Nested
      class IteratorWithOneItem {
        int randNumber = (int) (Math.random() * 100);

        @BeforeEach
        void addItem() {
          linkedList.addFirst(randNumber);
        }

        @BeforeEach
        @Description("Re instantiate iterator to get updated list reference")
        void iterator() {
          iterator = linkedList.iterator();
        }

        @Test
        @Description("Iterator get first item")
        void getNextItem() {
          assertEquals(randNumber, iterator.next());
        }

        @Test
        @Description("Iterator hasNext must return true if element exists")
        void isNextAvailable() {
          assertTrue(iterator.hasNext());
        }

        @Nested
        class PopulatedListIterator {
          int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

          @BeforeEach
          void populateList() {
            linkedList.empty();

            for(int item: intArr) {
              linkedList.addLast(item);
            }
          }

          @BeforeEach
          @Description("Re instantiate iterator to get updated list reference")
          void iterator() {
            iterator = linkedList.iterator();
          }

          @Test
          @Description("Iterator get items")
          void getNextItem() {
            for(int item: intArr) {
              assertEquals(item, iterator.next());
            }
          }

          @Test
          @Description("Iterator get items")
          void isNextItemAvailable() {
            for(int item: intArr) {
              assertTrue(iterator.hasNext());

              iterator.next();
            }

            assertFalse(iterator.hasNext());
          }

        }
      }

    }

    @Nested
    class TestReverseIterator {
      Iterator<Integer> iterator;

      @BeforeEach
      @Description("List reverse iterator instance")
      void iterator() {
        iterator = linkedList.reverseIterator();
      }

      @Test
      @Description("Return null if iterator next on empty list")
      void returnNullOnEmptyListNext() {
        assertNull(iterator.next());
      }

      @Test
      @Description("Return null if iterator next on empty list")
      void isNextElementAvailable() {
        assertFalse(iterator.hasNext());
      }

      @Test
      @Description("Check if size is 0")
      void checkSizeZero() {
        assertEquals(0, linkedList.size());
      }

      @Nested
      class ReverseIteratorWithOneItem {
        int randNumber = (int) (Math.random() * 100);

        @BeforeEach
        void addItem() {
          linkedList.addFirst(randNumber);
        }

        @BeforeEach
        @Description("Re instantiate reverse iterator to get updated list reference")
        void iterator() {
          iterator = linkedList.reverseIterator();
        }

        @Test
        @Description("Iterator get first item")
        void getNextItem() {
          assertEquals(randNumber, iterator.next());
        }

        @Test
        @Description("Iterator hasNext must return true if element exists")
        void isNextAvailable() {
          assertTrue(iterator.hasNext());
        }

        @Nested
        class PopulatedListIterator {
          int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

          @BeforeEach
          void populateList() {
            linkedList.empty();

            for(int item: intArr) {
              linkedList.addLast(item);
            }
          }

          @BeforeEach
          @Description("Re instantiate reverse iterator to get updated list reference")
          void iterator() {
            iterator = linkedList.reverseIterator();
          }

          @Test
          @Description("Iterator get items")
          void getNextItem() {
            for(int i = intArr.length - 1; i >= 0; i --) {
              assertEquals(intArr[i], iterator.next());
            }
          }

          @Test
          @Description("Iterator get items")
          void isNextItemAvailable() {
            for(int i = intArr.length - 1; i >= 0; i --) {
              assertTrue(iterator.hasNext());

              iterator.next();
            }

            assertFalse(iterator.hasNext());
          }
        }
      }
    }
  }
}