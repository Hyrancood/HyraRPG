package com.hyrancood.hyrarpg.util;

import com.hyrancood.hyrarpg.client.overlay.CharacterOverlay;
import com.hyrancood.hyrarpg.client.overlay.ManaOverlay;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OverlayHandler {

    @SubscribeEvent
    public static void overlayRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        Minecraft mc = Minecraft.getInstance();
        MatrixStack ms = event.getMatrixStack();
        assert mc.gameMode != null;
        GameType gm = mc.gameMode.getPlayerMode();
        if (gm == GameType.SURVIVAL || gm == GameType.ADVENTURE) {
            new ManaOverlay(mc, ms);
            new CharacterOverlay(mc, ms);
        }

    }
}
