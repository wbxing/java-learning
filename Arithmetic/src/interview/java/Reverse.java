package interview.java;

/**
 * 将一个字符串的指定部分进行翻转
 *
 * 比如：
 * str = "helloworld"
 * result = "hlrowolled"
 */
public class Reverse {

    public static String reverse(String str, int startIndex, int endIndex) {
        if (str != null) {
            StringBuilder sb = new StringBuilder(str.length());
            sb.append(str, 0, startIndex);
            for (int i = endIndex; i >= startIndex; i--) {
                sb.append(str.charAt(i));
            }
            sb.append(str.substring(endIndex + 1));
            return sb.toString();
        }
        return null;
    }
}
