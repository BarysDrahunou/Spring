package trials;

import myexceptions.WrongArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExtraTrialTest {
    ExtraTrial trial1;
    ExtraTrial trial2;

    @Before
    public void init() {
        trial1 = new ExtraTrial("Vasya", 12, 13, 10);
        trial2 = new ExtraTrial("Dima", 60, 41, 88);
    }

    @Test
    public void getMark3Test() {
        assertEquals(88, trial2.getMark3());
    }

    @Test(expected = WrongArgumentException.class)
    public void getMark3ExceptionTest() {
        trial1 = new ExtraTrial("Vasya", 12, 13, 110);
        assertEquals(110, trial1.getMark3());
    }

    @Test
    public void getClassConstantForExtraTrial() {
        assertEquals(40, ExtraTrial.getClassConstant());
    }

    @Test
    public void testToString() {
        assertEquals("Vasya; His marks : 12; 13; 10; trial is passed - false", trial1.toString());
        assertEquals("Dima; His marks : 60; 41; 88; trial is passed - true", trial2.toString());
    }

    @Test
    public void clearMarks() {
        trial1.clearMarks();
        trial2.clearMarks();
        assertEquals(0, trial1.getMark1());
        assertEquals(0, trial1.getMark2());
        assertEquals(0, trial2.getMark2());
        assertEquals(0, trial2.getMark2());
        assertEquals(0, trial1.getMark3());
        assertEquals(0, trial2.getMark3());
    }

    @Test
    public void copy() {
        assertEquals(new ExtraTrial("Vasya", 12, 13, 10).toString()
                , trial1.toString());
        assertNotEquals(new ExtraTrial("Egor", 60, 41, 88).toString()
                , trial2.toString());
    }

    @Test
    public void isPassed() {
        assertFalse(trial1.isPassed());
        assertTrue(trial2.isPassed());
    }
}