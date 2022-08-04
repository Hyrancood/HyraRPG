package com.hyrancood.hyrarpg;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hyrancood.hyrarpg.capability.IPoints;
import com.hyrancood.hyrarpg.capability.Points;
import com.hyrancood.hyrarpg.capability.PointsStorage;
import com.hyrancood.hyrarpg.events.TestEvent;
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
		eventBus.addListener(this::setup);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(IPoints.class, new PointsStorage(), Points::new);
		
		MinecraftForge.EVENT_BUS.register(new TestEvent());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
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
