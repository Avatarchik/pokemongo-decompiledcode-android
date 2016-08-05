package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzk
  implements Handler.Callback
{
  private final Handler mHandler;
  private final zza zzafP;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzafQ = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> zzafR = new ArrayList();
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzafS = new ArrayList();
  private volatile boolean zzafT = false;
  private final AtomicInteger zzafU = new AtomicInteger(0);
  private boolean zzafV = false;
  private final Object zzpd = new Object();
  
  public zzk(Looper paramLooper, zza paramzza)
  {
    this.zzafP = paramzza;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
    if (paramMessage.what == 1) {
      localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)paramMessage.obj;
    }
    boolean bool;
    synchronized (this.zzpd)
    {
      if ((this.zzafT) && (this.zzafP.isConnected()) && (this.zzafQ.contains(localConnectionCallbacks))) {
        localConnectionCallbacks.onConnected(this.zzafP.zzmS());
      }
      bool = true;
    }
    return bool;
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzw(paramConnectionCallbacks);
    synchronized (this.zzpd)
    {
      boolean bool = this.zzafQ.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzw(paramOnConnectionFailedListener);
    synchronized (this.zzpd)
    {
      boolean bool = this.zzafS.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzw(paramConnectionCallbacks);
    synchronized (this.zzpd)
    {
      if (this.zzafQ.contains(paramConnectionCallbacks))
      {
        Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + paramConnectionCallbacks + " is already registered");
        if (this.zzafP.isConnected()) {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        }
        return;
      }
      this.zzafQ.add(paramConnectionCallbacks);
    }
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzw(paramOnConnectionFailedListener);
    synchronized (this.zzpd)
    {
      if (this.zzafS.contains(paramOnConnectionFailedListener))
      {
        Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      this.zzafS.add(paramOnConnectionFailedListener);
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzw(paramConnectionCallbacks);
    synchronized (this.zzpd)
    {
      if (!this.zzafQ.remove(paramConnectionCallbacks)) {
        Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      while (!this.zzafV) {
        return;
      }
      this.zzafR.add(paramConnectionCallbacks);
    }
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzw(paramOnConnectionFailedListener);
    synchronized (this.zzpd)
    {
      if (!this.zzafS.remove(paramOnConnectionFailedListener)) {
        Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
      }
      return;
    }
  }
  
  public void zzbG(int paramInt)
  {
    boolean bool = false;
    if (Looper.myLooper() == this.mHandler.getLooper()) {
      bool = true;
    }
    zzx.zza(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.zzpd)
    {
      this.zzafV = true;
      ArrayList localArrayList = new ArrayList(this.zzafQ);
      int i = this.zzafU.get();
      Iterator localIterator = localArrayList.iterator();
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
      do
      {
        if (localIterator.hasNext())
        {
          localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)localIterator.next();
          if ((this.zzafT) && (this.zzafU.get() == i)) {}
        }
        else
        {
          this.zzafR.clear();
          this.zzafV = false;
          return;
        }
      } while (!this.zzafQ.contains(localConnectionCallbacks));
      localConnectionCallbacks.onConnectionSuspended(paramInt);
    }
  }
  
  public void zzh(Bundle paramBundle)
  {
    boolean bool1 = true;
    boolean bool2;
    if (Looper.myLooper() == this.mHandler.getLooper())
    {
      bool2 = bool1;
      zzx.zza(bool2, "onConnectionSuccess must only be called on the Handler thread");
    }
    boolean bool3;
    label214:
    synchronized (this.zzpd)
    {
      if (!this.zzafV)
      {
        bool3 = bool1;
        zzx.zzZ(bool3);
        this.mHandler.removeMessages(1);
        this.zzafV = true;
        if (this.zzafR.size() != 0) {
          break label214;
        }
        zzx.zzZ(bool1);
        ArrayList localArrayList = new ArrayList(this.zzafQ);
        int i = this.zzafU.get();
        Iterator localIterator = localArrayList.iterator();
        GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
        do
        {
          if (localIterator.hasNext())
          {
            localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)localIterator.next();
            if ((this.zzafT) && (this.zzafP.isConnected()) && (this.zzafU.get() == i)) {}
          }
          else
          {
            this.zzafR.clear();
            this.zzafV = false;
            return;
          }
        } while (this.zzafR.contains(localConnectionCallbacks));
        localConnectionCallbacks.onConnected(paramBundle);
      }
    }
  }
  
  public void zzi(ConnectionResult paramConnectionResult)
  {
    boolean bool;
    if (Looper.myLooper() == this.mHandler.getLooper())
    {
      bool = true;
      zzx.zza(bool, "onConnectionFailure must only be called on the Handler thread");
      this.mHandler.removeMessages(1);
    }
    label143:
    synchronized (this.zzpd)
    {
      ArrayList localArrayList = new ArrayList(this.zzafS);
      int i = this.zzafU.get();
      Iterator localIterator = localArrayList.iterator();
      GoogleApiClient.OnConnectionFailedListener localOnConnectionFailedListener;
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localOnConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)localIterator.next();
        if ((this.zzafT) && (this.zzafU.get() != i)) {
          break label143;
        }
      } while (!this.zzafS.contains(localOnConnectionFailedListener));
      localOnConnectionFailedListener.onConnectionFailed(paramConnectionResult);
    }
  }
  
  public void zzpk()
  {
    this.zzafT = false;
    this.zzafU.incrementAndGet();
  }
  
  public void zzpl()
  {
    this.zzafT = true;
  }
  
  public static abstract interface zza
  {
    public abstract boolean isConnected();
    
    public abstract Bundle zzmS();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */