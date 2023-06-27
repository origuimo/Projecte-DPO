package Business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Aventura {
    String nom;
    int enfrentaments;
    ArrayList<Monstre> monstres = new ArrayList<>();


    public Aventura(String nom, int enfrentaments) {
        this.nom = nom;
        this.enfrentaments = enfrentaments;
    }

    public Aventura(String nom, int enfrentaments, ArrayList<Monstre> monstres) {
        this.nom = nom;
        this.enfrentaments = enfrentaments;
        this.monstres = monstres;
    }

    public String toStringJsonAv() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
    public String toString() {
        return "* Name:" + nom + "\n" +
                "* Enfrentaments:" + enfrentaments + "\n" +
                "* Monsters: " + monstres + "\n";
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEnfrentaments() {
        return enfrentaments;
    }

    public void setEnfrentaments(int enfrentaments) {
        this.enfrentaments = enfrentaments;
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public void setMonstres(ArrayList<Monstre> monstres) {
        this.monstres = monstres;
    }
}
