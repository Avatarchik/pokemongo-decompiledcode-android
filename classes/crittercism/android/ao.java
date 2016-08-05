package crittercism.android;

public final class ao
  extends ak
{
  private int g;
  
  public ao(af paramaf, int paramInt)
  {
    super(paramaf);
    this.g = paramInt;
  }
  
  protected final af g()
  {
    int i;
    Object localObject;
    if ((this.a.c().equals("HEAD")) || ((this.g >= 100) && (this.g <= 199)) || (this.g == 204) || (this.g == 304))
    {
      i = 1;
      if (i == 0) {
        break label92;
      }
      this.a.b(a());
      localObject = this.a.b();
    }
    for (;;)
    {
      return (af)localObject;
      i = 0;
      break;
      label92:
      if (this.f)
      {
        localObject = new ai(this);
      }
      else if (this.d)
      {
        if (this.e > 0)
        {
          localObject = new ag(this, this.e);
        }
        else
        {
          this.a.b(a());
          localObject = this.a.b();
        }
      }
      else if (this.a.c().equals("CONNECT"))
      {
        this.a.b(a());
        localObject = this.a.b();
      }
      else
      {
        localObject = new aj(this);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */