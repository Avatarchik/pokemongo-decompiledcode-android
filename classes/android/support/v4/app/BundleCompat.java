package android.support.v4.app;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;

public class BundleCompat
{
  public static IBinder getBinder(Bundle paramBundle, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {}
    for (IBinder localIBinder = BundleCompatJellybeanMR2.getBinder(paramBundle, paramString);; localIBinder = BundleCompatDonut.getBinder(paramBundle, paramString)) {
      return localIBinder;
    }
  }
  
  public static void putBinder(Bundle paramBundle, String paramString, IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      BundleCompatJellybeanMR2.putBinder(paramBundle, paramString, paramIBinder);
    }
    for (;;)
    {
      return;
      BundleCompatDonut.putBinder(paramBundle, paramString, paramIBinder);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/app/BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */