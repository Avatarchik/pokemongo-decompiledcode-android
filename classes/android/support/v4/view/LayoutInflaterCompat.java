package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.LayoutInflater;

public class LayoutInflaterCompat
{
  static final LayoutInflaterCompatImpl IMPL;
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21) {
      IMPL = new LayoutInflaterCompatImplV21();
    }
    for (;;)
    {
      return;
      if (i >= 11) {
        IMPL = new LayoutInflaterCompatImplV11();
      } else {
        IMPL = new LayoutInflaterCompatImplBase();
      }
    }
  }
  
  public static void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
  {
    IMPL.setFactory(paramLayoutInflater, paramLayoutInflaterFactory);
  }
  
  static class LayoutInflaterCompatImplV21
    extends LayoutInflaterCompat.LayoutInflaterCompatImplV11
  {
    public void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
    {
      LayoutInflaterCompatLollipop.setFactory(paramLayoutInflater, paramLayoutInflaterFactory);
    }
  }
  
  static class LayoutInflaterCompatImplV11
    extends LayoutInflaterCompat.LayoutInflaterCompatImplBase
  {
    public void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
    {
      LayoutInflaterCompatHC.setFactory(paramLayoutInflater, paramLayoutInflaterFactory);
    }
  }
  
  static class LayoutInflaterCompatImplBase
    implements LayoutInflaterCompat.LayoutInflaterCompatImpl
  {
    public void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
    {
      LayoutInflaterCompatBase.setFactory(paramLayoutInflater, paramLayoutInflaterFactory);
    }
  }
  
  static abstract interface LayoutInflaterCompatImpl
  {
    public abstract void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/LayoutInflaterCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */