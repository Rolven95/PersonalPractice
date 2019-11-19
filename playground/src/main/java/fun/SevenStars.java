package fun;

import java.util.*;

public class SevenStars {

    List<Integer> getPick() {
        ArrayList<Integer> r = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            Random random = new Random();
            int pick = random.nextInt(10);
            r.add(pick);
        }


        Random ranSh = new Random();
        int shuLimit = ranSh.nextInt(777);

        for (int i = 0; i < shuLimit; i++) {
            Collections.shuffle(r);
        }
        return r;
    }


    public static void main(String[] args) {
        SevenStars sevenStars = new SevenStars();
        System.out.println(sevenStars.getPick());
    }
}
