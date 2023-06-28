package Persistance;

import Business.Aventura;
import Business.Personatge;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe que s'encarrega de fer les gestions amb la api per l'aventutra
 */
public class AventuraApi {

    /**
     * Llegeix les aventures que hi han guardades a la api
     * @return llistat de les aventures
     * @throws IOException
     */
    public static ArrayList<Aventura> llegirAventuresApi() throws IOException {
        boolean llegit;
        ApiHelper apiHelper = new ApiHelper();

        String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_32/adventures";
        String input = apiHelper.getFromUrl(url);

        var aventuresJSON = new JSONArray(input);
        ArrayList<Aventura> aventuras = new ArrayList<>();

        for (int i = 0; i < aventuresJSON.length(); i++) {

            JSONObject tmpAventuresJSON = aventuresJSON.getJSONObject(i);
            Aventura b = new Aventura(tmpAventuresJSON.getString("Nom"),
                    tmpAventuresJSON.getInt("NumEnfrentaments"),
                    tmpAventuresJSON.getJSONArray("Enfrentaments"));
            aventuras.add(b);
        }
        return aventuras;
    }

    /**
     * Afegeix una aventura a la api
     * @param aventura aventura a guardar
     * @throws IOException
     */
    public static void addAventuraApi(JSONObject aventura) throws IOException {

        ApiHelper apiHelper = new ApiHelper();
        String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_32/adventures";
        String json = aventura.toString();
        apiHelper.postToUrl(url, json);
    }
}
