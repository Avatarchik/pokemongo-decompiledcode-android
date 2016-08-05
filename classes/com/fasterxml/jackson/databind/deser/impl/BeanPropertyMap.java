package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BeanPropertyMap
  implements Iterable<SettableBeanProperty>, Serializable
{
  private static final long serialVersionUID = 2L;
  protected final boolean _caseInsensitive;
  private Object[] _hashArea;
  private int _hashMask;
  private SettableBeanProperty[] _propsInOrder;
  private int _size;
  private int _spillCount;
  
  public BeanPropertyMap(boolean paramBoolean, Collection<SettableBeanProperty> paramCollection)
  {
    this._caseInsensitive = paramBoolean;
    this._propsInOrder = ((SettableBeanProperty[])paramCollection.toArray(new SettableBeanProperty[paramCollection.size()]));
    init(paramCollection);
  }
  
  private final SettableBeanProperty _find2(String paramString, int paramInt, Object paramObject)
  {
    SettableBeanProperty localSettableBeanProperty = null;
    if (paramObject == null) {}
    label140:
    for (;;)
    {
      return localSettableBeanProperty;
      int i = 1 + this._hashMask;
      int j = i + (paramInt >> 1) << 1;
      Object localObject1 = this._hashArea[j];
      if (paramString.equals(localObject1))
      {
        localSettableBeanProperty = (SettableBeanProperty)this._hashArea[(j + 1)];
      }
      else if (localObject1 != null)
      {
        int k = i + (i >> 1) << 1;
        int m = k + this._spillCount;
        for (;;)
        {
          if (k >= m) {
            break label140;
          }
          Object localObject2 = this._hashArea[k];
          if ((localObject2 == paramString) || (paramString.equals(localObject2)))
          {
            localSettableBeanProperty = (SettableBeanProperty)this._hashArea[(k + 1)];
            break;
          }
          k += 2;
        }
      }
    }
  }
  
  private int _findFromOrdered(SettableBeanProperty paramSettableBeanProperty)
  {
    int i = 0;
    int j = this._propsInOrder.length;
    while (i < j)
    {
      if (this._propsInOrder[i] == paramSettableBeanProperty) {
        return i;
      }
      i++;
    }
    throw new IllegalStateException("Illegal state: property '" + paramSettableBeanProperty.getName() + "' missing from _propsInOrder");
  }
  
  private final int _hashCode(String paramString)
  {
    return paramString.hashCode() & this._hashMask;
  }
  
  public static BeanPropertyMap construct(Collection<SettableBeanProperty> paramCollection, boolean paramBoolean)
  {
    return new BeanPropertyMap(paramBoolean, paramCollection);
  }
  
  private static final int findSize(int paramInt)
  {
    int j;
    if (paramInt <= 5) {
      j = 8;
    }
    for (;;)
    {
      return j;
      if (paramInt <= 12)
      {
        j = 16;
      }
      else
      {
        int i = paramInt + (paramInt >> 2);
        j = 32;
        while (j < i) {
          j += j;
        }
      }
    }
  }
  
  private List<SettableBeanProperty> properties()
  {
    ArrayList localArrayList = new ArrayList(this._size);
    int i = 1;
    int j = this._hashArea.length;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if (localSettableBeanProperty != null) {
        localArrayList.add(localSettableBeanProperty);
      }
      i += 2;
    }
    return localArrayList;
  }
  
  protected SettableBeanProperty _rename(SettableBeanProperty paramSettableBeanProperty, NameTransformer paramNameTransformer)
  {
    if (paramSettableBeanProperty == null) {}
    SettableBeanProperty localSettableBeanProperty;
    for (Object localObject = paramSettableBeanProperty;; localObject = localSettableBeanProperty)
    {
      return (SettableBeanProperty)localObject;
      localSettableBeanProperty = paramSettableBeanProperty.withSimpleName(paramNameTransformer.transform(paramSettableBeanProperty.getName()));
      JsonDeserializer localJsonDeserializer1 = localSettableBeanProperty.getValueDeserializer();
      if (localJsonDeserializer1 != null)
      {
        JsonDeserializer localJsonDeserializer2 = localJsonDeserializer1.unwrappingDeserializer(paramNameTransformer);
        if (localJsonDeserializer2 != localJsonDeserializer1) {
          localSettableBeanProperty = localSettableBeanProperty.withValueDeserializer(localJsonDeserializer2);
        }
      }
    }
  }
  
  public BeanPropertyMap assignIndexes()
  {
    int i = 1;
    int j = this._hashArea.length;
    int k = 0;
    int m;
    if (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if (localSettableBeanProperty == null) {
        break label53;
      }
      m = k + 1;
      localSettableBeanProperty.assignIndex(k);
    }
    for (;;)
    {
      i += 2;
      k = m;
      break;
      return this;
      label53:
      m = k;
    }
  }
  
  public SettableBeanProperty find(int paramInt)
  {
    int i = 1;
    int j = this._hashArea.length;
    SettableBeanProperty localSettableBeanProperty;
    if (i < j)
    {
      localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if ((localSettableBeanProperty == null) || (paramInt != localSettableBeanProperty.getPropertyIndex())) {}
    }
    for (;;)
    {
      return localSettableBeanProperty;
      i += 2;
      break;
      localSettableBeanProperty = null;
    }
  }
  
  public SettableBeanProperty find(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Can not pass null property name");
    }
    if (this._caseInsensitive) {
      paramString = paramString.toLowerCase();
    }
    int i = paramString.hashCode() & this._hashMask;
    int j = i << 1;
    Object localObject = this._hashArea[j];
    if ((localObject == paramString) || (paramString.equals(localObject))) {}
    for (SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[(j + 1)];; localSettableBeanProperty = _find2(paramString, i, localObject)) {
      return localSettableBeanProperty;
    }
  }
  
  public boolean findDeserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException
  {
    SettableBeanProperty localSettableBeanProperty = find(paramString);
    boolean bool;
    if (localSettableBeanProperty == null) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
        bool = true;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          wrapAndThrow(localException, paramObject, paramString, paramDeserializationContext);
        }
      }
    }
  }
  
  public SettableBeanProperty[] getPropertiesInInsertionOrder()
  {
    return this._propsInOrder;
  }
  
  protected final String getPropertyName(SettableBeanProperty paramSettableBeanProperty)
  {
    if (this._caseInsensitive) {}
    for (String str = paramSettableBeanProperty.getName().toLowerCase();; str = paramSettableBeanProperty.getName()) {
      return str;
    }
  }
  
  protected void init(Collection<SettableBeanProperty> paramCollection)
  {
    this._size = paramCollection.size();
    int i = findSize(this._size);
    this._hashMask = (i - 1);
    Object[] arrayOfObject = new Object[2 * (i + (i >> 1))];
    int j = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
      if (localSettableBeanProperty != null)
      {
        String str = getPropertyName(localSettableBeanProperty);
        int k = _hashCode(str);
        int m = k << 1;
        if (arrayOfObject[m] != null)
        {
          m = i + (k >> 1) << 1;
          if (arrayOfObject[m] != null)
          {
            m = j + (i + (i >> 1) << 1);
            j += 2;
            if (m >= arrayOfObject.length) {
              arrayOfObject = Arrays.copyOf(arrayOfObject, 4 + arrayOfObject.length);
            }
          }
        }
        arrayOfObject[m] = str;
        arrayOfObject[(m + 1)] = localSettableBeanProperty;
      }
    }
    this._hashArea = arrayOfObject;
    this._spillCount = j;
  }
  
  public Iterator<SettableBeanProperty> iterator()
  {
    return properties().iterator();
  }
  
  public void remove(SettableBeanProperty paramSettableBeanProperty)
  {
    ArrayList localArrayList = new ArrayList(this._size);
    String str = getPropertyName(paramSettableBeanProperty);
    boolean bool = false;
    int i = 1;
    int j = this._hashArea.length;
    if (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if (localSettableBeanProperty == null) {}
      for (;;)
      {
        i += 2;
        break;
        if (!bool)
        {
          bool = str.equals(localSettableBeanProperty.getName());
          if (bool)
          {
            this._propsInOrder[_findFromOrdered(localSettableBeanProperty)] = null;
            continue;
          }
        }
        localArrayList.add(localSettableBeanProperty);
      }
    }
    if (!bool) {
      throw new NoSuchElementException("No entry '" + paramSettableBeanProperty.getName() + "' found, can't remove");
    }
    init(localArrayList);
  }
  
  public BeanPropertyMap renameAll(NameTransformer paramNameTransformer)
  {
    if ((paramNameTransformer == null) || (paramNameTransformer == NameTransformer.NOP)) {}
    for (;;)
    {
      return this;
      int i = this._propsInOrder.length;
      ArrayList localArrayList = new ArrayList(i);
      int j = 0;
      if (j < i)
      {
        SettableBeanProperty localSettableBeanProperty = this._propsInOrder[j];
        if (localSettableBeanProperty == null) {
          localArrayList.add(localSettableBeanProperty);
        }
        for (;;)
        {
          j++;
          break;
          localArrayList.add(_rename(localSettableBeanProperty, paramNameTransformer));
        }
      }
      this = new BeanPropertyMap(this._caseInsensitive, localArrayList);
    }
  }
  
  public void replace(SettableBeanProperty paramSettableBeanProperty)
  {
    String str = getPropertyName(paramSettableBeanProperty);
    int i = 1;
    int j = this._hashArea.length;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if ((localSettableBeanProperty != null) && (localSettableBeanProperty.getName().equals(str)))
      {
        this._hashArea[i] = paramSettableBeanProperty;
        this._propsInOrder[_findFromOrdered(localSettableBeanProperty)] = paramSettableBeanProperty;
        return;
      }
      i += 2;
    }
    throw new NoSuchElementException("No entry '" + paramSettableBeanProperty.getName() + "' found, can't replace");
  }
  
  public int size()
  {
    return this._size;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Properties=[");
    int i = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
      int j = i + 1;
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(localSettableBeanProperty.getName());
      localStringBuilder.append('(');
      localStringBuilder.append(localSettableBeanProperty.getType());
      localStringBuilder.append(')');
      i = j;
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public BeanPropertyMap withProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    String str = getPropertyName(paramSettableBeanProperty);
    int i = 1;
    int j = this._hashArea.length;
    if (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._hashArea[i];
      if ((localSettableBeanProperty != null) && (localSettableBeanProperty.getName().equals(str)))
      {
        this._hashArea[i] = paramSettableBeanProperty;
        this._propsInOrder[_findFromOrdered(localSettableBeanProperty)] = paramSettableBeanProperty;
      }
    }
    for (;;)
    {
      return this;
      i += 2;
      break;
      int k = _hashCode(str);
      int m = 1 + this._hashMask;
      int n = k << 1;
      if (this._hashArea[n] != null)
      {
        n = m + (k >> 1) << 1;
        if (this._hashArea[n] != null)
        {
          n = (m + (m >> 1) << 1) + this._spillCount;
          this._spillCount = (2 + this._spillCount);
          if (n >= this._hashArea.length) {
            this._hashArea = Arrays.copyOf(this._hashArea, 4 + this._hashArea.length);
          }
        }
      }
      this._hashArea[n] = str;
      this._hashArea[(n + 1)] = paramSettableBeanProperty;
      int i1 = this._propsInOrder.length;
      this._propsInOrder = ((SettableBeanProperty[])Arrays.copyOf(this._propsInOrder, i1 + 1));
      this._propsInOrder[i1] = paramSettableBeanProperty;
    }
  }
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonProcessingException))) {
        break label100;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    label100:
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/BeanPropertyMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */