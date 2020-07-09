import factory.FactoryOfTrials;
import trials.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
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

            trials.forEach(System.out::println);        //2
            printTheNumberOfPassedTrials();       //3
            trials.sort(Comparator.comparingInt(Runner::lambda));           //4
            printSum();         //5
            printFailedTrials();      //6

            printNumericArray(trials);     //7
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printTheNumberOfPassedTrials() {
        System.out.println(String.format("Number of successful passed tests: %s"
                , trials.stream().filter(Trial::isPassed).count()));
    }

    private static int lambda(Trial trial) {
        return Integer.sum(trial.getMark1(), trial.getMark2());
    }

    private static void printSum() {
        int result = trials.stream().map(Runner::lambda).reduce(0, Integer::sum);
        System.out.println(String.format("Sum of first marks from the collection: %s", result));
    }

    private static void printFailedTrials() {
        List<Trial> list = trials.stream().filter(x -> !x.isPassed()).map(Trial::copy).peek(Trial::clearMarks).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println(list.stream().anyMatch(Trial::isPassed));
    }

    private static void printNumericArray(List<Trial> trial) {
        int[] array = trial.stream().mapToInt(Runner::lambda).toArray();
        String stringList = Arrays.stream(array).boxed().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(stringList);
    }
}
