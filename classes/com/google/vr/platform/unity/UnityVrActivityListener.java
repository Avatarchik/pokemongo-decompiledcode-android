package com.google.vr.platform.unity;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.google.unity.GoogleUnityActivity;
import com.google.unity.GoogleUnityActivity.AndroidInputListener;
import com.google.vr.cardboard.NFCUtils;
import com.google.vr.cardboard.UiLayer;
import com.google.vr.cardboard.UiUtils;
import com.unity3d.player.UnityPlayer;

public class UnityVrActivityListener
  implements GoogleUnityActivity.AndroidInputListener
{
  private static final long NO_DOWNTIME = -1L;
  private static final String TAG = UnityVrActivityListener.class.getSimpleName();
  private static final long TAP_TIME_MS = 50L;
  private boolean alignmentMarkerEnabled = true;
  private final NFCUtils nfcUtils = new NFCUtils();
  private boolean settingsButtonEnabled = true;
  private boolean showVrBackButtonOnlyInVR = true;
  private boolean tapInProgress = false;
  private boolean tapIsTrigger = false;
  private int touchX = 0;
  private int touchY = 0;
  private UiLayer uiLayer;
  private final GoogleUnityActivity unityActivity = (GoogleUnityActivity)UnityPlayer.currentActivity;
  private boolean vrBackButtonEnabled = true;
  private Runnable vrBackButtonListener = new Runnable()
  {
    public void run() {}
  };
  private boolean vrModeEnabled = true;
  
  static
  {
    System.loadLibrary("vrunity");
  }
  
  public UnityVrActivityListener()
  {
    this.unityActivity.attachInputListener(this);
    this.nfcUtils.onCreate(this.unityActivity);
    this.uiLayer = new UiLayer(this.unityActivity);
    this.uiLayer.attachUiLayer(null);
    this.uiLayer.setEnabled(true);
    setVRModeEnabled(this.vrModeEnabled);
    onPause(false);
  }
  
  public static float[] getDisplayMetrics()
  {
    Display localDisplay = UnityPlayer.currentActivity.getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      localDisplay.getRealMetrics(localDisplayMetrics);
      float[] arrayOfFloat = new float[4];
      arrayOfFloat[0] = localDisplayMetrics.widthPixels;
      arrayOfFloat[1] = localDisplayMetrics.heightPixels;
      arrayOfFloat[2] = localDisplayMetrics.xdpi;
      arrayOfFloat[3] = localDisplayMetrics.ydpi;
      return arrayOfFloat;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;)
      {
        localDisplay.getMetrics(localDisplayMetrics);
      }
    }
  }
  
  private long injectMotionEventInternal(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong)
  {
    long l = SystemClock.uptimeMillis();
    if (paramLong == -1L) {
      paramLong = l;
    }
    float f1 = paramInt2;
    float f2 = paramInt3;
    MotionEvent localMotionEvent = MotionEvent.obtain(paramLong, l, paramInt1, f1, f2, 0);
    localMotionEvent.setSource(paramInt4);
    this.unityActivity.injectUnityEvent(localMotionEvent);
    localMotionEvent.recycle();
    return l;
  }
  
  private static native void setApplicationState(ClassLoader paramClassLoader, Context paramContext);
  
  public static void setUnityApplicationState()
  {
    Activity localActivity = UnityPlayer.currentActivity;
    setApplicationState(localActivity.getClass().getClassLoader(), localActivity.getApplicationContext());
  }
  
  private static native void vrBackButtonPressed();
  
  public void injectKeyDown(int paramInt)
  {
    this.unityActivity.injectUnityEvent(new KeyEvent(0, paramInt));
  }
  
  public void injectKeyPress(final int paramInt)
  {
    injectKeyDown(paramInt);
    this.unityActivity.getUnityPlayer().postDelayed(new Runnable()
    {
      public void run()
      {
        UnityVrActivityListener.this.injectKeyUp(paramInt);
      }
    }, 50L);
  }
  
  public void injectKeyUp(int paramInt)
  {
    this.unityActivity.injectUnityEvent(new KeyEvent(1, paramInt));
  }
  
  public void injectMouseMove(int paramInt1, int paramInt2)
  {
    injectMotionEventInternal(7, paramInt1, paramInt2, 8194, -1L);
  }
  
  public void injectSingleTap()
  {
    if (this.tapInProgress) {}
    for (;;)
    {
      return;
      this.tapInProgress = true;
      final int i = this.touchX;
      final int j = this.touchY;
      final long l = injectTouchDown(i, j);
      this.unityActivity.getUnityPlayer().postDelayed(new Runnable()
      {
        public void run()
        {
          UnityVrActivityListener.this.injectTouchUp(i, j, l);
          UnityVrActivityListener.access$102(UnityVrActivityListener.this, false);
          if ((UnityVrActivityListener.this.tapIsTrigger) && ((i != UnityVrActivityListener.this.touchX) || (j != UnityVrActivityListener.this.touchY))) {
            UnityVrActivityListener.this.injectMouseMove(UnityVrActivityListener.this.touchX, UnityVrActivityListener.this.touchY);
          }
        }
      }, 50L);
    }
  }
  
  public long injectTouchDown(int paramInt1, int paramInt2)
  {
    return injectMotionEventInternal(0, paramInt1, paramInt2, 4098, -1L);
  }
  
  public void injectTouchUp(int paramInt1, int paramInt2, long paramLong)
  {
    injectMotionEventInternal(1, paramInt1, paramInt2, 4098, paramLong);
  }
  
  public void launchConfigureActivity()
  {
    UiUtils.launchOrInstallCardboard(this.unityActivity, false);
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (((paramInt == 24) || (paramInt == 25)) && (this.vrModeEnabled)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (((paramInt == 24) || (paramInt == 25)) && (this.vrModeEnabled)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onPause(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.nfcUtils.onPause(this.unityActivity);
    }
    for (;;)
    {
      return;
      this.nfcUtils.onResume(this.unityActivity);
    }
  }
  
  public void onSystemUiVisibilityChange(int paramInt)
  {
    if ((this.tapIsTrigger) && (this.vrModeEnabled)) {
      injectSingleTap();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.tapIsTrigger) && (this.vrModeEnabled)) {
      if (paramMotionEvent.getAction() == 0) {
        injectSingleTap();
      }
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setAlignmentMarkerEnabled(boolean paramBoolean)
  {
    this.alignmentMarkerEnabled = paramBoolean;
    UiLayer localUiLayer = this.uiLayer;
    if ((this.alignmentMarkerEnabled) && (this.vrModeEnabled)) {}
    for (boolean bool = true;; bool = false)
    {
      localUiLayer.setAlignmentMarkerEnabled(bool);
      return;
    }
  }
  
  public void setSettingsButtonEnabled(boolean paramBoolean)
  {
    this.settingsButtonEnabled = paramBoolean;
    UiLayer localUiLayer = this.uiLayer;
    if ((this.settingsButtonEnabled) && (this.vrModeEnabled)) {}
    for (boolean bool = true;; bool = false)
    {
      localUiLayer.setSettingsButtonEnabled(bool);
      return;
    }
  }
  
  public void setShowVrBackButtonOnlyInVR(boolean paramBoolean)
  {
    this.showVrBackButtonOnlyInVR = paramBoolean;
    setVRBackButtonEnabled(this.vrBackButtonEnabled);
  }
  
  public void setTapIsTrigger(boolean paramBoolean)
  {
    this.tapIsTrigger = paramBoolean;
  }
  
  public void setTouchCoordinates(int paramInt1, int paramInt2)
  {
    this.touchX = paramInt1;
    this.touchY = paramInt2;
    if ((this.tapIsTrigger) && (!this.tapInProgress)) {
      injectMouseMove(paramInt1, paramInt2);
    }
  }
  
  public void setVRBackButtonEnabled(boolean paramBoolean)
  {
    this.vrBackButtonEnabled = paramBoolean;
    Runnable localRunnable = null;
    if ((this.vrBackButtonEnabled) && ((this.vrModeEnabled) || (!this.showVrBackButtonOnlyInVR))) {
      localRunnable = this.vrBackButtonListener;
    }
    this.uiLayer.setBackButtonListener(localRunnable);
  }
  
  public void setVRModeEnabled(boolean paramBoolean)
  {
    this.vrModeEnabled = paramBoolean;
    setSettingsButtonEnabled(this.settingsButtonEnabled);
    setAlignmentMarkerEnabled(this.alignmentMarkerEnabled);
    setVRBackButtonEnabled(this.vrBackButtonEnabled);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/vr/platform/unity/UnityVrActivityListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */