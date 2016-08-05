package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

public class DirectoryFileComparator
  extends AbstractFileComparator
  implements Serializable
{
  public static final Comparator<File> DIRECTORY_COMPARATOR = new DirectoryFileComparator();
  public static final Comparator<File> DIRECTORY_REVERSE = new ReverseComparator(DIRECTORY_COMPARATOR);
  
  private int getType(File paramFile)
  {
    if (paramFile.isDirectory()) {}
    for (int i = 1;; i = 2) {
      return i;
    }
  }
  
  public int compare(File paramFile1, File paramFile2)
  {
    return getType(paramFile1) - getType(paramFile2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/comparator/DirectoryFileComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */