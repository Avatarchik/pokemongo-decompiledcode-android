package com.upsight.android.analytics.internal.dispatcher.schema;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.internal.util.NetworkHelper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DeviceBlockProvider
  extends UpsightDataProvider
{
  public static final String CARRIER_KEY = "device.carrier";
  public static final String CONNECTION_KEY = "device.connection";
  private static final String DEVICE_TYPE_PHONE = "phone";
  private static final String DEVICE_TYPE_TABLET = "tablet";
  public static final String HARDWARE_KEY = "device.hardware";
  public static final String JAILBROKEN_KEY = "device.jailbroken";
  private static final String KERNEL_BUILD_KEY_TEST = "test-keys";
  public static final String LIMITED_AD_TRACKING_KEY = "device.limit_ad_tracking";
  public static final String MANUFACTURER_KEY = "device.manufacturer";
  private static final String OS_ANDROID = "android";
  public static final String OS_KEY = "device.os";
  public static final String OS_VERSION_KEY = "device.os_version";
  private static final String SPACE = " ";
  public static final String TYPE_KEY = "device.type";
  
  DeviceBlockProvider(Context paramContext)
  {
    put("device.carrier", NetworkHelper.getNetworkOperatorName(paramContext));
    put("device.connection", NetworkHelper.getActiveNetworkType(paramContext));
    put("device.hardware", Build.MODEL);
    put("device.jailbroken", Boolean.valueOf(isRooted()));
    put("device.manufacturer", Build.MANUFACTURER);
    put("device.os", "android");
    put("device.os_version", Build.VERSION.RELEASE + " " + Build.VERSION.SDK_INT);
    put("device.type", getDeviceType(paramContext));
  }
  
  private String getDeviceType(Context paramContext)
  {
    String str = "phone";
    if ((0xF & paramContext.getResources().getConfiguration().screenLayout) >= 3) {
      str = "tablet";
    }
    return str;
  }
  
  private boolean isRooted()
  {
    String str = Build.TAGS;
    if ((str != null) && (str.contains("test-keys"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "device.os";
    arrayOfString[1] = "device.os_version";
    arrayOfString[2] = "device.type";
    arrayOfString[3] = "device.hardware";
    arrayOfString[4] = "device.manufacturer";
    arrayOfString[5] = "device.carrier";
    arrayOfString[6] = "device.connection";
    arrayOfString[7] = "device.jailbroken";
    return new HashSet(Arrays.asList(arrayOfString));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/DeviceBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */