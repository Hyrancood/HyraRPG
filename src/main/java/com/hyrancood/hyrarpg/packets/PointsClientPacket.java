package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.api.ClientToServerPacket;
import hohserg.elegant.networking.api.ElegantPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

@ElegantPacket
public class PointsClientPacket implements ClientToServerPacket {
    final int[] points;

    public PointsClientPacket(int[] array){
        this.points = array;
    }

    @Override
    public void onReceive(ServerPlayerEntity player) {
        System.out.println(points[0]);
    }
}
