package com.hyrancood.hyrarpg.enchantments;

import com.hyrancood.hyrarpg.HyraRPG;
import com.hyrancood.hyrarpg.item.HyraRPGItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HyraRPGEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, HyraRPG.ID);

    static EnchantmentType MAGIC_WANDS_TYPE = EnchantmentType.create("magic_wands", item -> item == HyraRPGItems.WANDS.getEntries());

    public static final RegistryObject<Enchantment> MAGIC_EFFICIENCY = ENCHANTMENTS.register("magic_efficiency", MagicEfficiency::new);
    public static final RegistryObject<Enchantment> MAGIC_POWER = ENCHANTMENTS.register("magic_power", MagicPower::new);
    public static final RegistryObject<Enchantment> SACRIFICE = ENCHANTMENTS.register("sacrifice", Sacrifice::new);

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
