package com.voxelbusters.nativeplugins.features.sharing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.voxelbusters.nativeplugins.defines.Enums.eShareOptions;
import com.voxelbusters.nativeplugins.utilities.ApplicationUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SharingHelper
{
  public static HashMap<String, Enums.eShareOptions> packageNameMap = null;
  
  static
  {
    packageNameMap = new HashMap();
    packageNameMap.put("com.facebook.katana", Enums.eShareOptions.FB);
    packageNameMap.put("com.facebook.katana.LoginActivity", Enums.eShareOptions.FB);
    packageNameMap.put("com.twitter.android", Enums.eShareOptions.TWITTER);
    packageNameMap.put("com.google.android.apps.plus", Enums.eShareOptions.GOOGLE_PLUS);
    packageNameMap.put("com.instagram.android", Enums.eShareOptions.INSTAGRAM);
    packageNameMap.put("com.whatsapp", Enums.eShareOptions.WHATSAPP);
  }
  
  public static boolean checkIfPackageMatchesShareOptions(String paramString, String[] paramArrayOfString)
  {
    boolean bool = false;
    Enums.eShareOptions localeShareOptions = (Enums.eShareOptions)packageNameMap.get(paramString);
    int i;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0) && (localeShareOptions != null)) {
      i = paramArrayOfString.length;
    }
    for (int j = 0;; j++)
    {
      if (j >= i) {}
      for (;;)
      {
        return bool;
        if (Integer.parseInt(paramArrayOfString[j]) != localeShareOptions.ordinal()) {
          break;
        }
        bool = true;
      }
    }
  }
  
  public static Intent[] getPriorityIntents(Intent paramIntent)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = packageNameMap.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return (Intent[])localArrayList.toArray(new Intent[localArrayList.size()]);
      }
      String str = (String)localIterator.next();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setPackage(str);
      localArrayList.add(localIntent);
    }
  }
  
  public static Intent[] getPrioritySocialNetworkingIntents(Intent paramIntent)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = packageNameMap.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return (Intent[])localArrayList.toArray(new Intent[localArrayList.size()]);
      }
      String str = (String)localIterator.next();
      if (isSocialNetwork(str))
      {
        Intent localIntent = new Intent(paramIntent);
        localIntent.setPackage(str);
        localArrayList.add(localIntent);
      }
    }
  }
  
  public static boolean isServiceAvailable(Context paramContext, Enums.eShareOptions parameShareOptions)
  {
    boolean bool = false;
    if (parameShareOptions == Enums.eShareOptions.FB) {
      if ((ApplicationUtility.isIntentAvailable(paramContext, "android.intent.action.SEND", "text/plain", "com.facebook.katana")) || (ApplicationUtility.isIntentAvailable(paramContext, "android.intent.action.SEND", "text/plain", "com.facebook.katana.LoginActivity"))) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      if (parameShareOptions == Enums.eShareOptions.TWITTER)
      {
        bool = ApplicationUtility.isIntentAvailable(paramContext, "android.intent.action.SEND", "text/plain", "com.twitter.android");
      }
      else if (parameShareOptions == Enums.eShareOptions.WHATSAPP)
      {
        bool = ApplicationUtility.isIntentAvailable(paramContext, "android.intent.action.SEND", "text/plain", "com.whatsapp");
      }
      else if (parameShareOptions == Enums.eShareOptions.MESSAGE)
      {
        Intent localIntent = new Intent("android.intent.action.SENDTO");
        localIntent.setData(Uri.parse("smsto:"));
        bool = ApplicationUtility.isIntentAvailable(paramContext, localIntent);
      }
      else if (parameShareOptions == Enums.eShareOptions.MAIL)
      {
        bool = ApplicationUtility.isIntentAvailable(paramContext, "android.intent.action.SEND", "message/rfc822", null);
      }
    }
  }
  
  public static boolean isSocialNetwork(String paramString)
  {
    Enums.eShareOptions localeShareOptions = (Enums.eShareOptions)packageNameMap.get(paramString);
    if ((localeShareOptions != null) && ((Enums.eShareOptions.FB == localeShareOptions) || (Enums.eShareOptions.TWITTER == localeShareOptions) || (Enums.eShareOptions.GOOGLE_PLUS == localeShareOptions) || (Enums.eShareOptions.INSTAGRAM == localeShareOptions))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/sharing/SharingHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */