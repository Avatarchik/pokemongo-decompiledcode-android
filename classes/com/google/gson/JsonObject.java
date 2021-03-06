package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonObject
  extends JsonElement
{
  private final LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap();
  
  private JsonElement createJsonElement(Object paramObject)
  {
    if (paramObject == null) {}
    for (Object localObject = JsonNull.INSTANCE;; localObject = new JsonPrimitive(paramObject)) {
      return (JsonElement)localObject;
    }
  }
  
  public void add(String paramString, JsonElement paramJsonElement)
  {
    if (paramJsonElement == null) {
      paramJsonElement = JsonNull.INSTANCE;
    }
    this.members.put(paramString, paramJsonElement);
  }
  
  public void addProperty(String paramString, Boolean paramBoolean)
  {
    add(paramString, createJsonElement(paramBoolean));
  }
  
  public void addProperty(String paramString, Character paramCharacter)
  {
    add(paramString, createJsonElement(paramCharacter));
  }
  
  public void addProperty(String paramString, Number paramNumber)
  {
    add(paramString, createJsonElement(paramNumber));
  }
  
  public void addProperty(String paramString1, String paramString2)
  {
    add(paramString1, createJsonElement(paramString2));
  }
  
  JsonObject deepCopy()
  {
    JsonObject localJsonObject = new JsonObject();
    Iterator localIterator = this.members.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localJsonObject.add((String)localEntry.getKey(), ((JsonElement)localEntry.getValue()).deepCopy());
    }
    return localJsonObject;
  }
  
  public Set<Map.Entry<String, JsonElement>> entrySet()
  {
    return this.members.entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == this) || (((paramObject instanceof JsonObject)) && (((JsonObject)paramObject).members.equals(this.members)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JsonElement get(String paramString)
  {
    return (JsonElement)this.members.get(paramString);
  }
  
  public JsonArray getAsJsonArray(String paramString)
  {
    return (JsonArray)this.members.get(paramString);
  }
  
  public JsonObject getAsJsonObject(String paramString)
  {
    return (JsonObject)this.members.get(paramString);
  }
  
  public JsonPrimitive getAsJsonPrimitive(String paramString)
  {
    return (JsonPrimitive)this.members.get(paramString);
  }
  
  public boolean has(String paramString)
  {
    return this.members.containsKey(paramString);
  }
  
  public int hashCode()
  {
    return this.members.hashCode();
  }
  
  public JsonElement remove(String paramString)
  {
    return (JsonElement)this.members.remove(paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/JsonObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */