package com.upsight.android.managedvariables.type;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightManagedVariablesExtension;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.UpsightManagedVariablesApi;
import com.upsight.android.persistence.UpsightSubscription;

public abstract class UpsightManagedString
  extends UpsightManagedVariable<String>
{
  protected UpsightManagedString(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
  }
  
  public static UpsightManagedString fetch(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightManagedVariablesExtension localUpsightManagedVariablesExtension = (UpsightManagedVariablesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.managedvariables");
    if (localUpsightManagedVariablesExtension != null) {}
    for (UpsightManagedString localUpsightManagedString = (UpsightManagedString)localUpsightManagedVariablesExtension.getApi().fetch(UpsightManagedString.class, paramString);; localUpsightManagedString = null)
    {
      return localUpsightManagedString;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.managedvariables must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static UpsightSubscription fetch(UpsightContext paramUpsightContext, String paramString, UpsightManagedVariable.Listener<UpsightManagedString> paramListener)
  {
    UpsightManagedVariablesExtension localUpsightManagedVariablesExtension = (UpsightManagedVariablesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.managedvariables");
    if (localUpsightManagedVariablesExtension != null) {}
    for (Object localObject = localUpsightManagedVariablesExtension.getApi().fetch(UpsightManagedString.class, paramString, paramListener);; localObject = new UpsightManagedVariable.NoOpSubscription())
    {
      return (UpsightSubscription)localObject;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.managedvariables must be registered in your Android Manifest", new Object[0]);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/type/UpsightManagedString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */