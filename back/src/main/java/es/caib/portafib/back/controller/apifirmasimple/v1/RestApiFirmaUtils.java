package es.caib.portafib.back.controller.apifirmasimple.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apifirmasimple.v1.exceptions.ServerException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.utils.Base64;
import org.jboss.web.tomcat.security.login.WebAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.CodiBarresLocal;
import es.caib.portafib.ejb.CustodiaInfoLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaKeyValue;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.utils.EjbManager;
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
public class RestApiFirmaUtils {

  protected final Logger log = Logger.getLogger(getClass());
  
  
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

  public HttpHeaders addAccessControllAllowOrigin() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    return headers;
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

  protected String  autenticate(HttpServletRequest request) {

    try {
      String authHeader = request.getHeader(javax.ws.rs.core.HttpHeaders.AUTHORIZATION);
      if (authHeader == null || authHeader.trim().length() == 0) {
        final String msg = "No conte capçalera d'autenticació";
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      StringTokenizer st = new StringTokenizer(authHeader);
      if (!st.hasMoreTokens()) {
        final String msg = "La capçalera d'autenticació està buida";
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      String basic = st.nextToken();

      if (!basic.equalsIgnoreCase("Basic")) {
        final String msg = "Tipus d'autenticació no suportat " + basic;
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }

      String credentials = new String(Base64.decode(st.nextToken()));
      log.info("XYZ ZZZ autenticate::Credentials: " + credentials);
      int p = credentials.indexOf(":");
      if (p == -1) {
        final String msg = "Credentials amb format incorrecte: " + credentials;
        log.warn(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }
      String username = credentials.substring(0, p).trim();
      String password = credentials.substring(p + 1).trim();

      log.info("XYZ ZZZ autenticate::username: |" + username + "|");
      log.info("XYZ ZZZ autenticate::password: |" + password + "|");
      log.info("XYZ ZZZ autenticate:: PRE AUTENTICATE " + request.getUserPrincipal());
      
      boolean autenticat;
      //if (Configuracio.isCAIB()) 
      {
        // En entorns CAIB l'autenticació està en BBDD Seycon 
        WebAuthentication pwl = new WebAuthentication();
        autenticat = pwl.login(username, password);
      }
      /*
      else {
        // En entorns CAIB l'autenticació està en BBDD PortaFI, en la taula d'UsuarisAplicació 
        
      }
      */
      log.info("XYZ ZZZ autenticate:: POST AUTENTICATE " + request.getUserPrincipal());
      
      if (autenticat) {

        log.info(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");
        
        UsuariAplicacioLogicaLocal usuariAplicacioEjb = null;
        try {
          usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
        } catch (Throwable e) {
          // TODO traduccio
          final String msg = "No puc accedir al gestor d´obtenció de" +
                  " informació de usuari-aplicacio per " + username + ": " + e.getMessage();
          log.error(" XYZ ZZZ autenticate:: " + msg, e);
          return msg;
        }

        
        UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKeyFull(username);
        if (usuariAplicacio == null) {
          final String msg = "L'usuari " + username
              + " està autenticat però no s'ha donat d'alta en el PortaFIB ";
          log.error(" XYZ ZZZ autenticate:: " + msg);
          return msg;
        }
        
        
        EntitatJPA entitat = usuariAplicacio.getEntitat();
        // Check deshabilitada
        if (!entitat.isActiva()) {        
          final String msg = "L'entitat " + entitat.getNom() 
              +  " a la que està associat l'usuari-aplicacio " + username + " esta deshabilitada.";
          log.error(" XYZ ZZZ autenticate:: " + msg);
          return msg;

        }

        Collection<GrantedAuthority> seyconAuthorities = new ArrayList<GrantedAuthority>();
        User user = new User(username, password, seyconAuthorities);
        
        
        // create a new authentication token for usuariAplicacio
        LoginInfo loginInfo = new LoginInfo(user, usuariAplicacio, 
            entitat, seyconAuthorities);

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
        
        log.info("Inicialitzada Informació de UsuariAPLicacio dins de LoginInfo");
        
        
        return null; // OK

      } else {
        final String msg = "Usuari o contrasenya incorrectes";
        log.error(" XYZ ZZZ autenticate:: " + msg);
        return msg;
      }

    } catch (Exception e) {

      final String msg = "Error desconegut intentant autenticar petició REST: " + e.getMessage();
      log.error(" XYZ ZZZ autenticate:: " + msg, e);
      return msg;
    }

   

  }

  protected String internalGetTransacction() {
    String transactionID;
    synchronized (this) {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
      }

      transactionID = System.currentTimeMillis() + "" + System.nanoTime();
      transactionID = org.fundaciobit.plugins.utils.Base64.encode(transactionID).toLowerCase();
      transactionID = transactionID.replaceAll("=", "");

    }

    if (log.isDebugEnabled()) {
      log.info(" Creada transacció amb ID = |" + transactionID + "|");
    }
    return transactionID;
  }
  

  protected FirmaSimpleSignDocumentsResponse processPassarelaResults(
      PassarelaFullResults fullResults) throws Exception {
    PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

    FirmaSimpleStatus statusSignatureProcess = new FirmaSimpleStatus(
        passarelaSS.getStatus(), passarelaSS.getErrorMessage(),
        passarelaSS.getErrorStackTrace());

    List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();

    List<FirmaSimpleSignatureResult> results = new ArrayList<FirmaSimpleSignatureResult>();

    for (PassarelaSignatureResult psr : passarelaSR) {

      // TODO XYZ ZZZ #165 Si s'ha definit una CUSTODIA llavors s'ha de guardar el document
      // al SISTEMA DE CUSTODIA I retornar informacio al respecte
      //java.lang.String custodyFileID = ;
      //java.lang.String custodyFileURL = ;

      results.add(convertPassarelaSignatureResult2FirmaSimpleSignatureResult(psr));
    }

    FirmaSimpleSignDocumentsResponse fssfr;
    fssfr = new FirmaSimpleSignDocumentsResponse(statusSignatureProcess, results);
    return fssfr;
  }

  public FirmaSimpleSignatureResult convertPassarelaSignatureResult2FirmaSimpleSignatureResult(
      PassarelaSignatureResult psr) throws Exception {
    return new FirmaSimpleSignatureResult(psr.getSignID(), new FirmaSimpleStatus(psr
        .getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace()),
        convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile()), psr.getCustodyFileID(),
        psr.getCustodyFileURL());
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
        Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
        if (signAlgorithmID == null) {
          // Si val null cercar-ho a les DADES DE l'ENTITAT
          signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
        }
  
        log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithmID] = " + signAlgorithmID);
        
        // ALGORISME DE FIRMA
        String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
        log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithm] = " + signAlgorithm);
  
        // Mode de Firma
        final int signMode;
        if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
          // SI és una pADES llavors val implicit
          signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        } else {
          signMode = SignatureUtils.portafibModeSign2ApiModeSign(config.isModeDeFirma());
        }
  
        // TAULA DE FIRMES
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
  
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. #
        // PENDENT: Configuració etiquetes de la Taula de Firmes #176
        // Camp config.getPropietatsTaulaFirmes()
        PassarelaSignaturesTableHeader signaturesTableHeader = null;
  
        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
        // DADES DE l'ENTITAT
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
  
        // TODO #165 De la configuracio de usr-app s'ha obtenir un
        // "CustodiaInfoBean custodiaInfo" i convertir-lo a
        // secureVerificationCodeStampInfo
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

}
