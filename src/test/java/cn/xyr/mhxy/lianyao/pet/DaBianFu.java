package cn.xyr.mhxy.lianyao.pet;

import cn.xyr.mhxy.MagicBooK;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 日游神
 */

public class DaBianFu extends Pet {


    public static final Set<MagicBooK> baseSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.FX);
        add(MagicBooK.GJYZ);
        add(MagicBooK.RDS);
    }});

    public static final Set<MagicBooK> extSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.XX);
        add(MagicBooK.GJGZ);
        add(MagicBooK.GJQG);
    }});
    public static final Set<MagicBooK> allSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        addAll(extSkill);
        addAll(baseSkill);
    }});

    public static final int maxSkill = allSkill.size();

    public DaBianFu(int skill) {
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
        return "大蝙蝠";
    }

    @Override
    public int getBasePrice() {
        return 5;
    }
}
