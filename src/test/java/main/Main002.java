package main;

import java.util.Scanner;

public class Main002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toLowerCase();
        char aim = scanner.nextLine().toLowerCase().charAt(0);
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == aim) {
                count++;
            }
        }
        System.out.println(count);
    }
}
