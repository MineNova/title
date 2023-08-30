package net.velex.title.builder;

import com.google.common.base.Preconditions;
import net.velex.title.Title;
import net.velex.title.api.ServerAdaptModel;
import net.velex.title.api.enums.Result;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TitleBuilder {
  private final ServerAdaptModel model;
  
  private String title;
  private String subtitle;
  private int fadeIn;
  private int stay;
  private int fadeOut;
  
  public TitleBuilder(final @NotNull ServerAdaptModel model) {
    this.model = Preconditions.checkNotNull(model, "ServerAdaptModel reference cannot be null.");
  }
  
  /**
   * Sets the title text for the title.
   *
   * @param title content for the title.
   *
   * @return the current reference.
   */
  public @NotNull TitleBuilder title(final @NotNull String title) {
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
  public @NotNull TitleBuilder subtitle(final @NotNull String subtitle) {
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
  public @NotNull TitleBuilder times(final int fadeIn, final int stay, final int fadeOut) {
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
      model.showTitle(player, title, subtitle);
    } else {
      model.showTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }
    return Result.SUCCESS;
  }
}
