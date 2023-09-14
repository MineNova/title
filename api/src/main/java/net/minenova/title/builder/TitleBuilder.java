package net.minenova.title.builder;

import com.google.common.base.Preconditions;
import net.minenova.title.Title;
import net.minenova.title.adapt.ServerAdaptModel;
import net.minenova.title.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TitleBuilder {
  private final ServerAdaptModel serverAdaptModel;
  
  private String title;
  private String subtitle;
  private byte fadeIn;
  private byte stay;
  private byte fadeOut;
  
  public TitleBuilder(final @NotNull ServerAdaptModel serverAdaptModel) {
    this.serverAdaptModel = Preconditions.checkNotNull(serverAdaptModel, "ServerAdaptModel reference cannot be null.");
  }
  
  /**
   * Sets the title text for the title.
   *
   * @param title content for the title.
   *
   * @return the current reference.
   */
  public TitleBuilder title(final @NotNull String title) {
    this.title = Preconditions.checkNotNull(title, "Title content cannot be null in builder.");
    return this;
  }
  
  /**
   * Sets the subtitle text for the title.
   *
   * @param subtitle content for the subtitle.
   *
   * @return the current reference.
   */
  public TitleBuilder subtitle(final @NotNull String subtitle) {
    this.subtitle = Preconditions.checkNotNull(subtitle, "Subtitle content cannot be null in builder.");
    return this;
  }
  
  /**
   * Sets the time values for the title, if isn't set, just send the title with a default time.
   *
   * @param fadeIn in-coming title time.
   * @param stay staying title time.
   * @param fadeOut screen out title time.
   *
   * @return the current reference.
   */
  public TitleBuilder times(final byte fadeIn, final byte stay, final byte fadeOut) {
    this.fadeIn = fadeIn;
    this.stay = stay;
    this.fadeOut = fadeOut;
    return this;
  }
  
  /**
   * Tries to send the title to the given player, and return a {@link Result} enum type.
   *
   * @param player player who send the title.
   *
   * @return A {@link Result} enum type for this operation.
   */
  public @NotNull Result build(final @NotNull Player player) {
    // Checks if the server version is lower than 1.8.
    if (Title.SERVER_VERSION < 8) {
      return Result.NO_VERSION_SUPPORT;
    }
    // Checks if the title or the subtitle were established in the builder.
    if ((title == null) || (subtitle == null)) {
      return Result.NO_ARGUMENT_SPECIFY;
    }
    // Check if the time parameters were established for the title.
    if ((fadeIn <= 0) || (stay <= 0) || (fadeOut <= 0)) {
      serverAdaptModel.showTitle(player, title, subtitle);
    } else {
      serverAdaptModel.showTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }
    return Result.SUCCESS;
  }
}
