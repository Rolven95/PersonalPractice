package LeetCode;

/**
 * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: "Hello"
 * 输出: "hello"
 * 示例 2：
 * <p>
 * 输入: "here"
 * 输出: "here"
 * 示例 3：
 * <p>
 * 输入: "LOVELY"
 * 输出: "lovely"
 */
public class LowerCase {
    private static int DIFF;
    private static char z;
    private static char a;
    private static char A;
    private static char Z;

    static {
        a = 'a';
        z = 'z';
        A = 'A';
        Z = 'Z';
        char bigA = 'A';
        DIFF = (int) a - (int) bigA;
    }

    public String toLowerCase(String str) {
        if (str == null || str == "")
            return str;
        StringBuilder stringBuffer = new StringBuilder();
        for (char c : str.toCharArray()) {
            int toInt = (int) c;
            if (toInt >= (int) A && toInt <= (int) Z) {
                stringBuffer.append((char) ((int) c + DIFF));
            } else{
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
//        System.out.println((int) 'a');
//        System.out.println((int) 'b');
//        System.out.println((int) 'c');
//        System.out.println((int) 'd');
//        System.out.println((int) 'e');
//        System.out.println((int) 'f');
//        System.out.println((int) 'g');
//        System.out.println((int) 'h');
//        System.out.println((int) 'i');
//        System.out.println((int) 'j');
//        System.out.println((int) 'k');
//        System.out.println((int) 'l');
//        System.out.println((int) 'm');
//        System.out.println((int) 'n');
//        System.out.println((int) 'o');
//        System.out.println((int) 'p');
//        System.out.println((int) 'q');
//        System.out.println((int) 'r');
//        System.out.println((int) 's');
//        System.out.println((int) 't');
//        System.out.println((int) 'u');
//        System.out.println((int) 'v');
//        System.out.println((int) 'w');
//        System.out.println((int) 'x');
//        System.out.println((int) 'y');
//        System.out.println((int) 'z');
//
//
//
//
//
//        System.out.println((int) 'A');
//        System.out.println((int) 'B');
//        System.out.println((int) 'C');
//        System.out.println((int) 'D');
//        System.out.println((int) 'E');
//        System.out.println((int) 'F');


        String test = "Hello";
        LowerCase lowerCase = new LowerCase();
        lowerCase.toLowerCase(test);
    }
}
