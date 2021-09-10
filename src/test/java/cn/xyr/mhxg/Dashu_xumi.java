package cn.xyr.mhxg;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 梦幻西游打书模拟
 * 添加n个技能,随机移除一个技能，添加一个技能，
 * 当添加技能列表都有时成功
 *
 * @author XYR
 * @since 2021年3月19日 00:35:48
 */
public class Dashu_xumi {
    public static void main(String[] args) throws Exception {
        Set<String> set = new HashSet<>();
        set.add(GJMZX);
        set.add(GJFSBJ);
        set.add(GJFSLJ);
//        set.add(GJFSBD);
        write(new Date() + System.lineSeparator());
//        dashu(6, 100000, set);
        int min = set.size() + 1;
        for (int i = min; i < 11; i++) {
            dashu(i, 100000, set);
        }

        System.out.println("******模拟完成******");
    }

    public static Random random = new Random();
    public static final String GJFZ = "高级反震";
    public static final String GJBS = "高级必杀";
    public static final String GJFSBJ = "高级法术暴击";
    public static final String GJFSLJ = "高级法术连击";
    public static final String GJMZX = "高级魔之心";
    public static final String GJFSBD = "高级反法术波动";
    public static final String GJYZ = "高级夜战";
    public static final String GJXY = "高级幸运";
    public static final String GJSYFS = "高级神佑复生";
    public static final String GJLJ = "高级连击";
    public static final String GJXX = "高级吸血";
    public static final String GJTX = "高级偷袭";
    public static final String GJMJ = "高级敏捷";
    public static final String GJGZ = "高级感知";
    public static final String GJQG = "高级驱鬼";
    public static final String XMZY = "须弥真言";
    static Map<String, Integer> book = new HashMap<>();

    static {
        book.put(GJFZ, 700);
        book.put(GJBS, 3000);
        book.put(GJFSBJ, 3000);
        book.put(GJFSLJ, 500);
        book.put(GJMZX, 3000);
        book.put(GJFSBD, 4000);
        book.put(GJYZ, 1500);
        book.put(GJXY, 500);
        book.put(GJSYFS, 2000);
        book.put(GJLJ, 3000);
        book.put(GJXX, 3000);
        book.put(GJTX, 3000);
        book.put(GJMJ, 500);
        book.put(GJGZ, 500);
        book.put(GJQG, 500);
        book.put("4技能", 3000);
        book.put("5技能", 6000);
        book.put("6技能", 8000);
        book.put("7技能", 10000);
        book.put("8技能", 13000);
        book.put("9技能", 18000);
        book.put("10技能", 22000);
    }

    /**
     * 打书
     *
     * @param n        胚子技能数
     * @param count    执行次数
     * @param aimSkill 目标技能
     */
    public static void dashu(int n, int count, Set<String> aimSkill) {
        Set<String> set = new HashSet<>();
        for (int i = 2; i < n; i++) {
            set.add(i + "");
        }
        set.add(XMZY);
        set.add(GJMZX);
        long total = 0;
        List<String> list = new ArrayList<>(aimSkill);
        list.sort(Comparator.comparing(s -> book.get(s)));
        int danjia = book.get(n + "技能");
        for (int i = 0; i < count; i++) {
            total += dashu(danjia, set, list);
        }
        write("胚子单价：" + danjia + ",技能数:" + n + "，实验次数:" + count + ",平均花费：" + (total / count) + System.lineSeparator());
    }

    /**
     * 打书
     *
     * @param danjia   胚子单价
     * @param skillSet 当前技能列表
     * @param aimSkill 目标技能列表,优先队列
     * @return 成品花费
     */
    public static int dashu(int danjia, Set<String> skillSet, List<String> aimSkill) {
        int result = danjia;
        Set<String> set = new HashSet<>(skillSet);
        one:
        for (; ; ) {
            if (!set.contains(XMZY)) {
                result += danjia;
                set = new HashSet<>(skillSet);
            } else {
                tow:
                for (String skill : aimSkill) {
                    if (!set.contains(skill)) {
                        result += book.get(skill);
                        hit(set, skill);
                        continue one;
                    }
                }
                break;
            }
        }
        return result;
    }

    /**
     * 打书
     *
     * @param skillSet 技能列表
     * @param skill    技能
     */
    private static void hit(Set<String> skillSet, String skill) {
        List<String> list = new ArrayList<>(skillSet);
        String s = list.get(random.nextInt(list.size()));
        skillSet.remove(s);
        skillSet.add(skill);
        //write("技能列表" + skillSet + "," + skill + "顶掉" + s+System.lineSeparator());
    }

    private static void write(String str) {
        OutputStream os;
        try {
            os = new BufferedOutputStream(new FileOutputStream("mhds.txt", true));
            byte[] b = str.getBytes();
            os.write(b);
            os.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }

    }
}
