package Trials;

import Trainee.Trainee;

public class ExtraTrial extends Trial {
    static final int CLASS_CONSTANT_FOR_EXTRA_TRIAL = 50;

    public ExtraTrial(Trainee trainee) {
        super(trainee);
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && getTrainee().getMark3() >= CLASS_CONSTANT_FOR_EXTRA_TRIAL;
    }
}
