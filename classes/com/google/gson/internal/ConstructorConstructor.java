package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class ConstructorConstructor
{
  private final Map<Type, InstanceCreator<?>> instanceCreators;
  
  public ConstructorConstructor(Map<Type, InstanceCreator<?>> paramMap)
  {
    this.instanceCreators = paramMap;
  }
  
  private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> paramClass)
  {
    try
    {
      final Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor.isAccessible()) {
        localConstructor.setAccessible(true);
      }
      local3 = new ObjectConstructor()
      {
        public T construct()
        {
          try
          {
            Object localObject = localConstructor.newInstance(null);
            return (T)localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
      return local3;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        ObjectConstructor local3 = null;
      }
    }
  }
  
  private <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Type paramType, Class<? super T> paramClass)
  {
    Object localObject;
    if (Collection.class.isAssignableFrom(paramClass)) {
      if (SortedSet.class.isAssignableFrom(paramClass)) {
        localObject = new ObjectConstructor()
        {
          public T construct()
          {
            return new TreeSet();
          }
        };
      }
    }
    for (;;)
    {
      return (ObjectConstructor<T>)localObject;
      if (EnumSet.class.isAssignableFrom(paramClass))
      {
        localObject = new ObjectConstructor()
        {
          public T construct()
          {
            if ((paramType instanceof ParameterizedType))
            {
              Type localType = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localType instanceof Class)) {
                return EnumSet.noneOf((Class)localType);
              }
              throw new JsonIOException("Invalid EnumSet type: " + paramType.toString());
            }
            throw new JsonIOException("Invalid EnumSet type: " + paramType.toString());
          }
        };
      }
      else if (Set.class.isAssignableFrom(paramClass))
      {
        localObject = new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedHashSet();
          }
        };
      }
      else if (Queue.class.isAssignableFrom(paramClass))
      {
        localObject = new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedList();
          }
        };
      }
      else
      {
        localObject = new ObjectConstructor()
        {
          public T construct()
          {
            return new ArrayList();
          }
        };
        continue;
        if (Map.class.isAssignableFrom(paramClass))
        {
          if (SortedMap.class.isAssignableFrom(paramClass)) {
            localObject = new ObjectConstructor()
            {
              public T construct()
              {
                return new TreeMap();
              }
            };
          } else if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)paramType).getActualTypeArguments()[0]).getRawType()))) {
            localObject = new ObjectConstructor()
            {
              public T construct()
              {
                return new LinkedHashMap();
              }
            };
          } else {
            localObject = new ObjectConstructor()
            {
              public T construct()
              {
                return new LinkedTreeMap();
              }
            };
          }
        }
        else {
          localObject = null;
        }
      }
    }
  }
  
  private <T> ObjectConstructor<T> newUnsafeAllocator(final Type paramType, final Class<? super T> paramClass)
  {
    new ObjectConstructor()
    {
      private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
      
      public T construct()
      {
        try
        {
          Object localObject = this.unsafeAllocator.newInstance(paramClass);
          return (T)localObject;
        }
        catch (Exception localException)
        {
          throw new RuntimeException("Unable to invoke no-args constructor for " + paramType + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }
  
  public <T> ObjectConstructor<T> get(TypeToken<T> paramTypeToken)
  {
    final Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    final InstanceCreator localInstanceCreator1 = (InstanceCreator)this.instanceCreators.get(localType);
    Object localObject;
    if (localInstanceCreator1 != null) {
      localObject = new ObjectConstructor()
      {
        public T construct()
        {
          return (T)localInstanceCreator1.createInstance(localType);
        }
      };
    }
    for (;;)
    {
      return (ObjectConstructor<T>)localObject;
      final InstanceCreator localInstanceCreator2 = (InstanceCreator)this.instanceCreators.get(localClass);
      if (localInstanceCreator2 != null)
      {
        localObject = new ObjectConstructor()
        {
          public T construct()
          {
            return (T)localInstanceCreator2.createInstance(localType);
          }
        };
      }
      else
      {
        localObject = newDefaultConstructor(localClass);
        if (localObject == null)
        {
          ObjectConstructor localObjectConstructor = newDefaultImplementationConstructor(localType, localClass);
          if (localObjectConstructor != null) {
            localObject = localObjectConstructor;
          } else {
            localObject = newUnsafeAllocator(localType, localClass);
          }
        }
      }
    }
  }
  
  public String toString()
  {
    return this.instanceCreators.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/ConstructorConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */