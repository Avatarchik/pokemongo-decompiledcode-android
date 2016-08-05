package com.upsight.android.analytics.internal;

import android.content.Intent;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.UpsightGooglePlayHelper;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.monetization.UpsightMonetizationIapEvent;
import com.upsight.android.analytics.event.monetization.UpsightMonetizationIapEvent.Builder;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class GooglePlayHelper
  extends UpsightGooglePlayHelper
{
  private static final String STORE_NAME = "google_play";
  private ObjectMapper mMapper;
  private UpsightContext mUpsight;
  
  GooglePlayHelper(UpsightContext paramUpsightContext, ObjectMapper paramObjectMapper)
  {
    this.mUpsight = paramUpsightContext;
    this.mMapper = paramObjectMapper;
  }
  
  private JSONObject createIapBundle(int paramInt, String paramString1, String paramString2)
    throws JSONException
  {
    return new JSONObject().put("RESPONSE_CODE", paramInt).put("INAPP_PURCHASE_DATA", paramString1).put("INAPP_DATA_SIGNATURE", paramString2);
  }
  
  public void trackPurchase(int paramInt, String paramString1, double paramDouble1, double paramDouble2, String paramString2, Intent paramIntent, UpsightPublisherData paramUpsightPublisherData)
    throws UpsightException
  {
    int i = paramIntent.getIntExtra("RESPONSE_CODE", Integer.MIN_VALUE);
    switch (i)
    {
    default: 
      String str3 = "Failed to track Google Play purchase. See response code for details. responseCode=" + i;
      this.mUpsight.getLogger().e("Upsight", str3, new Object[0]);
      throw new UpsightException(str3, new Object[0]);
    case 1: 
      UpsightMonetizationIapEvent.createBuilder("google_play", null, Double.valueOf(paramDouble2), Double.valueOf(paramDouble1), Integer.valueOf(paramInt), paramString1, paramString2).setResolution(Resolution.cancel.toString()).put(paramUpsightPublisherData).record(this.mUpsight);
      return;
    }
    String str1 = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
    String str2 = paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
    if (TextUtils.isEmpty(str1))
    {
      this.mUpsight.getLogger().e("Upsight", "Failed to track Google Play purchase due to null or empty purchase data.", new Object[0]);
      throw new UpsightException("Failed to track Google Play purchase due to null or empty purchase data.", new Object[0]);
    }
    if (TextUtils.isEmpty(str2))
    {
      this.mUpsight.getLogger().e("Upsight", "Failed to track Google Play purchase due to null or empty data signature.", new Object[0]);
      throw new UpsightException("Failed to track Google Play purchase due to null or empty data signature.", new Object[0]);
    }
    try
    {
      JsonNode localJsonNode = this.mMapper.readTree(str1);
      PurchaseData localPurchaseData = (PurchaseData)this.mMapper.treeToValue(localJsonNode, PurchaseData.class);
      if (localPurchaseData == null) {
        break label534;
      }
      switch (localPurchaseData.purchaseState)
      {
      default: 
        this.mUpsight.getLogger().e("Upsight", "Failed to track Google Play purchase. Invalid purchase state.", new Object[0]);
        throw new UpsightException("Failed to track Google Play purchase. Invalid purchase state.", new Object[0]);
      }
    }
    catch (JsonParseException localJsonParseException)
    {
      this.mUpsight.getLogger().e("Upsight", localJsonParseException, "Failed to track Google Play purchase due to purchase data schema mismatch.", new Object[0]);
      throw new UpsightException(localJsonParseException, "Failed to track Google Play purchase due to purchase data schema mismatch.", new Object[0]);
    }
    catch (IOException localIOException)
    {
      this.mUpsight.getLogger().e("Upsight", localIOException, "Failed to track Google Play purchase due to malformed purchase data JSON.", new Object[0]);
      throw new UpsightException(localIOException, "Failed to track Google Play purchase due to malformed purchase data JSON.", new Object[0]);
    }
    Resolution localResolution = Resolution.buy;
    for (;;)
    {
      try
      {
        UpsightMonetizationIapEvent.createBuilder("google_play", createIapBundle(i, str1, str2), Double.valueOf(paramDouble2), Double.valueOf(paramDouble1), Integer.valueOf(paramInt), paramString1, paramString2).setResolution(localResolution.toString()).put(paramUpsightPublisherData).record(this.mUpsight);
      }
      catch (JSONException localJSONException)
      {
        this.mUpsight.getLogger().e("Upsight", localJSONException, "Failed to track Google Play purchase. Unable to create iap_bundle.", new Object[0]);
        throw new UpsightException(localJSONException, "Failed to track Google Play purchase. Unable to create iap_bundle.", new Object[0]);
      }
      localResolution = Resolution.cancel;
      continue;
      localResolution = Resolution.refund;
    }
    label534:
    this.mUpsight.getLogger().e("Upsight", "Failed to track Google Play purchase due to missing fields in purchase data.", new Object[0]);
    throw new UpsightException("Failed to track Google Play purchase due to missing fields in purchase data.", new Object[0]);
  }
  
  static class PurchaseData
  {
    @JsonProperty("developerPayload")
    String developerPayload;
    @JsonProperty("orderId")
    String orderId;
    @JsonProperty("packageName")
    String packageName;
    @JsonProperty("productId")
    String productId;
    @JsonProperty("purchaseState")
    int purchaseState;
    @JsonProperty("purchaseTime")
    long purchaseTime;
    @JsonProperty("purchaseToken")
    String purchaseToken;
  }
  
  public static enum Resolution
  {
    static
    {
      Resolution[] arrayOfResolution = new Resolution[3];
      arrayOfResolution[0] = buy;
      arrayOfResolution[1] = cancel;
      arrayOfResolution[2] = refund;
      $VALUES = arrayOfResolution;
    }
    
    private Resolution() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/GooglePlayHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */