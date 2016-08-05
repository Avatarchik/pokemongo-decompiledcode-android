package crittercism.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class bd
  extends BroadcastReceiver
{
  private az a;
  private String b;
  private b c;
  
  public bd(Context paramContext, az paramaz)
  {
    this.a = paramaz;
    d locald = new d(paramContext);
    this.b = locald.b();
    this.c = locald.a();
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    new StringBuilder("CrittercismReceiver: INTENT ACTION = ").append(paramIntent.getAction());
    dx.b();
    d locald = new d(paramContext);
    b localb = locald.a();
    String str;
    if ((this.c != localb) && (localb != b.c))
    {
      if (localb == b.d)
      {
        this.a.a(new ce(ce.a.b));
        this.c = localb;
      }
    }
    else
    {
      str = locald.b();
      if (!str.equals(this.b))
      {
        if ((!this.b.equals("unknown")) && (!this.b.equals("disconnected"))) {
          break label214;
        }
        if ((!str.equals("unknown")) && (!str.equals("disconnected"))) {
          this.a.a(new ce(ce.a.c, str));
        }
      }
    }
    for (;;)
    {
      this.b = str;
      return;
      if ((this.c != b.d) && (this.c != b.c)) {
        break;
      }
      this.a.a(new ce(ce.a.a));
      break;
      label214:
      if (str.equals("disconnected")) {
        this.a.a(new ce(ce.a.d, this.b));
      } else if (!str.equals("unknown")) {
        this.a.a(new ce(ce.a.e, this.b, str));
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */