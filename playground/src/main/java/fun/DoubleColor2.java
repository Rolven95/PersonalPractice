package fun;

import java.util.*;
import java.util.stream.Collectors;

public class DoubleColor2 {
    final static int PICK_POOL = 10000;

    public static void main(String[] args) {
        DoubleColor2 doubleColor2 = new DoubleColor2();
        List<List<Integer>> pickPool = new LinkedList<>();
        for(int i = 0; i < PICK_POOL ; i ++){
            pickPool.add(doubleColor2.getRandomSet());
        }
        while (pickPool.size()>1)
            cutList(pickPool);

        System.out.println(pickPool.get(0));
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
        HashSet<Integer> reds = new HashSet<>(6);
        for (int i = 0; i < 6; i++) {
            reds.add(getNumber(1, 33).get(0));
        }

        if (reds.size() != 6)
            return getReds();

        return reds.stream().sorted((o1, o2) -> {
            if (o1.equals(o2))
                return 0;
            if (o1 < o2)
                return -1;
            return 1;
        }).collect(Collectors.toList());
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
    public static void cutList(List<?> scr) {
        if (scr.size() <= 1)
            return;
        Collections.shuffle(scr);
        Random rand = new Random();
        scr.remove(rand.nextInt(scr.size()));
    }
}
