package com.fasterxml.jackson.databind.jsonFormatVisitors;

public enum JsonValueFormat
{
  private final String _desc;
  
  static
  {
    DATE = new JsonValueFormat("DATE", 1, "date");
    TIME = new JsonValueFormat("TIME", 2, "time");
    UTC_MILLISEC = new JsonValueFormat("UTC_MILLISEC", 3, "utc-millisec");
    REGEX = new JsonValueFormat("REGEX", 4, "regex");
    COLOR = new JsonValueFormat("COLOR", 5, "color");
    STYLE = new JsonValueFormat("STYLE", 6, "style");
    PHONE = new JsonValueFormat("PHONE", 7, "phone");
    URI = new JsonValueFormat("URI", 8, "uri");
    EMAIL = new JsonValueFormat("EMAIL", 9, "email");
    IP_ADDRESS = new JsonValueFormat("IP_ADDRESS", 10, "ip-address");
    IPV6 = new JsonValueFormat("IPV6", 11, "ipv6");
    HOST_NAME = new JsonValueFormat("HOST_NAME", 12, "host-name");
    JsonValueFormat[] arrayOfJsonValueFormat = new JsonValueFormat[13];
    arrayOfJsonValueFormat[0] = DATE_TIME;
    arrayOfJsonValueFormat[1] = DATE;
    arrayOfJsonValueFormat[2] = TIME;
    arrayOfJsonValueFormat[3] = UTC_MILLISEC;
    arrayOfJsonValueFormat[4] = REGEX;
    arrayOfJsonValueFormat[5] = COLOR;
    arrayOfJsonValueFormat[6] = STYLE;
    arrayOfJsonValueFormat[7] = PHONE;
    arrayOfJsonValueFormat[8] = URI;
    arrayOfJsonValueFormat[9] = EMAIL;
    arrayOfJsonValueFormat[10] = IP_ADDRESS;
    arrayOfJsonValueFormat[11] = IPV6;
    arrayOfJsonValueFormat[12] = HOST_NAME;
    $VALUES = arrayOfJsonValueFormat;
  }
  
  private JsonValueFormat(String paramString)
  {
    this._desc = paramString;
  }
  
  public String toString()
  {
    return this._desc;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsonFormatVisitors/JsonValueFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */