package spacemadness.com.lunarconsole.console;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import spacemadness.com.lunarconsole.R.layout;

public class ConsoleAdapter
  extends BaseAdapter
{
  private DataSource dataSource;
  
  public ConsoleAdapter(DataSource paramDataSource)
  {
    if (paramDataSource == null) {
      throw new NullPointerException("Data source is null");
    }
    this.dataSource = paramDataSource;
  }
  
  public int getCount()
  {
    return this.dataSource.getEntryCount();
  }
  
  public Object getItem(int paramInt)
  {
    return this.dataSource.getEntry(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return this.dataSource.getEntry(paramInt).type;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if (paramView != null) {
      localObject = (ViewHolder)paramView.getTag();
    }
    for (;;)
    {
      ((ViewHolder)localObject).bindViewHolder(this.dataSource.getEntry(paramInt));
      return paramView;
      paramView = LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.lunar_layout_console_log_entry, paramViewGroup, false);
      localObject = new ConsoleEntry.ViewHolder(paramView);
      paramView.setTag(localObject);
    }
  }
  
  public static abstract class ViewHolder<T extends ConsoleEntry>
  {
    protected final View itemView;
    
    public ViewHolder(View paramView)
    {
      this.itemView = paramView;
    }
    
    void bindViewHolder(ConsoleEntry paramConsoleEntry)
    {
      onBindViewHolder(paramConsoleEntry);
    }
    
    protected int getColor(int paramInt)
    {
      return getResources().getColor(paramInt);
    }
    
    protected Context getContext()
    {
      return this.itemView.getContext();
    }
    
    protected Resources getResources()
    {
      return getContext().getResources();
    }
    
    protected String getString(int paramInt)
    {
      return getResources().getString(paramInt);
    }
    
    public abstract void onBindViewHolder(T paramT);
  }
  
  public static abstract interface DataSource
  {
    public abstract ConsoleEntry getEntry(int paramInt);
    
    public abstract int getEntryCount();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/ConsoleAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */