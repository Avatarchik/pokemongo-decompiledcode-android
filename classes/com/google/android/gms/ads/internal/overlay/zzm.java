package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzm
  extends FrameLayout
  implements View.OnClickListener
{
  private final ImageButton zzBW;
  private final zzo zzBX;
  
  public zzm(Context paramContext, int paramInt, zzo paramzzo)
  {
    super(paramContext);
    this.zzBX = paramzzo;
    setOnClickListener(this);
    this.zzBW = new ImageButton(paramContext);
    this.zzBW.setImageResource(17301527);
    this.zzBW.setBackgroundColor(0);
    this.zzBW.setOnClickListener(this);
    this.zzBW.setPadding(0, 0, 0, 0);
    this.zzBW.setContentDescription("Interstitial close button");
    int i = zzl.zzcF().zzb(paramContext, paramInt);
    addView(this.zzBW, new FrameLayout.LayoutParams(i, i, 17));
  }
  
  public void onClick(View paramView)
  {
    if (this.zzBX != null) {
      this.zzBX.zzeE();
    }
  }
  
  public void zza(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean2) {
      if (paramBoolean1) {
        this.zzBW.setVisibility(4);
      }
    }
    for (;;)
    {
      return;
      this.zzBW.setVisibility(8);
      continue;
      this.zzBW.setVisibility(0);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */