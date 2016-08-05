package org.apache.commons.io.input;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.ByteOrderMark;

public class XmlStreamReader
  extends Reader
{
  private static final ByteOrderMark[] BOMS;
  private static final int BUFFER_SIZE = 4096;
  private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=[\"']?([.[^; \"']]*)[\"']?");
  private static final String EBCDIC = "CP1047";
  public static final Pattern ENCODING_PATTERN = Pattern.compile("<\\?xml.*encoding[\\s]*=[\\s]*((?:\".[^\"]*\")|(?:'.[^']*'))", 8);
  private static final String HTTP_EX_1 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be NULL";
  private static final String HTTP_EX_2 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch";
  private static final String HTTP_EX_3 = "Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Invalid MIME";
  private static final String RAW_EX_1 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch";
  private static final String RAW_EX_2 = "Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM";
  private static final String US_ASCII = "US-ASCII";
  private static final String UTF_16 = "UTF-16";
  private static final String UTF_16BE = "UTF-16BE";
  private static final String UTF_16LE = "UTF-16LE";
  private static final String UTF_32 = "UTF-32";
  private static final String UTF_32BE = "UTF-32BE";
  private static final String UTF_32LE = "UTF-32LE";
  private static final String UTF_8 = "UTF-8";
  private static final ByteOrderMark[] XML_GUESS_BYTES;
  private final String defaultEncoding;
  private final String encoding;
  private final Reader reader;
  
  static
  {
    ByteOrderMark[] arrayOfByteOrderMark1 = new ByteOrderMark[5];
    arrayOfByteOrderMark1[0] = ByteOrderMark.UTF_8;
    arrayOfByteOrderMark1[1] = ByteOrderMark.UTF_16BE;
    arrayOfByteOrderMark1[2] = ByteOrderMark.UTF_16LE;
    arrayOfByteOrderMark1[3] = ByteOrderMark.UTF_32BE;
    arrayOfByteOrderMark1[4] = ByteOrderMark.UTF_32LE;
    BOMS = arrayOfByteOrderMark1;
    ByteOrderMark[] arrayOfByteOrderMark2 = new ByteOrderMark[6];
    int[] arrayOfInt1 = new int[4];
    arrayOfInt1[0] = 60;
    arrayOfInt1[1] = 63;
    arrayOfInt1[2] = 120;
    arrayOfInt1[3] = 109;
    arrayOfByteOrderMark2[0] = new ByteOrderMark("UTF-8", arrayOfInt1);
    int[] arrayOfInt2 = new int[4];
    arrayOfInt2[0] = 0;
    arrayOfInt2[1] = 60;
    arrayOfInt2[2] = 0;
    arrayOfInt2[3] = 63;
    arrayOfByteOrderMark2[1] = new ByteOrderMark("UTF-16BE", arrayOfInt2);
    int[] arrayOfInt3 = new int[4];
    arrayOfInt3[0] = 60;
    arrayOfInt3[1] = 0;
    arrayOfInt3[2] = 63;
    arrayOfInt3[3] = 0;
    arrayOfByteOrderMark2[2] = new ByteOrderMark("UTF-16LE", arrayOfInt3);
    int[] arrayOfInt4 = new int[16];
    arrayOfInt4[0] = 0;
    arrayOfInt4[1] = 0;
    arrayOfInt4[2] = 0;
    arrayOfInt4[3] = 60;
    arrayOfInt4[4] = 0;
    arrayOfInt4[5] = 0;
    arrayOfInt4[6] = 0;
    arrayOfInt4[7] = 63;
    arrayOfInt4[8] = 0;
    arrayOfInt4[9] = 0;
    arrayOfInt4[10] = 0;
    arrayOfInt4[11] = 120;
    arrayOfInt4[12] = 0;
    arrayOfInt4[13] = 0;
    arrayOfInt4[14] = 0;
    arrayOfInt4[15] = 109;
    arrayOfByteOrderMark2[3] = new ByteOrderMark("UTF-32BE", arrayOfInt4);
    int[] arrayOfInt5 = new int[16];
    arrayOfInt5[0] = 60;
    arrayOfInt5[1] = 0;
    arrayOfInt5[2] = 0;
    arrayOfInt5[3] = 0;
    arrayOfInt5[4] = 63;
    arrayOfInt5[5] = 0;
    arrayOfInt5[6] = 0;
    arrayOfInt5[7] = 0;
    arrayOfInt5[8] = 120;
    arrayOfInt5[9] = 0;
    arrayOfInt5[10] = 0;
    arrayOfInt5[11] = 0;
    arrayOfInt5[12] = 109;
    arrayOfInt5[13] = 0;
    arrayOfInt5[14] = 0;
    arrayOfInt5[15] = 0;
    arrayOfByteOrderMark2[4] = new ByteOrderMark("UTF-32LE", arrayOfInt5);
    int[] arrayOfInt6 = new int[4];
    arrayOfInt6[0] = 76;
    arrayOfInt6[1] = 111;
    arrayOfInt6[2] = 167;
    arrayOfInt6[3] = 148;
    arrayOfByteOrderMark2[5] = new ByteOrderMark("CP1047", arrayOfInt6);
    XML_GUESS_BYTES = arrayOfByteOrderMark2;
  }
  
  public XmlStreamReader(File paramFile)
    throws IOException
  {
    this(new FileInputStream(paramFile));
  }
  
  public XmlStreamReader(InputStream paramInputStream)
    throws IOException
  {
    this(paramInputStream, true);
  }
  
  public XmlStreamReader(InputStream paramInputStream, String paramString)
    throws IOException
  {
    this(paramInputStream, paramString, true);
  }
  
  public XmlStreamReader(InputStream paramInputStream, String paramString, boolean paramBoolean)
    throws IOException
  {
    this(paramInputStream, paramString, paramBoolean, null);
  }
  
  public XmlStreamReader(InputStream paramInputStream, String paramString1, boolean paramBoolean, String paramString2)
    throws IOException
  {
    this.defaultEncoding = paramString2;
    BOMInputStream localBOMInputStream1 = new BOMInputStream(new BufferedInputStream(paramInputStream, 4096), false, BOMS);
    BOMInputStream localBOMInputStream2 = new BOMInputStream(localBOMInputStream1, true, XML_GUESS_BYTES);
    this.encoding = doHttpStream(localBOMInputStream1, localBOMInputStream2, paramString1, paramBoolean);
    this.reader = new InputStreamReader(localBOMInputStream2, this.encoding);
  }
  
  public XmlStreamReader(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    this(paramInputStream, paramBoolean, null);
  }
  
  public XmlStreamReader(InputStream paramInputStream, boolean paramBoolean, String paramString)
    throws IOException
  {
    this.defaultEncoding = paramString;
    BOMInputStream localBOMInputStream1 = new BOMInputStream(new BufferedInputStream(paramInputStream, 4096), false, BOMS);
    BOMInputStream localBOMInputStream2 = new BOMInputStream(localBOMInputStream1, true, XML_GUESS_BYTES);
    this.encoding = doRawStream(localBOMInputStream1, localBOMInputStream2, paramBoolean);
    this.reader = new InputStreamReader(localBOMInputStream2, this.encoding);
  }
  
  public XmlStreamReader(URL paramURL)
    throws IOException
  {
    this(paramURL.openConnection(), null);
  }
  
  public XmlStreamReader(URLConnection paramURLConnection, String paramString)
    throws IOException
  {
    this.defaultEncoding = paramString;
    String str = paramURLConnection.getContentType();
    BOMInputStream localBOMInputStream1 = new BOMInputStream(new BufferedInputStream(paramURLConnection.getInputStream(), 4096), false, BOMS);
    BOMInputStream localBOMInputStream2 = new BOMInputStream(localBOMInputStream1, true, XML_GUESS_BYTES);
    if (((paramURLConnection instanceof HttpURLConnection)) || (str != null)) {}
    for (this.encoding = doHttpStream(localBOMInputStream1, localBOMInputStream2, str, true);; this.encoding = doRawStream(localBOMInputStream1, localBOMInputStream2, true))
    {
      this.reader = new InputStreamReader(localBOMInputStream2, this.encoding);
      return;
    }
  }
  
  private String doHttpStream(BOMInputStream paramBOMInputStream1, BOMInputStream paramBOMInputStream2, String paramString, boolean paramBoolean)
    throws IOException
  {
    String str1 = paramBOMInputStream1.getBOMCharsetName();
    String str2 = paramBOMInputStream2.getBOMCharsetName();
    String str3 = getXmlProlog(paramBOMInputStream2, str2);
    try
    {
      String str5 = calculateHttpEncoding(paramString, str1, str2, str3, paramBoolean);
      str4 = str5;
    }
    catch (XmlStreamReaderException localXmlStreamReaderException)
    {
      String str4;
      while (paramBoolean) {
        str4 = doLenientDetection(paramString, localXmlStreamReaderException);
      }
      throw localXmlStreamReaderException;
    }
    return str4;
  }
  
  private String doLenientDetection(String paramString, XmlStreamReaderException paramXmlStreamReaderException)
    throws IOException
  {
    String str3;
    if ((paramString != null) && (paramString.startsWith("text/html")))
    {
      String str2 = paramString.substring("text/html".length());
      str3 = "text/xml" + str2;
    }
    do
    {
      try
      {
        String str4 = calculateHttpEncoding(str3, paramXmlStreamReaderException.getBomEncoding(), paramXmlStreamReaderException.getXmlGuessEncoding(), paramXmlStreamReaderException.getXmlEncoding(), true);
        str1 = str4;
        return str1;
      }
      catch (XmlStreamReaderException localXmlStreamReaderException)
      {
        paramXmlStreamReaderException = localXmlStreamReaderException;
      }
      str1 = paramXmlStreamReaderException.getXmlEncoding();
      if (str1 == null) {
        str1 = paramXmlStreamReaderException.getContentTypeEncoding();
      }
    } while (str1 != null);
    if (this.defaultEncoding == null) {}
    for (String str1 = "UTF-8";; str1 = this.defaultEncoding) {
      break;
    }
  }
  
  private String doRawStream(BOMInputStream paramBOMInputStream1, BOMInputStream paramBOMInputStream2, boolean paramBoolean)
    throws IOException
  {
    String str1 = paramBOMInputStream1.getBOMCharsetName();
    String str2 = paramBOMInputStream2.getBOMCharsetName();
    String str3 = getXmlProlog(paramBOMInputStream2, str2);
    try
    {
      String str5 = calculateRawEncoding(str1, str2, str3);
      str4 = str5;
    }
    catch (XmlStreamReaderException localXmlStreamReaderException)
    {
      String str4;
      while (paramBoolean) {
        str4 = doLenientDetection(null, localXmlStreamReaderException);
      }
      throw localXmlStreamReaderException;
    }
    return str4;
  }
  
  static String getContentTypeEncoding(String paramString)
  {
    String str1 = null;
    String str3;
    if (paramString != null)
    {
      int i = paramString.indexOf(";");
      if (i > -1)
      {
        String str2 = paramString.substring(i + 1);
        Matcher localMatcher = CHARSET_PATTERN.matcher(str2);
        if (!localMatcher.find()) {
          break label68;
        }
        str3 = localMatcher.group(1);
        if (str3 == null) {
          break label74;
        }
      }
    }
    label68:
    label74:
    for (str1 = str3.toUpperCase(Locale.US);; str1 = null)
    {
      return str1;
      str3 = null;
      break;
    }
  }
  
  static String getContentTypeMime(String paramString)
  {
    String str1 = null;
    int i;
    if (paramString != null)
    {
      i = paramString.indexOf(";");
      if (i < 0) {
        break label31;
      }
    }
    label31:
    for (String str2 = paramString.substring(0, i);; str2 = paramString)
    {
      str1 = str2.trim();
      return str1;
    }
  }
  
  private static String getXmlProlog(InputStream paramInputStream, String paramString)
    throws IOException
  {
    String str1 = null;
    if (paramString != null)
    {
      byte[] arrayOfByte = new byte['က'];
      paramInputStream.mark(4096);
      int i = 0;
      int j = 4096;
      int k = paramInputStream.read(arrayOfByte, 0, j);
      int m = -1;
      String str2 = null;
      while ((k != -1) && (m == -1) && (i < 4096))
      {
        i += k;
        j -= k;
        k = paramInputStream.read(arrayOfByte, i, j);
        str2 = new String(arrayOfByte, 0, i, paramString);
        m = str2.indexOf('>');
      }
      if (m == -1)
      {
        if (k == -1) {
          throw new IOException("Unexpected end of XML stream");
        }
        throw new IOException("XML prolog or ROOT element not found on first " + i + " bytes");
      }
      if (i > 0)
      {
        paramInputStream.reset();
        BufferedReader localBufferedReader = new BufferedReader(new StringReader(str2.substring(0, m + 1)));
        StringBuffer localStringBuffer = new StringBuffer();
        for (String str3 = localBufferedReader.readLine(); str3 != null; str3 = localBufferedReader.readLine()) {
          localStringBuffer.append(str3);
        }
        Matcher localMatcher = ENCODING_PATTERN.matcher(localStringBuffer);
        if (localMatcher.find())
        {
          String str4 = localMatcher.group(1).toUpperCase();
          str1 = str4.substring(1, -1 + str4.length());
        }
      }
    }
    return str1;
  }
  
  static boolean isAppXml(String paramString)
  {
    if ((paramString != null) && ((paramString.equals("application/xml")) || (paramString.equals("application/xml-dtd")) || (paramString.equals("application/xml-external-parsed-entity")) || ((paramString.startsWith("application/")) && (paramString.endsWith("+xml"))))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static boolean isTextXml(String paramString)
  {
    if ((paramString != null) && ((paramString.equals("text/xml")) || (paramString.equals("text/xml-external-parsed-entity")) || ((paramString.startsWith("text/")) && (paramString.endsWith("+xml"))))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  String calculateHttpEncoding(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
    throws IOException
  {
    Object localObject;
    if ((paramBoolean) && (paramString4 != null)) {
      localObject = paramString4;
    }
    String str1;
    for (;;)
    {
      return (String)localObject;
      str1 = getContentTypeMime(paramString1);
      localObject = getContentTypeEncoding(paramString1);
      boolean bool1 = isAppXml(str1);
      boolean bool2 = isTextXml(str1);
      if ((!bool1) && (!bool2))
      {
        Object[] arrayOfObject5 = new Object[5];
        arrayOfObject5[0] = str1;
        arrayOfObject5[1] = localObject;
        arrayOfObject5[2] = paramString2;
        arrayOfObject5[3] = paramString3;
        arrayOfObject5[4] = paramString4;
        throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Invalid MIME", arrayOfObject5), str1, (String)localObject, paramString2, paramString3, paramString4);
      }
      if (localObject == null)
      {
        if (bool1)
        {
          localObject = calculateRawEncoding(paramString2, paramString3, paramString4);
        }
        else
        {
          if (this.defaultEncoding == null) {}
          for (String str2 = "US-ASCII";; str2 = this.defaultEncoding)
          {
            localObject = str2;
            break;
          }
        }
      }
      else if ((((String)localObject).equals("UTF-16BE")) || (((String)localObject).equals("UTF-16LE")))
      {
        if (paramString2 != null)
        {
          Object[] arrayOfObject1 = new Object[5];
          arrayOfObject1[0] = str1;
          arrayOfObject1[1] = localObject;
          arrayOfObject1[2] = paramString2;
          arrayOfObject1[3] = paramString3;
          arrayOfObject1[4] = paramString4;
          throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be NULL", arrayOfObject1), str1, (String)localObject, paramString2, paramString3, paramString4);
        }
      }
      else if (((String)localObject).equals("UTF-16"))
      {
        if ((paramString2 != null) && (paramString2.startsWith("UTF-16")))
        {
          localObject = paramString2;
        }
        else
        {
          Object[] arrayOfObject4 = new Object[5];
          arrayOfObject4[0] = str1;
          arrayOfObject4[1] = localObject;
          arrayOfObject4[2] = paramString2;
          arrayOfObject4[3] = paramString3;
          arrayOfObject4[4] = paramString4;
          throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch", arrayOfObject4), str1, (String)localObject, paramString2, paramString3, paramString4);
        }
      }
      else if ((((String)localObject).equals("UTF-32BE")) || (((String)localObject).equals("UTF-32LE")))
      {
        if (paramString2 != null)
        {
          Object[] arrayOfObject2 = new Object[5];
          arrayOfObject2[0] = str1;
          arrayOfObject2[1] = localObject;
          arrayOfObject2[2] = paramString2;
          arrayOfObject2[3] = paramString3;
          arrayOfObject2[4] = paramString4;
          throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be NULL", arrayOfObject2), str1, (String)localObject, paramString2, paramString3, paramString4);
        }
      }
      else if (((String)localObject).equals("UTF-32"))
      {
        if ((paramString2 == null) || (!paramString2.startsWith("UTF-32"))) {
          break;
        }
        localObject = paramString2;
      }
    }
    Object[] arrayOfObject3 = new Object[5];
    arrayOfObject3[0] = str1;
    arrayOfObject3[1] = localObject;
    arrayOfObject3[2] = paramString2;
    arrayOfObject3[3] = paramString3;
    arrayOfObject3[4] = paramString4;
    throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch", arrayOfObject3), str1, (String)localObject, paramString2, paramString3, paramString4);
  }
  
  String calculateRawEncoding(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    String str;
    if (paramString1 == null) {
      if ((paramString2 == null) || (paramString3 == null)) {
        if (this.defaultEncoding == null)
        {
          str = "UTF-8";
          paramString1 = str;
        }
      }
    }
    label187:
    label316:
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return paramString1;
            str = this.defaultEncoding;
            break;
            if ((paramString3.equals("UTF-16")) && ((paramString2.equals("UTF-16BE")) || (paramString2.equals("UTF-16LE")))) {
              paramString1 = paramString2;
            } else {
              paramString1 = paramString3;
            }
          }
          if (!paramString1.equals("UTF-8")) {
            break label187;
          }
          if ((paramString2 != null) && (!paramString2.equals("UTF-8")))
          {
            Object[] arrayOfObject7 = new Object[3];
            arrayOfObject7[0] = paramString1;
            arrayOfObject7[1] = paramString2;
            arrayOfObject7[2] = paramString3;
            throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch", arrayOfObject7), paramString1, paramString2, paramString3);
          }
        } while ((paramString3 == null) || (paramString3.equals("UTF-8")));
        Object[] arrayOfObject6 = new Object[3];
        arrayOfObject6[0] = paramString1;
        arrayOfObject6[1] = paramString2;
        arrayOfObject6[2] = paramString3;
        throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch", arrayOfObject6), paramString1, paramString2, paramString3);
        if ((!paramString1.equals("UTF-16BE")) && (!paramString1.equals("UTF-16LE"))) {
          break label316;
        }
        if ((paramString2 != null) && (!paramString2.equals(paramString1)))
        {
          Object[] arrayOfObject2 = new Object[3];
          arrayOfObject2[0] = paramString1;
          arrayOfObject2[1] = paramString2;
          arrayOfObject2[2] = paramString3;
          throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch", arrayOfObject2), paramString1, paramString2, paramString3);
        }
      } while ((paramString3 == null) || (paramString3.equals("UTF-16")) || (paramString3.equals(paramString1)));
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString1;
      arrayOfObject1[1] = paramString2;
      arrayOfObject1[2] = paramString3;
      throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch", arrayOfObject1), paramString1, paramString2, paramString3);
      if ((!paramString1.equals("UTF-32BE")) && (!paramString1.equals("UTF-32LE"))) {
        break label445;
      }
      if ((paramString2 != null) && (!paramString2.equals(paramString1)))
      {
        Object[] arrayOfObject4 = new Object[3];
        arrayOfObject4[0] = paramString1;
        arrayOfObject4[1] = paramString2;
        arrayOfObject4[2] = paramString3;
        throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch", arrayOfObject4), paramString1, paramString2, paramString3);
      }
    } while ((paramString3 == null) || (paramString3.equals("UTF-32")) || (paramString3.equals(paramString1)));
    Object[] arrayOfObject3 = new Object[3];
    arrayOfObject3[0] = paramString1;
    arrayOfObject3[1] = paramString2;
    arrayOfObject3[2] = paramString3;
    throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch", arrayOfObject3), paramString1, paramString2, paramString3);
    label445:
    Object[] arrayOfObject5 = new Object[3];
    arrayOfObject5[0] = paramString1;
    arrayOfObject5[1] = paramString2;
    arrayOfObject5[2] = paramString3;
    throw new XmlStreamReaderException(MessageFormat.format("Invalid encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM", arrayOfObject5), paramString1, paramString2, paramString3);
  }
  
  public void close()
    throws IOException
  {
    this.reader.close();
  }
  
  public String getDefaultEncoding()
  {
    return this.defaultEncoding;
  }
  
  public String getEncoding()
  {
    return this.encoding;
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    return this.reader.read(paramArrayOfChar, paramInt1, paramInt2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/XmlStreamReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */