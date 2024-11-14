package cn.xyr.mhxg.lianyao.pet;

import cn.xyr.mhxg.MagicBooK;
import cn.xyr.mhxg.lianyao.Config;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 召唤兽超类
 */
@Slf4j
public abstract class Pet {

    public static final Random random = new Random();

    /**
     * 非必带技能
     */
    public abstract Set<MagicBooK> getExtSkill();

    /**
     * 技能
     */
    @Getter
    public Set<MagicBooK> skill = new HashSet<>();

    /**
     * 天赋技能
     */
    public abstract Set<MagicBooK> getBaseSkill();

    /**
     * 攻击资质
     */
    protected int attack;
    /**
     * 防御资质
     */
    protected int defense;
    /**
     * 体力资质
     */
    protected int physical;

    /**
     * 法力资质
     */
    protected int magic;

    /**
     * 速度资质
     */
    protected int velocity;
    /**
     * 成长
     */
    protected double grow;

    /**
     * 价格
     */
    @Getter
    public int price;
    /**
     * 等级
     */
    public int level = 0;

    public boolean original = true;

    /**
     * 类型： 0:66洗的宝宝 1：c66洗的宝宝
     */
    public abstract int getType();

    /**
     * 种类
     */
    public abstract String getTypeName();

    /**
     * 洗一次的价格
     */
    public int getRestPrice() {
        return getType() == 0 ? 5 : 30;
    }

    /**
     * 胚子价格
     */
    public abstract int getBasePrice();

    /**
     * 洗
     */
    private void rest() {

    }

    /**
     * 强行洗出大于skill技能数
     *
     * @param skillNum 技能数
     */
    protected Pet(int skillNum) {
        if (skillNum == -1) {
            return;
        }
        int extSkillNum = Math.min(skillNum - getBaseSkill().size(), getExtSkill().size());
        price += getBasePrice();
        Set<MagicBooK> extSkill = getExtSkill();
        Set<MagicBooK> collect;
        do {
            collect = extSkill.stream().filter(s -> random.nextInt(10) < 2).collect(Collectors.toSet());
            price += getRestPrice();
        } while (collect.size() < extSkillNum);
        skill.addAll(getBaseSkill());
        skill.addAll(collect);
        log.debug("洗{}", this);
    }

    /**
     * 炼妖
     */
    public Pet compositing(Pet pet) {
        // 升到30级 3技能以下有14%概率领悟  每次升级0.5%概率领悟
//        pet.price += Config.upgrade;
//        this.price += Config.upgrade;
        int r = random.nextInt(100);
        if (r < 15) {
            log.info("{}和{}炼妖失败！", this, pet);
            this.price += pet.price;
            this.skill = Collections.emptySet();
            this.original = false;
            return this;
        }
        r = random.nextInt(2);
        String str = this + "和" + pet;
        Pet res = r == 0 ? this : pet;
        // 所有技能随机
        Set<MagicBooK> allSkill = new HashSet<>(this.skill);
        allSkill.addAll(pet.skill);
        res.skill.clear();
        res.skill.addAll(res.getBaseSkill());
        Set<MagicBooK> collect = allSkill.stream().filter(s -> random.nextInt(100) < 35).collect(Collectors.toSet());
        res.skill.addAll(collect);
        res.price = price + pet.price;
        original = false;
        log.info("{}炼妖出{}！", str, res);
        res.level = 25;
        return res;
    }

    /**
     * 打书
     */
    public void beat(MagicBooK book) {
        if (skill.contains(book)) {
            throw new RuntimeException("召唤兽已经有" + book + "技能了！");
        }
        int size = skill.size();
        boolean override = true;
        switch (size) {
            case 0:
                skill.add(book);
                break;
            case 1:
                override = random.nextInt(5) > 0;
                break;
            case 2:
                override = random.nextInt(10) > 0;
                break;
            case 3:
                if (getType() == 0) {
                    override = random.nextInt(20) > 0;
                }
            default:
        }
        MagicBooK removeBook = override ? remove() : null;
        skill.add(book);
        log.debug("打书，{}顶掉{}", book, removeBook);
    }

    /**
     * 垫书
     */
    public void pave(Pet pet) {
        // 判断垫哪个划算
        Set<MagicBooK> commonBaseSkill = new HashSet<>(pet.getBaseSkill());
        commonBaseSkill.addAll(getBaseSkill());
        Set<MagicBooK> extSkill1 = new HashSet<>(commonBaseSkill);
        extSkill1.addAll(pet.skill);
        Set<MagicBooK> filter1 = skill.stream().filter(extSkill1::contains).collect(Collectors.toSet());
        Set<MagicBooK> extSkill2 = new HashSet<>(commonBaseSkill);
        extSkill2.addAll(this.skill);
        Set<MagicBooK> filter2 = pet.skill.stream().filter(extSkill2::contains).collect(Collectors.toSet());
        BigDecimal pp1 = pavePrice(skill.size(), filter1.size());
        BigDecimal pp2 = pavePrice(pet.skill.size(), filter2.size());
        if (pp1.compareTo(pp2) > 0) {
            pet.pave(extSkill2, filter2);
            Set<MagicBooK> extSkill = new HashSet<>(commonBaseSkill);
            extSkill.addAll(pet.skill);
            Set<MagicBooK> filter = skill.stream().filter(extSkill::contains).collect(Collectors.toSet());
            pave(extSkill, filter);
        } else {
            pave(extSkill1, filter1);
            Set<MagicBooK> extSkill = new HashSet<>(commonBaseSkill);
            extSkill.addAll(skill);
            Set<MagicBooK> filter = pet.skill.stream().filter(extSkill::contains).collect(Collectors.toSet());
            pet.pave(extSkill, filter);
        }
    }

    /**
     * 只垫掉公共技能
     */
    public void paveCommonSkill(Pet pet) {
        Set<MagicBooK> filter = skill.stream().filter(pet.skill::contains).collect(Collectors.toSet());
        if (filter.isEmpty()) {
            return;
        }
        Set<MagicBooK> extSkill = new HashSet<>(skill);
        extSkill.addAll(pet.skill);
        if (skill.size() < pet.skill.size()) {
            pave(extSkill, filter);
        } else {
            pet.pave(extSkill, filter);
        }
    }

    /**
     * 垫书
     *
     * @param extSkill 不能垫的书
     * @param filter   要垫掉的书
     */
    public void pave(Set<MagicBooK> extSkill, Set<MagicBooK> filter) {
        if (filter.isEmpty()) {
            return;
        }
        // 垫书列表
        ArrayList<MagicBooK> otherNormalBooks = MagicBooK.getOtherNormalBooks(extSkill);
        // 原生胚子可以直接覆盖
        if (this.original && this.getType() == 0) {
            int p = Config.bookPrice * this.skill.size();
            String s = this.toString();
            price += p;
            this.skill = new HashSet<>(otherNormalBooks.subList(0, this.skill.size()));
            log.debug("原生胚子{}直接学习技能花费{}变为{}", s, p, this);
            this.original = false;
            return;
        }
        log.debug("{}垫书，需要垫掉{}技能", this, filter);
        MagicBooK newBook;
        while (!filter.isEmpty()) {
            do {
                newBook = otherNormalBooks.get(random.nextInt(otherNormalBooks.size()));
            } while (skill.contains(newBook));
            beat(newBook);
            price += newBook.getPrice();
            filter = skill.stream().filter(extSkill::contains).collect(Collectors.toSet());
        }
    }

    public void upLevel() {
        if (level < 30) {
            price += Config.upgrade;
            for (; level < 30 && skill.size() < 3; level++) {
                int i = random.nextInt(200);
                if (i == 0) {
                    List<MagicBooK> notSkill = new ArrayList<>(getBaseSkill());
                    notSkill.addAll(getExtSkill());
                    notSkill.removeAll(skill);
                    int r = random.nextInt(notSkill.size());
                    skill.add(notSkill.get(r));
                }
            }
        }
    }

    @Override
    public String toString() {
        return getSkill().size() + "技能" + getTypeName() + getSkill() + ",价格" + price;
    }

    /**
     * 随机移除一个技能
     */
    private MagicBooK remove() {
        int move = random.nextInt(skill.size());
        int count = 0;
        for (MagicBooK remove : skill) {
            if (move == count++) {
                skill.remove(remove);
                return remove;
            }
        }
        throw new RuntimeException("不可达代码");
    }

    /**
     * @param i 总技能数
     * @param j 要垫掉的技能数
     */
    public static BigDecimal pavePrice(int i, int j) {
        if (i <= 5) {
            return BigDecimal.valueOf(i);
        }
        BigDecimal res = BigDecimal.ZERO;
        for (int k = 1; k < j; k++) {
            res = res.add(BigDecimal.valueOf(i).divide(BigDecimal.valueOf(k), 4, BigDecimal.ROUND_HALF_UP));
        }
        return res;
    }

}
