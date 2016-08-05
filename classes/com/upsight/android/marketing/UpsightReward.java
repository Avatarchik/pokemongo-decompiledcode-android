package com.upsight.android.marketing;

import org.json.JSONObject;

public abstract interface UpsightReward
{
  public abstract String getProduct();
  
  public abstract int getQuantity();
  
  public abstract JSONObject getSignatureData();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightReward.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */