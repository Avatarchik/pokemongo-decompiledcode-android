package com.upsight.android.marketing.internal.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public final class MarketingContentModel
{
  @JsonProperty("content_id")
  String contentId;
  @JsonProperty("context")
  JsonNode context;
  @JsonProperty("presentation")
  Presentation presentation;
  @JsonProperty("url")
  String templateUrl;
  
  static MarketingContentModel from(JsonNode paramJsonNode, ObjectMapper paramObjectMapper)
    throws IOException
  {
    return (MarketingContentModel)paramObjectMapper.treeToValue(paramJsonNode, MarketingContentModel.class);
  }
  
  public String getContentID()
  {
    return this.contentId;
  }
  
  public JsonNode getContext()
  {
    return this.context;
  }
  
  public MarketingContentModel.Presentation.DialogLayout getDialogLayouts()
  {
    return this.presentation.layout;
  }
  
  public String getPresentationStyle()
  {
    return this.presentation.style;
  }
  
  public String getTemplateUrl()
  {
    return this.templateUrl;
  }
  
  public static class Presentation
  {
    public static final String STYLE_DIALOG = "dialog";
    public static final String STYLE_FULLSCREEN = "fullscreen";
    @JsonProperty("layout")
    DialogLayout layout;
    @JsonProperty("style")
    String style;
    
    public static class DialogLayout
    {
      @JsonProperty("landscape")
      public Dimensions landscape;
      @JsonProperty("portrait")
      public Dimensions portrait;
      
      public static class Dimensions
      {
        @JsonProperty("h")
        public int h;
        @JsonProperty("w")
        public int w;
        @JsonProperty("x")
        public int x;
        @JsonProperty("y")
        public int y;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/MarketingContentModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */