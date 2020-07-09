package by.epam.inner.productsandpurchases;

public final class Product {
    private final String name;
    private final Byn price;

    public Product(String name, Byn price) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name can't be empty");
        }
        if (price.compareTo(new Byn()) == 0) {
            throw new IllegalArgumentException("Price can't be 0");
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
        return price.equals(product.price) && (name.equals(product.name));
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
