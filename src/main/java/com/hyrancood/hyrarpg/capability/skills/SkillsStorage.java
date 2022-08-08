package com.hyrancood.hyrarpg.capability.skills;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class SkillsStorage implements Capability.IStorage<ISkills>{
    @Nullable
    @Override
    public INBT writeNBT(Capability<ISkills> capability, ISkills instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("firstSkill", instance.getFirstSkill());
        nbt.putString("secondSkill", instance.getSecondSkill());
        nbt.putInt("firstSkillKD", instance.getFirstSkillKD());
        nbt.putInt("secondSkillKD", instance.getSecondSkillKD());
        return nbt;
    }

    @Override
    public void readNBT(Capability<ISkills> capability, ISkills instance, Direction side, INBT inbt) {
        CompoundNBT nbt = (CompoundNBT) inbt;
        instance.setFirstSkill(nbt.getString("firstSkill"));
        instance.setSecondSkill(nbt.getString("secondSkill"));
        instance.setFirstSkillKD(nbt.getInt("firstSkillKD"));
        instance.setSecondSkillKD(nbt.getInt("secondSkillKD"));
    }
}
