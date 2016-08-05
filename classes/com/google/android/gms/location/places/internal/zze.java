package com.google.android.gms.location.places.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public class zze
  extends zzj<zzg>
{
  private final PlacesParams zzaHb;
  private final Locale zzaHc = Locale.getDefault();
  
  public zze(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString1, String paramString2, PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramLooper, 65, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    String str = null;
    if (paramzzf.getAccount() != null) {
      str = paramzzf.getAccount().name;
    }
    this.zzaHb = new PlacesParams(paramString1, this.zzaHc, str, paramPlacesOptions.zzaGG, paramString2);
  }
  
  public void zza(com.google.android.gms.location.places.zzf paramzzf, String paramString)
    throws RemoteException
  {
    zzx.zzb(paramString, "placeId cannot be null");
    ((zzg)zzpc()).zza(paramString, this.zzaHb, paramzzf);
  }
  
  public void zza(com.google.android.gms.location.places.zzf paramzzf, String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    boolean bool1 = true;
    zzx.zzb(paramString, "fifeUrl cannot be null");
    boolean bool2;
    if (paramInt1 > 0)
    {
      bool2 = bool1;
      zzx.zzb(bool2, "width should be > 0");
      if (paramInt1 <= 0) {
        break label66;
      }
    }
    for (;;)
    {
      zzx.zzb(bool1, "height should be > 0");
      ((zzg)zzpc()).zza(paramString, paramInt1, paramInt2, paramInt3, this.zzaHb, paramzzf);
      return;
      bool2 = false;
      break;
      label66:
      bool1 = false;
    }
  }
  
  public void zza(zzl paramzzl, AddPlaceRequest paramAddPlaceRequest)
    throws RemoteException
  {
    zzx.zzb(paramAddPlaceRequest, "userAddedPlace == null");
    ((zzg)zzpc()).zza(paramAddPlaceRequest, this.zzaHb, paramzzl);
  }
  
  public void zza(zzl paramzzl, String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter)
    throws RemoteException
  {
    zzx.zzb(paramString, "query == null");
    zzx.zzb(paramzzl, "callback == null");
    if (paramAutocompleteFilter == null) {}
    for (AutocompleteFilter localAutocompleteFilter = AutocompleteFilter.create(null);; localAutocompleteFilter = paramAutocompleteFilter)
    {
      ((zzg)zzpc()).zza(paramString, paramLatLngBounds, localAutocompleteFilter, this.zzaHb, paramzzl);
      return;
    }
  }
  
  public void zza(zzl paramzzl, List<String> paramList)
    throws RemoteException
  {
    ((zzg)zzpc()).zzb(paramList, this.zzaHb, paramzzl);
  }
  
  protected zzg zzcd(IBinder paramIBinder)
  {
    return zzg.zza.zzcf(paramIBinder);
  }
  
  protected String zzfK()
  {
    return "com.google.android.gms.location.places.GeoDataApi";
  }
  
  protected String zzfL()
  {
    return "com.google.android.gms.location.places.internal.IGooglePlacesService";
  }
  
  public static class zza
    extends Api.zza<zze, PlacesOptions>
  {
    private final String zzaHd;
    private final String zzaHe;
    
    public zza(String paramString1, String paramString2)
    {
      this.zzaHd = paramString1;
      this.zzaHe = paramString2;
    }
    
    public zze zza(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, PlacesOptions paramPlacesOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      String str1;
      String str2;
      if (this.zzaHd != null)
      {
        str1 = this.zzaHd;
        if (this.zzaHe == null) {
          break label73;
        }
        str2 = this.zzaHe;
        label26:
        if (paramPlacesOptions != null) {
          break label82;
        }
      }
      label73:
      label82:
      for (PlacesOptions localPlacesOptions = new PlacesOptions.Builder().build();; localPlacesOptions = paramPlacesOptions)
      {
        return new zze(paramContext, paramLooper, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener, str1, str2, localPlacesOptions);
        str1 = paramContext.getPackageName();
        break;
        str2 = paramContext.getPackageName();
        break label26;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */