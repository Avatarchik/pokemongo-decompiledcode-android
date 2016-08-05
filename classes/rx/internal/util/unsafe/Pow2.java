package rx.internal.util.unsafe;

public final class Pow2
{
  private Pow2()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static boolean isPowerOfTwo(int paramInt)
  {
    if ((paramInt & paramInt - 1) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static int roundToPowerOfTwo(int paramInt)
  {
    return 1 << 32 - Integer.numberOfLeadingZeros(paramInt - 1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/Pow2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */