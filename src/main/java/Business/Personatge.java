package Business;

public class Personatge {
    String name;
    String player;
    int xp;
    int body;
    int mind;
    int spirit;
    String tipus;

    public Personatge(String name, String player, int xp, int body, int mind, int spirit, String tipus) {
        this.name = name;
        this.player = player;
        this.xp = xp;
        this.body = body;
        this.mind = mind;
        this.spirit = spirit;
        this.tipus = tipus;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
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

    public void setBody(int body) {
        this.body = body;
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
