package spacemadness.com.lunarconsole.ui;

import android.view.MotionEvent;
import spacemadness.com.lunarconsole.debug.Log;

public abstract class GestureRecognizer<T extends GestureRecognizer>
{
  private OnGestureListener<T> listener;
  
  public OnGestureListener<T> getListener()
  {
    return this.listener;
  }
  
  protected void notifyGestureRecognizer()
  {
    if (this.listener != null) {}
    try
    {
      this.listener.onGesture(this);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(localException, "Error while notifying gesture listener", new Object[0]);
      }
    }
  }
  
  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);
  
  public void setListener(OnGestureListener<T> paramOnGestureListener)
  {
    this.listener = paramOnGestureListener;
  }
  
  public static abstract interface OnGestureListener<T extends GestureRecognizer>
  {
    public abstract void onGesture(T paramT);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/ui/GestureRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */