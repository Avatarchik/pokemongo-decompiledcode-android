package com.google.android.gms.location.places.internal;

import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzsd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class zzu
  extends com.google.android.gms.common.data.zzc
{
  private final String TAG = "SafeDataBufferRef";
  
  public zzu(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  protected String zzF(String paramString1, String paramString2)
  {
    if ((zzce(paramString1)) && (!zzcg(paramString1))) {
      paramString2 = getString(paramString1);
    }
    return paramString2;
  }
  
  protected <E extends SafeParcelable> E zza(String paramString, Parcelable.Creator<E> paramCreator)
  {
    Object localObject = null;
    byte[] arrayOfByte = zzc(paramString, null);
    if (arrayOfByte == null) {}
    for (;;)
    {
      return (E)localObject;
      localObject = com.google.android.gms.common.internal.safeparcel.zzc.zza(arrayOfByte, paramCreator);
    }
  }
  
  protected <E extends SafeParcelable> List<E> zza(String paramString, Parcelable.Creator<E> paramCreator, List<E> paramList)
  {
    byte[] arrayOfByte = zzc(paramString, null);
    if (arrayOfByte == null) {}
    for (;;)
    {
      return paramList;
      try
      {
        zzru localzzru = zzru.zzz(arrayOfByte);
        if (localzzru.zzbhW == null) {
          continue;
        }
        ArrayList localArrayList = new ArrayList(localzzru.zzbhW.length);
        byte[][] arrayOfByte1 = localzzru.zzbhW;
        int i = arrayOfByte1.length;
        for (int j = 0; j < i; j++) {
          localArrayList.add(com.google.android.gms.common.internal.safeparcel.zzc.zza(arrayOfByte1[j], paramCreator));
        }
        paramList = localArrayList;
      }
      catch (zzsd localzzsd) {}
      if (Log.isLoggable("SafeDataBufferRef", 6)) {
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", localzzsd);
      }
    }
  }
  
  protected List<Integer> zza(String paramString, List<Integer> paramList)
  {
    byte[] arrayOfByte = zzc(paramString, null);
    if (arrayOfByte == null) {}
    for (;;)
    {
      return paramList;
      try
      {
        zzru localzzru = zzru.zzz(arrayOfByte);
        if (localzzru.zzbhV == null) {
          continue;
        }
        ArrayList localArrayList = new ArrayList(localzzru.zzbhV.length);
        for (int i = 0; i < localzzru.zzbhV.length; i++) {
          localArrayList.add(Integer.valueOf(localzzru.zzbhV[i]));
        }
        paramList = localArrayList;
      }
      catch (zzsd localzzsd) {}
      if (Log.isLoggable("SafeDataBufferRef", 6)) {
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", localzzsd);
      }
    }
  }
  
  protected float zzb(String paramString, float paramFloat)
  {
    if ((zzce(paramString)) && (!zzcg(paramString))) {
      paramFloat = getFloat(paramString);
    }
    return paramFloat;
  }
  
  protected List<String> zzb(String paramString, List<String> paramList)
  {
    byte[] arrayOfByte = zzc(paramString, null);
    if (arrayOfByte == null) {}
    for (;;)
    {
      return paramList;
      try
      {
        zzru localzzru = zzru.zzz(arrayOfByte);
        if (localzzru.zzbhU == null) {
          continue;
        }
        List localList = Arrays.asList(localzzru.zzbhU);
        paramList = localList;
      }
      catch (zzsd localzzsd) {}
      if (Log.isLoggable("SafeDataBufferRef", 6)) {
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", localzzsd);
      }
    }
  }
  
  protected byte[] zzc(String paramString, byte[] paramArrayOfByte)
  {
    if ((zzce(paramString)) && (!zzcg(paramString))) {
      paramArrayOfByte = getByteArray(paramString);
    }
    return paramArrayOfByte;
  }
  
  protected boolean zzh(String paramString, boolean paramBoolean)
  {
    if ((zzce(paramString)) && (!zzcg(paramString))) {
      paramBoolean = getBoolean(paramString);
    }
    return paramBoolean;
  }
  
  protected int zzz(String paramString, int paramInt)
  {
    if ((zzce(paramString)) && (!zzcg(paramString))) {
      paramInt = getInteger(paramString);
    }
    return paramInt;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */