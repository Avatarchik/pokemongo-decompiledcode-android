package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleKeyDeserializers
  implements KeyDeserializers, Serializable
{
  private static final long serialVersionUID = 1L;
  protected HashMap<ClassKey, KeyDeserializer> _classMappings = null;
  
  public SimpleKeyDeserializers addDeserializer(Class<?> paramClass, KeyDeserializer paramKeyDeserializer)
  {
    if (this._classMappings == null) {
      this._classMappings = new HashMap();
    }
    this._classMappings.put(new ClassKey(paramClass), paramKeyDeserializer);
    return this;
  }
  
  public KeyDeserializer findKeyDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
  {
    if (this._classMappings == null) {}
    for (KeyDeserializer localKeyDeserializer = null;; localKeyDeserializer = (KeyDeserializer)this._classMappings.get(new ClassKey(paramJavaType.getRawClass()))) {
      return localKeyDeserializer;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/module/SimpleKeyDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */