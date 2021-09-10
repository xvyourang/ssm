package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 后缀表达式(逆波兰表达式)
 * 1.先将字符串分割
 * 2.用逆波兰计算(连括号一起)
 * <p>
 * 2.2 用正则分割括号
 */
public class Main666 {
    public static void main(String[] args) {
        String str = "2^(4^3)";
        System.out.println(parse(str));
        System.out.println(nbl(parse(str)));
    }

    /**
     * 先转换为逆波兰表达式计算后计算
     */
    private static BigDecimal nbl(List<String> list) {
        LinkedList<String> deque = new LinkedList<>();
        LinkedList<String> result = new LinkedList<>();
        for (String str : list) {
            boolean fh = str.length() == 1 && fh(str.charAt(0));
            if (fh) {
                if (deque.isEmpty()) {
                    deque.push(str);
                } else if (")".equals(str)) {
                    String s;
                    while (!(s = deque.pop()).equals("(")) {
                        result.push(s);
                    }
                } else {
                    String tmp = deque.peek();
                    if (!str.equals("(")) {
                        if (tmp.equals("(")) {
                            deque.push(str);
                        } else {
                            if (compareTo(str, tmp)) {
                                result.push(str);
                            } else {
                                result.push(deque.pop());
                                deque.push(str);
                            }
                        }
                    } else {
                        deque.push(str);
                    }
                }
            } else {
                result.push(str);
            }
        }
        while (!deque.isEmpty()) {
            result.push(deque.pop());
        }
        System.out.println(result);

        while (!result.isEmpty()) {
            String s = result.removeLast();
            if (s.length() == 1 && fh(s.charAt(0))) {
                BigDecimal b = new BigDecimal(deque.pop());
                BigDecimal a = new BigDecimal(deque.pop());
                deque.push(js(a, b, s.charAt(0)) + "");
            } else {
                deque.push(s);
            }
        }
        return new BigDecimal(deque.pop());
    }

    private static BigDecimal js(BigDecimal a, BigDecimal b, char c) {
        switch (c) {
            case '^':
                return a.pow(b.intValue());
            case '*':
                return a.multiply(b);
            case '/':
                return a.divide(b, 2, 4);
            case '+':
                return a.add(b);
            case '-':
                return a.subtract(b);
            default:
                return BigDecimal.ZERO;
        }
    }

    /**
     * 将表达式转换为字符和数字
     */
    private static List<String> parse(String str) {
        List<String> result = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (fh(c)) {
                if (c == '-' && i > 0) {
                    if (fh(str.charAt(i - 1))) {
                        start = i;
                        continue;
                    }
                }
                if (start != -1) {
                    result.add(str.substring(start, i));
                    start = -1;
                }
                result.add(c + "");
            } else {
                if (start == -1) {
                    start = i;
                }
            }
        }
        if (!str.endsWith(")")) {
            result.add(str.substring(start));
        }
        return result;
    }

    private static boolean fh(char c) {
        return !(('0' <= c && c <= '9') || c == '.');
    }

    private static boolean compareTo(String s1, String s2) {
        return ysj(s1) >= ysj(s2);
    }

    private static int ysj(String str) {
        switch (str) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            case "(":
            case ")":
                return 4;
            default:
                return 0;
        }
    }
}
