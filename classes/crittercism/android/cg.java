package crittercism.android;

import java.util.Locale;

public final class cg
{
  public static final cg a = new cg();
  private volatile int b = 1;
  private final long c = System.currentTimeMillis();
  
  /**
   * @deprecated
   */
  private int b()
  {
    try
    {
      int i = this.b;
      this.b = (i + 1);
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final String a()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(1);
    arrayOfObject[1] = Long.valueOf(this.c);
    arrayOfObject[2] = Integer.valueOf(b());
    return String.format(localLocale, "%d.%d.%09d", arrayOfObject);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */