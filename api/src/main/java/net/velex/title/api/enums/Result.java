package net.velex.title.api.enums;

public enum Result {
  /**
   * Indicates that there was something to try to modify packet data.
   */
  PACKET_WRITE_ERROR,
  /**
   * Indicates that there was an error to install the adapt correspond to the server version.
   */
  ADAPT_INTERNAL_ERROR,
  /**
   * Indicates that the adapt to install is already installed for usage.
   */
  ADAPT_ALREADY_INSTALLED,
  /**
   * Indicates that the current implementation can't be assignable to the current {@link net.velex.title.api.ServerAdaptModel} instance.
   */
  ADAPT_IMPL_NOT_ASSIGNABLE,
  /**
   * Indicates that it operation is not supported for the current version.
   */
  NO_VERSION_SUPPORT,
  /**
   * Indicates that some builder argument weren't specified for the operation.
   */
  NO_ARGUMENT_SPECIFY,
  /**
   * Indicates that the header was established correctly for the target.
   */
  SHOULD_HEADER,
  /**
   * Indicates that the footer was established correctly.
   */
  SHOULD_FOOTER,
  /**
   * Indicates that the operation (send title, install adapt, etc) was completed successfully.
   */
  SUCCESS,
}
