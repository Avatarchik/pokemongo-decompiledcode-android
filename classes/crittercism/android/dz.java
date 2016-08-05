package crittercism.android;

import java.util.concurrent.ThreadFactory;

public final class dz
  implements ThreadFactory
{
  public final Thread newThread(Runnable paramRunnable)
  {
    return new dy(paramRunnable);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */