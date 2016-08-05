package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ah
  extends af
{
  private ai d;
  private int e;
  private int f = 0;
  
  public ah(ai paramai, int paramInt)
  {
    super(paramai);
    this.d = paramai;
    this.e = paramInt;
  }
  
  public final boolean a(int paramInt)
  {
    boolean bool = false;
    if (this.f >= 2 + this.e) {}
    for (;;)
    {
      return bool;
      if (paramInt == -1)
      {
        this.a.b(a());
        this.a.a(as.d);
        bool = true;
      }
      else
      {
        this.c = (1 + this.c);
        int i = (char)paramInt;
        this.f = (1 + this.f);
        if (this.f > this.e) {
          if (i == 10)
          {
            this.d.b(a());
            this.a.a(this.d);
            bool = true;
          }
          else if ((this.f == 2 + this.e) && (i != 10))
          {
            this.a.a(as.d);
            bool = true;
          }
        }
      }
    }
  }
  
  public final boolean a(CharArrayBuffer paramCharArrayBuffer)
  {
    return true;
  }
  
  public final af b()
  {
    return this.d;
  }
  
  public final af c()
  {
    return null;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */