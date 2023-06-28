package Business;

public class Monstre {

    /**
     * Nom del monstre
     */
    private String name;

    /**
     * Tipus de challenge
     */
    private String challenge;

    /**
     * experiencia del monstre
     */
    private int experience;

    /**
     * vida del monstre
     */
    private int hitPoints;

    /**
     * iniciativa del monstre
     */
    private int initiative;

    /**
     * Numero del dau de mal
     */
    private String damageDice;

    /**
     * tipus de mal
     */
    private String damageType;

    /**
     * Quantitat de monstres
     */
    private int quantitat;

    /**
     * Constructor del Monstre amb tota la informació
     * @param name
     * @param challenge
     * @param experience
     * @param hitPoints
     * @param initiative
     * @param damageDice
     * @param damageType
     */
    public Monstre(String name, String challenge, int experience, int hitPoints, int initiative, String damageDice, String damageType) {
        this.name = name;
        this.challenge = challenge;
        this.experience = experience;
        this.hitPoints = hitPoints;
        this.initiative = initiative;
        this.damageDice = damageDice;
        this.damageType = damageType;
    }

    /**
     * Constructor per guardar-nos el nom
     * @param name
     */
    public Monstre(String name) {
        this.name = name;
    }

    /**
     * Constructor del monstre per guardar-nos el nom i la quantitat de monstres
     * @param name
     * @param quantitat
     */
    public Monstre(String name, int quantitat) {
        this.name = name;
        this.quantitat = quantitat;
    }

    /**
     * toString per printar la informació del monstre
     * @return print
     */
    @Override
    public String toString() {
        return "Monstre{" +
                "name='" + name + '\'' +
                ", challenge='" + challenge + '\'' +
                ", experience=" + experience +
                ", hitPoints=" + hitPoints +
                ", initiative=" + initiative +
                ", damageDice='" + damageDice + '\'' +
                ", damageType='" + damageType + '\'' +
                '}';
    }
    public int getQuantitat() {
        return quantitat;
    }

    public String getName() {
        return name;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getChallenge() {
        return challenge;
    }

    public int getExperience() {
        return experience;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public String getDamageType() {
        return damageType;
    }
}
