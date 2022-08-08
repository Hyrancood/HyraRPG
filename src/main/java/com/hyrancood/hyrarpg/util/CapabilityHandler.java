package com.hyrancood.hyrarpg.util;

import com.hyrancood.hyrarpg.HyraRPG;

import com.hyrancood.hyrarpg.capability.character.CharacterProvider;
import com.hyrancood.hyrarpg.capability.mana.ManaProvider;
import com.hyrancood.hyrarpg.capability.points.IPoints;
import com.hyrancood.hyrarpg.capability.points.Points;
import com.hyrancood.hyrarpg.capability.points.PointsProvider;
import com.hyrancood.hyrarpg.capability.skills.SkillsProvider;
import com.hyrancood.hyrarpg.packets.PointsServerPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HyraRPG.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {
    public static final ResourceLocation POINTS = new ResourceLocation(HyraRPG.ID, "points");
	public static final ResourceLocation CHARACTER = new ResourceLocation(HyraRPG.ID, "character");
	public static final ResourceLocation MANA = new ResourceLocation(HyraRPG.ID, "mana");
	public static final ResourceLocation SKILLS = new ResourceLocation(HyraRPG.ID, "skills");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    	if(event.getObject() instanceof PlayerEntity) {
        	event.addCapability(POINTS, new PointsProvider());
			event.addCapability(CHARACTER, new CharacterProvider());
			event.addCapability(MANA, new ManaProvider());
			event.addCapability(SKILLS, new SkillsProvider());
    	}
	}
    
    @SubscribeEvent
    public void clone(PlayerEvent.Clone event) {
    	IPoints original = event.getOriginal().getCapability(PointsProvider.capability, null).orElseGet(Points::new);
    	IPoints clone = event.getEntity().getCapability(PointsProvider.capability, null).orElseGet(Points::new);
    	clone.setPointsArray(original.getPointsArray());
		syncPacket(event.getEntity(), clone);
    }
    
    @SubscribeEvent
	public void onPlayerLoggedInSyncPoints(PlayerEvent.PlayerLoggedInEvent event) {
    	PlayerEntity player = event.getPlayer();
    	IPoints points = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
		syncPacket(event.getEntity(), points);
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		PlayerEntity player = event.getPlayer();
    	IPoints points = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
		syncPacket(event.getEntity(), points);
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		PlayerEntity player = event.getPlayer();
    	IPoints points = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
		syncPacket(event.getEntity(), points);
	}

	private void syncPacket(Entity entity, IPoints points){
		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			new PointsServerPacket(points.getPointsArray()).sendToPlayer(player);
		}
	}
}
