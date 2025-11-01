package cn.xyr.mhxy.lianyao;


import ch.qos.logback.classic.Level;
import cn.xyr.mhxy.lianyao.pet.Pet;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 胚子炼妖
 */
@Slf4j
public class BaseLianYaoCount {

    /**
     * 胚子池
     */
    private static Map<Integer, Long> map = Collections.unmodifiableMap(new HashMap<Integer, Long>() {{
        // 合8技能 极限是7+3+10=20?
        put(1, 20L);
        put(2, 20L);
        put(3, 20L);
        put(4, 20L);
        put(5, 100L);
        put(6, 200L);
        put(7, 500L);
        put(8, 2000L);
        for (int i = 1; i <= 30; i++) {
            put(8 + i, (long) Math.pow(2, i) * 2000);
        }
    }});

    /**
     * 炼妖技能在的概率
     */
    private static final BigDecimal P = BigDecimal.valueOf(0.35);
    /**
     * 出主/副宠概率
     */
    private static final BigDecimal P2 = BigDecimal.valueOf(0.425);


    /**
     * 排列组合可能数缓存
     */
    private static final int[][] COMBINATION_CACHE = new int[50][50];
    /**
     * 概率缓存
     */
    private static final BigDecimal[][] P_CACHE = new BigDecimal[50][50];
    /**
     * 低于这个概率就不计算收益了
     */
    private static BigDecimal minP = BigDecimal.valueOf(0);
    private static String format = "";

    @Test
    public void test00() {
        Config.setLevel(Level.DEBUG);
        init();
        // 纯计算
        solution(6, 1, true, false);
    }

    @Test
    public void test01() {
        Config.setLevel(Level.ERROR);
        init();
        ArrayList<String> resultList = new ArrayList<>();
        // 胚子技能
        for (int j = 3; j < 12; j++) {
            // 主宠比副宠多几个技能
            for (int k = 0; k < 6; k++) {
                long min = Long.MAX_VALUE;
                String minStr = "";
                // 主宠垫书
                for (int l = 0; l < 2; l++) {
                    // 副宠垫书
                    for (int m = 0; m < 2; m++) {
                        if (l == 1 && m == 0) {
                            continue;
                        }
                        long solution = solution(j, k, l == 0, m == 0);
                        if (min > solution) {
                            min = solution;
                            minStr = format;
                        }
                    }
                }
                log.error("******************");
                resultList.add(minStr);
            }
        }
        resultList.forEach(log::error);
    }

    private static long getCount(int skill, int addSkill) {
        int tmp = skill * 2 + addSkill;
        BigDecimal p = BigDecimal.valueOf(100);
        BigDecimal b = BigDecimal.valueOf(0.35);
        for (int i = 0; i < tmp; i++) {
            p = p.divide(b, 20, 4);
        }
        // 6001349612561
        long l = p.longValue();
        log.debug("{}+{}最多{}技能,保证最低概率出100次需要{}个宝宝", skill, skill + addSkill, tmp, l);
        return l;
    }


    /**
     * @param skill    主宠胚子技能数
     * @param addSkill 副宠比主宠多几个技能
     * @param mainPave 主宠垫书？
     * @param fuPave   副宠垫书？
     */
    private static long solution(int skill, int addSkill, boolean mainPave, boolean fuPave) {
        // 主宠
        BigDecimal result = BigDecimal.valueOf(map.get(skill));
        log.info("主宠{}技能,价格{}", skill, result);
        // 副宠
        int fuSkill = skill + addSkill;
        Long fuBasePrice = map.get(fuSkill);
        result = add(result, fuBasePrice);
        log.info("副宠{}技能价格{}", fuSkill, fuBasePrice);
        int extSkill = skill + fuSkill;
        // 主宠垫书？
        if (mainPave) {
            BigDecimal fuPavePrice = Pet.pavePrice(skill, 3).multiply(BigDecimal.valueOf(Config.bookPrice));
            log.info("主宠垫书{}", fuPavePrice);
            result = result.add(fuPavePrice);
        }
        // 副宠垫书？
        if (fuPave) {
            BigDecimal fuPavePrice = Pet.pavePrice(fuSkill, 3).multiply(BigDecimal.valueOf(Config.bookPrice));
            log.info("副宠垫书{}", fuPavePrice);
            result = result.add(fuPavePrice);
        }
        log.info("总成本{}", result);
        // 主宠收益
        BigDecimal price = getPrice(extSkill, mainPave, true);
        log.info("主宠收益{}", price);
        // 副宠收益
        BigDecimal fuPrice = getPrice(extSkill, fuPave, false);
        price = price.add(fuPrice);
        log.info("副宠收益{}", fuPrice);
        result = result.subtract(P2.multiply(price));
        long count = getCount(skill, addSkill);
        format = String.format("炼妖,胚子%s技能,副宠比主宠多%s技能，主宠%s垫书,副宠%s垫书,综合亏损%s,推荐炼妖次数%s",
                skill, addSkill, mainPave ? "" : "不", fuPave ? "" : "不", result.longValue(), count);
        log.error(format);
        return result.longValue();
    }

    private static BigDecimal add(BigDecimal b, long l) {
        return b.add(BigDecimal.valueOf(l));
    }

    /**
     * 计算天赋base个、额外技能extSkill个的收益
     *
     * @param extSkill 额外技能
     * @param pave     是否垫掉天赋
     * @param main     主/副
     */
    private static BigDecimal getPrice(int extSkill, boolean pave, boolean main) {
        // 天赋技能
        int base = 3;
        // 主3 副5算钱
        BigDecimal res = BigDecimal.ZERO;
        if (!pave) {
            extSkill = extSkill - base;
        }
        for (int i = 2; i <= extSkill; i++) {
            BigDecimal p = P_CACHE[extSkill][i];
            // 忽略概率小于minP的
            if (p.compareTo(minP) < 1) {
                continue;
            }
            Long integer = map.get(base + i);
            BigDecimal price = p.multiply(BigDecimal.valueOf(map.get(base + i)));
            log.debug("{}概率炼妖出{}技能{}价值{},平均值{}", P_CACHE[extSkill][i], base + i, main ? "主宠" : "副宠", integer, price);
            res = res.add(price);
        }
        return res;
    }


    private static void init() {
        // 计算1-30技能的概率
        for (int i = 1; i < 35; i++) {
            count(i);
        }
//        for (int i = 0; i < P_CACHE.length; i++) {
//            for (int j = 0; j <= i; j++) {
//                log.error("总{}技能出{}技能的概率{}", i, j, P_CACHE[i][j]);
//            }
//        }
    }

    /**
     * 计算n技能的概率并缓存
     */
    private static void count(int n) {
        for (int i = n; i >= 0; i--) {
            P_CACHE[n][i] = count(n, i);
        }
    }

    /**
     * 计算总共n技能出i技能的概率
     */
    private static BigDecimal count(int n, int i) {
        return P.pow(i).multiply(BigDecimal.ONE.subtract(P).pow(n - i)).multiply(BigDecimal.valueOf(combination(n, i)));
    }


    /**
     * 排列组合计算
     */
    private static int combination(int n, int i) {
        if (i == 0 || i == n) {
            return 1;
        }
        if (COMBINATION_CACHE[n][i] != 0) {
            return COMBINATION_CACHE[n][i];
        }
        COMBINATION_CACHE[n][i] = combination(n - 1, i) + combination(n - 1, i - 1);
        return COMBINATION_CACHE[n][i];
    }
}
