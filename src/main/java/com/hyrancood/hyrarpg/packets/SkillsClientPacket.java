package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.skills.*;
import hohserg.elegant.networking.api.ClientToServerPacket;
import hohserg.elegant.networking.api.ElegantPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

@ElegantPacket
public class SkillsClientPacket implements ClientToServerPacket {
    final String skill1;
    final String skill2;
    final int skill1KD;
    final int skill2KD;

    public SkillsClientPacket(String skill1, String skill2, int skill1KD, int skill2KD){
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill1KD = skill1KD;
        this.skill2KD = skill2KD;
    }

    @Override
    public void onReceive(ServerPlayerEntity player) {
        ISkills skills = player.getCapability(SkillsProvider.capability).orElseGet(Skills::new);
        skills.setFirstSkill(this.skill1);
        skills.setSecondSkill(this.skill2);
        skills.setFirstSkillKD(this.skill1KD);
        skills.setSecondSkillKD(this.skill2KD);
    }
}
