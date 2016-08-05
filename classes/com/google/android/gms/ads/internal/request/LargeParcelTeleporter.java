package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzmt;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class LargeParcelTeleporter
  implements SafeParcelable
{
  public static final Parcelable.Creator<LargeParcelTeleporter> CREATOR = new zzl();
  final int mVersionCode;
  ParcelFileDescriptor zzFc;
  private Parcelable zzFd;
  private boolean zzFe;
  
  LargeParcelTeleporter(int paramInt, ParcelFileDescriptor paramParcelFileDescriptor)
  {
    this.mVersionCode = paramInt;
    this.zzFc = paramParcelFileDescriptor;
    this.zzFd = null;
    this.zzFe = true;
  }
  
  public LargeParcelTeleporter(SafeParcelable paramSafeParcelable)
  {
    this.mVersionCode = 1;
    this.zzFc = null;
    this.zzFd = paramSafeParcelable;
    this.zzFe = false;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Parcel localParcel;
    if (this.zzFc == null) {
      localParcel = Parcel.obtain();
    }
    try
    {
      this.zzFd.writeToParcel(localParcel, 0);
      byte[] arrayOfByte = localParcel.marshall();
      localParcel.recycle();
      this.zzFc = zzf(arrayOfByte);
      zzl.zza(this, paramParcel, paramInt);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
  }
  
  public <T extends SafeParcelable> T zza(Parcelable.Creator<T> paramCreator)
  {
    Object localObject1;
    if (this.zzFe) {
      if (this.zzFc == null)
      {
        zzb.e("File descriptor is empty, returning null.");
        localObject1 = null;
      }
    }
    for (;;)
    {
      return (T)localObject1;
      DataInputStream localDataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzFc));
      try
      {
        arrayOfByte = new byte[localDataInputStream.readInt()];
        localDataInputStream.readFully(arrayOfByte, 0, arrayOfByte.length);
        zzmt.zzb(localDataInputStream);
        localParcel = Parcel.obtain();
      }
      catch (IOException localIOException)
      {
        byte[] arrayOfByte;
        localIOException = localIOException;
        throw new IllegalStateException("Could not read from parcel file descriptor", localIOException);
      }
      finally
      {
        zzmt.zzb(localDataInputStream);
      }
    }
  }
  
  protected <T> ParcelFileDescriptor zzf(final byte[] paramArrayOfByte)
  {
    ParcelFileDescriptor localParcelFileDescriptor = null;
    for (;;)
    {
      try
      {
        ParcelFileDescriptor[] arrayOfParcelFileDescriptor = ParcelFileDescriptor.createPipe();
        localAutoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(arrayOfParcelFileDescriptor[1]);
        zzb.zzb("Error transporting the ad response", localIOException1);
      }
      catch (IOException localIOException1)
      {
        try
        {
          new Thread(new Runnable()
          {
            /* Error */
            public void run()
            {
              // Byte code:
              //   0: new 33	java/io/DataOutputStream
              //   3: dup
              //   4: aload_0
              //   5: getfield 23	com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1:zzFf	Ljava/io/OutputStream;
              //   8: invokespecial 36	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
              //   11: astore_1
              //   12: aload_1
              //   13: aload_0
              //   14: getfield 25	com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1:zzFg	[B
              //   17: arraylength
              //   18: invokevirtual 40	java/io/DataOutputStream:writeInt	(I)V
              //   21: aload_1
              //   22: aload_0
              //   23: getfield 25	com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1:zzFg	[B
              //   26: invokevirtual 44	java/io/DataOutputStream:write	([B)V
              //   29: aload_1
              //   30: ifnonnull +11 -> 41
              //   33: aload_0
              //   34: getfield 23	com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1:zzFf	Ljava/io/OutputStream;
              //   37: invokestatic 50	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
              //   40: return
              //   41: aload_1
              //   42: invokestatic 50	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
              //   45: goto -5 -> 40
              //   48: astore_2
              //   49: aconst_null
              //   50: astore_1
              //   51: ldc 52
              //   53: aload_2
              //   54: invokestatic 57	com/google/android/gms/ads/internal/util/client/zzb:zzb	(Ljava/lang/String;Ljava/lang/Throwable;)V
              //   57: invokestatic 63	com/google/android/gms/ads/internal/zzp:zzby	()Lcom/google/android/gms/internal/zzhu;
              //   60: aload_2
              //   61: iconst_1
              //   62: invokevirtual 69	com/google/android/gms/internal/zzhu:zzc	(Ljava/lang/Throwable;Z)V
              //   65: aload_1
              //   66: ifnonnull +13 -> 79
              //   69: aload_0
              //   70: getfield 23	com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1:zzFf	Ljava/io/OutputStream;
              //   73: invokestatic 50	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
              //   76: goto -36 -> 40
              //   79: aload_1
              //   80: invokestatic 50	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
              //   83: goto -43 -> 40
              //   86: astore_3
              //   87: aconst_null
              //   88: astore_1
              //   89: aload_1
              //   90: ifnonnull +12 -> 102
              //   93: aload_0
              //   94: getfield 23	com/google/android/gms/ads/internal/request/LargeParcelTeleporter$1:zzFf	Ljava/io/OutputStream;
              //   97: invokestatic 50	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
              //   100: aload_3
              //   101: athrow
              //   102: aload_1
              //   103: invokestatic 50	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
              //   106: goto -6 -> 100
              //   109: astore_3
              //   110: goto -21 -> 89
              //   113: astore_2
              //   114: goto -63 -> 51
              // Local variable table:
              //   start	length	slot	name	signature
              //   0	117	0	this	1
              //   11	92	1	localDataOutputStream	java.io.DataOutputStream
              //   48	13	2	localIOException1	IOException
              //   113	1	2	localIOException2	IOException
              //   86	15	3	localObject1	Object
              //   109	1	3	localObject2	Object
              // Exception table:
              //   from	to	target	type
              //   0	12	48	java/io/IOException
              //   0	12	86	finally
              //   12	29	109	finally
              //   51	65	109	finally
              //   12	29	113	java/io/IOException
            }
          }).start();
          localParcelFileDescriptor = arrayOfParcelFileDescriptor[0];
          return localParcelFileDescriptor;
        }
        catch (IOException localIOException2)
        {
          final ParcelFileDescriptor.AutoCloseOutputStream localAutoCloseOutputStream;
          for (;;) {}
        }
        localIOException1 = localIOException1;
        localAutoCloseOutputStream = null;
      }
      zzp.zzby().zzc(localIOException1, true);
      zzmt.zzb(localAutoCloseOutputStream);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/request/LargeParcelTeleporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */