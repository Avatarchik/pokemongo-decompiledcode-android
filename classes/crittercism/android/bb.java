package crittercism.android;

import com.crittercism.app.CrittercismConfig;
import java.util.List;

public final class bb
  extends CrittercismConfig
{
  private String b = "524c99a04002057fcd000001";
  private bn c;
  
  public bb(bn parambn, CrittercismConfig paramCrittercismConfig)
  {
    super(paramCrittercismConfig);
    this.c = parambn;
  }
  
  public final List a()
  {
    List localList = super.a();
    localList.add(this.c.b());
    return localList;
  }
  
  public final String b()
  {
    return this.c.a();
  }
  
  public final String c()
  {
    return this.c.b();
  }
  
  public final String d()
  {
    return this.c.d();
  }
  
  public final String e()
  {
    return this.c.c();
  }
  
  public final boolean equals(Object paramObject)
  {
    boolean bool;
    if (!(paramObject instanceof bb)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      bb localbb = (bb)paramObject;
      if ((super.equals(paramObject)) && (a(this.c.a(), localbb.c.a())) && (a(this.c.b(), localbb.c.b())) && (a(this.c.d(), localbb.c.d())) && (a(this.c.c(), localbb.c.c())) && (a(this.b, localbb.b))) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
  
  public final String f()
  {
    return this.b;
  }
  
  public final String g()
  {
    return this.a;
  }
  
  public final int hashCode()
  {
    return 31 * (31 * (31 * (31 * (31 * super.hashCode() + this.c.a().hashCode()) + this.c.b().hashCode()) + this.c.d().hashCode()) + this.c.c().hashCode()) + this.b.hashCode();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */