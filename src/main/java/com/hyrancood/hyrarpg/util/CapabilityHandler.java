package com.hyrancood.hyrarpg.util;

import com.hyrancood.hyrarpg.HyraRPG;

import com.hyrancood.hyrarpg.capability.character.*;
import com.hyrancood.hyrarpg.capability.character.Character;
import com.hyrancood.hyrarpg.capability.mana.*;
import com.hyrancood.hyrarpg.capability.points.*;
import com.hyrancood.hyrarpg.capability.skills.*;
import com.hyrancood.hyrarpg.packets.*;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
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
		PlayerEntity original = event.getOriginal();
		PlayerEntity clone = event.getPlayer();
		IPoints points = original.getCapability(PointsProvider.capability).orElseGet(Points::new);
		ICharacter character = original.getCapability(CharacterProvider.capability).orElseGet(Character::new);
		IMana mana = original.getCapability(ManaProvider.capability).orElseGet(Mana::new);
		ISkills skills = original.getCapability(SkillsProvider.capability).orElseGet(Skills::new);

		IPoints new_points = clone.getCapability(PointsProvider.capability).orElseGet(Points::new);
		ICharacter new_character = clone.getCapability(CharacterProvider.capability).orElseGet(Character::new);
		IMana new_mana = clone.getCapability(ManaProvider.capability).orElseGet(Mana::new);
		ISkills new_skills = clone.getCapability(SkillsProvider.capability).orElseGet(Skills::new);

    	new_points.setPointsArray(points.getPointsArray());

		new_character.setCharacter(character.getCharacter());
		new_character.setStep(character.getStep());
		new_character.setLevel(character.getLevel());
		new_character.setXP(character.getXP());
		new_character.setNeedXP(character.getNeedXP());

		new_mana.setMana(mana.getMana());
		new_mana.setMaxManaArray(mana.getMaxManaArray());
		new_mana.setManaRegenSpeedArray(mana.getManaRegenSpeedArray());

		new_skills.setFirstSkill(skills.getFirstSkill());
		new_skills.setSecondSkill(skills.getSecondSkill());
		new_skills.setFirstSkillKD(skills.getFirstSkillKD());
		new_skills.setSecondSkillKD(skills.getSecondSkillKD());
		syncServerPacket(event.getEntity());
    }
    
    @SubscribeEvent
	public void onPlayerLoggedInSyncPoints(PlayerEvent.PlayerLoggedInEvent event) {
		syncServerPacket(event.getEntity());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		syncServerPacket(event.getEntity());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		syncServerPacket(event.getEntity());
	}

	public void upgradeLevel (Entity entity){
		if (entity instanceof PlayerEntity){
			PlayerEntity player = (PlayerEntity) entity;
			ICharacter character = player.getCapability(CharacterProvider.capability).orElseGet(Character::new);
			boolean flag = player.getCommandSenderWorld().isClientSide;
			int lvl = character.getLevel();
			int step = character.getStep();
			float xp = character.getXP();
			int needXP = character.getNeedXP();
			if (lvl < 10+(15*step)){
				if (xp >= needXP){
					xp = 0;
					character.setXP(xp);
					character.addLevel();
					if (flag) player.displayClientMessage(
							new StringTextComponent("\u00A72\u0412\u044B \u043F\u043E\u0432\u044B\u0441\u0438\u043B\u0438 \u0443\u0440\u043E\u0432\u0435\u043D\u044C!"),
							(false));
					if (lvl < 10+(15*step)){
						if (step == 0){
							needXP = needXP + 10;
						} else if (step == 1){
							needXP = needXP + 70;
						} else {
							needXP = needXP + 150;
						}
					} else {
						needXP = 0;
						if (flag) {
							player.displayClientMessage(
									new StringTextComponent("\u00A72\u0412\u044B \u0434\u043E\u0441\u0442\u0438\u0433\u043B\u0438 \u043C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0433\u043E \u0443\u0440\u043E\u0432\u043D\u044F!"),
									(false));
							player.displayClientMessage(
									new StringTextComponent("\u00A7a\u041F\u0440\u043E\u0432\u0435\u0440\u044C\u0442\u0435 \u043E\u043A\u043D\u043E \u044D\u0432\u043E\u043B\u044E\u0446\u0438\u0438!"),
									(false));
						}
					}
					character.setNeedXP(needXP);
				}
			}
		}
	}

	public void syncServerPacket(Entity entity){
		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			upgradeLevel(player);
			IPoints points = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
			ICharacter character = player.getCapability(CharacterProvider.capability).orElseGet(Character::new);
			IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
			ISkills skills = player.getCapability(SkillsProvider.capability).orElseGet(Skills::new);

			new PointsServerPacket(points.getPointsArray()).sendToPlayer(player);
			new CharacterServerPacket(character.getCharacter(), character.getStep(), character.getLevel(), character.getXP(), character.getNeedXP()).sendToPlayer(player);
			new ManaServerPacket(mana.getMana(), mana.getMaxManaArray(), mana.getManaRegenSpeedArray()).sendToPlayer(player);
			new SkillsServerPacket(skills.getFirstSkill(), skills.getSecondSkill(), skills.getFirstSkillKD(), skills.getSecondSkillKD()).sendToPlayer(player);
		}
	}

	public void syncClientPacket(Entity entity){
		if (entity instanceof ClientPlayerEntity) {
			ClientPlayerEntity player = (ClientPlayerEntity) entity;
			upgradeLevel(player);
			IPoints points = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
			ICharacter character = player.getCapability(CharacterProvider.capability).orElseGet(Character::new);
			IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
			ISkills skills = player.getCapability(SkillsProvider.capability).orElseGet(Skills::new);

			new PointsClientPacket(points.getPointsArray()).sendToServer();
			new CharacterClientPacket(character.getCharacter(), character.getStep(), character.getLevel(), character.getXP(), character.getNeedXP()).sendToServer();
			new ManaClientPacket(mana.getMana(), mana.getMaxManaArray(), mana.getManaRegenSpeedArray()).sendToServer();
			new SkillsClientPacket(skills.getFirstSkill(), skills.getSecondSkill(), skills.getFirstSkillKD(), skills.getSecondSkillKD()).sendToServer();
		}
	}
}
