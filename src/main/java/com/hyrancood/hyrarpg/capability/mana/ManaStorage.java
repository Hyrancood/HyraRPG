package com.hyrancood.hyrarpg.capability.mana;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ManaStorage implements Capability.IStorage<IMana>{
    @Nullable
    @Override
    public INBT writeNBT(Capability<IMana> capability, IMana instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("mana", instance.getMana());
        nbt.putFloat("baseMaxMana", instance.getMaxMana(0));
        nbt.putFloat("currentMaxMana", instance.getMaxMana(1));
        nbt.putFloat("baseManaRegenSpeed", instance.getManaRegenSpeed(0));
        nbt.putFloat("currentManaRegenSpeed", instance.getManaRegenSpeed(1));
        return nbt;
    }

    @Override
    public void readNBT(Capability<IMana> capability, IMana instance, Direction side, INBT inbt) {
        CompoundNBT nbt = (CompoundNBT) inbt;
        instance.setMana(nbt.getFloat("mana"));
        instance.setMaxMana(0, nbt.getFloat("baseMaxMana"));
        instance.setMaxMana(1, nbt.getFloat("currentMaxMana"));
        instance.setManaRegenSpeed(0, nbt.getFloat("baseManaRegenSpeed"));
        instance.setManaRegenSpeed(1, nbt.getFloat("currentManaRegenSpeed"));
    }
}
