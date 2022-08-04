package com.hyrancood.hyrarpg.events;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.capability.IPoints;
import com.hyrancood.hyrarpg.capability.Points;
import com.hyrancood.hyrarpg.capability.PointsProvider;

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
		new PointsClientPacket(points.getPointsArray()).sendToServer();
	    int p = points.getPoints(0);
	    System.out.println(p);
	    points.addPoint(0);
	}
}
