package Business;

public class Aventura {
    String nom;
    int enfrentaments;

    public Aventura(String nom, int enfrentaments) {
        this.nom = nom;
        this.enfrentaments = enfrentaments;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
