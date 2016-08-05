package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Iterator;
import java.util.Map.Entry;

abstract class NodeCursor
  extends JsonStreamContext
{
  protected String _currentName;
  protected Object _currentValue;
  protected final NodeCursor _parent;
  
  public NodeCursor(int paramInt, NodeCursor paramNodeCursor)
  {
    this._type = paramInt;
    this._index = -1;
    this._parent = paramNodeCursor;
  }
  
  public abstract boolean currentHasChildren();
  
  public abstract JsonNode currentNode();
  
  public abstract JsonToken endToken();
  
  public final String getCurrentName()
  {
    return this._currentName;
  }
  
  public Object getCurrentValue()
  {
    return this._currentValue;
  }
  
  public final NodeCursor getParent()
  {
    return this._parent;
  }
  
  public final NodeCursor iterateChildren()
  {
    JsonNode localJsonNode = currentNode();
    if (localJsonNode == null) {
      throw new IllegalStateException("No current node");
    }
    if (localJsonNode.isArray()) {}
    for (Object localObject = new ArrayCursor(localJsonNode, this);; localObject = new ObjectCursor(localJsonNode, this))
    {
      return (NodeCursor)localObject;
      if (!localJsonNode.isObject()) {
        break;
      }
    }
    throw new IllegalStateException("Current node of type " + localJsonNode.getClass().getName());
  }
  
  public abstract JsonToken nextToken();
  
  public abstract JsonToken nextValue();
  
  public void overrideCurrentName(String paramString)
  {
    this._currentName = paramString;
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this._currentValue = paramObject;
  }
  
  protected static final class ObjectCursor
    extends NodeCursor
  {
    protected Iterator<Map.Entry<String, JsonNode>> _contents;
    protected Map.Entry<String, JsonNode> _current;
    protected boolean _needEntry;
    
    public ObjectCursor(JsonNode paramJsonNode, NodeCursor paramNodeCursor)
    {
      super(paramNodeCursor);
      this._contents = ((ObjectNode)paramJsonNode).fields();
      this._needEntry = true;
    }
    
    public boolean currentHasChildren()
    {
      if (((ContainerNode)currentNode()).size() > 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public JsonNode currentNode()
    {
      if (this._current == null) {}
      for (JsonNode localJsonNode = null;; localJsonNode = (JsonNode)this._current.getValue()) {
        return localJsonNode;
      }
    }
    
    public JsonToken endToken()
    {
      return JsonToken.END_OBJECT;
    }
    
    public JsonToken nextToken()
    {
      JsonToken localJsonToken = null;
      if (this._needEntry) {
        if (!this._contents.hasNext())
        {
          this._currentName = null;
          this._current = null;
        }
      }
      for (;;)
      {
        return localJsonToken;
        this._needEntry = false;
        this._current = ((Map.Entry)this._contents.next());
        if (this._current == null) {}
        for (String str = null;; str = (String)this._current.getKey())
        {
          this._currentName = str;
          localJsonToken = JsonToken.FIELD_NAME;
          break;
        }
        this._needEntry = true;
        localJsonToken = ((JsonNode)this._current.getValue()).asToken();
      }
    }
    
    public JsonToken nextValue()
    {
      JsonToken localJsonToken = nextToken();
      if (localJsonToken == JsonToken.FIELD_NAME) {
        localJsonToken = nextToken();
      }
      return localJsonToken;
    }
  }
  
  protected static final class ArrayCursor
    extends NodeCursor
  {
    protected Iterator<JsonNode> _contents;
    protected JsonNode _currentNode;
    
    public ArrayCursor(JsonNode paramJsonNode, NodeCursor paramNodeCursor)
    {
      super(paramNodeCursor);
      this._contents = paramJsonNode.elements();
    }
    
    public boolean currentHasChildren()
    {
      if (((ContainerNode)currentNode()).size() > 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public JsonNode currentNode()
    {
      return this._currentNode;
    }
    
    public JsonToken endToken()
    {
      return JsonToken.END_ARRAY;
    }
    
    public JsonToken nextToken()
    {
      JsonToken localJsonToken = null;
      if (!this._contents.hasNext()) {
        this._currentNode = null;
      }
      for (;;)
      {
        return localJsonToken;
        this._currentNode = ((JsonNode)this._contents.next());
        localJsonToken = this._currentNode.asToken();
      }
    }
    
    public JsonToken nextValue()
    {
      return nextToken();
    }
  }
  
  protected static final class RootCursor
    extends NodeCursor
  {
    protected boolean _done = false;
    protected JsonNode _node;
    
    public RootCursor(JsonNode paramJsonNode, NodeCursor paramNodeCursor)
    {
      super(paramNodeCursor);
      this._node = paramJsonNode;
    }
    
    public boolean currentHasChildren()
    {
      return false;
    }
    
    public JsonNode currentNode()
    {
      return this._node;
    }
    
    public JsonToken endToken()
    {
      return null;
    }
    
    public JsonToken nextToken()
    {
      JsonToken localJsonToken = null;
      if (!this._done)
      {
        this._done = true;
        localJsonToken = this._node.asToken();
      }
      for (;;)
      {
        return localJsonToken;
        this._node = null;
      }
    }
    
    public JsonToken nextValue()
    {
      return nextToken();
    }
    
    public void overrideCurrentName(String paramString) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/NodeCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */