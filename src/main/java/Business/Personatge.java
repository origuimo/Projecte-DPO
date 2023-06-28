package Business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Personatge {

    /**
     * Nom del personatge
     */
    private String name;

    /**
     * Jugador que ha creat el personatge
     */
    private String player;

    /**
     * Experiencia del personatge
     */
    private int xp;

    /**
     * Cos del personatge
     */
    private int body;

    /**
     * Mentalitat del personatge
     */
    private int mind;

    /**
     * Esperit del personatge
     */
    private int spirit;

    /**
     * Tipus de personatge
     */
    private String tipus;

    /**
     * Vida actual del personatge
     */
    private int hitPoints;

    /**
     * Vida maxima del personatge
     */
    private int maxPoints;

    /**
     * Constructor del Personatge
     * @param name
     * @param player
     * @param xp
     * @param body
     * @param mind
     * @param spirit
     * @param tipus
     */
    public Personatge(String name, String player, int xp, int body, int mind, int spirit, String tipus) {
        this.name = name;
        this.player = player;
        this.xp = xp;
        this.body = body;
        this.mind = mind;
        this.spirit = spirit;
        this.tipus = tipus;
    }

    /**
     * Passar el personatge a un json String per guardar-ho a la api
     * @return el string generat
     */
    public String toStringJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Personatge(int hitPoints, int maxPoints) {
        this.hitPoints = hitPoints;
        this.maxPoints = maxPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * toString per printar la informació del Personatge
     * @return print
     */
    @Override
    public String toString() {
        return "* Name:" + name + "\n" +
                "* Player:" + player + "\n" +
                "* Class: " + tipus + "\n" +
                "* Level: " + "\n" +
                "* Xp:" + xp + "\n" +
                "* Body: " + body + "\n" +
                "* Mind: " + mind + "\n" +
                "* Spirit: " + spirit + "\n";
    }

    public String getName() {
        return name;
    }

    public String getPlayer() {
        return player;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getBody() {
        return body;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
