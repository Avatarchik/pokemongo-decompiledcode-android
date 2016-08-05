package com.voxelbusters.nativeplugins.features.addressbook;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactDetails
{
  String displayName = "";
  ArrayList<String> emailList = new ArrayList();
  String familyName = "";
  String givenName = "";
  ArrayList<String> phoneList = new ArrayList();
  String profilePicturePath = "";
  
  public void addEmail(String paramString, int paramInt)
  {
    this.emailList.add(paramString);
  }
  
  public void addPhoneNumber(String paramString, int paramInt)
  {
    this.phoneList.add(paramString);
  }
  
  HashMap<String, Object> getHash()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("display-name", this.displayName);
    localHashMap.put("family-name", this.familyName);
    localHashMap.put("given-name", this.givenName);
    localHashMap.put("image-path", this.profilePicturePath);
    localHashMap.put("phone-number-list", this.phoneList);
    localHashMap.put("email-list", this.emailList);
    return localHashMap;
  }
  
  public void setNames(String paramString1, String paramString2, String paramString3)
  {
    this.displayName = paramString1;
    this.familyName = paramString2;
    this.givenName = paramString3;
  }
  
  public void setPicturePath(String paramString)
  {
    this.profilePicturePath = paramString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/addressbook/ContactDetails.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */