package com.voxelbusters.nativeplugins.utilities;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

public class StringUtility
{
  public static boolean contains(String paramString, String[] paramArrayOfString)
  {
    boolean bool = false;
    int i = paramArrayOfString.length;
    for (int j = 0;; j++)
    {
      if (j >= i) {}
      for (;;)
      {
        return bool;
        if (!paramString.contains(paramArrayOfString[j])) {
          break;
        }
        bool = true;
      }
    }
  }
  
  public static String[] convertJsonStringToStringArray(String paramString)
  {
    String[] arrayOfString1 = null;
    if (isNullOrEmpty(paramString)) {}
    for (String[] arrayOfString2 = null;; arrayOfString2 = arrayOfString1)
    {
      return arrayOfString2;
      int i;
      int j;
      do
      {
        try
        {
          localJSONArray = new JSONArray(paramString);
          i = localJSONArray.length();
          arrayOfString1 = new String[i];
          j = 0;
        }
        catch (JSONException localJSONException)
        {
          JSONArray localJSONArray;
          localJSONException.printStackTrace();
          Debug.error("NativePlugins.StringUtility", "Error in parsing jsonString " + paramString);
          break;
        }
        arrayOfString1[j] = new String(localJSONArray.getString(j));
        j++;
      } while (j < i);
    }
  }
  
  public static String getBase64DecodedString(String paramString)
  {
    byte[] arrayOfByte = Base64.decode(paramString, 0);
    Object localObject = "";
    try
    {
      String str = new String(arrayOfByte, "UTF-8");
      localObject = str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
    return (String)localObject;
  }
  
  public static String getCurrencySymbolFromCode(String paramString)
  {
    Object localObject = "";
    try
    {
      String str = Currency.getInstance(paramString).getSymbol();
      localObject = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Debug.log("NativePlugins.StringUtility", "Error in converting currency code : " + paramString);
      }
    }
    return (String)localObject;
  }
  
  public static String getCurrentTimeStamp()
  {
    return new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
  }
  
  public static String getFileNameWithoutExtension(String paramString)
  {
    String str = paramString;
    int i = paramString.lastIndexOf('.');
    if (i >= 0) {
      str = paramString.substring(0, i);
    }
    return str;
  }
  
  public static boolean isNullOrEmpty(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramString.equals("null"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/utilities/StringUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */