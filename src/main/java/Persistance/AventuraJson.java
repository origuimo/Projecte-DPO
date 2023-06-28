package Persistance;

import Business.Aventura;
import Business.Personatge;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AventuraJson {

    public static void escriureAventura(JSONObject aventura) {
        String contenidoJSON = "";
        try {
            contenidoJSON = new String(Files.readAllBytes(Paths.get("src/main/java/Fitxers/aventura.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray;
        if (contenidoJSON.isEmpty()) {
            jsonArray = new JSONArray();
        } else {
            jsonArray = new JSONArray(contenidoJSON);
        }

        jsonArray.put(aventura);

        try (FileWriter writer = new FileWriter("src/main/java/Fitxers/aventura.json")) {
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Aventura> llegirAventures() {
        File archivo = new File("src/main/java/Fitxers/aventura.json");
        String input = "";

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(archivo));
            String tmp = fileReader.readLine();
            while (tmp != null) {
                input += tmp;
                tmp = fileReader.readLine();
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Aventura> aventures = new ArrayList<>();

        if (!input.isEmpty()) {
            var aventuresJSON = new JSONArray(input);
            for (int i = 0; i < aventuresJSON.length(); i++) {
                JSONObject tmpAventuraJSON = aventuresJSON.getJSONObject(i);
                Aventura b = new Aventura(tmpAventuraJSON.getString("Nom"), tmpAventuraJSON.getInt("NumEnfrentaments"),
                        tmpAventuraJSON.getJSONArray("Enfrentaments"));
                aventures.add(b);
            }
        }

        return aventures;
    }


}
