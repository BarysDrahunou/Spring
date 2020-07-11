package by.epam.inner.factory;

import by.epam.inner.productsandpurchases.PercentDiscountPurchase;
import by.epam.inner.productsandpurchases.PriceDiscountPurchase;
import by.epam.inner.productsandpurchases.Product;
import by.epam.inner.productsandpurchases.Purchase;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class PurchasesFactoryTest {
    Purchase purchase;
    PercentDiscountPurchase percentDiscountPurchase;
    PriceDiscountPurchase priceDiscountPurchase;

    @Before
    public void init() {
        purchase = new Purchase(new Product("bread", 1, 11), 3);
        percentDiscountPurchase = new PercentDiscountPurchase("water;2.00;5;20");
        priceDiscountPurchase = new PriceDiscountPurchase("milk;3.20;1;2.00");
    }

    @Test
    public void getPurchaseFromFactory() {
        Purchase purchase1 = PurchasesFactory.getPurchaseFromFactory("Purchase;bread;1.11;3").orElse(null);
        Purchase percentDiscountPurchase1 = PurchasesFactory.getPurchaseFromFactory("PercentDiscountPurchase;water;2.00;5;20").orElse(null);
        Purchase priceDiscountPurchase1 = PurchasesFactory.getPurchaseFromFactory("PriceDiscountPurchase;milk;3.20;1;2.00").orElse(null);
        assertEquals(purchase, purchase1);
        assertEquals(percentDiscountPurchase, percentDiscountPurchase1);
        assertEquals(priceDiscountPurchase, priceDiscountPurchase1);
        assertEquals(Optional.empty(), PurchasesFactory.getPurchaseFromFactory("Purchase,bread,1.11,3"));
        assertEquals(Optional.empty(), PurchasesFactory.getPurchaseFromFactory("Purchase;bread,3"));
        assertEquals(Optional.empty(), PurchasesFactory.getPurchaseFromFactory(""));
        assertEquals(Optional.empty(), PurchasesFactory.getPurchaseFromFactory("AnotherTypeOfPurchase;bread;1.11;3"));
    }
}