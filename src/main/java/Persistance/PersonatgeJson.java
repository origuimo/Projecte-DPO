package Persistance;

import Business.Monstre;
import Business.Personatge;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonatgeJson {
    public static ArrayList<Personatge> llegirPersonatges(){

        String input = "";
        File a = new File("src/main/java/Fitxers/characters.json");

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
}
