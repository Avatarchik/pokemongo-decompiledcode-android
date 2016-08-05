package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;

public class OptionalHandlerFactory
  implements Serializable
{
  private static final String CLASS_NAME_DOM_DOCUMENT = "org.w3c.dom.Node";
  private static final String CLASS_NAME_DOM_NODE = "org.w3c.dom.Node";
  private static final String DESERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLDeserializers";
  private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer";
  private static final String DESERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer";
  private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
  private static final String SERIALIZERS_FOR_JAVAX_XML = "com.fasterxml.jackson.databind.ext.CoreXMLSerializers";
  private static final String SERIALIZER_FOR_DOM_NODE = "com.fasterxml.jackson.databind.ext.DOMSerializer";
  public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();
  private static final long serialVersionUID = 1L;
  
  private boolean doesImplement(Class<?> paramClass, String paramString)
  {
    boolean bool = true;
    Object localObject = paramClass;
    if (localObject != null) {
      if (!((Class)localObject).getName().equals(paramString)) {}
    }
    for (;;)
    {
      return bool;
      if (!hasInterface((Class)localObject, paramString))
      {
        localObject = ((Class)localObject).getSuperclass();
        break;
        bool = false;
      }
    }
  }
  
  private boolean hasInterface(Class<?> paramClass, String paramString)
  {
    boolean bool = true;
    Class[] arrayOfClass = paramClass.getInterfaces();
    int i = arrayOfClass.length;
    int j = 0;
    if (j < i) {
      if (!arrayOfClass[j].getName().equals(paramString)) {}
    }
    for (;;)
    {
      return bool;
      j++;
      break;
      int k = arrayOfClass.length;
      for (int m = 0;; m++)
      {
        if (m >= k) {
          break label80;
        }
        if (hasInterface(arrayOfClass[m], paramString)) {
          break;
        }
      }
      label80:
      bool = false;
    }
  }
  
  private boolean hasInterfaceStartingWith(Class<?> paramClass, String paramString)
  {
    boolean bool = true;
    Class[] arrayOfClass = paramClass.getInterfaces();
    int i = arrayOfClass.length;
    int j = 0;
    if (j < i) {
      if (!arrayOfClass[j].getName().startsWith(paramString)) {}
    }
    for (;;)
    {
      return bool;
      j++;
      break;
      int k = arrayOfClass.length;
      for (int m = 0;; m++)
      {
        if (m >= k) {
          break label80;
        }
        if (hasInterfaceStartingWith(arrayOfClass[m], paramString)) {
          break;
        }
      }
      label80:
      bool = false;
    }
  }
  
  private boolean hasSupertypeStartingWith(Class<?> paramClass, String paramString)
  {
    boolean bool = true;
    Class localClass = paramClass.getSuperclass();
    if (localClass != null) {
      if (!localClass.getName().startsWith(paramString)) {}
    }
    for (;;)
    {
      return bool;
      localClass = localClass.getSuperclass();
      break;
      for (Object localObject = paramClass;; localObject = ((Class)localObject).getSuperclass())
      {
        if (localObject == null) {
          break label65;
        }
        if (hasInterfaceStartingWith((Class)localObject, paramString)) {
          break;
        }
      }
      label65:
      bool = false;
    }
  }
  
  private Object instantiate(String paramString)
  {
    try
    {
      Object localObject2 = Class.forName(paramString).newInstance();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1 = null;
      }
    }
    catch (LinkageError localLinkageError)
    {
      for (;;) {}
    }
    return localObject1;
  }
  
  public JsonDeserializer<?> findDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject1 = null;
    Class localClass = paramJavaType.getRawClass();
    Object localObject2;
    if ((localClass.getName().startsWith("javax.xml.")) || (hasSupertypeStartingWith(localClass, "javax.xml.")))
    {
      localObject2 = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers");
      if (localObject2 != null) {
        break label99;
      }
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject1;
      if (doesImplement(localClass, "org.w3c.dom.Node"))
      {
        localObject1 = (JsonDeserializer)instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer");
      }
      else if (doesImplement(localClass, "org.w3c.dom.Node"))
      {
        localObject1 = (JsonDeserializer)instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer");
        continue;
        label99:
        localObject1 = ((Deserializers)localObject2).findBeanDeserializer(paramJavaType, paramDeserializationConfig, paramBeanDescription);
      }
    }
  }
  
  public JsonSerializer<?> findSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
  {
    JsonSerializer localJsonSerializer = null;
    Class localClass = paramJavaType.getRawClass();
    String str = localClass.getName();
    if (doesImplement(localClass, "org.w3c.dom.Node")) {
      localJsonSerializer = (JsonSerializer)instantiate("com.fasterxml.jackson.databind.ext.DOMSerializer");
    }
    for (;;)
    {
      return localJsonSerializer;
      if ((str.startsWith("javax.xml.")) || (hasSupertypeStartingWith(localClass, "javax.xml.")))
      {
        Object localObject = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLSerializers");
        if (localObject != null) {
          localJsonSerializer = ((Serializers)localObject).findSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ext/OptionalHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */