package cn.xyr.mhxg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 垫书模拟
 */
public class PaveBook {
    private static final BigDecimal BASE_BOOK_PRICE = BigDecimal.valueOf(70);
    private final static Random random = new Random();

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("飞行");
        list.add("弱电土");
        list.add("雷击");
        list.add("高级雷属性吸收");
        list.add("奔雷咒");
        List<String> base = new ArrayList<>();
        base.add("飞行");
        base.add("弱电土");
//        base.add("雷击");
        System.out.println(renZhen(list, base, "雷击", 45));
        List<String> base2 = new ArrayList<>(base);
        base2.add("雷击");
        System.out.println(zhiDian(list, base2));
        // 70/0.6+70/0.4+70/0.2=641.666667
        // 70/0.4+70/0.2=525
//        System.out.println(list.containsAll(base));
    }

    private static BigDecimal renZhen(List<String> list, List<String> base, String magicSkill, int level) {
        BigDecimal total = BigDecimal.ZERO;
        int size = 10000000;
        for (int i = 0; i < size; i++) {
            List<String> tmp = new ArrayList<>(list);
            BigDecimal bigDecimal = paveBook(tmp, base, magicSkill, level);
            total = total.add(bigDecimal);
        }
        return total.divide(BigDecimal.valueOf(size), 10, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 直接垫书的成本
     */
    private static BigDecimal zhiDian(List<String> list, List<String> base) {
        BigDecimal total = BigDecimal.ZERO;
        int size = 10000000;
        for (int i = 0; i < size; i++) {
            List<String> tmp = new ArrayList<>(list);
            total = total.add(paveBook(tmp, base));
        }
        return total.divide(BigDecimal.valueOf(size), 10, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal paveBook(List<String> list, List<String> base) {
        BigDecimal res = BigDecimal.ZERO;
        int count = 0;
        while (true) {
            int rand = random.nextInt(list.size());
            list.remove(rand);
            list.add(rand, count++ + "");
            res = res.add(BASE_BOOK_PRICE);
            ArrayList<String> tmp = new ArrayList<>(base);
            tmp.removeAll(list);
            if (tmp.size() == base.size()) {
                break;
            }
        }
        return res;
    }

    /**
     * 可以认证
     */
    public static BigDecimal paveBook(List<String> list, List<String> base, String magicSkill, int level) {
        BigDecimal res = BigDecimal.ZERO;
        int count = 0;
        while (true) {
            int rand = random.nextInt(list.size());
            list.remove(rand);
            list.add(rand, count++ + "");
            res = res.add(BASE_BOOK_PRICE);
            ArrayList<String> tmp = new ArrayList<>(base);
            tmp.removeAll(list);
            if (tmp.size() == base.size()) {
                break;
            }
        }
        if (list.contains(magicSkill)) {
            res = res.add(BigDecimal.valueOf(level * level * 160 + level * 4000).divide(BigDecimal.valueOf(10000), 10, BigDecimal.ROUND_HALF_UP));
        }
        return res;
    }
}
