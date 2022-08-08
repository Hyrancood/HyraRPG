package com.hyrancood.hyrarpg.capability.character;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class CharacterStorage implements Capability.IStorage<ICharacter>{
    @Nullable
    @Override
    public INBT writeNBT(Capability<ICharacter> capability, ICharacter instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("character", instance.getCharacter());
        nbt.putInt("step", instance.getStep());
        nbt.putInt("level", instance.getLevel());
        nbt.putFloat("xp", instance.getXP());
        nbt.putInt("needXP", instance.getNeedXP());
        return nbt;
    }

    @Override
    public void readNBT(Capability<ICharacter> capability, ICharacter instance, Direction side, INBT inbt) {
        CompoundNBT nbt = (CompoundNBT) inbt;
        instance.setCharacter(nbt.getString("character"));
        instance.setStep(nbt.getInt("step"));
        instance.setLevel(nbt.getInt("level"));
        instance.setXP(nbt.getFloat("xp"));
        instance.setNeedXP(nbt.getInt("needXP"));
    }
}
