package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice
  implements Runnable
{
  private static int h = 0;
  private static int i = 1;
  private static int j = 2;
  private static int k = 3;
  private volatile Thread a = null;
  private volatile boolean b = false;
  private AudioTrack c = null;
  private boolean d = false;
  private ByteBuffer e = null;
  private byte[] f = null;
  private volatile a g;
  
  private native int fmodGetInfo(int paramInt);
  
  private native int fmodProcess(ByteBuffer paramByteBuffer);
  
  private void releaseAudioTrack()
  {
    if (this.c != null)
    {
      if (this.c.getState() == 1) {
        this.c.stop();
      }
      this.c.release();
      this.c = null;
    }
    this.e = null;
    this.f = null;
    this.d = false;
  }
  
  /**
   * @deprecated
   */
  public void close()
  {
    try
    {
      stop();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  native int fmodProcessMicData(ByteBuffer paramByteBuffer, int paramInt);
  
  public boolean isRunning()
  {
    if ((this.a != null) && (this.a.isAlive())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void run()
  {
    int m = 3;
    boolean bool;
    label124:
    int n;
    if (this.b)
    {
      if ((this.d) || (m <= 0)) {
        break label322;
      }
      releaseAudioTrack();
      int i1 = fmodGetInfo(h);
      int i2 = 0xFFFFFFFC & Math.round(1.1F * AudioTrack.getMinBufferSize(i1, 3, 2));
      int i3 = fmodGetInfo(i);
      int i4 = fmodGetInfo(j);
      if (4 * (i3 * i4) > i2) {
        i2 = 4 * (i4 * i3);
      }
      this.c = new AudioTrack(3, i1, 3, 2, i2, 1);
      if (this.c.getState() == 1)
      {
        bool = true;
        this.d = bool;
        if (!this.d) {
          break label259;
        }
        this.e = ByteBuffer.allocateDirect(2 * (i3 * 2));
        this.f = new byte[this.e.capacity()];
        this.c.play();
        n = 3;
      }
    }
    for (;;)
    {
      if (this.d)
      {
        if (fmodGetInfo(k) == 1)
        {
          fmodProcess(this.e);
          this.e.get(this.f, 0, this.e.capacity());
          this.c.write(this.f, 0, this.e.capacity());
          this.e.position(0);
          m = n;
          break;
          bool = false;
          break label124;
          label259:
          Log.e("FMOD", "AudioTrack failed to initialize (status " + this.c.getState() + ")");
          releaseAudioTrack();
          n = m - 1;
          continue;
        }
        releaseAudioTrack();
        m = n;
        break;
        releaseAudioTrack();
        return;
      }
      m = n;
      break;
      label322:
      n = m;
    }
  }
  
  /**
   * @deprecated
   */
  public void start()
  {
    try
    {
      if (this.a != null) {
        stop();
      }
      this.a = new Thread(this, "FMODAudioDevice");
      this.a.setPriority(10);
      this.b = true;
      this.a.start();
      if (this.g != null) {
        this.g.b();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public int startAudioRecord(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if (this.g == null)
      {
        this.g = new a(this, paramInt1, paramInt2);
        this.g.b();
      }
      int m = this.g.a();
      return m;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void stop()
  {
    try
    {
      while (this.a != null)
      {
        this.b = false;
        try
        {
          this.a.join();
          this.a = null;
        }
        catch (InterruptedException localInterruptedException) {}
      }
      if (this.g != null) {
        this.g.c();
      }
      return;
    }
    finally {}
  }
  
  /**
   * @deprecated
   */
  public void stopAudioRecord()
  {
    try
    {
      if (this.g != null)
      {
        this.g.c();
        this.g = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/fmod/FMODAudioDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */