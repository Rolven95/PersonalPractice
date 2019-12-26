package leetcode;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * Suppose you are given the following code:
 * <p>
 * class ZeroEvenOdd {
 *      public ZeroEvenOdd(int n) { ... }      // constructor
 * public void zero(printNumber) { ... }  // only output 0's
 * public void even(printNumber) { ... }  // only output even numbers
 * public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 * <p>
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the thread is given a printNumber method to output an integer.
 * Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(),
 * the other calls even(), and the last one calls odd(). "0102" is the correct output.
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: "0102030405"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class ZeroEvenOdd {
    private LinkedList<Integer> num;

    private Semaphore semaphoreZ;
    private Semaphore semaphoreE;
    private Semaphore semaphoreO;

    public ZeroEvenOdd(int n) {
        num = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            num.addLast(i + 1);
        }
        semaphoreZ = new Semaphore(1);
        semaphoreE = new Semaphore(0);
        semaphoreO = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        if (!semaphoreZ.tryAcquire(10, TimeUnit.MILLISECONDS))
            return;
        if (num.isEmpty())
            return;
        printNumber.accept(0);
        if (num.getFirst() % 2 == 1)
            semaphoreO.release();
        else
            semaphoreE.release();

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        if (!semaphoreO.tryAcquire(10, TimeUnit.MILLISECONDS))
            return;
        if (num.isEmpty())
            return;
        printNumber.accept(num.poll());
        semaphoreZ.release();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        if (!semaphoreE.tryAcquire(10, TimeUnit.MILLISECONDS))
            return;
        if (num.isEmpty())
            return;
        printNumber.accept(num.poll());
        semaphoreZ.release();
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        zeroEvenOdd.zero(new IntConsumer() {
                            @Override
                            public void accept(int value) {
                                System.out.println(value);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        zeroEvenOdd.even(new IntConsumer() {
                            @Override
                            public void accept(int value) {
                                System.out.println(value);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        zeroEvenOdd.odd(new IntConsumer() {
                            @Override
                            public void accept(int value) {
                                System.out.println(value);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}