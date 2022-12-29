package Business;

public class Combat {


    int iniciativa;
    String nom;
    String tipus;
    int HitPoints;
    int xp;
    public Combat(int iniciativa, String nom, String tipus, int HitPoints, int xp) {
        this.iniciativa = iniciativa;
        this.nom = nom;
        this.tipus = tipus;
        this.HitPoints = HitPoints;
        this.xp = xp;
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHitPoints() {
        return HitPoints;
    }

    public void setHitPoints(int hitPoints) {
        HitPoints = hitPoints;
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
