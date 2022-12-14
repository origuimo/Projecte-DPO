package Persistance;

import Business.Monstre;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;


public class MonstreJson {

    public static ArrayList<Monstre> llegirMonstres() throws IOException {
        boolean llegit;
        String input = "";
        File a = new File("src/main/java/Fitxers/monsters.json");

        BufferedReader fileReader = new BufferedReader(new FileReader(a));

        String tmp = fileReader.readLine();

        while (tmp != null){
        input += tmp;
        tmp = fileReader.readLine();
        }

        var monstresJSON = new JSONArray(input);
        ArrayList<Monstre> monstres = new ArrayList<>();

        for (int i = 0; i < monstresJSON.length(); i++) {

            JSONObject tmpMonsterJSON = monstresJSON.getJSONObject(i);
            Monstre b = new Monstre(tmpMonsterJSON.getString("name"),
                    tmpMonsterJSON.getString("challenge"),
                    tmpMonsterJSON.getInt("experience"),
                    tmpMonsterJSON.getInt("hitPoints"),
                    tmpMonsterJSON.getInt("initiative"),
                    tmpMonsterJSON.getString("damageDice"),
                    tmpMonsterJSON.getString("damageType"));
            monstres.add(b);
        }
        return monstres;
    }
}
