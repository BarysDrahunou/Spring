package trials;

import myexceptions.WrongArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LightTrialTest {
    LightTrial trial1;
    LightTrial trial2;

    @Before
    public void init() {
        trial1 = new LightTrial("Vasya", 12, 13);
        trial2 = new LightTrial("Dima", 60, 41);
    }

    @Test
    public void constructorTest() {
        LightTrial trial = new LightTrial(trial2);
        assertEquals(trial1.toString(), new LightTrial("Vasya", 12, 13).toString());
        assertEquals(trial2.toString(), new LightTrial(trial).toString());
    }

    @Test(expected = WrongArgumentException.class)
    public void constructorTestException() {
        try {
            LightTrial trial = new LightTrial("Vasya", -12, 13);
        } catch (WrongArgumentException e) {
            try {
                LightTrial trial = new LightTrial("Vasya", 12, 130);
            } catch (WrongArgumentException e1) {
                LightTrial trial = new LightTrial("", 12, 13);
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void constructorTestNPException() {

        LightTrial trial = new LightTrial(null, 12, 13);
    }

    @Test
    public void getClassConstantForTest1() {

        assertEquals(10, LightTrial.getClassConstantForTest1());
    }

    @Test
    public void getClassConstantForTest2() {

        assertEquals(20, LightTrial.getClassConstantForTest2());
    }

    @Test
    public void copy() {
        assertEquals(new LightTrial("Vasya", 12, 13).toString(), trial1.toString());
        assertNotEquals(new LightTrial("Egor", 60, 41).toString(), trial2.toString());
    }

    @Test
    public void isPassed() {
        assertFalse(trial1.isPassed());
        assertTrue(trial2.isPassed());
    }
}