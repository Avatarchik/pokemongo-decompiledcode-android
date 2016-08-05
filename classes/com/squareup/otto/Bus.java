package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Bus
{
  public static final String DEFAULT_IDENTIFIER = "default";
  private final ThreadEnforcer enforcer;
  private final ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>> eventsToDispatch = new ThreadLocal()
  {
    protected ConcurrentLinkedQueue<Bus.EventWithHandler> initialValue()
    {
      return new ConcurrentLinkedQueue();
    }
  };
  private final ConcurrentMap<Class<?>, Set<Class<?>>> flattenHierarchyCache = new ConcurrentHashMap();
  private final HandlerFinder handlerFinder;
  private final ConcurrentMap<Class<?>, Set<EventHandler>> handlersByType = new ConcurrentHashMap();
  private final String identifier;
  private final ThreadLocal<Boolean> isDispatching = new ThreadLocal()
  {
    protected Boolean initialValue()
    {
      return Boolean.valueOf(false);
    }
  };
  private final ConcurrentMap<Class<?>, EventProducer> producersByType = new ConcurrentHashMap();
  
  public Bus()
  {
    this("default");
  }
  
  public Bus(ThreadEnforcer paramThreadEnforcer)
  {
    this(paramThreadEnforcer, "default");
  }
  
  public Bus(ThreadEnforcer paramThreadEnforcer, String paramString)
  {
    this(paramThreadEnforcer, paramString, HandlerFinder.ANNOTATED);
  }
  
  Bus(ThreadEnforcer paramThreadEnforcer, String paramString, HandlerFinder paramHandlerFinder)
  {
    this.enforcer = paramThreadEnforcer;
    this.identifier = paramString;
    this.handlerFinder = paramHandlerFinder;
  }
  
  public Bus(String paramString)
  {
    this(ThreadEnforcer.MAIN, paramString);
  }
  
  private void dispatchProducerResultToHandler(EventHandler paramEventHandler, EventProducer paramEventProducer)
  {
    localObject1 = null;
    try
    {
      Object localObject2 = paramEventProducer.produceEvent();
      localObject1 = localObject2;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        throwRuntimeException("Producer " + paramEventProducer + " threw an exception.", localInvocationTargetException);
        continue;
        dispatch(localObject1, paramEventHandler);
      }
    }
    if (localObject1 == null) {
      return;
    }
  }
  
  private Set<Class<?>> getClassesFor(Class<?> paramClass)
  {
    LinkedList localLinkedList = new LinkedList();
    HashSet localHashSet = new HashSet();
    localLinkedList.add(paramClass);
    while (!localLinkedList.isEmpty())
    {
      Class localClass1 = (Class)localLinkedList.remove(0);
      localHashSet.add(localClass1);
      Class localClass2 = localClass1.getSuperclass();
      if (localClass2 != null) {
        localLinkedList.add(localClass2);
      }
    }
    return localHashSet;
  }
  
  private static void throwRuntimeException(String paramString, InvocationTargetException paramInvocationTargetException)
  {
    Throwable localThrowable = paramInvocationTargetException.getCause();
    if (localThrowable != null) {
      throw new RuntimeException(paramString + ": " + localThrowable.getMessage(), localThrowable);
    }
    throw new RuntimeException(paramString + ": " + paramInvocationTargetException.getMessage(), paramInvocationTargetException);
  }
  
  protected void dispatch(Object paramObject, EventHandler paramEventHandler)
  {
    try
    {
      paramEventHandler.handleEvent(paramObject);
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        throwRuntimeException("Could not dispatch event: " + paramObject.getClass() + " to handler " + paramEventHandler, localInvocationTargetException);
      }
    }
  }
  
  /* Error */
  protected void dispatchQueuedEvents()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 66	com/squareup/otto/Bus:isDispatching	Ljava/lang/ThreadLocal;
    //   4: invokevirtual 182	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   7: checkcast 184	java/lang/Boolean
    //   10: invokevirtual 187	java/lang/Boolean:booleanValue	()Z
    //   13: ifeq +4 -> 17
    //   16: return
    //   17: aload_0
    //   18: getfield 66	com/squareup/otto/Bus:isDispatching	Ljava/lang/ThreadLocal;
    //   21: iconst_1
    //   22: invokestatic 191	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   25: invokevirtual 194	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   28: aload_0
    //   29: getfield 63	com/squareup/otto/Bus:eventsToDispatch	Ljava/lang/ThreadLocal;
    //   32: invokevirtual 182	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   35: checkcast 196	java/util/concurrent/ConcurrentLinkedQueue
    //   38: invokevirtual 199	java/util/concurrent/ConcurrentLinkedQueue:poll	()Ljava/lang/Object;
    //   41: checkcast 10	com/squareup/otto/Bus$EventWithHandler
    //   44: astore_2
    //   45: aload_2
    //   46: ifnonnull +17 -> 63
    //   49: aload_0
    //   50: getfield 66	com/squareup/otto/Bus:isDispatching	Ljava/lang/ThreadLocal;
    //   53: iconst_0
    //   54: invokestatic 191	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   57: invokevirtual 194	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   60: goto -44 -> 16
    //   63: aload_2
    //   64: getfield 203	com/squareup/otto/Bus$EventWithHandler:handler	Lcom/squareup/otto/EventHandler;
    //   67: invokevirtual 206	com/squareup/otto/EventHandler:isValid	()Z
    //   70: ifeq -42 -> 28
    //   73: aload_0
    //   74: aload_2
    //   75: getfield 210	com/squareup/otto/Bus$EventWithHandler:event	Ljava/lang/Object;
    //   78: aload_2
    //   79: getfield 203	com/squareup/otto/Bus$EventWithHandler:handler	Lcom/squareup/otto/EventHandler;
    //   82: invokevirtual 115	com/squareup/otto/Bus:dispatch	(Ljava/lang/Object;Lcom/squareup/otto/EventHandler;)V
    //   85: goto -57 -> 28
    //   88: astore_1
    //   89: aload_0
    //   90: getfield 66	com/squareup/otto/Bus:isDispatching	Ljava/lang/ThreadLocal;
    //   93: iconst_0
    //   94: invokestatic 191	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   97: invokevirtual 194	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   100: aload_1
    //   101: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	102	0	this	Bus
    //   88	13	1	localObject	Object
    //   44	35	2	localEventWithHandler	EventWithHandler
    // Exception table:
    //   from	to	target	type
    //   28	45	88	finally
    //   63	85	88	finally
  }
  
  protected void enqueueEvent(Object paramObject, EventHandler paramEventHandler)
  {
    ((ConcurrentLinkedQueue)this.eventsToDispatch.get()).offer(new EventWithHandler(paramObject, paramEventHandler));
  }
  
  Set<Class<?>> flattenHierarchy(Class<?> paramClass)
  {
    Object localObject = (Set)this.flattenHierarchyCache.get(paramClass);
    if (localObject == null)
    {
      Set localSet = getClassesFor(paramClass);
      localObject = (Set)this.flattenHierarchyCache.putIfAbsent(paramClass, localSet);
      if (localObject == null) {
        localObject = localSet;
      }
    }
    return (Set<Class<?>>)localObject;
  }
  
  Set<EventHandler> getHandlersForEventType(Class<?> paramClass)
  {
    return (Set)this.handlersByType.get(paramClass);
  }
  
  EventProducer getProducerForEventType(Class<?> paramClass)
  {
    return (EventProducer)this.producersByType.get(paramClass);
  }
  
  public void post(Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("Event to post must not be null.");
    }
    this.enforcer.enforce(this);
    Set localSet1 = flattenHierarchy(paramObject.getClass());
    int i = 0;
    Iterator localIterator1 = localSet1.iterator();
    while (localIterator1.hasNext())
    {
      Set localSet2 = getHandlersForEventType((Class)localIterator1.next());
      if ((localSet2 != null) && (!localSet2.isEmpty()))
      {
        i = 1;
        Iterator localIterator2 = localSet2.iterator();
        while (localIterator2.hasNext()) {
          enqueueEvent(paramObject, (EventHandler)localIterator2.next());
        }
      }
    }
    if ((i == 0) && (!(paramObject instanceof DeadEvent))) {
      post(new DeadEvent(this, paramObject));
    }
    dispatchQueuedEvents();
  }
  
  public void register(Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("Object to register must not be null.");
    }
    this.enforcer.enforce(this);
    Map localMap1 = this.handlerFinder.findAllProducers(paramObject);
    Iterator localIterator1 = localMap1.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Class localClass3 = (Class)localIterator1.next();
      EventProducer localEventProducer2 = (EventProducer)localMap1.get(localClass3);
      EventProducer localEventProducer3 = (EventProducer)this.producersByType.putIfAbsent(localClass3, localEventProducer2);
      if (localEventProducer3 != null) {
        throw new IllegalArgumentException("Producer method for type " + localClass3 + " found on type " + localEventProducer2.target.getClass() + ", but already registered by type " + localEventProducer3.target.getClass() + ".");
      }
      Set localSet = (Set)this.handlersByType.get(localClass3);
      if ((localSet != null) && (!localSet.isEmpty()))
      {
        Iterator localIterator5 = localSet.iterator();
        while (localIterator5.hasNext()) {
          dispatchProducerResultToHandler((EventHandler)localIterator5.next(), localEventProducer2);
        }
      }
    }
    Map localMap2 = this.handlerFinder.findAllSubscribers(paramObject);
    Iterator localIterator2 = localMap2.keySet().iterator();
    while (localIterator2.hasNext())
    {
      Class localClass2 = (Class)localIterator2.next();
      Object localObject = (Set)this.handlersByType.get(localClass2);
      if (localObject == null)
      {
        CopyOnWriteArraySet localCopyOnWriteArraySet = new CopyOnWriteArraySet();
        localObject = (Set)this.handlersByType.putIfAbsent(localClass2, localCopyOnWriteArraySet);
        if (localObject == null) {
          localObject = localCopyOnWriteArraySet;
        }
      }
      if (!((Set)localObject).addAll((Set)localMap2.get(localClass2))) {
        throw new IllegalArgumentException("Object already registered.");
      }
    }
    Iterator localIterator3 = localMap2.entrySet().iterator();
    label521:
    while (localIterator3.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator3.next();
      Class localClass1 = (Class)localEntry.getKey();
      EventProducer localEventProducer1 = (EventProducer)this.producersByType.get(localClass1);
      if ((localEventProducer1 != null) && (localEventProducer1.isValid()))
      {
        Iterator localIterator4 = ((Set)localEntry.getValue()).iterator();
        for (;;)
        {
          if (!localIterator4.hasNext()) {
            break label521;
          }
          EventHandler localEventHandler = (EventHandler)localIterator4.next();
          if (!localEventProducer1.isValid()) {
            break;
          }
          if (localEventHandler.isValid()) {
            dispatchProducerResultToHandler(localEventHandler, localEventProducer1);
          }
        }
      }
    }
  }
  
  public String toString()
  {
    return "[Bus \"" + this.identifier + "\"]";
  }
  
  public void unregister(Object paramObject)
  {
    if (paramObject == null) {
      throw new NullPointerException("Object to unregister must not be null.");
    }
    this.enforcer.enforce(this);
    Iterator localIterator1 = this.handlerFinder.findAllProducers(paramObject).entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry2 = (Map.Entry)localIterator1.next();
      Class localClass = (Class)localEntry2.getKey();
      EventProducer localEventProducer1 = getProducerForEventType(localClass);
      EventProducer localEventProducer2 = (EventProducer)localEntry2.getValue();
      if ((localEventProducer2 == null) || (!localEventProducer2.equals(localEventProducer1))) {
        throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + paramObject.getClass() + " registered?");
      }
      ((EventProducer)this.producersByType.remove(localClass)).invalidate();
    }
    Iterator localIterator2 = this.handlerFinder.findAllSubscribers(paramObject).entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator2.next();
      Set localSet = getHandlersForEventType((Class)localEntry1.getKey());
      Collection localCollection = (Collection)localEntry1.getValue();
      if ((localSet == null) || (!localSet.containsAll(localCollection))) {
        throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + paramObject.getClass() + " registered?");
      }
      Iterator localIterator3 = localSet.iterator();
      while (localIterator3.hasNext())
      {
        EventHandler localEventHandler = (EventHandler)localIterator3.next();
        if (localCollection.contains(localEventHandler)) {
          localEventHandler.invalidate();
        }
      }
      localSet.removeAll(localCollection);
    }
  }
  
  static class EventWithHandler
  {
    final Object event;
    final EventHandler handler;
    
    public EventWithHandler(Object paramObject, EventHandler paramEventHandler)
    {
      this.event = paramObject;
      this.handler = paramEventHandler;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/squareup/otto/Bus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */