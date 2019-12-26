package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

public class Test {


    public static void main(String[] args) {


        Thread sub = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Sub started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Sub ended");
            }
        });
        try {
            sub.start();
            System.out.println("Main job started");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main job ended");
            sub.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main continue");
    }


//
//    public static void main(String[] args) {
//        for (int i2 = 0; i2 < 100; i2++) {
//
//            TimeRecorder t = new TimeRecorder("parallel");
//            ArrayList<Integer> test = new ArrayList<>(2000000);
//            for (int i = 0; i < 2000000; i++) {
//                test.add(i);
//            }
//            t.record("init");
//            parallel(test.subList(0, 8));
//            t.record("8");
//            parallel(test.subList(0, 100));
//            t.record("100");
//            parallel(test.subList(0, 200));
//            t.record("200");
//            parallel(test.subList(0, 500));
//            t.record("500");
//
//            System.out.println(t.onTaskEndAndReturnReport());
//
//            TimeRecorder t2 = new TimeRecorder("serial");
//
//            t2.record("init");
//            serial(test.subList(0, 8));
//            t2.record("8");
//            serial(test.subList(0, 100));
//            t2.record("100");
//            serial(test.subList(0, 200));
//            t2.record("200");
//            serial(test.subList(0, 500));
//            t2.record("500");
//
//            System.out.println(t2.onTaskEndAndReturnReport());
//
//        }
//
//    }
//
//    private static void parallel(Collection<Integer> nums) {
//        nums.parallelStream().forEach(num -> {
//            try {
//                Thread.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    private static void serial(Collection<Integer> nums) {
//        nums.stream().forEach(num -> {
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
