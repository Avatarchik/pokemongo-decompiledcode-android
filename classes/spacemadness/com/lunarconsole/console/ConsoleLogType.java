package spacemadness.com.lunarconsole.console;

public final class ConsoleLogType
{
  public static final byte ASSERT = 1;
  public static final byte COUNT = 5;
  public static final byte ERROR = 0;
  public static final byte EXCEPTION = 4;
  public static final byte LOG = 3;
  public static final byte WARNING = 2;
  
  public static int getMask(int paramInt)
  {
    return 1 << paramInt;
  }
  
  public static boolean isErrorType(int paramInt)
  {
    int i = 1;
    if ((paramInt == 4) || (paramInt == 0) || (paramInt == i)) {}
    for (;;)
    {
      return i;
      i = 0;
    }
  }
  
  public static boolean isValidType(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 5)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/ConsoleLogType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */