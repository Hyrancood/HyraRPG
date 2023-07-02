package com.hyrancood.hyrarpg.item;

import com.hyrancood.hyrarpg.capability.mana.IMana;
import com.hyrancood.hyrarpg.capability.mana.Mana;
import com.hyrancood.hyrarpg.capability.mana.ManaProvider;
import com.hyrancood.hyrarpg.capability.points.IPoints;
import com.hyrancood.hyrarpg.capability.points.Points;
import com.hyrancood.hyrarpg.capability.points.PointsProvider;
import com.hyrancood.hyrarpg.enchantments.HyraRPGEnchantments;
import com.hyrancood.hyrarpg.util.CapabilityHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Optional;

public class MagicWands {
    public static class WoodenWand extends Item {
        public WoodenWand(Properties properties) {
            super(properties);
        }

        @Override
        public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            ItemStack itemstack = player.getItemInHand(hand);
            System.out.println("pressed");
            BeginWandAttack(world, player, itemstack);
            return super.use(world, player, hand);
        }
    }

    public static class GoldenWand extends Item {
        public GoldenWand(Properties properties) {
            super(properties);
        }

        @Override
        public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            ActionResult<ItemStack> val = super.use(world, player, hand);
            ItemStack itemstack = val.getObject();
            BeginWandAttack(world, player, itemstack);
            return val;
        }
    }

    public static class DiamondWand extends Item {
        public DiamondWand(Properties properties) {
            super(properties);
        }

        @Override
        public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            ActionResult<ItemStack> val = super.use(world, player, hand);
            ItemStack itemstack = val.getObject();
            BeginWandAttack(world, player, itemstack);
            return val;
        }
    }

    public static class EmeraldWand extends Item {
        public EmeraldWand(Properties properties) {
            super(properties);
        }

        @Override
        public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
            ActionResult<ItemStack> val = super.use(world, player, hand);
            ItemStack itemstack = val.getObject();
            BeginWandAttack(world, player, itemstack);
            return val;
        }
    }

    private static void BeginWandAttack(World world, PlayerEntity player, ItemStack itemstack) {
        IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
        IPoints points = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
        boolean creative = player.isCreative();
        int raytrace_distance;
        double max_distance = 0;
        double manaRequired = 0;
        double damage = 1;
        int KD = 0;

        int magicPower = 0;
        int magicEfficiency = 0;
        int sacrifice = 0;

        if (itemstack.getItem() == HyraRPGItems.WOODEN_WAND.get()) {
            max_distance = 24;
            manaRequired = 5;
            damage = 3;
            KD = 40;
        } else if (itemstack.getItem() == HyraRPGItems.GOLDEN_WAND.get()) {
            max_distance = 32;
            manaRequired = 4;
            damage = 4;
            KD = 25;
        } else if (itemstack.getItem() == HyraRPGItems.DIAMOND_WAND.get()) {
            max_distance = 48;
            manaRequired = 6;
            damage = 6;
            KD = 40;
        } else if (itemstack.getItem() == HyraRPGItems.EMERALD_WAND.get()) {
            max_distance = 64;
            manaRequired = 5;
            damage = 7;
            KD = 30;
        }

        magicPower = EnchantmentHelper.getEnchantmentLevel(HyraRPGEnchantments.MAGIC_POWER.get(), player);
        magicEfficiency = EnchantmentHelper.getEnchantmentLevel(HyraRPGEnchantments.MAGIC_EFFICIENCY.get(), player);
        sacrifice = EnchantmentHelper.getEnchantmentLevel(HyraRPGEnchantments.SACRIFICE.get(), player);
        damage = damage + magicPower * 2;
        manaRequired = manaRequired * (1 - 0.05 * magicEfficiency);
        if (((mana.getMana() > manaRequired) && (sacrifice == 0)) || ((sacrifice > 0) && (player.getHealth() > 2 * sacrifice)) || creative) {
            EntityRayTraceResult ray = getV(player, max_distance);

            if (ray != null) {
                LivingEntity target = (LivingEntity) ray.getEntity();
                summonParticles(world, target, player);
                if (target != player && target.isAttackable()) {
                    System.out.println("hurted");
                    target.hurt(DamageSource.playerAttack(player), (float) damage);
                    points.addPoint(2);
                    if (sacrifice > 0) {
                        EffectInstance effect;
                        if (target.getMobType() == CreatureAttribute.UNDEAD) {
                            effect = new EffectInstance(Effects.REGENERATION, 80, sacrifice - 1);
                        } else {
                            effect = new EffectInstance(Effects.POISON, 80, sacrifice - 1);
                        }
                        target.addEffect(effect);
                    }
                }
            } else {
                world.addParticle(ParticleTypes.SPIT, player.getX(), player.getY() + 1, player.getZ(), 0, 0, 0);
            }

            if (!creative) {
                if (sacrifice == 0) {
                    mana.reduceMana((float) manaRequired);
                } else {
                    player.setHealth(player.getHealth() - 2 * sacrifice);
                }
            }
            player.getCooldowns().addCooldown(itemstack.getItem(), KD);
            new CapabilityHandler().syncClientPacket(player);
        } else {
            if (world.isClientSide) {
                String text = (sacrifice == 0)
                        ? "\u0412\u0430\u043C \u043D\u0435 \u0445\u0432\u0430\u0442\u0430\u0435\u0442 \u043C\u0430\u043D\u044B!"
                        : "\u0412\u0430\u043C \u043D\u0435 \u0445\u0432\u0430\u0442\u0430\u0435\u0442 \u0436\u0438\u0437\u043D\u0435\u043D\u043D\u043E\u0439 \u0441\u0438\u043B\u044B!";
                player.displayClientMessage(new StringTextComponent(text), false);
            }
        }
    }

    private static void summonParticles(World world, Entity target, PlayerEntity player) {
        double dx = (player.getX() - target.getX()) / 6;
        double dy = (player.getY() - target.getY()) / 6;
        double dz = (player.getZ() - target.getZ()) / 6;
        for (int i = 0; i < 7; i++) {
            world.addParticle(ParticleTypes.SPIT, player.getX() + (dx * i), player.getY() + (dy * i) + 1, player.getZ() + (dz * i), 0, 0, 0);
        }
        world.addParticle(ParticleTypes.SPIT, target.getX() + 1, target.getY(), target.getZ(), 0, 0, 0);
        world.addParticle(ParticleTypes.SPIT, target.getX() - 1, target.getY(), target.getZ(), 0, 0, 0);
        world.addParticle(ParticleTypes.SPIT, target.getX(), target.getY(), target.getZ() + 1, 0, 0, 0);
        world.addParticle(ParticleTypes.SPIT, target.getX(), target.getY(), target.getZ() - 1, 0, 0, 0);
    }

    public static EntityRayTraceResult getV(PlayerEntity player, double range) {
        float playerRotX = player.xRot;
        float playerRotY = player.yRot;
        Vector3d startPos = player.getEyePosition(1);
        float f2 = (float) Math.cos(-playerRotY * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = (float) Math.sin(-playerRotY * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = (float) -Math.cos(-playerRotX * ((float) Math.PI / 180F));
        float additionY = (float) Math.sin(-playerRotX * ((float) Math.PI / 180F));
        float additionX = f3 * f4;
        float additionZ = f2 * f4;
        double d0 = range * 2;
        Vector3d endVec = startPos.add((double) additionX * d0, (double) additionY * d0, (double) additionZ * d0);
        AxisAlignedBB startEndBox = new AxisAlignedBB(startPos, endVec);
        Entity entity = null;
        for (Entity entity1 : player.level.getEntities(player, startEndBox, (val) -> true)) {
            AxisAlignedBB aabb = entity1.getBoundingBox().inflate((entity1.getPickRadius() + d0) / 2);
            Optional<Vector3d> optional = aabb.clip(startPos, endVec);
            if (aabb.contains(startPos)) {
                if (d0 >= 0.0D) {
                    entity = entity1;
                    startPos = optional.orElse(startPos);
                    d0 = 0.0D;
                }
            } else if (optional.isPresent()) {
                Vector3d vec31 = optional.get();
                double d1 = startPos.distanceToSqr(vec31);
                if (d1 < d0 || d0 == 0.0D) {
                    if (entity1.getRootVehicle() == player.getRootVehicle() && !entity1.canRiderInteract()) {
                        if (d0 == 0.0D) {
                            entity = entity1;
                            startPos = vec31;
                        }
                    } else {
                        entity = entity1;
                        startPos = vec31;
                        d0 = d1;
                    }
                }

            }
        }
        return (entity == null) ? null : new EntityRayTraceResult(entity);
    }
}
