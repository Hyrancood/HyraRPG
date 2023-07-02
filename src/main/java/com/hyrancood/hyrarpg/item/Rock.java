package com.hyrancood.hyrarpg.item;

import com.hyrancood.hyrarpg.entity.projectile.RockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Rock extends Item {

    public Rock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            RockEntity rockentity = new RockEntity(world, player);
            rockentity.setItem(itemstack);
            rockentity.shootFromRotation(player, player.xRot, player.yRot, 3.0F, 1.5F, 1.0F);
            world.addFreshEntity(rockentity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.sidedSuccess(itemstack, world.isClientSide());
    }
}
