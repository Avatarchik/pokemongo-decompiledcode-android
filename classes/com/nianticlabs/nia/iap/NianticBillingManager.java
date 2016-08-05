package com.nianticlabs.nia.iap;

import android.content.Context;
import com.nianticlabs.nia.contextservice.ContextService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class NianticBillingManager
  extends ContextService
  implements InAppBillingProvider.Delegate
{
  private InAppBillingProvider inAppBillingProvider;
  private boolean initializing;
  
  public NianticBillingManager(Context paramContext, long paramLong)
  {
    super(paramContext, paramLong);
    this.inAppBillingProvider = new GoogleInAppBillingProvider(paramContext);
  }
  
  private native void nativeInitializeCallback();
  
  private native void nativeOnConnectionStateChanged(boolean paramBoolean);
  
  private native void nativeProcessReceipt(String paramString1, String paramString2, String paramString3, int paramInt);
  
  private native void nativePurchasableItemsResult(PurchasableItemDetails[] paramArrayOfPurchasableItemDetails);
  
  private native void nativePurchaseResult(int paramInt);
  
  private native void nativeRecordPurchase(boolean paramBoolean, String paramString1, int paramInt, float paramFloat, String paramString2, String paramString3);
  
  public void ProcessReceipt(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    synchronized (this.callbackLock)
    {
      nativeProcessReceipt(paramString1, paramString2, paramString3, paramInt);
      return;
    }
  }
  
  public void getPurchasableItems(String[] paramArrayOfString)
  {
    ContextService.runOnServiceHandler(new Runnable()
    {
      public void run()
      {
        NianticBillingManager.this.inAppBillingProvider.getPurchasableItems(this.val$items);
      }
    });
  }
  
  public void initialize()
  {
    this.initializing = true;
    this.inAppBillingProvider.setDelegate(this);
    ContextService.runOnServiceHandler(new Runnable()
    {
      public void run()
      {
        NianticBillingManager.this.nativeInitializeCallback();
      }
    });
  }
  
  public boolean isBillingAvailable()
  {
    return this.inAppBillingProvider.isBillingAvailable();
  }
  
  public boolean isTransactionInProgress()
  {
    return this.inAppBillingProvider.isTransactionInProgress();
  }
  
  public void onConnectionStateChanged(final boolean paramBoolean)
  {
    ContextService.runOnServiceHandler(new Runnable()
    {
      public void run()
      {
        synchronized (NianticBillingManager.this.callbackLock)
        {
          NianticBillingManager.this.nativeOnConnectionStateChanged(paramBoolean);
          return;
        }
      }
    });
  }
  
  public void onPause()
  {
    this.inAppBillingProvider.onPause();
  }
  
  public void onResume()
  {
    this.inAppBillingProvider.onResume();
  }
  
  public void onStart() {}
  
  public void onStop() {}
  
  public void purchasableItemsResult(Collection<PurchasableItemDetails> paramCollection)
  {
    PurchasableItemDetails[] arrayOfPurchasableItemDetails = new PurchasableItemDetails[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      PurchasableItemDetails localPurchasableItemDetails = (PurchasableItemDetails)localIterator.next();
      int j = i + 1;
      arrayOfPurchasableItemDetails[i] = localPurchasableItemDetails;
      i = j;
    }
    synchronized (this.callbackLock)
    {
      nativePurchasableItemsResult(arrayOfPurchasableItemDetails);
      return;
    }
  }
  
  public void purchaseResult(PurchaseResult paramPurchaseResult)
  {
    synchronized (this.callbackLock)
    {
      nativePurchaseResult(paramPurchaseResult.ordinal());
      return;
    }
  }
  
  public void purchaseVendorItem(final String paramString1, final String paramString2)
  {
    ContextService.runOnServiceHandler(new Runnable()
    {
      public void run()
      {
        NianticBillingManager.this.inAppBillingProvider.purchaseItem(paramString1, paramString2);
      }
    });
  }
  
  public void redeemReceiptResult(final boolean paramBoolean, final String paramString)
  {
    ContextService.runOnServiceHandler(new Runnable()
    {
      public void run()
      {
        NianticBillingManager.this.inAppBillingProvider.onProcessedGoogleBillingTransaction(paramBoolean, paramString);
      }
    });
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/NianticBillingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */