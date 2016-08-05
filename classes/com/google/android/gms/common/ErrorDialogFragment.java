package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzx;

public class ErrorDialogFragment
  extends DialogFragment
{
  private Dialog mDialog = null;
  private DialogInterface.OnCancelListener zzaaa = null;
  
  public static ErrorDialogFragment newInstance(Dialog paramDialog)
  {
    return newInstance(paramDialog, null);
  }
  
  public static ErrorDialogFragment newInstance(Dialog paramDialog, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    ErrorDialogFragment localErrorDialogFragment = new ErrorDialogFragment();
    Dialog localDialog = (Dialog)zzx.zzb(paramDialog, "Cannot display null dialog");
    localDialog.setOnCancelListener(null);
    localDialog.setOnDismissListener(null);
    localErrorDialogFragment.mDialog = localDialog;
    if (paramOnCancelListener != null) {
      localErrorDialogFragment.zzaaa = paramOnCancelListener;
    }
    return localErrorDialogFragment;
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (this.zzaaa != null) {
      this.zzaaa.onCancel(paramDialogInterface);
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    if (this.mDialog == null) {
      setShowsDialog(false);
    }
    return this.mDialog;
  }
  
  public void show(FragmentManager paramFragmentManager, String paramString)
  {
    super.show(paramFragmentManager, paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/ErrorDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */