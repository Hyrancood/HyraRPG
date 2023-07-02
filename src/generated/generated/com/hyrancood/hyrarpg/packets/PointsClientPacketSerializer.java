package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.PointsClientPacket.class
)
public class PointsClientPacketSerializer implements ISerializer<PointsClientPacket> {
  public void serialize(PointsClientPacket value, ByteBuf acc) {
    serialize_PointsClientPacket_Generic(value, acc);
  }

  public PointsClientPacket unserialize(ByteBuf buf) {
    return unserialize_PointsClientPacket_Generic(buf);
  }

  void serialize_PointsClientPacket_Generic(PointsClientPacket value, ByteBuf acc) {
    serialize_PointsClientPacket_Concretic(value, acc);
  }

  PointsClientPacket unserialize_PointsClientPacket_Generic(ByteBuf buf) {
    return unserialize_PointsClientPacket_Concretic(buf);
  }

  void serialize_PointsClientPacket_Concretic(PointsClientPacket value, ByteBuf acc) {
    serialize_ArrayInt_Generic(value.points, acc);
  }

  PointsClientPacket unserialize_PointsClientPacket_Concretic(ByteBuf buf) {
    PointsClientPacket value = new PointsClientPacket(unserialize_ArrayInt_Generic(buf));
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
