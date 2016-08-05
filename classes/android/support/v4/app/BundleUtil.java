package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.Arrays;

class BundleUtil
{
  public static Bundle[] getBundleArrayFromBundle(Bundle paramBundle, String paramString)
  {
    Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray(paramString);
    Bundle[] arrayOfBundle;
    if (((arrayOfParcelable instanceof Bundle[])) || (arrayOfParcelable == null)) {
      arrayOfBundle = (Bundle[])arrayOfParcelable;
    }
    for (;;)
    {
      return arrayOfBundle;
      arrayOfBundle = (Bundle[])Arrays.copyOf(arrayOfParcelable, arrayOfParcelable.length, Bundle[].class);
      paramBundle.putParcelableArray(paramString, arrayOfBundle);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/app/BundleUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */