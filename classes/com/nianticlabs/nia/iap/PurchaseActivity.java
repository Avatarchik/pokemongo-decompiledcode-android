package com.nianticlabs.nia.iap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.lang.ref.WeakReference;

public class PurchaseActivity
  extends Activity
{
  public static final int REQUEST_PURCHASE_RESULT = 10009;
  private static String TAG = "PurchaseActivity";
  private GoogleInAppBillingProvider billingProvider;
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      Log.e(TAG, "Unandled requestCode: " + paramInt1);
    }
    for (;;)
    {
      finish();
      return;
      this.billingProvider.forwardedOnActivityResult(paramInt2, paramIntent);
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.billingProvider = null;
    WeakReference localWeakReference = GoogleInAppBillingProvider.getInstance();
    if (localWeakReference != null) {
      this.billingProvider = ((GoogleInAppBillingProvider)localWeakReference.get());
    }
    if (this.billingProvider == null) {
      throw new RuntimeException("Unable to locate GoogleInAppBillingProvider");
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i != 0)
    {
      Log.e(TAG, "Google Play Services not available: " + i);
      finish();
    }
    for (;;)
    {
      return;
      this.billingProvider.startBuyIntent(this);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/PurchaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */