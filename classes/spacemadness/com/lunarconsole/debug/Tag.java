package spacemadness.com.lunarconsole.debug;

public class Tag
{
  public boolean enabled;
  public final String name;
  
  public Tag(String paramString)
  {
    this(paramString, false);
  }
  
  public Tag(String paramString, boolean paramBoolean)
  {
    this.name = paramString;
    this.enabled = paramBoolean;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/debug/Tag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */