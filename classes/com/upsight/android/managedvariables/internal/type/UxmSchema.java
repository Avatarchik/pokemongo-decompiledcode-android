package com.upsight.android.managedvariables.internal.type;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.type.UpsightManagedBoolean;
import com.upsight.android.managedvariables.type.UpsightManagedFloat;
import com.upsight.android.managedvariables.type.UpsightManagedInt;
import com.upsight.android.managedvariables.type.UpsightManagedString;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class UxmSchema
{
  private static final String ITEM_SCHEMA_KEY_DEFAULT = "default";
  private static final String ITEM_SCHEMA_KEY_TAG = "tag";
  private static final String ITEM_SCHEMA_KEY_TYPE = "type";
  private static final Map<Class<? extends ManagedVariable>, Class<? extends BaseSchema>> sClassSchemaMap = new HashMap() {};
  private static final Map<String, Class<? extends BaseSchema>> sModelTypeSchemaMap = new HashMap() {};
  private static final Map<String, String> sTypeSchemaMap = new HashMap() {};
  private List<BaseSchema> mItemList = new ArrayList();
  private Map<String, BaseSchema> mItemSchemaMap = new HashMap();
  private UpsightLogger mLogger;
  public final String mSchemaJsonString;
  
  UxmSchema(UpsightLogger paramUpsightLogger)
  {
    this.mLogger = paramUpsightLogger;
    this.mSchemaJsonString = null;
  }
  
  private UxmSchema(List<BaseSchema> paramList, Map<String, BaseSchema> paramMap, UpsightLogger paramUpsightLogger, String paramString)
  {
    this.mItemList = paramList;
    this.mItemSchemaMap = paramMap;
    this.mLogger = paramUpsightLogger;
    this.mSchemaJsonString = paramString;
  }
  
  public static UxmSchema create(String paramString, ObjectMapper paramObjectMapper, UpsightLogger paramUpsightLogger)
    throws IllegalArgumentException
  {
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    ArrayNode localArrayNode;
    JsonNode localJsonNode;
    String str3;
    for (;;)
    {
      try
      {
        localArrayNode = (ArrayNode)paramObjectMapper.readTree(paramString);
        Iterator localIterator = localArrayNode.iterator();
        if (!localIterator.hasNext()) {
          break label643;
        }
        localJsonNode = (JsonNode)localIterator.next();
        if (!localJsonNode.isObject())
        {
          String str11 = "Managed variable schema must be a JSON object: " + localJsonNode;
          paramUpsightLogger.e("Upsight", str11, new Object[0]);
          throw new IllegalArgumentException(str11);
        }
      }
      catch (IOException localIOException)
      {
        String str2 = "Failed to parse UXM schema JSON: " + paramString;
        paramUpsightLogger.e("Upsight", localIOException, str2, new Object[0]);
        throw new IllegalArgumentException(str2, localIOException);
      }
      catch (ClassCastException localClassCastException)
      {
        String str1 = "UXM schema must be a JSON array: " + paramString;
        paramUpsightLogger.e("Upsight", localClassCastException, str1, new Object[0]);
        throw new IllegalArgumentException(str1, localClassCastException);
      }
      if (!localJsonNode.path("tag").isTextual())
      {
        String str10 = "Managed variable schema must contain a tag: " + localJsonNode;
        paramUpsightLogger.e("Upsight", str10, new Object[0]);
        throw new IllegalArgumentException(str10);
      }
      if (!localJsonNode.path("type").isTextual())
      {
        String str9 = "Managed variable schema must contain a type: " + localJsonNode;
        paramUpsightLogger.e("Upsight", str9, new Object[0]);
        throw new IllegalArgumentException(str9);
      }
      if (!localJsonNode.has("default"))
      {
        String str8 = "Managed variable schema must contain a default value: " + localJsonNode;
        paramUpsightLogger.e("Upsight", str8, new Object[0]);
        throw new IllegalArgumentException(str8);
      }
      str3 = (String)sTypeSchemaMap.get(localJsonNode.path("type").asText());
      if (!TextUtils.isEmpty(str3))
      {
        ((ObjectNode)localJsonNode).put("type", str3);
        String str5 = localJsonNode.path("tag").asText();
        Class localClass = (Class)sModelTypeSchemaMap.get(str3);
        if (localClass == null) {
          break label597;
        }
        try
        {
          BaseSchema localBaseSchema = (BaseSchema)paramObjectMapper.treeToValue(localJsonNode, localClass);
          localArrayList.add(localBaseSchema);
          localHashMap.put(str5, localBaseSchema);
        }
        catch (JsonProcessingException localJsonProcessingException)
        {
          String str7 = "Managed variable contains invalid fields: " + localJsonNode;
          paramUpsightLogger.e("Upsight", localJsonProcessingException, str7, new Object[0]);
          throw new IllegalArgumentException(str7, localJsonProcessingException);
        }
      }
    }
    String str4 = "Managed variable contains invalid types: " + localJsonNode;
    paramUpsightLogger.e("Upsight", str4, new Object[0]);
    throw new IllegalArgumentException(str4);
    label597:
    String str6 = "Unknown managed variable type: " + str3;
    paramUpsightLogger.e("Upsight", str6, new Object[0]);
    throw new IllegalArgumentException(str6);
    label643:
    return new UxmSchema(localArrayList, localHashMap, paramUpsightLogger, localArrayNode.toString());
  }
  
  public <T extends ManagedVariable> BaseSchema get(Class<T> paramClass, String paramString)
  {
    BaseSchema localBaseSchema = (BaseSchema)this.mItemSchemaMap.get(paramString);
    if (localBaseSchema == null) {
      localBaseSchema = null;
    }
    Class localClass1;
    Class localClass2;
    do
    {
      return localBaseSchema;
      localClass1 = (Class)sClassSchemaMap.get(paramClass);
      localClass2 = (Class)sModelTypeSchemaMap.get(localBaseSchema.type);
    } while ((localClass1 != null) && (localClass2 != null) && (localClass2.equals(localClass1)));
    String str = "The tag is not of the expected class: " + paramString;
    this.mLogger.e("Upsight", str, new Object[0]);
    throw new IllegalArgumentException(str);
  }
  
  public List<BaseSchema> getAllOrdered()
  {
    return new ArrayList(this.mItemList);
  }
  
  public static class FloatSchema
    extends UxmSchema.BaseSchema<Float>
  {
    @JsonProperty("max")
    public Float max;
    @JsonProperty("min")
    public Float min;
  }
  
  public static class IntSchema
    extends UxmSchema.BaseSchema<Integer>
  {
    @JsonProperty("max")
    public Integer max;
    @JsonProperty("min")
    public Integer min;
  }
  
  public static class BooleanSchema
    extends UxmSchema.BaseSchema<Boolean>
  {}
  
  public static class StringSchema
    extends UxmSchema.BaseSchema<String>
  {}
  
  public static class BaseSchema<T>
  {
    @JsonProperty("default")
    public T defaultValue;
    @JsonProperty("description")
    public String description;
    @JsonProperty("tag")
    public String tag;
    @JsonProperty("type")
    public String type;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */