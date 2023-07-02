package com.hyrancood.hyrarpg.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class Sacrifice extends Enchantment {

    public Sacrifice () {
        super(Rarity.RARE, HyraRPGEnchantments.MAGIC_WANDS_TYPE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
    }

    public int getMaxLevel() {
        return 2;
    }
}
