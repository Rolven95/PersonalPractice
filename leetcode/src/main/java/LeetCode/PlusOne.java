package LeetCode;

/*
Given a non-empty array of digits representing a non-negative integer,
plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list,
and each element in the array contain a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/plus-one
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

// 速度战胜94% 内存50%
// TODO 代码太难看 应考虑迭代
public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits.length == 1 && digits[0] == 0) {
            digits[0] = 1;
            return digits;
        }

        int digs = digits.length;
        int carryBit = 0;
        int root = digits[digs - 1] + 1 + carryBit;
        if (root == 10) {
            digits[digs - 1] = 0;
            carryBit = 1;
            if (digs == 1)
                return new int[]{1, 0};
            for (int i = digs - 2; i >= 0; i--) {
                int tmp = digits[i] + carryBit;
                if (tmp >= 10) {
                    carryBit = 1;
                    digits[i] = 0;
                    if (i == 0) {
                        int[] carryToReturn = new int[digs + 1];
                        for (int l = digs; l >= 1; l--) { // TODO 有拷贝工具方法
                            carryToReturn[l] = digits[l - 1];
                        }
                        carryToReturn[0] = 1;
                        return carryToReturn;
                    }
                } else {
                    digits[i] = tmp;
                    break;
                }
            }
        } else {
            digits[digs - 1] = root;
        }

        return digits;
    }
}