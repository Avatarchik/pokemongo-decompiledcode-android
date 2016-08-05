package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzx;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class zzlp
  extends Fragment
  implements DialogInterface.OnCancelListener
{
  private static final GoogleApiAvailability zzacJ = ;
  private boolean mStarted;
  private boolean zzacK;
  private int zzacL = -1;
  private ConnectionResult zzacM;
  private final Handler zzacN = new Handler(Looper.getMainLooper());
  private zzll zzacO;
  private final SparseArray<zza> zzacP = new SparseArray();
  
  public static zzlp zza(FragmentActivity paramFragmentActivity)
  {
    zzx.zzci("Must be called from main thread of process");
    FragmentManager localFragmentManager = paramFragmentActivity.getSupportFragmentManager();
    try
    {
      zzlp localzzlp = (zzlp)localFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
      if ((localzzlp == null) || (localzzlp.isRemoving())) {
        localzzlp = null;
      }
      return localzzlp;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", localClassCastException);
    }
  }
  
  private void zza(int paramInt, ConnectionResult paramConnectionResult)
  {
    Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
    zza localzza = (zza)this.zzacP.get(paramInt);
    if (localzza != null)
    {
      zzbp(paramInt);
      GoogleApiClient.OnConnectionFailedListener localOnConnectionFailedListener = localzza.zzacS;
      if (localOnConnectionFailedListener != null) {
        localOnConnectionFailedListener.onConnectionFailed(paramConnectionResult);
      }
    }
    zzok();
  }
  
  public static zzlp zzb(FragmentActivity paramFragmentActivity)
  {
    zzlp localzzlp = zza(paramFragmentActivity);
    FragmentManager localFragmentManager = paramFragmentActivity.getSupportFragmentManager();
    if (localzzlp == null)
    {
      localzzlp = new zzlp();
      localFragmentManager.beginTransaction().add(localzzlp, "GmsSupportLifecycleFragment").commitAllowingStateLoss();
      localFragmentManager.executePendingTransactions();
    }
    return localzzlp;
  }
  
  private void zzok()
  {
    this.zzacK = false;
    this.zzacL = -1;
    this.zzacM = null;
    if (this.zzacO != null)
    {
      this.zzacO.unregister();
      this.zzacO = null;
    }
    for (int i = 0; i < this.zzacP.size(); i++) {
      ((zza)this.zzacP.valueAt(i)).zzacR.connect();
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    for (int i = 0; i < this.zzacP.size(); i++) {
      ((zza)this.zzacP.valueAt(i)).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i = 1;
    switch (paramInt1)
    {
    default: 
      i = 0;
      label31:
      if (i != 0) {
        zzok();
      }
      break;
    }
    for (;;)
    {
      return;
      if (zzacJ.isGooglePlayServicesAvailable(getActivity()) != 0) {
        break;
      }
      if ((goto 31) || (paramInt2 == -1)) {
        break label31;
      }
      if (paramInt2 != 0) {
        break;
      }
      this.zzacM = new ConnectionResult(13, null);
      break;
      zza(this.zzacL, this.zzacM);
    }
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    zza(this.zzacL, new ConnectionResult(13, null));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null)
    {
      this.zzacK = paramBundle.getBoolean("resolving_error", false);
      this.zzacL = paramBundle.getInt("failed_client_id", -1);
      if (this.zzacL >= 0) {
        this.zzacM = new ConnectionResult(paramBundle.getInt("failed_status"), (PendingIntent)paramBundle.getParcelable("failed_resolution"));
      }
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("resolving_error", this.zzacK);
    if (this.zzacL >= 0)
    {
      paramBundle.putInt("failed_client_id", this.zzacL);
      paramBundle.putInt("failed_status", this.zzacM.getErrorCode());
      paramBundle.putParcelable("failed_resolution", this.zzacM.getResolution());
    }
  }
  
  public void onStart()
  {
    super.onStart();
    this.mStarted = true;
    if (!this.zzacK) {
      for (int i = 0; i < this.zzacP.size(); i++) {
        ((zza)this.zzacP.valueAt(i)).zzacR.connect();
      }
    }
  }
  
  public void onStop()
  {
    super.onStop();
    this.mStarted = false;
    for (int i = 0; i < this.zzacP.size(); i++) {
      ((zza)this.zzacP.valueAt(i)).zzacR.disconnect();
    }
  }
  
  public void zza(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzb(paramGoogleApiClient, "GoogleApiClient instance cannot be null");
    if (this.zzacP.indexOfKey(paramInt) < 0) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Already managing a GoogleApiClient with id " + paramInt);
      zza localzza = new zza(paramInt, paramGoogleApiClient, paramOnConnectionFailedListener);
      this.zzacP.put(paramInt, localzza);
      if ((this.mStarted) && (!this.zzacK)) {
        paramGoogleApiClient.connect();
      }
      return;
    }
  }
  
  public void zzbp(int paramInt)
  {
    zza localzza = (zza)this.zzacP.get(paramInt);
    this.zzacP.remove(paramInt);
    if (localzza != null) {
      localzza.zzom();
    }
  }
  
  private class zza
    implements GoogleApiClient.OnConnectionFailedListener
  {
    public final int zzacQ;
    public final GoogleApiClient zzacR;
    public final GoogleApiClient.OnConnectionFailedListener zzacS;
    
    public zza(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this.zzacQ = paramInt;
      this.zzacR = paramGoogleApiClient;
      this.zzacS = paramOnConnectionFailedListener;
      paramGoogleApiClient.registerConnectionFailedListener(this);
    }
    
    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      paramPrintWriter.append(paramString).append("GoogleApiClient #").print(this.zzacQ);
      paramPrintWriter.println(":");
      this.zzacR.dump(paramString + "  ", paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      zzlp.zzd(zzlp.this).post(new zzlp.zzb(zzlp.this, this.zzacQ, paramConnectionResult));
    }
    
    public void zzom()
    {
      this.zzacR.unregisterConnectionFailedListener(this);
      this.zzacR.disconnect();
    }
  }
  
  private class zzb
    implements Runnable
  {
    private final int zzacU;
    private final ConnectionResult zzacV;
    
    public zzb(int paramInt, ConnectionResult paramConnectionResult)
    {
      this.zzacU = paramInt;
      this.zzacV = paramConnectionResult;
    }
    
    public void run()
    {
      if ((!zzlp.zza(zzlp.this)) || (zzlp.zzb(zzlp.this))) {}
      for (;;)
      {
        return;
        zzlp.zza(zzlp.this, true);
        zzlp.zza(zzlp.this, this.zzacU);
        zzlp.zza(zzlp.this, this.zzacV);
        if (this.zzacV.hasResolution())
        {
          try
          {
            int i = 1 + (1 + zzlp.this.getActivity().getSupportFragmentManager().getFragments().indexOf(zzlp.this) << 16);
            this.zzacV.startResolutionForResult(zzlp.this.getActivity(), i);
          }
          catch (IntentSender.SendIntentException localSendIntentException)
          {
            zzlp.zzc(zzlp.this);
          }
        }
        else if (zzlp.zzol().isUserResolvableError(this.zzacV.getErrorCode()))
        {
          GooglePlayServicesUtil.showErrorDialogFragment(this.zzacV.getErrorCode(), zzlp.this.getActivity(), zzlp.this, 2, zzlp.this);
        }
        else if (this.zzacV.getErrorCode() == 18)
        {
          final Dialog localDialog = zzlp.zzol().zza(zzlp.this.getActivity(), zzlp.this);
          zzlp.zza(zzlp.this, zzll.zza(zzlp.this.getActivity().getApplicationContext(), new zzll()
          {
            protected void zzoi()
            {
              zzlp.zzc(zzlp.this);
              localDialog.dismiss();
            }
          }));
        }
        else
        {
          zzlp.zza(zzlp.this, this.zzacU, this.zzacV);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */