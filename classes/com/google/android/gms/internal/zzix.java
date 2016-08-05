package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

abstract class zzix
{
  private final WeakReference<View> zzJS;
  
  public zzix(View paramView)
  {
    this.zzJS = new WeakReference(paramView);
  }
  
  public final void detach()
  {
    ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
    if (localViewTreeObserver != null) {
      zzb(localViewTreeObserver);
    }
  }
  
  protected ViewTreeObserver getViewTreeObserver()
  {
    View localView = (View)this.zzJS.get();
    if (localView == null) {}
    for (ViewTreeObserver localViewTreeObserver = null;; localViewTreeObserver = null) {
      do
      {
        return localViewTreeObserver;
        localViewTreeObserver = localView.getViewTreeObserver();
      } while ((localViewTreeObserver != null) && (localViewTreeObserver.isAlive()));
    }
  }
  
  protected abstract void zza(ViewTreeObserver paramViewTreeObserver);
  
  protected abstract void zzb(ViewTreeObserver paramViewTreeObserver);
  
  public final void zzgW()
  {
    ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
    if (localViewTreeObserver != null) {
      zza(localViewTreeObserver);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */