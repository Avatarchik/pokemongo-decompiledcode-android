package com.upsight.android.analytics.internal.dispatcher.util;

import java.util.Map;

public class ByNameSelector<D>
  implements Selector<D>
{
  private Map<String, D> mData;
  private D mDefaultValue = null;
  
  public ByNameSelector(Map<String, D> paramMap)
  {
    this.mData = paramMap;
  }
  
  public ByNameSelector(Map<String, D> paramMap, D paramD)
  {
    this(paramMap);
  }
  
  public D select(String paramString)
  {
    Object localObject = this.mData.get(paramString);
    if (localObject != null) {}
    for (;;)
    {
      return (D)localObject;
      localObject = this.mDefaultValue;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/util/ByNameSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */