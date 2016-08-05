package dagger.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

public final class MapProviderFactory<K, V>
  implements Factory<Map<K, Provider<V>>>
{
  private final Map<K, Provider<V>> contributingMap;
  
  private MapProviderFactory(LinkedHashMap<K, Provider<V>> paramLinkedHashMap)
  {
    this.contributingMap = java.util.Collections.unmodifiableMap(paramLinkedHashMap);
  }
  
  public static <K, V> Builder<K, V> builder(int paramInt)
  {
    return new Builder(paramInt, null);
  }
  
  public Map<K, Provider<V>> get()
  {
    return this.contributingMap;
  }
  
  public static final class Builder<K, V>
  {
    private final LinkedHashMap<K, Provider<V>> mapBuilder;
    
    private Builder(int paramInt)
    {
      this.mapBuilder = Collections.newLinkedHashMapWithExpectedSize(paramInt);
    }
    
    public MapProviderFactory<K, V> build()
    {
      return new MapProviderFactory(this.mapBuilder, null);
    }
    
    public Builder<K, V> put(K paramK, Provider<V> paramProvider)
    {
      if (paramK == null) {
        throw new NullPointerException("The key is null");
      }
      if (paramProvider == null) {
        throw new NullPointerException("The provider of the value is null");
      }
      this.mapBuilder.put(paramK, paramProvider);
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/MapProviderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */