package android.support.v4.view;

import android.view.LayoutInflater;

class LayoutInflaterCompatLollipop
{
  static void setFactory(LayoutInflater paramLayoutInflater, LayoutInflaterFactory paramLayoutInflaterFactory)
  {
    if (paramLayoutInflaterFactory != null) {}
    for (LayoutInflaterCompatHC.FactoryWrapperHC localFactoryWrapperHC = new LayoutInflaterCompatHC.FactoryWrapperHC(paramLayoutInflaterFactory);; localFactoryWrapperHC = null)
    {
      paramLayoutInflater.setFactory2(localFactoryWrapperHC);
      return;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/LayoutInflaterCompatLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */