package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zzg.zza;

public final class SignInButton
  extends FrameLayout
  implements View.OnClickListener
{
  public static final int COLOR_DARK = 0;
  public static final int COLOR_LIGHT = 1;
  public static final int SIZE_ICON_ONLY = 2;
  public static final int SIZE_STANDARD = 0;
  public static final int SIZE_WIDE = 1;
  private int mColor;
  private int mSize;
  private View zzaat;
  private View.OnClickListener zzaau = null;
  
  public SignInButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setStyle(0, 0);
  }
  
  private static Button zza(Context paramContext, int paramInt1, int paramInt2)
  {
    zzab localzzab = new zzab(paramContext);
    localzzab.zza(paramContext.getResources(), paramInt1, paramInt2);
    return localzzab;
  }
  
  private void zzai(Context paramContext)
  {
    if (this.zzaat != null) {
      removeView(this.zzaat);
    }
    try
    {
      this.zzaat = zzaa.zzb(paramContext, this.mSize, this.mColor);
      addView(this.zzaat);
      this.zzaat.setEnabled(isEnabled());
      this.zzaat.setOnClickListener(this);
      return;
    }
    catch (zzg.zza localzza)
    {
      for (;;)
      {
        Log.w("SignInButton", "Sign in button not found, using placeholder instead");
        this.zzaat = zza(paramContext, this.mSize, this.mColor);
      }
    }
  }
  
  public void onClick(View paramView)
  {
    if ((this.zzaau != null) && (paramView == this.zzaat)) {
      this.zzaau.onClick(this);
    }
  }
  
  public void setColorScheme(int paramInt)
  {
    setStyle(this.mSize, paramInt);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.zzaat.setEnabled(paramBoolean);
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.zzaau = paramOnClickListener;
    if (this.zzaat != null) {
      this.zzaat.setOnClickListener(this);
    }
  }
  
  public void setSize(int paramInt)
  {
    setStyle(paramInt, this.mColor);
  }
  
  public void setStyle(int paramInt1, int paramInt2)
  {
    boolean bool1;
    if ((paramInt1 >= 0) && (paramInt1 < 3))
    {
      bool1 = true;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramInt1);
      zzx.zza(bool1, "Unknown button size %d", arrayOfObject1);
      if ((paramInt2 < 0) || (paramInt2 >= 2)) {
        break label92;
      }
    }
    label92:
    for (boolean bool2 = true;; bool2 = false)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt2);
      zzx.zza(bool2, "Unknown color scheme %s", arrayOfObject2);
      this.mSize = paramInt1;
      this.mColor = paramInt2;
      zzai(getContext());
      return;
      bool1 = false;
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/SignInButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */