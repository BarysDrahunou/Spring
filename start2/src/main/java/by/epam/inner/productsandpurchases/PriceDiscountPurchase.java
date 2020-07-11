package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.WrongArgumentException;

import java.util.Objects;
import java.util.Optional;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase(Product product, int number, Byn discount) {
        super(product, number);
        Objects.requireNonNull(discount);
        if (discount.compareTo(new Byn()) == 0) {
            throw new WrongArgumentException("Discount can't be 0", discount.toString());
        }
        this.discount = discount;
    }

    public PriceDiscountPurchase(String csv) {
        this(getFromCsv(csv).orElseThrow(() -> new WrongArgumentException("Incorrect input format", csv)));
    }
    public PriceDiscountPurchase(PriceDiscountPurchase priceDiscountPurchase) {
        this(priceDiscountPurchase.getProduct(), priceDiscountPurchase.getNumber(),priceDiscountPurchase.getDiscount());
    }
    private static Optional<PriceDiscountPurchase> getFromCsv(String csv) {
        try {
            String[] csvArray = csv.split(";");
            if (csvArray.length == 4) {
                return Optional.of(new PriceDiscountPurchase(new Product(csvArray[0], csvArray[1])
                        , Integer.parseInt(csvArray[2]), new Byn(csvArray[3])));
            } else {
                return Optional.empty();
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Byn getDiscount() {
        return new Byn(discount);
    }

    @Override
    public Byn getCost() {
        return super.getCost().subtraction(discount.multiplication(getNumber()));
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s;%s", super.fieldsToString(), getDiscount());
    }
}
