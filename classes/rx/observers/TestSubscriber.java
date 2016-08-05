package rx.observers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observer;
import rx.Subscriber;
import rx.annotations.Experimental;
import rx.exceptions.CompositeException;

public class TestSubscriber<T>
  extends Subscriber<T>
{
  private static final Observer<Object> INERT = new Observer()
  {
    public void onCompleted() {}
    
    public void onError(Throwable paramAnonymousThrowable) {}
    
    public void onNext(Object paramAnonymousObject) {}
  };
  private final long initialRequest;
  private volatile Thread lastSeenThread;
  private final CountDownLatch latch = new CountDownLatch(1);
  private final TestObserver<T> testObserver;
  
  public TestSubscriber()
  {
    this(-1L);
  }
  
  @Experimental
  public TestSubscriber(long paramLong)
  {
    this(INERT, paramLong);
  }
  
  public TestSubscriber(Observer<T> paramObserver)
  {
    this(paramObserver, -1L);
  }
  
  @Experimental
  public TestSubscriber(Observer<T> paramObserver, long paramLong)
  {
    if (paramObserver == null) {
      throw new NullPointerException();
    }
    this.testObserver = new TestObserver(paramObserver);
    this.initialRequest = paramLong;
  }
  
  public TestSubscriber(Subscriber<T> paramSubscriber)
  {
    this(paramSubscriber, -1L);
  }
  
  @Experimental
  public static <T> TestSubscriber<T> create()
  {
    return new TestSubscriber();
  }
  
  @Experimental
  public static <T> TestSubscriber<T> create(long paramLong)
  {
    return new TestSubscriber(paramLong);
  }
  
  @Experimental
  public static <T> TestSubscriber<T> create(Observer<T> paramObserver)
  {
    return new TestSubscriber(paramObserver);
  }
  
  @Experimental
  public static <T> TestSubscriber<T> create(Observer<T> paramObserver, long paramLong)
  {
    return new TestSubscriber(paramObserver, paramLong);
  }
  
  @Experimental
  public static <T> TestSubscriber<T> create(Subscriber<T> paramSubscriber)
  {
    return new TestSubscriber(paramSubscriber);
  }
  
  @Experimental
  public void assertCompleted()
  {
    int i = this.testObserver.getOnCompletedEvents().size();
    if (i == 0) {
      throw new AssertionError("Not completed!");
    }
    if (i > 1) {
      throw new AssertionError("Completed multiple times: " + i);
    }
  }
  
  @Experimental
  public void assertError(Class<? extends Throwable> paramClass)
  {
    List localList = this.testObserver.getOnErrorEvents();
    if (localList.size() == 0) {
      throw new AssertionError("No errors");
    }
    if (localList.size() > 1)
    {
      AssertionError localAssertionError1 = new AssertionError("Multiple errors: " + localList.size());
      localAssertionError1.initCause(new CompositeException(localList));
      throw localAssertionError1;
    }
    if (!paramClass.isInstance(localList.get(0)))
    {
      AssertionError localAssertionError2 = new AssertionError("Exceptions differ; expected: " + paramClass + ", actual: " + localList.get(0));
      localAssertionError2.initCause((Throwable)localList.get(0));
      throw localAssertionError2;
    }
  }
  
  @Experimental
  public void assertError(Throwable paramThrowable)
  {
    List localList = this.testObserver.getOnErrorEvents();
    if (localList.size() == 0) {
      throw new AssertionError("No errors");
    }
    if (localList.size() > 1)
    {
      AssertionError localAssertionError1 = new AssertionError("Multiple errors: " + localList.size());
      localAssertionError1.initCause(new CompositeException(localList));
      throw localAssertionError1;
    }
    if (!paramThrowable.equals(localList.get(0)))
    {
      AssertionError localAssertionError2 = new AssertionError("Exceptions differ; expected: " + paramThrowable + ", actual: " + localList.get(0));
      localAssertionError2.initCause((Throwable)localList.get(0));
      throw localAssertionError2;
    }
  }
  
  public void assertNoErrors()
  {
    if (getOnErrorEvents().size() > 0) {
      throw new RuntimeException("Unexpected onError events: " + getOnErrorEvents().size(), (Throwable)getOnErrorEvents().get(0));
    }
  }
  
  @Experimental
  public void assertNoTerminalEvent()
  {
    List localList = this.testObserver.getOnErrorEvents();
    int i = this.testObserver.getOnCompletedEvents().size();
    if ((localList.size() > 0) || (i > 0))
    {
      if (localList.isEmpty()) {
        throw new AssertionError("Found " + localList.size() + " errors and " + i + " completion events instead of none");
      }
      if (localList.size() == 1)
      {
        AssertionError localAssertionError1 = new AssertionError("Found " + localList.size() + " errors and " + i + " completion events instead of none");
        localAssertionError1.initCause((Throwable)localList.get(0));
        throw localAssertionError1;
      }
      AssertionError localAssertionError2 = new AssertionError("Found " + localList.size() + " errors and " + i + " completion events instead of none");
      localAssertionError2.initCause(new CompositeException(localList));
      throw localAssertionError2;
    }
  }
  
  @Experimental
  public void assertNoValues()
  {
    int i = this.testObserver.getOnNextEvents().size();
    if (i > 0) {
      throw new AssertionError("No onNext events expected yet some received: " + i);
    }
  }
  
  @Experimental
  public void assertNotCompleted()
  {
    int i = this.testObserver.getOnCompletedEvents().size();
    if (i == 1) {
      throw new AssertionError("Completed!");
    }
    if (i > 1) {
      throw new AssertionError("Completed multiple times: " + i);
    }
  }
  
  public void assertReceivedOnNext(List<T> paramList)
  {
    this.testObserver.assertReceivedOnNext(paramList);
  }
  
  public void assertTerminalEvent()
  {
    this.testObserver.assertTerminalEvent();
  }
  
  public void assertUnsubscribed()
  {
    if (!isUnsubscribed()) {
      throw new AssertionError("Not unsubscribed.");
    }
  }
  
  @Experimental
  public void assertValue(T paramT)
  {
    assertReceivedOnNext(Collections.singletonList(paramT));
  }
  
  @Experimental
  public void assertValueCount(int paramInt)
  {
    int i = this.testObserver.getOnNextEvents().size();
    if (i != paramInt) {
      throw new AssertionError("Number of onNext events differ; expected: " + paramInt + ", actual: " + i);
    }
  }
  
  @Experimental
  public void assertValues(T... paramVarArgs)
  {
    assertReceivedOnNext(Arrays.asList(paramVarArgs));
  }
  
  public void awaitTerminalEvent()
  {
    try
    {
      this.latch.await();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException("Interrupted", localInterruptedException);
    }
  }
  
  public void awaitTerminalEvent(long paramLong, TimeUnit paramTimeUnit)
  {
    try
    {
      this.latch.await(paramLong, paramTimeUnit);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException("Interrupted", localInterruptedException);
    }
  }
  
  public void awaitTerminalEventAndUnsubscribeOnTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    try
    {
      if (!this.latch.await(paramLong, paramTimeUnit)) {
        unsubscribe();
      }
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        unsubscribe();
      }
    }
  }
  
  public Thread getLastSeenThread()
  {
    return this.lastSeenThread;
  }
  
  public List<Notification<T>> getOnCompletedEvents()
  {
    return this.testObserver.getOnCompletedEvents();
  }
  
  public List<Throwable> getOnErrorEvents()
  {
    return this.testObserver.getOnErrorEvents();
  }
  
  public List<T> getOnNextEvents()
  {
    return this.testObserver.getOnNextEvents();
  }
  
  public void onCompleted()
  {
    try
    {
      this.lastSeenThread = Thread.currentThread();
      this.testObserver.onCompleted();
      return;
    }
    finally
    {
      this.latch.countDown();
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    try
    {
      this.lastSeenThread = Thread.currentThread();
      this.testObserver.onError(paramThrowable);
      return;
    }
    finally
    {
      this.latch.countDown();
    }
  }
  
  public void onNext(T paramT)
  {
    this.lastSeenThread = Thread.currentThread();
    this.testObserver.onNext(paramT);
  }
  
  public void onStart()
  {
    if (this.initialRequest >= 0L) {
      requestMore(this.initialRequest);
    }
  }
  
  public void requestMore(long paramLong)
  {
    request(paramLong);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/observers/TestSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */