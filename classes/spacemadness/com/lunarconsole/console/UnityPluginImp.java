package spacemadness.com.lunarconsole.console;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.unity3d.player.UnityPlayer;
import java.lang.ref.WeakReference;
import spacemadness.com.lunarconsole.core.LunarConsoleException;
import spacemadness.com.lunarconsole.utils.UIUtils;

public class UnityPluginImp
  implements ConsolePluginImp
{
  private final WeakReference<UnityPlayer> playerRef;
  
  public UnityPluginImp(Activity paramActivity)
  {
    UnityPlayer localUnityPlayer = resolveUnityPlayerInstance(paramActivity);
    if (localUnityPlayer == null) {
      throw new LunarConsoleException("Can't initialize plugin: UnityPlayer instance not resolved");
    }
    this.playerRef = new WeakReference(localUnityPlayer);
  }
  
  private UnityPlayer getPlayer()
  {
    return (UnityPlayer)this.playerRef.get();
  }
  
  private static UnityPlayer resolveUnityPlayerInstance(Activity paramActivity)
  {
    return resolveUnityPlayerInstance(UIUtils.getRootViewGroup(paramActivity));
  }
  
  private static UnityPlayer resolveUnityPlayerInstance(ViewGroup paramViewGroup)
  {
    Object localObject;
    if ((paramViewGroup instanceof UnityPlayer)) {
      localObject = (UnityPlayer)paramViewGroup;
    }
    for (;;)
    {
      return (UnityPlayer)localObject;
      for (int i = 0;; i++)
      {
        if (i >= paramViewGroup.getChildCount()) {
          break label78;
        }
        View localView = paramViewGroup.getChildAt(i);
        if ((localView instanceof UnityPlayer))
        {
          localObject = (UnityPlayer)localView;
          break;
        }
        if ((localView instanceof ViewGroup))
        {
          UnityPlayer localUnityPlayer = resolveUnityPlayerInstance((ViewGroup)localView);
          if (localUnityPlayer != null)
          {
            localObject = localUnityPlayer;
            break;
          }
        }
      }
      label78:
      localObject = null;
    }
  }
  
  public View getTouchRecepientView()
  {
    return getPlayer();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/UnityPluginImp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */