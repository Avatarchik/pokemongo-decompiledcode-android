package crittercism.android;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public final class e
{
  List a = new LinkedList();
  final Set b = new HashSet();
  final Set c = new HashSet();
  private Executor d;
  
  public e(Executor paramExecutor)
  {
    this(paramExecutor, new LinkedList(), new LinkedList());
  }
  
  private e(Executor paramExecutor, List paramList1, List paramList2)
  {
    this.d = paramExecutor;
    a(paramList1);
    b(paramList2);
  }
  
  @Deprecated
  public final void a(c paramc)
  {
    a(paramc, c.a.e);
  }
  
  public final void a(c paramc, c.a parama)
  {
    if (paramc.b) {}
    for (;;)
    {
      return;
      paramc.b = true;
      paramc.c = parama;
      this.d.execute(new a(paramc, (byte)0));
    }
  }
  
  public final void a(f paramf)
  {
    synchronized (this.a)
    {
      this.a.add(paramf);
      return;
    }
  }
  
  public final void a(List paramList)
  {
    synchronized (this.b)
    {
      this.b.addAll(paramList);
      this.b.remove(null);
      return;
    }
  }
  
  public final void b(List paramList)
  {
    synchronized (this.c)
    {
      this.c.addAll(paramList);
      this.c.remove(null);
      return;
    }
  }
  
  class a
    implements Runnable
  {
    private c b;
    
    private a(c paramc)
    {
      this.b = paramc;
    }
    
    private boolean a(c paramc)
    {
      String str = paramc.a();
      synchronized (e.this.b)
      {
        Iterator localIterator = e.this.b.iterator();
        while (localIterator.hasNext()) {
          if (str.contains((String)localIterator.next()))
          {
            bool = true;
            return bool;
          }
        }
        boolean bool = false;
      }
    }
    
    private boolean a(String paramString)
    {
      synchronized (e.this.c)
      {
        Iterator localIterator = e.this.c.iterator();
        while (localIterator.hasNext()) {
          if (paramString.contains((String)localIterator.next()))
          {
            bool = false;
            return bool;
          }
        }
        boolean bool = true;
      }
    }
    
    public final void run()
    {
      if (a(this.b)) {}
      for (;;)
      {
        return;
        String str = this.b.a();
        if (a(str))
        {
          int i = str.indexOf("?");
          if (i != -1) {
            this.b.a(str.substring(0, i));
          }
        }
        synchronized (e.this.a)
        {
          Iterator localIterator = e.this.a.iterator();
          if (localIterator.hasNext()) {
            ((f)localIterator.next()).a(this.b);
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */