package main;

import java.util.Scanner;

public class Main999 {
    static int[][] arr = {
            {0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 0, 1, 0}
    };
    static int max = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int[] ints : arr) {
            get(ints);
        }
        for (int i = 0; i < arr[0].length; i++) {
            int[] tmp = new int[arr.length];
            for (int j = 0; j < arr.length; j++) {
                tmp[j] = arr[j][i];
            }
            get(tmp);
        }
        System.out.println(max);
    }

    private static void get(int[] arr) {
        int m = 0;
        int pre = arr[0];
        for (int i : arr) {
            if (i == 1) {
                if (pre == 1) {
                    m++;
                } else {
                    m = 1;
                }
            } else {
                max = Math.max(m, max);
                m = 0;
            }
            pre = i;
        }
        max = Math.max(m, max);
    }
}
