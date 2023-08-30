package net.velex.title.impl;

import com.google.common.base.Preconditions;
import net.velex.title.api.AdaptManagerModel;
import net.velex.title.api.ServerAdaptModel;
import net.velex.title.api.enums.Result;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class SimpleAdaptManagerImpl implements AdaptManagerModel {
  private ServerAdaptModel model;
  
  @Override
  public @NotNull Result use(final @NotNull ServerAdaptModel model) {
    // Checks if the ServerAdaptModel has already a reference.
    if (this.model != null) {
      return Result.ADAPT_ALREADY_INSTALLED;
    }
    this.model = Preconditions.checkNotNull(model, "ServerAdaptModel reference cannot be null.");
    return Result.SUCCESS;
  }
  
  @Override
  public @NotNull Result detect() {
    final String packageName = Bukkit.getServer().getClass().getPackage().getName();
    final String release = packageName.substring(packageName.lastIndexOf('.') + 1);
    
    try {
      Class<?> clazz = Class.forName("net.velex.title.version." + release + ".VersionServerAdaptImpl");
      // Checks if the class founded is an ServerAdaptModel object implementation.
      if (clazz.isAssignableFrom(ServerAdaptModel.class)) {
        model = (ServerAdaptModel) clazz.getConstructor().newInstance();
      } else {
        return Result.ADAPT_IMPL_NOT_ASSIGNABLE;
      }
    } catch (final ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
      exception.printStackTrace();
      return Result.ADAPT_INTERNAL_ERROR;
    }
    return Result.SUCCESS;
  }
  
  @Override
  public @NotNull ServerAdaptModel serverAdapt() {
    // Checks if the ServerAdaptModel object hasn't an established reference.
    if (model == null) {
      throw new IllegalStateException("Server adapt has not been established yet.");
    }
    return model;
  }
}
