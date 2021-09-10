package main;

import java.math.BigInteger;
import java.util.Scanner;

public class Main005 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            char[] cs = str.toCharArray();
            BigInteger result = get(cs[2]);
            for (int i = 3; i < cs.length; i++) {
                result = result.multiply(BigInteger.valueOf(16)).add(get(cs[i]));
            }
            System.out.println(result);
        }
    }
    private static BigInteger  get(char c){
        if ('A' <= c && 'F' >= c) {
            return BigInteger.valueOf(c - 55);
        } else {
            return BigInteger.valueOf(c- 48);
        }
    }
}