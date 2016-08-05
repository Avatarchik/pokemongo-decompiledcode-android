package android.support.v4.os;

import android.os.Build.VERSION;

public final class CancellationSignal
{
  private boolean mCancelInProgress;
  private Object mCancellationSignalObj;
  private boolean mIsCanceled;
  private OnCancelListener mOnCancelListener;
  
  private void waitForCancelFinishedLocked()
  {
    while (this.mCancelInProgress) {
      try
      {
        wait();
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  /* Error */
  public void cancel()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 29	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   6: ifeq +8 -> 14
    //   9: aload_0
    //   10: monitorexit
    //   11: goto +96 -> 107
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 29	android/support/v4/os/CancellationSignal:mIsCanceled	Z
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield 23	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   24: aload_0
    //   25: getfield 31	android/support/v4/os/CancellationSignal:mOnCancelListener	Landroid/support/v4/os/CancellationSignal$OnCancelListener;
    //   28: astore_2
    //   29: aload_0
    //   30: getfield 33	android/support/v4/os/CancellationSignal:mCancellationSignalObj	Ljava/lang/Object;
    //   33: astore_3
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_2
    //   37: ifnull +9 -> 46
    //   40: aload_2
    //   41: invokeinterface 36 1 0
    //   46: aload_3
    //   47: ifnull +7 -> 54
    //   50: aload_3
    //   51: invokestatic 41	android/support/v4/os/CancellationSignalCompatJellybean:cancel	(Ljava/lang/Object;)V
    //   54: aload_0
    //   55: monitorenter
    //   56: aload_0
    //   57: iconst_0
    //   58: putfield 23	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   61: aload_0
    //   62: invokevirtual 44	java/lang/Object:notifyAll	()V
    //   65: aload_0
    //   66: monitorexit
    //   67: goto +40 -> 107
    //   70: astore 6
    //   72: aload_0
    //   73: monitorexit
    //   74: aload 6
    //   76: athrow
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    //   82: astore 4
    //   84: aload_0
    //   85: monitorenter
    //   86: aload_0
    //   87: iconst_0
    //   88: putfield 23	android/support/v4/os/CancellationSignal:mCancelInProgress	Z
    //   91: aload_0
    //   92: invokevirtual 44	java/lang/Object:notifyAll	()V
    //   95: aload_0
    //   96: monitorexit
    //   97: aload 4
    //   99: athrow
    //   100: astore 5
    //   102: aload_0
    //   103: monitorexit
    //   104: aload 5
    //   106: athrow
    //   107: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	CancellationSignal
    //   77	4	1	localObject1	Object
    //   28	13	2	localOnCancelListener	OnCancelListener
    //   33	18	3	localObject2	Object
    //   82	16	4	localObject3	Object
    //   100	5	5	localObject4	Object
    //   70	5	6	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   56	74	70	finally
    //   2	36	77	finally
    //   78	80	77	finally
    //   40	54	82	finally
    //   86	97	100	finally
    //   102	104	100	finally
  }
  
  public Object getCancellationSignalObject()
  {
    Object localObject2;
    if (Build.VERSION.SDK_INT < 16) {
      localObject2 = null;
    }
    for (;;)
    {
      return localObject2;
      try
      {
        if (this.mCancellationSignalObj == null)
        {
          this.mCancellationSignalObj = CancellationSignalCompatJellybean.create();
          if (this.mIsCanceled) {
            CancellationSignalCompatJellybean.cancel(this.mCancellationSignalObj);
          }
        }
        localObject2 = this.mCancellationSignalObj;
      }
      finally
      {
        localObject1 = finally;
        throw ((Throwable)localObject1);
      }
    }
  }
  
  public boolean isCanceled()
  {
    try
    {
      boolean bool = this.mIsCanceled;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setOnCancelListener(OnCancelListener paramOnCancelListener)
  {
    try
    {
      waitForCancelFinishedLocked();
      if (this.mOnCancelListener == paramOnCancelListener) {
        return;
      }
      this.mOnCancelListener = paramOnCancelListener;
      if ((this.mIsCanceled) && (paramOnCancelListener != null)) {}
    }
    finally
    {
      throw ((Throwable)localObject);
    }
  }
  
  public void throwIfCanceled()
  {
    if (isCanceled()) {
      throw new OperationCanceledException();
    }
  }
  
  public static abstract interface OnCancelListener
  {
    public abstract void onCancel();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/os/CancellationSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */