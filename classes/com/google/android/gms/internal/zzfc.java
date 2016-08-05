package com.google.android.gms.internal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zzgr
public class zzfc
  extends zzfh
{
  static final Set<String> zzAb;
  private String zzAc = "top-right";
  private boolean zzAd = true;
  private int zzAe = 0;
  private int zzAf = 0;
  private int zzAg = 0;
  private int zzAh = 0;
  private final Activity zzAi;
  private ImageView zzAj;
  private LinearLayout zzAk;
  private zzfi zzAl;
  private PopupWindow zzAm;
  private RelativeLayout zzAn;
  private ViewGroup zzAo;
  private int zznQ = -1;
  private int zznR = -1;
  private final zziz zzoM;
  private final Object zzpd = new Object();
  private AdSizeParcel zzzm;
  
  static
  {
    String[] arrayOfString = new String[7];
    arrayOfString[0] = "top-left";
    arrayOfString[1] = "top-right";
    arrayOfString[2] = "top-center";
    arrayOfString[3] = "center";
    arrayOfString[4] = "bottom-left";
    arrayOfString[5] = "bottom-right";
    arrayOfString[6] = "bottom-center";
    zzAb = new HashSet(Arrays.asList(arrayOfString));
  }
  
  public zzfc(zziz paramzziz, zzfi paramzzfi)
  {
    super(paramzziz, "resize");
    this.zzoM = paramzziz;
    this.zzAi = paramzziz.zzgZ();
    this.zzAl = paramzzfi;
  }
  
  private int[] zzee()
  {
    Object localObject;
    if (!zzeg()) {
      localObject = null;
    }
    for (;;)
    {
      return (int[])localObject;
      if (!this.zzAd) {
        break;
      }
      localObject = new int[2];
      localObject[0] = (this.zzAe + this.zzAg);
      localObject[1] = (this.zzAf + this.zzAh);
    }
    int[] arrayOfInt1 = zzp.zzbv().zzh(this.zzAi);
    int[] arrayOfInt2 = zzp.zzbv().zzj(this.zzAi);
    int i = arrayOfInt1[0];
    int j = this.zzAe + this.zzAg;
    int k = this.zzAf + this.zzAh;
    if (j < 0)
    {
      j = 0;
      label110:
      if (k >= arrayOfInt2[0]) {
        break label169;
      }
      k = arrayOfInt2[0];
    }
    for (;;)
    {
      int[] arrayOfInt3 = new int[2];
      arrayOfInt3[0] = j;
      arrayOfInt3[1] = k;
      localObject = arrayOfInt3;
      break;
      if (j + this.zznQ <= i) {
        break label110;
      }
      j = i - this.zznQ;
      break label110;
      label169:
      if (k + this.zznR > arrayOfInt2[1]) {
        k = arrayOfInt2[1] - this.zznR;
      }
    }
  }
  
  private void zzf(Map<String, String> paramMap)
  {
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("width"))) {
      this.zznQ = zzp.zzbv().zzaA((String)paramMap.get("width"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("height"))) {
      this.zznR = zzp.zzbv().zzaA((String)paramMap.get("height"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("offsetX"))) {
      this.zzAg = zzp.zzbv().zzaA((String)paramMap.get("offsetX"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("offsetY"))) {
      this.zzAh = zzp.zzbv().zzaA((String)paramMap.get("offsetY"));
    }
    if (!TextUtils.isEmpty((CharSequence)paramMap.get("allowOffscreen"))) {
      this.zzAd = Boolean.parseBoolean((String)paramMap.get("allowOffscreen"));
    }
    String str = (String)paramMap.get("customClosePosition");
    if (!TextUtils.isEmpty(str)) {
      this.zzAc = str;
    }
  }
  
  public void zza(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    synchronized (this.zzpd)
    {
      this.zzAe = paramInt1;
      this.zzAf = paramInt2;
      if ((this.zzAm != null) && (paramBoolean))
      {
        int[] arrayOfInt = zzee();
        if (arrayOfInt != null)
        {
          this.zzAm.update(zzl.zzcF().zzb(this.zzAi, arrayOfInt[0]), zzl.zzcF().zzb(this.zzAi, arrayOfInt[1]), this.zzAm.getWidth(), this.zzAm.getHeight());
          zzc(arrayOfInt[0], arrayOfInt[1]);
        }
      }
      else
      {
        return;
      }
      zzn(true);
    }
  }
  
  void zzb(int paramInt1, int paramInt2)
  {
    if (this.zzAl != null) {
      this.zzAl.zza(paramInt1, paramInt2, this.zznQ, this.zznR);
    }
  }
  
  void zzc(int paramInt1, int paramInt2)
  {
    zzb(paramInt1, paramInt2 - zzp.zzbv().zzj(this.zzAi)[0], this.zznQ, this.zznR);
  }
  
  public void zzd(int paramInt1, int paramInt2)
  {
    this.zzAe = paramInt1;
    this.zzAf = paramInt2;
  }
  
  boolean zzed()
  {
    if ((this.zznQ > -1) && (this.zznR > -1)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean zzef()
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        if (this.zzAm != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  boolean zzeg()
  {
    boolean bool = false;
    int[] arrayOfInt1 = zzp.zzbv().zzh(this.zzAi);
    int[] arrayOfInt2 = zzp.zzbv().zzj(this.zzAi);
    int i = arrayOfInt1[0];
    int j = arrayOfInt1[1];
    if ((this.zznQ < 50) || (this.zznQ > i)) {
      zzb.zzaH("Width is too small or too large.");
    }
    for (;;)
    {
      return bool;
      if ((this.zznR < 50) || (this.zznR > j))
      {
        zzb.zzaH("Height is too small or too large.");
      }
      else
      {
        if ((this.zznR != j) || (this.zznQ != i)) {
          break;
        }
        zzb.zzaH("Cannot resize to a full-screen ad.");
      }
    }
    String str;
    int k;
    label192:
    int m;
    int n;
    if (this.zzAd)
    {
      str = this.zzAc;
      k = -1;
      switch (str.hashCode())
      {
      default: 
        switch (k)
        {
        default: 
          m = -50 + (this.zzAe + this.zzAg + this.zznQ);
          n = this.zzAf + this.zzAh;
        }
        break;
      }
    }
    while ((m >= 0) && (m + 50 <= i) && (n >= arrayOfInt2[0]) && (n + 50 <= arrayOfInt2[1]))
    {
      bool = true;
      break;
      if (!str.equals("top-left")) {
        break label192;
      }
      k = 0;
      break label192;
      if (!str.equals("top-center")) {
        break label192;
      }
      k = 1;
      break label192;
      if (!str.equals("center")) {
        break label192;
      }
      k = 2;
      break label192;
      if (!str.equals("bottom-left")) {
        break label192;
      }
      k = 3;
      break label192;
      if (!str.equals("bottom-center")) {
        break label192;
      }
      k = 4;
      break label192;
      if (!str.equals("bottom-right")) {
        break label192;
      }
      k = 5;
      break label192;
      m = this.zzAe + this.zzAg;
      n = this.zzAf + this.zzAh;
      continue;
      m = -25 + (this.zzAe + this.zzAg + this.zznQ / 2);
      n = this.zzAf + this.zzAh;
      continue;
      m = -25 + (this.zzAe + this.zzAg + this.zznQ / 2);
      n = -25 + (this.zzAf + this.zzAh + this.zznR / 2);
      continue;
      m = this.zzAe + this.zzAg;
      n = -50 + (this.zzAf + this.zzAh + this.zznR);
      continue;
      m = -25 + (this.zzAe + this.zzAg + this.zznQ / 2);
      n = -50 + (this.zzAf + this.zzAh + this.zznR);
      continue;
      m = -50 + (this.zzAe + this.zzAg + this.zznQ);
      n = -50 + (this.zzAf + this.zzAh + this.zznR);
    }
  }
  
  public void zzg(Map<String, String> paramMap)
  {
    for (;;)
    {
      Window localWindow;
      int[] arrayOfInt;
      int i;
      int j;
      ViewParent localViewParent;
      Bitmap localBitmap;
      PopupWindow localPopupWindow;
      RelativeLayout.LayoutParams localLayoutParams;
      String str;
      synchronized (this.zzpd)
      {
        if (this.zzAi == null) {
          zzak("Not an activity context. Cannot resize.");
        } else if (this.zzoM.zzaN() == null) {
          zzak("Webview is not yet available, size is not set.");
        }
      }
      return;
      int k = -1;
      switch (k)
      {
      }
      boolean bool = false;
    }
  }
  
  public void zzn(boolean paramBoolean)
  {
    synchronized (this.zzpd)
    {
      if (this.zzAm != null)
      {
        this.zzAm.dismiss();
        this.zzAn.removeView(this.zzoM.getView());
        if (this.zzAo != null)
        {
          this.zzAo.removeView(this.zzAj);
          this.zzAo.addView(this.zzoM.getView());
          this.zzoM.zza(this.zzzm);
        }
        if (paramBoolean)
        {
          zzam("default");
          if (this.zzAl != null) {
            this.zzAl.zzbc();
          }
        }
        this.zzAm = null;
        this.zzAn = null;
        this.zzAo = null;
        this.zzAk = null;
      }
      return;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */