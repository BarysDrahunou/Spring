package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.NegativeArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BynTest {
    Byn byn1;
    Byn byn2;
    Byn byn3;
    Byn byn4;
    Byn byn5;

    @Before
    public void init() {
        byn1 = new Byn(100);
        byn2 = new Byn(0);
        byn3 = new Byn(byn1);
        byn4 = new Byn(12, 45);
        byn5 = new Byn("1.20");
    }

    @Test
    public void additionTest() {
        Byn result1 = byn1.addition(byn2);
        Byn result2 = byn3.addition(byn4);
        assertEquals(result1, new Byn(100));
        assertEquals(result2, new Byn("13.45"));
    }

    @Test
    public void subtractionTest() {
        Byn result1 = byn1.subtraction(byn2);
        Byn result2 = byn4.subtraction(byn5);
        assertEquals(result1, new Byn(100));
        assertEquals(result2, new Byn("11.25"));
    }

    @Test(expected = NegativeArgumentException.class)
    public void subtractionTestException() {
        byn1.subtraction(byn4);
    }

    @Test
    public void multiplication() {
        Byn result1 = byn1.multiplication(9);
        Byn result2 = byn4.multiplication(4);
        assertEquals(result1, new Byn(900));
        assertEquals(result2, new Byn("49.80"));
    }


    @Test
    public void testEquals() {
        assertEquals(byn1, new Byn(1, 0));
        assertEquals(byn3, new Byn("1.00"));
        assertEquals(byn4, new Byn(1245));
        assertNotEquals(byn4, new Byn(1246));
    }

    @Test
    public void testHashCode() {
        assertEquals(byn1.hashCode(), new Byn(1, 0).hashCode());
        assertEquals(byn3.hashCode(), new Byn("1.00").hashCode());
        assertEquals(byn4.hashCode(), new Byn(1245).hashCode());
        assertNotEquals(byn4.hashCode(), new Byn(1246).hashCode());
    }

    @Test
    public void compareTo() {
        assertEquals(0, new Byn(1, 0).compareTo(byn1));
        assertEquals(0, new Byn("1.00").compareTo(byn3));
        assertEquals(-2, new Byn(1243).compareTo(byn4));
        assertEquals(1, new Byn(1246).compareTo(byn4));
    }
}