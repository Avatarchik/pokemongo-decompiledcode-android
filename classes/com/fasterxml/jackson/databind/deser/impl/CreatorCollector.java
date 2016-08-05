package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreatorCollector
{
  protected static final int C_BOOLEAN = 5;
  protected static final int C_DEFAULT = 0;
  protected static final int C_DELEGATE = 6;
  protected static final int C_DOUBLE = 4;
  protected static final int C_INT = 2;
  protected static final int C_LONG = 3;
  protected static final int C_PROPS = 7;
  protected static final int C_STRING = 1;
  protected static final String[] TYPE_DESCS;
  protected final BeanDescription _beanDesc;
  protected final boolean _canFixAccess;
  protected final AnnotatedWithParams[] _creators = new AnnotatedWithParams[8];
  protected SettableBeanProperty[] _delegateArgs;
  protected int _explicitCreators = 0;
  protected boolean _hasNonDefaultCreator = false;
  protected AnnotatedParameter _incompleteParameter;
  protected SettableBeanProperty[] _propertyBasedArgs;
  
  static
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "default";
    arrayOfString[1] = "String";
    arrayOfString[2] = "int";
    arrayOfString[3] = "long";
    arrayOfString[4] = "double";
    arrayOfString[5] = "boolean";
    arrayOfString[6] = "delegate";
    arrayOfString[7] = "property-based";
    TYPE_DESCS = arrayOfString;
  }
  
  public CreatorCollector(BeanDescription paramBeanDescription, boolean paramBoolean)
  {
    this._beanDesc = paramBeanDescription;
    this._canFixAccess = paramBoolean;
  }
  
  private <T extends AnnotatedMember> T _fixAccess(T paramT)
  {
    if ((paramT != null) && (this._canFixAccess)) {
      ClassUtil.checkAndFixAccess((Member)paramT.getAnnotated());
    }
    return paramT;
  }
  
  @Deprecated
  public void addBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 5, paramBoolean);
  }
  
  public void addDelegatingCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    verifyNonDup(paramAnnotatedWithParams, 6, paramBoolean);
    this._delegateArgs = paramArrayOfSettableBeanProperty;
  }
  
  @Deprecated
  public void addDelegatingCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty)
  {
    addDelegatingCreator(paramAnnotatedWithParams, false, paramArrayOfCreatorProperty);
  }
  
  @Deprecated
  public void addDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 4, paramBoolean);
  }
  
  public void addIncompeteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    if (this._incompleteParameter == null) {
      this._incompleteParameter = paramAnnotatedParameter;
    }
  }
  
  @Deprecated
  public void addIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addIntCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 2, paramBoolean);
  }
  
  @Deprecated
  public void addLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addBooleanCreator(paramAnnotatedWithParams, false);
  }
  
  public void addLongCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 3, paramBoolean);
  }
  
  public void addPropertyCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    verifyNonDup(paramAnnotatedWithParams, 7, paramBoolean);
    if (paramArrayOfSettableBeanProperty.length > 1)
    {
      HashMap localHashMap = new HashMap();
      int i = 0;
      int j = paramArrayOfSettableBeanProperty.length;
      if (i < j)
      {
        String str = paramArrayOfSettableBeanProperty[i].getName();
        if ((str.length() == 0) && (paramArrayOfSettableBeanProperty[i].getInjectableValueId() != null)) {}
        Integer localInteger;
        do
        {
          i++;
          break;
          localInteger = (Integer)localHashMap.put(str, Integer.valueOf(i));
        } while (localInteger == null);
        throw new IllegalArgumentException("Duplicate creator property \"" + str + "\" (index " + localInteger + " vs " + i + ")");
      }
    }
    this._propertyBasedArgs = paramArrayOfSettableBeanProperty;
  }
  
  @Deprecated
  public void addPropertyCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty)
  {
    addPropertyCreator(paramAnnotatedWithParams, false, paramArrayOfCreatorProperty);
  }
  
  @Deprecated
  public void addStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    addStringCreator(paramAnnotatedWithParams, false);
  }
  
  public void addStringCreator(AnnotatedWithParams paramAnnotatedWithParams, boolean paramBoolean)
  {
    verifyNonDup(paramAnnotatedWithParams, 1, paramBoolean);
  }
  
  public ValueInstantiator constructValueInstantiator(DeserializationConfig paramDeserializationConfig)
  {
    int i;
    JavaType localJavaType1;
    JavaType localJavaType2;
    int j;
    label44:
    Class localClass;
    Object localObject;
    if (!this._hasNonDefaultCreator)
    {
      i = 1;
      if ((i == 0) && (this._creators[6] != null)) {
        break label97;
      }
      localJavaType1 = null;
      localJavaType2 = this._beanDesc.getType();
      if (this._hasNonDefaultCreator) {
        break label170;
      }
      j = 1;
      if ((i & j) == 0) {
        break label223;
      }
      localClass = localJavaType2.getRawClass();
      if ((localClass != Collection.class) && (localClass != List.class) && (localClass != ArrayList.class)) {
        break label176;
      }
      localObject = new Vanilla(1);
    }
    for (;;)
    {
      return (ValueInstantiator)localObject;
      i = 0;
      break;
      label97:
      int k = 0;
      int m;
      int n;
      if (this._delegateArgs != null)
      {
        m = 0;
        n = this._delegateArgs.length;
      }
      for (;;)
      {
        if (m < n)
        {
          if (this._delegateArgs[m] == null) {
            k = m;
          }
        }
        else
        {
          localJavaType1 = this._beanDesc.bindingsForBeanType().resolveType(this._creators[6].getGenericParameterType(k));
          break;
        }
        m++;
      }
      label170:
      j = 0;
      break label44;
      label176:
      if ((localClass == Map.class) || (localClass == LinkedHashMap.class))
      {
        localObject = new Vanilla(2);
      }
      else if (localClass == HashMap.class)
      {
        localObject = new Vanilla(3);
      }
      else
      {
        label223:
        localObject = new StdValueInstantiator(paramDeserializationConfig, localJavaType2);
        ((StdValueInstantiator)localObject).configureFromObjectSettings(this._creators[0], this._creators[6], localJavaType1, this._delegateArgs, this._creators[7], this._propertyBasedArgs);
        ((StdValueInstantiator)localObject).configureFromStringCreator(this._creators[1]);
        ((StdValueInstantiator)localObject).configureFromIntCreator(this._creators[2]);
        ((StdValueInstantiator)localObject).configureFromLongCreator(this._creators[3]);
        ((StdValueInstantiator)localObject).configureFromDoubleCreator(this._creators[4]);
        ((StdValueInstantiator)localObject).configureFromBooleanCreator(this._creators[5]);
        ((StdValueInstantiator)localObject).configureIncompleteParameter(this._incompleteParameter);
      }
    }
  }
  
  public boolean hasDefaultCreator()
  {
    boolean bool = false;
    if (this._creators[0] != null) {
      bool = true;
    }
    return bool;
  }
  
  public boolean hasDelegatingCreator()
  {
    if (this._creators[6] != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasPropertyBasedCreator()
  {
    if (this._creators[7] != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setDefaultCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._creators[0] = ((AnnotatedWithParams)_fixAccess(paramAnnotatedWithParams));
  }
  
  protected void verifyNonDup(AnnotatedWithParams paramAnnotatedWithParams, int paramInt, boolean paramBoolean)
  {
    int i = 1;
    int j = i << paramInt;
    this._hasNonDefaultCreator = i;
    AnnotatedWithParams localAnnotatedWithParams = this._creators[paramInt];
    if (localAnnotatedWithParams != null) {
      if ((j & this._explicitCreators) != 0) {
        if (paramBoolean) {}
      }
    }
    for (;;)
    {
      return;
      i = 1;
      for (;;)
      {
        if ((i != 0) && (localAnnotatedWithParams.getClass() == paramAnnotatedWithParams.getClass()))
        {
          Class localClass1 = localAnnotatedWithParams.getRawParameterType(0);
          Class localClass2 = paramAnnotatedWithParams.getRawParameterType(0);
          if (localClass1 == localClass2)
          {
            throw new IllegalArgumentException("Conflicting " + TYPE_DESCS[paramInt] + " creators: already had explicitly marked " + localAnnotatedWithParams + ", encountered " + paramAnnotatedWithParams);
            if (paramBoolean) {
              for (;;)
              {
                i = 0;
              }
            }
          }
          else
          {
            if (localClass2.isAssignableFrom(localClass1)) {
              break;
            }
          }
        }
      }
      if (paramBoolean) {
        this._explicitCreators = (j | this._explicitCreators);
      }
      this._creators[paramInt] = ((AnnotatedWithParams)_fixAccess(paramAnnotatedWithParams));
    }
  }
  
  protected static final class Vanilla
    extends ValueInstantiator
    implements Serializable
  {
    public static final int TYPE_COLLECTION = 1;
    public static final int TYPE_HASH_MAP = 3;
    public static final int TYPE_MAP = 2;
    private static final long serialVersionUID = 1L;
    private final int _type;
    
    public Vanilla(int paramInt)
    {
      this._type = paramInt;
    }
    
    public boolean canCreateUsingDefault()
    {
      return true;
    }
    
    public boolean canInstantiate()
    {
      return true;
    }
    
    public Object createUsingDefault(DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject;
      switch (this._type)
      {
      default: 
        throw new IllegalStateException("Unknown type " + this._type);
      case 1: 
        localObject = new ArrayList();
      }
      for (;;)
      {
        return localObject;
        localObject = new LinkedHashMap();
        continue;
        localObject = new HashMap();
      }
    }
    
    public String getValueTypeDesc()
    {
      String str;
      switch (this._type)
      {
      default: 
        str = Object.class.getName();
      }
      for (;;)
      {
        return str;
        str = ArrayList.class.getName();
        continue;
        str = LinkedHashMap.class.getName();
        continue;
        str = HashMap.class.getName();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/CreatorCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */