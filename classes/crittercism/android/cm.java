package crittercism.android;

import java.util.HashMap;

public enum cm
{
  private static HashMap C;
  private int D;
  private String E;
  
  static
  {
    A = new cm("SSL_PROTOCOL_EXCEPTION", 26, 26, "javax.net.ssl.SSLProtocolException");
    B = new cm("UNDEFINED_EXCEPTION", 27, -1, "__UNKNOWN__");
    cm[] arrayOfcm = new cm[28];
    arrayOfcm[0] = a;
    arrayOfcm[1] = b;
    arrayOfcm[2] = c;
    arrayOfcm[3] = d;
    arrayOfcm[4] = e;
    arrayOfcm[5] = f;
    arrayOfcm[6] = g;
    arrayOfcm[7] = h;
    arrayOfcm[8] = i;
    arrayOfcm[9] = j;
    arrayOfcm[10] = k;
    arrayOfcm[11] = l;
    arrayOfcm[12] = m;
    arrayOfcm[13] = n;
    arrayOfcm[14] = o;
    arrayOfcm[15] = p;
    arrayOfcm[16] = q;
    arrayOfcm[17] = r;
    arrayOfcm[18] = s;
    arrayOfcm[19] = t;
    arrayOfcm[20] = u;
    arrayOfcm[21] = v;
    arrayOfcm[22] = w;
    arrayOfcm[23] = x;
    arrayOfcm[24] = y;
    arrayOfcm[25] = z;
    arrayOfcm[26] = A;
    arrayOfcm[27] = B;
    F = arrayOfcm;
  }
  
  private cm(int paramInt1, String paramString1)
  {
    this.D = paramInt1;
    this.E = paramString1;
  }
  
  public static cm a(Throwable paramThrowable)
  {
    if (C == null) {
      b();
    }
    String str = null;
    if (paramThrowable != null) {
      str = paramThrowable.getClass().getName();
    }
    cm localcm = (cm)C.get(str);
    if (localcm == null) {
      localcm = B;
    }
    return localcm;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  private static void b()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 225	crittercism/android/cm:C	Ljava/util/HashMap;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +7 -> 15
    //   11: ldc 2
    //   13: monitorexit
    //   14: return
    //   15: new 241	java/util/HashMap
    //   18: dup
    //   19: invokespecial 247	java/util/HashMap:<init>	()V
    //   22: astore_2
    //   23: invokestatic 251	crittercism/android/cm:values	()[Lcrittercism/android/cm;
    //   26: astore_3
    //   27: aload_3
    //   28: arraylength
    //   29: istore 4
    //   31: iconst_0
    //   32: istore 5
    //   34: iload 5
    //   36: iload 4
    //   38: if_icmpge +27 -> 65
    //   41: aload_3
    //   42: iload 5
    //   44: aaload
    //   45: astore 6
    //   47: aload_2
    //   48: aload 6
    //   50: getfield 222	crittercism/android/cm:E	Ljava/lang/String;
    //   53: aload 6
    //   55: invokevirtual 255	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   58: pop
    //   59: iinc 5 1
    //   62: goto -28 -> 34
    //   65: aload_2
    //   66: putstatic 225	crittercism/android/cm:C	Ljava/util/HashMap;
    //   69: goto -58 -> 11
    //   72: astore_0
    //   73: ldc 2
    //   75: monitorexit
    //   76: aload_0
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   72	5	0	localObject	Object
    //   6	2	1	localHashMap1	HashMap
    //   22	44	2	localHashMap2	HashMap
    //   26	16	3	arrayOfcm	cm[]
    //   29	10	4	i1	int
    //   32	28	5	i2	int
    //   45	9	6	localcm	cm
    // Exception table:
    //   from	to	target	type
    //   3	7	72	finally
    //   15	69	72	finally
  }
  
  public final int a()
  {
    return this.D;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */