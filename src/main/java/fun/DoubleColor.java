package fun;

import Common.TimeRecorder;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoubleColor {
//    final static long TOTAL = 32L * 32L * 32L * 32L * 32L;
    final static long TOTAL = 32L * 32L * 32L * 32L * 32L * 32L * 15L;

    final static int WORKER = 10;
    static ExecutorService POOL = Executors.newFixedThreadPool(WORKER);

    public static void main(String[] args) {
        TimeRecorder time = new TimeRecorder("Double Color");

        final ConcurrentHashMap<Long, Integer> init2 = new ConcurrentHashMap<>();

        CountDownLatch latch = new CountDownLatch(WORKER);
        long part = TOTAL / WORKER;
        for (int t = 0; t < WORKER; t++) {
            int finalT = t;
            Random r = new Random(System.currentTimeMillis() + finalT);
            POOL.execute(() -> {
                long start = finalT * part;
                long end = (finalT + 1) * part;
                for (long i = start; i < end; i++) {
                    int filter = r.nextInt(10);
                    if (filter == getRandomDigit())
                        init2.put(i, 0);
                }
                latch.countDown();
                System.out.println("Thread Done, No." + finalT);
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Now have size :" + init2.size());

        time.record("init");
        System.out.println(time.onTaskEndAndReturnReport());
        ArrayList<Long> init3 = new ArrayList<>(init2.size());
        init3.addAll(init2.keySet());
        init2.clear();


        CountDownLatch latch2 = new CountDownLatch(WORKER);
        int subPart = init3.size() / WORKER;
        for (int t = 0; t < WORKER; t++) {
            int finalT = t;
            int start = finalT * subPart;
            int end = (finalT + 1) * subPart;
            POOL.execute(() -> {
                List<Long> sub = new ArrayList<>(init3.subList(start, end));
                System.out.println("Sub list size:" + sub.size());

                while (sub.size() >= 100)
                    sub = filter(sub);
                System.out.println("1/100 cut done! Thread:" + finalT);

                LinkedList<Long> cut = new LinkedList<>(sub);
                sub.clear();
                while (cut.size() > 1) {
                    cutList(cut);
                }

                System.out.println("Thread done! result: " + cut);
                latch2.countDown();
            });
        }

        try {
            latch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(time.onTaskEndAndReturnReport());

    }

    private static List<Long> filter(List<Long> src) {
        if (src.size() == 1)
            return src;
        List<Long> r = new LinkedList<>();
        Random rand = new Random(System.currentTimeMillis());
        for (Long num : src) {
            if (rand.nextInt(10) == getRandomDigit())
                r.add(num);
        }
        return r;
    }

    private static void cutList(LinkedList<Long> scr) {
        if (scr.size() <= 1)
            return;
        Random rand = new Random(System.currentTimeMillis());
        scr.remove(rand.nextInt(scr.size()));
    }

    private static int getRandomDigit() {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        int r = NumberUtils.toInt(timeStamp.substring(timeStamp.length()-1));
        return r;
    }
}
