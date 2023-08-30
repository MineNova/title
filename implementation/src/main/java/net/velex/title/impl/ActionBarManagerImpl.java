package net.velex.title.impl;

import com.google.common.base.Preconditions;
import net.velex.title.api.ActionBarManagerModel;
import net.velex.title.api.ServerAdaptModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public class ActionBarManagerImpl implements ActionBarManagerModel {
  private final ServerAdaptModel serverAdaptModel;
  
  public ActionBarManagerImpl(final @NotNull ServerAdaptModel serverAdaptModel) {
    this.serverAdaptModel = Preconditions.checkNotNull(serverAdaptModel, "ServerAdaptModel reference cannot be null.");
  }
  
  @Override
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
        }
        serverAdaptModel.showActionBar(player, content);
      }
    }.runTaskTimerAsynchronously(plugin, 0L, 40L);
  }
  
  @Override
  public void showActionBarLater(final @NotNull JavaPlugin plugin, final @NotNull Player player, final @NotNull String content, final short seconds) {
    Bukkit.getScheduler().runTaskLater(plugin, () -> serverAdaptModel.showActionBar(player, content), seconds * 20);
  }
}
