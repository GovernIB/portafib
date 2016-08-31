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
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.logic.utils.StampCustodiaInfo;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.utils.Constants;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.plugins.barcode.IBarcodePlugin;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesSet;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.utils.PluginsManager;
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
      UsuariAplicacioJPA usrApp) {

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
      
      
      if (passarelaSignaturesSet.getCommonInfoSignature().getUsername() == null) {
        passarelaSignaturesSet.getCommonInfoSignature().setUsername(usrApp.getUsuariAplicacioID());
      }
      

      // TODO XYZ ZZZ AIXÔ ES UNA COPIA DEL QUE HI HA PER FIRMA EN SERVIDOR WEB
      // !!!!

      for (PassarelaFileInfoSignature pfis : passarelaSignaturesSet
          .getFileInfoSignatureArray()) {

        // (1) Moure FitxerBean (datasource en memòria) a Fitxer en el Sistema
        // d'arxius
        FitxerBean originalInfo = pfis.getFileToSign();
        final String signID = pfis.getSignID();
        File original = getFitxerOriginalPath(signaturesSetID, signID);
        try {
          FileUtils.copyInputStreamToFile(originalInfo.getData().getInputStream(), original);
        } catch (IOException e) {
          // TODO traduir
          String msg = "Error desconegut copiant fitxer des de DataSource ("
              + pfis.getSignID() + ") a " + original.getAbsolutePath() + ": " + e.getMessage();
          throw new I18NException("error.unknown", msg);
        }
        // Desreferenciam memoria
        originalInfo.setData(null);
        // Alliberar memòria DataSource
        System.gc();

        // (2) Adaptam el fitxer

        // obtenir ruta on guardar fitxer adaptat
        File adaptat = getFitxerAdaptatPath(signaturesSetID, signID);

        if (FileInfoSignature.SIGN_TYPE_PADES.equals(pfis.getSignType())) {

          // (a.2.1) Converteix a PDF si necessari. En qualsevol cas deixa el
          // fitxer a "adaptat"
          convertirDocumentAPDF(originalInfo, original, adaptat);

          StampTaulaDeFirmes stampTaulaDeFirmes = null;

          // (a.2.2) Afegir taula de firmes
          final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();
          if (posicioTaulaFirmesID != FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT) {
            final byte[] logoSegellJpeg;

            final String titol;
            final String descripcio;
            final String signantLabel;
            final String resumLabel;
            final String titolLabel;
            final String descLabel;

            PassarelaSignaturesTableHeader tableHeader = pfis.getSignaturesTableHeader();

            if (tableHeader == null) {

              final Long logoSegellID = entitatEjb.executeQueryOne(EntitatFields.LOGOSEGELLID,
                  EntitatFields.ENTITATID.equal(entitatID));
              try {
                logoSegellJpeg = FileUtils.readFileToByteArray(FileSystemManager
                    .getFile(logoSegellID));
              } catch (IOException e) {
                // TODO Traduir
                String msg = "Error desconegut llegint logo-segell de l'entitat " + entitatID
                    + ": " + e.getMessage();                
                throw new I18NException("error.unknown", msg);
              }

              Locale localeSign = new Locale(pfis.getLanguageSign());

              titol = I18NLogicUtils.tradueix(locale, "tauladefirmes");
              descripcio = ""; // TODO Posar alguna cosa ????

              signantLabel = I18NLogicUtils.tradueix(localeSign, "signant");
              resumLabel = I18NLogicUtils.tradueix(localeSign, "resumdefirmes");
              titolLabel = I18NLogicUtils.tradueix(localeSign, "titol");
              descLabel = I18NLogicUtils.tradueix(localeSign, "descripcio");

            } else {

              logoSegellJpeg = tableHeader.getLogoJpeg();

              titol = tableHeader.getTitleFieldValue();
              descripcio = tableHeader.getDescriptionFieldValue();

              signantLabel = tableHeader.getSignatureLabel();
              resumLabel = tableHeader.getTitle();
              titolLabel = tableHeader.getTitleFieldLabel();
              descLabel = tableHeader.getDescriptionFieldLabel();
            }

            stampTaulaDeFirmes = new StampTaulaDeFirmes(pfis.getSignNumber(),
                posicioTaulaFirmesID, signantLabel, resumLabel, descLabel, descripcio,
                titolLabel, titol, logoSegellJpeg);
          }

          StampCustodiaInfo stampCodiSegurVerificacio = null;
          PassarelaSecureVerificationCodeStampInfo pcvsStamp = pfis
              .getSecureVerificationCodeStampInfo();

          if (pcvsStamp != null) {

            // TODO Message Position s'usarà per CodiBarPosition !!!!!
            if (pcvsStamp.getMessagePosition() != SecureVerificationCodeStampInfo.POSITION_NONE) {

              String codiBarresClass = codiBarresEjb.executeQueryOne(
                  CodiBarresFields.CODIBARRESID,
                  CodiBarresFields.NOM.equal(pcvsStamp.getBarCodeType()));

              if (codiBarresClass == null) {
                // TODO Traduir
                String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom "
                    + pcvsStamp.getBarCodeType();                
                throw new I18NException("error.unknown", msg);
              }

              IBarcodePlugin barcodePlugin;
              barcodePlugin = (IBarcodePlugin) PluginsManager
                  .instancePluginByClassName(codiBarresClass);

              stampCodiSegurVerificacio = new StampCustodiaInfo();

              stampCodiSegurVerificacio.setBarcodePlugin(barcodePlugin);
              stampCodiSegurVerificacio.setBarcodeText(pcvsStamp.getBarCodeText());
              stampCodiSegurVerificacio.setMissatgeCustodia(pcvsStamp.getMessage());
              stampCodiSegurVerificacio.setPagines(pcvsStamp.getPages());
              stampCodiSegurVerificacio.setPosicioCustodiaInfo(pcvsStamp.getMessagePosition());

            }
          }

          afegirTaulaDeFirmesCodiSegurVerificacio(adaptat, stampTaulaDeFirmes,
              stampCodiSegurVerificacio);
          // Final IF PADES
        } else {
          if (!FileInfoSignature.SIGN_TYPE_XADES.equals(pfis.getSignType())) {
            log.warn("Tipus de Signatura " + pfis.getSignType() + " no gestionat dins "
                + this.getClass().getName(), new Exception());
          }

          // L'original és l'adaptat, per això el movem allà
          try {
            FileUtils.moveFile(original, adaptat);
          } catch (Exception e) {
            log.error(" Error movent fitxer des de " + original.getAbsolutePath() + " a "
                + adaptat.getAbsolutePath(), e);
            throw new I18NException("error.copyfile", original.getAbsolutePath(),
                adaptat.getAbsolutePath());
          }

        }
      } // Final de For

      // 1.- Cridar convertir PassarelaSignaturesSet a SignaturesSet
      SignaturesSet ss = SignatureUtils.passarelaSignaturesSetToSignaturesSet(this,
          segellDeTempsPublicEjb, signaturesSetID, passarelaSignaturesSet, entitat);

      // 2.- Cercar Plugin associats als IDs
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

        if (signaturePlugin.filter(ss)) {
          modulFiltered = (PluginJPA) modulDeFirmaJPA;
          break;
        }

      }

      if (modulFiltered == null || signaturePlugin == null) {
        I18NException i18ne = new I18NException("signaturemodule.notfound");
        throw i18ne;
      }

      // Cridar al plugin per a que firmi
      // TODO XYZ ZZZ Com funciona lo del TIMESTAMP !!!!!
      String timestampUrlBase = null;
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
          signedFile.setMime(Constants.PDF_MIME_TYPE);
        } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
          signedFile.setMime("text/xml");
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
