package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class SerializationConfig
  extends MapperConfigBase<SerializationFeature, SerializationConfig>
  implements Serializable
{
  protected static final PrettyPrinter DEFAULT_PRETTY_PRINTER = new DefaultPrettyPrinter();
  private static final long serialVersionUID = 1L;
  protected final PrettyPrinter _defaultPrettyPrinter;
  protected final FilterProvider _filterProvider;
  protected final int _generatorFeatures;
  protected final int _generatorFeaturesToChange;
  protected final int _serFeatures;
  protected JsonInclude.Include _serializationInclusion = null;
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramSerializationConfig, paramInt1);
    this._serFeatures = paramInt2;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramInt3;
    this._generatorFeaturesToChange = paramInt4;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, JsonInclude.Include paramInclude)
  {
    super(paramSerializationConfig);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramInclude;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, PrettyPrinter paramPrettyPrinter)
  {
    super(paramSerializationConfig);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, PropertyName paramPropertyName)
  {
    super(paramSerializationConfig, paramPropertyName);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, BaseSettings paramBaseSettings)
  {
    super(paramSerializationConfig, paramBaseSettings);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, ContextAttributes paramContextAttributes)
  {
    super(paramSerializationConfig, paramContextAttributes);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, SimpleMixInResolver paramSimpleMixInResolver)
  {
    super(paramSerializationConfig, paramSimpleMixInResolver);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, SimpleMixInResolver paramSimpleMixInResolver, RootNameLookup paramRootNameLookup)
  {
    super(paramSerializationConfig, paramSimpleMixInResolver, paramRootNameLookup);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, SubtypeResolver paramSubtypeResolver)
  {
    super(paramSerializationConfig, paramSubtypeResolver);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, FilterProvider paramFilterProvider)
  {
    super(paramSerializationConfig);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramFilterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  private SerializationConfig(SerializationConfig paramSerializationConfig, Class<?> paramClass)
  {
    super(paramSerializationConfig, paramClass);
    this._serFeatures = paramSerializationConfig._serFeatures;
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._filterProvider = paramSerializationConfig._filterProvider;
    this._defaultPrettyPrinter = paramSerializationConfig._defaultPrettyPrinter;
    this._generatorFeatures = paramSerializationConfig._generatorFeatures;
    this._generatorFeaturesToChange = paramSerializationConfig._generatorFeaturesToChange;
  }
  
  public SerializationConfig(BaseSettings paramBaseSettings, SubtypeResolver paramSubtypeResolver, SimpleMixInResolver paramSimpleMixInResolver, RootNameLookup paramRootNameLookup)
  {
    super(paramBaseSettings, paramSubtypeResolver, paramSimpleMixInResolver, paramRootNameLookup);
    this._serFeatures = collectFeatureDefaults(SerializationFeature.class);
    this._filterProvider = null;
    this._defaultPrettyPrinter = DEFAULT_PRETTY_PRINTER;
    this._generatorFeatures = 0;
    this._generatorFeaturesToChange = 0;
  }
  
  private final SerializationConfig _withBase(BaseSettings paramBaseSettings)
  {
    if (this._base == paramBaseSettings) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramBaseSettings);
    }
  }
  
  public PrettyPrinter constructDefaultPrettyPrinter()
  {
    PrettyPrinter localPrettyPrinter = this._defaultPrettyPrinter;
    if ((localPrettyPrinter instanceof Instantiatable)) {
      localPrettyPrinter = (PrettyPrinter)((Instantiatable)localPrettyPrinter).createInstance();
    }
    return localPrettyPrinter;
  }
  
  public AnnotationIntrospector getAnnotationIntrospector()
  {
    if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {}
    for (AnnotationIntrospector localAnnotationIntrospector = super.getAnnotationIntrospector();; localAnnotationIntrospector = AnnotationIntrospector.nopInstance()) {
      return localAnnotationIntrospector;
    }
  }
  
  public PrettyPrinter getDefaultPrettyPrinter()
  {
    return this._defaultPrettyPrinter;
  }
  
  public VisibilityChecker<?> getDefaultVisibilityChecker()
  {
    VisibilityChecker localVisibilityChecker = super.getDefaultVisibilityChecker();
    if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
      localVisibilityChecker = localVisibilityChecker.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
    }
    if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
      localVisibilityChecker = localVisibilityChecker.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
    }
    if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
      localVisibilityChecker = localVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
    }
    return localVisibilityChecker;
  }
  
  public FilterProvider getFilterProvider()
  {
    return this._filterProvider;
  }
  
  public final int getSerializationFeatures()
  {
    return this._serFeatures;
  }
  
  public JsonInclude.Include getSerializationInclusion()
  {
    if (this._serializationInclusion != null) {}
    for (JsonInclude.Include localInclude = this._serializationInclusion;; localInclude = JsonInclude.Include.ALWAYS) {
      return localInclude;
    }
  }
  
  public final boolean hasSerializationFeatures(int paramInt)
  {
    if ((paramInt & this._serFeatures) == paramInt) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void initialize(JsonGenerator paramJsonGenerator)
  {
    if ((SerializationFeature.INDENT_OUTPUT.enabledIn(this._serFeatures)) && (paramJsonGenerator.getPrettyPrinter() == null))
    {
      PrettyPrinter localPrettyPrinter = constructDefaultPrettyPrinter();
      if (localPrettyPrinter != null) {
        paramJsonGenerator.setPrettyPrinter(localPrettyPrinter);
      }
    }
    boolean bool = SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this._serFeatures);
    if ((this._generatorFeaturesToChange != 0) || (bool))
    {
      int i = paramJsonGenerator.getFeatureMask();
      int j = i & (0xFFFFFFFF ^ this._generatorFeaturesToChange) | this._generatorFeatures;
      if (bool) {
        j |= JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.getMask();
      }
      if (i != j) {
        paramJsonGenerator.setFeatureMask(j);
      }
    }
  }
  
  public <T extends BeanDescription> T introspect(JavaType paramJavaType)
  {
    return getClassIntrospector().forSerialization(this, paramJavaType, this);
  }
  
  public BeanDescription introspectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forDirectClassAnnotations(this, paramJavaType, this);
  }
  
  public final boolean isEnabled(JsonGenerator.Feature paramFeature, JsonFactory paramJsonFactory)
  {
    boolean bool;
    if ((paramFeature.getMask() & this._generatorFeaturesToChange) != 0) {
      if ((this._generatorFeatures & paramFeature.getMask()) != 0) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramJsonFactory.isEnabled(paramFeature);
    }
  }
  
  public final boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    if ((this._serFeatures & paramSerializationFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    return "[SerializationConfig: flags=0x" + Integer.toHexString(this._serFeatures) + "]";
  }
  
  public boolean useRootWrapping()
  {
    boolean bool;
    if (this._rootName != null) {
      if (!this._rootName.isEmpty()) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
    }
  }
  
  public SerializationConfig with(Base64Variant paramBase64Variant)
  {
    return _withBase(this._base.with(paramBase64Variant));
  }
  
  public SerializationConfig with(JsonGenerator.Feature paramFeature)
  {
    int i = this._generatorFeatures | paramFeature.getMask();
    int j = this._generatorFeaturesToChange | paramFeature.getMask();
    if ((this._generatorFeatures == i) && (this._generatorFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int k = this._mapperFeatures;
      int m = this._serFeatures;
      this = new SerializationConfig(this, k, m, i, j);
    }
  }
  
  public SerializationConfig with(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig with(MapperFeature paramMapperFeature, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
    {
      i = this._mapperFeatures | paramMapperFeature.getMask();
      if (i != this._mapperFeatures) {
        break label40;
      }
    }
    for (;;)
    {
      return this;
      i = this._mapperFeatures & (0xFFFFFFFF ^ paramMapperFeature.getMask());
      break;
      label40:
      int j = this._serFeatures;
      int k = this._generatorFeatures;
      int m = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, i, j, k, m);
    }
  }
  
  public SerializationConfig with(PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    return _withBase(this._base.withPropertyNamingStrategy(paramPropertyNamingStrategy));
  }
  
  public SerializationConfig with(SerializationFeature paramSerializationFeature)
  {
    int i = this._serFeatures | paramSerializationFeature.getMask();
    if (i == this._serFeatures) {}
    for (;;)
    {
      return this;
      int j = this._mapperFeatures;
      int k = this._generatorFeatures;
      int m = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, j, i, k, m);
    }
  }
  
  public SerializationConfig with(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    int i = this._serFeatures | paramSerializationFeature.getMask();
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i |= paramVarArgs[k].getMask();
    }
    if (i == this._serFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._generatorFeatures;
      int i1 = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, m, i, n, i1);
    }
  }
  
  public SerializationConfig with(ContextAttributes paramContextAttributes)
  {
    if (paramContextAttributes == this._attributes) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramContextAttributes);
    }
  }
  
  public SerializationConfig with(HandlerInstantiator paramHandlerInstantiator)
  {
    return _withBase(this._base.withHandlerInstantiator(paramHandlerInstantiator));
  }
  
  public SerializationConfig with(ClassIntrospector paramClassIntrospector)
  {
    return _withBase(this._base.withClassIntrospector(paramClassIntrospector));
  }
  
  public SerializationConfig with(VisibilityChecker<?> paramVisibilityChecker)
  {
    return _withBase(this._base.withVisibilityChecker(paramVisibilityChecker));
  }
  
  public SerializationConfig with(SubtypeResolver paramSubtypeResolver)
  {
    if (paramSubtypeResolver == this._subtypeResolver) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramSubtypeResolver);
    }
  }
  
  public SerializationConfig with(TypeResolverBuilder<?> paramTypeResolverBuilder)
  {
    return _withBase(this._base.withTypeResolverBuilder(paramTypeResolverBuilder));
  }
  
  public SerializationConfig with(TypeFactory paramTypeFactory)
  {
    return _withBase(this._base.withTypeFactory(paramTypeFactory));
  }
  
  public SerializationConfig with(DateFormat paramDateFormat)
  {
    SerializationConfig localSerializationConfig1 = new SerializationConfig(this, this._base.withDateFormat(paramDateFormat));
    if (paramDateFormat == null) {}
    for (SerializationConfig localSerializationConfig2 = localSerializationConfig1.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);; localSerializationConfig2 = localSerializationConfig1.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
      return localSerializationConfig2;
    }
  }
  
  public SerializationConfig with(Locale paramLocale)
  {
    return _withBase(this._base.with(paramLocale));
  }
  
  public SerializationConfig with(TimeZone paramTimeZone)
  {
    return _withBase(this._base.with(paramTimeZone));
  }
  
  public SerializationConfig with(MapperFeature... paramVarArgs)
  {
    int i = this._mapperFeatures;
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i |= paramVarArgs[k].getMask();
    }
    if (i == this._mapperFeatures) {}
    for (;;)
    {
      return this;
      int m = this._serFeatures;
      int n = this._generatorFeatures;
      int i1 = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, i, m, n, i1);
    }
  }
  
  public SerializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withAppendedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withDefaultPrettyPrinter(PrettyPrinter paramPrettyPrinter)
  {
    if (this._defaultPrettyPrinter == paramPrettyPrinter) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramPrettyPrinter);
    }
  }
  
  public SerializationConfig withFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    int i = this._generatorFeatures;
    int j = this._generatorFeaturesToChange;
    int k = paramVarArgs.length;
    for (int m = 0; m < k; m++)
    {
      int i2 = paramVarArgs[m].getMask();
      i |= i2;
      j |= i2;
    }
    if ((this._generatorFeatures == i) && (this._generatorFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int n = this._mapperFeatures;
      int i1 = this._serFeatures;
      this = new SerializationConfig(this, n, i1, i, j);
    }
  }
  
  public SerializationConfig withFeatures(SerializationFeature... paramVarArgs)
  {
    int i = this._serFeatures;
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i |= paramVarArgs[k].getMask();
    }
    if (i == this._serFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._generatorFeatures;
      int i1 = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, m, i, n, i1);
    }
  }
  
  public SerializationConfig withFilters(FilterProvider paramFilterProvider)
  {
    if (paramFilterProvider == this._filterProvider) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramFilterProvider);
    }
  }
  
  public SerializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withInsertedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withRootName(PropertyName paramPropertyName)
  {
    if (paramPropertyName == null) {
      if (this._rootName != null) {
        break label24;
      }
    }
    for (;;)
    {
      return this;
      if (!paramPropertyName.equals(this._rootName)) {
        label24:
        this = new SerializationConfig(this, paramPropertyName);
      }
    }
  }
  
  public SerializationConfig withSerializationInclusion(JsonInclude.Include paramInclude)
  {
    if (this._serializationInclusion == paramInclude) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramInclude);
    }
  }
  
  public SerializationConfig withView(Class<?> paramClass)
  {
    if (this._view == paramClass) {}
    for (;;)
    {
      return this;
      this = new SerializationConfig(this, paramClass);
    }
  }
  
  public SerializationConfig withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility)
  {
    return _withBase(this._base.withVisibility(paramPropertyAccessor, paramVisibility));
  }
  
  public SerializationConfig without(JsonGenerator.Feature paramFeature)
  {
    int i = this._generatorFeatures & (0xFFFFFFFF ^ paramFeature.getMask());
    int j = this._generatorFeaturesToChange | paramFeature.getMask();
    if ((this._generatorFeatures == i) && (this._generatorFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int k = this._mapperFeatures;
      int m = this._serFeatures;
      this = new SerializationConfig(this, k, m, i, j);
    }
  }
  
  public SerializationConfig without(SerializationFeature paramSerializationFeature)
  {
    int i = this._serFeatures & (0xFFFFFFFF ^ paramSerializationFeature.getMask());
    if (i == this._serFeatures) {}
    for (;;)
    {
      return this;
      int j = this._mapperFeatures;
      int k = this._generatorFeatures;
      int m = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, j, i, k, m);
    }
  }
  
  public SerializationConfig without(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    int i = this._serFeatures & (0xFFFFFFFF ^ paramSerializationFeature.getMask());
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i &= (0xFFFFFFFF ^ paramVarArgs[k].getMask());
    }
    if (i == this._serFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._generatorFeatures;
      int i1 = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, m, i, n, i1);
    }
  }
  
  public SerializationConfig without(MapperFeature... paramVarArgs)
  {
    int i = this._mapperFeatures;
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i &= (0xFFFFFFFF ^ paramVarArgs[k].getMask());
    }
    if (i == this._mapperFeatures) {}
    for (;;)
    {
      return this;
      int m = this._serFeatures;
      int n = this._generatorFeatures;
      int i1 = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, i, m, n, i1);
    }
  }
  
  public SerializationConfig withoutFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    int i = this._generatorFeatures;
    int j = this._generatorFeaturesToChange;
    int k = paramVarArgs.length;
    for (int m = 0; m < k; m++)
    {
      int i2 = paramVarArgs[m].getMask();
      i &= (i2 ^ 0xFFFFFFFF);
      j |= i2;
    }
    if ((this._generatorFeatures == i) && (this._generatorFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int n = this._mapperFeatures;
      int i1 = this._serFeatures;
      this = new SerializationConfig(this, n, i1, i, j);
    }
  }
  
  public SerializationConfig withoutFeatures(SerializationFeature... paramVarArgs)
  {
    int i = this._serFeatures;
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i &= (0xFFFFFFFF ^ paramVarArgs[k].getMask());
    }
    if (i == this._serFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._generatorFeatures;
      int i1 = this._generatorFeaturesToChange;
      this = new SerializationConfig(this, m, i, n, i1);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/SerializationConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */