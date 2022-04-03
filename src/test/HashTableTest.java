package test;

import jdk.jfr.Description;
import main.company.SWEngineer;
import main.company.utility.HashTable;
import main.company.utility.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

  private HashTable<Integer, String> hashtable;
  private final Random random = new Random();

  @Test
  void isInstantiatedWithNew() {
    new HashTable<>();
  }

  @Nested
  class WhenNewTest {

    @BeforeEach()
    @Description("Initialize empty hash table")
    void createNewHashTable() {
      hashtable = new HashTable<>();
    }

    @Test
    @Description("At start hashtable must empty")
    void isEmpty() {
      assertTrue(hashtable.isEmpty());
      assertEquals(0, hashtable.size());
    }

    @Test
    @Description("Empty hashtable must return null every time we try to remove an element")
    void removeRandomElement() {
      int index = random.nextInt();

      assertNull(hashtable.remove(index));
      assertEquals(0, hashtable.size());
      assertTrue(hashtable.isEmpty());
    }

    @Test
    @Description("Empty hashtable must return null every time we try to get an element")
    void getRandomElement() {
      int index = random.nextInt();

      assertNull(hashtable.get(index));
      assertEquals(0, hashtable.size());
      assertTrue(hashtable.isEmpty());
    }

    @Nested
    class WhenAddOneElementTest {
      private int index = random.nextInt();
      private String value = "David";

      @BeforeEach()
      @Description("Add one element")
      void populateHashTable() {
        hashtable.put(index, value);
      }

      @Test
      @Description("Hashtable have size of 1 after adding 1 element")
      void isSizeEqualToOne() {
        assertEquals(1, hashtable.size());
      }

      @Test
      @Description("Hashtable must not be empty after adding element")
      void isEmptyAfterAddition() {
        assertFalse(hashtable.isEmpty());
      }

      @Test
      @Description("Hashtable must return element after getting element")
      void getAddedElement() {
        assertEquals(value, hashtable.get(index));
      }

      @Test
      @Description("Remove element from hash table, must return itself and hashtable must be empty")
      void removeAddedElement() {
        assertEquals(value, hashtable.remove(index));
        assertNull(hashtable.get(index));
        assertTrue(hashtable.isEmpty());
        assertEquals(0, hashtable.size());
      }

      @Test
      @Description("Empty hashtable")
      void emptyElements() {
        hashtable.empty();
        assertTrue(hashtable.isEmpty());
        assertNull(hashtable.get(index));
        assertEquals(0, hashtable.size());
      }
    }

    @Nested
    class WhenAddMultipleElementTest {
      private int[] indexes = {random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt()};
      private String[] values = {"David", "Janna", "Petros", "Ani"};

      @BeforeEach()
      @Description("Add elements")
      void addMultipleElements() {
        for (int i = 0; i < values.length; i++) {
          hashtable.put(indexes[i], values[i]);
        }
      }

      @Test
      @Description("Hashtable size must be equal to values array size")
      void isSizeEqualToValuesArraySize() {
        assertEquals(values.length, hashtable.size());
      }

      @Test
      @Description("Hashtable must not be empty after adding elements")
      void isEmptyAfterAddition() {
        assertFalse(hashtable.isEmpty());
      }

      @Nested
      class WhenFullHashTableIteratorTest {
        Iterator<HashTable.HashEntry<Integer, String>> iterator;

        @BeforeEach
        void initIterator() {
          iterator = hashtable.iterator();
        }

        @Test
        void startIteration() {
          int index = 0;

          while (iterator.hasNext()) {
            HashTable.HashEntry<Integer, String> hashEntry = iterator.next();

            assertTrue(hashtable.containsValue(hashEntry.getValue()));
            assertTrue(hashtable.containsKey(hashEntry.getKey()));
            index ++;
          }

          assertEquals(index, hashtable.size());
        }
      }

      @Nested
      class WhenDeleteOperationsTest {
        @Test
        @Description("Remove elements from hash table, must return itself and hashtable must be empty")
        void removeElements() {
          for (int i = 0; i < values.length; i ++) {
            assertEquals(values[i], hashtable.remove(indexes[i]));
            assertNull(hashtable.get(indexes[i]));
            assertEquals(values.length - 1 - i, hashtable.size());

            if (i == values.length - 1) {
              assertTrue(hashtable.isEmpty());
            } else {
              assertFalse(hashtable.isEmpty());
            }

            for (int j = i + 1; j < values.length; j ++) {
              assertEquals(values[j], hashtable.get(indexes[j]));
            }
          }

          assertEquals(0, hashtable.size());
          assertTrue(hashtable.isEmpty());
        }

        @Test
        @Description("Empty hashtable")
        void emptyElements() {
          hashtable.empty();
          assertEquals(0, hashtable.size());
          assertTrue(hashtable.isEmpty());
        }
      }
    }
  }
}