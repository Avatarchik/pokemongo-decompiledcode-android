package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.PopupWindow;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.internal.zzx;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzid
{
  private static final String zzIA = PublisherAdView.class.getName();
  private static final String zzIB = PublisherInterstitialAd.class.getName();
  private static final String zzIC = SearchAdView.class.getName();
  private static final String zzID = AdLoader.class.getName();
  public static final Handler zzIE = new zzia(Looper.getMainLooper());
  private static final String zzIy = AdView.class.getName();
  private static final String zzIz = InterstitialAd.class.getName();
  private boolean zzIF = true;
  private boolean zzIG = false;
  private String zzIa;
  private final Object zzpd = new Object();
  
  public static void runOnUiThread(Runnable paramRunnable)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
      paramRunnable.run();
    }
    for (;;)
    {
      return;
      zzIE.post(paramRunnable);
    }
  }
  
  private JSONArray zza(Collection<?> paramCollection)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      zza(localJSONArray, localIterator.next());
    }
    return localJSONArray;
  }
  
  private void zza(JSONArray paramJSONArray, Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof Bundle)) {
      paramJSONArray.put(zze((Bundle)paramObject));
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof Map)) {
        paramJSONArray.put(zzz((Map)paramObject));
      } else if ((paramObject instanceof Collection)) {
        paramJSONArray.put(zza((Collection)paramObject));
      } else if ((paramObject instanceof Object[])) {
        paramJSONArray.put(zza((Object[])paramObject));
      } else {
        paramJSONArray.put(paramObject);
      }
    }
  }
  
  private void zza(JSONObject paramJSONObject, String paramString, Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof Bundle)) {
      paramJSONObject.put(paramString, zze((Bundle)paramObject));
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof Map))
      {
        paramJSONObject.put(paramString, zzz((Map)paramObject));
      }
      else
      {
        if ((paramObject instanceof Collection))
        {
          if (paramString != null) {}
          for (;;)
          {
            paramJSONObject.put(paramString, zza((Collection)paramObject));
            break;
            paramString = "null";
          }
        }
        if ((paramObject instanceof Object[])) {
          paramJSONObject.put(paramString, zza(Arrays.asList((Object[])paramObject)));
        } else {
          paramJSONObject.put(paramString, paramObject);
        }
      }
    }
  }
  
  public static void zzb(Runnable paramRunnable)
  {
    if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
      paramRunnable.run();
    }
    for (;;)
    {
      return;
      zzic.zza(paramRunnable);
    }
  }
  
  private JSONObject zze(Bundle paramBundle)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      zza(localJSONObject, str, paramBundle.get(str));
    }
    return localJSONObject;
  }
  
  private boolean zzr(Context paramContext)
  {
    PowerManager localPowerManager = (PowerManager)paramContext.getSystemService("power");
    if (localPowerManager == null) {}
    for (boolean bool = false;; bool = localPowerManager.isScreenOn()) {
      return bool;
    }
  }
  
  public boolean zzH(Context paramContext)
  {
    boolean bool1 = false;
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "com.google.android.gms.ads.AdActivity");
    ResolveInfo localResolveInfo = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if ((localResolveInfo == null) || (localResolveInfo.activityInfo == null))
    {
      zzb.zzaH("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
      return bool1;
    }
    if ((0x10 & localResolveInfo.activityInfo.configChanges) == 0)
    {
      Object[] arrayOfObject7 = new Object[1];
      arrayOfObject7[bool1] = "keyboard";
      zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject7));
    }
    for (boolean bool2 = false;; bool2 = true)
    {
      if ((0x20 & localResolveInfo.activityInfo.configChanges) == 0)
      {
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[bool1] = "keyboardHidden";
        zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject6));
        bool2 = false;
      }
      if ((0x80 & localResolveInfo.activityInfo.configChanges) == 0)
      {
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[bool1] = "orientation";
        zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject5));
        bool2 = false;
      }
      if ((0x100 & localResolveInfo.activityInfo.configChanges) == 0)
      {
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[bool1] = "screenLayout";
        zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject4));
        bool2 = false;
      }
      if ((0x200 & localResolveInfo.activityInfo.configChanges) == 0)
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[bool1] = "uiMode";
        zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject3));
        bool2 = false;
      }
      if ((0x400 & localResolveInfo.activityInfo.configChanges) == 0)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[bool1] = "screenSize";
        zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject2));
        bool2 = false;
      }
      if ((0x800 & localResolveInfo.activityInfo.configChanges) == 0)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[bool1] = "smallestScreenSize";
        zzb.zzaH(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", arrayOfObject1));
        break;
      }
      bool1 = bool2;
      break;
    }
  }
  
  public boolean zzI(Context paramContext)
  {
    boolean bool = true;
    if (this.zzIG) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.USER_PRESENT");
      localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
      paramContext.getApplicationContext().registerReceiver(new zza(null), localIntentFilter);
      this.zzIG = bool;
    }
  }
  
  protected String zzJ(Context paramContext)
  {
    return new WebView(paramContext).getSettings().getUserAgentString();
  }
  
  public AlertDialog.Builder zzK(Context paramContext)
  {
    return new AlertDialog.Builder(paramContext);
  }
  
  public zzbq zzL(Context paramContext)
  {
    return new zzbq(paramContext);
  }
  
  public String zzM(Context paramContext)
  {
    Object localObject;
    try
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if (localActivityManager == null)
      {
        localObject = null;
      }
      else
      {
        List localList = localActivityManager.getRunningTasks(1);
        if ((localList != null) && (!localList.isEmpty()))
        {
          ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localList.get(0);
          if ((localRunningTaskInfo != null) && (localRunningTaskInfo.topActivity != null))
          {
            String str = localRunningTaskInfo.topActivity.getClassName();
            localObject = str;
          }
        }
      }
    }
    catch (Exception localException)
    {
      localObject = null;
    }
    return (String)localObject;
  }
  
  public boolean zzN(Context paramContext)
  {
    try
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      KeyguardManager localKeyguardManager = (KeyguardManager)paramContext.getSystemService("keyguard");
      if ((localActivityManager != null) && (localKeyguardManager != null))
      {
        List localList = localActivityManager.getRunningAppProcesses();
        if (localList == null)
        {
          bool1 = false;
        }
        else
        {
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
            if (Process.myPid() == localRunningAppProcessInfo.pid) {
              if ((localRunningAppProcessInfo.importance == 100) && (!localKeyguardManager.inKeyguardRestrictedInputMode()))
              {
                boolean bool2 = zzr(paramContext);
                if (bool2) {
                  return true;
                }
              }
            }
          }
          bool1 = false;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      bool1 = false;
    }
    boolean bool1 = false;
    return bool1;
  }
  
  public Bitmap zzO(Context paramContext)
  {
    Object localObject = null;
    if (!(paramContext instanceof Activity)) {}
    for (;;)
    {
      return (Bitmap)localObject;
      try
      {
        View localView = ((Activity)paramContext).getWindow().getDecorView();
        Bitmap localBitmap = Bitmap.createBitmap(localView.getWidth(), localView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        localView.layout(0, 0, localView.getWidth(), localView.getHeight());
        localView.draw(localCanvas);
        localObject = localBitmap;
      }
      catch (RuntimeException localRuntimeException)
      {
        zzb.zzb("Fail to capture screen shot", localRuntimeException);
      }
    }
  }
  
  public DisplayMetrics zza(WindowManager paramWindowManager)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public PopupWindow zza(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return new PopupWindow(paramView, paramInt1, paramInt2, paramBoolean);
  }
  
  public String zza(Context paramContext, View paramView, AdSizeParcel paramAdSizeParcel)
  {
    Object localObject = null;
    if (!((Boolean)zzby.zzvg.get()).booleanValue()) {}
    View localView;
    for (;;)
    {
      return (String)localObject;
      try
      {
        JSONObject localJSONObject1 = new JSONObject();
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("width", paramAdSizeParcel.width);
        localJSONObject2.put("height", paramAdSizeParcel.height);
        localJSONObject1.put("size", localJSONObject2);
        localJSONObject1.put("activity", zzM(paramContext));
        if (!paramAdSizeParcel.zztf)
        {
          JSONArray localJSONArray = new JSONArray();
          if (paramView != null)
          {
            ViewParent localViewParent = paramView.getParent();
            if (localViewParent != null)
            {
              int i = -1;
              if ((localViewParent instanceof ViewGroup)) {
                i = ((ViewGroup)localViewParent).indexOfChild(paramView);
              }
              JSONObject localJSONObject3 = new JSONObject();
              localJSONObject3.put("type", localViewParent.getClass().getName());
              localJSONObject3.put("index_of_child", i);
              localJSONArray.put(localJSONObject3);
            }
            if ((localViewParent == null) || (!(localViewParent instanceof View))) {
              break label264;
            }
            localView = (View)localViewParent;
            break;
          }
          if (localJSONArray.length() > 0) {
            localJSONObject1.put("parents", localJSONArray);
          }
        }
        String str = localJSONObject1.toString();
        localObject = str;
      }
      catch (JSONException localJSONException)
      {
        zzb.zzd("Fail to get view hierarchy json", localJSONException);
      }
    }
    for (;;)
    {
      paramView = localView;
      break;
      label264:
      localView = null;
    }
  }
  
  public String zza(Context paramContext, zzan paramzzan, String paramString)
  {
    if (paramzzan == null) {}
    for (;;)
    {
      return paramString;
      try
      {
        Uri localUri = Uri.parse(paramString);
        if (paramzzan.zzc(localUri)) {
          localUri = paramzzan.zza(localUri, paramContext);
        }
        String str = localUri.toString();
        paramString = str;
      }
      catch (Exception localException) {}
    }
  }
  
  public String zza(zziz paramzziz, String paramString)
  {
    return zza(paramzziz.getContext(), paramzziz.zzhg(), paramString);
  }
  
  public String zza(InputStreamReader paramInputStreamReader)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(8192);
    char[] arrayOfChar = new char['ࠀ'];
    for (;;)
    {
      int i = paramInputStreamReader.read(arrayOfChar);
      if (i == -1) {
        break;
      }
      localStringBuilder.append(arrayOfChar, 0, i);
    }
    return localStringBuilder.toString();
  }
  
  public String zza(StackTraceElement[] paramArrayOfStackTraceElement, String paramString)
  {
    int i;
    if (((Boolean)zzby.zzvr.get()).booleanValue())
    {
      i = 0;
      if (i + 1 >= paramArrayOfStackTraceElement.length) {
        break label155;
      }
      StackTraceElement localStackTraceElement = paramArrayOfStackTraceElement[i];
      String str2 = localStackTraceElement.getClassName();
      if ((!"loadAd".equalsIgnoreCase(localStackTraceElement.getMethodName())) || ((!zzIy.equalsIgnoreCase(str2)) && (!zzIz.equalsIgnoreCase(str2)) && (!zzIA.equalsIgnoreCase(str2)) && (!zzIB.equalsIgnoreCase(str2)) && (!zzIC.equalsIgnoreCase(str2)) && (!zzID.equalsIgnoreCase(str2)))) {}
    }
    label155:
    for (String str1 = paramArrayOfStackTraceElement[(i + 1)].getClassName();; str1 = null)
    {
      if ((str1 != null) && (!str1.contains(paramString))) {}
      for (;;)
      {
        return str1;
        i++;
        break;
        str1 = null;
      }
    }
  }
  
  JSONArray zza(Object[] paramArrayOfObject)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    int i = paramArrayOfObject.length;
    for (int j = 0; j < i; j++) {
      zza(localJSONArray, paramArrayOfObject[j]);
    }
    return localJSONArray;
  }
  
  public void zza(Activity paramActivity, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
  {
    Window localWindow = paramActivity.getWindow();
    if ((localWindow != null) && (localWindow.getDecorView() != null) && (localWindow.getDecorView().getViewTreeObserver() != null)) {
      localWindow.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(paramOnGlobalLayoutListener);
    }
  }
  
  public void zza(Activity paramActivity, ViewTreeObserver.OnScrollChangedListener paramOnScrollChangedListener)
  {
    Window localWindow = paramActivity.getWindow();
    if ((localWindow != null) && (localWindow.getDecorView() != null) && (localWindow.getDecorView().getViewTreeObserver() != null)) {
      localWindow.getDecorView().getViewTreeObserver().addOnScrollChangedListener(paramOnScrollChangedListener);
    }
  }
  
  public void zza(Context paramContext, String paramString, WebSettings paramWebSettings)
  {
    paramWebSettings.setUserAgentString(zzf(paramContext, paramString));
  }
  
  public void zza(Context paramContext, String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Context localContext = paramContext.getApplicationContext();
      if (localContext == null) {
        localContext = paramContext;
      }
      paramBundle.putString("os", Build.VERSION.RELEASE);
      paramBundle.putString("api", String.valueOf(Build.VERSION.SDK_INT));
      paramBundle.putString("device", zzp.zzbv().zzgE());
      paramBundle.putString("js", paramString1);
      paramBundle.putString("appid", localContext.getPackageName());
      paramBundle.putString("eids", TextUtils.join(",", zzby.zzdf()));
    }
    Uri.Builder localBuilder = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", paramString2);
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localBuilder.appendQueryParameter(str, paramBundle.getString(str));
    }
    zzp.zzbv().zzc(paramContext, paramString1, localBuilder.toString());
  }
  
  public void zza(Context paramContext, String paramString, List<String> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      new zzij(paramContext, paramString, (String)localIterator.next()).zzgz();
    }
  }
  
  public void zza(Context paramContext, String paramString1, List<String> paramList, String paramString2)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      new zzij(paramContext, paramString1, (String)localIterator.next(), paramString2).zzgz();
    }
  }
  
  public void zza(Context paramContext, String paramString, boolean paramBoolean, HttpURLConnection paramHttpURLConnection)
  {
    zza(paramContext, paramString, paramBoolean, paramHttpURLConnection, false);
  }
  
  public void zza(Context paramContext, String paramString1, boolean paramBoolean, HttpURLConnection paramHttpURLConnection, String paramString2)
  {
    paramHttpURLConnection.setConnectTimeout(60000);
    paramHttpURLConnection.setInstanceFollowRedirects(paramBoolean);
    paramHttpURLConnection.setReadTimeout(60000);
    paramHttpURLConnection.setRequestProperty("User-Agent", paramString2);
    paramHttpURLConnection.setUseCaches(false);
  }
  
  public void zza(Context paramContext, String paramString, boolean paramBoolean1, HttpURLConnection paramHttpURLConnection, boolean paramBoolean2)
  {
    paramHttpURLConnection.setConnectTimeout(60000);
    paramHttpURLConnection.setInstanceFollowRedirects(paramBoolean1);
    paramHttpURLConnection.setReadTimeout(60000);
    paramHttpURLConnection.setRequestProperty("User-Agent", zzf(paramContext, paramString));
    paramHttpURLConnection.setUseCaches(paramBoolean2);
  }
  
  public boolean zza(Context paramContext, Bitmap paramBitmap, String paramString)
  {
    boolean bool = false;
    zzx.zzcj("saveImageToFile must not be called on the main UI thread.");
    try
    {
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString, 0);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
      localFileOutputStream.close();
      paramBitmap.recycle();
      bool = true;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        zzb.zzb("Fail to save file", localException);
      }
    }
    return bool;
  }
  
  public boolean zza(PackageManager paramPackageManager, String paramString1, String paramString2)
  {
    if (paramPackageManager.checkPermission(paramString2, paramString1) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean zza(ClassLoader paramClassLoader, Class<?> paramClass, String paramString)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = paramClass.isAssignableFrom(Class.forName(paramString, false, paramClassLoader));
      bool1 = bool2;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return bool1;
  }
  
  public int zzaA(String paramString)
  {
    try
    {
      int j = Integer.parseInt(paramString);
      i = j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        zzb.zzaH("Could not parse value:" + localNumberFormatException);
        int i = 0;
      }
    }
    return i;
  }
  
  public boolean zzaB(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (boolean bool = false;; bool = paramString.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)")) {
      return bool;
    }
  }
  
  public String zzaz(String paramString)
  {
    return Uri.parse(paramString).buildUpon().query(null).build().toString();
  }
  
  public void zzb(Activity paramActivity, ViewTreeObserver.OnScrollChangedListener paramOnScrollChangedListener)
  {
    Window localWindow = paramActivity.getWindow();
    if ((localWindow != null) && (localWindow.getDecorView() != null) && (localWindow.getDecorView().getViewTreeObserver() != null)) {
      localWindow.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(paramOnScrollChangedListener);
    }
  }
  
  public void zzb(Context paramContext, Intent paramIntent)
  {
    try
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        paramIntent.addFlags(268435456);
        paramContext.startActivity(paramIntent);
      }
    }
  }
  
  public void zzb(Context paramContext, String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean)
  {
    if (((Boolean)zzby.zzvo.get()).booleanValue()) {
      zza(paramContext, paramString1, paramString2, paramBundle, paramBoolean);
    }
  }
  
  public void zzc(Context paramContext, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString2);
    zza(paramContext, paramString1, localArrayList);
  }
  
  public Map<String, String> zze(Uri paramUri)
  {
    if (paramUri == null) {}
    HashMap localHashMap;
    for (Object localObject = null;; localObject = localHashMap)
    {
      return (Map<String, String>)localObject;
      localHashMap = new HashMap();
      Iterator localIterator = zzp.zzbx().zzf(paramUri).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localHashMap.put(str, paramUri.getQueryParameter(str));
      }
    }
  }
  
  public String zzf(final Context paramContext, String paramString)
  {
    for (;;)
    {
      String str1;
      synchronized (this.zzpd)
      {
        if (this.zzIa != null)
        {
          str1 = this.zzIa;
          return str1;
        }
      }
      try
      {
        this.zzIa = zzp.zzbx().getDefaultUserAgent(paramContext);
        if (TextUtils.isEmpty(this.zzIa)) {
          if (!zzl.zzcF().zzgT())
          {
            this.zzIa = null;
            zzIE.post(new Runnable()
            {
              public void run()
              {
                synchronized (zzid.zza(zzid.this))
                {
                  zzid.zza(zzid.this, zzid.this.zzJ(paramContext));
                  zzid.zza(zzid.this).notifyAll();
                  return;
                }
              }
            });
            for (;;)
            {
              String str2 = this.zzIa;
              if (str2 != null) {
                break;
              }
              try
              {
                this.zzpd.wait();
              }
              catch (InterruptedException localInterruptedException)
              {
                this.zzIa = zzgC();
                zzb.zzaH("Interrupted, use default user agent: " + this.zzIa);
              }
            }
            localObject2 = finally;
            throw ((Throwable)localObject2);
          }
        }
        try
        {
          this.zzIa = zzJ(paramContext);
          this.zzIa = (this.zzIa + " (Mobile; " + paramString + ")");
          str1 = this.zzIa;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            this.zzIa = zzgC();
          }
        }
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
  }
  
  public Bitmap zzg(Context paramContext, String paramString)
  {
    zzx.zzcj("getBackgroundImage must not be called on the main UI thread.");
    try
    {
      FileInputStream localFileInputStream = paramContext.openFileInput(paramString);
      localBitmap = BitmapFactory.decodeStream(localFileInputStream);
      localFileInputStream.close();
      return localBitmap;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        zzb.e("Fail to get background image");
        Bitmap localBitmap = null;
      }
    }
  }
  
  public int[] zzg(Activity paramActivity)
  {
    Window localWindow = paramActivity.getWindow();
    int[] arrayOfInt;
    if (localWindow != null)
    {
      View localView = localWindow.findViewById(16908290);
      if (localView != null)
      {
        arrayOfInt = new int[2];
        arrayOfInt[0] = localView.getWidth();
        arrayOfInt[1] = localView.getHeight();
      }
    }
    for (;;)
    {
      return arrayOfInt;
      arrayOfInt = zzgF();
    }
  }
  
  public boolean zzgB()
  {
    return this.zzIF;
  }
  
  String zzgC()
  {
    StringBuffer localStringBuffer = new StringBuffer(256);
    localStringBuffer.append("Mozilla/5.0 (Linux; U; Android");
    if (Build.VERSION.RELEASE != null) {
      localStringBuffer.append(" ").append(Build.VERSION.RELEASE);
    }
    localStringBuffer.append("; ").append(Locale.getDefault());
    if (Build.DEVICE != null)
    {
      localStringBuffer.append("; ").append(Build.DEVICE);
      if (Build.DISPLAY != null) {
        localStringBuffer.append(" Build/").append(Build.DISPLAY);
      }
    }
    localStringBuffer.append(") AppleWebKit/533 Version/4.0 Safari/533");
    return localStringBuffer.toString();
  }
  
  public String zzgD()
  {
    UUID localUUID = UUID.randomUUID();
    byte[] arrayOfByte1 = BigInteger.valueOf(localUUID.getLeastSignificantBits()).toByteArray();
    byte[] arrayOfByte2 = BigInteger.valueOf(localUUID.getMostSignificantBits()).toByteArray();
    Object localObject = new BigInteger(1, arrayOfByte1).toString();
    int i = 0;
    while (i < 2) {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(arrayOfByte1);
        localMessageDigest.update(arrayOfByte2);
        byte[] arrayOfByte3 = new byte[8];
        System.arraycopy(localMessageDigest.digest(), 0, arrayOfByte3, 0, 8);
        String str = new BigInteger(1, arrayOfByte3).toString();
        localObject = str;
        i++;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
    }
    return (String)localObject;
  }
  
  public String zzgE()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {}
    for (;;)
    {
      return str2;
      str2 = str1 + " " + str2;
    }
  }
  
  protected int[] zzgF()
  {
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    return arrayOfInt;
  }
  
  public void zzh(Context paramContext, String paramString)
  {
    zzx.zzcj("deleteFile must not be called on the main UI thread.");
    paramContext.deleteFile(paramString);
  }
  
  public int[] zzh(Activity paramActivity)
  {
    int[] arrayOfInt1 = zzg(paramActivity);
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = zzl.zzcF().zzc(paramActivity, arrayOfInt1[0]);
    arrayOfInt2[1] = zzl.zzcF().zzc(paramActivity, arrayOfInt1[1]);
    return arrayOfInt2;
  }
  
  public int[] zzi(Activity paramActivity)
  {
    Window localWindow = paramActivity.getWindow();
    int[] arrayOfInt;
    if (localWindow != null)
    {
      View localView = localWindow.findViewById(16908290);
      if (localView != null)
      {
        arrayOfInt = new int[2];
        arrayOfInt[0] = localView.getTop();
        arrayOfInt[1] = localView.getBottom();
      }
    }
    for (;;)
    {
      return arrayOfInt;
      arrayOfInt = zzgF();
    }
  }
  
  public int[] zzj(Activity paramActivity)
  {
    int[] arrayOfInt1 = zzi(paramActivity);
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = zzl.zzcF().zzc(paramActivity, arrayOfInt1[0]);
    arrayOfInt2[1] = zzl.zzcF().zzc(paramActivity, arrayOfInt1[1]);
    return arrayOfInt2;
  }
  
  public Bitmap zzk(View paramView)
  {
    paramView.setDrawingCacheEnabled(true);
    Bitmap localBitmap = Bitmap.createBitmap(paramView.getDrawingCache());
    paramView.setDrawingCacheEnabled(false);
    return localBitmap;
  }
  
  public JSONObject zzz(Map<String, ?> paramMap)
    throws JSONException
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        zza(localJSONObject, str, paramMap.get(str));
      }
      return localJSONObject;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new JSONException("Could not convert map to JSON: " + localClassCastException.getMessage());
    }
  }
  
  private final class zza
    extends BroadcastReceiver
  {
    private zza() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.USER_PRESENT".equals(paramIntent.getAction())) {
        zzid.zza(zzid.this, true);
      }
      for (;;)
      {
        return;
        if ("android.intent.action.SCREEN_OFF".equals(paramIntent.getAction())) {
          zzid.zza(zzid.this, false);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */