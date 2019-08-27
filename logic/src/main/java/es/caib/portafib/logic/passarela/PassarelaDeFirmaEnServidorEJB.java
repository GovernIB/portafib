package es.caib.portafib.logic.passarela;

import es.caib.portafib.ejb.EstadisticaLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCustodyInfo;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PortaFIBTimeStampInfo;
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
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.FileUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeForUpgrade;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.validatesignature.api.ValidateSignatureResponse;
import org.fundaciobit.plugins.validatesignature.api.ValidationStatus;
import org.jboss.ejb3.annotation.SecurityDomain;

import javax.activation.DataHandler;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author anadal
 */
@Stateless(name = "PassarelaDeFirmaEnServidorEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class PassarelaDeFirmaEnServidorEJB extends
    AbstractPassarelaDeFirmaEJB<ISignatureServerPlugin> implements
    PassarelaDeFirmaEnServidorLocal {

  @EJB(mappedName = EstadisticaLocal.JNDI_NAME)
  protected EstadisticaLocal estadisticaEjb;

  @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaServidorLogicaLocal modulDeFirmaServidorEjb;

  @EJB(mappedName = SegellDeTempsPublicLogicaLocal.JNDI_NAME)
  protected SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb;

  @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
  protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;

  @Override
  protected AbstractPluginLogicaLocal<ISignatureServerPlugin> getModulDeFirmaEJB() {
    return modulDeFirmaServidorEjb;
  }

  SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  public PassarelaSignatureInServerResults signDocuments(PassarelaSignaturesSet passarelaSignaturesSet,
      EntitatJPA entitat, UsuariAplicacioJPA usrApp, PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID)
      throws NoCompatibleSignaturePluginException {

    Locale locale;

    try {
      locale = new Locale(passarelaSignaturesSet.getCommonInfoSignature().getLanguageUI());
    } catch (Throwable e) {
      locale = new Locale("ca");
    }

    String signaturesSetID = null;

    try {
      // Validar
      String entitatID = entitat.getEntitatID();
      SignaturesSetBeanValidator ssbv = new SignaturesSetBeanValidator(validator, this,
          entitatID);
      final boolean isNou = true;

      ssbv.throwValidationExceptionIfErrors(passarelaSignaturesSet, isNou);

      signaturesSetID = passarelaSignaturesSet.getSignaturesSetID();

      // Guardar ZYX ZZZ
      // storeSignaturesSet(new PassarelaSignaturesSetFull(entitatID,
      // signaturesSet));

      // XYZ ZZZ Ja s'ha mogut a passarela
      // if (passarelaSignaturesSet.getCommonInfoSignature().getUsername() == null) {
      // passarelaSignaturesSet.getCommonInfoSignature().setUsername(usrApp.getUsuariAplicacioID());
      // }

      PassarelaFileInfoSignature[] fisArray = passarelaSignaturesSet
          .getFileInfoSignatureArray();
      int[] originalNumberOfSignsArray2 = new int[fisArray.length];

      for (int i = 0; i < fisArray.length; i++) {

        PassarelaFileInfoSignature pfis = fisArray[i];

        final String signID = pfis.getSignID();
        File original = getFitxerOriginalPath(signaturesSetID, signID);

        // obtenir ruta on guardar fitxer adaptat
        File adaptat = getFitxerAdaptatPath(signaturesSetID, signID);

        originalNumberOfSignsArray2[i] = processFileToSign(locale, entitatID, pfis, original,
            adaptat, usrApp);

      } // Final de For

      // 1.- Cridar convertir PassarelaSignaturesSet a SignaturesSet
      Set<String> timeStampUrls = new HashSet<String>();
      SignaturesSet ss = SignatureUtils.passarelaSignaturesSetToSignaturesSet(this,
          segellDeTempsPublicEjb, passarelaSignaturesSet, usrApp, perfilDeFirma,
          configBySignID, entitat, timeStampUrls);

      //  S'han de poder fer multiples firmes amb diferents configuracions
      if (configBySignID.size() != 1) {
        // XYZ ZZZ
        final String msg = "El codi per realitzar multiples firmes amb diferents"
            + " configuracions de firma encara no està implementat."
            + " Consulti amb l'administrador de PortaFIB";
        final Exception e = new Exception(msg);
        return processError(e, msg);
      }

      UsuariAplicacioConfiguracioJPA config = configBySignID.values().iterator().next();

      // 2.- Cercar Plugin associats als IDs
      Long pluginFirmaEnServidorId = config.getPluginFirmaServidorID();
      ISignatureServerPlugin signaturePlugin;
      signaturePlugin = instantitatePluginDeFirmaEnServidor(pluginFirmaEnServidorId);

      Map<String, Object> parameters = new HashMap<String, Object>();
      parameters.put("signaturesSet", ss);

      String error = signaturePlugin.filter(ss, parameters);
      if (error != null) {
        throw new NoCompatibleSignaturePluginException(error);
      }

      // TIMESTAMP INFO
      String timestampUrlBase = null;
      if (timeStampUrls.size() != 0) {
        // Nota per ara només suportam una sola URL pel Segellador de Temps
        timestampUrlBase = timeStampUrls.iterator().next();
      }

      // FIRMAR
      ss = signaturePlugin.signDocuments(ss, timestampUrlBase, parameters);

      String languageUI = passarelaSignaturesSet.getCommonInfoSignature().getLanguageUI();

      // VALIDAR
      UsuariAplicacioConfiguracio configuracio;
      Map<String, ValidacioCompletaResponse> validacioResponseBySignID;
      validacioResponseBySignID = new HashMap<String, ValidacioCompletaResponse>();
      
      final boolean isDebug = log.isDebugEnabled();

      for (int i = 0; i < ss.getFileInfoSignatureArray().length; i++) {

        FileInfoSignature pfis = ss.getFileInfoSignatureArray()[i];

        StatusSignature status = pfis.getStatusSignature();

        configuracio = configBySignID.get(pfis.getSignID());

        boolean validarFitxerFirma = SignatureUtils.validarFirma(configuracio, entitatEjb, entitatID);
        boolean comprovarNifFirma = SignatureUtils.comprovarNifFirma(configuracio, entitatEjb, entitatID);
        boolean checkCanviatDocFirmat = SignatureUtils.checkCanviatDocFirmat(configuracio, entitatEjb, entitatID);
        
        if(isDebug) {
          log.info(" CONFIGURACIO => " + configuracio.getUsuariAplicacioConfigID());
        }
        
        boolean modificadaComprovacioDeNifEnFirma;
        
        String nif = passarelaSignaturesSet.getCommonInfoSignature().getAdministrationID(); 
        if (comprovarNifFirma && nif == null) {
          comprovarNifFirma = false;
          modificadaComprovacioDeNifEnFirma = true;
        } else {
          modificadaComprovacioDeNifEnFirma = false;
        }
        
        
        if(isDebug) {
          log.info("PassarelaDeFirmaEnServidorEJB: ENTRADES CONFIGURACIO => \n"
            + "+ validarFitxerFirma => " + validarFitxerFirma + "\n"
            + "+ comprovarNifFirma => " + comprovarNifFirma + "\n"
            + "+ checkCanviatDocFirmat => " + checkCanviatDocFirmat + "\n");
        }

        // (A) Validar la Firma
        final IPortaFIBDataSource fitxerOriginal;
        fitxerOriginal = new FileDataSource(pfis.getFileToSign());

        final IPortaFIBDataSource documentDetached;
        if (pfis.getPreviusSignatureDetachedFile() == null) {
          documentDetached = null;
        } else {
          documentDetached = new FileDataSource(pfis.getPreviusSignatureDetachedFile());
        }
        
        final int posTaulaDeFirmes = pfis.getSignaturesTableLocation();

        final IPortaFIBDataSource adaptat = new FileDataSource(getFitxerAdaptatPath(signaturesSetID, pfis.getSignID()));
        
        final IPortaFIBDataSource signature = new FileDataSource(status.getSignedData());

        final int signTypeID = SignatureUtils.convertApiSignTypeToPortafibSignType(pfis
            .getSignType());
        
        final boolean signMode = SignatureUtils.convertApiSignMode2PortafibSignMode(pfis.getSignMode());

        final int numFirmesOriginals = 0;

        // En passarel.la no hi ha flux de firma
        final int numFirmaPortaFIB = 1;

        ValidacioCompletaRequest validacioRequest = new ValidacioCompletaRequest(entitatID,
            validarFitxerFirma, checkCanviatDocFirmat, comprovarNifFirma, fitxerOriginal,
            adaptat, signature, documentDetached, signTypeID, signMode, languageUI,
            numFirmaPortaFIB, numFirmesOriginals, nif, posTaulaDeFirmes);

        // Aqui es fan totes les validacions completes !!!!!!
        ValidacioCompletaResponse validacioResponse;
        try {
          validacioResponse = validacioCompletaLogicaEjb
              .validateCompletaFirma(validacioRequest);
          
          if(isDebug) {
            log.info("n\n validacioResponse[" + pfis.getSignID() + "] => " + validacioResponse);
          }
          
          
          if (modificadaComprovacioDeNifEnFirma) {
            validacioResponse.setCheckAdministrationIDOfSigner(false);
          }
          

          validacioResponseBySignID.put(pfis.getSignID(), validacioResponse);

        } catch (I18NException e) {

          status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
          String msg = I18NLogicUtils.getMessage(e, new Locale(languageUI));

          log.error(msg, e);

          status.setErrorMsg(msg);
          Throwable cause = e.getCause();

          if (cause != null) {
            status.setErrorException(cause);
          }

        }

      }

      // FINAL VALIDAR

      // XYZ ZZZ ZZZ FALTA CUSTODIA
      PassarelaCustodyInfo custodyInfo = null;

      // Estadistica
      StatusSignaturesSet ssf = ss.getStatusSignaturesSet();
      if (ssf.getStatus() == StatusSignaturesSet.STATUS_FINAL_OK) {
        int signaturesValides = 0;
        for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
          StatusSignature fiss = fis.getStatusSignature();
          if (fiss.getStatus() == StatusSignature.STATUS_FINAL_OK) {
            ValidacioCompletaResponse infoValidacio = validacioResponseBySignID.get(fis.getSignID());
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
          try {
            EstadisticaJPA est = new EstadisticaJPA();
            est.setValor( (double) signaturesValides);
            est.setTipus(ConstantsV2.ESTADISTICA_TIPUS_PETICIO_FIRMES);
            est.setUsuariAplicacioID(usrApp.getUsuariAplicacioID());

            {
              Properties params = new Properties();
              params.setProperty("entitatID", entitatID);
              params.setProperty("username", ss.getCommonInfoSignature().getUsername());
              params.setProperty("administrationID", ss.getCommonInfoSignature().getAdministrationID());
              StringWriter writer = new StringWriter();
              params.store(writer, null);
              est.setParametres(writer.getBuffer().toString());
            }
            est.setEntitatID(entitatID);
            est.setData(new Timestamp(System.currentTimeMillis()));
            estadisticaEjb.create(est);
          } catch (Throwable th) {
            log.error("Error afegint estadistiques de Peticio Finalitzada: " + th.getMessage(),
                  th);
          }
        }
      }

      return 
          new PassarelaSignatureInServerResults(
              getSignatureStatusAndResults(ss, custodyInfo, config), validacioResponseBySignID);

    } catch (I18NValidationException i18nve) {

      String msg = I18NLogicUtils.getMessage(i18nve, locale);
      return processError(i18nve, msg);

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, locale);
      return processError(i18ne, msg);

    } finally {

      // ESBORRAR TOT DIRECTORI
      File basePath = getTransactionPath(signaturesSetID);
      try {
        FileUtils.deleteDirectory(basePath);
      } catch (IOException e) {
        log.error("Error eliminant directori " + basePath + "(S'ha d'esborrar manualment): "
            + e.getMessage(), e);
      }

    }

  }

  private ISignatureServerPlugin instantitatePluginDeFirmaEnServidor(
      Long pluginFirmaEnServidorID) throws I18NException {
    ISignatureServerPlugin signaturePlugin;

    PluginJPA modulDeFirmaJPA = modulDeFirmaServidorEjb
        .findByPrimaryKey(pluginFirmaEnServidorID);

    signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(modulDeFirmaJPA
        .getPluginID());

    return signaturePlugin;
  }

  @Override
  public UpgradeResponse upgradeSignature(FirmaSimpleFile signature, FirmaSimpleFile documentDetached,
      FirmaSimpleFile targetCertificate, SignatureTypeFormEnumForUpgrade signTypeForm,
      UsuariAplicacioJPA usrApp, PerfilDeFirma perfilDeFirma,
      UsuariAplicacioConfiguracio config, EntitatJPA entitat, String languageUI)
      throws NoCompatibleSignaturePluginException, I18NException {

    // 1.- Cercar Plugin associats als IDs

    ISignatureServerPlugin signaturePlugin;
    signaturePlugin = instantitatePluginDeFirmaEnServidor(config.getPluginFirmaServidorID());

    if (!signaturePlugin.isUpgradeSignatureSupported(signTypeForm)) {
      // XYZ ZZZ TRA
      String msg = "El plugin " + signaturePlugin.getName(new Locale(usrApp.getIdiomaID()))
          + " no suporta extensió de firma.";
      log.warn(msg);
      throw new NoCompatibleSignaturePluginException(msg);
    }

    PortaFIBTimeStampInfo info = null;

    if (signaturePlugin.isRequiredExternalTimeStampForUpgradeSignature()) {
      // Cercar en propietats d'aplicació el Segellador de Temps seleccionat i instanciar-ho

      boolean userRequiresTimeStamp = true;
      info = segellDeTempsPublicEjb.getTimeStampInfoForUsrApp(usrApp.getUsuariAplicacioID(), entitat, perfilDeFirma,
          config, userRequiresTimeStamp);

      // XYZ ZZZ TRA
      if (info == null) {
        String msg = "L'actualitzadador de Firmes (upgrade) '"
            + signaturePlugin.getName(new Locale(usrApp.getIdiomaID()))
            + "' requereix un Segellador de Temps però la Configuració de Firma "
            + config.getNom() + " associat al Perfil de Firma amb Codi "
            + perfilDeFirma.getCodi()
            + " no defineix Segellador de Temps. Consulti amb l'administrador de PortaFIB";

        log.error(msg);
        throw new NoCompatibleSignaturePluginException(msg);
      }
    }

    // FER UPDGRADE
    final byte[] signatureData = signature.getData();

    final byte[] upgradedSignature;
    try {
      if (info != null) {
        upgradedSignature = signaturePlugin.upgradeSignature(signatureData, null,
            signTypeForm, info.getTimeStampGenerator(), info.getTimeStampUrl());
      } else {
        upgradedSignature = signaturePlugin.upgradeSignature(signatureData, null,
            signTypeForm, null, null);
      }
    } catch (Exception e) {
      // XYZ ZZZ TRA
      String msg = "Error desconegut realitzant l'upgrade d'una firma: " + e.getMessage();
      log.error(msg, e);
      throw new I18NException(e, "genapp.comodi", new I18NArgumentString(msg));
    }

    // VALIDACIO DE FIRMA ACTUALITZADA

    // --------------------------------------------
    final String entitatID = entitat.getEntitatID();

    boolean validarFitxerFirma = SignatureUtils.validarFirma(config, entitatEjb, entitatID);
    boolean comprovarNifFirma = SignatureUtils.comprovarNifFirma(config, entitatEjb, entitatID);
    boolean checkCanviatDocFirmat = SignatureUtils.checkCanviatDocFirmat(config, entitatEjb,  entitatID);

    // (A) Validar la Firma
    final IPortaFIBDataSource fitxerOriginal;
    fitxerOriginal = new ByteArrayDataSource(signatureData);

    IPortaFIBDataSource documentDetachedDS = null;
    if (documentDetached != null) {
            
      documentDetachedDS = new ByteArrayDataSource(documentDetached.getData());
      
    }

    final IPortaFIBDataSource upgradedSignatureDS = new ByteArrayDataSource(upgradedSignature);

    final int signTypeID = getSignTypeToPortaFIB(signTypeForm);

    final int numFirmesOriginals;
    switch (signTypeID) {

      case ConstantsV2.TIPUSFIRMA_PADES:
        numFirmesOriginals = PdfUtils.getNumberOfSignaturesInPDF(fitxerOriginal
            .getInputStream());
      break;

      default:
        if (PropietatGlobalUtil.isStrictValidation()) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi", "No puc obtenir el numero de firmes originals pel tipus "
            + SignatureUtils.convertPortafibSignTypeToApiSignType(signTypeID) 
            + ". Consulti amb l'administrador de PortaFIB el valor de la propietat es.caib.portafib.strictvalidation");
        } else {
          numFirmesOriginals = 0;
        }

    }

    

    // En firma en servidor no hi ha flux de firma
    final int numFirmaPortaFIB = 1;

    // Deixar-ho així per si algun dia des de Passarela es passa 
    final boolean signMode;
    if (documentDetached == null) {
      signMode = ConstantsV2.SIGN_MODE_IMPLICIT;
    } else {
      signMode = ConstantsV2.SIGN_MODE_EXPLICIT; 
    }
    
    final String expectedNif = null;
    boolean modificatComprovarNifFirma = false;
    if (comprovarNifFirma == true) {
      comprovarNifFirma = false;
      modificatComprovarNifFirma = true;
    }
    

    ValidacioCompletaRequest validacioRequest = new ValidacioCompletaRequest(entitatID,
        validarFitxerFirma, checkCanviatDocFirmat, comprovarNifFirma, fitxerOriginal, fitxerOriginal,
        upgradedSignatureDS, documentDetachedDS, signTypeID, signMode, languageUI,
        numFirmaPortaFIB, numFirmesOriginals, expectedNif, ConstantsV2.TAULADEFIRMES_SENSETAULA);

    // Aqui es fan totes les validacions completes !!!!!!
    ValidacioCompletaResponse validacioResponse;
    validacioResponse = validacioCompletaLogicaEjb.validateCompletaFirma(validacioRequest);
    
    if (modificatComprovarNifFirma) {
      validacioResponse.setCheckAdministrationIDOfSigner(false);
    }
    

    return new UpgradeResponse(upgradedSignature, validacioResponse);

  }

  protected int getSignTypeToPortaFIB(SignatureTypeFormEnumForUpgrade signTypeForm)
      throws I18NException {

    final String type = signTypeForm.getType();

    if (SignatureTypeForUpgrade.PDF.equals(type) || SignatureTypeForUpgrade.PADES.equals(type)
        || SignatureTypeForUpgrade.PADES_BASELINE_2_1_1.equals(type)) {

      return ConstantsV2.TIPUSFIRMA_PADES;
    }

    if (SignatureTypeForUpgrade.XADES_V_1_3_2.equals(type)
        || SignatureTypeForUpgrade.XADES_V_1_2_2.equals(type)
        || SignatureTypeForUpgrade.XADES_V_1_1_1.equals(type)
        || SignatureTypeForUpgrade.XADES_BASELINE_2_1_1.equals(type)) {

      return ConstantsV2.TIPUSFIRMA_XADES;

    }

    if (SignatureTypeForUpgrade.CADES_BASELINE_2_2_1.equals(type)
        || SignatureTypeForUpgrade.CADES.equals(type)) {

      return ConstantsV2.TIPUSFIRMA_CADES;
    }

    // / XYZ ZZZ TRA Falta Traducció
    throw new I18NException("genapp.comodi", "Tipus de Firma per upgrade desconegut: " + type);

    /**
     * Attribute that represents identifier for XML_DSIG.
     */
    // public static final String XML_DSIG = "urn:ietf:rfc:3275";

    /**
     * Attribute that represents identifier for CMS.
     */
    // public static final String CMS = "urn:ietf:rfc:3369";

    /**
     * Attribute that represents identifier for CMS(TST).
     */
    // public static final String CMS_TST = "urn:afirma:dss:1.0:profile:XSS:forms:CMSWithTST";

    /**
     * Attribute that represents identifier for PKCS7.
     */
    // public static final String PKCS7 = "urn:ietf:rfc:2315";

    /**
     * Attribute that represents identifier for XML_TST.
     */
    // public static final String XML_TST =
    // "urn:oasis:names:tc:dss:1.0:core:schema:XMLTimeStampToken";

    /**
     * Attribute that represents identifier for ODF.
     */
    // public static final String ODF = "urn:afirma:dss:1.0:profile:XSS:forms:ODF";

  }

  /**
   * 
   * @param i18nve
   * @param msg
   * @return
   */
  private PassarelaSignatureInServerResults processError(Throwable i18nve, String msg) {
    PassarelaSignatureStatus pss = new PassarelaSignatureStatus();

    pss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
    pss.setErrorMessage(msg);

    StringWriter trace = new StringWriter();
    i18nve.printStackTrace(new java.io.PrintWriter(trace));
    pss.setErrorStackTrace(trace.toString());

    log.error(msg, i18nve);

    return new PassarelaSignatureInServerResults(new PassarelaFullResults(pss), null);
  }

  private PassarelaFullResults getSignatureStatusAndResults(SignaturesSet ssf,
      PassarelaCustodyInfo custodyInfo, UsuariAplicacioConfiguracio config) throws I18NException {

    PassarelaFullResults resultFull = new PassarelaFullResults();

    // 1.- Convertir Estat general
    {
      StatusSignaturesSet sss = ssf.getStatusSignaturesSet();
      PassarelaSignatureStatus pss = new PassarelaSignatureStatus();
      statusToPassarelaStatus(sss, pss);

      resultFull.setSignaturesSetStatus(pss);
    }

    // 2.- Convertir estat i resultat
    List<PassarelaSignatureResult> results = new ArrayList<PassarelaSignatureResult>();
    for (int i = 0; i < ssf.getFileInfoSignatureArray().length; i++) {

      FileInfoSignature pfis = ssf.getFileInfoSignatureArray()[i];
      StatusSignature ss = pfis.getStatusSignature();

      FitxerBean signedFile = null;

      if (ss.getStatus() == StatusSignature.STATUS_FINAL_OK && ss.getSignedData() != null
          && ss.getSignedData().exists()) {

        final String signType = pfis.getSignType();

        signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getName());

        // Això depen del tipus de firma !!!!!

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
          signedFile.setMime(ConstantsV2.MIME_TYPE_PDF);
          String nom = signedFile.getNom();
          if (!nom.trim().toLowerCase().endsWith(".pdf")) {
            signedFile.setNom(nom.trim() + ".pdf");
          }
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
          signedFile.setMime(ConstantsV2.MIME_TYPE_XML);
          String nom = signedFile.getNom();
          if (!nom.trim().toLowerCase().endsWith(".xml")) {
            signedFile.setNom(nom.trim() + ".xml");
          }
        } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
          signedFile.setMime(ConstantsV2.MIME_TYPE_BINARY);
          String nom = signedFile.getNom();
          if (!nom.trim().toLowerCase().endsWith(".csig")) {
            signedFile.setNom(nom.trim() + ".csig");
          }
        } else {
          signedFile.setMime(ConstantsV2.MIME_TYPE_BINARY);
        }
        signedFile.setTamany(ss.getSignedData().length());
        signedFile.setData(new DataHandler(new javax.activation.FileDataSource(ss
            .getSignedData())));
        signedFile.setDescripcio("Signed Document");

      }

      PassarelaValidationInfo validationInfo = null;

      PassarelaSignatureResult psr = new PassarelaSignatureResult(pfis.getSignID(),
          signedFile, custodyInfo, validationInfo);

      statusToPassarelaStatus(ss, psr);

      results.add(psr);
    }

    resultFull.setSignResults(results);

    return resultFull;

  }

  private void statusToPassarelaStatus(StatusSignaturesSet sss, PassarelaSignatureStatus pss) {
    pss.setStatus(sss.getStatus());
    pss.setErrorMessage(sss.getErrorMsg());
    if (sss.getErrorException() != null) {
      StringWriter trace = new StringWriter();
      sss.getErrorException().printStackTrace(new java.io.PrintWriter(trace));
      pss.setErrorStackTrace(trace.toString());
    }
  }

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -------------- DIRECTORI DE FITXERS TEMPORALS -------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  private static final String passarelaBasePath;

  static {
    // private static
    final String PASSARELA_DIRNAME = "PASSARELADEFIRMAENSERVIDOR";
    final File base = new File(FileSystemManager.getFilesPath(), PASSARELA_DIRNAME);
    base.mkdirs();
    passarelaBasePath = base.getAbsolutePath();
  }

  @Override
  protected String getPassarelaBasePath() {
    return passarelaBasePath;
  }

}
