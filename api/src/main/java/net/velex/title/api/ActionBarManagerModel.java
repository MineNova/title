package net.velex.title.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public interface ActionBarManagerModel {
  default void showActionBarToAllWhile(final @NotNull JavaPlugin plugin, final @NotNull String content, final @NotNull Callable<Boolean> condition) {
    Bukkit.getOnlinePlayers().forEach(player -> showActionBarWhile(plugin, player, content, condition));
  }

  default void showActionBarToAllLater(final @NotNull JavaPlugin plugin, final @NotNull String content, final short seconds) {
    Bukkit.getOnlinePlayers().forEach(player -> showActionBarLater(plugin, player, content, seconds));
  }
  
  /**
   * Shows the actionbar while the condition given is true.
   *
   * @param plugin A {@link JavaPlugin} reference.
   * @param player player who send the actionbar.
   * @param content content for the message.
   * @param condition condition for send the actionbar.
   */
  void showActionBarWhile(
    final @NotNull JavaPlugin plugin,
    final @NotNull Player player,
    final @NotNull String content,
    final @NotNull Callable<Boolean> condition
  );
  
  /**
   * Shows an actionbar after the time specified.
   *
   * @param plugin A {@link JavaPlugin} reference.
   * @param player player who send the actionbar.
   * @param content content for the message.
   * @param seconds seconds amount before send the actionbar.
   */
  void showActionBarLater(final @NotNull JavaPlugin plugin, final @NotNull Player player, final @NotNull String content, final short seconds);
}
