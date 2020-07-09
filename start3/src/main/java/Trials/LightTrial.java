package Trials;

import Trainee.Trainee;

public class LightTrial extends Trial {
    static final int CLASS_CONSTANT_FOR_TEST1 = 10;
    static final int CLASS_CONSTANT_FOR_TEST2 = 20;

    public LightTrial(Trainee trainee) {
        super(trainee);
    }
    public LightTrial(String account, int mark1, int mark2) {
        this(new Trainee(account, mark1, mark2));
    }

    @Override
    public boolean isPassed() {
        return getTrainee().getMark1() >= CLASS_CONSTANT_FOR_TEST1 && getTrainee().getMark2() >= CLASS_CONSTANT_FOR_TEST2;
    }
}
