package factory;

import myexceptions.WrongArgumentException;
import trials.Trial;
import com.google.gson.*;
import org.apache.logging.log4j.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;


public class FactoryOfTrials {


    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new Gson();

    public static Optional<Trial> getTrial(JsonObject jsonObject) {
        try {
            JsonObject args = jsonObject.get("args").getAsJsonObject();
            String className = jsonObject.get("class").getAsString();
            Class<?> clazz = Class.forName("trials." + className);
            Trial trial = GSON.fromJson(args, (Type) clazz);
            checkExtraData(args, className);
            return Optional.of((Trial) clazz.getConstructor(clazz).newInstance(trial));
        } catch (ClassNotFoundException | NumberFormatException | WrongArgumentException
                | NoSuchMethodException | IllegalAccessException | InstantiationException
                | InvocationTargetException e) {
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
