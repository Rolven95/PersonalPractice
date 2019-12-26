package leetcode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，
 * 使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。
 * <p>
 * 举个例子，A = "abcd"，B = "cdabcdab"。
 * <p>
 * 答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，
 * 此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
 * <p>
 * 注意:
 * <p>
 * A 与 B 字符串的长度在1和10000区间范围内。
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        if (A == null || B == null || A.isEmpty() || B.isEmpty())
            return -1;

        char[] charsA = A.toCharArray();
        char[] charsB = B.toCharArray();

        HashMap<Character, LinkedList<Integer>> aCharIndexs = new HashMap<>();
        for (int i = 0; i < charsA.length; i++) {
            char curr = charsA[i];
            if (!aCharIndexs.containsKey(curr))
                aCharIndexs.put(curr, new LinkedList<>());
            LinkedList<Integer> charIndexs = aCharIndexs.get(curr);
            charIndexs.add(i);
            aCharIndexs.put(curr, charIndexs);
        }

        // find init place.
        if (!aCharIndexs.containsKey(charsB[0]))
            return -1;
        LinkedList<Integer> aCharIndex = aCharIndexs.get(charsB[0]);
        for (Integer charIndex : aCharIndex) { // fixme might not be smallest answer
            int result = startCheck(charIndex, charsA, charsB);
            if (result != -1)
                return result;
        }
        return -1;
    }

    private int startCheck(int offsetA, char[] charsA, char[] charsB) {
//        int copy = 1;
        int cursor = offsetA;

        for (char charB : charsB) {
            if (charB != charsA[cursor])
                return -1;
            if (cursor == charsA.length - 1) {
                cursor = 0;
//                copy++;
            } else {
                cursor++;
            }
        }

        int rest = charsB.length - (charsA.length - offsetA);
        if (rest <= 0)
            return 1;
        else {
            double resA = (double) rest / (double) charsA.length;
            return (int) Math.ceil(resA) + 1;
        }
    }

    public static void main(String[] args) {
        String A = "abcd";
        String B = "cdabcdab";
//        String A = "aa";
//        String B = "aaa";
        RepeatedStringMatch repeatedStringMatch = new RepeatedStringMatch();
        repeatedStringMatch.repeatedStringMatch(A, B);
    }
}
