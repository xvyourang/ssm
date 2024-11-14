package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 看下用例对不对
 * 穷举排列组合
 */
public class Test02 {
    // 计数
    private static int count = 0;
    // 结果
    private static String result = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        // 362880种
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }
        for (int i = 1; i <= n; i++) {
            js(k, "", arr, i);
        }
        System.out.println(result);
    }

    /**
     * @param k   结果索引
     * @param str 当前字符串
     * @param arr 可用数组
     * @param now 当前数字
     */
    private static void js(int k, String str, List<Integer> arr, int now) {
        String tmp = str + now;
        if (arr.size() == 1) {
            count++;
            // 用这句调试
// 去页面
//            System.out.println(tmp);
            if (count == k) {
                result = tmp;
            }
            return;
        }
        List<Integer> list = new ArrayList<>(arr);
        list.remove((Integer) now);
        for (int i = 0; i < list.size(); i++) {
            js(k, tmp, list, list.get(i));
        }
    }
}
