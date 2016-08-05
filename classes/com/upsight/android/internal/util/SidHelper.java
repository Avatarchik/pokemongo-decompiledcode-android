package com.upsight.android.internal.util;

import android.content.Context;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class SidHelper
{
  private static final int BYTE_BUFFER_CAPACITY = 8;
  public static final String PREFERENCE_KEY_SID = "sid";
  
  /**
   * @deprecated
   */
  public static String getSid(Context paramContext)
  {
    try
    {
      String str = PreferencesHelper.getString(paramContext, "sid", null);
      if (str == null)
      {
        UUID localUUID = UUID.randomUUID();
        str = new BigInteger(1, longToBytes(localUUID.getLeastSignificantBits() ^ localUUID.getMostSignificantBits())).toString();
        PreferencesHelper.putString(paramContext, "sid", str);
      }
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static byte[] longToBytes(long paramLong)
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(8);
    localByteBuffer.putLong(paramLong);
    return localByteBuffer.array();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/util/SidHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */