package by.epam.inner.productsandpurchases;

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
        purchase2 = new Purchase(new Product("water", new Byn("1.20")), 6);
        purchase3 = new Purchase(new Product("milk", new Byn(0, 20)), 6);
        purchase4 = new Purchase(new Product("water", new Byn(1, 0)), 6);
    }

    @Test
    public void getNumber() {
        assertEquals(2, purchase1.getNumber());
        assertNotEquals(3, purchase1.getNumber());
    }

    @Test
    public void getProduct() {
        assertEquals(new Product("milk", 20), purchase1.getProduct());
        assertNotEquals("milk", 40, purchase1.getProduct());
    }

    @Test
    public void getCost() {
        assertEquals(new Byn(40), purchase1.getCost());
        assertNotEquals(new Byn(50), purchase1.getCost());
    }

    @Test
    public void testToString() {
        assertEquals("milk;0.20;2;0.40", purchase1.toString());
        assertEquals("water;1.20;6;7.20", purchase2.toString());
    }

    @Test
    public void fieldsToString() {
        assertEquals("milk;0.20;2", purchase1.fieldsToString());
        assertEquals("water;1.20;6", purchase2.fieldsToString());
    }

    @Test
    public void testEquals() {
        assertEquals(purchase1, purchase3);
        assertNotEquals(purchase2, purchase4);
    }

    @Test
    public void testHashCode() {
        assertEquals(purchase1.hashCode(), purchase3.hashCode());
        assertNotEquals(purchase2.hashCode(), purchase4.hashCode());
    }
}