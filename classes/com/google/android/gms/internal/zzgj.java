package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

@zzgr
public class zzgj
  extends zzgi
{
  private Object zzDw = new Object();
  private PopupWindow zzDx;
  private boolean zzDy = false;
  
  zzgj(Context paramContext, zzhs.zza paramzza, zziz paramzziz, zzgg.zza paramzza1)
  {
    super(paramContext, paramzza, paramzziz, paramzza1);
  }
  
  private void zzfA()
  {
    synchronized (this.zzDw)
    {
      this.zzDy = true;
      if (((this.mContext instanceof Activity)) && (((Activity)this.mContext).isDestroyed())) {
        this.zzDx = null;
      }
      if (this.zzDx != null)
      {
        if (this.zzDx.isShowing()) {
          this.zzDx.dismiss();
        }
        this.zzDx = null;
      }
      return;
    }
  }
  
  public void cancel()
  {
    zzfA();
    super.cancel();
  }
  
  protected void zzfz()
  {
    Window localWindow;
    if ((this.mContext instanceof Activity))
    {
      localWindow = ((Activity)this.mContext).getWindow();
      if ((localWindow != null) && (localWindow.getDecorView() != null)) {}
    }
    for (;;)
    {
      return;
      if (((Activity)this.mContext).isDestroyed()) {
        continue;
      }
      FrameLayout localFrameLayout = new FrameLayout(this.mContext);
      localFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      localFrameLayout.addView(this.zzoM.getView(), -1, -1);
      synchronized (this.zzDw)
      {
        if (!this.zzDy) {}
      }
    }
  }
  
  protected void zzz(int paramInt)
  {
    zzfA();
    super.zzz(paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */