package spacemadness.com.lunarconsole.console;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.unity3d.player.UnityPlayer;
import java.lang.ref.WeakReference;
import java.util.List;
import spacemadness.com.lunarconsole.R.anim;
import spacemadness.com.lunarconsole.core.Destroyable;
import spacemadness.com.lunarconsole.debug.Log;
import spacemadness.com.lunarconsole.debug.Tag;
import spacemadness.com.lunarconsole.debug.Tags;
import spacemadness.com.lunarconsole.ui.GestureRecognizer;
import spacemadness.com.lunarconsole.ui.GestureRecognizer.OnGestureListener;
import spacemadness.com.lunarconsole.ui.SwipeGestureRecognizer;
import spacemadness.com.lunarconsole.ui.SwipeGestureRecognizer.SwipeDirection;
import spacemadness.com.lunarconsole.utils.ThreadUtils;
import spacemadness.com.lunarconsole.utils.UIUtils;

public class ConsolePlugin
  implements Destroyable, ConsoleView.Listener, WarningView.Listener
{
  private static final ConsoleEntryDispatcher entryDispatcher = new ConsoleEntryDispatcher(new ConsoleEntryDispatcher.OnDispatchListener()
  {
    public void onDispatchEntries(List<ConsoleEntry> paramAnonymousList)
    {
      if (ConsolePlugin.instance != null) {
        ConsolePlugin.instance.logEntries(paramAnonymousList);
      }
      for (;;)
      {
        return;
        Log.e("Can't log message: plugin instance is not initialized", new Object[0]);
      }
    }
  });
  private static ConsolePlugin instance;
  private final WeakReference<Activity> activityRef;
  private final Console console;
  private ConsoleView consoleView;
  private final GestureRecognizer gestureDetector;
  private final ConsolePluginImp pluginImp;
  private final String version;
  private WarningView warningView;
  
  private ConsolePlugin(Activity paramActivity, String paramString, int paramInt1, int paramInt2, ConsolePluginImp paramConsolePluginImp)
  {
    if (paramActivity == null) {
      throw new NullPointerException("Context is null");
    }
    if (paramString == null) {
      throw new NullPointerException("Version is null");
    }
    this.version = paramString;
    this.pluginImp = paramConsolePluginImp;
    Console.Options localOptions = new Console.Options(paramInt1);
    localOptions.setTrimCount(paramInt2);
    this.console = new Console(localOptions);
    this.activityRef = new WeakReference(paramActivity);
    float f = UIUtils.dpToPx(paramActivity, 100.0F);
    this.gestureDetector = new SwipeGestureRecognizer(SwipeGestureRecognizer.SwipeDirection.Down, f);
    this.gestureDetector.setListener(new GestureRecognizer.OnGestureListener()
    {
      public void onGesture(GestureRecognizer paramAnonymousGestureRecognizer)
      {
        ConsolePlugin.this.showConsole();
      }
    });
  }
  
  public static void clear()
  {
    if (ThreadUtils.isRunningOnMainThread()) {
      clear0();
    }
    for (;;)
    {
      return;
      ThreadUtils.runOnUIThread(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void clear0()
  {
    if (instance != null) {
      instance.clearConsole();
    }
  }
  
  private void clearConsole()
  {
    try
    {
      this.console.clear();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(localException, "Can't clear console", new Object[0]);
      }
    }
  }
  
  private Activity getActivity()
  {
    return (Activity)this.activityRef.get();
  }
  
  public static String getVersion()
  {
    if (instance != null) {}
    for (String str = instance.version;; str = "?.?.?") {
      return str;
    }
  }
  
  public static void hide()
  {
    if (ThreadUtils.isRunningOnMainThread()) {
      hide0();
    }
    for (;;)
    {
      return;
      ThreadUtils.runOnUIThread(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void hide0()
  {
    if (instance != null) {
      instance.hideConsole();
    }
    for (;;)
    {
      return;
      Log.w("Can't hide console: instance is not initialized", new Object[0]);
    }
  }
  
  private boolean hideConsole()
  {
    boolean bool = false;
    try
    {
      if (this.consoleView == null) {
        return bool;
      }
      Log.d(Tags.CONSOLE, "Hide console", new Object[0]);
      Activity localActivity = getActivity();
      if (localActivity != null)
      {
        Animation localAnimation = AnimationUtils.loadAnimation(localActivity, R.anim.lunar_console_slide_out_top);
        localAnimation.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
            ConsolePlugin.this.removeConsoleView();
          }
          
          public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
          
          public void onAnimationStart(Animation paramAnonymousAnimation) {}
        });
        this.consoleView.startAnimation(localAnimation);
      }
      else
      {
        removeConsoleView();
      }
    }
    catch (Exception localException)
    {
      Log.e(localException, "Can't hide console", new Object[0]);
      return bool;
    }
    bool = true;
    return bool;
  }
  
  private void hideWarning()
  {
    ViewParent localViewParent;
    if (this.warningView != null)
    {
      Log.d(Tags.WARNING_VIEW, "Hide warning", new Object[0]);
      localViewParent = this.warningView.getParent();
      if (!(localViewParent instanceof ViewGroup)) {
        break label59;
      }
      ((ViewGroup)localViewParent).removeView(this.warningView);
    }
    for (;;)
    {
      this.warningView.destroy();
      this.warningView = null;
      return;
      label59:
      Log.e("Can't hide warning view: unexpected parent view " + localViewParent, new Object[0]);
    }
  }
  
  public static void init(Activity paramActivity, String paramString, int paramInt1, int paramInt2)
  {
    init(paramActivity, paramString, paramInt1, paramInt2, new DefaultPluginImp(paramActivity));
  }
  
  private static void init(Activity paramActivity, final String paramString, final int paramInt1, final int paramInt2, final ConsolePluginImp paramConsolePluginImp)
  {
    if (ThreadUtils.isRunningOnMainThread()) {
      init0(paramActivity, paramString, paramInt1, paramInt2, paramConsolePluginImp);
    }
    for (;;)
    {
      return;
      Log.d(Tags.PLUGIN, "Tried to initialize plugin on the secondary thread. Scheduling on UI-thread...", new Object[0]);
      ThreadUtils.runOnUIThread(new Runnable()
      {
        public void run()
        {
          ConsolePlugin.init0(ConsolePlugin.this, paramString, paramInt1, paramInt2, paramConsolePluginImp);
        }
      });
    }
  }
  
  public static void init(String paramString, int paramInt1, int paramInt2)
  {
    Activity localActivity = UnityPlayer.currentActivity;
    init(localActivity, paramString, paramInt1, paramInt2, new UnityPluginImp(localActivity));
  }
  
  private static void init0(Activity paramActivity, String paramString, int paramInt1, int paramInt2, ConsolePluginImp paramConsolePluginImp)
  {
    try
    {
      if (instance == null)
      {
        Tag localTag = Tags.PLUGIN;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = Integer.valueOf(paramInt1);
        Log.d(localTag, "Initializing plugin instance (%s): %d", arrayOfObject);
        instance = new ConsolePlugin(paramActivity, paramString, paramInt1, paramInt2, paramConsolePluginImp);
        instance.enableGestureRecognition();
      }
      else
      {
        Log.w("Plugin instance already initialized", new Object[0]);
      }
    }
    catch (Exception localException)
    {
      Log.e(localException, "Can't initialize plugin instance", new Object[0]);
    }
  }
  
  private void logEntries(List<ConsoleEntry> paramList)
  {
    for (int i = 0; i < paramList.size(); i++)
    {
      ConsoleEntry localConsoleEntry = (ConsoleEntry)paramList.get(i);
      this.console.logMessage(localConsoleEntry);
      if ((ConsoleLogType.isErrorType(localConsoleEntry.type)) && (!isConsoleShown())) {
        showWarning(localConsoleEntry.message);
      }
    }
  }
  
  public static void logMessage(String paramString1, String paramString2, int paramInt)
  {
    entryDispatcher.add(new ConsoleEntry((byte)paramInt, paramString1, paramString2));
  }
  
  private void removeConsoleView()
  {
    ViewParent localViewParent;
    if (this.consoleView != null)
    {
      localViewParent = this.consoleView.getParent();
      if (!(localViewParent instanceof ViewGroup)) {
        break label50;
      }
      ((ViewGroup)localViewParent).removeView(this.consoleView);
    }
    for (;;)
    {
      this.consoleView.destroy();
      this.consoleView = null;
      enableGestureRecognition();
      return;
      label50:
      Log.e("Can't remove console view: unexpected parent " + localViewParent, new Object[0]);
    }
  }
  
  public static void show()
  {
    if (ThreadUtils.isRunningOnMainThread()) {
      show0();
    }
    for (;;)
    {
      return;
      ThreadUtils.runOnUIThread(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void show0()
  {
    if (instance != null) {
      instance.showConsole();
    }
    for (;;)
    {
      return;
      Log.w("Can't show console: instance is not initialized", new Object[0]);
    }
  }
  
  private boolean showConsole()
  {
    boolean bool = false;
    try
    {
      if (this.consoleView == null)
      {
        Log.d(Tags.CONSOLE, "Show console", new Object[0]);
        Activity localActivity = getActivity();
        if (localActivity == null)
        {
          Log.e("Can't show console: activity reference is lost", new Object[0]);
        }
        else
        {
          FrameLayout localFrameLayout = UIUtils.getRootLayout(localActivity);
          this.consoleView = new ConsoleView(localActivity, this.console);
          this.consoleView.setListener(this);
          this.consoleView.requestFocus();
          FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
          localFrameLayout.addView(this.consoleView, localLayoutParams);
          Animation localAnimation = AnimationUtils.loadAnimation(localActivity, R.anim.lunar_console_slide_in_top);
          this.consoleView.startAnimation(localAnimation);
          disableGestureRecognition();
          bool = true;
        }
      }
      else
      {
        Log.w("Console is show already", new Object[0]);
      }
    }
    catch (Exception localException)
    {
      Log.e(localException, "Can't show console", new Object[0]);
    }
    return bool;
  }
  
  private void showWarning(String paramString)
  {
    try
    {
      if (this.warningView == null)
      {
        Log.d(Tags.WARNING_VIEW, "Show warning", new Object[0]);
        Activity localActivity = getActivity();
        if (localActivity == null)
        {
          Log.e("Can't show warning: activity reference is lost", new Object[0]);
        }
        else
        {
          FrameLayout localFrameLayout = UIUtils.getRootLayout(localActivity);
          this.warningView = new WarningView(localActivity);
          this.warningView.setListener(this);
          localFrameLayout.addView(this.warningView);
        }
      }
      else
      {
        this.warningView.setMessage(paramString);
      }
    }
    catch (Exception localException)
    {
      Log.e(localException, "Can't show warning", new Object[0]);
    }
  }
  
  public static void shutdown()
  {
    if (ThreadUtils.isRunningOnMainThread()) {
      shutdown0();
    }
    for (;;)
    {
      return;
      Log.d(Tags.PLUGIN, "Tried to shutdown plugin on the secondary thread. Scheduling on UI-thread...", new Object[0]);
      ThreadUtils.runOnUIThread(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void shutdown0()
  {
    try
    {
      if (instance != null)
      {
        instance.destroy();
        instance = null;
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(localException, "Error while shutting down the plugin", new Object[0]);
      }
    }
  }
  
  public void destroy()
  {
    disableGestureRecognition();
    this.console.destroy();
    entryDispatcher.cancelAll();
    Log.d(Tags.PLUGIN, "Plugin destroyed", new Object[0]);
  }
  
  public void disableGestureRecognition()
  {
    Log.d(Tags.GESTURES, "Disable gesture recognition", new Object[0]);
    View localView = this.pluginImp.getTouchRecepientView();
    if (localView != null) {
      localView.setOnTouchListener(null);
    }
    for (;;)
    {
      return;
      Log.w("Can't disable gesture recognition: touch view is null", new Object[0]);
    }
  }
  
  public void enableGestureRecognition()
  {
    Log.d(Tags.GESTURES, "Enable gesture recognition", new Object[0]);
    View localView = this.pluginImp.getTouchRecepientView();
    if (localView == null) {
      Log.w("Can't enable gesture recognition: touch view is null", new Object[0]);
    }
    for (;;)
    {
      return;
      localView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          ConsolePlugin.this.gestureDetector.onTouchEvent(paramAnonymousMotionEvent);
          return false;
        }
      });
    }
  }
  
  public boolean isConsoleShown()
  {
    if (this.consoleView != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onClose(ConsoleView paramConsoleView)
  {
    hideConsole();
  }
  
  public void onDetailsClick(WarningView paramWarningView)
  {
    hideWarning();
    showConsole();
  }
  
  public void onDismissClick(WarningView paramWarningView)
  {
    hideWarning();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/ConsolePlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */