package es.gob.afirma.signers.pades;

import com.aowagie.text.Annotation;
import com.aowagie.text.DocumentException;
import com.aowagie.text.Image;
import com.aowagie.text.Jpeg;
import com.aowagie.text.Rectangle;
import com.aowagie.text.pdf.PdfContentByte;
import com.aowagie.text.pdf.PdfReader;
import com.aowagie.text.pdf.PdfStamper;
import com.aowagie.text.pdf.PdfWriter;

import es.gob.afirma.core.misc.Base64;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


//----------------------------------
//---- PORTAFIB: NOUS IMPORTS ------
//----------------------------------

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLContext;

import es.gob.afirma.core.misc.AOUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public final class PdfPreProcessor
{
  private static final Logger LOGGER = Logger.getLogger("es.gob.afirma");
  private static final int LAST_PAGE = -1;
  private static final int ALL_PAGES = 0;
  private static final int FIRST_PAGE = 1;
  
  public static void addMoreInfo(Map<String, String> paramMap, PdfStamper paramPdfStamper)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return;
    }
    paramPdfStamper.setMoreInfo(paramMap);
  }
  
  static void attachFile(Properties paramProperties, PdfStamper paramPdfStamper)
    throws IOException
  {
    if (paramProperties == null) {
      return;
    }
    if (paramPdfStamper == null) {
      throw new IllegalArgumentException("No se puede adjuntar un fichero a un PdfStamper nulo");
    }
    String str1 = paramProperties.getProperty("attach");
    
    String str2 = paramProperties.getProperty("attachFileName");
    
    String str3 = paramProperties.getProperty("attachDescription");
    if ((str1 != null) && (str2 != null))
    {
      byte[] arrayOfByte;
      try
      {
        arrayOfByte = Base64.decode(str1);
      }
      catch (IOException localIOException)
      {
        LOGGER.warning("Se ha indicado un adjunto, pero no estaba en formato Base64, se ignorara : " + localIOException);
        return;
      }
      paramPdfStamper.getWriter().addFileAttachment(str3, arrayOfByte, null, str2);
    }
  }
  
  public static void addImage(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, PdfStamper paramPdfStamper)
    throws IOException
  {
    PdfContentByte localPdfContentByte = paramPdfStamper.getOverContent(paramInt5);
    try
    {
      Jpeg localJpeg = new Jpeg(paramArrayOfByte);
      if (paramString != null) {
        localJpeg.setAnnotation(new Annotation(0.0F, 0.0F, 0.0F, 0.0F, paramString));
      }
      localPdfContentByte.addImage(localJpeg, paramInt1, 0.0F, 0.0F, paramInt2, paramInt3, paramInt4, false);
    }
    catch (DocumentException localDocumentException)
    {
      throw new IOException("Error durante la insercion de la imagen en el PDF: " + localDocumentException, localDocumentException);
    }
  }
  
  static void addImage(Properties paramProperties, PdfStamper paramPdfStamper, PdfReader paramPdfReader)
    throws IOException
  {
    if ((paramProperties == null) || (paramPdfStamper == null)) {
      return;
    }
    String str1 = paramProperties.getProperty("image");
    if ((str1 == null) || (str1.length() < 1)) {
      return;
    }
    byte[] arrayOfByte = Base64.decode(str1);
    
    Rectangle localRectangle = getPositionOnPage(paramProperties, "image");
    if (localRectangle == null) {
      return;
    }
    String str2 = paramProperties.getProperty("imagePage");
    if (str2 == null) {
      return;
    }
    int i;
    try
    {
      i = Integer.parseInt(str2);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new IOException("Se ha indicado un numero de pagina con formato invalido para insertar la imagen (" + str2 + "): " + localNumberFormatException, localNumberFormatException);
    }
    if (i == -1) {
      i = paramPdfReader.getNumberOfPages();
    }
    int j;
    if (i == 0)
    {
      i = 1;
      j = paramPdfReader.getNumberOfPages();
    }
    else
    {
      j = i;
    }
    for (int k = i; k <= j; k++) {
      addImage(arrayOfByte, 
      
        (int)localRectangle.getWidth(), 
        (int)localRectangle.getHeight(), 
        (int)localRectangle.getLeft(), 
        (int)localRectangle.getBottom(), k, null, paramPdfStamper);
    }
    LOGGER.info("Anadida imagen al PDF antes de la firma");
  }
  
  static Rectangle getPositionOnPage(Properties paramProperties, String paramString)
  {
    if ((paramProperties == null) || (paramString == null))
    {
      LOGGER.severe("Se ha pedido una posicion para un elemento grafico nulo");
      return null;
    }
    if ((paramProperties.getProperty(paramString + "PositionOnPageLowerLeftX") != null) && 
      (paramProperties.getProperty(paramString + "PositionOnPageLowerLeftY") != null) && 
      (paramProperties.getProperty(paramString + "PositionOnPageUpperRightX") != null) && 
      (paramProperties.getProperty(paramString + "PositionOnPageUpperRightY") != null)) {
      try
      {
        return new Rectangle(Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageLowerLeftX")), 
          Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageLowerLeftY")), 
          Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageUpperRightX")), 
          Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageUpperRightY")));
      }
      catch (Exception localException)
      {
        LOGGER.severe("Se ha indicado una posicion invalida para el elemento grafico '" + paramString + "': " + localException);
      }
    }
    return null;
  }
  
  
//----------------------------------
//--- PORTAFIB: CODI ELIMINAT ------
//----------------------------------
  
//  static Image getImage(String paramString)
//  {
//    if ((paramString == null) || (paramString.isEmpty())) {
//      return null;
//    }
//    byte[] arrayOfByte;
//    try
//    {
//      arrayOfByte = Base64.decode(paramString);
//    }
//    catch (Exception localException1)
//    {
//      LOGGER.severe("Se ha proporcionado una imagen de rubrica que no esta codificada en Base64: " + localException1);
//      return null;
//    }
//    try
//    {
//      return new Jpeg(arrayOfByte);
//    }
//    catch (Exception localException2)
//    {
//      LOGGER.info("Se ha proporcionado una imagen de rubrica que no esta codificada en JPEG: " + localException2);
//    }
//    return null;
//  }



//----------------------------------
//------ PORTAFIB: NOU CODI  -------
//----------------------------------
// 2018-09-28 Llançar excepcions si alguna cosa no va bé. 

public static Image getImage(final String imagebase64Encoded, X509Certificate certificate)
    throws IOException {

  if (imagebase64Encoded == null || "".equals(imagebase64Encoded))  { //$NON-NLS-1$
    return null;
  }
  final byte[] image;
  
  if (imagebase64Encoded.startsWith("http:") || imagebase64Encoded.startsWith("https:")) {
    final String url = imagebase64Encoded;
    try {
      image = downloadImage(url, certificate.getEncoded());
    } catch (final Exception e) {
      String msg = "Se ha producido un error durante la descarga de la imagen de rubrica"
          + " desde la URL " + url + ": " + e.getMessage(); 
      LOGGER.severe(msg); //$NON-NLS-1$
      // TODO Aqui hay que lanzar una excepción !!!!
      // No podemos firmar con una rubrica incorrecta !!!!!
      throw new IOException(msg);
    }
    
  } else {
    try {
      image = Base64.decode(imagebase64Encoded);
    } catch (final Exception e) {
      String msg = "Se ha proporcionado una imagen de rubrica que no esta codificada en Base64: " + e; 
      LOGGER.severe(msg); //$NON-NLS-1$
      throw new IOException(msg);
    }
  }
  
  
  try {
    return new Jpeg(image);
  } catch (final Exception e) {
    String msg = "Se ha proporcionado una imagen de rubrica que no esta codificada en JPEG: " + e; 
    LOGGER.severe(msg); //$NON-NLS-1$
    throw new IOException(msg);
  }
  
}


static byte[] downloadImage(String url, byte[] certificate) throws Exception  {
  String charset = "UTF-8";

  
  String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
  String CRLF = "\r\n"; // Line separator required by multipart/form-data.

  URLConnection connection = new URL(url).openConnection();
  connection.setDoOutput(true);
  connection.setDoInput(true);
  connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

  
  if (url.toLowerCase().startsWith("https")) {
    // IGNORAR CERTIFICATS DE SERVIDOR
    
    // Create a trust manager that does not validate certificate chains 
    final TrustManager[] trustAllCerts = new TrustManager[] {
        new X509TrustManager() { 
          @Override public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
          } 

          @Override public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
          } 
          
          @Override public X509Certificate[] getAcceptedIssuers() {
            return null; 
          } 
        } 
      };
    
    
      //Install the all-trusting trust manager
      final SSLContext sslContext = SSLContext.getInstance( "SSL" );
      sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
      //Create an ssl socket factory with our all-trusting manager
      final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
    
    
      //All set up, we can get a resource through https now:
      //Tell the url connection object to use our socket factory which bypasses security checks
      ( (HttpsURLConnection) connection ).setSSLSocketFactory( sslSocketFactory );
      
      
      ( (HttpsURLConnection) connection ).setHostnameVerifier(
          new javax.net.ssl.HostnameVerifier(){

                  @Override
              public boolean verify(String hostname,
                      javax.net.ssl.SSLSession sslSession) {
                  return true;
              }
          });
      
  }
  
  OutputStream output = connection.getOutputStream();
  PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);


  // Send binary file.
  writer.append("--" + boundary).append(CRLF);
  writer.append("Content-Disposition: form-data; name=\"certificateFile\"; filename=\"certificate.cer\"").append(CRLF);
  writer.append("Content-Type: application/x-509-user-cert").append(CRLF);
  writer.append("Content-Transfer-Encoding: binary").append(CRLF);
  writer.append(CRLF).flush();
  output.write(certificate);
  output.flush(); // Important before continuing with writer!
  writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

  // End of multipart/form-data.
  writer.append("--" + boundary + "--").append(CRLF).flush();


  // Request is lazily fired whenever you need to obtain information about response.
    
  int responseCode = ((HttpURLConnection) connection).getResponseCode();
  
  if (responseCode != 200) {
    throw new Exception("Retornado codigo de error " + responseCode 
        + " durante la descarga de la imagen.");
  }
  
  InputStream is = connection.getInputStream();
  
  return AOUtil.getDataFromInputStream(is);
  
  
}






}
  
  
  
