package net.velex.title.builder;

import net.velex.title.api.ServerAdaptModel;
import com.google.common.base.Preconditions;
import net.velex.title.Title;
import net.velex.title.api.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NameTagBuilder {
  private final ServerAdaptModel model;
  
  private String name;
  
  public NameTagBuilder(final @NotNull ServerAdaptModel model) {
    this.model = Preconditions.checkNotNull(model, "ServerAdaptModel reference cannot be null.");
  }
  
  /**
   * Sets the new name for the player.
   *
   * @param name name for the player.
   *
   * @return the current reference.
   */
  public @NotNull NameTagBuilder name(final @NotNull String name) {
    this.name = Preconditions.checkNotNull(name, "Name format cannot be null.");
    return this;
  }
  
  /**
   * Tries to modify the current display-name for the given player, and return {@link Result} enum type.
   *
   * @param player player who modify their name.
   *
   * @return A {@link Result} enum type.
   */
  public @NotNull Result build(final @NotNull Player player) {
    // Checks if the server version is lower than 1.8.
    if (Title.SERVER_VERSION < 8) {
      return Result.NO_VERSION_SUPPORT;
    }
    // Checks if the name for the name were established.
    if (name == null) {
      return Result.NO_ARGUMENT_SPECIFY;
    }
    return model.setNameList(player, name);
  }
}
