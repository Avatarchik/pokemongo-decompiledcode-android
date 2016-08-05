package com.upsight.android.analytics.internal;

import android.content.ContextWrapper;
import android.content.res.Resources;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.R.raw;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;

public class AnalyticsContext
  extends ContextWrapper
{
  private static final String LOG_TAG = AnalyticsContext.class.getSimpleName();
  private final UpsightContext mUpsight;
  
  @Inject
  public AnalyticsContext(UpsightContext paramUpsightContext)
  {
    super(paramUpsightContext);
    this.mUpsight = paramUpsightContext;
  }
  
  public String getDefaultDispatcherConfiguration()
  {
    try
    {
      String str3 = IOUtils.toString(getResources().openRawResource(R.raw.dispatcher_config));
      str2 = str3;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        UpsightLogger localUpsightLogger = this.mUpsight.getLogger();
        String str1 = LOG_TAG;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException;
        localUpsightLogger.e(str1, "Could not read default configuration.", arrayOfObject);
        String str2 = null;
      }
    }
    return str2;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */