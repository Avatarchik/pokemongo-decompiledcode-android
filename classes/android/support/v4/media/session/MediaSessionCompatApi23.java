package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaSessionCompatApi23
{
  public static Object createCallback(Callback paramCallback)
  {
    return new CallbackProxy(paramCallback);
  }
  
  static class CallbackProxy<T extends MediaSessionCompatApi23.Callback>
    extends MediaSessionCompatApi21.CallbackProxy<T>
  {
    public CallbackProxy(T paramT)
    {
      super();
    }
    
    public void onPlayFromUri(Uri paramUri, Bundle paramBundle)
    {
      ((MediaSessionCompatApi23.Callback)this.mCallback).onPlayFromUri(paramUri, paramBundle);
    }
  }
  
  public static abstract interface Callback
    extends MediaSessionCompatApi21.Callback
  {
    public abstract void onPlayFromUri(Uri paramUri, Bundle paramBundle);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/media/session/MediaSessionCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */