import java.util.Scanner;

/**
 * 整数拆分为连续的整数之和
 * 打印所有可能拆分数少的排前面
 * 输入：9
 * 输出
 * 9=9
 * 9=4+5
 * 9=2+3+4
 * Result:3
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // t=(2*a1+n-1)*n/2
        int t = scanner.nextInt();
        int result = 0;
        for (int i = t; i > 0; i--) {
            // i是a1
            for (int j = 1; j <= t; j++) {
                // j是n
                int res = (2 * i + j - 1) * j / 2;
                if (res == t) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(t).append("=");
                    // j=1只有一个数
                    for (int k = 0; k < j; k++) {
                        sb.append(i + k).append("+");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    System.out.println(sb);
                    result++;
                }
            }
        }
        System.out.println("Result:" + result);
    }
}
