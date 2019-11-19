package LeetCode;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/***
 Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.
  

 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?

 Credits:
 Special thanks to @pbrother for adding this problem and creating all test cases.

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/combination-sum-iv
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
//@Slf4j
class CombinationSum4 {
    HashMap<Integer, Integer> cache = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        if (cache.containsKey(target))
            return cache.get(target);

        int solution = 0;
        for (Integer integer : nums) {
            if (integer > target)
                continue;
            if (integer == target) {
                solution += 1;
                continue;
            }
            solution += combinationSum4(nums, target - integer);
        }
        cache.put(target, solution);
        System.out.println(nums.toString() + ", target:" + target);
        return solution;
    }

    public static void main(String[] args) {
        CombinationSum4 combinationSum4 = new CombinationSum4();
        int a = combinationSum4.combinationSum4(new int[]{1, 2, 3}, 7);

    }
}