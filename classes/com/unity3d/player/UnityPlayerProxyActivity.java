package com.unity3d.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class UnityPlayerProxyActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = new Intent(this, UnityPlayerActivity.class);
    localIntent.addFlags(65536);
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null) {
      localIntent.putExtras(localBundle);
    }
    startActivity(localIntent);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/UnityPlayerProxyActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */