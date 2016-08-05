package com.upsight.android.analytics.internal.dispatcher.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ByFilterSelector<D>
  implements Selector<D>
{
  private Map<String, D> mData;
  private D mDefaultValue = null;
  private Set<Filter> mFilters;
  
  public ByFilterSelector(Map<String, D> paramMap)
  {
    this.mData = paramMap;
    this.mFilters = new HashSet();
    Iterator localIterator = this.mData.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      this.mFilters.add(new Filter(str));
    }
  }
  
  public ByFilterSelector(Map<String, D> paramMap, D paramD)
  {
    this(paramMap);
  }
  
  private String getFilterFor(String paramString)
  {
    Filter localFilter = null;
    Iterator localIterator = this.mFilters.iterator();
    while (localIterator.hasNext()) {
      localFilter = ((Filter)localIterator.next()).getFilterIfBetter(paramString, localFilter);
    }
    if (localFilter == null) {}
    for (String str = null;; str = localFilter.getFilter()) {
      return str;
    }
  }
  
  public D select(String paramString)
  {
    String str = getFilterFor(paramString);
    if (str == null) {}
    for (Object localObject = this.mDefaultValue;; localObject = this.mData.get(str)) {
      return (D)localObject;
    }
  }
  
  static class Filter
  {
    private static final String SEPARATOR = ".";
    private final String mFilter;
    private final boolean mIsWildcard;
    private final String mMatcher;
    
    public Filter(String paramString)
    {
      this.mFilter = paramString;
      this.mIsWildcard = paramString.endsWith("*");
      this.mMatcher = paramString.replaceAll("\\*", "");
    }
    
    public String getFilter()
    {
      return this.mFilter;
    }
    
    public Filter getFilterIfBetter(String paramString, Filter paramFilter)
    {
      if ((this.mMatcher.equals(paramString)) || ((paramString.startsWith(this.mMatcher)) && ((this.mMatcher.endsWith(".")) || (this.mIsWildcard) || (this.mMatcher.isEmpty())) && ((paramFilter == null) || (paramFilter.getMatcher().length() < this.mMatcher.length())))) {
        paramFilter = this;
      }
      return paramFilter;
    }
    
    public String getMatcher()
    {
      return this.mMatcher;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/util/ByFilterSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */