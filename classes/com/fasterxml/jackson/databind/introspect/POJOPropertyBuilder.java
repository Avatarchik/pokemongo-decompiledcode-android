package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class POJOPropertyBuilder
  extends BeanPropertyDefinition
  implements Comparable<POJOPropertyBuilder>
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected Linked<AnnotatedParameter> _ctorParameters;
  protected Linked<AnnotatedField> _fields;
  protected final boolean _forSerialization;
  protected Linked<AnnotatedMethod> _getters;
  protected final PropertyName _internalName;
  protected final PropertyName _name;
  protected Linked<AnnotatedMethod> _setters;
  
  public POJOPropertyBuilder(PropertyName paramPropertyName, AnnotationIntrospector paramAnnotationIntrospector, boolean paramBoolean)
  {
    this(paramPropertyName, paramPropertyName, paramAnnotationIntrospector, paramBoolean);
  }
  
  protected POJOPropertyBuilder(PropertyName paramPropertyName1, PropertyName paramPropertyName2, AnnotationIntrospector paramAnnotationIntrospector, boolean paramBoolean)
  {
    this._internalName = paramPropertyName1;
    this._name = paramPropertyName2;
    this._annotationIntrospector = paramAnnotationIntrospector;
    this._forSerialization = paramBoolean;
  }
  
  public POJOPropertyBuilder(POJOPropertyBuilder paramPOJOPropertyBuilder, PropertyName paramPropertyName)
  {
    this._internalName = paramPOJOPropertyBuilder._internalName;
    this._name = paramPropertyName;
    this._annotationIntrospector = paramPOJOPropertyBuilder._annotationIntrospector;
    this._fields = paramPOJOPropertyBuilder._fields;
    this._ctorParameters = paramPOJOPropertyBuilder._ctorParameters;
    this._getters = paramPOJOPropertyBuilder._getters;
    this._setters = paramPOJOPropertyBuilder._setters;
    this._forSerialization = paramPOJOPropertyBuilder._forSerialization;
  }
  
  private <T> boolean _anyExplicitNames(Linked<T> paramLinked)
  {
    if (paramLinked != null) {
      if ((paramLinked.name == null) || (!paramLinked.isNameExplicit)) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramLinked = paramLinked.next;
      break;
    }
  }
  
  private <T> boolean _anyExplicits(Linked<T> paramLinked)
  {
    if (paramLinked != null) {
      if ((paramLinked.name == null) || (!paramLinked.name.hasSimpleName())) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramLinked = paramLinked.next;
      break;
    }
  }
  
  private <T> boolean _anyIgnorals(Linked<T> paramLinked)
  {
    if (paramLinked != null) {
      if (!paramLinked.isMarkedIgnored) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramLinked = paramLinked.next;
      break;
    }
  }
  
  private <T> boolean _anyVisible(Linked<T> paramLinked)
  {
    if (paramLinked != null) {
      if (!paramLinked.isVisible) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramLinked = paramLinked.next;
      break;
    }
  }
  
  private <T extends AnnotatedMember> Linked<T> _applyAnnotations(Linked<T> paramLinked, AnnotationMap paramAnnotationMap)
  {
    AnnotatedMember localAnnotatedMember = (AnnotatedMember)((AnnotatedMember)paramLinked.value).withAnnotations(paramAnnotationMap);
    if (paramLinked.next != null) {
      paramLinked = paramLinked.withNext(_applyAnnotations(paramLinked.next, paramAnnotationMap));
    }
    return paramLinked.withValue(localAnnotatedMember);
  }
  
  private void _explode(Collection<PropertyName> paramCollection, Map<PropertyName, POJOPropertyBuilder> paramMap, Linked<?> paramLinked)
  {
    Object localObject = paramLinked;
    if (localObject != null)
    {
      PropertyName localPropertyName = ((Linked)localObject).name;
      if ((!((Linked)localObject).isNameExplicit) || (localPropertyName == null)) {
        if (((Linked)localObject).isVisible) {}
      }
      for (;;)
      {
        localObject = ((Linked)localObject).next;
        break;
        throw new IllegalStateException("Conflicting/ambiguous property name definitions (implicit name '" + this._name + "'): found multiple explicit names: " + paramCollection + ", but also implicit accessor: " + localObject);
        POJOPropertyBuilder localPOJOPropertyBuilder = (POJOPropertyBuilder)paramMap.get(localPropertyName);
        if (localPOJOPropertyBuilder == null)
        {
          localPOJOPropertyBuilder = new POJOPropertyBuilder(this._internalName, localPropertyName, this._annotationIntrospector, this._forSerialization);
          paramMap.put(localPropertyName, localPOJOPropertyBuilder);
        }
        if (paramLinked == this._fields)
        {
          localPOJOPropertyBuilder._fields = ((Linked)localObject).withNext(localPOJOPropertyBuilder._fields);
        }
        else if (paramLinked == this._getters)
        {
          localPOJOPropertyBuilder._getters = ((Linked)localObject).withNext(localPOJOPropertyBuilder._getters);
        }
        else if (paramLinked == this._setters)
        {
          localPOJOPropertyBuilder._setters = ((Linked)localObject).withNext(localPOJOPropertyBuilder._setters);
        }
        else
        {
          if (paramLinked != this._ctorParameters) {
            break label251;
          }
          localPOJOPropertyBuilder._ctorParameters = ((Linked)localObject).withNext(localPOJOPropertyBuilder._ctorParameters);
        }
      }
      label251:
      throw new IllegalStateException("Internal error: mismatched accessors, property: " + this);
    }
  }
  
  private Set<PropertyName> _findExplicitNames(Linked<? extends AnnotatedMember> paramLinked, Set<PropertyName> paramSet)
  {
    if (paramLinked != null)
    {
      if ((!paramLinked.isNameExplicit) || (paramLinked.name == null)) {}
      for (;;)
      {
        paramLinked = paramLinked.next;
        break;
        if (paramSet == null) {
          paramSet = new HashSet();
        }
        paramSet.add(paramLinked.name);
      }
    }
    return paramSet;
  }
  
  private <T extends AnnotatedMember> AnnotationMap _getAllAnnotations(Linked<T> paramLinked)
  {
    AnnotationMap localAnnotationMap = ((AnnotatedMember)paramLinked.value).getAllAnnotations();
    if (paramLinked.next != null) {
      localAnnotationMap = AnnotationMap.merge(localAnnotationMap, _getAllAnnotations(paramLinked.next));
    }
    return localAnnotationMap;
  }
  
  private AnnotationMap _mergeAnnotations(int paramInt, Linked<? extends AnnotatedMember>... paramVarArgs)
  {
    AnnotationMap localAnnotationMap = _getAllAnnotations(paramVarArgs[paramInt]);
    for (int i = paramInt + 1;; i++) {
      if (i < paramVarArgs.length)
      {
        if (paramVarArgs[i] != null) {
          localAnnotationMap = AnnotationMap.merge(localAnnotationMap, _mergeAnnotations(i, paramVarArgs));
        }
      }
      else {
        return localAnnotationMap;
      }
    }
  }
  
  private <T> Linked<T> _removeIgnored(Linked<T> paramLinked)
  {
    if (paramLinked == null) {}
    for (;;)
    {
      return paramLinked;
      paramLinked = paramLinked.withoutIgnored();
    }
  }
  
  private <T> Linked<T> _removeNonVisible(Linked<T> paramLinked)
  {
    if (paramLinked == null) {}
    for (;;)
    {
      return paramLinked;
      paramLinked = paramLinked.withoutNonVisible();
    }
  }
  
  private <T> Linked<T> _trimByVisibility(Linked<T> paramLinked)
  {
    if (paramLinked == null) {}
    for (;;)
    {
      return paramLinked;
      paramLinked = paramLinked.trimByVisibility();
    }
  }
  
  private static <T> Linked<T> merge(Linked<T> paramLinked1, Linked<T> paramLinked2)
  {
    if (paramLinked1 == null) {}
    for (;;)
    {
      return paramLinked2;
      if (paramLinked2 == null) {
        paramLinked2 = paramLinked1;
      } else {
        paramLinked2 = paramLinked1.append(paramLinked2);
      }
    }
  }
  
  protected String _findDefaultValue()
  {
    (String)fromMemberAnnotations(new WithMember()
    {
      public String withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.findPropertyDefaultValue(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected String _findDescription()
  {
    (String)fromMemberAnnotations(new WithMember()
    {
      public String withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.findPropertyDescription(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected Integer _findIndex()
  {
    (Integer)fromMemberAnnotations(new WithMember()
    {
      public Integer withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.findPropertyIndex(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected Boolean _findRequired()
  {
    (Boolean)fromMemberAnnotations(new WithMember()
    {
      public Boolean withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.hasRequiredMarker(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected int _getterPriority(AnnotatedMethod paramAnnotatedMethod)
  {
    int i = 2;
    String str = paramAnnotatedMethod.getName();
    if ((str.startsWith("get")) && (str.length() > 3)) {}
    for (i = 1;; i = 3) {
      do
      {
        return i;
      } while ((str.startsWith("is")) && (str.length() > i));
    }
  }
  
  protected int _setterPriority(AnnotatedMethod paramAnnotatedMethod)
  {
    String str = paramAnnotatedMethod.getName();
    if ((str.startsWith("set")) && (str.length() > 3)) {}
    for (int i = 1;; i = 2) {
      return i;
    }
  }
  
  public void addAll(POJOPropertyBuilder paramPOJOPropertyBuilder)
  {
    this._fields = merge(this._fields, paramPOJOPropertyBuilder._fields);
    this._ctorParameters = merge(this._ctorParameters, paramPOJOPropertyBuilder._ctorParameters);
    this._getters = merge(this._getters, paramPOJOPropertyBuilder._getters);
    this._setters = merge(this._setters, paramPOJOPropertyBuilder._setters);
  }
  
  public void addCtor(AnnotatedParameter paramAnnotatedParameter, PropertyName paramPropertyName, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this._ctorParameters = new Linked(paramAnnotatedParameter, this._ctorParameters, paramPropertyName, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public void addField(AnnotatedField paramAnnotatedField, PropertyName paramPropertyName, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this._fields = new Linked(paramAnnotatedField, this._fields, paramPropertyName, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public void addGetter(AnnotatedMethod paramAnnotatedMethod, PropertyName paramPropertyName, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this._getters = new Linked(paramAnnotatedMethod, this._getters, paramPropertyName, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public void addSetter(AnnotatedMethod paramAnnotatedMethod, PropertyName paramPropertyName, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this._setters = new Linked(paramAnnotatedMethod, this._setters, paramPropertyName, paramBoolean1, paramBoolean2, paramBoolean3);
  }
  
  public boolean anyIgnorals()
  {
    if ((_anyIgnorals(this._fields)) || (_anyIgnorals(this._getters)) || (_anyIgnorals(this._setters)) || (_anyIgnorals(this._ctorParameters))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean anyVisible()
  {
    if ((_anyVisible(this._fields)) || (_anyVisible(this._getters)) || (_anyVisible(this._setters)) || (_anyVisible(this._ctorParameters))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int compareTo(POJOPropertyBuilder paramPOJOPropertyBuilder)
  {
    int i;
    if (this._ctorParameters != null)
    {
      if (paramPOJOPropertyBuilder._ctorParameters != null) {
        break label31;
      }
      i = -1;
    }
    for (;;)
    {
      return i;
      if (paramPOJOPropertyBuilder._ctorParameters != null) {
        i = 1;
      } else {
        label31:
        i = getName().compareTo(paramPOJOPropertyBuilder.getName());
      }
    }
  }
  
  public boolean couldDeserialize()
  {
    if ((this._ctorParameters != null) || (this._setters != null) || (this._fields != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean couldSerialize()
  {
    if ((this._getters != null) || (this._fields != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Collection<POJOPropertyBuilder> explode(Collection<PropertyName> paramCollection)
  {
    HashMap localHashMap = new HashMap();
    _explode(paramCollection, localHashMap, this._fields);
    _explode(paramCollection, localHashMap, this._getters);
    _explode(paramCollection, localHashMap, this._setters);
    _explode(paramCollection, localHashMap, this._ctorParameters);
    return localHashMap.values();
  }
  
  public JsonProperty.Access findAccess()
  {
    (JsonProperty.Access)fromMemberAnnotationsExcept(new WithMember()
    {
      public JsonProperty.Access withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.findPropertyAccess(paramAnonymousAnnotatedMember);
      }
    }, JsonProperty.Access.AUTO);
  }
  
  public Set<PropertyName> findExplicitNames()
  {
    Set localSet1 = _findExplicitNames(this._fields, null);
    Set localSet2 = _findExplicitNames(this._getters, localSet1);
    Set localSet3 = _findExplicitNames(this._setters, localSet2);
    Set localSet4 = _findExplicitNames(this._ctorParameters, localSet3);
    if (localSet4 == null) {
      localSet4 = Collections.emptySet();
    }
    return localSet4;
  }
  
  public JsonInclude.Include findInclusion()
  {
    JsonInclude.Include localInclude = null;
    if (this._annotationIntrospector == null) {}
    for (;;)
    {
      return localInclude;
      AnnotatedMember localAnnotatedMember = getAccessor();
      localInclude = this._annotationIntrospector.findSerializationInclusion(localAnnotatedMember, null);
    }
  }
  
  public ObjectIdInfo findObjectIdInfo()
  {
    (ObjectIdInfo)fromMemberAnnotations(new WithMember()
    {
      public ObjectIdInfo withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        ObjectIdInfo localObjectIdInfo = POJOPropertyBuilder.this._annotationIntrospector.findObjectIdInfo(paramAnonymousAnnotatedMember);
        if (localObjectIdInfo != null) {
          localObjectIdInfo = POJOPropertyBuilder.this._annotationIntrospector.findObjectReferenceInfo(paramAnonymousAnnotatedMember, localObjectIdInfo);
        }
        return localObjectIdInfo;
      }
    });
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType()
  {
    (AnnotationIntrospector.ReferenceProperty)fromMemberAnnotations(new WithMember()
    {
      public AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.findReferenceType(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  public Class<?>[] findViews()
  {
    (Class[])fromMemberAnnotations(new WithMember()
    {
      public Class<?>[] withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.findViews(paramAnonymousAnnotatedMember);
      }
    });
  }
  
  protected <T> T fromMemberAnnotations(WithMember<T> paramWithMember)
  {
    Object localObject = null;
    if (this._annotationIntrospector != null)
    {
      if (!this._forSerialization) {
        break label70;
      }
      if (this._getters == null) {}
    }
    for (localObject = paramWithMember.withMember((AnnotatedMember)this._getters.value);; localObject = paramWithMember.withMember((AnnotatedMember)this._setters.value)) {
      label70:
      do
      {
        if ((localObject == null) && (this._fields != null)) {
          localObject = paramWithMember.withMember((AnnotatedMember)this._fields.value);
        }
        return (T)localObject;
        if (this._ctorParameters != null) {
          localObject = paramWithMember.withMember((AnnotatedMember)this._ctorParameters.value);
        }
      } while ((localObject != null) || (this._setters == null));
    }
  }
  
  protected <T> T fromMemberAnnotationsExcept(WithMember<T> paramWithMember, T paramT)
  {
    Object localObject;
    if (this._annotationIntrospector == null) {
      localObject = null;
    }
    for (;;)
    {
      return (T)localObject;
      if (this._forSerialization)
      {
        if (this._getters != null)
        {
          localObject = paramWithMember.withMember((AnnotatedMember)this._getters.value);
          if ((localObject != null) && (localObject != paramT)) {}
        }
        else if (this._fields != null)
        {
          localObject = paramWithMember.withMember((AnnotatedMember)this._fields.value);
          if ((localObject != null) && (localObject != paramT)) {}
        }
        else if (this._ctorParameters != null)
        {
          localObject = paramWithMember.withMember((AnnotatedMember)this._ctorParameters.value);
          if ((localObject != null) && (localObject != paramT)) {}
        }
        else if (this._setters != null)
        {
          localObject = paramWithMember.withMember((AnnotatedMember)this._setters.value);
          if ((localObject != null) && (localObject != paramT)) {}
        }
        else
        {
          localObject = null;
        }
      }
      else if (this._ctorParameters != null)
      {
        localObject = paramWithMember.withMember((AnnotatedMember)this._ctorParameters.value);
        if ((localObject != null) && (localObject != paramT)) {}
      }
      else if (this._setters != null)
      {
        localObject = paramWithMember.withMember((AnnotatedMember)this._setters.value);
        if ((localObject != null) && (localObject != paramT)) {}
      }
      else if (this._fields != null)
      {
        localObject = paramWithMember.withMember((AnnotatedMember)this._fields.value);
        if ((localObject != null) && (localObject != paramT)) {}
      }
      else if (this._getters != null)
      {
        localObject = paramWithMember.withMember((AnnotatedMember)this._getters.value);
        if ((localObject != null) && (localObject != paramT)) {}
      }
      else
      {
        localObject = null;
      }
    }
  }
  
  public AnnotatedMember getAccessor()
  {
    Object localObject = getGetter();
    if (localObject == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedParameter getConstructorParameter()
  {
    AnnotatedParameter localAnnotatedParameter;
    if (this._ctorParameters == null) {
      localAnnotatedParameter = null;
    }
    for (;;)
    {
      return localAnnotatedParameter;
      Linked localLinked = this._ctorParameters;
      do
      {
        if ((((AnnotatedParameter)localLinked.value).getOwner() instanceof AnnotatedConstructor))
        {
          localAnnotatedParameter = (AnnotatedParameter)localLinked.value;
          break;
        }
        localLinked = localLinked.next;
      } while (localLinked != null);
      localAnnotatedParameter = (AnnotatedParameter)this._ctorParameters.value;
    }
  }
  
  public Iterator<AnnotatedParameter> getConstructorParameters()
  {
    if (this._ctorParameters == null) {}
    for (Object localObject = EmptyIterator.instance();; localObject = new MemberIterator(this._ctorParameters)) {
      return (Iterator<AnnotatedParameter>)localObject;
    }
  }
  
  public AnnotatedField getField()
  {
    if (this._fields == null)
    {
      localObject = null;
      return (AnnotatedField)localObject;
    }
    Object localObject = (AnnotatedField)this._fields.value;
    Linked localLinked = this._fields.next;
    label30:
    AnnotatedField localAnnotatedField;
    Class localClass1;
    Class localClass2;
    if (localLinked != null)
    {
      localAnnotatedField = (AnnotatedField)localLinked.value;
      localClass1 = ((AnnotatedField)localObject).getDeclaringClass();
      localClass2 = localAnnotatedField.getDeclaringClass();
      if (localClass1 == localClass2) {
        break label91;
      }
      if (!localClass1.isAssignableFrom(localClass2)) {
        break label81;
      }
      localObject = localAnnotatedField;
    }
    label81:
    while (localClass2.isAssignableFrom(localClass1))
    {
      localLinked = localLinked.next;
      break label30;
      break;
    }
    label91:
    throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + ((AnnotatedField)localObject).getFullName() + " vs " + localAnnotatedField.getFullName());
  }
  
  public PropertyName getFullName()
  {
    return this._name;
  }
  
  public AnnotatedMethod getGetter()
  {
    Object localObject = this._getters;
    AnnotatedMethod localAnnotatedMethod;
    if (localObject == null) {
      localAnnotatedMethod = null;
    }
    for (;;)
    {
      return localAnnotatedMethod;
      Linked localLinked = ((Linked)localObject).next;
      if (localLinked == null)
      {
        localAnnotatedMethod = (AnnotatedMethod)((Linked)localObject).value;
      }
      else
      {
        if (localLinked != null)
        {
          Class localClass1 = ((AnnotatedMethod)((Linked)localObject).value).getDeclaringClass();
          Class localClass2 = ((AnnotatedMethod)localLinked.value).getDeclaringClass();
          if (localClass1 != localClass2) {
            if (localClass1.isAssignableFrom(localClass2)) {
              localObject = localLinked;
            }
          }
          for (;;)
          {
            localLinked = localLinked.next;
            break;
            if (!localClass2.isAssignableFrom(localClass1))
            {
              int i = _getterPriority((AnnotatedMethod)localLinked.value);
              int j = _getterPriority((AnnotatedMethod)((Linked)localObject).value);
              if (i == j) {
                break label143;
              }
              if (i < j) {
                localObject = localLinked;
              }
            }
          }
          label143:
          throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod)((Linked)localObject).value).getFullName() + " vs " + ((AnnotatedMethod)localLinked.value).getFullName());
        }
        this._getters = ((Linked)localObject).withoutNext();
        localAnnotatedMethod = (AnnotatedMethod)((Linked)localObject).value;
      }
    }
  }
  
  public String getInternalName()
  {
    return this._internalName.getSimpleName();
  }
  
  public PropertyMetadata getMetadata()
  {
    Boolean localBoolean = _findRequired();
    String str1 = _findDescription();
    Integer localInteger = _findIndex();
    String str2 = _findDefaultValue();
    PropertyMetadata localPropertyMetadata;
    if ((localBoolean == null) && (localInteger == null) && (str2 == null)) {
      if (str1 == null) {
        localPropertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      }
    }
    for (;;)
    {
      return localPropertyMetadata;
      localPropertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL.withDescription(str1);
      continue;
      localPropertyMetadata = PropertyMetadata.construct(localBoolean.booleanValue(), str1, localInteger, str2);
    }
  }
  
  public AnnotatedMember getMutator()
  {
    Object localObject = getConstructorParameter();
    if (localObject == null)
    {
      localObject = getSetter();
      if (localObject == null) {
        localObject = getField();
      }
    }
    return (AnnotatedMember)localObject;
  }
  
  public String getName()
  {
    if (this._name == null) {}
    for (String str = null;; str = this._name.getSimpleName()) {
      return str;
    }
  }
  
  public AnnotatedMember getNonConstructorMutator()
  {
    Object localObject = getSetter();
    if (localObject == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedMember getPrimaryMember()
  {
    if (this._forSerialization) {}
    for (AnnotatedMember localAnnotatedMember = getAccessor();; localAnnotatedMember = getMutator()) {
      return localAnnotatedMember;
    }
  }
  
  public AnnotatedMethod getSetter()
  {
    Object localObject = this._setters;
    AnnotatedMethod localAnnotatedMethod;
    if (localObject == null) {
      localAnnotatedMethod = null;
    }
    for (;;)
    {
      return localAnnotatedMethod;
      Linked localLinked = ((Linked)localObject).next;
      if (localLinked == null)
      {
        localAnnotatedMethod = (AnnotatedMethod)((Linked)localObject).value;
      }
      else
      {
        if (localLinked != null)
        {
          Class localClass1 = ((AnnotatedMethod)((Linked)localObject).value).getDeclaringClass();
          Class localClass2 = ((AnnotatedMethod)localLinked.value).getDeclaringClass();
          if (localClass1 != localClass2) {
            if (localClass1.isAssignableFrom(localClass2)) {
              localObject = localLinked;
            }
          }
          for (;;)
          {
            localLinked = localLinked.next;
            break;
            if (!localClass2.isAssignableFrom(localClass1))
            {
              int i = _setterPriority((AnnotatedMethod)localLinked.value);
              int j = _setterPriority((AnnotatedMethod)((Linked)localObject).value);
              if (i == j) {
                break label143;
              }
              if (i < j) {
                localObject = localLinked;
              }
            }
          }
          label143:
          throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod)((Linked)localObject).value).getFullName() + " vs " + ((AnnotatedMethod)localLinked.value).getFullName());
        }
        this._setters = ((Linked)localObject).withoutNext();
        localAnnotatedMethod = (AnnotatedMethod)((Linked)localObject).value;
      }
    }
  }
  
  public PropertyName getWrapperName()
  {
    AnnotatedMember localAnnotatedMember = getPrimaryMember();
    if ((localAnnotatedMember == null) || (this._annotationIntrospector == null)) {}
    for (PropertyName localPropertyName = null;; localPropertyName = this._annotationIntrospector.findWrapperName(localAnnotatedMember)) {
      return localPropertyName;
    }
  }
  
  public boolean hasConstructorParameter()
  {
    if (this._ctorParameters != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasField()
  {
    if (this._fields != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasGetter()
  {
    if (this._getters != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasName(PropertyName paramPropertyName)
  {
    return this._name.equals(paramPropertyName);
  }
  
  public boolean hasSetter()
  {
    if (this._setters != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isExplicitlyIncluded()
  {
    if ((_anyExplicits(this._fields)) || (_anyExplicits(this._getters)) || (_anyExplicits(this._setters)) || (_anyExplicits(this._ctorParameters))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isExplicitlyNamed()
  {
    if ((_anyExplicitNames(this._fields)) || (_anyExplicitNames(this._getters)) || (_anyExplicitNames(this._setters)) || (_anyExplicitNames(this._ctorParameters))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isTypeId()
  {
    Boolean localBoolean = (Boolean)fromMemberAnnotations(new WithMember()
    {
      public Boolean withMember(AnnotatedMember paramAnonymousAnnotatedMember)
      {
        return POJOPropertyBuilder.this._annotationIntrospector.isTypeId(paramAnonymousAnnotatedMember);
      }
    });
    if ((localBoolean != null) && (localBoolean.booleanValue())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void mergeAnnotations(boolean paramBoolean)
  {
    if (paramBoolean) {
      if (this._getters != null)
      {
        Linked[] arrayOfLinked5 = new Linked[4];
        arrayOfLinked5[0] = this._getters;
        arrayOfLinked5[1] = this._fields;
        arrayOfLinked5[2] = this._ctorParameters;
        arrayOfLinked5[3] = this._setters;
        AnnotationMap localAnnotationMap5 = _mergeAnnotations(0, arrayOfLinked5);
        this._getters = _applyAnnotations(this._getters, localAnnotationMap5);
      }
    }
    for (;;)
    {
      return;
      if (this._fields != null)
      {
        Linked[] arrayOfLinked4 = new Linked[3];
        arrayOfLinked4[0] = this._fields;
        arrayOfLinked4[1] = this._ctorParameters;
        arrayOfLinked4[2] = this._setters;
        AnnotationMap localAnnotationMap4 = _mergeAnnotations(0, arrayOfLinked4);
        this._fields = _applyAnnotations(this._fields, localAnnotationMap4);
        continue;
        if (this._ctorParameters != null)
        {
          Linked[] arrayOfLinked3 = new Linked[4];
          arrayOfLinked3[0] = this._ctorParameters;
          arrayOfLinked3[1] = this._setters;
          arrayOfLinked3[2] = this._fields;
          arrayOfLinked3[3] = this._getters;
          AnnotationMap localAnnotationMap3 = _mergeAnnotations(0, arrayOfLinked3);
          this._ctorParameters = _applyAnnotations(this._ctorParameters, localAnnotationMap3);
        }
        else if (this._setters != null)
        {
          Linked[] arrayOfLinked2 = new Linked[3];
          arrayOfLinked2[0] = this._setters;
          arrayOfLinked2[1] = this._fields;
          arrayOfLinked2[2] = this._getters;
          AnnotationMap localAnnotationMap2 = _mergeAnnotations(0, arrayOfLinked2);
          this._setters = _applyAnnotations(this._setters, localAnnotationMap2);
        }
        else if (this._fields != null)
        {
          Linked[] arrayOfLinked1 = new Linked[2];
          arrayOfLinked1[0] = this._fields;
          arrayOfLinked1[1] = this._getters;
          AnnotationMap localAnnotationMap1 = _mergeAnnotations(0, arrayOfLinked1);
          this._fields = _applyAnnotations(this._fields, localAnnotationMap1);
        }
      }
    }
  }
  
  public void removeConstructors()
  {
    this._ctorParameters = null;
  }
  
  public void removeIgnored()
  {
    this._fields = _removeIgnored(this._fields);
    this._getters = _removeIgnored(this._getters);
    this._setters = _removeIgnored(this._setters);
    this._ctorParameters = _removeIgnored(this._ctorParameters);
  }
  
  public void removeNonVisible(boolean paramBoolean)
  {
    JsonProperty.Access localAccess = findAccess();
    if (localAccess == null) {
      localAccess = JsonProperty.Access.AUTO;
    }
    switch (localAccess)
    {
    default: 
      this._getters = _removeNonVisible(this._getters);
      this._ctorParameters = _removeNonVisible(this._ctorParameters);
      if ((!paramBoolean) || (this._getters == null))
      {
        this._fields = _removeNonVisible(this._fields);
        this._setters = _removeNonVisible(this._setters);
      }
      break;
    }
    for (;;)
    {
      return;
      this._setters = null;
      this._ctorParameters = null;
      if (!this._forSerialization)
      {
        this._fields = null;
        continue;
        this._getters = null;
        if (this._forSerialization) {
          this._fields = null;
        }
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[Property '").append(this._name).append("'; ctors: ").append(this._ctorParameters).append(", field(s): ").append(this._fields).append(", getter(s): ").append(this._getters).append(", setter(s): ").append(this._setters);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void trimByVisibility()
  {
    this._fields = _trimByVisibility(this._fields);
    this._getters = _trimByVisibility(this._getters);
    this._setters = _trimByVisibility(this._setters);
    this._ctorParameters = _trimByVisibility(this._ctorParameters);
  }
  
  public POJOPropertyBuilder withName(PropertyName paramPropertyName)
  {
    return new POJOPropertyBuilder(this, paramPropertyName);
  }
  
  public POJOPropertyBuilder withSimpleName(String paramString)
  {
    PropertyName localPropertyName = this._name.withSimpleName(paramString);
    if (localPropertyName == this._name) {}
    for (;;)
    {
      return this;
      this = new POJOPropertyBuilder(this, localPropertyName);
    }
  }
  
  protected static final class Linked<T>
  {
    public final boolean isMarkedIgnored;
    public final boolean isNameExplicit;
    public final boolean isVisible;
    public final PropertyName name;
    public final Linked<T> next;
    public final T value;
    
    public Linked(T paramT, Linked<T> paramLinked, PropertyName paramPropertyName, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      this.value = paramT;
      this.next = paramLinked;
      if ((paramPropertyName == null) || (paramPropertyName.isEmpty())) {}
      for (PropertyName localPropertyName = null;; localPropertyName = paramPropertyName)
      {
        this.name = localPropertyName;
        if (!paramBoolean1) {
          break label72;
        }
        if (this.name != null) {
          break;
        }
        throw new IllegalArgumentException("Can not pass true for 'explName' if name is null/empty");
      }
      if (!paramPropertyName.hasSimpleName()) {
        paramBoolean1 = false;
      }
      label72:
      this.isNameExplicit = paramBoolean1;
      this.isVisible = paramBoolean2;
      this.isMarkedIgnored = paramBoolean3;
    }
    
    protected Linked<T> append(Linked<T> paramLinked)
    {
      if (this.next == null) {}
      for (Linked localLinked = withNext(paramLinked);; localLinked = withNext(this.next.append(paramLinked))) {
        return localLinked;
      }
    }
    
    public String toString()
    {
      String str = this.value.toString() + "[visible=" + this.isVisible + ",ignore=" + this.isMarkedIgnored + ",explicitName=" + this.isNameExplicit + "]";
      if (this.next != null) {
        str = str + ", " + this.next.toString();
      }
      return str;
    }
    
    public Linked<T> trimByVisibility()
    {
      Linked localLinked;
      if (this.next == null) {
        localLinked = this;
      }
      for (;;)
      {
        return localLinked;
        localLinked = this.next.trimByVisibility();
        if (this.name != null)
        {
          if (localLinked.name == null) {
            localLinked = withNext(null);
          } else {
            localLinked = withNext(localLinked);
          }
        }
        else if (localLinked.name == null) {
          if (this.isVisible == localLinked.isVisible) {
            localLinked = withNext(localLinked);
          } else if (this.isVisible) {
            localLinked = withNext(null);
          }
        }
      }
    }
    
    public Linked<T> withNext(Linked<T> paramLinked)
    {
      if (paramLinked == this.next) {}
      for (;;)
      {
        return this;
        this = new Linked(this.value, paramLinked, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
      }
    }
    
    public Linked<T> withValue(T paramT)
    {
      if (paramT == this.value) {}
      for (;;)
      {
        return this;
        this = new Linked(paramT, this.next, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
      }
    }
    
    public Linked<T> withoutIgnored()
    {
      Object localObject;
      if (this.isMarkedIgnored) {
        if (this.next == null) {
          localObject = null;
        }
      }
      for (;;)
      {
        return (Linked<T>)localObject;
        localObject = this.next.withoutIgnored();
        continue;
        if (this.next != null)
        {
          Linked localLinked = this.next.withoutIgnored();
          if (localLinked != this.next)
          {
            localObject = withNext(localLinked);
            continue;
          }
        }
        localObject = this;
      }
    }
    
    public Linked<T> withoutNext()
    {
      if (this.next == null) {}
      for (;;)
      {
        return this;
        this = new Linked(this.value, null, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
      }
    }
    
    public Linked<T> withoutNonVisible()
    {
      if (this.next == null) {}
      for (Linked localLinked = null;; localLinked = this.next.withoutNonVisible())
      {
        if (this.isVisible) {
          localLinked = withNext(localLinked);
        }
        return localLinked;
      }
    }
  }
  
  protected static class MemberIterator<T extends AnnotatedMember>
    implements Iterator<T>
  {
    private POJOPropertyBuilder.Linked<T> next;
    
    public MemberIterator(POJOPropertyBuilder.Linked<T> paramLinked)
    {
      this.next = paramLinked;
    }
    
    public boolean hasNext()
    {
      if (this.next != null) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public T next()
    {
      if (this.next == null) {
        throw new NoSuchElementException();
      }
      AnnotatedMember localAnnotatedMember = (AnnotatedMember)this.next.value;
      this.next = this.next.next;
      return localAnnotatedMember;
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private static abstract interface WithMember<T>
  {
    public abstract T withMember(AnnotatedMember paramAnnotatedMember);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/POJOPropertyBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */