package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTest {
    Purchase purchase1;
    Purchase purchase2;
    Purchase purchase3;
    Purchase purchase4;

    @Before
    public void init() {
        purchase1 = new Purchase(new Product("milk", 20), 2);
        purchase2 = new Purchase(new Product("water", new Byn("1.20")), "6");
        purchase3 = new Purchase(new Product("milk", new Byn(0, 20)), "6");
        purchase4 = new Purchase(new Product("water", new Byn(1, 0)), "6");
    }

    @Test
    public void getNumberTest() {
        assertEquals(2, purchase1.getNumber());
        assertEquals(6, purchase2.getNumber());
    }

    @Test(expected = NegativeArgumentException.class)
    public void getNumberTestException() {
        purchase1 = new Purchase(new Product("milk",200), -2);
    }

    @Test
    public void getProductTest() {
        assertEquals(new Product("milk", new Byn("0.20")), purchase1.getProduct());
        assertEquals(new Product("water", new Byn("1.20")), purchase2.getProduct());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductTestException() {
        purchase1 = new Purchase(new Product("milk", "200"), 2);
    }

    @Test
    public void getCostTest() {
        assertEquals(new Byn("0.40"), purchase1.getCost());
        assertEquals(new Byn("7.20"), purchase2.getCost());

    }

    @Test
    public void toStringTest() {
        assertEquals("milk;0.20;2;0.40", purchase1.toString());
        assertEquals("water;1.20;6;7.20", purchase2.toString());
    }

    @Test
    public void fieldsToStringTest() {
        assertEquals("milk;0.20;2", purchase1.fieldsToString());
        assertEquals("water;1.20;6", purchase2.fieldsToString());
    }

    @Test
    public void equalsTest() {
        assertEquals(purchase1, purchase3);
        assertNotEquals(purchase2, purchase4);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(purchase1.hashCode(), purchase3.hashCode());
        assertNotEquals(purchase2.hashCode(), purchase4.hashCode());
    }
}