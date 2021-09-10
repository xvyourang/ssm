package cn.xyr.ssm;

import java.util.Scanner;

public class Test05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] dieArr = new int[m];
        for (int i = 0; i < m; i++) {
            int die = scanner.nextInt();
            dieArr[i] = die;
        }
        int k = scanner.nextInt();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            if (i == 0) {
                if (i + k >= m) {
                    max = n;
                    break;
                } else {
                    max = dieArr[i + k] - 1;
                }
            } else if (i + k >= m) {
                int start = dieArr[i - 1];
                max = Math.max(max, n - start);
                break;
            } else {
                int start = dieArr[i - 1];
                int end = dieArr[i + k] - 1;
                max = Math.max(max, end - start);
            }
        }
        System.out.println(max);
    }
}
