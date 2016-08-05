package com.upsight.android;

import com.upsight.android.managedvariables.UpsightManagedVariablesApi;
import com.upsight.android.managedvariables.UpsightManagedVariablesComponent;
import com.upsight.android.managedvariables.internal.type.UxmBlockProvider;
import com.upsight.android.managedvariables.internal.type.UxmContentFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpsightManagedVariablesExtension_MembersInjector
  implements MembersInjector<UpsightManagedVariablesExtension>
{
  private final Provider<UpsightManagedVariablesApi> mManagedVariablesProvider;
  private final Provider<UxmBlockProvider> mUxmBlockProvider;
  private final Provider<UxmContentFactory> mUxmContentFactoryProvider;
  private final MembersInjector<UpsightExtension<UpsightManagedVariablesComponent, UpsightManagedVariablesApi>> supertypeInjector;
  
  static
  {
    if (!UpsightManagedVariablesExtension_MembersInjector.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UpsightManagedVariablesExtension_MembersInjector(MembersInjector<UpsightExtension<UpsightManagedVariablesComponent, UpsightManagedVariablesApi>> paramMembersInjector, Provider<UpsightManagedVariablesApi> paramProvider, Provider<UxmContentFactory> paramProvider1, Provider<UxmBlockProvider> paramProvider2)
  {
    assert (paramMembersInjector != null);
    this.supertypeInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.mManagedVariablesProvider = paramProvider;
    assert (paramProvider1 != null);
    this.mUxmContentFactoryProvider = paramProvider1;
    assert (paramProvider2 != null);
    this.mUxmBlockProvider = paramProvider2;
  }
  
  public static MembersInjector<UpsightManagedVariablesExtension> create(MembersInjector<UpsightExtension<UpsightManagedVariablesComponent, UpsightManagedVariablesApi>> paramMembersInjector, Provider<UpsightManagedVariablesApi> paramProvider, Provider<UxmContentFactory> paramProvider1, Provider<UxmBlockProvider> paramProvider2)
  {
    return new UpsightManagedVariablesExtension_MembersInjector(paramMembersInjector, paramProvider, paramProvider1, paramProvider2);
  }
  
  public void injectMembers(UpsightManagedVariablesExtension paramUpsightManagedVariablesExtension)
  {
    if (paramUpsightManagedVariablesExtension == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    this.supertypeInjector.injectMembers(paramUpsightManagedVariablesExtension);
    paramUpsightManagedVariablesExtension.mManagedVariables = ((UpsightManagedVariablesApi)this.mManagedVariablesProvider.get());
    paramUpsightManagedVariablesExtension.mUxmContentFactory = ((UxmContentFactory)this.mUxmContentFactoryProvider.get());
    paramUpsightManagedVariablesExtension.mUxmBlockProvider = ((UxmBlockProvider)this.mUxmBlockProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightManagedVariablesExtension_MembersInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */