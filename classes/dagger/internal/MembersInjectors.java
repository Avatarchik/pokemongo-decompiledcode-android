package dagger.internal;

import dagger.MembersInjector;

public final class MembersInjectors
{
  public static <T> MembersInjector<T> delegatingTo(MembersInjector<? super T> paramMembersInjector)
  {
    return paramMembersInjector;
  }
  
  public static <T> MembersInjector<T> noOp()
  {
    return NoOpMembersInjector.INSTANCE;
  }
  
  private static enum NoOpMembersInjector
    implements MembersInjector<Object>
  {
    static
    {
      NoOpMembersInjector[] arrayOfNoOpMembersInjector = new NoOpMembersInjector[1];
      arrayOfNoOpMembersInjector[0] = INSTANCE;
      $VALUES = arrayOfNoOpMembersInjector;
    }
    
    private NoOpMembersInjector() {}
    
    public void injectMembers(Object paramObject)
    {
      if (paramObject == null) {
        throw new NullPointerException();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/MembersInjectors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */