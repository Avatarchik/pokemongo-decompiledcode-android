package com.google.android.gms.auth.firstparty.shared;

public enum zzd
{
  private final String zzUK;
  
  static
  {
    zzUA = new zzd("NOT_LOGGED_IN", 39, "NotLoggedIn");
    zzUB = new zzd("NO_GMAIL", 40, "NoGmail");
    zzUC = new zzd("REQUEST_DENIED", 41, "RequestDenied");
    zzUD = new zzd("SERVER_ERROR", 42, "ServerError");
    zzUE = new zzd("USERNAME_UNAVAILABLE", 43, "UsernameUnavailable");
    zzUF = new zzd("GPLUS_OTHER", 44, "GPlusOther");
    zzUG = new zzd("GPLUS_NICKNAME", 45, "GPlusNickname");
    zzUH = new zzd("GPLUS_INVALID_CHAR", 46, "GPlusInvalidChar");
    zzUI = new zzd("GPLUS_INTERSTITIAL", 47, "GPlusInterstitial");
    zzUJ = new zzd("GPLUS_PROFILE_ERROR", 48, "ProfileUpgradeError");
    zzd[] arrayOfzzd = new zzd[49];
    arrayOfzzd[0] = zzTN;
    arrayOfzzd[1] = zzTO;
    arrayOfzzd[2] = zzTP;
    arrayOfzzd[3] = zzTQ;
    arrayOfzzd[4] = zzTR;
    arrayOfzzd[5] = zzTS;
    arrayOfzzd[6] = zzTT;
    arrayOfzzd[7] = zzTU;
    arrayOfzzd[8] = zzTV;
    arrayOfzzd[9] = zzTW;
    arrayOfzzd[10] = zzTX;
    arrayOfzzd[11] = zzTY;
    arrayOfzzd[12] = zzTZ;
    arrayOfzzd[13] = zzUa;
    arrayOfzzd[14] = zzUb;
    arrayOfzzd[15] = zzUc;
    arrayOfzzd[16] = zzUd;
    arrayOfzzd[17] = zzUe;
    arrayOfzzd[18] = zzUf;
    arrayOfzzd[19] = zzUg;
    arrayOfzzd[20] = zzUh;
    arrayOfzzd[21] = zzUi;
    arrayOfzzd[22] = zzUj;
    arrayOfzzd[23] = zzUk;
    arrayOfzzd[24] = zzUl;
    arrayOfzzd[25] = zzUm;
    arrayOfzzd[26] = zzUn;
    arrayOfzzd[27] = zzUo;
    arrayOfzzd[28] = zzUp;
    arrayOfzzd[29] = zzUq;
    arrayOfzzd[30] = zzUr;
    arrayOfzzd[31] = zzUs;
    arrayOfzzd[32] = zzUt;
    arrayOfzzd[33] = zzUu;
    arrayOfzzd[34] = zzUv;
    arrayOfzzd[35] = zzUw;
    arrayOfzzd[36] = zzUx;
    arrayOfzzd[37] = zzUy;
    arrayOfzzd[38] = zzUz;
    arrayOfzzd[39] = zzUA;
    arrayOfzzd[40] = zzUB;
    arrayOfzzd[41] = zzUC;
    arrayOfzzd[42] = zzUD;
    arrayOfzzd[43] = zzUE;
    arrayOfzzd[44] = zzUF;
    arrayOfzzd[45] = zzUG;
    arrayOfzzd[46] = zzUH;
    arrayOfzzd[47] = zzUI;
    arrayOfzzd[48] = zzUJ;
    zzUL = arrayOfzzd;
  }
  
  private zzd(String paramString)
  {
    this.zzUK = paramString;
  }
  
  public static boolean zza(zzd paramzzd)
  {
    if ((zzTV.equals(paramzzd)) || (zzUe.equals(paramzzd)) || (zzUh.equals(paramzzd)) || (zzTZ.equals(paramzzd)) || (zzUj.equals(paramzzd)) || (zzUl.equals(paramzzd)) || (zzb(paramzzd))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean zzb(zzd paramzzd)
  {
    if ((zzTO.equals(paramzzd)) || (zzUm.equals(paramzzd)) || (zzUn.equals(paramzzd)) || (zzUo.equals(paramzzd)) || (zzUp.equals(paramzzd)) || (zzUq.equals(paramzzd)) || (zzUr.equals(paramzzd)) || (zzUs.equals(paramzzd))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static final zzd zzbE(String paramString)
  {
    Object localObject1 = null;
    zzd[] arrayOfzzd = values();
    int i = arrayOfzzd.length;
    int j = 0;
    Object localObject2;
    if (j < i)
    {
      localObject2 = arrayOfzzd[j];
      if (!((zzd)localObject2).zzUK.equals(paramString)) {
        break label47;
      }
    }
    for (;;)
    {
      j++;
      localObject1 = localObject2;
      break;
      return (zzd)localObject1;
      label47:
      localObject2 = localObject1;
    }
  }
  
  public static boolean zzc(zzd paramzzd)
  {
    if ((zzTS.equals(paramzzd)) || (zzTT.equals(paramzzd))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/firstparty/shared/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */