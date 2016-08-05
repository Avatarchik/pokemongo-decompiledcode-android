package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

public final class DeserializationConfig
  extends MapperConfigBase<DeserializationFeature, DeserializationConfig>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final int _deserFeatures;
  protected final JsonNodeFactory _nodeFactory;
  protected final int _parserFeatures;
  protected final int _parserFeaturesToChange;
  protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramDeserializationConfig, paramInt1);
    this._deserFeatures = paramInt2;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._parserFeatures = paramInt3;
    this._parserFeaturesToChange = paramInt4;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, PropertyName paramPropertyName)
  {
    super(paramDeserializationConfig, paramPropertyName);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, BaseSettings paramBaseSettings)
  {
    super(paramDeserializationConfig, paramBaseSettings);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, ContextAttributes paramContextAttributes)
  {
    super(paramDeserializationConfig, paramContextAttributes);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, SimpleMixInResolver paramSimpleMixInResolver)
  {
    super(paramDeserializationConfig, paramSimpleMixInResolver);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, SimpleMixInResolver paramSimpleMixInResolver, RootNameLookup paramRootNameLookup)
  {
    super(paramDeserializationConfig, paramSimpleMixInResolver, paramRootNameLookup);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, SubtypeResolver paramSubtypeResolver)
  {
    super(paramDeserializationConfig, paramSubtypeResolver);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, JsonNodeFactory paramJsonNodeFactory)
  {
    super(paramDeserializationConfig);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramJsonNodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, LinkedNode<DeserializationProblemHandler> paramLinkedNode)
  {
    super(paramDeserializationConfig);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramLinkedNode;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
  {
    super(paramDeserializationConfig, paramClass);
    this._deserFeatures = paramDeserializationConfig._deserFeatures;
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._parserFeatures = paramDeserializationConfig._parserFeatures;
    this._parserFeaturesToChange = paramDeserializationConfig._parserFeaturesToChange;
  }
  
  public DeserializationConfig(BaseSettings paramBaseSettings, SubtypeResolver paramSubtypeResolver, SimpleMixInResolver paramSimpleMixInResolver, RootNameLookup paramRootNameLookup)
  {
    super(paramBaseSettings, paramSubtypeResolver, paramSimpleMixInResolver, paramRootNameLookup);
    this._deserFeatures = collectFeatureDefaults(DeserializationFeature.class);
    this._nodeFactory = JsonNodeFactory.instance;
    this._problemHandlers = null;
    this._parserFeatures = 0;
    this._parserFeaturesToChange = 0;
  }
  
  private final DeserializationConfig _withBase(BaseSettings paramBaseSettings)
  {
    if (this._base == paramBaseSettings) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, paramBaseSettings);
    }
  }
  
  public TypeDeserializer findTypeDeserializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    AnnotatedClass localAnnotatedClass = introspectClassAnnotations(paramJavaType.getRawClass()).getClassInfo();
    TypeResolverBuilder localTypeResolverBuilder = getAnnotationIntrospector().findTypeResolver(this, localAnnotatedClass, paramJavaType);
    Collection localCollection = null;
    if (localTypeResolverBuilder == null)
    {
      localTypeResolverBuilder = getDefaultTyper(paramJavaType);
      if (localTypeResolverBuilder != null) {
        break label57;
      }
    }
    label57:
    for (TypeDeserializer localTypeDeserializer = null;; localTypeDeserializer = localTypeResolverBuilder.buildTypeDeserializer(this, paramJavaType, localCollection))
    {
      return localTypeDeserializer;
      localCollection = getSubtypeResolver().collectAndResolveSubtypesByTypeId(this, localAnnotatedClass);
    }
  }
  
  public AnnotationIntrospector getAnnotationIntrospector()
  {
    if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {}
    for (Object localObject = super.getAnnotationIntrospector();; localObject = NopAnnotationIntrospector.instance) {
      return (AnnotationIntrospector)localObject;
    }
  }
  
  protected BaseSettings getBaseSettings()
  {
    return this._base;
  }
  
  public VisibilityChecker<?> getDefaultVisibilityChecker()
  {
    VisibilityChecker localVisibilityChecker = super.getDefaultVisibilityChecker();
    if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
      localVisibilityChecker = localVisibilityChecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
    }
    if (!isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
      localVisibilityChecker = localVisibilityChecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
    }
    if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
      localVisibilityChecker = localVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
    }
    return localVisibilityChecker;
  }
  
  public final int getDeserializationFeatures()
  {
    return this._deserFeatures;
  }
  
  public final JsonNodeFactory getNodeFactory()
  {
    return this._nodeFactory;
  }
  
  public LinkedNode<DeserializationProblemHandler> getProblemHandlers()
  {
    return this._problemHandlers;
  }
  
  public final boolean hasDeserializationFeatures(int paramInt)
  {
    if ((paramInt & this._deserFeatures) == paramInt) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean hasSomeOfFeatures(int paramInt)
  {
    if ((paramInt & this._deserFeatures) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void initialize(JsonParser paramJsonParser)
  {
    if (this._parserFeaturesToChange != 0)
    {
      int i = paramJsonParser.getFeatureMask();
      int j = i & (0xFFFFFFFF ^ this._parserFeaturesToChange) | this._parserFeatures;
      if (i != j) {
        paramJsonParser.setFeatureMask(j);
      }
    }
  }
  
  public <T extends BeanDescription> T introspect(JavaType paramJavaType)
  {
    return getClassIntrospector().forDeserialization(this, paramJavaType, this);
  }
  
  public BeanDescription introspectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType)
  {
    return getClassIntrospector().forDirectClassAnnotations(this, paramJavaType, this);
  }
  
  public <T extends BeanDescription> T introspectForBuilder(JavaType paramJavaType)
  {
    return getClassIntrospector().forDeserializationWithBuilder(this, paramJavaType, this);
  }
  
  public <T extends BeanDescription> T introspectForCreation(JavaType paramJavaType)
  {
    return getClassIntrospector().forCreation(this, paramJavaType, this);
  }
  
  public final boolean isEnabled(JsonParser.Feature paramFeature, JsonFactory paramJsonFactory)
  {
    boolean bool;
    if ((paramFeature.getMask() & this._parserFeaturesToChange) != 0) {
      if ((this._parserFeatures & paramFeature.getMask()) != 0) {
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
  
  public final boolean isEnabled(DeserializationFeature paramDeserializationFeature)
  {
    if ((this._deserFeatures & paramDeserializationFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
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
      bool = isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
    }
  }
  
  public DeserializationConfig with(Base64Variant paramBase64Variant)
  {
    return _withBase(this._base.with(paramBase64Variant));
  }
  
  public DeserializationConfig with(JsonParser.Feature paramFeature)
  {
    int i = this._parserFeatures | paramFeature.getMask();
    int j = this._parserFeaturesToChange | paramFeature.getMask();
    if ((this._parserFeatures == i) && (this._parserFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int k = this._mapperFeatures;
      int m = this._deserFeatures;
      this = new DeserializationConfig(this, k, m, i, j);
    }
  }
  
  public DeserializationConfig with(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig with(DeserializationFeature paramDeserializationFeature)
  {
    int i = this._deserFeatures | paramDeserializationFeature.getMask();
    if (i == this._deserFeatures) {}
    for (;;)
    {
      return this;
      int j = this._mapperFeatures;
      int k = this._parserFeatures;
      int m = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, j, i, k, m);
    }
  }
  
  public DeserializationConfig with(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    int i = this._deserFeatures | paramDeserializationFeature.getMask();
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i |= paramVarArgs[k].getMask();
    }
    if (i == this._deserFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._parserFeatures;
      int i1 = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, m, i, n, i1);
    }
  }
  
  public DeserializationConfig with(MapperFeature paramMapperFeature, boolean paramBoolean)
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
      int j = this._deserFeatures;
      int k = this._parserFeatures;
      int m = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, i, j, k, m);
    }
  }
  
  public DeserializationConfig with(PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    return _withBase(this._base.withPropertyNamingStrategy(paramPropertyNamingStrategy));
  }
  
  public DeserializationConfig with(ContextAttributes paramContextAttributes)
  {
    if (paramContextAttributes == this._attributes) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, paramContextAttributes);
    }
  }
  
  public DeserializationConfig with(HandlerInstantiator paramHandlerInstantiator)
  {
    return _withBase(this._base.withHandlerInstantiator(paramHandlerInstantiator));
  }
  
  public DeserializationConfig with(ClassIntrospector paramClassIntrospector)
  {
    return _withBase(this._base.withClassIntrospector(paramClassIntrospector));
  }
  
  public DeserializationConfig with(VisibilityChecker<?> paramVisibilityChecker)
  {
    return _withBase(this._base.withVisibilityChecker(paramVisibilityChecker));
  }
  
  public DeserializationConfig with(SubtypeResolver paramSubtypeResolver)
  {
    if (this._subtypeResolver == paramSubtypeResolver) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, paramSubtypeResolver);
    }
  }
  
  public DeserializationConfig with(TypeResolverBuilder<?> paramTypeResolverBuilder)
  {
    return _withBase(this._base.withTypeResolverBuilder(paramTypeResolverBuilder));
  }
  
  public DeserializationConfig with(JsonNodeFactory paramJsonNodeFactory)
  {
    if (this._nodeFactory == paramJsonNodeFactory) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, paramJsonNodeFactory);
    }
  }
  
  public DeserializationConfig with(TypeFactory paramTypeFactory)
  {
    return _withBase(this._base.withTypeFactory(paramTypeFactory));
  }
  
  public DeserializationConfig with(DateFormat paramDateFormat)
  {
    return _withBase(this._base.withDateFormat(paramDateFormat));
  }
  
  public DeserializationConfig with(Locale paramLocale)
  {
    return _withBase(this._base.with(paramLocale));
  }
  
  public DeserializationConfig with(TimeZone paramTimeZone)
  {
    return _withBase(this._base.with(paramTimeZone));
  }
  
  public DeserializationConfig with(MapperFeature... paramVarArgs)
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
      int m = this._deserFeatures;
      int n = this._parserFeatures;
      int i1 = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, i, m, n, i1);
    }
  }
  
  public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withAppendedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withFeatures(JsonParser.Feature... paramVarArgs)
  {
    int i = this._parserFeatures;
    int j = this._parserFeaturesToChange;
    int k = paramVarArgs.length;
    for (int m = 0; m < k; m++)
    {
      int i2 = paramVarArgs[m].getMask();
      i |= i2;
      j |= i2;
    }
    if ((this._parserFeatures == i) && (this._parserFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int n = this._mapperFeatures;
      int i1 = this._deserFeatures;
      this = new DeserializationConfig(this, n, i1, i, j);
    }
  }
  
  public DeserializationConfig withFeatures(DeserializationFeature... paramVarArgs)
  {
    int i = this._deserFeatures;
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i |= paramVarArgs[k].getMask();
    }
    if (i == this._deserFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._parserFeatures;
      int i1 = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, m, i, n, i1);
    }
  }
  
  public DeserializationConfig withHandler(DeserializationProblemHandler paramDeserializationProblemHandler)
  {
    if (LinkedNode.contains(this._problemHandlers, paramDeserializationProblemHandler)) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, new LinkedNode(paramDeserializationProblemHandler, this._problemHandlers));
    }
  }
  
  public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    return _withBase(this._base.withInsertedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withNoProblemHandlers()
  {
    if (this._problemHandlers == null) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, (LinkedNode)null);
    }
  }
  
  public DeserializationConfig withRootName(PropertyName paramPropertyName)
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
        this = new DeserializationConfig(this, paramPropertyName);
      }
    }
  }
  
  public DeserializationConfig withView(Class<?> paramClass)
  {
    if (this._view == paramClass) {}
    for (;;)
    {
      return this;
      this = new DeserializationConfig(this, paramClass);
    }
  }
  
  public DeserializationConfig withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility)
  {
    return _withBase(this._base.withVisibility(paramPropertyAccessor, paramVisibility));
  }
  
  public DeserializationConfig without(JsonParser.Feature paramFeature)
  {
    int i = this._parserFeatures & (0xFFFFFFFF ^ paramFeature.getMask());
    int j = this._parserFeaturesToChange | paramFeature.getMask();
    if ((this._parserFeatures == i) && (this._parserFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int k = this._mapperFeatures;
      int m = this._deserFeatures;
      this = new DeserializationConfig(this, k, m, i, j);
    }
  }
  
  public DeserializationConfig without(DeserializationFeature paramDeserializationFeature)
  {
    int i = this._deserFeatures & (0xFFFFFFFF ^ paramDeserializationFeature.getMask());
    if (i == this._deserFeatures) {}
    for (;;)
    {
      return this;
      int j = this._mapperFeatures;
      int k = this._parserFeatures;
      int m = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, j, i, k, m);
    }
  }
  
  public DeserializationConfig without(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    int i = this._deserFeatures & (0xFFFFFFFF ^ paramDeserializationFeature.getMask());
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i &= (0xFFFFFFFF ^ paramVarArgs[k].getMask());
    }
    if (i == this._deserFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._parserFeatures;
      int i1 = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, m, i, n, i1);
    }
  }
  
  public DeserializationConfig without(MapperFeature... paramVarArgs)
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
      int m = this._deserFeatures;
      int n = this._parserFeatures;
      int i1 = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, i, m, n, i1);
    }
  }
  
  public DeserializationConfig withoutFeatures(JsonParser.Feature... paramVarArgs)
  {
    int i = this._parserFeatures;
    int j = this._parserFeaturesToChange;
    int k = paramVarArgs.length;
    for (int m = 0; m < k; m++)
    {
      int i2 = paramVarArgs[m].getMask();
      i &= (i2 ^ 0xFFFFFFFF);
      j |= i2;
    }
    if ((this._parserFeatures == i) && (this._parserFeaturesToChange == j)) {}
    for (;;)
    {
      return this;
      int n = this._mapperFeatures;
      int i1 = this._deserFeatures;
      this = new DeserializationConfig(this, n, i1, i, j);
    }
  }
  
  public DeserializationConfig withoutFeatures(DeserializationFeature... paramVarArgs)
  {
    int i = this._deserFeatures;
    int j = paramVarArgs.length;
    for (int k = 0; k < j; k++) {
      i &= (0xFFFFFFFF ^ paramVarArgs[k].getMask());
    }
    if (i == this._deserFeatures) {}
    for (;;)
    {
      return this;
      int m = this._mapperFeatures;
      int n = this._parserFeatures;
      int i1 = this._parserFeaturesToChange;
      this = new DeserializationConfig(this, m, i, n, i1);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/DeserializationConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */