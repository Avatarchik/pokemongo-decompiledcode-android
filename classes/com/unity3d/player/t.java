package com.unity3d.player;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class t
{
  public static t a;
  private final ViewGroup b;
  private Set c = new HashSet();
  private View d;
  private View e;
  
  t(ViewGroup paramViewGroup)
  {
    this.b = paramViewGroup;
    a = this;
  }
  
  private void e(View paramView)
  {
    this.b.addView(paramView, this.b.getChildCount());
  }
  
  private void f(View paramView)
  {
    this.b.removeView(paramView);
    this.b.requestLayout();
  }
  
  public final Context a()
  {
    return this.b.getContext();
  }
  
  public final void a(View paramView)
  {
    this.c.add(paramView);
    if (this.d != null) {
      e(paramView);
    }
  }
  
  public final void b(View paramView)
  {
    this.c.remove(paramView);
    if (this.d != null) {
      f(paramView);
    }
  }
  
  public final void c(View paramView)
  {
    if (this.d != paramView)
    {
      this.d = paramView;
      this.b.addView(paramView);
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext()) {
        e((View)localIterator.next());
      }
      if (this.e != null) {
        this.e.setVisibility(4);
      }
    }
  }
  
  public final void d(View paramView)
  {
    if (this.d == paramView)
    {
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext()) {
        f((View)localIterator.next());
      }
      this.b.removeView(paramView);
      this.d = null;
      if (this.e != null) {
        this.e.setVisibility(0);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */