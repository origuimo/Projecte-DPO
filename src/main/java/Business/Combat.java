package Business;

public class Combat {


    int iniciativa;
    String nom;
    String tipus;

    public Combat(int iniciativa, String nom, String tipus) {
        this.iniciativa = iniciativa;
        this.nom = nom;
        this.tipus = tipus;
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

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
