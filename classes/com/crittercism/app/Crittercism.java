package com.crittercism.app;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.ConditionVariable;
import com.crittercism.integrations.PluginException;
import crittercism.android.az;
import crittercism.android.az.1;
import crittercism.android.az.7;
import crittercism.android.bb;
import crittercism.android.bc;
import crittercism.android.bg;
import crittercism.android.cf;
import crittercism.android.cf.a;
import crittercism.android.dg;
import crittercism.android.dk;
import crittercism.android.dq;
import crittercism.android.dt;
import crittercism.android.dw;
import crittercism.android.dx;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

public class Crittercism
{
  public static void _logCrashException(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {}
    try
    {
      dx.b("Unable to handle application crash. Missing parameters");
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    new StringBuilder("_logCrashException(msg, stack) called: ").append(paramString1).append(" ").append(paramString2);
    dx.b();
    _logCrashException(new PluginException(paramString1, paramString2));
  }
  
  public static void _logCrashException(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {}
    try
    {
      dx.b("Unable to handle application crash. Missing parameters");
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    new StringBuilder("_logCrashException(name, msg, stack) called: ").append(paramString1).append(" ").append(paramString2).append(" ").append(paramString3);
    dx.b();
    _logCrashException(new PluginException(paramString1, paramString2, paramString3));
  }
  
  public static void _logCrashException(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt)
  {
    try
    {
      new StringBuilder("_logCrashException(String, String, String[], String[], String[], int[]) called: ").append(paramString1).append(" ").append(paramString2);
      dx.b();
      if ((paramString1 == null) || (paramString2 == null) || (paramArrayOfString1 == null) || (paramArrayOfString2 == null) || (paramArrayOfString3 == null) || (paramArrayOfInt == null))
      {
        dx.b("Unable to handle application crash. Missing parameters");
      }
      else
      {
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramArrayOfString1;
        arrayOfObject[1] = paramArrayOfString2;
        arrayOfObject[2] = paramArrayOfString3;
        arrayOfObject[3] = paramArrayOfInt;
        if (!a(arrayOfObject)) {
          dx.b("Unable to handle application crash. Missing stack elements");
        }
      }
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    _logCrashException(new PluginException(paramString1, paramString2, paramArrayOfString1, paramArrayOfString2, paramArrayOfString3, paramArrayOfInt));
  }
  
  @Deprecated
  public static void _logCrashException(Throwable paramThrowable)
  {
    try
    {
      new StringBuilder("_logCrashException(Throwable) called with throwable: ").append(paramThrowable.getMessage());
      dx.b();
      if (!az.A().b) {
        b("_logCrashException");
      } else {
        az.A().a(paramThrowable);
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void _logHandledException(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      new StringBuilder("_logHandledException(name, msg, stack) called: ").append(paramString1).append(" ").append(paramString2).append(" ").append(paramString3);
      dx.b();
      if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
        dx.c("Calling logHandledException with null parameter(s). Nothing will be reported to Crittercism");
      } else {
        logHandledException(new PluginException(paramString1, paramString2, paramString3));
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void _logHandledException(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt)
  {
    try
    {
      new StringBuilder("_logHandledException(name, msg, classes, methods, files, lines) called: ").append(paramString1);
      dx.b();
      if ((paramString1 == null) || (paramString2 == null) || (paramArrayOfString1 == null)) {
        dx.c("Calling logHandledException with null parameter(s). Nothing will be reported to Crittercism");
      } else {
        logHandledException(new PluginException(paramString1, paramString2, paramArrayOfString1, paramArrayOfString2, paramArrayOfString3, paramArrayOfInt));
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  private static void a(String paramString)
  {
    dx.b("Crittercism cannot be initialized", new NullPointerException(paramString + " was null"));
  }
  
  private static boolean a(Object... paramVarArgs)
  {
    int i = 1;
    if ((paramVarArgs.length <= 0) || (paramVarArgs[0] == null)) {
      i = 0;
    }
    label63:
    for (;;)
    {
      return i;
      int j = Array.getLength(paramVarArgs[0]);
      int k = i;
      for (;;)
      {
        if (k >= paramVarArgs.length) {
          break label63;
        }
        if (paramVarArgs[k] == null)
        {
          i = 0;
          break;
        }
        if (Array.getLength(paramVarArgs[k]) != j)
        {
          i = 0;
          break;
        }
        int m;
        k += 1;
      }
    }
  }
  
  private static void b(String paramString)
  {
    dx.b("Must initialize Crittercism before calling " + Crittercism.class.getName() + "." + paramString + "().  Request is being ignored...", new IllegalStateException());
  }
  
  public static void beginTransaction(String paramString)
  {
    try
    {
      az localaz = az.A();
      Transaction localTransaction1;
      if (localaz.t)
      {
        dx.c("Transactions are not supported for services. Ignoring Crittercism.beginTransaction() call for " + paramString + ".");
      }
      else
      {
        localTransaction1 = Transaction.a(paramString);
        if ((localTransaction1 instanceof bg)) {
          synchronized (localaz.z)
          {
            Transaction localTransaction2 = (Transaction)localaz.z.remove(paramString);
            if (localTransaction2 != null) {
              ((bg)localTransaction2).h();
            }
            if (localaz.z.size() > 50) {
              dx.c("Crittercism only supports a maximum of 50 concurrent transactions. Ignoring Crittercism.beginTransaction() call for " + paramString + ".");
            }
          }
        }
      }
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
      localaz.z.put(paramString, localTransaction1);
      localTransaction1.a();
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static boolean didCrashOnLastLoad()
  {
    boolean bool = false;
    try
    {
      az localaz = az.A();
      if (!localaz.b)
      {
        b("didCrashOnLoad");
      }
      else if (!localaz.B())
      {
        localaz.e.block();
        bool = dq.a;
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    return bool;
  }
  
  public static void endTransaction(String paramString)
  {
    try
    {
      az localaz = az.A();
      if (localaz.t) {
        dx.c("Transactions are not supported for services. Ignoring Crittercism.endTransaction() call for " + paramString + ".");
      }
      Transaction localTransaction;
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      synchronized (localaz.z)
      {
        localTransaction = (Transaction)localaz.z.remove(paramString);
        if (localTransaction != null)
        {
          localTransaction.b();
          return;
          localThreadDeath = localThreadDeath;
          throw localThreadDeath;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void failTransaction(String paramString)
  {
    try
    {
      az localaz = az.A();
      if (localaz.t) {
        dx.c("Transactions are not supported for services. Ignoring Crittercism.failTransaction() call for " + paramString + ".");
      }
      Transaction localTransaction;
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      synchronized (localaz.z)
      {
        localTransaction = (Transaction)localaz.z.remove(paramString);
        if (localTransaction != null)
        {
          localTransaction.c();
          return;
          localThreadDeath = localThreadDeath;
          throw localThreadDeath;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static AlertDialog generateRateMyAppAlertDialog(Context paramContext)
  {
    Object localObject = null;
    try
    {
      az localaz = az.A();
      if (localaz.A != null)
      {
        str2 = localaz.A.b();
        str1 = localaz.A.c();
        AlertDialog localAlertDialog = localaz.a(paramContext, str1, str2);
        localObject = localAlertDialog;
        return (AlertDialog)localObject;
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a(localThrowable);
        continue;
        String str1 = null;
        String str2 = null;
      }
    }
  }
  
  public static AlertDialog generateRateMyAppAlertDialog(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      AlertDialog localAlertDialog2 = az.A().a(paramContext, paramString1, paramString2);
      localAlertDialog1 = localAlertDialog2;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a(localThrowable);
        AlertDialog localAlertDialog1 = null;
      }
    }
    return localAlertDialog1;
  }
  
  public static boolean getOptOutStatus()
  {
    boolean bool1 = false;
    try
    {
      az localaz = az.A();
      if (!localaz.b)
      {
        b("getOptOutStatus");
      }
      else
      {
        boolean bool2 = localaz.B();
        bool1 = bool2;
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    return bool1;
  }
  
  public static int getTransactionValue(String paramString)
  {
    try
    {
      int j = az.A().b(paramString);
      i = j;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a(localThrowable);
        int i = -1;
      }
    }
    return i;
  }
  
  /**
   * @deprecated
   */
  public static void initialize(Context paramContext, String paramString)
  {
    try
    {
      initialize(paramContext, paramString, new CrittercismConfig());
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public static void initialize(Context paramContext, String paramString, CrittercismConfig paramCrittercismConfig)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_1
    //   4: ifnonnull +15 -> 19
    //   7: ldc -30
    //   9: invokevirtual 229	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   12: invokestatic 231	com/crittercism/app/Crittercism:a	(Ljava/lang/String;)V
    //   15: ldc 2
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: ifnonnull +52 -> 72
    //   23: ldc -23
    //   25: invokevirtual 229	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   28: invokestatic 231	com/crittercism/app/Crittercism:a	(Ljava/lang/String;)V
    //   31: goto -16 -> 15
    //   34: astore 11
    //   36: new 235	java/lang/IllegalArgumentException
    //   39: dup
    //   40: new 22	java/lang/StringBuilder
    //   43: dup
    //   44: ldc -19
    //   46: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload 11
    //   51: invokevirtual 238	crittercism/android/bn$a:getMessage	()Ljava/lang/String;
    //   54: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 103	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokespecial 239	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   63: athrow
    //   64: astore 4
    //   66: ldc 2
    //   68: monitorexit
    //   69: aload 4
    //   71: athrow
    //   72: aload_2
    //   73: ifnonnull +19 -> 92
    //   76: ldc -38
    //   78: invokevirtual 229	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   81: invokestatic 231	com/crittercism/app/Crittercism:a	(Ljava/lang/String;)V
    //   84: goto -69 -> 15
    //   87: astore 10
    //   89: aload 10
    //   91: athrow
    //   92: invokestatic 74	crittercism/android/az:A	()Lcrittercism/android/az;
    //   95: getfield 77	crittercism/android/az:b	Z
    //   98: ifne -83 -> 15
    //   101: invokestatic 245	java/lang/System:nanoTime	()J
    //   104: lstore 5
    //   106: invokestatic 74	crittercism/android/az:A	()Lcrittercism/android/az;
    //   109: aload_0
    //   110: aload_1
    //   111: aload_2
    //   112: invokevirtual 247	crittercism/android/az:a	(Landroid/content/Context;Ljava/lang/String;Lcom/crittercism/app/CrittercismConfig;)V
    //   115: invokestatic 245	java/lang/System:nanoTime	()J
    //   118: lload 5
    //   120: lsub
    //   121: ldc2_w 248
    //   124: ldiv
    //   125: lstore 7
    //   127: new 22	java/lang/StringBuilder
    //   130: dup
    //   131: ldc -5
    //   133: invokespecial 26	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   136: lload 7
    //   138: invokevirtual 254	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   141: ldc_w 256
    //   144: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: invokestatic 34	crittercism/android/dx:b	()V
    //   151: goto -136 -> 15
    //   154: astore_3
    //   155: aload_3
    //   156: invokestatic 44	crittercism/android/dx:a	(Ljava/lang/Throwable;)V
    //   159: goto -144 -> 15
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	paramContext	Context
    //   0	162	1	paramString	String
    //   0	162	2	paramCrittercismConfig	CrittercismConfig
    //   154	2	3	localThrowable	Throwable
    //   64	6	4	localObject	Object
    //   104	15	5	l1	long
    //   125	12	7	l2	long
    //   87	3	10	localThreadDeath	ThreadDeath
    //   34	16	11	locala	crittercism.android.bn.a
    // Exception table:
    //   from	to	target	type
    //   7	15	34	crittercism/android/bn$a
    //   23	31	34	crittercism/android/bn$a
    //   76	84	34	crittercism/android/bn$a
    //   92	151	34	crittercism/android/bn$a
    //   7	15	64	finally
    //   23	31	64	finally
    //   36	64	64	finally
    //   76	84	64	finally
    //   89	92	64	finally
    //   92	151	64	finally
    //   155	159	64	finally
    //   7	15	87	java/lang/ThreadDeath
    //   23	31	87	java/lang/ThreadDeath
    //   76	84	87	java/lang/ThreadDeath
    //   92	151	87	java/lang/ThreadDeath
    //   7	15	154	java/lang/Throwable
    //   23	31	154	java/lang/Throwable
    //   76	84	154	java/lang/Throwable
    //   92	151	154	java/lang/Throwable
  }
  
  public static void leaveBreadcrumb(String paramString)
  {
    try
    {
      if (!az.A().b) {
        b("leaveBreadcrumb");
      } else if (paramString == null) {
        dx.b("Cannot leave null breadcrumb", new NullPointerException());
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
      az localaz = az.A();
      if (!localaz.f.b())
      {
        az.7 local7 = new az.7(localaz, new cf(paramString, cf.a.a));
        if (!localaz.q.a(local7))
        {
          new StringBuilder("SENDING ").append(paramString).append(" TO EXECUTOR");
          dx.b();
          localaz.s.execute(local7);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void logHandledException(Throwable paramThrowable)
  {
    try
    {
      if (!az.A().b) {
        b("logHandledException");
      } else if (!az.A().f.b()) {
        az.A().b(paramThrowable);
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void logNetworkRequest(String paramString, URL paramURL, long paramLong1, long paramLong2, long paramLong3, int paramInt, Exception paramException)
  {
    try
    {
      long l = System.currentTimeMillis() - paramLong1;
      if (!az.A().b) {
        b("logEndpoint");
      } else if (!az.A().f.b()) {
        az.A().a(paramString, paramURL, paramLong1, paramLong2, paramLong3, paramInt, paramException, l);
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void performRateMyAppButtonAction(CritterRateMyAppButtons paramCritterRateMyAppButtons)
  {
    az localaz;
    String str;
    try
    {
      if (az.A().f.b())
      {
        dx.c("User has opted out of crittercism.  performRateMyAppButtonAction exiting.");
      }
      else
      {
        localaz = az.A();
        if (Build.VERSION.SDK_INT < 5) {
          dx.c("Rate my app not supported below api level 5");
        }
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
      str = localaz.D();
      if (str == null) {
        dx.b("Cannot create proper URI to open app market.  Returning null.");
      }
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    int i = crittercism.android.az.4.a[paramCritterRateMyAppButtons.ordinal()];
    switch (i)
    {
    default: 
      break;
    case 1: 
      try
      {
        localaz.a(str);
      }
      catch (Exception localException2)
      {
        dx.c("performRateMyAppButtonAction(CritterRateMyAppButtons.YES) failed.  Email support@crittercism.com.");
        dx.c();
      }
    case 2: 
      try
      {
        localaz.C();
      }
      catch (Exception localException1)
      {
        dx.c("performRateMyAppButtonAction(CritterRateMyAppButtons.NO) failed.  Email support@crittercism.com.");
      }
    }
  }
  
  public static void sendAppLoadData()
  {
    try
    {
      bb localbb = az.A().u;
      if (localbb == null)
      {
        b("sendAppLoadData");
      }
      else if (localbb.delaySendingAppLoad())
      {
        if (az.A().f.b()) {
          return;
        }
        localaz = az.A();
        if (!localaz.u.delaySendingAppLoad()) {
          dx.c("CrittercismConfig instance not set to delay sending app loads.");
        }
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      az localaz;
      throw localThreadDeath;
      if ((localaz.t) || (localaz.C)) {
        return;
      }
      localaz.C = true;
      az.1 local1 = new az.1(localaz);
      if (localaz.q.a(local1)) {
        return;
      }
      localaz.s.execute(local1);
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    dx.a("sendAppLoadData() will only send data to Crittercism if \"delaySendingAppLoad\" is set to true in the configuration settings you include in the init call.");
  }
  
  public static void setMetadata(JSONObject paramJSONObject)
  {
    try
    {
      if (!az.A().b) {
        b("setMetadata");
      } else {
        az.A().a(paramJSONObject);
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void setOptOutStatus(boolean paramBoolean)
  {
    try
    {
      if (!az.A().b)
      {
        b("setOptOutStatus");
      }
      else
      {
        az localaz = az.A();
        dk localdk = new dk(localaz.c, localaz, paramBoolean);
        if (!localaz.q.a(localdk)) {
          localaz.s.execute(localdk);
        }
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
  }
  
  public static void setTransactionValue(String paramString, int paramInt)
  {
    try
    {
      az localaz = az.A();
      if (localaz.t) {
        dx.c("Transactions are not supported for services. Ignoring Crittercism.setTransactionValue() call for " + paramString + ".");
      } else {
        synchronized (localaz.z)
        {
          Transaction localTransaction = (Transaction)localaz.z.get(paramString);
          if (localTransaction != null) {
            localTransaction.a(paramInt);
          }
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
      dx.a(localThrowable);
    }
  }
  
  public static void setUsername(String paramString)
  {
    try
    {
      if (!az.A().b) {
        b("setUsername");
      } else if (paramString == null) {
        dx.c("Crittercism.setUsername() given invalid parameter: null");
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
      localJSONObject = new JSONObject();
    }
    catch (Throwable localThrowable)
    {
      try
      {
        JSONObject localJSONObject;
        localJSONObject.putOpt("username", paramString);
        az.A().a(localJSONObject);
      }
      catch (JSONException localJSONException)
      {
        dx.b("Crittercism.setUsername()", localJSONException);
      }
      localThrowable = localThrowable;
      dx.a(localThrowable);
    }
  }
  
  public static void updateLocation(Location paramLocation)
  {
    if (!az.A().b) {
      b("updateLocation");
    }
    for (;;)
    {
      return;
      if (paramLocation == null) {
        dx.b("Cannot leave null location", new NullPointerException());
      } else {
        bc.a(paramLocation);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/app/Crittercism.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */