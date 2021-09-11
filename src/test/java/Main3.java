import java.util.*;

/**
 * 输入n个字符串
 * 打印出这些字符串所能组合出的所有可能、每次字符串都要使用到
 * 字典序升序输出
 * 输入
 * 3
 * a b c
 * 输出
 * a b c
 * a c b
 * b a c
 * b c a
 * c a b
 * c b a
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        String[] strings = line.split("\\s");
        boolean[] v = new boolean[n];
        // 总可能数是n的阶乘
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        solution(strings, v, set, sb, 0);
        List<String> list = new ArrayList<>(set);
        list.sort((s0, s1) -> {
            // 字典序排序
            int len = s0.length();
            for (int i = 0; i < len; i++) {
                char c0 = s0.charAt(i);
                char c1 = s1.charAt(i);
                if (c0 != c1) {
                    return c0 - c1;
                }
            }
            return 0;
        });
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * @param v
     * @param sb
     * @param index 已经排列了几个数了
     */
    private static void solution(String[] strings, boolean[] v, Set<String> set, StringBuilder sb, int index) {
        if (index == v.length) {
            set.add(sb.toString());
            return;
        }
        for (int i = 0; i < v.length; i++) {
            if (!v[i]) {
                StringBuilder tmp = new StringBuilder(sb);
                tmp.append(strings[i]);
                v[i] = true;
                solution(strings, v, set, tmp, index + 1);
                v[i] = false;
            }
        }
    }
}
