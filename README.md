# opaque-argument-types

[![artifact][artifact]][artifact-url]
[![javadoc][javadoc]][javadoc-url]
[![action][action]][action-url]
[![license][license]][license-url]

A Velocity library that provides [opaque argument types](https://jd.papermc.io/velocity/3.0.0/com/velocitypowered/api/command/OpaqueArgumentType.html)
corresponding to the argument parsers known by the vanilla client in different
[protocol versions](https://jd.papermc.io/velocity/3.0.0/com/velocitypowered/api/network/ProtocolVersion.html)
of the game.

Most of the javadoc contents are taken verbatim from the [wiki.vg Command Data](https://wiki.vg/Command_Data#Parsers)
page, licensed under the [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) license.

## Installation

Requires Java 11 or later.

### Gradle

```groovy
repositories {
  mavenCentral()
}

dependencies {
  compile 'me.hugmanrique:opaque-argument-types:1.0.0-SNAPSHOT'
}
```

### Maven

```xml
<dependency>
  <groupId>me.hugmanrique</groupId>
  <artifactId>opaque-argument-types</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Usage

The [`OpaqueArgumentTypes`](https://javadoc.io/doc/me.hugmanrique/opaque-argument-types/latest/me/hugmanrique/opaqueargumenttypes/OpaqueArgumentTypes.html)
class provides factory methods to construct [`OpaqueArgumentType`](https://jd.papermc.io/velocity/3.0.0/com/velocitypowered/api/command/OpaqueArgumentType.html)
instances for all the parser types known by the vanilla client. Some argument types accept properties,
which this library can serialize for all the Minecraft versions supported by Velocity.

The following example constructs a simplified version of the `/give` command:

```java
final CommandManager commandManager = ...;
final OpaqueArgumentType itemType = OpaqueArgumentTypes.itemStack(commandManager);
final LiteralCommandNode<CommandSource> literal = LiteralArgumentBuilder
    .<CommandSource>literal("give")
    .then(RequiredArgumentBuilder.argument("item", itemType))
    .build();

commandManager.register(new BrigadierCommand(literal));
```

The client now provides appropriate suggestions for the partial inputs `/give ` or `/give minecraft:`.
Recall that Velocity automatically forwards any execution that contains an arguments node with
an opaque type to the backend server. This means that any [`Command`](https://github.com/Mojang/brigadier/blob/master/src/main/java/com/mojang/brigadier/Command.java)
or [`SuggestionProvider`](https://github.com/Mojang/brigadier/blob/master/src/main/java/com/mojang/brigadier/suggestion/SuggestionProvider.java)
on that node are ignored.

Using a static import to call the methods in the [`OpaqueArgumentTypes`](https://javadoc.io/doc/me.hugmanrique/opaque-argument-types/latest/me/hugmanrique/opaqueargumenttypes/OpaqueArgumentTypes.html)
class is encouraged (this follows the convention used in [Brigadier](https://github.com/Mojang/brigadier)).

## License

[MIT](LICENSE) &copy; [Hugo Manrique](https://hugmanrique.me).

[artifact]: https://img.shields.io/maven-central/v/me.hugmanrique/opaque-argument-types
[artifact-url]: https://search.maven.org/artifact/me.hugmanrique/opaque-argument-types
[javadoc]: https://javadoc.io/badge2/me.hugmanrique/opaque-argument-types/javadoc.svg
[javadoc-url]: https://javadoc.io/doc/me.hugmanrique/opaque-argument-types
[action]: https://github.com/hugmanrique/opaque-argument-types/actions/workflows/build.yml/badge.svg
[action-url]: https://github.com/hugmanrique/opaque-argument-types/actions/workflows/build.yml
[license]: https://img.shields.io/github/license/hugmanrique/opaque-argument-types.svg
[license-url]: LICENSE
