package com.upsight.android.marketing;

import com.upsight.android.marketing.internal.billboard.Billboard;

public abstract interface UpsightBillboardManager
{
  public abstract boolean registerBillboard(Billboard paramBillboard);
  
  public abstract boolean registerContentMediator(UpsightContentMediator paramUpsightContentMediator);
  
  public abstract boolean unregisterBillboard(Billboard paramBillboard);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightBillboardManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */