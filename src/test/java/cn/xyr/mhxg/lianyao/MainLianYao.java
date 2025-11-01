package cn.xyr.mhxg.lianyao;


import ch.qos.logback.classic.Level;
import cn.xyr.mhxg.MagicBooK;
import cn.xyr.mhxg.lianyao.pet.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 胚子炼妖
 */
@Slf4j
public class MainLianYao {
    private static List<MagicBooK> booKList = new ArrayList<MagicBooK>() {{
        // 天赋技能
        add(MagicBooK.FSBJ);
        add(MagicBooK.RDT);
        add(MagicBooK.SG);
//        add(MagicBooK.FX);
        add(MagicBooK.LJI);
        add(MagicBooK.GJYZ);
        add(MagicBooK.RDS);
        // 额外技能
        add(MagicBooK.GJLSXXS);
        add(MagicBooK.BLZ);
        add(MagicBooK.XX);
        add(MagicBooK.GJQG);
        // 垫书技能
        add(MagicBooK.FZ);
        add(MagicBooK.XX);
        add(MagicBooK.LJ);
        add(MagicBooK.FX);
        add(MagicBooK.FY);
        add(MagicBooK.GZ);
        add(MagicBooK.ZS);
        add(MagicBooK.HG);
        add(MagicBooK.BS);
        add(MagicBooK.XY);
        add(MagicBooK.ZJ);
        add(MagicBooK.TX);
        add(MagicBooK.HZ);
        add(MagicBooK.QG);
        add(MagicBooK.LY);
        add(MagicBooK.LH);
        add(MagicBooK.LJI);
        add(MagicBooK.MZX);
        add(MagicBooK.DU);
        add(MagicBooK.FSBD);
        add(MagicBooK.FSDK);
        add(MagicBooK.FSLJ);
        add(MagicBooK.JSJZ);
    }};
    /**
     * 胚子池
     */
    private static Map<Integer, Integer> map = Collections.unmodifiableMap(new HashMap<Integer, Integer>() {{
        // 合8技能 极限是7+3+10=20?
        put(4, 20);
        put(5, 150);
        put(6, 350);
        put(7, 1000);
        put(8, 2500);
        put(9, 5500);
        put(10, 12000);
        put(11, 30000);
        put(12, 55000);
        put(13, 100000);
        put(14, 200000);
        put(15, 400000);
        put(16, 800000);
        put(17, 1600000);
        put(18, 3200000);
        put(19, 6400000);
        put(20, 12800000);
    }});
    /**
     * 优先
     */
    private static PriorityQueue<Pet> queue = new PriorityQueue<>((p1, p2) -> p2.getSkill().size() - p1.getSkill().size());
    /**
     * 用于统计消耗机关鸟数量
     */
    private static double jgn = 0;
    /**
     * 炼妖次数
     */
    private static double compositing = 0;
    /**
     * 用于统计消耗的副宠数量
     */
    private static Map<Integer, Double> baseCountMap = new HashMap<>();

    private static ArrayList<String> resultList = new ArrayList<>();

    private static String format = null;

    private static String mainPetName = "机关鸟";

    @Test
    public void test00() {
        Config.setLevel(Level.INFO);
//        Config.setLevel(Level.INFO);
//        Config.setLevel(Level.ERROR);
        Config.addSkill = 2;
        solution(1, 7, 2, 6, 9);


    }

    @Test
    public void test04() {
        Config.setLevel(Level.ERROR);
        Config.addSkill = 3;
        solution(10000, 7, 3, 3, 6);
        solution(10000, 7, 3, 3, 7);
        solution(10000, 7, 3, 3, 8);
    }

    @Test
    public void test03() {
        Config.setLevel(Level.ERROR);
        Config.addSkill = 4;
        for (int i = 5; i < 12; i++) {
            solution(100000, 8, 2, 2, i);
        }
    }

    @Test
    public void test02() {
        Config.setLevel(Level.ERROR);
        int count = 100000;
        solution(count, 3, 3, 3, 5);
        solution(count, 4, 3, 3, 6);
        solution(count, 5, 3, 3, 7);
        solution(count, 6, 3, 3, 6);
        solution(count, 7, 3, 3, 7);
        solution(count, 8, 3, 3, 6);
    }

    @Test
    public void test01() {
        Config.setLevel(Level.ERROR);
        Config.paveCommonSkill = true;
        Config.addSkill = 4;
        // 目标技能数
        for (int i = 3; i <= 5; i++) {
            BigDecimal min = BigDecimal.valueOf(Integer.MAX_VALUE);
            String minStr = "";
            // 胚子最低技能数 洗技能太蠢 2-4就好
            one:
            for (int j = 1; j <= 4; j++) {
                // 主宠垫书技能数
                for (int k = j; k <= i; k++) {
                    // 副宠最低垫书技能数
                    for (int l = 5; l <= i + Config.addSkill; l++) {
                        BigDecimal solution = solution(i, j, k, l);
                        if (min.compareTo(solution) > 0) {
                            min = solution;
                            minStr = format;
                        }
                        if (i == j) {
                            break one;
                        }
                    }
                }
            }
            log.error("******************");
            resultList.add(minStr);
        }
        log.error("******************");
        resultList.forEach(log::error);
    }

    private static BigDecimal solution(int aimSkill, int minSkill, int minMainPaveSkill, int paveSkill) {
        int tmp = (aimSkill - 1) * 2 + Config.addSkill;
        BigDecimal p = BigDecimal.valueOf(100);
        BigDecimal b = BigDecimal.valueOf(0.35);
        for (int i = 0; i < tmp; i++) {
            p = p.divide(b, 20, 4);
        }
        long l = p.longValue();
        log.info("{}+{}最多{}技能,保证最低概率出100次需要{}个宝宝", aimSkill - 1, aimSkill - 1 + Config.addSkill, tmp, l);
        return solution(l, aimSkill, minSkill, minMainPaveSkill, paveSkill);
    }

    /**
     * 计算成本
     *
     * @param count            总获取召唤兽数
     * @param aimSkill         获取的召唤兽最低技能数
     * @param minSkill         胚子最低技能数
     * @param paveSkill        副宠最低垫书技能数
     * @param minMainPaveSkill 主宠最低垫书技能数
     */
    private static BigDecimal solution(long count, int aimSkill, int minSkill, int minMainPaveSkill, int paveSkill) {
        Config.minBaseSkill = minSkill;
        Config.minMainPaveSkill = minMainPaveSkill;
        Config.minPaveSkill = paveSkill;
        BigDecimal total = BigDecimal.ZERO;
        queue.clear();
        jgn = 0L;
        baseCountMap.clear();
        for (long i = 0; i < count; i++) {
            Pet pet = get(aimSkill);
            total = total.add(BigDecimal.valueOf(pet.price));
            int size = pet.getSkill().size();
            log.info("第{}只{}", i + 1, pet);
//            System.err.println(String.format("第%s只%s", i + 1, pet));
//            if (size > aimSkill) {
//                i += Math.pow(2, size - aimSkill) - 1;
//            }
        }
        BigDecimal divide = total.divide(BigDecimal.valueOf(count), 4, 4);
        format = String.format("总共炼妖%s只%s技能，胚子最低技能数%s,主宠最低垫书技能数%s,副宠最低垫书技能数%s,平均成本%s", count, aimSkill, minSkill, minMainPaveSkill, paveSkill, divide);
        log.error(format);
        StringBuffer sb = new StringBuffer();
        baseCountMap.forEach((k, v) -> sb.append(k).append("技能").append(v / count).append("只"));
        log.error("平均炼妖次数{},主宠消耗{}只,副宠消耗{}", compositing / count, jgn / count, sb);
//        log.error("剩余胚子{}", queue);
        return divide;
    }


    /**
     * 获取一个skill技能以上的机关鸟
     * 多技能炼妖顺序 日游神>泪妖>雷鸟人>大蝙蝠
     */
    private static Pet get(int skill) {
        Pet peek = queue.peek();
        // 技能数符合要求
        if (peek != null && peek.getSkill().size() >= skill) {
            return queue.poll();
        }
        if (skill <= Config.minBaseSkill) {
            jgn++;
            return new JiGuanNiao(Config.minBaseSkill);
        }
        return compositing(skill);
    }


    private static Set<MagicBooK> getExtSkill(Class<? extends Pet> clazz) {
        if (clazz == RiYouShen.class) {
            return RiYouShen.extSkill;
        }
        if (clazz == LeiYao.class) {
            return LeiYao.extSkill;
        }
        if (clazz == LeiNiaoRen.class) {
            return LeiNiaoRen.extSkill;
        }
        if (clazz == DaBianFu.class) {
            return DaBianFu.extSkill;
        }
        throw new RuntimeException("请配置");
    }

    /**
     * 获取指定类型胚子
     *
     * @param clazz 类型
     * @param skill 技能数
     */
    private static Pet get(Class<? extends Pet> clazz, int skill) {
        skill = Math.max(skill, Config.minBaseSkill);
        try {
            Constructor<? extends Pet> constructor = clazz.getConstructor(int.class);
            return constructor.newInstance(skill);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }

    /**
     * 炼妖
     */
    private static Pet compositing(Pet p1, Pet p2) {
        // 先升级看看会不会领悟
        p1.upLevel();
        p2.upLevel();
        // 垫书
        // 主宠
        if (p1.skill.size() >= Config.minMainPaveSkill) {
            Set<MagicBooK> set = new HashSet<>(p1.getBaseSkill());
            set.addAll(p2.skill);
            Set<MagicBooK> tmpSkill = new HashSet<>(p1.getBaseSkill());
            tmpSkill.addAll(p2.skill);
            Set<MagicBooK> filter = p1.skill.stream().filter(tmpSkill::contains).collect(Collectors.toSet());
            p1.pave(set, filter);
        }
        // 副宠
        if (p2.skill.size() >= Config.minPaveSkill) {
            p2.pave(p1);
        }
        if (Config.paveCommonSkill) {
            //  只垫冲突技能、不看天赋
            p1.paveCommonSkill(p2);
        }
        return p1.compositing(p2);
    }

    /**
     * 炼妖获取技能书大于skill的不含exist的召唤兽
     */
    private static Pet compositing(int skill) {
        // 技能数不够，炼妖 这里胚子技能数需要研究个最优算法
        int minSkill = (skill + 1) / 2;
        // 炼妖
        int price = 0;
        while (true) {
            Pet p1 = get(minSkill);
            p1.price += price;
            price = 0;
            if (p1.getSkill().size() >= skill) {
                return p1;
            }
            // 炼妖副宠技能数也需要研究个最优解
            int sk = p1.getSkill().size() + Config.addSkill;
            Pet p2 = gen(sk, map.get(sk));
            // 统计消耗的副宠
            Double aLong = baseCountMap.computeIfAbsent(sk, k -> 0d);
            baseCountMap.put(sk, aLong + 1);
            Pet pet = compositing(p1, p2);
            compositing++;
            int size = pet.getSkill().size();
            if (size == 0) {
                // 炼妖失败
                price = pet.price;
            } else {
                if (mainPetName.equals(pet.getTypeName())) {
                    // 主宠
                    if (pet.skill.size() >= skill) {
                        return pet;
                    } else if (pet.skill.size() <= Config.minBaseSkill) {
                        price = pet.price;
                    } else {
                        queue.add(pet);
                    }

                } else {
                    // 副宠
                    if (pet.skill.size() > 4) {
                        // 能卖钱/回炉
                        int resFcSkill = pet.skill.size();
                        price = pet.price - map.get(resFcSkill);
                        Double fc = baseCountMap.computeIfAbsent(resFcSkill, k -> 0d);
                        baseCountMap.put(resFcSkill, fc - 1);
                    } else {
                        price = pet.price;
                    }
                }
            }
        }
    }

    private static RiYouShen gen(int skill, int price) {
        if (skill <= 4) {
            return new RiYouShen(4);
        }
        RiYouShen res = new RiYouShen(-1);
        res.skill.addAll(RiYouShen.allSkill);
        res.skill.addAll(booKList.subList(0, skill - 5));
        res.price = price;
        res.original = false;
        log.debug("买{}", res);
        return res;
    }
}
