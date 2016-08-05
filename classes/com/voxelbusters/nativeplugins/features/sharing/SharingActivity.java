package com.voxelbusters.nativeplugins.features.sharing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import com.voxelbusters.nativeplugins.NativePluginHelper;
import com.voxelbusters.nativeplugins.defines.Enums.eShareOptions;
import com.voxelbusters.nativeplugins.utilities.Debug;
import com.voxelbusters.nativeplugins.utilities.StringUtility;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SharingActivity
  extends Activity
{
  final int SEND_MAIL_REQUEST_CODE = 2;
  final int SEND_SMS_REQUEST_CODE = 3;
  final int SHARE_ON_WHATS_APP_REQUEST_CODE = 4;
  final int SHARING_REQUEST_CODE = 1;
  Bundle bundleInfo;
  File currentImageFileShared = null;
  
  private void shareItem(String paramString1, String paramString2, String paramString3, String paramString4, String[] paramArrayOfString)
  {
    Context localContext = NativePluginHelper.getCurrentContext();
    Intent localIntent1 = new Intent("android.intent.action.SEND");
    boolean bool;
    label56:
    int i;
    int j;
    ArrayList localArrayList;
    Iterator localIterator;
    if (StringUtility.isNullOrEmpty(paramString3))
    {
      bool = false;
      localIntent1.setType(getMimeType(paramString4, bool));
      if (!StringUtility.isNullOrEmpty(paramString2)) {
        break label368;
      }
      localIntent1.putExtra("android.intent.extra.TEXT", paramString1);
      if (!StringUtility.isNullOrEmpty(paramString3)) {
        localIntent1.putExtra("android.intent.extra.STREAM", Uri.parse(paramString3));
      }
      localIntent1.addCategory("android.intent.category.DEFAULT");
      i = 0;
      j = 0;
      if ((paramArrayOfString != null) && (StringUtility.contains(Enums.eShareOptions.MESSAGE.ordinal(), paramArrayOfString)) && (StringUtility.contains(Enums.eShareOptions.MAIL.ordinal(), paramArrayOfString)) && (StringUtility.contains(Enums.eShareOptions.WHATSAPP.ordinal(), paramArrayOfString))) {
        j = 1;
      }
      List localList = localContext.getPackageManager().queryIntentActivities(localIntent1, 0);
      if (localList.isEmpty()) {
        break label531;
      }
      localArrayList = new ArrayList();
      localIterator = localList.iterator();
      label219:
      if (localIterator.hasNext()) {
        break label402;
      }
      if (localArrayList.isEmpty()) {
        break label525;
      }
      Intent localIntent3 = Intent.createChooser((Intent)localArrayList.remove(0), "Share via");
      localIntent3.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])localArrayList.toArray(new Parcelable[0]));
      localIntent3.addFlags(1);
      startActivityForResult(localIntent3, 1);
    }
    for (;;)
    {
      if (i != 0)
      {
        AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this);
        AlertDialog.Builder localBuilder2 = localBuilder1.setTitle("Share").setMessage("No services found!").setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            NativePluginHelper.sendMessage("SharingFinished", "failed");
            SharingActivity.this.finish();
          }
        });
        DialogInterface.OnKeyListener local2 = new DialogInterface.OnKeyListener()
        {
          public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
          {
            if (paramAnonymousInt == 4)
            {
              NativePluginHelper.sendMessage("SharingFinished", "failed");
              SharingActivity.this.finish();
            }
            return true;
          }
        };
        localBuilder2.setOnKeyListener(local2);
        localBuilder2.create().show();
      }
      return;
      bool = true;
      break;
      label368:
      localIntent1.putExtra("android.intent.extra.TEXT", paramString1 + "\n" + paramString2);
      break label56;
      label402:
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      if (localResolveInfo.activityInfo != null) {}
      for (String str = localResolveInfo.activityInfo.packageName;; str = null)
      {
        if ((str == null) || (SharingHelper.checkIfPackageMatchesShareOptions(str, paramArrayOfString)) || ((j != 0) && (!SharingHelper.isSocialNetwork(str)))) {
          break label523;
        }
        Intent localIntent2 = new Intent(localIntent1);
        ComponentName localComponentName = new ComponentName(str, localResolveInfo.activityInfo.name);
        localIntent2.setComponent(localComponentName);
        localIntent2.setPackage(str);
        localArrayList.add(localIntent2);
        break;
      }
      label523:
      break label219;
      label525:
      i = 1;
      continue;
      label531:
      i = 1;
    }
  }
  
  String getMimeType(String paramString, boolean paramBoolean)
  {
    String str = "text/plain";
    if (paramBoolean) {
      str = "image/*";
    }
    if ("mail".equals(paramString)) {
      str = "message/rfc822";
    }
    return str;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1) {
      NativePluginHelper.sendMessage("SharingFinished", "closed");
    }
    for (;;)
    {
      finish();
      return;
      if (paramInt1 == 2) {
        NativePluginHelper.sendMessage("MailShareFinished", "closed");
      } else if (paramInt1 == 3) {
        NativePluginHelper.sendMessage("MessagingShareFinished", "closed");
      } else if (paramInt1 == 4) {
        NativePluginHelper.sendMessage("WhatsAppShareFinished", "closed");
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.bundleInfo == null) {
      this.bundleInfo = getIntent().getExtras();
    }
    String str = this.bundleInfo.getString("type");
    if (StringUtility.isNullOrEmpty(str)) {
      shareItem(this.bundleInfo.getString("message"), this.bundleInfo.getString("url"), this.bundleInfo.getString("image-path"), str, this.bundleInfo.getStringArray("exclude-list"));
    }
    for (;;)
    {
      return;
      if (str.equals("sms")) {
        shareWithSMS(this.bundleInfo);
      } else if (str.equals("mail")) {
        shareWithEmail(this.bundleInfo);
      } else if (str.equals("whatsapp")) {
        shareOnWhatsApp(this.bundleInfo);
      } else {
        Debug.log("NativePlugins.Sharing", "Sharing not implemented for this type " + str);
      }
    }
  }
  
  void shareOnWhatsApp(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("message");
    String str2 = paramBundle.getString("image-path");
    if (StringUtility.isNullOrEmpty(str2)) {}
    for (boolean bool = false;; bool = true)
    {
      String str3 = getMimeType("whatsapp", bool);
      Intent localIntent = new Intent("android.intent.action.SEND");
      if (str1 != null) {
        localIntent.putExtra("android.intent.extra.TEXT", str1);
      }
      if (str2 != null) {
        localIntent.putExtra("android.intent.extra.STREAM", Uri.parse(str2));
      }
      localIntent.setType(str3);
      localIntent.setPackage("com.whatsapp");
      startActivityForResult(localIntent, 4);
      return;
    }
  }
  
  void shareWithEmail(Bundle paramBundle)
  {
    int i = 0;
    CharSequence localCharSequence = paramBundle.getCharSequence("body");
    String str1 = paramBundle.getString("subject");
    String[] arrayOfString1 = paramBundle.getStringArray("to-recipient-list");
    String[] arrayOfString2 = paramBundle.getStringArray("cc-recipient-list");
    String[] arrayOfString3 = paramBundle.getStringArray("bcc-recipient-list");
    String[] arrayOfString4 = paramBundle.getStringArray("attachment");
    String str2 = getMimeType("mail", false);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType(str2);
    localIntent.putExtra("android.intent.extra.TEXT", localCharSequence);
    localIntent.putExtra("android.intent.extra.SUBJECT", str1);
    localIntent.putExtra("android.intent.extra.EMAIL", arrayOfString1);
    localIntent.putExtra("android.intent.extra.CC", arrayOfString2);
    localIntent.putExtra("android.intent.extra.BCC", arrayOfString3);
    ArrayList localArrayList;
    int j;
    if (arrayOfString4 != null)
    {
      localIntent.setAction("android.intent.action.SEND_MULTIPLE");
      localArrayList = new ArrayList();
      j = arrayOfString4.length;
    }
    for (;;)
    {
      if (i >= j)
      {
        localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", localArrayList);
        startActivityForResult(localIntent, 2);
        return;
      }
      localArrayList.add(Uri.parse(arrayOfString4[i]));
      i++;
    }
  }
  
  void shareWithSMS(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("message");
    String[] arrayOfString = paramBundle.getStringArray("to-recipient-list");
    String str2 = "";
    int i;
    if (arrayOfString != null) {
      i = arrayOfString.length;
    }
    for (int j = 0;; j++)
    {
      if (j >= i)
      {
        Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str2));
        localIntent.putExtra("sms_body", str1);
        startActivityForResult(localIntent, 3);
        return;
      }
      String str3 = arrayOfString[j];
      str2 = str2 + str3 + ";";
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/sharing/SharingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */