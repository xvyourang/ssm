package main;


/**
 * NC128 接雨水问题
 * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Main007_1 {
    public static void main(String[] args) {
//        int [] arr= {5,1,1,1,1,1,1,5};
        int[] arr = {3, 1, 2, 5, 2, 4, 1, 3, 4};
//        int[] arr = new int[999999999 + 2];
//        arr[0] = 1000000000;
//        for (int i = 0; i < 999999999; i++) {
//            arr[i + 1] = 1;
//        }
//        arr[999999999 / 2] = 800000009;
//        arr[999999999 + 1] = 1000000000;
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
        int left = 0;
        int right = arr.length - 1;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        long result = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, arr[left]);
            rightMax = Math.max(rightMax, arr[right]);
            // 小的一遍移动
            if (leftMax < rightMax) {
                result += leftMax - arr[left];
                left++;
            } else {
                result += rightMax - arr[right];
                right--;
            }
        }
        return result;
    }

}
