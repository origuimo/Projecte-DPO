package Persistance;

import Business.Aventura;
import Business.Personatge;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AventuraApi {

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

    public static void addAventuraApi(JSONObject aventura) throws IOException {

        ApiHelper apiHelper = new ApiHelper();
        String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_32/adventures";
        String json = aventura.toString();
        apiHelper.postToUrl(url, json);
    }
}
