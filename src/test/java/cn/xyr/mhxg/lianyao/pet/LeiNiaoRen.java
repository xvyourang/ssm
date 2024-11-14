package cn.xyr.mhxg.lianyao.pet;

import cn.xyr.mhxg.MagicBooK;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 日游神
 */

public class LeiNiaoRen extends Pet {

    public static final Set<MagicBooK> extSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.GJLSXXS);
        add(MagicBooK.BLZ);
    }});
    public static final Set<MagicBooK> baseSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.FX);
        add(MagicBooK.RDT);
        add(MagicBooK.LJI);
    }});

    public static final Set<MagicBooK> allSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        addAll(extSkill);
        addAll(baseSkill);
    }});

    public static final int maxSkill = allSkill.size();

    public LeiNiaoRen(int skill) {
        super(skill);
    }

    @Override
    public Set<MagicBooK> getExtSkill() {
        return extSkill;
    }

    @Override
    public Set<MagicBooK> getBaseSkill() {
        return baseSkill;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public String getTypeName() {
        return "雷鸟人";
    }

    @Override
    public int getBasePrice() {
        return 5;
    }
}
