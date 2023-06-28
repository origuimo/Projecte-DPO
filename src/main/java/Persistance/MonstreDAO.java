package Persistance;

import Business.Aventura;
import Business.Monstre;

import java.io.IOException;
import java.util.ArrayList;

public interface MonstreDAO {

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
