package com.hyrancood.hyrarpg.client.overlay;

import com.hyrancood.hyrarpg.capability.character.*;
import com.hyrancood.hyrarpg.capability.character.Character;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.DecimalFormat;

public class CharacterOverlay extends AbstractGui {
    protected String character;
    protected float playerXP;
    protected int needXP;
    protected int lvl;
    protected int step;
    protected int w;
    protected int h;

    public CharacterOverlay(Minecraft mc, MatrixStack ms){
        this.w = mc.getWindow().getGuiScaledWidth();
        this.h = mc.getWindow().getGuiScaledHeight();

        ClientPlayerEntity player = mc.player;
        assert player != null;
        ICharacter character = player.getCapability(CharacterProvider.capability).orElseGet(Character::new);
        this.character = character.getCharacter();
        this.playerXP = character.getXP();
        this.needXP = character.getNeedXP();
        this.lvl = character.getLevel();
        this.step = character.getStep();

        String xp = new DecimalFormat("##.#").format(this.playerXP) +"/"+ this.needXP;
        String lev =""+this.lvl;
        String name = new TranslationTextComponent("character.hyrarpg." + this.character).getString() + "";
        drawCenteredString(ms, mc.font, name, this.w/2 + 50, this.h-58, -16751053);
        drawCenteredString(ms, mc.font, xp, this.w/2, this.h-58, -16764058);
        drawCenteredString(ms, mc.font, lev, this.w/2, this.h-48, -6750208);
    }
}
