package es.caib.portafib.logic;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfComparator;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.certificate.InformacioCertificat;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author anadal
 */
@Stateless(name = "ValidacioCompletaFirmaLogicaEJB")
@SecurityDomain("seycon")
public class ValidacioCompletaFirmaLogicaEJB implements ValidacioCompletaFirmaLogicaLocal {

  protected static Logger log = Logger.getLogger(ValidacioCompletaFirmaLogicaEJB.class);

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  private EntitatLocal entitatEjb;

  @EJB(mappedName = PluginValidacioFirmesLogicaLocal.JNDI_NAME)
  protected PluginValidacioFirmesLogicaLocal validacioFirmesEjb;

  @Override
  public ValidacioCompletaResponse validateCompletaFirma(
      ValidacioCompletaRequest validacioRequest) throws I18NException {

    String signType;
    String mime;
    String extension;

    switch (validacioRequest.getSignTypeID()) {
      case ConstantsV2.TIPUSFIRMA_PADES:
        extension = "pdf";
        mime = ConstantsV2.MIME_TYPE_PDF;
        signType = FileInfoSignature.SIGN_TYPE_PADES;
      break;

      case ConstantsV2.TIPUSFIRMA_CADES:
        extension = "csig";
        mime = ConstantsV2.MIME_TYPE_BINARY;
        signType = FileInfoSignature.SIGN_TYPE_CADES;
      break;

      case ConstantsV2.TIPUSFIRMA_XADES:
        extension = "xml";
        mime = ConstantsV2.MIME_TYPE_XML;
        signType = FileInfoSignature.SIGN_TYPE_XADES;
      break;

      default:
        // XYZ ZZZ TRA

        throw new I18NException(
            "genapp.comodi",
            "No esta implementada la validacio completa de fitxers firmats"
                + " amb tipus de firma "
                + validacioRequest.getSignTypeID()
                + " (TIPUSFIRMA_PADES=0, TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)");
    }

    // (a) Validar el Fitxer de la Firma
    String nifFirmant = null;
    BigInteger numeroSerieCertificat = null;
    String emissorCertificat = null;
    String subjectCertificat = null;
    Boolean checkValidationSignature = null;
    String perfilDeFirma = null;
    
    ValidateSignatureResponse validateSignatureResponse = null;
    if (validacioRequest.isValidarFitxerFirma()) {

      IPortaFIBDataSource documentDetached = validacioRequest.getDocumentDetachedData();

      if (documentDetached == null) {

        // Si es CAdES o XAdES en la primera firma i es requereix firma explicita o
        // detached, llavors getDocumentDetachedData() valdrà null, però per la
        // validació necessitam el valor del fitxer original
        if (validacioRequest.getSignMode() == ConstantsV2.SIGN_MODE_EXPLICIT) {
          documentDetached = validacioRequest.getOriginalData();
        }

      }

      if (log.isDebugEnabled()) {
        log.info("validateCompletaFirma :: getDocumentDetachedData() => "
          + documentDetached);
      }
      
      validateSignatureResponse = validacioFirmesEjb.validateSignature(
          validacioRequest.getEntitatID(), signType, validacioRequest.getSignatureData(),
          documentDetached, validacioRequest.getLanguageUI());
      
      perfilDeFirma = validateSignatureResponse.getSignProfile();

      SignatureDetailInfo[] sdi = validateSignatureResponse.getSignatureDetailInfo();

      if (sdi != null) {

        // Esbrinar informació de la darrera Firma
        InformacioCertificat info = null;
        Date signDate = null;
        
        for (int i = 0; i < sdi.length; i++) {
          Date d = sdi[0].getSignDate();
          if (d == null) {
            signDate = null;
            info = null;
            break;
          } else {
            if (signDate == null || d.getTime() > signDate.getTime()) {
              signDate = d;
              info = sdi[i].getCertificateInfo();
            }
          }
          
          
        }
        
        if (signDate == null) {
          log.warn("No ha definit alguna de les dates de la firma cosa que "
              + "implica que la informació de la validació pot ser inconsistent."
              + " Omitim la cerca en aquest punt.");
        } else {
        
          log.info("XYZ ZZZ NIF DE LA DARRERA FIRMA => "
              + info.getNifResponsable());
  
          nifFirmant = info.getNifResponsable();
          numeroSerieCertificat = info.getNumeroSerie();
          emissorCertificat = info.getEmissorOrganitzacio();
          subjectCertificat = info.getSubject();
        }
      } else {
        log.warn("El validador de signatures no ha retornat informació del certificat !!!!",
            new Exception());
      }

      checkValidationSignature = true;

    }

    // (b) Validar si s'ha modificat el fitxer original
    Boolean checkDocumentModifications = null;
    X509Certificate certificateLastSign = null;
    if (validacioRequest.isCheckCanviatDocFirmat()) {

      if (validacioRequest.getSignTypeID() == ConstantsV2.TIPUSFIRMA_PADES) {
        

        File tmpDir = new File(FileSystemManager.getFilesPath(), "COMPAREPDF");
        tmpDir.mkdirs();
        
        int posTaulaDeFirmes = validacioRequest.getPosTaulaDeFirmes();
        
        PdfComparator.compare(validacioRequest.getAdaptedData(),
            validacioRequest.getSignatureData(), tmpDir, posTaulaDeFirmes);
        
        checkDocumentModifications = true;

        /*
        X509Certificate cert;

        cert = checkCanviatDocFirmatPDF(validacioRequest.getOriginalData(),
            validacioRequest.getFitxersByNumFirma(), validacioRequest.getSignatureData(),
            validacioRequest.getNumFirmaPortaFIB(), validacioRequest.getNumFirmesOriginals());

        if (nifFirmant == null) {
          nifFirmant = CertificateUtils.getDNI(cert);
        }

        if (numeroSerieCertificat == null) {
          numeroSerieCertificat = cert.getSerialNumber();
        }

        if (emissorCertificat == null) {
          emissorCertificat = cert.getIssuerDN().getName();
        }

        if (subjectCertificat == null) {
          subjectCertificat = cert.getSubjectDN().getName();
        }

        certificateLastSign = cert;

        
        */

      } else {
        String msg = "No esta implementat el xequeig de modificacio de fitxer signat"
            + " amb tipus de firma " + validacioRequest.getSignTypeID()
            + "(TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3)."
            + " Consulti amb l'administrador de PortaFIb el valor de la propietat es.caib.portafib.strictvalidation";
        if (PropietatGlobalUtil.isStrictValidation()) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", msg);
        } else {
          checkDocumentModifications = false;
        }
      }

    }

    // =============================================

    /*
     * String name = names.get(numFirmaPortaFIB + numFirmesOriginals - 1); // names.size() - 1
     * 
     * PdfPKCS7 pk = af.verifySignature(name); // Calendar cal = pk.getSignDate();
     * 
     * log.info(" XYZ ZZZ PdfPKCS7 pk = " + pk);
     * 
     * X509Certificate cert = pk.getSigningCertificate();
     * 
     * log.info(" XYZ ZZZ PdfPKCS7 X509Certificate.cert = " + cert.getSubjectDN());
     * 
     * log.info(" XYZ ZZZ PdfPKCS7 X509Certificate.getSubjectDN() = " + cert.getSubjectDN());
     * 
     * 
     * 
     * ResultatValidacio validacio = validateCertificat(cert);
     * 
     * if (isDebug) { long now = System.currentTimeMillis();
     * log.debug("checkCertificatePADES - Validar Certificat remotament: " + (now - start));
     * start = now; }
     * 
     * InformacioCertificat info = validacio.getInformacioCertificat(); if (info == null) {
     * info = new InformacioCertificat(); }
     * 
     * // Obtenir informació del certificat if (info.getNumeroSerie() == null) {
     * info.setNumeroSerie(cert.getSerialNumber()); }
     * 
     * if (info.getEmissorOrganitzacio() == null) {
     * info.setEmissorOrganitzacio(cert.getIssuerDN().getName()); }
     * 
     * if (info.getSubject() == null) { info.setSubject(cert.getSubjectDN().getName()); }
     * 
     * if (info.getNifResponsable() == null) {
     * info.setNifResponsable(CertificateUtils.getDNI(cert)); }
     * 
     * if (log.isDebugEnabled()) { long now = System.currentTimeMillis();
     * log.debug("checkCertificatePADES - Executed Info Certificat in " + (now - start) +
     * " ms"); log.debug("Numero Serie: " + info.getNumeroSerie()); log.debug("Emissor: " +
     * info.getEmissorOrganitzacio()); log.debug("Subject: " + info.getSubject());
     * log.debug("DNI: " + info.getNifResponsable()); }
     * 
     * return info;
     */

    // =================================================

    // (c) Verificar que el NIF del certificat correspon amb qui tenia que
    // firmar

    // Obtenir informació del certificat
    Boolean checkAdministrationIDOfSigner = null;
    if (validacioRequest.isComprovarNifFirma()) {

      if (validacioRequest.getNifEsperat() == null) {
        // XYZ ZZZ TRA
        String msg = "La configuració de firma exigeix que es comprovi que el NIF"
            + " que ha signat és gual a l'esperat, però en la petició no s'ha"
            + " enviat cap NIF. Consulti amb l'administrador de PortaFIB el valor"
            + " de la propietat es.caib.portafib.strictvalidation";
        if (PropietatGlobalUtil.isStrictValidation()) {
          throw new I18NException("genapp.comodi", msg);
        } else {
          log.warn(msg, new Exception());
          checkAdministrationIDOfSigner = false;
        }

      } else {

        if (validacioRequest.getSignTypeID() == ConstantsV2.TIPUSFIRMA_PADES) {

          if (nifFirmant == null) {

            X509Certificate cert = getLastCertificateOfSignedPdf(
                validacioRequest.getSignatureData(), validacioRequest.getNumFirmaPortaFIB(),
                validacioRequest.getNumFirmesOriginals());

            //if (certificateLastSign != null) {
              certificateLastSign = cert;
            //}

            nifFirmant = CertificateUtils.getDNI(cert);

            if (numeroSerieCertificat == null) {
              numeroSerieCertificat = cert.getSerialNumber();
            }

            if (emissorCertificat == null) {
              emissorCertificat = cert.getIssuerDN().getName();
            }

            if (subjectCertificat == null) {
              subjectCertificat = cert.getSubjectDN().getName();
            }

          }

          log.info("XYZ ZZZ nifFirmant: " + nifFirmant);
          log.info("XYZ ZZZ getNifEsperat(): " + validacioRequest.getNifEsperat());

          LogicUtils.checkExpectedNif(nifFirmant, validacioRequest.getNifEsperat());
          checkAdministrationIDOfSigner = true;

        } else {
          String msg = "No esta implementat la comprovació de que qui ha signat és el mateix que l'esperat"
              + " pel tipus de firma "
              + validacioRequest.getSignTypeID()
              + "(TIPUSFIRMA_XADES=1, TIPUSFIRMA_CADES=2, TIPUSFIRMA_SMIME=3). "
              + " Consulti amb l'administrador de PortaFIB el valor de la propietat es.caib.portafib.strictvalidation";
          if (PropietatGlobalUtil.isStrictValidation()) {

            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", msg);
          } else {
            log.warn(msg, new Exception());
            checkAdministrationIDOfSigner = false;
          }
        }
      }

    }

    // Debug
    final boolean isDebug = log.isDebugEnabled();
    if (isDebug) {
      log.debug("checkCanviatDocFirmat: " + validacioRequest.isCheckCanviatDocFirmat());
      log.debug("NumeroSerieCertificat = " + numeroSerieCertificat);
      log.debug("Emissor = " + emissorCertificat);
      log.debug("Subject = " + subjectCertificat);
      log.debug("NIF = " + nifFirmant);
    }

    log.info("XYZ ZZZ checkAdministrationIDOfSigner: " + checkAdministrationIDOfSigner);
    log.info("XYZ ZZZ checkDocumentModifications: " + checkDocumentModifications);
    log.info("XYZ ZZZ checkValidationSignature: " + checkValidationSignature);

    ValidacioCompletaResponse resposta = new ValidacioCompletaResponse(signType, mime,
        extension, nifFirmant, checkAdministrationIDOfSigner, checkDocumentModifications,
        checkValidationSignature, validateSignatureResponse, numeroSerieCertificat,
        emissorCertificat, subjectCertificat, certificateLastSign, perfilDeFirma);

    //System.gc();

    return resposta;
  }


  /**
   * 
   * @param signedPDFData
   * @param numFirmaPortaFIB
   * @param numFirmesOriginals
   * @return
   * @throws I18NException
   */
  public static X509Certificate getLastCertificateOfSignedPdf(
      IPortaFIBDataSource signedPDFData, int numFirmaPortaFIB, int numFirmesOriginals)
      throws I18NException {

    Security.addProvider(new BouncyCastleProvider());
    ArrayList<String> names;

    PdfReader reader;
    try {
      reader = new PdfReader(signedPDFData.getInputStream());
    } catch (IOException e1) {
      throw new I18NException(e1, "genapp", new I18NArgumentString(
          "Error llegint PDF firmat: " + e1.getMessage()));
    }
    AcroFields af = reader.getAcroFields();
    names = af.getSignatureNames();

    if (names == null || names.size() == 0) {
      // TODO XYZ ZZZ traduir
      throw new I18NException("genapp.comodi",
          "No hi ha informació de signatures en el document firmat");
    }
    {
      if (names.size() != (numFirmaPortaFIB + numFirmesOriginals)) {
        // TODO XYZ ZZZ Traduir
        throw new I18NException("genapp.comodi", "S´esperaven "
            + (numFirmaPortaFIB + numFirmesOriginals)
            + " firmes, però el document pujat conté " + names.size() + " firmes");
      }

    }

    // ================ Validar el certificat de la darrera firma

    String name = names.get(numFirmaPortaFIB + numFirmesOriginals - 1); // names.size() - 1

    PdfPKCS7 pk = af.verifySignature(name);
    // Calendar cal = pk.getSignDate();

    log.info(" XYZ ZZZ PdfPKCS7 pk = " + pk);

    X509Certificate cert = pk.getSigningCertificate();

    log.info(" XYZ ZZZ PdfPKCS7 X509Certificate.cert = " + cert.getSubjectDN());

    log.info(" XYZ ZZZ PdfPKCS7 X509Certificate.getSubjectDN() = " + cert.getSubjectDN());

    return cert;
  }

}
