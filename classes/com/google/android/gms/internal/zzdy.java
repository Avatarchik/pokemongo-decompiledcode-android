package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzgr
public class zzdy
  extends zzdv
{
  private static final Set<String> zzyk = Collections.synchronizedSet(new HashSet());
  private static final DecimalFormat zzyl = new DecimalFormat("#,###");
  private File zzym;
  private boolean zzyn;
  
  public zzdy(zziz paramzziz)
  {
    super(paramzziz);
    File localFile = paramzziz.getContext().getCacheDir();
    if (localFile == null) {
      zzb.zzaH("Context.getCacheDir() returned null");
    }
    for (;;)
    {
      return;
      this.zzym = new File(localFile, "admobVideoStreams");
      if ((!this.zzym.isDirectory()) && (!this.zzym.mkdirs()))
      {
        zzb.zzaH("Could not create preload cache directory at " + this.zzym.getAbsolutePath());
        this.zzym = null;
      }
      else if ((!this.zzym.setReadable(true, false)) || (!this.zzym.setExecutable(true, false)))
      {
        zzb.zzaH("Could not set cache file permissions at " + this.zzym.getAbsolutePath());
        this.zzym = null;
      }
    }
  }
  
  private File zza(File paramFile)
  {
    return new File(this.zzym, paramFile.getName() + ".done");
  }
  
  private static void zzb(File paramFile)
  {
    if (paramFile.isFile()) {
      paramFile.setLastModified(System.currentTimeMillis());
    }
    for (;;)
    {
      return;
      try
      {
        paramFile.createNewFile();
      }
      catch (IOException localIOException) {}
    }
  }
  
  public void abort()
  {
    this.zzyn = true;
  }
  
  public boolean zzab(String paramString)
  {
    boolean bool;
    if (this.zzym == null)
    {
      zza(paramString, null, "noCacheDir", null);
      bool = false;
    }
    for (;;)
    {
      return bool;
      for (;;)
      {
        if (zzdK() > ((Integer)zzby.zzuy.get()).intValue()) {
          if (!zzdL())
          {
            zzb.zzaH("Unable to expire stream cache");
            zza(paramString, null, "expireFailed", null);
            bool = false;
            break;
          }
        }
      }
      String str1 = zzac(paramString);
      File localFile1 = new File(this.zzym, str1);
      File localFile2 = zza(localFile1);
      if ((localFile1.isFile()) && (localFile2.isFile()))
      {
        int i2 = (int)localFile1.length();
        zzb.zzaF("Stream cache hit at " + paramString);
        zza(paramString, localFile1.getAbsolutePath(), i2);
        bool = true;
        continue;
      }
      String str2 = this.zzym.getAbsolutePath() + paramString;
      URLConnection localURLConnection;
      int i;
      int i1;
      String str9;
      int j;
      String str4;
      int k;
      String str8;
      ReadableByteChannel localReadableByteChannel;
      FileChannel localFileChannel;
      ByteBuffer localByteBuffer;
      zzmn localzzmn;
      int m;
      long l1;
      long l2;
      zzik localzzik;
      long l3;
      int n;
      String str7;
      String str6;
      String str5;
      synchronized (zzyk)
      {
        if (zzyk.contains(str2))
        {
          zzb.zzaH("Stream cache already in progress at " + paramString);
          zza(paramString, localFile1.getAbsolutePath(), "inProgress", null);
          bool = false;
        }
      }
    }
  }
  
  public int zzdK()
  {
    int i = 0;
    if (this.zzym == null) {}
    for (;;)
    {
      return i;
      File[] arrayOfFile = this.zzym.listFiles();
      int j = arrayOfFile.length;
      for (int k = 0; k < j; k++) {
        if (!arrayOfFile[k].getName().endsWith(".done")) {
          i++;
        }
      }
    }
  }
  
  public boolean zzdL()
  {
    boolean bool1 = false;
    if (this.zzym == null) {
      return bool1;
    }
    Object localObject1 = null;
    long l1 = Long.MAX_VALUE;
    File[] arrayOfFile = this.zzym.listFiles();
    int i = arrayOfFile.length;
    int j = 0;
    label34:
    File localFile2;
    long l2;
    if (j < i)
    {
      localFile2 = arrayOfFile[j];
      if (localFile2.getName().endsWith(".done")) {
        break label138;
      }
      l2 = localFile2.lastModified();
      if (l2 >= l1) {
        break label138;
      }
    }
    for (Object localObject2 = localFile2;; localObject2 = localObject1)
    {
      j++;
      localObject1 = localObject2;
      l1 = l2;
      break label34;
      boolean bool2;
      if (localObject1 != null)
      {
        bool2 = ((File)localObject1).delete();
        File localFile1 = zza((File)localObject1);
        if (localFile1.isFile()) {
          bool2 &= localFile1.delete();
        }
      }
      for (;;)
      {
        bool1 = bool2;
        break;
        bool2 = false;
      }
      label138:
      l2 = l1;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */