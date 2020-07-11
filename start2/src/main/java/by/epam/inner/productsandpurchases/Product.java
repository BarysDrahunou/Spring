package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.*;

import java.util.Objects;

public final class Product {
    private final String name;
    private final Byn price;

    public Product(String name, Byn price) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);
        if (name.isEmpty()) {
            throw new WrongArgumentException("String can't be empty", name);
        }
        if (price.compareTo(new Byn()) == 0) {
            throw new WrongArgumentException("Price can't be empty", price.toString());
        }
        this.name = name;
        this.price = price;
    }

    public Product(Product product) {
        this(product.name, product.price);
    }

    public Product(String name, int price) {
        this(name, new Byn(price));
    }

    public Product(String name, int rubs, int coins) {
        this(name, new Byn(rubs, coins));
    }

    public Product(String name, String price) {
        this(name, new Byn(price));
    }

    public Byn getPrice() {
        return new Byn(price);
    }

    public String getName() {
        return new String(name);
    }

    @Override
    public String toString() {
        return String.format("%s;%s", name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
