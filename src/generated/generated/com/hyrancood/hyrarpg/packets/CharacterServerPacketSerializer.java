package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.CharacterServerPacket.class
)
public class CharacterServerPacketSerializer implements ISerializer<CharacterServerPacket> {
  public void serialize(CharacterServerPacket value, ByteBuf acc) {
    serialize_CharacterServerPacket_Generic(value, acc);
  }

  public CharacterServerPacket unserialize(ByteBuf buf) {
    return unserialize_CharacterServerPacket_Generic(buf);
  }

  void serialize_CharacterServerPacket_Generic(CharacterServerPacket value, ByteBuf acc) {
    serialize_CharacterServerPacket_Concretic(value, acc);
  }

  CharacterServerPacket unserialize_CharacterServerPacket_Generic(ByteBuf buf) {
    return unserialize_CharacterServerPacket_Concretic(buf);
  }

  void serialize_CharacterServerPacket_Concretic(CharacterServerPacket value, ByteBuf acc) {
    serialize_String_Generic(value.character, acc);
    serialize_Int_Generic(value.step, acc);
    serialize_Int_Generic(value.level, acc);
    serialize_Float_Generic(value.xp, acc);
    serialize_Int_Generic(value.needXP, acc);
  }

  CharacterServerPacket unserialize_CharacterServerPacket_Concretic(ByteBuf buf) {
    CharacterServerPacket value = new CharacterServerPacket(unserialize_String_Generic(buf), unserialize_Int_Generic(buf), unserialize_Int_Generic(buf), unserialize_Float_Generic(buf), unserialize_Int_Generic(buf));
    return value;
  }
}
