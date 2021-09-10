package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 * 随机文件名生成器
 */
public class Main006 {
    private static final char[] base = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '+', '-', '(', ')', '.', '_', '@', '#', '%', '~', '&'};
    private static Random random = new Random();

    public static void main(String[] args) throws Exception {
        BufferedWriter br = new BufferedWriter(new FileWriter("G:\\360data\\重要数据\\桌面\\pwds.csv"));
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(get(random.nextInt(16) + 16)).append(',');
            sb.append(get(random.nextInt(16) + 16));
            sb.append(System.lineSeparator());
            br.write(sb.toString());
        }
        br.flush();
        br.close();
        // 文件名16-32位
        // 密码16-32位
    }

    /**
     * 获取len长度的随机密码
     */
    public static String get(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int num = random.nextInt(base.length);
            sb.append(base[num]);
        }
        return sb.toString();
    }
}
