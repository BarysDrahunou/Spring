package Trials;

import Trainee.Trainee;

public class StrongTrial extends Trial {
    public StrongTrial(Trainee trainee) {
        super(trainee);
    }

    @Override
    public boolean isPassed() {
        return Integer.sum(getTrainee().getMark1()/2,getTrainee().getMark2())>= Trial.getClassConstant();
    }
}
