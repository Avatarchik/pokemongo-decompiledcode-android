package crittercism.android;

import java.io.IOException;
import java.io.InputStream;

public final class x
  extends InputStream
  implements al
{
  private ae a;
  private c b;
  private InputStream c;
  private e d;
  private af e;
  
  public x(ae paramae, InputStream paramInputStream, e parame)
  {
    if (paramae == null) {
      throw new NullPointerException("socket was null");
    }
    if (paramInputStream == null) {
      throw new NullPointerException("delegate was null");
    }
    if (parame == null) {
      throw new NullPointerException("dispatch was null");
    }
    this.a = paramae;
    this.c = paramInputStream;
    this.d = parame;
    this.e = b();
    if (this.e == null) {
      throw new NullPointerException("parser was null");
    }
  }
  
  private void a(Exception paramException)
  {
    try
    {
      c localc = e();
      localc.a(paramException);
      this.d.a(localc, c.a.h);
      return;
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
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
  }
  
  private void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      this.e.a(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        this.e = as.d;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        this.e = as.d;
        dx.a(localThrowable);
      }
    }
  }
  
  private c e()
  {
    if (this.b == null) {
      this.b = this.a.b();
    }
    if (this.b == null) {
      throw new IllegalStateException("No statistics were queued up.");
    }
    return this.b;
  }
  
  public final af a()
  {
    return this.e;
  }
  
  public final void a(int paramInt)
  {
    c localc = e();
    localc.c();
    localc.e = paramInt;
  }
  
  public final void a(af paramaf)
  {
    this.e = paramaf;
  }
  
  public final void a(String paramString) {}
  
  public final void a(String paramString1, String paramString2) {}
  
  public final boolean a(InputStream paramInputStream)
  {
    if (this.c == paramInputStream) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final int available()
  {
    return this.c.available();
  }
  
  public final af b()
  {
    return new ap(this);
  }
  
  public final void b(int paramInt)
  {
    c localc = null;
    if (this.b != null)
    {
      int i = this.b.e;
      if ((i >= 100) && (i < 200))
      {
        localc = new c(this.b.a());
        localc.e(this.b.a);
        localc.d(this.b.d);
        localc.f = this.b.f;
      }
      this.b.b(paramInt);
      this.d.a(this.b, c.a.g);
    }
    this.b = localc;
  }
  
  public final String c()
  {
    return e().f;
  }
  
  public final void close()
  {
    try
    {
      this.e.f();
      this.c.close();
      return;
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
      }
    }
  }
  
  public final void d()
  {
    if (this.b != null)
    {
      cn localcn = this.b.g;
      cm localcm = cm.a;
      if ((localcn.a != co.d.ordinal()) || (localcn.b != localcm.a())) {
        break label64;
      }
    }
    label64:
    for (int i = 1;; i = 0)
    {
      if ((i != 0) && (this.e != null)) {
        this.e.f();
      }
      return;
    }
  }
  
  public final void mark(int paramInt)
  {
    this.c.mark(paramInt);
  }
  
  public final boolean markSupported()
  {
    return this.c.markSupported();
  }
  
  /* Error */
  public final int read()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	crittercism/android/x:c	Ljava/io/InputStream;
    //   4: invokevirtual 175	java/io/InputStream:read	()I
    //   7: istore_2
    //   8: aload_0
    //   9: getfield 43	crittercism/android/x:e	Lcrittercism/android/af;
    //   12: iload_2
    //   13: invokevirtual 178	crittercism/android/af:a	(I)Z
    //   16: pop
    //   17: iload_2
    //   18: ireturn
    //   19: astore_1
    //   20: aload_0
    //   21: aload_1
    //   22: invokespecial 180	crittercism/android/x:a	(Ljava/lang/Exception;)V
    //   25: aload_1
    //   26: athrow
    //   27: astore 5
    //   29: aload 5
    //   31: athrow
    //   32: astore 4
    //   34: aload_0
    //   35: getstatic 84	crittercism/android/as:d	Lcrittercism/android/as;
    //   38: putfield 43	crittercism/android/x:e	Lcrittercism/android/af;
    //   41: goto -24 -> 17
    //   44: astore_3
    //   45: aload_0
    //   46: getstatic 84	crittercism/android/as:d	Lcrittercism/android/as;
    //   49: putfield 43	crittercism/android/x:e	Lcrittercism/android/af;
    //   52: aload_3
    //   53: invokestatic 74	crittercism/android/dx:a	(Ljava/lang/Throwable;)V
    //   56: goto -39 -> 17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	x
    //   19	7	1	localIOException	IOException
    //   7	11	2	i	int
    //   44	9	3	localThrowable	Throwable
    //   32	1	4	localIllegalStateException	IllegalStateException
    //   27	3	5	localThreadDeath	ThreadDeath
    // Exception table:
    //   from	to	target	type
    //   0	8	19	java/io/IOException
    //   8	17	27	java/lang/ThreadDeath
    //   8	17	32	java/lang/IllegalStateException
    //   8	17	44	java/lang/Throwable
  }
  
  public final int read(byte[] paramArrayOfByte)
  {
    try
    {
      int i = this.c.read(paramArrayOfByte);
      a(paramArrayOfByte, 0, i);
      return i;
    }
    catch (IOException localIOException)
    {
      a(localIOException);
      throw localIOException;
    }
  }
  
  public final int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      int i = this.c.read(paramArrayOfByte, paramInt1, paramInt2);
      a(paramArrayOfByte, paramInt1, i);
      return i;
    }
    catch (IOException localIOException)
    {
      a(localIOException);
      throw localIOException;
    }
  }
  
  /**
   * @deprecated
   */
  public final void reset()
  {
    try
    {
      this.c.reset();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final long skip(long paramLong)
  {
    return this.c.skip(paramLong);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */