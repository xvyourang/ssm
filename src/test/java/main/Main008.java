package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * NC95 数组中的最长连续子序列
 * 给定无序数组arr，返回其中最长的连续序列的长度(要求值连续，位置可以不连续,例如 3,4,5,6为连续的自然数）
 * arr[i]<10^8
 * arr.length<10^5
 */
public class Main008 {
    public static void main(String[] args) {
        int n = 100000000;
//        int[] arr = new int[n];
        int[] arr = {100,4,200,1,3,2};
        System.out.println(MLS(arr));

    }

    /**
     * 排序
     */
    public static int MLS(int[] arr) {
        Arrays.sort(arr);
        int max = 1;
        int res = 1;
        int now = arr[0];
        for (int i : arr) {
            if (now + 1 == i) {
                now = i;
                res++;
            } else if (now + 1 < i) {
                max = Math.max(max, res);
                res = 1;
                now = i;
            }
        }
        max = Math.max(max, res);
        return max;
    }

}
