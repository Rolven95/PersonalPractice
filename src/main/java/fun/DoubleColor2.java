package fun;

import java.util.*;

public class DoubleColor2 {
    final static int COUNT = 20;

    public static void main(String[] args) {
        DoubleColor2 doubleColor2 = new DoubleColor2();
        for (int i = 0; i < COUNT; i++) {
            System.out.println(doubleColor2.getRandomSet());
        }
    }

    public List<Integer> getRandomSet() {
        List<Integer> toReturn = new ArrayList<>(7);
        toReturn.addAll(getReds());



        toReturn.addAll(getBlue());

        return toReturn;
    }

    private List<Integer> getBlue() {
        ArrayList<Integer> blue = new ArrayList<>(1);
        blue.add(getNumber(1, 16).get(0));
        return blue;
    }

    private List<Integer> getReds() {
        ArrayList<Integer> reds = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            reds.add(getNumber(1, 33).get(0));
        }
        reds.sort((o1, o2) -> {
            if (o1.equals(o2))
                return 0;
            if (o1 < o2)
                return -1;
            return 1;
        });
        return reds;
    }

    /**
     * return numbers from gaven range, start from 1.
     */
    private List<Integer> getNumber(int count, int range) {
        List<Integer> pool = this.getRandomList(range);
        while (pool.size() > count) {
            cutList(pool);
        }
        return pool;
    }

    /**
     * get list stores integers, start from 1
     */
    private List<Integer> getRandomList(int count) {
        ArrayList<Integer> all = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            all.add(i);
        }
        Collections.shuffle(all);
        return all;
    }

    /**
     * randomly remove a element in the list
     */
    private static void cutList(List<Integer> scr) {
        if (scr.size() <= 1)
            return;
        Collections.shuffle(scr);
        Random rand = new Random();
        scr.remove(rand.nextInt(scr.size()));
    }
}
