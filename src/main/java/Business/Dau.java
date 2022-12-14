package Business;

import java.util.ArrayList;

public class Dau {

    public static ArrayList<Integer> daus6cares() {

        ArrayList<Integer> nums = new ArrayList<>();
        int numero1 = (int) (Math.random() * 6 + 1);
        nums.add(0);
        int numero2 = (int) (Math.random() * 6 + 1);
        nums.add(1);

        return nums;
    }
}
