package me.hugmanrique.opaqueargumenttypes;

import static me.hugmanrique.opaqueargumenttypes.ProtocolUtils.encodeBoolean;
import static me.hugmanrique.opaqueargumenttypes.ProtocolUtils.encodeIdentifier;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.OpaqueArgumentType;
import com.velocitypowered.api.network.ProtocolVersion;
import net.kyori.adventure.key.Key;

/**
 * Provides argument types corresponding to the argument parsers known by
 * the vanilla client in different {@link ProtocolVersion protocol versions}.
 *
 * <p>Most of the javadoc contents in this class are taken from the
 * <a href="https://wiki.vg/Command_Data#Parsers">wiki.vg Command Data</a> page,
 * licensed under the <a href="https://creativecommons.org/licenses/by-sa/3.0/">CC BY-SA 3.0</a>
 * license.
 */
public final class OpaqueArgumentTypes {

  /**
   * Returns an argument type for the {@code minecraft:entity} parser.
   * Used to specify a selector, player name, or UUID.
   *
   * @param manager the command manager.
   * @param single if set, only allows a single entity.
   * @param onlyPlayers if set, only allows players.
   * @return the argument type.
   */
  public static OpaqueArgumentType entity(final CommandManager manager,
                                          final boolean single, final boolean onlyPlayers) {
    byte[] properties = encodeBoolean(single);
    if (onlyPlayers) {
      properties[0] |= 0x2;
    }

    return manager.opaqueArgumentTypeBuilder(Key.key("entity"))
        .withProperties(properties)
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:game_profile} parser.
   * Used to specify a player, only or not. Can also use a select, which may
   * match one or more players (but not entities).
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType gameProfile(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("game_profile"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:block_pos} parser.
   * Used to specify a location, represented as 3 integers. May use relative
   * locations with {@code ~}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType blockPos(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("block_pos"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:column_pos} parser.
   * Used to specify a column location, represented as 3 integers. May use
   * relative locations with {@code ~}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType columnPos(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("column_pos"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:vec3} parser.
   * Used to specify a location, represented as 3 numbers (which may have
   * a decimal point, but will be moved to the center of a block if none is
   * specified). May use relative locations with {@code ~}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType vec3(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("vec3"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:vec2} parser.
   * Used to specify a location, represented as 2 numbers (which may have
   * a decimal point, but will be moved to the center of a block if none is
   * specified). May use relative locations with {@code ~}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType vec2(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("vec2"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:block_state} parser.
   * Used to specify a block state, optionally including NBT and state information.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType blockState(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("block_state"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:block_predicate} parser.
   * Used to specify a block, or a block tag.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType blockPredicate(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("block_predicate"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:item_stack} parser.
   * Used to specify an item, optionally including NBT.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType itemStack(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("item_stack"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:item_predicate} parser.
   * Used to specify an item, or an item tag.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType itemPredicate(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("item_predicate"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:color} parser.
   * Used to specify a <a href="https://wiki.vg/Chat#Colors">chat color</a>. Case-insensitive.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType color(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("color"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:component} parser.
   * Used to specify a JSON <a href="https://wiki.vg/Chat">Chat component</a>.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType component(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("component"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:message} parser.
   * Used to specify a regular message, potentially including selectors.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType message(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("message"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:nbt} parser.
   * Used to specify an NBT value, parsed using JSON-NBT rules.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType nbt(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("nbt"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:nbt_tag} parser.
   * Used to specify a partial NBT tag, usable in the {@code /data modify} command.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType nbtTag(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("nbt_tag"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:nbt_path} parser.
   * Used to specify a path within an NBT value, allowing for array and
   * member accesses.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType nbtPath(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("nbt_path"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:objective} parser.
   * Used to specify a scoreboard objective.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType objective(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("objective"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:objective_criteria} parser.
   * Used to specify a single score criterion.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType objectiveCriteria(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("objective_criteria"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:operation} parser.
   * Used to specify a scoreboard operator.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType operation(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("operation"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:particle} parser.
   * Used to specify a particle effect (an identifier with extra information
   * following it for specific particles, mirroring the <a href="https://wiki.vg/Command_Data#Particle">Particle packet</a>.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType particle(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("particle"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:angle} parser.
   * Used to specify an angle in degrees.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType angle(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("angle"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:rotation} parser.
   * Used to specify an angle, represented as 2 numbers (which may have
   * a decimal point). May use relative locations with {@code ~}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType rotation(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("rotation"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:scoreboard_slot} parser.
   * Used to specify a scoreboard display position slot.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType scoreboardSlot(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("scoreboard_slot"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:score_holder} parser.
   * Used to specify an object that can join a team. Allows selectors and {@code *}.
   *
   * @param manager the command manager.
   * @param multiple if set, allows multiple.
   * @return the argument type.
   */
  public static OpaqueArgumentType scoreHolder(final CommandManager manager,
                                               final boolean multiple) {
    return manager.opaqueArgumentTypeBuilder(Key.key("score_holder"))
        .withProperties(encodeBoolean(multiple))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:swizzle} parser.
   * Used to specify a collection of up to 3 axes.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType swizzle(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("swizzle"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:team} parser.
   * Used to specify the name of a team. Parsed as an unquoted string.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType team(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("team"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:item_slot} parser.
   * Used to specify a name for an inventory slot.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType itemSlot(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("item_slot"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:resource_location} parser.
   * Used to specify an identifier.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType resourceLocation(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("resource_location"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:mob_effect} parser.
   * Used to specify a potion effect.
   *
   * <p>Removed in {@link ProtocolVersion#MINECRAFT_1_19_3}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType mobEffect(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("mob_effect"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:function} parser.
   * Used to specify a function.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType function(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("function"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:entity_anchor} parser.
   * Used to specify the entity anchor related to the facing argument in the
   * teleport command, either {@code feet} or {@code eyes}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType entityAnchor(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("entity_anchor"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:range} parser.
   * Used to specify a range of values within a minimum and a maximum
   * quantity.
   *
   * <p>Removed in {@link ProtocolVersion#MINECRAFT_1_19}.
   *
   * @param manager the command manager.
   * @param decimals whether decimal values are allowed.
   * @return the argument type.
   */
  public static OpaqueArgumentType range(final CommandManager manager,
                                         final boolean decimals) {
    return manager.opaqueArgumentTypeBuilder(Key.key("range"))
        .withProperties(encodeBoolean(decimals))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:int_range} parser.
   * Used to specify an integer range of values within a minimum and a
   * maximum quantity.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType intRange(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("int_range"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:float_range} parser.
   * Used to specify a floating-point range of values within a minimum and
   * a maximum quantity.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType floatRange(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("float_range"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:item_enchantment} parser.
   * Used to specify an item enchantment.
   *
   * <p>Removed in {@link ProtocolVersion#MINECRAFT_1_19_3}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType itemEnchantment(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("item_enchantment"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:entity_summon} parser.
   * Used to specify an entity summon.
   *
   * <p>Removed in {@link ProtocolVersion#MINECRAFT_1_19_3}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType entitySummon(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("entity_summon"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:dimension} parser.
   * Used to specify a dimension.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType dimension(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("dimension"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:gamemode} parser.
   * Used to specify a gamemode.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType gameMode(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("gamemode"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:nbt_compound_tag} parser.
   * Used to specify a full NBT tag.
   *
   * <p>Removed in {@link ProtocolVersion#MINECRAFT_1_19}.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType nbtCompoundTag(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("nbt_compound_tag"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:time} parser.
   * Used to specify a time duration
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType time(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("time"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:resource_or_tag} parser.
   * Used to specify an identifier or a tag name for a registry.
   *
   * @param manager the command manager.
   * @param registry the registry from which the suggestions are sourced from.
   * @return the argument type.
   */
  public static OpaqueArgumentType resourceOrTag(final CommandManager manager,
                                                 final Key registry) {
    return manager.opaqueArgumentTypeBuilder(Key.key("resource_or_tag"))
        .withProperties(encodeIdentifier(registry))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:resource_or_tag_key} parser.
   * Used to specify an identifier or a tag name for a registry.
   *
   * @param manager the command manager.
   * @param registry the registry from which the suggestions are sourced from.
   * @return the argument type.
   */
  public static OpaqueArgumentType resourceOrTagKey(final CommandManager manager,
                                                    final Key registry) {
    return manager.opaqueArgumentTypeBuilder(Key.key("resource_or_tag_key"))
        .withProperties(encodeIdentifier(registry))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:resource} parser.
   * Used to specify an identifier for a registry.
   *
   * @param manager the command manager.
   * @param registry the registry from which the suggestions are sourced from.
   * @return the argument type.
   */
  public static OpaqueArgumentType resource(final CommandManager manager,
                                            final Key registry) {
    return manager.opaqueArgumentTypeBuilder(Key.key("resource"))
        .withProperties(encodeIdentifier(registry))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:resource_key} parser.
   * Used to specify an identifier for a registry.
   *
   * @param manager the command manager.
   * @param registry the registry from which the suggestions are sourced from.
   * @return the argument type.
   */
  public static OpaqueArgumentType resourceKey(final CommandManager manager,
                                               final Key registry) {
    return manager.opaqueArgumentTypeBuilder(Key.key("resource_key"))
        .withProperties(encodeIdentifier(registry))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:template_mirror} parser.
   * Used to specify a template mirror.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType templateMirror(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("template_mirror"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:template_rotation} parser.
   * Used to specify a template rotation.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType templateRotation(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("template_rotation"))
        .build();
  }

  /**
   * Returns an argument type for the {@code minecraft:uuid} parser.
   * Used to specify a UUID value.
   *
   * @param manager the command manager.
   * @return the argument type.
   */
  public static OpaqueArgumentType uuid(final CommandManager manager) {
    return manager.opaqueArgumentTypeBuilder(Key.key("uuid"))
        .build();
  }

  // todo: Forge parsers?
  // todo: Crossstitch parser?

  private OpaqueArgumentTypes() {
    throw new AssertionError();
  }
}
