package leetcode;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class RemoveKdigits {
//    public String removeKdigits(String num, int k) {
//        int l = num.length();
////        if (k >= l)
////            return "0";
//        ArrayList<String> split = new ArrayList<>(l);
//        StringBuilder tmp = new StringBuilder();
//        for (char c : num.toCharArray()) {
//            if (c == '0') {
//                if (tmp.length() > 0) {
//                    split.add(tmp.toString());
//                    tmp.delete(0, tmp.length());
//                }
//                split.add("0");
//            } else {
//                tmp.append(c);
//            }
//        }
//
//        StringBuffer rt = new StringBuffer();
//        for (String s : split) {
//            if (s.length() <= k) {
//                k = k - s.length();
//                continue;
//            }
//
//
//            if (k > 0) {
//                // remove largest
//                continue;
//            }
//
//            // append
//            if (!s.equals("0")) {
//                tmp.append(s);
//            } else {
//                if (tmp.length() > 0)
//                    tmp.append(s);
//            }
//        }
//        return null;
//    }
//
//    // 0 - 9
//    private String removeKLargestNum(String numS, int k) {
//        int[] counters = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        for (char c : numS.toCharArray()) {
//            int num = c - '0';
//            int old = counters[num];
//            counters[num] = old + 1;
//        }
//        StringBuffer stringBuffer = new StringBuffer(numS);
//        for (char c : numS.toCharArray()) {
//            if(k == 0)
//                stringBuffer.append(c);
//            if()
//
//        }
//
//    public static void main(String[] args) {
//        RemoveKdigits r = new RemoveKdigits();
//        String test = "10220000";
//        r.removeKdigits(test, 1);
//    }
}
