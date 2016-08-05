package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.zzce;
import com.google.android.gms.internal.zzcg;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zziz;
import java.util.HashMap;

@zzgr
public class zzk
  extends FrameLayout
  implements zzh
{
  private final FrameLayout zzBN;
  private final zzq zzBO;
  private zzi zzBP;
  private boolean zzBQ;
  private boolean zzBR;
  private TextView zzBS;
  private long zzBT;
  private long zzBU;
  private String zzBV;
  private final zziz zzoM;
  private String zzxZ;
  
  public zzk(Context paramContext, zziz paramzziz, int paramInt, zzcg paramzzcg, zzce paramzzce)
  {
    super(paramContext);
    this.zzoM = paramzziz;
    this.zzBN = new FrameLayout(paramContext);
    addView(this.zzBN, new FrameLayout.LayoutParams(-1, -1));
    zzb.zzs(paramzziz.zzhb());
    this.zzBP = paramzziz.zzhb().zzoH.zza(paramContext, paramzziz, paramInt, paramzzcg, paramzzce);
    if (this.zzBP != null) {
      this.zzBN.addView(this.zzBP, new FrameLayout.LayoutParams(-1, -1, 17));
    }
    this.zzBS = new TextView(paramContext);
    this.zzBS.setBackgroundColor(-16777216);
    zzeY();
    this.zzBO = new zzq(this);
    this.zzBO.zzfg();
    if (this.zzBP != null) {
      this.zzBP.zza(this);
    }
    if (this.zzBP == null) {
      zzh("AdVideoUnderlay Error", "Allocating player failed.");
    }
  }
  
  private void zza(String paramString, String... paramVarArgs)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("event", paramString);
    int i = paramVarArgs.length;
    int j = 0;
    Object localObject = null;
    if (j < i)
    {
      String str = paramVarArgs[j];
      if (localObject == null) {}
      for (;;)
      {
        j++;
        localObject = str;
        break;
        localHashMap.put(localObject, str);
        str = null;
      }
    }
    this.zzoM.zzb("onVideoEvent", localHashMap);
  }
  
  public static void zzd(zziz paramzziz)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("event", "no_video_view");
    paramzziz.zzb("onVideoEvent", localHashMap);
  }
  
  private void zzeY()
  {
    if (!zzfa())
    {
      this.zzBN.addView(this.zzBS, new FrameLayout.LayoutParams(-1, -1));
      this.zzBN.bringChildToFront(this.zzBS);
    }
  }
  
  private void zzeZ()
  {
    if (zzfa()) {
      this.zzBN.removeView(this.zzBS);
    }
  }
  
  private boolean zzfa()
  {
    if (this.zzBS.getParent() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void zzfb()
  {
    if (this.zzoM.zzgZ() == null) {
      return;
    }
    if (!this.zzBQ) {
      if ((0x80 & this.zzoM.zzgZ().getWindow().getAttributes().flags) == 0) {
        break label85;
      }
    }
    label85:
    for (boolean bool = true;; bool = false)
    {
      this.zzBR = bool;
      if (this.zzBR) {
        break;
      }
      this.zzoM.zzgZ().getWindow().addFlags(128);
      this.zzBQ = true;
      break;
      break;
    }
  }
  
  private void zzfc()
  {
    if (this.zzoM.zzgZ() == null) {}
    for (;;)
    {
      return;
      if ((this.zzBQ) && (!this.zzBR))
      {
        this.zzoM.zzgZ().getWindow().clearFlags(128);
        this.zzBQ = false;
      }
    }
  }
  
  public void destroy()
  {
    this.zzBO.cancel();
    if (this.zzBP != null) {
      this.zzBP.stop();
    }
    zzfc();
  }
  
  public void onPaused()
  {
    zza("pause", new String[0]);
    zzfc();
  }
  
  public void pause()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.pause();
    }
  }
  
  public void play()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.play();
    }
  }
  
  public void seekTo(int paramInt)
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.seekTo(paramInt);
    }
  }
  
  public void setMimeType(String paramString)
  {
    this.zzBV = paramString;
  }
  
  public void zza(float paramFloat)
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.zza(paramFloat);
    }
  }
  
  public void zzan(String paramString)
  {
    this.zzxZ = paramString;
  }
  
  public void zzd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt3 == 0) || (paramInt4 == 0)) {}
    for (;;)
    {
      return;
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(paramInt3 + 2, paramInt4 + 2);
      localLayoutParams.setMargins(paramInt1 - 1, paramInt2 - 1, 0, 0);
      this.zzBN.setLayoutParams(localLayoutParams);
      requestLayout();
    }
  }
  
  public void zzd(MotionEvent paramMotionEvent)
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.dispatchTouchEvent(paramMotionEvent);
    }
  }
  
  public void zzeQ() {}
  
  public void zzeR()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      if (this.zzBU == 0L)
      {
        float f = this.zzBP.getDuration() / 1000.0F;
        int i = this.zzBP.getVideoWidth();
        int j = this.zzBP.getVideoHeight();
        String[] arrayOfString = new String[6];
        arrayOfString[0] = "duration";
        arrayOfString[1] = String.valueOf(f);
        arrayOfString[2] = "videoWidth";
        arrayOfString[3] = String.valueOf(i);
        arrayOfString[4] = "videoHeight";
        arrayOfString[5] = String.valueOf(j);
        zza("canplaythrough", arrayOfString);
      }
    }
  }
  
  public void zzeS()
  {
    zzfb();
  }
  
  public void zzeT()
  {
    zza("ended", new String[0]);
    zzfc();
  }
  
  public void zzeU()
  {
    zzeY();
    this.zzBU = this.zzBT;
  }
  
  public void zzeV()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      if (!TextUtils.isEmpty(this.zzxZ))
      {
        this.zzBP.setMimeType(this.zzBV);
        this.zzBP.setVideoPath(this.zzxZ);
      }
      else
      {
        zza("no_src", new String[0]);
      }
    }
  }
  
  public void zzeW()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      TextView localTextView = new TextView(this.zzBP.getContext());
      localTextView.setText("AdMob - " + this.zzBP.zzer());
      localTextView.setTextColor(-65536);
      localTextView.setBackgroundColor(65280);
      this.zzBN.addView(localTextView, new FrameLayout.LayoutParams(-2, -2, 17));
      this.zzBN.bringChildToFront(localTextView);
    }
  }
  
  void zzeX()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      long l = this.zzBP.getCurrentPosition();
      if ((this.zzBT != l) && (l > 0L))
      {
        zzeZ();
        float f = (float)l / 1000.0F;
        String[] arrayOfString = new String[2];
        arrayOfString[0] = "time";
        arrayOfString[1] = String.valueOf(f);
        zza("timeupdate", arrayOfString);
        this.zzBT = l;
      }
    }
  }
  
  public void zzex()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.zzex();
    }
  }
  
  public void zzey()
  {
    if (this.zzBP == null) {}
    for (;;)
    {
      return;
      this.zzBP.zzey();
    }
  }
  
  public void zzh(String paramString1, String paramString2)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "what";
    arrayOfString[1] = paramString1;
    arrayOfString[2] = "extra";
    arrayOfString[3] = paramString2;
    zza("error", arrayOfString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */