package com.upsight.android.analytics.internal.dispatcher.schema;

import com.upsight.android.analytics.provider.UpsightDataProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractUxmBlockProvider
  extends UpsightDataProvider
{
  public static final String BUNDLE_HASH = "bundle.hash";
  public static final String BUNDLE_ID = "bundle.id";
  public static final String BUNDLE_SCHEMA_HASH = "bundle.schema_hash";
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "bundle.schema_hash";
    arrayOfString[1] = "bundle.id";
    arrayOfString[2] = "bundle.hash";
    return new HashSet(Arrays.asList(arrayOfString));
  }
  
  public abstract String getBundleHash();
  
  public abstract String getBundleId();
  
  public abstract String getBundleSchemaHash();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/AbstractUxmBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */