package net.velex.title.impl;

import com.google.common.base.Preconditions;
import net.velex.title.api.ServerAdaptModel;
import net.velex.title.api.TablistManagerModel;
import net.velex.title.api.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TablistManagerModelImpl implements TablistManagerModel {
  private final ServerAdaptModel serverAdaptModel;
  
  public TablistManagerModelImpl(final @NotNull ServerAdaptModel serverAdaptModel) {
    this.serverAdaptModel = Preconditions.checkNotNull(serverAdaptModel, "ServerAdaptModel reference cannot be null.");
  }
  
  @Override
  public @NotNull Result showHeaderAndFooter(
    final @NotNull Player player,
    final @NotNull String header,
    final @NotNull String footer
  ) {
    return serverAdaptModel.setHeaderAndFooter(player, header, footer);
  }
  
  @Override
  public @NotNull Result showHeader(final @NotNull Player player, final @NotNull String header) {
    return serverAdaptModel.setHeader(player, header);
  }
  
  @Override
  public @NotNull Result showFooter(final @NotNull Player player, final @NotNull String footer) {
    return serverAdaptModel.setFooter(player, footer);
  }
}
