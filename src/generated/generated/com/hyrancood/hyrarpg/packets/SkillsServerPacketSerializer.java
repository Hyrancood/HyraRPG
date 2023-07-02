package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.SkillsServerPacket.class
)
public class SkillsServerPacketSerializer implements ISerializer<SkillsServerPacket> {
  public void serialize(SkillsServerPacket value, ByteBuf acc) {
    serialize_SkillsServerPacket_Generic(value, acc);
  }

  public SkillsServerPacket unserialize(ByteBuf buf) {
    return unserialize_SkillsServerPacket_Generic(buf);
  }

  void serialize_SkillsServerPacket_Generic(SkillsServerPacket value, ByteBuf acc) {
    serialize_SkillsServerPacket_Concretic(value, acc);
  }

  SkillsServerPacket unserialize_SkillsServerPacket_Generic(ByteBuf buf) {
    return unserialize_SkillsServerPacket_Concretic(buf);
  }

  void serialize_SkillsServerPacket_Concretic(SkillsServerPacket value, ByteBuf acc) {
    serialize_String_Generic(value.skill1, acc);
    serialize_String_Generic(value.skill2, acc);
    serialize_Int_Generic(value.skill1KD, acc);
    serialize_Int_Generic(value.skill2KD, acc);
  }

  SkillsServerPacket unserialize_SkillsServerPacket_Concretic(ByteBuf buf) {
    SkillsServerPacket value = new SkillsServerPacket(unserialize_String_Generic(buf), unserialize_String_Generic(buf), unserialize_Int_Generic(buf), unserialize_Int_Generic(buf));
    return value;
  }
}
