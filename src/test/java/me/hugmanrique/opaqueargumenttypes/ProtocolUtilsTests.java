package me.hugmanrique.opaqueargumenttypes;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import net.kyori.adventure.key.Key;
import org.junit.jupiter.api.Test;

final class ProtocolUtilsTests {

  @Test
  void testVarIntLength() {
    assertEquals(1, ProtocolUtils.varIntLength(0));
    assertEquals(1, ProtocolUtils.varIntLength((1 << 7) - 1));

    assertEquals(2, ProtocolUtils.varIntLength(1 << 7));
    assertEquals(2, ProtocolUtils.varIntLength((1 << 14) - 1));

    assertEquals(3, ProtocolUtils.varIntLength(1 << 14));
    assertEquals(3, ProtocolUtils.varIntLength((1 << 21) - 1));

    assertEquals(4, ProtocolUtils.varIntLength(1 << 21));
    assertEquals(4, ProtocolUtils.varIntLength((1 << 28) - 1));

    assertEquals(5, ProtocolUtils.varIntLength(1 << 28));
    assertEquals(5, ProtocolUtils.varIntLength(Integer.MAX_VALUE));
    assertEquals(5, ProtocolUtils.varIntLength(Integer.MIN_VALUE));
  }

  private void assertVarIntEquals(final byte[] expected, final int value) {
    final int bufLen = ProtocolUtils.varIntLength(value);
    final byte[] dest = new byte[bufLen];
    ProtocolUtils.encodeVarInt(value, dest, 0);

    assertArrayEquals(expected, dest);
  }

  @Test
  void testEncodeVarInt() {
    assertVarIntEquals(new byte[] { 0x0 }, 0);
    assertVarIntEquals(new byte[] { 0x32 }, 50);

    assertVarIntEquals(new byte[] { 0x7F }, (1 << 7) - 1);
    assertVarIntEquals(new byte[] { (byte) 0x80, 0x1 }, 1 << 7);
    assertVarIntEquals(new byte[] { (byte) 0x98, 0x1 }, 152);

    assertVarIntEquals(new byte[] { (byte) 0xFF, 0x7F }, (1 << 14) - 1);
    assertVarIntEquals(new byte[] { (byte) 0x80, (byte) 0x80, 0x1 }, 1 << 14);
    assertVarIntEquals(new byte[] { (byte) 0xD2, (byte) 0xE0, 0x3 }, 61522);

    assertVarIntEquals(new byte[] { (byte) 0xFF, (byte) 0xFF, 0x7F }, (1 << 21) - 1);
    assertVarIntEquals(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x80, 0x1 }, 1 << 21);
    assertVarIntEquals(new byte[] { (byte) 0xFF, (byte) 0xED, (byte) 0x82, 0x4 }, 8435455);

    assertVarIntEquals(new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, 0x7F }, (1 << 28) - 1);
    assertVarIntEquals(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, 0x1 }, 1 << 28);
    assertVarIntEquals(new byte[] { (byte) 0xF5, (byte) 0xF7, (byte) 0xD9, (byte) 0xAF, 0x1}, 368475125);

    assertVarIntEquals(new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, 0x7 }, Integer.MAX_VALUE);
    assertVarIntEquals(new byte[] { (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0x80, 0x8 }, Integer.MIN_VALUE);
  }

  @Test
  void testEncodeIdentifier() {
    final byte[] encoded = ProtocolUtils.encodeIdentifier(Key.key("test"));
    final byte[] contents = Arrays.copyOfRange(encoded, 1, encoded.length);

    assertEquals(14, encoded[0]); // VarInt-encoded length in bytes
    assertArrayEquals("minecraft:test".getBytes(StandardCharsets.US_ASCII), contents);
  }
}
