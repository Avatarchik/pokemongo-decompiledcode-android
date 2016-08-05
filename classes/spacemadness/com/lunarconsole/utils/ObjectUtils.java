package spacemadness.com.lunarconsole.utils;

public class ObjectUtils
{
  public static boolean areEqual(Object paramObject1, Object paramObject2)
  {
    if (((paramObject1 != null) && (paramObject1.equals(paramObject2))) || ((paramObject1 == null) && (paramObject2 == null))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static <T> T as(Object paramObject, Class<? extends T> paramClass)
  {
    if (paramClass.isInstance(paramObject)) {}
    for (;;)
    {
      return (T)paramObject;
      paramObject = null;
    }
  }
  
  public static <T> T notNullOrDefault(T paramT1, T paramT2)
  {
    if (paramT2 == null) {
      throw new NullPointerException("Default object is null");
    }
    if (paramT1 != null) {}
    for (;;)
    {
      return paramT1;
      paramT1 = paramT2;
    }
  }
  
  public static String toString(Object paramObject)
  {
    if (paramObject != null) {}
    for (String str = paramObject.toString();; str = null) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/utils/ObjectUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */