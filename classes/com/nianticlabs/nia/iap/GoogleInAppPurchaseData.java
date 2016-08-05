package com.nianticlabs.nia.iap;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

final class GoogleInAppPurchaseData
{
  private static final String TAG = "GoogleInAppPurchaseData";
  private String developerPayload;
  private String orderId;
  private String packageName;
  private String productId;
  private String purchaseState;
  private long purchaseTime;
  
  static GoogleInAppPurchaseData fromJson(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      localGoogleInAppPurchaseData = new GoogleInAppPurchaseData();
      localGoogleInAppPurchaseData.orderId = stringFromJson(localJSONObject, "orderId");
      localGoogleInAppPurchaseData.packageName = stringFromJson(localJSONObject, "packageName");
      localGoogleInAppPurchaseData.productId = stringFromJson(localJSONObject, "productId");
      localGoogleInAppPurchaseData.purchaseTime = longFromJson(localJSONObject, "purchaseTime");
      localGoogleInAppPurchaseData.purchaseState = stringFromJson(localJSONObject, "purchaseState");
      localGoogleInAppPurchaseData.developerPayload = stringFromJson(localJSONObject, "developerPayload");
      return localGoogleInAppPurchaseData;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.e("GoogleInAppPurchaseData", "Failed to parse GoogleInAppPurchaseData: %s", localJSONException);
        GoogleInAppPurchaseData localGoogleInAppPurchaseData = null;
      }
    }
  }
  
  private static long longFromJson(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      long l2 = paramJSONObject.getLong(paramString);
      l1 = l2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        long l1 = 0L;
      }
    }
    return l1;
  }
  
  private static String stringFromJson(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      String str2 = paramJSONObject.getString(paramString);
      str1 = str2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        String str1 = "";
      }
    }
    return str1;
  }
  
  String getDeveloperPayload()
  {
    return this.developerPayload;
  }
  
  String getOrderId()
  {
    return this.orderId;
  }
  
  String getPackageName()
  {
    return this.packageName;
  }
  
  String getProductId()
  {
    return this.productId;
  }
  
  String getPurchaseState()
  {
    return this.purchaseState;
  }
  
  long getPurchaseTime()
  {
    return this.purchaseTime;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/GoogleInAppPurchaseData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */