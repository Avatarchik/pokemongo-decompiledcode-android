package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache
  extends ConcurrentHashMap<String, String>
{
  private static final int MAX_ENTRIES = 180;
  public static final InternCache instance = new InternCache();
  private final Object lock = new Object();
  
  private InternCache()
  {
    super(180, 0.8F, 4);
  }
  
  public String intern(String paramString)
  {
    String str1 = (String)get(paramString);
    Object localObject1;
    if (str1 != null) {
      localObject1 = str1;
    }
    for (;;)
    {
      return (String)localObject1;
      if (size() >= 180) {}
      synchronized (this.lock)
      {
        if (size() >= 180) {
          clear();
        }
        String str2 = paramString.intern();
        put(str2, str2);
        localObject1 = str2;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/InternCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */