package net.minenova.title.builder;

import com.google.common.base.Preconditions;
import net.minenova.title.Title;
import net.minenova.title.adapt.ServerAdaptModel;
import net.minenova.title.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ActionBarBuilder {
  private final ServerAdaptModel serverAdaptModel;
  
  private String message;
  
  public ActionBarBuilder(final @NotNull ServerAdaptModel serverAdaptModel) {
    this.serverAdaptModel = Preconditions.checkNotNull(serverAdaptModel, "ServerAdaptModel implementation cannot be null.");
  }
  
  /**
   * Sets the message to send for the actionbar.
   *
   * @param message content for the message.
   *
   * @return the current reference.
   */
  public @NotNull ActionBarBuilder message(final @NotNull String message) {
    this.message = Preconditions.checkNotNull(message, "ActionBar message cannot be null.");
    return this;
  }
  
  /**
   * Tries to send the actionbar message to the given player, and return a {@link Result} enum type.
   *
   * @param player player who send the actionbar.
   *
   * @return If the actionbar was sent correctly, return the {@link Result#SUCCESS} type.
   * <p>If the
   */
  public @NotNull Result build(final @NotNull Player player) {
    // Checks if the server version is lower than 1.8.
    if (Title.SERVER_VERSION < 8) {
      return Result.NO_VERSION_SUPPORT;
    }
    // Checks if the message were established or not.
    if (message == null) {
      return Result.NO_ARGUMENT_SPECIFY;
    }
    serverAdaptModel.showActionBar(player, message);
    return Result.SUCCESS;
  }
}
