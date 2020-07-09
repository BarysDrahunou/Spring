package by.epam.inner.myexceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class WrongArgumentExceptionTest {
    WrongArgumentException wrongArgumentException=new WrongArgumentException("Problem value","Message");

    @Test
    public void testToString() {
        assertEquals("Message - Problem value",wrongArgumentException.toString());
    }
}