package com.upsight.android.analytics.internal.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract interface JacksonHelper
{
  public static class JSONArraySerializer
  {
    private static ObjectMapper sMapper = new ObjectMapper();
    
    public static JSONArray fromArrayNode(ArrayNode paramArrayNode)
    {
      Object localObject = null;
      if (paramArrayNode != null) {}
      try
      {
        JSONArray localJSONArray = new JSONArray(paramArrayNode.toString());
        localObject = localJSONArray;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
      return (JSONArray)localObject;
    }
    
    public static ArrayNode toArrayNode(JSONArray paramJSONArray)
    {
      ArrayNode localArrayNode = null;
      try
      {
        localArrayNode = (ArrayNode)sMapper.readTree(paramJSONArray.toString());
        return localArrayNode;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
  }
  
  public static class JSONObjectSerializer
  {
    private static ObjectMapper sMapper = new ObjectMapper();
    
    public static JSONObject fromObjectNode(ObjectNode paramObjectNode)
    {
      Object localObject = null;
      if (paramObjectNode != null) {}
      try
      {
        JSONObject localJSONObject = new JSONObject(paramObjectNode.toString());
        localObject = localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
      return (JSONObject)localObject;
    }
    
    public static ObjectNode toObjectNode(JSONObject paramJSONObject)
    {
      ObjectNode localObjectNode = null;
      if (paramJSONObject != null) {}
      try
      {
        localObjectNode = (ObjectNode)sMapper.readTree(paramJSONObject.toString());
        return localObjectNode;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/util/JacksonHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */