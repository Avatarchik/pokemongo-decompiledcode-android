package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observable.Operator;
import rx.Observer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.Subscriptions;

public final class OperatorWindowWithTime<T>
  implements Observable.Operator<Observable<T>, T>
{
  static final Object NEXT_SUBJECT = new Object();
  static final NotificationLite<Object> nl = NotificationLite.instance();
  final Scheduler scheduler;
  final int size;
  final long timeshift;
  final long timespan;
  final TimeUnit unit;
  
  public OperatorWindowWithTime(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    this.timespan = paramLong1;
    this.timeshift = paramLong2;
    this.unit = paramTimeUnit;
    this.size = paramInt;
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super Observable<T>> paramSubscriber)
  {
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    Object localObject;
    if (this.timespan == this.timeshift)
    {
      localObject = new ExactSubscriber(paramSubscriber, localWorker);
      ((ExactSubscriber)localObject).add(localWorker);
      ((ExactSubscriber)localObject).scheduleExact();
    }
    for (;;)
    {
      return (Subscriber<? super T>)localObject;
      InexactSubscriber localInexactSubscriber = new InexactSubscriber(paramSubscriber, localWorker);
      localInexactSubscriber.add(localWorker);
      localInexactSubscriber.startNewChunk();
      localInexactSubscriber.scheduleChunk();
      localObject = localInexactSubscriber;
    }
  }
  
  final class InexactSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    final List<OperatorWindowWithTime.CountedSerializedSubject<T>> chunks;
    boolean done;
    final Object guard;
    final Scheduler.Worker worker;
    
    public InexactSubscriber(Scheduler.Worker paramWorker)
    {
      super();
      this.child = paramWorker;
      Scheduler.Worker localWorker;
      this.worker = localWorker;
      this.guard = new Object();
      this.chunks = new LinkedList();
    }
    
    OperatorWindowWithTime.CountedSerializedSubject<T> createCountedSerializedSubject()
    {
      BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
      return new OperatorWindowWithTime.CountedSerializedSubject(localBufferUntilSubscriber, localBufferUntilSubscriber);
    }
    
    public void onCompleted()
    {
      synchronized (this.guard)
      {
        if (this.done) {
          return;
        }
        this.done = true;
        ArrayList localArrayList = new ArrayList(this.chunks);
        this.chunks.clear();
        Iterator localIterator = localArrayList.iterator();
        if (localIterator.hasNext()) {
          ((OperatorWindowWithTime.CountedSerializedSubject)localIterator.next()).consumer.onCompleted();
        }
      }
      this.child.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      synchronized (this.guard)
      {
        if (this.done) {
          return;
        }
        this.done = true;
        ArrayList localArrayList = new ArrayList(this.chunks);
        this.chunks.clear();
        Iterator localIterator = localArrayList.iterator();
        if (localIterator.hasNext()) {
          ((OperatorWindowWithTime.CountedSerializedSubject)localIterator.next()).consumer.onError(paramThrowable);
        }
      }
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      ArrayList localArrayList;
      Iterator localIterator2;
      OperatorWindowWithTime.CountedSerializedSubject localCountedSerializedSubject1;
      synchronized (this.guard)
      {
        if (!this.done)
        {
          localArrayList = new ArrayList(this.chunks);
          Iterator localIterator1 = this.chunks.iterator();
          while (localIterator1.hasNext())
          {
            OperatorWindowWithTime.CountedSerializedSubject localCountedSerializedSubject2 = (OperatorWindowWithTime.CountedSerializedSubject)localIterator1.next();
            int i = 1 + localCountedSerializedSubject2.count;
            localCountedSerializedSubject2.count = i;
            if (i == OperatorWindowWithTime.this.size) {
              localIterator1.remove();
            }
          }
        }
      }
    }
    
    public void onStart()
    {
      request(Long.MAX_VALUE);
    }
    
    void scheduleChunk()
    {
      this.worker.schedulePeriodically(new Action0()
      {
        public void call()
        {
          OperatorWindowWithTime.InexactSubscriber.this.startNewChunk();
        }
      }, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.unit);
    }
    
    void startNewChunk()
    {
      final OperatorWindowWithTime.CountedSerializedSubject localCountedSerializedSubject = createCountedSerializedSubject();
      synchronized (this.guard)
      {
        if (!this.done) {
          this.chunks.add(localCountedSerializedSubject);
        }
      }
    }
    
    void terminateChunk(OperatorWindowWithTime.CountedSerializedSubject<T> paramCountedSerializedSubject)
    {
      int i = 0;
      synchronized (this.guard)
      {
        if (!this.done)
        {
          Iterator localIterator = this.chunks.iterator();
          while (localIterator.hasNext()) {
            if ((OperatorWindowWithTime.CountedSerializedSubject)localIterator.next() == paramCountedSerializedSubject)
            {
              i = 1;
              localIterator.remove();
            }
          }
          if (i != 0) {
            paramCountedSerializedSubject.consumer.onCompleted();
          }
        }
      }
    }
  }
  
  static final class CountedSerializedSubject<T>
  {
    final Observer<T> consumer;
    int count;
    final Observable<T> producer;
    
    public CountedSerializedSubject(Observer<T> paramObserver, Observable<T> paramObservable)
    {
      this.consumer = new SerializedObserver(paramObserver);
      this.producer = paramObservable;
    }
  }
  
  final class ExactSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    boolean emitting;
    final Object guard;
    List<Object> queue;
    volatile OperatorWindowWithTime.State<T> state;
    final Scheduler.Worker worker;
    
    public ExactSubscriber(Scheduler.Worker paramWorker)
    {
      this.child = new SerializedSubscriber(paramWorker);
      Scheduler.Worker localWorker;
      this.worker = localWorker;
      this.guard = new Object();
      this.state = OperatorWindowWithTime.State.empty();
      paramWorker.add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          if (OperatorWindowWithTime.ExactSubscriber.this.state.consumer == null) {
            OperatorWindowWithTime.ExactSubscriber.this.unsubscribe();
          }
        }
      }));
    }
    
    void complete()
    {
      Observer localObserver = this.state.consumer;
      this.state = this.state.clear();
      if (localObserver != null) {
        localObserver.onCompleted();
      }
      this.child.onCompleted();
      unsubscribe();
    }
    
    boolean drain(List<Object> paramList)
    {
      boolean bool = true;
      if (paramList == null) {
        break label15;
      }
      for (;;)
      {
        return bool;
        Iterator localIterator = paramList.iterator();
        label15:
        if (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          if (localObject == OperatorWindowWithTime.NEXT_SUBJECT)
          {
            if (replaceSubject()) {
              break;
            }
            bool = false;
            continue;
          }
          if (OperatorWindowWithTime.nl.isError(localObject))
          {
            error(OperatorWindowWithTime.nl.getError(localObject));
          }
          else if (OperatorWindowWithTime.nl.isCompleted(localObject))
          {
            complete();
          }
          else
          {
            if (emitValue(localObject)) {
              break;
            }
            bool = false;
          }
        }
      }
    }
    
    boolean emitValue(T paramT)
    {
      OperatorWindowWithTime.State localState1 = this.state;
      boolean bool;
      if (localState1.consumer == null)
      {
        if (!replaceSubject())
        {
          bool = false;
          return bool;
        }
        localState1 = this.state;
      }
      localState1.consumer.onNext(paramT);
      if (localState1.count == -1 + OperatorWindowWithTime.this.size) {
        localState1.consumer.onCompleted();
      }
      for (OperatorWindowWithTime.State localState2 = localState1.clear();; localState2 = localState1.next())
      {
        this.state = localState2;
        bool = true;
        break;
      }
    }
    
    void error(Throwable paramThrowable)
    {
      Observer localObserver = this.state.consumer;
      this.state = this.state.clear();
      if (localObserver != null) {
        localObserver.onError(paramThrowable);
      }
      this.child.onError(paramThrowable);
      unsubscribe();
    }
    
    void nextWindow()
    {
      int i;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(OperatorWindowWithTime.NEXT_SUBJECT);
          return;
        }
        this.emitting = true;
        i = 0;
      }
      try
      {
        boolean bool1 = replaceSubject();
        if (!bool1)
        {
          if (0 == 0)
          {
            synchronized (this.guard)
            {
              this.emitting = false;
            }
            localObject2 = finally;
            throw ((Throwable)localObject2);
          }
        }
        else {
          synchronized (this.guard)
          {
            boolean bool2;
            do
            {
              List localList = this.queue;
              if (localList == null)
              {
                this.emitting = false;
                i = 1;
                if (i != 0) {
                  break;
                }
                synchronized (this.guard)
                {
                  this.emitting = false;
                }
              }
              this.queue = null;
              bool2 = drain(localList);
            } while (bool2);
            if (0 == 0) {
              synchronized (this.guard)
              {
                this.emitting = false;
              }
            }
          }
        }
        return;
      }
      finally
      {
        if (i == 0) {}
        synchronized (this.guard)
        {
          this.emitting = false;
          throw ((Throwable)localObject3);
        }
      }
    }
    
    public void onCompleted()
    {
      List localList;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(OperatorWindowWithTime.nl.completed());
          return;
        }
        localList = this.queue;
        this.queue = null;
        this.emitting = true;
      }
      try
      {
        drain(localList);
        complete();
      }
      catch (Throwable localThrowable)
      {
        error(localThrowable);
      }
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    
    public void onError(Throwable paramThrowable)
    {
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          this.queue = Collections.singletonList(OperatorWindowWithTime.nl.error(paramThrowable));
        }
        else
        {
          this.queue = null;
          this.emitting = true;
          error(paramThrowable);
        }
      }
    }
    
    public void onNext(T paramT)
    {
      int i;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(paramT);
          return;
        }
        this.emitting = true;
        i = 0;
      }
      try
      {
        boolean bool1 = emitValue(paramT);
        if (!bool1)
        {
          if (0 == 0)
          {
            synchronized (this.guard)
            {
              this.emitting = false;
            }
            localObject2 = finally;
            throw ((Throwable)localObject2);
          }
        }
        else {
          synchronized (this.guard)
          {
            boolean bool2;
            do
            {
              List localList = this.queue;
              if (localList == null)
              {
                this.emitting = false;
                i = 1;
                if (i != 0) {
                  break;
                }
                synchronized (this.guard)
                {
                  this.emitting = false;
                }
              }
              this.queue = null;
              bool2 = drain(localList);
            } while (bool2);
            if (0 == 0) {
              synchronized (this.guard)
              {
                this.emitting = false;
              }
            }
          }
        }
        return;
      }
      finally
      {
        if (i == 0) {}
        synchronized (this.guard)
        {
          this.emitting = false;
          throw ((Throwable)localObject3);
        }
      }
    }
    
    public void onStart()
    {
      request(Long.MAX_VALUE);
    }
    
    boolean replaceSubject()
    {
      Observer localObserver = this.state.consumer;
      if (localObserver != null) {
        localObserver.onCompleted();
      }
      if (this.child.isUnsubscribed())
      {
        this.state = this.state.clear();
        unsubscribe();
      }
      for (boolean bool = false;; bool = true)
      {
        return bool;
        BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
        this.state = this.state.create(localBufferUntilSubscriber, localBufferUntilSubscriber);
        this.child.onNext(localBufferUntilSubscriber);
      }
    }
    
    void scheduleExact()
    {
      this.worker.schedulePeriodically(new Action0()
      {
        public void call()
        {
          OperatorWindowWithTime.ExactSubscriber.this.nextWindow();
        }
      }, 0L, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
    }
  }
  
  static final class State<T>
  {
    static final State<Object> EMPTY = new State(null, null, 0);
    final Observer<T> consumer;
    final int count;
    final Observable<T> producer;
    
    public State(Observer<T> paramObserver, Observable<T> paramObservable, int paramInt)
    {
      this.consumer = paramObserver;
      this.producer = paramObservable;
      this.count = paramInt;
    }
    
    public static <T> State<T> empty()
    {
      return EMPTY;
    }
    
    public State<T> clear()
    {
      return empty();
    }
    
    public State<T> create(Observer<T> paramObserver, Observable<T> paramObservable)
    {
      return new State(paramObserver, paramObservable, 0);
    }
    
    public State<T> next()
    {
      return new State(this.consumer, this.producer, 1 + this.count);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorWindowWithTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */