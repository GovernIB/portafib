/*     */ package es.gob.afirma.signers.pades;
/*     */ 
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.Image;
/*     */ import com.lowagie.text.Rectangle;
/*     */ import com.lowagie.text.exceptions.BadPasswordException;
/*     */ import com.lowagie.text.pdf.PdfDate;
/*     */ import com.lowagie.text.pdf.PdfName;
/*     */ import com.lowagie.text.pdf.PdfObject;
/*     */ import com.lowagie.text.pdf.PdfPKCS7;
/*     */ import com.lowagie.text.pdf.PdfPKCS7.X509Name;
/*     */ import com.lowagie.text.pdf.PdfReader;
/*     */ import com.lowagie.text.pdf.PdfSignature;
/*     */ import com.lowagie.text.pdf.PdfSignatureAppearance;
/*     */ import com.lowagie.text.pdf.PdfStamper;
/*     */ import com.lowagie.text.pdf.PdfStamperImp;
/*     */ import com.lowagie.text.pdf.PdfWriter;
/*     */ import es.gob.afirma.core.AOCancelledOperationException;
/*     */ import es.gob.afirma.core.AOException;
/*     */ import es.gob.afirma.core.ui.AOUIFactory;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Properties;
/*     */ import java.util.logging.Logger;
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
/*     */ public final class PdfSessionManager
/*     */ {
/*     */   static final int LAST_PAGE = -1;
/*     */   private static final int UNDEFINED = -1;
/*     */   private static final int CSIZE = 27000;
/*  51 */   private static final Logger LOGGER = Logger.getLogger("es.gob.afirma");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int PDF_MAX_VERSION = 7;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int PDF_MIN_VERSION = 2;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static PdfTriPhaseSession getSessionData(byte[] paramArrayOfByte, Certificate[] paramArrayOfCertificate, Calendar paramCalendar, Properties paramProperties)
/*     */     throws IOException, InvalidPdfException, AOException
/*     */   {
  
//----------------------------------
//---- PORTAFIB: NOU ELIMINAT ------
//----------------------------------
  
/*  83 */  /*   Image localImage = PdfPreProcessor.getImage(paramProperties.getProperty("signatureRubricImage")); */


//----------------------------------
//---- PORTAFIB: NOU CODI ------
//----------------------------------

       Image localImage = PdfPreProcessor.getImage(paramProperties.getProperty("signatureRubricImage"), (X509Certificate)paramArrayOfCertificate[0]);


/*     */     
/*     */ 
/*  86 */     String str1 = paramProperties.getProperty("signReason");
/*     */     
/*     */ 
/*  89 */     String str2 = paramProperties.getProperty("signatureField");
/*     */     
/*     */ 
/*  92 */     String str3 = paramProperties.getProperty("signatureProductionCity");
/*     */     
/*     */ 
/*  95 */     String str4 = paramProperties.getProperty("signerContact");
/*     */     
/*     */ 
/*  98 */     int i = -1;
/*     */     try {
/* 100 */       i = Integer.parseInt(paramProperties.getProperty("signaturePage"));
/*     */     }
/*     */     catch (Exception localException1) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 107 */     String str5 = paramProperties.getProperty("signatureSubFilter");
/*     */     
/*     */     int j;
/*     */     try
/*     */     {
/* 112 */       j = paramProperties.getProperty("certificationLevel") != null ? Integer.parseInt(paramProperties.getProperty("certificationLevel")) : -1;
/*     */ 
/*     */     }
/*     */     catch (Exception localException2)
/*     */     {
/* 117 */       j = -1;
/*     */     }
/*     */     
/*     */     int k;
/*     */     try
/*     */     {
/* 123 */       k = paramProperties.getProperty("pdfVersion") != null ? Integer.parseInt(paramProperties.getProperty("pdfVersion")) : 7;
/*     */ 
/*     */     }
/*     */     catch (Exception localException3)
/*     */     {
/* 128 */       LOGGER.warning("Error en el establecimiento de la version PDF, se usara 7: " + localException3);
/* 129 */       k = 7;
/*     */     }
/* 131 */     if ((k != -1) && ((k < 2) || (k > 7))) {
/* 132 */       LOGGER.warning("Se ha establecido un valor invalido para version, se ignorara: " + k);
/* 133 */       k = -1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 140 */     String str6 = PdfVisibleAreasUtils.getLayerText(paramProperties.getProperty("layer4Text"), (X509Certificate)paramArrayOfCertificate[0], paramCalendar);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 147 */     String str7 = PdfVisibleAreasUtils.getLayerText(paramProperties.getProperty("layer2Text"), (X509Certificate)paramArrayOfCertificate[0], paramCalendar);
/*     */     
/*     */ 
/*     */ 
/*     */     int m;
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 156 */       m = paramProperties.getProperty("layer2FontFamily") != null ? Integer.parseInt(paramProperties.getProperty("layer2FontFamily")) : -1;
/*     */ 
/*     */     }
/*     */     catch (Exception localException4)
/*     */     {
/* 161 */       m = -1;
/*     */     }
/*     */     
/*     */     int n;
/*     */     try
/*     */     {
/* 167 */       n = paramProperties.getProperty("layer2FontSize") != null ? Integer.parseInt(paramProperties.getProperty("layer2FontSize")) : -1;
/*     */ 
/*     */     }
/*     */     catch (Exception localException5)
/*     */     {
/* 172 */       n = -1;
/*     */     }
/*     */     
/*     */     int i1;
/*     */     try
/*     */     {
/* 178 */       i1 = paramProperties.getProperty("layer2FontStyle") != null ? Integer.parseInt(paramProperties.getProperty("layer2FontStyle")) : -1;
/*     */ 
/*     */     }
/*     */     catch (Exception localException6)
/*     */     {
/* 183 */       i1 = -1;
/*     */     }
/*     */     
/*     */ 
/* 187 */     String str8 = paramProperties.getProperty("layer2FontColor");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     byte[] arrayOfByte1;
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 198 */       arrayOfByte1 = XmpHelper.addSignHistoryToXmp(paramArrayOfByte, paramCalendar);
/*     */     }
/*     */     catch (Exception localException7) {
/* 201 */       LOGGER.warning("No ha podido registrarse la firma en el historico XMP: " + localException7);
/* 202 */       arrayOfByte1 = paramArrayOfByte;
/*     */     }
/*     */     
/* 205 */     PdfReader localPdfReader = PdfUtil.getPdfReader(arrayOfByte1, paramProperties, Boolean.parseBoolean(paramProperties.getProperty("headLess")));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 213 */     byte[] arrayOfByte2 = localPdfReader.getMetadata();
/* 214 */     boolean bool = PdfUtil.isPdfA1(arrayOfByte2);
/* 215 */     if (bool) {
/* 216 */       LOGGER.info("Detectado PDF-A1, no se comprimira el PDF");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 222 */     PdfUtil.checkPdfCertification(localPdfReader.getCertificationLevel(), paramProperties);
/*     */     
/* 224 */     if ((PdfUtil.pdfHasUnregisteredSignatures(localPdfReader)) && (!Boolean.TRUE.toString().equalsIgnoreCase(paramProperties.getProperty("allowCosigningUnregisteredSignatures")))) {
/* 225 */       throw new PdfHasUnregisteredSignaturesException();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 230 */     localPdfReader.removeUsageRights();
/*     */     
/* 232 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
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
/*     */     PdfStamper localPdfStamper;
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
/*     */     try
/*     */     {
/* 262 */       localPdfStamper = PdfStamper.createSignature(localPdfReader, localByteArrayOutputStream, k == -1 ? '\000' : Integer.toString(k).toCharArray()[0], null, PdfUtil.getAppendMode(paramProperties, localPdfReader), paramCalendar);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (DocumentException localDocumentException1)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 272 */       LOGGER.severe("Error al crear la firma para estampar: " + localDocumentException1);
/* 273 */       throw new AOException("Error al crear la firma para estampar", localDocumentException1);
/*     */ 
/*     */     }
/*     */     catch (BadPasswordException localBadPasswordException)
/*     */     {
/*     */ 
/* 279 */       if (Boolean.parseBoolean(paramProperties.getProperty("headLess"))) {
/* 280 */         throw new BadPdfPasswordException(localBadPasswordException);
/*     */       }
/*     */       
/*     */ 
/* 284 */       String localObject = new String(AOUIFactory.getPassword(paramProperties.getProperty("userPassword") == null ? CommonPdfMessages.getString("AOPDFSigner.0") : CommonPdfMessages.getString("AOPDFSigner.1"), null));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 290 */       if ("".equals(localObject)) {
/* 291 */         throw new AOCancelledOperationException("Entrada de contrasena de PDF cancelada por el usuario", localBadPasswordException);
/*     */       }
/*     */       
/*     */ 
/* 295 */       paramProperties.put("userPassword", localObject);
/* 296 */       return getSessionData(arrayOfByte1, paramArrayOfCertificate, paramCalendar, paramProperties);
/*     */     }
/*     */     
/*     */ 
/* 300 */     PdfSignatureAppearance localPdfSignatureAppearance = localPdfStamper.getSignatureAppearance();
/*     */     
/*     */ 
/* 303 */     if ((k > 2) && (!bool) && (!"false".equalsIgnoreCase(paramProperties.getProperty("compressPdf")))) {
/* 304 */       localPdfStamper.setFullCompression();
/*     */     }
/* 306 */     localPdfSignatureAppearance.setAcro6Layers(true);
/*     */     
/* 308 */     PdfUtil.enableLtv(localPdfStamper);
/*     */     
/*     */ 
/* 311 */     PdfPreProcessor.attachFile(paramProperties, localPdfStamper);
/*     */     
/*     */ 
/* 314 */     PdfPreProcessor.addImage(paramProperties, localPdfStamper, localPdfReader);
/*     */     
/*     */ 
/* 317 */     localPdfSignatureAppearance.setRender(0);
/*     */     
/*     */ 
/* 320 */     if (str1 != null) {
/* 321 */       localPdfSignatureAppearance.setReason(str1);
/*     */     }
/*     */     
/* 324 */     localPdfSignatureAppearance.setSignDate(paramCalendar);
/*     */     
/*     */ 
/* 327 */     if (i == -1) {
/* 328 */       i = localPdfReader.getNumberOfPages();
/*     */     }
/*     */     
/*     */ 
/* 332 */     Object localObject = getSignaturePositionOnPage(paramProperties);
/* 333 */     if ((localObject != null) && (str2 == null)) {
/* 334 */       localPdfSignatureAppearance.setVisibleSignature((Rectangle)localObject, i, null);
/*     */     }
/* 336 */     else if (str2 != null) {
/* 337 */       localPdfSignatureAppearance.setVisibleSignature(str2);
/*     */     }
/*     */     
/*     */ 
/* 341 */     if (str3 != null) {
/* 342 */       localPdfSignatureAppearance.setLocation(str3);
/*     */     }
/*     */     
/*     */ 
/* 346 */     if (str4 != null) {
/* 347 */       localPdfSignatureAppearance.setContact(str4);
/*     */     }
/*     */     
/*     */ 
/* 351 */     if (localImage != null) {
/* 352 */       localPdfSignatureAppearance.setImage(localImage);
/* 353 */       localPdfSignatureAppearance.setLayer2Text("");
/* 354 */       localPdfSignatureAppearance.setLayer4Text("");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 362 */     if (str7 != null) {
/* 363 */       localPdfSignatureAppearance.setLayer2Text(str7);
/* 364 */       localPdfSignatureAppearance.setLayer2Font(PdfVisibleAreasUtils.getFont(m, n, i1, str8));
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
/* 375 */     if (str6 != null) {
/* 376 */       localPdfSignatureAppearance.setLayer4Text(str6);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 383 */     localPdfSignatureAppearance.setCrypto(null, paramArrayOfCertificate, null, null);
/*     */     
/* 385 */     PdfSignature localPdfSignature = new PdfSignature(PdfName.ADOBE_PPKLITE, (str5 != null) && (!"".equals(str5)) ? new PdfName(str5) : PdfName.ADBE_PKCS7_DETACHED);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 391 */     if (localPdfSignatureAppearance.getSignDate() != null) {
/* 392 */       localPdfSignature.setDate(new PdfDate(localPdfSignatureAppearance.getSignDate()));
/*     */     }
/*     */     
/* 395 */     localPdfSignature.setName(PdfPKCS7.getSubjectFields((X509Certificate)paramArrayOfCertificate[0]).getField("CN"));
/*     */     
/* 397 */     if (localPdfSignatureAppearance.getReason() != null) {
/* 398 */       localPdfSignature.setReason(localPdfSignatureAppearance.getReason());
/*     */     }
/*     */     
/*     */ 
/* 402 */     if (localPdfSignatureAppearance.getLocation() != null) {
/* 403 */       localPdfSignature.setLocation(localPdfSignatureAppearance.getLocation());
/*     */     }
/*     */     
/*     */ 
/* 407 */     if (localPdfSignatureAppearance.getContact() != null) {
/* 408 */       localPdfSignature.setContact(localPdfSignatureAppearance.getContact());
/*     */     }
/*     */     
/* 411 */     localPdfSignatureAppearance.setCryptoDictionary(localPdfSignature);
/*     */     
/*     */ 
/*     */ 
/* 415 */     if (j != -1) {
/* 416 */       localPdfSignatureAppearance.setCertificationLevel(j);
/*     */     }
/*     */     
/*     */ 
/* 420 */     if (k != -1) {
/* 421 */       localPdfStamper.getWriter().setPdfVersion(Integer.toString(k).toCharArray()[0]);
/*     */     }
/*     */     
/*     */ 
/* 425 */     HashMap localHashMap = new HashMap();
/* 426 */     localHashMap.put(PdfName.CONTENTS, Integer.valueOf(54002));
/*     */     try
/*     */     {
/* 429 */       localPdfSignatureAppearance.preClose(localHashMap, paramCalendar);
/*     */     } catch (DocumentException localDocumentException2) {
/* 431 */       LOGGER.severe("Error al estampar la firma: " + localDocumentException2);
/* 432 */       throw new AOException("Error al estampar la firma", localDocumentException2);
/*     */     }
/*     */     
/* 435 */     PdfObject localPdfObject = ((PdfStamperImp)localPdfStamper.getWriter()).getFileID();
/*     */     
/* 437 */     return new PdfTriPhaseSession(localPdfSignatureAppearance, localByteArrayOutputStream, new String(localPdfObject.getBytes()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static Rectangle getSignaturePositionOnPage(Properties paramProperties)
/*     */   {
/* 448 */     return PdfPreProcessor.getPositionOnPage(paramProperties, "signature");
/*     */   }
/*     */ }


/* Location:              D:\dades\dades\CarpetesPersonals\Programacio\portafib-1.1\plugins-signatureweb\afirma-triphase-server-lib\local-repo\es\gob\afirma\afirma-crypto-pdf_OLD\3.3.2-SNAPSHOT\afirma-crypto-pdf-3.3.2-SNAPSHOT.jar!\es\gob\afirma\signers\pades\PdfSessionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */