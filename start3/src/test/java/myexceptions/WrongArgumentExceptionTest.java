package myexceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class WrongArgumentExceptionTest {
    WrongArgumentException wrongArgumentException = new WrongArgumentException("Problem value", 100);

    @Test
    public void testToString() {
        assertEquals(String.format("%d - Problem value", 100),wrongArgumentException.toString());
    }
}