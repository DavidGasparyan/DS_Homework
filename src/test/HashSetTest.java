package test;

import main.company.SWEngineer;
import main.company.utility.HashMap;
import main.company.utility.HashSet;
import main.company.utility.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {
  private HashSet<SWEngineer> set;

  void newSet() {
    set = new HashSet<>();
  }

  @Nested
  class WhenNewTest {
    @BeforeEach()
    void newHashMap() {
      set = new HashSet<>();
    }

    @Test
    void emptyHashMap() {
      assertTrue(set.isEmpty());
    }

    @Test
    void isSizeZero() {
      assertEquals(0, set.size());
    }

    @Test
    void getHashMapRandElem() {
      SWEngineer randomSWEngineer = new SWEngineer("1", "David", "Gasparyan", "Junior");

      assertFalse(set.remove(randomSWEngineer));
      assertFalse(set.contains(randomSWEngineer));
    }

    @Test
    void testSize() {
      assertEquals(0, set.size());
    }

    @Nested
    class WhenAddTest {

      @Test
      void addSWEngineer() {
        SWEngineer SWE = new SWEngineer("1", "David", "Gasparyan", "Junior");

        assertTrue(set.add(SWE));
      }

      @Nested
      class WhenAddSuccessfulTest {
        private SWEngineer SWE;

        @BeforeEach
        void addSWEngineer() {
          SWE = new SWEngineer("1", "David", "Gasparyan", "Junior");

          set.add(SWE);
        }

        @Test
        void checkSize () {
          assertEquals(1, set.size());
        }

        @Test
        void checkEmpty() {
          assertFalse(set.isEmpty());
        }

        @Test
        void getSWEngineer() {
          assertTrue(set.contains(SWE));
        }

        @Nested
        class WhenAddSimilarTest {

          @BeforeEach
          void addSimilarSWE() {
            set.add(SWE);
          }

          @Test
          void testSize() {
            assertEquals(1, set.size());
          }

          @Test
          void checkEmpty() {
            assertFalse(set.isEmpty());
          }

          @Test
          void getSWEngineer() {
            assertTrue(set.contains(SWE));
          }
        }
      }

      @Nested
      class WhenAddMultiple {
        SWEngineer[] swArr = new SWEngineer[6];

        @BeforeEach
        void addMultipleSWE() {
          for(int i = 0; i < swArr.length; i ++) {
            String index = Integer.toString(i);
            swArr[i] = new SWEngineer(index, "David", "Gasparyan", "Junior");
          }

          for (int i = 0; i < swArr.length; i ++) {
            set.add(swArr[i]);
          }
        }

        @Test
        void checkSize() {
          assertEquals(6, set.size());
        }

        @Test
        void isEmpty() {
          assertFalse(set.isEmpty());
        }

        @Test
        void getSWEngineers() {
          for (int i = 0; i < swArr.length; i ++) {
            assertTrue(set.contains(swArr[i]));
          }
        }

        @Nested
        class WhenFullHashSetIteratorTest {
          Iterator<SWEngineer> iterator;

          @BeforeEach
          void initIterator() {
            iterator = set.iterator();
          }

          @Test
          void startIteration() {
            int index = 0;
            while (iterator.hasNext()) {
              assertTrue(set.contains(iterator.next()));
              index ++;
            }

            assertEquals(index, set.size());
          }
        }

        @Nested
        class WhenEmptyHashMapTest {

          @BeforeEach
          void emptyHashMap() {
            set.empty();
          }

          @Test
          void isEmpty() {
            assertTrue(set.isEmpty());
          }

          @Test
          void checkSize() {
            assertEquals(0, set.size());
          }
        }
      }
    }
  }
}