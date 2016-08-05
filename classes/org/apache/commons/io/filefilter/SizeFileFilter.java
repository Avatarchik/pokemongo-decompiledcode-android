package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class SizeFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  private final boolean acceptLarger;
  private final long size;
  
  public SizeFileFilter(long paramLong)
  {
    this(paramLong, true);
  }
  
  public SizeFileFilter(long paramLong, boolean paramBoolean)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("The size must be non-negative");
    }
    this.size = paramLong;
    this.acceptLarger = paramBoolean;
  }
  
  public boolean accept(File paramFile)
  {
    boolean bool1 = true;
    boolean bool2;
    if (paramFile.length() < this.size)
    {
      bool2 = bool1;
      if (!this.acceptLarger) {
        break label39;
      }
      if (bool2) {
        break label34;
      }
    }
    for (;;)
    {
      return bool1;
      bool2 = false;
      break;
      label34:
      bool1 = false;
      continue;
      label39:
      bool1 = bool2;
    }
  }
  
  public String toString()
  {
    if (this.acceptLarger) {}
    for (String str = ">=";; str = "<") {
      return super.toString() + "(" + str + this.size + ")";
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/SizeFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */