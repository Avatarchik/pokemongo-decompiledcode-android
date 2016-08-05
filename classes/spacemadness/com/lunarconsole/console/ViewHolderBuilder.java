package spacemadness.com.lunarconsole.console;

import android.view.ViewGroup;

public abstract class ViewHolderBuilder<T extends ConsoleEntry>
{
  public abstract ConsoleAdapter.ViewHolder<T> createViewHolder(ViewGroup paramViewGroup);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/ViewHolderBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */