package Trainee;

public class Trainee {
    private final String NAME;
    private final int MARK1;
    private final int MARK2;
    private int mark3 = 0;

    public Trainee(String name, int mark1, int mark2) {
        this.NAME = name;
        this.MARK1 = mark1;
        this.MARK2 = mark2;
    }

    public Trainee(String name, int mark1, int mark2, int mark3) {
        this.NAME = name;
        this.MARK1 = mark1;
        this.MARK2 = mark2;
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
        return "Trainee.Trainee{" +
                "name='" + NAME + '\'' +
                ", mark1=" + MARK1 +
                ", mark2=" + MARK2 +
                ", mark3=" + mark3 +
                '}';
    }
}
