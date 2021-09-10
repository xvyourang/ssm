package cn.xyr.ssm;

import java.util.Scanner;

public class Test06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line0 = scanner.nextLine();
        String[] strings0 = line0.split("\\s");
        int m = Integer.parseInt(strings0[0]);
        int t = Integer.parseInt(strings0[1]);
        int p = Integer.parseInt(strings0[2]);
        String line1 = scanner.nextLine();
        String[] strings1 = line1.split("\\s");
        int[] s = new int[strings1.length];
        for (int i = 0; i < strings1.length; i++) {
            s[i] = Integer.parseInt(strings1[i]);
        }
        int max = Integer.MIN_VALUE;
        int start = 0;
        boolean[] use = new boolean[s.length];
        use[0] = s[0] <= 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i] <= 0) {
                use[i] = true;
            } else if (s[i] < s[i - 1]) {
                use[i] = true;
            } else if (s[i] - s[i - 1] >= 10) {
                use[i] = true;
            }
        }
        for (int i = 0; i < use.length; i++) {
            if (!use[i]) {
                start = i;
                break;
            }
        }
        // 计算故障
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            count = 0;
            for (int j = i; j < i + m; j++) {
                if (j >= s.length) {
                    continue;
                }
                if (use[j]) {
                    count++;
                }
                // 判断是否舍弃，如果舍弃计算最大长度
                if (count >= t) {
                    // 舍弃
                    max = Math.max(j - start, max);
                    // 计算故障恢复
                    int hf = 0;
                    for (int k = i + j + 1; k < s.length; k++) {
                        if (!use[k]) {
                            hf++;
                        }
                        if (hf >= p) {
                            // 故障恢复
                            start = k;
                            break;
                        }
                    }
                }
            }
        }
        max = Math.max(s.length - start, max);
        System.out.println(max);
    }
}
