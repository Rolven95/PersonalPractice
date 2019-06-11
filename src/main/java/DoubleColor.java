
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.*;

public class DoubleColor {

    public static void main(String[] args) {
        long totalPossible = new BigDecimal(33).pow(6).multiply(new BigDecimal(16)).longValue();
        ExecutorService executorService = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        final ConcurrentHashMap<Integer, Integer> result = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int t = 0; t < 100; t++) {
            int finalT = t;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    int offset = (int) (totalPossible / 100L * (finalT + 1));
                    int max = (int) (totalPossible / 100L);
                    System.out.println("Thread Started. t = " + finalT);

                    LinkedList<Integer> allList = new LinkedList<>();
                    for (int i = 0; i < max; i++) {
                        allList.add(i);
                    }
                    System.out.println("All Map inited. t = " + finalT);

                    int size = max;
                    while (size > 2) {
                        allList.remove(RandomUtils.nextInt(0, size));
                    }
                    System.out.println("List filtered. t = " + finalT);

                    for (Integer integer : allList) {
                        result.put(integer, null);
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
        for (Integer integer : result.keySet())
            System.out.println(integer);
    }
}
