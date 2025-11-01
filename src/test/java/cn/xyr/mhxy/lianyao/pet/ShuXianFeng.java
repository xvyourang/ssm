package cn.xyr.mhxy.lianyao.pet;

import cn.xyr.mhxy.MagicBooK;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 日游神
 */

public class ShuXianFeng extends Pet {

    public static final Set<MagicBooK> baseSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.MJ);
    }});

    public static final Set<MagicBooK> extSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.YZ);
        add(MagicBooK.TSYD);
        add(MagicBooK.MS);
    }});


    public static final Set<MagicBooK> allSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        addAll(extSkill);
        addAll(baseSkill);
    }});

    @Getter
    public static final int maxSkill = allSkill.size();

    public ShuXianFeng(int skill) {
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
        return "鼠先锋";
    }

    @Override
    public int getBasePrice() {
        return 5;
    }
}
