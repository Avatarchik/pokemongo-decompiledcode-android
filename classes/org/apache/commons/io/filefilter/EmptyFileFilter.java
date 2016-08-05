package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class EmptyFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  public static final IOFileFilter EMPTY = new EmptyFileFilter();
  public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);
  
  public boolean accept(File paramFile)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      if ((arrayOfFile == null) || (arrayOfFile.length == 0)) {
        bool2 = bool1;
      }
      return bool2;
    }
    if (paramFile.length() == 0L) {}
    for (;;)
    {
      bool2 = bool1;
      break;
      bool1 = false;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/EmptyFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */