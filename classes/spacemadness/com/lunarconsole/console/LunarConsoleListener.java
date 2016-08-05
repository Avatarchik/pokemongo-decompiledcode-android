package spacemadness.com.lunarconsole.console;

public abstract interface LunarConsoleListener
{
  public abstract void onAddEntry(Console paramConsole, ConsoleEntry paramConsoleEntry, boolean paramBoolean);
  
  public abstract void onClearEntries(Console paramConsole);
  
  public abstract void onRemoveEntries(Console paramConsole, int paramInt1, int paramInt2);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/LunarConsoleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */