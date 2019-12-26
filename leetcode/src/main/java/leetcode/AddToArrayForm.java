package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].
 * <p>
 * Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 * <p>
 *  
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,2,0,0], K = 34
 * Output: [1,2,3,4]
 * Explanation: 1200 + 34 = 1234
 * Example 2:
 * <p>
 * Input: A = [2,7,4], K = 181
 * Output: [4,5,5]
 * Explanation: 274 + 181 = 455
 * Example 3:
 * <p>
 * Input: A = [2,1,5], K = 806
 * Output: [1,0,2,1]
 * Explanation: 215 + 806 = 1021
 * Example 4:
 * <p>
 * Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * Output: [1,0,0,0,0,0,0,0,0,0,0]
 * Explanation: 9999999999 + 1 = 10000000000
 *  
 * <p>
 * Note：
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * If A.length > 1, then A[0] != 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddToArrayForm {
    public List<Integer> addToArrayForm(int[] A, int K) {
        char[] kArray = String.valueOf(K).toCharArray();
        int kCursor = kArray.length - 1;
        boolean carry = false;

        LinkedList<Integer> toReturn = new LinkedList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            if (kCursor < 0) {
                int sum = A[i];
                if (carry) {
                    sum++;
                    carry = false;
                }
                if (sum >= 10) {
                    toReturn.addFirst(sum - 10);
                    carry = true;
                } else {
                    toReturn.addFirst(sum);
                }

            } else {
                Integer k = Integer.valueOf(kArray[kCursor] + "");
                int sum = k + A[i];
                if (carry) {
                    sum++;
                    carry = false;
                }
                if (sum >= 10) {
                    toReturn.addFirst(sum - 10);
                    carry = true;
                } else {
                    toReturn.addFirst(sum);
                }
                kCursor--;
            }
        }

        while (kCursor >=0){
            toReturn.addFirst(Integer.valueOf(kArray[kCursor] + ""));
            kCursor--;
        }

        if (carry) {
            toReturn.addFirst(1);
        }
        return toReturn;
    }


    public static void main(String[] args) {
        int[] test = new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        AddToArrayForm addToArrayForm = new AddToArrayForm();
        addToArrayForm.addToArrayForm(test, 1);
    }
}
