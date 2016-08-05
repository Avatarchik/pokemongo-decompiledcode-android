package com.upsight.android.internal.util;

import android.util.Log;
import com.upsight.android.UpsightException;
import com.upsight.android.persistence.UpsightDataStoreListener;

public final class LoggingListener<T>
  implements UpsightDataStoreListener<T>
{
  public void onFailure(UpsightException paramUpsightException)
  {
    Log.e("Upsight", "Uncaught Exception within Upsight SDK.", paramUpsightException);
  }
  
  public void onSuccess(T paramT) {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/util/LoggingListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */