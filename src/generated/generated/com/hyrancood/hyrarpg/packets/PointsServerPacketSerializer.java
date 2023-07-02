package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.PointsServerPacket.class
)
public class PointsServerPacketSerializer implements ISerializer<PointsServerPacket> {
  public void serialize(PointsServerPacket value, ByteBuf acc) {
    serialize_PointsServerPacket_Generic(value, acc);
  }

  public PointsServerPacket unserialize(ByteBuf buf) {
    return unserialize_PointsServerPacket_Generic(buf);
  }

  void serialize_PointsServerPacket_Generic(PointsServerPacket value, ByteBuf acc) {
    serialize_PointsServerPacket_Concretic(value, acc);
  }

  PointsServerPacket unserialize_PointsServerPacket_Generic(ByteBuf buf) {
    return unserialize_PointsServerPacket_Concretic(buf);
  }

  void serialize_PointsServerPacket_Concretic(PointsServerPacket value, ByteBuf acc) {
    serialize_ArrayInt_Generic(value.points, acc);
  }

  PointsServerPacket unserialize_PointsServerPacket_Concretic(ByteBuf buf) {
    PointsServerPacket value = new PointsServerPacket(unserialize_ArrayInt_Generic(buf));
    return value;
  }

  void serialize_ArrayInt_Generic(int[] value, ByteBuf acc) {
    acc.writeInt(value.length);
    for (int e :value) {
      serialize_Int_Generic(e,acc);
    }
  }

  int[] unserialize_ArrayInt_Generic(ByteBuf buf) {
    int size = buf.readInt();
    int[] value = new  int[size];
    for (int i=0;i<size;i++) {
      int e = unserialize_Int_Generic(buf);
      value[i] = e;
    }
    return value;
  }
}
