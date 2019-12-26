package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * */
public class KuoHao {
    public List<String> generateParenthesis(int n) {
        if(n <= 0)
            return new LinkedList<>();

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            stringBuffer.insert(i, "()");
        }
        char[] chars = stringBuffer.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {

        }
        return null;
    }
}
