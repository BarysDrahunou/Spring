package factory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import trials.ExtraTrial;
import trials.StrongTrial;
import trials.Trial;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class FactoryOfTrialsTest {
    JsonObject jsonObject1;
    JsonObject jsonObject2;
    JsonObject jsonObject3;
    Gson gson = new Gson();

    @Before
    public void init() {
        String json1 = "{ \"class\": \"Trial\", \"args\": { \"account\": \"Vitali\", \"mark1\": 10, \"mark2\": 10 } }";
        jsonObject1 = gson.fromJson(json1, JsonObject.class);
        String json2 = "{ \"class\": \"StrongTrial\", \"args\": { \"account\": \"Dimon\", \"mark1\": 32, \"mark2\": 77 } }";
        jsonObject2 = gson.fromJson(json2, JsonObject.class);
        String json3 = "{ \"class\": \"SomeClass\", \"args\": { \"account\": \"Dimon\", \"mark1\": 32, \"mark2\": 77 } }";
        jsonObject3 = gson.fromJson(json3, JsonObject.class);
    }

    @Test
    public void getTrialTest() {
        assertEquals(FactoryOfTrials.getTrial(jsonObject1).get().toString()
                , new Trial("Vitali", 10, 10).toString());
        assertEquals(FactoryOfTrials.getTrial(jsonObject2).get().toString()
                , new StrongTrial("Dimon", 32, 77).toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void getTrialTestException() {
        assertEquals(FactoryOfTrials.getTrial(jsonObject3).get().toString()
                , new Trial("Dimon", 32, 77).toString());
    }
}