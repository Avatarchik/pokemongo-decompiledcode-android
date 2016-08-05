package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class CanWriteFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  public static final IOFileFilter CANNOT_WRITE = new NotFileFilter(CAN_WRITE);
  public static final IOFileFilter CAN_WRITE = new CanWriteFileFilter();
  
  public boolean accept(File paramFile)
  {
    return paramFile.canWrite();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/CanWriteFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */