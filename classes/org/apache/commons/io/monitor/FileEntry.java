package org.apache.commons.io.monitor;

import java.io.File;
import java.io.Serializable;

public class FileEntry
  implements Serializable
{
  static final FileEntry[] EMPTY_ENTRIES = new FileEntry[0];
  private FileEntry[] children;
  private boolean directory;
  private boolean exists;
  private final File file;
  private long lastModified;
  private long length;
  private String name;
  private final FileEntry parent;
  
  public FileEntry(File paramFile)
  {
    this((FileEntry)null, paramFile);
  }
  
  public FileEntry(FileEntry paramFileEntry, File paramFile)
  {
    if (paramFile == null) {
      throw new IllegalArgumentException("File is missing");
    }
    this.file = paramFile;
    this.parent = paramFileEntry;
    this.name = paramFile.getName();
  }
  
  public FileEntry[] getChildren()
  {
    if (this.children != null) {}
    for (FileEntry[] arrayOfFileEntry = this.children;; arrayOfFileEntry = EMPTY_ENTRIES) {
      return arrayOfFileEntry;
    }
  }
  
  public File getFile()
  {
    return this.file;
  }
  
  public long getLastModified()
  {
    return this.lastModified;
  }
  
  public long getLength()
  {
    return this.length;
  }
  
  public int getLevel()
  {
    if (this.parent == null) {}
    for (int i = 0;; i = 1 + this.parent.getLevel()) {
      return i;
    }
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public FileEntry getParent()
  {
    return this.parent;
  }
  
  public boolean isDirectory()
  {
    return this.directory;
  }
  
  public boolean isExists()
  {
    return this.exists;
  }
  
  public FileEntry newChildInstance(File paramFile)
  {
    return new FileEntry(this, paramFile);
  }
  
  public boolean refresh(File paramFile)
  {
    long l1 = 0L;
    boolean bool1 = false;
    boolean bool2 = this.exists;
    long l2 = this.lastModified;
    boolean bool3 = this.directory;
    long l3 = this.length;
    this.name = paramFile.getName();
    this.exists = paramFile.exists();
    boolean bool4;
    if (this.exists)
    {
      bool4 = paramFile.isDirectory();
      this.directory = bool4;
      if (!this.exists) {
        break label157;
      }
    }
    label157:
    for (long l4 = paramFile.lastModified();; l4 = l1)
    {
      this.lastModified = l4;
      if ((this.exists) && (!this.directory)) {
        l1 = paramFile.length();
      }
      this.length = l1;
      if ((this.exists != bool2) || (this.lastModified != l2) || (this.directory != bool3) || (this.length != l3)) {
        bool1 = true;
      }
      return bool1;
      bool4 = false;
      break;
    }
  }
  
  public void setChildren(FileEntry[] paramArrayOfFileEntry)
  {
    this.children = paramArrayOfFileEntry;
  }
  
  public void setDirectory(boolean paramBoolean)
  {
    this.directory = paramBoolean;
  }
  
  public void setExists(boolean paramBoolean)
  {
    this.exists = paramBoolean;
  }
  
  public void setLastModified(long paramLong)
  {
    this.lastModified = paramLong;
  }
  
  public void setLength(long paramLong)
  {
    this.length = paramLong;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/monitor/FileEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */