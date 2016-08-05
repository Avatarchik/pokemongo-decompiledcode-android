package com.unity3d.player;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManager.DisplayListener;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public final class k
  implements g
{
  private Object a = new Object[0];
  private Presentation b;
  private DisplayManager.DisplayListener c;
  
  public final void a(Context paramContext)
  {
    if (this.c == null) {}
    for (;;)
    {
      return;
      DisplayManager localDisplayManager = (DisplayManager)paramContext.getSystemService("display");
      if (localDisplayManager != null) {
        localDisplayManager.unregisterDisplayListener(this.c);
      }
    }
  }
  
  public final void a(final UnityPlayer paramUnityPlayer, Context paramContext)
  {
    DisplayManager localDisplayManager = (DisplayManager)paramContext.getSystemService("display");
    if (localDisplayManager == null) {}
    for (;;)
    {
      return;
      localDisplayManager.registerDisplayListener(new DisplayManager.DisplayListener()
      {
        public final void onDisplayAdded(int paramAnonymousInt)
        {
          paramUnityPlayer.displayChanged(-1, null);
        }
        
        public final void onDisplayChanged(int paramAnonymousInt)
        {
          paramUnityPlayer.displayChanged(-1, null);
        }
        
        public final void onDisplayRemoved(int paramAnonymousInt)
        {
          paramUnityPlayer.displayChanged(-1, null);
        }
      }, null);
    }
  }
  
  public final boolean a(final UnityPlayer paramUnityPlayer, final Context paramContext, int paramInt)
  {
    boolean bool;
    synchronized (this.a)
    {
      if ((this.b != null) && (this.b.isShowing()))
      {
        Display localDisplay2 = this.b.getDisplay();
        if ((localDisplay2 != null) && (localDisplay2.getDisplayId() == paramInt))
        {
          bool = true;
          break label138;
        }
      }
      DisplayManager localDisplayManager = (DisplayManager)paramContext.getSystemService("display");
      if (localDisplayManager == null)
      {
        bool = false;
      }
      else
      {
        final Display localDisplay1 = localDisplayManager.getDisplay(paramInt);
        if (localDisplay1 == null)
        {
          bool = false;
        }
        else
        {
          paramUnityPlayer.b(new Runnable()
          {
            public final void run()
            {
              synchronized (k.a(k.this))
              {
                if (k.b(k.this) != null) {
                  k.b(k.this).dismiss();
                }
                k.a(k.this, new Presentation(paramContext, localDisplay1)
                {
                  protected final void onCreate(Bundle paramAnonymous2Bundle)
                  {
                    SurfaceView localSurfaceView = new SurfaceView(k.2.this.a);
                    localSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback()
                    {
                      public final void surfaceChanged(SurfaceHolder paramAnonymous3SurfaceHolder, int paramAnonymous3Int1, int paramAnonymous3Int2, int paramAnonymous3Int3)
                      {
                        k.2.this.c.displayChanged(1, paramAnonymous3SurfaceHolder.getSurface());
                      }
                      
                      public final void surfaceCreated(SurfaceHolder paramAnonymous3SurfaceHolder)
                      {
                        k.2.this.c.displayChanged(1, paramAnonymous3SurfaceHolder.getSurface());
                      }
                      
                      public final void surfaceDestroyed(SurfaceHolder paramAnonymous3SurfaceHolder)
                      {
                        k.2.this.c.displayChanged(1, null);
                      }
                    });
                    setContentView(localSurfaceView);
                  }
                  
                  public final void onDisplayRemoved()
                  {
                    dismiss();
                    synchronized (k.a(k.this))
                    {
                      k.a(k.this, null);
                      return;
                    }
                  }
                });
                k.b(k.this).show();
                return;
              }
            }
          });
          bool = true;
        }
      }
    }
    label138:
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */