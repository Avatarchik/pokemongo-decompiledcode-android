package com.upsight.android.marketing.internal.content;

import android.text.TextUtils;
import com.squareup.otto.Bus;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.marketing.UpsightMarketingContentStore;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class MarketingContentStoreImpl
  extends UpsightMarketingContentStore
  implements MarketingContentStore
{
  public static final long DEFAULT_TIME_TO_LIVE_MS = 600000L;
  private Bus mBus;
  private Clock mClock;
  private final Map<String, MarketingContent> mContentMap = new HashMap();
  private final Map<String, String> mParentEligibilityMap = new HashMap();
  private final Map<String, Set<String>> mScopeEligibilityMap = new HashMap();
  private final Map<String, Long> mTimestamps = new HashMap();
  
  public MarketingContentStoreImpl(Bus paramBus, Clock paramClock)
  {
    this.mBus = paramBus;
    this.mClock = paramClock;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public MarketingContent get(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 33	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:mTimestamps	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 53 2 0
    //   12: checkcast 55	java/lang/Long
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull +44 -> 61
    //   20: aload_0
    //   21: getfield 43	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:mClock	Lcom/upsight/android/analytics/internal/session/Clock;
    //   24: invokeinterface 61 1 0
    //   29: ldc2_w 9
    //   32: aload_3
    //   33: invokevirtual 64	java/lang/Long:longValue	()J
    //   36: ladd
    //   37: lcmp
    //   38: ifgt +23 -> 61
    //   41: aload_0
    //   42: getfield 35	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:mContentMap	Ljava/util/Map;
    //   45: aload_1
    //   46: invokeinterface 53 2 0
    //   51: checkcast 66	com/upsight/android/marketing/internal/content/MarketingContent
    //   54: astore 5
    //   56: aload_0
    //   57: monitorexit
    //   58: aload 5
    //   60: areturn
    //   61: aload_0
    //   62: aload_1
    //   63: invokevirtual 70	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:remove	(Ljava/lang/String;)Z
    //   66: pop
    //   67: aconst_null
    //   68: astore 5
    //   70: goto -14 -> 56
    //   73: astore_2
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_2
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	MarketingContentStoreImpl
    //   0	78	1	paramString	String
    //   73	4	2	localObject	Object
    //   15	18	3	localLong	Long
    //   54	15	5	localMarketingContent	MarketingContent
    // Exception table:
    //   from	to	target	type
    //   2	56	73	finally
    //   61	67	73	finally
  }
  
  /**
   * @deprecated
   */
  public Set<String> getIdsForScope(String paramString)
  {
    try
    {
      Object localObject2 = (Set)this.mScopeEligibilityMap.get(paramString);
      if (localObject2 == null) {
        localObject2 = new HashSet();
      }
      return (Set<String>)localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public boolean isContentReady(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 80	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:getIdsForScope	(Ljava/lang/String;)Ljava/util/Set;
    //   7: invokeinterface 84 1 0
    //   12: istore_3
    //   13: iload_3
    //   14: ifne +11 -> 25
    //   17: iconst_1
    //   18: istore 4
    //   20: aload_0
    //   21: monitorexit
    //   22: iload 4
    //   24: ireturn
    //   25: iconst_0
    //   26: istore 4
    //   28: goto -8 -> 20
    //   31: astore_2
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	this	MarketingContentStoreImpl
    //   0	36	1	paramString	String
    //   31	4	2	localObject	Object
    //   12	2	3	bool1	boolean
    //   18	9	4	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   2	13	31	finally
  }
  
  /**
   * @deprecated
   */
  public boolean presentScopedContent(String paramString, String[] paramArrayOfString)
  {
    for (;;)
    {
      MarketingContent localMarketingContent;
      int j;
      try
      {
        localMarketingContent = (MarketingContent)this.mContentMap.get(paramString);
        if ((localMarketingContent == null) || (paramArrayOfString == null) || (paramArrayOfString.length <= 0)) {
          break label150;
        }
        int i = paramArrayOfString.length;
        j = 0;
        if (j < i)
        {
          String str = paramArrayOfString[j];
          Set localSet = (Set)this.mScopeEligibilityMap.get(str);
          if (localSet != null)
          {
            localSet.add(paramString);
          }
          else
          {
            HashSet localHashSet = new HashSet();
            localHashSet.add(paramString);
            this.mScopeEligibilityMap.put(str, localHashSet);
          }
        }
      }
      finally {}
      localMarketingContent.markPresentable(new MarketingContent.ScopedAvailabilityEvent(paramString, paramArrayOfString), this.mBus);
      label150:
      for (boolean bool = true;; bool = false) {
        return bool;
      }
      j++;
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public boolean presentScopelessContent(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 35	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:mContentMap	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 53 2 0
    //   12: checkcast 66	com/upsight/android/marketing/internal/content/MarketingContent
    //   15: astore 4
    //   17: aload 4
    //   19: ifnull +48 -> 67
    //   22: aload_2
    //   23: invokestatic 110	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   26: ifne +41 -> 67
    //   29: aload_0
    //   30: getfield 39	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:mParentEligibilityMap	Ljava/util/Map;
    //   33: aload_2
    //   34: aload_1
    //   35: invokeinterface 94 3 0
    //   40: pop
    //   41: aload 4
    //   43: new 112	com/upsight/android/marketing/internal/content/MarketingContent$ScopelessAvailabilityEvent
    //   46: dup
    //   47: aload_1
    //   48: aload_2
    //   49: invokespecial 115	com/upsight/android/marketing/internal/content/MarketingContent$ScopelessAvailabilityEvent:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   52: aload_0
    //   53: getfield 41	com/upsight/android/marketing/internal/content/MarketingContentStoreImpl:mBus	Lcom/squareup/otto/Bus;
    //   56: invokevirtual 103	com/upsight/android/marketing/internal/content/MarketingContent:markPresentable	(Lcom/upsight/android/marketing/internal/content/MarketingContent$AvailabilityEvent;Lcom/squareup/otto/Bus;)V
    //   59: iconst_1
    //   60: istore 5
    //   62: aload_0
    //   63: monitorexit
    //   64: iload 5
    //   66: ireturn
    //   67: iconst_0
    //   68: istore 5
    //   70: goto -8 -> 62
    //   73: astore_3
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_3
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	MarketingContentStoreImpl
    //   0	78	1	paramString1	String
    //   0	78	2	paramString2	String
    //   73	4	3	localObject	Object
    //   15	27	4	localMarketingContent	MarketingContent
    //   60	9	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	59	73	finally
  }
  
  /**
   * @deprecated
   */
  public boolean put(String paramString, MarketingContent paramMarketingContent)
  {
    boolean bool = false;
    try
    {
      if ((!TextUtils.isEmpty(paramString)) && (paramMarketingContent != null))
      {
        this.mContentMap.put(paramString, paramMarketingContent);
        this.mTimestamps.put(paramString, Long.valueOf(this.mClock.currentTimeMillis()));
        bool = true;
      }
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean remove(String paramString)
  {
    boolean bool = false;
    for (;;)
    {
      try
      {
        if (TextUtils.isEmpty(paramString)) {
          break label213;
        }
        if (this.mContentMap.remove(paramString) != null)
        {
          bool = true;
          if (!bool) {
            break label213;
          }
          Iterator localIterator1 = this.mScopeEligibilityMap.keySet().iterator();
          if (!localIterator1.hasNext()) {
            break;
          }
          String str3 = (String)localIterator1.next();
          Set localSet = (Set)this.mScopeEligibilityMap.get(str3);
          if ((localSet == null) || (!localSet.contains(paramString))) {
            continue;
          }
          localIterator1.remove();
          continue;
        }
        bool = false;
      }
      finally {}
    }
    Iterator localIterator2 = this.mParentEligibilityMap.keySet().iterator();
    while (localIterator2.hasNext())
    {
      String str1 = (String)localIterator2.next();
      String str2 = (String)this.mParentEligibilityMap.get(str1);
      if ((paramString.equals(str1)) || (paramString.equals(str2))) {
        localIterator2.remove();
      }
    }
    this.mTimestamps.remove(paramString);
    label213:
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/MarketingContentStoreImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */