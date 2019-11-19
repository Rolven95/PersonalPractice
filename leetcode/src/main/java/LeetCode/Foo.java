package LeetCode;

import java.util.concurrent.CountDownLatch;

/**
 * Suppose we have a class:
 * <p>
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * The same instance of Foo will be passed to three different threads.
 * Thread A will call one(), thread B will call two(), and thread C will call three().
 * Design a mechanism and modify the program to ensure that two() is executed after one(),
 * and three() is executed after two().
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: "onetwothree"
 * Explanation: There are three threads being fired asynchronously.
 * The input [1,2,3] means thread A calls one(), thread B calls two(),
 * and thread C calls three(). "onetwothree" is the correct output.
 * Example 2:
 * <p>
 * Input: [1,3,2]
 * Output: "onetwothree"
 * Explanation: The input [1,3,2] means thread A calls one(),
 * thread B calls three(), and thread C calls two(). "onetwothree" is the correct output.
 *  
 * <p>
 * Note:
 * <p>
 * We do not know how the threads will be scheduled in the operating system,
 * even though the numbers in the input seems to imply the ordering.
 * The input format you see is mainly to ensure our tests' comprehensiveness.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Foo {
    private CountDownLatch countDownLatchA;
    private CountDownLatch countDownLatchB;

    public Foo() {
        countDownLatchA = new CountDownLatch(1);
        countDownLatchB = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        countDownLatchA.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatchA.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        countDownLatchB.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        countDownLatchB.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
