package test;

import main.company.utility.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
  ArrayList<Object> arrayList;

  @Test
  @DisplayName("Is instantiated with new ArrayList()")
  void isInstantiatedWithNew() {
    new ArrayList<>();
  }

  @Nested
  @DisplayName("When new")
  class WhenNew {
    @BeforeEach
    void createNewArrayList() {
      arrayList = new ArrayList<>();
    }

    @Test
    @DisplayName("is empty")
    void isEmpty() {
      assertTrue(arrayList.isEmpty());
    }


    @Nested
    @DisplayName("after adding an element to list")
    class AfterAdding {

      String element = "My element";

      String[] fruits = {"apple", "mango", "grape"};

      @BeforeEach
      void pushAnElement() {
        arrayList.addFirst(element);
      }

      @Test
      @DisplayName("ArrayList is no longer empty")
      void isNotEmpty() {
        assertFalse(arrayList.isEmpty());
      }

      @Test
      @DisplayName("After removing the only element array must be empty")
      void returnElementWhenLastRemoved() {
//        assertTrue(arrayList.removeLast());
//        assertTrue(arrayList.isEmpty());
      }
    }
  }
}
