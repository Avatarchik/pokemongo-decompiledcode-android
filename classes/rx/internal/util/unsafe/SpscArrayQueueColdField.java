package rx.internal.util.unsafe;

abstract class SpscArrayQueueColdField<E>
  extends ConcurrentCircularArrayQueue<E>
{
  private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
  protected final int lookAheadStep;
  
  public SpscArrayQueueColdField(int paramInt)
  {
    super(paramInt);
    this.lookAheadStep = Math.min(paramInt / 4, MAX_LOOK_AHEAD_STEP.intValue());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpscArrayQueueColdField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */