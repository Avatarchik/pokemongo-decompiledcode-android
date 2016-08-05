package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

public class LastModifiedFileComparator
  extends AbstractFileComparator
  implements Serializable
{
  public static final Comparator<File> LASTMODIFIED_COMPARATOR = new LastModifiedFileComparator();
  public static final Comparator<File> LASTMODIFIED_REVERSE = new ReverseComparator(LASTMODIFIED_COMPARATOR);
  
  public int compare(File paramFile1, File paramFile2)
  {
    long l = paramFile1.lastModified() - paramFile2.lastModified();
    int i;
    if (l < 0L) {
      i = -1;
    }
    for (;;)
    {
      return i;
      if (l > 0L) {
        i = 1;
      } else {
        i = 0;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/comparator/LastModifiedFileComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */