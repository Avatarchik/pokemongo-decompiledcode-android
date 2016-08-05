package com.voxelbusters.nativeplugins.features.addressbook;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import com.voxelbusters.nativeplugins.NativePluginHelper;
import com.voxelbusters.nativeplugins.utilities.ApplicationUtility;
import com.voxelbusters.nativeplugins.utilities.Debug;
import com.voxelbusters.nativeplugins.utilities.FileUtility;
import com.voxelbusters.nativeplugins.utilities.JSONUtility;
import com.voxelbusters.nativeplugins.utilities.StringUtility;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddressBookHandler
{
  static final Uri CONTACT_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
  static final String CONTACT_IN_VISIBLE_GROUP = "in_visible_group";
  static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/name";
  @SuppressLint({"InlinedApi"})
  static final String DISPLAY_NAME;
  static final String EMAIL_CONTACT_ID = "contact_id";
  static final Uri EMAIL_CONTENT_URI;
  static final String EMAIL_DATA = "data1";
  static final String EMAIL_TYPE = "data2";
  static final String FAMILY_NAME = "data3";
  static final String GIVEN_NAME = "data2";
  static final String HAS_PHONE_NUMBER = "has_phone_number";
  private static AddressBookHandler INSTANCE;
  static final String PHONE_CONTACT_ID = "contact_id";
  static final Uri PHONE_CONTENT_URI;
  static final String PHONE_DISPLAY_NAME = "display_name";
  static final String PHONE_NUMBER = "data1";
  static final String PHONE_TYPE = "data2";
  static final String PHOTO_CONTENT_DIRECTORY = "photo";
  static final String PHOTO_URI;
  static final String ROOT_CONTACT_ID = "_id";
  
  static
  {
    String str1;
    if (Build.VERSION.SDK_INT >= 11)
    {
      str1 = "display_name";
      DISPLAY_NAME = str1;
      PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
      if (Build.VERSION.SDK_INT < 11) {
        break label55;
      }
    }
    label55:
    for (String str2 = "photo_uri";; str2 = "photo_id")
    {
      PHOTO_URI = str2;
      EMAIL_CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
      return;
      str1 = "display_name";
      break;
    }
  }
  
  private void addContactInternal(JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("family-name");
    String str2 = paramJSONObject.optString("given-name");
    String str3 = paramJSONObject.optString("image-path", null);
    byte[] arrayOfByte = null;
    if (!StringUtility.isNullOrEmpty(str3))
    {
      ByteArrayOutputStream localByteArrayOutputStream = FileUtility.getBitmapStream(str3);
      if (localByteArrayOutputStream != null) {
        arrayOfByte = localByteArrayOutputStream.toByteArray();
      }
    }
    JSONArray localJSONArray1 = paramJSONObject.optJSONArray("email-list");
    JSONArray localJSONArray2 = paramJSONObject.optJSONArray("phone-number-list");
    ContentResolver localContentResolver = NativePluginHelper.getCurrentContext().getContentResolver();
    ArrayList localArrayList = new ArrayList();
    int i = localArrayList.size();
    Debug.error("Test", "count : " + i);
    ContentProviderOperation.Builder localBuilder1 = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
    localBuilder1.withValue("account_type", null);
    localBuilder1.withValue("account_name", null);
    localArrayList.add(localBuilder1.build());
    ContentProviderOperation.Builder localBuilder2 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
    localBuilder2.withValueBackReference("raw_contact_id", i);
    localBuilder2.withValue("data3", str1);
    localBuilder2.withValue("data2", str2);
    localBuilder2.withValue("mimetype", "vnd.android.cursor.item/name");
    localArrayList.add(localBuilder2.build());
    if (arrayOfByte != null)
    {
      ContentProviderOperation.Builder localBuilder5 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
      localBuilder5.withValueBackReference("raw_contact_id", i);
      localBuilder5.withValue("data15", arrayOfByte);
      localBuilder5.withValue("mimetype", "vnd.android.cursor.item/photo");
      localArrayList.add(localBuilder5.build());
    }
    int m;
    int j;
    if (localJSONArray1 != null)
    {
      m = 0;
      int n = localJSONArray1.length();
      if (m < n) {}
    }
    else if (localJSONArray2 != null)
    {
      j = 0;
    }
    for (;;)
    {
      int k = localJSONArray2.length();
      Object localObject;
      if (j >= k) {
        localObject = null;
      }
      try
      {
        ContentProviderResult[] arrayOfContentProviderResult = localContentResolver.applyBatch("com.android.contacts", localArrayList);
        localObject = arrayOfContentProviderResult;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          String str6;
          String str5;
          localRemoteException.printStackTrace();
        }
      }
      catch (OperationApplicationException localOperationApplicationException)
      {
        for (;;)
        {
          localOperationApplicationException.printStackTrace();
          continue;
          String str4 = "true";
        }
      }
      if (localObject == null)
      {
        str4 = "false";
        NativePluginHelper.sendMessage("ABAddNewContactFinished", str4);
        return;
        str6 = null;
        try
        {
          str6 = (String)localJSONArray1.get(m);
          if (!StringUtility.isNullOrEmpty(str6))
          {
            ContentProviderOperation.Builder localBuilder4 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            localBuilder4.withValueBackReference("raw_contact_id", i);
            localBuilder4.withValue("data1", str6);
            localBuilder4.withValue("data2", Integer.valueOf(3));
            localBuilder4.withValue("mimetype", "vnd.android.cursor.item/email_v2");
            localArrayList.add(localBuilder4.build());
          }
          m++;
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            localJSONException2.printStackTrace();
          }
        }
        str5 = null;
        try
        {
          str5 = (String)localJSONArray2.get(j);
          if (!StringUtility.isNullOrEmpty(str5))
          {
            ContentProviderOperation.Builder localBuilder3 = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            localBuilder3.withValueBackReference("raw_contact_id", i);
            localBuilder3.withValue("data1", str5);
            localBuilder3.withValue("data2", Integer.valueOf(7));
            localBuilder3.withValue("mimetype", "vnd.android.cursor.item/phone_v2");
            localArrayList.add(localBuilder3.build());
          }
          j++;
        }
        catch (JSONException localJSONException1)
        {
          for (;;)
          {
            localJSONException1.printStackTrace();
          }
        }
      }
    }
  }
  
  public static AddressBookHandler getInstance()
  {
    if (INSTANCE == null) {
      INSTANCE = new AddressBookHandler();
    }
    return INSTANCE;
  }
  
  /* Error */
  private void readContactsInBackground()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 154	com/voxelbusters/nativeplugins/NativePluginHelper:getCurrentContext	()Landroid/content/Context;
    //   5: astore 9
    //   7: aload 9
    //   9: invokevirtual 160	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   12: astore 10
    //   14: aload 10
    //   16: getstatic 63	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:CONTACT_CONTENT_URI	Landroid/net/Uri;
    //   19: aconst_null
    //   20: ldc_w 284
    //   23: aconst_null
    //   24: new 171	java/lang/StringBuilder
    //   27: dup
    //   28: getstatic 71	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:DISPLAY_NAME	Ljava/lang/String;
    //   31: invokestatic 287	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   34: invokespecial 176	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   37: ldc_w 289
    //   40: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: invokevirtual 296	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_1
    //   50: aload_1
    //   51: invokeinterface 302 1 0
    //   56: istore 11
    //   58: new 162	java/util/ArrayList
    //   61: dup
    //   62: invokespecial 163	java/util/ArrayList:<init>	()V
    //   65: astore 12
    //   67: iload 11
    //   69: ifeq +358 -> 427
    //   72: new 304	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails
    //   75: dup
    //   76: invokespecial 305	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails:<init>	()V
    //   79: astore 13
    //   81: aload_0
    //   82: aload_1
    //   83: ldc 54
    //   85: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   88: astore 14
    //   90: iconst_2
    //   91: anewarray 258	java/lang/String
    //   94: astore 15
    //   96: aload 15
    //   98: iconst_0
    //   99: ldc 17
    //   101: aastore
    //   102: aload 15
    //   104: iconst_1
    //   105: aload 14
    //   107: aastore
    //   108: aload 10
    //   110: getstatic 220	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   113: aconst_null
    //   114: ldc_w 311
    //   117: aload 15
    //   119: ldc 31
    //   121: invokevirtual 296	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   124: astore 16
    //   126: aload 16
    //   128: invokeinterface 302 1 0
    //   133: pop
    //   134: aload_0
    //   135: aload 16
    //   137: getstatic 71	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:DISPLAY_NAME	Ljava/lang/String;
    //   140: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   143: astore 18
    //   145: aload 13
    //   147: aload 18
    //   149: aload_0
    //   150: aload 16
    //   152: ldc 34
    //   154: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   157: aload_0
    //   158: aload 16
    //   160: ldc 31
    //   162: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   165: invokevirtual 315	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails:setNames	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   168: aload 16
    //   170: invokeinterface 318 1 0
    //   175: aload_0
    //   176: aload_1
    //   177: ldc 38
    //   179: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   182: invokestatic 322	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   185: ifle +304 -> 489
    //   188: iconst_1
    //   189: istore 19
    //   191: iload 19
    //   193: ifeq +51 -> 244
    //   196: aload 10
    //   198: getstatic 76	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:PHONE_CONTENT_URI	Landroid/net/Uri;
    //   201: aconst_null
    //   202: new 171	java/lang/StringBuilder
    //   205: dup
    //   206: ldc_w 324
    //   209: invokespecial 176	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   212: aload 14
    //   214: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: aconst_null
    //   221: aconst_null
    //   222: invokevirtual 296	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   225: astore 28
    //   227: aload 28
    //   229: invokeinterface 327 1 0
    //   234: ifne +261 -> 495
    //   237: aload 28
    //   239: invokeinterface 318 1 0
    //   244: getstatic 80	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:PHOTO_URI	Ljava/lang/String;
    //   247: astore 20
    //   249: aload_0
    //   250: aload_1
    //   251: aload 20
    //   253: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   256: astore 21
    //   258: aload 21
    //   260: invokestatic 128	com/voxelbusters/nativeplugins/utilities/StringUtility:isNullOrEmpty	(Ljava/lang/String;)Z
    //   263: ifne +94 -> 357
    //   266: aload 9
    //   268: aload 21
    //   270: invokestatic 333	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   273: ldc_w 335
    //   276: aload 14
    //   278: invokestatic 339	com/voxelbusters/nativeplugins/utilities/FileUtility:getSavedLocalFileFromUri	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   281: astore 27
    //   283: aload 27
    //   285: ifnonnull +60 -> 345
    //   288: ldc_w 341
    //   291: ldc_w 343
    //   294: invokestatic 190	com/voxelbusters/nativeplugins/utilities/Debug:error	(Ljava/lang/String;Ljava/lang/String;)V
    //   297: ldc_w 341
    //   300: new 171	java/lang/StringBuilder
    //   303: dup
    //   304: ldc_w 345
    //   307: invokespecial 176	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   310: aload 18
    //   312: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokestatic 348	com/voxelbusters/nativeplugins/utilities/Debug:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   321: ldc_w 341
    //   324: new 171	java/lang/StringBuilder
    //   327: dup
    //   328: ldc_w 350
    //   331: invokespecial 176	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   334: aload 21
    //   336: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   342: invokestatic 348	com/voxelbusters/nativeplugins/utilities/Debug:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   345: aload 27
    //   347: ifnull +10 -> 357
    //   350: aload 13
    //   352: aload 27
    //   354: invokevirtual 353	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails:setPicturePath	(Ljava/lang/String;)V
    //   357: getstatic 85	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:EMAIL_CONTENT_URI	Landroid/net/Uri;
    //   360: astore 22
    //   362: iconst_1
    //   363: anewarray 258	java/lang/String
    //   366: astore 23
    //   368: aload 23
    //   370: iconst_0
    //   371: aload 14
    //   373: aastore
    //   374: aload 10
    //   376: aload 22
    //   378: aconst_null
    //   379: ldc_w 355
    //   382: aload 23
    //   384: aconst_null
    //   385: invokevirtual 296	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   388: astore 24
    //   390: aload 24
    //   392: invokeinterface 327 1 0
    //   397: ifne +158 -> 555
    //   400: aload 24
    //   402: invokeinterface 318 1 0
    //   407: aload 12
    //   409: aload 13
    //   411: invokevirtual 359	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails:getHash	()Ljava/util/HashMap;
    //   414: invokevirtual 217	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   417: pop
    //   418: aload_1
    //   419: invokeinterface 327 1 0
    //   424: ifne -352 -> 72
    //   427: invokestatic 364	java/lang/System:gc	()V
    //   430: ldc_w 366
    //   433: astore 4
    //   435: aload_1
    //   436: ifnull +9 -> 445
    //   439: aload_1
    //   440: invokeinterface 318 1 0
    //   445: aload 12
    //   447: astore 5
    //   449: new 368	java/util/HashMap
    //   452: dup
    //   453: invokespecial 369	java/util/HashMap:<init>	()V
    //   456: astore 6
    //   458: aload 6
    //   460: ldc_w 371
    //   463: aload 4
    //   465: invokevirtual 375	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   468: pop
    //   469: aload 6
    //   471: ldc_w 377
    //   474: aload 5
    //   476: invokevirtual 375	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   479: pop
    //   480: ldc_w 379
    //   483: aload 6
    //   485: invokestatic 382	com/voxelbusters/nativeplugins/NativePluginHelper:sendMessage	(Ljava/lang/String;Ljava/util/HashMap;)V
    //   488: return
    //   489: iconst_0
    //   490: istore 19
    //   492: goto -301 -> 191
    //   495: aload 13
    //   497: aload_0
    //   498: aload 28
    //   500: ldc 28
    //   502: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   505: aload_0
    //   506: aload 28
    //   508: ldc 31
    //   510: invokevirtual 386	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorInt	(Landroid/database/Cursor;Ljava/lang/String;)I
    //   513: invokevirtual 390	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails:addPhoneNumber	(Ljava/lang/String;I)V
    //   516: goto -289 -> 227
    //   519: astore_2
    //   520: aload_2
    //   521: invokevirtual 391	java/lang/Exception:printStackTrace	()V
    //   524: ldc_w 341
    //   527: aload_2
    //   528: invokevirtual 394	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   531: invokestatic 190	com/voxelbusters/nativeplugins/utilities/Debug:error	(Ljava/lang/String;Ljava/lang/String;)V
    //   534: ldc_w 396
    //   537: astore 4
    //   539: aconst_null
    //   540: astore 5
    //   542: aload_1
    //   543: ifnull -94 -> 449
    //   546: aload_1
    //   547: invokeinterface 318 1 0
    //   552: goto -103 -> 449
    //   555: aload_0
    //   556: aload 24
    //   558: ldc 28
    //   560: invokevirtual 309	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorString	(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;
    //   563: astore 25
    //   565: aload 25
    //   567: ifnull -177 -> 390
    //   570: aload 13
    //   572: aload 25
    //   574: aload_0
    //   575: aload 24
    //   577: ldc 31
    //   579: invokevirtual 386	com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler:getCursorInt	(Landroid/database/Cursor;Ljava/lang/String;)I
    //   582: invokevirtual 399	com/voxelbusters/nativeplugins/features/addressbook/ContactDetails:addEmail	(Ljava/lang/String;I)V
    //   585: goto -195 -> 390
    //   588: astore_3
    //   589: aload_1
    //   590: ifnull +9 -> 599
    //   593: aload_1
    //   594: invokeinterface 318 1 0
    //   599: aload_3
    //   600: athrow
    //   601: astore_3
    //   602: goto -13 -> 589
    //   605: astore_2
    //   606: goto -86 -> 520
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	609	0	this	AddressBookHandler
    //   1	593	1	localCursor1	Cursor
    //   519	9	2	localException1	Exception
    //   605	1	2	localException2	Exception
    //   588	12	3	localObject1	Object
    //   601	1	3	localObject2	Object
    //   433	105	4	str1	String
    //   447	94	5	localArrayList1	ArrayList
    //   456	28	6	localHashMap	java.util.HashMap
    //   5	262	9	localContext	Context
    //   12	363	10	localContentResolver	ContentResolver
    //   56	12	11	bool	boolean
    //   65	381	12	localArrayList2	ArrayList
    //   79	492	13	localContactDetails	ContactDetails
    //   88	284	14	str2	String
    //   94	24	15	arrayOfString1	String[]
    //   124	45	16	localCursor2	Cursor
    //   143	168	18	str3	String
    //   189	302	19	i	int
    //   247	5	20	str4	String
    //   256	79	21	str5	String
    //   360	17	22	localUri	Uri
    //   366	17	23	arrayOfString2	String[]
    //   388	188	24	localCursor3	Cursor
    //   563	10	25	str6	String
    //   281	72	27	str7	String
    //   225	282	28	localCursor4	Cursor
    // Exception table:
    //   from	to	target	type
    //   72	435	519	java/lang/Exception
    //   495	516	519	java/lang/Exception
    //   555	585	519	java/lang/Exception
    //   72	435	588	finally
    //   495	516	588	finally
    //   555	585	588	finally
    //   2	67	601	finally
    //   520	539	601	finally
    //   2	67	605	java/lang/Exception
  }
  
  public void addContact(String paramString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        AddressBookHandler.this.addContactInternal(this.val$json);
      }
    }).start();
  }
  
  int getCursorInt(Cursor paramCursor, String paramString)
  {
    return paramCursor.getInt(paramCursor.getColumnIndex(paramString));
  }
  
  String getCursorString(Cursor paramCursor, String paramString)
  {
    Object localObject = null;
    try
    {
      int i = paramCursor.getColumnIndex(paramString);
      if (i != -1)
      {
        String str = paramCursor.getString(i);
        localObject = str;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    return (String)localObject;
  }
  
  public boolean isAuthorized()
  {
    return ApplicationUtility.hasPermission(NativePluginHelper.getCurrentContext(), "android.permission.READ_CONTACTS");
  }
  
  public void readContacts()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        AddressBookHandler.this.readContactsInBackground();
      }
    }).start();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/addressbook/AddressBookHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */