package org.apache.commons.io.filefilter;

import java.util.List;

public abstract interface ConditionalFileFilter
{
  public abstract void addFileFilter(IOFileFilter paramIOFileFilter);
  
  public abstract List<IOFileFilter> getFileFilters();
  
  public abstract boolean removeFileFilter(IOFileFilter paramIOFileFilter);
  
  public abstract void setFileFilters(List<IOFileFilter> paramList);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/ConditionalFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */