package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.ManaClientPacket.class
)
public class ManaClientPacketSerializer implements ISerializer<ManaClientPacket> {
  public void serialize(ManaClientPacket value, ByteBuf acc) {
    serialize_ManaClientPacket_Generic(value, acc);
  }

  public ManaClientPacket unserialize(ByteBuf buf) {
    return unserialize_ManaClientPacket_Generic(buf);
  }

  void serialize_ManaClientPacket_Generic(ManaClientPacket value, ByteBuf acc) {
    serialize_ManaClientPacket_Concretic(value, acc);
  }

  ManaClientPacket unserialize_ManaClientPacket_Generic(ByteBuf buf) {
    return unserialize_ManaClientPacket_Concretic(buf);
  }

  void serialize_ManaClientPacket_Concretic(ManaClientPacket value, ByteBuf acc) {
    serialize_Float_Generic(value.mana, acc);
    serialize_ArrayFloat_Generic(value.maxMana, acc);
    serialize_ArrayFloat_Generic(value.manaSpeed, acc);
  }

  ManaClientPacket unserialize_ManaClientPacket_Concretic(ByteBuf buf) {
    ManaClientPacket value = new ManaClientPacket(unserialize_Float_Generic(buf), unserialize_ArrayFloat_Generic(buf), unserialize_ArrayFloat_Generic(buf));
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
