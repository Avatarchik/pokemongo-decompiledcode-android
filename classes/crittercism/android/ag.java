package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ag
  extends af
{
  private int d;
  private int e = 0;
  
  public ag(af paramaf, int paramInt)
  {
    super(paramaf);
    this.d = paramInt;
  }
  
  public final boolean a(int paramInt)
  {
    boolean bool = true;
    if (paramInt == -1) {
      this.a.a(as.d);
    }
    for (;;)
    {
      return bool;
      this.e = (1 + this.e);
      this.c = (1 + this.c);
      if (this.e == this.d)
      {
        this.a.b(a());
        af localaf = this.a.b();
        this.a.a(localaf);
      }
      else
      {
        bool = false;
      }
    }
  }
  
  public final boolean a(CharArrayBuffer paramCharArrayBuffer)
  {
    return true;
  }
  
  public final int b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramInt2 == -1)
    {
      this.a.a(as.d);
      paramInt2 = -1;
    }
    for (;;)
    {
      return paramInt2;
      if (paramInt2 + this.e < this.d)
      {
        this.e = (paramInt2 + this.e);
        this.c = (paramInt2 + this.c);
      }
      else
      {
        paramInt2 = this.d - this.e;
        this.c = (paramInt2 + this.c);
        this.a.b(a());
        this.a.a(this.a.b());
      }
    }
  }
  
  public final af b()
  {
    return as.d;
  }
  
  public final af c()
  {
    return as.d;
  }
  
  protected final int d()
  {
    return 0;
  }
  
  protected final int e()
  {
    return 0;
  }
  
  public final void f()
  {
    this.a.b(a());
    this.a.a(as.d);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */