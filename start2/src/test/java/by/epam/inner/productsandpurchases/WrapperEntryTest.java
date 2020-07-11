package by.epam.inner.productsandpurchases;

import by.epam.inner.date.WrapperDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WrapperEntryTest {
    WrapperEntry wrapperEntry1;
    WrapperEntry wrapperEntry2;

    @Before
    public void init() {
        wrapperEntry1 = new WrapperEntry(new WrapperDate("18-12-18"),
                new Purchase("milk;1.20;5"));
        wrapperEntry2 = new WrapperEntry(new WrapperDate("20-07-01"),
                new PercentDiscountPurchase("water;1.00;6;20"));
    }

    @Test
    public void getDate() {
        WrapperDate date1 = new WrapperDate("18-12-18");
        WrapperDate date2 = new WrapperDate("20-07-02");
        assertEquals(date1.toString(), wrapperEntry1.getDate().toString());
        assertNotEquals(date2.toString(), wrapperEntry2.getDate().toString());
    }

    @Test
    public void getPurchase() {
        Purchase purchase = new Purchase(new Product("milk", 1, 20), 5);
        PercentDiscountPurchase percentDiscountPurchase = new PercentDiscountPurchase(
                new Product("water", 200), 6, 20);
        assertEquals(purchase, wrapperEntry1.getPurchase());
        assertNotEquals(percentDiscountPurchase, wrapperEntry2.getPurchase());
    }

}