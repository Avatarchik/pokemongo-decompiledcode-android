package crittercism.android;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class bs
{
  public final File a;
  public String b;
  public List c;
  private cj d;
  private int e;
  private int f;
  private int g;
  private a h;
  private boolean i = false;
  
  public bs(Context paramContext, br parambr)
  {
    this(new File(paramContext.getFilesDir().getAbsolutePath() + "//com.crittercism//" + str), parambr.c(), parambr.d(), parambr.e(), parambr.b(), parambr.f());
  }
  
  private bs(File paramFile, a parama, cj paramcj, int paramInt1, int paramInt2, String paramString)
  {
    this.h = parama;
    this.d = paramcj;
    this.g = paramInt1;
    this.f = paramInt2;
    this.b = paramString;
    this.a = paramFile;
    paramFile.mkdirs();
    d();
    this.e = h().length;
    this.c = new LinkedList();
  }
  
  private boolean c(ch paramch)
  {
    boolean bool = false;
    localFile = new File(this.a, paramch.e());
    localObject1 = null;
    try
    {
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile));
      localObject1 = localBufferedOutputStream;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        new StringBuilder("Could not open output stream to : ").append(localFile);
        dx.a();
      }
    }
    for (;;)
    {
      try
      {
        paramch.a((OutputStream)localObject1);
      }
      catch (IOException localIOException2)
      {
        localFile.delete();
        dx.a("Unable to write to " + localFile.getAbsolutePath(), localIOException2);
        try
        {
          ((OutputStream)localObject1).close();
        }
        catch (IOException localIOException3)
        {
          localFile.delete();
          dx.a("Unable to close " + localFile.getAbsolutePath(), localIOException3);
        }
        break;
      }
      finally
      {
        try
        {
          ((OutputStream)localObject1).close();
          throw ((Throwable)localObject2);
        }
        catch (IOException localIOException1)
        {
          localFile.delete();
          dx.a("Unable to close " + localFile.getAbsolutePath(), localIOException1);
        }
        break;
      }
      try
      {
        ((OutputStream)localObject1).close();
        bool = true;
      }
      catch (IOException localIOException4)
      {
        localFile.delete();
        dx.a("Unable to close " + localFile.getAbsolutePath(), localIOException4);
        break;
      }
    }
    return bool;
  }
  
  private boolean d()
  {
    boolean bool = true;
    String str;
    if (!this.a.isDirectory())
    {
      this.i = bool;
      str = this.a.getAbsolutePath();
      if (this.a.exists()) {
        new IOException(str + " is not a directory");
      }
    }
    else
    {
      if (this.i) {
        break label101;
      }
    }
    for (;;)
    {
      return bool;
      new FileNotFoundException(str + " does not exist");
      break;
      label101:
      bool = false;
    }
  }
  
  private void e()
  {
    while ((b() > i()) && (f())) {}
  }
  
  private boolean f()
  {
    boolean bool = false;
    if (this.h == null) {}
    for (;;)
    {
      return bool;
      a locala = this.h;
      File[] arrayOfFile = g();
      File localFile = null;
      if (arrayOfFile.length > locala.a) {
        localFile = arrayOfFile[locala.a];
      }
      if ((localFile != null) && (localFile.delete())) {
        bool = true;
      }
    }
  }
  
  private File[] g()
  {
    File[] arrayOfFile = h();
    Arrays.sort(arrayOfFile);
    return arrayOfFile;
  }
  
  private File[] h()
  {
    File[] arrayOfFile = this.a.listFiles();
    if (arrayOfFile == null) {
      arrayOfFile = new File[0];
    }
    return arrayOfFile;
  }
  
  /**
   * @deprecated
   */
  private int i()
  {
    try
    {
      int j = this.f;
      return j;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final bs a(Context paramContext)
  {
    String str1 = this.a.getName();
    String str2 = str1 + "_" + UUID.randomUUID().toString();
    return new bs(new File(paramContext.getFilesDir().getAbsolutePath() + "//com.crittercism/pending/" + str2), this.h, this.d, this.g, this.f, this.b);
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public final void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 94	crittercism/android/bs:d	()Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: invokespecial 97	crittercism/android/bs:h	()[Ljava/io/File;
    //   18: astore_3
    //   19: iconst_0
    //   20: istore 4
    //   22: iload 4
    //   24: aload_3
    //   25: arraylength
    //   26: if_icmpge -15 -> 11
    //   29: aload_3
    //   30: iload 4
    //   32: aaload
    //   33: invokevirtual 146	java/io/File:delete	()Z
    //   36: pop
    //   37: iinc 4 1
    //   40: goto -18 -> 22
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	48	0	this	bs
    //   43	4	1	localObject	Object
    //   6	2	2	bool	boolean
    //   18	12	3	arrayOfFile	File[]
    //   20	18	4	j	int
    // Exception table:
    //   from	to	target	type
    //   2	7	43	finally
    //   14	37	43	finally
  }
  
  public final void a(bs parambs)
  {
    if (parambs == null) {}
    bs localbs1;
    for (;;)
    {
      return;
      int j = this.a.getName().compareTo(parambs.a.getName());
      if (j != 0)
      {
        if (j < 0)
        {
          localbs1 = parambs;
          label36:
          synchronized (this) {}
        }
        try
        {
          if ((!d()) || (!parambs.d()))
          {
            continue;
            localObject1 = finally;
            throw ((Throwable)localObject1);
            localbs1 = this;
            ??? = parambs;
            break label36;
          }
          File[] arrayOfFile = g();
          for (int k = 0; k < arrayOfFile.length; k++)
          {
            File localFile = new File(parambs.a, arrayOfFile[k].getName());
            arrayOfFile[k].renameTo(localFile);
          }
          parambs.e();
          Iterator localIterator = this.c.iterator();
          while (localIterator.hasNext()) {
            ((bt)localIterator.next()).d();
          }
        }
        finally {}
      }
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public final void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 94	crittercism/android/bs:d	()Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_1
    //   15: ifnull -4 -> 11
    //   18: new 31	java/io/File
    //   21: dup
    //   22: aload_0
    //   23: getfield 88	crittercism/android/bs:a	Ljava/io/File;
    //   26: invokevirtual 45	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   29: aload_1
    //   30: invokespecial 233	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   33: astore 4
    //   35: aload 4
    //   37: invokevirtual 159	java/io/File:exists	()Z
    //   40: ifeq -29 -> 11
    //   43: aload 4
    //   45: invokevirtual 146	java/io/File:delete	()Z
    //   48: pop
    //   49: goto -38 -> 11
    //   52: astore_2
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_2
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	bs
    //   0	57	1	paramString	String
    //   52	4	2	localObject	Object
    //   6	2	3	bool	boolean
    //   33	11	4	localFile	File
    // Exception table:
    //   from	to	target	type
    //   2	7	52	finally
    //   18	49	52	finally
  }
  
  /**
   * @deprecated
   */
  public final boolean a(ch paramch)
  {
    boolean bool1 = false;
    for (;;)
    {
      try
      {
        boolean bool2 = d();
        if (!bool2) {
          return bool1;
        }
        if (this.e >= this.g)
        {
          dx.b();
          continue;
        }
        j = b();
      }
      finally {}
      int j;
      if ((j != i()) || (f())) {
        if (j > i())
        {
          this.i = true;
        }
        else
        {
          boolean bool3 = c(paramch);
          if (bool3) {
            this.e = (1 + this.e);
          }
          synchronized (this.c)
          {
            Iterator localIterator = this.c.iterator();
            if (localIterator.hasNext()) {
              ((bt)localIterator.next()).c();
            }
          }
          bool1 = bool3;
        }
      }
    }
  }
  
  /**
   * @deprecated
   */
  public final int b()
  {
    try
    {
      int j = h().length;
      return j;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public final boolean b(ch paramch)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 94	crittercism/android/bs:d	()Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +11 -> 19
    //   11: iconst_0
    //   12: istore 6
    //   14: aload_0
    //   15: monitorexit
    //   16: iload 6
    //   18: ireturn
    //   19: new 31	java/io/File
    //   22: dup
    //   23: aload_0
    //   24: getfield 88	crittercism/android/bs:a	Ljava/io/File;
    //   27: aload_1
    //   28: invokeinterface 113 1 0
    //   33: invokespecial 116	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   36: invokevirtual 146	java/io/File:delete	()Z
    //   39: pop
    //   40: aload_0
    //   41: aload_1
    //   42: invokespecial 237	crittercism/android/bs:c	(Lcrittercism/android/ch;)Z
    //   45: istore 5
    //   47: iload 5
    //   49: istore 6
    //   51: goto -37 -> 14
    //   54: astore_2
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_2
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	bs
    //   0	59	1	paramch	ch
    //   54	4	2	localObject	Object
    //   6	2	3	bool1	boolean
    //   45	3	5	bool2	boolean
    //   12	38	6	bool3	boolean
    // Exception table:
    //   from	to	target	type
    //   2	7	54	finally
    //   19	47	54	finally
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public final List c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 242	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 243	java/util/ArrayList:<init>	()V
    //   9: astore_1
    //   10: aload_0
    //   11: invokespecial 94	crittercism/android/bs:d	()Z
    //   14: istore_3
    //   15: iload_3
    //   16: ifne +7 -> 23
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: areturn
    //   23: aload_0
    //   24: getfield 80	crittercism/android/bs:d	Lcrittercism/android/cj;
    //   27: pop
    //   28: aload_0
    //   29: invokespecial 172	crittercism/android/bs:g	()[Ljava/io/File;
    //   32: astore 5
    //   34: iconst_0
    //   35: istore 6
    //   37: iload 6
    //   39: aload 5
    //   41: arraylength
    //   42: if_icmpge -23 -> 19
    //   45: aload_1
    //   46: aload_0
    //   47: getfield 80	crittercism/android/bs:d	Lcrittercism/android/cj;
    //   50: aload 5
    //   52: iload 6
    //   54: aaload
    //   55: invokevirtual 248	crittercism/android/cj:a	(Ljava/io/File;)Lcrittercism/android/bq;
    //   58: invokeinterface 252 2 0
    //   63: pop
    //   64: iinc 6 1
    //   67: goto -30 -> 37
    //   70: astore_2
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_2
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	bs
    //   9	37	1	localArrayList	java.util.ArrayList
    //   70	4	2	localObject	Object
    //   14	2	3	bool	boolean
    //   32	19	5	arrayOfFile	File[]
    //   35	30	6	j	int
    // Exception table:
    //   from	to	target	type
    //   2	15	70	finally
    //   23	64	70	finally
  }
  
  public static class a
  {
    int a;
    
    public a(int paramInt)
    {
      this.a = paramInt;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */