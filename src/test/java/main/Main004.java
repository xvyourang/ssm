package main;

import java.util.Objects;
import java.util.Scanner;

public class Main004 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            if (str != null && !Objects.equals("", str)) {
                char[] cs = str.toCharArray();
                int count = 0;
                char[] result = new char[8];
                for (char c : cs) {
                    result[count++] = c;
                    if (count == 8) {
                        System.out.println(new String(result));
                        count = 0;
                        result = new char[8];
                    }
                }
                if (count != 0) {
                    for (int j = count; j < 8; j++) {
                        result[count++] = '0';
                    }
                    System.out.println(new String(result));
                }
            }
        }
    }
}
