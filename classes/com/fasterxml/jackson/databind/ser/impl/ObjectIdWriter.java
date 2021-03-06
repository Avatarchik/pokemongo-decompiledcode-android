package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;

public final class ObjectIdWriter
{
  public final boolean alwaysAsId;
  public final ObjectIdGenerator<?> generator;
  public final JavaType idType;
  public final SerializableString propertyName;
  public final JsonSerializer<Object> serializer;
  
  protected ObjectIdWriter(JavaType paramJavaType, SerializableString paramSerializableString, ObjectIdGenerator<?> paramObjectIdGenerator, JsonSerializer<?> paramJsonSerializer, boolean paramBoolean)
  {
    this.idType = paramJavaType;
    this.propertyName = paramSerializableString;
    this.generator = paramObjectIdGenerator;
    this.serializer = paramJsonSerializer;
    this.alwaysAsId = paramBoolean;
  }
  
  public static ObjectIdWriter construct(JavaType paramJavaType, PropertyName paramPropertyName, ObjectIdGenerator<?> paramObjectIdGenerator, boolean paramBoolean)
  {
    if (paramPropertyName == null) {}
    for (String str = null;; str = paramPropertyName.getSimpleName()) {
      return construct(paramJavaType, str, paramObjectIdGenerator, paramBoolean);
    }
  }
  
  @Deprecated
  public static ObjectIdWriter construct(JavaType paramJavaType, String paramString, ObjectIdGenerator<?> paramObjectIdGenerator, boolean paramBoolean)
  {
    if (paramString == null) {}
    for (Object localObject = null;; localObject = new SerializedString(paramString)) {
      return new ObjectIdWriter(paramJavaType, (SerializableString)localObject, paramObjectIdGenerator, null, paramBoolean);
    }
  }
  
  public ObjectIdWriter withAlwaysAsId(boolean paramBoolean)
  {
    if (paramBoolean == this.alwaysAsId) {}
    for (;;)
    {
      return this;
      this = new ObjectIdWriter(this.idType, this.propertyName, this.generator, this.serializer, paramBoolean);
    }
  }
  
  public ObjectIdWriter withSerializer(JsonSerializer<?> paramJsonSerializer)
  {
    return new ObjectIdWriter(this.idType, this.propertyName, this.generator, paramJsonSerializer, this.alwaysAsId);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/ObjectIdWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */