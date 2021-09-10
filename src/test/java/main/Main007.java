package main;


/**
 * NC128 接雨水问题
 * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Main007 {
    public static void main(String[] args) {
//        int [] arr= {5,1,1,1,1,1,1,5};
//        int [] arr= {3,1,2,5,2,4,1,3};
        int[] arr = new int[999999999 + 2];
        arr[0] = 1000000000;
        for (int i = 0; i < 999999999; i++) {
            arr[i + 1] = 1;
        }
        arr[999999999 / 2] = 800000009;
        arr[999999999 + 1] = 100000000;
//        long l = Runtime.getRuntime().totalMemory();
//        long l2 = Runtime.getRuntime().freeMemory();
//        System.out.println("总内存" + l + "\n空闲内存" + l2 + "\n已使用内存" + (l - l2));
        System.out.println(maxWater(arr));

    }

    public static long maxWater(int[] arr) {
        long l = System.currentTimeMillis();
        if (arr.length < 2) {
            return 0L;
        }
        // write code here
        int left = 0;
        long result = 0;
        long tmp = 0;
        int size = arr.length - 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[left]) {
                // 当前高度比左边低，计算最高积水
                tmp += arr[left] - arr[i];
            } else {
                // 当前高度比左边高，计算结果，设置left为当前高度
                result += tmp;
                left = i;
                tmp = 0;
            }
        }
        // 最终计算
        int maxIndex = left;
        left = arr.length - 1;
        tmp = 0;
        for (int i = arr.length - 1; i >= maxIndex; i--) {
            if (arr[i] < arr[left]) {
                // 当前高度比右边低，计算最高积水
                tmp += arr[left] - arr[i];
            } else {
                // 当前高度比右边高，计算结果，设置left为当前高度
                result += tmp;
                left = i;
                tmp = 0;
            }
        }
        System.out.println("内存炸了？" + (System.currentTimeMillis() - l));
        return result;
    }

}
