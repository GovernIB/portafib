package es.caib.portafib.logic.passarela;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.utils.ConstantsV2;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
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
  public PassarelaFullResults signDocuments(
      PassarelaSignaturesSet passarelaSignaturesSet, EntitatJPA entitat,
      UsuariAplicacioJPA usrApp, UsuariAplicacioConfiguracio config) throws NoCompatibleSignaturePluginException {

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
//      if (passarelaSignaturesSet.getCommonInfoSignature().getUsername() == null) {
//        passarelaSignaturesSet.getCommonInfoSignature().setUsername(usrApp.getUsuariAplicacioID());
//      }
      
      PassarelaFileInfoSignature[] fisArray =  passarelaSignaturesSet.getFileInfoSignatureArray();
      int[] originalNumberOfSignsArray2 = new int[fisArray.length];
      for (int i = 0; i < fisArray.length; i++) {

        PassarelaFileInfoSignature pfis = fisArray[i];
       
        final String signID = pfis.getSignID();
        File original = getFitxerOriginalPath(signaturesSetID, signID);

        // obtenir ruta on guardar fitxer adaptat
        File adaptat = getFitxerAdaptatPath(signaturesSetID, signID);

        originalNumberOfSignsArray2[i] = processFileToSign(locale, entitatID, pfis,
            original, adaptat, usrApp);

      } // Final de For

      // 1.- Cridar convertir PassarelaSignaturesSet a SignaturesSet
      SignaturesSet ss = SignatureUtils.passarelaSignaturesSetToSignaturesSet(this,
          segellDeTempsPublicEjb, signaturesSetID, passarelaSignaturesSet, entitat);

      // 2.- Cercar Plugin associats als IDs
      
      ISignatureServerPlugin signaturePlugin;
      signaturePlugin = instantitatePluginDeFirmaEnServidor(config);
      
      if (!signaturePlugin.filter(ss)) {
        throw new NoCompatibleSignaturePluginException();
      }
      
      /*  XYZ ZZZ
      if (signaturePlugin != null) {
        
      } else {
      
      
        ****   S'ha de consultar a l'usuari aplicació
        
        List<Plugin> moduls;
        {
          List<Long> filterPluginsByIDs = passarelaSignaturesSet.getCommonInfoSignature()
              .getAcceptedPlugins();
          final Where where = modulDeFirmaServidorEjb.getWhere(entitatID);
          if (filterPluginsByIDs == null || filterPluginsByIDs.size() == 0) {
            // Cercam tots els plugins disponibles
            moduls = modulDeFirmaServidorEjb.select(where);
          } else {
  
            moduls = modulDeFirmaServidorEjb.select(Where.AND(where,
                PluginFields.PLUGINID.in(filterPluginsByIDs)));
          }
        }
  
        // 3.- Elegim el primer que passi el filtre
        PluginJPA modulFiltered = null;
        ISignatureServerPlugin signaturePlugin = null;
  
        for (Plugin modulDeFirmaJPA : moduls) {
          signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(modulDeFirmaJPA
              .getPluginID());
  
          if (signaturePlugin == null) {
            throw new I18NException("plugin.signatureserver.noexist",
                String.valueOf(modulDeFirmaJPA.getPluginID()));
          }
  
          ****+ 
          
          if (signaturePlugin.filter(ss)) {
            modulFiltered = (PluginJPA) modulDeFirmaJPA;
            break;
          }
  
        }
      }
     

      if (modulFiltered == null || signaturePlugin == null) {
        I18NException i18ne = new I18NException("signaturemodule.notfound");
        throw i18ne;
      }
       */

      // Cridar al plugin per a que firmi
      // XYZ hauria de cridar a l'altre
      String absoluteURL = PropietatGlobalUtil.getSignatureModuleAbsoluteURL();
      if (absoluteURL == null) {
         absoluteURL = PropietatGlobalUtil.getAppUrl();
      }
      
      // Segellat de temps
      String timestampUrlBase = SignatureUtils.
          getAbsoluteURLToTimeStampGeneratorPerFirmaEnServidor(absoluteURL,
              config.getPluginFirmaServidorID());
      
      // FIRMAR
      ss = signaturePlugin.signDocuments(ss, timestampUrlBase);

      // Convertir a Status i Results
      return getSignatureStatusAndResults(ss);

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
      UsuariAplicacioConfiguracio config) throws I18NException {
    ISignatureServerPlugin signaturePlugin;
    {
    
    Long pluginId = config.getPluginFirmaServidorID();

    PluginJPA modulDeFirmaJPA = modulDeFirmaServidorEjb.findByPrimaryKey(pluginId);
    
    
    signaturePlugin = modulDeFirmaServidorEjb.getInstanceByPluginID(modulDeFirmaJPA
        .getPluginID());
    }
    return signaturePlugin;
  }

  
  
  @Override
  public byte[] upgradeSignature(byte[] signature, SignatureTypeFormEnumForUpgrade signTypeForm,   
      UsuariAplicacioJPA usrApp, UsuariAplicacioConfiguracio config) 
          throws NoCompatibleSignaturePluginException, I18NException, Exception {
  
    
    // 1.- Cercar Plugin associats als IDs
    
    ISignatureServerPlugin signaturePlugin;
    signaturePlugin = instantitatePluginDeFirmaEnServidor(config);
    
    
    if (!signaturePlugin.isUpgradeSignatureSupported(signTypeForm)) {
      log.warn("El plugin " + signaturePlugin.getName(new Locale("ca")) + " no suporta extensió de firma.");
      throw new NoCompatibleSignaturePluginException();
    }
    
    
    ITimeStampGenerator timestampGenerator = null;
    
    if (signaturePlugin.isRequiredExternalTimeStampForUpgradeSignature()) {
       // XYZ ZZZZ TODO fa falta cercar en propietats d'aplicació
       // el Segellador de Temps seleccionat i instanciar-ho
       // timestampGenerator = << INSTANCIAR-HO >>
    }
    
    
    return signaturePlugin.upgradeSignature(signature, signTypeForm, timestampGenerator);
    
    
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

  private PassarelaFullResults getSignatureStatusAndResults(SignaturesSet ssf)
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

      
      PassarelaSignatureResult psr = new PassarelaSignatureResult();
      
      psr.setSignID(pfis.getSignID());
      
      statusToPassarelaStatus(ss, psr);

      if (ss.getSignedData() != null && ss.getSignedData().exists()) {

        FitxerBean signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getName());

        // Això depen del tipus de firma !!!!!
        final String signType = pfis.getSignType();
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

        psr.setSignedFile(signedFile);
      }

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
