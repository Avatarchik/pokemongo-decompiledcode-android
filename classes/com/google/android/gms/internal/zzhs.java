package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzh.zza;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zzgr
public class zzhs
{
  public final int errorCode;
  public final int orientation;
  public final zziz zzBD;
  public final long zzEJ;
  public final boolean zzEK;
  public final long zzEL;
  public final List<String> zzEM;
  public final String zzEP;
  public final AdRequestParcel zzEn;
  public final String zzEq;
  public final long zzHA;
  public final zzh.zza zzHB;
  public final JSONObject zzHw;
  public final zzee zzHx;
  public final AdSizeParcel zzHy;
  public final long zzHz;
  public final List<String> zzyY;
  public final List<String> zzyZ;
  public final long zzzc;
  public final zzed zzzu;
  public final zzen zzzv;
  public final String zzzw;
  public final zzeg zzzx;
  
  public zzhs(AdRequestParcel paramAdRequestParcel, zziz paramzziz, List<String> paramList1, int paramInt1, List<String> paramList2, List<String> paramList3, int paramInt2, long paramLong1, String paramString1, boolean paramBoolean, zzed paramzzed, zzen paramzzen, String paramString2, zzee paramzzee, zzeg paramzzeg, long paramLong2, AdSizeParcel paramAdSizeParcel, long paramLong3, long paramLong4, long paramLong5, String paramString3, JSONObject paramJSONObject, zzh.zza paramzza)
  {
    this.zzEn = paramAdRequestParcel;
    this.zzBD = paramzziz;
    List localList1;
    List localList2;
    if (paramList1 != null)
    {
      localList1 = Collections.unmodifiableList(paramList1);
      this.zzyY = localList1;
      this.errorCode = paramInt1;
      if (paramList2 == null) {
        break label181;
      }
      localList2 = Collections.unmodifiableList(paramList2);
      label48:
      this.zzyZ = localList2;
      if (paramList3 == null) {
        break label187;
      }
    }
    label181:
    label187:
    for (List localList3 = Collections.unmodifiableList(paramList3);; localList3 = null)
    {
      this.zzEM = localList3;
      this.orientation = paramInt2;
      this.zzzc = paramLong1;
      this.zzEq = paramString1;
      this.zzEK = paramBoolean;
      this.zzzu = paramzzed;
      this.zzzv = paramzzen;
      this.zzzw = paramString2;
      this.zzHx = paramzzee;
      this.zzzx = paramzzeg;
      this.zzEL = paramLong2;
      this.zzHy = paramAdSizeParcel;
      this.zzEJ = paramLong3;
      this.zzHz = paramLong4;
      this.zzHA = paramLong5;
      this.zzEP = paramString3;
      this.zzHw = paramJSONObject;
      this.zzHB = paramzza;
      return;
      localList1 = null;
      break;
      localList2 = null;
      break label48;
    }
  }
  
  public zzhs(zza paramzza, zziz paramzziz, zzed paramzzed, zzen paramzzen, String paramString, zzeg paramzzeg, zzh.zza paramzza1)
  {
    this(paramzza.zzHC.zzEn, paramzziz, paramzza.zzHD.zzyY, paramzza.errorCode, paramzza.zzHD.zzyZ, paramzza.zzHD.zzEM, paramzza.zzHD.orientation, paramzza.zzHD.zzzc, paramzza.zzHC.zzEq, paramzza.zzHD.zzEK, paramzzed, paramzzen, paramString, paramzza.zzHx, paramzzeg, paramzza.zzHD.zzEL, paramzza.zzqn, paramzza.zzHD.zzEJ, paramzza.zzHz, paramzza.zzHA, paramzza.zzHD.zzEP, paramzza.zzHw, paramzza1);
  }
  
  public boolean zzbY()
  {
    if ((this.zzBD == null) || (this.zzBD.zzhe() == null)) {}
    for (boolean bool = false;; bool = this.zzBD.zzhe().zzbY()) {
      return bool;
    }
  }
  
  @zzgr
  public static final class zza
  {
    public final int errorCode;
    public final long zzHA;
    public final AdRequestInfoParcel zzHC;
    public final AdResponseParcel zzHD;
    public final JSONObject zzHw;
    public final zzee zzHx;
    public final long zzHz;
    public final AdSizeParcel zzqn;
    
    public zza(AdRequestInfoParcel paramAdRequestInfoParcel, AdResponseParcel paramAdResponseParcel, zzee paramzzee, AdSizeParcel paramAdSizeParcel, int paramInt, long paramLong1, long paramLong2, JSONObject paramJSONObject)
    {
      this.zzHC = paramAdRequestInfoParcel;
      this.zzHD = paramAdResponseParcel;
      this.zzHx = paramzzee;
      this.zzqn = paramAdSizeParcel;
      this.errorCode = paramInt;
      this.zzHz = paramLong1;
      this.zzHA = paramLong2;
      this.zzHw = paramJSONObject;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzhs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */