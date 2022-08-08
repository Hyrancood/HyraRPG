package com.hyrancood.hyrarpg.capability.points;

import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

import javax.annotation.Nullable;

public class PointsStorage implements Capability.IStorage<IPoints> {

	@Nullable
	@Override
	public INBT writeNBT(Capability<IPoints> capability, IPoints instance, net.minecraft.util.Direction side) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putIntArray("points", instance.getPointsArray());
		return nbt;
	}

	@Override
	public void readNBT(Capability<IPoints> capability, IPoints instance, net.minecraft.util.Direction side,
			INBT inbt) {
		CompoundNBT nbt = (CompoundNBT) inbt;
		instance.setPointsArray(nbt.getIntArray("points"));
		
	}

}
