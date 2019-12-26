package leetcode;

/**
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * <p>
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 */
public class CheckPerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num == 0)
            return true;

        int half = num / 2;
        int sum = 0;
        for (int i = 1; i <= half; i++) {
            if (num % i == 0)
                sum += i;
        }
        if (sum == num)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        CheckPerfectNumber c = new CheckPerfectNumber();
        c.checkPerfectNumber(28);
    }
}
