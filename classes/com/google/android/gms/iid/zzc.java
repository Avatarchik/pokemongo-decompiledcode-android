package com.google.android.gms.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class zzc
{
  static String zzaDN = null;
  static int zzaDO = 0;
  static int zzaDP = 0;
  static int zzaDQ = 0;
  Context context;
  Messenger zzaCA;
  PendingIntent zzaCw;
  Map<String, Object> zzaDR = new HashMap();
  Messenger zzaDS;
  MessengerCompat zzaDT;
  long zzaDU;
  long zzaDV;
  int zzaDW;
  int zzaDX;
  long zzaDY;
  
  public zzc(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private void zzA(Object paramObject)
  {
    synchronized (getClass())
    {
      Iterator localIterator = this.zzaDR.keySet().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject2 = this.zzaDR.get(str);
        this.zzaDR.put(str, paramObject);
        zze(localObject2, paramObject);
      }
    }
  }
  
  static String zza(KeyPair paramKeyPair, String... paramVarArgs)
  {
    Object localObject = null;
    for (;;)
    {
      try
      {
        arrayOfByte = TextUtils.join("\n", paramVarArgs).getBytes("UTF-8");
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        byte[] arrayOfByte;
        PrivateKey localPrivateKey;
        Signature localSignature;
        String str2;
        Log.e("InstanceID/Rpc", "Unable to encode string", localUnsupportedEncodingException);
        continue;
        String str1 = "SHA256withECDSA";
        continue;
      }
      try
      {
        localPrivateKey = paramKeyPair.getPrivate();
        if (!(localPrivateKey instanceof RSAPrivateKey)) {
          continue;
        }
        str1 = "SHA256withRSA";
        localSignature = Signature.getInstance(str1);
        localSignature.initSign(localPrivateKey);
        localSignature.update(arrayOfByte);
        str2 = InstanceID.zzm(localSignature.sign());
        localObject = str2;
      }
      catch (GeneralSecurityException localGeneralSecurityException)
      {
        Log.e("InstanceID/Rpc", "Unable to sign registration request", localGeneralSecurityException);
      }
    }
    return (String)localObject;
  }
  
  public static String zzaE(Context paramContext)
  {
    String str;
    if (zzaDN != null)
    {
      str = zzaDN;
      return str;
    }
    zzaDO = Process.myUid();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
    for (;;)
    {
      ResolveInfo localResolveInfo;
      if (localIterator.hasNext())
      {
        localResolveInfo = (ResolveInfo)localIterator.next();
        if (localPackageManager.checkPermission("com.google.android.c2dm.permission.RECEIVE", localResolveInfo.serviceInfo.packageName) != 0) {}
      }
      try
      {
        ApplicationInfo localApplicationInfo3 = localPackageManager.getApplicationInfo(localResolveInfo.serviceInfo.packageName, 0);
        Log.w("InstanceID/Rpc", "Found " + localApplicationInfo3.uid);
        zzaDP = localApplicationInfo3.uid;
        zzaDN = localResolveInfo.serviceInfo.packageName;
        str = zzaDN;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException3) {}
      Log.w("InstanceID/Rpc", "Possible malicious package " + localResolveInfo.serviceInfo.packageName + " declares " + "com.google.android.c2dm.intent.REGISTER" + " without permission");
      continue;
      Log.w("InstanceID/Rpc", "Failed to resolve REGISTER intent, falling back");
      try
      {
        ApplicationInfo localApplicationInfo2 = localPackageManager.getApplicationInfo("com.google.android.gms", 0);
        zzaDN = localApplicationInfo2.packageName;
        zzaDP = localApplicationInfo2.uid;
        str = zzaDN;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        try
        {
          ApplicationInfo localApplicationInfo1 = localPackageManager.getApplicationInfo("com.google.android.gsf", 0);
          zzaDN = localApplicationInfo1.packageName;
          zzaDP = localApplicationInfo1.uid;
          str = zzaDN;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2)
        {
          Log.w("InstanceID/Rpc", "Both Google Play Services and legacy GSF package are missing");
          str = null;
        }
      }
      break;
    }
  }
  
  private Intent zzb(Bundle paramBundle, KeyPair paramKeyPair)
    throws IOException
  {
    ConditionVariable localConditionVariable = new ConditionVariable();
    String str = zzws();
    synchronized (getClass())
    {
      this.zzaDR.put(str, localConditionVariable);
      zza(paramBundle, paramKeyPair, str);
      localConditionVariable.block(30000L);
    }
    Object localObject3;
    synchronized (getClass())
    {
      localObject3 = this.zzaDR.remove(str);
      if ((localObject3 instanceof Intent))
      {
        Intent localIntent = (Intent)localObject3;
        return localIntent;
        localObject1 = finally;
        throw ((Throwable)localObject1);
      }
      if ((localObject3 instanceof String)) {
        throw new IOException((String)localObject3);
      }
    }
    Log.w("InstanceID/Rpc", "No response " + localObject3);
    throw new IOException("TIMEOUT");
  }
  
  private void zzdn(String paramString)
  {
    if (!"com.google.android.gsf".equals(zzaDN)) {}
    for (;;)
    {
      return;
      this.zzaDW = (1 + this.zzaDW);
      if (this.zzaDW >= 3)
      {
        if (this.zzaDW == 3) {
          this.zzaDX = (1000 + new Random().nextInt(1000));
        }
        this.zzaDX = (2 * this.zzaDX);
        this.zzaDY = (SystemClock.elapsedRealtime() + this.zzaDX);
        Log.w("InstanceID/Rpc", "Backoff due to " + paramString + " for " + this.zzaDX);
      }
    }
  }
  
  private void zze(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 instanceof ConditionVariable)) {
      ((ConditionVariable)paramObject1).open();
    }
    Messenger localMessenger;
    Message localMessage;
    if ((paramObject1 instanceof Messenger))
    {
      localMessenger = (Messenger)paramObject1;
      localMessage = Message.obtain();
      localMessage.obj = paramObject2;
    }
    try
    {
      localMessenger.send(localMessage);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("InstanceID/Rpc", "Failed to send response " + localRemoteException);
      }
    }
  }
  
  private void zzi(String paramString, Object paramObject)
  {
    synchronized (getClass())
    {
      Object localObject2 = this.zzaDR.get(paramString);
      this.zzaDR.put(paramString, paramObject);
      zze(localObject2, paramObject);
      return;
    }
  }
  
  /**
   * @deprecated
   */
  public static String zzws()
  {
    try
    {
      int i = zzaDQ;
      zzaDQ = i + 1;
      String str = Integer.toString(i);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  Intent zza(Bundle paramBundle, KeyPair paramKeyPair)
    throws IOException
  {
    Intent localIntent = zzb(paramBundle, paramKeyPair);
    if ((localIntent != null) && (localIntent.hasExtra("google.messenger"))) {
      localIntent = zzb(paramBundle, paramKeyPair);
    }
    return localIntent;
  }
  
  void zza(Bundle paramBundle, KeyPair paramKeyPair, String paramString)
    throws IOException
  {
    long l = SystemClock.elapsedRealtime();
    if ((this.zzaDY != 0L) && (l <= this.zzaDY))
    {
      Log.w("InstanceID/Rpc", "Backoff mode, next request attempt: " + (this.zzaDY - l) + " interval: " + this.zzaDX);
      throw new IOException("RETRY_LATER");
    }
    zzwr();
    if (zzaDN == null) {
      throw new IOException("MISSING_INSTANCEID_SERVICE");
    }
    this.zzaDU = SystemClock.elapsedRealtime();
    Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent.setPackage(zzaDN);
    paramBundle.putString("gmsv", Integer.toString(GoogleCloudMessaging.zzaB(this.context)));
    paramBundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
    paramBundle.putString("app_ver", Integer.toString(InstanceID.zzaC(this.context)));
    paramBundle.putString("cliv", "1");
    paramBundle.putString("appid", InstanceID.zza(paramKeyPair));
    String str = InstanceID.zzm(paramKeyPair.getPublic().getEncoded());
    paramBundle.putString("pub2", str);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = this.context.getPackageName();
    arrayOfString[1] = str;
    paramBundle.putString("sig", zza(paramKeyPair, arrayOfString));
    localIntent.putExtras(paramBundle);
    zzo(localIntent);
    zzb(localIntent, paramString);
  }
  
  protected void zzb(Intent paramIntent, String paramString)
  {
    this.zzaDU = SystemClock.elapsedRealtime();
    paramIntent.putExtra("kid", "|ID|" + paramString + "|");
    paramIntent.putExtra("X-kid", "|ID|" + paramString + "|");
    boolean bool = "com.google.android.gsf".equals(zzaDN);
    String str = paramIntent.getStringExtra("useGsf");
    if (str != null) {
      bool = "1".equals(str);
    }
    if (Log.isLoggable("InstanceID/Rpc", 3)) {
      Log.d("InstanceID/Rpc", "Sending " + paramIntent.getExtras());
    }
    Message localMessage2;
    if (this.zzaDS != null)
    {
      paramIntent.putExtra("google.messenger", this.zzaCA);
      localMessage2 = Message.obtain();
      localMessage2.obj = paramIntent;
    }
    for (;;)
    {
      try
      {
        this.zzaDS.send(localMessage2);
        return;
      }
      catch (RemoteException localRemoteException2)
      {
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
          Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
        }
      }
      if (bool)
      {
        Intent localIntent = new Intent("com.google.android.gms.iid.InstanceID");
        localIntent.setPackage(this.context.getPackageName());
        localIntent.putExtra("GSF", paramIntent);
        this.context.startService(localIntent);
      }
      else
      {
        paramIntent.putExtra("google.messenger", this.zzaCA);
        paramIntent.putExtra("messenger2", "1");
        if (this.zzaDT != null)
        {
          Message localMessage1 = Message.obtain();
          localMessage1.obj = paramIntent;
          try
          {
            this.zzaDT.send(localMessage1);
          }
          catch (RemoteException localRemoteException1)
          {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
              Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
            }
          }
        }
        else
        {
          this.context.startService(paramIntent);
        }
      }
    }
  }
  
  public void zze(Message paramMessage)
  {
    if (paramMessage == null) {}
    for (;;)
    {
      return;
      if ((paramMessage.obj instanceof Intent))
      {
        Intent localIntent = (Intent)paramMessage.obj;
        localIntent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
        if (localIntent.hasExtra("google.messenger"))
        {
          Parcelable localParcelable = localIntent.getParcelableExtra("google.messenger");
          if ((localParcelable instanceof MessengerCompat)) {
            this.zzaDT = ((MessengerCompat)localParcelable);
          }
          if ((localParcelable instanceof Messenger)) {
            this.zzaDS = ((Messenger)localParcelable);
          }
        }
        zzr((Intent)paramMessage.obj);
      }
      else
      {
        Log.w("InstanceID/Rpc", "Dropping invalid message");
      }
    }
  }
  
  /**
   * @deprecated
   */
  void zzo(Intent paramIntent)
  {
    try
    {
      if (this.zzaCw == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        this.zzaCw = PendingIntent.getBroadcast(this.context, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", this.zzaCw);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  String zzp(Intent paramIntent)
    throws IOException
  {
    if (paramIntent == null) {
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    String str1 = paramIntent.getStringExtra("registration_id");
    if (str1 == null) {
      str1 = paramIntent.getStringExtra("unregistered");
    }
    paramIntent.getLongExtra("Retry-After", 0L);
    if ((str1 == null) || (str1 == null))
    {
      String str2 = paramIntent.getStringExtra("error");
      if (str2 != null) {
        throw new IOException(str2);
      }
      Log.w("InstanceID/Rpc", "Unexpected response from GCM " + paramIntent.getExtras(), new Throwable());
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    return str1;
  }
  
  void zzq(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("error");
    if (str1 == null) {
      Log.w("InstanceID/Rpc", "Unexpected response, no error or registration id " + paramIntent.getExtras());
    }
    for (;;)
    {
      return;
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        Log.d("InstanceID/Rpc", "Received InstanceID error " + str1);
      }
      String str2 = null;
      if (str1.startsWith("|"))
      {
        String[] arrayOfString = str1.split("\\|");
        if (!"ID".equals(arrayOfString[1])) {
          Log.w("InstanceID/Rpc", "Unexpected structured response " + str1);
        }
        if (arrayOfString.length > 2)
        {
          str2 = arrayOfString[2];
          str1 = arrayOfString[3];
          if (str1.startsWith(":")) {
            str1 = str1.substring(1);
          }
          label170:
          paramIntent.putExtra("error", str1);
        }
      }
      else
      {
        if (str2 != null) {
          break label275;
        }
        zzA(str1);
      }
      for (;;)
      {
        long l = paramIntent.getLongExtra("Retry-After", 0L);
        if (l <= 0L) {
          break label284;
        }
        this.zzaDV = SystemClock.elapsedRealtime();
        this.zzaDX = (1000 * (int)l);
        this.zzaDY = (SystemClock.elapsedRealtime() + this.zzaDX);
        Log.w("InstanceID/Rpc", "Explicit request from server to backoff: " + this.zzaDX);
        break;
        str1 = "UNKNOWN";
        break label170;
        label275:
        zzi(str2, str1);
      }
      label284:
      if (("SERVICE_NOT_AVAILABLE".equals(str1)) || ("AUTHENTICATION_FAILED".equals(str1))) {
        zzdn(str1);
      }
    }
  }
  
  void zzr(Intent paramIntent)
  {
    if (paramIntent == null) {
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        Log.d("InstanceID/Rpc", "Unexpected response: null");
      }
    }
    for (;;)
    {
      return;
      String str1 = paramIntent.getAction();
      if (("com.google.android.c2dm.intent.REGISTRATION".equals(str1)) || ("com.google.android.gms.iid.InstanceID".equals(str1))) {
        break;
      }
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        Log.d("InstanceID/Rpc", "Unexpected response " + paramIntent.getAction());
      }
    }
    String str2 = paramIntent.getStringExtra("registration_id");
    if (str2 == null) {}
    for (String str3 = paramIntent.getStringExtra("unregistered");; str3 = str2)
    {
      if (str3 == null)
      {
        zzq(paramIntent);
        break;
      }
      this.zzaDU = SystemClock.elapsedRealtime();
      this.zzaDY = 0L;
      this.zzaDW = 0;
      this.zzaDX = 0;
      if (Log.isLoggable("InstanceID/Rpc", 3)) {
        Log.d("InstanceID/Rpc", "AppIDResponse: " + str3 + " " + paramIntent.getExtras());
      }
      Object localObject = null;
      String[] arrayOfString;
      String str4;
      if (str3.startsWith("|"))
      {
        arrayOfString = str3.split("\\|");
        if (!"ID".equals(arrayOfString[1])) {
          Log.w("InstanceID/Rpc", "Unexpected structured response " + str3);
        }
        str4 = arrayOfString[2];
        if (arrayOfString.length > 4)
        {
          if (!"SYNC".equals(arrayOfString[3])) {
            break label348;
          }
          InstanceIDListenerService.zzaD(this.context);
        }
      }
      label348:
      while (!"RST".equals(arrayOfString[3]))
      {
        String str5 = arrayOfString[(-1 + arrayOfString.length)];
        if (str5.startsWith(":")) {
          str5 = str5.substring(1);
        }
        paramIntent.putExtra("registration_id", str5);
        localObject = str4;
        if (localObject != null) {
          break label395;
        }
        zzA(paramIntent);
        break;
      }
      InstanceIDListenerService.zza(this.context, InstanceID.getInstance(this.context).zzwo());
      paramIntent.removeExtra("registration_id");
      zzi(str4, paramIntent);
      break;
      label395:
      zzi((String)localObject, paramIntent);
      break;
    }
  }
  
  void zzwr()
  {
    if (this.zzaCA != null) {}
    for (;;)
    {
      return;
      zzaE(this.context);
      this.zzaCA = new Messenger(new Handler(Looper.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          zzc.this.zze(paramAnonymousMessage);
        }
      });
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/iid/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */