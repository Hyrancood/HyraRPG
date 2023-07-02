package com.hyrancood.hyrarpg.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class MagicPower extends Enchantment {

    public MagicPower () {
        super(Rarity.COMMON, HyraRPGEnchantments.MAGIC_WANDS_TYPE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
    }

    public int getMaxLevel() {
        return 5;
    }
}
