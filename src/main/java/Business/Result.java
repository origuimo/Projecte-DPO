package Business;

import java.util.ArrayList;

public class Result {
    public final boolean correcte;
    public final ArrayList<Monstre> monstres;

    public final int data;

    public Result(boolean correcte, ArrayList<Monstre> monstres, int data) {
        this.correcte = correcte;
        this.monstres = monstres;
        this.data = data;
    }
}
