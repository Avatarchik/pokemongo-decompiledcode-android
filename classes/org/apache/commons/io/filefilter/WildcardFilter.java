package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

@Deprecated
public class WildcardFilter
  extends AbstractFileFilter
  implements Serializable
{
  private final String[] wildcards;
  
  public WildcardFilter(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("The wildcard must not be null");
    }
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    this.wildcards = arrayOfString;
  }
  
  public WildcardFilter(List<String> paramList)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("The wildcard list must not be null");
    }
    this.wildcards = ((String[])paramList.toArray(new String[paramList.size()]));
  }
  
  public WildcardFilter(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      throw new IllegalArgumentException("The wildcard array must not be null");
    }
    this.wildcards = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, this.wildcards, 0, paramArrayOfString.length);
  }
  
  public boolean accept(File paramFile)
  {
    boolean bool = false;
    if (paramFile.isDirectory()) {}
    label57:
    for (;;)
    {
      return bool;
      String[] arrayOfString = this.wildcards;
      int i = arrayOfString.length;
      for (int j = 0;; j++)
      {
        if (j >= i) {
          break label57;
        }
        String str = arrayOfString[j];
        if (FilenameUtils.wildcardMatch(paramFile.getName(), str))
        {
          bool = true;
          break;
        }
      }
    }
  }
  
  public boolean accept(File paramFile, String paramString)
  {
    boolean bool = false;
    if ((paramFile != null) && (new File(paramFile, paramString).isDirectory())) {}
    label65:
    for (;;)
    {
      return bool;
      String[] arrayOfString = this.wildcards;
      int i = arrayOfString.length;
      for (int j = 0;; j++)
      {
        if (j >= i) {
          break label65;
        }
        if (FilenameUtils.wildcardMatch(paramString, arrayOfString[j]))
        {
          bool = true;
          break;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/WildcardFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */