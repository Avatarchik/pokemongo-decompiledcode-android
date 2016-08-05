package com.nianticlabs.nia.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.nianticlabs.nia.contextservice.ServiceStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationManagerProvider
  implements GpsProvider
{
  private static final boolean ENABLE_VERBOSE_LOGS = false;
  private static final String TAG = "LocationManagerProvider";
  private final Context context;
  private boolean firstLocationUpdate = false;
  private final GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener()
  {
    private GpsSatellite[] getSatellites(GpsStatus paramAnonymousGpsStatus)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramAnonymousGpsStatus.getSatellites().iterator();
      while (localIterator.hasNext()) {
        localArrayList.add((GpsSatellite)localIterator.next());
      }
      return (GpsSatellite[])localArrayList.toArray(new GpsSatellite[localArrayList.size()]);
    }
    
    public void onGpsStatusChanged(int paramAnonymousInt)
    {
      if (LocationManagerProvider.this.running)
      {
        GpsStatus localGpsStatus = LocationManagerProvider.this.locationManager.getGpsStatus(null);
        LocationManagerProvider.this.updateGpsStatus(localGpsStatus.getTimeToFirstFix(), getSatellites(localGpsStatus));
      }
    }
  };
  private LocationListener listener = new LocationListener()
  {
    public void onLocationChanged(Location paramAnonymousLocation)
    {
      if (LocationManagerProvider.this.running) {
        LocationManagerProvider.this.updateLocation(paramAnonymousLocation);
      }
    }
    
    public void onProviderDisabled(String paramAnonymousString)
    {
      LocationManagerProvider.this.updateStatus(ServiceStatus.PERMISSION_DENIED);
    }
    
    public void onProviderEnabled(String paramAnonymousString)
    {
      LocationManagerProvider.this.updateStatus(ServiceStatus.RUNNING);
    }
    
    public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
  };
  private LocationManager locationManager;
  private final String provider;
  private Provider.ProviderListener providerListener = null;
  private boolean running = false;
  private final float updateDistance;
  private final int updateTime;
  
  public LocationManagerProvider(Context paramContext, String paramString, int paramInt, float paramFloat)
  {
    this.context = paramContext;
    this.provider = paramString;
    this.updateTime = paramInt;
    this.updateDistance = paramFloat;
  }
  
  private void updateGpsStatus(int paramInt, GpsSatellite[] paramArrayOfGpsSatellite)
  {
    Provider.ProviderListener localProviderListener = this.providerListener;
    if ((localProviderListener != null) && ((localProviderListener instanceof GpsProvider.GpsProviderListener))) {
      ((GpsProvider.GpsProviderListener)localProviderListener).onGpsStatusUpdate(paramInt, paramArrayOfGpsSatellite);
    }
  }
  
  private void updateLocation(Location paramLocation)
  {
    Provider.ProviderListener localProviderListener = this.providerListener;
    if (localProviderListener != null)
    {
      if (this.firstLocationUpdate)
      {
        this.firstLocationUpdate = false;
        updateStatus(ServiceStatus.RUNNING);
      }
      localProviderListener.onProviderLocation(paramLocation);
    }
  }
  
  private void updateStatus(ServiceStatus paramServiceStatus)
  {
    Provider.ProviderListener localProviderListener = this.providerListener;
    if (localProviderListener != null) {
      localProviderListener.onProviderStatus(paramServiceStatus);
    }
  }
  
  public void onPause()
  {
    if (this.running) {}
    try
    {
      this.locationManager.removeUpdates(this.listener);
      this.running = false;
      updateStatus(ServiceStatus.STOPPED);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        Log.e("LocationManagerProvider", "Not allowed to access " + this.provider + " for updates", localSecurityException);
      }
    }
  }
  
  /* Error */
  public void onResume()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 43	com/nianticlabs/nia/location/LocationManagerProvider:firstLocationUpdate	Z
    //   5: getstatic 143	com/nianticlabs/nia/contextservice/ServiceStatus:FAILED	Lcom/nianticlabs/nia/contextservice/ServiceStatus;
    //   8: astore_1
    //   9: aload_0
    //   10: getfield 79	com/nianticlabs/nia/location/LocationManagerProvider:locationManager	Landroid/location/LocationManager;
    //   13: aload_0
    //   14: getfield 57	com/nianticlabs/nia/location/LocationManagerProvider:provider	Ljava/lang/String;
    //   17: aload_0
    //   18: getfield 59	com/nianticlabs/nia/location/LocationManagerProvider:updateTime	I
    //   21: i2l
    //   22: aload_0
    //   23: getfield 61	com/nianticlabs/nia/location/LocationManagerProvider:updateDistance	F
    //   26: aload_0
    //   27: getfield 48	com/nianticlabs/nia/location/LocationManagerProvider:listener	Landroid/location/LocationListener;
    //   30: invokestatic 149	com/nianticlabs/nia/contextservice/ContextService:getServiceLooper	()Landroid/os/Looper;
    //   33: invokevirtual 153	android/location/LocationManager:requestLocationUpdates	(Ljava/lang/String;JFLandroid/location/LocationListener;Landroid/os/Looper;)V
    //   36: ldc 17
    //   38: ldc -101
    //   40: invokestatic 159	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   43: pop
    //   44: aload_0
    //   45: getfield 57	com/nianticlabs/nia/location/LocationManagerProvider:provider	Ljava/lang/String;
    //   48: ldc -95
    //   50: if_acmpne +15 -> 65
    //   53: aload_0
    //   54: getfield 79	com/nianticlabs/nia/location/LocationManagerProvider:locationManager	Landroid/location/LocationManager;
    //   57: aload_0
    //   58: getfield 51	com/nianticlabs/nia/location/LocationManagerProvider:gpsStatusListener	Landroid/location/GpsStatus$Listener;
    //   61: invokevirtual 165	android/location/LocationManager:addGpsStatusListener	(Landroid/location/GpsStatus$Listener;)Z
    //   64: pop
    //   65: aload_0
    //   66: iconst_1
    //   67: putfield 41	com/nianticlabs/nia/location/LocationManagerProvider:running	Z
    //   70: aload_1
    //   71: astore 4
    //   73: aload_0
    //   74: getfield 41	com/nianticlabs/nia/location/LocationManagerProvider:running	Z
    //   77: ifeq +108 -> 185
    //   80: aload_0
    //   81: getstatic 168	com/nianticlabs/nia/contextservice/ServiceStatus:INITIALIZED	Lcom/nianticlabs/nia/contextservice/ServiceStatus;
    //   84: invokespecial 75	com/nianticlabs/nia/location/LocationManagerProvider:updateStatus	(Lcom/nianticlabs/nia/contextservice/ServiceStatus;)V
    //   87: aload_0
    //   88: aload_0
    //   89: getfield 79	com/nianticlabs/nia/location/LocationManagerProvider:locationManager	Landroid/location/LocationManager;
    //   92: aload_0
    //   93: getfield 57	com/nianticlabs/nia/location/LocationManagerProvider:provider	Ljava/lang/String;
    //   96: invokevirtual 172	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   99: invokespecial 69	com/nianticlabs/nia/location/LocationManagerProvider:updateLocation	(Landroid/location/Location;)V
    //   102: return
    //   103: astore 6
    //   105: ldc 17
    //   107: new 118	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   114: ldc -82
    //   116: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: aload_0
    //   120: getfield 57	com/nianticlabs/nia/location/LocationManagerProvider:provider	Ljava/lang/String;
    //   123: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc -80
    //   128: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: aload 6
    //   136: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   139: pop
    //   140: goto -70 -> 70
    //   143: astore_2
    //   144: ldc 17
    //   146: new 118	java/lang/StringBuilder
    //   149: dup
    //   150: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   153: ldc 121
    //   155: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_0
    //   159: getfield 57	com/nianticlabs/nia/location/LocationManagerProvider:provider	Ljava/lang/String;
    //   162: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc 127
    //   167: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: aload_2
    //   174: invokestatic 137	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   177: pop
    //   178: getstatic 179	com/nianticlabs/nia/contextservice/ServiceStatus:PERMISSION_DENIED	Lcom/nianticlabs/nia/contextservice/ServiceStatus;
    //   181: astore_1
    //   182: goto -112 -> 70
    //   185: aload_0
    //   186: aload 4
    //   188: invokespecial 75	com/nianticlabs/nia/location/LocationManagerProvider:updateStatus	(Lcom/nianticlabs/nia/contextservice/ServiceStatus;)V
    //   191: goto -89 -> 102
    //   194: astore 5
    //   196: goto -94 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	this	LocationManagerProvider
    //   8	174	1	localServiceStatus1	ServiceStatus
    //   143	31	2	localSecurityException1	SecurityException
    //   71	116	4	localServiceStatus2	ServiceStatus
    //   194	1	5	localSecurityException2	SecurityException
    //   103	32	6	localIllegalArgumentException	IllegalArgumentException
    // Exception table:
    //   from	to	target	type
    //   9	70	103	java/lang/IllegalArgumentException
    //   9	70	143	java/lang/SecurityException
    //   87	102	194	java/lang/SecurityException
  }
  
  public void onStart()
  {
    this.locationManager = ((LocationManager)this.context.getSystemService("location"));
  }
  
  public void onStop()
  {
    this.locationManager = null;
  }
  
  public void setListener(Provider.ProviderListener paramProviderListener)
  {
    this.providerListener = paramProviderListener;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/location/LocationManagerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */