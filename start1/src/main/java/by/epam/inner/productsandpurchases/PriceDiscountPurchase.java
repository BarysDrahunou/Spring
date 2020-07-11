package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.NegativeArgumentException;

public final class PriceDiscountPurchase extends Purchase {
    private final Byn discount;

    public PriceDiscountPurchase(Product product, int number, Byn discount) {
        super(product, number);
        checkDiscount(discount, product.getPrice());
        this.discount = discount;
    }

    public PriceDiscountPurchase(Product product, String number, String discount) {
        this(product, checkString(number), new Byn(discount));
    }

    private static void checkDiscount(Byn discount, Byn price) {
        if (discount.compareTo(price) >= 0) {
            throw new NegativeArgumentException("Discount can't be more or equal than price", price.compareTo(discount));
        }
    }

    @Override
    public Byn getCost() {
        return super.getCost().subtraction(discount.multiplication(getNumber()));
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s;%s", super.fieldsToString(), discount);
    }
}
