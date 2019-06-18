package es.caib.portafib.back.controller.rest.apifirmasimple.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;

import org.apache.commons.io.IOUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCustodyInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleValidationInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apisib.core.beans.ApisIBKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.caib.portafib.back.controller.rest.RestFirmaUtils;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaKeyValue;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaCustodyInfo;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class RestApiFirmaSimpleUtils<K extends ApisIBKeyValue> extends RestFirmaUtils<K> {

  protected static final String TIPUS_WEB = "WEB";

  protected static final String TIPUS_EN_SERVIDOR = "SERVER";

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  private es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;


  public ResponseEntity<FirmaSimpleError> generateNoAvailablePlugin(String language,
      boolean firma) {
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
      transactionID = org.fundaciobit.pluginsib.core.utils.Base64.encode(transactionID)
          .toLowerCase();
      transactionID = transactionID.replaceAll("=", "");

    }

    if (log.isDebugEnabled()) {
      log.info(" Creada transacció amb ID = |" + transactionID + "|");
    }
    return transactionID;
  }

  
  public FirmaSimpleSignatureResult convertPassarelaSignatureResult2FirmaSimpleSignatureResult(
      PassarelaSignatureResult psr, PassarelaCommonInfoSignature commonInfo,
      PassarelaFileInfoSignature infoSignature, boolean isSignatureInServer) throws Exception {

    FirmaSimpleStatus status = new FirmaSimpleStatus(psr.getStatus(), psr.getErrorMessage(),
        psr.getErrorStackTrace());

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
       */
      String eniTipoFirma = SignatureUtils.getEniTipoFirma(signType, signMode);

      if (eniTipoFirma == null) {
        if (psr.getCustodyInfo() != null && psr.getCustodyInfo().getCustodyFileCSV() != null) {
          eniTipoFirma = "TF01";
        }
      }

      

      // EPES T C X XL A 'BASELINE B-Level' 'BASELINE LT-Level' 'BASELINE LTA-Level' 'BASELINE
      // T-Level' LTV
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
        // EPES, T, C, X, XL, A, BASELINE B-Level, BASELINE T-Level, BASELINE LT-Level,
        // BASELINE
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
      // avanzada, Claves concertadas, Firma electrónica avanzada basada en certificados, CSV,
      // ..
      // TODO XYZ ZZZ Aixó ha de venir del plugin
      String eniSignLevel = null;

      FirmaSimpleCustodyInfo custody = null;
      {
        PassarelaCustodyInfo pci = psr.getCustodyInfo();

        if (pci != null) {
          custody = new FirmaSimpleCustodyInfo(pci.getCustodyFileID(),
              pci.getCustodyFileCSV() , pci.getCustodyFileCSVValidationWeb(),
              pci.getCustodyFileURL(), pci.getCustodyFileCSVGenerationDefinition(),
              pci.getCustodyFileOriginalFileDirectURL(), 
              pci.getCustodyFilePrintableFileDirectUrl(), pci.getCustodyFileEniFileDirectUrl());
        }
      }

      


      FirmaSimpleValidationInfo validation = null;
      {
        PassarelaValidationInfo pvi = psr.getValidationInfo();
        if (pvi != null) {
          validation = new FirmaSimpleValidationInfo(pvi.getCheckAdministrationIDOfSigner(),
              pvi.getCheckDocumentModifications(), pvi.getCheckValidationSignature(),
              pvi.getNoCheckValidationReason());
        }
      }

      final List<FirmaSimpleKeyValue> additionInformation = null;
      final Date signDate = new Date();
      
      // XYZ ZZZ ZZZ  Que passarela retorni dades de la validació de la firma 
      // i que aqui es puguin usar !!!!
      String serialNumberCert = null;
      String issuerCert = null;
      String subjectCert = null;
      
      FirmaSimpleSignerInfo signerInfo;
      signerInfo = new FirmaSimpleSignerInfo(eniRolFirma, eniSignerName, eniSignerAdministrationId, eniSignLevel,
          signDate, serialNumberCert, issuerCert, subjectCert, additionInformation);

      sfi = new FirmaSimpleSignedFileInfo(signOperation, signType, signAlgorithm, signMode,
          signaturesTableLocation, timeStampIncluded, policyIncluded, eniTipoFirma,
          eniPerfilFirma, signerInfo,   custody, validation);
    }

    return new FirmaSimpleSignatureResult(psr.getSignID(), status, file, sfi);

  }



  
  /**
   *  EN SERVIDOR
   * @param transactionID
   * @param virtualTransactionID
   * @param simpleSignature
   * @param perfilFirma
   * @param configEnServidor
   * @return
   * @throws I18NException
   */
  protected PassarelaSignaturesSet convertRestBean2PassarelaBeanServer(String transactionID,
      final String virtualTransactionID, FirmaSimpleSignDocumentRequest simpleSignature,
      PerfilDeFirma perfilFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException, I18NValidationException {
   
    final boolean esFirmaEnServidor = true;
    
    FirmaSimpleSignDocumentsRequest simpleSignaturesSet;
    simpleSignaturesSet  = new FirmaSimpleSignDocumentsRequest(
        simpleSignature.getCommonInfo(),
        new FirmaSimpleFileInfoSignature[] { simpleSignature.getFileInfoSignature() }
        );
    
    LoginInfo loginInfo = LoginInfo.getInstance();
    
    PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID,
            virtualTransactionID, simpleSignaturesSet, esFirmaEnServidor,
            loginInfo, perfilFirma, configBySignID);
    
    return pss;

  }
  
  
  /**
   * WEB
   * 
   * @param transactionID
   * @param virtualTransactionID
   * @param simpleSignature
   * @param perfilWeb
   * @return
   * @throws I18NException
   */
  protected PassarelaSignaturesSet convertRestBean2PassarelaBeanWeb(String transactionID,
      final String virtualTransactionID,
      FirmaSimpleSignDocumentsRequest simpleSignaturesSet,
      PerfilDeFirma perfilWeb,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
          throws I18NException, I18NValidationException {
    
    final boolean esFirmaEnServidor = false;
    
    
    LoginInfo loginInfo = LoginInfo.getInstance();
    
    PassarelaSignaturesSet pss = convertRestBean2PassarelaBean(transactionID, virtualTransactionID,
        simpleSignaturesSet, esFirmaEnServidor, loginInfo, perfilWeb,configBySignID);
    
    return pss;
    
  }
  
  
  
  
  private PassarelaSignaturesSet convertRestBean2PassarelaBean(String transactionID,
      final String virtualTransactionID, FirmaSimpleSignDocumentsRequest simpleSignaturesSet,
      final boolean esFirmaEnServidor, LoginInfo loginInfo,
      //UsuariAplicacioConfiguracioJPA configEnServidor,
      PerfilDeFirma perfilFirma, Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) 
          throws I18NException, I18NValidationException {
    // throws Exception, I18NException {
    String languageUI = "ca";

    final String usuariAplicacioID = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();
    
    final String type = esFirmaEnServidor ? TIPUS_EN_SERVIDOR : TIPUS_WEB;

    try {

      // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
      if (simpleSignaturesSet == null) {
        // Traduir
        throw new I18NException("genapp.comodi","FirmaSimpleSignDocumentsRequest val null");
      }

      FirmaSimpleCommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();
      if (commonInfo == null) {
        throw new I18NException("genapp.comodi","L'atribut commonInfo val null");
      }

      languageUI = commonInfo.getLanguageUI();
      log.info(" XYZ ZZZ LanguageUI() => " + languageUI);
      if (languageUI == null || languageUI.trim().length() == 0) {
        throw new I18NException("genapp.comodi",
            "El camp languageUI de l'atribut commonInfo val null o està buit");
      }

      // TODO XYZ FALTA CHECK
      FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray = simpleSignaturesSet
          .getFileInfoSignatureArray();

      if (simpleFileInfoSignatureArray == null || simpleFileInfoSignatureArray.length == 0) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi","No ha enviat fitxers a firmar.");
      }
      
      EntitatJPA entitatJPA = loginInfo.getEntitat();


      // DADES ESPECIFIQUES DE CADA FIRMA

      PassarelaFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

      String lastCertificate = null;
      PassarelaPolicyInfoSignature lastPolicyInfoSignature = null;
      
      
      
      for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

        FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];

        String signID = sfis.getSignID();

        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign() => " + sfis.getFileToSign());
        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getNom() => " + sfis.getFileToSign().getNom());
        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::sfis.getFileToSign().getData(byte[]) => " + sfis.getFileToSign().getData());

        FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), type,
            virtualTransactionID, signID);

        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign => " + fileToSign);
        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getNom() => " + fileToSign.getNom());
        log.info("XYZ ZZZ \n\n  convertRestBean2PassarelaBean::fileToSign.getData() => " + fileToSign.getData());

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
        UsuariAplicacioConfiguracioJPA config = configBySignID.get(sfis.getSignID());
       


        // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
        final int signOperation = config.getTipusOperacioFirma();

        // TIPUS DE FIRMA
        final String signType = SignatureUtils.convertPortafibSignTypeToApiSignType(config
            .getTipusFirmaID());

        // Algorisme de Firma
        String signAlgorithm = getAlgorismeDeFirmaOfConfig(config, entitatJPA);

        // Mode de Firma
        final int signMode;
        if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
          // SI és una pADES llavors val implicit
          signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        } else {
          signMode = SignatureUtils.convertPortafibSignMode2ApiSignMode(config.isModeDeFirma());
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
      
        secureVerificationCodeStampInfo = getCustodiaOfUsuari(loginInfo.getUsuariAplicacio(), entitatJPA);
        

        fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign,
            signID, name, reason, location, signerEmail, signNumber, languageSign,
            signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
            signaturesTableHeader, secureVerificationCodeStampInfo, useTimeStamp,
            expedientCodi, expedientNom, expedientUrl, procedimentCodi, procedimentNom,
            additionalInformation);
        
        // LES DADES COMUNS DE TOTES LES CONFIGURACIONS HAN DE SER IGUALS
        if (i == 0) {
          lastCertificate = config.getFiltreCertificats();
          lastPolicyInfoSignature = getPoliticaFirmaOfConfig(usuariAplicacioID, config, entitatJPA);
        } else {
          // Comparar lastCertificate amb actual a veure si són iguals
          if (!compare(lastCertificate, config.getFiltreCertificats())) {
         // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "El camp Filtre de Certificats"
                + " de les diferents configuracions del Perfil "
                + perfilFirma.getCodi() 
                + " haurien de tenir el mateix valor i no el tenen.");
            
          }
          
          
          
          // Comparar lastPolicyInfoSignature amb actual a veure si són iguals
          if (!compare(lastPolicyInfoSignature, getPoliticaFirmaOfConfig(usuariAplicacioID, config, entitatJPA))) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "Els camps de Politica de Firma "
                + " de les diferents configuracions del Perfil "
                + perfilFirma.getCodi() 
                + " haurien de tenir el mateix valor i no el tenen.");
          }
          
        }
        

      } // FINAL FOR DE TOTS
      
      // DADES COMUNS

      // final String entitatID = entitatJPA.getEntitatID();

      // Donam de temps 5 minuts més un minut per cada signatura
      // Proporcional al numero de firmes !!!!
      Calendar expiryDate = Calendar.getInstance();
      expiryDate.add(Calendar.MINUTE, 5 + simpleFileInfoSignatureArray.length);

      // ========== FILTRE DE CERTIFICATS
      // Cercar-ho a info de l'usuari-app.Si val null o buit cercar-ho de les
      // DADES DE l'ENTITAT
      String filtreCertificats = lastCertificate;
      if (filtreCertificats == null || filtreCertificats.trim().length() == 0) {
        filtreCertificats = entitatJPA.getFiltreCertificats();
      }

      // ========== POLITICA DE FIRMA
      // Cercar l'ús de la politica de firma i actuar al respecte
      final PassarelaPolicyInfoSignature policyInfoSignature = lastPolicyInfoSignature;

      final String username = commonInfo.getUsername();
      final String administrationID = commonInfo.getAdministrationID();
      PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(
          languageUI, filtreCertificats, username, administrationID, null, policyInfoSignature);

      // OBJECTE FINAL

      PassarelaSignaturesSet pss = new PassarelaSignaturesSet(transactionID,
          expiryDate.getTime(), commonInfoSignature, fileInfoSignatureArray);
      return pss;
    } catch (I18NValidationException ve) {
      throw ve;
    }  catch (I18NException i18ne) {
      throw i18ne;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      // XYZ ZZZ TRA
      throw new I18NException(e, "genapp.comodi", new I18NArgumentString(e.getMessage()));
    }

  }
  
  
  public static boolean compare(String str1, String str2) {
    return (str1 == null ? str2 == null : str1.equals(str2));
  }
  
  
  public static boolean compare(PassarelaPolicyInfoSignature pp1,
      PassarelaPolicyInfoSignature pp2) {
    if (pp1 == null) {
      return pp2 == null;
    } else {
      if (pp2 == null) {
        return false;
      }
      
      if (compare (pp1.getPolicyIdentifier(), pp2.getPolicyIdentifier())
          && compare(pp1.getPolicyIdentifierHash(), pp2.getPolicyIdentifierHash())
          && compare(pp1.getPolicyIdentifierHashAlgorithm(), pp2.getPolicyIdentifierHashAlgorithm())
          && compare(pp1.getPolicyUrlDocument(), pp2.getPolicyUrlDocument())) {
        return true;
      } else {
        return false;
      }
    }
  }
  

  public PassarelaSecureVerificationCodeStampInfo getCustodiaOfUsuari(
      final UsuariAplicacioJPA usuariAplicacio, // final UsuariAplicacioConfiguracio config,
      EntitatJPA entitatJPA) throws I18NException, I18NValidationException {
    final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;

    CustodiaInfo custodiaInfo = custodiaInfoLogicaEjb.getCustodiaUA(usuariAplicacio, null, "Passarela Custòdia", entitatJPA);
    
    if (custodiaInfo == null) {
      return null;
    }

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
    
    
    /* XYZ ZZZ #165
    {
      // CustodiaInfoBean custodiaInfo = config.getCustodiaInfoID()
      int politicaCustodia = usuariAplicacio.getPoliticaCustodia();
      boolean obtenirDeEntitat = false;
      if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT) {
        obtenirDeEntitat = true;
        politicaCustodia = entitatJPA.getPoliticaCustodia();
      }

      switch (politicaCustodia) {

        case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
          secureVerificationCodeStampInfo = null;
        break;
        case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
          // XYZ ZZZ Traduir #165
          throw new I18NException("genapp.comodi", 
              "Politica de Custodia no suportada per PortaFIB (Usuari aplicació "
                  + usuariAplicacio.getUsuariAplicacioID() + ")");

        case ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_ENTITAT_PER_DEFECTE_ACTIU:
        case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO:
          long custodiaInfoID = entitatJPA.getCustodiaInfoID();
          if (obtenirDeEntitat) {
            custodiaInfoID = entitatJPA.getCustodiaInfoID();
          } else {
            custodiaInfoID = usuariAplicacio.getCustodiaInfoID();
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

        case ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_ENTITAT_PER_DEFECTE_NO_ACTIU:
          secureVerificationCodeStampInfo = null;
        break;

        case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
          throw new I18NException("genapp.comodi",
              "Politica de Custodia no suportada per API FIRMA SIMPLE "
              + "(Usuari aplicació " + usuariAplicacio.getUsuariAplicacioID() + ")");

        default:
          // XYZ ZZZ Traduir
          throw new I18NException("genapp.comodi",
              "Politica de Custòdia desconeguda (" + politicaCustodia
              + ") en usuari aplicació " + usuariAplicacio.getUsuariAplicacioID());
      }

    }
    */

    return secureVerificationCodeStampInfo;
  }

  


  

  
  
  
  protected PerfilDeFirma getPerfilDeFirma(FirmaSimpleCommonInfo commonInfo,
      final boolean esFirmaEnServidor) throws I18NException {
    
    String codiPerfil = commonInfo.getSignProfile();
    
    PerfilDeFirma perfil;
    String usrAppID = LoginInfo.getInstance().getUsuariAplicacio().getUsuariAplicacioID();
    if (codiPerfil == null) {
      perfil = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirmaPerApiFirmaSimple(
          usrAppID, esFirmaEnServidor);
      codiPerfil = perfil.getCodi();
      commonInfo.setSignProfile(codiPerfil);
    } else {
      perfil = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirma(usrAppID,
          codiPerfil, esFirmaEnServidor ? ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR
              : ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB);
    }
    return perfil;
  }
  

  /*
  protected RestLoginInfo commonChecks(boolean esFirmaEnServidor, String codiPerfil)
      throws I18NException {

    LoginInfo loginInfo = commonChecks(esFirmaEnServidor);

    // Checks usuari aplicacio
    final UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
    final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
    log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

    
    final PerfilDeFirma perfilFirma = getPerfilDeFirma(esFirmaEnServidor, codiPerfil,
        usuariAplicacioID);

    return new RestLoginInfo(loginInfo, perfilFirma);

  }

  protected PerfilDeFirma getPerfilDeFirma(boolean esFirmaEnServidor, String codiPerfil,
      final String usuariAplicacioID) throws I18NException {
    log.info(" XYZ ZZZ codiPerfil = " + codiPerfil);
    // Cercam informacio del Perfil
    final PerfilDeFirma perfilFirma;
    perfilFirma = configuracioUsuariAplicacioLogicaLocalEjb.getPerfilDeFirma(
        usuariAplicacioID, codiPerfil,
        esFirmaEnServidor ? ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR : ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB);

    log.info(" XYZ ZZZ PERFIL = " + perfilFirma);
    return perfilFirma;
  }
  */
/*
  public ResponseEntity<?> internalGetAvailableProfiles(HttpServletRequest request,
      String locale, final boolean esFirmaEnServidor2) {
    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {

      LoginInfo loginInfo = commonChecks();

      String usuariAplicacioID = loginInfo.getUsuariAplicacio().getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

      
      // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_FIRMASIMPLE
      Where w = null;
      List<PerfilDeFirma> perfils = commonAvailableProfiles(w);


      List<FirmaSimpleAvailableProfile> list = new ArrayList<FirmaSimpleAvailableProfile>();

      for (PerfilDeFirma perfil : perfils) {

        String codiPerfil = perfil.getCodi();

        String descripcio = perfil.getDescripcio();


        // Falta llegir-ho de la BBDD
        FirmaSimpleAvailableProfile ap = new FirmaSimpleAvailableProfile(codiPerfil,
            perfil.getNom(), descripcio);

        list.add(ap);
      }

      FirmaSimpleAvailableProfiles fsap;
      fsap = new FirmaSimpleAvailableProfiles(list);
     

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleAvailableProfiles>(fsap, headers,
          HttpStatus.OK);

      return re;

    } catch (Throwable th) {

      // XYZ ZZZ Traduir
      String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }
*/
  /**
   * 
   * @author anadal
   *
   */
  public class RestLoginInfo {

    public final LoginInfo loginInfo;

    public final PerfilDeFirma perfilDeFirma;

    public RestLoginInfo(LoginInfo loginInfo, PerfilDeFirma perfilDeFirma) {
      super();
      this.loginInfo = loginInfo;
      this.perfilDeFirma = perfilDeFirma;
    }

  }

}
