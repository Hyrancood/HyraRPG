package com.hyrancood.hyrarpg.packets;

import com.hyrancood.hyrarpg.capability.points.IPoints;
import com.hyrancood.hyrarpg.capability.points.Points;
import com.hyrancood.hyrarpg.capability.points.PointsProvider;
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
        IPoints p = player.getCapability(PointsProvider.capability).orElseGet(Points::new);
        p.setPointsArray(this.points);
    }
}
