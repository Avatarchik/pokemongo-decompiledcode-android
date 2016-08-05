package com.google.android.gms.ads.internal.util.client;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzmx;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@zzgr
public class zza
{
  public static final Handler zzJt = new Handler(Looper.getMainLooper());
  
  private void zza(ViewGroup paramViewGroup, AdSizeParcel paramAdSizeParcel, String paramString, int paramInt1, int paramInt2)
  {
    if (paramViewGroup.getChildCount() != 0) {}
    for (;;)
    {
      return;
      Context localContext = paramViewGroup.getContext();
      TextView localTextView = new TextView(localContext);
      localTextView.setGravity(17);
      localTextView.setText(paramString);
      localTextView.setTextColor(paramInt1);
      localTextView.setBackgroundColor(paramInt2);
      FrameLayout localFrameLayout = new FrameLayout(localContext);
      localFrameLayout.setBackgroundColor(paramInt1);
      int i = zzb(localContext, 3);
      localFrameLayout.addView(localTextView, new FrameLayout.LayoutParams(paramAdSizeParcel.widthPixels - i, paramAdSizeParcel.heightPixels - i, 17));
      paramViewGroup.addView(localFrameLayout, paramAdSizeParcel.widthPixels, paramAdSizeParcel.heightPixels);
    }
  }
  
  public String zzQ(Context paramContext)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    if (localContentResolver == null) {}
    for (String str = null;; str = Settings.Secure.getString(localContentResolver, "android_id"))
    {
      if ((str == null) || (zzgS())) {
        str = "emulator";
      }
      return zzaE(str);
    }
  }
  
  public boolean zzR(Context paramContext)
  {
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean zzS(Context paramContext)
  {
    boolean bool = false;
    if (paramContext.getResources().getConfiguration().orientation != 2) {}
    for (;;)
    {
      return bool;
      DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      if ((int)(localDisplayMetrics.heightPixels / localDisplayMetrics.density) < 600) {
        bool = true;
      }
    }
  }
  
  public boolean zzT(Context paramContext)
  {
    boolean bool1 = false;
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    int i;
    int k;
    boolean bool2;
    if (zzmx.zzqz())
    {
      localDisplay.getRealMetrics(localDisplayMetrics);
      i = localDisplayMetrics.heightPixels;
      k = localDisplayMetrics.widthPixels;
      localDisplay.getMetrics(localDisplayMetrics);
      int m = localDisplayMetrics.heightPixels;
      int n = localDisplayMetrics.widthPixels;
      if ((m != i) || (n != k)) {
        break label153;
      }
      bool2 = true;
      label85:
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label153:
      try
      {
        i = ((Integer)Display.class.getMethod("getRawHeight", new Class[0]).invoke(localDisplay, new Object[0])).intValue();
        int j = ((Integer)Display.class.getMethod("getRawWidth", new Class[0]).invoke(localDisplay, new Object[0])).intValue();
        k = j;
      }
      catch (Exception localException) {}
      bool2 = false;
      break label85;
    }
  }
  
  public int zzU(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
    if (i > 0) {}
    for (int j = paramContext.getResources().getDimensionPixelSize(i);; j = 0) {
      return j;
    }
  }
  
  public int zza(DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramDisplayMetrics);
  }
  
  public void zza(ViewGroup paramViewGroup, AdSizeParcel paramAdSizeParcel, String paramString)
  {
    zza(paramViewGroup, paramAdSizeParcel, paramString, -16777216, -1);
  }
  
  public void zza(ViewGroup paramViewGroup, AdSizeParcel paramAdSizeParcel, String paramString1, String paramString2)
  {
    zzb.zzaH(paramString2);
    zza(paramViewGroup, paramAdSizeParcel, paramString1, -65536, -16777216);
  }
  
  public String zzaE(String paramString)
  {
    int i = 0;
    if (i < 2) {}
    for (;;)
    {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(paramString.getBytes());
        Locale localLocale = Locale.US;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = new BigInteger(1, localMessageDigest.digest());
        String str2 = String.format(localLocale, "%032X", arrayOfObject);
        str1 = str2;
        return str1;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i++;
      }
      break;
      String str1 = null;
    }
  }
  
  public int zzb(Context paramContext, int paramInt)
  {
    return zza(paramContext.getResources().getDisplayMetrics(), paramInt);
  }
  
  public int zzb(DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    return Math.round(paramInt / paramDisplayMetrics.density);
  }
  
  public int zzc(Context paramContext, int paramInt)
  {
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    return zzb(localDisplayMetrics, paramInt);
  }
  
  public boolean zzgS()
  {
    return Build.DEVICE.startsWith("generic");
  }
  
  public boolean zzgT()
  {
    if (Looper.myLooper() == Looper.getMainLooper()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/util/client/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */