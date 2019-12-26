package leetcode;

import java.util.HashMap;

public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (!count.containsKey(nums[i]))
                count.put(nums[i], 0);
            count.put(nums[i], 1 + count.get(nums[i]));
            if (nums[i] > max)
                max = nums[i];
            if (nums[i] < min)
                min = nums[i];
        }

        int sequence = 0;
        for (int i = min; i <= max; i++) {
            if (count.containsKey(i)) {
                sequence = sequence + count.get(i);
            }
            if (sequence >= k)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] test = new int[]{2, 3, 1, 3, 4, 2, 4, 2, 3, 4};
        FindKthLargest findKthLargest = new FindKthLargest();
        findKthLargest.findKthLargest(test, 3);
    }
}
