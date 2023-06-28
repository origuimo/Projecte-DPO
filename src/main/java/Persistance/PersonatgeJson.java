package Persistance;

import Business.Monstre;
import Business.Personatge;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;


public class PersonatgeJson {

    static File a = new File("src/main/java/Fitxers/characters.json");
    public static ArrayList<Personatge> llegirPersonatges(){

        String input = "";


        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(a));

            String tmp = fileReader.readLine();

            while (tmp != null){
                input += tmp;
                tmp = fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        var personatgesJSON = new JSONArray(input);
        ArrayList<Personatge> personatges = new ArrayList<>();

        for (int i = 0; i < personatgesJSON.length(); i++) {

            JSONObject tmpPersonatgeJSON = personatgesJSON.getJSONObject(i);
            Personatge b = new Personatge(tmpPersonatgeJSON.getString("name"),
                    tmpPersonatgeJSON.getString("player"),
                    tmpPersonatgeJSON.getInt("xp"),
                    tmpPersonatgeJSON.getInt("body"),
                    tmpPersonatgeJSON.getInt("mind"),
                    tmpPersonatgeJSON.getInt("spirit"),
                    tmpPersonatgeJSON.getString("class"));
            personatges.add(b);
        }
        return personatges;
    }

    public static void addCharacter(Personatge personatge){
        try {

            // Leer el archivo JSON y convertirlo en un JSONArray
            JSONArray jsonArray = new JSONArray(new JSONTokener(new FileReader(a)));

            // Crear un nuevo JSONObject
            JSONObject newObject = new JSONObject();
            newObject.put("name", personatge.getName());
            newObject.put("player", personatge.getPlayer());
            newObject.put("xp", personatge.getXp());
            newObject.put("body", personatge.getBody());
            newObject.put("mind", personatge.getMind());
            newObject.put("spirit", personatge.getSpirit());
            newObject.put("class", personatge.getTipus());

            // Agregar el nuevo JSONObject al JSONArray
            jsonArray.put(newObject);

            // Escribir el contenido actualizado en el archivo JSON
            FileWriter fileWriter = new FileWriter(a);
            jsonArray.write(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeCharacter(String name) {
        try {
            // Ruta del archivo JSON existente
            String filePath = "src/main/java/Fitxers/characters.json";

            // Leer el archivo JSON y convertirlo en un JSONArray
            JSONArray jsonArray = new JSONArray(new JSONTokener(new FileReader(filePath)));

            // Buscar el elemento a eliminar por el nombre
            int index = -1;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("name").equals(name)) {
                    index = i;
                    break;
                }
            }

            // Eliminar el elemento del JSONArray si se encontró
            if (index != -1) {
                jsonArray.remove(index);

                // Escribir el contenido actualizado en el archivo JSON
                FileWriter fileWriter = new FileWriter(filePath);
                jsonArray.write(fileWriter);
                fileWriter.close();
            } else {
                System.out.println("No se encontró el elemento con el nombre especificado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
