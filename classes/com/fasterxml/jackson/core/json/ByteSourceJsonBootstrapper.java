package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ByteSourceJsonBootstrapper
{
  static final byte UTF8_BOM_1 = -17;
  static final byte UTF8_BOM_2 = -69;
  static final byte UTF8_BOM_3 = -65;
  protected boolean _bigEndian = true;
  private final boolean _bufferRecyclable;
  protected int _bytesPerChar = 0;
  protected final IOContext _context;
  protected final InputStream _in;
  protected final byte[] _inputBuffer;
  private int _inputEnd;
  protected int _inputProcessed;
  private int _inputPtr;
  
  public ByteSourceJsonBootstrapper(IOContext paramIOContext, InputStream paramInputStream)
  {
    this._context = paramIOContext;
    this._in = paramInputStream;
    this._inputBuffer = paramIOContext.allocReadIOBuffer();
    this._inputPtr = 0;
    this._inputEnd = 0;
    this._inputProcessed = 0;
    this._bufferRecyclable = true;
  }
  
  public ByteSourceJsonBootstrapper(IOContext paramIOContext, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this._context = paramIOContext;
    this._in = null;
    this._inputBuffer = paramArrayOfByte;
    this._inputPtr = paramInt1;
    this._inputEnd = (paramInt1 + paramInt2);
    this._inputProcessed = (-paramInt1);
    this._bufferRecyclable = false;
  }
  
  private boolean checkUTF16(int paramInt)
  {
    boolean bool = false;
    if ((0xFF00 & paramInt) == 0) {}
    for (this._bigEndian = true;; this._bigEndian = false)
    {
      this._bytesPerChar = 2;
      bool = true;
      do
      {
        return bool;
      } while ((paramInt & 0xFF) != 0);
    }
  }
  
  private boolean checkUTF32(int paramInt)
    throws IOException
  {
    boolean bool = false;
    if (paramInt >> 8 == 0) {
      this._bigEndian = true;
    }
    for (;;)
    {
      this._bytesPerChar = 4;
      bool = true;
      do
      {
        return bool;
        if ((0xFFFFFF & paramInt) == 0)
        {
          this._bigEndian = false;
          break;
        }
        if ((0xFF00FFFF & paramInt) == 0)
        {
          reportWeirdUCS4("3412");
          break;
        }
      } while ((0xFFFF00FF & paramInt) != 0);
      reportWeirdUCS4("2143");
    }
  }
  
  private boolean handleBOM(int paramInt)
    throws IOException
  {
    int i = 1;
    int j;
    switch (paramInt)
    {
    default: 
      j = paramInt >>> 16;
      if (j == 65279)
      {
        this._inputPtr = (2 + this._inputPtr);
        this._bytesPerChar = 2;
        this._bigEndian = i;
      }
      break;
    }
    for (;;)
    {
      return i;
      this._bigEndian = i;
      this._inputPtr = (4 + this._inputPtr);
      this._bytesPerChar = 4;
      continue;
      this._inputPtr = (4 + this._inputPtr);
      this._bytesPerChar = 4;
      this._bigEndian = false;
      continue;
      reportWeirdUCS4("2143");
      reportWeirdUCS4("3412");
      break;
      if (j == 65534)
      {
        this._inputPtr = (2 + this._inputPtr);
        this._bytesPerChar = 2;
        this._bigEndian = false;
      }
      else if (paramInt >>> 8 == 15711167)
      {
        this._inputPtr = (3 + this._inputPtr);
        this._bytesPerChar = i;
        this._bigEndian = i;
      }
      else
      {
        i = 0;
      }
    }
  }
  
  public static MatchStrength hasJSONFormat(InputAccessor paramInputAccessor)
    throws IOException
  {
    MatchStrength localMatchStrength;
    if (!paramInputAccessor.hasMoreBytes()) {
      localMatchStrength = MatchStrength.INCONCLUSIVE;
    }
    for (;;)
    {
      return localMatchStrength;
      byte b = paramInputAccessor.nextByte();
      if (b == -17)
      {
        if (!paramInputAccessor.hasMoreBytes()) {
          localMatchStrength = MatchStrength.INCONCLUSIVE;
        } else if (paramInputAccessor.nextByte() != -69) {
          localMatchStrength = MatchStrength.NO_MATCH;
        } else if (!paramInputAccessor.hasMoreBytes()) {
          localMatchStrength = MatchStrength.INCONCLUSIVE;
        } else if (paramInputAccessor.nextByte() != -65) {
          localMatchStrength = MatchStrength.NO_MATCH;
        } else if (!paramInputAccessor.hasMoreBytes()) {
          localMatchStrength = MatchStrength.INCONCLUSIVE;
        } else {
          b = paramInputAccessor.nextByte();
        }
      }
      else
      {
        int i = skipSpace(paramInputAccessor, b);
        if (i < 0)
        {
          localMatchStrength = MatchStrength.INCONCLUSIVE;
        }
        else if (i == 123)
        {
          int m = skipSpace(paramInputAccessor);
          if (m < 0) {
            localMatchStrength = MatchStrength.INCONCLUSIVE;
          } else if ((m == 34) || (m == 125)) {
            localMatchStrength = MatchStrength.SOLID_MATCH;
          } else {
            localMatchStrength = MatchStrength.NO_MATCH;
          }
        }
        else if (i == 91)
        {
          int k = skipSpace(paramInputAccessor);
          if (k < 0) {
            localMatchStrength = MatchStrength.INCONCLUSIVE;
          } else if ((k == 93) || (k == 91)) {
            localMatchStrength = MatchStrength.SOLID_MATCH;
          } else {
            localMatchStrength = MatchStrength.SOLID_MATCH;
          }
        }
        else
        {
          localMatchStrength = MatchStrength.WEAK_MATCH;
          if ((i != 34) && ((i > 57) || (i < 48))) {
            if (i == 45)
            {
              int j = skipSpace(paramInputAccessor);
              if (j < 0) {
                localMatchStrength = MatchStrength.INCONCLUSIVE;
              } else if ((j > 57) || (j < 48)) {
                localMatchStrength = MatchStrength.NO_MATCH;
              }
            }
            else if (i == 110)
            {
              localMatchStrength = tryMatch(paramInputAccessor, "ull", localMatchStrength);
            }
            else if (i == 116)
            {
              localMatchStrength = tryMatch(paramInputAccessor, "rue", localMatchStrength);
            }
            else if (i == 102)
            {
              localMatchStrength = tryMatch(paramInputAccessor, "alse", localMatchStrength);
            }
            else
            {
              localMatchStrength = MatchStrength.NO_MATCH;
            }
          }
        }
      }
    }
  }
  
  private void reportWeirdUCS4(String paramString)
    throws IOException
  {
    throw new CharConversionException("Unsupported UCS-4 endianness (" + paramString + ") detected");
  }
  
  private static int skipSpace(InputAccessor paramInputAccessor)
    throws IOException
  {
    if (!paramInputAccessor.hasMoreBytes()) {}
    for (int i = -1;; i = skipSpace(paramInputAccessor, paramInputAccessor.nextByte())) {
      return i;
    }
  }
  
  private static int skipSpace(InputAccessor paramInputAccessor, byte paramByte)
    throws IOException
  {
    for (;;)
    {
      int i = paramByte & 0xFF;
      if ((i != 32) && (i != 13) && (i != 10) && (i != 9)) {}
      for (;;)
      {
        return i;
        if (paramInputAccessor.hasMoreBytes()) {
          break;
        }
        i = -1;
      }
      paramByte = paramInputAccessor.nextByte();
      (paramByte & 0xFF);
    }
  }
  
  private static MatchStrength tryMatch(InputAccessor paramInputAccessor, String paramString, MatchStrength paramMatchStrength)
    throws IOException
  {
    int i = 0;
    int j = paramString.length();
    for (;;)
    {
      if (i < j) {
        if (paramInputAccessor.hasMoreBytes()) {
          break label29;
        }
      }
      for (paramMatchStrength = MatchStrength.INCONCLUSIVE;; paramMatchStrength = MatchStrength.NO_MATCH)
      {
        return paramMatchStrength;
        label29:
        if (paramInputAccessor.nextByte() == paramString.charAt(i)) {
          break;
        }
      }
      i++;
    }
  }
  
  public JsonParser constructParser(int paramInt1, ObjectCodec paramObjectCodec, ByteQuadsCanonicalizer paramByteQuadsCanonicalizer, CharsToNameCanonicalizer paramCharsToNameCanonicalizer, int paramInt2)
    throws IOException
  {
    ByteQuadsCanonicalizer localByteQuadsCanonicalizer;
    if ((detectEncoding() == JsonEncoding.UTF8) && (JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(paramInt2))) {
      localByteQuadsCanonicalizer = paramByteQuadsCanonicalizer.makeChild(paramInt2);
    }
    for (Object localObject = new UTF8StreamJsonParser(this._context, paramInt1, this._in, paramObjectCodec, localByteQuadsCanonicalizer, this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);; localObject = new ReaderBasedJsonParser(this._context, paramInt1, constructReader(), paramObjectCodec, paramCharsToNameCanonicalizer.makeChild(paramInt2))) {
      return (JsonParser)localObject;
    }
  }
  
  public Reader constructReader()
    throws IOException
  {
    JsonEncoding localJsonEncoding = this._context.getEncoding();
    InputStream localInputStream;
    Object localObject2;
    switch (localJsonEncoding.bits())
    {
    default: 
      throw new RuntimeException("Internal error");
    case 8: 
    case 16: 
      localInputStream = this._in;
      if (localInputStream == null) {
        localObject2 = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
      }
      break;
    }
    for (;;)
    {
      for (Object localObject1 = new InputStreamReader((InputStream)localObject2, localJsonEncoding.getJavaName());; localObject1 = new UTF32Reader(this._context, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._context.getEncoding().isBigEndian()))
      {
        return (Reader)localObject1;
        if (this._inputPtr >= this._inputEnd) {
          break label185;
        }
        localObject2 = new MergedStream(this._context, localInputStream, this._inputBuffer, this._inputPtr, this._inputEnd);
        break;
      }
      label185:
      localObject2 = localInputStream;
    }
  }
  
  public JsonEncoding detectEncoding()
    throws IOException
  {
    int i = 0;
    int j;
    if (ensureLoaded(4))
    {
      j = this._inputBuffer[this._inputPtr] << 24 | (0xFF & this._inputBuffer[(1 + this._inputPtr)]) << 16 | (0xFF & this._inputBuffer[(2 + this._inputPtr)]) << 8 | 0xFF & this._inputBuffer[(3 + this._inputPtr)];
      if (handleBOM(j))
      {
        i = 1;
        if (i != 0) {
          break label186;
        }
      }
    }
    for (JsonEncoding localJsonEncoding = JsonEncoding.UTF8;; localJsonEncoding = JsonEncoding.UTF8)
    {
      this._context.setEncoding(localJsonEncoding);
      return localJsonEncoding;
      if (checkUTF32(j))
      {
        i = 1;
        break;
      }
      if (!checkUTF16(j >>> 16)) {
        break;
      }
      i = 1;
      break;
      if ((!ensureLoaded(2)) || (!checkUTF16((0xFF & this._inputBuffer[this._inputPtr]) << 8 | 0xFF & this._inputBuffer[(1 + this._inputPtr)]))) {
        break;
      }
      i = 1;
      break;
      label186:
      switch (this._bytesPerChar)
      {
      case 3: 
      default: 
        throw new RuntimeException("Internal error");
      }
    }
    if (this._bigEndian) {}
    for (localJsonEncoding = JsonEncoding.UTF16_BE;; localJsonEncoding = JsonEncoding.UTF16_LE) {
      break;
    }
    if (this._bigEndian) {}
    for (localJsonEncoding = JsonEncoding.UTF32_BE;; localJsonEncoding = JsonEncoding.UTF32_LE) {
      break;
    }
  }
  
  protected boolean ensureLoaded(int paramInt)
    throws IOException
  {
    int i = 1;
    int j = this._inputEnd - this._inputPtr;
    for (;;)
    {
      if (j < paramInt) {
        if (this._in != null) {
          break label38;
        }
      }
      label38:
      int m;
      for (int k = -1; k < i; m = this._in.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd))
      {
        i = 0;
        return i;
      }
      this._inputEnd = (m + this._inputEnd);
      j += m;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/ByteSourceJsonBootstrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */