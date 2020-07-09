package by.epam.inner.factory;

import by.epam.inner.productsandpurchases.*;
import by.epam.inner.myexceptions.CsvLineException;

public class PurchasesFactory {
    public static Purchase getPurchaseFromFactory(String csv) throws CsvLineException {
        String[] values = csv.split(";");
        try {
            switch (values.length) {
                case 3:
                    return new Purchase(new Product(values[0], values[1]), values[2]);
                case 4:
                    return new PriceDiscountPurchase(new Product(values[0], values[1]), values[2], values[3]);
                default:
                    throw new CsvLineException("Incorrect input format", csv);
            }
        } catch (IllegalArgumentException e1) {
            throw new CsvLineException("Incorrect input format", csv);
        }
    }
}
