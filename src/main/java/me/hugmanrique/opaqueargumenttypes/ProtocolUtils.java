package me.hugmanrique.opaqueargumenttypes;

import net.kyori.adventure.key.Key;

/**
 * Provides utilities for encoding some data types in the Minecraft protocol.
 */
final class ProtocolUtils {

  // These methods aren't called that frequently, so VarInt encoding doesn't need
  // to be extremely efficient. Thus, we use the standard algorithm.

  static int varIntLength(final int value) {
    return (31 - Integer.numberOfLeadingZeros(value)) / 7 + 1;
  }

  static void encodeVarInt(int value, final byte[] dest, int offset) {
    do {
      byte current = (byte) (value & 0x7F);
      value >>>= 7;
      if (value != 0) {
        current |= 0x80; // set continuation bit
      }
      dest[offset++] = current;
    } while (value != 0);
  }

  private static byte[] encodeAsciiString(final String value) {
    final int varIntLen = varIntLength(value.length());
    final int bufLen = varIntLen + value.length(); // in ASCII, each character occupies a single byte.
    byte[] encoded = new byte[bufLen];

    encodeVarInt(value.length(), encoded, 0);
    for (int i = 0; i < value.length(); i++) {
      encoded[varIntLen + i] = (byte) value.charAt(i);
    }
    return encoded;
  }

  static byte[] encodeIdentifier(final Key identifier) {
    return encodeAsciiString(identifier.asString());
  }

  static byte[] encodeBoolean(final boolean value) {
    return new byte[] { value ? (byte) 0x1 : (byte) 0x0 };
  }

  private ProtocolUtils() {
    throw new AssertionError();
  }
}
