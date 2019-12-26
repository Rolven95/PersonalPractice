package leetcode;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        /**
         * abcab
         *
         * char[26]; last char
         * dp[0] = 1;
         * dp[n] = if(dp[n-1] < )
         * */
        if (s == null || s.length() == 0)
            return 0;

        char[] chars = s.toCharArray();
        HashMap<Character, Integer> lastOneIndex = new HashMap<>();
        int[] dp = new int[s.length()];
        dp[0] = 1;
        lastOneIndex.put(chars[0], 0);
        int longest = 1;
        for (int i = 1; i < chars.length; i++) {

            if (!lastOneIndex.containsKey(chars[i])) { // has before
                dp[i] = dp[i - 1] + 1;
            } else {
                int disToLastSame = i - lastOneIndex.get(chars[i]);
                if (dp[i - 1] >= disToLastSame)
                    dp[i] = disToLastSame;
                else
                    dp[i] = dp[i - 1] + 1;
            }
            if (dp[i] > longest)
                longest = dp[i];
            lastOneIndex.put(chars[i], i);
        }
        return longest;
    }

    public static void main(String[] args) {
        String test = "dvdf";
        LengthOfLongestSubstring l = new LengthOfLongestSubstring();
        l.lengthOfLongestSubstring(test);
    }

}
