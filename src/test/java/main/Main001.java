package main;

import java.util.Scanner;

public class Main001 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strings = str.split(" ");
        if (strings.length>0){
            System.out.println(strings[strings.length-1].length());
        }
    }
}
