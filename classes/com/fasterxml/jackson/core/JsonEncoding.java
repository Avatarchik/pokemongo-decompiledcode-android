package com.fasterxml.jackson.core;

public enum JsonEncoding
{
  protected final boolean _bigEndian;
  protected final int _bits;
  protected final String _javaName;
  
  static
  {
    UTF16_BE = new JsonEncoding("UTF16_BE", 1, "UTF-16BE", true, 16);
    UTF16_LE = new JsonEncoding("UTF16_LE", 2, "UTF-16LE", false, 16);
    UTF32_BE = new JsonEncoding("UTF32_BE", 3, "UTF-32BE", true, 32);
    UTF32_LE = new JsonEncoding("UTF32_LE", 4, "UTF-32LE", false, 32);
    JsonEncoding[] arrayOfJsonEncoding = new JsonEncoding[5];
    arrayOfJsonEncoding[0] = UTF8;
    arrayOfJsonEncoding[1] = UTF16_BE;
    arrayOfJsonEncoding[2] = UTF16_LE;
    arrayOfJsonEncoding[3] = UTF32_BE;
    arrayOfJsonEncoding[4] = UTF32_LE;
    $VALUES = arrayOfJsonEncoding;
  }
  
  private JsonEncoding(String paramString, boolean paramBoolean, int paramInt)
  {
    this._javaName = paramString;
    this._bigEndian = paramBoolean;
    this._bits = paramInt;
  }
  
  public int bits()
  {
    return this._bits;
  }
  
  public String getJavaName()
  {
    return this._javaName;
  }
  
  public boolean isBigEndian()
  {
    return this._bigEndian;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */