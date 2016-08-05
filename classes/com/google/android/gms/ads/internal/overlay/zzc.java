package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzmn;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzgr
public class zzc
  extends zzi
  implements AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener
{
  private static final Map<Integer, String> zzAO = new HashMap();
  private final zzp zzAP;
  private int zzAQ = 0;
  private int zzAR = 0;
  private MediaPlayer zzAS;
  private Uri zzAT;
  private int zzAU;
  private int zzAV;
  private int zzAW;
  private int zzAX;
  private int zzAY;
  private float zzAZ = 1.0F;
  private boolean zzBa;
  private boolean zzBb;
  private int zzBc;
  private zzh zzBd;
  
  static
  {
    zzAO.put(Integer.valueOf(64532), "MEDIA_ERROR_IO");
    zzAO.put(Integer.valueOf(64529), "MEDIA_ERROR_MALFORMED");
    zzAO.put(Integer.valueOf(64526), "MEDIA_ERROR_UNSUPPORTED");
    zzAO.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
    zzAO.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
    zzAO.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
    zzAO.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
    zzAO.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
    zzAO.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
    zzAO.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
    zzAO.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
    zzAO.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
    zzAO.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
    zzAO.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
    zzAO.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
    zzAO.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
  }
  
  public zzc(Context paramContext, zzp paramzzp)
  {
    super(paramContext);
    setSurfaceTextureListener(this);
    this.zzAP = paramzzp;
    this.zzAP.zza(this);
  }
  
  private void zzb(float paramFloat)
  {
    if (this.zzAS != null) {}
    try
    {
      this.zzAS.setVolume(paramFloat, paramFloat);
      for (;;)
      {
        return;
        zzb.zzaH("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
  }
  
  private void zzeA()
  {
    zzb.v("AdMediaPlayerView audio focus lost");
    this.zzBb = false;
    zzeB();
  }
  
  private void zzeB()
  {
    if ((!this.zzBa) && (this.zzBb)) {
      zzb(this.zzAZ);
    }
    for (;;)
    {
      return;
      zzb(0.0F);
    }
  }
  
  private AudioManager zzeC()
  {
    return (AudioManager)getContext().getSystemService("audio");
  }
  
  private void zzes()
  {
    zzb.v("AdMediaPlayerView init MediaPlayer");
    SurfaceTexture localSurfaceTexture = getSurfaceTexture();
    if ((this.zzAT == null) || (localSurfaceTexture == null)) {}
    for (;;)
    {
      return;
      zzt(false);
      try
      {
        this.zzAS = new MediaPlayer();
        this.zzAS.setOnBufferingUpdateListener(this);
        this.zzAS.setOnCompletionListener(this);
        this.zzAS.setOnErrorListener(this);
        this.zzAS.setOnInfoListener(this);
        this.zzAS.setOnPreparedListener(this);
        this.zzAS.setOnVideoSizeChangedListener(this);
        this.zzAW = 0;
        this.zzAS.setDataSource(getContext(), this.zzAT);
        this.zzAS.setSurface(new Surface(localSurfaceTexture));
        this.zzAS.setAudioStreamType(3);
        this.zzAS.setScreenOnWhilePlaying(true);
        this.zzAS.prepareAsync();
        zzt(1);
      }
      catch (IOException localIOException)
      {
        zzb.zzd("Failed to initialize MediaPlayer at " + this.zzAT, localIOException);
        onError(this.zzAS, 1, 0);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
    }
  }
  
  private void zzet()
  {
    if ((zzew()) && (this.zzAS.getCurrentPosition() > 0) && (this.zzAR != 3))
    {
      zzb.v("AdMediaPlayerView nudging MediaPlayer");
      zzb(0.0F);
      this.zzAS.start();
      int i = this.zzAS.getCurrentPosition();
      long l = com.google.android.gms.ads.internal.zzp.zzbz().currentTimeMillis();
      while ((zzew()) && (this.zzAS.getCurrentPosition() == i) && (com.google.android.gms.ads.internal.zzp.zzbz().currentTimeMillis() - l <= 250L)) {}
      this.zzAS.pause();
      zzeB();
    }
  }
  
  private void zzeu()
  {
    AudioManager localAudioManager = zzeC();
    if ((localAudioManager != null) && (!this.zzBb))
    {
      if (localAudioManager.requestAudioFocus(this, 3, 2) != 1) {
        break label32;
      }
      zzez();
    }
    for (;;)
    {
      return;
      label32:
      zzb.zzaH("AdMediaPlayerView audio focus request failed");
    }
  }
  
  private void zzev()
  {
    zzb.v("AdMediaPlayerView abandon audio focus");
    AudioManager localAudioManager = zzeC();
    if ((localAudioManager != null) && (this.zzBb))
    {
      if (localAudioManager.abandonAudioFocus(this) != 1) {
        break label37;
      }
      this.zzBb = false;
    }
    for (;;)
    {
      return;
      label37:
      zzb.zzaH("AdMediaPlayerView abandon audio focus failed");
    }
  }
  
  private boolean zzew()
  {
    int i = 1;
    if ((this.zzAS != null) && (this.zzAQ != -1) && (this.zzAQ != 0) && (this.zzAQ != i)) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  private void zzez()
  {
    zzb.v("AdMediaPlayerView audio focus gained");
    this.zzBb = true;
    zzeB();
  }
  
  private void zzt(int paramInt)
  {
    if (paramInt == 3) {
      this.zzAP.zzfe();
    }
    for (;;)
    {
      this.zzAQ = paramInt;
      return;
      if ((this.zzAQ == 3) && (paramInt != 3)) {
        this.zzAP.zzff();
      }
    }
  }
  
  private void zzt(boolean paramBoolean)
  {
    zzb.v("AdMediaPlayerView release");
    if (this.zzAS != null)
    {
      this.zzAS.reset();
      this.zzAS.release();
      this.zzAS = null;
      zzt(0);
      if (paramBoolean)
      {
        this.zzAR = 0;
        zzu(0);
      }
      zzev();
    }
  }
  
  private void zzu(int paramInt)
  {
    this.zzAR = paramInt;
  }
  
  public int getCurrentPosition()
  {
    if (zzew()) {}
    for (int i = this.zzAS.getCurrentPosition();; i = 0) {
      return i;
    }
  }
  
  public int getDuration()
  {
    if (zzew()) {}
    for (int i = this.zzAS.getDuration();; i = -1) {
      return i;
    }
  }
  
  public int getVideoHeight()
  {
    if (this.zzAS != null) {}
    for (int i = this.zzAS.getVideoHeight();; i = 0) {
      return i;
    }
  }
  
  public int getVideoWidth()
  {
    if (this.zzAS != null) {}
    for (int i = this.zzAS.getVideoWidth();; i = 0) {
      return i;
    }
  }
  
  public void onAudioFocusChange(int paramInt)
  {
    if (paramInt > 0) {
      zzez();
    }
    for (;;)
    {
      return;
      if (paramInt < 0) {
        zzeA();
      }
    }
  }
  
  public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    this.zzAW = paramInt;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    zzb.v("AdMediaPlayerView completion");
    zzt(5);
    zzu(5);
    zzid.zzIE.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzeT();
        }
      }
    });
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    final String str1 = (String)zzAO.get(Integer.valueOf(paramInt1));
    final String str2 = (String)zzAO.get(Integer.valueOf(paramInt2));
    zzb.zzaH("AdMediaPlayerView MediaPlayer error: " + str1 + ":" + str2);
    zzt(-1);
    zzu(-1);
    zzid.zzIE.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzh(str1, str2);
        }
      }
    });
    return true;
  }
  
  public boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    String str1 = (String)zzAO.get(Integer.valueOf(paramInt1));
    String str2 = (String)zzAO.get(Integer.valueOf(paramInt2));
    zzb.v("AdMediaPlayerView MediaPlayer info: " + str1 + ":" + str2);
    return true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getDefaultSize(this.zzAU, paramInt1);
    int j = getDefaultSize(this.zzAV, paramInt2);
    int k;
    int m;
    int n;
    if ((this.zzAU > 0) && (this.zzAV > 0))
    {
      k = View.MeasureSpec.getMode(paramInt1);
      m = View.MeasureSpec.getSize(paramInt1);
      n = View.MeasureSpec.getMode(paramInt2);
      j = View.MeasureSpec.getSize(paramInt2);
      if ((k != 1073741824) || (n != 1073741824)) {
        break label202;
      }
      if (j * this.zzAU >= m * this.zzAV) {
        break label165;
      }
      i = j * this.zzAU / this.zzAV;
    }
    for (;;)
    {
      setMeasuredDimension(i, j);
      if (Build.VERSION.SDK_INT == 16)
      {
        if (((this.zzAX > 0) && (this.zzAX != i)) || ((this.zzAY > 0) && (this.zzAY != j))) {
          zzet();
        }
        this.zzAX = i;
        this.zzAY = j;
      }
      return;
      label165:
      if (j * this.zzAU > m * this.zzAV)
      {
        j = m * this.zzAV / this.zzAU;
        i = m;
        continue;
        label202:
        int i3;
        if (k == 1073741824)
        {
          i3 = m * this.zzAV / this.zzAU;
          if ((n == Integer.MIN_VALUE) && (i3 > j)) {
            i = m;
          }
        }
        else
        {
          if (n == 1073741824)
          {
            i = j * this.zzAU / this.zzAV;
            if ((k != Integer.MIN_VALUE) || (i <= m)) {
              continue;
            }
            i = m;
            continue;
          }
          int i1 = this.zzAU;
          int i2 = this.zzAV;
          if ((n == Integer.MIN_VALUE) && (i2 > j)) {}
          for (i = j * this.zzAU / this.zzAV;; i = i1)
          {
            if ((k != Integer.MIN_VALUE) || (i <= m)) {
              break label368;
            }
            j = m * this.zzAV / this.zzAU;
            i = m;
            break;
            j = i2;
          }
          label368:
          continue;
        }
        j = i3;
        i = m;
      }
      else
      {
        i = m;
      }
    }
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    zzb.v("AdMediaPlayerView prepared");
    zzt(2);
    this.zzAP.zzeR();
    zzid.zzIE.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzeR();
        }
      }
    });
    this.zzAU = paramMediaPlayer.getVideoWidth();
    this.zzAV = paramMediaPlayer.getVideoHeight();
    if (this.zzBc != 0) {
      seekTo(this.zzBc);
    }
    zzet();
    zzb.zzaG("AdMediaPlayerView stream dimensions: " + this.zzAU + " x " + this.zzAV);
    if (this.zzAR == 3) {
      play();
    }
    zzeu();
    zzeB();
  }
  
  public void onSurfaceTextureAvailable(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    zzb.v("AdMediaPlayerView surface created");
    zzes();
    zzid.zzIE.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null) {
          zzc.zza(zzc.this).zzeQ();
        }
      }
    });
  }
  
  public boolean onSurfaceTextureDestroyed(SurfaceTexture paramSurfaceTexture)
  {
    zzb.v("AdMediaPlayerView surface destroyed");
    if ((this.zzAS != null) && (this.zzBc == 0)) {
      this.zzBc = this.zzAS.getCurrentPosition();
    }
    zzid.zzIE.post(new Runnable()
    {
      public void run()
      {
        if (zzc.zza(zzc.this) != null)
        {
          zzc.zza(zzc.this).onPaused();
          zzc.zza(zzc.this).zzeU();
        }
      }
    });
    zzt(true);
    return true;
  }
  
  public void onSurfaceTextureSizeChanged(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    int i = 1;
    zzb.v("AdMediaPlayerView surface changed");
    int j;
    if (this.zzAR == 3)
    {
      j = i;
      if ((this.zzAU != paramInt1) || (this.zzAV != paramInt2)) {
        break label80;
      }
    }
    for (;;)
    {
      if ((this.zzAS != null) && (j != 0) && (i != 0))
      {
        if (this.zzBc != 0) {
          seekTo(this.zzBc);
        }
        play();
      }
      return;
      j = 0;
      break;
      label80:
      i = 0;
    }
  }
  
  public void onSurfaceTextureUpdated(SurfaceTexture paramSurfaceTexture)
  {
    this.zzAP.zzb(this);
  }
  
  public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    zzb.v("AdMediaPlayerView size changed: " + paramInt1 + " x " + paramInt2);
    this.zzAU = paramMediaPlayer.getVideoWidth();
    this.zzAV = paramMediaPlayer.getVideoHeight();
    if ((this.zzAU != 0) && (this.zzAV != 0)) {
      requestLayout();
    }
  }
  
  public void pause()
  {
    zzb.v("AdMediaPlayerView pause");
    if ((zzew()) && (this.zzAS.isPlaying()))
    {
      this.zzAS.pause();
      zzt(4);
      zzid.zzIE.post(new Runnable()
      {
        public void run()
        {
          if (zzc.zza(zzc.this) != null) {
            zzc.zza(zzc.this).onPaused();
          }
        }
      });
    }
    zzu(4);
  }
  
  public void play()
  {
    zzb.v("AdMediaPlayerView play");
    if (zzew())
    {
      this.zzAS.start();
      zzt(3);
      zzid.zzIE.post(new Runnable()
      {
        public void run()
        {
          if (zzc.zza(zzc.this) != null) {
            zzc.zza(zzc.this).zzeS();
          }
        }
      });
    }
    zzu(3);
  }
  
  public void seekTo(int paramInt)
  {
    zzb.v("AdMediaPlayerView seek " + paramInt);
    if (zzew()) {
      this.zzAS.seekTo(paramInt);
    }
    for (this.zzBc = 0;; this.zzBc = paramInt) {
      return;
    }
  }
  
  public void setMimeType(String paramString) {}
  
  public void setVideoPath(String paramString)
  {
    setVideoURI(Uri.parse(paramString));
  }
  
  public void setVideoURI(Uri paramUri)
  {
    this.zzAT = paramUri;
    this.zzBc = 0;
    zzes();
    requestLayout();
    invalidate();
  }
  
  public void stop()
  {
    zzb.v("AdMediaPlayerView stop");
    if (this.zzAS != null)
    {
      this.zzAS.stop();
      this.zzAS.release();
      this.zzAS = null;
      zzt(0);
      zzu(0);
      zzev();
    }
    this.zzAP.onStop();
  }
  
  public String toString()
  {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }
  
  public void zza(float paramFloat)
  {
    this.zzAZ = paramFloat;
    zzeB();
  }
  
  public void zza(zzh paramzzh)
  {
    this.zzBd = paramzzh;
  }
  
  public String zzer()
  {
    return "MediaPlayer";
  }
  
  public void zzex()
  {
    this.zzBa = true;
    zzeB();
  }
  
  public void zzey()
  {
    this.zzBa = false;
    zzeB();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */