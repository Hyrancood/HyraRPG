package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.api.ElegantPacket;
import hohserg.elegant.networking.api.ServerToClientPacket;
import net.minecraft.client.Minecraft;

@ElegantPacket
public class PointsServerPacket implements ServerToClientPacket {
    final int[] points;

    public PointsServerPacket(int[] array){
        this.points = array;
    }

    @Override
    public void onReceive(Minecraft mc) {
        System.out.println(points[0]);
    }
}
