package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzg;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate>
{
  private T zzapn;
  private Bundle zzapo;
  private LinkedList<zza> zzapp;
  private final zzf<T> zzapq = new zzf()
  {
    public void zza(T paramAnonymousT)
    {
      zza.zza(zza.this, paramAnonymousT);
      Iterator localIterator = zza.zza(zza.this).iterator();
      while (localIterator.hasNext()) {
        ((zza.zza)localIterator.next()).zzb(zza.zzb(zza.this));
      }
      zza.zza(zza.this).clear();
      zza.zza(zza.this, null);
    }
  };
  
  private void zza(Bundle paramBundle, zza paramzza)
  {
    if (this.zzapn != null)
    {
      paramzza.zzb(this.zzapn);
      return;
    }
    if (this.zzapp == null) {
      this.zzapp = new LinkedList();
    }
    this.zzapp.add(paramzza);
    if (paramBundle != null)
    {
      if (this.zzapo != null) {
        break label78;
      }
      this.zzapo = ((Bundle)paramBundle.clone());
    }
    for (;;)
    {
      zza(this.zzapq);
      break;
      label78:
      this.zzapo.putAll(paramBundle);
    }
  }
  
  public static void zzb(FrameLayout paramFrameLayout)
  {
    Context localContext = paramFrameLayout.getContext();
    final int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(localContext);
    String str1 = zzg.zzc(localContext, i, GooglePlayServicesUtil.zzaf(localContext));
    String str2 = zzg.zzh(localContext, i);
    LinearLayout localLinearLayout = new LinearLayout(paramFrameLayout.getContext());
    localLinearLayout.setOrientation(1);
    localLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView(localLinearLayout);
    TextView localTextView = new TextView(paramFrameLayout.getContext());
    localTextView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    localTextView.setText(str1);
    localLinearLayout.addView(localTextView);
    if (str2 != null)
    {
      Button localButton = new Button(localContext);
      localButton.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      localButton.setText(str2);
      localLinearLayout.addView(localButton);
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          zza.this.startActivity(GooglePlayServicesUtil.zzbj(i));
        }
      });
    }
  }
  
  private void zzer(int paramInt)
  {
    while ((!this.zzapp.isEmpty()) && (((zza)this.zzapp.getLast()).getState() >= paramInt)) {
      this.zzapp.removeLast();
    }
  }
  
  public void onCreate(final Bundle paramBundle)
  {
    zza(paramBundle, new zza()
    {
      public int getState()
      {
        return 1;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onCreate(paramBundle);
      }
    });
  }
  
  public View onCreateView(final LayoutInflater paramLayoutInflater, final ViewGroup paramViewGroup, final Bundle paramBundle)
  {
    final FrameLayout localFrameLayout = new FrameLayout(paramLayoutInflater.getContext());
    zza(paramBundle, new zza()
    {
      public int getState()
      {
        return 2;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        localFrameLayout.removeAllViews();
        localFrameLayout.addView(zza.zzb(zza.this).onCreateView(paramLayoutInflater, paramViewGroup, paramBundle));
      }
    });
    if (this.zzapn == null) {
      zza(localFrameLayout);
    }
    return localFrameLayout;
  }
  
  public void onDestroy()
  {
    if (this.zzapn != null) {
      this.zzapn.onDestroy();
    }
    for (;;)
    {
      return;
      zzer(1);
    }
  }
  
  public void onDestroyView()
  {
    if (this.zzapn != null) {
      this.zzapn.onDestroyView();
    }
    for (;;)
    {
      return;
      zzer(2);
    }
  }
  
  public void onInflate(final Activity paramActivity, final Bundle paramBundle1, final Bundle paramBundle2)
  {
    zza(paramBundle2, new zza()
    {
      public int getState()
      {
        return 0;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onInflate(paramActivity, paramBundle1, paramBundle2);
      }
    });
  }
  
  public void onLowMemory()
  {
    if (this.zzapn != null) {
      this.zzapn.onLowMemory();
    }
  }
  
  public void onPause()
  {
    if (this.zzapn != null) {
      this.zzapn.onPause();
    }
    for (;;)
    {
      return;
      zzer(5);
    }
  }
  
  public void onResume()
  {
    zza(null, new zza()
    {
      public int getState()
      {
        return 5;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onResume();
      }
    });
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.zzapn != null) {
      this.zzapn.onSaveInstanceState(paramBundle);
    }
    for (;;)
    {
      return;
      if (this.zzapo != null) {
        paramBundle.putAll(this.zzapo);
      }
    }
  }
  
  public void onStart()
  {
    zza(null, new zza()
    {
      public int getState()
      {
        return 4;
      }
      
      public void zzb(LifecycleDelegate paramAnonymousLifecycleDelegate)
      {
        zza.zzb(zza.this).onStart();
      }
    });
  }
  
  public void onStop()
  {
    if (this.zzapn != null) {
      this.zzapn.onStop();
    }
    for (;;)
    {
      return;
      zzer(4);
    }
  }
  
  protected void zza(FrameLayout paramFrameLayout)
  {
    zzb(paramFrameLayout);
  }
  
  protected abstract void zza(zzf<T> paramzzf);
  
  public T zzrZ()
  {
    return this.zzapn;
  }
  
  private static abstract interface zza
  {
    public abstract int getState();
    
    public abstract void zzb(LifecycleDelegate paramLifecycleDelegate);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/dynamic/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */