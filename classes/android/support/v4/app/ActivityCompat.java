package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import java.util.List;
import java.util.Map;

public class ActivityCompat
  extends ContextCompat
{
  private static ActivityCompat21.SharedElementCallback21 createCallback(SharedElementCallback paramSharedElementCallback)
  {
    SharedElementCallback21Impl localSharedElementCallback21Impl = null;
    if (paramSharedElementCallback != null) {
      localSharedElementCallback21Impl = new SharedElementCallback21Impl(paramSharedElementCallback);
    }
    return localSharedElementCallback21Impl;
  }
  
  public static void finishAffinity(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      ActivityCompatJB.finishAffinity(paramActivity);
    }
    for (;;)
    {
      return;
      paramActivity.finish();
    }
  }
  
  public static void finishAfterTransition(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      ActivityCompat21.finishAfterTransition(paramActivity);
    }
    for (;;)
    {
      return;
      paramActivity.finish();
    }
  }
  
  public static boolean invalidateOptionsMenu(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      ActivityCompatHoneycomb.invalidateOptionsMenu(paramActivity);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static void postponeEnterTransition(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      ActivityCompat21.postponeEnterTransition(paramActivity);
    }
  }
  
  public static void requestPermissions(@NonNull final Activity paramActivity, @NonNull String[] paramArrayOfString, final int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      ActivityCompatApi23.requestPermissions(paramActivity, paramArrayOfString, paramInt);
    }
    for (;;)
    {
      return;
      if ((paramActivity instanceof OnRequestPermissionsResultCallback)) {
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            int[] arrayOfInt = new int[ActivityCompat.this.length];
            PackageManager localPackageManager = paramActivity.getPackageManager();
            String str = paramActivity.getPackageName();
            int i = ActivityCompat.this.length;
            for (int j = 0; j < i; j++) {
              arrayOfInt[j] = localPackageManager.checkPermission(ActivityCompat.this[j], str);
            }
            ((ActivityCompat.OnRequestPermissionsResultCallback)paramActivity).onRequestPermissionsResult(paramInt, ActivityCompat.this, arrayOfInt);
          }
        });
      }
    }
  }
  
  public static void setEnterSharedElementCallback(Activity paramActivity, SharedElementCallback paramSharedElementCallback)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      ActivityCompat21.setEnterSharedElementCallback(paramActivity, createCallback(paramSharedElementCallback));
    }
  }
  
  public static void setExitSharedElementCallback(Activity paramActivity, SharedElementCallback paramSharedElementCallback)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      ActivityCompat21.setExitSharedElementCallback(paramActivity, createCallback(paramSharedElementCallback));
    }
  }
  
  public static boolean shouldShowRequestPermissionRationale(@NonNull Activity paramActivity, @NonNull String paramString)
  {
    if (Build.VERSION.SDK_INT >= 23) {}
    for (boolean bool = ActivityCompatApi23.shouldShowRequestPermissionRationale(paramActivity, paramString);; bool = false) {
      return bool;
    }
  }
  
  public static void startActivity(Activity paramActivity, Intent paramIntent, @Nullable Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      ActivityCompatJB.startActivity(paramActivity, paramIntent, paramBundle);
    }
    for (;;)
    {
      return;
      paramActivity.startActivity(paramIntent);
    }
  }
  
  public static void startActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt, @Nullable Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      ActivityCompatJB.startActivityForResult(paramActivity, paramIntent, paramInt, paramBundle);
    }
    for (;;)
    {
      return;
      paramActivity.startActivityForResult(paramIntent, paramInt);
    }
  }
  
  public static void startPostponedEnterTransition(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      ActivityCompat21.startPostponedEnterTransition(paramActivity);
    }
  }
  
  public Uri getReferrer(Activity paramActivity)
  {
    Uri localUri;
    if (Build.VERSION.SDK_INT >= 22) {
      localUri = ActivityCompat22.getReferrer(paramActivity);
    }
    for (;;)
    {
      return localUri;
      Intent localIntent = paramActivity.getIntent();
      localUri = (Uri)localIntent.getParcelableExtra("android.intent.extra.REFERRER");
      if (localUri == null)
      {
        String str = localIntent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (str != null) {
          localUri = Uri.parse(str);
        } else {
          localUri = null;
        }
      }
    }
  }
  
  private static class SharedElementCallback21Impl
    extends ActivityCompat21.SharedElementCallback21
  {
    private SharedElementCallback mCallback;
    
    public SharedElementCallback21Impl(SharedElementCallback paramSharedElementCallback)
    {
      this.mCallback = paramSharedElementCallback;
    }
    
    public Parcelable onCaptureSharedElementSnapshot(View paramView, Matrix paramMatrix, RectF paramRectF)
    {
      return this.mCallback.onCaptureSharedElementSnapshot(paramView, paramMatrix, paramRectF);
    }
    
    public View onCreateSnapshotView(Context paramContext, Parcelable paramParcelable)
    {
      return this.mCallback.onCreateSnapshotView(paramContext, paramParcelable);
    }
    
    public void onMapSharedElements(List<String> paramList, Map<String, View> paramMap)
    {
      this.mCallback.onMapSharedElements(paramList, paramMap);
    }
    
    public void onRejectSharedElements(List<View> paramList)
    {
      this.mCallback.onRejectSharedElements(paramList);
    }
    
    public void onSharedElementEnd(List<String> paramList, List<View> paramList1, List<View> paramList2)
    {
      this.mCallback.onSharedElementEnd(paramList, paramList1, paramList2);
    }
    
    public void onSharedElementStart(List<String> paramList, List<View> paramList1, List<View> paramList2)
    {
      this.mCallback.onSharedElementStart(paramList, paramList1, paramList2);
    }
  }
  
  public static abstract interface OnRequestPermissionsResultCallback
  {
    public abstract void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/app/ActivityCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */