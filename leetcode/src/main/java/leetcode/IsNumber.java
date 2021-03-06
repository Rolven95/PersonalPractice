package leetcode;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。
 * 这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
 */
public class IsNumber {


    public boolean isNumber(String s) {
        if (s == null || s.isEmpty())
            return false;

        int start = '0';
        int end = '9';
        boolean sign = false;
        boolean hasDigit = false;
        boolean hasE = false;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '-' || current == '+') {
                if (sign)
                    return false;
                else
                    sign = true;
            } else if (current == 'e') {
                if (hasE)
                    return false;
                else {
                    if (!hasDigit)
                        return false;
                    hasE = true;
                    sign = false;
                }
            } else if (current < start || current > end) {
                return false;
            }
            hasDigit = true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println((int) '0');
        System.out.println((int) '1');
        System.out.println((int) '2');
        System.out.println((int) '3');
        System.out.println((int) '4');
        System.out.println((int) '5');
        System.out.println((int) '6');
        System.out.println((int) '7');
        System.out.println((int) '8');
        System.out.println((int) '9');
        System.out.println((int) '0');
    }
}
