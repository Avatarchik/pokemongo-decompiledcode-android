package com.upsight.android.analytics.internal.association;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.annotation.Created;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import rx.Observable;
import rx.functions.Action1;

class AssociationManagerImpl
  implements AssociationManager
{
  static final long ASSOCIATION_EXPIRY = 604800000L;
  private static final String KEY_UPSIGHT_DATA = "upsight_data";
  private final Map<String, Set<Association>> mAssociations;
  private final Clock mClock;
  private final UpsightDataStore mDataStore;
  private boolean mIsLaunched = false;
  
  AssociationManagerImpl(UpsightDataStore paramUpsightDataStore, Clock paramClock)
  {
    this.mDataStore = paramUpsightDataStore;
    this.mClock = paramClock;
    this.mAssociations = new HashMap();
  }
  
  /* Error */
  /**
   * @deprecated
   */
  void addAssociation(String paramString, Association paramAssociation)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic 49	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: istore 4
    //   8: iload 4
    //   10: ifne +7 -> 17
    //   13: aload_2
    //   14: ifnonnull +6 -> 20
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: getfield 41	com/upsight/android/analytics/internal/association/AssociationManagerImpl:mAssociations	Ljava/util/Map;
    //   24: aload_1
    //   25: invokeinterface 55 2 0
    //   30: checkcast 57	java/util/Set
    //   33: astore 5
    //   35: aload 5
    //   37: ifnonnull +12 -> 49
    //   40: new 59	java/util/HashSet
    //   43: dup
    //   44: invokespecial 60	java/util/HashSet:<init>	()V
    //   47: astore 5
    //   49: aload 5
    //   51: aload_2
    //   52: invokeinterface 64 2 0
    //   57: pop
    //   58: aload_0
    //   59: getfield 41	com/upsight/android/analytics/internal/association/AssociationManagerImpl:mAssociations	Ljava/util/Map;
    //   62: aload_1
    //   63: aload 5
    //   65: invokeinterface 68 3 0
    //   70: pop
    //   71: goto -54 -> 17
    //   74: astore_3
    //   75: aload_0
    //   76: monitorexit
    //   77: aload_3
    //   78: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	AssociationManagerImpl
    //   0	79	1	paramString	String
    //   0	79	2	paramAssociation	Association
    //   74	4	3	localObject1	Object
    //   6	3	4	bool	boolean
    //   33	31	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	8	74	finally
    //   20	71	74	finally
  }
  
  /**
   * @deprecated
   */
  public void associate(String paramString, ObjectNode paramObjectNode)
  {
    try
    {
      associateInner(paramString, paramObjectNode);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  void associateInner(String paramString, ObjectNode paramObjectNode)
  {
    Set localSet;
    int i;
    Iterator localIterator1;
    for (;;)
    {
      Association localAssociation;
      try
      {
        localSet = (Set)this.mAssociations.get(paramString);
        if (localSet == null) {
          break label332;
        }
        i = 0;
        localIterator1 = localSet.iterator();
        if (!localIterator1.hasNext()) {
          break label332;
        }
        localAssociation = (Association)localIterator1.next();
        if (this.mClock.currentTimeMillis() - localAssociation.getTimestampMs() > 604800000L)
        {
          localIterator1.remove();
          UpsightDataStore localUpsightDataStore2 = this.mDataStore;
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = localAssociation.getId();
          localUpsightDataStore2.removeObservable(Association.class, arrayOfString2).subscribe();
          continue;
        }
        if (i != 0) {
          continue;
        }
      }
      finally {}
      Association.UpsightDataFilter localUpsightDataFilter = localAssociation.getUpsightDataFilter();
      JsonNode localJsonNode1 = paramObjectNode.path("upsight_data");
      if (localJsonNode1.isObject())
      {
        ObjectNode localObjectNode = (ObjectNode)localJsonNode1;
        JsonNode localJsonNode2 = localObjectNode.path(localUpsightDataFilter.matchKey);
        if (localJsonNode2.isValueNode())
        {
          Iterator localIterator2 = localUpsightDataFilter.matchValues.iterator();
          if (localIterator2.hasNext())
          {
            if (!localJsonNode2.equals((JsonNode)localIterator2.next())) {
              break;
            }
            Iterator localIterator3 = localAssociation.getUpsightData().fields();
            while (localIterator3.hasNext())
            {
              Map.Entry localEntry = (Map.Entry)localIterator3.next();
              localObjectNode.put((String)localEntry.getKey(), (JsonNode)localEntry.getValue());
            }
            localIterator1.remove();
            UpsightDataStore localUpsightDataStore1 = this.mDataStore;
            String[] arrayOfString1 = new String[1];
            arrayOfString1[0] = localAssociation.getId();
            localUpsightDataStore1.removeObservable(Association.class, arrayOfString1).subscribe();
            i = 1;
          }
        }
      }
    }
    label332:
  }
  
  @Created
  public void handleCreate(Association paramAssociation)
  {
    addAssociation(paramAssociation.getWith(), paramAssociation);
  }
  
  /**
   * @deprecated
   */
  public void launch()
  {
    try
    {
      if (!this.mIsLaunched)
      {
        this.mIsLaunched = true;
        launchInner();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  void launchInner()
  {
    try
    {
      this.mDataStore.subscribe(this);
      this.mDataStore.fetchObservable(Association.class).subscribe(new Action1()
      {
        public void call(Association paramAnonymousAssociation)
        {
          AssociationManagerImpl.this.addAssociation(paramAnonymousAssociation.getWith(), paramAnonymousAssociation);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/association/AssociationManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */