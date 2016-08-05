package com.upsight.android.analytics.internal.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.logger.UpsightLogger;
import rx.Scheduler.Worker;

public class ActionContext
{
  public final Bus mBus;
  public final Clock mClock;
  public final UpsightLogger mLogger;
  public final Scheduler.Worker mMainWorker;
  public final ObjectMapper mMapper;
  public final UpsightContext mUpsight;
  
  public ActionContext(UpsightContext paramUpsightContext, Bus paramBus, ObjectMapper paramObjectMapper, Clock paramClock, Scheduler.Worker paramWorker, UpsightLogger paramUpsightLogger)
  {
    this.mUpsight = paramUpsightContext;
    this.mBus = paramBus;
    this.mMapper = paramObjectMapper;
    this.mClock = paramClock;
    this.mMainWorker = paramWorker;
    this.mLogger = paramUpsightLogger;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/action/ActionContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */