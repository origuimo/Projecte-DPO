package Business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Classe que representa les aventures.
 */
public class Aventura {

    /**
     * nom de l'aventura
     */
    private String nom;

    /**
     * numero d'enfrentaments en aquesta aventura
     */
    private int enfrentaments;

    /**
     * array amb les llistes de monstres contra els que lluitem a cada enfrentament
     */
    private JSONArray monstres;

    /**
     * Constructor de l'aventura
     * @param nom nom de l'aventura
     * @param enfrentaments numero d'enfrentaments
     * @param monstres monstres de l'aventura
     */
    public Aventura(String nom, int enfrentaments, JSONArray monstres) {
        this.nom = nom;
        this.enfrentaments = enfrentaments;
        this.monstres = monstres;
    }

    /**
     * Passem la aventura a tipus json per guardar-la
     * @return String en format JSON
     */
    public String toStringJsonAv() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    /**
     * metode toString de l'aventura
     * @return Atributs de l'aentura printats
     */
    public String toString() {
        return "* Name:" + nom + "\n" +
                "* Enfrentaments:" + enfrentaments + "\n" +
                "* Monsters: " + monstres + "\n";
    }
    public String getNom() {
        return nom;
    }

    public int getEnfrentaments() {
        return enfrentaments;
    }

}
