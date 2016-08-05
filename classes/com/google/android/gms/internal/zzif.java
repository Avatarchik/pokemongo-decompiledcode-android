package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@zzgr
public class zzif
{
  private final Context mContext;
  private int mState = 0;
  private final float zzAC;
  private String zzII;
  private float zzIJ;
  private float zzIK;
  private float zzIL;
  
  public zzif(Context paramContext)
  {
    this.mContext = paramContext;
    this.zzAC = paramContext.getResources().getDisplayMetrics().density;
  }
  
  public zzif(Context paramContext, String paramString)
  {
    this(paramContext);
    this.zzII = paramString;
  }
  
  private void showDialog()
  {
    if (!(this.mContext instanceof Activity)) {
      zzb.zzaG("Can not create dialog without Activity Context");
    }
    for (;;)
    {
      return;
      final String str = zzaD(this.zzII);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
      localBuilder.setMessage(str);
      localBuilder.setTitle("Ad Information");
      localBuilder.setPositiveButton("Share", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          zzp.zzbv().zzb(zzif.zza(zzif.this), Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", str), "Share via"));
        }
      });
      localBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      });
      localBuilder.create().show();
    }
  }
  
  static String zzaD(String paramString)
  {
    String str2;
    if (TextUtils.isEmpty(paramString)) {
      str2 = "No debug information";
    }
    for (;;)
    {
      return str2;
      String str1 = paramString.replaceAll("\\+", "%20");
      Uri localUri = new Uri.Builder().encodedQuery(str1).build();
      StringBuilder localStringBuilder = new StringBuilder();
      Map localMap = zzp.zzbv().zze(localUri);
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str3 = (String)localIterator.next();
        localStringBuilder.append(str3).append(" = ").append((String)localMap.get(str3)).append("\n\n");
      }
      str2 = localStringBuilder.toString().trim();
      if (TextUtils.isEmpty(str2)) {
        str2 = "No debug information";
      }
    }
  }
  
  void zza(int paramInt, float paramFloat1, float paramFloat2)
  {
    if (paramInt == 0)
    {
      this.mState = 0;
      this.zzIJ = paramFloat1;
      this.zzIK = paramFloat2;
      this.zzIL = paramFloat2;
    }
    for (;;)
    {
      return;
      if (this.mState != -1) {
        if (paramInt == 2)
        {
          if (paramFloat2 > this.zzIK) {
            this.zzIK = paramFloat2;
          }
          for (;;)
          {
            if (this.zzIK - this.zzIL <= 30.0F * this.zzAC) {
              break label99;
            }
            this.mState = -1;
            break;
            if (paramFloat2 < this.zzIL) {
              this.zzIL = paramFloat2;
            }
          }
          label99:
          if ((this.mState == 0) || (this.mState == 2)) {
            if (paramFloat1 - this.zzIJ >= 50.0F * this.zzAC) {
              this.zzIJ = paramFloat1;
            }
          }
          for (this.mState = (1 + this.mState);; this.mState = (1 + this.mState))
          {
            do
            {
              if ((this.mState != 1) && (this.mState != 3)) {
                break label230;
              }
              if (paramFloat1 <= this.zzIJ) {
                break;
              }
              this.zzIJ = paramFloat1;
              break;
            } while (((this.mState != 1) && (this.mState != 3)) || (paramFloat1 - this.zzIJ > -50.0F * this.zzAC));
            this.zzIJ = paramFloat1;
          }
          label230:
          if ((this.mState == 2) && (paramFloat1 < this.zzIJ)) {
            this.zzIJ = paramFloat1;
          }
        }
        else if ((paramInt == 1) && (this.mState == 4))
        {
          showDialog();
        }
      }
    }
  }
  
  public void zzaC(String paramString)
  {
    this.zzII = paramString;
  }
  
  public void zze(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getHistorySize();
    for (int j = 0; j < i; j++) {
      zza(paramMotionEvent.getActionMasked(), paramMotionEvent.getHistoricalX(0, j), paramMotionEvent.getHistoricalY(0, j));
    }
    zza(paramMotionEvent.getActionMasked(), paramMotionEvent.getX(), paramMotionEvent.getY());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzif.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */