package Business;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
