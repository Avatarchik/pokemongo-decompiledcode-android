package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.IOCase;

public class NameFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  private final IOCase caseSensitivity;
  private final String[] names;
  
  public NameFileFilter(String paramString)
  {
    this(paramString, null);
  }
  
  public NameFileFilter(String paramString, IOCase paramIOCase)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("The wildcard must not be null");
    }
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    this.names = arrayOfString;
    if (paramIOCase == null) {
      paramIOCase = IOCase.SENSITIVE;
    }
    this.caseSensitivity = paramIOCase;
  }
  
  public NameFileFilter(List<String> paramList)
  {
    this(paramList, null);
  }
  
  public NameFileFilter(List<String> paramList, IOCase paramIOCase)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("The list of names must not be null");
    }
    this.names = ((String[])paramList.toArray(new String[paramList.size()]));
    if (paramIOCase == null) {
      paramIOCase = IOCase.SENSITIVE;
    }
    this.caseSensitivity = paramIOCase;
  }
  
  public NameFileFilter(String[] paramArrayOfString)
  {
    this(paramArrayOfString, null);
  }
  
  public NameFileFilter(String[] paramArrayOfString, IOCase paramIOCase)
  {
    if (paramArrayOfString == null) {
      throw new IllegalArgumentException("The array of names must not be null");
    }
    this.names = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, this.names, 0, paramArrayOfString.length);
    if (paramIOCase == null) {
      paramIOCase = IOCase.SENSITIVE;
    }
    this.caseSensitivity = paramIOCase;
  }
  
  public boolean accept(File paramFile)
  {
    String str1 = paramFile.getName();
    String[] arrayOfString = this.names;
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str2 = arrayOfString[j];
      if (!this.caseSensitivity.checkEquals(str1, str2)) {}
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
    String[] arrayOfString = this.names;
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str = arrayOfString[j];
      if (!this.caseSensitivity.checkEquals(paramString, str)) {}
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
    if (this.names != null) {
      for (int i = 0; i < this.names.length; i++)
      {
        if (i > 0) {
          localStringBuilder.append(",");
        }
        localStringBuilder.append(this.names[i]);
      }
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/NameFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */