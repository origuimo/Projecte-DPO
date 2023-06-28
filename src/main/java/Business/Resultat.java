package Business;

public class Resultat {

    /**
     * nombre de personatges
     */
    private int nPlayers;

    /**
     * nombre de Monstres
     */
    private int nMonstres;

    /**
     * experiencia
     */
    private int xp;

    /**
     * Constructor del Resultat
     * @param nPlayers
     * @param nMonstres
     * @param xp
     */
    public Resultat(int nPlayers, int nMonstres, int xp) {
        this.nPlayers = nPlayers;
        this.nMonstres = nMonstres;
        this.xp = xp;
    }

    public int getnPlayers() {
        return nPlayers;
    }

    public int getnMonstres() {
        return nMonstres;
    }

    public int getXp() {
        return xp;
    }
}
