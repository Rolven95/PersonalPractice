package fun;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * Pick five numbers from 1-35,
 * pick another two numbers from 1-12
 */
public class BigHappy {
    public static void main(String[] args) {
        BigHappy bigHappy = new BigHappy();
        String pick = bigHappy.getPick(5, 2, null, 1234);
        System.out.println(pick);
    }

    /**
     * Get a single pick.
     *
     * @param first   how many in first section
     * @param second  how many in second section
     * @param seedSrc random seed, only for outer layer. Set timeStamp if null.
     * @param repeat  return 1 result from "repeat" generated picks. Must >= 1.
     */
    public String getPick(int first, int second, Long seedSrc, int repeat) {
        if (first < 5 || first > 35)
            return "error first arg";
        if (second < 2 || second > 12)
            return "error second arg";
        long seed;
        if (seedSrc == null)
            seed = System.currentTimeMillis();
        else
            seed = seedSrc;

        LinkedList<Pick> picks = new LinkedList<>();
        for (int i = 0; i < repeat; i++) {
            picks.add(new Pick(getFirst(first), getSecond(second)));
        }


        while (picks.size() > 1)
            cutList(picks, seed);
        if (picks.isEmpty())
            return "Error, empty pick.";
        return picks.peek().toString();
    }

    private LinkedList<Integer> getFirst(int count) {
        LinkedList<Integer> pool = getFirstPool();
        while (pool.size() > count)
            cutList(pool, null);
        return pool;
    }

    private LinkedList<Integer> getSecond(int count) {
        LinkedList<Integer> pool = getSecondPool();
        while (pool.size() > count)
            cutList(pool, null);
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


    private static void cutList(LinkedList<?> scr, Long seed) {
        if (scr.size() <= 1)
            return;

        Collections.shuffle(scr);

        Random rand;
        if (seed == null)
            rand = new Random();
        else
            rand = new Random(seed);
        scr.remove(rand.nextInt(scr.size()));

    }

    @Data
    @AllArgsConstructor
    private class Pick {
        private List<Integer> first;
        private List<Integer> second;

        @Override
        public String toString() {
            first.sort(new Sorter<>());
            second.sort(new Sorter<>());
            return "First:" + first + ", Second:" + second;
        }
    }

    private class Sorter<T> implements Comparator<T> {

        @Override
        public int compare(T i1, T i2) {
            if (i1 == null || i2 == null)
                return -1;

            int o1 = (Integer)i1;
            int o2 = (Integer) i2;

            if (o1 == o2)
                return 0;
            else if (o1 < o2)
                return -1;
            else
                return 1;
        }
    }
}
