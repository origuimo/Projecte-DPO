package Persistance;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class AventuraJson {

    public static void escriureAventura(JSONObject aventura) {

        try (FileWriter writer = new FileWriter("src/main/java/Fitxers/aventura.json")) {
            writer.append(aventura.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
