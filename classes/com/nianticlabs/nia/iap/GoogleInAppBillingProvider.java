package com.nianticlabs.nia.iap;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.nianticlabs.nia.contextservice.ContextService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoogleInAppBillingProvider
  implements InAppBillingProvider
{
  private static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
  private static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
  private static final int BILLING_RESPONSE_RESULT_ERROR = 6;
  private static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
  private static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
  private static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
  private static final int BILLING_RESPONSE_RESULT_NOT_FOUND = 1000;
  private static final int BILLING_RESPONSE_RESULT_OK = 0;
  private static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
  private static final int BILLING_SERVICE_VERSION = 3;
  static final boolean ENABLE_VERBOSE_LOGS = false;
  private static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
  private static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
  private static final String ITEM_TYPE_INAPP = "inapp";
  private static final String PACKAGE_NAME_BASE = "com.niantic";
  private static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
  private static final String RESPONSE_CODE = "RESPONSE_CODE";
  private static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
  private static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
  private static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
  private static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
  private static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
  private static final String UNKNOWN_CURRENCY_STRING = "UNKNOWN";
  private static WeakReference<GoogleInAppBillingProvider> instance = null;
  private static final Logger log = new Logger(GoogleInAppBillingProvider.class);
  private IInAppBillingService billingService = null;
  private boolean clientConnected = false;
  private boolean connectionInProgress = false;
  private final Context context;
  private Map<String, GetSkuDetailsResponseItem> currentPurchasableItems;
  private InAppBillingProvider.Delegate delegate;
  private String itemBeingPurchased = null;
  private final String packageName;
  private PendingIntent pendingIntent;
  private boolean purchaseSupported = false;
  private ServiceConnection serviceConnection = null;
  private int transactionsInProgress = 0;
  
  public GoogleInAppBillingProvider(Context paramContext)
  {
    String str = paramContext.getPackageName();
    if (!str.startsWith("com.niantic")) {}
    for (this.packageName = "ERROR";; this.packageName = str)
    {
      this.context = paramContext;
      this.currentPurchasableItems = new HashMap();
      instance = new WeakReference(this);
      connectToBillingService();
      return;
    }
  }
  
  private void connectToBillingService()
  {
    if (this.connectionInProgress) {}
    for (;;)
    {
      return;
      if (this.billingService != null)
      {
        finalizeConnectionResult();
      }
      else
      {
        this.connectionInProgress = true;
        this.serviceConnection = new ServiceConnection()
        {
          public void onServiceConnected(ComponentName paramAnonymousComponentName, final IBinder paramAnonymousIBinder)
          {
            ContextService.runOnServiceHandler(new Runnable()
            {
              public void run()
              {
                if (GoogleInAppBillingProvider.this.serviceConnection == null) {
                  GoogleInAppBillingProvider.this.finalizeConnectionResult();
                }
                for (;;)
                {
                  return;
                  GoogleInAppBillingProvider.access$002(GoogleInAppBillingProvider.this, IInAppBillingService.Stub.asInterface(paramAnonymousIBinder));
                  try
                  {
                    int i = GoogleInAppBillingProvider.this.billingService.isBillingSupported(3, GoogleInAppBillingProvider.this.packageName, "inapp");
                    GoogleInAppBillingProvider localGoogleInAppBillingProvider = GoogleInAppBillingProvider.this;
                    if (i == 0) {}
                    for (boolean bool = true;; bool = false)
                    {
                      GoogleInAppBillingProvider.access$1102(localGoogleInAppBillingProvider, bool);
                      if (GoogleInAppBillingProvider.this.currentPurchasableItems.size() <= 0) {
                        break label162;
                      }
                      new GoogleInAppBillingProvider.ProcessPurchasedItemsTask(GoogleInAppBillingProvider.this).execute(new Void[0]);
                      break;
                    }
                  }
                  catch (RemoteException localRemoteException)
                  {
                    for (;;)
                    {
                      GoogleInAppBillingProvider.access$1102(GoogleInAppBillingProvider.this, false);
                    }
                    label162:
                    GoogleInAppBillingProvider.this.finalizeConnectionResult();
                  }
                }
              }
            });
          }
          
          public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
          {
            ContextService.runOnServiceHandler(new Runnable()
            {
              public void run()
              {
                GoogleInAppBillingProvider.access$002(GoogleInAppBillingProvider.this, null);
                GoogleInAppBillingProvider.this.finalizeConnectionResult();
              }
            });
          }
        };
        Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        localIntent.setPackage("com.android.vending");
        List localList = this.context.getPackageManager().queryIntentServices(localIntent, 0);
        if ((localList == null) || (localList.isEmpty())) {
          finalizeConnectionResult();
        }
        this.context.bindService(localIntent, this.serviceConnection, 1);
      }
    }
  }
  
  private void finalizeConnectionResult()
  {
    boolean bool = false;
    this.connectionInProgress = false;
    if (this.delegate != null)
    {
      InAppBillingProvider.Delegate localDelegate = this.delegate;
      if (this.billingService != null) {
        bool = true;
      }
      localDelegate.onConnectionStateChanged(bool);
    }
  }
  
  private void finalizePurchaseResult(PurchaseResult paramPurchaseResult)
  {
    this.transactionsInProgress = (-1 + this.transactionsInProgress);
    maybeDisconnectBillingService();
    if (this.delegate != null) {
      this.delegate.purchaseResult(paramPurchaseResult);
    }
  }
  
  public static WeakReference<GoogleInAppBillingProvider> getInstance()
  {
    return instance;
  }
  
  private static int getResponseCodeFromBundle(Bundle paramBundle)
  {
    return getResponseCodeFromObject(paramBundle.get("RESPONSE_CODE"));
  }
  
  private static int getResponseCodeFromIntent(Intent paramIntent)
  {
    return getResponseCodeFromObject(paramIntent.getExtras().get("RESPONSE_CODE"));
  }
  
  private static int getResponseCodeFromObject(Object paramObject)
  {
    int i;
    if (paramObject == null) {
      i = 0;
    }
    for (;;)
    {
      return i;
      if ((paramObject instanceof Integer)) {
        i = ((Integer)paramObject).intValue();
      } else if ((paramObject instanceof Long)) {
        i = (int)((Long)paramObject).longValue();
      } else {
        i = 6;
      }
    }
  }
  
  private boolean handlePurchaseErrorResult(int paramInt)
  {
    boolean bool = false;
    switch (paramInt)
    {
    case 2: 
    case 5: 
    case 6: 
    default: 
      finalizePurchaseResult(PurchaseResult.FAILURE);
    }
    for (;;)
    {
      bool = true;
      return bool;
      finalizePurchaseResult(PurchaseResult.USER_CANCELLED);
      continue;
      finalizePurchaseResult(PurchaseResult.BILLING_UNAVAILABLE);
      continue;
      finalizePurchaseResult(PurchaseResult.SKU_NOT_AVAILABLE);
      continue;
      new ProcessPurchasedItemsTask().execute(new Void[0]);
      finalizePurchaseResult(PurchaseResult.FAILURE);
    }
  }
  
  private void launchPurchaseActivity(PendingIntent paramPendingIntent)
  {
    this.pendingIntent = paramPendingIntent;
    ContextService.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(GoogleInAppBillingProvider.this.context, PurchaseActivity.class);
        GoogleInAppBillingProvider.this.context.startActivity(localIntent);
      }
    });
  }
  
  private void maybeDisconnectBillingService()
  {
    if ((this.transactionsInProgress > 0) || (this.connectionInProgress) || (this.clientConnected)) {}
    for (;;)
    {
      return;
      if (this.serviceConnection != null) {
        this.context.unbindService(this.serviceConnection);
      }
      this.serviceConnection = null;
      this.billingService = null;
      this.transactionsInProgress = 0;
    }
  }
  
  private void notifyPurchasableItemsResult(Collection<PurchasableItemDetails> paramCollection)
  {
    if (this.delegate != null) {
      this.delegate.purchasableItemsResult(paramCollection);
    }
  }
  
  private void processPurchaseResult(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    String str1 = this.itemBeingPurchased;
    this.itemBeingPurchased = null;
    if (this.billingService == null) {}
    for (;;)
    {
      return;
      if ((paramInt2 == 1000) || (!handlePurchaseErrorResult(paramInt2))) {
        if (paramInt1 == 0)
        {
          finalizePurchaseResult(PurchaseResult.USER_CANCELLED);
        }
        else if (paramInt1 != -1)
        {
          finalizePurchaseResult(PurchaseResult.FAILURE);
        }
        else if ((paramInt2 == 1000) || (paramString1 == null) || (paramString2 == null))
        {
          finalizePurchaseResult(PurchaseResult.FAILURE);
        }
        else
        {
          String str2 = "UNKNOWN";
          String str3 = null;
          int i = 0;
          if (str1 != null)
          {
            GetSkuDetailsResponseItem localGetSkuDetailsResponseItem = (GetSkuDetailsResponseItem)this.currentPurchasableItems.get(str1);
            if (localGetSkuDetailsResponseItem != null)
            {
              str3 = localGetSkuDetailsResponseItem.getProductId();
              str2 = localGetSkuDetailsResponseItem.getCurrencyCode();
              i = localGetSkuDetailsResponseItem.getPriceE6();
            }
          }
          if (str3 == null)
          {
            GoogleInAppPurchaseData localGoogleInAppPurchaseData = GoogleInAppPurchaseData.fromJson(paramString1);
            if (localGoogleInAppPurchaseData != null) {
              str3 = localGoogleInAppPurchaseData.getProductId();
            }
            if (str3 != null) {}
          }
          this.delegate.ProcessReceipt(paramString1, paramString2, str2, i);
        }
      }
    }
  }
  
  public void forwardedOnActivityResult(final int paramInt, Intent paramIntent)
  {
    final int i;
    final String str1;
    if (paramIntent != null)
    {
      i = getResponseCodeFromIntent(paramIntent);
      str1 = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
    }
    for (final String str2 = paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");; str2 = null)
    {
      ContextService.runOnServiceHandler(new Runnable()
      {
        public void run()
        {
          GoogleInAppBillingProvider.this.processPurchaseResult(paramInt, i, str1, str2);
        }
      });
      return;
      i = 1000;
      str1 = null;
    }
  }
  
  public void getPurchasableItems(ArrayList<String> paramArrayList)
  {
    if (!isBillingAvailable()) {
      notifyPurchasableItemsResult(Collections.emptyList());
    }
    for (;;)
    {
      return;
      new GetSkuDetailsTask(paramArrayList).execute(new Void[0]);
    }
  }
  
  public boolean isBillingAvailable()
  {
    if ((this.billingService != null) && (this.purchaseSupported)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isTransactionInProgress()
  {
    if (this.transactionsInProgress > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onPause()
  {
    this.clientConnected = false;
    maybeDisconnectBillingService();
  }
  
  public void onProcessedGoogleBillingTransaction(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean) {
      finalizePurchaseResult(PurchaseResult.FAILURE);
    }
    for (;;)
    {
      return;
      if (this.billingService == null) {
        finalizePurchaseResult(PurchaseResult.FAILURE);
      } else if (paramString == null) {
        finalizePurchaseResult(PurchaseResult.FAILURE);
      } else {
        new ConsumeItemTask(paramString).execute(new Void[0]);
      }
    }
  }
  
  public void onResume()
  {
    this.clientConnected = true;
    connectToBillingService();
  }
  
  public void purchaseItem(String paramString1, String paramString2)
  {
    this.transactionsInProgress = (1 + this.transactionsInProgress);
    if (!isBillingAvailable()) {
      finalizePurchaseResult(PurchaseResult.BILLING_UNAVAILABLE);
    }
    PendingIntent localPendingIntent;
    for (;;)
    {
      return;
      if (!this.currentPurchasableItems.keySet().contains(paramString1)) {
        finalizePurchaseResult(PurchaseResult.SKU_NOT_AVAILABLE);
      } else {
        try
        {
          Bundle localBundle = this.billingService.getBuyIntent(3, this.packageName, paramString1, "inapp", paramString2);
          localPendingIntent = (PendingIntent)localBundle.getParcelable("BUY_INTENT");
          if (!handlePurchaseErrorResult(getResponseCodeFromBundle(localBundle)))
          {
            if (localPendingIntent != null) {
              break;
            }
            finalizePurchaseResult(PurchaseResult.FAILURE);
          }
        }
        catch (RemoteException localRemoteException)
        {
          finalizePurchaseResult(PurchaseResult.FAILURE);
        }
      }
    }
    if (this.transactionsInProgress == 1) {}
    for (this.itemBeingPurchased = paramString1;; this.itemBeingPurchased = null)
    {
      launchPurchaseActivity(localPendingIntent);
      break;
    }
  }
  
  public void setDelegate(InAppBillingProvider.Delegate paramDelegate)
  {
    this.delegate = paramDelegate;
  }
  
  public void startBuyIntent(Activity paramActivity)
  {
    try
    {
      paramActivity.startIntentSenderForResult(this.pendingIntent.getIntentSender(), 10009, new Intent(), 0, 0, 0);
      return;
    }
    catch (IntentSender.SendIntentException localSendIntentException)
    {
      for (;;)
      {
        this.itemBeingPurchased = null;
        this.pendingIntent = null;
        finalizePurchaseResult(PurchaseResult.FAILURE);
      }
    }
  }
  
  private class ProcessPurchasedItemsTask
    extends AsyncTask<Void, Void, Bundle>
  {
    private final IInAppBillingService billingService = GoogleInAppBillingProvider.this.billingService;
    
    public ProcessPurchasedItemsTask() {}
    
    protected Bundle doInBackground(Void... paramVarArgs)
    {
      Bundle localBundle1 = null;
      if (this.billingService == null) {
        return localBundle1;
      }
      Object localObject1 = null;
      Object localObject2 = null;
      String str = null;
      label73:
      do
      {
        try
        {
          localBundle2 = this.billingService.getPurchases(3, GoogleInAppBillingProvider.this.packageName, "inapp", str);
          i = GoogleInAppBillingProvider.getResponseCodeFromBundle(localBundle2);
          localArrayList1 = localBundle2.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
          localArrayList2 = localBundle2.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
          if (i != 5) {
            continue;
          }
        }
        catch (RemoteException localRemoteException)
        {
          Bundle localBundle2;
          int i;
          ArrayList localArrayList1;
          ArrayList localArrayList2;
          for (;;) {}
        }
        if (localObject1 == null) {
          break;
        }
        localBundle1 = new Bundle();
        localBundle1.putStringArrayList("INAPP_PURCHASE_DATA_LIST", (ArrayList)localObject1);
        localBundle1.putStringArrayList("INAPP_DATA_SIGNATURE_LIST", (ArrayList)localObject2);
        break;
      } while ((i != 0) || (!localBundle2.containsKey("INAPP_PURCHASE_DATA_LIST")) || (!localBundle2.containsKey("INAPP_DATA_SIGNATURE_LIST")) || (localArrayList1.size() != localArrayList2.size()));
      if (localObject1 == null)
      {
        localObject1 = localArrayList1;
        localObject2 = localArrayList2;
      }
      for (;;)
      {
        str = localBundle2.getString("INAPP_CONTINUATION_TOKEN");
        if (str == null) {
          break label73;
        }
        if (str.length() != 0) {
          break;
        }
        break label73;
        ((ArrayList)localObject1).addAll(localArrayList1);
        ((ArrayList)localObject2).addAll(localArrayList2);
      }
    }
    
    protected void onPostExecute(final Bundle paramBundle)
    {
      if (paramBundle != null) {
        ContextService.runOnServiceHandler(new Runnable()
        {
          public void run()
          {
            ArrayList localArrayList1 = paramBundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            ArrayList localArrayList2 = paramBundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            for (int i = 0; i < localArrayList1.size(); i++)
            {
              GoogleInAppBillingProvider.access$608(GoogleInAppBillingProvider.this);
              GoogleInAppBillingProvider.this.processPurchaseResult(-1, 0, (String)localArrayList1.get(i), (String)localArrayList2.get(i));
            }
            GoogleInAppBillingProvider.this.finalizeConnectionResult();
            GoogleInAppBillingProvider.this.maybeDisconnectBillingService();
          }
        });
      }
      for (;;)
      {
        return;
        GoogleInAppBillingProvider.this.finalizeConnectionResult();
        GoogleInAppBillingProvider.this.maybeDisconnectBillingService();
      }
    }
  }
  
  private class ConsumeItemTask
    extends AsyncTask<Void, Void, Integer>
  {
    private final IInAppBillingService billingService;
    private final String purchaseToken;
    
    public ConsumeItemTask(String paramString)
    {
      this.purchaseToken = paramString;
      this.billingService = GoogleInAppBillingProvider.this.billingService;
    }
    
    protected Integer doInBackground(Void... paramVarArgs)
    {
      Object localObject = null;
      if (this.billingService == null) {}
      for (;;)
      {
        return (Integer)localObject;
        try
        {
          Integer localInteger = Integer.valueOf(this.billingService.consumePurchase(3, GoogleInAppBillingProvider.this.packageName, this.purchaseToken));
          localObject = localInteger;
        }
        catch (RemoteException localRemoteException) {}
      }
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      if ((paramInteger == null) || (paramInteger.intValue() != 0)) {
        GoogleInAppBillingProvider.this.finalizePurchaseResult(PurchaseResult.FAILURE);
      }
      for (;;)
      {
        return;
        GoogleInAppBillingProvider.this.finalizePurchaseResult(PurchaseResult.SUCCESS);
      }
    }
  }
  
  private class GetSkuDetailsTask
    extends AsyncTask<Void, Void, Bundle>
  {
    private final IInAppBillingService billingService = GoogleInAppBillingProvider.this.billingService;
    private final Bundle requestBundle = new Bundle();
    
    public GetSkuDetailsTask()
    {
      ArrayList localArrayList;
      this.requestBundle.putStringArrayList("ITEM_ID_LIST", localArrayList);
    }
    
    protected Bundle doInBackground(Void... paramVarArgs)
    {
      Object localObject = null;
      if (this.billingService == null) {}
      for (;;)
      {
        return (Bundle)localObject;
        try
        {
          Bundle localBundle = this.billingService.getSkuDetails(3, GoogleInAppBillingProvider.this.packageName, "inapp", this.requestBundle);
          localObject = localBundle;
        }
        catch (RemoteException localRemoteException) {}
      }
    }
    
    protected void onPostExecute(Bundle paramBundle)
    {
      ArrayList localArrayList = new ArrayList();
      GoogleInAppBillingProvider.this.currentPurchasableItems.clear();
      if ((paramBundle != null) && (paramBundle.containsKey("DETAILS_LIST")))
      {
        Iterator localIterator = paramBundle.getStringArrayList("DETAILS_LIST").iterator();
        while (localIterator.hasNext())
        {
          GetSkuDetailsResponseItem localGetSkuDetailsResponseItem = GetSkuDetailsResponseItem.fromJson((String)localIterator.next());
          if (localGetSkuDetailsResponseItem != null)
          {
            PurchasableItemDetails localPurchasableItemDetails = GetSkuDetailsResponseItem.toPurchasableItemDetails(localGetSkuDetailsResponseItem);
            localArrayList.add(localPurchasableItemDetails);
            GoogleInAppBillingProvider.this.currentPurchasableItems.put(localPurchasableItemDetails.getItemId(), localGetSkuDetailsResponseItem);
          }
        }
      }
      GoogleInAppBillingProvider.this.notifyPurchasableItemsResult(localArrayList);
      new GoogleInAppBillingProvider.ProcessPurchasedItemsTask(GoogleInAppBillingProvider.this).execute(new Void[0]);
    }
  }
  
  static class Logger
  {
    private final String tag;
    
    public Logger(Class paramClass)
    {
      this.tag = paramClass.toString();
    }
    
    void assertOnServiceThread(String paramString)
    {
      if (!ContextService.onServiceThread()) {
        severe(this.tag + ": Must be on the service thread: " + paramString, new Object[0]);
      }
    }
    
    void dev(String paramString, Object... paramVarArgs) {}
    
    void error(String paramString, Object... paramVarArgs) {}
    
    void severe(String paramString, Object... paramVarArgs) {}
    
    void warning(String paramString, Object... paramVarArgs) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/iap/GoogleInAppBillingProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */