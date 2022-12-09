package Business;

public class Monstre {

    String name;
    String challenge;
    int experience;
    int hitPoints;
    int initiative;
    String damageDice;
    String damageType;

    public Monstre(String name, String challenge, int experience, int hitPoints, int initiative, String damageDice, String damageType) {
        this.name = name;
        this.challenge = challenge;
        this.experience = experience;
        this.hitPoints = hitPoints;
        this.initiative = initiative;
        this.damageDice = damageDice;
        this.damageType = damageType;
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

    public String getName() {
        return name;
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
