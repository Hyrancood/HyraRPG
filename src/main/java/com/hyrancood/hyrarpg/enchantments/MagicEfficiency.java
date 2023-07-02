package com.hyrancood.hyrarpg.enchantments;

import com.hyrancood.hyrarpg.item.HyraRPGItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class MagicEfficiency extends Enchantment {

    public MagicEfficiency () {
        super(Rarity.COMMON, HyraRPGEnchantments.MAGIC_WANDS_TYPE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
    }

    public int getMaxLevel() {
        return 5;
    }
}
