package com.voxelbusters.nativeplugins.features.sharing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import com.voxelbusters.nativeplugins.NativePluginHelper;
import com.voxelbusters.nativeplugins.defines.CommonDefines;
import com.voxelbusters.nativeplugins.defines.Enums.eShareOptions;
import com.voxelbusters.nativeplugins.utilities.FileUtility;
import com.voxelbusters.nativeplugins.utilities.StringUtility;

public class SharingHandler
{
  private static SharingHandler INSTANCE;
  
  public static SharingHandler getInstance()
  {
    if (INSTANCE == null) {
      INSTANCE = new SharingHandler();
    }
    return INSTANCE;
  }
  
  Context getContext()
  {
    return NativePluginHelper.getCurrentContext();
  }
  
  public boolean isServiceAvailable(int paramInt)
  {
    Enums.eShareOptions localeShareOptions = Enums.eShareOptions.values()[paramInt];
    return SharingHelper.isServiceAvailable(getContext(), localeShareOptions);
  }
  
  public void sendMail(String paramString1, String paramString2, boolean paramBoolean, byte[] paramArrayOfByte, int paramInt, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("type", "mail");
    localBundle.putString("subject", paramString1);
    if (StringUtility.isNullOrEmpty(paramString2)) {
      paramString2 = "";
    }
    Object localObject = paramString2;
    if (paramBoolean) {
      localObject = Html.fromHtml(paramString2);
    }
    localBundle.putCharSequence("body", (CharSequence)localObject);
    if (paramInt != 0)
    {
      Uri localUri = FileUtility.createSharingFileUri(getContext(), paramArrayOfByte, paramInt, CommonDefines.SHARING_DIR, paramString4);
      if (localUri != null)
      {
        String[] arrayOfString4 = new String[1];
        arrayOfString4[0] = localUri.toString();
        localBundle.putStringArray("attachment", arrayOfString4);
      }
    }
    String[] arrayOfString1 = StringUtility.convertJsonStringToStringArray(paramString5);
    String[] arrayOfString2 = StringUtility.convertJsonStringToStringArray(paramString6);
    String[] arrayOfString3 = StringUtility.convertJsonStringToStringArray(paramString7);
    localBundle.putStringArray("to-recipient-list", arrayOfString1);
    localBundle.putStringArray("cc-recipient-list", arrayOfString2);
    localBundle.putStringArray("bcc-recipient-list", arrayOfString3);
    startActivity(localBundle);
  }
  
  public void sendSms(String paramString1, String paramString2)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("type", "sms");
    localBundle.putString("message", paramString1);
    localBundle.putStringArray("to-recipient-list", StringUtility.convertJsonStringToStringArray(paramString2));
    startActivity(localBundle);
  }
  
  public void share(String paramString1, String paramString2, byte[] paramArrayOfByte, int paramInt, String paramString3)
  {
    String[] arrayOfString = StringUtility.convertJsonStringToStringArray(paramString3);
    Bundle localBundle = new Bundle();
    localBundle.putString("type", "");
    localBundle.putString("message", paramString1);
    localBundle.putString("url", paramString2);
    Uri localUri = FileUtility.createSharingFileUri(getContext(), paramArrayOfByte, paramInt, CommonDefines.SHARING_DIR, System.currentTimeMillis() + ".png");
    if (localUri != null) {
      localBundle.putString("image-path", localUri.toString());
    }
    localBundle.putStringArray("exclude-list", arrayOfString);
    startActivity(localBundle);
  }
  
  public void shareOnWhatsApp(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    Uri localUri = null;
    if (paramInt != 0) {
      localUri = FileUtility.createSharingFileUri(getContext(), paramArrayOfByte, paramInt, CommonDefines.SHARING_DIR, System.currentTimeMillis() + ".png");
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("type", "whatsapp");
    localBundle.putString("message", paramString);
    if (localUri != null) {
      localBundle.putString("image-path", localUri.toString());
    }
    startActivity(localBundle);
  }
  
  public void startActivity(Bundle paramBundle)
  {
    Intent localIntent = new Intent(NativePluginHelper.getCurrentContext(), SharingActivity.class);
    localIntent.putExtras(paramBundle);
    NativePluginHelper.startActivityOnUiThread(localIntent);
  }
  
  public static enum eShareCategeories
  {
    TEXT,  UNDEFINED;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/sharing/SharingHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */