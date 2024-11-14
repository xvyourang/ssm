package cn.xyr.mhxg;

import java.util.*;

/**
 * 魔兽要诀
 */
public enum MagicBooK {

    /// 低级技能
    FJ("反击", 80),
    FZ("反震", 80),
    XX("吸血", 80),
    LJ("连击", 80),
    FX("飞行", 80),
    FY("防御", 80),
    GZ("感知", 80),
    ZS("再生", 80),
    MS("冥思", 80),
    MJ("敏捷", 80),
    HG("慧根", 80),
    BS("必杀", 80),
    XY("幸运", 80),
    ZJ("招架", 80),
    TX("偷袭", 80),
    HZ("合纵", 80),
    QG("驱鬼", 80),
    LY("落岩", 80),
    LH("烈火", 80),
    LJI("雷击", 80),
    MZX("魔之心", 80),
    DU("毒", 80),
    FSBD("法术波动", 80),
    FSDK("法术抵抗", 80),
    FSLJ("法术连击", 80),
    JSJZ("精神集中", 80),
    CD("迟钝", 80),
    JJBS("进击必杀", 80),
    JJFB("进击法爆", 80),
    LSXXS("雷属性吸收", 80),
    TSSXS("土属性吸收", 80),
    HXSXS("火属性吸收", 80),
    SSXXS("水属性吸收", 80),
    FDXY("否定信仰", 80),


    DQ("盾气", 80),
    QL("强力", 80),
    SJ("神迹", 80),
    YZ("夜战", 80),
    FSBJ("法术暴击", 80),
    RDT("弱点土", 80),
    RDS("弱点水", 80),
    SG("水攻", 80),

    // 垃圾高级技能
    GJLSXXS("高级雷属性吸收", 80),
    BLZ("奔雷咒", 150),
    GJZS("高级再生", 80),

    // 高级技能
    GJFSLJ("高级法术连击", 150),
    GJXY("高级幸运", 200),
    GJFZ("高级反震", 400),
    GJFSBJ("高级法术暴击", 2000),
    GJFSBD("高级反法术波动", 2000),

    DYLH("地狱烈火", 150),
    SMJS("水漫金山", 150),
    TSYD("泰山压顶", 150),
    GJGZ("高级感知", 500),
    GJSYFS("高级神佑复生", 2000),
    GJXX("高级吸血", 2800),
    GJMJ("高级敏捷", 1900),
    GJQL("高级强力", 1500),
    GJLJ("高级连击", 4330),
    GJBS("高级必杀", 6000),
    GJTX("高级偷袭", 5100),
    GJYZ("高级夜战", 3300),
    GJQG("高级驱鬼", 900),
    GJMZX("高级魔之心", 3300),
    // 特殊技能
    XMZY("须弥真言", 999999),
    CQBY("出其不意", 999999),
    // 法宠能认证 搜索时要忽略鉴定的技能
    CQXM4("4出奇须弥", 8200),
    CQXM5("5出奇须弥", 8200),
    CQXM6("6出奇须弥", 10000),
    CQXM7("7出奇须弥", 14000),
    CQXM8("8出奇须弥", 20000),
    CQXM9("9出奇须弥", 26000),
    CQXM10("10出奇须弥", 36400),
    CQXM11("11出奇须弥", 52000),
    XM4("4须弥", 1500),
    XM5("5须弥", 2000),
    XM6("6须弥", 3000),
    XM7("7须弥", 4000),
    XM8("8须弥", 7800),
    XM9("9须弥", 12000),
    XM10("10须弥", 18000),
    XM11("11须弥", 35000),
    JGN7("7技能机关鸟", 6300),
    JGN8("8技能机关鸟", 15300),
    JGN9("9技能机关鸟", 36000),
    BB5("5技能", 125),
    BB6("6技能", 250),
    BB7("7技能", 750),
    BB8("8技能", 2000),
    BB9("9技能", 5000),
    BB10("10技能", 9000),
    BB11("11技能", 15000),
    BB12("12技能", 26000);
    private String name;
    private int price;

    MagicBooK(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public static HashMap<String, MagicBooK> CBG = new HashMap<>();
    /**
     * 垫书技能
     */
    public static HashSet<MagicBooK> normal = new HashSet<>();

    public void setPrice(int price) {
        this.price = price;
    }

    static {
        for (MagicBooK magicBooK : values()) {
            String magicBooKName = magicBooK.getName();
            CBG.put(magicBooKName, magicBooK);
            if (magicBooK.getPrice() <= 80) {
                normal.add(magicBooK);
            }
        }

    }

    /**
     * 获取非目标技能列表
     *
     * @param aim 目标技能列表
     */
    public static ArrayList<MagicBooK> getOtherBooks(Set<MagicBooK> aim) {
        Collection<MagicBooK> values = CBG.values();
        values.removeAll(aim);
        return new ArrayList<>(values);
    }

    /**
     * 获取能垫书的列表
     *
     * @param aim 目标技能列表
     */
    public static ArrayList<MagicBooK> getOtherNormalBooks(Set<MagicBooK> aim) {
        ArrayList<MagicBooK> magicBooKS = new ArrayList<>(normal);
        magicBooKS.removeAll(aim);
        return magicBooKS;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
