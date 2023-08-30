package net.velex.title.api;

import net.velex.title.api.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ServerAdaptModel {
  /**
   * Sends a title with modifiable times.
   *
   * @param player {@code Player} who send the title.
   * @param title Title content message.
   * @param subtitle Subtitle content message.
   * @param fadeIn Appear time.
   * @param stay Stay time.
   * @param fadeOut Out time.
   */
  void showTitle(
    final @NotNull Player player,
    final @NotNull String title,
    final @NotNull String subtitle,
    final int fadeIn,
    final int stay,
    final int fadeOut
  );
  
  /**
   * Sends a title to the player.
   *
   * @param player {@code Player} who send the title.
   * @param title Title frame content.
   * @param subtitle Subtitle frame content.
   */
  void showTitle(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle);
  
  /**
   * Modifies the tablist header and footer.
   *
   * @param player {@code Player} who modify the header/footer.
   * @param header Content for the header.
   * @param footer Content for the footer.
   */
  @NotNull Result setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer);
  
  @NotNull Result setHeader(final @NotNull Player player, final @NotNull String header);
  
  @NotNull Result setFooter(final @NotNull Player player, final @NotNull String footer);
  
  /**
   * Send an actionbar to the player.
   *
   * @param player {@code Player} who send the actionbar.
   * @param message Message to send.
   */
  void showActionBar(final @NotNull Player player, final @NotNull String message);
  
  @NotNull Result setNameList(final @NotNull Player player, final @NotNull String content);
}
