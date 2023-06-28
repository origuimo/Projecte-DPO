package Persistance;

import Business.Aventura;
import Business.Monstre;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Tria si agafar la informació de la API o del fitxer
 */
public interface MonstreDAO {

    /**
     * Llegeix els monstres de la API o el fitxer
     * @param data API o Fitxer
     * @return llistat de monstres
     * @throws IOException
     */
    static ArrayList<Monstre> llegirMonstres(int data) throws IOException {

        ArrayList<Monstre> monstres;

        if(data == 1){
            monstres = MonstreJson.llegirMonstres();
        }else{
            monstres = MonstreApi.llegirMonstresApi();
        }
        return monstres;
    }
}
