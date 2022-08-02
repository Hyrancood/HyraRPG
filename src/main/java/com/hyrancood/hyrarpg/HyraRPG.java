package com.hyrancood.hyrarpg;

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

import com.hyrancood.hyrarpg.capability.*;
import com.hyrancood.hyrarpg.item.HyrarpgItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hyrarpg")
public class HyraRPG {
	public static final String ID = "hyrarpg";
	private static final Logger LOGGER = LogManager.getLogger();

	public HyraRPG() {
		
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		HyrarpgItems.register(eventBus);
		eventBus.addListener(this::setup);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(IPoints.class, new PointsStorage(), Points::new);
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
