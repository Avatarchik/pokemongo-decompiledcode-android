package dagger.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

public final class SetFactory<T>
  implements Factory<Set<T>>
{
  private static final String ARGUMENTS_MUST_BE_NON_NULL = "SetFactory.create() requires its arguments to be non-null";
  private final List<Provider<Set<T>>> contributingProviders;
  
  static
  {
    if (!SetFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private SetFactory(List<Provider<Set<T>>> paramList)
  {
    this.contributingProviders = paramList;
  }
  
  public static <T> Factory<Set<T>> create(Factory<Set<T>> paramFactory)
  {
    assert (paramFactory != null) : "SetFactory.create() requires its arguments to be non-null";
    return paramFactory;
  }
  
  public static <T> Factory<Set<T>> create(Provider<Set<T>>... paramVarArgs)
  {
    assert (paramVarArgs != null) : "SetFactory.create() requires its arguments to be non-null";
    List localList = Arrays.asList(paramVarArgs);
    assert (!localList.contains(null)) : "Codegen error?  Null within provider list.";
    assert (!hasDuplicates(localList)) : "Codegen error?  Duplicates in the provider list";
    return new SetFactory(localList);
  }
  
  private static boolean hasDuplicates(List<? extends Object> paramList)
  {
    HashSet localHashSet = new HashSet(paramList);
    if (paramList.size() != localHashSet.size()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Set<T> get()
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList(this.contributingProviders.size());
    int j = 0;
    int k = this.contributingProviders.size();
    while (j < k)
    {
      Provider localProvider = (Provider)this.contributingProviders.get(j);
      Set localSet = (Set)localProvider.get();
      if (localSet == null) {
        throw new NullPointerException(localProvider + " returned null");
      }
      localArrayList.add(localSet);
      i += localSet.size();
      j++;
    }
    LinkedHashSet localLinkedHashSet = Collections.newLinkedHashSetWithExpectedSize(i);
    int m = 0;
    int n = localArrayList.size();
    while (m < n)
    {
      Iterator localIterator = ((Set)localArrayList.get(m)).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (localObject == null) {
          throw new NullPointerException("a null element was provided");
        }
        localLinkedHashSet.add(localObject);
      }
      m++;
    }
    return java.util.Collections.unmodifiableSet(localLinkedHashSet);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/SetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */