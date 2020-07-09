package Factory;

import Trainee.Trainee;
import Trials.Trial;
import com.google.gson.*;
import org.apache.logging.log4j.*;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;


public class FactoryOfTrials {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Optional<Trial> getTrial(JsonObject jsonObject) {
        try {
            Class<?> clazz = Class.forName("Trials."+jsonObject.get("class").getAsString());
            JsonObject object = jsonObject.get("args").getAsJsonObject();
            String nameOfTrainee = object.get("account").getAsString();
            Trainee trainee;
            JsonElement mark1 = object.get("mark1");
            JsonElement mark2 = object.get("mark2");
            JsonElement mark3 = object.get("mark3");
            if (Objects.nonNull(mark3)) {
                trainee = new Trainee(nameOfTrainee, mark1.getAsInt(), mark2.getAsInt(), mark3.getAsInt());
            } else {
                trainee = new Trainee(nameOfTrainee, mark1.getAsInt(), mark2.getAsInt());
            }
            String className = jsonObject.get("class").getAsString();
            Type type=Class.forName("Trials."+className);
            final Gson GSON = new Gson();
            System.out.println(object);
            Trial trial =GSON.fromJson(object, type);
            object.remove("account");
            object.remove("mark1");
            object.remove("mark2");
            object.remove("mark3");
            jsonObject.remove("class");
            if (jsonObject.get("args").toString().length() == 2) {
                jsonObject.remove("args");
            }
            if (jsonObject.toString().length() > 2) {
                LOGGER.warn(String.format("Redundant data - %s", jsonObject));
            }
            return Optional.of((Trial) clazz.getConstructor(Trainee.class).newInstance(trainee));
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | NumberFormatException  e) {
            LOGGER.error(e);
            return Optional.empty();
        }
    }
}
