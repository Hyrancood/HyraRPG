package com.hyrancood.hyrarpg.item;

import com.hyrancood.hyrarpg.HyraRPG;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HyrarpgItems {
	
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, HyraRPG.ID);
	
	public static final RegistryObject<Item> IronStick = ITEMS.register("iron_stick",
			() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(64).rarity(Rarity.COMMON)));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

}
