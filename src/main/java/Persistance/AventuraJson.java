package Persistance;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class AventuraJson {

    public static void escriureAventura(JSONObject aventura) {

        try (FileWriter writer = new FileWriter("aventura.json")) {
            writer.write(aventura.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
