/*     */ package es.gob.afirma.signers.pades;
/*     */ 
/*     */ import com.lowagie.text.Annotation;
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.Image;
/*     */ import com.lowagie.text.Jpeg;
/*     */ import com.lowagie.text.Rectangle;
/*     */ import com.lowagie.text.pdf.PdfContentByte;
/*     */ import com.lowagie.text.pdf.PdfReader;
/*     */ import com.lowagie.text.pdf.PdfStamper;
/*     */ import com.lowagie.text.pdf.PdfWriter;
/*     */ import es.gob.afirma.core.misc.Base64;
/*     */ import java.io.IOException;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 

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
import es.gob.afirma.core.misc.AOUtil;



/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PdfPreProcessor
/*     */ {
/*  33 */   private static final Logger LOGGER = Logger.getLogger("es.gob.afirma");
/*     */   
/*     */ 
/*     */   private static final int LAST_PAGE = -1;
/*     */   
/*     */ 
/*     */   private static final int ALL_PAGES = 0;
/*     */   
/*     */ 
/*     */   private static final int FIRST_PAGE = 1;
/*     */   
/*     */ 
/*     */   public static void addMoreInfo(Map<String, String> paramMap, PdfStamper paramPdfStamper)
/*     */   {
/*  47 */     if ((paramMap == null) || (paramMap.isEmpty())) {
/*  48 */       return;
/*     */     }
/*  50 */     paramPdfStamper.setMoreInfo(paramMap);
/*     */   }
/*     */   
/*     */   static void attachFile(Properties paramProperties, PdfStamper paramPdfStamper) throws IOException {
/*  54 */     if (paramProperties == null) {
/*  55 */       return;
/*     */     }
/*  57 */     if (paramPdfStamper == null) {
/*  58 */       throw new IllegalArgumentException("No se puede adjuntar un fichero a un PdfStamper nulo");
/*     */     }
/*     */     
/*  61 */     String str1 = paramProperties.getProperty("attach");
/*     */     
/*     */ 
/*  64 */     String str2 = paramProperties.getProperty("attachFileName");
/*     */     
/*     */ 
/*  67 */     String str3 = paramProperties.getProperty("attachDescription");
/*     */     
/*  69 */     if ((str1 != null) && (str2 != null)) {
/*     */       byte[] arrayOfByte;
/*     */       try {
/*  72 */         arrayOfByte = Base64.decode(str1);
/*     */       }
/*     */       catch (IOException localIOException) {
/*  75 */         LOGGER.warning("Se ha indicado un adjunto, pero no estaba en formato Base64, se ignorara : " + localIOException);
/*  76 */         return;
/*     */       }
/*  78 */       paramPdfStamper.getWriter().addFileAttachment(str3, arrayOfByte, null, str2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void addImage(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, PdfStamper paramPdfStamper)
/*     */     throws IOException
/*     */   {
/* 103 */     PdfContentByte localPdfContentByte = paramPdfStamper.getOverContent(paramInt5);
/*     */     try {
/* 105 */       Jpeg localJpeg = new Jpeg(paramArrayOfByte);
/* 106 */       if (paramString != null) {
/* 107 */         localJpeg.setAnnotation(new Annotation(0.0F, 0.0F, 0.0F, 0.0F, paramString));
/*     */       }
/* 109 */       localPdfContentByte.addImage(localJpeg, paramInt1, 0.0F, 0.0F, paramInt2, paramInt3, paramInt4, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (DocumentException localDocumentException)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */       throw new IOException("Error durante la insercion de la imagen en el PDF: " + localDocumentException, localDocumentException);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static void addImage(Properties paramProperties, PdfStamper paramPdfStamper, PdfReader paramPdfReader)
/*     */     throws IOException
/*     */   {
/* 132 */     if ((paramProperties == null) || (paramPdfStamper == null)) {
/* 133 */       return;
/*     */     }
/*     */     
/* 136 */     String str1 = paramProperties.getProperty("image");
/* 137 */     if ((str1 == null) || (str1.length() < 1)) {
/* 138 */       return;
/*     */     }
/* 140 */     byte[] arrayOfByte = Base64.decode(str1);
/*     */     
/* 142 */     Rectangle localRectangle = getPositionOnPage(paramProperties, "image");
/*     */     
/* 144 */     if (localRectangle == null) {
/* 145 */       return;
/*     */     }
/*     */     
/* 148 */     String str2 = paramProperties.getProperty("imagePage");
/* 149 */     if (str2 == null) {
/*     */       return;
/*     */     }
/*     */     int i;
/*     */     try
/*     */     {
/* 155 */       i = Integer.parseInt(str2);
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException) {
/* 158 */       throw new IOException("Se ha indicado un numero de pagina con formato invalido para insertar la imagen (" + str2 + "): " + localNumberFormatException, localNumberFormatException);
/*     */     }
/*     */     
/* 161 */     if (i == -1) {
/* 162 */       i = paramPdfReader.getNumberOfPages();
/*     */     }
/*     */     int j;
/* 165 */     if (i == 0) {
/* 166 */       i = 1;
/* 167 */       j = paramPdfReader.getNumberOfPages();
/*     */     }
/*     */     else {
/* 170 */       j = i;
/*     */     }
/*     */     
/* 173 */     for (int k = i; k <= j; k++) {
/* 174 */       addImage(arrayOfByte, (int)localRectangle.getWidth(), (int)localRectangle.getHeight(), (int)localRectangle.getLeft(), (int)localRectangle.getBottom(), k, null, paramPdfStamper);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 186 */     LOGGER.info("Anadida imagen al PDF antes de la firma");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   static Rectangle getPositionOnPage(Properties paramProperties, String paramString)
/*     */   {
/* 196 */     if ((paramProperties == null) || (paramString == null)) {
/* 197 */       LOGGER.severe("Se ha pedido una posicion para un elemento grafico nulo");
/* 198 */       return null;
/*     */     }
/* 200 */     if ((paramProperties.getProperty(paramString + "PositionOnPageLowerLeftX") != null) && (paramProperties.getProperty(paramString + "PositionOnPageLowerLeftY") != null) && (paramProperties.getProperty(paramString + "PositionOnPageUpperRightX") != null) && (paramProperties.getProperty(paramString + "PositionOnPageUpperRightY") != null))
/*     */     {
/*     */ 
/*     */       try
/*     */       {
/*     */ 
/* 206 */         return new Rectangle(Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageLowerLeftX")), Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageLowerLeftY")), Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageUpperRightX")), Integer.parseInt(paramProperties.getProperty(paramString + "PositionOnPageUpperRightY")));
/*     */ 
/*     */ 
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */ 
/* 213 */         LOGGER.severe("Se ha indicado una posicion invalida para el elemento grafico '" + paramString + "': " + localException);
/*     */       }
/*     */     }
/* 216 */     return null;
/*     */   }
/*     */  


// ----------------------------------
// --- PORTAFIB: CODI ELIMINAT ------
// ----------------------------------

/*        static Image getImage(String paramString) {
/* 220      if ((paramString == null) || ("".equals(paramString))) {
/* 221        return null;
/*          }
/*          byte[] arrayOfByte;
/*          try {
/* 225        arrayOfByte = Base64.decode(paramString);
/*          }
/*          catch (Exception localException1) {
/* 228        LOGGER.severe("Se ha proporcionado una imagen de rubrica que no esta codificada en Base64: " + localException1);
/* 229        return null;
/*          }
/*          try {
/* 232        return new Jpeg(arrayOfByte);
/*          }
/*          catch (Exception localException2) {
/* 235        LOGGER.info("Se ha proporcionado una imagen de rubrica que no esta codificada en JPEG: " + localException2);
/*          }
/* 237      return null;
/*        }
*/


//----------------------------------
//------ PORTAFIB: NOU CODI  -------
//----------------------------------


public static com.lowagie.text.Image getImage(final String imagebase64Encoded, X509Certificate certificate) {
  if (imagebase64Encoded == null || "".equals(imagebase64Encoded)) { //$NON-NLS-1$
    return null;
  }
  final byte[] image;
  
  if (imagebase64Encoded.startsWith("http:") || imagebase64Encoded.startsWith("https:")) {
    final String url = imagebase64Encoded;
    try {
      image = downloadImage(url, certificate.getEncoded());
    } catch (final Exception e) {
      // XYZ
      LOGGER
      .severe("Se ha producido un error durante la descarga de la imagen de rubrica"
          + " desde la URL " + url + ": " + e.getMessage()); //$NON-NLS-1$
      // TODO Aqui hay que lanzar una excepción !!!!1
      // No podemos firmar con una rubrica incorrecta !!!!!
      return null;
    }
    
  } else {
    try {
      image = Base64.decode(imagebase64Encoded);
    } catch (final Exception e) {
      LOGGER
          .severe("Se ha proporcionado una imagen de rubrica que no esta codificada en Base64: " + e); //$NON-NLS-1$
      return null;
    }
  }
  
  
  try {
    return new Jpeg(image);
  } catch (final Exception e) {
    LOGGER
        .info("Se ha proporcionado una imagen de rubrica que no esta codificada en JPEG: " + e); //$NON-NLS-1$
  }
  return null;
}


static byte[] downloadImage(String url, byte[] certificate) throws Exception  {
  String charset = "UTF-8";

  
  String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
  String CRLF = "\r\n"; // Line separator required by multipart/form-data.

  URLConnection connection = new URL(url).openConnection();
  connection.setDoOutput(true);
  connection.setDoInput(true);
  connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);


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






/*     */ }


/* Location:              D:\dades\dades\CarpetesPersonals\Programacio\portafib-1.1\plugins-signatureweb\afirma-triphase-server-lib\local-repo\es\gob\afirma\afirma-crypto-pdf_OLD\3.3.2-SNAPSHOT\afirma-crypto-pdf-3.3.2-SNAPSHOT.jar!\es\gob\afirma\signers\pades\PdfPreProcessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */