package jpower.core.test;

import jpower.core.MultiMap;
import org.junit.Before;
import org.junit.Test;

public class MultiMapTest {
    private MultiMap<String, String> multiMap;

    @Before
    public void beforeTest() {
        multiMap = new MultiMap<String, String>();
    }

    @Test
    public void testAdd() {
        multiMap.add("Tests", "Test1");
        multiMap.add("Tests", "Test2");

    }
}
