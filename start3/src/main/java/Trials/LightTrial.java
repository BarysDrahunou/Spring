package Trials;

import Trainee.Trainee;

public class LightTrial extends Trial {
    static final int CLASS_CONSTANT_FOR_TEST1 = 10;
    static final int CLASS_CONSTANT_FOR_TEST2 = 20;

    public LightTrial(Trainee trainee) {
        super(trainee);
    }

    @Override
    public boolean isPassed() {
        return getTrainee().getMark1() >= CLASS_CONSTANT_FOR_TEST1 && getTrainee().getMark2() >= CLASS_CONSTANT_FOR_TEST2;
    }
}
