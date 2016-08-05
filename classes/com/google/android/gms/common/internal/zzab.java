package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.Button;
import com.google.android.gms.R.color;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;

public final class zzab
  extends Button
{
  public zzab(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public zzab(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, 16842824);
  }
  
  private void zza(Resources paramResources)
  {
    setTypeface(Typeface.DEFAULT_BOLD);
    setTextSize(14.0F);
    float f = paramResources.getDisplayMetrics().density;
    setMinHeight((int)(0.5F + f * 48.0F));
    setMinWidth((int)(0.5F + f * 48.0F));
  }
  
  private void zzb(Resources paramResources, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      throw new IllegalStateException("Unknown button size: " + paramInt1);
    }
    for (int i = zzd(paramInt2, R.drawable.common_signin_btn_text_dark, R.drawable.common_signin_btn_text_light); i == -1; i = zzd(paramInt2, R.drawable.common_signin_btn_icon_dark, R.drawable.common_signin_btn_icon_light)) {
      throw new IllegalStateException("Could not find background resource!");
    }
    setBackgroundDrawable(paramResources.getDrawable(i));
  }
  
  private void zzc(Resources paramResources, int paramInt1, int paramInt2)
  {
    setTextColor(paramResources.getColorStateList(zzd(paramInt2, R.color.common_signin_btn_text_dark, R.color.common_signin_btn_text_light)));
    switch (paramInt1)
    {
    default: 
      throw new IllegalStateException("Unknown button size: " + paramInt1);
    case 0: 
      setText(paramResources.getString(R.string.common_signin_button_text));
    }
    for (;;)
    {
      return;
      setText(paramResources.getString(R.string.common_signin_button_text_long));
      continue;
      setText(null);
    }
  }
  
  private int zzd(int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt1)
    {
    default: 
      throw new IllegalStateException("Unknown color scheme: " + paramInt1);
    case 1: 
      paramInt2 = paramInt3;
    }
    return paramInt2;
  }
  
  public void zza(Resources paramResources, int paramInt1, int paramInt2)
  {
    boolean bool1;
    if ((paramInt1 >= 0) && (paramInt1 < 3))
    {
      bool1 = true;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramInt1);
      zzx.zza(bool1, "Unknown button size %d", arrayOfObject1);
      if ((paramInt2 < 0) || (paramInt2 >= 2)) {
        break label96;
      }
    }
    label96:
    for (boolean bool2 = true;; bool2 = false)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt2);
      zzx.zza(bool2, "Unknown color scheme %s", arrayOfObject2);
      zza(paramResources);
      zzb(paramResources, paramInt1, paramInt2);
      zzc(paramResources, paramInt1, paramInt2);
      return;
      bool1 = false;
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */