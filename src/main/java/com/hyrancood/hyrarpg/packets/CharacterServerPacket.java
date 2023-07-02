package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.character.*;
import com.hyrancood.hyrarpg.capability.character.Character;
import hohserg.elegant.networking.api.ElegantPacket;
import hohserg.elegant.networking.api.ServerToClientPacket;
import net.minecraft.client.Minecraft;

@ElegantPacket
public class CharacterServerPacket implements ServerToClientPacket {
    final String character;
    final int step;
    final int level;
    final float xp;
    final int needXP;

    public CharacterServerPacket(String character, int step, int level, float xp, int needXP){
        this.character = character;
        this.step = step;
        this.level = level;
        this.xp = xp;
        this.needXP = needXP;
    }

    @Override
    public void onReceive(Minecraft mc) {
        ICharacter capability = mc.player.getCapability(CharacterProvider.capability).orElseGet(Character::new);
        capability.setCharacter(this.character);
        capability.setLevel(this.level);
        capability.setStep(this.step);
        capability.setXP(this.xp);
        capability.setNeedXP(this.needXP);
    }
}
