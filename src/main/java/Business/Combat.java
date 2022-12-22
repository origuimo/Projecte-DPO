package Business;

public class Combat {


    int iniciativa;
    String nom;

    public Combat(int iniciativa, String nom) {
        this.iniciativa = iniciativa;
        this.nom = nom;
    }
    public int getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(int iniciativa) {
        this.iniciativa = iniciativa;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
