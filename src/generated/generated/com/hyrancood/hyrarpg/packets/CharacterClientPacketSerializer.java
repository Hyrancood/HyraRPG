package com.hyrancood.hyrarpg.packets;

import hohserg.elegant.networking.impl.ISerializer;
import hohserg.elegant.networking.impl.SerializerMark;
import io.netty.buffer.ByteBuf;

@SerializerMark(
    packetClass = com.hyrancood.hyrarpg.packets.CharacterClientPacket.class
)
public class CharacterClientPacketSerializer implements ISerializer<CharacterClientPacket> {
  public void serialize(CharacterClientPacket value, ByteBuf acc) {
    serialize_CharacterClientPacket_Generic(value, acc);
  }

  public CharacterClientPacket unserialize(ByteBuf buf) {
    return unserialize_CharacterClientPacket_Generic(buf);
  }

  void serialize_CharacterClientPacket_Generic(CharacterClientPacket value, ByteBuf acc) {
    serialize_CharacterClientPacket_Concretic(value, acc);
  }

  CharacterClientPacket unserialize_CharacterClientPacket_Generic(ByteBuf buf) {
    return unserialize_CharacterClientPacket_Concretic(buf);
  }

  void serialize_CharacterClientPacket_Concretic(CharacterClientPacket value, ByteBuf acc) {
    serialize_String_Generic(value.character, acc);
    serialize_Int_Generic(value.step, acc);
    serialize_Int_Generic(value.level, acc);
    serialize_Float_Generic(value.xp, acc);
    serialize_Int_Generic(value.needXP, acc);
  }

  CharacterClientPacket unserialize_CharacterClientPacket_Concretic(ByteBuf buf) {
    CharacterClientPacket value = new CharacterClientPacket(unserialize_String_Generic(buf), unserialize_Int_Generic(buf), unserialize_Int_Generic(buf), unserialize_Float_Generic(buf), unserialize_Int_Generic(buf));
    return value;
  }
}
