package spacemadness.com.lunarconsole.utils;

import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class StringUtils
{
  public static boolean IsNullOrEmpty(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static <T> String Join(List<T> paramList)
  {
    return Join(paramList, ",");
  }
  
  public static <T> String Join(List<T> paramList, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(localIterator.next());
      i++;
      if (i < paramList.size()) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(byte[] paramArrayOfByte)
  {
    return Join(paramArrayOfByte, ",");
  }
  
  public static String Join(byte[] paramArrayOfByte, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(paramArrayOfByte[i]);
      if (i < -1 + paramArrayOfByte.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(char[] paramArrayOfChar)
  {
    return Join(paramArrayOfChar, ",");
  }
  
  public static String Join(char[] paramArrayOfChar, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfChar.length; i++)
    {
      localStringBuilder.append(paramArrayOfChar[i]);
      if (i < -1 + paramArrayOfChar.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(double[] paramArrayOfDouble)
  {
    return Join(paramArrayOfDouble, ",");
  }
  
  public static String Join(double[] paramArrayOfDouble, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfDouble.length; i++)
    {
      localStringBuilder.append(paramArrayOfDouble[i]);
      if (i < -1 + paramArrayOfDouble.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(float[] paramArrayOfFloat)
  {
    return Join(paramArrayOfFloat, ",");
  }
  
  public static String Join(float[] paramArrayOfFloat, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfFloat.length; i++)
    {
      localStringBuilder.append(paramArrayOfFloat[i]);
      if (i < -1 + paramArrayOfFloat.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(int[] paramArrayOfInt)
  {
    return Join(paramArrayOfInt, ",");
  }
  
  public static String Join(int[] paramArrayOfInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfInt.length; i++)
    {
      localStringBuilder.append(paramArrayOfInt[i]);
      if (i < -1 + paramArrayOfInt.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(long[] paramArrayOfLong)
  {
    return Join(paramArrayOfLong, ",");
  }
  
  public static String Join(long[] paramArrayOfLong, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfLong.length; i++)
    {
      localStringBuilder.append(paramArrayOfLong[i]);
      if (i < -1 + paramArrayOfLong.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static <T> String Join(T[] paramArrayOfT)
  {
    return Join(paramArrayOfT, ",");
  }
  
  public static <T> String Join(T[] paramArrayOfT, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfT.length; i++)
    {
      localStringBuilder.append(paramArrayOfT[i]);
      if (i < -1 + paramArrayOfT.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(short[] paramArrayOfShort)
  {
    return Join(paramArrayOfShort, ",");
  }
  
  public static String Join(short[] paramArrayOfShort, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfShort.length; i++)
    {
      localStringBuilder.append(paramArrayOfShort[i]);
      if (i < -1 + paramArrayOfShort.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String Join(boolean[] paramArrayOfBoolean)
  {
    return Join(paramArrayOfBoolean, ",");
  }
  
  public static String Join(boolean[] paramArrayOfBoolean, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramArrayOfBoolean.length; i++)
    {
      localStringBuilder.append(paramArrayOfBoolean[i]);
      if (i < -1 + paramArrayOfBoolean.length) {
        localStringBuilder.append(paramString);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String NonNullOrEmpty(String paramString)
  {
    if (paramString != null) {}
    for (;;)
    {
      return paramString;
      paramString = "";
    }
  }
  
  public static String TryFormat(String paramString, Object... paramVarArgs)
  {
    if ((paramString != null) && (paramVarArgs != null) && (paramVarArgs.length > 0)) {}
    try
    {
      String str = String.format(paramString, paramVarArgs);
      paramString = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("Lunar", "Error while formatting String: " + localException.getMessage());
      }
    }
    return paramString;
  }
  
  public static boolean contains(String paramString, CharSequence paramCharSequence)
  {
    if ((paramString != null) && (paramCharSequence != null) && (paramString.contains(paramCharSequence))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean containsIgnoreCase(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null) && (paramString1.length() >= paramString2.length()) && (paramString1.toLowerCase().contains(paramString2.toLowerCase()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean hasPrefix(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null) && (paramString1.startsWith(paramString2))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static int length(String paramString)
  {
    if (paramString != null) {}
    for (int i = paramString.length();; i = 0) {
      return i;
    }
  }
  
  public static String nullOrNonEmpty(String paramString)
  {
    if (IsNullOrEmpty(paramString)) {
      paramString = null;
    }
    return paramString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/utils/StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */