package outland.feature;

/**
 * An exception representing a client (4xx) response code.
 */
public class ClientException extends FeatureException {

  /**
   * @param problem the Problem detail
   */
  public ClientException(Problem problem) {
    super(problem);
  }

  /**
   * @param problem the Problem detail
   * @param cause the cause
   */
  public ClientException(Problem problem, Throwable cause) {
    super(problem, cause);
  }
}
