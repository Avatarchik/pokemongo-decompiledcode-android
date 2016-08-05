package crittercism.android;

import java.util.List;
import java.util.Map;

public abstract class n
{
  Map a;
  
  public n(Map paramMap)
  {
    this.a = paramMap;
  }
  
  private String c(String paramString)
  {
    List localList = (List)this.a.get(paramString);
    if (localList != null) {}
    for (String str = (String)localList.get(-1 + localList.size());; str = null) {
      return str;
    }
  }
  
  public final long a(String paramString)
  {
    long l1 = Long.MAX_VALUE;
    String str = c(paramString);
    if (str != null) {}
    try
    {
      long l2 = Long.parseLong(str);
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    return l1;
  }
  
  public final int b(String paramString)
  {
    int i = -1;
    String str = c(paramString);
    if (str != null) {}
    try
    {
      int j = Integer.parseInt(str);
      i = j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    return i;
  }
  
  public final String toString()
  {
    return this.a.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */