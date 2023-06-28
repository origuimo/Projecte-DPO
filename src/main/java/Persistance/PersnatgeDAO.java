package Persistance;

import Business.Personatge;

import java.io.IOException;
import java.util.ArrayList;

public interface PersnatgeDAO {

    static ArrayList<Personatge> llegirPersonatges(int data) throws IOException {

        ArrayList<Personatge> personatges;

        if(data == 1){
            personatges = PersonatgeJson.llegirPersonatges();
        }else{
            personatges = PersonatgeApi.llegirPersonatgesApi();
        }
        return personatges;
    }

    static void borrarPersonatge(int data, String nomEliminar) throws IOException {
        if(data == 1){
            PersonatgeJson.removeCharacter(nomEliminar);
        }else{
            PersonatgeApi.removeCharacterApi(nomEliminar);
        }
    }
}
