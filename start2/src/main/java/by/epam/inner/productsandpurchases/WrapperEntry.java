package by.epam.inner.productsandpurchases;

import by.epam.inner.date.WrapperDate;
import by.epam.inner.factory.PurchasesFactory;
import org.apache.logging.log4j.*;

import java.util.Optional;

public final class WrapperEntry {

    private final WrapperDate date;
    private final Purchase purchase;
    private static final Logger LOGGER = LogManager.getLogger();

    public WrapperEntry(WrapperDate date, Purchase purchase) {
        this.date = date;
        this.purchase = purchase;
    }

    public WrapperDate getDate() {
        return new WrapperEntry(date, purchase).date;
    }

    public Purchase getPurchase() {
        return new WrapperEntry(date, purchase).purchase;
    }

    public static Optional<WrapperEntry> getWrapperEntryFromCsv(String csv) {
        String[] values = csv.split(";", 2);
        if (values.length < 2) {
            LOGGER.error(String.format("Incorrect csv-file format - %s", csv));
            return Optional.empty();
        }
        Optional<WrapperDate> date = WrapperDate.getDateFromCsv(values[0]);
        Optional<Purchase> purchase = PurchasesFactory.getPurchaseFromFactory(values[1]);
        if (date.isEmpty() || purchase.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(new WrapperEntry(date.get(), purchase.get()));
        }
    }
}
