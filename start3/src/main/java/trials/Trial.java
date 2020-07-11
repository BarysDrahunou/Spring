package trials;

import myexceptions.WrongArgumentException;

public class Trial {

    private final String account;
    private int mark1;
    private int mark2;

    public Trial(String account, int mark1, int mark2) {
        this.account = account;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    private static final int CLASS_CONSTANT = 50;

    public static int getClassConstant() {
        return CLASS_CONSTANT;
    }

    public String getAccount() {
        return account;
    }

    public int getMark1() {
        return mark1;
    }

    public int getMark2() {
        return mark2;
    }

    @Override
    public String toString() {
        return String.format("%s; trial is passed - %s"
                , fieldsToString(), isPassed());
    }

    protected String fieldsToString() {
        return String.format("%s; His marks : %s; %s"
                , getAccount(), getMark1(), getMark2());
    }

    public boolean isTrialValid() {
        int[] fields = new int[]{getMark1(), getMark2()};
        for (int field : fields) {
            if (field < 0 || field > 100) {
                throw new WrongArgumentException(
                        "Field value must be between 0 and 100", getMark1());
            }
        }
        return true;
    }

    public void clearMarks() {
        mark1 = 0;
        mark2 = 0;
    }

    public Trial copy() {
        return new Trial(this.account, this.mark1, this.mark2);
    }

    public boolean isPassed() {
        return Integer.sum(getMark1(), getMark2()) >= getClassConstant();
    }
}
