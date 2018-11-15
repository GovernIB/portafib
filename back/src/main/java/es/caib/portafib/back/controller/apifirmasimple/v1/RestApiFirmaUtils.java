package es.caib.portafib.back.controller.apifirmasimple.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleAvailableProfiles;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleCustodyInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleValidationInfo;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.exceptions.ServerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.caib.portafib.back.controller.common.rest.RestUtils;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.CodiBarresLocal;
import es.caib.portafib.ejb.CustodiaInfoLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaKeyValue;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaCustodyInfo;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
public abstract class RestApiFirmaUtils extends RestUtils {

  protected static final String TIPUS_WEB = "WEB";
  
  protected static final String TIPUS_EN_SERVIDOR = "SERVER";
  
  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;
  
  
  public ResponseEntity<FirmaSimpleError> generateServerError(String msg) {
    return generateServerError(msg, null,HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, HttpStatus status) {
    return generateServerError(msg, null,status);
  }
  
  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th) {
    return generateServerError(msg, th,HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  public ResponseEntity<FirmaSimpleError> generateServerError(String msg, Throwable th, HttpStatus status) {
    String sStackTrace = null;
    if (th != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      sStackTrace = sw.toString();
    }

    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        ServerException.class.getName(), sStackTrace), status);
  }

  public ResponseEntity<FirmaSimpleError> generateNoAvailablePlugin(String language, boolean firma) {
    // TODO XYZ ZZZ Traduir
    String msg;
    if (firma) {
      msg = "No s'ha trobat cap plugin que pugui realitzar la firma o alguna de les firmes sol·licitades.";
    } else {
      msg = "El plugin seleccionat no suporta el proces d'actualització de firma.";
    }
    return new ResponseEntity<FirmaSimpleError>(new FirmaSimpleError(msg,
        NoAvailablePluginException.class.getName()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  

  public FirmaSimpleFile convertFitxerBeanToFirmaSimpleFile(FitxerBean fb) throws Exception {

    if (fb == null) {
      return null;
    }
    InputStream is = null;
    try {
      is = fb.getData().getInputStream();
      byte[] data = IOUtils.toByteArray(is);
      return new FirmaSimpleFile(fb.getNom(), fb.getMime(), data);
    } catch (Exception e) {
      throw e;
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e2) {
        }
      }
    }

  }

  public FitxerBean convertFirmaSimpleFileToFitxerBean(FirmaSimpleFile asf, String type,
      String transactionID, String signID) throws Exception {
    FitxerBean fileToSign = new FitxerBean();
    fileToSign.setDescripcio(null);
    final String mime = asf.getMime();
    fileToSign.setMime(mime);
    fileToSign.setNom(asf.getNom());

    byte[] data = asf.getData();
    fileToSign.setTamany(data.length);

    File folderTransaction = getTransactionFolder(type, transactionID);
    folderTransaction.mkdirs();

    File file = new File(folderTransaction, "IN_" + signID);

    FileOutputStream fos = new FileOutputStream(file);
    fos.write(data);
    fos.flush();
    fos.close();

    FileDataSource fds = new FileDataSource(file);

    fileToSign.setData(new DataHandler(fds));
    return fileToSign;
  }

  public File getTransactionFolder(String type, String transactionID) {
    File folderApiFirmaSimple = new File(FileSystemManager.getFilesPath(), "APIFIRMASIMPLE");

    File folderType = new File(folderApiFirmaSimple, type);

    File folderTransaction = new File(folderType, transactionID);
    return folderTransaction;
  }

  

  protected String internalGetTransacction() {
    String transactionID;
    synchronized (this) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }

      transactionID = System.currentTimeMillis() + "" + System.nanoTime();
      transactionID = org.fundaciobit.pluginsib.core.utils.Base64.encode(transactionID).toLowerCase();
      transactionID = transactionID.replaceAll("=", "");

    }

    if (log.isDebugEnabled()) {
      log.info(" Creada transacció amb ID = |" + transactionID + "|");
    }
    return transactionID;
  }
  

  protected FirmaSimpleSignDocumentsResponse processPassarelaResults(
      PassarelaFullResults fullResults, PassarelaSignaturesSet pss,
      boolean isSignatureInServer) throws Exception {
    PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

    FirmaSimpleStatus statusSignatureProcess = new FirmaSimpleStatus(
        passarelaSS.getStatus(), passarelaSS.getErrorMessage(),
        passarelaSS.getErrorStackTrace());

    List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();

    List<FirmaSimpleSignatureResult> results = new ArrayList<FirmaSimpleSignatureResult>();
    

    
    Map<String, PassarelaFileInfoSignature> infoBySignID = new HashMap<String, PassarelaFileInfoSignature>();
    for(PassarelaFileInfoSignature pfis : pss.getFileInfoSignatureArray()) {
      
      infoBySignID.put(pfis.getSignID(), pfis);

    }
 


    for (PassarelaSignatureResult psr : passarelaSR) {

      // TODO XYZ ZZZ #165 Si s'ha definit una CUSTODIA llavors s'ha de guardar el document
      // al SISTEMA DE CUSTODIA I retornar informacio al respecte
      //java.lang.String custodyFileID = ;
      //java.lang.String custodyFileURL = ;

      results.add(convertPassarelaSignatureResult2FirmaSimpleSignatureResult(psr,
          pss.getCommonInfoSignature(), infoBySignID.get(psr.getSignID()), isSignatureInServer));
    }

    FirmaSimpleSignDocumentsResponse fssfr;
    fssfr = new FirmaSimpleSignDocumentsResponse(statusSignatureProcess, results);
    return fssfr;
  }

  public FirmaSimpleSignatureResult convertPassarelaSignatureResult2FirmaSimpleSignatureResult(
      PassarelaSignatureResult psr, PassarelaCommonInfoSignature commonInfo,
      PassarelaFileInfoSignature infoSignature, boolean isSignatureInServer) throws Exception {
    
    FirmaSimpleStatus status = new FirmaSimpleStatus(psr
        .getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace());
    
    
    FirmaSimpleSignedFileInfo sfi = null;
    FirmaSimpleFile file = null;
    
    if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {
    
      file = convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile());
  
      final int signOperation = infoSignature.getSignOperation();
      final String signType = infoSignature.getSignType();
      final String signAlgorithm = infoSignature.getSignAlgorithm();
      final int signMode = infoSignature.getSignMode();
      final int signaturesTableLocation = infoSignature.getSignaturesTableLocation();
      final boolean timeStampIncluded = infoSignature.isUseTimeStamp();
      final boolean policyIncluded = (commonInfo.getPolicyInfoSignature() != null);

      /**
       * eEMGDE.Firma.TipoFirma.FormatoFirma (eEMGDE17.1.1): TF01 (CSV), TF02 (XAdES internally
       * detached signature), TF03 (XAdES enveloped signature), TF04 (CAdES detached/explicit
       * signature), TF05 (CAdES attached/implicit signature), TF06 (PAdES)
       * 
       * 
       *   
       * 
       * 
       */
      String eniTipoFirma; // TF01, ...
      {
        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
          eniTipoFirma="TF06";
        } else if (psr.getCustodyInfo() != null) {
          
          eniTipoFirma="TF01";
        } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
          if (signMode == FileInfoSignature.SIGN_MODE_IMPLICIT) {
            eniTipoFirma = "TF04"; // (CAdES detached/explicit
          } else {
            eniTipoFirma = "TF05"; // (CAdES attached/implicit signature),
          }
        } else if(FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
          if (signMode == FileInfoSignature.SIGN_MODE_IMPLICIT) {
            eniTipoFirma = "TF03"; // (XAdES enveloped signature)
          } else {
            eniTipoFirma = "TF02"; // (XAdES internally detached signature), ,
          }
        } else {
          eniTipoFirma = null;
        }
        
      }
      
      // EPES T C X XL A 'BASELINE B-Level' 'BASELINE LT-Level' 'BASELINE LTA-Level' 'BASELINE T-Level' LTV
      String eniPerfilFirma;
      if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
        // 2.- Para las firmas PADES: EPES, LTV, BASELINE B-Level, BASELINE T-Level
        // TODO XYZ ZZZ Falta LTV
        if (timeStampIncluded) {
          eniPerfilFirma = "BASELINE T-Level";
        } else if (policyIncluded) {
          eniPerfilFirma = "EPES";
        } else {
          eniPerfilFirma = "BASELINE B-Level";
        }
      } else {
        // 1.- Para las firmas XADES y CADES:
        // EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level, BASELINE LT-Level, BASELINE
        // LTA-Level. 
        // TODO XYZ ZZZ Falta EPES, T, C, X, XL, A, BASELINE LTA-Level. 
        if (timeStampIncluded) {
          eniPerfilFirma = "BASELINE T-Level";
        } else if (policyIncluded) {
          eniPerfilFirma = "EPES";
        } else {
          eniPerfilFirma = "BASELINE B-Level";
        }
        
      }


      // válida, autentica, refrenda, visa, representa, testimonia, ..
      final String eniRolFirma = "firma"; // ???
      
      String eniSignerName;
      String eniSignerAdministrationId;
      if (isSignatureInServer) {
        eniSignerName = null;
        eniSignerAdministrationId = null;
      } else {
        eniSignerName = commonInfo.getUsername();
        eniSignerAdministrationId = commonInfo.getAdministrationID();
      }
      
      // eEMGDE.Firma.NivelFirma (eEMGDE17.5.4) Indicador normalizado que refleja el grado de
      // confianza de la firma utilizado. Ejemplos: Nick, PIN ciudadano, Firma electrónica
      // avanzada, Claves concertadas, Firma electrónica avanzada basada en certificados, CSV, ..
      // TODO XYZ ZZZ Aixó ha de venir del plugin
      String eniSignLevel = null;


      FirmaSimpleCustodyInfo custody = null;
      {
        
        PassarelaCustodyInfo pci = psr.getCustodyInfo();
        
        if (pci != null) {
          custody = new FirmaSimpleCustodyInfo(pci.getCustodyFileID(), 
              pci.getCustodyFileURL(), pci.getCustodyFileCSV(),
              pci.getCustodyFileCSVGenerationDefinition(),
              pci.getCustodyFileCSVValidationWeb());
          
        }
      }
      
      // XYZ ZZZ ZZZ
      FirmaSimpleValidationInfo validation = null;
      {
         PassarelaValidationInfo pvi = psr.getValidationInfo();
         if (pvi != null) {
           validation = new FirmaSimpleValidationInfo(
             pvi.getCheckAdministrationIDOfSigner(), pvi.getCheckDocumentModifications(), 
             pvi.getCheckValidationSignature());
         }
      }

      final List<FirmaSimpleKeyValue> additionInformation = null;

      sfi = new FirmaSimpleSignedFileInfo(
          signOperation, signType, signAlgorithm,
          signMode, signaturesTableLocation, timeStampIncluded,
          policyIncluded, eniTipoFirma, eniPerfilFirma, eniRolFirma,
          eniSignerName, eniSignerAdministrationId, eniSignLevel,
          custody, validation, additionInformation);
    }
    
    return new FirmaSimpleSignatureResult(psr.getSignID(), status, file,sfi); 
        
  }


  

  protected PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
      FirmaSimpleSignDocumentsRequest simpleSignaturesSet,
      final String virtualTransactionID, final boolean esFirmaEnServidor, LoginInfo loginInfo,
      final String usuariAplicacioID, final UsuariAplicacioConfiguracio config,
      CodiBarresLocal codiBarresEjb, CustodiaInfoLocal custodiaInfoEjb)
      throws RestException {
      //throws Exception, I18NException {
    String languageUI = "ca";
    
    final String type = esFirmaEnServidor? TIPUS_EN_SERVIDOR : TIPUS_WEB ;
    
    
    try {
  
      // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
      if (simpleSignaturesSet == null) {
        // Traduir
        throw new RestException("FirmaSimpleSignDocumentsRequest val null");
      }
          
      FirmaSimpleCommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();
      if (commonInfo == null) {
        throw new RestException("L'atribut commonInfo val null");
      }
  
      languageUI = commonInfo.getLanguageUI();
      log.info(" XYZ ZZZ LanguageUI() => " + languageUI);
      if (languageUI == null || languageUI.trim().length() == 0) {
        throw new RestException("El camp languageUI de l'atribut commonInfo val null o està buit");
      }
  
      // TODO XYZ FALTA CHECK
      FirmaSimpleFileInfoSignature[] fitxersAFirmar = simpleSignaturesSet
          .getFileInfoSignatureArray();
      
      if (fitxersAFirmar == null || fitxersAFirmar.length == 0) {
        // XYZ ZZZ 
        throw new RestException("No ha enviat fitxers a firmar.");
      }
  
      if (esFirmaEnServidor) {
        Integer max = config.getMaxFirmesEnServidor();;
        if (max != null) {
          if (fitxersAFirmar.length > max) {
            // XYZ ZZZ Traduir !!!!
            throw new RestException("El màxim de fitxers a firmar en una transacció és de "
                + max);
          }
        }
      }
  
  
      EntitatJPA entitatJPA = loginInfo.getEntitat();
  
      //final String entitatID = entitatJPA.getEntitatID();
  
      // Vull suposar que abans de 10 minuts haurà firmat
      // Proporcional al numero de firmes !!!!
      Calendar expiryDate = Calendar.getInstance();
      expiryDate.add(Calendar.MINUTE, 5 + fitxersAFirmar.length);
  
      // ========== FILTRE DE CERTIFICATS
      // Cercar-ho a info de l'usuari-app.Si val null o buit cercar-ho de les
      // DADES DE l'ENTITAT
      String filtreCertificats = config.getFiltreCertificats();
      if (filtreCertificats == null || filtreCertificats.trim().length() == 0) {
        filtreCertificats = entitatJPA.getFiltreCertificats();
      }
  
      // ========== POLITICA DE FIRMA
      // Cercar l'ús de la politica de firma i actuar al respecte
      final PassarelaPolicyInfoSignature policyInfoSignature = getPoliticaFirmaOfConfig(
          usuariAplicacioID, config, entitatJPA);
  
      final String username = commonInfo.getUsername();
      final String administrationID = commonInfo.getAdministrationID();
      PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(
          languageUI, filtreCertificats, username, administrationID, null, policyInfoSignature);
  
      FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray;
      simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();
  
      PassarelaFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];
  
      // final FileInfoSignature[] aFirmar = new
      // FileInfoSignature[simpleFileInfoSignatureArray.length];
  
      for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {
  
        FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];
  
        String signID = sfis.getSignID();
  
        FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), type,
            virtualTransactionID, signID);
  
        // XYZ ZZZ FALTA ENCARA NO SUPORTAT
        FitxerBean prevSign = null;
        if (sfis.getPreviusSignatureDetachedFile() != null) {
          prevSign = convertFirmaSimpleFileToFitxerBean(
              sfis.getPreviusSignatureDetachedFile(), type, virtualTransactionID, signID);
        }

        String name = sfis.getName();
        String reason = sfis.getReason();
        String location = sfis.getLocation();
        String signerEmail = sfis.getSignerEmail();
        int signNumber = sfis.getSignNumber();
        String languageSign = sfis.getLanguageSign();
  
        final String expedientCodi = sfis.getExpedientCodi();
  
        final String expedientNom = sfis.getExpedientNom();
  
        final String expedientUrl = sfis.getExpedientUrl();
  
        final String procedimentCodi = sfis.getProcedimentCodi();
  
        final String procedimentNom = sfis.getProcedimentNom();
  
        final List<PassarelaKeyValue> additionalInformation;
        {
          List<FirmaSimpleKeyValue> additionalInfoList = sfis.getAdditionalInformation();
          if (additionalInfoList == null || additionalInfoList.size() == 0) {
            additionalInformation = null;
          } else {
            additionalInformation = new ArrayList<PassarelaKeyValue>();
            for (FirmaSimpleKeyValue firmaSimpleKeyValue : additionalInfoList) {
              additionalInformation.add(new PassarelaKeyValue(firmaSimpleKeyValue.getKey(),
                  firmaSimpleKeyValue.getValue()));
            }
          }
        }
  
        // ============ FIRMA
  
        // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
        final int signOperation = config.getTipusOperacioFirma();
  
        // TIPUS DE FIRMA
        final String signType = SignatureUtils.portafibSignTypeToApiSignType(config
            .getTipusFirmaID());

        // Algorisme de Firma
        String signAlgorithm = getAlgorismeDeFirmaOfConfig(config, entitatJPA);
  
        // Mode de Firma
        final int signMode;
        if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
          // SI és una pADES llavors val implicit
          signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        } else {
          signMode = SignatureUtils.portafibModeSign2ApiModeSign(config.isModeDeFirma());
        }
  
        // TAULA DE FIRMES
        final int signaturesTableLocation = getSignaturesTableLocationOfConfig(
            usuariAplicacioID, config, entitatJPA);
  
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. #
        // PENDENT: Configuració etiquetes de la Taula de Firmes #176
        // Camp config.getPropietatsTaulaFirmes()
        PassarelaSignaturesTableHeader signaturesTableHeader = null;
  
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
        // DADES DE l'ENTITAT
        final boolean useTimeStamp = getUseTimestampOfConfig(usuariAplicacioID, config,
            entitatJPA);
  
        // TODO #165 De la configuracio de usr-app s'ha obtenir un
        // "CustodiaInfoBean custodiaInfo" i convertir-lo a
        // secureVerificationCodeStampInfo
        final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;
        secureVerificationCodeStampInfo = getCustodiaOfConfig(usuariAplicacioID, config, entitatJPA);
  
        fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign,
            signID, name, reason, location, signerEmail, signNumber, languageSign,
            signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
            signaturesTableHeader, secureVerificationCodeStampInfo, useTimeStamp,
            expedientCodi, expedientNom, expedientUrl, procedimentCodi, procedimentNom,
            additionalInformation);
        /*
         * 
         * File fileToSign2 = null; String mimeType = fileToSign.getMime();
         * 
         * SignaturesTableHeader signaturesTableHeader2 = null; PdfVisibleSignature
         * pdfVisibleSignature = null; SecureVerificationCodeStampInfo
         * secureVerificationCodeStampInfo2 = null; ITimeStampGenerator timeStampGenerator =
         * null;
         * 
         * aFirmar[i] = new FileInfoSignature(signID, fileToSign2, mimeType, name, reason,
         * location, signerEmail, signNumber, languageSign, signType, signAlgorithm, signMode,
         * signaturesTableLocation, signaturesTableHeader2, pdfVisibleSignature,
         * secureVerificationCodeStampInfo2, useTimeStamp, timeStampGenerator);
         */
      } // FINAL FOR DE TOTS
  
      
  
      PassarelaSignaturesSet pss = new PassarelaSignaturesSet(transactionID,
          expiryDate.getTime(), commonInfoSignature, fileInfoSignatureArray);
      return pss;
    } catch (RestException re) {
      throw re;
    } catch (I18NException i18ne) {
      // XYZ ZZZ String idioma = simpleSignaturesSet.getCommonInfo().getLanguageUI();
  
      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));
  
      log.error(msg, i18ne);
  
      throw new RestException(msg);
    } catch(Exception e) {
      log.error(e.getMessage(), e);
      throw new RestException(e.getMessage());
    }
    
  }

  
  public PassarelaSecureVerificationCodeStampInfo getCustodiaOfConfig(
      final String usuariAplicacioID, final UsuariAplicacioConfiguracio config,
      EntitatJPA entitatJPA) throws RestException, I18NException {
    final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;
    {
      // CustodiaInfoBean custodiaInfo = config.getCustodiaInfoID()
      int politicaCustodia = config.getPoliticaCustodia();
      boolean obtenirDeEntitat = false;
      if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_EL_DEFINIT_EN_ENTITAT) {
        obtenirDeEntitat = true;
        politicaCustodia = entitatJPA.getPoliticaCustodia();
      }

      switch (politicaCustodia) {

        case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
          secureVerificationCodeStampInfo = null;
          break;
        case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
          // XYZ ZZZ Traduir #165
          throw new RestException("Politica de Custodia no suportada per PortaFIB (Usuari aplicació "
              + usuariAplicacioID + ")");

        case ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_ACTIU:
        case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA:
          long custodiaInfoID = entitatJPA.getCustodiaInfoID();
          if (obtenirDeEntitat) {
            custodiaInfoID = entitatJPA.getCustodiaInfoID();
          } else {
            custodiaInfoID = config.getCustodiaInfoID();
          }

          CustodiaInfo custodiaInfo = custodiaInfoEjb.findByPrimaryKey(custodiaInfoID);

          secureVerificationCodeStampInfo = new PassarelaSecureVerificationCodeStampInfo();

          secureVerificationCodeStampInfo.setBarCodePosition((int) custodiaInfo
              .getCodiBarresPosicioPaginaID());
          secureVerificationCodeStampInfo.setBarCodeText(custodiaInfo.getCodiBarresText());

          String codiBarresID = custodiaInfo.getCodiBarresID();

          String codiBarresNom = codiBarresEjb.executeQueryOne(CodiBarresFields.NOM,
              CodiBarresFields.CODIBARRESID.equal(codiBarresID));

          if (codiBarresNom == null) {
            // TODO Traduir XYZ ZZZ
            String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom "
                + codiBarresNom;
            throw new I18NException("error.unknown", msg);
          }

          secureVerificationCodeStampInfo.setBarCodeType(codiBarresNom);

          long messagePosition = custodiaInfo.getMissatgePosicioPaginaID();
          secureVerificationCodeStampInfo.setMessagePosition((int) messagePosition);
          secureVerificationCodeStampInfo.setMessage(custodiaInfo.getMissatge());
          secureVerificationCodeStampInfo.setPages(custodiaInfo.getPagines());
          break;

        case ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_NO_ACTIU:
          secureVerificationCodeStampInfo = null;
          break;

        case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
          throw new RestException("Politica de Custodia no suportada per API FIRMA SIMPLE "
              + "(Usuari aplicació " + usuariAplicacioID + ")");

        default:
          // XYZ ZZZ Traduir
          throw new RestException("Politica de Custòdia desconeguda (" + politicaCustodia
              + ") en usuari aplicació " + usuariAplicacioID);
      }

    }
    
    return secureVerificationCodeStampInfo;
  }
  
  
  public String getAlgorismeDeFirmaOfConfig(final UsuariAplicacioConfiguracio config,
      EntitatJPA entitatJPA) throws I18NException {
    Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
    if (signAlgorithmID == null) {
      // Si val null cercar-ho a les DADES DE l'ENTITAT
      signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
    }
 
    log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithmID] = " + signAlgorithmID);
    
    // ALGORISME DE FIRMA
    String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
    log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithm] = " + signAlgorithm);
    return signAlgorithm;
  }

  public boolean getUseTimestampOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws RestException {
    final boolean useTimeStamp;
    {
      int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();
 
      boolean obtenirDeEntitat = false;
 
      if (politicaSegellatDeTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT) {
        obtenirDeEntitat = true;
        politicaSegellatDeTemps = entitatJPA.getPoliticaSegellatDeTemps();
      }
 
      switch (politicaSegellatDeTemps) {
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
        useTimeStamp = false;
        break;
 
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
        useTimeStamp = true;
        break;
 
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
        useTimeStamp = true;
        break;
      case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
        useTimeStamp = false;
        break;
 
      default:
        // XYZ ZZZ Traduir
        throw new RestException("Politica de segellat de temps desconeguda ("
            + politicaSegellatDeTemps + ") en usuari aplicació " + usuariAplicacioID);
      }
    }
    return useTimeStamp;
  }

  public PassarelaPolicyInfoSignature getPoliticaFirmaOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws RestException {
    final PassarelaPolicyInfoSignature policyInfoSignature;
    {
      int usPoliticaDeFirma = config.getUsPoliticaDeFirma();
      boolean obtenirDeEntitat = false;
      if (usPoliticaDeFirma == ConstantsPortaFIB.US_POLITICA_DE_FIRMA_DEFINIT_EN_ENTITAT) {
        usPoliticaDeFirma = entitatJPA.getUsPoliticaDeFirma();
        obtenirDeEntitat = true;
      }
 
      switch (usPoliticaDeFirma) {
 
      // 0 => no usar politica de firma,
      case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_NO_USAR:
        policyInfoSignature = null;
        break;
 
      // 1=> usar politica d'aquesta configuracio
      case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT:
        if (obtenirDeEntitat) {
          policyInfoSignature = new PassarelaPolicyInfoSignature(
              entitatJPA.getPolicyIdentifier(), entitatJPA.getPolicyIdentifierHash(),
              entitatJPA.getPolicyIdentifierHashAlgorithm(),
              entitatJPA.getPolicyUrlDocument());
        } else {
          policyInfoSignature = new PassarelaPolicyInfoSignature(
              config.getPolicyIdentifier(), config.getPolicyIdentifierHash(),
              config.getPolicyIdentifierHashAlgorithm(), config.getPolicyUrlDocument());
        }
        break;
 
      // 2 => L'usuari web o usuari-app elegeixen la politica de firma
      case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OPCIONAL:
        // XYZ ZZZ Traduir
        throw new RestException("Ús de Politica de Firma no suportada en API Firma Simple ("
            + usPoliticaDeFirma + ") en usuari aplicació " + usuariAplicacioID);
 
      default:
        // XYZ ZZZ Traduir
        throw new RestException("Ús de Politica de Firma desconeguda ("
            + usPoliticaDeFirma + ") en usuari aplicació " + usuariAplicacioID);
 
      }
    }
    if (policyInfoSignature == null) {
      log.info("No usam politica de firma");
    } else {
      log.info("Usam politica de firma: " + policyInfoSignature.getPolicyIdentifier() + "("
          + policyInfoSignature.getPolicyUrlDocument() + ")");
    }
    return policyInfoSignature;
  }

  public static int getSignaturesTableLocationOfConfig(final String usuariAplicacioID,
      final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) throws RestException {
    final int signaturesTableLocation; // =
                                       // FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
      int politicaTaulaDeFirmes = config.getPoliticaTaulaFirmes();
      boolean obtenirDeEntitat = false;
      if (politicaTaulaDeFirmes == ConstantsPortaFIB.POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT) {
        politicaTaulaDeFirmes = entitatJPA.getPoliticaTaulaFirmes();
        obtenirDeEntitat = true;
      }
 
      switch (politicaTaulaDeFirmes) {
      // 0 no es permet taules de firmes
 
      case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET:
        signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;
 
      // 1 obligatori politica definida en la configuració d'usuari aplicació o entitat
      case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT:
        if (obtenirDeEntitat) {
          signaturesTableLocation = entitatJPA.getPosicioTaulaFirmes();
        } else {
          signaturesTableLocation = config.getPosicioTaulaFirmesID();
        }
        break;
 
      // 2 opcional, per defecte el definit a l'entitat o conf. de usuari aplicacio
      case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF:
        // XYZ ZZZ Que faig: sense taula de firmes o llançar una excepció indicant
        // que aquest valor no es vàlid per API Firma Simple ??
        signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        break;
 
      default:
        // XYZ ZZZ Traduir
        throw new RestException("Politica de Taules de Firmes desconeguda ("
            + politicaTaulaDeFirmes + ") en usuari aplicació " + usuariAplicacioID);
      }
 
    } else {
      // XADES, CADES, ...
      signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
    }
    return signaturesTableLocation;
  }
  
  
  /**
   *
   * @param esFirmaEnServidor
   * @return
   * @throws RestException
   * @throws I18NException
   */
  protected RestLoginInfo commonChecks(boolean esFirmaEnServidor) 
      throws RestException, I18NException {

      LoginInfo loginInfo = LoginInfo.getInstance();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        // TODO XYZ ZZZ Traduir
        throw new RestException("Aquest servei només el poden fer servir els usuari-aplicació");
      }

      // Checks usuari aplicacio
      final UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
      final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

      // Cercam que tengui configuracio
      final UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacio(usuariAplicacioID);

      if (esFirmaEnServidor) {
        Long pluginId = config.getPluginFirmaServidorID();
        if (pluginId == null) {
          // XYZ ZZZ Traduir
          throw new RestException("No es permeten firmes en servidor a través de l'usuari aplicació "
              + usuariAplicacioID + "(Consulti amb l'administrador de PortaFIB)");
        }
      }

     return new RestLoginInfo(loginInfo, config);

  }
  
  


  public ResponseEntity<?> internalGetAvailableProfiles(HttpServletRequest request,
      String locale, final boolean esFirmaEnServidor) {
    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {

      
      RestLoginInfo restLoginInfo = commonChecks(esFirmaEnServidor);
      
      

      LoginInfo loginInfo = restLoginInfo.loginInfo;

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        throw new Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
      }

      // Checks usuari aplicacio
      log.info(" XYZ ZZZ Usuari-APP = " + loginInfo.getUsuariAplicacio());
      EntitatJPA entitatJPA = loginInfo.getUsuariAplicacio().getEntitat();
      UsuariAplicacioConfiguracio config = restLoginInfo.config;
      String usuariAplicacioID = config.getUsuariAplicacioID();
      
      Locale loc = new Locale(locale);
      
      StringBuffer str = new StringBuffer();
      // Tipus Firma i Politica
      String signType;
      {
        signType = SignatureUtils.portafibSignTypeToApiSignType(config.getTipusFirmaID());
        
        String politica;
        if (getPoliticaFirmaOfConfig(usuariAplicacioID, config, entitatJPA) == null) {
          politica = "BES";
        } else {
          politica = "EPES";
        }
        str.append(I18NLogicUtils.tradueix(loc,"profile.tipusfirma", signType,politica));
      }
      // Algorisme de Firma
      {
        String algorismeDeFirma = getAlgorismeDeFirmaOfConfig(config, entitatJPA);
        str.append(I18NLogicUtils.tradueix(loc,"profile.algorisme", algorismeDeFirma));
      }
      // taula de firmes
      {
        int postaulaFirmes = getSignaturesTableLocationOfConfig(usuariAplicacioID,
            config,  entitatJPA);
        str.append(I18NLogicUtils.tradueix(loc,"profile.tauladefirmes", 
            I18NLogicUtils.tradueix(loc, "tauladefirmes." + postaulaFirmes)));
      }
      // Segell de temps
      {
        if (getUseTimestampOfConfig(usuariAplicacioID, config, entitatJPA)) {
          str.append(I18NLogicUtils.tradueix(loc,"profile.segelldetemps"));
        }
      }
      // Custòdia
      {
        if (getCustodiaOfConfig(usuariAplicacioID, config, entitatJPA) != null) {
          str.append(I18NLogicUtils.tradueix(loc,"profile.custodia"));
        }
      }
      

      // XYZ ZZZ ZZZ Falta llegir-ho de la BBDD
      FirmaSimpleAvailableProfile ap = new FirmaSimpleAvailableProfile();
      ap.setName(signType);
      ap.setDescription(str.toString()); 

      List<FirmaSimpleAvailableProfile> list = new ArrayList<FirmaSimpleAvailableProfile>();
      list.add(ap);
      
      FirmaSimpleAvailableProfiles fsap = new FirmaSimpleAvailableProfiles(list);
      
      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleAvailableProfiles>(fsap, headers, HttpStatus.OK);

      return re;
  
    } catch (Throwable th) {
  
      // XYZ ZZZ Traduir
      String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();
  
      log.error(msg, th);
  
      return generateServerError(msg, th);
    }
  }
  
  
  /**
   * 
   * @author anadal
   *
   */
  public class RestLoginInfo {
    
    public final LoginInfo loginInfo;
    
    public final UsuariAplicacioConfiguracio config;

    public RestLoginInfo(LoginInfo loginInfo,
        UsuariAplicacioConfiguracio config) {
      super();
      this.loginInfo = loginInfo;
      this.config = config;
    }

  }

}
