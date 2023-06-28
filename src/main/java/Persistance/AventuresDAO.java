package Persistance;

import Business.Aventura;
import Business.Personatge;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static Persistance.AventuraApi.addAventuraApi;
import static Persistance.AventuraJson.escriureAventura;

public interface AventuresDAO {

    static ArrayList<Aventura> llegirAventures(int data) throws IOException {

        ArrayList<Aventura> aventures;

        if(data == 1){
            aventures = AventuraJson.llegirAventures();
        }else{
            aventures = AventuraApi.llegirAventuresApi();
        }
        return aventures;
    }

    static void safeAventures(int data, JSONObject aventura) throws IOException {

        if(data == 1){
            escriureAventura(aventura);
        }else{
            addAventuraApi(aventura);
        }
    }
}
