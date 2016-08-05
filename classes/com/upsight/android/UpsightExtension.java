package com.upsight.android;

public abstract class UpsightExtension<T extends BaseComponent, U>
{
  private T mExtensionComponent;
  
  public U getApi()
    throws IllegalStateException
  {
    throw new IllegalStateException("This Upsight extension supports no public API.");
  }
  
  public final T getComponent()
  {
    return this.mExtensionComponent;
  }
  
  protected void onCreate(UpsightContext paramUpsightContext) {}
  
  protected void onPostCreate(UpsightContext paramUpsightContext) {}
  
  protected abstract T onResolve(UpsightContext paramUpsightContext);
  
  final void setComponent(T paramT)
  {
    this.mExtensionComponent = paramT;
  }
  
  public static abstract interface BaseComponent<T extends UpsightExtension>
  {
    public abstract void inject(T paramT);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */