package outland.feature;

import java.util.Optional;

/**
 * Returns optional empty instead of an authorization.
 */
public class EmptyAuthorizationProvider implements AuthorizationProvider {

  @Override public Optional<Authorization> authorization(String group, String scope) {
    return Optional.empty();
  }
}
