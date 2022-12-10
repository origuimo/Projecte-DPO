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
        return "Personatge{" +
                "name='" + name + '\'' +
                ", player='" + player + '\'' +
                ", xp=" + xp +
                ", body=" + body +
                ", mind=" + mind +
                ", spirit=" + spirit +
                ", tipus='" + tipus + '\'' +
                '}';
    }
}
