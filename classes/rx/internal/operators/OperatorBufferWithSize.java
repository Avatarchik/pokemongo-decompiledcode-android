package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorBufferWithSize<T>
  implements Observable.Operator<List<T>, T>
{
  final int count;
  final int skip;
  
  public OperatorBufferWithSize(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0) {
      throw new IllegalArgumentException("count must be greater than 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("skip must be greater than 0");
    }
    this.count = paramInt1;
    this.skip = paramInt2;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super List<T>> paramSubscriber)
  {
    if (this.count == this.skip) {}
    for (Object localObject = new Subscriber(paramSubscriber)
        {
          List<T> buffer;
          
          public void onCompleted()
          {
            List localList = this.buffer;
            this.buffer = null;
            if (localList != null) {}
            try
            {
              paramSubscriber.onNext(localList);
              paramSubscriber.onCompleted();
              return;
            }
            catch (Throwable localThrowable)
            {
              for (;;)
              {
                onError(localThrowable);
              }
            }
          }
          
          public void onError(Throwable paramAnonymousThrowable)
          {
            this.buffer = null;
            paramSubscriber.onError(paramAnonymousThrowable);
          }
          
          public void onNext(T paramAnonymousT)
          {
            if (this.buffer == null) {
              this.buffer = new ArrayList(OperatorBufferWithSize.this.count);
            }
            this.buffer.add(paramAnonymousT);
            if (this.buffer.size() == OperatorBufferWithSize.this.count)
            {
              List localList = this.buffer;
              this.buffer = null;
              paramSubscriber.onNext(localList);
            }
          }
          
          public void setProducer(final Producer paramAnonymousProducer)
          {
            paramSubscriber.setProducer(new Producer()
            {
              private volatile boolean infinite = false;
              
              public void request(long paramAnonymous2Long)
              {
                if (this.infinite) {}
                for (;;)
                {
                  return;
                  if (paramAnonymous2Long >= Long.MAX_VALUE / OperatorBufferWithSize.this.count)
                  {
                    this.infinite = true;
                    paramAnonymousProducer.request(Long.MAX_VALUE);
                  }
                  else
                  {
                    paramAnonymousProducer.request(paramAnonymous2Long * OperatorBufferWithSize.this.count);
                  }
                }
              }
            });
          }
        };; localObject = new Subscriber(paramSubscriber)
        {
          final List<List<T>> chunks = new LinkedList();
          int index;
          
          /* Error */
          public void onCompleted()
          {
            // Byte code:
            //   0: aload_0
            //   1: getfield 37	rx/internal/operators/OperatorBufferWithSize$2:chunks	Ljava/util/List;
            //   4: invokeinterface 46 1 0
            //   9: astore_2
            //   10: aload_2
            //   11: invokeinterface 52 1 0
            //   16: ifeq +42 -> 58
            //   19: aload_2
            //   20: invokeinterface 56 1 0
            //   25: checkcast 42	java/util/List
            //   28: astore_3
            //   29: aload_0
            //   30: getfield 27	rx/internal/operators/OperatorBufferWithSize$2:val$child	Lrx/Subscriber;
            //   33: aload_3
            //   34: invokevirtual 60	rx/Subscriber:onNext	(Ljava/lang/Object;)V
            //   37: goto -27 -> 10
            //   40: astore 4
            //   42: aload_0
            //   43: aload 4
            //   45: invokevirtual 64	rx/internal/operators/OperatorBufferWithSize$2:onError	(Ljava/lang/Throwable;)V
            //   48: aload_0
            //   49: getfield 37	rx/internal/operators/OperatorBufferWithSize$2:chunks	Ljava/util/List;
            //   52: invokeinterface 67 1 0
            //   57: return
            //   58: aload_0
            //   59: getfield 27	rx/internal/operators/OperatorBufferWithSize$2:val$child	Lrx/Subscriber;
            //   62: invokevirtual 69	rx/Subscriber:onCompleted	()V
            //   65: aload_0
            //   66: getfield 37	rx/internal/operators/OperatorBufferWithSize$2:chunks	Ljava/util/List;
            //   69: invokeinterface 67 1 0
            //   74: goto -17 -> 57
            //   77: astore_1
            //   78: aload_0
            //   79: getfield 37	rx/internal/operators/OperatorBufferWithSize$2:chunks	Ljava/util/List;
            //   82: invokeinterface 67 1 0
            //   87: aload_1
            //   88: athrow
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	89	0	this	2
            //   77	11	1	localObject	Object
            //   9	11	2	localIterator	Iterator
            //   28	6	3	localList	List
            //   40	4	4	localThrowable	Throwable
            // Exception table:
            //   from	to	target	type
            //   29	37	40	java/lang/Throwable
            //   0	29	77	finally
            //   29	37	77	finally
            //   42	48	77	finally
            //   58	65	77	finally
          }
          
          public void onError(Throwable paramAnonymousThrowable)
          {
            this.chunks.clear();
            paramSubscriber.onError(paramAnonymousThrowable);
          }
          
          public void onNext(T paramAnonymousT)
          {
            int i = this.index;
            this.index = (i + 1);
            if (i % OperatorBufferWithSize.this.skip == 0) {
              this.chunks.add(new ArrayList(OperatorBufferWithSize.this.count));
            }
            Iterator localIterator = this.chunks.iterator();
            while (localIterator.hasNext())
            {
              List localList = (List)localIterator.next();
              localList.add(paramAnonymousT);
              if (localList.size() == OperatorBufferWithSize.this.count)
              {
                localIterator.remove();
                paramSubscriber.onNext(localList);
              }
            }
          }
          
          public void setProducer(final Producer paramAnonymousProducer)
          {
            paramSubscriber.setProducer(new Producer()
            {
              private volatile boolean firstRequest = true;
              private volatile boolean infinite = false;
              
              private void requestInfinite()
              {
                this.infinite = true;
                paramAnonymousProducer.request(Long.MAX_VALUE);
              }
              
              public void request(long paramAnonymous2Long)
              {
                if (paramAnonymous2Long == 0L) {}
                for (;;)
                {
                  return;
                  if (paramAnonymous2Long < 0L) {
                    throw new IllegalArgumentException("request a negative number: " + paramAnonymous2Long);
                  }
                  if (!this.infinite) {
                    if (paramAnonymous2Long == Long.MAX_VALUE)
                    {
                      requestInfinite();
                    }
                    else if (this.firstRequest)
                    {
                      this.firstRequest = false;
                      if (paramAnonymous2Long - 1L >= (Long.MAX_VALUE - OperatorBufferWithSize.this.count) / OperatorBufferWithSize.this.skip) {
                        requestInfinite();
                      } else {
                        paramAnonymousProducer.request(OperatorBufferWithSize.this.count + OperatorBufferWithSize.this.skip * (paramAnonymous2Long - 1L));
                      }
                    }
                    else if (paramAnonymous2Long >= Long.MAX_VALUE / OperatorBufferWithSize.this.skip)
                    {
                      requestInfinite();
                    }
                    else
                    {
                      paramAnonymousProducer.request(paramAnonymous2Long * OperatorBufferWithSize.this.skip);
                    }
                  }
                }
              }
            });
          }
        }) {
      return (Subscriber<? super T>)localObject;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorBufferWithSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */