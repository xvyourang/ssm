import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 仙剑4锻造时间计算
 */
public class XJ4 {

    public static void main0(String[] args) throws InterruptedException {
        while (true) {
            long now = System.currentTimeMillis();
            // 计算下一个时间点
            long r2 = (now / 30500 + 1) * 30500 + 19500;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            System.out.println(sdf.format(new Date(r2)));
            Thread.sleep(30500);
        }
    }

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("cmd /c date 2010-10-20");
    }

}
