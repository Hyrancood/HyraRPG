package com.hyrancood.hyrarpg.util.events;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.capability.character.Character;
import com.hyrancood.hyrarpg.capability.character.CharacterProvider;
import com.hyrancood.hyrarpg.capability.character.ICharacter;
import com.hyrancood.hyrarpg.util.CapabilityHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = HyraRPG.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Scoring {

    @SubscribeEvent
    public static void breakingBlocks(BlockEvent.BreakEvent event){
        PlayerEntity entity = event.getPlayer();
        BlockState block = event.getState();
        if (entity == null) return;
        if (block == null) return;

        float koof = 0.0f;
        float xp = 0.1f;
        if (block.getBlock() == Blocks.ACACIA_LOG) { koof = 0.07f; }
        if (block.getBlock() == Blocks.BIRCH_LOG) { koof = 0.065f; }
        if (block.getBlock() == Blocks.DARK_OAK_LOG) { koof = 0.06f; }
        if (block.getBlock() == Blocks.JUNGLE_LOG) { koof = 0.05f; }
        if (block.getBlock() == Blocks.OAK_LOG) { koof = 0.055f; }
        if (block.getBlock() == Blocks.SPRUCE_LOG) { koof = 0.05f; }
        if (block.getBlock() == Blocks.STONE) { koof = 0.04f; }
        if (block.getBlock() == Blocks.CARROTS) { koof = 0.025f; xp = 0.15f; }
        if (block.getBlock() == Blocks.POTATOES) { koof = 0.025f; xp = 0.15f; }
        if (block.getBlock() == Blocks.PUMPKIN) { koof = 0.025f; }

        if (Math.random() < koof){
            entity.getCapability(CharacterProvider.capability, null).orElseGet(Character::new).addXP(xp);
            new CapabilityHandler().syncServerPacket(entity);
        }
    }

    @SubscribeEvent
    public static void KillMob(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        Entity source = event.getSource().getEntity();
        World world = event.getEntity().getCommandSenderWorld();
        if (entity == null || source == null) return;
        if (source instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) source;
            float xp = 0;
            if (entity instanceof LivingEntity) { xp = MathHelper.nextFloat(new Random(),0.15f, 0.3f); }
            if (entity instanceof AnimalEntity) {
                xp = MathHelper.nextFloat(new Random(),0.4f, 0.6f);
                if (entity instanceof HoglinEntity) xp = MathHelper.nextFloat(new Random(),4, 5.5f);
            }
            if (entity instanceof MonsterEntity) {
                xp = MathHelper.nextFloat(new Random(), 0.75f, 1.25f);
                if (entity instanceof BlazeEntity) xp = MathHelper.nextFloat(new Random(),0.9f, 1.4f);
                if (entity instanceof CreeperEntity) xp = MathHelper.nextFloat(new Random(),0.7f, 1.2f);
                if (entity instanceof ElderGuardianEntity) xp = MathHelper.nextFloat(new Random(),15f, 30);
                if (entity instanceof EndermanEntity) xp = MathHelper.nextFloat(new Random(),0.6f, 1.1f);
                if (entity instanceof EndermiteEntity) xp = MathHelper.nextFloat(new Random(),1.4f, 2.1f);
                if (entity instanceof GuardianEntity) xp = MathHelper.nextFloat(new Random(),1.5f, 2.3f);
                if (entity instanceof IllusionerEntity) xp = MathHelper.nextFloat(new Random(),5, 8);
                if (entity instanceof PiglinEntity) xp = MathHelper.nextFloat(new Random(),1.2f, 2);
                if (entity instanceof PiglinBruteEntity) xp = MathHelper.nextFloat(new Random(),3.5f, 5);
                if (entity instanceof PillagerEntity || entity instanceof VindicatorEntity) xp = MathHelper.nextFloat(new Random(),1.5f, 3);
                if (entity instanceof VexEntity) xp = MathHelper.nextFloat(new Random(),2, 3);
                if (entity instanceof WitchEntity) xp = MathHelper.nextFloat(new Random(),3.5f, 4.5f);
                if (entity instanceof WitherSkeletonEntity) xp = MathHelper.nextFloat(new Random(),2, 4);
                if (entity instanceof ZoglinEntity) xp = MathHelper.nextFloat(new Random(),4, 5.5f);
                if (entity instanceof ZombifiedPiglinEntity) xp = MathHelper.nextFloat(new Random(),0.5f, 0.8f);
            }
            if (entity instanceof PhantomEntity) xp = MathHelper.nextFloat(new Random(),0.8f, 1.3f);
            if (entity instanceof ShulkerEntity) xp = MathHelper.nextFloat(new Random(),2f, 5);
            if (entity instanceof SlimeEntity) xp = MathHelper.nextFloat(new Random(),0.7f, 1.1f);
            if (entity instanceof MagmaCubeEntity) xp = MathHelper.nextFloat(new Random(),1, 1.3f);
            if (entity instanceof GhastEntity || entity instanceof IronGolemEntity) xp = MathHelper.nextFloat(new Random(),2, 3.5f);
            xp = (float) (1 * xp * (1 + 0.0007 * (world.getDayTime() / 24000)));

            player.getCapability(CharacterProvider.capability, null).orElseGet(Character::new).addXP(xp);
            new CapabilityHandler().syncServerPacket(player);
        }
    }

    @SubscribeEvent
    public static void playerDie(LivingDeathEvent event){
        Entity entity = event.getEntity();
        Entity source = event.getSource().getEntity();
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            ICharacter targetcharacter = player.getCapability(CharacterProvider.capability, null).orElseGet(Character::new);
            float targetxp = targetcharacter.getXP();
            if (source instanceof PlayerEntity) {
                ICharacter sourcecharacter = source.getCapability(CharacterProvider.capability, null).orElseGet(Character::new);
                if (targetxp >= 500) {
                    sourcecharacter.addXP(500);
                    targetcharacter.setXP(targetxp-500);
                } else {
                    sourcecharacter.addXP(targetxp);
                    targetcharacter.setXP(0);
                }
                new CapabilityHandler().syncServerPacket(source);
            } else {
                if (targetxp >= 300) {
                    targetcharacter.setXP(targetxp-300);
                } else {
                    targetcharacter.setXP(targetxp/2);
                }
            }
            new CapabilityHandler().syncServerPacket(player);
        }
    }
}
