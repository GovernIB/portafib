package es.caib.portafib.logic;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.plugins.certificate.InformacioCertificat;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.validatesignature.api.SignatureDetailInfo;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;

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

        checkDocumentModifications = true;

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

        String msg = "La configuració de firma exigeix que es comprovi que el NIF"
            + " que ha signat és gual a l'esperat, però en la petició"
            + " no s'ha enviat cap NIF."
            + " Consulti amb l'administrador de PortaFIB el valor de la propietat es.caib.portafib.strictvalidation";
        if (PropietatGlobalUtil.isStrictValidation()) {
          // XYZ ZZZ TRA
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

            if (certificateLastSign != null) {
              certificateLastSign = cert;
            }

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

    System.gc();

    return resposta;
  }

  /**
   * 
   * @param originalData
   * @param fitxersByNumFirma
   * @param signedPDFData
   * @param numFirmaPortaFIB
   * @param numFirmesOriginals
   * @return
   * @throws I18NException
   * @throws Exception
   */
  public static X509Certificate checkCanviatDocFirmatPDF(IPortaFIBDataSource originalData,
      Map<Integer, IPortaFIBDataSource> fitxersByNumFirma,
      IPortaFIBDataSource signedPDFDataDS, int numFirmaPortaFIB, int numFirmesOriginals)
      throws I18NException {

    final boolean isDebug = log.isDebugEnabled();

    long start = isDebug ? System.currentTimeMillis() : 0;

    byte[] signedPDFData = signedPDFDataDS.getByteArray();

    Security.addProvider(new BouncyCastleProvider());
    ArrayList<String> names;

    PdfReader reader;
    try {
      reader = new PdfReader(signedPDFData);

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

        // TODO fields.getTotalRevisions()
        if (isDebug) {
          long now = System.currentTimeMillis();
          log.debug("checkCertificatePADES - Final init: " + (now - start));
          start = now;
        }

        // === Comprovar que el fitxer no s'ha modificat ===
        if (originalData != null) {
          if (numFirmaPortaFIB == 1) {
            // Comprovar fitxer original i pujat
            // byte[] originalData = FileSystemManager.getFileContent(fitxerOriginalID);

            boolean isOK;
            isOK = PdfUtils.checkDocumentWhenFirstSign(originalData.getByteArray(),
                signedPDFData, numFirmesOriginals);

            if (!isOK) {
              // TODO XYZ ZZZ TRA traduir
              String msg = "El document original s'ha modificat durant el procés de firma."
                  + "Abans de realitzar la primera firma del document "
                  + "que s'acaba de pujar, el document PDF original s´ha modificat.";

              log.error(msg);

              throw new I18NException("genapp.comodi", msg);
            }

            if (isDebug) {
              long now = System.currentTimeMillis();
              log.debug("checkCertificatePADES - Primera Firma: " + (now - start));
              start = now;
            }

          } else {
            // =========================================================
            // Comprovar checksum de totes les revisions (amb firma)
            // i comparar-les amb els fitxers guardats
            // =========================================================

            log.info(" XYZ ZZZ |||||||||||||   FIRMA 2 o SUPERIOR (" + numFirmaPortaFIB + ")");

            // XYZ ZZZ
            log.info(" XYZ ZZZ numFirmaXYZ  = " + numFirmaPortaFIB);
            log.info(" XYZ ZZZ numFirmesOriginals = " + numFirmesOriginals);
            log.info(" XYZ ZZZ names.size = " + names.size());
            log.info(" XYZ ZZZ names.tostring = " + Arrays.toString(names.toArray()));
            // XYZ ZZZ
            for (String nomSignatura : names) {
              // Obtenim el hash del fitxer del repositori amb numero de firma numRev
              int numRev = af.getRevision(nomSignatura);
              log.info(" XYZ ZZZ names[" + nomSignatura + "]: REV => " + numRev);
            }

            if (fitxersByNumFirma == null) {
              log.info(" XYZ ZZZ fitxersByNumFirma is NULL");
            } else {
              Set<Integer> revisions = fitxersByNumFirma.keySet();
              for (Integer rev : revisions) {
                log.info(" XYZ ZZZ fitxersByNumFirma[REv:" + rev + "] = FitxerID:"
                    + fitxersByNumFirma.get(rev));
              }
            }

            IPortaFIBDataSource fitxerID = null;
            for (String nomSignatura : names) {

              // Obtenim el hash del fitxer del repositori amb numero de firma numRev
              int numRev = af.getRevision(nomSignatura);

              log.info("XYZ ZZZ FOR numRev for [" + nomSignatura + "]= " + numRev);
              log.info("XYZ ZZZ FOR [numFirmesOriginals != 0 && numRev < numFirmesOriginals] ="
                  + numFirmesOriginals + "!= 0 && " + numRev + " < " + numFirmesOriginals);
              if (numFirmesOriginals != 0 && numRev <= numFirmesOriginals) {
                log.info("XYZ ZZZ FOR CONTINUE 1");
                continue;
              }

              if (numRev == (numFirmaPortaFIB + numFirmesOriginals)) {
                // ignoram la versió actual que s'ha pujat
                log.info("XYZ ZZZ FOR CONTINUE 2");
                continue;
              }

              final int fitxerREV = (numFirmesOriginals == 0) ? numRev
                  : (numRev - numFirmesOriginals);
              log.info("XYZ ZZZ FOR Searching fitxer by index firma PortaFIB = " + fitxerREV);

              fitxerID = fitxersByNumFirma.get(fitxerREV);

              String hashDocRepositori = fitxerID.checkSum();

              // obtenim el hash del fitxer dins el doc
              InputStream is = af.extractRevision(nomSignatura);
              String hashDocFirmat = Utils.getChecksum(is);

              if (!hashDocRepositori.equals(hashDocFirmat)) {
                // TODO XYZ ZZZ traduir

                String msg = "El document s'ha modificat durant el procés de firma:"
                    + "La signatura " + nomSignatura + " de la revisió " + numRev
                    + " del document que s'acaba de pujar, no correspon  "
                    + " amb el fitxer de la posicio/revisio " + fitxerREV
                    + " que té signatura número " + numRev;

                log.error(msg);

                throw new I18NException("genapp.comodi", msg);
              }

              if (isDebug) {
                long now = System.currentTimeMillis();
                log.debug("checkCertificatePADES - Firma N=" + (fitxersByNumFirma.size() + 1)
                    + ": " + (now - start));
                start = now;
              }

            }

            // =========================================================
            // Revisar que no s'hagi creat una revisió (no de firma) entre firmes
            // Es a dir, no volem que mofifiquin el document ni amb revisions
            // =========================================================

            if (fitxerID != null) {
              /*
               * La diferencia del numero de revisions internes PDF entre la penultima firma i
               * aquesta darrera firma ha de ser de 1. Si aquest numero es major significa que
               * s'ha modificat ekl document amb revisions entre les firmes.
               */

              int[] revPenultim;
              revPenultim = PdfUtils.splitPDFRevisions(fitxerID.getByteArray());

              int[] revDarrer = PdfUtils.splitPDFRevisions(signedPDFData);

              if ((revPenultim.length + 1) != revDarrer.length) {
                // Han afegit una nova revisió

                // TODO traduir
                String msg = "S´ha modificat el document abans de la darrera firma."
                    + "\n - Penultim (Final Revisions): " + Arrays.toString(revPenultim)
                    + "\n - Darrer (Final Revisions): " + Arrays.toString(revDarrer);

                log.error(msg);

                throw new I18NException("genapp.comodi", msg);
              }

              if (isDebug) {
                long now = System.currentTimeMillis();
                log.debug("checkCertificatePADES - Estructura PDF: " + (now - start));
                start = now;
              }
            }

          }
        }

      } // Final de Ignore Checks

      // ================ Validar el certificat de la darrera firma

      String name = names.get(numFirmaPortaFIB + numFirmesOriginals - 1); // names.size() - 1

      PdfPKCS7 pk = af.verifySignature(name);
      // Calendar cal = pk.getSignDate();

      log.info(" XYZ ZZZ PdfPKCS7 pk = " + pk);

      X509Certificate cert = pk.getSigningCertificate();

      log.info(" XYZ ZZZ PdfPKCS7 X509Certificate.cert = " + cert.getSubjectDN());

      log.info(" XYZ ZZZ PdfPKCS7 X509Certificate.getSubjectDN() = " + cert.getSubjectDN());

      return cert;

    } catch (I18NException e1) {
      throw e1;
    } catch (Exception e1) {
      throw new I18NException(
          e1,
          "genapp",
          new I18NArgumentString(
              "Error desconegut durant la revisio de si el document PDf original ha sigut modificat despres de la firma: "
                  + e1.getMessage()));
    }

    /*
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

  }

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
