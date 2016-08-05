package com.nianticlabs.nia.iap;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

class GetSkuDetailsResponseItem
{
  private static final String TAG = "SkuDetailsResponseItem";
  private String description;
  private String price;
  private String price_amount_micros;
  private String price_currency_code;
  private String productId;
  private String title;
  private String type;
  
  static GetSkuDetailsResponseItem fromJson(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      localGetSkuDetailsResponseItem = new GetSkuDetailsResponseItem();
      localGetSkuDetailsResponseItem.productId = stringFromJson(localJSONObject, "productId");
      localGetSkuDetailsResponseItem.type = stringFromJson(localJSONObject, "type");
      localGetSkuDetailsResponseItem.price = stringFromJson(localJSONObject, "price");
      localGetSkuDetailsResponseItem.price_amount_micros = stringFromJson(localJSONObject, "price_amount_micros");
      localGetSkuDetailsResponseItem.price_currency_code = stringFromJson(localJSONObject, "price_currency_code");
      localGetSkuDetailsResponseItem.title = stringFromJson(localJSONObject, "title");
      localGetSkuDetailsResponseItem.description = stringFromJson(localJSONObject, "description");
      return localGetSkuDetailsResponseItem;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Log.e("SkuDetailsResponseItem", "Failed to parse GetSkuDetailsResponseItem", localJSONException);
        GetSkuDetailsResponseItem localGetSkuDetailsResponseItem = null;
      }
    }
  }
  
  static GetSkuDetailsResponseItem fromPurchasableItemDetails(PurchasableItemDetails paramPurchasableItemDetails)
  {
    GetSkuDetailsResponseItem localGetSkuDetailsResponseItem = new GetSkuDetailsResponseItem();
    localGetSkuDetailsResponseItem.productId = paramPurchasableItemDetails.getItemId();
    localGetSkuDetailsResponseItem.type = "inapp";
    localGetSkuDetailsResponseItem.price = paramPurchasableItemDetails.getPrice();
    localGetSkuDetailsResponseItem.price_amount_micros = "";
    localGetSkuDetailsResponseItem.price_currency_code = "";
    localGetSkuDetailsResponseItem.title = paramPurchasableItemDetails.getTitle();
    localGetSkuDetailsResponseItem.description = paramPurchasableItemDetails.getDescription();
    return localGetSkuDetailsResponseItem;
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
  
  static PurchasableItemDetails toPurchasableItemDetails(GetSkuDetailsResponseItem paramGetSkuDetailsResponseItem)
  {
    return new PurchasableItemDetails(paramGetSkuDetailsResponseItem.productId, paramGetSkuDetailsResponseItem.title, paramGetSkuDetailsResponseItem.description, paramGetSkuDetailsResponseItem.price);
  }
  
  String getCurrencyCode()
  {
    return this.price_currency_code;
  }
  
  int getPriceE6()
  {
    try
    {
      int j = Integer.parseInt(this.price_amount_micros);
      i = j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        int i = 0;
      }
    }
    return i;
  }
  
  String getProductId()
  {
    return this.productId;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/GetSkuDetailsResponseItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */