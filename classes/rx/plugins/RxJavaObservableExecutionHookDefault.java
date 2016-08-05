package rx.plugins;

class RxJavaObservableExecutionHookDefault
  extends RxJavaObservableExecutionHook
{
  private static RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();
  
  public static RxJavaObservableExecutionHook getInstance()
  {
    return INSTANCE;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/plugins/RxJavaObservableExecutionHookDefault.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */