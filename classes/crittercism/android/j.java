package crittercism.android;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public final class j
{
  public static Object a(Field paramField, Object paramObject)
  {
    Object localObject1 = null;
    if (paramField == null) {}
    for (;;)
    {
      return localObject1;
      if (paramField == null) {
        continue;
      }
      paramField.setAccessible(true);
      try
      {
        Object localObject2 = paramField.get(paramObject);
        localObject1 = localObject2;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        throw new cl("Unable to get value of field", localThrowable);
      }
    }
  }
  
  public static Field a(Class paramClass1, Class paramClass2)
  {
    Field[] arrayOfField = paramClass1.getDeclaredFields();
    Field localField = null;
    for (int i = 0; i < arrayOfField.length; i++) {
      if (paramClass2.isAssignableFrom(arrayOfField[i].getType()))
      {
        if (localField != null) {
          throw new cl("Field is ambiguous: " + localField.getName() + ", " + arrayOfField[i].getName());
        }
        localField = arrayOfField[i];
      }
    }
    if (localField == null) {
      throw new cl("Could not find field matching type: " + paramClass2.getName());
    }
    localField.setAccessible(true);
    return localField;
  }
  
  public static void a(AccessibleObject[] paramArrayOfAccessibleObject)
  {
    for (int i = 0; i < paramArrayOfAccessibleObject.length; i++)
    {
      AccessibleObject localAccessibleObject = paramArrayOfAccessibleObject[i];
      if (localAccessibleObject != null) {
        localAccessibleObject.setAccessible(true);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */