package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass
  extends Annotated
{
  private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
  protected final AnnotationIntrospector _annotationIntrospector;
  protected final Class<?> _class;
  protected AnnotationMap _classAnnotations;
  protected List<AnnotatedConstructor> _constructors;
  protected List<AnnotatedMethod> _creatorMethods;
  protected boolean _creatorsResolved = false;
  protected AnnotatedConstructor _defaultConstructor;
  protected List<AnnotatedField> _fields;
  protected AnnotatedMethodMap _memberMethods;
  protected final ClassIntrospector.MixInResolver _mixInResolver;
  protected final Class<?> _primaryMixIn;
  protected final List<Class<?>> _superTypes;
  
  private AnnotatedClass(Class<?> paramClass, List<Class<?>> paramList, AnnotationIntrospector paramAnnotationIntrospector, ClassIntrospector.MixInResolver paramMixInResolver, AnnotationMap paramAnnotationMap)
  {
    this._class = paramClass;
    this._superTypes = paramList;
    this._annotationIntrospector = paramAnnotationIntrospector;
    this._mixInResolver = paramMixInResolver;
    if (this._mixInResolver == null) {}
    for (Class localClass = null;; localClass = this._mixInResolver.findMixInClassFor(this._class))
    {
      this._primaryMixIn = localClass;
      this._classAnnotations = paramAnnotationMap;
      return;
    }
  }
  
  private AnnotationMap _addAnnotationsIfNotPresent(AnnotationMap paramAnnotationMap, Annotation[] paramArrayOfAnnotation)
  {
    if (paramArrayOfAnnotation != null)
    {
      List localList = null;
      int i = paramArrayOfAnnotation.length;
      for (int j = 0; j < i; j++)
      {
        Annotation localAnnotation = paramArrayOfAnnotation[j];
        if ((paramAnnotationMap.addIfNotPresent(localAnnotation)) && (_isAnnotationBundle(localAnnotation))) {
          localList = _addFromBundle(localAnnotation, localList);
        }
      }
      if (localList != null) {
        _addAnnotationsIfNotPresent(paramAnnotationMap, (Annotation[])localList.toArray(new Annotation[localList.size()]));
      }
    }
    return paramAnnotationMap;
  }
  
  private void _addAnnotationsIfNotPresent(AnnotatedMember paramAnnotatedMember, Annotation[] paramArrayOfAnnotation)
  {
    if (paramArrayOfAnnotation != null)
    {
      List localList = null;
      int i = paramArrayOfAnnotation.length;
      for (int j = 0; j < i; j++)
      {
        Annotation localAnnotation = paramArrayOfAnnotation[j];
        if ((paramAnnotatedMember.addIfNotPresent(localAnnotation)) && (_isAnnotationBundle(localAnnotation))) {
          localList = _addFromBundle(localAnnotation, localList);
        }
      }
      if (localList != null) {
        _addAnnotationsIfNotPresent(paramAnnotatedMember, (Annotation[])localList.toArray(new Annotation[localList.size()]));
      }
    }
  }
  
  private List<Annotation> _addFromBundle(Annotation paramAnnotation, List<Annotation> paramList)
  {
    Annotation[] arrayOfAnnotation = paramAnnotation.annotationType().getDeclaredAnnotations();
    int i = arrayOfAnnotation.length;
    int j = 0;
    if (j < i)
    {
      Annotation localAnnotation = arrayOfAnnotation[j];
      if (((localAnnotation instanceof Target)) || ((localAnnotation instanceof Retention))) {}
      for (;;)
      {
        j++;
        break;
        if (paramList == null) {
          paramList = new ArrayList();
        }
        paramList.add(localAnnotation);
      }
    }
    return paramList;
  }
  
  private void _addOrOverrideAnnotations(AnnotatedMember paramAnnotatedMember, Annotation[] paramArrayOfAnnotation)
  {
    if (paramArrayOfAnnotation != null)
    {
      List localList = null;
      int i = paramArrayOfAnnotation.length;
      for (int j = 0; j < i; j++)
      {
        Annotation localAnnotation = paramArrayOfAnnotation[j];
        if ((paramAnnotatedMember.addOrOverride(localAnnotation)) && (_isAnnotationBundle(localAnnotation))) {
          localList = _addFromBundle(localAnnotation, localList);
        }
      }
      if (localList != null) {
        _addOrOverrideAnnotations(paramAnnotatedMember, (Annotation[])localList.toArray(new Annotation[localList.size()]));
      }
    }
  }
  
  private AnnotationMap _emptyAnnotationMap()
  {
    return new AnnotationMap();
  }
  
  private AnnotationMap[] _emptyAnnotationMaps(int paramInt)
  {
    AnnotationMap[] arrayOfAnnotationMap;
    if (paramInt == 0) {
      arrayOfAnnotationMap = NO_ANNOTATION_MAPS;
    }
    for (;;)
    {
      return arrayOfAnnotationMap;
      arrayOfAnnotationMap = new AnnotationMap[paramInt];
      for (int i = 0; i < paramInt; i++) {
        arrayOfAnnotationMap[i] = _emptyAnnotationMap();
      }
    }
  }
  
  private final boolean _isAnnotationBundle(Annotation paramAnnotation)
  {
    if ((this._annotationIntrospector != null) && (this._annotationIntrospector.isAnnotationBundle(paramAnnotation))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean _isIncludableField(Field paramField)
  {
    boolean bool = false;
    if (paramField.isSynthetic()) {}
    for (;;)
    {
      return bool;
      if (!Modifier.isStatic(paramField.getModifiers())) {
        bool = true;
      }
    }
  }
  
  public static AnnotatedClass construct(Class<?> paramClass, AnnotationIntrospector paramAnnotationIntrospector, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    return new AnnotatedClass(paramClass, ClassUtil.findSuperTypes(paramClass, null), paramAnnotationIntrospector, paramMixInResolver, null);
  }
  
  public static AnnotatedClass constructWithoutSuperTypes(Class<?> paramClass, AnnotationIntrospector paramAnnotationIntrospector, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    return new AnnotatedClass(paramClass, Collections.emptyList(), paramAnnotationIntrospector, paramMixInResolver, null);
  }
  
  private void resolveClassAnnotations()
  {
    this._classAnnotations = new AnnotationMap();
    if (this._annotationIntrospector != null)
    {
      if (this._primaryMixIn != null) {
        _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn);
      }
      _addAnnotationsIfNotPresent(this._classAnnotations, this._class.getDeclaredAnnotations());
      Iterator localIterator = this._superTypes.iterator();
      while (localIterator.hasNext())
      {
        Class localClass = (Class)localIterator.next();
        _addClassMixIns(this._classAnnotations, localClass);
        _addAnnotationsIfNotPresent(this._classAnnotations, localClass.getDeclaredAnnotations());
      }
      _addClassMixIns(this._classAnnotations, Object.class);
    }
  }
  
  private void resolveCreators()
  {
    ArrayList localArrayList1 = null;
    Constructor[] arrayOfConstructor = this._class.getDeclaredConstructors();
    int i = arrayOfConstructor.length;
    int j = 0;
    if (j < i)
    {
      Constructor localConstructor = arrayOfConstructor[j];
      if (localConstructor.getParameterTypes().length == 0) {
        this._defaultConstructor = _constructConstructor(localConstructor, true);
      }
      for (;;)
      {
        j++;
        break;
        if (localArrayList1 == null) {
          localArrayList1 = new ArrayList(Math.max(10, arrayOfConstructor.length));
        }
        localArrayList1.add(_constructConstructor(localConstructor, false));
      }
    }
    if (localArrayList1 == null) {}
    for (this._constructors = Collections.emptyList();; this._constructors = localArrayList1)
    {
      if ((this._primaryMixIn != null) && ((this._defaultConstructor != null) || (!this._constructors.isEmpty()))) {
        _addConstructorMixIns(this._primaryMixIn);
      }
      if (this._annotationIntrospector == null) {
        break;
      }
      if ((this._defaultConstructor != null) && (this._annotationIntrospector.hasIgnoreMarker(this._defaultConstructor))) {
        this._defaultConstructor = null;
      }
      if (this._constructors == null) {
        break;
      }
      int i1 = this._constructors.size();
      for (;;)
      {
        i1--;
        if (i1 < 0) {
          break;
        }
        if (this._annotationIntrospector.hasIgnoreMarker((AnnotatedMember)this._constructors.get(i1))) {
          this._constructors.remove(i1);
        }
      }
    }
    ArrayList localArrayList2 = null;
    Method[] arrayOfMethod = _findClassMethods(this._class);
    int k = arrayOfMethod.length;
    int m = 0;
    if (m < k)
    {
      Method localMethod = arrayOfMethod[m];
      if (!Modifier.isStatic(localMethod.getModifiers())) {}
      for (;;)
      {
        m++;
        break;
        if (localArrayList2 == null) {
          localArrayList2 = new ArrayList(8);
        }
        localArrayList2.add(_constructCreatorMethod(localMethod));
      }
    }
    if (localArrayList2 == null) {
      this._creatorMethods = Collections.emptyList();
    }
    for (;;)
    {
      this._creatorsResolved = true;
      return;
      this._creatorMethods = localArrayList2;
      if (this._primaryMixIn != null) {
        _addFactoryMixIns(this._primaryMixIn);
      }
      if (this._annotationIntrospector != null)
      {
        int n = this._creatorMethods.size();
        for (;;)
        {
          n--;
          if (n < 0) {
            break;
          }
          if (this._annotationIntrospector.hasIgnoreMarker((AnnotatedMember)this._creatorMethods.get(n))) {
            this._creatorMethods.remove(n);
          }
        }
      }
    }
  }
  
  private void resolveFields()
  {
    Map localMap = _findFields(this._class, null);
    if ((localMap == null) || (localMap.size() == 0)) {
      this._fields = Collections.emptyList();
    }
    for (;;)
    {
      return;
      this._fields = new ArrayList(localMap.size());
      this._fields.addAll(localMap.values());
    }
  }
  
  private void resolveMemberMethods()
  {
    this._memberMethods = new AnnotatedMethodMap();
    AnnotatedMethodMap localAnnotatedMethodMap = new AnnotatedMethodMap();
    _addMemberMethods(this._class, this._memberMethods, this._primaryMixIn, localAnnotatedMethodMap);
    Iterator localIterator1 = this._superTypes.iterator();
    if (localIterator1.hasNext())
    {
      Class localClass2 = (Class)localIterator1.next();
      if (this._mixInResolver == null) {}
      for (Class localClass3 = null;; localClass3 = this._mixInResolver.findMixInClassFor(localClass2))
      {
        _addMemberMethods(localClass2, this._memberMethods, localClass3, localAnnotatedMethodMap);
        break;
      }
    }
    if (this._mixInResolver != null)
    {
      Class localClass1 = this._mixInResolver.findMixInClassFor(Object.class);
      if (localClass1 != null) {
        _addMethodMixIns(this._class, this._memberMethods, localClass1, localAnnotatedMethodMap);
      }
    }
    if ((this._annotationIntrospector != null) && (!localAnnotatedMethodMap.isEmpty()))
    {
      Iterator localIterator2 = localAnnotatedMethodMap.iterator();
      while (localIterator2.hasNext())
      {
        AnnotatedMethod localAnnotatedMethod1 = (AnnotatedMethod)localIterator2.next();
        try
        {
          Method localMethod = Object.class.getDeclaredMethod(localAnnotatedMethod1.getName(), localAnnotatedMethod1.getRawParameterTypes());
          if (localMethod != null)
          {
            AnnotatedMethod localAnnotatedMethod2 = _constructMethod(localMethod);
            _addMixOvers(localAnnotatedMethod1.getAnnotated(), localAnnotatedMethod2, false);
            this._memberMethods.add(localAnnotatedMethod2);
          }
        }
        catch (Exception localException) {}
      }
    }
  }
  
  protected void _addClassMixIns(AnnotationMap paramAnnotationMap, Class<?> paramClass)
  {
    if (this._mixInResolver != null) {
      _addClassMixIns(paramAnnotationMap, paramClass, this._mixInResolver.findMixInClassFor(paramClass));
    }
  }
  
  protected void _addClassMixIns(AnnotationMap paramAnnotationMap, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == null) {}
    for (;;)
    {
      return;
      _addAnnotationsIfNotPresent(paramAnnotationMap, paramClass2.getDeclaredAnnotations());
      Iterator localIterator = ClassUtil.findSuperTypes(paramClass2, paramClass1).iterator();
      while (localIterator.hasNext()) {
        _addAnnotationsIfNotPresent(paramAnnotationMap, ((Class)localIterator.next()).getDeclaredAnnotations());
      }
    }
  }
  
  protected void _addConstructorMixIns(Class<?> paramClass)
  {
    MemberKey[] arrayOfMemberKey = null;
    int i;
    int k;
    label25:
    Constructor localConstructor;
    if (this._constructors == null)
    {
      i = 0;
      Constructor[] arrayOfConstructor = paramClass.getDeclaredConstructors();
      int j = arrayOfConstructor.length;
      k = 0;
      if (k >= j) {
        return;
      }
      localConstructor = arrayOfConstructor[k];
      if (localConstructor.getParameterTypes().length != 0) {
        break label85;
      }
      if (this._defaultConstructor != null) {
        _addMixOvers(localConstructor, this._defaultConstructor, false);
      }
    }
    for (;;)
    {
      k++;
      break label25;
      i = this._constructors.size();
      break;
      label85:
      if (arrayOfMemberKey == null)
      {
        arrayOfMemberKey = new MemberKey[i];
        for (int n = 0; n < i; n++) {
          arrayOfMemberKey[n] = new MemberKey(((AnnotatedConstructor)this._constructors.get(n)).getAnnotated());
        }
      }
      MemberKey localMemberKey = new MemberKey(localConstructor);
      for (int m = 0; m < i; m++) {
        if (localMemberKey.equals(arrayOfMemberKey[m])) {
          break label175;
        }
      }
      continue;
      label175:
      _addMixOvers(localConstructor, (AnnotatedConstructor)this._constructors.get(m), true);
    }
  }
  
  protected void _addFactoryMixIns(Class<?> paramClass)
  {
    MemberKey[] arrayOfMemberKey = null;
    int i = this._creatorMethods.size();
    Method[] arrayOfMethod = paramClass.getDeclaredMethods();
    int j = arrayOfMethod.length;
    int k = 0;
    if (k < j)
    {
      Method localMethod = arrayOfMethod[k];
      if (!Modifier.isStatic(localMethod.getModifiers())) {}
      for (;;)
      {
        k++;
        break;
        if (localMethod.getParameterTypes().length != 0)
        {
          if (arrayOfMemberKey == null)
          {
            arrayOfMemberKey = new MemberKey[i];
            for (int n = 0; n < i; n++) {
              arrayOfMemberKey[n] = new MemberKey(((AnnotatedMethod)this._creatorMethods.get(n)).getAnnotated());
            }
          }
          MemberKey localMemberKey = new MemberKey(localMethod);
          for (int m = 0; m < i; m++) {
            if (localMemberKey.equals(arrayOfMemberKey[m])) {
              break label156;
            }
          }
          continue;
          label156:
          _addMixOvers(localMethod, (AnnotatedMethod)this._creatorMethods.get(m), true);
        }
      }
    }
  }
  
  protected void _addFieldMixIns(Class<?> paramClass1, Class<?> paramClass2, Map<String, AnnotatedField> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramClass2);
    ClassUtil.findSuperTypes(paramClass2, paramClass1, localArrayList);
    Iterator localIterator = localArrayList.iterator();
    if (localIterator.hasNext())
    {
      Field[] arrayOfField = ((Class)localIterator.next()).getDeclaredFields();
      int i = arrayOfField.length;
      int j = 0;
      label68:
      Field localField;
      if (j < i)
      {
        localField = arrayOfField[j];
        if (_isIncludableField(localField)) {
          break label97;
        }
      }
      for (;;)
      {
        j++;
        break label68;
        break;
        label97:
        AnnotatedField localAnnotatedField = (AnnotatedField)paramMap.get(localField.getName());
        if (localAnnotatedField != null) {
          _addOrOverrideAnnotations(localAnnotatedField, localField.getDeclaredAnnotations());
        }
      }
    }
  }
  
  protected void _addMemberMethods(Class<?> paramClass1, AnnotatedMethodMap paramAnnotatedMethodMap1, Class<?> paramClass2, AnnotatedMethodMap paramAnnotatedMethodMap2)
  {
    if (paramClass2 != null) {
      _addMethodMixIns(paramClass1, paramAnnotatedMethodMap1, paramClass2, paramAnnotatedMethodMap2);
    }
    if (paramClass1 == null) {
      return;
    }
    Method[] arrayOfMethod = _findClassMethods(paramClass1);
    int i = arrayOfMethod.length;
    int j = 0;
    label33:
    Method localMethod;
    if (j < i)
    {
      localMethod = arrayOfMethod[j];
      if (_isIncludableMemberMethod(localMethod)) {
        break label62;
      }
    }
    for (;;)
    {
      j++;
      break label33;
      break;
      label62:
      AnnotatedMethod localAnnotatedMethod1 = paramAnnotatedMethodMap1.find(localMethod);
      if (localAnnotatedMethod1 == null)
      {
        AnnotatedMethod localAnnotatedMethod2 = _constructMethod(localMethod);
        paramAnnotatedMethodMap1.add(localAnnotatedMethod2);
        AnnotatedMethod localAnnotatedMethod3 = paramAnnotatedMethodMap2.remove(localMethod);
        if (localAnnotatedMethod3 != null) {
          _addMixOvers(localAnnotatedMethod3.getAnnotated(), localAnnotatedMethod2, false);
        }
      }
      else
      {
        _addMixUnders(localMethod, localAnnotatedMethod1);
        if ((localAnnotatedMethod1.getDeclaringClass().isInterface()) && (!localMethod.getDeclaringClass().isInterface())) {
          paramAnnotatedMethodMap1.add(localAnnotatedMethod1.withMethod(localMethod));
        }
      }
    }
  }
  
  protected void _addMethodMixIns(Class<?> paramClass1, AnnotatedMethodMap paramAnnotatedMethodMap1, Class<?> paramClass2, AnnotatedMethodMap paramAnnotatedMethodMap2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramClass2);
    ClassUtil.findSuperTypes(paramClass2, paramClass1, localArrayList);
    Iterator localIterator = localArrayList.iterator();
    if (localIterator.hasNext())
    {
      Method[] arrayOfMethod = ((Class)localIterator.next()).getDeclaredMethods();
      int i = arrayOfMethod.length;
      int j = 0;
      label68:
      Method localMethod;
      if (j < i)
      {
        localMethod = arrayOfMethod[j];
        if (_isIncludableMemberMethod(localMethod)) {
          break label97;
        }
      }
      for (;;)
      {
        j++;
        break label68;
        break;
        label97:
        AnnotatedMethod localAnnotatedMethod1 = paramAnnotatedMethodMap1.find(localMethod);
        if (localAnnotatedMethod1 != null)
        {
          _addMixUnders(localMethod, localAnnotatedMethod1);
        }
        else
        {
          AnnotatedMethod localAnnotatedMethod2 = paramAnnotatedMethodMap2.find(localMethod);
          if (localAnnotatedMethod2 != null) {
            _addMixUnders(localMethod, localAnnotatedMethod2);
          } else {
            paramAnnotatedMethodMap2.add(_constructMethod(localMethod));
          }
        }
      }
    }
  }
  
  protected void _addMixOvers(Constructor<?> paramConstructor, AnnotatedConstructor paramAnnotatedConstructor, boolean paramBoolean)
  {
    _addOrOverrideAnnotations(paramAnnotatedConstructor, paramConstructor.getDeclaredAnnotations());
    if (paramBoolean)
    {
      Annotation[][] arrayOfAnnotation = paramConstructor.getParameterAnnotations();
      int i = 0;
      int j = arrayOfAnnotation.length;
      while (i < j)
      {
        Annotation[] arrayOfAnnotation1 = arrayOfAnnotation[i];
        int k = arrayOfAnnotation1.length;
        for (int m = 0; m < k; m++) {
          paramAnnotatedConstructor.addOrOverrideParam(i, arrayOfAnnotation1[m]);
        }
        i++;
      }
    }
  }
  
  protected void _addMixOvers(Method paramMethod, AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
  {
    _addOrOverrideAnnotations(paramAnnotatedMethod, paramMethod.getDeclaredAnnotations());
    if (paramBoolean)
    {
      Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
      int i = 0;
      int j = arrayOfAnnotation.length;
      while (i < j)
      {
        Annotation[] arrayOfAnnotation1 = arrayOfAnnotation[i];
        int k = arrayOfAnnotation1.length;
        for (int m = 0; m < k; m++) {
          paramAnnotatedMethod.addOrOverrideParam(i, arrayOfAnnotation1[m]);
        }
        i++;
      }
    }
  }
  
  protected void _addMixUnders(Method paramMethod, AnnotatedMethod paramAnnotatedMethod)
  {
    _addAnnotationsIfNotPresent(paramAnnotatedMethod, paramMethod.getDeclaredAnnotations());
  }
  
  protected AnnotationMap _collectRelevantAnnotations(Annotation[] paramArrayOfAnnotation)
  {
    return _addAnnotationsIfNotPresent(new AnnotationMap(), paramArrayOfAnnotation);
  }
  
  protected AnnotationMap[] _collectRelevantAnnotations(Annotation[][] paramArrayOfAnnotation)
  {
    int i = paramArrayOfAnnotation.length;
    AnnotationMap[] arrayOfAnnotationMap = new AnnotationMap[i];
    for (int j = 0; j < i; j++) {
      arrayOfAnnotationMap[j] = _collectRelevantAnnotations(paramArrayOfAnnotation[j]);
    }
    return arrayOfAnnotationMap;
  }
  
  protected AnnotatedConstructor _constructConstructor(Constructor<?> paramConstructor, boolean paramBoolean)
  {
    AnnotatedConstructor localAnnotatedConstructor;
    if (this._annotationIntrospector == null) {
      localAnnotatedConstructor = new AnnotatedConstructor(this, paramConstructor, _emptyAnnotationMap(), _emptyAnnotationMaps(paramConstructor.getParameterTypes().length));
    }
    for (;;)
    {
      return localAnnotatedConstructor;
      if (paramBoolean)
      {
        localAnnotatedConstructor = new AnnotatedConstructor(this, paramConstructor, _collectRelevantAnnotations(paramConstructor.getDeclaredAnnotations()), null);
      }
      else
      {
        Annotation[][] arrayOfAnnotation1 = paramConstructor.getParameterAnnotations();
        int i = paramConstructor.getParameterTypes().length;
        AnnotationMap[] arrayOfAnnotationMap = null;
        if (i != arrayOfAnnotation1.length)
        {
          Class localClass = paramConstructor.getDeclaringClass();
          if ((localClass.isEnum()) && (i == 2 + arrayOfAnnotation1.length))
          {
            Annotation[][] arrayOfAnnotation3 = arrayOfAnnotation1;
            arrayOfAnnotation1 = new Annotation[2 + arrayOfAnnotation3.length][];
            System.arraycopy(arrayOfAnnotation3, 0, arrayOfAnnotation1, 2, arrayOfAnnotation3.length);
          }
          for (arrayOfAnnotationMap = _collectRelevantAnnotations(arrayOfAnnotation1); arrayOfAnnotationMap == null; arrayOfAnnotationMap = _collectRelevantAnnotations(arrayOfAnnotation1))
          {
            label140:
            throw new IllegalStateException("Internal error: constructor for " + paramConstructor.getDeclaringClass().getName() + " has mismatch: " + i + " parameters; " + arrayOfAnnotation1.length + " sets of annotations");
            if ((!localClass.isMemberClass()) || (i != 1 + arrayOfAnnotation1.length)) {
              break label140;
            }
            Annotation[][] arrayOfAnnotation2 = arrayOfAnnotation1;
            arrayOfAnnotation1 = new Annotation[1 + arrayOfAnnotation2.length][];
            System.arraycopy(arrayOfAnnotation2, 0, arrayOfAnnotation1, 1, arrayOfAnnotation2.length);
          }
        }
        arrayOfAnnotationMap = _collectRelevantAnnotations(arrayOfAnnotation1);
        localAnnotatedConstructor = new AnnotatedConstructor(this, paramConstructor, _collectRelevantAnnotations(paramConstructor.getDeclaredAnnotations()), arrayOfAnnotationMap);
      }
    }
  }
  
  protected AnnotatedMethod _constructCreatorMethod(Method paramMethod)
  {
    if (this._annotationIntrospector == null) {}
    for (AnnotatedMethod localAnnotatedMethod = new AnnotatedMethod(this, paramMethod, _emptyAnnotationMap(), _emptyAnnotationMaps(paramMethod.getParameterTypes().length));; localAnnotatedMethod = new AnnotatedMethod(this, paramMethod, _collectRelevantAnnotations(paramMethod.getDeclaredAnnotations()), _collectRelevantAnnotations(paramMethod.getParameterAnnotations()))) {
      return localAnnotatedMethod;
    }
  }
  
  protected AnnotatedField _constructField(Field paramField)
  {
    if (this._annotationIntrospector == null) {}
    for (AnnotatedField localAnnotatedField = new AnnotatedField(this, paramField, _emptyAnnotationMap());; localAnnotatedField = new AnnotatedField(this, paramField, _collectRelevantAnnotations(paramField.getDeclaredAnnotations()))) {
      return localAnnotatedField;
    }
  }
  
  protected AnnotatedMethod _constructMethod(Method paramMethod)
  {
    if (this._annotationIntrospector == null) {}
    for (AnnotatedMethod localAnnotatedMethod = new AnnotatedMethod(this, paramMethod, _emptyAnnotationMap(), null);; localAnnotatedMethod = new AnnotatedMethod(this, paramMethod, _collectRelevantAnnotations(paramMethod.getDeclaredAnnotations()), null)) {
      return localAnnotatedMethod;
    }
  }
  
  protected Method[] _findClassMethods(Class<?> paramClass)
  {
    try
    {
      Method[] arrayOfMethod2 = paramClass.getDeclaredMethods();
      arrayOfMethod1 = arrayOfMethod2;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      for (;;)
      {
        Method[] arrayOfMethod1;
        ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
        if (localClassLoader != null) {
          break label27;
        }
        throw localNoClassDefFoundError;
        try
        {
          label27:
          Class localClass = localClassLoader.loadClass(paramClass.getName());
          arrayOfMethod1 = localClass.getDeclaredMethods();
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          throw localNoClassDefFoundError;
        }
      }
    }
    return arrayOfMethod1;
  }
  
  protected Map<String, AnnotatedField> _findFields(Class<?> paramClass, Map<String, AnnotatedField> paramMap)
  {
    Class localClass1 = paramClass.getSuperclass();
    if (localClass1 != null)
    {
      paramMap = _findFields(localClass1, paramMap);
      Field[] arrayOfField = paramClass.getDeclaredFields();
      int i = arrayOfField.length;
      int j = 0;
      if (j < i)
      {
        Field localField = arrayOfField[j];
        if (!_isIncludableField(localField)) {}
        for (;;)
        {
          j++;
          break;
          if (paramMap == null) {
            paramMap = new LinkedHashMap();
          }
          paramMap.put(localField.getName(), _constructField(localField));
        }
      }
      if (this._mixInResolver != null)
      {
        Class localClass2 = this._mixInResolver.findMixInClassFor(paramClass);
        if (localClass2 != null) {
          _addFieldMixIns(localClass1, localClass2, paramMap);
        }
      }
    }
    return paramMap;
  }
  
  protected boolean _isIncludableMemberMethod(Method paramMethod)
  {
    boolean bool = false;
    if (Modifier.isStatic(paramMethod.getModifiers())) {}
    for (;;)
    {
      return bool;
      if ((!paramMethod.isSynthetic()) && (!paramMethod.isBridge()) && (paramMethod.getParameterTypes().length <= 2)) {
        bool = true;
      }
    }
  }
  
  public Iterable<Annotation> annotations()
  {
    if (this._classAnnotations == null) {
      resolveClassAnnotations();
    }
    return this._classAnnotations.annotations();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject == null) || (paramObject.getClass() != getClass())) {
        bool = false;
      } else if (((AnnotatedClass)paramObject)._class != this._class) {
        bool = false;
      }
    }
  }
  
  public Iterable<AnnotatedField> fields()
  {
    if (this._fields == null) {
      resolveFields();
    }
    return this._fields;
  }
  
  public AnnotatedMethod findMethod(String paramString, Class<?>[] paramArrayOfClass)
  {
    if (this._memberMethods == null) {
      resolveMemberMethods();
    }
    return this._memberMethods.find(paramString, paramArrayOfClass);
  }
  
  protected AnnotationMap getAllAnnotations()
  {
    if (this._classAnnotations == null) {
      resolveClassAnnotations();
    }
    return this._classAnnotations;
  }
  
  public Class<?> getAnnotated()
  {
    return this._class;
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    if (this._classAnnotations == null) {
      resolveClassAnnotations();
    }
    return this._classAnnotations.get(paramClass);
  }
  
  public Annotations getAnnotations()
  {
    if (this._classAnnotations == null) {
      resolveClassAnnotations();
    }
    return this._classAnnotations;
  }
  
  public List<AnnotatedConstructor> getConstructors()
  {
    if (!this._creatorsResolved) {
      resolveCreators();
    }
    return this._constructors;
  }
  
  public AnnotatedConstructor getDefaultConstructor()
  {
    if (!this._creatorsResolved) {
      resolveCreators();
    }
    return this._defaultConstructor;
  }
  
  public int getFieldCount()
  {
    if (this._fields == null) {
      resolveFields();
    }
    return this._fields.size();
  }
  
  public Type getGenericType()
  {
    return this._class;
  }
  
  public int getMemberMethodCount()
  {
    if (this._memberMethods == null) {
      resolveMemberMethods();
    }
    return this._memberMethods.size();
  }
  
  public int getModifiers()
  {
    return this._class.getModifiers();
  }
  
  public String getName()
  {
    return this._class.getName();
  }
  
  public Class<?> getRawType()
  {
    return this._class;
  }
  
  public List<AnnotatedMethod> getStaticMethods()
  {
    if (!this._creatorsResolved) {
      resolveCreators();
    }
    return this._creatorMethods;
  }
  
  public boolean hasAnnotations()
  {
    if (this._classAnnotations == null) {
      resolveClassAnnotations();
    }
    if (this._classAnnotations.size() > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return this._class.getName().hashCode();
  }
  
  public Iterable<AnnotatedMethod> memberMethods()
  {
    if (this._memberMethods == null) {
      resolveMemberMethods();
    }
    return this._memberMethods;
  }
  
  public String toString()
  {
    return "[AnnotedClass " + this._class.getName() + "]";
  }
  
  public AnnotatedClass withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return new AnnotatedClass(this._class, this._superTypes, this._annotationIntrospector, this._mixInResolver, paramAnnotationMap);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */