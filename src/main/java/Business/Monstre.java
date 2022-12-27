package Business;

public class Monstre {

    String name;
    String challenge;
    int experience;
    int hitPoints;
    int initiative;
    String damageDice;
    String damageType;
    int quantitat;

    public Monstre(String name, String challenge, int experience, int hitPoints, int initiative, String damageDice, String damageType) {
        this.name = name;
        this.challenge = challenge;
        this.experience = experience;
        this.hitPoints = hitPoints;
        this.initiative = initiative;
        this.damageDice = damageDice;
        this.damageType = damageType;
    }


    public Monstre(String name) {
        this.name = name;
    }

    public Monstre(String name, int quantitat) {
        this.name = name;
        this.quantitat = quantitat;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
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
