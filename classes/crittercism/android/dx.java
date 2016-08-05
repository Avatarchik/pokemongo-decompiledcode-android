package crittercism.android;

import android.util.Log;
import java.util.concurrent.ExecutorService;

public final class dx
{
  public static a a = a.a;
  private static ec b;
  
  public static void a() {}
  
  public static void a(ec paramec)
  {
    b = paramec;
  }
  
  public static void a(String paramString)
  {
    Log.i("Crittercism", paramString);
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    Log.e("Crittercism", paramString, paramThrowable);
  }
  
  public static void a(Throwable paramThrowable)
  {
    if (!(paramThrowable instanceof cp)) {}
    try
    {
      if ((b != null) && (a == a.b))
      {
        ec localec = b;
        ec.1 local1 = new ec.1(localec, paramThrowable, Thread.currentThread().getId());
        if (!localec.c.a(local1)) {
          localec.b.execute(local1);
        }
      }
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public static void b() {}
  
  public static void b(String paramString)
  {
    Log.e("Crittercism", paramString);
  }
  
  public static void b(String paramString, Throwable paramThrowable)
  {
    Log.w("Crittercism", paramString, paramThrowable);
  }
  
  public static void c() {}
  
  public static void c(String paramString)
  {
    Log.w("Crittercism", paramString);
  }
  
  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      d = arrayOfa;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */