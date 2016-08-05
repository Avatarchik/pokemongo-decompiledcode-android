package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.regex.Pattern;

public class VersionUtil
{
  private static final Pattern V_SEP = Pattern.compile("[-_./;:]");
  private final Version _v;
  
  protected VersionUtil()
  {
    Object localObject = null;
    try
    {
      Version localVersion = versionFor(getClass());
      localObject = localVersion;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.err.println("ERROR: Failed to load Version information from " + getClass());
      }
    }
    if (localObject == null) {
      localObject = Version.unknownVersion();
    }
    this._v = ((Version)localObject);
  }
  
  private static final void _close(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  @Deprecated
  public static Version mavenVersionFor(ClassLoader paramClassLoader, String paramString1, String paramString2)
  {
    localInputStream = paramClassLoader.getResourceAsStream("META-INF/maven/" + paramString1.replaceAll("\\.", "/") + "/" + paramString2 + "/pom.properties");
    if (localInputStream != null) {}
    try
    {
      Properties localProperties = new Properties();
      localProperties.load(localInputStream);
      String str1 = localProperties.getProperty("version");
      String str2 = localProperties.getProperty("artifactId");
      Version localVersion2 = parseVersion(str1, localProperties.getProperty("groupId"), str2);
      localVersion1 = localVersion2;
      _close(localInputStream);
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException = localIOException;
        _close(localInputStream);
        Version localVersion1 = Version.unknownVersion();
      }
    }
    finally
    {
      localObject = finally;
      _close(localInputStream);
      throw ((Throwable)localObject);
    }
    return localVersion1;
  }
  
  public static Version packageVersionFor(Class<?> paramClass)
  {
    for (;;)
    {
      try
      {
        localClass = Class.forName(paramClass.getPackage().getName() + ".PackageVersion", true, paramClass.getClassLoader());
      }
      catch (Exception localException1)
      {
        Class localClass;
        Version localVersion2;
        Version localVersion1 = null;
        continue;
      }
      try
      {
        localVersion2 = ((Versioned)localClass.newInstance()).version();
        localVersion1 = localVersion2;
        return localVersion1;
      }
      catch (Exception localException2)
      {
        throw new IllegalArgumentException("Failed to get Versioned out of " + localClass);
      }
    }
  }
  
  public static Version parseVersion(String paramString1, String paramString2, String paramString3)
  {
    String str1 = null;
    int i = 0;
    int j;
    int k;
    if (paramString1 != null)
    {
      String str2 = paramString1.trim();
      if (str2.length() > 0)
      {
        String[] arrayOfString = V_SEP.split(str2);
        j = parseVersionPart(arrayOfString[0]);
        if (arrayOfString.length > 1)
        {
          k = parseVersionPart(arrayOfString[1]);
          if (arrayOfString.length > 2) {
            i = parseVersionPart(arrayOfString[2]);
          }
          if (arrayOfString.length > 3) {
            str1 = arrayOfString[3];
          }
        }
      }
    }
    for (Version localVersion = new Version(j, k, i, str1, paramString2, paramString3);; localVersion = null)
    {
      return localVersion;
      k = 0;
      break;
    }
  }
  
  protected static int parseVersionPart(String paramString)
  {
    int i = 0;
    int j = 0;
    int k = paramString.length();
    for (;;)
    {
      int m;
      if (j < k)
      {
        m = paramString.charAt(j);
        if ((m <= 57) && (m >= 48)) {}
      }
      else
      {
        return i;
      }
      i = i * 10 + (m - 48);
      j++;
    }
  }
  
  public static final void throwInternal()
  {
    throw new RuntimeException("Internal error: this code path should never get executed");
  }
  
  public static Version versionFor(Class<?> paramClass)
  {
    return packageVersionFor(paramClass);
  }
  
  public Version version()
  {
    return this._v;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/VersionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */