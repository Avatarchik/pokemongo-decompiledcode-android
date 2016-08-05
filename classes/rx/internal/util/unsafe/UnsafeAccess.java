package rx.internal.util.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class UnsafeAccess
{
  public static final Unsafe UNSAFE;
  
  static
  {
    Unsafe localUnsafe = null;
    try
    {
      Field localField = Unsafe.class.getDeclaredField("theUnsafe");
      localField.setAccessible(true);
      localUnsafe = (Unsafe)localField.get(null);
      UNSAFE = localUnsafe;
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  private UnsafeAccess()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static long addressOf(Class<?> paramClass, String paramString)
  {
    try
    {
      Field localField = paramClass.getDeclaredField(paramString);
      long l = UNSAFE.objectFieldOffset(localField);
      return l;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      InternalError localInternalError = new InternalError();
      localInternalError.initCause(localNoSuchFieldException);
      throw localInternalError;
    }
  }
  
  public static boolean compareAndSwapInt(Object paramObject, long paramLong, int paramInt1, int paramInt2)
  {
    return UNSAFE.compareAndSwapInt(paramObject, paramLong, paramInt1, paramInt2);
  }
  
  public static int getAndAddInt(Object paramObject, long paramLong, int paramInt)
  {
    int i;
    int j;
    do
    {
      i = UNSAFE.getIntVolatile(paramObject, paramLong);
      j = i + paramInt;
    } while (!UNSAFE.compareAndSwapInt(paramObject, paramLong, i, j));
    return i;
  }
  
  public static int getAndIncrementInt(Object paramObject, long paramLong)
  {
    int i;
    int j;
    do
    {
      i = UNSAFE.getIntVolatile(paramObject, paramLong);
      j = i + 1;
    } while (!UNSAFE.compareAndSwapInt(paramObject, paramLong, i, j));
    return i;
  }
  
  public static int getAndSetInt(Object paramObject, long paramLong, int paramInt)
  {
    int i;
    do
    {
      i = UNSAFE.getIntVolatile(paramObject, paramLong);
    } while (!UNSAFE.compareAndSwapInt(paramObject, paramLong, i, paramInt));
    return i;
  }
  
  public static final boolean isUnsafeAvailable()
  {
    if (UNSAFE != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/UnsafeAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */