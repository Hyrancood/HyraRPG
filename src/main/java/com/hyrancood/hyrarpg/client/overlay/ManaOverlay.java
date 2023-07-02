package com.hyrancood.hyrarpg.client.overlay;

import com.hyrancood.hyrarpg.capability.mana.*;
import com.hyrancood.hyrarpg.capability.mana.ManaProvider;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

import java.text.DecimalFormat;

public class ManaOverlay extends AbstractGui {
    protected static final ResourceLocation MANASTAR = new ResourceLocation("hyrarpg:textures/wigets.png");
    protected float playerMana;
    protected float maxMana;
    protected int w;
    protected int h;

    public ManaOverlay(Minecraft mc, MatrixStack ms){
        this.w = mc.getWindow().getGuiScaledWidth();
        this.h = mc.getWindow().getGuiScaledHeight();

        ClientPlayerEntity player = mc.player;
        assert player != null;
        IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
        this.playerMana = mana.getMana();
        this.maxMana = mana.getMaxMana(1);

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableAlphaTest();

        mc.getTextureManager().bind(MANASTAR);
        int offsetX = 10;
        for (int i = 0; i < 10; i++) {
            blit(ms, this.w/2 + offsetX, this.h-48, 0, 0, 8, 8, 64, 64);
            if (this.playerMana >= (this.maxMana/40)*1 + (this.maxMana / 10)*i) {
                blit(ms, this.w/2 + offsetX, this.h-48, 8, 0, 8, 8, 64, 64);
            }
            if (this.playerMana >= (this.maxMana/40)*2 + (this.maxMana / 10)*i) {
                blit(ms, this.w/2 + offsetX, this.h-48, 16, 0, 8, 8, 64, 64);
            }
            if (this.playerMana >= (this.maxMana/40)*3 + (this.maxMana / 10)*i) {
                blit(ms, this.w/2 + offsetX, this.h-48, 24, 0, 8, 8, 64, 64);
            }
            if (this.playerMana >= (this.maxMana/40)*4 + (this.maxMana / 10)*i) {
                blit(ms, this.w/2 + offsetX, this.h-48, 32, 0, 8, 8, 64, 64);
            }
            offsetX = offsetX + 8;
        }

        String text = new DecimalFormat("##.#").format(this.playerMana) + "/" + new DecimalFormat("##.#").format(this.maxMana);
        drawCenteredString(ms, mc.font, text, this.w/2 + 50, this.h-48, 10526880);

        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableAlphaTest();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
