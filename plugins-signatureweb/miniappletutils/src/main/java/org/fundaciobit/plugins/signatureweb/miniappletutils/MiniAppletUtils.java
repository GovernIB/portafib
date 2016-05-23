package org.fundaciobit.plugins.signatureweb.miniappletutils;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signatureweb.api.PdfRubricRectangle;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.utils.Base64;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletUtils {

  protected final static Logger log = Logger.getLogger(MiniAppletUtils.class.getName());

  public static MiniAppletSignInfo convertRemoteSignature(
      CommonInfoSignature commonInfoSignature, FileInfoSignature fileInfo,
      String timeStampURL, String rubricURL) throws Exception {
    final boolean isLocalSignature = false;
    return convert(commonInfoSignature, fileInfo, timeStampURL, null, rubricURL,
        isLocalSignature);

  }

  public static MiniAppletSignInfo convertLocalSignature(
      CommonInfoSignature commonInfoSignature, FileInfoSignature fileInfo,
      String timeStampURL, X509Certificate certificate) throws Exception {
    final boolean isLocalSignature = true;
    return convert(commonInfoSignature, fileInfo, timeStampURL, certificate, null,
        isLocalSignature);
  }

  private static MiniAppletSignInfo convert(CommonInfoSignature commonInfoSignature,
      FileInfoSignature fileInfo, String timeStampURL, X509Certificate certificate,
      String rubricURL, boolean isLocalSignature) throws Exception {
    MiniAppletSignInfo info;

    Properties miniAppletProperties = new Properties();

    /*
     * explicit La firma resultante no incluirá los datos firmados. Si no se
     * indica el parámetro mode se configura automáticamente este
     * comportamiento.
     * 
     * implicit La firma resultante incluirá internamente una copia de los datos
     * firmados. El uso de este valor podría generar firmas de gran tamaño.
     */
    if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_SIGN_MODE,
          MiniAppletConstants.VALUE_SIGN_MODE_IMPLICIT);
    } else {
      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_SIGN_MODE,
          MiniAppletConstants.VALUE_SIGN_MODE_EXPLICIT);
    }

    // POLITICA DE FIRMA
    PolicyInfoSignature policy = commonInfoSignature.getPolicyInfoSignature();

    if (policy != null && policy.getPolicyIdentifier() == null
        && policy.getPolicyIdentifier().trim().length() != 0) {

      String oid = policy.getPolicyIdentifier();

      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_IDENTIFIER, oid);

      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_HASH,
          policy.getPolicyIdentifierHash());

      miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_HASH_ALGORITHM,
          policy.getPolicyIdentifierHashAlgorithm());

      String val = policy.getPolicyUrlDocument();
      if (val != null) {
        miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_POLICY_QUALIFIER, val);
      }
    }

    // ====================  TIPUS DE FIRMA
    String tipusFirma;
    if (FileInfoSignature.SIGN_TYPE_PADES.equals(fileInfo.getSignType())) {

      miniAppletProperties.setProperty("alwaysCreateRevision", "true");

      tipusFirma = MiniAppletConstants.VALUE_SIGN_TYPE_PADES;

      // POLITICA DE FIRMA PADES
      if (policy == null || policy.getPolicyIdentifier() == null
          || policy.getPolicyIdentifier().trim().length() == 0) {
        miniAppletProperties.setProperty("signatureSubFilter",
            MiniAppletConstants.PADES_SUBFILTER_BES);
            //MiniAppletConstants.PADES_SUBFILTER_BASIC
      } else {
        miniAppletProperties.setProperty("signatureSubFilter",
            MiniAppletConstants.PADES_SUBFILTER_BES);
      }
      
      
      // Sign reason
      if (fileInfo.getReason() != null) {
        miniAppletProperties.setProperty("signReason", fileInfo.getReason());
      }
      
      if (fileInfo.getSignerEmail() != null) {
        miniAppletProperties.setProperty("signerContact", fileInfo.getSignerEmail());
      }
      
      
      

      // PDF Visible      
      if (fileInfo.getSignaturesTableLocation() != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
        
        PdfVisibleSignature pdfSign = fileInfo.getPdfVisibleSignature();

        miniAppletProperties.setProperty(MiniAppletConstants.PROPERTY_SIGNATUREPAGE,
            String.valueOf(fileInfo.getSignaturesTableLocation()));

        PdfRubricRectangle rr = pdfSign.getPdfRubricRectangle();

        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTX,
            String.valueOf((int) rr.getLowerLeftX()));
        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTX,
            String.valueOf((int) rr.getUpperRightX()));
        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGELOWERLEFTY,
            String.valueOf((int) rr.getLowerLeftY()));
        miniAppletProperties.setProperty(
            MiniAppletConstants.PROPERTY_SIGNATUREPOSITIONONPAGEUPPERRIGHTY,
            String.valueOf((int) rr.getUpperRightY()));

        if (isLocalSignature) {
          byte[] signatureRubricImage;
          signatureRubricImage = pdfSign.getRubricGenerator().genenerateRubricImage(
              certificate, new Date());
          miniAppletProperties.setProperty(
              MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE,
              Base64.encode(signatureRubricImage));
        } else {
          miniAppletProperties.setProperty(
              MiniAppletConstants.PROPERTY_SIGNATURE_RUBRIC_IMAGE, rubricURL);
        }
      }

    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(fileInfo.getSignType())) {
      // TODO Alguna cosa mes ???
      tipusFirma = MiniAppletConstants.VALUE_SIGN_TYPE_CADES;

    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(fileInfo.getSignType())) {
      // TODO Alguna cosa mes ???
      tipusFirma = MiniAppletConstants.VALUE_SIGN_TYPE_XADES;
      
      // En xades no te sentit el camp 'mode'
      miniAppletProperties.remove(MiniAppletConstants.PROPERTY_SIGN_MODE);
      
      /**
       * addKeyInfoKeyValue 
       *       - true  -> Incluye el nodo KeyValue dentro de KeyInfo de XAdES (comportamiento por defecto).
               - false -> No incluye el nodo KeyValue dentro de KeyInfo de XAdES.
       * addKeyInfoKeyName
       *       - true  -> Incluye el nodo KeyName dentro de KeyInfo de XAdES.
       *       - false -> No incluye el nodo KeyName dentro de KeyInfo
       */ 
      if (fileInfo.getSignMode() == FileInfoSignature.SIGN_MODE_IMPLICIT) {
        /*
         * implicit La firma resultante incluirá internamente una copia de los datos
         * firmados. El uso de este valor podría generar firmas de gran tamaño.
         */
        miniAppletProperties.setProperty("addKeyInfoKeyValue", "true");
       
      } else {
        /*
         * explicit La firma resultante no incluirá los datos firmados. Si no se
         * indica el parámetro mode se configura automáticamente este
         * comportamiento.
         */
        miniAppletProperties.setProperty("addKeyInfoKeyValue", "false");
      }
      
      final String mime = fileInfo.getMimeType();
      if (mime != null && !mime.equals("application/octet-stream") 
          && !mime.equals("application/octet-stream")
          && !mime.equals("application/binary")
          && !mime.equals("unknown/unknown")) {
          miniAppletProperties.setProperty("mimeType",mime);
          
      }
      log.info("Enviant a firma Xades:: fitxer " + fileInfo.getName() + " amb mime " + mime);
      
      // headless  true -> Evita que se muestren diálogos gráficos adicionales
      //                   al usuario  (como por ejemplo, para la dereferenciación
      //                   de hojas de estilo enlazadas con rutas relativas).
      miniAppletProperties.setProperty("headless","true");

    } else {
      // TODO Traduir
      throw new Exception("Tipus de firma no suportada: " + fileInfo.getSignType());
    }
    miniAppletProperties.setProperty(MiniAppletConstants.APPLET_SIGN_TYPE, tipusFirma);

    // ALGORISME DE FIRMA
    String algorisme;
    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA1;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA256;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA384;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(fileInfo.getSignAlgorithm())) {
      algorisme = MiniAppletConstants.VALUE_SIGN_ALGORITHM_SHA512;
    } else {
      throw new Exception("Tipus d'algorisme no suportat " + fileInfo.getSignAlgorithm());
    }
    miniAppletProperties.setProperty(MiniAppletConstants.APPLET_SIGN_ALGORITHM, algorisme);

    // Segell de Temps (Segellat de temps)
    if (timeStampURL != null) {
      
      if (log.isDebugEnabled()) {
        log.debug("convert::isLocal=" + isLocalSignature);
        log.debug("convert::tsaURL=" + timeStampURL);
      }
      

      miniAppletProperties.setProperty("tsaURL", timeStampURL);

      ITimeStampGenerator timestampGenerator = fileInfo.getTimeStampGenerator();

      miniAppletProperties
          .setProperty("tsaPolicy", timestampGenerator.getTimeStampPolicyOID());

      final String CATCERT_REQUIRECERT = "" + Boolean.TRUE;
      miniAppletProperties.setProperty("tsaRequireCert", CATCERT_REQUIRECERT);

      miniAppletProperties.setProperty("tsaHashAlgorithm",
          timestampGenerator.getTimeStampHashAlgorithm());
      // Sello de tiempo a nivel de firma.
      miniAppletProperties.setProperty("tsType", "" + MiniAppletConstants.TS_SIGN);

    }
    
    // Location (comú a Pades, Xades i cades)
    if(fileInfo.getLocation() != null) {
      miniAppletProperties.setProperty("signatureProductionCity", fileInfo.getLocation());
    }
    
    

    byte[] pdf = FileUtils.readFromFile(fileInfo.getFileToSign());

    info = new MiniAppletSignInfo(pdf, tipusFirma, algorisme, certificate,
        miniAppletProperties);

    return info;
  }
  
  
  
  

  public static boolean matchFilter(X509Certificate certificate, String filter)
      throws IOException, Exception, NoSuchMethodException, InstantiationException,
      IllegalAccessException, InvocationTargetException {
    Properties propertyFilters = new Properties();
    propertyFilters.load(new StringReader(filter));

    MiniAppletClassLoader macl = new MiniAppletClassLoader();

    // CertFilterManager certFilterManager = new
    // CertFilterManager(propertyFilters);
    Class<?> certFilterManagerClass = macl
        .loadClass("es.gob.afirma.keystores.filters.CertFilterManager");
    Constructor<?> contructor = certFilterManagerClass.getConstructor(Properties.class);
    Object certFilterManager = contructor.newInstance(propertyFilters);

    // List<CertificateFilter> filtres = certFilterManager.getFilters();
    Method method2 = macl.getMethod(certFilterManagerClass, "getFilters");
    List<? extends Object> filtres = (List<? extends Object>) method2
        .invoke(certFilterManager);

    // MultipleCertificateFilter mcf = new
    // MultipleCertificateFilter(filtres.toArray());
    // Crear Array de CertificateFilter
    Class<?> certFilterClass = macl
        .loadClass("es.gob.afirma.keystores.filters.CertificateFilter");
    Object certFilterArray = Array.newInstance(certFilterClass, filtres.size());

    int index = 0;
    for (Object object : filtres) {
      Array.set(certFilterArray, index, object);
      index++;
    }
    // Instanciar
    Class<?> mcfClass = macl
        .loadClass("es.gob.afirma.keystores.filters.MultipleCertificateFilter");
    Constructor<?> contructorMCF = mcfClass.getConstructor(certFilterArray.getClass());
    Object mcf = contructorMCF.newInstance(certFilterArray);

    // boolean match = mcf.matches(certificate1);
    Method methodMatches = macl.getMethod(mcfClass, "matches");
    Boolean match = (Boolean) methodMatches.invoke(mcf, certificate);

    return match;
  }

  

}
