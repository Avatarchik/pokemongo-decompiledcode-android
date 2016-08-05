package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.Set;

public abstract class Task
  implements Parcelable
{
  public static final int EXTRAS_LIMIT_BYTES = 10240;
  public static final int NETWORK_STATE_ANY = 2;
  public static final int NETWORK_STATE_CONNECTED = 0;
  public static final int NETWORK_STATE_UNMETERED = 1;
  protected static final long UNINITIALIZED = -1L;
  private final Bundle mExtras;
  private final String mTag;
  private final String zzaCN;
  private final boolean zzaCO;
  private final boolean zzaCP;
  private final int zzaCQ;
  private final boolean zzaCR;
  private final zzc zzaCS;
  
  @Deprecated
  Task(Parcel paramParcel)
  {
    Log.e("Task", "Constructing a Task object using a parcel.");
    this.zzaCN = paramParcel.readString();
    this.mTag = paramParcel.readString();
    int j;
    if (paramParcel.readInt() == i)
    {
      j = i;
      this.zzaCO = j;
      if (paramParcel.readInt() != i) {
        break label89;
      }
    }
    for (;;)
    {
      this.zzaCP = i;
      this.zzaCQ = 2;
      this.zzaCR = false;
      this.zzaCS = zzc.zzaCI;
      this.mExtras = null;
      return;
      j = 0;
      break;
      label89:
      i = 0;
    }
  }
  
  Task(Builder paramBuilder)
  {
    this.zzaCN = paramBuilder.gcmTaskService;
    this.mTag = paramBuilder.tag;
    this.zzaCO = paramBuilder.updateCurrent;
    this.zzaCP = paramBuilder.isPersisted;
    this.zzaCQ = paramBuilder.requiredNetworkState;
    this.zzaCR = paramBuilder.requiresCharging;
    this.zzaCS = paramBuilder.zzaCT;
    this.mExtras = paramBuilder.extras;
  }
  
  public static void zzA(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      Parcel localParcel = Parcel.obtain();
      paramBundle.writeToParcel(localParcel, 0);
      int i = localParcel.dataSize();
      if (i > 10240)
      {
        localParcel.recycle();
        throw new IllegalArgumentException("Extras exceeding maximum size(10240 bytes): " + i);
      }
      localParcel.recycle();
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext()) {
        if (!zzz(paramBundle.get((String)localIterator.next()))) {
          throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean. ");
        }
      }
    }
  }
  
  public static void zza(zzc paramzzc)
  {
    if (paramzzc != null)
    {
      int i = paramzzc.zzvZ();
      if ((i != 1) && (i != 0)) {
        throw new IllegalArgumentException("Must provide a valid RetryPolicy: " + i);
      }
      int j = paramzzc.zzwa();
      int k = paramzzc.zzwb();
      if ((i == 0) && (j < 0)) {
        throw new IllegalArgumentException("InitialBackoffSeconds can't be negative: " + j);
      }
      if ((i == 1) && (j < 10)) {
        throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
      }
      if (k < j) {
        throw new IllegalArgumentException("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: " + paramzzc.zzwb());
      }
    }
  }
  
  private static boolean zzz(Object paramObject)
  {
    if (((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || ((paramObject instanceof Double)) || ((paramObject instanceof String)) || ((paramObject instanceof Boolean))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public int getRequiredNetwork()
  {
    return this.zzaCQ;
  }
  
  public boolean getRequiresCharging()
  {
    return this.zzaCR;
  }
  
  public String getServiceName()
  {
    return this.zzaCN;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public boolean isPersisted()
  {
    return this.zzaCP;
  }
  
  public boolean isUpdateCurrent()
  {
    return this.zzaCO;
  }
  
  public void toBundle(Bundle paramBundle)
  {
    paramBundle.putString("tag", this.mTag);
    paramBundle.putBoolean("update_current", this.zzaCO);
    paramBundle.putBoolean("persisted", this.zzaCP);
    paramBundle.putString("service", this.zzaCN);
    paramBundle.putInt("requiredNetwork", this.zzaCQ);
    paramBundle.putBoolean("requiresCharging", this.zzaCR);
    paramBundle.putBundle("retryStrategy", this.zzaCS.zzz(new Bundle()));
    paramBundle.putBundle("extras", this.mExtras);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.zzaCN);
    paramParcel.writeString(this.mTag);
    int j;
    if (this.zzaCO)
    {
      j = i;
      paramParcel.writeInt(j);
      if (!this.zzaCP) {
        break label53;
      }
    }
    for (;;)
    {
      paramParcel.writeInt(i);
      return;
      j = 0;
      break;
      label53:
      i = 0;
    }
  }
  
  public static abstract class Builder
  {
    protected Bundle extras;
    protected String gcmTaskService;
    protected boolean isPersisted;
    protected int requiredNetworkState;
    protected boolean requiresCharging;
    protected String tag;
    protected boolean updateCurrent;
    protected zzc zzaCT = zzc.zzaCI;
    
    public abstract Task build();
    
    protected void checkConditions()
    {
      if (this.gcmTaskService != null) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must provide an endpoint for this task by calling setService(ComponentName).");
        GcmNetworkManager.zzdh(this.tag);
        Task.zza(this.zzaCT);
        if (this.isPersisted) {
          Task.zzA(this.extras);
        }
        return;
      }
    }
    
    public abstract Builder setExtras(Bundle paramBundle);
    
    public abstract Builder setPersisted(boolean paramBoolean);
    
    public abstract Builder setRequiredNetwork(int paramInt);
    
    public abstract Builder setRequiresCharging(boolean paramBoolean);
    
    public abstract Builder setService(Class<? extends GcmTaskService> paramClass);
    
    public abstract Builder setTag(String paramString);
    
    public abstract Builder setUpdateCurrent(boolean paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */