package Trials;

import Trainee.Trainee;

public class Trial {
    private final Trainee TRAINEE;
    private static final int CLASS_CONSTANT = 50;

    public Trial(Trainee trainee) {
        this.TRAINEE = trainee;
    }

    public Trainee getTrainee() {
        return TRAINEE;
    }

    public static int getClassConstant() {
        return CLASS_CONSTANT;
    }

    @Override
    public String toString() {
        return String.format("%s; trial is passed - %s", TRAINEE, isPassed());
    }

    public boolean isPassed() {
        return Integer.sum(TRAINEE.getMark1(), TRAINEE.getMark2()) >= CLASS_CONSTANT;
    }
}
