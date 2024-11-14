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
public class BaseLianYao {


    /**
     * 优先
     */
    private static PriorityQueue<Pet> queue = new PriorityQueue<>((p1, p2) -> p2.getSkill().size() - p1.getSkill().size());

    private static ArrayList<String> resultList = new ArrayList<>();
    private static String format = null;

    @Test
    public void test00() {
        Config.setLevel(Level.INFO);
//        Config.setLevel(Level.INFO);
//        Config.setLevel(Level.ERROR);
        for (int i = 0; i < 10; i++) {
            RiYouShen p1 = new RiYouShen(5);
            LeiYao p2 = new LeiYao(5);
            p1.compositing(p2);
        }
    }


    @Test
    public void test03() {
        Config.setLevel(Level.ERROR);
        int count = 100000;
        Config.paveCommonSkill = false;
        solution(count, 4, 4, 9);


    }

    @Test
    public void test02() {
        Config.setLevel(Level.ERROR);
        int count = 100000;
        Config.paveCommonSkill = false;
        solution(count, 5, 4, 9);
        solution(count, 6, 4, 11);
        solution(count, 7, 4, 13);
        solution(count, 8, 4, 12);
        solution(count, 8, 5, 12);

    }

    @Test
    public void test01() {
        Config.setLevel(Level.ERROR);
        Config.paveCommonSkill = false;
        int count = 100000;
        for (int i = 5; i < 9; i++) {
            BigDecimal min = BigDecimal.valueOf(Integer.MAX_VALUE);
            String minStr = "";
            for (int j = 4; j <= 5; j++) {
                for (int k = j * 2; k < i * 2; k++) {
                    BigDecimal solution = solution(count, i, j, k);
                    if (min.compareTo(solution) > 0) {
                        min = solution;
                        minStr = format;
                    }
                }
            }
            resultList.add(minStr);
        }
        resultList.forEach(System.out::println);
    }

    /**
     * 计算成本
     *
     * @param count     总获取召唤兽数
     * @param aimSkill  获取的召唤兽最低技能数
     * @param minSkill  胚子最低技能数
     * @param paveSkill 最低垫书技能数
     */
    private static BigDecimal solution(int count, int aimSkill, int minSkill, int paveSkill) {
        Config.minPaveSkill = paveSkill;
        Config.minBaseSkill = minSkill;
        BigDecimal total = BigDecimal.ZERO;
        queue.clear();
        for (int i = 0; i < count; i++) {
            Pet pet = get(aimSkill);
            total = total.add(BigDecimal.valueOf(pet.price));
            int size = pet.getSkill().size();
            log.debug("第{}只{}", i + 1, pet);
            if (size > aimSkill) {
                i += Math.pow(2.8, size - aimSkill) - 1;
            }
        }
        BigDecimal divide = total.divide(BigDecimal.valueOf(count));
        format = String.format("总共炼妖%s只%s技能，胚子最低技能数%s,最低垫书技能数%s,平均成本%s", count, aimSkill, minSkill, paveSkill, divide);
        log.error(format);
//        log.error("剩余胚子{}", queue);
        return divide;
    }


    /**
     * 获取一个skill技能以上的召唤兽
     * 多技能炼妖顺序 日游神>泪妖>雷鸟人>大蝙蝠
     */
    private static Pet get(int skill) {
        if (queue.isEmpty()) {
            return get(Collections.emptySet(), skill);
        }
        Pet peek = queue.peek();
        // 技能数符合要求
        if (peek.getSkill().size() >= skill) {
            return queue.poll();
        }
        return compositing(Collections.emptySet(), skill);
    }

    private static Pet get(Pet pet, int skill) {
        // 判断能不能获取到一个不用垫书的符合条件的胚子
        // 不行就垫掉一样的技能
        Set<MagicBooK> exist = new HashSet<>(pet.getBaseSkill());
        exist.addAll(pet.getSkill());
        return get(exist, skill);
    }

    /**
     * 获取一个技能数大于skill的副宠,尽可能少的包含exist的技能
     */
    private static Pet get(Set<MagicBooK> exist, int skill) {
        // 先从池子找
        List<Pet> collect = queue.stream().filter(pet -> pet.getSkill().stream().noneMatch(exist::contains)).collect(Collectors.toList());
        if (collect.size() > 0) {
            PriorityQueue<Pet> priorityQueue = new PriorityQueue<>((p1, p2) -> p2.getSkill().size() - p1.getSkill().size());
            priorityQueue.addAll(collect);
            Pet res = priorityQueue.poll();
            queue.remove(res);
            return res;
        }
        // 判断是否要炼妖
        if (skill > Config.minBaseSkill) {
            // todo:是否要考虑如果得到的还是有冲突就重新弄一个
            return compositing(exist, skill);
        }
        // 遍历胚子列表(优先队列) 获取一个垫书最少的胚子
        Class<? extends Pet> minClazz = Config.typeList.get(0);
        boolean direct = false;
        int minSkill = Integer.MAX_VALUE;
        for (Class<? extends Pet> petClass : Config.typeList) {
            Set<MagicBooK> baseSkill = getBaseSkill(petClass);
            Set<MagicBooK> baseCommonSkill = exist.stream().filter(baseSkill::contains).collect(Collectors.toSet());
            Set<MagicBooK> extSkill = getExtSkill(petClass);
            Set<MagicBooK> extCommonSkill = exist.stream().filter(extSkill::contains).collect(Collectors.toSet());
            // 判断这个类型的召唤兽能直接洗出符合条件的吗
            // 允许的冲突技能数
            int extSkillNum = extSkill.size() - (Config.minBaseSkill - baseSkill.size());
            // 天赋不冲突
            if (baseCommonSkill.size() == 0) {
                if (extCommonSkill.size() == 0) {
                    // 额外技能也不冲突
                    minClazz = petClass;
                    break;
                }
                if (extSkillNum >= extCommonSkill.size()) {
                    direct = true;
                    minClazz = petClass;
                    minSkill = extCommonSkill.size();
                }

            }
            // 天赋技能有冲突、找出冲突技能最少的类型
            int min = baseCommonSkill.size() + extCommonSkill.size();
            if (min < minSkill) {
                minSkill = min;
                minClazz = petClass;
                direct = false;
            }
        }
        log.trace("{}冲突技能最少{}个冲突技能,{}可以洗出不冲突的", minClazz, minSkill, direct);
        if (direct) {
            // 可以洗出不冲突的
            while (true) {
                Pet pet = get(minClazz, skill);
                Set<MagicBooK> filter = exist.stream().filter(pet.skill::contains).collect(Collectors.toSet());
                if (filter.isEmpty()) {
                    return pet;
                }
                queue.add(pet);
            }
        }
        return get(minClazz, skill);
    }


    private static Set<MagicBooK> getBaseSkill(Class<? extends Pet> clazz) {
        if (clazz == RiYouShen.class) {
            return RiYouShen.baseSkill;
        }
        if (clazz == LeiYao.class) {
            return LeiYao.baseSkill;
        }
        if (clazz == LeiNiaoRen.class) {
            return LeiNiaoRen.baseSkill;
        }
        if (clazz == DaBianFu.class) {
            return DaBianFu.baseSkill;
        }
        throw new RuntimeException("请配置");
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
        p1.upLevel();
        p2.upLevel();
        // 垫书
        int size = p1.getSkill().size() + p2.getSkill().size();
        if (size >= Config.minPaveSkill) {
            p1.pave(p2);
        } else if (Config.paveCommonSkill) {
            //  只垫冲突技能、不看天赋
            p1.paveCommonSkill(p2);
        }
        return p1.compositing(p2);
    }

    /**
     * 炼妖获取技能书大于skill的不含exist的召唤兽
     */
    private static Pet compositing(Set<MagicBooK> exist, int skill) {
        // 技能数不够，炼妖 这里胚子技能数需要研究个最优算法
        int minSkill = (skill + 1) / 2;
        // 炼妖
        int price = 0;
        while (true) {
            Pet p1 = get(exist, minSkill);
            p1.price += price;
            price = 0;
            if (p1.getSkill().size() >= skill) {
                return p1;
            }
            // 炼妖副宠技能数也需要研究个最优解
            Pet p2 = get(p1, p1.skill.size());
            if (p2.getSkill().size() >= skill) {
                queue.add(p1);
                return p2;
            }
            Pet pet = compositing(p1, p2);
            int size = pet.getSkill().size();
            if (size >= skill) {
                return pet;
            } else if (size == 0) {
                // 炼妖失败
                price = pet.price;
            } else if (size <= Config.minBaseSkill) {
                // 练出的技能书太少、重洗
                price = pet.price - pet.getBasePrice();
            } else {
                queue.add(pet);
            }
        }
    }
}
