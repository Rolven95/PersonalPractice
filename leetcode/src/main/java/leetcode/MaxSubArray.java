package leetcode;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int dpCurrentMax = nums[0];
        int i = 1;
        while (i < nums.length) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if(dp[i] > dpCurrentMax)
                dpCurrentMax = dp[i];
            i++;
        }
//        for (int i1 : dp) {
//            System.out.println(i1);
//        }
        return dpCurrentMax;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] testCase = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArray.maxSubArray(testCase);
    }
}