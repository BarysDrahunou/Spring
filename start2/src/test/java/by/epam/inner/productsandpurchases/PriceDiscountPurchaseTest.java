package by.epam.inner.productsandpurchases;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceDiscountPurchaseTest {

    PriceDiscountPurchase priceDiscountPurchase1;
    PriceDiscountPurchase priceDiscountPurchase2;

    @Test
    public void getCostTest() {
        priceDiscountPurchase1 = new PriceDiscountPurchase(
                new Product("Bread", "2.20"), 5, new Byn("1.20"));
        priceDiscountPurchase2 = new PriceDiscountPurchase(
                new Product("Milk", 100), 1, new Byn("0.08"));
        Byn result1 = priceDiscountPurchase1.getCost();
        Byn result2 = priceDiscountPurchase2.getCost();
        assertEquals(result1, new Byn("5.00"));
        assertEquals(result2, new Byn("0.92"));
    }

    @Test
    public void fieldsToStringTest() {
        priceDiscountPurchase1 = new PriceDiscountPurchase(
                new Product("Bread", "2.20"), 5, new Byn("1.20"));
        assertEquals("Bread;2.20;5;1.20",priceDiscountPurchase1.fieldsToString());
    }
}