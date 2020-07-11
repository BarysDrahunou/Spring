package by.epam.inner.productsandpurchases;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercentDiscountPurchaseTest {

    PercentDiscountPurchase percentDiscountPurchase1;
    PercentDiscountPurchase percentDiscountPurchase2;

    @Test
    public void getCostTest() {
        percentDiscountPurchase1 = new PercentDiscountPurchase(
                new Product("Bread", "2.20"), 5, 50.00);
        percentDiscountPurchase2 = new PercentDiscountPurchase(
                new Product("Milk", 100), 1, 50.00);
        Byn result1 = percentDiscountPurchase1.getCost();
        Byn result2 = percentDiscountPurchase2.getCost();
        assertEquals(result1, new Byn("11.00"));
        assertEquals(result2, new Byn("1.00"));
    }

    @Test
    public void fieldsToStringTest() {
        percentDiscountPurchase1 = new PercentDiscountPurchase(
                new Product("Bread", "2.20"), 5, 50);
        assertEquals("Bread;2.20;5;50.0",percentDiscountPurchase1.fieldsToString());
    }
}