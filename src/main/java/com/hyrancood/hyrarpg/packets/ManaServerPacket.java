package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.mana.*;
import hohserg.elegant.networking.api.ElegantPacket;
import hohserg.elegant.networking.api.ServerToClientPacket;
import net.minecraft.client.Minecraft;

@ElegantPacket
public class ManaServerPacket implements ServerToClientPacket {
    final float m;
    final float[] maxMana;
    final float[] manaSpeed;

    public  ManaServerPacket(float mana, float[] maxMana, float[] manaSpeed) {
        this.m = mana;
        this.maxMana = maxMana;
        this.manaSpeed = manaSpeed;
    }

    @Override
    public void onReceive(Minecraft mc) {
        IMana mana = mc.player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
        mana.setMana(this.m);
        mana.setMaxManaArray(this.maxMana);
        mana.setManaRegenSpeedArray(this.manaSpeed);
    }
}
