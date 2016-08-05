package spacemadness.com.lunarconsole.console;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import spacemadness.com.lunarconsole.core.LunarConsoleException;
import spacemadness.com.lunarconsole.utils.UIUtils;

class DefaultPluginImp
  implements ConsolePluginImp
{
  private final WeakReference<View> rootViewRef;
  
  public DefaultPluginImp(Activity paramActivity)
  {
    ViewGroup localViewGroup = UIUtils.getRootViewGroup(paramActivity);
    if (localViewGroup == null) {
      throw new LunarConsoleException("Can't initialize plugin: root view not found");
    }
    this.rootViewRef = new WeakReference(localViewGroup);
  }
  
  public View getTouchRecepientView()
  {
    return (View)this.rootViewRef.get();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/DefaultPluginImp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */