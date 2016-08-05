package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

public class FileDeleteStrategy
{
  public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();
  public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy("Normal");
  private final String name;
  
  protected FileDeleteStrategy(String paramString)
  {
    this.name = paramString;
  }
  
  public void delete(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!doDelete(paramFile))) {
      throw new IOException("Deletion failed: " + paramFile);
    }
  }
  
  public boolean deleteQuietly(File paramFile)
  {
    boolean bool1;
    if ((paramFile == null) || (!paramFile.exists())) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      try
      {
        boolean bool2 = doDelete(paramFile);
        bool1 = bool2;
      }
      catch (IOException localIOException)
      {
        bool1 = false;
      }
    }
  }
  
  protected boolean doDelete(File paramFile)
    throws IOException
  {
    return paramFile.delete();
  }
  
  public String toString()
  {
    return "FileDeleteStrategy[" + this.name + "]";
  }
  
  static class ForceFileDeleteStrategy
    extends FileDeleteStrategy
  {
    ForceFileDeleteStrategy()
    {
      super();
    }
    
    protected boolean doDelete(File paramFile)
      throws IOException
    {
      FileUtils.forceDelete(paramFile);
      return true;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/FileDeleteStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */