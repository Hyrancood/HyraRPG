package com.hyrancood.hyrarpg.events;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.capability.IPoints;
import com.hyrancood.hyrarpg.capability.Points;
import com.hyrancood.hyrarpg.capability.PointsProvider;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HyraRPG.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TestEvent {
	
	@SubscribeEvent
    public static void onPlayerLogsIn(PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        LazyOptional<IPoints> lazypoints = player.getCapability(PointsProvider.capability, null);
		IPoints points = lazypoints.orElse(new Points());

        player.displayClientMessage(new StringTextComponent("Welcome, your power is " + (points.getPoints(0))), false);
        System.out.println(points.getPointsArray());
    }
	
	@SubscribeEvent
	public static void RMCevent(PlayerInteractEvent.RightClickEmpty event) {
		PlayerEntity player = event.getPlayer();
		LazyOptional<IPoints> lazypoints = player.getCapability(PointsProvider.capability, null);
		IPoints points = lazypoints.orElseGet(Points::new);
	    int p = points.getPoints(0);
	    System.out.println(p);
	    points.addPoint(0);
	    p = points.getPoints(0);
	    System.out.println(p);
	    points.addPoints(0, 2);
	    p = points.getPoints(0);
	    System.out.println(p);
	}
}
