package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StdSubtypeResolver
  extends SubtypeResolver
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected LinkedHashSet<NamedType> _registeredSubtypes;
  
  protected void _collectAndResolve(AnnotatedClass paramAnnotatedClass, NamedType paramNamedType, MapperConfig<?> paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector, HashMap<NamedType, NamedType> paramHashMap)
  {
    if (!paramNamedType.hasName())
    {
      String str = paramAnnotationIntrospector.findTypeName(paramAnnotatedClass);
      if (str != null) {
        paramNamedType = new NamedType(paramNamedType.getType(), str);
      }
    }
    if (paramHashMap.containsKey(paramNamedType)) {
      if ((paramNamedType.hasName()) && (!((NamedType)paramHashMap.get(paramNamedType)).hasName())) {
        paramHashMap.put(paramNamedType, paramNamedType);
      }
    }
    for (;;)
    {
      return;
      paramHashMap.put(paramNamedType, paramNamedType);
      List localList = paramAnnotationIntrospector.findSubtypes(paramAnnotatedClass);
      if ((localList != null) && (!localList.isEmpty()))
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          NamedType localNamedType = (NamedType)localIterator.next();
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), paramAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, paramAnnotationIntrospector, paramHashMap);
        }
      }
    }
  }
  
  protected void _collectAndResolveByTypeId(AnnotatedClass paramAnnotatedClass, NamedType paramNamedType, MapperConfig<?> paramMapperConfig, Set<Class<?>> paramSet, Map<String, NamedType> paramMap)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    if (!paramNamedType.hasName())
    {
      String str = localAnnotationIntrospector.findTypeName(paramAnnotatedClass);
      if (str != null) {
        paramNamedType = new NamedType(paramNamedType.getType(), str);
      }
    }
    if (paramNamedType.hasName()) {
      paramMap.put(paramNamedType.getName(), paramNamedType);
    }
    if (paramSet.add(paramNamedType.getType()))
    {
      List localList = localAnnotationIntrospector.findSubtypes(paramAnnotatedClass);
      if ((localList != null) && (!localList.isEmpty()))
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          NamedType localNamedType = (NamedType)localIterator.next();
          _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, paramSet, paramMap);
        }
      }
    }
  }
  
  protected Collection<NamedType> _combineNamedAndUnnamed(Set<Class<?>> paramSet, Map<String, NamedType> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.values());
    Iterator localIterator1 = paramMap.values().iterator();
    while (localIterator1.hasNext()) {
      paramSet.remove(((NamedType)localIterator1.next()).getType());
    }
    Iterator localIterator2 = paramSet.iterator();
    while (localIterator2.hasNext()) {
      localArrayList.add(new NamedType((Class)localIterator2.next()));
    }
    return localArrayList;
  }
  
  @Deprecated
  public Collection<NamedType> collectAndResolveSubtypes(AnnotatedClass paramAnnotatedClass, MapperConfig<?> paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector)
  {
    return collectAndResolveSubtypesByClass(paramMapperConfig, paramAnnotatedClass);
  }
  
  @Deprecated
  public Collection<NamedType> collectAndResolveSubtypes(AnnotatedMember paramAnnotatedMember, MapperConfig<?> paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector, JavaType paramJavaType)
  {
    return collectAndResolveSubtypesByClass(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    HashMap localHashMap = new HashMap();
    if (this._registeredSubtypes != null)
    {
      Class localClass = paramAnnotatedClass.getRawType();
      Iterator localIterator = this._registeredSubtypes.iterator();
      while (localIterator.hasNext())
      {
        NamedType localNamedType = (NamedType)localIterator.next();
        if (localClass.isAssignableFrom(localNamedType.getType())) {
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localAnnotationIntrospector, localHashMap);
        }
      }
    }
    _collectAndResolve(paramAnnotatedClass, new NamedType(paramAnnotatedClass.getRawType(), null), paramMapperConfig, localAnnotationIntrospector, localHashMap);
    return new ArrayList(localHashMap.values());
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    if (paramJavaType == null) {}
    HashMap localHashMap;
    for (Class localClass1 = paramAnnotatedMember.getRawType();; localClass1 = paramJavaType.getRawClass())
    {
      localHashMap = new HashMap();
      if (this._registeredSubtypes == null) {
        break;
      }
      Iterator localIterator2 = this._registeredSubtypes.iterator();
      while (localIterator2.hasNext())
      {
        NamedType localNamedType3 = (NamedType)localIterator2.next();
        Class localClass2 = localNamedType3.getType();
        if (localClass1.isAssignableFrom(localClass2)) {
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localNamedType3.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType3, paramMapperConfig, localAnnotationIntrospector, localHashMap);
        }
      }
    }
    List localList = localAnnotationIntrospector.findSubtypes(paramAnnotatedMember);
    if (localList != null)
    {
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext())
      {
        NamedType localNamedType2 = (NamedType)localIterator1.next();
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localNamedType2.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType2, paramMapperConfig, localAnnotationIntrospector, localHashMap);
      }
    }
    NamedType localNamedType1 = new NamedType(localClass1, null);
    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(localClass1, localAnnotationIntrospector, paramMapperConfig), localNamedType1, paramMapperConfig, localAnnotationIntrospector, localHashMap);
    return new ArrayList(localHashMap.values());
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass)
  {
    HashSet localHashSet = new HashSet();
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    _collectAndResolveByTypeId(paramAnnotatedClass, new NamedType(paramAnnotatedClass.getRawType(), null), paramMapperConfig, localHashSet, localLinkedHashMap);
    if (this._registeredSubtypes != null)
    {
      Class localClass = paramAnnotatedClass.getRawType();
      Iterator localIterator = this._registeredSubtypes.iterator();
      while (localIterator.hasNext())
      {
        NamedType localNamedType = (NamedType)localIterator.next();
        if (localClass.isAssignableFrom(localNamedType.getType()))
        {
          AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
          _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType, paramMapperConfig, localHashSet, localLinkedHashMap);
        }
      }
    }
    return _combineNamedAndUnnamed(localHashSet, localLinkedHashMap);
  }
  
  public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    if (paramJavaType == null) {}
    HashSet localHashSet;
    LinkedHashMap localLinkedHashMap;
    for (Class localClass1 = paramAnnotatedMember.getRawType();; localClass1 = paramJavaType.getRawClass())
    {
      localHashSet = new HashSet();
      localLinkedHashMap = new LinkedHashMap();
      NamedType localNamedType1 = new NamedType(localClass1, null);
      _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localClass1, localAnnotationIntrospector, paramMapperConfig), localNamedType1, paramMapperConfig, localHashSet, localLinkedHashMap);
      List localList = localAnnotationIntrospector.findSubtypes(paramAnnotatedMember);
      if (localList == null) {
        break;
      }
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        NamedType localNamedType3 = (NamedType)localIterator2.next();
        _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType3.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType3, paramMapperConfig, localHashSet, localLinkedHashMap);
      }
    }
    if (this._registeredSubtypes != null)
    {
      Iterator localIterator1 = this._registeredSubtypes.iterator();
      while (localIterator1.hasNext())
      {
        NamedType localNamedType2 = (NamedType)localIterator1.next();
        Class localClass2 = localNamedType2.getType();
        if (localClass1.isAssignableFrom(localClass2)) {
          _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(localNamedType2.getType(), localAnnotationIntrospector, paramMapperConfig), localNamedType2, paramMapperConfig, localHashSet, localLinkedHashMap);
        }
      }
    }
    return _combineNamedAndUnnamed(localHashSet, localLinkedHashMap);
  }
  
  public void registerSubtypes(NamedType... paramVarArgs)
  {
    if (this._registeredSubtypes == null) {
      this._registeredSubtypes = new LinkedHashSet();
    }
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      NamedType localNamedType = paramVarArgs[j];
      this._registeredSubtypes.add(localNamedType);
    }
  }
  
  public void registerSubtypes(Class<?>... paramVarArgs)
  {
    NamedType[] arrayOfNamedType = new NamedType[paramVarArgs.length];
    int i = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      arrayOfNamedType[i] = new NamedType(paramVarArgs[i]);
      i++;
    }
    registerSubtypes(arrayOfNamedType);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/StdSubtypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */