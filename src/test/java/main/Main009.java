package main;

/**
 * 最长递增子序列
 */
public class Main009 {
    public static void main(String[] args) {
        int n = 100000000;
//        int[] arr = new int[n];
        int[] arr = {1, 2, 3, 5, 8, 9, 10, 20, 30};
        th(arr, 4, arr.length);
    }

    public static int MLS(int[] arr) {
        int res = 0;
        // tmp中的最大值
        int max = Integer.MIN_VALUE;
        // 必然是个递增序列
        int[] tmp = new int[arr.length];
        for (int i : arr) {
            if (i <= max) {
                th(tmp, i, res);
            } else {
                tmp[res] = i;
                res++;
                max = i;
            }
        }
        return res;
    }

    // 二分查找位置替换
    public static void th(int[] arr, int i, int size) {
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (i > arr[mid]) {
                left = mid + 1;
            } else if (i < arr[mid]) {
                right = mid - 1;
            } else {
                // 等于什么都不做
                return;
            }
        }
        arr[left] = i;
    }
}
