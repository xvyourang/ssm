package cn.xyr.mhxy;

import java.math.BigDecimal;

/**
 * 炼妖
 * 垫书永远成本最低
 *
 * 这个算不了 因为5+5出6/7/8/9/10 成本不能直接除以概率 要考虑出更多技能对成本的降低
 */
public class LianYao {
    /**
     * 排列组合可能数缓存
     */
    private static final int[][] COMBINATION_CACHE = new int[50][50];
    /**
     * 概率缓存
     */
    private static final BigDecimal[][] P_CACHE = new BigDecimal[50][50];
    /**
     * 炼妖每个技能保留的概率
     */
    private static final BigDecimal P = BigDecimal.valueOf(0.35);
    /**
     * 5技能3必带胚子150W
     */
    private static final BigDecimal BASE_PEIZHI = BigDecimal.valueOf(150);
    /**
     * 多技能宝宝最低价格
     */
    private static final BigDecimal[] price = new BigDecimal[50];
    /**
     * 垫书兽决价格
     */
    private static final BigDecimal BASE_BOOK = BigDecimal.valueOf(70);

    public static void main(String[] args) {
        init();
        for (int i = 1; i < 30; i++) {
            for (int j = 0; j <=i ; j++) {
                System.out.println(i+"技能炼妖,出"+j+"技能以上的概率是"+P_CACHE[i][j]);
            }
        }
        // 70/0.6+70/0.4+70/0.2=641.666667
//        System.out.println(dianShu(5, 3));
        // 先计算概率
        // 计算10技能最低成本
        price[2] = BASE_PEIZHI;
        // 5/3
//
//
//        // 最低6技能
//        for (int i = 3; i < 25; i++) {
//            // 最少2技能
//            for (int j = 2; j < i-1; j++) {
//                // 胚子2的技能数
//                int b = Math.max(i - j, 2);
//                // 2个胚子成本
//                BigDecimal cb = price[j].add(dianShu(j,3)).add(price[b]).add(dianShu(b,3));
//                // 总技能数
//                int n = j + b+6;
//                for (int k = j + 1; k <= n; k++) {
//                    // 计算成本
//                    if (P_CACHE[n][k]==null){
//                        System.out.println(11);
//                    }
//                    BigDecimal hf = cb.divide(P_CACHE[n][k], 10, BigDecimal.ROUND_HALF_UP);
//                    if (price[k] != null) {
//                        price[k] = hf.min(price[k]);
//                    } else {
//                        price[k] = hf;
//                    }
//                }
//            }
//        }
//        for (int i = 5; i < 30; i++) {
//            System.out.println(i + "技能最低成本" + price[i]);
//        }
    }

    private static void init() {
        // 计算1-30技能的概率
        for (int i = 1; i < 35; i++) {
            count(i);
        }
    }

    /**
     * 计算n技能的概率并缓存
     */
    private static void count(int n) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = n; i >= 0; i--) {
            BigDecimal count = count(n, i);
            total = total.add(count);
            P_CACHE[n][i] = total;
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

    /**
     * 计算垫掉必带技能成本
     *
     * @param n 总技能数
     * @param i 必带技能数
     */
    private static BigDecimal dianShu(int n, int i) {
        BigDecimal res = BigDecimal.ZERO;
        for (int j = i; j > 0; j--) {
            res = res.add(BigDecimal.valueOf(n).divide(BigDecimal.valueOf(j), 10, BigDecimal.ROUND_HALF_UP));
        }
        // 5/3+5/2+5
        return BASE_BOOK.multiply(res);
    }
}
