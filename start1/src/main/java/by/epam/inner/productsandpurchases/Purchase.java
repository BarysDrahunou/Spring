package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.*;

public class Purchase {
    private final Product product;
    private final int number;

    public Purchase(Product product, int number) {
        if (number <= 0) {
            throw new NegativeArgumentException("Number must be more than 0", number);
        }
        this.product = product;
        this.number = number;
    }

    public Purchase(Product product, String number) {
        this(product, checkString(number));
    }

    protected static int checkString(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Incorrect format for number");
        }
    }

    public int getNumber() {
        return number;
    }

    public Product getProduct() {
        return new Product(product);
    }

    public Byn getCost() {
        return product.getPrice().multiplication(number);
    }

    @Override
    public String toString() {
        return String.format("%s;%s", fieldsToString(), getCost());
    }

    protected String fieldsToString() {
        return String.format("%s;%s", product, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;

        Purchase purchase = (Purchase) o;

        return product.equals(purchase.product);
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}
