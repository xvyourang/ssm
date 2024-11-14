package cn.xyr.mhxg;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 梦幻西游打书模拟
 * 添加n个技能,随机移除一个技能，添加一个技能，
 * 当添加技能列表都有时成功
 *
 * @author XYR
 * @since 2021年3月19日 00:35:48
 */
public class HitAttackBook {
    public static void main(String[] args) throws Exception {
        Set<MagicBooK> set = new HashSet<>();
        set.add(MagicBooK.GJLJ);
        set.add(MagicBooK.GJBS);
        set.add(MagicBooK.GJTX);
        set.add(MagicBooK.GJYZ);
        set.add(MagicBooK.GJQG);
        set.add(MagicBooK.GJMJ);
        set.add(MagicBooK.GJQL);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        write(sdf.format(new Date()) + System.lineSeparator());
        dashu(9, 100000, set);
//        int min = set.size();
//        for (int i = min; i < 13; i++) {
//            dashu(i, 100000, set);
//        }

        System.out.println("******模拟完成******");
    }

    public static Random random = new Random();

    /**
     * 打书
     *
     * @param n        胚子技能数
     * @param count    执行次数
     * @param aimSkill 目标技能
     */
    public static void dashu(int n, int count, Set<MagicBooK> aimSkill) {
        Set<MagicBooK> set = new HashSet<>();
        ArrayList<MagicBooK> otherBooks = MagicBooK.getOtherBooks(aimSkill);
        while (set.size()<n){
            set.add(otherBooks.get(random.nextInt(otherBooks.size())));
        }
        long total = 0;
        List<MagicBooK> list = new ArrayList<>(aimSkill);
        list.sort(Comparator.comparing(MagicBooK::getPrice));
        MagicBooK base = MagicBooK.CBG.get(n + "技能机关鸟");
        for (int i = 0; i < count; i++) {
            total += dashu(base, set, list);
        }
        write("胚子单价：" + base.getPrice() + ",技能数:" + n + "，实验次数:" + count + ",平均花费：" + (total / count) + System.lineSeparator());
    }

    /**
     * 打书
     *
     * @param base   胚子
     * @param skillSet 当前技能列表
     * @param aimSkill 目标技能列表,优先队列
     * @return 成品花费
     */
    public static int dashu(MagicBooK base, Set<MagicBooK> skillSet, List<MagicBooK> aimSkill) {
        int result = base.getPrice();
        Set<MagicBooK> set = new HashSet<>(skillSet);
        one:
        for (; ; ) {
            for (MagicBooK skill : aimSkill) {
                if (!set.contains(skill)) {
                    result += skill.getPrice();
                    hit(set, skill);
                    continue one;
                }
            }
            break;
        }
        return result;
    }

    /**
     * 打书
     *
     * @param skillSet 技能列表
     * @param skill    技能
     */
    private static void hit(Set<MagicBooK> skillSet, MagicBooK skill) {
        List<MagicBooK> list = new ArrayList<>(skillSet);
        MagicBooK s = list.get(random.nextInt(list.size()));
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
