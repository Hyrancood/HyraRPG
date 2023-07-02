package com.hyrancood.hyrarpg.item;

import com.hyrancood.hyrarpg.HyraRPG;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class HyraRPGItems {
	
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, HyraRPG.ID);
	public static final DeferredRegister<Item> WANDS =
			DeferredRegister.create(ForgeRegistries.ITEMS, HyraRPG.ID);

	public static final RegistryObject<Item> IRON_STICK = ITEMS.register("iron_stick",
			() -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(64).rarity(Rarity.COMMON)));

	//THROWING WEAPON
	public static final RegistryObject<Item> ROCK = ITEMS.register("rock",
			() -> new Rock(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(16).rarity(Rarity.COMMON)));

	//WANDS
	public static final RegistryObject<Item> WOODEN_WAND = WANDS.register("wooden_wand",
			() -> new MagicWands.WoodenWand(new Item.Properties().tab(HyraTabs.WANDS).stacksTo(1).rarity(Rarity.COMMON).durability(32)));
	public static final RegistryObject<Item> GOLDEN_WAND = WANDS.register("golden_wand",
			() -> new MagicWands.GoldenWand(new Item.Properties().tab(HyraTabs.WANDS).stacksTo(1).rarity(Rarity.COMMON).durability(48)));
	public static final RegistryObject<Item> DIAMOND_WAND = WANDS.register("diamond_wand",
			() -> new MagicWands.DiamondWand(new Item.Properties().tab(HyraTabs.WANDS).stacksTo(1).rarity(Rarity.COMMON).durability(64)));
	public static final RegistryObject<Item> EMERALD_WAND = WANDS.register("emerald_wand",
			() -> new MagicWands.EmeraldWand(new Item.Properties().tab(HyraTabs.WANDS).stacksTo(1).rarity(Rarity.COMMON).durability(96)));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
		WANDS.register(eventBus);
	}

	//TABS
	public static class HyraTabs extends ItemGroup {
		public static HyraTabs[] TABS = new HyraTabs[2];
		public static final ItemGroup WANDS = (new ItemGroup(ItemGroup.TABS.length, "Wands") {
			@OnlyIn(Dist.CLIENT)
			public ItemStack makeIcon() {
				return new ItemStack(EMERALD_WAND.get());
			}
		}).setRecipeFolderName("wands");

		public static final ItemGroup FIREARM = (new ItemGroup(ItemGroup.TABS.length+1, "Firearm") {
			@OnlyIn(Dist.CLIENT)
			public ItemStack makeIcon() {
				return new ItemStack(ROCK.get());
			}
		}).setRecipeFolderName("firearm");

		public HyraTabs(String label) {
			super(label);
		}

		@Override
		public ItemStack makeIcon() {
			return null;
		}
	}

}
