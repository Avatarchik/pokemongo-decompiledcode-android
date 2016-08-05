package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzie;
import java.util.Iterator;
import java.util.List;

class zzb
  extends RelativeLayout
{
  private static final float[] zzwj;
  private final RelativeLayout zzwk;
  private AnimationDrawable zzwl;
  
  static
  {
    float[] arrayOfFloat = new float[8];
    arrayOfFloat[0] = 5.0F;
    arrayOfFloat[1] = 5.0F;
    arrayOfFloat[2] = 5.0F;
    arrayOfFloat[3] = 5.0F;
    arrayOfFloat[4] = 5.0F;
    arrayOfFloat[5] = 5.0F;
    arrayOfFloat[6] = 5.0F;
    arrayOfFloat[7] = 5.0F;
    zzwj = arrayOfFloat;
  }
  
  public zzb(Context paramContext, zza paramzza)
  {
    super(paramContext);
    zzx.zzw(paramzza);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.addRule(10);
    localLayoutParams1.addRule(11);
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape(zzwj, null, null));
    localShapeDrawable.getPaint().setColor(paramzza.getBackgroundColor());
    this.zzwk = new RelativeLayout(paramContext);
    this.zzwk.setLayoutParams(localLayoutParams1);
    zzp.zzbx().zza(this.zzwk, localShapeDrawable);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    if (!TextUtils.isEmpty(paramzza.getText()))
    {
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
      TextView localTextView = new TextView(paramContext);
      localTextView.setLayoutParams(localLayoutParams3);
      localTextView.setId(1195835393);
      localTextView.setTypeface(Typeface.DEFAULT);
      localTextView.setText(paramzza.getText());
      localTextView.setTextColor(paramzza.getTextColor());
      localTextView.setTextSize(paramzza.getTextSize());
      localTextView.setPadding(zzl.zzcF().zzb(paramContext, 4), 0, zzl.zzcF().zzb(paramContext, 4), 0);
      this.zzwk.addView(localTextView);
      localLayoutParams2.addRule(1, localTextView.getId());
    }
    ImageView localImageView = new ImageView(paramContext);
    localImageView.setLayoutParams(localLayoutParams2);
    localImageView.setId(1195835394);
    List localList = paramzza.zzds();
    if (localList.size() > 1)
    {
      this.zzwl = new AnimationDrawable();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Drawable localDrawable = (Drawable)localIterator.next();
        this.zzwl.addFrame(localDrawable, paramzza.zzdt());
      }
      zzp.zzbx().zza(localImageView, this.zzwl);
    }
    for (;;)
    {
      this.zzwk.addView(localImageView);
      addView(this.zzwk);
      return;
      if (localList.size() == 1) {
        localImageView.setImageDrawable((Drawable)localList.get(0));
      }
    }
  }
  
  public void onAttachedToWindow()
  {
    if (this.zzwl != null) {
      this.zzwl.start();
    }
    super.onAttachedToWindow();
  }
  
  public ViewGroup zzdu()
  {
    return this.zzwk;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/formats/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */