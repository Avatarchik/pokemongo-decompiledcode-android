package com.voxelbusters.nativeplugins.features.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.voxelbusters.nativeplugins.NativePluginHelper;
import com.voxelbusters.nativeplugins.utilities.StringUtility;
import java.util.HashMap;

public class UiActivity
  extends Activity
{
  AlertDialog alertDialog;
  Bundle bundleInfo;
  boolean paused;
  
  private void showAlertDialog(Bundle paramBundle)
  {
    int i = 3;
    final String str = paramBundle.getString("tag");
    final String[] arrayOfString = paramBundle.getStringArray("button-list");
    this.alertDialog = getDialogWithDefaultDetails(paramBundle);
    if (arrayOfString.length > i) {}
    for (final int j = 0;; j++)
    {
      if (j >= i)
      {
        this.alertDialog.show();
        return;
        i = arrayOfString.length;
        break;
      }
      int k = -1 - j;
      DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          String str1 = arrayOfString[j];
          String str2 = str;
          HashMap localHashMap = new HashMap();
          localHashMap.put("button-pressed", str1);
          localHashMap.put("caller", str2);
          NativePluginHelper.sendMessage("AlertDialogClosed", localHashMap);
          UiHandler.getInstance().onFinish(str2);
          UiActivity.this.finish();
        }
      };
      this.alertDialog.setButton(k, arrayOfString[j], local2);
    }
  }
  
  private void showLoginPrompt(Bundle paramBundle)
  {
    final String[] arrayOfString = paramBundle.getStringArray("button-list");
    setContentView(new LinearLayout(this));
    this.alertDialog = getDialogWithDefaultDetails(paramBundle);
    final EditText localEditText1 = new EditText(this);
    final EditText localEditText2 = new EditText(this);
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setOrientation(1);
    localLinearLayout.addView(localEditText1);
    localLinearLayout.addView(localEditText2);
    this.alertDialog.setView(localLinearLayout);
    String str1 = paramBundle.getString("place-holder-text-1");
    String str2 = paramBundle.getString("place-holder-text-2");
    localEditText2.setTransformationMethod(new PasswordTransformationMethod());
    localEditText1.setHint(str1);
    localEditText2.setHint(str2);
    int i;
    if (arrayOfString.length > 3) {
      i = 3;
    }
    for (final int j = 0;; j++)
    {
      if (j >= i)
      {
        this.alertDialog.show();
        return;
        i = arrayOfString.length;
        break;
      }
      int k = -1 - j;
      DialogInterface.OnClickListener local4 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("button-pressed", arrayOfString[j]);
          localHashMap.put("username", localEditText1.getText().toString());
          localHashMap.put("password", localEditText2.getText().toString());
          NativePluginHelper.sendMessage("LoginPromptDialogClosed", localHashMap);
          UiActivity.this.finish();
        }
      };
      this.alertDialog.setButton(k, arrayOfString[j], local4);
    }
  }
  
  private void showSinglePrompt(Bundle paramBundle)
  {
    int i = 3;
    final String[] arrayOfString = paramBundle.getStringArray("button-list");
    this.alertDialog = getDialogWithDefaultDetails(paramBundle);
    final EditText localEditText = new EditText(this);
    this.alertDialog.setView(localEditText);
    boolean bool = paramBundle.getBoolean("is-secure");
    String str = paramBundle.getString("place-holder-text-1");
    if (bool) {
      localEditText.setTransformationMethod(new PasswordTransformationMethod());
    }
    if (str != null) {
      localEditText.setHint(str);
    }
    if (arrayOfString.length > i) {}
    for (final int j = 0;; j++)
    {
      if (j >= i)
      {
        this.alertDialog.show();
        return;
        i = arrayOfString.length;
        break;
      }
      int k = -1 - j;
      DialogInterface.OnClickListener local3 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          String str = localEditText.getText().toString();
          HashMap localHashMap = new HashMap();
          localHashMap.put("button-pressed", arrayOfString[j]);
          localHashMap.put("input", str);
          NativePluginHelper.sendMessage("SingleFieldPromptDialogClosed", localHashMap);
          UiActivity.this.finish();
        }
      };
      this.alertDialog.setButton(k, arrayOfString[j], local3);
    }
  }
  
  AlertDialog getDialogWithDefaultDetails(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("title");
    String str2 = paramBundle.getString("message");
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    if (!StringUtility.isNullOrEmpty(str1)) {
      localBuilder.setTitle(str1);
    }
    if (!StringUtility.isNullOrEmpty(str2)) {
      localBuilder.setMessage(str2);
    }
    localBuilder.setCancelable(false);
    return localBuilder.create();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.bundleInfo == null) {
      this.bundleInfo = getIntent().getExtras();
    }
    eUiType localeUiType = eUiType.values()[this.bundleInfo.getInt("type")];
    if (localeUiType == eUiType.ALERT_DIALOG) {
      showAlertDialog(this.bundleInfo);
    }
    for (;;)
    {
      return;
      if (localeUiType == eUiType.SINGLE_FIELD_PROMPT) {
        showSinglePrompt(this.bundleInfo);
      } else if (localeUiType == eUiType.LOGIN_PROMPT) {
        showLoginPrompt(this.bundleInfo);
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.alertDialog != null)
    {
      this.alertDialog.dismiss();
      this.alertDialog = null;
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.paused = true;
  }
  
  @SuppressLint({"NewApi"})
  protected void onResume()
  {
    super.onResume();
    if (this.paused)
    {
      finish();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          UiActivity.this.startActivity(UiActivity.this.getIntent());
        }
      }, 10L);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/ui/UiActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */