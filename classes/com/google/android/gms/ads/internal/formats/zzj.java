package com.google.android.gms.ads.internal.formats;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzco.zza;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zziz;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class zzj
  extends zzco.zza
  implements View.OnClickListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  private final FrameLayout zznZ;
  private final Object zzpd = new Object();
  private final FrameLayout zzwU;
  private final Map<String, WeakReference<View>> zzwV = new HashMap();
  private zzb zzwW;
  boolean zzwX = false;
  int zzwY;
  int zzwZ;
  private zzh zzwx;
  
  public zzj(FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    this.zzwU = paramFrameLayout1;
    this.zznZ = paramFrameLayout2;
    zziu.zza(this.zzwU, this);
    zziu.zza(this.zzwU, this);
    this.zzwU.setOnTouchListener(this);
  }
  
  int getMeasuredHeight()
  {
    return this.zzwU.getMeasuredHeight();
  }
  
  int getMeasuredWidth()
  {
    return this.zzwU.getMeasuredWidth();
  }
  
  public void onClick(View paramView)
  {
    JSONObject localJSONObject1;
    synchronized (this.zzpd)
    {
      if (this.zzwx == null) {
        return;
      }
      localJSONObject1 = new JSONObject();
      Iterator localIterator = this.zzwV.entrySet().iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          View localView = (View)((WeakReference)localEntry.getValue()).get();
          Point localPoint = zzj(localView);
          JSONObject localJSONObject3 = new JSONObject();
          try
          {
            localJSONObject3.put("width", zzp(localView.getWidth()));
            localJSONObject3.put("height", zzp(localView.getHeight()));
            localJSONObject3.put("x", zzp(localPoint.x));
            localJSONObject3.put("y", zzp(localPoint.y));
            localJSONObject1.put((String)localEntry.getKey(), localJSONObject3);
          }
          catch (JSONException localJSONException2)
          {
            com.google.android.gms.ads.internal.util.client.zzb.zzaH("Unable to get view rectangle for view " + (String)localEntry.getKey());
          }
        }
      }
    }
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject2.put("x", zzp(this.zzwY));
      localJSONObject2.put("y", zzp(this.zzwZ));
      if ((this.zzwW != null) && (this.zzwW.zzdu().equals(paramView))) {
        this.zzwx.zza("1007", localJSONObject1, localJSONObject2);
      }
    }
    catch (JSONException localJSONException1)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Unable to get click location");
        continue;
        this.zzwx.zza(paramView, this.zzwV, localJSONObject1, localJSONObject2);
      }
    }
  }
  
  public void onGlobalLayout()
  {
    synchronized (this.zzpd)
    {
      if (this.zzwX)
      {
        int i = getMeasuredWidth();
        int j = getMeasuredHeight();
        if ((i != 0) && (j != 0))
        {
          this.zznZ.setLayoutParams(new FrameLayout.LayoutParams(i, j));
          this.zzwX = false;
        }
      }
      if (this.zzwx != null) {
        this.zzwx.zzi(this.zzwU);
      }
      return;
    }
  }
  
  public void onScrollChanged()
  {
    synchronized (this.zzpd)
    {
      if (this.zzwx != null) {
        this.zzwx.zzi(this.zzwU);
      }
      return;
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    synchronized (this.zzpd)
    {
      if (this.zzwx != null)
      {
        Point localPoint = zzc(paramMotionEvent);
        this.zzwY = localPoint.x;
        this.zzwZ = localPoint.y;
        MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
        localMotionEvent.setLocation(localPoint.x, localPoint.y);
        this.zzwx.zzb(localMotionEvent);
        localMotionEvent.recycle();
      }
    }
    return false;
  }
  
  public zzd zzW(String paramString)
  {
    synchronized (this.zzpd)
    {
      WeakReference localWeakReference = (WeakReference)this.zzwV.get(paramString);
      if (localWeakReference == null)
      {
        localObject3 = null;
        zzd localzzd = zze.zzy(localObject3);
        return localzzd;
      }
      Object localObject3 = (View)localWeakReference.get();
    }
  }
  
  /* Error */
  public void zza(String paramString, zzd paramzzd)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 259	com/google/android/gms/dynamic/zze:zzp	(Lcom/google/android/gms/dynamic/zzd;)Ljava/lang/Object;
    //   4: checkcast 118	android/view/View
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 41	com/google/android/gms/ads/internal/formats/zzj:zzpd	Ljava/lang/Object;
    //   12: astore 4
    //   14: aload 4
    //   16: monitorenter
    //   17: aload_3
    //   18: ifnonnull +18 -> 36
    //   21: aload_0
    //   22: getfield 46	com/google/android/gms/ads/internal/formats/zzj:zzwV	Ljava/util/Map;
    //   25: aload_1
    //   26: invokeinterface 262 2 0
    //   31: pop
    //   32: aload 4
    //   34: monitorexit
    //   35: return
    //   36: aload_0
    //   37: getfield 46	com/google/android/gms/ads/internal/formats/zzj:zzwV	Ljava/util/Map;
    //   40: aload_1
    //   41: new 113	java/lang/ref/WeakReference
    //   44: dup
    //   45: aload_3
    //   46: invokespecial 265	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   49: invokeinterface 268 3 0
    //   54: pop
    //   55: aload_3
    //   56: aload_0
    //   57: invokevirtual 269	android/view/View:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   60: aload_3
    //   61: aload_0
    //   62: invokevirtual 273	android/view/View:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   65: goto -33 -> 32
    //   68: astore 5
    //   70: aload 4
    //   72: monitorexit
    //   73: aload 5
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	zzj
    //   0	76	1	paramString	String
    //   0	76	2	paramzzd	zzd
    //   7	54	3	localView	View
    //   12	59	4	localObject1	Object
    //   68	6	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   21	73	68	finally
  }
  
  public void zzb(zzd paramzzd)
  {
    synchronized (this.zzpd)
    {
      this.zzwX = true;
      final zzh localzzh = (zzh)zze.zzp(paramzzd);
      if (((this.zzwx instanceof zzg)) && (((zzg)this.zzwx).zzdB())) {
        ((zzg)this.zzwx).zzb(localzzh);
      }
      do
      {
        this.zznZ.removeAllViews();
        this.zzwW = zzf(localzzh);
        if (this.zzwW != null)
        {
          this.zzwV.put("1007", new WeakReference(this.zzwW.zzdu()));
          this.zznZ.addView(this.zzwW);
        }
        zzid.zzIE.post(new Runnable()
        {
          public void run()
          {
            zziz localzziz = localzzh.zzdC();
            if (localzziz != null) {
              zzj.zza(zzj.this).addView(localzziz.getView());
            }
          }
        });
        localzzh.zzh(this.zzwU);
        return;
        this.zzwx = localzzh;
      } while (!(this.zzwx instanceof zzg));
      ((zzg)this.zzwx).zzb(null);
    }
  }
  
  Point zzc(MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = new int[2];
    this.zzwU.getLocationOnScreen(arrayOfInt);
    float f1 = paramMotionEvent.getRawX() - arrayOfInt[0];
    float f2 = paramMotionEvent.getRawY() - arrayOfInt[1];
    return new Point((int)f1, (int)f2);
  }
  
  zzb zzf(zzh paramzzh)
  {
    return paramzzh.zza(this);
  }
  
  Point zzj(View paramView)
  {
    Point localPoint1;
    if ((this.zzwW != null) && (this.zzwW.zzdu().equals(paramView)))
    {
      Point localPoint2 = new Point();
      this.zzwU.getGlobalVisibleRect(new Rect(), localPoint2);
      Point localPoint3 = new Point();
      paramView.getGlobalVisibleRect(new Rect(), localPoint3);
      localPoint1 = new Point(localPoint3.x - localPoint2.x, localPoint3.y - localPoint2.y);
    }
    for (;;)
    {
      return localPoint1;
      localPoint1 = new Point();
      paramView.getGlobalVisibleRect(new Rect(), localPoint1);
    }
  }
  
  int zzp(int paramInt)
  {
    return zzl.zzcF().zzc(this.zzwx.getContext(), paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/formats/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */