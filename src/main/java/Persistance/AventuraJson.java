package Persistance;

import java.io.FileWriter;
import java.io.IOException;

public class AventuraJson {

    public static <Gson> void escriureAventura() {
        // Crear una instancia de Gson
        Gson gson = new Gson();

        // Serializar el objeto a una cadena JSON
        String json = gson.toJson();

        // Escribir la cadena JSON en un archivo
        try (FileWriter writer = new FileWriter("person.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
