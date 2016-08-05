package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class FragmentTransitionCompat21
{
  public static void addTargets(Object paramObject, ArrayList<View> paramArrayList)
  {
    Transition localTransition = (Transition)paramObject;
    if ((localTransition instanceof TransitionSet))
    {
      TransitionSet localTransitionSet = (TransitionSet)localTransition;
      int k = localTransitionSet.getTransitionCount();
      for (int m = 0; m < k; m++) {
        addTargets(localTransitionSet.getTransitionAt(m), paramArrayList);
      }
    }
    if ((!hasSimpleTarget(localTransition)) && (isNullOrEmpty(localTransition.getTargets())))
    {
      int i = paramArrayList.size();
      for (int j = 0; j < i; j++) {
        localTransition.addTarget((View)paramArrayList.get(j));
      }
    }
  }
  
  public static void addTransitionTargets(Object paramObject1, Object paramObject2, View paramView1, final ViewRetriever paramViewRetriever, final View paramView2, EpicenterView paramEpicenterView, final Map<String, String> paramMap, final ArrayList<View> paramArrayList1, Map<String, View> paramMap1, final Map<String, View> paramMap2, ArrayList<View> paramArrayList2)
  {
    if ((paramObject1 != null) || (paramObject2 != null))
    {
      final Transition localTransition = (Transition)paramObject1;
      if (localTransition != null) {
        localTransition.addTarget(paramView2);
      }
      if (paramObject2 != null) {
        setSharedElementTargets(paramObject2, paramView2, paramMap1, paramArrayList2);
      }
      if (paramViewRetriever != null) {
        paramView1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
        {
          public boolean onPreDraw()
          {
            FragmentTransitionCompat21.this.getViewTreeObserver().removeOnPreDrawListener(this);
            if (localTransition != null) {
              localTransition.removeTarget(paramView2);
            }
            View localView1 = paramViewRetriever.getView();
            if (localView1 != null)
            {
              if (!paramMap.isEmpty())
              {
                FragmentTransitionCompat21.findNamedViews(paramMap2, localView1);
                paramMap2.keySet().retainAll(paramMap.values());
                Iterator localIterator = paramMap.entrySet().iterator();
                while (localIterator.hasNext())
                {
                  Map.Entry localEntry = (Map.Entry)localIterator.next();
                  String str = (String)localEntry.getValue();
                  View localView2 = (View)paramMap2.get(str);
                  if (localView2 != null) {
                    localView2.setTransitionName((String)localEntry.getKey());
                  }
                }
              }
              if (localTransition != null)
              {
                FragmentTransitionCompat21.captureTransitioningViews(paramArrayList1, localView1);
                paramArrayList1.removeAll(paramMap2.values());
                paramArrayList1.add(paramView2);
                FragmentTransitionCompat21.addTargets(localTransition, paramArrayList1);
              }
            }
            return true;
          }
        });
      }
      setSharedElementEpicenter(localTransition, paramEpicenterView);
    }
  }
  
  public static void beginDelayedTransition(ViewGroup paramViewGroup, Object paramObject)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup, (Transition)paramObject);
  }
  
  private static void bfsAddViewChildren(List<View> paramList, View paramView)
  {
    int i = paramList.size();
    if (containedBeforeIndex(paramList, paramView, i)) {}
    for (;;)
    {
      return;
      paramList.add(paramView);
      for (int j = i; j < paramList.size(); j++)
      {
        View localView1 = (View)paramList.get(j);
        if ((localView1 instanceof ViewGroup))
        {
          ViewGroup localViewGroup = (ViewGroup)localView1;
          int k = localViewGroup.getChildCount();
          for (int m = 0; m < k; m++)
          {
            View localView2 = localViewGroup.getChildAt(m);
            if (!containedBeforeIndex(paramList, localView2, i)) {
              paramList.add(localView2);
            }
          }
        }
      }
    }
  }
  
  public static Object captureExitingViews(Object paramObject, View paramView1, ArrayList<View> paramArrayList, Map<String, View> paramMap, View paramView2)
  {
    if (paramObject != null)
    {
      captureTransitioningViews(paramArrayList, paramView1);
      if (paramMap != null) {
        paramArrayList.removeAll(paramMap.values());
      }
      if (!paramArrayList.isEmpty()) {
        break label35;
      }
      paramObject = null;
    }
    for (;;)
    {
      return paramObject;
      label35:
      paramArrayList.add(paramView2);
      addTargets((Transition)paramObject, paramArrayList);
    }
  }
  
  private static void captureTransitioningViews(ArrayList<View> paramArrayList, View paramView)
  {
    ViewGroup localViewGroup;
    if (paramView.getVisibility() == 0)
    {
      if (!(paramView instanceof ViewGroup)) {
        break label65;
      }
      localViewGroup = (ViewGroup)paramView;
      if (!localViewGroup.isTransitionGroup()) {
        break label33;
      }
      paramArrayList.add(localViewGroup);
    }
    for (;;)
    {
      return;
      label33:
      int i = localViewGroup.getChildCount();
      for (int j = 0; j < i; j++) {
        captureTransitioningViews(paramArrayList, localViewGroup.getChildAt(j));
      }
      continue;
      label65:
      paramArrayList.add(paramView);
    }
  }
  
  public static void cleanupTransitions(View paramView1, final View paramView2, Object paramObject1, final ArrayList<View> paramArrayList1, Object paramObject2, final ArrayList<View> paramArrayList2, Object paramObject3, final ArrayList<View> paramArrayList3, Object paramObject4, final ArrayList<View> paramArrayList4, final Map<String, View> paramMap)
  {
    final Transition localTransition1 = (Transition)paramObject1;
    final Transition localTransition2 = (Transition)paramObject2;
    final Transition localTransition3 = (Transition)paramObject3;
    final Transition localTransition4 = (Transition)paramObject4;
    if (localTransition4 != null) {
      paramView1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
      {
        public boolean onPreDraw()
        {
          FragmentTransitionCompat21.this.getViewTreeObserver().removeOnPreDrawListener(this);
          if (localTransition1 != null) {
            FragmentTransitionCompat21.removeTargets(localTransition1, paramArrayList1);
          }
          if (localTransition2 != null) {
            FragmentTransitionCompat21.removeTargets(localTransition2, paramArrayList2);
          }
          if (localTransition3 != null) {
            FragmentTransitionCompat21.removeTargets(localTransition3, paramArrayList3);
          }
          Iterator localIterator = paramMap.entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            ((View)localEntry.getValue()).setTransitionName((String)localEntry.getKey());
          }
          int i = paramArrayList4.size();
          for (int j = 0; j < i; j++) {
            localTransition4.excludeTarget((View)paramArrayList4.get(j), false);
          }
          localTransition4.excludeTarget(paramView2, false);
          return true;
        }
      });
    }
  }
  
  public static Object cloneTransition(Object paramObject)
  {
    if (paramObject != null) {
      paramObject = ((Transition)paramObject).clone();
    }
    return paramObject;
  }
  
  private static boolean containedBeforeIndex(List<View> paramList, View paramView, int paramInt)
  {
    int i = 0;
    if (i < paramInt) {
      if (paramList.get(i) != paramView) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      i++;
      break;
    }
  }
  
  public static void excludeTarget(Object paramObject, View paramView, boolean paramBoolean)
  {
    ((Transition)paramObject).excludeTarget(paramView, paramBoolean);
  }
  
  public static void findNamedViews(Map<String, View> paramMap, View paramView)
  {
    if (paramView.getVisibility() == 0)
    {
      String str = paramView.getTransitionName();
      if (str != null) {
        paramMap.put(str, paramView);
      }
      if ((paramView instanceof ViewGroup))
      {
        ViewGroup localViewGroup = (ViewGroup)paramView;
        int i = localViewGroup.getChildCount();
        for (int j = 0; j < i; j++) {
          findNamedViews(paramMap, localViewGroup.getChildAt(j));
        }
      }
    }
  }
  
  private static Rect getBoundsOnScreen(View paramView)
  {
    Rect localRect = new Rect();
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    localRect.set(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
    return localRect;
  }
  
  public static String getTransitionName(View paramView)
  {
    return paramView.getTransitionName();
  }
  
  private static boolean hasSimpleTarget(Transition paramTransition)
  {
    if ((!isNullOrEmpty(paramTransition.getTargetIds())) || (!isNullOrEmpty(paramTransition.getTargetNames())) || (!isNullOrEmpty(paramTransition.getTargetTypes()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean isNullOrEmpty(List paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static Object mergeTransitions(Object paramObject1, Object paramObject2, Object paramObject3, boolean paramBoolean)
  {
    boolean bool = true;
    Transition localTransition1 = (Transition)paramObject1;
    Transition localTransition2 = (Transition)paramObject2;
    Transition localTransition3 = (Transition)paramObject3;
    if ((localTransition1 != null) && (localTransition2 != null)) {
      bool = paramBoolean;
    }
    Object localObject1;
    if (bool)
    {
      TransitionSet localTransitionSet1 = new TransitionSet();
      if (localTransition1 != null) {
        localTransitionSet1.addTransition(localTransition1);
      }
      if (localTransition2 != null) {
        localTransitionSet1.addTransition(localTransition2);
      }
      if (localTransition3 != null) {
        localTransitionSet1.addTransition(localTransition3);
      }
      localObject1 = localTransitionSet1;
    }
    for (;;)
    {
      return localObject1;
      Object localObject2 = null;
      if ((localTransition2 != null) && (localTransition1 != null)) {
        localObject2 = new TransitionSet().addTransition(localTransition2).addTransition(localTransition1).setOrdering(1);
      }
      for (;;)
      {
        if (localTransition3 == null) {
          break label196;
        }
        TransitionSet localTransitionSet2 = new TransitionSet();
        if (localObject2 != null) {
          localTransitionSet2.addTransition((Transition)localObject2);
        }
        localTransitionSet2.addTransition(localTransition3);
        localObject1 = localTransitionSet2;
        break;
        if (localTransition2 != null) {
          localObject2 = localTransition2;
        } else if (localTransition1 != null) {
          localObject2 = localTransition1;
        }
      }
      label196:
      localObject1 = localObject2;
    }
  }
  
  public static void removeTargets(Object paramObject, ArrayList<View> paramArrayList)
  {
    Transition localTransition = (Transition)paramObject;
    if ((localTransition instanceof TransitionSet))
    {
      TransitionSet localTransitionSet = (TransitionSet)localTransition;
      int j = localTransitionSet.getTransitionCount();
      for (int k = 0; k < j; k++) {
        removeTargets(localTransitionSet.getTransitionAt(k), paramArrayList);
      }
    }
    if (!hasSimpleTarget(localTransition))
    {
      List localList = localTransition.getTargets();
      if ((localList != null) && (localList.size() == paramArrayList.size()) && (localList.containsAll(paramArrayList))) {
        for (int i = -1 + paramArrayList.size(); i >= 0; i--) {
          localTransition.removeTarget((View)paramArrayList.get(i));
        }
      }
    }
  }
  
  public static void setEpicenter(Object paramObject, View paramView)
  {
    ((Transition)paramObject).setEpicenterCallback(new Transition.EpicenterCallback()
    {
      public Rect onGetEpicenter(Transition paramAnonymousTransition)
      {
        return FragmentTransitionCompat21.this;
      }
    });
  }
  
  private static void setSharedElementEpicenter(Transition paramTransition, EpicenterView paramEpicenterView)
  {
    if (paramTransition != null) {
      paramTransition.setEpicenterCallback(new Transition.EpicenterCallback()
      {
        private Rect mEpicenter;
        
        public Rect onGetEpicenter(Transition paramAnonymousTransition)
        {
          if ((this.mEpicenter == null) && (FragmentTransitionCompat21.this.epicenter != null)) {
            this.mEpicenter = FragmentTransitionCompat21.getBoundsOnScreen(FragmentTransitionCompat21.this.epicenter);
          }
          return this.mEpicenter;
        }
      });
    }
  }
  
  public static void setSharedElementTargets(Object paramObject, View paramView, Map<String, View> paramMap, ArrayList<View> paramArrayList)
  {
    TransitionSet localTransitionSet = (TransitionSet)paramObject;
    paramArrayList.clear();
    paramArrayList.addAll(paramMap.values());
    List localList = localTransitionSet.getTargets();
    localList.clear();
    int i = paramArrayList.size();
    for (int j = 0; j < i; j++) {
      bfsAddViewChildren(localList, (View)paramArrayList.get(j));
    }
    paramArrayList.add(paramView);
    addTargets(localTransitionSet, paramArrayList);
  }
  
  public static Object wrapSharedElementTransition(Object paramObject)
  {
    Object localObject = null;
    if (paramObject == null) {}
    for (;;)
    {
      return localObject;
      Transition localTransition = (Transition)paramObject;
      if (localTransition != null)
      {
        localObject = new TransitionSet();
        ((TransitionSet)localObject).addTransition(localTransition);
      }
    }
  }
  
  public static class EpicenterView
  {
    public View epicenter;
  }
  
  public static abstract interface ViewRetriever
  {
    public abstract View getView();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/app/FragmentTransitionCompat21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */