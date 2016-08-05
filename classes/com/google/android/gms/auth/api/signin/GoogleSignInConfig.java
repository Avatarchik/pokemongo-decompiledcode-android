package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzc;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GoogleSignInConfig
  implements Api.ApiOptions.Optional, SafeParcelable
{
  public static final Parcelable.Creator<GoogleSignInConfig> CREATOR = new zze();
  public static final Scope zzTe = new Scope("profile");
  public static final Scope zzTf = new Scope("email");
  public static final Scope zzTg = new Scope("openid");
  public static final GoogleSignInConfig zzTh = new zza().zzmc();
  final int versionCode;
  private Account zzQd;
  private final ArrayList<Scope> zzSX;
  private boolean zzTi;
  private final boolean zzTj;
  private final boolean zzTk;
  private String zzTl;
  
  GoogleSignInConfig(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString)
  {
    this.versionCode = paramInt;
    this.zzSX = paramArrayList;
    this.zzQd = paramAccount;
    this.zzTi = paramBoolean1;
    this.zzTj = paramBoolean2;
    this.zzTk = paramBoolean3;
    this.zzTl = paramString;
  }
  
  private GoogleSignInConfig(Set<Scope> paramSet, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString)
  {
    this(1, new ArrayList(paramSet), paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject == null) {}
    for (;;)
    {
      return bool1;
      try
      {
        GoogleSignInConfig localGoogleSignInConfig = (GoogleSignInConfig)paramObject;
        if ((this.zzSX.size() != localGoogleSignInConfig.zzlS().size()) || (!this.zzSX.containsAll(localGoogleSignInConfig.zzlS()))) {
          continue;
        }
        if (this.zzQd == null)
        {
          if (localGoogleSignInConfig.getAccount() != null) {
            continue;
          }
          label62:
          if (!TextUtils.isEmpty(this.zzTl)) {
            break label142;
          }
          if (!TextUtils.isEmpty(localGoogleSignInConfig.zzmb())) {
            continue;
          }
        }
        while ((this.zzTk == localGoogleSignInConfig.zzma()) && (this.zzTi == localGoogleSignInConfig.zzlY()) && (this.zzTj == localGoogleSignInConfig.zzlZ()))
        {
          bool1 = true;
          break;
          if (!this.zzQd.equals(localGoogleSignInConfig.getAccount())) {
            break;
          }
          break label62;
          label142:
          boolean bool2 = this.zzTl.equals(localGoogleSignInConfig.zzmb());
          if (!bool2) {
            break;
          }
        }
      }
      catch (ClassCastException localClassCastException) {}
    }
  }
  
  public Account getAccount()
  {
    return this.zzQd;
  }
  
  public int hashCode()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzSX.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Scope)localIterator.next()).zznG());
    }
    Collections.sort(localArrayList);
    return new zzc().zzl(localArrayList).zzl(this.zzQd).zzl(this.zzTl).zzP(this.zzTk).zzP(this.zzTi).zzP(this.zzTj).zzmd();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public ArrayList<Scope> zzlS()
  {
    return new ArrayList(this.zzSX);
  }
  
  public boolean zzlY()
  {
    return this.zzTi;
  }
  
  public boolean zzlZ()
  {
    return this.zzTj;
  }
  
  public boolean zzma()
  {
    return this.zzTk;
  }
  
  public String zzmb()
  {
    return this.zzTl;
  }
  
  public static final class zza
  {
    private Account zzQd;
    private boolean zzTi;
    private boolean zzTj;
    private boolean zzTk;
    private String zzTl;
    private Set<Scope> zzTm;
    
    public zza()
    {
      Scope[] arrayOfScope = new Scope[1];
      arrayOfScope[0] = GoogleSignInConfig.zzTg;
      this.zzTm = new HashSet(Arrays.asList(arrayOfScope));
    }
    
    public GoogleSignInConfig zzmc()
    {
      return new GoogleSignInConfig(this.zzTm, this.zzQd, this.zzTi, this.zzTj, this.zzTk, this.zzTl, null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/signin/GoogleSignInConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */