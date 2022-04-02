package main.company.utility;

import main.company.SWEngineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
  private HashMap<SWEngineer, String> hashmap;

  void newHashMap() {
    hashmap = new HashMap<>();
  }

  @Nested
  class WhenNewTest {
    @BeforeEach()
    void newHashMap() {
      hashmap = new HashMap<>();
    }

    @Test
    void emptyHashMap() {
      assertTrue(hashmap.isEmpty());
    }

    @Test
    void isSizeZero() {
      assertEquals(0, hashmap.size());
    }

    @Test
    void getHashMapRandElem() {
      SWEngineer randomSWEngineer = new SWEngineer();

      assertNull(hashmap.remove(randomSWEngineer));
      assertNull(hashmap.get(randomSWEngineer));
    }

    @Test
    void testSize() {
      assertEquals(0, hashmap.size());
    }

    @Nested
    class WhenAddTest {

      @Test
      void addSWEngineer() {
        SWEngineer SWE = new SWEngineer();

        assertEquals("David", hashmap.put(SWE, "David"));
      }

      @Nested
      class WhenAddSuccessfulTest {
        private SWEngineer SWE;
        private String name = "David";

        @BeforeEach
        void addSWEngineer() {
          SWE = new SWEngineer();

          assertEquals(name, hashmap.put(SWE, name));
        }

        @Test
        void checkSize () {
          assertEquals(1, hashmap.size());
        }

        @Test
        void checkEmpty() {
          assertFalse(hashmap.isEmpty());
        }

        @Test
        void getSWEngineer() {
          assertEquals(name, hashmap.get(SWE));
        }

        @Nested
        class WhenAddSimilarTest {
          @Test
          void addSimilarSWE() {
            assertNull(hashmap.put(SWE, name));
          }

          @Test
          void testSize() {
            assertEquals(1, hashmap.size());
          }

          @Test
          void checkEmpty() {
            assertFalse(hashmap.isEmpty());
          }

          @Test
          void getSWEngineer() {
            assertEquals(name, hashmap.get(SWE));
          }
        }
      }

      @Nested
      class WhenAddMultiple {
        SWEngineer[] swArr = new SWEngineer[6];
        String[] names = {"David", "Anna", "Janna", "Michale", "Karen", "Petros"};

        @BeforeEach
        void addMultipleSWE() {
          for(int i = 0; i < swArr.length; i ++) {
            swArr[i] = new SWEngineer();
          }

          for (int i = 0; i < swArr.length; i ++) {
            hashmap.put(swArr[i], names[i]);
          }
        }

        @Test
        void checkSize() {
          assertEquals(6, hashmap.size());
        }

        @Test
        void isEmpty() {
          assertFalse(hashmap.isEmpty());
        }

        @Test
        void getSWEngineers() {
          for (int i = 0; i < swArr.length; i ++) {
            assertEquals(names[i], hashmap.get(swArr[i]));
          }
        }

        @Nested
        class WhenEmptyHashMapTest {

          @BeforeEach
          void emptyHashMap() {
            hashmap.empty();
          }

          @Test
          void isEmpty() {
            assertTrue(hashmap.isEmpty());
          }

          @Test
          void checkSize() {
            assertEquals(0, hashmap.size());
          }
        }
      }
    }
  }
}