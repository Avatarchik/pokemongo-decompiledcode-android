package com.google.vr.cardboard;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.util.Log;

public class NFCUtils
{
  private static final String TAG = NFCUtils.class.getSimpleName();
  Context context;
  NfcAdapter nfcAdapter;
  BroadcastReceiver nfcBroadcastReceiver;
  IntentFilter[] nfcIntentFilters;
  
  private IntentFilter createNfcIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.nfc.action.NDEF_DISCOVERED");
    localIntentFilter.addAction("android.nfc.action.TECH_DISCOVERED");
    localIntentFilter.addAction("android.nfc.action.TAG_DISCOVERED");
    return localIntentFilter;
  }
  
  protected boolean isNFCEnabled()
  {
    if ((this.nfcAdapter != null) && (this.nfcAdapter.isEnabled())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onCreate(Activity paramActivity)
  {
    this.context = paramActivity.getApplicationContext();
    this.nfcAdapter = NfcAdapter.getDefaultAdapter(this.context);
    this.nfcBroadcastReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        Log.i(NFCUtils.TAG, "Got an NFC tag!");
        NFCUtils.this.onNFCTagDetected((Tag)paramAnonymousIntent.getParcelableExtra("android.nfc.extra.TAG"));
      }
    };
    IntentFilter localIntentFilter1 = createNfcIntentFilter();
    localIntentFilter1.addDataScheme("cardboard");
    IntentFilter localIntentFilter2 = createNfcIntentFilter();
    localIntentFilter2.addDataScheme("http");
    localIntentFilter2.addDataAuthority("goo.gl", null);
    IntentFilter localIntentFilter3 = createNfcIntentFilter();
    localIntentFilter3.addDataScheme("http");
    localIntentFilter3.addDataAuthority("google.com", null);
    localIntentFilter3.addDataPath("/cardboard/cfg.*", 2);
    IntentFilter[] arrayOfIntentFilter = new IntentFilter[3];
    arrayOfIntentFilter[0] = localIntentFilter1;
    arrayOfIntentFilter[1] = localIntentFilter2;
    arrayOfIntentFilter[2] = localIntentFilter3;
    this.nfcIntentFilters = arrayOfIntentFilter;
  }
  
  protected void onNFCTagDetected(Tag paramTag) {}
  
  public void onPause(Activity paramActivity)
  {
    if (isNFCEnabled()) {
      this.nfcAdapter.disableForegroundDispatch(paramActivity);
    }
    paramActivity.unregisterReceiver(this.nfcBroadcastReceiver);
  }
  
  public void onResume(Activity paramActivity)
  {
    paramActivity.registerReceiver(this.nfcBroadcastReceiver, createNfcIntentFilter());
    Intent localIntent = new Intent("android.nfc.action.NDEF_DISCOVERED");
    localIntent.setPackage(paramActivity.getPackageName());
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(this.context, 0, localIntent, 0);
    if (isNFCEnabled()) {
      this.nfcAdapter.enableForegroundDispatch(paramActivity, localPendingIntent, this.nfcIntentFilters, (String[][])null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/vr/cardboard/NFCUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */