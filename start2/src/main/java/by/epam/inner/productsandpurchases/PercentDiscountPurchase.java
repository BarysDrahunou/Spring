package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.WrongArgumentException;

import java.util.Optional;

public final class PercentDiscountPurchase extends Purchase {
    private final double discount;
    private static final int MIN_NUMBER_OF_PURCHASES = 15;

    public PercentDiscountPurchase(Product product, int number, double discount) {
        super(product, number);
        if (discount <= 0 || discount >= 100) {
            throw new WrongArgumentException("Discount can't be less than 0 or more than 100", String.valueOf(discount));
        }
        this.discount = discount;
    }

    public PercentDiscountPurchase(PercentDiscountPurchase percentDiscountPurchase) {
        this(percentDiscountPurchase.getProduct(), percentDiscountPurchase.getNumber(), percentDiscountPurchase.getDiscount());
    }

    public PercentDiscountPurchase(String csv) {
        this(getFromCsv(csv).orElseThrow(() -> new WrongArgumentException("Incorrect input format", csv)));
    }

    public double getDiscount() {
        return discount;
    }

    private static Optional<PercentDiscountPurchase> getFromCsv(String csv) {
        try {
            String[] csvArray = csv.split(";");
            if (csvArray.length == 4) {
                return Optional.of(new PercentDiscountPurchase(new Product(csvArray[0], csvArray[1])
                        , Integer.parseInt(csvArray[2]), Double.parseDouble(csvArray[3])));
            } else {
                return Optional.empty();
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }


    @Override
    public Byn getCost() {
        if (super.getNumber() >= MIN_NUMBER_OF_PURCHASES) {
            return super.getCost().multiplicationWithDouble((100 - discount) / 100);
        } else {
            return super.getCost();
        }
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s;%s", super.fieldsToString(), discount);
    }
}
