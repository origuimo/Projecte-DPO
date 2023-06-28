package Business;

public class Resultat {
    public int nPlayers;
    public int nMonstres;

    public Resultat(int nPlayers, int nMonstres) {
        this.nPlayers = nPlayers;
        this.nMonstres = nMonstres;
    }

    public int getnPlayers() {
        return nPlayers;
    }

    public int getnMonstres() {
        return nMonstres;
    }
}
