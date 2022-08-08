package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.points.*;
import com.hyrancood.hyrarpg.capability.points.PointsProvider;
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
        IPoints p = mc.player.getCapability(PointsProvider.capability).orElseGet(Points::new);
        p.setPointsArray(this.points);
    }
}
