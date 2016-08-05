package com.unity3d.player;

import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class l
  implements h
{
  private Choreographer a = null;
  private long b = 0L;
  private Choreographer.FrameCallback c;
  private Lock d = new ReentrantLock();
  
  public final void a()
  {
    this.d.lock();
    if (this.a != null) {
      this.a.removeFrameCallback(this.c);
    }
    this.a = null;
    this.d.unlock();
  }
  
  public final void a(final UnityPlayer paramUnityPlayer)
  {
    this.d.lock();
    if (this.a == null)
    {
      this.a = Choreographer.getInstance();
      if (this.a != null)
      {
        m.Log(4, "Choreographer available: Enabling VSYNC timing");
        this.c = new Choreographer.FrameCallback()
        {
          public final void doFrame(long paramAnonymousLong)
          {
            
            if (v.c()) {
              paramUnityPlayer.nativeAddVSyncTime(paramAnonymousLong);
            }
            UnityPlayer.unlockNativeAccess();
            l.a(l.this).lock();
            if (l.b(l.this) != null) {
              l.b(l.this).postFrameCallback(l.c(l.this));
            }
            l.a(l.this).unlock();
          }
        };
        this.a.postFrameCallback(this.c);
      }
    }
    this.d.unlock();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */