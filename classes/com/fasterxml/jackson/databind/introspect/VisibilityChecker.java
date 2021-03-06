package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public abstract interface VisibilityChecker<T extends VisibilityChecker<T>>
{
  public abstract boolean isCreatorVisible(AnnotatedMember paramAnnotatedMember);
  
  public abstract boolean isCreatorVisible(Member paramMember);
  
  public abstract boolean isFieldVisible(AnnotatedField paramAnnotatedField);
  
  public abstract boolean isFieldVisible(Field paramField);
  
  public abstract boolean isGetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  public abstract boolean isGetterVisible(Method paramMethod);
  
  public abstract boolean isIsGetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  public abstract boolean isIsGetterVisible(Method paramMethod);
  
  public abstract boolean isSetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  public abstract boolean isSetterVisible(Method paramMethod);
  
  public abstract T with(JsonAutoDetect.Visibility paramVisibility);
  
  public abstract T with(JsonAutoDetect paramJsonAutoDetect);
  
  public abstract T withCreatorVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  public abstract T withFieldVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  public abstract T withGetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  public abstract T withIsGetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  public abstract T withSetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  public abstract T withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility);
  
  @JsonAutoDetect(creatorVisibility=JsonAutoDetect.Visibility.ANY, fieldVisibility=JsonAutoDetect.Visibility.PUBLIC_ONLY, getterVisibility=JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility=JsonAutoDetect.Visibility.PUBLIC_ONLY, setterVisibility=JsonAutoDetect.Visibility.ANY)
  public static class Std
    implements VisibilityChecker<Std>, Serializable
  {
    protected static final Std DEFAULT = new Std((JsonAutoDetect)Std.class.getAnnotation(JsonAutoDetect.class));
    private static final long serialVersionUID = 1L;
    protected final JsonAutoDetect.Visibility _creatorMinLevel;
    protected final JsonAutoDetect.Visibility _fieldMinLevel;
    protected final JsonAutoDetect.Visibility _getterMinLevel;
    protected final JsonAutoDetect.Visibility _isGetterMinLevel;
    protected final JsonAutoDetect.Visibility _setterMinLevel;
    
    public Std(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT)
      {
        this._getterMinLevel = DEFAULT._getterMinLevel;
        this._isGetterMinLevel = DEFAULT._isGetterMinLevel;
        this._setterMinLevel = DEFAULT._setterMinLevel;
        this._creatorMinLevel = DEFAULT._creatorMinLevel;
      }
      for (this._fieldMinLevel = DEFAULT._fieldMinLevel;; this._fieldMinLevel = paramVisibility)
      {
        return;
        this._getterMinLevel = paramVisibility;
        this._isGetterMinLevel = paramVisibility;
        this._setterMinLevel = paramVisibility;
        this._creatorMinLevel = paramVisibility;
      }
    }
    
    public Std(JsonAutoDetect.Visibility paramVisibility1, JsonAutoDetect.Visibility paramVisibility2, JsonAutoDetect.Visibility paramVisibility3, JsonAutoDetect.Visibility paramVisibility4, JsonAutoDetect.Visibility paramVisibility5)
    {
      this._getterMinLevel = paramVisibility1;
      this._isGetterMinLevel = paramVisibility2;
      this._setterMinLevel = paramVisibility3;
      this._creatorMinLevel = paramVisibility4;
      this._fieldMinLevel = paramVisibility5;
    }
    
    public Std(JsonAutoDetect paramJsonAutoDetect)
    {
      this._getterMinLevel = paramJsonAutoDetect.getterVisibility();
      this._isGetterMinLevel = paramJsonAutoDetect.isGetterVisibility();
      this._setterMinLevel = paramJsonAutoDetect.setterVisibility();
      this._creatorMinLevel = paramJsonAutoDetect.creatorVisibility();
      this._fieldMinLevel = paramJsonAutoDetect.fieldVisibility();
    }
    
    public static Std defaultInstance()
    {
      return DEFAULT;
    }
    
    public boolean isCreatorVisible(AnnotatedMember paramAnnotatedMember)
    {
      return isCreatorVisible(paramAnnotatedMember.getMember());
    }
    
    public boolean isCreatorVisible(Member paramMember)
    {
      return this._creatorMinLevel.isVisible(paramMember);
    }
    
    public boolean isFieldVisible(AnnotatedField paramAnnotatedField)
    {
      return isFieldVisible(paramAnnotatedField.getAnnotated());
    }
    
    public boolean isFieldVisible(Field paramField)
    {
      return this._fieldMinLevel.isVisible(paramField);
    }
    
    public boolean isGetterVisible(AnnotatedMethod paramAnnotatedMethod)
    {
      return isGetterVisible(paramAnnotatedMethod.getAnnotated());
    }
    
    public boolean isGetterVisible(Method paramMethod)
    {
      return this._getterMinLevel.isVisible(paramMethod);
    }
    
    public boolean isIsGetterVisible(AnnotatedMethod paramAnnotatedMethod)
    {
      return isIsGetterVisible(paramAnnotatedMethod.getAnnotated());
    }
    
    public boolean isIsGetterVisible(Method paramMethod)
    {
      return this._isGetterMinLevel.isVisible(paramMethod);
    }
    
    public boolean isSetterVisible(AnnotatedMethod paramAnnotatedMethod)
    {
      return isSetterVisible(paramAnnotatedMethod.getAnnotated());
    }
    
    public boolean isSetterVisible(Method paramMethod)
    {
      return this._setterMinLevel.isVisible(paramMethod);
    }
    
    public String toString()
    {
      return "[Visibility:" + " getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
    }
    
    public Std with(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {}
      for (Std localStd = DEFAULT;; localStd = new Std(paramVisibility)) {
        return localStd;
      }
    }
    
    public Std with(JsonAutoDetect paramJsonAutoDetect)
    {
      Std localStd = this;
      if (paramJsonAutoDetect != null) {
        localStd = localStd.withGetterVisibility(paramJsonAutoDetect.getterVisibility()).withIsGetterVisibility(paramJsonAutoDetect.isGetterVisibility()).withSetterVisibility(paramJsonAutoDetect.setterVisibility()).withCreatorVisibility(paramJsonAutoDetect.creatorVisibility()).withFieldVisibility(paramJsonAutoDetect.fieldVisibility());
      }
      return localStd;
    }
    
    public Std withCreatorVisibility(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {
        paramVisibility = DEFAULT._creatorMinLevel;
      }
      if (this._creatorMinLevel == paramVisibility) {}
      for (;;)
      {
        return this;
        JsonAutoDetect.Visibility localVisibility1 = this._getterMinLevel;
        JsonAutoDetect.Visibility localVisibility2 = this._isGetterMinLevel;
        JsonAutoDetect.Visibility localVisibility3 = this._setterMinLevel;
        JsonAutoDetect.Visibility localVisibility4 = this._fieldMinLevel;
        this = new Std(localVisibility1, localVisibility2, localVisibility3, paramVisibility, localVisibility4);
      }
    }
    
    public Std withFieldVisibility(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {
        paramVisibility = DEFAULT._fieldMinLevel;
      }
      if (this._fieldMinLevel == paramVisibility) {}
      for (;;)
      {
        return this;
        this = new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, paramVisibility);
      }
    }
    
    public Std withGetterVisibility(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {
        paramVisibility = DEFAULT._getterMinLevel;
      }
      if (this._getterMinLevel == paramVisibility) {}
      for (;;)
      {
        return this;
        JsonAutoDetect.Visibility localVisibility1 = this._isGetterMinLevel;
        JsonAutoDetect.Visibility localVisibility2 = this._setterMinLevel;
        JsonAutoDetect.Visibility localVisibility3 = this._creatorMinLevel;
        JsonAutoDetect.Visibility localVisibility4 = this._fieldMinLevel;
        this = new Std(paramVisibility, localVisibility1, localVisibility2, localVisibility3, localVisibility4);
      }
    }
    
    public Std withIsGetterVisibility(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {
        paramVisibility = DEFAULT._isGetterMinLevel;
      }
      if (this._isGetterMinLevel == paramVisibility) {}
      for (;;)
      {
        return this;
        JsonAutoDetect.Visibility localVisibility1 = this._getterMinLevel;
        JsonAutoDetect.Visibility localVisibility2 = this._setterMinLevel;
        JsonAutoDetect.Visibility localVisibility3 = this._creatorMinLevel;
        JsonAutoDetect.Visibility localVisibility4 = this._fieldMinLevel;
        this = new Std(localVisibility1, paramVisibility, localVisibility2, localVisibility3, localVisibility4);
      }
    }
    
    public Std withSetterVisibility(JsonAutoDetect.Visibility paramVisibility)
    {
      if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {
        paramVisibility = DEFAULT._setterMinLevel;
      }
      if (this._setterMinLevel == paramVisibility) {}
      for (;;)
      {
        return this;
        JsonAutoDetect.Visibility localVisibility1 = this._getterMinLevel;
        JsonAutoDetect.Visibility localVisibility2 = this._isGetterMinLevel;
        JsonAutoDetect.Visibility localVisibility3 = this._creatorMinLevel;
        JsonAutoDetect.Visibility localVisibility4 = this._fieldMinLevel;
        this = new Std(localVisibility1, localVisibility2, paramVisibility, localVisibility3, localVisibility4);
      }
    }
    
    public Std withVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility)
    {
      switch (VisibilityChecker.1.$SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[paramPropertyAccessor.ordinal()])
      {
      }
      for (;;)
      {
        return this;
        this = withGetterVisibility(paramVisibility);
        continue;
        this = withSetterVisibility(paramVisibility);
        continue;
        this = withCreatorVisibility(paramVisibility);
        continue;
        this = withFieldVisibility(paramVisibility);
        continue;
        this = withIsGetterVisibility(paramVisibility);
        continue;
        this = with(paramVisibility);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/VisibilityChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */