package com.hyrancood.hyrarpg.client.overlay;

import com.hyrancood.hyrarpg.capability.mana.IMana;
import com.hyrancood.hyrarpg.capability.mana.Mana;
import com.hyrancood.hyrarpg.capability.mana.ManaProvider;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ManaOverlay {
    private static final ResourceLocation EMPTYSTAR = new ResourceLocation("hyrarpg:textures/overlay/empty_manastar.png");
    private static final ResourceLocation FIRST = new ResourceLocation("hyrarpg:textures/overlay/firstquarter_manastar.png");
    private static final ResourceLocation SECOND = new ResourceLocation("hyrarpg:textures/overlay/secondquarter_manastar.png");
    private static final ResourceLocation THIRD = new ResourceLocation("hyrarpg:textures/overlay/thirdquarter_manastar.png");
    private static final ResourceLocation FOURTH = new ResourceLocation("hyrarpg:textures/overlay/fourthquarter_manastar.png");

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void manaRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            System.out.println("draw");
            int w = event.getWindow().getScreenWidth();
            int h = event.getWindow().getScreenHeight();
            PlayerEntity player = Minecraft.getInstance().player;
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableAlphaTest();
            int offsetX = 10;
            for (int i = 0; i < 10; i++) {
                Minecraft.getInstance().getTextureManager().bind(EMPTYSTAR);
                Minecraft.getInstance().gui.blit(event.getMatrixStack(), w / 2 + offsetX, h - 48, 0, 0, 8, 8, 8, 8);
                offsetX = offsetX + 8;
            }
            offsetX = 10;

            IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
            float currentMana = mana.getMana();
            float currentMaxMana = mana.getMaxMana(1);

            for (int i = 0; i < (int) (10); i++) {
                if (currentMana >= (currentMaxMana / 40) * 1 + (currentMaxMana / 10) * i) {
                    Minecraft.getInstance().getTextureManager().bind(FIRST);
                    Minecraft.getInstance().gui.blit(event.getMatrixStack(), w / 2 + offsetX, h - 48, 0, 0, 8, 8, 8, 8);
                }
                if (currentMana >= (currentMaxMana / 40) * 2 + (currentMaxMana / 10) * i) {
                    Minecraft.getInstance().getTextureManager().bind(SECOND);
                    Minecraft.getInstance().gui.blit(event.getMatrixStack(), w / 2 + offsetX, h - 48, 0, 0, 8, 8, 8, 8);
                }
                if (currentMana >= (currentMaxMana / 40) * 3 + (currentMaxMana / 10) * i) {
                    Minecraft.getInstance().getTextureManager().bind(THIRD);
                    Minecraft.getInstance().gui.blit(event.getMatrixStack(), w / 2 + offsetX, h - 48, 0, 0, 8, 8, 8, 8);
                }
                if (currentMana >= (currentMaxMana / 40) * 4 + (currentMaxMana / 10) * i) {
                    Minecraft.getInstance().getTextureManager().bind(FOURTH);
                    Minecraft.getInstance().gui.blit(event.getMatrixStack(), w / 2 + offsetX, h - 48, 0, 0, 8, 8, 8, 8);
                }

                offsetX = offsetX + 8;
            }
        }
    }
}
