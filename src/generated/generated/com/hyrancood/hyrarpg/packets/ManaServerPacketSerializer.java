package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.ManaServerPacket.class
)
public class ManaServerPacketSerializer implements ISerializer<ManaServerPacket> {
  public void serialize(ManaServerPacket value, ByteBuf acc) {
    serialize_ManaServerPacket_Generic(value, acc);
  }

  public ManaServerPacket unserialize(ByteBuf buf) {
    return unserialize_ManaServerPacket_Generic(buf);
  }

  void serialize_ManaServerPacket_Generic(ManaServerPacket value, ByteBuf acc) {
    serialize_ManaServerPacket_Concretic(value, acc);
  }

  ManaServerPacket unserialize_ManaServerPacket_Generic(ByteBuf buf) {
    return unserialize_ManaServerPacket_Concretic(buf);
  }

  void serialize_ManaServerPacket_Concretic(ManaServerPacket value, ByteBuf acc) {
    serialize_Float_Generic(value.m, acc);
    serialize_ArrayFloat_Generic(value.maxMana, acc);
    serialize_ArrayFloat_Generic(value.manaSpeed, acc);
  }

  ManaServerPacket unserialize_ManaServerPacket_Concretic(ByteBuf buf) {
    ManaServerPacket value = new ManaServerPacket(unserialize_Float_Generic(buf), unserialize_ArrayFloat_Generic(buf), unserialize_ArrayFloat_Generic(buf));
    return value;
  }

  void serialize_ArrayFloat_Generic(float[] value, ByteBuf acc) {
    acc.writeInt(value.length);
    for (float e :value) {
      serialize_Float_Generic(e,acc);
    }
  }

  float[] unserialize_ArrayFloat_Generic(ByteBuf buf) {
    int size = buf.readInt();
    float[] value = new  float[size];
    for (int i=0;i<size;i++) {
      float e = unserialize_Float_Generic(buf);
      value[i] = e;
    }
    return value;
  }
}
