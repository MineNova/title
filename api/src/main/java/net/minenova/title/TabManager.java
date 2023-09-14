package net.minenova.title;

import com.google.common.base.Preconditions;
import net.minenova.title.adapt.ServerAdaptModel;
import net.minenova.title.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TabManager {
  private final ServerAdaptModel serverAdaptModel;
  
  public TabManager(final @NotNull ServerAdaptModel serverAdaptModel) {
    this.serverAdaptModel = Preconditions.checkNotNull(serverAdaptModel, "ServerAdaptModel reference cannot be null.");
  }
  
  /**
   * Establish the values given as new tab-list header/footer for the player given, and return a type {@link Result}
   * <p>value from executed method.
   *
   * @param player player to who show this.
   * @param header text for the header.
   * @param footer text for the footer.
   *
   * @see ServerAdaptModel#setHeaderAndFooter(Player, String, String)
   * @return A {@link Result} value returned by the method internal execution.
   */
  public @NotNull Result showHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer) {
    return this.serverAdaptModel.setHeaderAndFooter(player, header, footer);
  }
  
  /**
   * Establish the text given as the new tab-list header for the player given, and return a type {@link Result}
   * <p>value from executed method.
   *
   * @param player player to who show this.
   * @param header text for the header.
   *
   * @see ServerAdaptModel#setHeader(Player, String)
   * @return A {@link Result} value returned by the method internal execution.
   */
  public @NotNull Result showHeader(final @NotNull Player player, final @NotNull String header) {
    return this.serverAdaptModel.setHeader(player, header);
  }
  
  /**
   * Establish the text given as the new footer for the player given, and return a type {@link Result} value
   * <p>from executed method.
   *
   * @param player player to who show this.
   * @param footer text for the footer.
   *
   * @see ServerAdaptModel#setFooter(Player, String)
   * @return A {@link Result} value returned by the method internal execution.
   */
  public @NotNull Result showFooter(final @NotNull Player player, final @NotNull String footer) {
    return this.serverAdaptModel.setFooter(player, footer);
  }
  
  /**
   * Establish a new list name for the player and return a type {@link Result} value from executed method.
   *
   * @param player player to who apply this change.
   * @param name new list-name for the player.
   *
   * @see ServerAdaptModel#setNameList(Player, String)
   * @return A {@link Result} value returned by the method internal execution.
   */
  public @NotNull Result changeListName(final @NotNull Player player, final @NotNull String name) {
    return this.serverAdaptModel.setNameList(player, name);
  }
}
