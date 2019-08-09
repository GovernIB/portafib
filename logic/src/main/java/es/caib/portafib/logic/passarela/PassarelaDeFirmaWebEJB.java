package es.caib.portafib.logic.passarela;

import es.caib.portafib.ejb.CodiBarresLocal;
import es.caib.portafib.ejb.EstadisticaLocal;
import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.LogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.ValidacioCompletaRequest;
import es.caib.portafib.logic.utils.ValidacioCompletaResponse;
import es.caib.portafib.logic.utils.datasource.ByteArrayDataSource;
import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.plugins.validatesignature.api.ValidationStatus;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PassarelaDeFirmaWebEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class PassarelaDeFirmaWebEJB extends AbstractPassarelaDeFirmaEJB<ISignatureWebPlugin>
    implements PassarelaDeFirmaWebLocal {

  @EJB(mappedName = CodiBarresLocal.JNDI_NAME)
  protected CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = EstadisticaLocal.JNDI_NAME)
  protected EstadisticaLocal estadisticaEjb;

  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME, beanName = "ModulDeFirmaWebLogicaEJB")
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
  protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;

  protected SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  protected AbstractPluginLogicaLocal<ISignatureWebPlugin> getModulDeFirmaEJB() {
    return modulDeFirmaEjb;
  }

  @Override
  public String startTransaction(PassarelaSignaturesSet signaturesSet, String entitatID,
      boolean fullView, UsuariAplicacioJPA usuariAplicacio, PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID) throws I18NException,
      I18NValidationException {

    final String urlBase = LogicUtils.getUrlBase(perfilDeFirma);

    // Validar
    SignaturesSetBeanValidator ssbv = new SignaturesSetBeanValidator(validator, this,
        entitatID);
    final boolean isNou = true;
    ssbv.throwValidationExceptionIfErrors(signaturesSet, isNou);

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    // Tiquet # 186
    if (PropietatGlobalUtil.isDisabledSignaturesTable()) {
      if (signaturesSet != null) {
        PassarelaFileInfoSignature[] files = signaturesSet.getFileInfoSignatureArray();
        for (PassarelaFileInfoSignature passarelaFileInfoSignature : files) {
          passarelaFileInfoSignature
              .setSignaturesTableLocation(ConstantsV2.TAULADEFIRMES_SENSETAULA); // = 0
          passarelaFileInfoSignature.setSignaturesTableHeader(null);
        }
      }
    }

    // Canviar llista buida per NULL
    List<Long> filterPluginsByIDs = signaturesSet.getCommonInfoSignature()
        .getAcceptedPlugins();
    if (filterPluginsByIDs != null && filterPluginsByIDs.size() == 0) {
      signaturesSet.getCommonInfoSignature().setAcceptedPlugins(null);
    }

    try {

      Locale locale;

      try {
        locale = new Locale(signaturesSet.getCommonInfoSignature().getLanguageUI());
      } catch (Throwable e) {
        locale = new Locale("ca");
      }

      PassarelaFileInfoSignature[] fileInfoSignatureArray = signaturesSet
          .getFileInfoSignatureArray();
      int[] originalNumberOfSignsArray = new int[fileInfoSignatureArray.length];

      int count = 0;
      for (int i = 0; i < fileInfoSignatureArray.length; i++) {

        PassarelaFileInfoSignature pfis = fileInfoSignatureArray[i];

        final String signID = pfis.getSignID();
        File original = getFitxerOriginalPath(signaturesSetID, signID);

        // obtenir ruta on guardar fitxer adaptat
        File adaptat = getFitxerAdaptatPath(signaturesSetID, signID);
        originalNumberOfSignsArray[count] = processFileToSign(locale, entitatID, pfis,
            original, adaptat, usuariAplicacio);
        count++;
      }

      // Guardar
      storeSignaturesSet(new PassarelaSignaturesSetWebInternalUse(entitatID,
          originalNumberOfSignsArray, fullView, signaturesSet,
          usuariAplicacio.getUsuariAplicacioID(), urlBase, perfilDeFirma, configBySignID));

    } catch (I18NException i18n) {
      deleteSignaturesSet(signaturesSetID);
      throw i18n;
    }

    // URL on Iniciar el proces de firma
    final String absoluteURL = urlBase + PASSARELA_CONTEXTPATH + "/start/" + signaturesSetID;
    if (log.isDebugEnabled()) {
      log.debug("Inici de TRANSACCIO PORTAFIB = " + absoluteURL);
    }

    return absoluteURL;
  }

  @Override
  public List<String> getSupportedBarCodeTypes() throws I18NException {
    return codiBarresEjb.executeQuery(CodiBarresFields.NOM, null);
  }

  @Override
  public PassarelaSignatureStatus getStatusTransaction(String transactionID)
      throws I18NException {

    PassarelaSignaturesSetWebInternalUse ss = readSignaturesSet(transactionID);

    if (ss == null) {
      log.error("getStatusTransaction(" + transactionID + ") == NULL !!!!! (caducat ?????)");
      return null;
    } else {
      if (log.isDebugEnabled()) {
        log.error("getStatusTransaction(" + transactionID + ") == " + ss.getStatus());
      }
      return ss;
    }
  }

  @Override
  public PassarelaSignaturesSetWebInternalUse getSignaturesSetFullByTransactionID(
      String transactionID) throws I18NException {
    PassarelaSignaturesSetWebInternalUse ss = readSignaturesSet(transactionID);
    return ss;
  }

  @Override
  public List<PassarelaSignatureResult> getSignatureResults(String transactionID,
      boolean addFiles) throws I18NException {
    PassarelaSignaturesSetWebInternalUse ssf = readSignaturesSet(transactionID);
    if (ssf == null) {
      return null;
    }

    Map<String, PassarelaFileInfoSignature> fileInfoSignMap = new HashMap<String, PassarelaFileInfoSignature>();
    for (PassarelaFileInfoSignature pfis : ssf.getSignaturesSet().getFileInfoSignatureArray()) {
      fileInfoSignMap.put(pfis.getSignID(), pfis);
    }

    Map<String, PassarelaSignatureStatusWebInternalUse> map = ssf.getStatusBySignatureID();
    Set<String> signsID = map.keySet();
    List<PassarelaSignatureResult> list = new ArrayList<PassarelaSignatureResult>();
    for (String id : signsID) {
      PassarelaSignatureStatusWebInternalUse ss = map.get(id);

      PassarelaFileInfoSignature pfis = fileInfoSignMap.get(id);

      PassarelaSignatureResult psr = convertToPassarelaSignatureResult(addFiles, ss, pfis);
      list.add(psr);
    }

    return list;

  }

  protected PassarelaSignatureResult convertToPassarelaSignatureResult(boolean addFiles,
      PassarelaSignatureStatusWebInternalUse ss, PassarelaFileInfoSignature pfis) {

    final String id = pfis.getSignID();

    PassarelaSignatureResult psr = new PassarelaSignatureResult();

    // XYZ ZZZ Aqui fa falta obtenir la informació de Custòdia
    // psr.setCustodyInfo(custodyInfo);

    psr.setStatus(ss.getStatus());
    psr.setErrorMessage(ss.getErrorMessage());
    psr.setErrorStackTrace(ss.getErrorStackTrace());
    psr.setSignID(id);

    if (addFiles) {
      if (ss.getFitxerFirmat() != null && ss.getFitxerFirmat().exists()) {
        DataSource fds = new javax.activation.FileDataSource(ss.getFitxerFirmat());

        FitxerBean signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getNom());
        signedFile.setMime(ConstantsV2.MIME_TYPE_PDF);
        signedFile.setTamany(ss.getFitxerFirmat().length());
        signedFile.setData(new DataHandler(fds));
        signedFile.setDescripcio("Signed Document");

        psr.setSignedFile(signedFile);
      }
    }

    // validationInfo
    ValidacioCompletaResponse infoValidacio = ss.getInfoValidacio();

    log.info(" XYZ ZZZ ZZZ  **** infoValidacio = ss.getInfoValidacio() => " + infoValidacio);

    PassarelaValidationInfo pvi = null;
    if (infoValidacio != null) {
      pvi = new PassarelaValidationInfo(infoValidacio.getCheckAdministrationIDOfSigner(),
          infoValidacio.getCheckDocumentModifications(),
          infoValidacio.getCheckValidationSignature(), null);
    }
    psr.setValidationInfo(pvi);

    return psr;
  }

  @Override
  public PassarelaSignatureResult getSignatureResult(String transactionID, String signID)
      throws I18NException {
    PassarelaSignaturesSetWebInternalUse ssf = readSignaturesSet(transactionID);
    if (ssf == null) {
      return null;
    }

    PassarelaSignatureStatusWebInternalUse ss = ssf.getStatusBySignatureID().get(signID);

    for (PassarelaFileInfoSignature pfis : ssf.getSignaturesSet().getFileInfoSignatureArray()) {
      if (pfis.getSignID().equals(signID)) {
        return convertToPassarelaSignatureResult(true, ss, pfis);
      }
    }

    return null;

  }

  @Override
  public void closeTransaction(String transactionID) {
    deleteSignaturesSet(transactionID);
  }

  @Override
  public PassarelaSignaturesSetWebInternalUse finalProcesDeFirma(String transactionID,
      SignaturesSetWeb ss) throws I18NException, IOException {

    log.info(" XYZ ZZZ ZZZ \n\n finalProcesDeFirma => ENTRA\n\n");

    StatusSignaturesSet sss = ss.getStatusSignaturesSet();

    //StatusSignaturesSet statusFinal = null;

    Map<String, File> fitxersFirmatsBySignID = new HashMap<String, File>();

    PassarelaSignaturesSetWebInternalUse ssf;

    ssf = getSignaturesSetFullByTransactionID(transactionID);
    if (ssf == null) {
      // "Ha tardat massa temps en firmar. Torni a intentar-ho."
      throw new I18NException("firmar.tempsexcedit");
    }

    Map<String, PassarelaSignatureStatusWebInternalUse> statusBySignID = ssf
        .getStatusBySignatureID();

    switch (sss.getStatus()) {

      case StatusSignaturesSet.STATUS_FINAL_OK: {
        // Revisam les firma

        //statusFinal = sss;
        for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
          // TODO check null
          StatusSignature status = fis.getStatusSignature();
          final String signID = fis.getSignID();

          if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {
            PassarelaSignatureStatusWebInternalUse pss = statusBySignID.get(signID);
            // Check que status.getSignedData() != null
            if (status.getSignedData() == null || !status.getSignedData().exists()) {
              status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
              // TODO traduir
              String msg = "L'estat indica que ha finalitzat correctament però en la signatura amb ID "
                  + signID
                  + "("
                  + fis.getName()
                  + ")"
                  + ", el fitxer firmat o no s'ha definit o no existeix";
              status.setErrorMsg(msg);
              //statusFinal = status;

              // Copiar estat
              pss.setErrorMessage(msg);
              pss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
              pss.setErrorStackTrace(null);

            } else {

              File firmat = getFitxerFirmatPath(transactionID, signID);
              FileUtils.moveFile(status.getSignedData(), firmat);
              fitxersFirmatsBySignID.put(signID, firmat);
              // Copiar estat
              pss.setErrorMessage(status.getErrorMsg());
              pss.setStatus(status.getStatus());
              pss.setFitxerFirmat(firmat);

            }
            status.setProcessed(true);
          }
        }

        // Validar certificat i firmes, i comprovar que els NIFs corresponen
        final boolean isFirmaServidor = false;
        log.info("\n\n Crida a Validate Signatures ... \n\n");
        validateSignatures(ssf, isFirmaServidor);
      }

      break;

      case StatusSignaturesSet.STATUS_FINAL_ERROR:

        if (sss.getErrorException() == null) {
          log.error("ERROR EN PASSARELA PORTAFIB" + sss.getErrorMsg());
        } else {
          log.error("ERROR EN PASSARELA PORTAFIB" + sss.getErrorMsg(), sss.getErrorException());
        }

        //statusFinal = sss;
      break;

      case StatusSignaturesSet.STATUS_CANCELLED:
        if (sss.getErrorMsg() == null) {
          sss.setErrorMsg(I18NLogicUtils.tradueix(new Locale(ss.getCommonInfoSignature()
              .getLanguageUI()), "plugindefirma.cancelat"));
        }
        //statusFinal = sss;
      break;

      default:
        String inconsistentState = "El mòdul de firma ha finalitzat inesperadament "
            + "(no ha establit l'estat final del procés de firma)";
        sss.setErrorMsg(inconsistentState);
        sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        //statusFinal = sss;
        log.error(inconsistentState, new Exception());

    }

    // Copiar Estat General
    //ssf.setStatus(statusFinal.getStatus());
    //ssf.setErrorMessage(statusFinal.getErrorMsg());
    ssf.setStatus(sss.getStatus());
    ssf.setErrorMessage(sss.getErrorMsg());

    if (sss.getErrorException() != null) {
      StringWriter trace = new StringWriter();
      sss.getErrorException().printStackTrace(new java.io.PrintWriter(trace));
      ssf.setErrorStackTrace(trace.toString());
    }

    // Estadistica
    if (ssf.getStatus() == StatusSignaturesSet.STATUS_FINAL_OK) {
      int signaturesValides = 0;
      for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
        final String signID = fis.getSignID();
        PassarelaSignatureStatusWebInternalUse pss = statusBySignID.get(signID);
        if (pss.getStatus() == StatusSignature.STATUS_FINAL_OK) {
          ValidacioCompletaResponse infoValidacio = pss.getInfoValidacio();
          if (infoValidacio != null) {
            ValidateSignatureResponse validateSignatureResponse = infoValidacio.getValidateSignatureResponse();
            if (validateSignatureResponse != null) {
              if (validateSignatureResponse.getValidationStatus().getStatus() != ValidationStatus.SIGNATURE_VALID) {
                continue;
              }
            }
          }
          signaturesValides++;
        }
      }
      if (signaturesValides > 0) {
        // Estadistiques
        try {
          EstadisticaJPA est = new EstadisticaJPA();
          est.setValor( (double) signaturesValides);
          est.setTipus(ConstantsV2.ESTADISTICA_TIPUS_PETICIO_FIRMES);
          est.setUsuariAplicacioID(ssf.getApplicationID());
          {
            Properties params = new Properties();
            params.setProperty("entitatID", ssf.getEntitatID());
            params.setProperty("transactionID", transactionID);
            params.setProperty("administrationID", ss.getCommonInfoSignature().getAdministrationID());
            StringWriter writer = new StringWriter();
            params.store(writer, null);
            est.setParametres(writer.getBuffer().toString());
          }
          est.setEntitatID(ssf.getEntitatID());
          est.setData(new Timestamp(System.currentTimeMillis()));
          estadisticaEjb.create(est);
        } catch (Throwable th) {
          log.error("Error afegint estadistiques de Peticio Finalitzada: " + th.getMessage(),
                th);
        }
      }
    }

    return ssf;
  }


  /**
   * 
   * Validar certificat i firmes, i comprovar que els NIFs corresponen
   * 
   * @param ssf
   */
  protected void validateSignatures(PassarelaSignaturesSetWebInternalUse ssf,
      boolean esFirmaEnServidor) {

    final boolean isDebug = log.isDebugEnabled();

    if (isDebug) {
      log.info("VALIDACIO WEB PASSARELA  => Cridada a  validateSignatures(ssf, isFirmaServidor)");
    }

    Map<String, PassarelaSignatureStatusWebInternalUse> statusBySignID = ssf
        .getStatusBySignatureID();

    final String languageUI = ssf.getSignaturesSet().getCommonInfoSignature().getLanguageUI();

    final String applicationID = ssf.getApplicationID();
    final UsuariAplicacio usuariAplicacio;
    usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(applicationID);

    final String entitatID = usuariAplicacio.getEntitatID();

    Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = ssf.getConfigBySignID();

    PassarelaFileInfoSignature[] fileInfoSignatureArray = ssf.getSignaturesSet()
        .getFileInfoSignatureArray();

    for (int p = 0; p < fileInfoSignatureArray.length; p++) {

      PassarelaFileInfoSignature fis = fileInfoSignatureArray[p];

      // TODO check null
      final String signID = fis.getSignID();

      UsuariAplicacioConfiguracioJPA configuracio = configBySignID.get(signID);

      PassarelaSignatureStatusWebInternalUse status = statusBySignID.get(signID);

      if (status.getStatus() != StatusSignature.STATUS_FINAL_OK) {
        continue;
      }

      try {

        boolean validarFitxerFirma = SignatureUtils.validarFirma(configuracio, entitatEjb,
            entitatID);
        boolean comprovarNifFirma = SignatureUtils.comprovarNifFirma(configuracio, entitatEjb,
            entitatID);
        boolean checkCanviatDocFirmat = SignatureUtils.checkCanviatDocFirmat(configuracio,
            entitatEjb, entitatID);

        // (A) Validar la Firma
        final IPortaFIBDataSource fitxerOriginal;
        {
          try {

            if (isDebug) {
              log.info("fis.getFileToSign() => " + fis.getFileToSign());

              log.info("fis.getFileToSign().getData() => " + fis.getFileToSign().getData());
              log.info("fis.getFileToSign().getNom() => " + fis.getFileToSign().getNom());

              log.info("fis.getFileToSign().getFitxerID() => "
                  + fis.getFileToSign().getFitxerID());
            }

            InputStream is = fis.getFileToSign().getData().getInputStream();
            fitxerOriginal = new ByteArrayDataSource(IOUtils.toByteArray(is));
            is.close();
          } catch (IOException e) {
            // XYZ ZZZ traduir
            final String msg = "Error desconegut al intentar obtenir contingut del fitxer detached: "
                + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
          }
        }

        final IPortaFIBDataSource documentDetached;
        {
          FitxerBean pd = fis.getPreviusSignatureDetachedFile();
          if (pd == null) {
            documentDetached = null;
          } else {
            try {
              InputStream is = pd.getData().getInputStream();
              documentDetached = new ByteArrayDataSource(IOUtils.toByteArray(is));
              is.close();
            } catch (IOException e) {
              // XYZ ZZZ traduir
              final String msg = "Error desconegut al intentar obtenir contingut del fitxer detached: "
                  + e.getMessage();
              log.error(msg, e);
              throw new I18NException("genapp.comodi", msg);
            }
          }
        }

        final IPortaFIBDataSource signature = new es.caib.portafib.logic.utils.datasource.FileDataSource(
            status.getFitxerFirmat());

        final int signTypeID = SignatureUtils.convertApiSignTypeToPortafibSignType(fis
            .getSignType());

        final boolean signMode = SignatureUtils.convertApiSignMode2PortafibSignMode(fis
            .getSignMode());

        final int numFirmesOriginals = ssf.getOriginalNumberOfSignsArray()[p];

        String expectedNif = ssf.getSignaturesSet().getCommonInfoSignature()
            .getAdministrationID();

        // En passarel.la no hi ha flux de firma
        final int numFirmaPortaFIB = 1;
        
        IPortaFIBDataSource adaptat = new FileDataSource(getFitxerAdaptatPath(ssf.getSignaturesSet().getSignaturesSetID(), signID));
        int posTaulaDeFirmes = fis.getSignaturesTableLocation();

        ValidacioCompletaRequest validacioRequest = new ValidacioCompletaRequest(entitatID,
            validarFitxerFirma, checkCanviatDocFirmat, comprovarNifFirma, fitxerOriginal, adaptat,
            signature, documentDetached, signTypeID, signMode, languageUI, 
            numFirmaPortaFIB, numFirmesOriginals, expectedNif, posTaulaDeFirmes);

        // Aqui es fan totes les validacions completes !!!!!!
        ValidacioCompletaResponse validacioResponse;
        validacioResponse = validacioCompletaLogicaEjb.validateCompletaFirma(validacioRequest);

        log.info("\n\n   XYZ ZZZ ZZZ VALIDACIO RESPONSE ==>  " + validacioResponse + "\n\n");

        status.setInfoValidacio(validacioResponse);

      } catch (I18NException i18n) {
        // Error en la validació
        status.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        status.setErrorMessage(I18NLogicUtils.getMessage(i18n, new Locale(languageUI)));
      }

    }
  }

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -------------- DIRECTORI DE FITXERS TEMPORALS -------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  public static final String PASSARELA_DIRNAME = "PASSARELADEFIRMAWEB";

  public static final String passarelaBasePath;

  static {
    final File base = new File(FileSystemManager.getFilesPath(), PASSARELA_DIRNAME);
    base.mkdirs();
    passarelaBasePath = base.getAbsolutePath();
  }

  @Override
  protected String getPassarelaBasePath() {
    return passarelaBasePath;
  }

  public File getFitxerOriginalPath(String transactionID, String signID) {
    File p = new File(passarelaBasePath + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "original");
  }

  public File getFitxerFirmatPath(String transactionID, String signID) {
    File p = new File(passarelaBasePath + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "firmat");
  }

  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T S ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  private static final Map<String, PassarelaSignaturesSetWebInternalUse> passarelaSignaturesSets = new HashMap<String, PassarelaSignaturesSetWebInternalUse>();

  private static long lastCheckFirmesCaducades = 0;

  /**
   * Fa neteja
   * 
   * @param transactionID
   * @return
   */
  protected PassarelaSignaturesSetWebInternalUse readSignaturesSet(String transactionID) {

    checkExpiredSignaturesSet();

    log.info("XYZ ZZZ  Calling readSignaturesSet(" + transactionID + ")");

    synchronized (passarelaSignaturesSets) {

      PassarelaSignaturesSetWebInternalUse pss = passarelaSignaturesSets.get(transactionID);
      if (pss == null) {

        log.info("XYZ ZZZ  La transacció " + transactionID + " no existeix !!!!!");

        if (passarelaSignaturesSets.size() == 0) {
          log.info("XYZ ZZZ  passarelaSignaturesSets ESTA BUIT  !!!!!");
        } else {
          log.info("XYZ ZZZ Contingut de  passarelaSignaturesSets:");
          for (String id : passarelaSignaturesSets.keySet()) {
            log.info("          XYZ ZZZ  EXISTEIX ID :  " + id);
          }
        }

      }

      return pss;
    }

  }

  protected void storeSignaturesSet(PassarelaSignaturesSetWebInternalUse signaturesSet) {
    checkExpiredSignaturesSet();
    synchronized (passarelaSignaturesSets) {
      passarelaSignaturesSets.put(signaturesSet.getSignaturesSet().getSignaturesSetID(),
          signaturesSet);
    }
  }

  protected void deleteSignaturesSet(String transactionID) {

    PassarelaSignaturesSetWebInternalUse pss = readSignaturesSet(transactionID);

    if (pss == null) {
      log.warn("NO Existeix signaturesSetID igual a " + transactionID);
      return;
    }

    deleteSignaturesSet(pss);
  }

  protected void deleteSignaturesSet(PassarelaSignaturesSetWebInternalUse pss) {

    final String signaturesSetID = pss.getSignaturesSet().getSignaturesSetID();

    // ESBORRAR TOT DIRECTORI
    File basePath = getTransactionPath(signaturesSetID);
    try {
      FileUtils.deleteDirectory(basePath);
    } catch (IOException e) {
      log.error(
          "Error eliminant directori " + basePath + "(S'ha d'esborrar manualment): "
              + e.getMessage(), e);
    }

    if (log.isDebugEnabled()) {
      log.debug("Eliminant transacció WEB ]" + signaturesSetID + "[");
    }

    passarelaSignaturesSets.remove(signaturesSetID);
  }

  /**
   * Fer net peticions caducades SignaturesSet.getExpiryDate() Check si existeix algun proces
   * de firma caducat s'ha d'esborrar Com a mínim cada minut es revisa si hi ha caducats
   */
  private void checkExpiredSignaturesSet() {

    Long now = System.currentTimeMillis();

    final long un_minut_en_ms = 60 * 60 * 1000;

    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<PassarelaSignaturesSetWebInternalUse> keysToDelete = new ArrayList<PassarelaSignaturesSetWebInternalUse>();

      Set<String> ids = passarelaSignaturesSets.keySet();
      for (String id : ids) {
        PassarelaSignaturesSetWebInternalUse ssf = passarelaSignaturesSets.get(id);
        if (ssf == null) {
          continue;
        }
        PassarelaSignaturesSet ss = ssf.getSignaturesSet();

        if (now > ss.getExpiryDate().getTime()) {
          keysToDelete.add(ssf);
          SimpleDateFormat sdf = new SimpleDateFormat();
          log.info("Passarel·la De Firma: Tancant SignatureSET amb ID = " + id
              + " a causa de que està caducat " + "( ARA: " + sdf.format(new Date(now))
              + " | CADUCITAT: " + sdf.format(ss.getExpiryDate()) + ")");
        }
      }

      if (keysToDelete.size() != 0) {
        synchronized (passarelaSignaturesSets) {

          for (PassarelaSignaturesSetWebInternalUse pss : keysToDelete) {
            deleteSignaturesSet(pss);
          }
        }
      }
    }
  }

}
