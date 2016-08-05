package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.io.InputStream;

public abstract class GeneratorBase
  extends JsonGenerator
{
  protected static final int DERIVED_FEATURES_MASK = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.getMask() | JsonGenerator.Feature.ESCAPE_NON_ASCII.getMask() | JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.getMask();
  public static final int SURR1_FIRST = 55296;
  public static final int SURR1_LAST = 56319;
  public static final int SURR2_FIRST = 56320;
  public static final int SURR2_LAST = 57343;
  protected final String WRITE_BINARY = "write a binary value";
  protected final String WRITE_BOOLEAN = "write a boolean value";
  protected final String WRITE_NULL = "write a null";
  protected final String WRITE_NUMBER = "write a number";
  protected final String WRITE_RAW = "write a raw (unencoded) value";
  protected final String WRITE_STRING = "write a string";
  protected boolean _cfgNumbersAsStrings;
  protected boolean _closed;
  protected int _features;
  protected ObjectCodec _objectCodec;
  protected JsonWriteContext _writeContext;
  
  protected GeneratorBase(int paramInt, ObjectCodec paramObjectCodec)
  {
    this._features = paramInt;
    this._objectCodec = paramObjectCodec;
    if (JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(paramInt)) {}
    for (DupDetector localDupDetector = DupDetector.rootDetector(this);; localDupDetector = null)
    {
      this._writeContext = JsonWriteContext.createRootContext(localDupDetector);
      this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(paramInt);
      return;
    }
  }
  
  protected GeneratorBase(int paramInt, ObjectCodec paramObjectCodec, JsonWriteContext paramJsonWriteContext)
  {
    this._features = paramInt;
    this._objectCodec = paramObjectCodec;
    this._writeContext = paramJsonWriteContext;
    this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(paramInt);
  }
  
  protected PrettyPrinter _constructDefaultPrettyPrinter()
  {
    return new DefaultPrettyPrinter();
  }
  
  protected final int _decodeSurrogate(int paramInt1, int paramInt2)
    throws IOException
  {
    if ((paramInt2 < 56320) || (paramInt2 > 57343)) {
      _reportError("Incomplete surrogate pair: first char 0x" + Integer.toHexString(paramInt1) + ", second 0x" + Integer.toHexString(paramInt2));
    }
    return 65536 + (paramInt1 - 55296 << 10) + (paramInt2 - 56320);
  }
  
  protected abstract void _releaseBuffers();
  
  protected abstract void _verifyValueWrite(String paramString)
    throws IOException;
  
  public void close()
    throws IOException
  {
    this._closed = true;
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature)
  {
    int i = paramFeature.getMask();
    this._features &= (i ^ 0xFFFFFFFF);
    if ((i & DERIVED_FEATURES_MASK) != 0)
    {
      if (paramFeature != JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
        break label40;
      }
      this._cfgNumbersAsStrings = false;
    }
    for (;;)
    {
      return this;
      label40:
      if (paramFeature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
        setHighestNonEscapedChar(0);
      } else if (paramFeature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION) {
        this._writeContext = this._writeContext.withDupDetector(null);
      }
    }
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature)
  {
    int i = paramFeature.getMask();
    this._features = (i | this._features);
    if ((i & DERIVED_FEATURES_MASK) != 0)
    {
      if (paramFeature != JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
        break label37;
      }
      this._cfgNumbersAsStrings = true;
    }
    for (;;)
    {
      return this;
      label37:
      if (paramFeature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
        setHighestNonEscapedChar(127);
      } else if ((paramFeature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION) && (this._writeContext.getDupDetector() == null)) {
        this._writeContext = this._writeContext.withDupDetector(DupDetector.rootDetector(this));
      }
    }
  }
  
  public abstract void flush()
    throws IOException;
  
  public final ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public Object getCurrentValue()
  {
    return this._writeContext.getCurrentValue();
  }
  
  public int getFeatureMask()
  {
    return this._features;
  }
  
  public final JsonWriteContext getOutputContext()
  {
    return this._writeContext;
  }
  
  public boolean isClosed()
  {
    return this._closed;
  }
  
  public final boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    if ((this._features & paramFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
    return this;
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this._writeContext.setCurrentValue(paramObject);
  }
  
  public JsonGenerator setFeatureMask(int paramInt)
  {
    int i = paramInt ^ this._features;
    this._features = paramInt;
    if ((i & DERIVED_FEATURES_MASK) != 0)
    {
      this._cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(paramInt);
      if (JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(i))
      {
        if (!JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(paramInt)) {
          break label105;
        }
        setHighestNonEscapedChar(127);
      }
      if (JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i))
      {
        if (!JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(paramInt)) {
          break label114;
        }
        if (this._writeContext.getDupDetector() != null) {}
      }
    }
    label105:
    label114:
    for (this._writeContext = this._writeContext.withDupDetector(DupDetector.rootDetector(this));; this._writeContext = this._writeContext.withDupDetector(null))
    {
      return this;
      setHighestNonEscapedChar(0);
      break;
    }
  }
  
  public JsonGenerator useDefaultPrettyPrinter()
  {
    if (getPrettyPrinter() != null) {}
    for (;;)
    {
      return this;
      this = setPrettyPrinter(_constructDefaultPrettyPrinter());
    }
  }
  
  public Version version()
  {
    return VersionUtil.versionFor(getClass());
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException
  {
    _reportUnsupportedOperation();
    return 0;
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    writeFieldName(paramSerializableString.getValue());
  }
  
  public void writeObject(Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      if (this._objectCodec != null) {
        this._objectCodec.writeValue(this, paramObject);
      } else {
        _writeSimpleObject(paramObject);
      }
    }
  }
  
  public void writeRawValue(SerializableString paramSerializableString)
    throws IOException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramSerializableString);
  }
  
  public void writeRawValue(String paramString)
    throws IOException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramString);
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramString, paramInt1, paramInt2);
  }
  
  public void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    writeString(paramSerializableString.getValue());
  }
  
  public void writeTree(TreeNode paramTreeNode)
    throws IOException
  {
    if (paramTreeNode == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      if (this._objectCodec == null) {
        throw new IllegalStateException("No ObjectCodec defined");
      }
      this._objectCodec.writeValue(this, paramTreeNode);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/base/GeneratorBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */