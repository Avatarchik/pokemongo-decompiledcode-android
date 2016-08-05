package com.fasterxml.jackson.core;

public final class Base64Variants
{
  public static final Base64Variant MIME = new Base64Variant("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
  public static final Base64Variant MIME_NO_LINEFEEDS = new Base64Variant(MIME, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
  public static final Base64Variant MODIFIED_FOR_URL;
  public static final Base64Variant PEM = new Base64Variant(MIME, "PEM", true, '=', 64);
  static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    localStringBuilder.setCharAt(localStringBuilder.indexOf("+"), '-');
    localStringBuilder.setCharAt(localStringBuilder.indexOf("/"), '_');
    MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", localStringBuilder.toString(), false, '\000', Integer.MAX_VALUE);
  }
  
  public static Base64Variant getDefaultVariant()
  {
    return MIME_NO_LINEFEEDS;
  }
  
  public static Base64Variant valueOf(String paramString)
    throws IllegalArgumentException
  {
    Base64Variant localBase64Variant;
    if (MIME._name.equals(paramString)) {
      localBase64Variant = MIME;
    }
    for (;;)
    {
      return localBase64Variant;
      if (MIME_NO_LINEFEEDS._name.equals(paramString))
      {
        localBase64Variant = MIME_NO_LINEFEEDS;
      }
      else if (PEM._name.equals(paramString))
      {
        localBase64Variant = PEM;
      }
      else
      {
        if (!MODIFIED_FOR_URL._name.equals(paramString)) {
          break;
        }
        localBase64Variant = MODIFIED_FOR_URL;
      }
    }
    if (paramString == null) {}
    for (String str = "<null>";; str = "'" + paramString + "'") {
      throw new IllegalArgumentException("No Base64Variant with name " + str);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/Base64Variants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */