package Persistance;

import Business.Monstre;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe que s'encarrega de gestionar els monstres amb la api
 */
public class MonstreApi {

    /**
     * Llegeix els monstres guardats a la api
     * @return llistat dels monstres
     * @throws IOException
     */
    public static ArrayList<Monstre> llegirMonstresApi() throws IOException {
        boolean llegit;
        ApiHelper apiHelper = new ApiHelper();

        String url = "https://balandrau.salle.url.edu/dpoo/shared/monsters";
        String input = apiHelper.getFromUrl(url);

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
