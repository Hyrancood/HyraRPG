package com.hyrancood.hyrarpg.capability;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;


public class SyncPoints{
	private int[] data;
	
	public SyncPoints(int[] array) {
		this.data = array;
	}
	
	public SyncPoints(PacketBuffer buffer) {
		this.data = buffer.readVarIntArray();
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeVarIntArray(this.data);
	}
	
	public void handle(Supplier<NetworkEvent.Context> supplier) {
		Context context = supplier.get();
		context.enqueueWork(() -> {
			if (!context.getDirection().getReceptionSide().isServer()) {
				Minecraft instance = Minecraft.getInstance();
				IPoints variables = ((IPoints) instance.player.getCapability(PointsProvider.capability, null).orElseGet(Points::new));
				variables.setPointsArray(data);
			}
		});

        context.setPacketHandled(true);
	}
}
