import Factory.FactoryOfTrials;
import Trainee.Trainee;
import Trials.*;
import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class Runner {
    private static final List<Trial> TRIALS = new ArrayList<>();

    public static void main(String[] args) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(args[0])) {
            JsonArray arrayOfAccounts = gson.fromJson(reader, JsonArray.class);
            for (JsonElement element : arrayOfAccounts) {
                try {
                    Trial trial = FactoryOfTrials.getTrial(element.getAsJsonObject());
                    if (Objects.nonNull(trial)) {
                        TRIALS.add(trial);             //1
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            TRIALS.forEach(System.out::println);        //2
            printTheNumberOfPassedTrials();       //3
            List<Trial> sortedTrial = sortTheCollection();            //4
            printSum1();         //5
            printSum2();        //5
            printFailuredTrials();      //6

            printNumericArray(sortedTrial);     //7
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTheNumberOfPassedTrials() {
        System.out.println(String.format("Number of successful passed tests: %s"
                , TRIALS.stream().filter(Trial::isPassed).count()));
    }

    private static List<Trial> sortTheCollection() {
        return TRIALS.stream().sorted(Comparator.comparingInt(
                x -> (x.getTrainee().getMark1() + x.getTrainee().getMark2()))).collect(Collectors.toList());

    }

    private static void printSum1() {
        int result = TRIALS.stream().map(x -> x.getTrainee().getMark1()).reduce(0, Integer::sum);
        System.out.println(String.format("Sum of first marks from the collection: %s", result));
    }

    private static void printSum2() {
        int result = TRIALS.stream().map(x -> x.getTrainee().getMark2()).reduce(0, Integer::sum);
        System.out.println(String.format("Sum of second marks from the collection: %s", result));
    }

    private static void printFailuredTrials() {
        List<Trial> list = TRIALS.stream().filter(x -> !x.isPassed()).map(x ->
                new Trial(new Trainee(x.getTrainee().getName(), 0, 0, 0))
        ).collect(Collectors.toList());
        System.out.println(list.stream().anyMatch(Trial::isPassed));
        list.forEach(System.out::println);
    }

    private static void printNumericArray(List<Trial> trial) {
        System.out.println(Arrays.toString(trial.stream().map(x -> x.getTrainee().getMark1() + x.getTrainee().getMark2()).toArray(Integer[]::new)));
    }
}
