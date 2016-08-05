package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfj;
import com.google.android.gms.internal.zzfk;

public class AdActivity
  extends Activity
{
  public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
  public static final String SIMPLE_CLASS_NAME = "AdActivity";
  private zzfk zznK;
  
  private void zzaE()
  {
    if (this.zznK != null) {}
    try
    {
      this.zznK.zzaE();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward setContentViewSet to ad overlay:", localRemoteException);
      }
    }
  }
  
  public void onBackPressed()
  {
    int i = 1;
    try
    {
      if (this.zznK != null)
      {
        boolean bool = this.zznK.zzeF();
        i = bool;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onBackPressed to ad overlay:", localRemoteException);
      }
    }
    if (i != 0) {
      super.onBackPressed();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.zznK = zzfj.zzb(this);
    if (this.zznK == null)
    {
      zzb.zzaH("Could not create ad overlay.");
      finish();
    }
    for (;;)
    {
      return;
      try
      {
        this.zznK.onCreate(paramBundle);
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not forward onCreate to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onDestroy()
  {
    try
    {
      if (this.zznK != null) {
        this.zznK.onDestroy();
      }
      super.onDestroy();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onDestroy to ad overlay:", localRemoteException);
      }
    }
  }
  
  protected void onPause()
  {
    try
    {
      if (this.zznK != null) {
        this.zznK.onPause();
      }
      super.onPause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onPause to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onRestart()
  {
    super.onRestart();
    try
    {
      if (this.zznK != null) {
        this.zznK.onRestart();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onRestart to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    try
    {
      if (this.zznK != null) {
        this.zznK.onResume();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onResume to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    try
    {
      if (this.zznK != null) {
        this.zznK.onSaveInstanceState(paramBundle);
      }
      super.onSaveInstanceState(paramBundle);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onSaveInstanceState to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    try
    {
      if (this.zznK != null) {
        this.zznK.onStart();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onStart to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  protected void onStop()
  {
    try
    {
      if (this.zznK != null) {
        this.zznK.onStop();
      }
      super.onStop();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward onStop to ad overlay:", localRemoteException);
        finish();
      }
    }
  }
  
  public void setContentView(int paramInt)
  {
    super.setContentView(paramInt);
    zzaE();
  }
  
  public void setContentView(View paramView)
  {
    super.setContentView(paramView);
    zzaE();
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    zzaE();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/AdActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */