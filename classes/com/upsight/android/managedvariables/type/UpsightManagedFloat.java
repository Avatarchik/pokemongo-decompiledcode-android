package com.upsight.android.managedvariables.type;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightManagedVariablesExtension;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.UpsightManagedVariablesApi;
import com.upsight.android.persistence.UpsightSubscription;

public abstract class UpsightManagedFloat
  extends UpsightManagedVariable<Float>
{
  protected UpsightManagedFloat(String paramString, Float paramFloat1, Float paramFloat2)
  {
    super(paramString, paramFloat1, paramFloat2);
  }
  
  public static UpsightManagedFloat fetch(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightManagedVariablesExtension localUpsightManagedVariablesExtension = (UpsightManagedVariablesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.managedvariables");
    if (localUpsightManagedVariablesExtension != null) {}
    for (UpsightManagedFloat localUpsightManagedFloat = (UpsightManagedFloat)localUpsightManagedVariablesExtension.getApi().fetch(UpsightManagedFloat.class, paramString);; localUpsightManagedFloat = null)
    {
      return localUpsightManagedFloat;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.managedvariables must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static UpsightSubscription fetch(UpsightContext paramUpsightContext, String paramString, UpsightManagedVariable.Listener<UpsightManagedFloat> paramListener)
  {
    UpsightManagedVariablesExtension localUpsightManagedVariablesExtension = (UpsightManagedVariablesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.managedvariables");
    if (localUpsightManagedVariablesExtension != null) {}
    for (Object localObject = localUpsightManagedVariablesExtension.getApi().fetch(UpsightManagedFloat.class, paramString, paramListener);; localObject = new UpsightManagedVariable.NoOpSubscription())
    {
      return (UpsightSubscription)localObject;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.managedvariables must be registered in your Android Manifest", new Object[0]);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/type/UpsightManagedFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */