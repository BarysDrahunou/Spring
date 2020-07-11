import factory.FactoryOfTrials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trials.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger();
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
            LOGGER.error(e);
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
        System.out.println("Sum of first and second marks from the collection:");
        trials.forEach(f -> System.out.println(lambda(f)));
        System.out.println("_________________________");
    }

    private static void printFailedTrials() {
        List<Trial> list = trials.stream().filter(x -> !x.isPassed()).map(Trial::copy)
                .peek(Trial::clearMarks).peek(System.out::println).collect(Collectors.toList());
        System.out.println(list.stream().noneMatch(Trial::isPassed));
    }

    private static void printNumericArray(List<Trial> trial) {
        int[] array = trial.stream().mapToInt(Runner::lambda).toArray();
        String stringList = Arrays.stream(array).boxed()
                .map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(stringList);
    }
}
