package by.epam.inner.factory;

import by.epam.inner.myexceptions.WrongArgumentException;
import by.epam.inner.productsandpurchases.*;
import org.apache.logging.log4j.*;

import java.util.Optional;

public class PurchasesFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private enum PurchaseKind {
        PURCHASE {
            @Override
            Purchase getPurchase(String csv) {
                return new Purchase(csv);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String csv) {
                return new PriceDiscountPurchase(csv);
            }
        },
        PERCENT_DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String csv) {
                return new PercentDiscountPurchase(csv);
            }
        };

        abstract Purchase getPurchase(String csv);
    }

    public static Optional<Purchase> getPurchaseFromFactory(String csv) {
        try {
            String[] values = csv.split(";", 2);
            if (values.length < 2) {
                LOGGER.error(String.format("Incorrect csv-file format - %s", csv));
                return Optional.empty();
            }
            String className = values[0].replaceAll("([^_])([A-Z])", "$1_$2").toUpperCase();
                return Optional.of(PurchaseKind.valueOf(className).getPurchase(values[1]));
        }
        catch (IllegalArgumentException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
    }


}
