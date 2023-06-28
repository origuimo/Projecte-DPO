package Persistance;

import Business.Personatge;

import java.io.IOException;
import java.util.ArrayList;

/**
 * S'encarrega de decidir si guardem la informacio dels personatges a la api o al fitxer
 */
public interface PersnatgeDAO {

    /**
     * Llegeix els personatges de la API o del Fitxer
     * @param data API o Fitxer
     * @return llistat dels personatges
     * @throws IOException
     */
    static ArrayList<Personatge> llegirPersonatges(int data) throws IOException {

        ArrayList<Personatge> personatges;

        if(data == 1){
            personatges = PersonatgeJson.llegirPersonatges();
        }else{
            personatges = PersonatgeApi.llegirPersonatgesApi();
        }
        return personatges;
    }

    /**
     * Borra un Personatge de la API o del Fitxer
     * @param data API o Fitxer
     * @param nomEliminar personatge a eliminar
     * @throws IOException
     */
    static void borrarPersonatge(int data, String nomEliminar) throws IOException {
        if(data == 1){
            PersonatgeJson.removeCharacter(nomEliminar);
        }else{
            PersonatgeApi.removeCharacterApi(nomEliminar);
        }
    }
}
