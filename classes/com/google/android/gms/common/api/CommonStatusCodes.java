package com.google.android.gms.common.api;

public class CommonStatusCodes
{
  @Deprecated
  public static final int API_NOT_AVAILABLE = 17;
  public static final int API_NOT_CONNECTED = 17;
  public static final int AUTH_API_ACCESS_FORBIDDEN = 3001;
  public static final int AUTH_API_CLIENT_ERROR = 3002;
  public static final int AUTH_API_INVALID_CREDENTIALS = 3000;
  public static final int AUTH_API_SERVER_ERROR = 3003;
  public static final int AUTH_TOKEN_ERROR = 3004;
  public static final int AUTH_URL_RESOLUTION = 3005;
  public static final int CANCELED = 16;
  public static final int DEVELOPER_ERROR = 10;
  public static final int ERROR = 13;
  public static final int INTERNAL_ERROR = 8;
  public static final int INTERRUPTED = 14;
  public static final int INVALID_ACCOUNT = 5;
  public static final int LICENSE_CHECK_FAILED = 11;
  public static final int NETWORK_ERROR = 7;
  public static final int RESOLUTION_REQUIRED = 6;
  public static final int SERVICE_DISABLED = 3;
  public static final int SERVICE_INVALID = 9;
  public static final int SERVICE_MISSING = 1;
  public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
  public static final int SIGN_IN_REQUIRED = 4;
  public static final int SUCCESS = 0;
  public static final int SUCCESS_CACHE = -1;
  public static final int TIMEOUT = 15;
  
  public static String getStatusCodeString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      str = "unknown status code: " + paramInt;
    }
    for (;;)
    {
      return str;
      str = "SUCCESS_CACHE";
      continue;
      str = "SUCCESS";
      continue;
      str = "SERVICE_MISSING";
      continue;
      str = "SERVICE_VERSION_UPDATE_REQUIRED";
      continue;
      str = "SERVICE_DISABLED";
      continue;
      str = "SIGN_IN_REQUIRED";
      continue;
      str = "INVALID_ACCOUNT";
      continue;
      str = "RESOLUTION_REQUIRED";
      continue;
      str = "NETWORK_ERROR";
      continue;
      str = "INTERNAL_ERROR";
      continue;
      str = "SERVICE_INVALID";
      continue;
      str = "DEVELOPER_ERROR";
      continue;
      str = "LICENSE_CHECK_FAILED";
      continue;
      str = "ERROR";
      continue;
      str = "INTERRUPTED";
      continue;
      str = "TIMEOUT";
      continue;
      str = "CANCELED";
      continue;
      str = "API_NOT_CONNECTED";
      continue;
      str = "AUTH_API_INVALID_CREDENTIALS";
      continue;
      str = "AUTH_API_ACCESS_FORBIDDEN";
      continue;
      str = "AUTH_API_CLIENT_ERROR";
      continue;
      str = "AUTH_API_SERVER_ERROR";
      continue;
      str = "AUTH_TOKEN_ERROR";
      continue;
      str = "AUTH_URL_RESOLUTION";
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/api/CommonStatusCodes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */