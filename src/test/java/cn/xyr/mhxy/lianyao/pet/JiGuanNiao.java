package cn.xyr.mhxy.lianyao.pet;

import cn.xyr.mhxy.MagicBooK;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 日游神
 */

public class JiGuanNiao extends Pet {

    public static final Set<MagicBooK> baseSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.FX);
    }});

    public static final Set<MagicBooK> extSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        add(MagicBooK.GJTX);
        add(MagicBooK.YZ);
        add(MagicBooK.GJZS);
        add(MagicBooK.FDXY);
    }});


    public static final Set<MagicBooK> allSkill = Collections.unmodifiableSet(new HashSet<MagicBooK>() {{
        addAll(extSkill);
        addAll(baseSkill);
    }});

    @Getter
    public static final int maxSkill = allSkill.size();

    public JiGuanNiao(int skill) {
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
        return 1;
    }

    @Override
    public String getTypeName() {
        return "机关鸟";
    }

    @Override
    public int getBasePrice() {
        return 5;
    }
}
