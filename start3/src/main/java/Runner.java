import Factory.FactoryOfTrials;
import Trainee.Trainee;
import Trials.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Runner {
    private static List<Trial> trials;

    public static void main(String[] args) {
        try {

            Type type = new TypeToken<List<JsonObject>>() {
            }.getType();
            List<JsonObject> jsonObjects = new Gson().fromJson(new FileReader(args[0]), type);
            trials = jsonObjects.stream()
                    .map(FactoryOfTrials::getTrial)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());          //1
//
//            trials.forEach(System.out::println);        //2
//            printTheNumberOfPassedTrials();       //3
//            trials.sort(Comparator.comparingInt(
//                    x -> (x.getTrainee().getMark1() + x.getTrainee().getMark2())));           //4
//            printSum1();         //5
//            printSum2();        //5
//            printFailuredTrials();      //6
//
//            printNumericArray(sortedTrial);     //7
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTheNumberOfPassedTrials() {
        System.out.println(String.format("Number of successful passed tests: %s"
                , trials.stream().filter(Trial::isPassed).count()));
    }

    private static void printSum1() {
        int result = trials.stream().map(x -> x.getTrainee().getMark1()).reduce(0, Integer::sum);
        System.out.println(String.format("Sum of first marks from the collection: %s", result));
    }

    private static void printSum2() {
        int result = trials.stream().map(x -> x.getTrainee().getMark2()).reduce(0, Integer::sum);
        System.out.println(String.format("Sum of second marks from the collection: %s", result));
    }

//    private static void printFailuredTrials() {
//        List<Trial> list = trials.stream().filter(x -> !x.isPassed()).map(x ->
//                new Trial(new Trainee(x.getTrainee().getName(), 0, 0, 0))
//        ).collect(Collectors.toList());
//        System.out.println(list.stream().anyMatch(Trial::isPassed));
//        list.forEach(System.out::println);
//    }

    private static void printNumericArray(List<Trial> trial) {
        System.out.println(Arrays.toString(trial.stream().map(x -> x.getTrainee().getMark1() + x.getTrainee().getMark2()).toArray(Integer[]::new)));
    }
}
