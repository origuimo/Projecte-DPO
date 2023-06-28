package Persistance;

import Business.Monstre;
import Business.Personatge;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe que s'encarrega de gestionar la informacio dels personatges amb la api
 */
public class PersonatgeApi {

    /**
     * Enllaç de la api
     */
    private static String url = "https://balandrau.salle.url.edu/dpoo/S1-Project_32/characters";

    /**
     * Llegeix el llistat de personatges de la API
     * @return llistat de personatges
     * @throws IOException
     */
    public static ArrayList<Personatge> llegirPersonatgesApi() throws IOException {
        boolean llegit;
        ApiHelper apiHelper = new ApiHelper();

        ;
        String input = apiHelper.getFromUrl(url);

        var charactersJSON = new JSONArray(input);
        ArrayList<Personatge> personatges = new ArrayList<>();

        for (int i = 0; i < charactersJSON.length(); i++) {

            JSONObject tmpPersonatgesJSON = charactersJSON.getJSONObject(i);
            Personatge b = new Personatge(tmpPersonatgesJSON.getString("name"),
                    tmpPersonatgesJSON.getString("player"),
                    tmpPersonatgesJSON.getInt("xp"),
                    tmpPersonatgesJSON.getInt("body"),
                    tmpPersonatgesJSON.getInt("mind"),
                    tmpPersonatgesJSON.getInt("spirit"),
                    tmpPersonatgesJSON.getString("class"));
            personatges.add(b);
        }
        return personatges;
    }

    /**
     * Afegeix un nou personatge a la API
     * @param personatge personatge a afegir
     * @throws IOException
     */
    public static void addCharacterApi(Personatge personatge) throws IOException {

        ApiHelper apiHelper = new ApiHelper();

        String json = personatge.toStringJson();
        apiHelper.postToUrl(url, json);
    }

    /**
     * Elimina un personatge de la API
     * @param nom nom del personatge a eliminar
     * @throws IOException
     */
    public static void removeCharacterApi(String nom) throws IOException {

        ApiHelper apiHelper = new ApiHelper();

        apiHelper.deleteFromUrl("https://balandrau.salle.url.edu/dpoo/S1-Project_32/characters?name=" + nom);

    }
}
