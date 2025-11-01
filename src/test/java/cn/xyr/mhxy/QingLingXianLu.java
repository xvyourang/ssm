package cn.xyr.mhxy;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

@Slf4j
public class QingLingXianLu {
    private static Map<Integer, Integer[]> map = new HashMap<>();
    private static Map<Integer, Integer> totalMap = new HashMap<>();
    private static Map<Integer, Integer> priceMap = new HashMap<>();

    static {
        map.put(6, new Integer[]{34, 67, 100});
        map.put(7, new Integer[]{34, 67, 100});
        map.put(8, new Integer[]{34, 67, 100});
        priceMap.put(0, 51);
        priceMap.put(6, 55);
        priceMap.put(7, 450);
        priceMap.put(8, 850);

    }

    private static Random random = new Random();

    private static void setLogLevel(Level level) {
        ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger root = loggerContext.getLogger("root");
        root.setLevel(level);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int now = 50;
        int count = 0;
        int success = 0;
        while (scanner.hasNext()) {
            String next = scanner.next();
            int value = Integer.parseInt(next);
            if (value == 0) {
                eat(0);
                now = 50;
                count = 0;
            } else if (value == -1) {
                long total = 0;
                for (Map.Entry<Integer, Integer> entry : totalMap.entrySet()) {
                    total += Long.valueOf(priceMap.get(entry.getKey())) * entry.getValue();
                }
                log.info("实验次数{}次，平均花费{}万", count, total / count);
                final int tmp = success;
                totalMap.forEach((k, v) -> log.info("{}价格{},总消耗{}，平均消耗{}", k == 0 ? "洗了重来" : k, priceMap.get(k), v, Float.valueOf(v) / tmp));
            } else {
                if (count < 6) {
                    now += eat(value);
                    log.info("当期灵性值{}", now);
                    count++;
                } else {
                    now += eat(value);
                    log.info("当期灵性值{}", now);
                    if (now != 110) {
                        eat(0);
                    } else {
                        success++;
                    }
                    now = 50;
                    count = 0;
                }

            }
        }


    }

    @Test
    public void test00() {
        eat110(1, 1);
    }

    @Test
    public void test01() {
        setLogLevel(Level.WARN);
        setLogLevel(Level.ERROR);
        setLogLevel(Level.DEBUG);
        setLogLevel(Level.INFO);
        for (int i = 0; i < 4; i++) {
            eat110(i, 100000);
        }
    }

    private static void eat110(int sixCount, int count) {
        totalMap.clear();
        for (int i = 0; i < count; i++) {
            eat110(sixCount);
        }
        long total = 0;
        for (Map.Entry<Integer, Integer> entry : totalMap.entrySet()) {
            total += Long.valueOf(priceMap.get(entry.getKey())) * entry.getValue();
        }
        log.info("实验次数{}次，策略前{}都吃6爆8，平均花费{}万", count, sixCount, total / count);
        totalMap.forEach((k, v) -> log.info("{}价格{},总消耗{}，平均消耗{}", k == 0 ? "洗了重来" : k, priceMap.get(k), v, Float.valueOf(v) / count));
    }

    /**
     * @param sixCount 前几瓶吃6
     */
    private static void eat110(int sixCount) {
        int aim = 110;
        int now;
        do {
            now = 50;
            for (int i = 0; i < 6; i++) {
                // 前几瓶吃6爆8，没爆就洗了
                if (i < sixCount) {
                    int result = eat(6);
                    if (result == 8) {
                        now += result;
                    } else {
                        // 没达标 洗掉
                        eat(0);
                        now = 50;
                        i = -1;
                    }
                } else {
                    // 下一瓶吃几？算法:前5瓶最低值需要是8、第6瓶赌爆+2，80->8,81->7,82->6
                    int tmp = 50 + (i + 1) * 8 - now;
                    now += eat(tmp);
                }
            }
            if (now == 100) {
                now += eat(8);
            }
            log.debug("当期值{}", now);
            if (now != aim) {
                eat(0);
            }
        } while (now != aim);
    }

    /**
     * 吃清灵仙露
     */
    private static int eat(int value) {
        Integer integer = totalMap.computeIfAbsent(value, k -> 0);
        totalMap.put(value, integer + 1);
        if (value == 0) {
            log.debug("洗了重来");
            return 0;
        }
        int r = random.nextInt(100);
        Integer[] arr = map.get(value);
        for (int i = 0; i < arr.length; i++) {
            if (r < arr[i]) {
                int result = value + i;
                log.debug("吃{}爆{}", value, result);
                return result;
            }
        }
        throw new RuntimeException("异常情况");
    }
}
