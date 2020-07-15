package trials;

import myexceptions.WrongArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrongTrialTest {
    StrongTrial trial1;
    StrongTrial trial2;

    @Before
    public void init() {
        trial1 = new StrongTrial("Vasya", 12, 13);
        trial2 = new StrongTrial("Dima", 60, 41);
    }

    @Test
    public void constructorTest() {
        StrongTrial trial = new StrongTrial(trial2);
        assertEquals(trial1.toString(), new StrongTrial("Vasya", 12, 13).toString());
        assertEquals(trial2.toString(), new StrongTrial(trial).toString());
    }

    @Test(expected = WrongArgumentException.class)
    public void constructorTestException() {
        try {
            StrongTrial trial = new StrongTrial("Vasya", -12, 13);
        } catch (WrongArgumentException e) {
            try {
                StrongTrial trial = new StrongTrial("Vasya", 12, 130);
            } catch (WrongArgumentException e1) {
                StrongTrial trial = new StrongTrial("", 12, 13);
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void constructorTestNPException() {

        StrongTrial trial = new StrongTrial(null, 12, 13);
    }

    @Test
    public void getClassConstant() {
        assertEquals(30, StrongTrial.getClassConstant());
    }

    @Test
    public void copy() {
        assertEquals(new StrongTrial("Vasya", 12, 13).toString(), trial1.toString());
        assertNotEquals(new StrongTrial("Egor", 60, 41).toString(), trial2.toString());
    }

    @Test
    public void isPassed() {
        assertFalse(trial1.isPassed());
        assertTrue(trial2.isPassed());
    }
}