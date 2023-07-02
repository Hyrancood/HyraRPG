package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.api.IByteBufSerializable;
import hohserg.elegant.networking.impl.IPacketProvider;
import hohserg.elegant.networking.impl.PacketProviderMark;
import java.lang.Class;
import java.lang.String;

@PacketProviderMark
public class PointsServerPacketProvider implements IPacketProvider {
  public Class<? extends IByteBufSerializable> getPacketClass() {
    return com.hyrancood.hyrarpg.packets.PointsServerPacket.class;
  }

  public String modid() {
    return "hyrarpg";
  }
}
