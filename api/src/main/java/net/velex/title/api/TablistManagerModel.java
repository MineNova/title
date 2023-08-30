package net.velex.title.api;

import net.velex.title.api.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface TablistManagerModel {
  /**
   * Shows a custom header and footer in the tablist for the player, and return a {@link Result} enum type for the operation.
   *
   * @param player {@code Player} who show the header and footer.
   * @param header Header frame content.
   * @param footer Footer frame content.
   *
   * @return A {@link Result} enum type for the operation final result.
   */
  @NotNull Result showHeaderAndFooter(final @NotNull Player player, final @NotNull String header, final @NotNull String footer);
  
  /**
   * Shows a custom header in the tablist for the player, and return a {@link Result} enum type for the operation.
   *
   * @param player {@code Player} who show the header.
   * @param header Header frame content.
   *
   * @return A {@link Result} enum type for the operation final result.
   */
  @NotNull Result showHeader(final @NotNull Player player, final @NotNull String header);
  
  /**
   * Shows a custom footer in the tablist for the player, and return a {@link Result} enum type for the operation.
   *
   * @param player {@code Player} who show the header.
   * @param footer Footer frame content.
   *
   * @return A {@link Result} enum type for the operation final result.
   */
  @NotNull Result showFooter(final @NotNull Player player, final @NotNull String footer);
}
