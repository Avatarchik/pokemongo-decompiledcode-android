package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzmg;
import com.google.android.gms.internal.zzmx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object zzadG = new Object();
  private static HashSet<Uri> zzadH = new HashSet();
  private static ImageManager zzadI;
  private static ImageManager zzadJ;
  private final Context mContext;
  private final Handler mHandler;
  private final ExecutorService zzadK;
  private final zzb zzadL;
  private final zzlv zzadM;
  private final Map<zza, ImageReceiver> zzadN;
  private final Map<Uri, ImageReceiver> zzadO;
  private final Map<Uri, Long> zzadP;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.zzadK = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      this.zzadL = new zzb(this.mContext);
      if (zzmx.zzqx()) {
        zzoB();
      }
    }
    for (;;)
    {
      this.zzadM = new zzlv();
      this.zzadN = new HashMap();
      this.zzadO = new HashMap();
      this.zzadP = new HashMap();
      return;
      this.zzadL = null;
    }
  }
  
  public static ImageManager create(Context paramContext)
  {
    return zzb(paramContext, false);
  }
  
  private Bitmap zza(zza.zza paramzza)
  {
    if (this.zzadL == null) {}
    for (Bitmap localBitmap = null;; localBitmap = (Bitmap)this.zzadL.get(paramzza)) {
      return localBitmap;
    }
  }
  
  public static ImageManager zzb(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      if (zzadJ == null) {
        zzadJ = new ImageManager(paramContext, true);
      }
    }
    for (ImageManager localImageManager = zzadJ;; localImageManager = zzadI)
    {
      return localImageManager;
      if (zzadI == null) {
        zzadI = new ImageManager(paramContext, false);
      }
    }
  }
  
  private void zzoB()
  {
    this.mContext.registerComponentCallbacks(new zze(this.zzadL));
  }
  
  public void loadImage(ImageView paramImageView, int paramInt)
  {
    zza(new zza.zzb(paramImageView, paramInt));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    zza(new zza.zzb(paramImageView, paramUri));
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    zza.zzb localzzb = new zza.zzb(paramImageView, paramUri);
    localzzb.zzby(paramInt);
    zza(localzzb);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    zza(new zza.zzc(paramOnImageLoadedListener, paramUri));
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    zza.zzc localzzc = new zza.zzc(paramOnImageLoadedListener, paramUri);
    localzzc.zzby(paramInt);
    zza(localzzc);
  }
  
  public void zza(zza paramzza)
  {
    zzb.zzci("ImageManager.loadImage() must be called in the main thread");
    new zzd(paramzza).run();
  }
  
  private static final class zza
  {
    static int zza(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }
  
  private static final class zzb
    extends zzmg<zza.zza, Bitmap>
  {
    public zzb(Context paramContext)
    {
      super();
    }
    
    private static int zzaj(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      int i;
      if ((0x100000 & paramContext.getApplicationInfo().flags) != 0)
      {
        i = 1;
        if ((i == 0) || (!zzmx.zzqu())) {
          break label55;
        }
      }
      label55:
      for (int j = ImageManager.zza.zza(localActivityManager);; j = localActivityManager.getMemoryClass())
      {
        return (int)(0.33F * (j * 1048576));
        i = 0;
        break;
      }
    }
    
    protected int zza(zza.zza paramzza, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }
    
    protected void zza(boolean paramBoolean, zza.zza paramzza, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, paramzza, paramBitmap1, paramBitmap2);
    }
  }
  
  private static final class zze
    implements ComponentCallbacks2
  {
    private final ImageManager.zzb zzadL;
    
    public zze(ImageManager.zzb paramzzb)
    {
      this.zzadL = paramzzb;
    }
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory()
    {
      this.zzadL.evictAll();
    }
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt >= 60) {
        this.zzadL.evictAll();
      }
      for (;;)
      {
        return;
        if (paramInt >= 20) {
          this.zzadL.trimToSize(this.zzadL.size() / 2);
        }
      }
    }
  }
  
  private final class zzf
    implements Runnable
  {
    private final Bitmap mBitmap;
    private final Uri mUri;
    private boolean zzadU;
    private final CountDownLatch zzoS;
    
    public zzf(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      this.mUri = paramUri;
      this.mBitmap = paramBitmap;
      this.zzadU = paramBoolean;
      this.zzoS = paramCountDownLatch;
    }
    
    private void zza(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
    {
      ArrayList localArrayList = ImageManager.ImageReceiver.zza(paramImageReceiver);
      int i = localArrayList.size();
      int j = 0;
      if (j < i)
      {
        zza localzza = (zza)localArrayList.get(j);
        if (paramBoolean) {
          localzza.zza(ImageManager.zzb(ImageManager.this), this.mBitmap, false);
        }
        for (;;)
        {
          if (!(localzza instanceof zza.zzc)) {
            ImageManager.zza(ImageManager.this).remove(localzza);
          }
          j++;
          break;
          ImageManager.zzd(ImageManager.this).put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
          localzza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), false);
        }
      }
    }
    
    public void run()
    {
      zzb.zzci("OnBitmapLoadedRunnable must be executed in the main thread");
      boolean bool;
      if (this.mBitmap != null)
      {
        bool = true;
        if (ImageManager.zzh(ImageManager.this) == null) {
          break label97;
        }
        if (!this.zzadU) {
          break label67;
        }
        ImageManager.zzh(ImageManager.this).evictAll();
        System.gc();
        this.zzadU = false;
        ImageManager.zzg(ImageManager.this).post(this);
      }
      for (;;)
      {
        return;
        bool = false;
        break;
        label67:
        if (bool) {
          ImageManager.zzh(ImageManager.this).put(new zza.zza(this.mUri), this.mBitmap);
        }
        label97:
        ImageManager.ImageReceiver localImageReceiver = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).remove(this.mUri);
        if (localImageReceiver != null) {
          zza(localImageReceiver, bool);
        }
        this.zzoS.countDown();
        synchronized (ImageManager.zzoC())
        {
          ImageManager.zzoD().remove(this.mUri);
        }
      }
    }
  }
  
  private final class zzc
    implements Runnable
  {
    private final Uri mUri;
    private final ParcelFileDescriptor zzadS;
    
    public zzc(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      this.mUri = paramUri;
      this.zzadS = paramParcelFileDescriptor;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 35
      //   2: invokestatic 41	com/google/android/gms/common/internal/zzb:zzcj	(Ljava/lang/String;)V
      //   5: iconst_0
      //   6: istore_1
      //   7: aconst_null
      //   8: astore_2
      //   9: aload_0
      //   10: getfield 26	com/google/android/gms/common/images/ImageManager$zzc:zzadS	Landroid/os/ParcelFileDescriptor;
      //   13: ifnull +25 -> 38
      //   16: aload_0
      //   17: getfield 26	com/google/android/gms/common/images/ImageManager$zzc:zzadS	Landroid/os/ParcelFileDescriptor;
      //   20: invokevirtual 47	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
      //   23: invokestatic 53	android/graphics/BitmapFactory:decodeFileDescriptor	(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
      //   26: astore 11
      //   28: aload 11
      //   30: astore_2
      //   31: aload_0
      //   32: getfield 26	com/google/android/gms/common/images/ImageManager$zzc:zzadS	Landroid/os/ParcelFileDescriptor;
      //   35: invokevirtual 56	android/os/ParcelFileDescriptor:close	()V
      //   38: new 58	java/util/concurrent/CountDownLatch
      //   41: dup
      //   42: iconst_1
      //   43: invokespecial 61	java/util/concurrent/CountDownLatch:<init>	(I)V
      //   46: astore_3
      //   47: aload_0
      //   48: getfield 19	com/google/android/gms/common/images/ImageManager$zzc:zzadR	Lcom/google/android/gms/common/images/ImageManager;
      //   51: invokestatic 65	com/google/android/gms/common/images/ImageManager:zzg	(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
      //   54: new 67	com/google/android/gms/common/images/ImageManager$zzf
      //   57: dup
      //   58: aload_0
      //   59: getfield 19	com/google/android/gms/common/images/ImageManager$zzc:zzadR	Lcom/google/android/gms/common/images/ImageManager;
      //   62: aload_0
      //   63: getfield 24	com/google/android/gms/common/images/ImageManager$zzc:mUri	Landroid/net/Uri;
      //   66: aload_2
      //   67: iload_1
      //   68: aload_3
      //   69: invokespecial 70	com/google/android/gms/common/images/ImageManager$zzf:<init>	(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
      //   72: invokevirtual 76	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   75: pop
      //   76: aload_3
      //   77: invokevirtual 79	java/util/concurrent/CountDownLatch:await	()V
      //   80: return
      //   81: astore 7
      //   83: ldc 81
      //   85: new 83	java/lang/StringBuilder
      //   88: dup
      //   89: invokespecial 84	java/lang/StringBuilder:<init>	()V
      //   92: ldc 86
      //   94: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   97: aload_0
      //   98: getfield 24	com/google/android/gms/common/images/ImageManager$zzc:mUri	Landroid/net/Uri;
      //   101: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   104: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   107: aload 7
      //   109: invokestatic 103	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   112: pop
      //   113: iconst_1
      //   114: istore_1
      //   115: goto -84 -> 31
      //   118: astore 9
      //   120: ldc 81
      //   122: ldc 105
      //   124: aload 9
      //   126: invokestatic 103	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   129: pop
      //   130: goto -92 -> 38
      //   133: astore 5
      //   135: ldc 81
      //   137: new 83	java/lang/StringBuilder
      //   140: dup
      //   141: invokespecial 84	java/lang/StringBuilder:<init>	()V
      //   144: ldc 107
      //   146: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   149: aload_0
      //   150: getfield 24	com/google/android/gms/common/images/ImageManager$zzc:mUri	Landroid/net/Uri;
      //   153: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   156: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   159: invokestatic 111	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   162: pop
      //   163: goto -83 -> 80
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	166	0	this	zzc
      //   6	109	1	bool	boolean
      //   8	59	2	localObject	Object
      //   46	31	3	localCountDownLatch	CountDownLatch
      //   133	1	5	localInterruptedException	InterruptedException
      //   81	27	7	localOutOfMemoryError	OutOfMemoryError
      //   118	7	9	localIOException	java.io.IOException
      //   26	3	11	localBitmap	Bitmap
      // Exception table:
      //   from	to	target	type
      //   16	28	81	java/lang/OutOfMemoryError
      //   31	38	118	java/io/IOException
      //   76	80	133	java/lang/InterruptedException
    }
  }
  
  private final class ImageReceiver
    extends ResultReceiver
  {
    private final Uri mUri;
    private final ArrayList<zza> zzadQ;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      this.mUri = paramUri;
      this.zzadQ = new ArrayList();
    }
    
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      ParcelFileDescriptor localParcelFileDescriptor = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.zzf(ImageManager.this).execute(new ImageManager.zzc(ImageManager.this, this.mUri, localParcelFileDescriptor));
    }
    
    public void zzb(zza paramzza)
    {
      zzb.zzci("ImageReceiver.addImageRequest() must be called in the main thread");
      this.zzadQ.add(paramzza);
    }
    
    public void zzc(zza paramzza)
    {
      zzb.zzci("ImageReceiver.removeImageRequest() must be called in the main thread");
      this.zzadQ.remove(paramzza);
    }
    
    public void zzoE()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", this.mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.zzb(ImageManager.this).sendBroadcast(localIntent);
    }
  }
  
  private final class zzd
    implements Runnable
  {
    private final zza zzadT;
    
    public zzd(zza paramzza)
    {
      this.zzadT = paramzza;
    }
    
    public void run()
    {
      zzb.zzci("LoadImageRunnable must be executed on the main thread");
      ImageManager.ImageReceiver localImageReceiver1 = (ImageManager.ImageReceiver)ImageManager.zza(ImageManager.this).get(this.zzadT);
      if (localImageReceiver1 != null)
      {
        ImageManager.zza(ImageManager.this).remove(this.zzadT);
        localImageReceiver1.zzc(this.zzadT);
      }
      zza.zza localzza = this.zzadT.zzadV;
      if (localzza.uri == null) {
        this.zzadT.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
      }
      for (;;)
      {
        return;
        Bitmap localBitmap = ImageManager.zza(ImageManager.this, localzza);
        if (localBitmap != null)
        {
          this.zzadT.zza(ImageManager.zzb(ImageManager.this), localBitmap, true);
          continue;
        }
        Long localLong = (Long)ImageManager.zzd(ImageManager.this).get(localzza.uri);
        if (localLong != null)
        {
          if (SystemClock.elapsedRealtime() - localLong.longValue() < 3600000L)
          {
            this.zzadT.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
            continue;
          }
          ImageManager.zzd(ImageManager.this).remove(localzza.uri);
        }
        this.zzadT.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this));
        ImageManager.ImageReceiver localImageReceiver2 = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).get(localzza.uri);
        if (localImageReceiver2 == null)
        {
          localImageReceiver2 = new ImageManager.ImageReceiver(ImageManager.this, localzza.uri);
          ImageManager.zze(ImageManager.this).put(localzza.uri, localImageReceiver2);
        }
        localImageReceiver2.zzb(this.zzadT);
        if (!(this.zzadT instanceof zza.zzc)) {
          ImageManager.zza(ImageManager.this).put(this.zzadT, localImageReceiver2);
        }
        synchronized (ImageManager.zzoC())
        {
          if (!ImageManager.zzoD().contains(localzza.uri))
          {
            ImageManager.zzoD().add(localzza.uri);
            localImageReceiver2.zzoE();
          }
        }
      }
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/images/ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */