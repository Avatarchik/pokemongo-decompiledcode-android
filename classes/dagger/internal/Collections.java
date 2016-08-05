package dagger.internal;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

final class Collections
{
  private static final int MAX_POWER_OF_TWO = 1073741824;
  
  private static int calculateInitialCapacity(int paramInt)
  {
    int i;
    if (paramInt < 3) {
      i = paramInt + 1;
    }
    for (;;)
    {
      return i;
      if (paramInt < 1073741824) {
        i = (int)(1.0F + paramInt / 0.75F);
      } else {
        i = Integer.MAX_VALUE;
      }
    }
  }
  
  static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int paramInt)
  {
    return new LinkedHashMap(calculateInitialCapacity(paramInt));
  }
  
  static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int paramInt)
  {
    return new LinkedHashSet(calculateInitialCapacity(paramInt));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/Collections.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */