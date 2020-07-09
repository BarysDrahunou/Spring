package by.epam.inner.factory;

import by.epam.inner.myexceptions.*;
import by.epam.inner.productsandpurchases.*;
import org.junit.Test;

import static by.epam.inner.factory.PurchasesFactory.getPurchaseFromFactory;
import static org.junit.Assert.*;

public class PurchasesFactoryTest {

    @Test
    public void getPurchaseFromFactoryTest() throws CsvLineException {
        assertEquals(new Purchase(new Product("water", 100), 5)
                , getPurchaseFromFactory("water;1.00;5"));
        assertEquals(new PriceDiscountPurchase(new Product("bread", 900),
                        1, new Byn("2.00"))
                , getPurchaseFromFactory("bread;9.00;1;2.00"));
    }

    @Test(expected = CsvLineException.class)
    public void getPurchaseFromFactoryTestCsvException() throws CsvLineException {
        getPurchaseFromFactory("water;1.00");
    }
    @Test(expected = NegativeArgumentException.class)
    public void getPurchaseFromFactoryTestNegativeArgException() throws CsvLineException {
        getPurchaseFromFactory("water;-5.00;5");
    }
}