package com.upsight.android.unity;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import com.upsight.android.marketing.UpsightBillboard.Dimensions;
import com.upsight.android.marketing.UpsightBillboard.PresentationStyle;
import com.upsight.android.marketing.UpsightBillboardHandlers.DefaultHandler;
import com.upsight.android.marketing.UpsightPurchase;
import com.upsight.android.marketing.UpsightReward;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

public class BillboardHandler
  extends UpsightBillboardHandlers.DefaultHandler
{
  protected static final String TAG = "UpsightBillboardHandler";
  private String mCurrentScope;
  private UpsightPlugin mPlugin;
  
  public BillboardHandler(Activity paramActivity, UpsightPlugin paramUpsightPlugin)
  {
    super(paramActivity);
    this.mPlugin = paramUpsightPlugin;
  }
  
  public ViewGroup onAttach(String paramString, UpsightBillboard.PresentationStyle paramPresentationStyle, Set<UpsightBillboard.Dimensions> paramSet)
  {
    this.mCurrentScope = paramString;
    ViewGroup localViewGroup = super.onAttach(paramString, paramPresentationStyle, paramSet);
    if (localViewGroup != null)
    {
      this.mPlugin.setHasActiveBillboard(true);
      this.mPlugin.UnitySendMessage("onBillboardAppear", paramString);
    }
    return localViewGroup;
  }
  
  public void onDetach()
  {
    super.onDetach();
    Log.i("UpsightBillboardHandler", "onDetach");
    this.mPlugin.UnitySendMessage("onBillboardDismiss", this.mCurrentScope);
    this.mPlugin.removeBillboardFromMap(this.mCurrentScope);
    this.mPlugin.setHasActiveBillboard(false);
  }
  
  public void onNextView()
  {
    super.onNextView();
    Log.i("UpsightBillboardHandler", "onNextView");
  }
  
  public void onPurchases(List<UpsightPurchase> paramList)
  {
    super.onPurchases(paramList);
    Log.i("UpsightBillboardHandler", "onPurchases");
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      UpsightPurchase localUpsightPurchase = (UpsightPurchase)localIterator.next();
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("productIdentifier", localUpsightPurchase.getProduct());
        localJSONObject.put("quantity", localUpsightPurchase.getQuantity());
        localJSONObject.put("billboardScope", this.mCurrentScope);
        this.mPlugin.UnitySendMessage("billboardDidReceivePurchase", localJSONObject.toString());
      }
      catch (Exception localException)
      {
        Log.i("UpsightBillboardHandler", "Error creating JSON" + localException.getMessage());
      }
    }
  }
  
  public void onRewards(List<UpsightReward> paramList)
  {
    super.onRewards(paramList);
    Log.i("UpsightBillboardHandler", "onRewards");
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      UpsightReward localUpsightReward = (UpsightReward)localIterator.next();
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("productIdentifier", localUpsightReward.getProduct());
        localJSONObject.put("quantity", localUpsightReward.getQuantity());
        localJSONObject.put("signatureData", localUpsightReward.getSignatureData());
        localJSONObject.put("billboardScope", this.mCurrentScope);
        this.mPlugin.UnitySendMessage("billboardDidReceiveReward", localJSONObject.toString());
      }
      catch (Exception localException)
      {
        Log.i("UpsightBillboardHandler", "Error creating JSON" + localException.getMessage());
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/unity/BillboardHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */