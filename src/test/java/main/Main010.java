package main;

import java.util.Arrays;
import java.util.Scanner;

public class Main010 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            int n = scanner.nextInt();
//            int temp = integerhuafen(n);
//            System.out.print(temp);
//        }
        integerhuafen(20);
//        for (int i = 1; i < 21; i++) {
//            System.out.println(i+"->"+integerhuafen(i));
//        }
    }
    public static int integerhuafen(int n) {
        // 存放 i拆成最大j的组合数
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++ ){
                if(i == 1 || j == 1){
                    dp[i][j] = 1;
                }else if (i == j) {
                    dp[i][j] = 1 + dp[i][j - 1];
                }else if (i < j) {
                    dp[i][j] = dp[i][i];
                }else {
                    dp[i][j] = dp[i - j][j] + dp[i][j - 1];
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[n][n];
    }
}