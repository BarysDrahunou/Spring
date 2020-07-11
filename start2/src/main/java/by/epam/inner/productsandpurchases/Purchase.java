package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.*;

import java.util.Objects;
import java.util.Optional;

public class Purchase {
    private final Product product;
    private final int number;

    public Purchase(Product product, int number) {
        Objects.requireNonNull(product);
        if (number <= 0) {
            throw new WrongArgumentException("Number must be more than 0", String.valueOf(number));
        }
        this.product = product;
        this.number = number;
    }

    public Purchase(Purchase purchase) {
        this(purchase.getProduct(), purchase.getNumber());
    }

    private static Optional<Purchase> getFromCsv(String csv) {
        try {
            String[] csvArray = csv.split(";");
            if (csvArray.length == 3) {
                return Optional.of(new Purchase(new Product(csvArray[0], csvArray[1]), Integer.parseInt(csvArray[2])));
            } else {
                return Optional.empty();
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Purchase(String csv) {
        this(getFromCsv(csv).orElseThrow(() -> new WrongArgumentException("Incorrect input format", csv)));
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

        return Objects.equals(product, purchase.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
