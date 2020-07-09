package by.epam.inner.productsandpurchases;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    Product product1;
    Product product2;
    Product product3;
    Product product4;

    @Before
    public void init() {
        product1 = new Product("bread", 100);
        product2 = new Product("milk", 8, 89);
        product3 = new Product("bread", new Byn("1.00"));
        product4 = new Product("water", new Byn(345));
    }
    @Test
    public void getPriceTest() {
        assertEquals(new Byn("1.00"), product1.getPrice());
        assertEquals(new Byn("3.45"), product4.getPrice());
    }

    @Test
    public void getNameTest() {
        assertEquals("milk", product2.getName());
        assertEquals("water", product4.getName());
    }

    @Test
    public void toStringTest() {
        assertEquals("bread;1.00", product1.toString());
        assertEquals("water;3.45", product4.toString());
    }

    @Test
    public void equalsTest() {
        assertEquals(product1, product3);
        assertNotEquals(product2, product4);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(product1.hashCode(), product3.hashCode());
        assertNotEquals(product2.hashCode(), product4.hashCode());
    }
}