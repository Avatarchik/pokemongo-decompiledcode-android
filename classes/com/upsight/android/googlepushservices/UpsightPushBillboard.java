package com.upsight.android.googlepushservices;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightGooglePushServicesExtension;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.marketing.UpsightBillboard;
import com.upsight.android.marketing.UpsightBillboard.Handler;

public class UpsightPushBillboard
{
  public static UpsightBillboard create(UpsightContext paramUpsightContext, UpsightBillboard.Handler paramHandler)
    throws IllegalArgumentException, IllegalStateException
  {
    UpsightGooglePushServicesExtension localUpsightGooglePushServicesExtension = (UpsightGooglePushServicesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.googlepushservices");
    if (localUpsightGooglePushServicesExtension != null) {
      localUpsightGooglePushServicesExtension.getApi().createPushBillboard(paramUpsightContext, paramHandler);
    }
    for (;;)
    {
      return new NoOpBillboard(null);
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.googlepushservices must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  private static class NoOpBillboard
    extends UpsightBillboard
  {
    public void destroy() {}
    
    protected UpsightBillboard setUp(UpsightContext paramUpsightContext)
      throws IllegalStateException
    {
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/UpsightPushBillboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */