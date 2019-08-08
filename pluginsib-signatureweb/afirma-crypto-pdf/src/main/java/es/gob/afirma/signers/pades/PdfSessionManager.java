package es.gob.afirma.signers.pades;

import com.aowagie.text.DocumentException;
import com.aowagie.text.Image;
import com.aowagie.text.Rectangle;
import com.aowagie.text.exceptions.BadPasswordException;
import com.aowagie.text.pdf.PdfDate;
import com.aowagie.text.pdf.PdfName;
import com.aowagie.text.pdf.PdfObject;
import com.aowagie.text.pdf.PdfPKCS7;
import com.aowagie.text.pdf.PdfPKCS7.X509Name;
import com.aowagie.text.pdf.PdfReader;
import com.aowagie.text.pdf.PdfSignature;
import com.aowagie.text.pdf.PdfSignatureAppearance;
import com.aowagie.text.pdf.PdfStamper;
import com.aowagie.text.pdf.PdfStamperImp;
import com.aowagie.text.pdf.PdfWriter;
import es.gob.afirma.core.AOCancelledOperationException;
import es.gob.afirma.core.AOException;
import es.gob.afirma.core.ui.AOUIFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public final class PdfSessionManager
{
  static final int LAST_PAGE = -1;
  static final int NEW_PAGE = -2;
  private static final int UNDEFINED = -1;
  private static final int CSIZE = 27000;
  private static final Logger LOGGER = Logger.getLogger("es.gob.afirma");
  private static final int PDF_MAX_VERSION = 7;
  private static final int PDF_MIN_VERSION = 2;
  
  public static PdfTriPhaseSession getSessionData(byte[] paramArrayOfByte, Certificate[] paramArrayOfCertificate, Calendar paramCalendar, Properties paramProperties)
    throws IOException, InvalidPdfException, AOException
  {

  //----------------------------------
  //---- PORTAFIB: NOU ELIMINAT ------
  //----------------------------------
    
  /* Image localImage = PdfPreProcessor.getImage(paramProperties.getProperty("signatureRubricImage")); */


  //----------------------------------
  //---- PORTAFIB: NOU CODI ------
  //----------------------------------

    Image localImage = PdfPreProcessor.getImage(paramProperties.getProperty("signatureRubricImage"), (X509Certificate)paramArrayOfCertificate[0]);

    
    
    String str1 = paramProperties.getProperty("signReason");
    
    String str2 = paramProperties.getProperty("signatureField");
    
    String str3 = paramProperties.getProperty("signatureProductionCity");
    
    String str4 = paramProperties.getProperty("signerContact");
    
    int i = -1;
    String str5 = paramProperties.getProperty("signaturePage", "-1");
    if ("append".equalsIgnoreCase(str5)) {
      i = -2;
    } else {
      try
      {
        i = Integer.parseInt(str5);
      }
      catch (Exception localException1)
      {
        LOGGER.warning("Se ha indicado un numero de pagina invalido ('" + str5 + "'), se usara la ultima pagina: " + localException1);
      }
    }
    String str6 = paramProperties.getProperty("signatureSubFilter");
    int j;
    try
    {
      j = paramProperties.getProperty("certificationLevel") != null ? Integer.parseInt(paramProperties.getProperty("certificationLevel")) : -1;
    }
    catch (Exception localException2)
    {
      j = -1;
    }
    int k;
    try
    {
      k = paramProperties.getProperty("pdfVersion") != null ? Integer.parseInt(paramProperties.getProperty("pdfVersion")) : 7;
    }
    catch (Exception localException3)
    {
      LOGGER.warning("Error en el establecimiento de la version PDF, se usara 7: " + localException3);
      k = 7;
    }
    if ((k != -1) && ((k < 2) || (k > 7)))
    {
      LOGGER.warning("Se ha establecido un valor invalido para version, se ignorara: " + k);
      k = -1;
    }
    String str7 = PdfVisibleAreasUtils.getLayerText(paramProperties
      .getProperty("layer4Text"), (X509Certificate)paramArrayOfCertificate[0], paramCalendar);
    
    String str8 = PdfVisibleAreasUtils.getLayerText(paramProperties
      .getProperty("layer2Text"), (X509Certificate)paramArrayOfCertificate[0], paramCalendar);
    int m;
    try
    {
      m = paramProperties.getProperty("layer2FontFamily") != null ? Integer.parseInt(paramProperties.getProperty("layer2FontFamily")) : -1;
    }
    catch (Exception localException4)
    {
      m = -1;
    }
    int n;
    try
    {
      n = paramProperties.getProperty("layer2FontSize") != null ? Integer.parseInt(paramProperties.getProperty("layer2FontSize")) : -1;
    }
    catch (Exception localException5)
    {
      n = -1;
    }
    int i1;
    try
    {
      i1 = paramProperties.getProperty("layer2FontStyle") != null ? Integer.parseInt(paramProperties.getProperty("layer2FontStyle")) : -1;
    }
    catch (Exception localException6)
    {
      i1 = -1;
    }
    String str9 = paramProperties.getProperty("layer2FontColor");
    byte[] arrayOfByte1;
    try
    {
      arrayOfByte1 = XmpHelper.addSignHistoryToXmp(paramArrayOfByte, paramCalendar);
    }
    catch (Exception localException7)
    {
      LOGGER.warning("No ha podido registrarse la firma en el historico XMP: " + localException7);
      arrayOfByte1 = paramArrayOfByte;
    }
    PdfReader localPdfReader = PdfUtil.getPdfReader(arrayOfByte1, paramProperties, 
    
      Boolean.parseBoolean(paramProperties.getProperty("headless")));
    
    byte[] arrayOfByte2 = localPdfReader.getMetadata();
    boolean bool = PdfUtil.isPdfA1(arrayOfByte2);
    if (bool) {
      LOGGER.info("Detectado PDF-A1, no se comprimira el PDF");
    }
    PdfUtil.checkPdfCertification(localPdfReader.getCertificationLevel(), paramProperties);
    if ((PdfUtil.pdfHasUnregisteredSignatures(localPdfReader)) && (!Boolean.TRUE.toString().equalsIgnoreCase(paramProperties.getProperty("allowCosigningUnregisteredSignatures")))) {
      throw new PdfHasUnregisteredSignaturesException();
    }
    localPdfReader.removeUsageRights();
    
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PdfStamper localPdfStamper;
    try
    {
      localPdfStamper = PdfStamper.createSignature(localPdfReader, localByteArrayOutputStream, k == -1 ? '\000' : 
      
        Integer.toString(k).toCharArray()[0], null, 
        
        PdfUtil.getAppendMode(paramProperties, localPdfReader), paramCalendar);
    }
    catch (DocumentException localDocumentException1)
    {
      LOGGER.severe("Error al crear la firma para estampar: " + localDocumentException1);
      throw new AOException("Error al crear la firma para estampar", localDocumentException1);
    }
    catch (BadPasswordException localBadPasswordException)
    {
      if (Boolean.parseBoolean(paramProperties.getProperty("headless"))) {
        throw new BadPdfPasswordException(localBadPasswordException);
      }
      String localObject = new String(AOUIFactory.getPassword(paramProperties
        .getProperty("userPassword") == null ? CommonPdfMessages.getString("AOPDFSigner.0") : CommonPdfMessages.getString("AOPDFSigner.1"), null));
      if ("".equals(localObject)) {
        throw new AOCancelledOperationException("Entrada de contrasena de PDF cancelada por el usuario", localBadPasswordException);
      }
      paramProperties.put("userPassword", localObject);
      return getSessionData(arrayOfByte1, paramArrayOfCertificate, paramCalendar, paramProperties);
    }
    Rectangle localRectangle = getSignaturePositionOnPage(paramProperties);
    if ((i == -2) && (localRectangle != null) && (str2 == null))
    {
      localPdfStamper.insertPage(localPdfReader.getNumberOfPages() + 1, localPdfReader.getPageSizeWithRotation(1));
      
      i = -1;
    }
    Object localObject = localPdfStamper.getSignatureAppearance();
    if ((k > 2) && (!bool) && (!"false".equalsIgnoreCase(paramProperties.getProperty("compressPdf")))) {
      localPdfStamper.setFullCompression();
    }
    ((PdfSignatureAppearance)localObject).setAcro6Layers(true);
    
    PdfUtil.enableLtv(localPdfStamper);
    
    PdfPreProcessor.attachFile(paramProperties, localPdfStamper);
    
    PdfPreProcessor.addImage(paramProperties, localPdfStamper, localPdfReader);
    
    ((PdfSignatureAppearance)localObject).setRender(0);
    if (str1 != null) {
      ((PdfSignatureAppearance)localObject).setReason(str1);
    }
    ((PdfSignatureAppearance)localObject).setSignDate(paramCalendar);
    if (i == -1) {
      i = localPdfReader.getNumberOfPages();
    }
    if ((localRectangle != null) && (str2 == null)) {
      ((PdfSignatureAppearance)localObject).setVisibleSignature(localRectangle, i, null);
    } else if (str2 != null) {
      ((PdfSignatureAppearance)localObject).setVisibleSignature(str2);
    }
    if (str3 != null) {
      ((PdfSignatureAppearance)localObject).setLocation(str3);
    }
    if (str4 != null) {
      ((PdfSignatureAppearance)localObject).setContact(str4);
    }
    if (localImage != null)
    {
      ((PdfSignatureAppearance)localObject).setImage(localImage);
      ((PdfSignatureAppearance)localObject).setLayer2Text("");
      ((PdfSignatureAppearance)localObject).setLayer4Text("");
    }
    if (str8 != null)
    {
      ((PdfSignatureAppearance)localObject).setLayer2Text(str8);
      ((PdfSignatureAppearance)localObject).setLayer2Font(
        PdfVisibleAreasUtils.getFont(m, n, i1, str9));
    }
    if (str7 != null) {
      ((PdfSignatureAppearance)localObject).setLayer4Text(str7);
    }
    ((PdfSignatureAppearance)localObject).setCrypto(null, paramArrayOfCertificate, null, null);
    PdfSignature localPdfSignature;
    if (str6 == null || str6.isEmpty()) {
      localPdfSignature = new PdfSignature(PdfName.ADOBE_PPKLITE, PdfName.ADBE_PKCS7_DETACHED);
    } else {
      localPdfSignature = new PdfSignature(PdfName.ADOBE_PPKLITE, new PdfName(str6) );
    }
    if (((PdfSignatureAppearance)localObject).getSignDate() != null) {
      localPdfSignature.setDate(new PdfDate(((PdfSignatureAppearance)localObject).getSignDate()));
    }
    localPdfSignature.setName(PdfPKCS7.getSubjectFields((X509Certificate)paramArrayOfCertificate[0]).getField("CN"));
    if (((PdfSignatureAppearance)localObject).getReason() != null) {
      localPdfSignature.setReason(((PdfSignatureAppearance)localObject).getReason());
    }
    if (((PdfSignatureAppearance)localObject).getLocation() != null) {
      localPdfSignature.setLocation(((PdfSignatureAppearance)localObject).getLocation());
    }
    if (((PdfSignatureAppearance)localObject).getContact() != null) {
      localPdfSignature.setContact(((PdfSignatureAppearance)localObject).getContact());
    }
    ((PdfSignatureAppearance)localObject).setCryptoDictionary(localPdfSignature);
    if (j != -1) {
      ((PdfSignatureAppearance)localObject).setCertificationLevel(j);
    }
    if (k != -1) {
      localPdfStamper.getWriter().setPdfVersion(Integer.toString(k).toCharArray()[0]);
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put(PdfName.CONTENTS, Integer.valueOf(54002));
    try
    {
      ((PdfSignatureAppearance)localObject).preClose(localHashMap, paramCalendar);
    }
    catch (DocumentException localDocumentException2)
    {
      LOGGER.severe("Error al estampar la firma: " + localDocumentException2);
      throw new AOException("Error al estampar la firma", localDocumentException2);
    }
    PdfObject localPdfObject = ((PdfStamperImp)localPdfStamper.getWriter()).getFileID();
    
    return new PdfTriPhaseSession((PdfSignatureAppearance)localObject, localByteArrayOutputStream, new String(localPdfObject.getBytes()));
  }
  
  private static Rectangle getSignaturePositionOnPage(Properties paramProperties)
  {
    return PdfPreProcessor.getPositionOnPage(paramProperties, "signature");
  }
}
