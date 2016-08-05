package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.zzlr;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzmy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzb
{
  private static final Object zzafW = new Object();
  private static Integer zzahE;
  private static zzb zzahy;
  private final List<String> zzahA;
  private final List<String> zzahB;
  private final List<String> zzahC;
  private zze zzahD;
  private zze zzahF;
  private final List<String> zzahz;
  
  private zzb()
  {
    if (getLogLevel() == zzd.LOG_LEVEL_OFF)
    {
      this.zzahz = Collections.EMPTY_LIST;
      this.zzahA = Collections.EMPTY_LIST;
      this.zzahB = Collections.EMPTY_LIST;
      this.zzahC = Collections.EMPTY_LIST;
      return;
    }
    String str1 = (String)zzc.zza.zzahI.get();
    List localList1;
    label60:
    String str2;
    List localList2;
    label84:
    String str3;
    List localList3;
    label111:
    String str4;
    if (str1 == null)
    {
      localList1 = Collections.EMPTY_LIST;
      this.zzahz = localList1;
      str2 = (String)zzc.zza.zzahJ.get();
      if (str2 != null) {
        break label212;
      }
      localList2 = Collections.EMPTY_LIST;
      this.zzahA = localList2;
      str3 = (String)zzc.zza.zzahK.get();
      if (str3 != null) {
        break label226;
      }
      localList3 = Collections.EMPTY_LIST;
      this.zzahB = localList3;
      str4 = (String)zzc.zza.zzahL.get();
      if (str4 != null) {
        break label241;
      }
    }
    label212:
    label226:
    label241:
    for (List localList4 = Collections.EMPTY_LIST;; localList4 = Arrays.asList(str4.split(",")))
    {
      this.zzahC = localList4;
      this.zzahD = new zze(1024, ((Long)zzc.zza.zzahM.get()).longValue());
      this.zzahF = new zze(1024, ((Long)zzc.zza.zzahM.get()).longValue());
      break;
      localList1 = Arrays.asList(str1.split(","));
      break label60;
      localList2 = Arrays.asList(str2.split(","));
      break label84;
      localList3 = Arrays.asList(str3.split(","));
      break label111;
    }
  }
  
  private static int getLogLevel()
  {
    if (zzahE == null) {}
    try
    {
      if (zzmm.zzjA()) {}
      for (int i = ((Integer)zzc.zza.zzahH.get()).intValue();; i = zzd.LOG_LEVEL_OFF)
      {
        zzahE = Integer.valueOf(i);
        return zzahE.intValue();
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        zzahE = Integer.valueOf(zzd.LOG_LEVEL_OFF);
      }
    }
  }
  
  private void zza(Context paramContext, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    long l1 = System.currentTimeMillis();
    String str = null;
    if (((getLogLevel() & zzd.zzahR) != 0) && (paramInt != 13)) {
      str = zzmy.zzl(3, 5);
    }
    long l2 = 0L;
    if ((getLogLevel() & zzd.zzahT) != 0) {
      l2 = Debug.getNativeHeapAllocatedSize();
    }
    if ((paramInt == 1) || (paramInt == 4) || (paramInt == 14)) {}
    for (ConnectionEvent localConnectionEvent = new ConnectionEvent(l1, paramInt, null, null, null, null, str, paramString1, SystemClock.elapsedRealtime(), l2);; localConnectionEvent = new ConnectionEvent(l1, paramInt, paramString2, paramString3, paramString4, paramString5, str, paramString1, SystemClock.elapsedRealtime(), l2))
    {
      paramContext.startService(new Intent().setComponent(zzd.zzahN).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", localConnectionEvent));
      return;
    }
  }
  
  private void zza(Context paramContext, String paramString1, String paramString2, Intent paramIntent, int paramInt)
  {
    String str1 = null;
    if ((!zzqi()) || (this.zzahD == null)) {}
    do
    {
      return;
      if ((paramInt != 4) && (paramInt != 1)) {
        break;
      }
    } while (!this.zzahD.zzcy(paramString1));
    String str2 = null;
    String str3 = null;
    for (;;)
    {
      zza(paramContext, paramString1, paramInt, str1, paramString2, str3, str2);
      break;
      ServiceInfo localServiceInfo = zzd(paramContext, paramIntent);
      if (localServiceInfo == null)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString2;
        arrayOfObject[1] = paramIntent.toUri(0);
        Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", arrayOfObject));
        break;
      }
      str3 = localServiceInfo.processName;
      str2 = localServiceInfo.name;
      str1 = zzmy.zzaq(paramContext);
      if (!zzb(str1, paramString2, str3, str2)) {
        break;
      }
      this.zzahD.zzcx(paramString1);
    }
  }
  
  private String zzb(ServiceConnection paramServiceConnection)
  {
    return String.valueOf(Process.myPid() << 32 | System.identityHashCode(paramServiceConnection));
  }
  
  private boolean zzb(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    int i = getLogLevel();
    if ((this.zzahz.contains(paramString1)) || (this.zzahA.contains(paramString2)) || (this.zzahB.contains(paramString3)) || (this.zzahC.contains(paramString4)) || ((paramString3.equals(paramString1)) && ((i & zzd.zzahS) != 0))) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private boolean zzc(Context paramContext, Intent paramIntent)
  {
    ComponentName localComponentName = paramIntent.getComponent();
    if ((localComponentName == null) || ((com.google.android.gms.common.internal.zzd.zzaeK) && ("com.google.android.gms".equals(localComponentName.getPackageName())))) {}
    for (boolean bool = false;; bool = zzmm.zzl(paramContext, localComponentName.getPackageName())) {
      return bool;
    }
  }
  
  private static ServiceInfo zzd(Context paramContext, Intent paramIntent)
  {
    List localList = paramContext.getPackageManager().queryIntentServices(paramIntent, 128);
    ServiceInfo localServiceInfo;
    if ((localList == null) || (localList.size() == 0))
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramIntent.toUri(0);
      arrayOfObject1[1] = zzmy.zzl(3, 20);
      Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", arrayOfObject1));
      localServiceInfo = null;
    }
    for (;;)
    {
      return localServiceInfo;
      if (localList.size() > 1)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = paramIntent.toUri(0);
        arrayOfObject2[1] = zzmy.zzl(3, 20);
        Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", arrayOfObject2));
        Iterator localIterator = localList.iterator();
        if (localIterator.hasNext())
        {
          Log.w("ConnectionTracker", ((ResolveInfo)localIterator.next()).serviceInfo.name);
          localServiceInfo = null;
          continue;
        }
      }
      localServiceInfo = ((ResolveInfo)localList.get(0)).serviceInfo;
    }
  }
  
  public static zzb zzqh()
  {
    synchronized (zzafW)
    {
      if (zzahy == null) {
        zzahy = new zzb();
      }
      return zzahy;
    }
  }
  
  private boolean zzqi()
  {
    boolean bool = false;
    if (!com.google.android.gms.common.internal.zzd.zzaeK) {}
    for (;;)
    {
      return bool;
      if (getLogLevel() != zzd.LOG_LEVEL_OFF) {
        bool = true;
      }
    }
  }
  
  public void zza(Context paramContext, ServiceConnection paramServiceConnection)
  {
    paramContext.unbindService(paramServiceConnection);
    zza(paramContext, zzb(paramServiceConnection), null, null, 1);
  }
  
  public void zza(Context paramContext, ServiceConnection paramServiceConnection, String paramString, Intent paramIntent)
  {
    zza(paramContext, zzb(paramServiceConnection), paramString, paramIntent, 3);
  }
  
  public boolean zza(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    return zza(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  public boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    if (zzc(paramContext, paramIntent)) {
      Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
    }
    boolean bool1;
    for (boolean bool2 = false;; bool2 = bool1)
    {
      return bool2;
      bool1 = paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
      if (bool1) {
        zza(paramContext, zzb(paramServiceConnection), paramString, paramIntent, 2);
      }
    }
  }
  
  public void zzb(Context paramContext, ServiceConnection paramServiceConnection)
  {
    zza(paramContext, zzb(paramServiceConnection), null, null, 4);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/stats/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */