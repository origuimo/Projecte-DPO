package Business;

public class Combat {


    /**
     * Iniciativa de cada membre del combat
     */
    private int iniciativa;

    /**
     * Nom del membre
     */
    private String nom;

    /**
     * Tipus (monstre o personatge)
     */
    private String tipus;

    /**
     * Punts de vida
     */
    private int HitPoints;

    /**
     * Experiencia del personatge
     */
    private int xp;

    /**
     * Si esta viu o mort
     */
    private boolean alive;

    /**
     * El mal que fa
     */
    private String mal;

    /**
     * Constructor de Combat
     * @param iniciativa
     * @param nom
     * @param tipus
     * @param HitPoints
     * @param xp
     * @param alive
     * @param mal
     */
    public Combat(int iniciativa, String nom, String tipus, int HitPoints, int xp, boolean alive, String mal) {
        this.iniciativa = iniciativa;
        this.nom = nom;
        this.tipus = tipus;
        this.HitPoints = HitPoints;
        this.xp = xp;
        this.alive = alive;
        this.mal = mal;
    }
    public int getIniciativa() {
        return iniciativa;
    }


    public String getNom() {
        return nom;
    }

    public int getXp() {
        return xp;
    }

    public String getMal() {
        return mal;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getHitPoints() {
        return HitPoints;
    }

    public void setHitPoints(int hitPoints) {
        HitPoints = hitPoints;
    }

    public String getTipus() {
        return tipus;
    }

}
