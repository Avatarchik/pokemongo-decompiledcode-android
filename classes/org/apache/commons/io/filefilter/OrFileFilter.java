package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OrFileFilter
  extends AbstractFileFilter
  implements ConditionalFileFilter, Serializable
{
  private final List<IOFileFilter> fileFilters;
  
  public OrFileFilter()
  {
    this.fileFilters = new ArrayList();
  }
  
  public OrFileFilter(List<IOFileFilter> paramList)
  {
    if (paramList == null) {}
    for (this.fileFilters = new ArrayList();; this.fileFilters = new ArrayList(paramList)) {
      return;
    }
  }
  
  public OrFileFilter(IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    if ((paramIOFileFilter1 == null) || (paramIOFileFilter2 == null)) {
      throw new IllegalArgumentException("The filters must not be null");
    }
    this.fileFilters = new ArrayList(2);
    addFileFilter(paramIOFileFilter1);
    addFileFilter(paramIOFileFilter2);
  }
  
  public boolean accept(File paramFile)
  {
    Iterator localIterator = this.fileFilters.iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((IOFileFilter)localIterator.next()).accept(paramFile));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean accept(File paramFile, String paramString)
  {
    Iterator localIterator = this.fileFilters.iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((IOFileFilter)localIterator.next()).accept(paramFile, paramString));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void addFileFilter(IOFileFilter paramIOFileFilter)
  {
    this.fileFilters.add(paramIOFileFilter);
  }
  
  public List<IOFileFilter> getFileFilters()
  {
    return Collections.unmodifiableList(this.fileFilters);
  }
  
  public boolean removeFileFilter(IOFileFilter paramIOFileFilter)
  {
    return this.fileFilters.remove(paramIOFileFilter);
  }
  
  public void setFileFilters(List<IOFileFilter> paramList)
  {
    this.fileFilters.clear();
    this.fileFilters.addAll(paramList);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.toString());
    localStringBuilder.append("(");
    if (this.fileFilters != null)
    {
      int i = 0;
      if (i < this.fileFilters.size())
      {
        if (i > 0) {
          localStringBuilder.append(",");
        }
        Object localObject = this.fileFilters.get(i);
        if (localObject == null) {}
        for (String str = "null";; str = localObject.toString())
        {
          localStringBuilder.append(str);
          i++;
          break;
        }
      }
    }
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/OrFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */