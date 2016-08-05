package com.upsight.android.managedvariables.type;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightManagedVariablesExtension;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.UpsightManagedVariablesApi;
import com.upsight.android.persistence.UpsightSubscription;

public abstract class UpsightManagedBoolean
  extends UpsightManagedVariable<Boolean>
{
  protected UpsightManagedBoolean(String paramString, Boolean paramBoolean1, Boolean paramBoolean2)
  {
    super(paramString, paramBoolean1, paramBoolean2);
  }
  
  public static UpsightManagedBoolean fetch(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightManagedVariablesExtension localUpsightManagedVariablesExtension = (UpsightManagedVariablesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.managedvariables");
    if (localUpsightManagedVariablesExtension != null) {}
    for (UpsightManagedBoolean localUpsightManagedBoolean = (UpsightManagedBoolean)localUpsightManagedVariablesExtension.getApi().fetch(UpsightManagedBoolean.class, paramString);; localUpsightManagedBoolean = null)
    {
      return localUpsightManagedBoolean;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.managedvariables must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static UpsightSubscription fetch(UpsightContext paramUpsightContext, String paramString, UpsightManagedVariable.Listener<UpsightManagedBoolean> paramListener)
  {
    UpsightManagedVariablesExtension localUpsightManagedVariablesExtension = (UpsightManagedVariablesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.managedvariables");
    if (localUpsightManagedVariablesExtension != null) {}
    for (Object localObject = localUpsightManagedVariablesExtension.getApi().fetch(UpsightManagedBoolean.class, paramString, paramListener);; localObject = new UpsightManagedVariable.NoOpSubscription())
    {
      return (UpsightSubscription)localObject;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.managedvariables must be registered in your Android Manifest", new Object[0]);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/type/UpsightManagedBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */