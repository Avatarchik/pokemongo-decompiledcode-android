package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.Serializable;

public class DelegateFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  private final FileFilter fileFilter;
  private final FilenameFilter filenameFilter;
  
  public DelegateFileFilter(FileFilter paramFileFilter)
  {
    if (paramFileFilter == null) {
      throw new IllegalArgumentException("The FileFilter must not be null");
    }
    this.fileFilter = paramFileFilter;
    this.filenameFilter = null;
  }
  
  public DelegateFileFilter(FilenameFilter paramFilenameFilter)
  {
    if (paramFilenameFilter == null) {
      throw new IllegalArgumentException("The FilenameFilter must not be null");
    }
    this.filenameFilter = paramFilenameFilter;
    this.fileFilter = null;
  }
  
  public boolean accept(File paramFile)
  {
    if (this.fileFilter != null) {}
    for (boolean bool = this.fileFilter.accept(paramFile);; bool = super.accept(paramFile)) {
      return bool;
    }
  }
  
  public boolean accept(File paramFile, String paramString)
  {
    if (this.filenameFilter != null) {}
    for (boolean bool = this.filenameFilter.accept(paramFile, paramString);; bool = super.accept(paramFile, paramString)) {
      return bool;
    }
  }
  
  public String toString()
  {
    if (this.fileFilter != null) {}
    for (String str = this.fileFilter.toString();; str = this.filenameFilter.toString()) {
      return super.toString() + "(" + str + ")";
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/DelegateFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */