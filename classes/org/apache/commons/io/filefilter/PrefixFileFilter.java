package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.IOCase;

public class PrefixFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  private final IOCase caseSensitivity;
  private final String[] prefixes;
  
  public PrefixFileFilter(String paramString)
  {
    this(paramString, IOCase.SENSITIVE);
  }
  
  public PrefixFileFilter(String paramString, IOCase paramIOCase)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("The prefix must not be null");
    }
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    this.prefixes = arrayOfString;
    if (paramIOCase == null) {
      paramIOCase = IOCase.SENSITIVE;
    }
    this.caseSensitivity = paramIOCase;
  }
  
  public PrefixFileFilter(List<String> paramList)
  {
    this(paramList, IOCase.SENSITIVE);
  }
  
  public PrefixFileFilter(List<String> paramList, IOCase paramIOCase)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("The list of prefixes must not be null");
    }
    this.prefixes = ((String[])paramList.toArray(new String[paramList.size()]));
    if (paramIOCase == null) {
      paramIOCase = IOCase.SENSITIVE;
    }
    this.caseSensitivity = paramIOCase;
  }
  
  public PrefixFileFilter(String[] paramArrayOfString)
  {
    this(paramArrayOfString, IOCase.SENSITIVE);
  }
  
  public PrefixFileFilter(String[] paramArrayOfString, IOCase paramIOCase)
  {
    if (paramArrayOfString == null) {
      throw new IllegalArgumentException("The array of prefixes must not be null");
    }
    this.prefixes = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, this.prefixes, 0, paramArrayOfString.length);
    if (paramIOCase == null) {
      paramIOCase = IOCase.SENSITIVE;
    }
    this.caseSensitivity = paramIOCase;
  }
  
  public boolean accept(File paramFile)
  {
    String str1 = paramFile.getName();
    String[] arrayOfString = this.prefixes;
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str2 = arrayOfString[j];
      if (!this.caseSensitivity.checkStartsWith(str1, str2)) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      j++;
      break;
    }
  }
  
  public boolean accept(File paramFile, String paramString)
  {
    String[] arrayOfString = this.prefixes;
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str = arrayOfString[j];
      if (!this.caseSensitivity.checkStartsWith(paramString, str)) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      j++;
      break;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.toString());
    localStringBuilder.append("(");
    if (this.prefixes != null) {
      for (int i = 0; i < this.prefixes.length; i++)
      {
        if (i > 0) {
          localStringBuilder.append(",");
        }
        localStringBuilder.append(this.prefixes[i]);
      }
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/PrefixFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */