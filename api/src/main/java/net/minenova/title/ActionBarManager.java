package net.minenova.title;

import com.google.common.base.Preconditions;
import net.minenova.title.adapt.ServerAdaptModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public class ActionBarManager {
  private final ServerAdaptModel serverAdaptModel;
  
  public ActionBarManager(final @NotNull ServerAdaptModel serverAdaptModel) {
    this.serverAdaptModel = Preconditions.checkNotNull(serverAdaptModel, "ServerAdaptModel reference cannot be null.");
  }
  
  /**
   * Shows an action-bar while the condition given be true.
   *
   * @param plugin a type {@link JavaPlugin} instance.
   * @param player player to who show the action-bar.
   * @param content text for the action-bar.
   * @param condition value to check for keep action-bar showed.
   */
  public void showActionBarWhile(
    final @NotNull JavaPlugin plugin,
    final @NotNull Player player,
    final @NotNull String content,
    final @NotNull Callable<Boolean> condition
  ) {
    new BukkitRunnable() {
      @Override
      public void run() {
        try {
          // Checks if the current callback value is 'false'.
          if (!condition.call()) {
            cancel();
            return;
          }
        } catch (final Exception exception) {
          exception.printStackTrace();
          cancel();
          return;
        }
        serverAdaptModel.showActionBar(player, content);
      }
    }.runTaskTimerAsynchronously(plugin, 0L, 40L);
  }
  
  /**
   * Shows the action-bar to player later of 'x' seconds.
   *
   * @param plugin a type {@link JavaPlugin} instance.
   * @param player player to who show the action-bar.
   * @param content text for the action-bar.
   * @param seconds seconds amount to wait before send the action-bar.
   */
  public void showActionBarLater(
    final @NotNull JavaPlugin plugin,
    final @NotNull Player player,
    final @NotNull String content,
    final short seconds
  ) {
    Bukkit.getScheduler().runTaskLater(plugin, () -> this.serverAdaptModel.showActionBar(player, content), seconds * 20);
  }
}
