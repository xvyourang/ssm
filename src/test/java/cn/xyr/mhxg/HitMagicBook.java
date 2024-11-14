package cn.xyr.mhxg;

import org.junit.Test;

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
public class HitMagicBook {

    /**
     * 全红BY鬼将
     * @throws Exception
     */
    @Test
    public void qhgj() throws Exception{
        Set<String> aimSkill = new HashSet<>();
        aimSkill.add(MagicBooK.GJBS.getName());
        aimSkill.add(MagicBooK.GJLJ.getName());
        aimSkill.add(MagicBooK.YZ.getName());
        aimSkill.add(MagicBooK.GJTX.getName());
        Set<String> baseSkill = new HashSet<>();
//        baseSkill.add(MagicBooK.CQBY.getName());
        write("全红BY鬼将：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + System.lineSeparator());
        // 出奇 须弥 所以+2
        int min = aimSkill.size() + baseSkill.size();
//        for (int i = min; i < 12; i++) {
        Set<String> initSkill = new HashSet<>();
        for (int j = baseSkill.size(); j < 4; j++) {
            initSkill.add(j+"");
        }
        initSkill.addAll(baseSkill);
        HitBook(1000000, 2000, aimSkill, initSkill, baseSkill);
//        }

        System.out.println("******模拟完成******");
    }
    /**
     * 出奇须弥
     */
    @Test
    public void cqxm1() throws Exception {
        Set<String> aimSkill = new HashSet<>();
        aimSkill.add(MagicBooK.GJMZX.getName());
        aimSkill.add(MagicBooK.GJFSBJ.getName());
        aimSkill.add(MagicBooK.GJFSLJ.getName());
//        aimSkill.add(MagicBooK.GJFSBD.getName());
        Set<String> baseSkill = new HashSet<>();
        baseSkill.add(MagicBooK.XMZY.getName());
//        baseSkill.add(MagicBooK.CQBY.getName());
        write("须弥：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + System.lineSeparator());
        // 出奇 须弥 所以+2
        int min = aimSkill.size() + baseSkill.size();
//        for (int i = min; i < 12; i++) {
        Set<String> initSkill = new HashSet<>();
        for (int j = baseSkill.size(); j < 6; j++) {
            initSkill.add(j+"");
        }
        initSkill.add(MagicBooK.GJMZX.getName());
        initSkill.addAll(baseSkill);
        HitBook(1000000, 13000, aimSkill, initSkill, baseSkill);
//        }

        System.out.println("******模拟完成******");
    }

    /**
     * 8红连善
     */
    @Test
    public void attack() {
        Set<String> aimSkill = new HashSet<>();
        aimSkill.add(MagicBooK.GJLJ.getName());
        aimSkill.add(MagicBooK.GJBS.getName());
        aimSkill.add(MagicBooK.GJXX.getName());
        aimSkill.add(MagicBooK.GJTX.getName());
        aimSkill.add(MagicBooK.GJYZ.getName());
        aimSkill.add(MagicBooK.GJSYFS.getName());
        aimSkill.add(MagicBooK.GJFZ.getName());
        aimSkill.add(MagicBooK.GJXY.getName());
        Set<String> baseSkill = new HashSet<>();
        write("8红连善：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + System.lineSeparator());
        // 出奇 须弥 所以+2
        int min = aimSkill.size() + baseSkill.size();
        for (int i = min; i < 13; i++) {
            Set<String> initSkill = new HashSet<>();
            for (int j = baseSkill.size(); j < i; j++) {
                initSkill.add(j + "");
            }
            initSkill.addAll(baseSkill);
            HitBook(1000000, MagicBooK.CBG.get(i + "技能").getPrice(), aimSkill, initSkill, baseSkill);
        }

        System.out.println("******模拟完成******");
    }

    /**
     * 出奇须弥
     */
    @Test
    public void cqxm() throws Exception {
        Set<String> aimSkill = new HashSet<>();
        aimSkill.add(MagicBooK.GJMZX.getName());
        aimSkill.add(MagicBooK.GJFSBJ.getName());
        aimSkill.add(MagicBooK.GJFSLJ.getName());
        aimSkill.add(MagicBooK.GJFSBD.getName());
        Set<String> baseSkill = new HashSet<>();
        baseSkill.add(MagicBooK.XMZY.getName());
        baseSkill.add(MagicBooK.CQBY.getName());
        write("出奇须弥：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + System.lineSeparator());
        // 出奇 须弥 所以+2
        int min = aimSkill.size() + baseSkill.size();
        for (int i = min; i < 12; i++) {
            Set<String> initSkill = new HashSet<>();
            for (int j = baseSkill.size(); j < i; j++) {
                initSkill.add(j + "");
            }
            initSkill.addAll(baseSkill);
            HitBook(1000000, MagicBooK.CBG.get(i + "出奇须弥").getPrice(), aimSkill, initSkill, baseSkill);
        }

        System.out.println("******模拟完成******");
    }

    /**
     * 四法须弥
     */
    @Test
    public void sfxm() throws Exception {
        Set<String> aimSkill = new HashSet<>();
        aimSkill.add(MagicBooK.GJMZX.getName());
        aimSkill.add(MagicBooK.GJFSBJ.getName());
        aimSkill.add(MagicBooK.GJFSLJ.getName());
//        aimSkill.add(MagicBooK.GJFSBD.getName());
        Set<String> baseSkill = new HashSet<>();
        baseSkill.add(MagicBooK.XMZY.getName());
        write("四法须弥：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + System.lineSeparator());
        // 出奇 须弥 所以+2
        int min = aimSkill.size() + baseSkill.size();
        for (int i = min; i < 12; i++) {
            Set<String> initSkill = new HashSet<>();
            for (int j = baseSkill.size(); j < i; j++) {
                initSkill.add(j + "");
            }
            initSkill.addAll(baseSkill);
            HitBook(1000000, MagicBooK.CBG.get(i + "须弥").getPrice(), aimSkill, initSkill, baseSkill);
        }

        System.out.println("******模拟完成******");
    }

    public static Random random = new Random();


    /**
     * 打书
     *
     * @param count     执行次数
     * @param price     胚子单价
     * @param aimSkill  目标技能
     * @param initSkill 初始技能
     * @param baseSkill 特殊技能，打掉就失败
     */
    public static void HitBook(int count, int price, Set<String> aimSkill, Set<String> initSkill, Set<String> baseSkill) {
        int skillNum = initSkill.size();
        long total = 0;
        int failTotal = 0;
        List<String> list = new ArrayList<>(aimSkill);
        list.sort(Comparator.comparing(s -> MagicBooK.CBG.get(s).getPrice()));

        for (int i = 0; i < count; i++) {
            Map<String, Integer> result = HitBook(price, initSkill, list, baseSkill);
            total += result.get("total");
            failTotal += result.get("fail");
        }
        String outStr = "胚子单价：" + price + ",技能数:" + skillNum + "，打成功次数:" + (count / 10000) + "万次,成功率" + (count * 100L / (count + failTotal)) + "%，平均花费：" + (total / count) + "万mhb";
        System.out.println(outStr);
        write(outStr + System.lineSeparator());
    }

    /**
     * 打书
     *
     * @param price     胚子单价
     * @param initSkill 初始技能列表
     * @param aimSkill  目标技能列表,优先队列
     * @return 花费、是否失败
     */
    public static Map<String, Integer> HitBook(int price, Set<String> initSkill, List<String> aimSkill, Set<String> baseSkill) {
        Map<String, Integer> result = new HashMap<>();
        int total = price;
        int fail = 0;
        Set<String> nowSkill = new HashSet<>(initSkill);
        one:
        for (; ; ) {
            boolean key = false;
            for (String skill : baseSkill) {
                if (key = !nowSkill.contains(skill)) {
                    break;
                }
            }
            if (key) {
                fail++;
                // 打失败了 用新宠、技能重置
                // 有须弥还能换钱
                if (nowSkill.contains(MagicBooK.XMZY.getName())) {
                    total -= MagicBooK.CBG.get(initSkill.size() + "须弥").getPrice();
                }
                total += price;
                nowSkill = new HashSet<>(initSkill);
            } else {
                for (String skill : aimSkill) {
                    if (!nowSkill.contains(skill)) {
                        total += MagicBooK.CBG.get(skill).getPrice();
                        hit(nowSkill, skill);
                        continue one;
                    }
                }
                break;
            }
        }
        result.put("total", total);
        result.put("fail", fail);
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
            e.printStackTrace();
        }

    }
}
