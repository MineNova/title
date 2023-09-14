package net.minenova.title.adapt.version.v1_16_R3;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_16_R3.*;
import net.minenova.title.adapt.ServerAdaptModel;
import net.minenova.title.enums.Result;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class VersionServerAdaptImpl implements ServerAdaptModel {
  private PlayerConnection connection;
  private PacketPlayOutPlayerListHeaderFooter packetPlayOutPlayerListHeaderFooter;
  
  @Override
  public void showTitle(
    final @NotNull Player player,
    final @NotNull String title,
    final @NotNull String subtitle,
    final byte fadeIn,
    final byte stay,
    final byte fadeOut
  ) {
    connection = ((CraftPlayer) player).getHandle().playerConnection;
    connection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0]));
    connection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subtitle)[0]));
  }
  
  @Override
  public void showTitle(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle) {
    connection = ((CraftPlayer) player).getHandle().playerConnection;
    connection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0]));
    connection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subtitle)[0]));
  }
  
  @Override
  public @NotNull Result setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer) {
    final PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
    packetPlayOutPlayerListHeaderFooter = new PacketPlayOutPlayerListHeaderFooter();
    connection = ((CraftPlayer) player).getHandle().playerConnection;
    packetDataSerializer.a(CraftChatMessage.fromString(footer)[0]);
    packetDataSerializer.a(CraftChatMessage.fromString(header)[0]);
    try {
      packetPlayOutPlayerListHeaderFooter.a(packetDataSerializer);
    } catch (final IOException exception) {
      exception.printStackTrace();
      return Result.PACKET_WRITE_ERROR;
    }
    connection.sendPacket(packetPlayOutPlayerListHeaderFooter);
    return Result.SUCCESS;
  }
  
  @Override
  public @NotNull Result setHeader(final @NotNull Player player, final @NotNull String header) {
    final PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
    packetPlayOutPlayerListHeaderFooter = new PacketPlayOutPlayerListHeaderFooter();
    connection = ((CraftPlayer) player).getHandle().playerConnection;
    packetDataSerializer.a(CraftChatMessage.fromString("")[0]);
    packetDataSerializer.a(CraftChatMessage.fromString(header)[0]);
    try {
      packetPlayOutPlayerListHeaderFooter.a(packetDataSerializer);
    } catch (final IOException exception) {
      exception.printStackTrace();
      return Result.PACKET_WRITE_ERROR;
    }
    connection.sendPacket(packetPlayOutPlayerListHeaderFooter);
    return Result.SUCCESS;
  }
  
  @Override
  public @NotNull Result setFooter(final @NotNull Player player, final @NotNull String footer) {
    final PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
    packetPlayOutPlayerListHeaderFooter = new PacketPlayOutPlayerListHeaderFooter();
    connection = ((CraftPlayer) player).getHandle().playerConnection;
    packetDataSerializer.a(CraftChatMessage.fromString(footer)[0]);
    packetDataSerializer.a(CraftChatMessage.fromString("")[0]);
    try {
      packetPlayOutPlayerListHeaderFooter.a(packetDataSerializer);
    } catch (final IOException exception) {
      exception.printStackTrace();
      return Result.PACKET_WRITE_ERROR;
    }
    connection.sendPacket(packetPlayOutPlayerListHeaderFooter);
    return Result.SHOULD_FOOTER;
  }
  
  @Override
  public void showActionBar(final @NotNull Player player, final @NotNull String message) {
    connection = ((CraftPlayer) player).getHandle().playerConnection;
    connection.sendPacket(new PacketPlayOutChat(CraftChatMessage.fromString(message)[0], ChatMessageType.GAME_INFO, player.getUniqueId()));
  }
  
  @Override
  public @NotNull Result setNameList(final @NotNull Player player, final @NotNull String content) {
    final PacketDataSerializer packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
    final PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo();
    packetDataSerializer.a(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME);
    packetDataSerializer.a(player.getUniqueId());
    packetDataSerializer.a(CraftChatMessage.fromString(content)[0]);
    try {
      packetPlayOutPlayerInfo.a(packetDataSerializer);
    } catch (final IOException exception) {
      exception.printStackTrace();
      return Result.PACKET_WRITE_ERROR;
    }
    for (Player another : Bukkit.getOnlinePlayers()) {
      ((CraftPlayer) another).getHandle().playerConnection.sendPacket(packetPlayOutPlayerInfo);
    }
    return Result.SUCCESS;
  }
}
