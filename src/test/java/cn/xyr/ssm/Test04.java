package cn.xyr.ssm;

import java.util.*;

public class Test04 {
    private static Map<Character, PriorityQueue<String>> map = new HashMap<>();
    private static Comparator<String> comparator = (s0, s1) -> {
        int len0 = s0.length();
        int len1 = s1.length();
        if (len0 != len1) {
            return len1 - len0;
        }
//        char c0 = s0.charAt(len1 - 1);
//        char c1 = s1.charAt(len2 - 1);
//        return c0 - c1;
        for (int i = 0; i < len0; i++) {
            char c0 = s0.charAt(i);
            char c1 = s1.charAt(i);
            if (c0 != c1) {
                return c0 - c1;
            }
        }
        return 0;
//        throw new NullPointerException();
    };


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        String start = null;
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            if (i == k) {
                start = str;
            } else {
                char c = str.charAt(0);
                PriorityQueue<String> queue = map.get(c);
                if (queue == null) {
                    queue = new PriorityQueue<>(comparator);
                    map.put(c, queue);
                }
                queue.add(str);
            }
        }
        if (start == null) {
            throw new NullPointerException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        char end = start.charAt(start.length() - 1);
        while (true) {
            PriorityQueue<String> queue = map.get(end);
            if (queue == null) {
                break;
            }
            String poll = queue.poll();
            sb.append(poll);
            if (queue.isEmpty()) {
                map.remove(end);
            }
            end = poll.charAt(poll.length() - 1);
        }
        System.out.println(sb);
    }
}
