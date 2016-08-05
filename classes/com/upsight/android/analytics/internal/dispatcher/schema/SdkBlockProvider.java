package com.upsight.android.analytics.internal.dispatcher.schema;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SdkBlockProvider
  extends UpsightDataProvider
{
  public static final String BUILD_KEY = "sdk.build";
  public static final String PLUGIN_KEY = "sdk.plugin";
  public static final String VERSION_KEY = "sdk.version";
  
  SdkBlockProvider(UpsightContext paramUpsightContext)
  {
    put("sdk.version", paramUpsightContext.getSdkVersion());
    put("sdk.build", paramUpsightContext.getSdkBuild());
    put("sdk.plugin", paramUpsightContext.getSdkPlugin());
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "sdk.version";
    arrayOfString[1] = "sdk.build";
    arrayOfString[2] = "sdk.plugin";
    return new HashSet(Arrays.asList(arrayOfString));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/SdkBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */