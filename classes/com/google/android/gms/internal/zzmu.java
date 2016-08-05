package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzmu
{
  private static final Pattern zzaim = Pattern.compile("\\\\.");
  private static final Pattern zzain = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static String zzcz(String paramString)
  {
    Matcher localMatcher;
    StringBuffer localStringBuffer;
    if (!TextUtils.isEmpty(paramString))
    {
      localMatcher = zzain.matcher(paramString);
      localStringBuffer = null;
      while (localMatcher.find())
      {
        if (localStringBuffer == null) {
          localStringBuffer = new StringBuffer();
        }
        switch (localMatcher.group().charAt(0))
        {
        default: 
          break;
        case '\b': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\b");
          break;
        case '"': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\\\\"");
          break;
        case '\\': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\\\\\");
          break;
        case '/': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\/");
          break;
        case '\f': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\f");
          break;
        case '\n': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\n");
          break;
        case '\r': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\r");
          break;
        case '\t': 
          localMatcher.appendReplacement(localStringBuffer, "\\\\t");
        }
      }
      if (localStringBuffer != null) {
        break label217;
      }
    }
    for (;;)
    {
      return paramString;
      label217:
      localMatcher.appendTail(localStringBuffer);
      paramString = localStringBuffer.toString();
    }
  }
  
  public static boolean zzd(Object paramObject1, Object paramObject2)
  {
    boolean bool1 = false;
    if ((paramObject1 == null) && (paramObject2 == null)) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      if ((paramObject1 != null) && (paramObject2 != null))
      {
        JSONObject localJSONObject1;
        JSONObject localJSONObject2;
        label68:
        String str;
        if (((paramObject1 instanceof JSONObject)) && ((paramObject2 instanceof JSONObject)))
        {
          localJSONObject1 = (JSONObject)paramObject1;
          localJSONObject2 = (JSONObject)paramObject2;
          if (localJSONObject1.length() != localJSONObject2.length()) {
            continue;
          }
          Iterator localIterator = localJSONObject1.keys();
          if (localIterator.hasNext())
          {
            str = (String)localIterator.next();
            if (!localJSONObject2.has(str)) {
              continue;
            }
          }
        }
        try
        {
          boolean bool3 = zzd(localJSONObject1.get(str), localJSONObject2.get(str));
          if (bool3) {
            break label68;
          }
        }
        catch (JSONException localJSONException2) {}
        bool1 = true;
        continue;
        JSONArray localJSONArray1;
        JSONArray localJSONArray2;
        int i;
        if (((paramObject1 instanceof JSONArray)) && ((paramObject2 instanceof JSONArray)))
        {
          localJSONArray1 = (JSONArray)paramObject1;
          localJSONArray2 = (JSONArray)paramObject2;
          if (localJSONArray1.length() != localJSONArray2.length()) {
            continue;
          }
          i = 0;
          if (i >= localJSONArray1.length()) {}
        }
        try
        {
          boolean bool2 = zzd(localJSONArray1.get(i), localJSONArray2.get(i));
          if (!bool2) {
            continue;
          }
          i++;
        }
        catch (JSONException localJSONException1) {}
        bool1 = true;
        continue;
        bool1 = paramObject1.equals(paramObject2);
        continue;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */