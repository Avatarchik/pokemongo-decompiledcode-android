package org.apache.commons.io;

import java.io.Serializable;

public final class IOCase
  implements Serializable
{
  public static final IOCase INSENSITIVE;
  public static final IOCase SENSITIVE;
  public static final IOCase SYSTEM;
  private static final long serialVersionUID = -6343169151696340687L;
  private final String name;
  private final transient boolean sensitive;
  
  static
  {
    boolean bool = true;
    SENSITIVE = new IOCase("Sensitive", bool);
    INSENSITIVE = new IOCase("Insensitive", false);
    if (!FilenameUtils.isSystemWindows()) {}
    for (;;)
    {
      SYSTEM = new IOCase("System", bool);
      return;
      bool = false;
    }
  }
  
  private IOCase(String paramString, boolean paramBoolean)
  {
    this.name = paramString;
    this.sensitive = paramBoolean;
  }
  
  public static IOCase forName(String paramString)
  {
    IOCase localIOCase;
    if (SENSITIVE.name.equals(paramString)) {
      localIOCase = SENSITIVE;
    }
    for (;;)
    {
      return localIOCase;
      if (INSENSITIVE.name.equals(paramString))
      {
        localIOCase = INSENSITIVE;
      }
      else
      {
        if (!SYSTEM.name.equals(paramString)) {
          break;
        }
        localIOCase = SYSTEM;
      }
    }
    throw new IllegalArgumentException("Invalid IOCase name: " + paramString);
  }
  
  private Object readResolve()
  {
    return forName(this.name);
  }
  
  public int checkCompareTo(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new NullPointerException("The strings must not be null");
    }
    if (this.sensitive) {}
    for (int i = paramString1.compareTo(paramString2);; i = paramString1.compareToIgnoreCase(paramString2)) {
      return i;
    }
  }
  
  public boolean checkEndsWith(String paramString1, String paramString2)
  {
    int i = paramString2.length();
    if (!this.sensitive) {}
    for (boolean bool = true;; bool = false) {
      return paramString1.regionMatches(bool, paramString1.length() - i, paramString2, 0, i);
    }
  }
  
  public boolean checkEquals(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new NullPointerException("The strings must not be null");
    }
    if (this.sensitive) {}
    for (boolean bool = paramString1.equals(paramString2);; bool = paramString1.equalsIgnoreCase(paramString2)) {
      return bool;
    }
  }
  
  public int checkIndexOf(String paramString1, int paramInt, String paramString2)
  {
    int i = paramString1.length() - paramString2.length();
    int j;
    if (i >= paramInt)
    {
      j = paramInt;
      if (j <= i) {
        if (!checkRegionMatches(paramString1, j, paramString2)) {}
      }
    }
    for (;;)
    {
      return j;
      j++;
      break;
      j = -1;
    }
  }
  
  public boolean checkRegionMatches(String paramString1, int paramInt, String paramString2)
  {
    if (!this.sensitive) {}
    for (boolean bool = true;; bool = false) {
      return paramString1.regionMatches(bool, paramInt, paramString2, 0, paramString2.length());
    }
  }
  
  public boolean checkStartsWith(String paramString1, String paramString2)
  {
    if (!this.sensitive) {}
    for (boolean bool = true;; bool = false) {
      return paramString1.regionMatches(bool, 0, paramString2, 0, paramString2.length());
    }
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean isCaseSensitive()
  {
    return this.sensitive;
  }
  
  public String toString()
  {
    return this.name;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/IOCase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */