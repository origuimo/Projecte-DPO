package Business;

import java.util.ArrayList;

public class Dau {

    public static ArrayList<Integer> daus6cares() {

        ArrayList<Integer> nums = new ArrayList<>();
        int numero1 = (int) (Math.random() * 6 + 1);
        nums.add(numero1);
        int numero2 = (int) (Math.random() * 6 + 1);
        nums.add(numero2);

        return nums;
    }
    public static Integer daus12cares() {
        int numero = (int) (Math.random() * 12 + 1);

        return numero;
    }
    public static Integer daus10cares() {
        int numero = (int) (Math.random() * 10 + 1);

        return numero;
    }
    public static Integer daumonstre(int dau) {
        int numero = (int) (Math.random() * dau + 1);

        return numero;
    }
}
