package dev.kaldiroglu.dp.structural.business.proxy.solution;

/**
 * Protection proxy. Sits OUTSIDE the cache so that an unauthorized request is
 * never served from a cached entry — every caller is re-checked, even if the
 * profile is hot in cache.
 */
public class AuthorizationProxy implements CustomerProfileService {

    private final CustomerProfileService delegate;
    private final AccessPolicy policy;

    public AuthorizationProxy(CustomerProfileService delegate, AccessPolicy policy) {
        this.delegate = delegate;
        this.policy = policy;
    }

    @Override
    public CustomerProfile getProfile(Caller caller, String customerId) {
        if (!policy.canRead(caller, customerId)) {
            throw new SecurityException("denied: " + caller.userId() + " -> " + customerId);
        }
        return delegate.getProfile(caller, customerId);
    }
}
