package factory;

import myexceptions.WrongArgumentException;
import trials.Trial;
import com.google.gson.*;
import org.apache.logging.log4j.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;


public class FactoryOfTrials {


    private static final Logger LOGGER = LogManager.getLogger();

    public static Optional<Trial> getTrial(JsonObject jsonObject) {
        try {
            JsonObject args = jsonObject.get("args").getAsJsonObject();
            String className = jsonObject.get("class").getAsString();
            Type type = Class.forName("trials." + className);
            final Gson GSON = new Gson();
            Trial trial = GSON.fromJson(args, type);
            checkExtraData(args, className);
            if (trial.isTrialValid()) {
                return Optional.of(trial);
            } else {
                return Optional.empty();
            }
        } catch (ClassNotFoundException | NumberFormatException | WrongArgumentException e) {
            LOGGER.error(e);
            return Optional.empty();
        }
    }

    private static void checkExtraData(JsonObject jsonObject, String className) {
        String[] propertiesToRemove = new String[]{"account", "mark1", "mark2", "class"};
        Arrays.stream(propertiesToRemove).forEach(jsonObject::remove);
        if (className.equals("ExtraTrial")) {
            jsonObject.remove("mark3");
        }
        if (jsonObject.toString().length() > 2) {
            LOGGER.warn(String.format("Redundant data - %s", jsonObject));
        }
    }
}
