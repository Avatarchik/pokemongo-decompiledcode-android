package com.upsight.android.internal.persistence.storable;

import android.text.TextUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightException;
import com.upsight.android.persistence.UpsightStorableSerializer;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

public final class StorableInfoCache
{
  private final ConcurrentHashMap<Class<?>, StorableIdentifierAccessor> mAccessorMap = new ConcurrentHashMap();
  private final ConcurrentHashMap<Class<?>, StorableInfo<?>> mInfoMap = new ConcurrentHashMap();
  private final ObjectMapper mObjectMapper;
  private final ConcurrentHashMap<Class<?>, UpsightStorableSerializer<?>> mSerializerMap = new ConcurrentHashMap();
  
  StorableInfoCache(ObjectMapper paramObjectMapper)
  {
    this.mObjectMapper = paramObjectMapper;
  }
  
  private StorableIdentifierAccessor resolveIdentifierAccessor(Class<?> paramClass)
    throws UpsightException
  {
    Object localObject1 = (StorableIdentifierAccessor)this.mAccessorMap.get(paramClass);
    if (localObject1 != null) {}
    for (Object localObject3 = localObject1;; localObject3 = localObject1)
    {
      return (StorableIdentifierAccessor)localObject3;
      Object localObject2 = paramClass;
      while ((localObject1 == null) && (localObject2 != null))
      {
        Field[] arrayOfField = ((Class)localObject2).getDeclaredFields();
        int i = arrayOfField.length;
        int j = 0;
        while (j < i)
        {
          Field localField = arrayOfField[j];
          if ((UpsightStorableIdentifier)localField.getAnnotation(UpsightStorableIdentifier.class) == null)
          {
            j++;
          }
          else
          {
            if (!localField.getType().equals(String.class)) {
              break label110;
            }
            localObject1 = new StorableFieldIdentifierAccessor(localField);
          }
        }
        localObject2 = ((Class)localObject2).getSuperclass();
        continue;
        label110:
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = UpsightStorableIdentifier.class.getSimpleName();
        throw new UpsightException("Field annotated with @%s must be of type String.", arrayOfObject);
      }
      if (localObject1 == null) {
        localObject1 = new StorableIdentifierNoopAccessor();
      }
      this.mAccessorMap.put(paramClass, localObject1);
    }
  }
  
  private <T> UpsightStorableSerializer<T> resolveSerializer(Class<T> paramClass)
  {
    Object localObject = (UpsightStorableSerializer)this.mSerializerMap.get(paramClass);
    if (localObject == null)
    {
      localObject = new DefaultJsonSerializer(this.mObjectMapper, paramClass);
      this.mSerializerMap.put(paramClass, localObject);
    }
    return (UpsightStorableSerializer<T>)localObject;
  }
  
  private <T> StorableTypeAccessor<T> resolveType(Class<T> paramClass)
    throws UpsightException
  {
    Object localObject = null;
    UpsightStorableType localUpsightStorableType1 = (UpsightStorableType)paramClass.getAnnotation(UpsightStorableType.class);
    if (localUpsightStorableType1 != null)
    {
      if (TextUtils.isEmpty(localUpsightStorableType1.value()))
      {
        Object[] arrayOfObject7 = new Object[1];
        arrayOfObject7[0] = UpsightStorableType.class.getSimpleName();
        throw new UpsightException("Class annotated with @%s must define non empty value.", arrayOfObject7);
      }
      localObject = new StorableStaticTypeAccessor(localUpsightStorableType1.value());
    }
    for (Method localMethod : paramClass.getDeclaredMethods())
    {
      UpsightStorableType localUpsightStorableType2 = (UpsightStorableType)localMethod.getAnnotation(UpsightStorableType.class);
      if (localUpsightStorableType2 != null)
      {
        if (!localMethod.getReturnType().equals(String.class))
        {
          Object[] arrayOfObject6 = new Object[1];
          arrayOfObject6[0] = UpsightStorableType.class;
          throw new UpsightException("Method annotated with @%s must return empty.", arrayOfObject6);
        }
        if (localMethod.getParameterTypes().length > 0)
        {
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = UpsightStorableType.class;
          throw new UpsightException("Method annotated with @%s must have no parameters.", arrayOfObject5);
        }
        if (localObject != null)
        {
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = UpsightStorableType.class.getSimpleName();
          throw new UpsightException("@%s can only be defined once in class.", arrayOfObject4);
        }
        if (!TextUtils.isEmpty(localUpsightStorableType2.value()))
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = UpsightStorableType.class.getSimpleName();
          throw new UpsightException("Method annotated with @%s should not define type in annotation but return it.", arrayOfObject3);
        }
        if (!Modifier.isPublic(localMethod.getModifiers()))
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = UpsightStorableType.class.getSimpleName();
          throw new UpsightException("Method annotated with @%s must be public.", arrayOfObject2);
        }
        localObject = new StorableMethodTypeAccessor(localMethod);
      }
    }
    if (localObject == null)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = UpsightStorableType.class.getSimpleName();
      throw new UpsightException("Class must either be annotated or have method annotated with %s.", arrayOfObject1);
    }
    return (StorableTypeAccessor<T>)localObject;
  }
  
  public <T> StorableInfo<T> get(Class<T> paramClass)
    throws UpsightException
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("Class can not be null.");
    }
    StorableInfo localStorableInfo = (StorableInfo)this.mInfoMap.get(paramClass);
    if (localStorableInfo == null)
    {
      UpsightStorableSerializer localUpsightStorableSerializer = resolveSerializer(paramClass);
      StorableTypeAccessor localStorableTypeAccessor = resolveType(paramClass);
      localStorableInfo = new StorableInfo(localStorableTypeAccessor, localUpsightStorableSerializer, resolveIdentifierAccessor(paramClass));
      if (!localStorableTypeAccessor.isDynamic()) {
        this.mInfoMap.put(paramClass, localStorableInfo);
      }
    }
    return localStorableInfo;
  }
  
  public <T> void setSerializer(Class<T> paramClass, UpsightStorableSerializer<T> paramUpsightStorableSerializer)
  {
    this.mSerializerMap.put(paramClass, paramUpsightStorableSerializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableInfoCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */