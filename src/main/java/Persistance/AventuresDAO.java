package Persistance;

import Business.Aventura;
import Business.Personatge;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static Persistance.AventuraApi.addAventuraApi;
import static Persistance.AventuraJson.escriureAventura;

/**
 * S'encarrega de decidir si guardem la informacio de les aventures a la api o al fitxer
 */
public interface AventuresDAO {

    /**
     * llegir les aventures
     * @param data API o Fitxer
     * @return llistat de aventures
     * @throws IOException
     */
    static ArrayList<Aventura> llegirAventures(int data) throws IOException {

        ArrayList<Aventura> aventures;

        if(data == 1){
            aventures = AventuraJson.llegirAventures();
        }else{
            aventures = AventuraApi.llegirAventuresApi();
        }
        return aventures;
    }

    /**
     * Guardar una aventura
     * @param data API o Fitxer
     * @param aventura aventura a guardar
     * @throws IOException
     */
    static void safeAventures(int data, JSONObject aventura) throws IOException {

        if(data == 1){
            escriureAventura(aventura);
        }else{
            addAventuraApi(aventura);
        }
    }
}
