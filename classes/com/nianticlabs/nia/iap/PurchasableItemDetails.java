package com.nianticlabs.nia.iap;

class PurchasableItemDetails
{
  private String description;
  private String itemId;
  private String price;
  private String title;
  
  PurchasableItemDetails(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.itemId = paramString1;
    this.title = paramString2;
    this.description = paramString3;
    this.price = paramString4;
  }
  
  String getDescription()
  {
    return this.description;
  }
  
  String getItemId()
  {
    return this.itemId;
  }
  
  String getPrice()
  {
    return this.price;
  }
  
  String getTitle()
  {
    return this.title;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/PurchasableItemDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */