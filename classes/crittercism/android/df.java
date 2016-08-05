package crittercism.android;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

public final class df
{
  private Context a;
  private List b;
  
  public df(Context paramContext)
  {
    this.a = paramContext;
    this.b = new ArrayList();
  }
  
  public final void a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.b.iterator();
    while (localIterator1.hasNext()) {
      localArrayList.add(new Thread((di)localIterator1.next()));
    }
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext()) {
      ((Thread)localIterator2.next()).start();
    }
    Iterator localIterator3 = localArrayList.iterator();
    while (localIterator3.hasNext()) {
      ((Thread)localIterator3.next()).join();
    }
  }
  
  /**
   * @deprecated
   */
  public final void a(bs parambs, cz paramcz, String paramString1, String paramString2, String paramString3, au paramau, cx paramcx)
  {
    try
    {
      if (parambs.b() > 0)
      {
        bs localbs = parambs.a(this.a);
        cy localcy = paramcz.a(localbs, parambs, paramString3, this.a, paramau);
        dh localdh = new dh(localbs, parambs, paramau, new db(paramString1, paramString2).a(), localcy, paramcx);
        this.b.add(localdh);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final void a(dg paramdg, ExecutorService paramExecutorService)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      di localdi = (di)localIterator.next();
      if (!paramdg.a(localdi)) {
        paramExecutorService.execute(localdi);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/df.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */