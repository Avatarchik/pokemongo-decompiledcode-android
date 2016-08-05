package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ExploreByTouchHelper
  extends AccessibilityDelegateCompat
{
  private static final String DEFAULT_CLASS_NAME = View.class.getName();
  public static final int HOST_ID = -1;
  public static final int INVALID_ID = Integer.MIN_VALUE;
  private int mFocusedVirtualViewId = Integer.MIN_VALUE;
  private int mHoveredVirtualViewId = Integer.MIN_VALUE;
  private final AccessibilityManager mManager;
  private ExploreByTouchNodeProvider mNodeProvider;
  private final int[] mTempGlobalRect = new int[2];
  private final Rect mTempParentRect = new Rect();
  private final Rect mTempScreenRect = new Rect();
  private final Rect mTempVisibleRect = new Rect();
  private final View mView;
  
  public ExploreByTouchHelper(View paramView)
  {
    if (paramView == null) {
      throw new IllegalArgumentException("View may not be null");
    }
    this.mView = paramView;
    this.mManager = ((AccessibilityManager)paramView.getContext().getSystemService("accessibility"));
  }
  
  private boolean clearAccessibilityFocus(int paramInt)
  {
    if (isAccessibilityFocused(paramInt))
    {
      this.mFocusedVirtualViewId = Integer.MIN_VALUE;
      this.mView.invalidate();
      sendEventForVirtualView(paramInt, 65536);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private AccessibilityEvent createEvent(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    }
    for (AccessibilityEvent localAccessibilityEvent = createEventForChild(paramInt1, paramInt2);; localAccessibilityEvent = createEventForHost(paramInt2)) {
      return localAccessibilityEvent;
    }
  }
  
  private AccessibilityEvent createEventForChild(int paramInt1, int paramInt2)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt2);
    localAccessibilityEvent.setEnabled(true);
    localAccessibilityEvent.setClassName(DEFAULT_CLASS_NAME);
    onPopulateEventForVirtualView(paramInt1, localAccessibilityEvent);
    if ((localAccessibilityEvent.getText().isEmpty()) && (localAccessibilityEvent.getContentDescription() == null)) {
      throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
    }
    localAccessibilityEvent.setPackageName(this.mView.getContext().getPackageName());
    AccessibilityEventCompat.asRecord(localAccessibilityEvent).setSource(this.mView, paramInt1);
    return localAccessibilityEvent;
  }
  
  private AccessibilityEvent createEventForHost(int paramInt)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt);
    ViewCompat.onInitializeAccessibilityEvent(this.mView, localAccessibilityEvent);
    return localAccessibilityEvent;
  }
  
  private AccessibilityNodeInfoCompat createNode(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = createNodeForChild(paramInt);; localAccessibilityNodeInfoCompat = createNodeForHost()) {
      return localAccessibilityNodeInfoCompat;
    }
  }
  
  private AccessibilityNodeInfoCompat createNodeForChild(int paramInt)
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain();
    localAccessibilityNodeInfoCompat.setEnabled(true);
    localAccessibilityNodeInfoCompat.setClassName(DEFAULT_CLASS_NAME);
    onPopulateNodeForVirtualView(paramInt, localAccessibilityNodeInfoCompat);
    if ((localAccessibilityNodeInfoCompat.getText() == null) && (localAccessibilityNodeInfoCompat.getContentDescription() == null)) {
      throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
    }
    localAccessibilityNodeInfoCompat.getBoundsInParent(this.mTempParentRect);
    if (this.mTempParentRect.isEmpty()) {
      throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    }
    int i = localAccessibilityNodeInfoCompat.getActions();
    if ((i & 0x40) != 0) {
      throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    }
    if ((i & 0x80) != 0) {
      throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    }
    localAccessibilityNodeInfoCompat.setPackageName(this.mView.getContext().getPackageName());
    localAccessibilityNodeInfoCompat.setSource(this.mView, paramInt);
    localAccessibilityNodeInfoCompat.setParent(this.mView);
    if (this.mFocusedVirtualViewId == paramInt)
    {
      localAccessibilityNodeInfoCompat.setAccessibilityFocused(true);
      localAccessibilityNodeInfoCompat.addAction(128);
    }
    for (;;)
    {
      if (intersectVisibleToUser(this.mTempParentRect))
      {
        localAccessibilityNodeInfoCompat.setVisibleToUser(true);
        localAccessibilityNodeInfoCompat.setBoundsInParent(this.mTempParentRect);
      }
      this.mView.getLocationOnScreen(this.mTempGlobalRect);
      int j = this.mTempGlobalRect[0];
      int k = this.mTempGlobalRect[1];
      this.mTempScreenRect.set(this.mTempParentRect);
      this.mTempScreenRect.offset(j, k);
      localAccessibilityNodeInfoCompat.setBoundsInScreen(this.mTempScreenRect);
      return localAccessibilityNodeInfoCompat;
      localAccessibilityNodeInfoCompat.setAccessibilityFocused(false);
      localAccessibilityNodeInfoCompat.addAction(64);
    }
  }
  
  private AccessibilityNodeInfoCompat createNodeForHost()
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(this.mView);
    ViewCompat.onInitializeAccessibilityNodeInfo(this.mView, localAccessibilityNodeInfoCompat);
    onPopulateNodeForHost(localAccessibilityNodeInfoCompat);
    LinkedList localLinkedList = new LinkedList();
    getVisibleVirtualViews(localLinkedList);
    Iterator localIterator = localLinkedList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      localAccessibilityNodeInfoCompat.addChild(this.mView, localInteger.intValue());
    }
    return localAccessibilityNodeInfoCompat;
  }
  
  private boolean intersectVisibleToUser(Rect paramRect)
  {
    boolean bool = false;
    if ((paramRect == null) || (paramRect.isEmpty())) {}
    for (;;)
    {
      return bool;
      if (this.mView.getWindowVisibility() == 0)
      {
        View localView;
        for (ViewParent localViewParent = this.mView.getParent();; localViewParent = localView.getParent())
        {
          if (!(localViewParent instanceof View)) {
            break label73;
          }
          localView = (View)localViewParent;
          if ((ViewCompat.getAlpha(localView) <= 0.0F) || (localView.getVisibility() != 0)) {
            break;
          }
        }
        label73:
        if ((localViewParent != null) && (this.mView.getLocalVisibleRect(this.mTempVisibleRect))) {
          bool = paramRect.intersect(this.mTempVisibleRect);
        }
      }
    }
  }
  
  private boolean isAccessibilityFocused(int paramInt)
  {
    if (this.mFocusedVirtualViewId == paramInt) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean manageFocusForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    boolean bool;
    switch (paramInt2)
    {
    default: 
      bool = false;
    }
    for (;;)
    {
      return bool;
      bool = requestAccessibilityFocus(paramInt1);
      continue;
      bool = clearAccessibilityFocus(paramInt1);
    }
  }
  
  private boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    switch (paramInt1)
    {
    }
    for (boolean bool = performActionForChild(paramInt1, paramInt2, paramBundle);; bool = performActionForHost(paramInt2, paramBundle)) {
      return bool;
    }
  }
  
  private boolean performActionForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    switch (paramInt2)
    {
    }
    for (boolean bool = onPerformActionForVirtualView(paramInt1, paramInt2, paramBundle);; bool = manageFocusForChild(paramInt1, paramInt2, paramBundle)) {
      return bool;
    }
  }
  
  private boolean performActionForHost(int paramInt, Bundle paramBundle)
  {
    return ViewCompat.performAccessibilityAction(this.mView, paramInt, paramBundle);
  }
  
  private boolean requestAccessibilityFocus(int paramInt)
  {
    boolean bool = false;
    if ((!this.mManager.isEnabled()) || (!AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager))) {}
    for (;;)
    {
      return bool;
      if (!isAccessibilityFocused(paramInt))
      {
        if (this.mFocusedVirtualViewId != Integer.MIN_VALUE) {
          sendEventForVirtualView(this.mFocusedVirtualViewId, 65536);
        }
        this.mFocusedVirtualViewId = paramInt;
        this.mView.invalidate();
        sendEventForVirtualView(paramInt, 32768);
        bool = true;
      }
    }
  }
  
  private void updateHoveredVirtualView(int paramInt)
  {
    if (this.mHoveredVirtualViewId == paramInt) {}
    for (;;)
    {
      return;
      int i = this.mHoveredVirtualViewId;
      this.mHoveredVirtualViewId = paramInt;
      sendEventForVirtualView(paramInt, 128);
      sendEventForVirtualView(i, 256);
    }
  }
  
  public boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if ((!this.mManager.isEnabled()) || (!AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager))) {}
    for (;;)
    {
      return bool2;
      switch (paramMotionEvent.getAction())
      {
      case 8: 
      default: 
        break;
      case 7: 
      case 9: 
        int i = getVirtualViewAt(paramMotionEvent.getX(), paramMotionEvent.getY());
        updateHoveredVirtualView(i);
        if (i != Integer.MIN_VALUE) {}
        for (;;)
        {
          bool2 = bool1;
          break;
          bool1 = false;
        }
      case 10: 
        if (this.mFocusedVirtualViewId != Integer.MIN_VALUE)
        {
          updateHoveredVirtualView(Integer.MIN_VALUE);
          bool2 = bool1;
        }
        break;
      }
    }
  }
  
  public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
  {
    if (this.mNodeProvider == null) {
      this.mNodeProvider = new ExploreByTouchNodeProvider(null);
    }
    return this.mNodeProvider;
  }
  
  public int getFocusedVirtualView()
  {
    return this.mFocusedVirtualViewId;
  }
  
  protected abstract int getVirtualViewAt(float paramFloat1, float paramFloat2);
  
  protected abstract void getVisibleVirtualViews(List<Integer> paramList);
  
  public void invalidateRoot()
  {
    invalidateVirtualView(-1);
  }
  
  public void invalidateVirtualView(int paramInt)
  {
    sendEventForVirtualView(paramInt, 2048);
  }
  
  protected abstract boolean onPerformActionForVirtualView(int paramInt1, int paramInt2, Bundle paramBundle);
  
  protected abstract void onPopulateEventForVirtualView(int paramInt, AccessibilityEvent paramAccessibilityEvent);
  
  public void onPopulateNodeForHost(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {}
  
  protected abstract void onPopulateNodeForVirtualView(int paramInt, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);
  
  public boolean sendEventForVirtualView(int paramInt1, int paramInt2)
  {
    boolean bool = false;
    if ((paramInt1 == Integer.MIN_VALUE) || (!this.mManager.isEnabled())) {}
    for (;;)
    {
      return bool;
      ViewParent localViewParent = this.mView.getParent();
      if (localViewParent != null)
      {
        AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, paramInt2);
        bool = ViewParentCompat.requestSendAccessibilityEvent(localViewParent, this.mView, localAccessibilityEvent);
      }
    }
  }
  
  private class ExploreByTouchNodeProvider
    extends AccessibilityNodeProviderCompat
  {
    private ExploreByTouchNodeProvider() {}
    
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
    {
      return ExploreByTouchHelper.this.createNode(paramInt);
    }
    
    public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      return ExploreByTouchHelper.this.performAction(paramInt1, paramInt2, paramBundle);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/widget/ExploreByTouchHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */