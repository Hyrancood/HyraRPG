package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.SkillsClientPacket.class
)
public class SkillsClientPacketSerializer implements ISerializer<SkillsClientPacket> {
  public void serialize(SkillsClientPacket value, ByteBuf acc) {
    serialize_SkillsClientPacket_Generic(value, acc);
  }

  public SkillsClientPacket unserialize(ByteBuf buf) {
    return unserialize_SkillsClientPacket_Generic(buf);
  }

  void serialize_SkillsClientPacket_Generic(SkillsClientPacket value, ByteBuf acc) {
    serialize_SkillsClientPacket_Concretic(value, acc);
  }

  SkillsClientPacket unserialize_SkillsClientPacket_Generic(ByteBuf buf) {
    return unserialize_SkillsClientPacket_Concretic(buf);
  }

  void serialize_SkillsClientPacket_Concretic(SkillsClientPacket value, ByteBuf acc) {
    serialize_String_Generic(value.skill1, acc);
    serialize_String_Generic(value.skill2, acc);
    serialize_Int_Generic(value.skill1KD, acc);
    serialize_Int_Generic(value.skill2KD, acc);
  }

  SkillsClientPacket unserialize_SkillsClientPacket_Concretic(ByteBuf buf) {
    SkillsClientPacket value = new SkillsClientPacket(unserialize_String_Generic(buf), unserialize_String_Generic(buf), unserialize_Int_Generic(buf), unserialize_Int_Generic(buf));
    return value;
  }
}
