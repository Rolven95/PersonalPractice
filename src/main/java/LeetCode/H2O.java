package LeetCode;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * There are two kinds of threads, oxygen and hydrogen.
 * Your goal is to group these threads to form water molecules. 
 * There is a barrier where each thread has to wait until a complete molecule can be formed.
 * Hydrogen and oxygen threads will be given a releaseHydrogen 
 * and releaseOxygen method respectfully,
 * which will allow them to pass the barrier.
 * These threads should pass the barrier in groups of three,
 * and they must be able to immediately bond with each other to form a water molecule. 
 * You must guarantee that all the threads from one molecule bond
 * before any other threads from the next molecule do.
 * <p>
 * In other words:
 * <p>
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present,
 * it has to wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present,
 * it has to wait for an oxygen thread and another hydrogen thread.
 * Write synchronization code for oxygen
 * and hydrogen molecules that enforces these constraints.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "HOH"
 * Output: "HHO"
 * Explanation: "HOH" and "OHH" are also valid answers.
 * Example 2:
 * <p>
 * Input: "OOHHHH"
 * Output: "HHOHHO"
 * Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH",
 * "HHOOHH", "HOHOHH" and "OHHOHH"
 * <p>
 * are also valid answers.
 *  
 * <p>
 * Constraints:
 * <p>
 * Total length of input string will be 3n, where 1 ≤ n ≤ 50.
 * Total number of H will be 2n in the input string.
 * Total number of O will be n in the input string.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/building-h2o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * "HHO HOH HHO HHO OHH OHH HHO HHO HHO OHH HOH"
 */
class H2O {
    private CyclicBarrier mBarrier;
    private Semaphore mOSemaphore;
    private Semaphore mHSemaphore;

    public H2O() {
        mBarrier = new CyclicBarrier(3);
        mOSemaphore = new Semaphore(1);
        mHSemaphore = new Semaphore(2);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        mHSemaphore.acquire();
        {
            releaseHydrogen.run();
        }
        try {
            mBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        mOSemaphore.acquire();
        {
            releaseOxygen.run();
        }
        try {
            mBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        mBarrier.reset();
        mOSemaphore.release();
        mHSemaphore.release(2);
    }
}