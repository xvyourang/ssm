package cn.xyr.ssm;


import cn.xyr.ssm.common.utils.ScheduledExecutorManger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 素数对
 * 输入n，n>0
 * 输出n以内的素数对数量
 *
 * @author 77288
 * @time 2019/6/8 15:50
 */
public class Test01 {
    private static int conut = 0;

    public static void main(String[] args) throws Exception {
        one:
        for (int i = 0; ; i++) {
            if (i == 10) {
                break;
            }
            tow:
            for (int j = 0; ; j++) {
                if (j == 5) {
                    continue one;
                } else {
                    System.out.println(i+"->"+j);
                }
            }
        }
    }
}
