package net.minenova.title.adapt;

import net.minenova.title.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface ServerAdaptModel {
  /**
   * Sends a title with modifiable times.
   *
   * @param player player to who send the title.
   * @param title content for the title message.
   * @param subtitle content for the subtitle message.
   * @param fadeIn max seconds for title appears.
   * @param stay max seconds for title on-screen stay.
   * @param fadeOut max seconds for title disappears.
   */
  void showTitle(
    final @NotNull Player player,
    final @NotNull String title,
    final @NotNull String subtitle,
    final byte fadeIn,
    final byte stay,
    final byte fadeOut);
  
  /**
   * Sends a title to the player.
   *
   * @param player player to who send the title.
   * @param title content for the title.
   * @param subtitle content for the subtitle.
   */
  void showTitle(final @NotNull Player player, final @NotNull String title, final @NotNull String subtitle);
  
  /**
   * Modifies the tab-list header and footer, and return a {@link Result} enum type.
   *
   * @param player player to who show this modification.
   * @param header content for the header.
   * @param footer content for the footer.
   *
   * @return If the packet was sent correctly, return the {@link Result#SUCCESS} type.
   * <p>Else, just return the {@link Result#PACKET_WRITE_ERROR} type.
   */
  @NotNull Result setHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer);
  
  /**
   * Modifies the tab-list header and return a {@link Result} enum type.
   *
   * @param player player to who show this.
   * @param header content for the frame.
   *
   * @return If the packet was sent correctly, return the {@link Result#SUCCESS} type.
   * <p>Else, just return the {@link Result#PACKET_WRITE_ERROR} type.
   */
  @NotNull Result setHeader(final @NotNull Player player, final @NotNull String header);
  
  /**
   * Modifies the tab-list footer and return a {@link Result} enum type.
   *
   * @param player player to who show this modification.
   * @param footer content for the frame.
   *
   * @return If the packet was sent correctly, return the {@link Result#SUCCESS} type.
   * <p>Else, just return the {@link Result#PACKET_WRITE_ERROR} type.
   */
  @NotNull Result setFooter(final @NotNull Player player, final @NotNull String footer);
  
  /**
   * Send an actionbar to the player.
   *
   * @param player {@code Player} who send the actionbar.
   * @param message Message to send.
   */
  void showActionBar(final @NotNull Player player, final @NotNull String message);
  
  /**
   * Modifies the display-name of the player in the tab, and return a {@link Result} enum type.
   *
   * @param player player to who change the display-name.
   * @param content the new display-name.
   *
   * @return If the packet was sent correctly, return the {@link Result#SUCCESS} type.
   * <p>Else, just return the {@link Result#PACKET_WRITE_ERROR} type.
   */
  @NotNull Result setNameList(final @NotNull Player player, final @NotNull String content);
}
