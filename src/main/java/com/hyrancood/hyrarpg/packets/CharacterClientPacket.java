package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.character.*;
import com.hyrancood.hyrarpg.capability.character.Character;
import hohserg.elegant.networking.api.ClientToServerPacket;
import hohserg.elegant.networking.api.ElegantPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

@ElegantPacket
public class CharacterClientPacket implements ClientToServerPacket {
    final String character;
    final int step;
    final float xp;
    final int needXP;

    public CharacterClientPacket(String character, int step, float xp, int needXP){
        this.character = character;
        this.step = step;
        this.xp = xp;
        this.needXP = needXP;
    }

    @Override
    public void onReceive(ServerPlayerEntity player) {
        ICharacter capability = player.getCapability(CharacterProvider.capability).orElseGet(Character::new);
        capability.setCharacter(this.character);
        capability.setStep(this.step);
        capability.setXP(this.xp);
        capability.setNeedXP(this.needXP);
    }
}
