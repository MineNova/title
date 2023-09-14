package net.minenova.title.adapt;

import com.google.common.base.Preconditions;
import net.minenova.title.enums.Result;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class AdaptManager {
  private ServerAdaptModel serverAdaptModel;
  
  /**
   * Uses the {@link ServerAdaptModel} implementation given as main adapt for usage, and return
   * <p>a {@link Result} enum type.
   *
   * @param model A type {@link ServerAdaptModel} reference.
   *
   * @return If the adapt was established success, return the {@link Result#SUCCESS} type.
   * <p>Else, return the {@link Result#ADAPT_ALREADY_INSTALLED} type.
   */
  public @NotNull Result use(final @NotNull ServerAdaptModel model) {
    // Checks if the ServerAdaptModel has already a reference.
    if (this.serverAdaptModel != null) {
      return Result.ADAPT_ALREADY_INSTALLED;
    }
    this.serverAdaptModel = Preconditions.checkNotNull(model, "ServerAdaptModel reference cannot be null.");
    return Result.SUCCESS;
  }
  
  /**
   * Assigns the required adapt using the server version on use, and return a {@link Result} enum type.
   *
   * @return If the adapt was established successfully, return the {@link Result#SUCCESS} type.
   * <p>If the implementation can't be assigned, return the {@link Result#ADAPT_NOT_ASSIGNABLE} type.
   * <p>Else, return the {@link Result#ADAPT_INTERNAL_ERROR} type.
   */
  public @NotNull Result detectAndAssign() {
    final String packageName = Bukkit.getServer().getClass().getPackage().getName();
    final String release = packageName.substring(packageName.lastIndexOf('.') + 1);
    try {
      Class<?> clazz = Class.forName("net.minenova.title.adapt.version." + release + ".VersionServerAdaptImpl");
      // Checks if the class founded is an ServerAdaptModel object implementation.
      if (clazz.isAssignableFrom(ServerAdaptModel.class)) {
        this.serverAdaptModel = (ServerAdaptModel) clazz.getConstructor().newInstance();
      } else {
        return Result.ADAPT_NOT_ASSIGNABLE;
      }
    } catch (final ClassNotFoundException | NoSuchMethodException | InstantiationException
                   | IllegalAccessException | InvocationTargetException exception) {
      exception.printStackTrace();
      return Result.ADAPT_INTERNAL_ERROR;
    }
    return Result.SUCCESS;
  }
  
  /**
   * Returns the current {@link ServerAdaptModel} reference.
   *
   * @return A reference {@link ServerAdaptModel} type.
   */
  public @NotNull ServerAdaptModel adapt() {
    // Checks if the ServerAdaptModel object hasn't an established reference.
    if (this.serverAdaptModel == null) {
      throw new IllegalStateException("Server adapt has not been established yet.");
    }
    return this.serverAdaptModel;
  }
}
