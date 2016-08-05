package com.upsight.android.analytics.internal.dispatcher.schema;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SidProvider
  extends UpsightDataProvider
{
  public static final String SID_KEY = "sid";
  
  SidProvider(UpsightContext paramUpsightContext)
  {
    put("sid", paramUpsightContext.getSid());
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "sid";
    return new HashSet(Arrays.asList(arrayOfString));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/SidProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */