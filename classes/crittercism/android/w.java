package crittercism.android;

import java.io.OutputStream;

public final class w
  extends OutputStream
  implements al
{
  private ae a;
  private OutputStream b;
  private c c;
  private af d;
  
  public w(ae paramae, OutputStream paramOutputStream)
  {
    if (paramae == null) {
      throw new NullPointerException("socket was null");
    }
    if (paramOutputStream == null) {
      throw new NullPointerException("output stream was null");
    }
    this.a = paramae;
    this.b = paramOutputStream;
    this.d = b();
    if (this.d == null) {
      throw new NullPointerException("parser was null");
    }
  }
  
  private void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      this.d.a(paramArrayOfByte, paramInt1, paramInt2);
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
        this.d = as.d;
      }
    }
  }
  
  private c d()
  {
    if (this.c == null) {
      this.c = this.a.a();
    }
    return this.c;
  }
  
  public final af a()
  {
    return this.d;
  }
  
  public final void a(int paramInt) {}
  
  public final void a(af paramaf)
  {
    this.d = paramaf;
  }
  
  public final void a(String paramString)
  {
    c localc = d();
    if (localc != null) {
      localc.b(paramString);
    }
  }
  
  public final void a(String paramString1, String paramString2)
  {
    c localc = d();
    localc.b();
    localc.f = paramString1;
    localc.i = null;
    k localk = localc.h;
    if (paramString2 != null) {
      localk.c = paramString2;
    }
    this.a.a(localc);
  }
  
  public final boolean a(OutputStream paramOutputStream)
  {
    if (this.b == paramOutputStream) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final af b()
  {
    return new an(this);
  }
  
  public final void b(int paramInt)
  {
    c localc = this.c;
    this.c = null;
    if (localc != null) {
      localc.d(paramInt);
    }
  }
  
  public final String c()
  {
    c localc = d();
    String str = null;
    if (localc != null) {
      str = localc.f;
    }
    return str;
  }
  
  public final void close()
  {
    this.b.close();
  }
  
  public final void flush()
  {
    this.b.flush();
  }
  
  public final void write(int paramInt)
  {
    this.b.write(paramInt);
    try
    {
      this.d.a(paramInt);
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
        this.d = as.d;
      }
    }
  }
  
  public final void write(byte[] paramArrayOfByte)
  {
    this.b.write(paramArrayOfByte);
    if (paramArrayOfByte != null) {
      a(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
  }
  
  public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.b.write(paramArrayOfByte, paramInt1, paramInt2);
    if (paramArrayOfByte != null) {
      a(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */