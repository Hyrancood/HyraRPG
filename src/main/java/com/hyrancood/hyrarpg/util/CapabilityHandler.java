package com.hyrancood.hyrarpg.util;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.capability.PointsProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HyraRPG.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {
    public static final ResourceLocation POINTS = new ResourceLocation(HyraRPG.ID, "points");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    	if(event.getObject() instanceof PlayerEntity) {
        	event.addCapability(POINTS, new PointsProvider());
    	}
	}

}