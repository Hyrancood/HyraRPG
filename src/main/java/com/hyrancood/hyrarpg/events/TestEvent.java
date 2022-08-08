package com.hyrancood.hyrarpg.events;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.capability.points.IPoints;
import com.hyrancood.hyrarpg.capability.points.Points;
import com.hyrancood.hyrarpg.capability.points.PointsProvider;

import com.hyrancood.hyrarpg.packets.PointsClientPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HyraRPG.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestEvent {
	
	@SubscribeEvent
	public static void RMCevent(PlayerInteractEvent.RightClickEmpty event) {
		PlayerEntity player = event.getPlayer();
		IPoints points = player.getCapability(PointsProvider.capability, null).orElseGet(Points::new);
	    int p = points.getPoints(3);
	    points.addPoint(3);
		new PointsClientPacket(points.getPointsArray()).sendToServer();
	}
}
