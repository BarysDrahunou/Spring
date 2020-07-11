package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Byn implements Comparable<Byn> {
    private final int value;
    private static final Pattern PATTERN = Pattern.compile("^[\\d]+[.][\\d]{2}$");

    public Byn(int value) {
        if (value < 0) {
            throw new NegativeArgumentException("Value can't be less than 0", value);
        }
        this.value = value;
    }

    public Byn() {
        this(0);
    }

    public Byn(Byn byn) {
        this(getIntValue(String.valueOf(byn)));
    }

    public Byn(int rubs, int coins) {
        this(getIntValue(rubs, coins));
    }

    public Byn(String byn) {
        this(getIntValue(byn));
    }

    private static int getIntValue(String strByn) {
        Matcher matcher = PATTERN.matcher(strByn);
        if (!(matcher.matches())) {
            throw new IllegalArgumentException("Incorrect string line");
        }
        return getIntValue(Integer.parseInt(strByn.split("\\.")[0]), Integer.parseInt(strByn.split("\\.")[1]));
    }

    private static int getIntValue(int rubs, int coins) {
        if (coins < 0) {
            throw new NegativeArgumentException("coins can't be less than 0", coins);
        }
        if (coins > 99) {
            throw new NegativeArgumentException("coins can't be more than 99", coins);
        }
        return 100 * rubs + coins;
    }

    public Byn addition(Byn byn) {
        return new Byn(this.value + byn.value);
    }

    public Byn subtraction(Byn byn) {
        return new Byn(this.value - byn.value);
    }

    public Byn multiplication(int number) {
        return new Byn(this.value * number);
    }

    @Override
    public String toString() {
        int CONSTANT = 100;
        return String.format("%s.%02d", value / CONSTANT, value % CONSTANT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Byn)) return false;

        Byn byn1 = (Byn) o;

        return value == byn1.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(Byn o) {
        return this.value - o.value;
    }
}
