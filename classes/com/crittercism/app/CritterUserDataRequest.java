package com.crittercism.app;

import crittercism.android.az;
import crittercism.android.dl;
import crittercism.android.dw;
import java.util.HashMap;
import java.util.Map;

public class CritterUserDataRequest
{
  private final CritterCallback a;
  private az b;
  private Map c;
  private dl d;
  
  public CritterUserDataRequest(CritterCallback paramCritterCallback)
  {
    this.a = paramCritterCallback;
    this.b = az.A();
    this.c = new HashMap();
    this.d = new dl(this.b);
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public void makeRequest()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 29	com/crittercism/app/CritterUserDataRequest:b	Lcrittercism/android/az;
    //   6: getfield 51	crittercism/android/az:q	Lcrittercism/android/dg;
    //   9: astore_2
    //   10: aload_2
    //   11: ifnonnull +45 -> 56
    //   14: new 53	java/lang/IllegalStateException
    //   17: dup
    //   18: invokespecial 54	java/lang/IllegalStateException:<init>	()V
    //   21: astore_3
    //   22: new 56	java/lang/StringBuilder
    //   25: dup
    //   26: ldc 58
    //   28: invokespecial 61	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   31: aload_0
    //   32: invokevirtual 65	java/lang/Object:getClass	()Ljava/lang/Class;
    //   35: invokevirtual 71	java/lang/Class:getName	()Ljava/lang/String;
    //   38: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: ldc 77
    //   43: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: aload_3
    //   50: invokestatic 85	crittercism/android/dx:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: new 6	com/crittercism/app/CritterUserDataRequest$1
    //   59: dup
    //   60: aload_0
    //   61: invokespecial 88	com/crittercism/app/CritterUserDataRequest$1:<init>	(Lcom/crittercism/app/CritterUserDataRequest;)V
    //   64: astore 4
    //   66: aload_2
    //   67: aload 4
    //   69: invokevirtual 93	crittercism/android/dg:a	(Ljava/lang/Runnable;)Z
    //   72: ifne -19 -> 53
    //   75: new 95	crittercism/android/dy
    //   78: dup
    //   79: aload 4
    //   81: invokespecial 98	crittercism/android/dy:<init>	(Ljava/lang/Runnable;)V
    //   84: invokevirtual 101	crittercism/android/dy:start	()V
    //   87: goto -34 -> 53
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	CritterUserDataRequest
    //   90	4	1	localObject	Object
    //   9	58	2	localdg	crittercism.android.dg
    //   21	29	3	localIllegalStateException	IllegalStateException
    //   64	16	4	local1	1
    // Exception table:
    //   from	to	target	type
    //   2	53	90	finally
    //   56	87	90	finally
  }
  
  public CritterUserDataRequest requestDidCrashOnLastLoad()
  {
    this.d.c();
    return this;
  }
  
  public CritterUserDataRequest requestOptOutStatus()
  {
    this.d.b();
    return this;
  }
  
  public CritterUserDataRequest requestRateMyAppInfo()
  {
    this.d.e();
    return this;
  }
  
  public CritterUserDataRequest requestUserUUID()
  {
    this.d.d();
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/app/CritterUserDataRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */