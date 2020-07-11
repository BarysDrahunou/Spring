package by.epam.inner.productsandpurchases;

import by.epam.inner.myexceptions.*;

import java.util.Optional;
import java.util.regex.*;

public final class Byn implements Comparable<Byn> {
    private final int value;
    private static final Pattern PATTERN = Pattern.compile("^[\\d]+[.][\\d]{2}$");
    private static final int CONVERTER = 100;

    public Byn(int value) {
        if (value < 0) {
            throw new WrongArgumentException("Value can't be less than 0"
                    , String.valueOf(value));
        }
        this.value = value;
    }

    public Byn() {
        this(0);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn(int rubs, int coins) {
        this(getIntValue(rubs, coins)
                .orElseThrow(() -> new WrongArgumentException("Wrong amount", rubs + ";" + coins)));
    }

    public Byn(String byn) {
        this(getIntValue(byn)
                .orElseThrow(() -> new WrongArgumentException("Incorrect string format", byn)));
    }

    private static Optional<Integer> getIntValue(String strByn) {
        Matcher matcher = PATTERN.matcher(strByn);
        if (!(matcher.matches())) {
            return Optional.empty();
        }
        String[] array = strByn.split("\\.");
        int rubs = Integer.parseInt(array[0]);
        int coins = Integer.parseInt(array[1]);
        return Optional.of(rubs * CONVERTER + coins);
    }


    private static Optional<Integer> getIntValue(int rubs, int coins) {
        if (rubs < 0 || coins < 0 || coins > 99) {
            return Optional.empty();
        }
        return Optional.of(rubs * CONVERTER + coins);
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

    public Byn multiplicationWithDouble(double number) {
        return new Byn((int) Math.round(this.value * number));
    }

    @Override
    public String toString() {
        return String.format("%s.%02d", value / CONVERTER, value % CONVERTER);
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
