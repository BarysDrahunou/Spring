package Trials;

import Trainee.Trainee;

public class StrongTrial extends Trial {

    public StrongTrial(Trainee trainee) {
        super(trainee);
    }
    public StrongTrial(String account, int mark1, int mark2) {
        this(new Trainee(account, mark1, mark2));
    }
    @Override
    public boolean isPassed() {
        return Integer.sum(getTrainee().getMark1()/2,getTrainee().getMark2())>= Trial.getClassConstant();
    }
}
