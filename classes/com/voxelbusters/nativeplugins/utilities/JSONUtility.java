package com.voxelbusters.nativeplugins.utilities;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtility
{
  public static int findString(JSONArray paramJSONArray, String paramString)
  {
    int i = -1;
    int j = 0;
    for (;;)
    {
      if (j >= paramJSONArray.length()) {}
      for (;;)
      {
        return i;
        Object localObject = null;
        try
        {
          String str = paramJSONArray.getString(j);
          localObject = str;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            localJSONException.printStackTrace();
          }
          j++;
        }
        if ((localObject == null) || (!((String)localObject).equals(paramString))) {
          break;
        }
        i = j;
      }
    }
  }
  
  public static JSONObject getJSON(String paramString)
  {
    Object localObject = null;
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      localObject = localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
    return (JSONObject)localObject;
  }
  
  public static JSONArray getJSONArray(String paramString)
  {
    try
    {
      localJSONArray = new JSONArray(paramString);
      return localJSONArray;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
        JSONArray localJSONArray = new JSONArray();
      }
    }
  }
  
  public static String getJSONString(HashMap paramHashMap)
  {
    return new JSONObject(paramHashMap).toString();
  }
  
  public static JSONObject getJSONfromBundle(Bundle paramBundle)
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramBundle.keySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localJSONObject;
      }
      String str = (String)localIterator.next();
      Object localObject = paramBundle.get(str);
      try
      {
        localJSONObject.put(str, localObject);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        Debug.error("NativePlugins.JSONUtility", "Exception while entering key " + str);
      }
    }
  }
  
  public static String[] getKeys(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.names();
    String[] arrayOfString = new String[localJSONArray.length()];
    int i = 0;
    int j = localJSONArray.length();
    for (;;)
    {
      if (i >= j) {
        return arrayOfString;
      }
      try
      {
        arrayOfString[i] = localJSONArray.getString(i);
        i++;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  public static JSONArray removeIndex(JSONArray paramJSONArray, int paramInt)
  {
    localJSONArray = new JSONArray();
    int i = 0;
    try
    {
      while (i < paramJSONArray.length())
      {
        if (i != paramInt) {
          localJSONArray.put(paramJSONArray.get(i));
        }
        i++;
      }
      return localJSONArray;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/utilities/JSONUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */