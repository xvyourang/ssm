package cn.xyr.mhxg.lianyao.pet;

import cn.xyr.mhxg.MagicBooK;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 日游神
 */

public class LeiYao extends Pet {

    public static final Set<MagicBooK> extSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.MS);
        add(MagicBooK.GJMZX);
    }});
    public static final Set<MagicBooK> baseSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.FSBJ);
        add(MagicBooK.RDT);
        add(MagicBooK.SG);
    }});
    public static final Set<MagicBooK> allSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        addAll(extSkill);
        addAll(baseSkill);
    }});

    @Getter
    public static final int maxSkill = allSkill.size();

    public LeiYao(int skill) {
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
        return "泪妖";
    }

    @Override
    public int getBasePrice() {
        return 5;
    }
}
