package Trainee;

import Myexceptions.WrongArgumentException;

import java.util.Objects;

public class Trainee {
    private final String NAME;
    private final int MARK1;
    private final int MARK2;
    private int mark3 = -1;

    public Trainee(String name, int mark1, int mark2) {
        Objects.requireNonNull(name);
        int min = Math.min(mark1, mark2);
        int max = Math.max(mark1, mark2);
        if (min < 0) {
            throw new WrongArgumentException("Mark can't be less than 0", min);
        }
        if (max > 100) {
            throw new WrongArgumentException("Mark can't be more than 100", max);
        }
        this.NAME = name;
        this.MARK1 = mark1;
        this.MARK2 = mark2;
    }

    public Trainee(String name, int mark1, int mark2, int mark3) {
        this(name, mark1, mark2);
        if (mark3 > 100 || mark3 < 0) {
            throw new WrongArgumentException("Mark must be between 0 and 100", mark3);
        }
        this.mark3 = mark3;
    }

    public String getName() {
        return NAME;
    }

    public int getMark1() {
        return MARK1;
    }

    public int getMark2() {
        return MARK2;
    }

    public int getMark3() {
        return mark3;
    }

    @Override
    public String toString() {
        return
                "name='" + NAME + '\'' +
                        ", mark1=" + MARK1 +
                        ", mark2=" + MARK2 +
                        toStringForExtraTest();
    }

    private String toStringForExtraTest() {
        return mark3 == -1 ? "" : ", mark3=" + mark3;
    }
}
