package fun;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Pick five numbers from 1-35,
 * pick another two numbers from 1-12
 */
public class BigHappy {
    final static int COUNT = 5;

    public static void main(String[] args) {
        BigHappy bigHappy = new BigHappy();

        for (int i = 0; i < COUNT; i++) {
            List<Integer> first = bigHappy.getFirst();
            List<Integer> second = bigHappy.getSecond();
            System.out.println("First:" + first + ", Second:" + second);
        }
    }

    private LinkedList<Integer> getFirst() {
        LinkedList<Integer> pool = getFirstPool();
        while (pool.size() > 5)
            cutList(pool);
        return pool;
    }

    private LinkedList<Integer> getSecond() {
        LinkedList<Integer> pool = getSecondPool();
        while (pool.size() > 2)
            cutList(pool);
        return pool;
    }

    private LinkedList<Integer> getFirstPool() {
        LinkedList<Integer> f = new LinkedList<>();
        for (int i = 1; i <= 35; i++) {
            f.add(i);
        }
        return f;
    }

    private LinkedList<Integer> getSecondPool() {
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 1; i <= 12; i++) {
            s.add(i);
        }
        return s;
    }



    private static void cutList(LinkedList<Integer> scr) {
        if (scr.size() <= 1)
            return;
        Random rand = new Random();
        scr.remove(rand.nextInt(scr.size()));
    }
}
