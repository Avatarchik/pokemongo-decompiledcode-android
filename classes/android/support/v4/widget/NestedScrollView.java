package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollView
  extends FrameLayout
  implements NestedScrollingParent, NestedScrollingChild
{
  private static final AccessibilityDelegate ACCESSIBILITY_DELEGATE = new AccessibilityDelegate();
  static final int ANIMATED_SCROLL_GAP = 250;
  private static final int INVALID_POINTER = -1;
  static final float MAX_SCROLL_FACTOR = 0.5F;
  private static final int[] SCROLLVIEW_STYLEABLE;
  private static final String TAG = "NestedScrollView";
  private int mActivePointerId = -1;
  private final NestedScrollingChildHelper mChildHelper;
  private View mChildToScrollTo = null;
  private EdgeEffectCompat mEdgeGlowBottom;
  private EdgeEffectCompat mEdgeGlowTop;
  private boolean mFillViewport;
  private boolean mIsBeingDragged = false;
  private boolean mIsLaidOut = false;
  private boolean mIsLayoutDirty = true;
  private int mLastMotionY;
  private long mLastScroll;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private int mNestedYOffset;
  private final NestedScrollingParentHelper mParentHelper;
  private SavedState mSavedState;
  private final int[] mScrollConsumed = new int[2];
  private final int[] mScrollOffset = new int[2];
  private ScrollerCompat mScroller;
  private boolean mSmoothScrollingEnabled = true;
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  private float mVerticalScrollFactor;
  
  static
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 16843130;
    SCROLLVIEW_STYLEABLE = arrayOfInt;
  }
  
  public NestedScrollView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NestedScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NestedScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initScrollView();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, SCROLLVIEW_STYLEABLE, paramInt, 0);
    setFillViewport(localTypedArray.getBoolean(0, false));
    localTypedArray.recycle();
    this.mParentHelper = new NestedScrollingParentHelper(this);
    this.mChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
    ViewCompat.setAccessibilityDelegate(this, ACCESSIBILITY_DELEGATE);
  }
  
  private boolean canScroll()
  {
    boolean bool = false;
    View localView = getChildAt(0);
    if (localView != null)
    {
      int i = localView.getHeight();
      if (getHeight() < i + getPaddingTop() + getPaddingBottom()) {
        bool = true;
      }
    }
    return bool;
  }
  
  private static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 >= paramInt3) || (paramInt1 < 0)) {
      paramInt1 = 0;
    }
    for (;;)
    {
      return paramInt1;
      if (paramInt2 + paramInt1 > paramInt3) {
        paramInt1 = paramInt3 - paramInt2;
      }
    }
  }
  
  private void doScrollY(int paramInt)
  {
    if (paramInt != 0)
    {
      if (!this.mSmoothScrollingEnabled) {
        break label18;
      }
      smoothScrollBy(0, paramInt);
    }
    for (;;)
    {
      return;
      label18:
      scrollBy(0, paramInt);
    }
  }
  
  private void endDrag()
  {
    this.mIsBeingDragged = false;
    recycleVelocityTracker();
    stopNestedScroll();
    if (this.mEdgeGlowTop != null)
    {
      this.mEdgeGlowTop.onRelease();
      this.mEdgeGlowBottom.onRelease();
    }
  }
  
  private void ensureGlows()
  {
    Context localContext;
    if (ViewCompat.getOverScrollMode(this) != 2) {
      if (this.mEdgeGlowTop == null)
      {
        localContext = getContext();
        this.mEdgeGlowTop = new EdgeEffectCompat(localContext);
      }
    }
    for (this.mEdgeGlowBottom = new EdgeEffectCompat(localContext);; this.mEdgeGlowBottom = null)
    {
      return;
      this.mEdgeGlowTop = null;
    }
  }
  
  private View findFocusableViewInBounds(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = getFocusables(2);
    Object localObject = null;
    int i = 0;
    int j = localArrayList.size();
    int k = 0;
    if (k < j)
    {
      View localView = (View)localArrayList.get(k);
      int m = localView.getTop();
      int n = localView.getBottom();
      int i1;
      if ((paramInt1 < n) && (m < paramInt2))
      {
        if ((paramInt1 >= m) || (n >= paramInt2)) {
          break label106;
        }
        i1 = 1;
        label87:
        if (localObject != null) {
          break label112;
        }
        localObject = localView;
        i = i1;
      }
      for (;;)
      {
        k++;
        break;
        label106:
        i1 = 0;
        break label87;
        label112:
        if (((paramBoolean) && (m < ((View)localObject).getTop())) || ((!paramBoolean) && (n > ((View)localObject).getBottom()))) {}
        for (int i2 = 1;; i2 = 0)
        {
          if (i == 0) {
            break label171;
          }
          if ((i1 == 0) || (i2 == 0)) {
            break;
          }
          localObject = localView;
          break;
        }
        label171:
        if (i1 != 0)
        {
          localObject = localView;
          i = 1;
        }
        else if (i2 != 0)
        {
          localObject = localView;
        }
      }
    }
    return (View)localObject;
  }
  
  private void flingWithNestedDispatch(int paramInt)
  {
    int i = getScrollY();
    if (((i > 0) || (paramInt > 0)) && ((i < getScrollRange()) || (paramInt < 0))) {}
    for (boolean bool = true;; bool = false)
    {
      if (!dispatchNestedPreFling(0.0F, paramInt))
      {
        dispatchNestedFling(0.0F, paramInt, bool);
        if (bool) {
          fling(paramInt);
        }
      }
      return;
    }
  }
  
  private int getScrollRange()
  {
    int i = 0;
    if (getChildCount() > 0) {
      i = Math.max(0, getChildAt(0).getHeight() - (getHeight() - getPaddingBottom() - getPaddingTop()));
    }
    return i;
  }
  
  private float getVerticalScrollFactorCompat()
  {
    if (this.mVerticalScrollFactor == 0.0F)
    {
      TypedValue localTypedValue = new TypedValue();
      Context localContext = getContext();
      if (!localContext.getTheme().resolveAttribute(16842829, localTypedValue, true)) {
        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
      }
      this.mVerticalScrollFactor = localTypedValue.getDimension(localContext.getResources().getDisplayMetrics());
    }
    return this.mVerticalScrollFactor;
  }
  
  private boolean inChild(int paramInt1, int paramInt2)
  {
    boolean bool = false;
    if (getChildCount() > 0)
    {
      int i = getScrollY();
      View localView = getChildAt(0);
      if ((paramInt2 >= localView.getTop() - i) && (paramInt2 < localView.getBottom() - i) && (paramInt1 >= localView.getLeft()) && (paramInt1 < localView.getRight())) {
        bool = true;
      }
    }
    return bool;
  }
  
  private void initOrResetVelocityTracker()
  {
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
    for (;;)
    {
      return;
      this.mVelocityTracker.clear();
    }
  }
  
  private void initScrollView()
  {
    this.mScroller = new ScrollerCompat(getContext(), null);
    setFocusable(true);
    setDescendantFocusability(262144);
    setWillNotDraw(false);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
  }
  
  private void initVelocityTrackerIfNotExists()
  {
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  private boolean isOffScreen(View paramView)
  {
    boolean bool = false;
    if (!isWithinDeltaOfScreen(paramView, 0, getHeight())) {
      bool = true;
    }
    return bool;
  }
  
  private static boolean isViewDescendantOf(View paramView1, View paramView2)
  {
    boolean bool = true;
    if (paramView1 == paramView2) {}
    for (;;)
    {
      return bool;
      ViewParent localViewParent = paramView1.getParent();
      if ((!(localViewParent instanceof ViewGroup)) || (!isViewDescendantOf((View)localViewParent, paramView2))) {
        bool = false;
      }
    }
  }
  
  private boolean isWithinDeltaOfScreen(View paramView, int paramInt1, int paramInt2)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    if ((paramInt1 + this.mTempRect.bottom >= getScrollY()) && (this.mTempRect.top - paramInt1 <= paramInt2 + getScrollY())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = (0xFF00 & paramMotionEvent.getAction()) >> 8;
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId) {
      if (i != 0) {
        break label64;
      }
    }
    label64:
    for (int j = 1;; j = 0)
    {
      this.mLastMotionY = ((int)MotionEventCompat.getY(paramMotionEvent, j));
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, j);
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.clear();
      }
      return;
    }
  }
  
  private void recycleVelocityTracker()
  {
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }
  
  private boolean scrollAndFocus(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool1 = true;
    int i = getHeight();
    int j = getScrollY();
    int k = j + i;
    if (paramInt1 == 33) {}
    for (boolean bool2 = true;; bool2 = false)
    {
      Object localObject = findFocusableViewInBounds(bool2, paramInt2, paramInt3);
      if (localObject == null) {
        localObject = this;
      }
      if ((paramInt2 < j) || (paramInt3 > k)) {
        break;
      }
      bool1 = false;
      if (localObject != findFocus()) {
        ((View)localObject).requestFocus(paramInt1);
      }
      return bool1;
    }
    if (bool2) {}
    for (int m = paramInt2 - j;; m = paramInt3 - k)
    {
      doScrollY(m);
      break;
    }
  }
  
  private void scrollToChild(View paramView)
  {
    paramView.getDrawingRect(this.mTempRect);
    offsetDescendantRectToMyCoords(paramView, this.mTempRect);
    int i = computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    if (i != 0) {
      scrollBy(0, i);
    }
  }
  
  private boolean scrollToChildRect(Rect paramRect, boolean paramBoolean)
  {
    int i = computeScrollDeltaToGetChildRectOnScreen(paramRect);
    boolean bool;
    if (i != 0)
    {
      bool = true;
      if (bool)
      {
        if (!paramBoolean) {
          break label37;
        }
        scrollBy(0, i);
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      break;
      label37:
      smoothScrollBy(0, i);
    }
  }
  
  public void addView(View paramView)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView);
  }
  
  public void addView(View paramView, int paramInt)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramInt);
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() > 0) {
      throw new IllegalStateException("ScrollView can host only one direct child");
    }
    super.addView(paramView, paramLayoutParams);
  }
  
  public boolean arrowScroll(int paramInt)
  {
    boolean bool = false;
    View localView1 = findFocus();
    if (localView1 == this) {
      localView1 = null;
    }
    View localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    int i = getMaxScrollAmount();
    if ((localView2 != null) && (isWithinDeltaOfScreen(localView2, i, getHeight())))
    {
      localView2.getDrawingRect(this.mTempRect);
      offsetDescendantRectToMyCoords(localView2, this.mTempRect);
      doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      localView2.requestFocus(paramInt);
      if ((localView1 != null) && (localView1.isFocused()) && (isOffScreen(localView1)))
      {
        int i1 = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(i1);
      }
      bool = true;
      label134:
      return bool;
    }
    int j = i;
    if ((paramInt == 33) && (getScrollY() < j))
    {
      j = getScrollY();
      label161:
      if (j == 0) {
        break label244;
      }
      if (paramInt != 130) {
        break label246;
      }
    }
    label244:
    label246:
    for (int n = j;; n = -j)
    {
      doScrollY(n);
      break;
      if ((paramInt != 130) || (getChildCount() <= 0)) {
        break label161;
      }
      int k = getChildAt(0).getBottom();
      int m = getScrollY() + getHeight() - getPaddingBottom();
      if (k - m >= i) {
        break label161;
      }
      j = k - m;
      break label161;
      break label134;
    }
  }
  
  public void computeScroll()
  {
    int i = 1;
    int k;
    int n;
    int i1;
    if (this.mScroller.computeScrollOffset())
    {
      int j = getScrollX();
      k = getScrollY();
      int m = this.mScroller.getCurrX();
      n = this.mScroller.getCurrY();
      if ((j != m) || (k != n))
      {
        i1 = getScrollRange();
        int i2 = ViewCompat.getOverScrollMode(this);
        if ((i2 != 0) && ((i2 != i) || (i1 <= 0))) {
          break label135;
        }
        overScrollByCompat(m - j, n - k, j, k, 0, i1, 0, 0, false);
        if (i != 0)
        {
          ensureGlows();
          if ((n > 0) || (k <= 0)) {
            break label140;
          }
          this.mEdgeGlowTop.onAbsorb((int)this.mScroller.getCurrVelocity());
        }
      }
    }
    for (;;)
    {
      return;
      label135:
      i = 0;
      break;
      label140:
      if ((n >= i1) && (k < i1)) {
        this.mEdgeGlowBottom.onAbsorb((int)this.mScroller.getCurrVelocity());
      }
    }
  }
  
  protected int computeScrollDeltaToGetChildRectOnScreen(Rect paramRect)
  {
    int n;
    if (getChildCount() == 0) {
      n = 0;
    }
    int i;
    int j;
    int k;
    do
    {
      return n;
      i = getHeight();
      j = getScrollY();
      k = j + i;
      int m = getVerticalFadingEdgeLength();
      if (paramRect.top > 0) {
        j += m;
      }
      if (paramRect.bottom < getChildAt(0).getHeight()) {
        k -= m;
      }
      n = 0;
      if ((paramRect.bottom > k) && (paramRect.top > j))
      {
        if (paramRect.height() > i) {}
        for (int i2 = 0 + (paramRect.top - j);; i2 = 0 + (paramRect.bottom - k))
        {
          n = Math.min(i2, getChildAt(0).getBottom() - k);
          break;
        }
      }
    } while ((paramRect.top >= j) || (paramRect.bottom >= k));
    if (paramRect.height() > i) {}
    for (int i1 = 0 - (k - paramRect.bottom);; i1 = 0 - (j - paramRect.top))
    {
      n = Math.max(i1, -getScrollY());
      break;
    }
  }
  
  protected int computeVerticalScrollOffset()
  {
    return Math.max(0, super.computeVerticalScrollOffset());
  }
  
  protected int computeVerticalScrollRange()
  {
    int i = getChildCount();
    int j = getHeight() - getPaddingBottom() - getPaddingTop();
    if (i == 0) {
      return j;
    }
    int k = getChildAt(0).getBottom();
    int m = getScrollY();
    int n = Math.max(0, k - j);
    if (m < 0) {
      k -= m;
    }
    for (;;)
    {
      j = k;
      break;
      if (m > n) {
        k += m - n;
      }
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return this.mChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return this.mChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return this.mChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return this.mChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (this.mEdgeGlowTop != null)
    {
      int i = getScrollY();
      if (!this.mEdgeGlowTop.isFinished())
      {
        int n = paramCanvas.save();
        int i1 = getWidth() - getPaddingLeft() - getPaddingRight();
        paramCanvas.translate(getPaddingLeft(), Math.min(0, i));
        this.mEdgeGlowTop.setSize(i1, getHeight());
        if (this.mEdgeGlowTop.draw(paramCanvas)) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        paramCanvas.restoreToCount(n);
      }
      if (!this.mEdgeGlowBottom.isFinished())
      {
        int j = paramCanvas.save();
        int k = getWidth() - getPaddingLeft() - getPaddingRight();
        int m = getHeight();
        paramCanvas.translate(-k + getPaddingLeft(), m + Math.max(getScrollRange(), i));
        paramCanvas.rotate(180.0F, k, 0.0F);
        this.mEdgeGlowBottom.setSize(k, m);
        if (this.mEdgeGlowBottom.draw(paramCanvas)) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        paramCanvas.restoreToCount(j);
      }
    }
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    boolean bool1 = false;
    this.mTempRect.setEmpty();
    if (!canScroll())
    {
      if ((isFocused()) && (paramKeyEvent.getKeyCode() != 4))
      {
        View localView1 = findFocus();
        if (localView1 == this) {
          localView1 = null;
        }
        View localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, 130);
        if ((localView2 != null) && (localView2 != this) && (localView2.requestFocus(130))) {
          bool1 = true;
        }
      }
      return bool1;
    }
    boolean bool2 = false;
    if (paramKeyEvent.getAction() == 0) {}
    switch (paramKeyEvent.getKeyCode())
    {
    default: 
    case 19: 
    case 20: 
      for (;;)
      {
        bool1 = bool2;
        break;
        if (!paramKeyEvent.isAltPressed())
        {
          bool2 = arrowScroll(33);
        }
        else
        {
          bool2 = fullScroll(33);
          continue;
          if (!paramKeyEvent.isAltPressed()) {
            bool2 = arrowScroll(130);
          } else {
            bool2 = fullScroll(130);
          }
        }
      }
    }
    if (paramKeyEvent.isShiftPressed()) {}
    for (int i = 33;; i = 130)
    {
      pageScroll(i);
      break;
    }
  }
  
  public void fling(int paramInt)
  {
    if (getChildCount() > 0)
    {
      int i = getHeight() - getPaddingBottom() - getPaddingTop();
      int j = getChildAt(0).getHeight();
      this.mScroller.fling(getScrollX(), getScrollY(), 0, paramInt, 0, 0, 0, Math.max(0, j - i), 0, i / 2);
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public boolean fullScroll(int paramInt)
  {
    if (paramInt == 130) {}
    for (int i = 1;; i = 0)
    {
      int j = getHeight();
      this.mTempRect.top = 0;
      this.mTempRect.bottom = j;
      if (i != 0)
      {
        int k = getChildCount();
        if (k > 0)
        {
          View localView = getChildAt(k - 1);
          this.mTempRect.bottom = (localView.getBottom() + getPaddingBottom());
          this.mTempRect.top = (this.mTempRect.bottom - j);
        }
      }
      return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
    }
  }
  
  protected float getBottomFadingEdgeStrength()
  {
    float f;
    if (getChildCount() == 0) {
      f = 0.0F;
    }
    for (;;)
    {
      return f;
      int i = getVerticalFadingEdgeLength();
      int j = getHeight() - getPaddingBottom();
      int k = getChildAt(0).getBottom() - getScrollY() - j;
      if (k < i) {
        f = k / i;
      } else {
        f = 1.0F;
      }
    }
  }
  
  public int getMaxScrollAmount()
  {
    return (int)(0.5F * getHeight());
  }
  
  public int getNestedScrollAxes()
  {
    return this.mParentHelper.getNestedScrollAxes();
  }
  
  protected float getTopFadingEdgeStrength()
  {
    float f;
    if (getChildCount() == 0) {
      f = 0.0F;
    }
    for (;;)
    {
      return f;
      int i = getVerticalFadingEdgeLength();
      int j = getScrollY();
      if (j < i) {
        f = j / i;
      } else {
        f = 1.0F;
      }
    }
  }
  
  public boolean hasNestedScrollingParent()
  {
    return this.mChildHelper.hasNestedScrollingParent();
  }
  
  public boolean isFillViewport()
  {
    return this.mFillViewport;
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return this.mChildHelper.isNestedScrollingEnabled();
  }
  
  public boolean isSmoothScrollingEnabled()
  {
    return this.mSmoothScrollingEnabled;
  }
  
  protected void measureChild(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
  }
  
  protected void measureChildWithMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    paramView.measure(getChildMeasureSpec(paramInt1, paramInt2 + (getPaddingLeft() + getPaddingRight() + localMarginLayoutParams.leftMargin + localMarginLayoutParams.rightMargin), localMarginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin, 0));
  }
  
  public void onAttachedToWindow()
  {
    this.mIsLaidOut = false;
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if ((0x2 & MotionEventCompat.getSource(paramMotionEvent)) != 0) {
      switch (paramMotionEvent.getAction())
      {
      }
    }
    label126:
    for (;;)
    {
      boolean bool = false;
      return bool;
      if (!this.mIsBeingDragged)
      {
        float f = MotionEventCompat.getAxisValue(paramMotionEvent, 9);
        if (f != 0.0F)
        {
          int i = (int)(f * getVerticalScrollFactorCompat());
          int j = getScrollRange();
          int k = getScrollY();
          int m = k - i;
          if (m < 0) {
            m = 0;
          }
          for (;;)
          {
            if (m == k) {
              break label126;
            }
            super.scrollTo(getScrollX(), m);
            bool = true;
            break;
            if (m > j) {
              m = j;
            }
          }
        }
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 1;
    int j = paramMotionEvent.getAction();
    if ((j == 2) && (this.mIsBeingDragged)) {}
    boolean bool;
    for (;;)
    {
      return i;
      if ((getScrollY() != 0) || (ViewCompat.canScrollVertically(this, i))) {
        break;
      }
      bool = false;
    }
    switch (j & 0xFF)
    {
    }
    for (;;)
    {
      bool = this.mIsBeingDragged;
      break;
      int m = this.mActivePointerId;
      if (m != -1)
      {
        int n = MotionEventCompat.findPointerIndex(paramMotionEvent, m);
        if (n == -1)
        {
          Log.e("NestedScrollView", "Invalid pointerId=" + m + " in onInterceptTouchEvent");
        }
        else
        {
          int i1 = (int)MotionEventCompat.getY(paramMotionEvent, n);
          if ((Math.abs(i1 - this.mLastMotionY) > this.mTouchSlop) && ((0x2 & getNestedScrollAxes()) == 0))
          {
            this.mIsBeingDragged = bool;
            this.mLastMotionY = i1;
            initVelocityTrackerIfNotExists();
            this.mVelocityTracker.addMovement(paramMotionEvent);
            this.mNestedYOffset = 0;
            ViewParent localViewParent = getParent();
            if (localViewParent != null)
            {
              localViewParent.requestDisallowInterceptTouchEvent(bool);
              continue;
              int k = (int)paramMotionEvent.getY();
              if (!inChild((int)paramMotionEvent.getX(), k))
              {
                this.mIsBeingDragged = false;
                recycleVelocityTracker();
              }
              else
              {
                this.mLastMotionY = k;
                this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
                initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(paramMotionEvent);
                if (!this.mScroller.isFinished()) {}
                for (;;)
                {
                  this.mIsBeingDragged = bool;
                  startNestedScroll(2);
                  break;
                  bool = false;
                }
                this.mIsBeingDragged = false;
                this.mActivePointerId = -1;
                recycleVelocityTracker();
                stopNestedScroll();
                continue;
                onSecondaryPointerUp(paramMotionEvent);
              }
            }
          }
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mIsLayoutDirty = false;
    if ((this.mChildToScrollTo != null) && (isViewDescendantOf(this.mChildToScrollTo, this))) {
      scrollToChild(this.mChildToScrollTo);
    }
    this.mChildToScrollTo = null;
    int i;
    if (!this.mIsLaidOut)
    {
      if (this.mSavedState != null)
      {
        scrollTo(getScrollX(), this.mSavedState.scrollPosition);
        this.mSavedState = null;
      }
      if (getChildCount() <= 0) {
        break label158;
      }
      i = getChildAt(0).getMeasuredHeight();
      int j = Math.max(0, i - (paramInt4 - paramInt2 - getPaddingBottom() - getPaddingTop()));
      if (getScrollY() <= j) {
        break label164;
      }
      scrollTo(getScrollX(), j);
    }
    for (;;)
    {
      scrollTo(getScrollX(), getScrollY());
      this.mIsLaidOut = true;
      return;
      label158:
      i = 0;
      break;
      label164:
      if (getScrollY() < 0) {
        scrollTo(getScrollX(), 0);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (!this.mFillViewport) {}
    for (;;)
    {
      return;
      if ((View.MeasureSpec.getMode(paramInt2) != 0) && (getChildCount() > 0))
      {
        View localView = getChildAt(0);
        int i = getMeasuredHeight();
        if (localView.getMeasuredHeight() < i)
        {
          FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)localView.getLayoutParams();
          localView.measure(getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight(), localLayoutParams.width), View.MeasureSpec.makeMeasureSpec(i - getPaddingTop() - getPaddingBottom(), 1073741824));
        }
      }
    }
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (!paramBoolean) {
      flingWithNestedDispatch((int)paramFloat2);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    return false;
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getScrollY();
    scrollBy(0, paramInt4);
    int j = getScrollY() - i;
    dispatchNestedScroll(0, j, 0, paramInt4 - j, null);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    this.mParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt);
    startNestedScroll(2);
  }
  
  protected void onOverScrolled(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    super.scrollTo(paramInt1, paramInt2);
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    boolean bool = false;
    View localView;
    if (paramInt == 2)
    {
      paramInt = 130;
      if (paramRect != null) {
        break label44;
      }
      localView = FocusFinder.getInstance().findNextFocus(this, null, paramInt);
      label26:
      if (localView != null) {
        break label58;
      }
    }
    for (;;)
    {
      return bool;
      if (paramInt != 1) {
        break;
      }
      paramInt = 33;
      break;
      label44:
      localView = FocusFinder.getInstance().findNextFocusFromRect(this, paramRect, paramInt);
      break label26;
      label58:
      if (!isOffScreen(localView)) {
        bool = localView.requestFocus(paramInt, paramRect);
      }
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    this.mSavedState = localSavedState;
    requestLayout();
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.scrollPosition = getScrollY();
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findFocus();
    if ((localView == null) || (this == localView)) {}
    for (;;)
    {
      return;
      if (isWithinDeltaOfScreen(localView, 0, paramInt4))
      {
        localView.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(localView, this.mTempRect);
        doScrollY(computeScrollDeltaToGetChildRectOnScreen(this.mTempRect));
      }
    }
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    if ((paramInt & 0x2) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onStopNestedScroll(View paramView)
  {
    stopNestedScroll();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    initVelocityTrackerIfNotExists();
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if (i == 0) {
      this.mNestedYOffset = 0;
    }
    localMotionEvent.offsetLocation(0.0F, this.mNestedYOffset);
    switch (i)
    {
    }
    for (;;)
    {
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.addMovement(localMotionEvent);
      }
      localMotionEvent.recycle();
      for (boolean bool1 = true;; bool1 = false)
      {
        return bool1;
        if (getChildCount() != 0) {
          break;
        }
      }
      if (!this.mScroller.isFinished()) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        this.mIsBeingDragged = bool2;
        if (bool2)
        {
          ViewParent localViewParent2 = getParent();
          if (localViewParent2 != null) {
            localViewParent2.requestDisallowInterceptTouchEvent(true);
          }
        }
        if (!this.mScroller.isFinished()) {
          this.mScroller.abortAnimation();
        }
        this.mLastMotionY = ((int)paramMotionEvent.getY());
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
        startNestedScroll(2);
        break;
      }
      int m = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
      if (m == -1)
      {
        Log.e("NestedScrollView", "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
      }
      else
      {
        int n = (int)MotionEventCompat.getY(paramMotionEvent, m);
        int i1 = this.mLastMotionY - n;
        if (dispatchNestedPreScroll(0, i1, this.mScrollConsumed, this.mScrollOffset))
        {
          i1 -= this.mScrollConsumed[1];
          localMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
          this.mNestedYOffset += this.mScrollOffset[1];
        }
        label393:
        int i2;
        int i3;
        if ((!this.mIsBeingDragged) && (Math.abs(i1) > this.mTouchSlop))
        {
          ViewParent localViewParent1 = getParent();
          if (localViewParent1 != null) {
            localViewParent1.requestDisallowInterceptTouchEvent(true);
          }
          this.mIsBeingDragged = true;
          if (i1 > 0) {
            i1 -= this.mTouchSlop;
          }
        }
        else
        {
          if (!this.mIsBeingDragged) {
            continue;
          }
          this.mLastMotionY = (n - this.mScrollOffset[1]);
          i2 = getScrollY();
          i3 = getScrollRange();
          int i4 = ViewCompat.getOverScrollMode(this);
          if ((i4 != 0) && ((i4 != 1) || (i3 <= 0))) {
            break label571;
          }
        }
        label571:
        for (int i5 = 1;; i5 = 0)
        {
          if ((overScrollByCompat(0, i1, 0, getScrollY(), 0, i3, 0, 0, true)) && (!hasNestedScrollingParent())) {
            this.mVelocityTracker.clear();
          }
          int i6 = getScrollY() - i2;
          if (!dispatchNestedScroll(0, i6, 0, i1 - i6, this.mScrollOffset)) {
            break label577;
          }
          this.mLastMotionY -= this.mScrollOffset[1];
          localMotionEvent.offsetLocation(0.0F, this.mScrollOffset[1]);
          this.mNestedYOffset += this.mScrollOffset[1];
          break;
          i1 += this.mTouchSlop;
          break label393;
        }
        label577:
        if (i5 != 0)
        {
          ensureGlows();
          int i7 = i2 + i1;
          if (i7 < 0)
          {
            this.mEdgeGlowTop.onPull(i1 / getHeight(), MotionEventCompat.getX(paramMotionEvent, m) / getWidth());
            if (!this.mEdgeGlowBottom.isFinished()) {
              this.mEdgeGlowBottom.onRelease();
            }
          }
          while ((this.mEdgeGlowTop != null) && ((!this.mEdgeGlowTop.isFinished()) || (!this.mEdgeGlowBottom.isFinished())))
          {
            ViewCompat.postInvalidateOnAnimation(this);
            break;
            if (i7 > i3)
            {
              this.mEdgeGlowBottom.onPull(i1 / getHeight(), 1.0F - MotionEventCompat.getX(paramMotionEvent, m) / getWidth());
              if (!this.mEdgeGlowTop.isFinished()) {
                this.mEdgeGlowTop.onRelease();
              }
            }
          }
          if (this.mIsBeingDragged)
          {
            VelocityTracker localVelocityTracker = this.mVelocityTracker;
            localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int k = (int)VelocityTrackerCompat.getYVelocity(localVelocityTracker, this.mActivePointerId);
            if (Math.abs(k) > this.mMinimumVelocity) {
              flingWithNestedDispatch(-k);
            }
            this.mActivePointerId = -1;
            endDrag();
            continue;
            if ((this.mIsBeingDragged) && (getChildCount() > 0))
            {
              this.mActivePointerId = -1;
              endDrag();
              continue;
              int j = MotionEventCompat.getActionIndex(paramMotionEvent);
              this.mLastMotionY = ((int)MotionEventCompat.getY(paramMotionEvent, j));
              this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, j);
              continue;
              onSecondaryPointerUp(paramMotionEvent);
              this.mLastMotionY = ((int)MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId)));
            }
          }
        }
      }
    }
  }
  
  boolean overScrollByCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
  {
    int i = ViewCompat.getOverScrollMode(this);
    int j;
    int k;
    label34:
    int m;
    label53:
    int n;
    label72:
    int i1;
    int i2;
    int i3;
    int i5;
    boolean bool1;
    label140:
    boolean bool2;
    if (computeHorizontalScrollRange() > computeHorizontalScrollExtent())
    {
      j = 1;
      if (computeVerticalScrollRange() <= computeVerticalScrollExtent()) {
        break label191;
      }
      k = 1;
      if ((i != 0) && ((i != 1) || (j == 0))) {
        break label197;
      }
      m = 1;
      if ((i != 0) && ((i != 1) || (k == 0))) {
        break label203;
      }
      n = 1;
      i1 = paramInt3 + paramInt1;
      if (m == 0) {
        paramInt7 = 0;
      }
      i2 = paramInt4 + paramInt2;
      if (n == 0) {
        paramInt8 = 0;
      }
      i3 = -paramInt7;
      int i4 = paramInt7 + paramInt5;
      i5 = -paramInt8;
      int i6 = paramInt8 + paramInt6;
      bool1 = false;
      if (i1 <= i4) {
        break label209;
      }
      i1 = i4;
      bool1 = true;
      bool2 = false;
      if (i2 <= i6) {
        break label226;
      }
      i2 = i6;
      bool2 = true;
      label157:
      onOverScrolled(i1, i2, bool1, bool2);
      if ((!bool1) && (!bool2)) {
        break label243;
      }
    }
    label191:
    label197:
    label203:
    label209:
    label226:
    label243:
    for (boolean bool3 = true;; bool3 = false)
    {
      return bool3;
      j = 0;
      break;
      k = 0;
      break label34;
      m = 0;
      break label53;
      n = 0;
      break label72;
      if (i1 >= i3) {
        break label140;
      }
      i1 = i3;
      bool1 = true;
      break label140;
      if (i2 >= i5) {
        break label157;
      }
      i2 = i5;
      bool2 = true;
      break label157;
    }
  }
  
  public boolean pageScroll(int paramInt)
  {
    int i;
    int j;
    if (paramInt == 130)
    {
      i = 1;
      j = getHeight();
      if (i == 0) {
        break label124;
      }
      this.mTempRect.top = (j + getScrollY());
      int k = getChildCount();
      if (k > 0)
      {
        View localView = getChildAt(k - 1);
        if (j + this.mTempRect.top > localView.getBottom()) {
          this.mTempRect.top = (localView.getBottom() - j);
        }
      }
    }
    for (;;)
    {
      this.mTempRect.bottom = (j + this.mTempRect.top);
      return scrollAndFocus(paramInt, this.mTempRect.top, this.mTempRect.bottom);
      i = 0;
      break;
      label124:
      this.mTempRect.top = (getScrollY() - j);
      if (this.mTempRect.top < 0) {
        this.mTempRect.top = 0;
      }
    }
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    if (!this.mIsLayoutDirty) {
      scrollToChild(paramView2);
    }
    for (;;)
    {
      super.requestChildFocus(paramView1, paramView2);
      return;
      this.mChildToScrollTo = paramView2;
    }
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    paramRect.offset(paramView.getLeft() - paramView.getScrollX(), paramView.getTop() - paramView.getScrollY());
    return scrollToChildRect(paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    if (paramBoolean) {
      recycleVelocityTracker();
    }
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout()
  {
    this.mIsLayoutDirty = true;
    super.requestLayout();
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    if (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      int i = clamp(paramInt1, getWidth() - getPaddingRight() - getPaddingLeft(), localView.getWidth());
      int j = clamp(paramInt2, getHeight() - getPaddingBottom() - getPaddingTop(), localView.getHeight());
      if ((i != getScrollX()) || (j != getScrollY())) {
        super.scrollTo(i, j);
      }
    }
  }
  
  public void setFillViewport(boolean paramBoolean)
  {
    if (paramBoolean != this.mFillViewport)
    {
      this.mFillViewport = paramBoolean;
      requestLayout();
    }
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    this.mChildHelper.setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setSmoothScrollingEnabled(boolean paramBoolean)
  {
    this.mSmoothScrollingEnabled = paramBoolean;
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return true;
  }
  
  public final void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (getChildCount() == 0) {
      return;
    }
    if (AnimationUtils.currentAnimationTimeMillis() - this.mLastScroll > 250L)
    {
      int i = getHeight() - getPaddingBottom() - getPaddingTop();
      int j = Math.max(0, getChildAt(0).getHeight() - i);
      int k = getScrollY();
      int m = Math.max(0, Math.min(k + paramInt2, j)) - k;
      this.mScroller.startScroll(getScrollX(), k, 0, m);
      ViewCompat.postInvalidateOnAnimation(this);
    }
    for (;;)
    {
      this.mLastScroll = AnimationUtils.currentAnimationTimeMillis();
      break;
      if (!this.mScroller.isFinished()) {
        this.mScroller.abortAnimation();
      }
      scrollBy(paramInt1, paramInt2);
    }
  }
  
  public final void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1 - getScrollX(), paramInt2 - getScrollY());
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return this.mChildHelper.startNestedScroll(paramInt);
  }
  
  public void stopNestedScroll()
  {
    this.mChildHelper.stopNestedScroll();
  }
  
  static class AccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      NestedScrollView localNestedScrollView = (NestedScrollView)paramView;
      paramAccessibilityEvent.setClassName(ScrollView.class.getName());
      AccessibilityRecordCompat localAccessibilityRecordCompat = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
      if (localNestedScrollView.getScrollRange() > 0) {}
      for (boolean bool = true;; bool = false)
      {
        localAccessibilityRecordCompat.setScrollable(bool);
        localAccessibilityRecordCompat.setScrollX(localNestedScrollView.getScrollX());
        localAccessibilityRecordCompat.setScrollY(localNestedScrollView.getScrollY());
        localAccessibilityRecordCompat.setMaxScrollX(localNestedScrollView.getScrollX());
        localAccessibilityRecordCompat.setMaxScrollY(localNestedScrollView.getScrollRange());
        return;
      }
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      NestedScrollView localNestedScrollView = (NestedScrollView)paramView;
      paramAccessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
      if (localNestedScrollView.isEnabled())
      {
        int i = localNestedScrollView.getScrollRange();
        if (i > 0)
        {
          paramAccessibilityNodeInfoCompat.setScrollable(true);
          if (localNestedScrollView.getScrollY() > 0) {
            paramAccessibilityNodeInfoCompat.addAction(8192);
          }
          if (localNestedScrollView.getScrollY() < i) {
            paramAccessibilityNodeInfoCompat.addAction(4096);
          }
        }
      }
    }
    
    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      boolean bool = true;
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {}
      for (;;)
      {
        return bool;
        NestedScrollView localNestedScrollView = (NestedScrollView)paramView;
        if (!localNestedScrollView.isEnabled()) {
          bool = false;
        } else {
          switch (paramInt)
          {
          default: 
            bool = false;
            break;
          case 4096: 
            int k = Math.min(localNestedScrollView.getHeight() - localNestedScrollView.getPaddingBottom() - localNestedScrollView.getPaddingTop() + localNestedScrollView.getScrollY(), localNestedScrollView.getScrollRange());
            if (k != localNestedScrollView.getScrollY()) {
              localNestedScrollView.smoothScrollTo(0, k);
            } else {
              bool = false;
            }
            break;
          case 8192: 
            int i = localNestedScrollView.getHeight() - localNestedScrollView.getPaddingBottom() - localNestedScrollView.getPaddingTop();
            int j = Math.max(localNestedScrollView.getScrollY() - i, 0);
            if (j != localNestedScrollView.getScrollY()) {
              localNestedScrollView.smoothScrollTo(0, j);
            } else {
              bool = false;
            }
            break;
          }
        }
      }
    }
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public NestedScrollView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new NestedScrollView.SavedState(paramAnonymousParcel);
      }
      
      public NestedScrollView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new NestedScrollView.SavedState[paramAnonymousInt];
      }
    };
    public int scrollPosition;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      this.scrollPosition = paramParcel.readInt();
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.scrollPosition + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.scrollPosition);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/widget/NestedScrollView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */