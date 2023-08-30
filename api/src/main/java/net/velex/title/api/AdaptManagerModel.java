package net.velex.title.api;

import net.velex.title.api.enums.Result;
import org.jetbrains.annotations.NotNull;

public interface AdaptManagerModel {
  /**
   * Just uses the adapt given for the lib components.
   *
   * @param model An {@link ServerAdaptModel} implementation for the server version on use.
   *
   * @return A {@link Result} enum type.
   */
  @NotNull Result use(final @NotNull ServerAdaptModel model);
  
  /**
   * Uses the current server version to decide what adapt type use.
   *
   * @return A {@link Result} enum type.
   */
  @NotNull Result detect();
  
  /**
   * Returns the reference for the current {@link ServerAdaptModel} object.
   *
   * @return A {@link ServerAdaptModel} reference.
   */
  @NotNull ServerAdaptModel serverAdapt();
}
