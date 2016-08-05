package com.upsight.android.analytics.internal.dispatcher.delivery;

import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.internal.util.GzipHelper;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.apache.commons.io.IOUtils;

class UpsightEndpoint
{
  private static final String CONNECTION_CLOSE = "close";
  private static final int CONNECTION_TIMEOUT_MS = 30000;
  private static final String CONTENT_ENCODING_GZIP = "gzip";
  private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
  private static final String EMPTY_STRING = "";
  static final String HTTP_HEADER_REF_ID = "X-US-Ref-Id";
  static final String HTTP_HEADER_US_DIGEST = "X-US-DIGEST";
  public static final String LOG_TEXT_POSTING = "POSTING:       ";
  public static final String LOG_TEXT_RECEIVING = "RECEIVING:     ";
  public static final String LOG_TEXT_REQUEST_BODY = "\nREQUEST BODY:  ";
  public static final String LOG_TEXT_RESPONSE_BODY = "\nRESPONSE BODY: ";
  public static final String LOG_TEXT_RESPONSE_BODY_NONE = "[NONE]";
  public static final String LOG_TEXT_STATUS_CODE = "\nSTATUS CODE:   ";
  public static final String LOG_TEXT_TO = "\nTO:            ";
  private static final String POST_METHOD_NAME = "POST";
  public static final String SIGNED_MESSAGE_SEPARATOR = ":";
  private static final String USER_AGENT_ANDROID = "Android-" + Build.VERSION.SDK_INT;
  private static final boolean USE_GZIP;
  private String mEndpointAddress;
  private UpsightLogger mLogger;
  private ObjectMapper mMapper;
  private SignatureVerifier mSignatureVerifier;
  
  public UpsightEndpoint(String paramString, SignatureVerifier paramSignatureVerifier, ObjectMapper paramObjectMapper, UpsightLogger paramUpsightLogger)
  {
    this.mEndpointAddress = paramString;
    this.mSignatureVerifier = paramSignatureVerifier;
    this.mMapper = paramObjectMapper;
    this.mLogger = paramUpsightLogger;
  }
  
  private byte[] getRequestBodyBytes(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (byte[] arrayOfByte = GzipHelper.compress(paramString);; arrayOfByte = paramString.getBytes()) {
      return arrayOfByte;
    }
  }
  
  private String getVerifiedResponse(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject = "";
    String str1 = paramHttpURLConnection.getRequestProperty("X-US-Ref-Id");
    str2 = paramHttpURLConnection.getHeaderField("X-US-DIGEST");
    String str3;
    byte[] arrayOfByte1;
    if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)))
    {
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      if (localInputStream != null)
      {
        str3 = IOUtils.toString(localInputStream);
        if (!TextUtils.isEmpty(str3)) {
          arrayOfByte1 = (str3 + ":" + str1).getBytes();
        }
      }
    }
    try
    {
      byte[] arrayOfByte2 = Base64.decode(str2, 8);
      boolean bool = this.mSignatureVerifier.verify(arrayOfByte1, arrayOfByte2);
      if (bool) {
        localObject = str3;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        this.mLogger.e("Upsight", localIllegalArgumentException, "Message signature is not valid Base64. X-US-DIGEST: " + str2, new Object[0]);
      }
    }
    return (String)localObject;
  }
  
  /* Error */
  public Response send(UpsightRequest paramUpsightRequest)
    throws IOException
  {
    // Byte code:
    //   0: invokestatic 177	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   3: invokevirtual 178	java/util/UUID:toString	()Ljava/lang/String;
    //   6: astore_2
    //   7: aconst_null
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 101	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:mMapper	Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   13: aload_1
    //   14: invokevirtual 184	com/fasterxml/jackson/databind/ObjectMapper:writeValueAsString	(Ljava/lang/Object;)Ljava/lang/String;
    //   17: astore 5
    //   19: new 70	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   26: ldc 32
    //   28: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_2
    //   32: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: ldc 50
    //   37: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_0
    //   41: getfield 97	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:mEndpointAddress	Ljava/lang/String;
    //   44: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 38
    //   49: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: aload 5
    //   54: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: astore 6
    //   59: aload_0
    //   60: getfield 103	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:mLogger	Lcom/upsight/android/logger/UpsightLogger;
    //   63: ldc -95
    //   65: aload 6
    //   67: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: iconst_0
    //   71: anewarray 4	java/lang/Object
    //   74: invokeinterface 188 4 0
    //   79: aload_0
    //   80: aload 5
    //   82: iconst_0
    //   83: invokespecial 190	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:getRequestBodyBytes	(Ljava/lang/String;Z)[B
    //   86: astore 7
    //   88: new 192	java/net/URL
    //   91: dup
    //   92: aload_0
    //   93: getfield 97	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:mEndpointAddress	Ljava/lang/String;
    //   96: invokespecial 195	java/net/URL:<init>	(Ljava/lang/String;)V
    //   99: invokevirtual 199	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   102: checkcast 125	java/net/HttpURLConnection
    //   105: astore_3
    //   106: aload_3
    //   107: ldc 53
    //   109: invokevirtual 202	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   112: aload_3
    //   113: ldc 26
    //   115: aload_2
    //   116: invokevirtual 206	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   119: aload_3
    //   120: ldc -48
    //   122: ldc 20
    //   124: invokevirtual 206	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   127: aload_3
    //   128: ldc -46
    //   130: getstatic 93	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:USER_AGENT_ANDROID	Ljava/lang/String;
    //   133: invokevirtual 206	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: aload_3
    //   137: ldc -44
    //   139: ldc 11
    //   141: invokevirtual 206	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   144: aload_3
    //   145: sipush 30000
    //   148: invokevirtual 216	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   151: aload_3
    //   152: sipush 30000
    //   155: invokevirtual 219	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   158: aload_3
    //   159: iconst_1
    //   160: invokevirtual 223	java/net/HttpURLConnection:setDoInput	(Z)V
    //   163: aload_3
    //   164: iconst_1
    //   165: invokevirtual 226	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   168: aload_3
    //   169: aload 7
    //   171: arraylength
    //   172: invokevirtual 229	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   175: aload 7
    //   177: aload_3
    //   178: invokevirtual 233	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   181: invokestatic 237	org/apache/commons/io/IOUtils:write	([BLjava/io/OutputStream;)V
    //   184: aconst_null
    //   185: astore 8
    //   187: aload_3
    //   188: invokevirtual 241	java/net/HttpURLConnection:getResponseCode	()I
    //   191: istore 9
    //   193: new 70	java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial 73	java/lang/StringBuilder:<init>	()V
    //   200: ldc 35
    //   202: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: aload_2
    //   206: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: ldc 47
    //   211: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: iload 9
    //   216: invokevirtual 87	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   219: astore 10
    //   221: iload 9
    //   223: sipush 200
    //   226: if_icmpne +39 -> 265
    //   229: aload_0
    //   230: aload_3
    //   231: invokespecial 243	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:getVerifiedResponse	(Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   234: astore 8
    //   236: aload 10
    //   238: ldc 41
    //   240: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: astore 12
    //   245: aload 8
    //   247: invokestatic 138	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   250: ifeq +59 -> 309
    //   253: ldc 44
    //   255: astore 13
    //   257: aload 12
    //   259: aload 13
    //   261: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload_0
    //   266: getfield 103	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint:mLogger	Lcom/upsight/android/logger/UpsightLogger;
    //   269: ldc -95
    //   271: aload 10
    //   273: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   276: iconst_0
    //   277: anewarray 4	java/lang/Object
    //   280: invokeinterface 188 4 0
    //   285: new 6	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint$Response
    //   288: dup
    //   289: iload 9
    //   291: aload 8
    //   293: invokespecial 246	com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint$Response:<init>	(ILjava/lang/String;)V
    //   296: astore 11
    //   298: aload_3
    //   299: ifnull +7 -> 306
    //   302: aload_3
    //   303: invokevirtual 249	java/net/HttpURLConnection:disconnect	()V
    //   306: aload 11
    //   308: areturn
    //   309: aload 8
    //   311: astore 13
    //   313: goto -56 -> 257
    //   316: astore 4
    //   318: aload_3
    //   319: ifnull +7 -> 326
    //   322: aload_3
    //   323: invokevirtual 249	java/net/HttpURLConnection:disconnect	()V
    //   326: aload 4
    //   328: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	329	0	this	UpsightEndpoint
    //   0	329	1	paramUpsightRequest	UpsightRequest
    //   6	200	2	str1	String
    //   8	315	3	localHttpURLConnection	HttpURLConnection
    //   316	11	4	localObject	Object
    //   17	64	5	str2	String
    //   57	9	6	localStringBuilder1	StringBuilder
    //   86	90	7	arrayOfByte	byte[]
    //   185	125	8	str3	String
    //   191	99	9	i	int
    //   219	53	10	localStringBuilder2	StringBuilder
    //   296	11	11	localResponse	Response
    //   243	15	12	localStringBuilder3	StringBuilder
    //   255	57	13	str4	String
    // Exception table:
    //   from	to	target	type
    //   9	298	316	finally
  }
  
  public static class Response
  {
    public final String body;
    public final int statusCode;
    
    public Response(int paramInt, String paramString)
    {
      this.statusCode = paramInt;
      this.body = paramString;
    }
    
    public boolean isOk()
    {
      if (this.statusCode == 200) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/UpsightEndpoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */