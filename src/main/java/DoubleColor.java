
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.*;

public class DoubleColor {

    public static void main(String[] args) {
        long TOTAL_POSS = new BigDecimal(33).pow(6).multiply(new BigDecimal(16)).longValue();
//        final long TOTAL_POSS = 103;
        final int SECTION_SIZE = 10000;

        final ExecutorService executorService = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        final ConcurrentHashMap<Integer, Integer> result = new ConcurrentHashMap<>();

        final CountDownLatch countDownLatch = new CountDownLatch(SECTION_SIZE);


        for (int t = 0; t < SECTION_SIZE; t++) {
            int finalT = t;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int offset = (int) (TOTAL_POSS / 10000L * (finalT + 1));
                    int max = (int) (TOTAL_POSS / 10000L);
                    System.out.println("Thread Started. t = " + finalT);

                    LinkedList<Integer> allList = new LinkedList<>();
                    for (int i = 0; i < max; i++) {
                        allList.add(i);
                    }
                    System.out.println("All Map inited. t = " + finalT);

                    int size = max;
                    while (size > 2) {
                        allList.remove(RandomUtils.nextInt(0, size));
                        size--;
                    }
                    System.out.println("List filtered. t = " + finalT);

                    for (Integer integer : allList) {
                        result.put(integer + offset, 0);
//                        System.out.println("offSet:" + integer + offset);
//                        System.out.println("Result Added:" + integer + offset);
                    }
                    System.out.println("All map added. t = " + finalT);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All job done!");
        for (Integer integer : result.keySet())
            System.out.println(integer);
    }
}
