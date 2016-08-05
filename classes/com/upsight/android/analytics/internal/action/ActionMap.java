package com.upsight.android.analytics.internal.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.upsight.android.UpsightException;
import com.upsight.android.logger.UpsightLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ActionMap<T extends Actionable, U extends ActionContext>
  extends HashMap<String, List<Action<T, U>>>
{
  private static final String ACTIONS = "actions";
  private static final String TAG = ActionMap.class.getSimpleName();
  private static final String TRIGGER = "trigger";
  private int mActiveActionCount = 0;
  private boolean mIsActionMapCompleted = false;
  
  public ActionMap(ActionFactory<T, U> paramActionFactory, U paramU, JsonNode paramJsonNode)
  {
    if ((paramJsonNode != null) && (paramJsonNode.isArray()))
    {
      Iterator localIterator = paramJsonNode.elements();
      while (localIterator.hasNext())
      {
        JsonNode localJsonNode1 = (JsonNode)localIterator.next();
        JsonNode localJsonNode2 = localJsonNode1.get("trigger");
        JsonNode localJsonNode3 = localJsonNode1.get("actions");
        if ((localJsonNode2 != null) && (localJsonNode2.isTextual()) && (localJsonNode3 != null) && (localJsonNode3.isArray()))
        {
          int i = localJsonNode3.size();
          if (i > 0)
          {
            ArrayList localArrayList = new ArrayList(i);
            int j = 0;
            for (;;)
            {
              if (j < i)
              {
                JsonNode localJsonNode4 = null;
                try
                {
                  localJsonNode4 = localJsonNode3.get(j);
                  localArrayList.add(paramActionFactory.create(paramU, localJsonNode4));
                  j++;
                }
                catch (UpsightException localUpsightException)
                {
                  for (;;)
                  {
                    paramU.mLogger.e(TAG, localUpsightException, "Unable to create action from actionJSON=" + localJsonNode4, new Object[0]);
                  }
                }
              }
            }
            if (localArrayList.size() > 0) {
              put(localJsonNode2.asText(), localArrayList);
            }
          }
        }
      }
    }
  }
  
  private boolean isFinished()
  {
    if ((this.mIsActionMapCompleted) && (this.mActiveActionCount == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public void executeActions(String paramString, T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 130	com/upsight/android/analytics/internal/action/ActionMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   7: checkcast 83	java/util/List
    //   10: astore 4
    //   12: aload 4
    //   14: ifnull +58 -> 72
    //   17: aload 4
    //   19: invokeinterface 133 1 0
    //   24: astore 5
    //   26: aload 5
    //   28: invokeinterface 52 1 0
    //   33: ifeq +39 -> 72
    //   36: aload 5
    //   38: invokeinterface 56 1 0
    //   43: checkcast 135	com/upsight/android/analytics/internal/action/Action
    //   46: astore 6
    //   48: aload_0
    //   49: iconst_1
    //   50: aload_0
    //   51: getfield 35	com/upsight/android/analytics/internal/action/ActionMap:mActiveActionCount	I
    //   54: iadd
    //   55: putfield 35	com/upsight/android/analytics/internal/action/ActionMap:mActiveActionCount	I
    //   58: aload 6
    //   60: aload_2
    //   61: invokevirtual 139	com/upsight/android/analytics/internal/action/Action:execute	(Lcom/upsight/android/analytics/internal/action/Actionable;)V
    //   64: goto -38 -> 26
    //   67: astore_3
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_3
    //   71: athrow
    //   72: aload_0
    //   73: monitorexit
    //   74: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	this	ActionMap
    //   0	75	1	paramString	String
    //   0	75	2	paramT	T
    //   67	4	3	localObject	Object
    //   10	8	4	localList	List
    //   24	13	5	localIterator	Iterator
    //   46	13	6	localAction	Action
    // Exception table:
    //   from	to	target	type
    //   2	64	67	finally
  }
  
  /**
   * @deprecated
   */
  public boolean signalActionCompleted()
  {
    try
    {
      this.mActiveActionCount = (-1 + this.mActiveActionCount);
      boolean bool = isFinished();
      return bool;
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
  public boolean signalActionMapCompleted()
  {
    try
    {
      this.mIsActionMapCompleted = true;
      boolean bool = isFinished();
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/action/ActionMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */