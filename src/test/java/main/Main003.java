package main;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int count = scanner.nextInt();
            boolean[] arr = new boolean[1001];
            for (int i = 0; i < count; i++) {
                arr[scanner.nextInt()]=true;
            }
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]){
                    System.out.println(i);
                }
            }
        }
    }
}
