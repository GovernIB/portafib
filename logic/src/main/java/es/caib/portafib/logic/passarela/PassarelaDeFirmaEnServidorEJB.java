package es.caib.portafib.logic.passarela;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCustodyInfo;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PortaFIBTimeStampInfo;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsV2;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

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

  @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaServidorLogicaLocal modulDeFirmaServidorEjb;

  @EJB(mappedName = SegellDeTempsPublicLogicaLocal.JNDI_NAME)
  protected SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb;

  @Override
  protected AbstractPluginLogicaLocal<ISignatureServerPlugin> getModulDeFirmaEJB() {
    return modulDeFirmaServidorEjb;
  }

  SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  public PassarelaFullResults signDocuments(PassarelaSignaturesSet passarelaSignaturesSet,
      EntitatJPA entitat, UsuariAplicacioJPA usrApp, PerfilDeFirma perfilDeFirma, 
      UsuariAplicacioConfiguracioJPA config) throws NoCompatibleSignaturePluginException {

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
          config, entitat, timeStampUrls);
      

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

      // XYZ ZZZ ZZZ FALTA CUSTODIA
      PassarelaCustodyInfo custodyInfo = null;

      return getSignatureStatusAndResults(ss, custodyInfo, config, locale);

    } catch (I18NValidationException i18nve) {

      String msg = I18NLogicUtils.getMessage(i18nve, locale);
      return processError(i18nve, msg);

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, locale);
      return processError(i18ne, msg);

    } finally {

      // BORRAR TOT DIRECTORI
      File basePath = getTransactionPath(signaturesSetID);
      try {
        FileUtils.deleteDirectory(basePath);
      } catch (IOException e) {
        log.error("Error eliminant directori " + basePath + "(S'ha de borrar manualment): "
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
  public byte[] upgradeSignature(FirmaSimpleFile signature, FirmaSimpleFile targetCertificate,
      SignatureTypeFormEnumForUpgrade signTypeForm, UsuariAplicacioJPA usrApp,
      PerfilDeFirma perfilDeFirma, UsuariAplicacioConfiguracio config, EntitatJPA entitat) 
          throws NoCompatibleSignaturePluginException, I18NException, Exception {

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
      info =  segellDeTempsPublicEjb.getTimeStampInfoForUsrApp(usrApp, entitat,
          perfilDeFirma, config, userRequiresTimeStamp);

      // XYZ ZZZ TRA
      if (info == null) {
        String msg = "L'actualitzadador de Firmes (upgrade) '"
           + signaturePlugin.getName(new Locale(usrApp.getIdiomaID()))
           + "' requereix un Segellador de Temps però la Configuració de Firma " 
           + config.getNom() + " associat al Perfil de Firma amb Codi " 
           + perfilDeFirma.getCodi() + " no defineix Segellador de Temps. Consulti amb l'administrador de PortaFIB";
        
        log.error(msg);
        throw new NoCompatibleSignaturePluginException(msg);
      }
    }

    // FER UPDGRADE
    final byte[] signatureData = signature.getData();
    
    if (info != null) {
      return signaturePlugin.upgradeSignature(signatureData, null, signTypeForm,
          info.getTimeStampGenerator(), info.getTimeStampUrl());
    } else {
      return signaturePlugin.upgradeSignature(signatureData, null, signTypeForm,
        null, null);
    }

  }

  /**
   * 
   * @param i18nve
   * @param msg
   * @return
   */
  private PassarelaFullResults processError(Throwable i18nve, String msg) {
    PassarelaSignatureStatus pss = new PassarelaSignatureStatus();

    pss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
    pss.setErrorMessage(msg);

    StringWriter trace = new StringWriter();
    i18nve.printStackTrace(new java.io.PrintWriter(trace));
    pss.setErrorStackTrace(trace.toString());

    log.error(msg, i18nve);

    return new PassarelaFullResults(pss);
  }

  private PassarelaFullResults getSignatureStatusAndResults(SignaturesSet ssf,
      PassarelaCustodyInfo custodyInfo, UsuariAplicacioConfiguracio config, Locale locale)
      throws I18NException {

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

      final String signType = pfis.getSignType();

      FitxerBean signedFile = null;
      if (ss.getSignedData() != null && ss.getSignedData().exists()) {

        signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getName());

        // Això depen del tipus de firma !!!!!

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
          signedFile.setMime(ConstantsV2.PDF_MIME_TYPE);
          String nom = signedFile.getNom();
          if (!nom.trim().toLowerCase().endsWith(".pdf")) {
            signedFile.setNom(nom.trim() + ".pdf");
          }
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
          signedFile.setMime("text/xml");
          String nom = signedFile.getNom();
          if (!nom.trim().toLowerCase().endsWith(".xml")) {
            signedFile.setNom(nom.trim() + ".xml");
          }
        } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
          signedFile.setMime("application/octet-stream");
          String nom = signedFile.getNom();
          if (!nom.trim().toLowerCase().endsWith(".csig")) {
            signedFile.setNom(nom.trim() + ".csig");
          }
        } else {
          signedFile.setMime("application/octet-stream");
        }
        signedFile.setTamany(ss.getSignedData().length());
        signedFile.setData(new DataHandler(new FileDataSource(ss.getSignedData())));
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
