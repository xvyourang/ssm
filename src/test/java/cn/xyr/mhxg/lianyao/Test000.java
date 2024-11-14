package cn.xyr.mhxg.lianyao;

import ch.qos.logback.classic.Level;
import cn.xyr.mhxg.MagicBooK;
import cn.xyr.mhxg.lianyao.pet.JiGuanNiao;
import cn.xyr.mhxg.lianyao.pet.LeiYao;
import cn.xyr.mhxg.lianyao.pet.Pet;
import cn.xyr.mhxg.lianyao.pet.RiYouShen;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

@Slf4j
public class Test000 {
    private static Map<Integer, Integer> map = new HashMap<>();

    @Test
    public void test01() {
        Config.setLevel(Level.INFO);
        for (int i = 0; i < 10; i++) {
            RiYouShen p1 = new RiYouShen(5);
            p1.skill.add(MagicBooK.RDT);
            JiGuanNiao p2 = new JiGuanNiao(3);
            p1.compositing(p2);
        }
    }

    @Test
    public void test02() {
        Config.setLevel(Level.INFO);
        for (int i = 0; i < 10; i++) {
            RiYouShen p1 = new RiYouShen(5);
            LeiYao p2 = new LeiYao(5);
            p1.compositing(p2);
        }
    }

    @Test
    public void test03() {
        double n = 3;
        double res = (n + 1) * n * n / ((n * n - 1) * (n * n - 1));

        System.out.println(res);

    }

    @Test
    public void test04() {
        int count = 1000000;
        int n = 6;
        int k = n;
        List<Integer> aim = new ArrayList<>();
        map.clear();
        for (int i = 1; i <= k; i++) {
            aim.add(i);
        }
        for (int i = 0; i < count; i++) {
            ttt(aim, n);
        }
        System.err.println("n=" + n + ",k=" + k);
        map.forEach((key, v) -> {
//            System.err.println("第" + key + "个技能需要" + (((double) v) / count) + "本兽决");
            System.err.println(key + "=" + (((double) v) / count));
        });
    }

    private static void ttt(List<Integer> aim, int skill) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < skill; i++) {
            list.add(-i);
        }
        while (true) {
            for (Integer i : aim) {
                if (list.contains(i)) {
                    continue;
                }
                int r = Pet.random.nextInt(list.size());
                Integer remove = list.remove(r);
                list.add(r, i);
//                System.out.println("打" + i + "覆盖" + remove);
                Integer value = map.computeIfAbsent(i, k -> 0);
                map.put(i, value + 1);
                break;
            }
            long count = list.stream().filter(aim::contains).count();
            if (count == aim.size()) {
                break;
            }
        }
    }

    @Test
    public void test05() {
        double n = 3;
        double k = 3;
        double a = (n - k + 1) / n;
        double b = (k - 1) / n;
        double c = 1 / (k - 1);
        double d = (k - 2) / (k - 1);
        log.info("n={},k={},a={},b={},c={},d={}", n, k, a, b, c, d);
        double total = 0;
        // 其他书循环到打成的概率
        double sl = a / (1 - b * d) ;
        double q = a + sl;
        // 多打一本k书的概率
        double p = b * b * c;
        double r = q / (1 - p) / (1 - p);
        log.info("sl={},q={},p={},r={}", sl, q, p, r);
        for (double j = 1; j < 10; j++) {
            double ki = j * q * Math.pow(p, j - 1);
            total += ki;
            log.info("i={},ki={}", j, ki);
        }
        log.info("{}个技能，打第{}技能时，消耗{}本", n, k, total);
    }
}
