package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException
  extends UserRecoverableAuthException
{
  private final int zzRy;
  
  GooglePlayServicesAvailabilityException(int paramInt, String paramString, Intent paramIntent)
  {
    super(paramString, paramIntent);
    this.zzRy = paramInt;
  }
  
  public int getConnectionStatusCode()
  {
    return this.zzRy;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/GooglePlayServicesAvailabilityException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */