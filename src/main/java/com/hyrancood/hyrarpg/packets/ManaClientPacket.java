package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.mana.*;
import hohserg.elegant.networking.api.ClientToServerPacket;
import hohserg.elegant.networking.api.ElegantPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

@ElegantPacket
public class ManaClientPacket implements ClientToServerPacket {
    final float m;
    final float[] maxMana;
    final float[] manaSpeed;

    public  ManaClientPacket(float mana, float[] maxMana, float[] manaSpeed) {
        this.m = mana;
        this.maxMana = maxMana;
        this.manaSpeed = manaSpeed;
    }

    @Override
    public void onReceive(ServerPlayerEntity player) {
        IMana mana = player.getCapability(ManaProvider.capability).orElseGet(Mana::new);
        mana.setMana(this.m);
        mana.setMaxManaArray(this.maxMana);
        mana.setManaRegenSpeedArray(this.manaSpeed);
    }
}
