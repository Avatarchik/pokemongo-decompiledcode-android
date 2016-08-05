package crittercism.android;

import android.os.Build.VERSION;
import android.os.ConditionVariable;
import android.os.Process;
import com.crittercism.app.Transaction;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bg
  extends Transaction
  implements ch
{
  private static ExecutorService b = Executors.newSingleThreadExecutor(new dz());
  private static ScheduledExecutorService c = Executors.newScheduledThreadPool(1, new dz());
  private static List o = new LinkedList();
  private static volatile long p = 0L;
  private static volatile long q = 0L;
  private static final int[] r;
  private static bg s = null;
  private static bh t = new bh();
  private String d;
  private long e = -1L;
  private int f;
  private long g;
  private long h;
  private long i;
  private a j;
  private Map k;
  private String l;
  private long m;
  private ScheduledFuture n;
  
  static
  {
    int[] arrayOfInt = new int[22];
    arrayOfInt[0] = 32;
    arrayOfInt[1] = 544;
    arrayOfInt[2] = 32;
    arrayOfInt[3] = 32;
    arrayOfInt[4] = 32;
    arrayOfInt[5] = 32;
    arrayOfInt[6] = 32;
    arrayOfInt[7] = 32;
    arrayOfInt[8] = 32;
    arrayOfInt[9] = 32;
    arrayOfInt[10] = 32;
    arrayOfInt[11] = 32;
    arrayOfInt[12] = 32;
    arrayOfInt[13] = 32;
    arrayOfInt[14] = 32;
    arrayOfInt[15] = 32;
    arrayOfInt[16] = 32;
    arrayOfInt[17] = 32;
    arrayOfInt[18] = 32;
    arrayOfInt[19] = 32;
    arrayOfInt[20] = 32;
    arrayOfInt[21] = 8224;
    r = arrayOfInt;
  }
  
  public bg(az paramaz, String paramString)
  {
    this.f = i1;
    this.n = null;
    if (paramString.length() > 255) {
      dx.c("Transaction name exceeds 255 characters! Truncating to first 255 characters.");
    }
    for (this.d = paramString.substring(0, 255);; this.d = paramString)
    {
      this.j = a.a;
      this.k = new HashMap();
      this.a = paramaz;
      this.l = cg.a.a();
      this.e = -1L;
      JSONObject localJSONObject = t.d.optJSONObject(paramString);
      if (localJSONObject != null) {
        i1 = localJSONObject.optInt("value", i1);
      }
      this.f = i1;
      return;
    }
  }
  
  private bg(bg parambg)
  {
    this.f = -1;
    this.n = null;
    this.d = parambg.d;
    this.e = parambg.e;
    this.f = parambg.f;
    this.g = parambg.g;
    this.h = parambg.h;
    this.j = parambg.j;
    this.k = parambg.k;
    this.l = parambg.l;
    this.i = parambg.i;
    this.m = parambg.m;
  }
  
  public bg(JSONArray paramJSONArray)
  {
    this.f = -1;
    this.n = null;
    this.d = paramJSONArray.getString(0);
    this.j = a.values()[paramJSONArray.getInt(1)];
    this.e = ((int)(1000.0D * paramJSONArray.getDouble(2)));
    this.f = paramJSONArray.optInt(3, -1);
    this.k = new HashMap();
    JSONObject localJSONObject = paramJSONArray.getJSONObject(4);
    Iterator localIterator = localJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      this.k.put(str, localJSONObject.getString(str));
    }
    this.g = ed.a.a(paramJSONArray.getString(5));
    this.h = ed.a.a(paramJSONArray.getString(6));
    this.i = ((paramJSONArray.optDouble(7, 0.0D) * Math.pow(10.0D, 9.0D)));
    this.l = cg.a.a();
  }
  
  public static List a(az paramaz, boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    long l1;
    long l2;
    int i1;
    long l3;
    FutureTask localFutureTask;
    synchronized (o)
    {
      localLinkedList.addAll(o);
      l1 = System.currentTimeMillis();
      l2 = System.nanoTime();
      i1 = -1 + localLinkedList.size();
      if (i1 < 0) {}
    }
  }
  
  private void a(long paramLong)
  {
    if (l()) {
      this.n = c.schedule(new di()
      {
        public final void a()
        {
          bg.b(bg.this);
        }
      }, paramLong, TimeUnit.MILLISECONDS);
    }
  }
  
  public static void a(aw paramaw)
  {
    bs localbs;
    try
    {
      localbs = paramaw.w();
      List localList = localbs.c();
      l1 = System.currentTimeMillis();
      Iterator localIterator = localList.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localJSONArray = (JSONArray)((bz)localIterator.next()).a();
      } while (localJSONArray == null);
    }
    catch (ThreadDeath localThreadDeath)
    {
      for (;;)
      {
        try
        {
          long l1;
          JSONArray localJSONArray;
          bg localbg = new bg(localJSONArray);
          localbg.h = l1;
          localbg.j = a.h;
          paramaw.x().a(localbg);
        }
        catch (JSONException localJSONException)
        {
          dx.a(localJSONException);
          continue;
          localThreadDeath = localThreadDeath;
          throw localThreadDeath;
        }
        catch (ParseException localParseException)
        {
          dx.a(localParseException);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
    }
    for (;;)
    {
      return;
      localbs.a();
    }
  }
  
  public static void a(final az paramaz)
  {
    q = System.nanoTime();
    LinkedList localLinkedList = new LinkedList();
    for (;;)
    {
      synchronized (o)
      {
        localLinkedList.addAll(o);
        Iterator localIterator = localLinkedList.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        synchronized ((bg)localIterator.next())
        {
          if (???.j == a.b)
          {
            if (???.m < p) {
              break label144;
            }
            if (???.m <= q) {
              ???.i += q - ???.m;
            }
          }
          ???.r();
        }
      }
      label144:
      ???.i += q - p;
    }
    FutureTask localFutureTask = new FutureTask(new di()
    {
      public final void a()
      {
        Iterator localIterator = bg.this.iterator();
        while (localIterator.hasNext()) {
          synchronized ((bg)localIterator.next())
          {
            if (bg.a(???) == bg.a.b) {
              paramaz.n.b(???);
            }
          }
        }
      }
    }, null);
    synchronized (b)
    {
      b.execute(localFutureTask);
    }
    try
    {
      localFutureTask.get();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        dx.a(localInterruptedException);
      }
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;)
      {
        dx.a(localExecutionException);
      }
    }
  }
  
  private void a(a parama)
  {
    if (((parama == a.c) || (parama == a.e)) || (this.j == a.b))
    {
      r();
      b(parama);
    }
    for (;;)
    {
      return;
      if (this.j != a.f) {
        dx.b("Transaction " + this.d + " is not running. Either it has not been started or it has been stopped.", new IllegalStateException("Transaction is not running"));
      }
    }
  }
  
  public static void a(bh parambh)
  {
    t = parambh;
  }
  
  /**
   * @deprecated
   */
  private void b(int paramInt)
  {
    if (paramInt < 0) {}
    for (;;)
    {
      try
      {
        dx.c("Ignoring Transaction.setValue(int) call. Negative parameter provided.");
        return;
      }
      finally {}
      if (this.j == a.a)
      {
        this.f = paramInt;
      }
      else
      {
        if (this.j == a.b)
        {
          this.f = paramInt;
          di local7 = new di()
          {
            public final void a()
            {
              bg.this.a.q.a.block();
              bg.this.a.n.a(this.a);
            }
          };
          synchronized (b)
          {
            b.execute(local7);
          }
        }
        dx.b("Transaction " + this.d + " no longer in progress. Ignoring setValue(int) call.", new IllegalStateException("Transaction no longer in progress"));
      }
    }
  }
  
  /* Error */
  public static void b(az paramaz)
  {
    // Byte code:
    //   0: new 2	crittercism/android/bg
    //   3: dup
    //   4: aload_0
    //   5: ldc_w 421
    //   8: invokespecial 423	crittercism/android/bg:<init>	(Lcrittercism/android/az;Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_1
    //   13: putstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   16: aload_1
    //   17: monitorenter
    //   18: invokestatic 425	crittercism/android/bg:m	()J
    //   21: lstore 5
    //   23: lload 5
    //   25: ldc2_w 96
    //   28: lcmp
    //   29: ifeq +176 -> 205
    //   32: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   35: getstatic 271	crittercism/android/bg$a:b	Lcrittercism/android/bg$a;
    //   38: putfield 126	crittercism/android/bg:j	Lcrittercism/android/bg$a;
    //   41: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   44: astore 7
    //   46: invokestatic 430	android/os/SystemClock:elapsedRealtime	()J
    //   49: lstore 8
    //   51: aload 7
    //   53: invokestatic 259	java/lang/System:currentTimeMillis	()J
    //   56: lload 8
    //   58: lload 5
    //   60: lsub
    //   61: lsub
    //   62: putfield 162	crittercism/android/bg:g	J
    //   65: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   68: astore 10
    //   70: getstatic 433	java/util/concurrent/TimeUnit:NANOSECONDS	Ljava/util/concurrent/TimeUnit;
    //   73: lload 5
    //   75: getstatic 323	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   78: invokevirtual 437	java/util/concurrent/TimeUnit:convert	(JLjava/util/concurrent/TimeUnit;)J
    //   81: lstore 11
    //   83: getstatic 433	java/util/concurrent/TimeUnit:NANOSECONDS	Ljava/util/concurrent/TimeUnit;
    //   86: invokestatic 430	android/os/SystemClock:elapsedRealtime	()J
    //   89: getstatic 323	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   92: invokevirtual 437	java/util/concurrent/TimeUnit:convert	(JLjava/util/concurrent/TimeUnit;)J
    //   95: lstore 13
    //   97: aload 10
    //   99: invokestatic 262	java/lang/System:nanoTime	()J
    //   102: lload 13
    //   104: lload 11
    //   106: lsub
    //   107: lsub
    //   108: putfield 168	crittercism/android/bg:m	J
    //   111: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   114: getstatic 93	crittercism/android/bg:t	Lcrittercism/android/bh;
    //   117: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   120: getfield 122	crittercism/android/bg:d	Ljava/lang/String;
    //   123: invokevirtual 438	crittercism/android/bh:a	(Ljava/lang/String;)J
    //   126: putfield 99	crittercism/android/bg:e	J
    //   129: getstatic 80	crittercism/android/bg:o	Ljava/util/List;
    //   132: astore 15
    //   134: aload 15
    //   136: monitorenter
    //   137: getstatic 80	crittercism/android/bg:o	Ljava/util/List;
    //   140: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   143: invokeinterface 442 2 0
    //   148: pop
    //   149: aload 15
    //   151: monitorexit
    //   152: new 12	crittercism/android/bg$3
    //   155: dup
    //   156: aload_0
    //   157: new 2	crittercism/android/bg
    //   160: dup
    //   161: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   164: invokespecial 412	crittercism/android/bg:<init>	(Lcrittercism/android/bg;)V
    //   167: invokespecial 445	crittercism/android/bg$3:<init>	(Lcrittercism/android/az;Lcrittercism/android/bg;)V
    //   170: astore 18
    //   172: getstatic 69	crittercism/android/bg:b	Ljava/util/concurrent/ExecutorService;
    //   175: astore 19
    //   177: aload 19
    //   179: monitorenter
    //   180: getstatic 69	crittercism/android/bg:b	Ljava/util/concurrent/ExecutorService;
    //   183: aload 18
    //   185: invokeinterface 298 2 0
    //   190: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   193: getstatic 88	crittercism/android/bg:s	Lcrittercism/android/bg;
    //   196: getfield 99	crittercism/android/bg:e	J
    //   199: invokespecial 447	crittercism/android/bg:a	(J)V
    //   202: aload 19
    //   204: monitorexit
    //   205: aload_1
    //   206: monitorexit
    //   207: goto +34 -> 241
    //   210: astore 16
    //   212: aload 15
    //   214: monitorexit
    //   215: aload 16
    //   217: athrow
    //   218: astore 4
    //   220: aload_1
    //   221: monitorexit
    //   222: aload 4
    //   224: athrow
    //   225: astore_3
    //   226: aload_3
    //   227: athrow
    //   228: astore 20
    //   230: aload 19
    //   232: monitorexit
    //   233: aload 20
    //   235: athrow
    //   236: astore_2
    //   237: aload_2
    //   238: invokestatic 314	crittercism/android/dx:a	(Ljava/lang/Throwable;)V
    //   241: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	paramaz	az
    //   11	210	1	localbg1	bg
    //   236	2	2	localThrowable	Throwable
    //   225	2	3	localThreadDeath	ThreadDeath
    //   218	5	4	localObject1	Object
    //   21	53	5	l1	long
    //   44	8	7	localbg2	bg
    //   49	8	8	l2	long
    //   68	30	10	localbg3	bg
    //   81	24	11	l3	long
    //   95	8	13	l4	long
    //   210	6	16	localObject2	Object
    //   170	14	18	local3	3
    //   228	6	20	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   137	152	210	finally
    //   18	137	218	finally
    //   152	180	218	finally
    //   205	218	218	finally
    //   230	236	218	finally
    //   0	18	225	java/lang/ThreadDeath
    //   220	225	225	java/lang/ThreadDeath
    //   180	205	228	finally
    //   0	18	236	java/lang/Throwable
    //   220	225	236	java/lang/Throwable
  }
  
  private void b(a parama)
  {
    this.j = parama;
    this.h = System.currentTimeMillis();
    long l1 = System.nanoTime();
    if (l())
    {
      long l2 = Math.max(p, this.m);
      this.i += l1 - l2;
    }
    di local6;
    synchronized (o)
    {
      o.remove(this);
      local6 = new di()
      {
        public final void a()
        {
          FutureTask localFutureTask;
          if (bg.a(this.a) != bg.a.c)
          {
            Runnable local1 = new Runnable()
            {
              public final void run() {}
            };
            ExecutorService localExecutorService = bg.this.a.s;
            localFutureTask = new FutureTask(local1, null);
            localExecutorService.execute(localFutureTask);
          }
          try
          {
            localFutureTask.get();
            bg.this.a.q.a.block();
            bg.this.a.n.a(bg.c(bg.this));
            bg.this.a.o.a(this.a);
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;)
            {
              dx.a(localInterruptedException);
            }
          }
          catch (ExecutionException localExecutionException)
          {
            for (;;)
            {
              dx.a(localExecutionException);
            }
          }
        }
      };
    }
    synchronized (b)
    {
      b.execute(local6);
      return;
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  public static void f()
  {
    p = System.nanoTime();
    LinkedList localLinkedList = new LinkedList();
    synchronized (o)
    {
      localLinkedList.addAll(o);
      if ((s == null) || (q != 0L)) {}
    }
    for (;;)
    {
      synchronized (s)
      {
        bg localbg3 = s;
        localbg3.i += p - s.m;
        Iterator localIterator = localLinkedList.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        synchronized ((bg)localIterator.next())
        {
          if (???.j == a.b)
          {
            if ((???.n == null) || (!???.n.isCancelled())) {
              break label200;
            }
            ???.a(???.e - TimeUnit.MILLISECONDS.convert(???.i, TimeUnit.NANOSECONDS));
          }
        }
        localObject1 = finally;
        throw ((Throwable)localObject1);
      }
      label200:
      if (???.n == null) {
        ???.a(???.e);
      }
    }
  }
  
  public static void g()
  {
    try
    {
      if (s != null) {
        s.b();
      }
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
  
  public static void i()
  {
    LinkedList localLinkedList = new LinkedList();
    synchronized (o)
    {
      localLinkedList.addAll(o);
      Iterator localIterator = localLinkedList.iterator();
      while (localIterator.hasNext()) {
        synchronized ((bg)localIterator.next())
        {
          if (???.j == a.b)
          {
            ???.e = t.a(???.d);
            ???.r();
            ???.a(???.e);
          }
        }
      }
    }
  }
  
  private static boolean l()
  {
    if (p > q) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static long m()
  {
    arrayOfLong = new long[1];
    int i1 = Process.myPid();
    String str = "/proc/" + i1 + "/stat";
    try
    {
      Class[] arrayOfClass = new Class[5];
      arrayOfClass[0] = String.class;
      arrayOfClass[1] = int[].class;
      arrayOfClass[2] = String[].class;
      arrayOfClass[3] = long[].class;
      arrayOfClass[4] = float[].class;
      Method localMethod = Process.class.getDeclaredMethod("readProcFile", arrayOfClass);
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = str;
      arrayOfObject[1] = r;
      arrayOfObject[2] = null;
      arrayOfObject[3] = arrayOfLong;
      arrayOfObject[4] = null;
      boolean bool = ((Boolean)localMethod.invoke(null, arrayOfObject)).booleanValue();
      if (bool) {
        break label205;
      }
      l1 = -1L;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        dx.a(localNoSuchMethodException);
        l1 = -1L;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        dx.a(localIllegalArgumentException);
        l1 = -1L;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        dx.a(localIllegalAccessException);
        l1 = -1L;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        dx.a(localInvocationTargetException);
        long l1 = -1L;
        continue;
        l1 = 10L * arrayOfLong[0];
      }
    }
    return l1;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  private void n()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 126	crittercism/android/bg:j	Lcrittercism/android/bg$a;
    //   6: getstatic 124	crittercism/android/bg$a:a	Lcrittercism/android/bg$a;
    //   9: if_acmpne +124 -> 133
    //   12: aload_0
    //   13: getstatic 271	crittercism/android/bg$a:b	Lcrittercism/android/bg$a;
    //   16: putfield 126	crittercism/android/bg:j	Lcrittercism/android/bg$a;
    //   19: aload_0
    //   20: invokestatic 259	java/lang/System:currentTimeMillis	()J
    //   23: putfield 162	crittercism/android/bg:g	J
    //   26: aload_0
    //   27: invokestatic 262	java/lang/System:nanoTime	()J
    //   30: putfield 168	crittercism/android/bg:m	J
    //   33: aload_0
    //   34: getstatic 93	crittercism/android/bg:t	Lcrittercism/android/bh;
    //   37: aload_0
    //   38: getfield 122	crittercism/android/bg:d	Ljava/lang/String;
    //   41: invokevirtual 438	crittercism/android/bh:a	(Ljava/lang/String;)J
    //   44: putfield 99	crittercism/android/bg:e	J
    //   47: getstatic 80	crittercism/android/bg:o	Ljava/util/List;
    //   50: astore_2
    //   51: aload_2
    //   52: monitorenter
    //   53: getstatic 80	crittercism/android/bg:o	Ljava/util/List;
    //   56: aload_0
    //   57: invokeinterface 442 2 0
    //   62: pop
    //   63: aload_2
    //   64: monitorexit
    //   65: new 14	crittercism/android/bg$4
    //   68: dup
    //   69: aload_0
    //   70: new 2	crittercism/android/bg
    //   73: dup
    //   74: aload_0
    //   75: invokespecial 412	crittercism/android/bg:<init>	(Lcrittercism/android/bg;)V
    //   78: invokespecial 511	crittercism/android/bg$4:<init>	(Lcrittercism/android/bg;Lcrittercism/android/bg;)V
    //   81: astore 5
    //   83: getstatic 69	crittercism/android/bg:b	Ljava/util/concurrent/ExecutorService;
    //   86: astore 6
    //   88: aload 6
    //   90: monitorenter
    //   91: getstatic 69	crittercism/android/bg:b	Ljava/util/concurrent/ExecutorService;
    //   94: aload 5
    //   96: invokeinterface 298 2 0
    //   101: aload_0
    //   102: aload_0
    //   103: getfield 99	crittercism/android/bg:e	J
    //   106: invokespecial 447	crittercism/android/bg:a	(J)V
    //   109: aload 6
    //   111: monitorexit
    //   112: aload_0
    //   113: monitorexit
    //   114: return
    //   115: astore_3
    //   116: aload_2
    //   117: monitorexit
    //   118: aload_3
    //   119: athrow
    //   120: astore_1
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_1
    //   124: athrow
    //   125: astore 7
    //   127: aload 6
    //   129: monitorexit
    //   130: aload 7
    //   132: athrow
    //   133: new 386	java/lang/StringBuilder
    //   136: dup
    //   137: ldc_w 388
    //   140: invokespecial 390	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   143: aload_0
    //   144: getfield 122	crittercism/android/bg:d	Ljava/lang/String;
    //   147: invokevirtual 394	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: ldc_w 513
    //   153: invokevirtual 394	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 399	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: new 401	java/lang/IllegalStateException
    //   162: dup
    //   163: ldc_w 515
    //   166: invokespecial 404	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   169: invokestatic 407	crittercism/android/dx:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   172: goto -60 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	this	bg
    //   120	4	1	localObject1	Object
    //   115	4	3	localObject2	Object
    //   81	14	5	local4	4
    //   125	6	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   53	65	115	finally
    //   2	53	120	finally
    //   65	91	120	finally
    //   116	120	120	finally
    //   127	172	120	finally
    //   91	112	125	finally
  }
  
  /**
   * @deprecated
   */
  private void o()
  {
    try
    {
      a(a.c);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  private void p()
  {
    try
    {
      a(a.e);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  private void q()
  {
    try
    {
      a(a.i);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  private void r()
  {
    try
    {
      if (this.n != null) {
        this.n.cancel(false);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  private void s()
  {
    try
    {
      if (this.j == a.b) {
        b(a.f);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  private int t()
  {
    try
    {
      int i1 = this.f;
      return i1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final void a()
  {
    try
    {
      n();
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
  
  public final void a(int paramInt)
  {
    try
    {
      b(paramInt);
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
  
  public final void a(OutputStream paramOutputStream)
  {
    Object localObject = null;
    try
    {
      JSONArray localJSONArray = j();
      localObject = localJSONArray;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
    if (localObject != null) {
      paramOutputStream.write(((JSONArray)localObject).toString().getBytes());
    }
  }
  
  public final void b()
  {
    try
    {
      o();
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
  
  public final void c()
  {
    try
    {
      p();
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
  
  public final int d()
  {
    try
    {
      int i2 = t();
      i1 = i2;
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
        int i1 = -1;
      }
    }
    return i1;
  }
  
  public final String e()
  {
    return this.l;
  }
  
  public final void h()
  {
    try
    {
      q();
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
  
  public final JSONArray j()
  {
    JSONArray localJSONArray1 = new JSONArray().put(this.d).put(this.j.ordinal()).put(this.e / 1000.0D);
    Object localObject;
    JSONArray localJSONArray2;
    if (this.f == -1)
    {
      localObject = JSONObject.NULL;
      localJSONArray2 = localJSONArray1.put(localObject).put(new JSONObject(this.k)).put(ed.a.a(new Date(this.g))).put(ed.a.a(new Date(this.h)));
      if (Build.VERSION.SDK_INT < 14) {
        break label163;
      }
      localJSONArray2.put(Math.round(1000.0D * (this.i / Math.pow(10.0D, 9.0D))) / 1000.0D);
    }
    for (;;)
    {
      return localJSONArray2;
      localObject = Integer.valueOf(this.f);
      break;
      label163:
      localJSONArray2.put(JSONObject.NULL);
    }
  }
  
  public final a k()
  {
    return this.j;
  }
  
  static enum a
  {
    static
    {
      a[] arrayOfa = new a[9];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      arrayOfa[3] = d;
      arrayOfa[4] = e;
      arrayOfa[5] = f;
      arrayOfa[6] = g;
      arrayOfa[7] = h;
      arrayOfa[8] = i;
      j = arrayOfa;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */