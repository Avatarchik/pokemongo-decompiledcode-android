package com.nianticlabs.nia.iap;

 enum PurchaseResult
{
  static
  {
    NO_PURCHASE = new PurchaseResult("NO_PURCHASE", 1);
    FAILURE = new PurchaseResult("FAILURE", 2);
    BALANCE_TOO_LOW = new PurchaseResult("BALANCE_TOO_LOW", 3);
    SKU_NOT_AVAILABLE = new PurchaseResult("SKU_NOT_AVAILABLE", 4);
    IN_PROGRESS = new PurchaseResult("IN_PROGRESS", 5);
    SUCCESS = new PurchaseResult("SUCCESS", 6);
    PURCHASE_DEFERRED = new PurchaseResult("PURCHASE_DEFERRED", 7);
    USER_CANCELLED = new PurchaseResult("USER_CANCELLED", 8);
    BILLING_UNAVAILABLE = new PurchaseResult("BILLING_UNAVAILABLE", 9);
    PurchaseResult[] arrayOfPurchaseResult = new PurchaseResult[10];
    arrayOfPurchaseResult[0] = UNDEFINED;
    arrayOfPurchaseResult[1] = NO_PURCHASE;
    arrayOfPurchaseResult[2] = FAILURE;
    arrayOfPurchaseResult[3] = BALANCE_TOO_LOW;
    arrayOfPurchaseResult[4] = SKU_NOT_AVAILABLE;
    arrayOfPurchaseResult[5] = IN_PROGRESS;
    arrayOfPurchaseResult[6] = SUCCESS;
    arrayOfPurchaseResult[7] = PURCHASE_DEFERRED;
    arrayOfPurchaseResult[8] = USER_CANCELLED;
    arrayOfPurchaseResult[9] = BILLING_UNAVAILABLE;
    $VALUES = arrayOfPurchaseResult;
  }
  
  private PurchaseResult() {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/PurchaseResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */