package Business;

import java.util.ArrayList;

public class Dau {

    /**
     * Calcula dos numeros aleatoris entre 1 i 6
     * @return els numeros calculats
     */
    public static ArrayList<Integer> daus6cares() {

        ArrayList<Integer> nums = new ArrayList<>();
        int numero1 = (int) (Math.random() * 6 + 1);
        nums.add(numero1);
        int numero2 = (int) (Math.random() * 6 + 1);
        nums.add(numero2);

        return nums;
    }

    /**
     * calcula un numero aleatori entre 1 i 12
     * @return el numero calculat
     */
    public static Integer daus12cares() {
        int numero = (int) (Math.random() * 12 + 1);

        return numero;
    }

    /**
     * calcula un numero aleatori entre 1 i 6
     * @return el numero calculat
     */
    public static Integer dau6cares() {
        int numero = (int) (Math.random() * 6 + 1);

        return numero;
    }

    /**
     * Calcula un numero aleatori entre 1 i 10
     * @return el numero calculat
     */
    public static Integer daus10cares() {
        int numero = (int) (Math.random() * 10 + 1);

        return numero;
    }

    /**
     * Calcula un numero del 1 al numero que tingui del dau del mal cada monstre
     * @param dau numero de dau del monstre
     * @return el numero calculat
     */
    public static Integer daumonstre(int dau) {
        int numero = (int) (Math.random() * dau + 1);

        return numero;
    }

    /**
     * Calcula un numero del 1 al 8
     * @return el numero calculat
     */
    public static Integer dau8cares() {
        int numero = (int) (Math.random() * 8 + 1);

        return numero;
    }

    /**
     * Calcula un numero del 1 al 20
     * @return el numero calculat
     */
    public static Integer dau20cares() {
        int numero = (int) (Math.random() * 20 + 1);

        return numero;
    }

    /**
     * Calcula un numero del 1 al 4
     * @return el numero calculat
     */
    public static Integer dau4cares() {
        int numero = (int) (Math.random() * 4 + 1);

        return numero;
    }

    /**
     * Calcula un numero del 1 al 3
     * @return el numero Calculat
     */
    public static Integer dau3cares() {
        int numero = (int) (Math.random() * 3 + 1);

        return numero;
    }
}
