package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.net.URL;

public class JsonFactory
  implements Versioned, Serializable
{
  protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = ;
  protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
  protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
  private static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
  public static final String FORMAT_NAME_JSON = "JSON";
  protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal();
  private static final long serialVersionUID = 1L;
  protected final transient ByteQuadsCanonicalizer _byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
  protected CharacterEscapes _characterEscapes;
  protected int _factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
  protected int _generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
  protected InputDecorator _inputDecorator;
  protected ObjectCodec _objectCodec;
  protected OutputDecorator _outputDecorator;
  protected int _parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
  @Deprecated
  protected final transient BytesToNameCanonicalizer _rootByteSymbols = BytesToNameCanonicalizer.createRoot();
  protected final transient CharsToNameCanonicalizer _rootCharSymbols = CharsToNameCanonicalizer.createRoot();
  protected SerializableString _rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
  
  public JsonFactory()
  {
    this(null);
  }
  
  protected JsonFactory(JsonFactory paramJsonFactory, ObjectCodec paramObjectCodec)
  {
    this._objectCodec = null;
    this._factoryFeatures = paramJsonFactory._factoryFeatures;
    this._parserFeatures = paramJsonFactory._parserFeatures;
    this._generatorFeatures = paramJsonFactory._generatorFeatures;
    this._characterEscapes = paramJsonFactory._characterEscapes;
    this._inputDecorator = paramJsonFactory._inputDecorator;
    this._outputDecorator = paramJsonFactory._outputDecorator;
    this._rootValueSeparator = paramJsonFactory._rootValueSeparator;
  }
  
  public JsonFactory(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
  }
  
  protected void _checkInvalidCopy(Class<?> paramClass)
  {
    if (getClass() != paramClass) {
      throw new IllegalStateException("Failed copy(): " + getClass().getName() + " (version: " + version() + ") does not override copy(); it has to");
    }
  }
  
  protected IOContext _createContext(Object paramObject, boolean paramBoolean)
  {
    return new IOContext(_getBufferRecycler(), paramObject, paramBoolean);
  }
  
  protected JsonGenerator _createGenerator(Writer paramWriter, IOContext paramIOContext)
    throws IOException
  {
    WriterBasedJsonGenerator localWriterBasedJsonGenerator = new WriterBasedJsonGenerator(paramIOContext, this._generatorFeatures, this._objectCodec, paramWriter);
    if (this._characterEscapes != null) {
      localWriterBasedJsonGenerator.setCharacterEscapes(this._characterEscapes);
    }
    SerializableString localSerializableString = this._rootValueSeparator;
    if (localSerializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
      localWriterBasedJsonGenerator.setRootValueSeparator(localSerializableString);
    }
    return localWriterBasedJsonGenerator;
  }
  
  protected JsonParser _createParser(InputStream paramInputStream, IOContext paramIOContext)
    throws IOException
  {
    return new ByteSourceJsonBootstrapper(paramIOContext, paramInputStream).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
  }
  
  protected JsonParser _createParser(Reader paramReader, IOContext paramIOContext)
    throws IOException
  {
    return new ReaderBasedJsonParser(paramIOContext, this._parserFeatures, paramReader, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
  }
  
  protected JsonParser _createParser(byte[] paramArrayOfByte, int paramInt1, int paramInt2, IOContext paramIOContext)
    throws IOException
  {
    return new ByteSourceJsonBootstrapper(paramIOContext, paramArrayOfByte, paramInt1, paramInt2).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
  }
  
  protected JsonParser _createParser(char[] paramArrayOfChar, int paramInt1, int paramInt2, IOContext paramIOContext, boolean paramBoolean)
    throws IOException
  {
    return new ReaderBasedJsonParser(paramIOContext, this._parserFeatures, null, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures), paramArrayOfChar, paramInt1, paramInt1 + paramInt2, paramBoolean);
  }
  
  protected JsonGenerator _createUTF8Generator(OutputStream paramOutputStream, IOContext paramIOContext)
    throws IOException
  {
    UTF8JsonGenerator localUTF8JsonGenerator = new UTF8JsonGenerator(paramIOContext, this._generatorFeatures, this._objectCodec, paramOutputStream);
    if (this._characterEscapes != null) {
      localUTF8JsonGenerator.setCharacterEscapes(this._characterEscapes);
    }
    SerializableString localSerializableString = this._rootValueSeparator;
    if (localSerializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
      localUTF8JsonGenerator.setRootValueSeparator(localSerializableString);
    }
    return localUTF8JsonGenerator;
  }
  
  protected Writer _createWriter(OutputStream paramOutputStream, JsonEncoding paramJsonEncoding, IOContext paramIOContext)
    throws IOException
  {
    if (paramJsonEncoding == JsonEncoding.UTF8) {}
    for (Object localObject = new UTF8Writer(paramIOContext, paramOutputStream);; localObject = new OutputStreamWriter(paramOutputStream, paramJsonEncoding.getJavaName())) {
      return (Writer)localObject;
    }
  }
  
  protected final InputStream _decorate(InputStream paramInputStream, IOContext paramIOContext)
    throws IOException
  {
    InputStream localInputStream;
    if (this._inputDecorator != null)
    {
      localInputStream = this._inputDecorator.decorate(paramIOContext, paramInputStream);
      if (localInputStream == null) {}
    }
    for (;;)
    {
      return localInputStream;
      localInputStream = paramInputStream;
    }
  }
  
  protected final OutputStream _decorate(OutputStream paramOutputStream, IOContext paramIOContext)
    throws IOException
  {
    OutputStream localOutputStream;
    if (this._outputDecorator != null)
    {
      localOutputStream = this._outputDecorator.decorate(paramIOContext, paramOutputStream);
      if (localOutputStream == null) {}
    }
    for (;;)
    {
      return localOutputStream;
      localOutputStream = paramOutputStream;
    }
  }
  
  protected final Reader _decorate(Reader paramReader, IOContext paramIOContext)
    throws IOException
  {
    Reader localReader;
    if (this._inputDecorator != null)
    {
      localReader = this._inputDecorator.decorate(paramIOContext, paramReader);
      if (localReader == null) {}
    }
    for (;;)
    {
      return localReader;
      localReader = paramReader;
    }
  }
  
  protected final Writer _decorate(Writer paramWriter, IOContext paramIOContext)
    throws IOException
  {
    Writer localWriter;
    if (this._outputDecorator != null)
    {
      localWriter = this._outputDecorator.decorate(paramIOContext, paramWriter);
      if (localWriter == null) {}
    }
    for (;;)
    {
      return localWriter;
      localWriter = paramWriter;
    }
  }
  
  public BufferRecycler _getBufferRecycler()
  {
    SoftReference localSoftReference;
    BufferRecycler localBufferRecycler;
    if (isEnabled(Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING))
    {
      localSoftReference = (SoftReference)_recyclerRef.get();
      if (localSoftReference == null)
      {
        localBufferRecycler = null;
        if (localBufferRecycler == null)
        {
          localBufferRecycler = new BufferRecycler();
          _recyclerRef.set(new SoftReference(localBufferRecycler));
        }
      }
    }
    for (;;)
    {
      return localBufferRecycler;
      localBufferRecycler = (BufferRecycler)localSoftReference.get();
      break;
      localBufferRecycler = new BufferRecycler();
    }
  }
  
  protected InputStream _optimizedStreamFromURL(URL paramURL)
    throws IOException
  {
    if ("file".equals(paramURL.getProtocol()))
    {
      String str = paramURL.getHost();
      if (((str != null) && (str.length() != 0)) || (paramURL.getPath().indexOf('%') >= 0)) {}
    }
    for (Object localObject = new FileInputStream(paramURL.getPath());; localObject = paramURL.openStream()) {
      return (InputStream)localObject;
    }
  }
  
  public boolean canHandleBinaryNatively()
  {
    return false;
  }
  
  public boolean canUseCharArrays()
  {
    return true;
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema)
  {
    String str = getFormatName();
    if ((str != null) && (str.equals(paramFormatSchema.getSchemaType()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final JsonFactory configure(Feature paramFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (JsonFactory localJsonFactory = enable(paramFeature);; localJsonFactory = disable(paramFeature)) {
      return localJsonFactory;
    }
  }
  
  public final JsonFactory configure(JsonGenerator.Feature paramFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (JsonFactory localJsonFactory = enable(paramFeature);; localJsonFactory = disable(paramFeature)) {
      return localJsonFactory;
    }
  }
  
  public final JsonFactory configure(JsonParser.Feature paramFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (JsonFactory localJsonFactory = enable(paramFeature);; localJsonFactory = disable(paramFeature)) {
      return localJsonFactory;
    }
  }
  
  public JsonFactory copy()
  {
    _checkInvalidCopy(JsonFactory.class);
    return new JsonFactory(this, null);
  }
  
  public JsonGenerator createGenerator(File paramFile, JsonEncoding paramJsonEncoding)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    IOContext localIOContext = _createContext(localFileOutputStream, true);
    localIOContext.setEncoding(paramJsonEncoding);
    if (paramJsonEncoding == JsonEncoding.UTF8) {}
    for (JsonGenerator localJsonGenerator = _createUTF8Generator(_decorate(localFileOutputStream, localIOContext), localIOContext);; localJsonGenerator = _createGenerator(_decorate(_createWriter(localFileOutputStream, paramJsonEncoding, localIOContext), localIOContext), localIOContext)) {
      return localJsonGenerator;
    }
  }
  
  public JsonGenerator createGenerator(OutputStream paramOutputStream)
    throws IOException
  {
    return createGenerator(paramOutputStream, JsonEncoding.UTF8);
  }
  
  public JsonGenerator createGenerator(OutputStream paramOutputStream, JsonEncoding paramJsonEncoding)
    throws IOException
  {
    IOContext localIOContext = _createContext(paramOutputStream, false);
    localIOContext.setEncoding(paramJsonEncoding);
    if (paramJsonEncoding == JsonEncoding.UTF8) {}
    for (JsonGenerator localJsonGenerator = _createUTF8Generator(_decorate(paramOutputStream, localIOContext), localIOContext);; localJsonGenerator = _createGenerator(_decorate(_createWriter(paramOutputStream, paramJsonEncoding, localIOContext), localIOContext), localIOContext)) {
      return localJsonGenerator;
    }
  }
  
  public JsonGenerator createGenerator(Writer paramWriter)
    throws IOException
  {
    IOContext localIOContext = _createContext(paramWriter, false);
    return _createGenerator(_decorate(paramWriter, localIOContext), localIOContext);
  }
  
  @Deprecated
  public JsonGenerator createJsonGenerator(File paramFile, JsonEncoding paramJsonEncoding)
    throws IOException
  {
    return createGenerator(paramFile, paramJsonEncoding);
  }
  
  @Deprecated
  public JsonGenerator createJsonGenerator(OutputStream paramOutputStream)
    throws IOException
  {
    return createGenerator(paramOutputStream, JsonEncoding.UTF8);
  }
  
  @Deprecated
  public JsonGenerator createJsonGenerator(OutputStream paramOutputStream, JsonEncoding paramJsonEncoding)
    throws IOException
  {
    return createGenerator(paramOutputStream, paramJsonEncoding);
  }
  
  @Deprecated
  public JsonGenerator createJsonGenerator(Writer paramWriter)
    throws IOException
  {
    return createGenerator(paramWriter);
  }
  
  @Deprecated
  public JsonParser createJsonParser(File paramFile)
    throws IOException, JsonParseException
  {
    return createParser(paramFile);
  }
  
  @Deprecated
  public JsonParser createJsonParser(InputStream paramInputStream)
    throws IOException, JsonParseException
  {
    return createParser(paramInputStream);
  }
  
  @Deprecated
  public JsonParser createJsonParser(Reader paramReader)
    throws IOException, JsonParseException
  {
    return createParser(paramReader);
  }
  
  @Deprecated
  public JsonParser createJsonParser(String paramString)
    throws IOException, JsonParseException
  {
    return createParser(paramString);
  }
  
  @Deprecated
  public JsonParser createJsonParser(URL paramURL)
    throws IOException, JsonParseException
  {
    return createParser(paramURL);
  }
  
  @Deprecated
  public JsonParser createJsonParser(byte[] paramArrayOfByte)
    throws IOException, JsonParseException
  {
    return createParser(paramArrayOfByte);
  }
  
  @Deprecated
  public JsonParser createJsonParser(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonParseException
  {
    return createParser(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public JsonParser createParser(File paramFile)
    throws IOException, JsonParseException
  {
    IOContext localIOContext = _createContext(paramFile, true);
    return _createParser(_decorate(new FileInputStream(paramFile), localIOContext), localIOContext);
  }
  
  public JsonParser createParser(InputStream paramInputStream)
    throws IOException, JsonParseException
  {
    IOContext localIOContext = _createContext(paramInputStream, false);
    return _createParser(_decorate(paramInputStream, localIOContext), localIOContext);
  }
  
  public JsonParser createParser(Reader paramReader)
    throws IOException, JsonParseException
  {
    IOContext localIOContext = _createContext(paramReader, false);
    return _createParser(_decorate(paramReader, localIOContext), localIOContext);
  }
  
  public JsonParser createParser(String paramString)
    throws IOException, JsonParseException
  {
    int i = paramString.length();
    if ((this._inputDecorator != null) || (i > 32768) || (!canUseCharArrays())) {}
    IOContext localIOContext;
    char[] arrayOfChar;
    for (JsonParser localJsonParser = createParser(new StringReader(paramString));; localJsonParser = _createParser(arrayOfChar, 0, i, localIOContext, true))
    {
      return localJsonParser;
      localIOContext = _createContext(paramString, true);
      arrayOfChar = localIOContext.allocTokenBuffer(i);
      paramString.getChars(0, i, arrayOfChar, 0);
    }
  }
  
  public JsonParser createParser(URL paramURL)
    throws IOException, JsonParseException
  {
    IOContext localIOContext = _createContext(paramURL, true);
    return _createParser(_decorate(_optimizedStreamFromURL(paramURL), localIOContext), localIOContext);
  }
  
  public JsonParser createParser(byte[] paramArrayOfByte)
    throws IOException, JsonParseException
  {
    IOContext localIOContext = _createContext(paramArrayOfByte, true);
    InputStream localInputStream;
    if (this._inputDecorator != null)
    {
      localInputStream = this._inputDecorator.decorate(localIOContext, paramArrayOfByte, 0, paramArrayOfByte.length);
      if (localInputStream == null) {}
    }
    for (JsonParser localJsonParser = _createParser(localInputStream, localIOContext);; localJsonParser = _createParser(paramArrayOfByte, 0, paramArrayOfByte.length, localIOContext)) {
      return localJsonParser;
    }
  }
  
  public JsonParser createParser(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonParseException
  {
    IOContext localIOContext = _createContext(paramArrayOfByte, true);
    InputStream localInputStream;
    if (this._inputDecorator != null)
    {
      localInputStream = this._inputDecorator.decorate(localIOContext, paramArrayOfByte, paramInt1, paramInt2);
      if (localInputStream == null) {}
    }
    for (JsonParser localJsonParser = _createParser(localInputStream, localIOContext);; localJsonParser = _createParser(paramArrayOfByte, paramInt1, paramInt2, localIOContext)) {
      return localJsonParser;
    }
  }
  
  public JsonParser createParser(char[] paramArrayOfChar)
    throws IOException
  {
    return createParser(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public JsonParser createParser(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._inputDecorator != null) {}
    for (JsonParser localJsonParser = createParser(new CharArrayReader(paramArrayOfChar, paramInt1, paramInt2));; localJsonParser = _createParser(paramArrayOfChar, paramInt1, paramInt2, _createContext(paramArrayOfChar, true), false)) {
      return localJsonParser;
    }
  }
  
  public JsonFactory disable(Feature paramFeature)
  {
    this._factoryFeatures &= (0xFFFFFFFF ^ paramFeature.getMask());
    return this;
  }
  
  public JsonFactory disable(JsonGenerator.Feature paramFeature)
  {
    this._generatorFeatures &= (0xFFFFFFFF ^ paramFeature.getMask());
    return this;
  }
  
  public JsonFactory disable(JsonParser.Feature paramFeature)
  {
    this._parserFeatures &= (0xFFFFFFFF ^ paramFeature.getMask());
    return this;
  }
  
  public JsonFactory enable(Feature paramFeature)
  {
    this._factoryFeatures |= paramFeature.getMask();
    return this;
  }
  
  public JsonFactory enable(JsonGenerator.Feature paramFeature)
  {
    this._generatorFeatures |= paramFeature.getMask();
    return this;
  }
  
  public JsonFactory enable(JsonParser.Feature paramFeature)
  {
    this._parserFeatures |= paramFeature.getMask();
    return this;
  }
  
  public CharacterEscapes getCharacterEscapes()
  {
    return this._characterEscapes;
  }
  
  public ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public String getFormatName()
  {
    if (getClass() == JsonFactory.class) {}
    for (String str = "JSON";; str = null) {
      return str;
    }
  }
  
  public Class<? extends FormatFeature> getFormatReadFeatureType()
  {
    return null;
  }
  
  public Class<? extends FormatFeature> getFormatWriteFeatureType()
  {
    return null;
  }
  
  public InputDecorator getInputDecorator()
  {
    return this._inputDecorator;
  }
  
  public OutputDecorator getOutputDecorator()
  {
    return this._outputDecorator;
  }
  
  public String getRootValueSeparator()
  {
    if (this._rootValueSeparator == null) {}
    for (String str = null;; str = this._rootValueSeparator.getValue()) {
      return str;
    }
  }
  
  public MatchStrength hasFormat(InputAccessor paramInputAccessor)
    throws IOException
  {
    if (getClass() == JsonFactory.class) {}
    for (MatchStrength localMatchStrength = hasJSONFormat(paramInputAccessor);; localMatchStrength = null) {
      return localMatchStrength;
    }
  }
  
  protected MatchStrength hasJSONFormat(InputAccessor paramInputAccessor)
    throws IOException
  {
    return ByteSourceJsonBootstrapper.hasJSONFormat(paramInputAccessor);
  }
  
  public final boolean isEnabled(Feature paramFeature)
  {
    if ((this._factoryFeatures & paramFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    if ((this._generatorFeatures & paramFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isEnabled(JsonParser.Feature paramFeature)
  {
    if ((this._parserFeatures & paramFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected Object readResolve()
  {
    return new JsonFactory(this, this._objectCodec);
  }
  
  public boolean requiresCustomCodec()
  {
    return false;
  }
  
  public boolean requiresPropertyOrdering()
  {
    return false;
  }
  
  public JsonFactory setCharacterEscapes(CharacterEscapes paramCharacterEscapes)
  {
    this._characterEscapes = paramCharacterEscapes;
    return this;
  }
  
  public JsonFactory setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
    return this;
  }
  
  public JsonFactory setInputDecorator(InputDecorator paramInputDecorator)
  {
    this._inputDecorator = paramInputDecorator;
    return this;
  }
  
  public JsonFactory setOutputDecorator(OutputDecorator paramOutputDecorator)
  {
    this._outputDecorator = paramOutputDecorator;
    return this;
  }
  
  public JsonFactory setRootValueSeparator(String paramString)
  {
    if (paramString == null) {}
    for (SerializedString localSerializedString = null;; localSerializedString = new SerializedString(paramString))
    {
      this._rootValueSeparator = localSerializedString;
      return this;
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public static enum Feature
  {
    private final boolean _defaultState;
    
    static
    {
      CANONICALIZE_FIELD_NAMES = new Feature("CANONICALIZE_FIELD_NAMES", 1, true);
      FAIL_ON_SYMBOL_HASH_OVERFLOW = new Feature("FAIL_ON_SYMBOL_HASH_OVERFLOW", 2, true);
      USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING = new Feature("USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING", 3, true);
      Feature[] arrayOfFeature = new Feature[4];
      arrayOfFeature[0] = INTERN_FIELD_NAMES;
      arrayOfFeature[1] = CANONICALIZE_FIELD_NAMES;
      arrayOfFeature[2] = FAIL_ON_SYMBOL_HASH_OVERFLOW;
      arrayOfFeature[3] = USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING;
      $VALUES = arrayOfFeature;
    }
    
    private Feature(boolean paramBoolean)
    {
      this._defaultState = paramBoolean;
    }
    
    public static int collectDefaults()
    {
      int i = 0;
      for (Feature localFeature : values()) {
        if (localFeature.enabledByDefault()) {
          i |= localFeature.getMask();
        }
      }
      return i;
    }
    
    public boolean enabledByDefault()
    {
      return this._defaultState;
    }
    
    public boolean enabledIn(int paramInt)
    {
      if ((paramInt & getMask()) != 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int getMask()
    {
      return 1 << ordinal();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */