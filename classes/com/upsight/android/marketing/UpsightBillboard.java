package com.upsight.android.marketing;

import android.text.TextUtils;
import android.view.ViewGroup;
import com.upsight.android.UpsightContext;
import com.upsight.android.marketing.internal.billboard.Billboard;
import java.util.List;
import java.util.Set;

public abstract class UpsightBillboard
{
  public static UpsightBillboard create(UpsightContext paramUpsightContext, String paramString, Handler paramHandler)
    throws IllegalArgumentException, IllegalStateException
  {
    if ((TextUtils.isEmpty(paramString)) && (paramHandler != null)) {
      throw new IllegalArgumentException("The billboard scope and handler must be non-null and non-empty.");
    }
    Billboard localBillboard = new Billboard(paramString, paramHandler);
    localBillboard.setUp(paramUpsightContext);
    return localBillboard;
  }
  
  public abstract void destroy();
  
  protected abstract UpsightBillboard setUp(UpsightContext paramUpsightContext)
    throws IllegalStateException;
  
  public static abstract interface Handler
  {
    public abstract ViewGroup onAttach(String paramString, UpsightBillboard.PresentationStyle paramPresentationStyle, Set<UpsightBillboard.Dimensions> paramSet);
    
    public abstract void onDetach();
    
    public abstract void onNextView();
    
    public abstract void onPurchases(List<UpsightPurchase> paramList);
    
    public abstract void onRewards(List<UpsightReward> paramList);
  }
  
  public static class Dimensions
  {
    public final int height;
    public final LayoutOrientation layout;
    public final int width;
    
    public Dimensions(LayoutOrientation paramLayoutOrientation, int paramInt1, int paramInt2)
    {
      this.layout = paramLayoutOrientation;
      this.width = paramInt1;
      this.height = paramInt2;
    }
    
    public static enum LayoutOrientation
    {
      static
      {
        Landscape = new LayoutOrientation("Landscape", 1);
        LayoutOrientation[] arrayOfLayoutOrientation = new LayoutOrientation[2];
        arrayOfLayoutOrientation[0] = Portrait;
        arrayOfLayoutOrientation[1] = Landscape;
        $VALUES = arrayOfLayoutOrientation;
      }
      
      private LayoutOrientation() {}
    }
  }
  
  public static enum PresentationStyle
  {
    static
    {
      Dialog = new PresentationStyle("Dialog", 1);
      Fullscreen = new PresentationStyle("Fullscreen", 2);
      PresentationStyle[] arrayOfPresentationStyle = new PresentationStyle[3];
      arrayOfPresentationStyle[0] = None;
      arrayOfPresentationStyle[1] = Dialog;
      arrayOfPresentationStyle[2] = Fullscreen;
      $VALUES = arrayOfPresentationStyle;
    }
    
    private PresentationStyle() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightBillboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */