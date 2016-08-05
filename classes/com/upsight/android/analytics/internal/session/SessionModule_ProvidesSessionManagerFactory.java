package com.upsight.android.analytics.internal.session;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class SessionModule_ProvidesSessionManagerFactory
  implements Factory<SessionManager>
{
  private final SessionModule module;
  private final Provider<SessionManagerImpl> sessionManagerProvider;
  
  static
  {
    if (!SessionModule_ProvidesSessionManagerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public SessionModule_ProvidesSessionManagerFactory(SessionModule paramSessionModule, Provider<SessionManagerImpl> paramProvider)
  {
    assert (paramSessionModule != null);
    this.module = paramSessionModule;
    assert (paramProvider != null);
    this.sessionManagerProvider = paramProvider;
  }
  
  public static Factory<SessionManager> create(SessionModule paramSessionModule, Provider<SessionManagerImpl> paramProvider)
  {
    return new SessionModule_ProvidesSessionManagerFactory(paramSessionModule, paramProvider);
  }
  
  public SessionManager get()
  {
    SessionManager localSessionManager = this.module.providesSessionManager((SessionManagerImpl)this.sessionManagerProvider.get());
    if (localSessionManager == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localSessionManager;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/SessionModule_ProvidesSessionManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */