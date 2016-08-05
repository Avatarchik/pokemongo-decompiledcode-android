package com.crittercism.app;

import android.content.Context;
import android.content.res.AssetManager;
import crittercism.android.dx;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CrittercismNDK
{
  private static final String LEGACY_SO_FILE_NAME = "libcrittercism-ndk.so";
  private static final String LIBRARY_NAME = "crittercism-v3";
  private static final String SO_FILE_NAME = "libcrittercism-v3.so";
  private static boolean isNdkInstalled = false;
  
  public static boolean copyLibFromAssets(Context paramContext, File paramFile)
  {
    boolean bool = false;
    dx.b();
    FileOutputStream localFileOutputStream;
    InputStream localInputStream;
    try
    {
      paramFile.getParentFile().mkdirs();
      localFileOutputStream = new FileOutputStream(paramFile);
      localInputStream = getJarredLibFileStream(paramContext);
      byte[] arrayOfByte = new byte[' '];
      for (;;)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i < 0) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      return bool;
    }
    catch (Exception localException)
    {
      dx.b("Could not install breakpad library: " + localException.toString());
    }
    for (;;)
    {
      localInputStream.close();
      localFileOutputStream.close();
      bool = true;
    }
  }
  
  public static boolean doNdkSharedLibrariesExist(Context paramContext)
  {
    boolean bool = false;
    try
    {
      getJarredLibFileStream(paramContext);
      bool = true;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    return bool;
  }
  
  public static File getInstalledLibraryFile(Context paramContext)
  {
    String str = paramContext.getFilesDir().getAbsolutePath() + "/com.crittercism/lib/";
    return new File(str + "libcrittercism-v3.so");
  }
  
  public static InputStream getJarredLibFileStream(Context paramContext)
  {
    String str = "armeabi";
    if (System.getProperty("os.arch").contains("v7")) {
      str = str + "-v7a";
    }
    return paramContext.getAssets().open(str + "/libcrittercism-v3.so");
  }
  
  public static native boolean installNdk(String paramString);
  
  public static void installNdkLib(Context paramContext, String paramString)
  {
    boolean bool = true;
    String str = paramContext.getFilesDir().getAbsolutePath() + "/" + paramString;
    if (doNdkSharedLibrariesExist(paramContext))
    {
      bool = loadLibraryFromAssets(paramContext);
      if (bool) {
        break label64;
      }
    }
    for (;;)
    {
      return;
      try
      {
        System.loadLibrary("crittercism-v3");
      }
      catch (Throwable localThrowable1)
      {
        bool = false;
      }
      break;
      try
      {
        label64:
        if (installNdk(str))
        {
          new File(str).mkdirs();
          isNdkInstalled = true;
        }
        else
        {
          dx.c("Unable to initialize NDK crash reporting.");
        }
      }
      catch (Throwable localThrowable2) {}
    }
  }
  
  public static boolean isNdkCrashReportingInstalled()
  {
    return isNdkInstalled;
  }
  
  public static boolean loadLibraryFromAssets(Context paramContext)
  {
    boolean bool = false;
    File localFile1 = new File(paramContext.getFilesDir(), "/com.crittercism/lib/");
    File localFile2 = new File(localFile1, "libcrittercism-v3.so");
    File localFile3 = new File(localFile1, "libcrittercism-ndk.so");
    if (!localFile2.exists()) {
      if (!copyLibFromAssets(paramContext, localFile2)) {
        localFile2.delete();
      }
    }
    for (;;)
    {
      return bool;
      localFile3.delete();
      try
      {
        System.load(localFile2.getAbsolutePath());
        bool = true;
      }
      catch (Throwable localThrowable)
      {
        dx.a("Unable to install NDK library", localThrowable);
        localFile2.delete();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/app/CrittercismNDK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */