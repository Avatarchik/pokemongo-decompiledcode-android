package spacemadness.com.lunarconsole.ui;

import android.view.MotionEvent;

public class SwipeGestureRecognizer
  extends GestureRecognizer<SwipeGestureRecognizer>
{
  private final SwipeDirection direction;
  private float endX;
  private float endY;
  private float startX;
  private float startY;
  private boolean swiping;
  private final float threshold;
  
  public SwipeGestureRecognizer(SwipeDirection paramSwipeDirection, float paramFloat)
  {
    this.direction = paramSwipeDirection;
    this.threshold = paramFloat;
  }
  
  private void handleSwipe(SwipeDirection paramSwipeDirection, float paramFloat1, float paramFloat2)
  {
    if (((paramSwipeDirection == SwipeDirection.Down) && (paramFloat2 >= this.threshold)) || ((paramSwipeDirection == SwipeDirection.Up) && (-paramFloat2 >= this.threshold)) || ((paramSwipeDirection == SwipeDirection.Right) && (paramFloat1 >= this.threshold)) || ((paramSwipeDirection == SwipeDirection.Left) && (-paramFloat1 >= this.threshold))) {
      notifyGestureRecognizer();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (0xFF & paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return true;
      this.swiping = true;
      this.startX = paramMotionEvent.getX(0);
      this.startY = paramMotionEvent.getY(0);
      continue;
      if (this.swiping)
      {
        handleSwipe(this.direction, this.endX - this.startX, this.endY - this.startY);
        this.swiping = false;
        continue;
        if (this.swiping)
        {
          this.endX = paramMotionEvent.getX(0);
          this.endY = paramMotionEvent.getY(0);
        }
      }
    }
  }
  
  public static enum SwipeDirection
  {
    static
    {
      Down = new SwipeDirection("Down", 1);
      Left = new SwipeDirection("Left", 2);
      Right = new SwipeDirection("Right", 3);
      SwipeDirection[] arrayOfSwipeDirection = new SwipeDirection[4];
      arrayOfSwipeDirection[0] = Up;
      arrayOfSwipeDirection[1] = Down;
      arrayOfSwipeDirection[2] = Left;
      arrayOfSwipeDirection[3] = Right;
      $VALUES = arrayOfSwipeDirection;
    }
    
    private SwipeDirection() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/ui/SwipeGestureRecognizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */