package com.hyrancood.hyrarpg;

import com.hyrancood.hyrarpg.capability.character.Character;
import com.hyrancood.hyrarpg.capability.character.CharacterStorage;
import com.hyrancood.hyrarpg.capability.character.ICharacter;
import com.hyrancood.hyrarpg.capability.mana.IMana;
import com.hyrancood.hyrarpg.capability.mana.Mana;
import com.hyrancood.hyrarpg.capability.mana.ManaStorage;
import com.hyrancood.hyrarpg.capability.points.IPoints;
import com.hyrancood.hyrarpg.capability.points.Points;
import com.hyrancood.hyrarpg.capability.points.PointsStorage;
import com.hyrancood.hyrarpg.capability.skills.ISkills;
import com.hyrancood.hyrarpg.capability.skills.Skills;
import com.hyrancood.hyrarpg.capability.skills.SkillsStorage;
import com.hyrancood.hyrarpg.enchantments.HyraRPGEnchantments;
import com.hyrancood.hyrarpg.util.OverlayHandler;
import com.hyrancood.hyrarpg.util.events.PassiveManaRegeneration;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hyrancood.hyrarpg.item.HyraRPGItems;
import com.hyrancood.hyrarpg.util.CapabilityHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hyrarpg")
public class HyraRPG {
	public static final String ID = "hyrarpg";
	private static final Logger LOGGER = LogManager.getLogger();

	public HyraRPG() {
		
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		HyraRPGItems.register(eventBus);
		HyraRPGEnchantments.register(eventBus);
		eventBus.addListener(this::setup);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(IPoints.class, new PointsStorage(), Points::new);
		CapabilityManager.INSTANCE.register(ICharacter.class, new CharacterStorage(), Character::new);
		CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana::new);
		CapabilityManager.INSTANCE.register(ISkills.class, new SkillsStorage(), Skills::new);

		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new OverlayHandler());
		MinecraftForge.EVENT_BUS.register(new PassiveManaRegeneration());
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}
	}
}
