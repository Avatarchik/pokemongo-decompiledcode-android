package spacemadness.com.lunarconsole.console;

import java.util.Iterator;
import spacemadness.com.lunarconsole.utils.ObjectUtils;
import spacemadness.com.lunarconsole.utils.StringUtils;

public class ConsoleEntryList
{
  private LimitSizeEntryList currentEntries;
  private final LimitSizeEntryList entries;
  private int errorCount;
  private String filterText;
  private LimitSizeEntryList filteredEntries;
  private int logCount;
  private int logDisabledTypesMask;
  private int warningCount;
  
  public ConsoleEntryList(int paramInt1, int paramInt2)
  {
    this.entries = new LimitSizeEntryList(paramInt1, paramInt2);
    this.currentEntries = this.entries;
    this.logDisabledTypesMask = 0;
  }
  
  private boolean appendFilter()
  {
    if (isFiltering()) {
      useFilteredFromEntries(this.filteredEntries);
    }
    for (boolean bool = true;; bool = applyFilter()) {
      return bool;
    }
  }
  
  private boolean applyFilter()
  {
    boolean bool1 = true;
    boolean bool2;
    if ((StringUtils.length(this.filterText) > 0) || (hasLogTypeFilters()))
    {
      bool2 = bool1;
      if (!bool2) {
        break label40;
      }
      useFilteredFromEntries(this.entries);
    }
    for (;;)
    {
      return bool1;
      bool2 = false;
      break;
      label40:
      bool1 = removeFilter();
    }
  }
  
  private LimitSizeEntryList filterEntries(LimitSizeEntryList paramLimitSizeEntryList)
  {
    LimitSizeEntryList localLimitSizeEntryList = new LimitSizeEntryList(paramLimitSizeEntryList.capacity(), paramLimitSizeEntryList.getTrimSize());
    Iterator localIterator = paramLimitSizeEntryList.iterator();
    while (localIterator.hasNext())
    {
      ConsoleEntry localConsoleEntry = (ConsoleEntry)localIterator.next();
      if (isFiltered(localConsoleEntry)) {
        localLimitSizeEntryList.addObject(localConsoleEntry);
      }
    }
    return localLimitSizeEntryList;
  }
  
  private boolean hasLogTypeFilters()
  {
    if (this.logDisabledTypesMask != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isFiltered(ConsoleEntry paramConsoleEntry)
  {
    boolean bool = false;
    if ((this.logDisabledTypesMask & ConsoleLogType.getMask(paramConsoleEntry.type)) != 0) {}
    for (;;)
    {
      return bool;
      if ((StringUtils.length(this.filterText) == 0) || (StringUtils.containsIgnoreCase(paramConsoleEntry.message, this.filterText))) {
        bool = true;
      }
    }
  }
  
  private boolean removeFilter()
  {
    if (isFiltering())
    {
      this.currentEntries = this.entries;
      this.filteredEntries = null;
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void useFilteredFromEntries(LimitSizeEntryList paramLimitSizeEntryList)
  {
    LimitSizeEntryList localLimitSizeEntryList = filterEntries(paramLimitSizeEntryList);
    this.currentEntries = localLimitSizeEntryList;
    this.filteredEntries = localLimitSizeEntryList;
  }
  
  public void addEntry(ConsoleEntry paramConsoleEntry)
  {
    this.entries.addObject(paramConsoleEntry);
    int i = paramConsoleEntry.type;
    if (i == 3) {
      this.logCount = (1 + this.logCount);
    }
    for (;;)
    {
      return;
      if (i == 2) {
        this.warningCount = (1 + this.warningCount);
      } else if (ConsoleLogType.isErrorType(i)) {
        this.errorCount = (1 + this.errorCount);
      }
    }
  }
  
  public void clear()
  {
    this.entries.clear();
    if (this.filteredEntries != null) {
      this.filteredEntries.clear();
    }
    this.logCount = 0;
    this.warningCount = 0;
    this.errorCount = 0;
  }
  
  public int count()
  {
    return this.currentEntries.count();
  }
  
  public boolean filterEntry(ConsoleEntry paramConsoleEntry)
  {
    boolean bool = true;
    if (isFiltering())
    {
      if (!isFiltered(paramConsoleEntry)) {
        break label27;
      }
      this.filteredEntries.addObject(paramConsoleEntry);
    }
    for (;;)
    {
      return bool;
      label27:
      bool = false;
    }
  }
  
  public ConsoleEntry getEntry(int paramInt)
  {
    return (ConsoleEntry)this.currentEntries.objectAtIndex(paramInt);
  }
  
  public int getErrorCount()
  {
    return this.errorCount;
  }
  
  public String getFilterText()
  {
    return this.filterText;
  }
  
  public int getLogCount()
  {
    return this.logCount;
  }
  
  public String getText()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = this.currentEntries.count();
    Iterator localIterator = this.currentEntries.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((ConsoleEntry)localIterator.next()).message);
      i++;
      if (i < j) {
        localStringBuilder.append('\n');
      }
    }
    return localStringBuilder.toString();
  }
  
  public int getWarningCount()
  {
    return this.warningCount;
  }
  
  public boolean isFilterLogTypeEnabled(int paramInt)
  {
    if ((this.logDisabledTypesMask & ConsoleLogType.getMask(paramInt)) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isFiltering()
  {
    if (this.filteredEntries != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isOverfloating()
  {
    return this.currentEntries.isOverfloating();
  }
  
  public boolean isTrimmed()
  {
    return this.currentEntries.isTrimmed();
  }
  
  public int overflowAmount()
  {
    return this.currentEntries.overflowCount();
  }
  
  public boolean setFilterByLogType(int paramInt, boolean paramBoolean)
  {
    return setFilterByLogTypeMask(ConsoleLogType.getMask(paramInt), paramBoolean);
  }
  
  public boolean setFilterByLogTypeMask(int paramInt, boolean paramBoolean)
  {
    int i = this.logDisabledTypesMask;
    boolean bool;
    if (paramBoolean)
    {
      this.logDisabledTypesMask = (paramInt | this.logDisabledTypesMask);
      if (i == this.logDisabledTypesMask) {
        break label65;
      }
      if (!paramBoolean) {
        break label56;
      }
      bool = appendFilter();
    }
    for (;;)
    {
      return bool;
      this.logDisabledTypesMask &= (paramInt ^ 0xFFFFFFFF);
      break;
      label56:
      bool = applyFilter();
      continue;
      label65:
      bool = false;
    }
  }
  
  public boolean setFilterByText(String paramString)
  {
    boolean bool;
    if (!ObjectUtils.areEqual(this.filterText, paramString))
    {
      String str = this.filterText;
      this.filterText = paramString;
      if ((StringUtils.length(paramString) > StringUtils.length(str)) && ((StringUtils.length(str) == 0) || (StringUtils.hasPrefix(paramString, str)))) {
        bool = appendFilter();
      }
    }
    for (;;)
    {
      return bool;
      bool = applyFilter();
      continue;
      bool = false;
    }
  }
  
  public int totalCount()
  {
    return this.currentEntries.totalCount();
  }
  
  public void trimHead(int paramInt)
  {
    this.entries.trimHead(paramInt);
  }
  
  public int trimmedCount()
  {
    return this.currentEntries.trimmedCount();
  }
  
  private static class LimitSizeEntryList
    extends LimitSizeList<ConsoleEntry>
  {
    public LimitSizeEntryList(int paramInt1, int paramInt2)
    {
      super(paramInt1, paramInt2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/ConsoleEntryList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */