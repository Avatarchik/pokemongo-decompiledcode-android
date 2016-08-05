package crittercism.android;

import com.crittercism.integrations.PluginException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bk
  implements ch
{
  public long a;
  public JSONArray b;
  public String c;
  public String d = "";
  public JSONArray e;
  public String f;
  public JSONObject g;
  private JSONObject h;
  private JSONArray i;
  private JSONArray j;
  private String k;
  private JSONArray l;
  private String m;
  private int n = -1;
  private boolean o = false;
  private String p;
  
  public bk(Throwable paramThrowable, long paramLong)
  {
    this.o = (paramThrowable instanceof PluginException);
    this.p = cg.a.a();
    this.f = "uhe";
    bu localbu = new bu();
    localbu.a(new bx.a()).a(new bx.c()).a(new bx.b()).a(new bx.d()).a(new bx.e()).a(new bx.f()).a(new bx.o()).a(new bx.p()).a(new bx.i()).a(new bx.j()).a(new bx.h()).a(new bx.z()).a(new bx.aa()).a(new bx.k()).a(new bx.l()).a(new bx.n()).a(new bx.m()).a(new bx.q()).a(new bx.r()).a(new bx.s()).a(new bx.t()).a(new bx.u()).a(new bx.v()).a(new bx.w()).a(new bx.x()).a(new bx.y());
    this.g = localbu.a();
    this.h = new JSONObject();
    this.a = paramLong;
    this.c = a(paramThrowable);
    if (paramThrowable.getMessage() != null) {
      this.d = paramThrowable.getMessage();
    }
    if (!this.o) {
      this.n = c(paramThrowable);
    }
    this.k = "android";
    this.m = ed.a.a();
    this.l = new JSONArray();
    String[] arrayOfString = b(paramThrowable);
    int i2 = arrayOfString.length;
    while (i1 < i2)
    {
      String str = arrayOfString[i1];
      this.l.put(str);
      i1++;
    }
  }
  
  private String a(Throwable paramThrowable)
  {
    if (this.o) {}
    String str;
    for (Object localObject = ((PluginException)paramThrowable).getExceptionName();; localObject = str)
    {
      return (String)localObject;
      Throwable localThrowable;
      do
      {
        paramThrowable = localThrowable;
        str = paramThrowable.getClass().getName();
        localThrowable = paramThrowable.getCause();
      } while ((localThrowable != null) && (localThrowable != paramThrowable));
    }
  }
  
  private static String[] b(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    for (;;)
    {
      paramThrowable.printStackTrace(localPrintWriter);
      Throwable localThrowable = paramThrowable.getCause();
      if ((localThrowable == null) || (localThrowable == paramThrowable)) {
        return localStringWriter.toString().split("\n");
      }
      paramThrowable = localThrowable;
    }
  }
  
  private static int c(Throwable paramThrowable)
  {
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    int i1 = 0;
    StackTraceElement localStackTraceElement;
    if (i1 < arrayOfStackTraceElement.length) {
      localStackTraceElement = arrayOfStackTraceElement[i1];
    }
    for (;;)
    {
      try
      {
        Class localClass = Class.forName(localStackTraceElement.getClassName());
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        if (localClassLoader == null) {
          break label86;
        }
        if (localClass.getClassLoader() == localClassLoader)
        {
          i3 = 1;
          if (i3 == 0)
          {
            i2 = i1 + 1;
            return i2;
          }
        }
        else
        {
          localClassLoader = localClassLoader.getParent();
          continue;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        i1++;
      }
      int i2 = -1;
      continue;
      label86:
      int i3 = 0;
    }
  }
  
  public final void a()
  {
    this.e = new JSONArray();
    Iterator localIterator = Thread.getAllStackTraces().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      HashMap localHashMap = new HashMap();
      Thread localThread = (Thread)localEntry.getKey();
      if (localThread.getId() != this.a)
      {
        localHashMap.put("name", localThread.getName());
        localHashMap.put("id", Long.valueOf(localThread.getId()));
        localHashMap.put("state", localThread.getState().name());
        localHashMap.put("stacktrace", new JSONArray(Arrays.asList((Object[])localEntry.getValue())));
        this.e.put(new JSONObject(localHashMap));
      }
    }
  }
  
  public final void a(bs parambs)
  {
    this.i = new bo(parambs).a;
  }
  
  public final void a(OutputStream paramOutputStream)
  {
    paramOutputStream.write(b().toString().getBytes());
  }
  
  public final void a(String paramString, bs parambs)
  {
    JSONArray localJSONArray = new bo(parambs).a;
    try
    {
      this.h.put(paramString, localJSONArray);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public final void a(List paramList)
  {
    this.j = new JSONArray();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      bg localbg = (bg)localIterator.next();
      try
      {
        this.j.put(localbg.j());
      }
      catch (JSONException localJSONException)
      {
        dx.a(localJSONException);
      }
    }
  }
  
  public final JSONObject b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("app_state", this.g);
    localHashMap.put("breadcrumbs", this.h);
    localHashMap.put("current_thread_id", Long.valueOf(this.a));
    if (this.i != null) {
      localHashMap.put("endpoints", this.i);
    }
    if (this.b != null) {
      localHashMap.put("systemBreadcrumbs", this.b);
    }
    if ((this.j != null) && (this.j.length() > 0)) {
      localHashMap.put("transactions", this.j);
    }
    localHashMap.put("exception_name", this.c);
    localHashMap.put("exception_reason", this.d);
    localHashMap.put("platform", this.k);
    if (this.e != null) {
      localHashMap.put("threads", this.e);
    }
    localHashMap.put("ts", this.m);
    String str = this.f;
    if (this.a != 1L) {
      str = str + "-bg";
    }
    localHashMap.put("type", str);
    localHashMap.put("unsymbolized_stacktrace", this.l);
    if (!this.o) {
      localHashMap.put("suspect_line_index", Integer.valueOf(this.n));
    }
    return new JSONObject(localHashMap);
  }
  
  public final String e()
  {
    return this.p;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */