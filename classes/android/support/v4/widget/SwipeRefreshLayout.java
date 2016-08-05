package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;

public class SwipeRefreshLayout
  extends ViewGroup
  implements NestedScrollingParent, NestedScrollingChild
{
  private static final int ALPHA_ANIMATION_DURATION = 300;
  private static final int ANIMATE_TO_START_DURATION = 200;
  private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
  private static final int CIRCLE_BG_LIGHT = -328966;
  private static final int CIRCLE_DIAMETER = 40;
  private static final int CIRCLE_DIAMETER_LARGE = 56;
  private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0F;
  public static final int DEFAULT = 1;
  private static final int DEFAULT_CIRCLE_TARGET = 64;
  private static final float DRAG_RATE = 0.5F;
  private static final int INVALID_POINTER = -1;
  public static final int LARGE = 0;
  private static final int[] LAYOUT_ATTRS;
  private static final String LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
  private static final int MAX_ALPHA = 255;
  private static final float MAX_PROGRESS_ANGLE = 0.8F;
  private static final int SCALE_DOWN_DURATION = 150;
  private static final int STARTING_PROGRESS_ALPHA = 76;
  private int mActivePointerId = -1;
  private Animation mAlphaMaxAnimation;
  private Animation mAlphaStartAnimation;
  private final Animation mAnimateToCorrectPosition = new Animation()
  {
    public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
    {
      if (!SwipeRefreshLayout.this.mUsingCustomStart) {}
      for (int i = (int)(SwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop));; i = (int)SwipeRefreshLayout.this.mSpinnerFinalOffset)
      {
        int j = SwipeRefreshLayout.this.mFrom + (int)(paramAnonymousFloat * (i - SwipeRefreshLayout.this.mFrom)) - SwipeRefreshLayout.this.mCircleView.getTop();
        SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(j, false);
        SwipeRefreshLayout.this.mProgress.setArrowScale(1.0F - paramAnonymousFloat);
        return;
      }
    }
  };
  private final Animation mAnimateToStartPosition = new Animation()
  {
    public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
    {
      SwipeRefreshLayout.this.moveToStart(paramAnonymousFloat);
    }
  };
  private int mCircleHeight;
  private CircleImageView mCircleView;
  private int mCircleViewIndex = -1;
  private int mCircleWidth;
  private int mCurrentTargetOffsetTop;
  private final DecelerateInterpolator mDecelerateInterpolator;
  protected int mFrom;
  private float mInitialDownY;
  private float mInitialMotionY;
  private boolean mIsBeingDragged;
  private OnRefreshListener mListener;
  private int mMediumAnimationDuration;
  private final NestedScrollingChildHelper mNestedScrollingChildHelper;
  private final NestedScrollingParentHelper mNestedScrollingParentHelper;
  private boolean mNotify;
  private boolean mOriginalOffsetCalculated = false;
  protected int mOriginalOffsetTop;
  private final int[] mParentScrollConsumed = new int[2];
  private final Animation mPeek = new Animation()
  {
    public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
    {
      if (!SwipeRefreshLayout.this.mUsingCustomStart) {}
      for (int i = (int)(SwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop));; i = (int)SwipeRefreshLayout.this.mSpinnerFinalOffset)
      {
        int j = SwipeRefreshLayout.this.mFrom + (int)(paramAnonymousFloat * (i - SwipeRefreshLayout.this.mFrom)) - SwipeRefreshLayout.this.mCircleView.getTop();
        SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(j, false);
        SwipeRefreshLayout.this.mProgress.setArrowScale(1.0F - paramAnonymousFloat);
        return;
      }
    }
  };
  private MaterialProgressDrawable mProgress;
  private Animation.AnimationListener mRefreshListener = new Animation.AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      if (SwipeRefreshLayout.this.mRefreshing)
      {
        SwipeRefreshLayout.this.mProgress.setAlpha(255);
        SwipeRefreshLayout.this.mProgress.start();
        if ((SwipeRefreshLayout.this.mNotify) && (SwipeRefreshLayout.this.mListener != null)) {
          SwipeRefreshLayout.this.mListener.onRefresh();
        }
      }
      for (;;)
      {
        SwipeRefreshLayout.access$802(SwipeRefreshLayout.this, SwipeRefreshLayout.this.mCircleView.getTop());
        return;
        SwipeRefreshLayout.this.mProgress.stop();
        SwipeRefreshLayout.this.mCircleView.setVisibility(8);
        SwipeRefreshLayout.this.setColorViewAlpha(255);
        if (SwipeRefreshLayout.this.mScale) {
          SwipeRefreshLayout.this.setAnimationProgress(0.0F);
        } else {
          SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SwipeRefreshLayout.this.mOriginalOffsetTop - SwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
        }
      }
    }
    
    public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
    
    public void onAnimationStart(Animation paramAnonymousAnimation) {}
  };
  private boolean mRefreshing = false;
  private boolean mReturningToStart;
  private boolean mScale;
  private Animation mScaleAnimation;
  private Animation mScaleDownAnimation;
  private Animation mScaleDownToStartAnimation;
  private float mSpinnerFinalOffset;
  private float mStartingScale;
  private View mTarget;
  private float mTotalDragDistance = -1.0F;
  private float mTotalUnconsumed;
  private int mTouchSlop;
  private boolean mUsingCustomStart;
  
  static
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 16842766;
    LAYOUT_ATTRS = arrayOfInt;
  }
  
  public SwipeRefreshLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.mMediumAnimationDuration = getResources().getInteger(17694721);
    setWillNotDraw(false);
    this.mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, LAYOUT_ATTRS);
    setEnabled(localTypedArray.getBoolean(0, true));
    localTypedArray.recycle();
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.mCircleWidth = ((int)(40.0F * localDisplayMetrics.density));
    this.mCircleHeight = ((int)(40.0F * localDisplayMetrics.density));
    createProgressView();
    ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    this.mSpinnerFinalOffset = (64.0F * localDisplayMetrics.density);
    this.mTotalDragDistance = this.mSpinnerFinalOffset;
    this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
    setNestedScrollingEnabled(true);
  }
  
  private void animateOffsetToCorrectPosition(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    this.mAnimateToCorrectPosition.reset();
    this.mAnimateToCorrectPosition.setDuration(200L);
    this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
    if (paramAnimationListener != null) {
      this.mCircleView.setAnimationListener(paramAnimationListener);
    }
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
  }
  
  private void animateOffsetToStartPosition(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    if (this.mScale) {
      startScaleDownReturnToStartAnimation(paramInt, paramAnimationListener);
    }
    for (;;)
    {
      return;
      this.mFrom = paramInt;
      this.mAnimateToStartPosition.reset();
      this.mAnimateToStartPosition.setDuration(200L);
      this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
      if (paramAnimationListener != null) {
        this.mCircleView.setAnimationListener(paramAnimationListener);
      }
      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mAnimateToStartPosition);
    }
  }
  
  private void createProgressView()
  {
    this.mCircleView = new CircleImageView(getContext(), -328966, 20.0F);
    this.mProgress = new MaterialProgressDrawable(getContext(), this);
    this.mProgress.setBackgroundColor(-328966);
    this.mCircleView.setImageDrawable(this.mProgress);
    this.mCircleView.setVisibility(8);
    addView(this.mCircleView);
  }
  
  private void ensureTarget()
  {
    if (this.mTarget == null) {}
    for (int i = 0;; i++) {
      if (i < getChildCount())
      {
        View localView = getChildAt(i);
        if (!localView.equals(this.mCircleView)) {
          this.mTarget = localView;
        }
      }
      else
      {
        return;
      }
    }
  }
  
  private void finishSpinner(float paramFloat)
  {
    if (paramFloat > this.mTotalDragDistance) {
      setRefreshing(true, true);
    }
    for (;;)
    {
      return;
      this.mRefreshing = false;
      this.mProgress.setStartEndTrim(0.0F, 0.0F);
      Animation.AnimationListener local5 = null;
      if (!this.mScale) {
        local5 = new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
            if (!SwipeRefreshLayout.this.mScale) {
              SwipeRefreshLayout.this.startScaleDownAnimation(null);
            }
          }
          
          public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
          
          public void onAnimationStart(Animation paramAnonymousAnimation) {}
        };
      }
      animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, local5);
      this.mProgress.showArrow(false);
    }
  }
  
  private float getMotionEventY(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = MotionEventCompat.findPointerIndex(paramMotionEvent, paramInt);
    if (i < 0) {}
    for (float f = -1.0F;; f = MotionEventCompat.getY(paramMotionEvent, i)) {
      return f;
    }
  }
  
  private boolean isAlphaUsedForScale()
  {
    if (Build.VERSION.SDK_INT < 11) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isAnimationRunning(Animation paramAnimation)
  {
    if ((paramAnimation != null) && (paramAnimation.hasStarted()) && (!paramAnimation.hasEnded())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void moveSpinner(float paramFloat)
  {
    this.mProgress.showArrow(true);
    float f1 = Math.min(1.0F, Math.abs(paramFloat / this.mTotalDragDistance));
    float f2 = 5.0F * (float)Math.max(f1 - 0.4D, 0.0D) / 3.0F;
    float f3 = Math.abs(paramFloat) - this.mTotalDragDistance;
    float f4;
    float f6;
    int i;
    if (this.mUsingCustomStart)
    {
      f4 = this.mSpinnerFinalOffset - this.mOriginalOffsetTop;
      float f5 = Math.max(0.0F, Math.min(f3, 2.0F * f4) / f4);
      f6 = 2.0F * (float)(f5 / 4.0F - Math.pow(f5 / 4.0F, 2.0D));
      float f7 = 2.0F * (f4 * f6);
      i = this.mOriginalOffsetTop + (int)(f7 + f4 * f1);
      if (this.mCircleView.getVisibility() != 0) {
        this.mCircleView.setVisibility(0);
      }
      if (!this.mScale)
      {
        ViewCompat.setScaleX(this.mCircleView, 1.0F);
        ViewCompat.setScaleY(this.mCircleView, 1.0F);
      }
      if (paramFloat >= this.mTotalDragDistance) {
        break label317;
      }
      if (this.mScale) {
        setAnimationProgress(paramFloat / this.mTotalDragDistance);
      }
      if ((this.mProgress.getAlpha() > 76) && (!isAnimationRunning(this.mAlphaStartAnimation))) {
        startProgressAlphaStartAnimation();
      }
      float f9 = f2 * 0.8F;
      this.mProgress.setStartEndTrim(0.0F, Math.min(0.8F, f9));
      this.mProgress.setArrowScale(Math.min(1.0F, f2));
    }
    for (;;)
    {
      float f8 = 0.5F * (-0.25F + 0.4F * f2 + 2.0F * f6);
      this.mProgress.setProgressRotation(f8);
      setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
      return;
      f4 = this.mSpinnerFinalOffset;
      break;
      label317:
      if ((this.mProgress.getAlpha() < 255) && (!isAnimationRunning(this.mAlphaMaxAnimation))) {
        startProgressAlphaMaxAnimation();
      }
    }
  }
  
  private void moveToStart(float paramFloat)
  {
    setTargetOffsetTopAndBottom(this.mFrom + (int)(paramFloat * (this.mOriginalOffsetTop - this.mFrom)) - this.mCircleView.getTop(), false);
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId) {
      if (i != 0) {
        break label33;
      }
    }
    label33:
    for (int j = 1;; j = 0)
    {
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, j);
      return;
    }
  }
  
  private void peek(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    this.mPeek.reset();
    this.mPeek.setDuration(500L);
    this.mPeek.setInterpolator(this.mDecelerateInterpolator);
    if (paramAnimationListener != null) {
      this.mCircleView.setAnimationListener(paramAnimationListener);
    }
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mPeek);
  }
  
  private void setAnimationProgress(float paramFloat)
  {
    if (isAlphaUsedForScale()) {
      setColorViewAlpha((int)(255.0F * paramFloat));
    }
    for (;;)
    {
      return;
      ViewCompat.setScaleX(this.mCircleView, paramFloat);
      ViewCompat.setScaleY(this.mCircleView, paramFloat);
    }
  }
  
  private void setColorViewAlpha(int paramInt)
  {
    this.mCircleView.getBackground().setAlpha(paramInt);
    this.mProgress.setAlpha(paramInt);
  }
  
  private void setRefreshing(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mRefreshing != paramBoolean1)
    {
      this.mNotify = paramBoolean2;
      ensureTarget();
      this.mRefreshing = paramBoolean1;
      if (!this.mRefreshing) {
        break label42;
      }
      animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
    }
    for (;;)
    {
      return;
      label42:
      startScaleDownAnimation(this.mRefreshListener);
    }
  }
  
  private void setTargetOffsetTopAndBottom(int paramInt, boolean paramBoolean)
  {
    this.mCircleView.bringToFront();
    this.mCircleView.offsetTopAndBottom(paramInt);
    this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
    if ((paramBoolean) && (Build.VERSION.SDK_INT < 11)) {
      invalidate();
    }
  }
  
  private Animation startAlphaAnimation(final int paramInt1, final int paramInt2)
  {
    Object localObject;
    if ((this.mScale) && (isAlphaUsedForScale())) {
      localObject = null;
    }
    for (;;)
    {
      return (Animation)localObject;
      localObject = new Animation()
      {
        public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
        {
          SwipeRefreshLayout.this.mProgress.setAlpha((int)(paramInt1 + paramAnonymousFloat * (paramInt2 - paramInt1)));
        }
      };
      ((Animation)localObject).setDuration(300L);
      this.mCircleView.setAnimationListener(null);
      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation((Animation)localObject);
    }
  }
  
  private void startProgressAlphaMaxAnimation()
  {
    this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 255);
  }
  
  private void startProgressAlphaStartAnimation()
  {
    this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
  }
  
  private void startScaleDownAnimation(Animation.AnimationListener paramAnimationListener)
  {
    this.mScaleDownAnimation = new Animation()
    {
      public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        SwipeRefreshLayout.this.setAnimationProgress(1.0F - paramAnonymousFloat);
      }
    };
    this.mScaleDownAnimation.setDuration(150L);
    this.mCircleView.setAnimationListener(paramAnimationListener);
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mScaleDownAnimation);
  }
  
  private void startScaleDownReturnToStartAnimation(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    if (isAlphaUsedForScale()) {}
    for (this.mStartingScale = this.mProgress.getAlpha();; this.mStartingScale = ViewCompat.getScaleX(this.mCircleView))
    {
      this.mScaleDownToStartAnimation = new Animation()
      {
        public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
        {
          float f = SwipeRefreshLayout.this.mStartingScale + paramAnonymousFloat * -SwipeRefreshLayout.this.mStartingScale;
          SwipeRefreshLayout.this.setAnimationProgress(f);
          SwipeRefreshLayout.this.moveToStart(paramAnonymousFloat);
        }
      };
      this.mScaleDownToStartAnimation.setDuration(150L);
      if (paramAnimationListener != null) {
        this.mCircleView.setAnimationListener(paramAnimationListener);
      }
      this.mCircleView.clearAnimation();
      this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
      return;
    }
  }
  
  private void startScaleUpAnimation(Animation.AnimationListener paramAnimationListener)
  {
    this.mCircleView.setVisibility(0);
    if (Build.VERSION.SDK_INT >= 11) {
      this.mProgress.setAlpha(255);
    }
    this.mScaleAnimation = new Animation()
    {
      public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        SwipeRefreshLayout.this.setAnimationProgress(paramAnonymousFloat);
      }
    };
    this.mScaleAnimation.setDuration(this.mMediumAnimationDuration);
    if (paramAnimationListener != null) {
      this.mCircleView.setAnimationListener(paramAnimationListener);
    }
    this.mCircleView.clearAnimation();
    this.mCircleView.startAnimation(this.mScaleAnimation);
  }
  
  public boolean canChildScrollUp()
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (Build.VERSION.SDK_INT < 14) {
      if ((this.mTarget instanceof AbsListView))
      {
        AbsListView localAbsListView = (AbsListView)this.mTarget;
        if ((localAbsListView.getChildCount() <= 0) || ((localAbsListView.getFirstVisiblePosition() <= 0) && (localAbsListView.getChildAt(0).getTop() >= localAbsListView.getPaddingTop()))) {}
      }
    }
    for (;;)
    {
      return bool1;
      bool1 = false;
      continue;
      if ((ViewCompat.canScrollVertically(this.mTarget, -1)) || (this.mTarget.getScrollY() > 0)) {
        bool2 = bool1;
      }
      bool1 = bool2;
      continue;
      bool1 = ViewCompat.canScrollVertically(this.mTarget, -1);
    }
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return this.mNestedScrollingChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return this.mNestedScrollingChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return this.mNestedScrollingChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (this.mCircleViewIndex < 0) {}
    for (;;)
    {
      return paramInt2;
      if (paramInt2 == paramInt1 - 1) {
        paramInt2 = this.mCircleViewIndex;
      } else if (paramInt2 >= this.mCircleViewIndex) {
        paramInt2++;
      }
    }
  }
  
  public int getNestedScrollAxes()
  {
    return this.mNestedScrollingParentHelper.getNestedScrollAxes();
  }
  
  public int getProgressCircleDiameter()
  {
    if (this.mCircleView != null) {}
    for (int i = this.mCircleView.getMeasuredHeight();; i = 0) {
      return i;
    }
  }
  
  public boolean hasNestedScrollingParent()
  {
    return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
  }
  
  public boolean isRefreshing()
  {
    return this.mRefreshing;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    ensureTarget();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((this.mReturningToStart) && (i == 0)) {
      this.mReturningToStart = false;
    }
    if ((!isEnabled()) || (this.mReturningToStart) || (canChildScrollUp()) || (this.mRefreshing)) {
      return bool;
    }
    switch (i)
    {
    }
    for (;;)
    {
      bool = this.mIsBeingDragged;
      break;
      setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop(), true);
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      this.mIsBeingDragged = false;
      float f2 = getMotionEventY(paramMotionEvent, this.mActivePointerId);
      if (f2 == -1.0F) {
        break;
      }
      this.mInitialDownY = f2;
      continue;
      if (this.mActivePointerId == -1)
      {
        Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
        break;
      }
      float f1 = getMotionEventY(paramMotionEvent, this.mActivePointerId);
      if (f1 == -1.0F) {
        break;
      }
      if ((f1 - this.mInitialDownY > this.mTouchSlop) && (!this.mIsBeingDragged))
      {
        this.mInitialMotionY = (this.mInitialDownY + this.mTouchSlop);
        this.mIsBeingDragged = true;
        this.mProgress.setAlpha(76);
        continue;
        onSecondaryPointerUp(paramMotionEvent);
        continue;
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getMeasuredWidth();
    int j = getMeasuredHeight();
    if (getChildCount() == 0) {}
    for (;;)
    {
      return;
      if (this.mTarget == null) {
        ensureTarget();
      }
      if (this.mTarget != null)
      {
        View localView = this.mTarget;
        int k = getPaddingLeft();
        int m = getPaddingTop();
        int n = i - getPaddingLeft() - getPaddingRight();
        int i1 = j - getPaddingTop() - getPaddingBottom();
        localView.layout(k, m, k + n, m + i1);
        int i2 = this.mCircleView.getMeasuredWidth();
        int i3 = this.mCircleView.getMeasuredHeight();
        this.mCircleView.layout(i / 2 - i2 / 2, this.mCurrentTargetOffsetTop, i / 2 + i2 / 2, i3 + this.mCurrentTargetOffsetTop);
      }
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mTarget == null) {
      ensureTarget();
    }
    if (this.mTarget == null) {}
    label180:
    for (;;)
    {
      return;
      this.mTarget.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 1073741824));
      this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleHeight, 1073741824));
      if ((!this.mUsingCustomStart) && (!this.mOriginalOffsetCalculated))
      {
        this.mOriginalOffsetCalculated = true;
        int j = -this.mCircleView.getMeasuredHeight();
        this.mOriginalOffsetTop = j;
        this.mCurrentTargetOffsetTop = j;
      }
      this.mCircleViewIndex = -1;
      for (int i = 0;; i++)
      {
        if (i >= getChildCount()) {
          break label180;
        }
        if (getChildAt(i) == this.mCircleView)
        {
          this.mCircleViewIndex = i;
          break;
        }
      }
    }
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return false;
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    return false;
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    if ((paramInt2 > 0) && (this.mTotalUnconsumed > 0.0F))
    {
      if (paramInt2 <= this.mTotalUnconsumed) {
        break label102;
      }
      paramArrayOfInt[1] = (paramInt2 - (int)this.mTotalUnconsumed);
      this.mTotalUnconsumed = 0.0F;
    }
    for (;;)
    {
      moveSpinner(this.mTotalUnconsumed);
      int[] arrayOfInt = this.mParentScrollConsumed;
      if (dispatchNestedPreScroll(paramInt1 - paramArrayOfInt[0], paramInt2 - paramArrayOfInt[1], arrayOfInt, null))
      {
        paramArrayOfInt[0] += arrayOfInt[0];
        paramArrayOfInt[1] += arrayOfInt[1];
      }
      return;
      label102:
      this.mTotalUnconsumed -= paramInt2;
      paramArrayOfInt[1] = paramInt2;
    }
  }
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt4 < 0)
    {
      int i = Math.abs(paramInt4);
      this.mTotalUnconsumed += i;
      moveSpinner(this.mTotalUnconsumed);
    }
    dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt1, null);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    this.mNestedScrollingParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt);
    this.mTotalUnconsumed = 0.0F;
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    if ((isEnabled()) && ((paramInt & 0x2) != 0)) {
      startNestedScroll(paramInt & 0x2);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onStopNestedScroll(View paramView)
  {
    this.mNestedScrollingParentHelper.onStopNestedScroll(paramView);
    if (this.mTotalUnconsumed > 0.0F)
    {
      finishSpinner(this.mTotalUnconsumed);
      this.mTotalUnconsumed = 0.0F;
    }
    stopNestedScroll();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((this.mReturningToStart) && (i == 0)) {
      this.mReturningToStart = false;
    }
    if ((!isEnabled()) || (this.mReturningToStart) || (canChildScrollUp())) {}
    for (;;)
    {
      return bool;
      switch (i)
      {
      case 4: 
      default: 
      case 0: 
      case 2: 
      case 5: 
      case 6: 
        for (;;)
        {
          bool = true;
          break;
          this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
          this.mIsBeingDragged = false;
          continue;
          int j = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
          if (j < 0)
          {
            Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
            break;
          }
          float f2 = 0.5F * (MotionEventCompat.getY(paramMotionEvent, j) - this.mInitialMotionY);
          if (this.mIsBeingDragged)
          {
            if (f2 <= 0.0F) {
              break;
            }
            moveSpinner(f2);
            continue;
            this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, MotionEventCompat.getActionIndex(paramMotionEvent));
            continue;
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
      }
      if (this.mActivePointerId == -1)
      {
        if (i == 1) {
          Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
        }
      }
      else
      {
        float f1 = 0.5F * (MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId)) - this.mInitialMotionY);
        this.mIsBeingDragged = false;
        finishSpinner(f1);
        this.mActivePointerId = -1;
      }
    }
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    if (((Build.VERSION.SDK_INT < 21) && ((this.mTarget instanceof AbsListView))) || ((this.mTarget != null) && (!ViewCompat.isNestedScrollingEnabled(this.mTarget)))) {}
    for (;;)
    {
      return;
      super.requestDisallowInterceptTouchEvent(paramBoolean);
    }
  }
  
  @Deprecated
  public void setColorScheme(@ColorInt int... paramVarArgs)
  {
    setColorSchemeResources(paramVarArgs);
  }
  
  @ColorInt
  public void setColorSchemeColors(int... paramVarArgs)
  {
    ensureTarget();
    this.mProgress.setColorSchemeColors(paramVarArgs);
  }
  
  public void setColorSchemeResources(@ColorRes int... paramVarArgs)
  {
    Resources localResources = getResources();
    int[] arrayOfInt = new int[paramVarArgs.length];
    for (int i = 0; i < paramVarArgs.length; i++) {
      arrayOfInt[i] = localResources.getColor(paramVarArgs[i]);
    }
    setColorSchemeColors(arrayOfInt);
  }
  
  public void setDistanceToTriggerSync(int paramInt)
  {
    this.mTotalDragDistance = paramInt;
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    this.mNestedScrollingChildHelper.setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.mListener = paramOnRefreshListener;
  }
  
  @Deprecated
  public void setProgressBackgroundColor(int paramInt)
  {
    setProgressBackgroundColorSchemeResource(paramInt);
  }
  
  public void setProgressBackgroundColorSchemeColor(@ColorInt int paramInt)
  {
    this.mCircleView.setBackgroundColor(paramInt);
    this.mProgress.setBackgroundColor(paramInt);
  }
  
  public void setProgressBackgroundColorSchemeResource(@ColorRes int paramInt)
  {
    setProgressBackgroundColorSchemeColor(getResources().getColor(paramInt));
  }
  
  public void setProgressViewEndTarget(boolean paramBoolean, int paramInt)
  {
    this.mSpinnerFinalOffset = paramInt;
    this.mScale = paramBoolean;
    this.mCircleView.invalidate();
  }
  
  public void setProgressViewOffset(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    this.mScale = paramBoolean;
    this.mCircleView.setVisibility(8);
    this.mCurrentTargetOffsetTop = paramInt1;
    this.mOriginalOffsetTop = paramInt1;
    this.mSpinnerFinalOffset = paramInt2;
    this.mUsingCustomStart = true;
    this.mCircleView.invalidate();
  }
  
  public void setRefreshing(boolean paramBoolean)
  {
    int i;
    if ((paramBoolean) && (this.mRefreshing != paramBoolean))
    {
      this.mRefreshing = paramBoolean;
      if (!this.mUsingCustomStart)
      {
        i = (int)(this.mSpinnerFinalOffset + this.mOriginalOffsetTop);
        setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
        this.mNotify = false;
        startScaleUpAnimation(this.mRefreshListener);
      }
    }
    for (;;)
    {
      return;
      i = (int)this.mSpinnerFinalOffset;
      break;
      setRefreshing(paramBoolean, false);
    }
  }
  
  public void setSize(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1)) {
      return;
    }
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    int j;
    if (paramInt == 0)
    {
      j = (int)(56.0F * localDisplayMetrics.density);
      this.mCircleWidth = j;
    }
    int i;
    for (this.mCircleHeight = j;; this.mCircleHeight = i)
    {
      this.mCircleView.setImageDrawable(null);
      this.mProgress.updateSizes(paramInt);
      this.mCircleView.setImageDrawable(this.mProgress);
      break;
      i = (int)(40.0F * localDisplayMetrics.density);
      this.mCircleWidth = i;
    }
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return this.mNestedScrollingChildHelper.startNestedScroll(paramInt);
  }
  
  public void stopNestedScroll()
  {
    this.mNestedScrollingChildHelper.stopNestedScroll();
  }
  
  public static abstract interface OnRefreshListener
  {
    public abstract void onRefresh();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/widget/SwipeRefreshLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */