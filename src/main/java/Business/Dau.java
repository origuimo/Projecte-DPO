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
    public static Integer dau6cares() {
        int numero = (int) (Math.random() * 6 + 1);

        return numero;
    }
    public static Integer daus10cares() {
        int numero = (int) (Math.random() * 10 + 1);

        return numero;
    }
    public static Integer daumonstre(int dau) {
        int numero = (int) (Math.random() * dau);

        return numero;
    }
    public static Integer dau8cares() {
        int numero = (int) (Math.random() * 8 + 1);

        return numero;
    }
    public static Integer dau20cares() {
        int numero = (int) (Math.random() * 20 + 1);

        return numero;
    }
    public static Integer dau4cares() {
        int numero = (int) (Math.random() * 4 + 1);

        return numero;
    }
}
