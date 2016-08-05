package com.google.vr.cardboard;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UiUtils
{
  private static final String CARDBOARD_CONFIGURE_ACTION = "com.google.vrtoolkit.cardboard.CONFIGURE";
  private static final String CARDBOARD_WEBSITE = "http://google.com/cardboard/cfg";
  private static final String INTENT_KEY = "intent";
  
  static void launchOrInstallCardboard(Context paramContext)
  {
    launchOrInstallCardboard(paramContext, true);
  }
  
  public static void launchOrInstallCardboard(Context paramContext, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Intent localIntent1 = new Intent();
    localIntent1.setAction("com.google.vrtoolkit.cardboard.CONFIGURE");
    List localList = localPackageManager.queryIntentActivities(localIntent1, 0);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
      String str = localResolveInfo.activityInfo.packageName;
      if (str.startsWith("com.google."))
      {
        Intent localIntent3 = new Intent(localIntent1);
        localIntent3.setClassName(str, localResolveInfo.activityInfo.name);
        localArrayList.add(localIntent3);
      }
    }
    if (localArrayList.isEmpty()) {
      showInstallDialog(paramContext);
    }
    for (;;)
    {
      return;
      if (localArrayList.size() == 1) {}
      for (Intent localIntent2 = (Intent)localArrayList.get(0);; localIntent2 = localIntent1)
      {
        if (!paramBoolean) {
          break label185;
        }
        showConfigureDialog(paramContext, localIntent2);
        break;
      }
      label185:
      paramContext.startActivity(localIntent2);
    }
  }
  
  private static void showConfigureDialog(Context paramContext, Intent paramIntent)
  {
    FragmentManager localFragmentManager = ((Activity)paramContext).getFragmentManager();
    ConfigureSettingsDialogFragment localConfigureSettingsDialogFragment = new ConfigureSettingsDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("intent", paramIntent);
    localConfigureSettingsDialogFragment.setArguments(localBundle);
    localConfigureSettingsDialogFragment.show(localFragmentManager, "ConfigureCardboardDialog");
  }
  
  private static void showInstallDialog(Context paramContext)
  {
    FragmentManager localFragmentManager = ((Activity)paramContext).getFragmentManager();
    new InstallSettingsDialogFragment().show(localFragmentManager, "InstallCardboardDialog");
  }
  
  public static class ConfigureSettingsDialogFragment
    extends DialogFragment
  {
    private Intent intent;
    private final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          UiUtils.ConfigureSettingsDialogFragment.this.getActivity().startActivity(UiUtils.ConfigureSettingsDialogFragment.this.intent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          for (;;)
          {
            UiUtils.showInstallDialog(UiUtils.ConfigureSettingsDialogFragment.this.getActivity());
          }
        }
      }
    };
    
    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      this.intent = ((Intent)getArguments().getParcelable("intent"));
    }
    
    public Dialog onCreateDialog(Bundle paramBundle)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
      localBuilder.setTitle(Strings.getString("DIALOG_TITLE")).setMessage(Strings.getString("DIALOG_MESSAGE_SETUP")).setPositiveButton(Strings.getString("SETUP_BUTTON"), this.listener).setNegativeButton(Strings.getString("CANCEL_BUTTON"), null);
      return localBuilder.create();
    }
  }
  
  public static class InstallSettingsDialogFragment
    extends DialogFragment
  {
    private final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          UiUtils.InstallSettingsDialogFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://google.com/cardboard/cfg")));
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          for (;;)
          {
            Toast.makeText(UiUtils.InstallSettingsDialogFragment.this.getActivity().getApplicationContext(), Strings.getString("NO_BROWSER_TEXT"), 1).show();
          }
        }
      }
    };
    
    public Dialog onCreateDialog(Bundle paramBundle)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
      localBuilder.setTitle(Strings.getString("DIALOG_TITLE")).setMessage(Strings.getString("DIALOG_MESSAGE_NO_CARDBOARD")).setPositiveButton(Strings.getString("GO_TO_PLAYSTORE_BUTTON"), this.listener).setNegativeButton(Strings.getString("CANCEL_BUTTON"), null);
      return localBuilder.create();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/vr/cardboard/UiUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */