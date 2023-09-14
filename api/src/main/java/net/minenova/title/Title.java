package net.minenova.title;

import net.minenova.title.adapt.AdaptManager;
import net.minenova.title.adapt.ServerAdaptModel;
import net.minenova.title.builder.ActionBarBuilder;
import net.minenova.title.builder.TitleBuilder;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface Title {
  /**
   * Indicates the running version for this server instance.
   */
  byte SERVER_VERSION = serverRelease();
  
  /**
   * Returns a new implementation for the {@link AdaptManager} type.
   *
   * @return A type {@link AdaptManager} reference.
   */
  @Contract(value = " -> new", pure = true)
  static @NotNull AdaptManager newAdaptManager() {
    return new AdaptManager();
  }
  
  /**
   * Returns a new implementation for the {@link TabManager} type.
   *
   * @param serverAdaptModel A type {@link ServerAdaptModel} reference.
   *
   * @return A type {@link TabManager} reference.
   */
  @Contract(value = "_ -> new", pure = true)
  static @NotNull TabManager newTabManager(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new TabManager(serverAdaptModel);
  }
  
  /**
   * Returns a new instance of {@link TitleBuilder} type.
   *
   * @param serverAdaptModel A type {@link ServerAdaptModel} reference.
   *
   * @return A {@link TitleBuilder} reference.
   */
  @Contract(value = "_ -> new", pure = true)
  static @NotNull TitleBuilder buildTitle(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new TitleBuilder(serverAdaptModel);
  }
  
  /**
   * Returns a new instance of {@link ActionBarBuilder} type.
   *
   * @param serverAdaptModel A type {@link ServerAdaptModel} reference.
   *
   * @return A type {@link ActionBarBuilder} reference.
   */
  static @NotNull ActionBarBuilder buildActionBar(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new ActionBarBuilder(serverAdaptModel);
  }
  
  /**
   * Returns a new instance of {@link ActionBarManager} type.
   *
   * @param serverAdaptModel A type {@link ServerAdaptModel} reference.
   *
   * @return A type {@link ActionBarManager} reference.
   */
  @Contract(value = "_ -> new", pure = true)
  static @NotNull ActionBarManager newActionBar(final @NotNull ServerAdaptModel serverAdaptModel) {
    return new ActionBarManager(serverAdaptModel);
  }
  
  /**
   * Returns the current server version.
   *
   * @return The server version on use.
   */
  static byte serverRelease() {
    String version = Bukkit.getVersion();
    byte index = (byte) version.lastIndexOf("MC:");
    // Checks if the 'index' value isn't -1.
    // Else, check if the 'version' ends with 'SNAPSHOT'.
    if (index != -1) {
      version = version.substring(index + 4, version.length() - 1);
    } else if (version.endsWith("SNAPSHOT")) {
      index = (byte) version.indexOf('-');
      version = version.substring(0, index);
    }
    final byte lastDot = (byte) version.lastIndexOf('.');
    // Checks if the index of the 'version' value are distinct of 'lastDot' value.
    if (version.indexOf('.') != lastDot) {
      version = version.substring(0, lastDot);
    }
    return Byte.parseByte(version.substring(2));
  }
}
