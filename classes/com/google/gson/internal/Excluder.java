package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder
  implements TypeAdapterFactory, Cloneable
{
  public static final Excluder DEFAULT = new Excluder();
  private static final double IGNORE_VERSIONS = -1.0D;
  private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();
  private int modifiers = 136;
  private boolean requireExpose;
  private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
  private boolean serializeInnerClasses = true;
  private double version = -1.0D;
  
  private boolean isAnonymousOrLocal(Class<?> paramClass)
  {
    if ((!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isInnerClass(Class<?> paramClass)
  {
    if ((paramClass.isMemberClass()) && (!isStatic(paramClass))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isStatic(Class<?> paramClass)
  {
    if ((0x8 & paramClass.getModifiers()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isValidSince(Since paramSince)
  {
    if ((paramSince != null) && (paramSince.value() > this.version)) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private boolean isValidUntil(Until paramUntil)
  {
    if ((paramUntil != null) && (paramUntil.value() <= this.version)) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private boolean isValidVersion(Since paramSince, Until paramUntil)
  {
    if ((isValidSince(paramSince)) && (isValidUntil(paramUntil))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected Excluder clone()
  {
    try
    {
      Excluder localExcluder = (Excluder)super.clone();
      return localExcluder;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError();
    }
  }
  
  public <T> TypeAdapter<T> create(final Gson paramGson, final TypeToken<T> paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    final boolean bool1 = excludeClass(localClass, true);
    final boolean bool2 = excludeClass(localClass, false);
    if ((!bool1) && (!bool2)) {}
    for (Object localObject = null;; localObject = new TypeAdapter()
        {
          private TypeAdapter<T> delegate;
          
          private TypeAdapter<T> delegate()
          {
            TypeAdapter localTypeAdapter = this.delegate;
            if (localTypeAdapter != null) {}
            for (;;)
            {
              return localTypeAdapter;
              localTypeAdapter = paramGson.getDelegateAdapter(Excluder.this, paramTypeToken);
              this.delegate = localTypeAdapter;
            }
          }
          
          public T read(JsonReader paramAnonymousJsonReader)
            throws IOException
          {
            if (bool2) {
              paramAnonymousJsonReader.skipValue();
            }
            for (Object localObject = null;; localObject = delegate().read(paramAnonymousJsonReader)) {
              return (T)localObject;
            }
          }
          
          public void write(JsonWriter paramAnonymousJsonWriter, T paramAnonymousT)
            throws IOException
          {
            if (bool1) {
              paramAnonymousJsonWriter.nullValue();
            }
            for (;;)
            {
              return;
              delegate().write(paramAnonymousJsonWriter, paramAnonymousT);
            }
          }
        }) {
      return (TypeAdapter<T>)localObject;
    }
  }
  
  public Excluder disableInnerClassSerialization()
  {
    Excluder localExcluder = clone();
    localExcluder.serializeInnerClasses = false;
    return localExcluder;
  }
  
  public boolean excludeClass(Class<?> paramClass, boolean paramBoolean)
  {
    boolean bool;
    if ((this.version != -1.0D) && (!isValidVersion((Since)paramClass.getAnnotation(Since.class), (Until)paramClass.getAnnotation(Until.class)))) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if ((!this.serializeInnerClasses) && (isInnerClass(paramClass)))
      {
        bool = true;
      }
      else if (isAnonymousOrLocal(paramClass))
      {
        bool = true;
      }
      else
      {
        if (paramBoolean) {}
        for (List localList = this.serializationStrategies;; localList = this.deserializationStrategies)
        {
          Iterator localIterator = localList.iterator();
          do
          {
            if (!localIterator.hasNext()) {
              break;
            }
          } while (!((ExclusionStrategy)localIterator.next()).shouldSkipClass(paramClass));
          bool = true;
          break;
        }
        bool = false;
      }
    }
  }
  
  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    boolean bool;
    if ((this.modifiers & paramField.getModifiers()) != 0) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if ((this.version != -1.0D) && (!isValidVersion((Since)paramField.getAnnotation(Since.class), (Until)paramField.getAnnotation(Until.class))))
      {
        bool = true;
      }
      else if (paramField.isSynthetic())
      {
        bool = true;
      }
      else
      {
        if (this.requireExpose)
        {
          Expose localExpose = (Expose)paramField.getAnnotation(Expose.class);
          if (localExpose != null)
          {
            if (!paramBoolean) {
              break label116;
            }
            if (localExpose.serialize()) {
              break label126;
            }
          }
          label116:
          while (!localExpose.deserialize())
          {
            bool = true;
            break;
          }
        }
        label126:
        if ((!this.serializeInnerClasses) && (isInnerClass(paramField.getType())))
        {
          bool = true;
        }
        else if (isAnonymousOrLocal(paramField.getType()))
        {
          bool = true;
        }
        else
        {
          if (paramBoolean) {}
          for (List localList = this.serializationStrategies;; localList = this.deserializationStrategies)
          {
            if (localList.isEmpty()) {
              break label247;
            }
            FieldAttributes localFieldAttributes = new FieldAttributes(paramField);
            Iterator localIterator = localList.iterator();
            do
            {
              if (!localIterator.hasNext()) {
                break;
              }
            } while (!((ExclusionStrategy)localIterator.next()).shouldSkipField(localFieldAttributes));
            bool = true;
            break;
          }
          label247:
          bool = false;
        }
      }
    }
  }
  
  public Excluder excludeFieldsWithoutExposeAnnotation()
  {
    Excluder localExcluder = clone();
    localExcluder.requireExpose = true;
    return localExcluder;
  }
  
  public Excluder withExclusionStrategy(ExclusionStrategy paramExclusionStrategy, boolean paramBoolean1, boolean paramBoolean2)
  {
    Excluder localExcluder = clone();
    if (paramBoolean1)
    {
      localExcluder.serializationStrategies = new ArrayList(this.serializationStrategies);
      localExcluder.serializationStrategies.add(paramExclusionStrategy);
    }
    if (paramBoolean2)
    {
      localExcluder.deserializationStrategies = new ArrayList(this.deserializationStrategies);
      localExcluder.deserializationStrategies.add(paramExclusionStrategy);
    }
    return localExcluder;
  }
  
  public Excluder withModifiers(int... paramVarArgs)
  {
    Excluder localExcluder = clone();
    localExcluder.modifiers = 0;
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++) {
      localExcluder.modifiers = (paramVarArgs[j] | localExcluder.modifiers);
    }
    return localExcluder;
  }
  
  public Excluder withVersion(double paramDouble)
  {
    Excluder localExcluder = clone();
    localExcluder.version = paramDouble;
    return localExcluder;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/Excluder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */