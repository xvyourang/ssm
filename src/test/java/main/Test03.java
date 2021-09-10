package main;

import java.util.Scanner;

/**
 * 动态规划
 * 有向无环图
 */
public class Test03 {
    public static void main(String[] args) {
        // 动态规划..
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // 列表长度
        int m = scanner.nextInt();
        int[][] arr = new int[n][n];
        int[] res = new int[n];
        for (int i = 0; i < m; i++) {
            int s = scanner.nextInt() - 1;
            int e = scanner.nextInt() - 1;
            int t = scanner.nextInt();
            if (arr[s][e] != 0) {
                arr[s][e] = Math.min(arr[s][e], t);
            } else {
                arr[s][e] = t;
            }
        }

        int start = scanner.nextInt() - 1;
        int end = scanner.nextInt() - 1;
        // 计算start到每个点的距离
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                // 03  01 13 02 23
                for (int k = i + 1; k < j; k++) {
                    // 走不到
                    if (arr[i][k] == 0 || arr[k][j] == 0) {
                        continue;
                    }
                    int value = arr[i][k] + arr[k][j];
                    if (arr[i][j] != 0) {
                        arr[i][j] = Math.min(arr[i][j], value);
                    } else {
                        arr[i][j] = value;
                    }
                }
            }
        }
        int result = arr[start][end];
        if (result != 0) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }
}
