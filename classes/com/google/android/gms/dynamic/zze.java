package com.google.android.gms.dynamic;

import android.os.IBinder;
import java.lang.reflect.Field;

public final class zze<T>
  extends zzd.zza
{
  private final T mWrappedObject;
  
  private zze(T paramT)
  {
    this.mWrappedObject = paramT;
  }
  
  public static <T> T zzp(zzd paramzzd)
  {
    Object localObject2;
    if ((paramzzd instanceof zze)) {
      localObject2 = ((zze)paramzzd).mWrappedObject;
    }
    for (;;)
    {
      return (T)localObject2;
      IBinder localIBinder = paramzzd.asBinder();
      Field[] arrayOfField = localIBinder.getClass().getDeclaredFields();
      if (arrayOfField.length != 1) {
        break label122;
      }
      Field localField = arrayOfField[0];
      if (!localField.isAccessible())
      {
        localField.setAccessible(true);
        try
        {
          Object localObject1 = localField.get(localIBinder);
          localObject2 = localObject1;
        }
        catch (NullPointerException localNullPointerException)
        {
          throw new IllegalArgumentException("Binder object is null.", localNullPointerException);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw new IllegalArgumentException("remoteBinder is the wrong class.", localIllegalArgumentException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          throw new IllegalArgumentException("Could not access the field in remoteBinder.", localIllegalAccessException);
        }
      }
    }
    throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly one declared *private* field for the wrapped object. Preferably, this is an instance of the ObjectWrapper<T> class.");
    label122:
    throw new IllegalArgumentException("The concrete class implementing IObjectWrapper must have exactly *one* declared private field for the wrapped object.  Preferably, this is an instance of the ObjectWrapper<T> class.");
  }
  
  public static <T> zzd zzy(T paramT)
  {
    return new zze(paramT);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/dynamic/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */