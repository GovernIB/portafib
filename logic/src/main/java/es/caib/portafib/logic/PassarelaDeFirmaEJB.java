package es.caib.portafib.logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.passarela.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatusFull;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetFull;
import es.caib.portafib.logic.passarela.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.utils.StampCustodiaInfo;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.Constants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.barcode.IBarcodePlugin;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.utils.PluginsManager;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PassarelaDeFirmaEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class PassarelaDeFirmaEJB implements PassarelaDeFirmaLocal {

  protected static final Logger log = Logger.getLogger(PassarelaDeFirmaEJB.class);

  // public static final Locale locale = new Locale("ca");

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  private EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;
  
  @EJB(mappedName = ModulDeFirmaLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaLogicaLocal modulDeFirmaEjb;


  SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  public String startTransaction(PassarelaSignaturesSet signaturesSet, String entitatID)
      throws I18NException, I18NValidationException {

    // Validar
    SignaturesSetBeanValidator ssbv = new SignaturesSetBeanValidator(validator, this,
        entitatID);
    final boolean isNou = true;
    ssbv.throwValidationExceptionIfErrors(signaturesSet, isNou);

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    // Guardar
    storeSignaturesSet(new PassarelaSignaturesSetFull(entitatID, signaturesSet));
    
    // Canviar llista buida per NULL
    List<Long> filterPluginsByIDs = signaturesSet.getCommonInfoSignature().getAcceptedPlugins();
    if (filterPluginsByIDs != null && filterPluginsByIDs.size() == 0) {
      signaturesSet.getCommonInfoSignature().setAcceptedPlugins(null);
    }
        
    

    try {

      for (PassarelaFileInfoSignature pfis : signaturesSet.getFileInfoSignatureArray()) {

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
            final String langUI;
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
                log.error(msg, e);
                throw new I18NException("error.unknown", msg);
              }

              langUI = pfis.getLanguageSign();

              Locale locale = new Locale(langUI);

              titol = I18NLogicUtils.tradueix(locale, "tauladefirmes");
              descripcio = ""; // TODO Posar alguna cosa ????

              signantLabel = I18NLogicUtils.tradueix(locale, "signant");
              resumLabel = I18NLogicUtils.tradueix(locale, "resumdefirmes");
              titolLabel = I18NLogicUtils.tradueix(locale, "titol");
              descLabel = I18NLogicUtils.tradueix(locale, "descripcio");

            } else {

              logoSegellJpeg = tableHeader.getLogoJpeg();

              langUI = pfis.getLanguageSign();
              titol = tableHeader.getTitleFieldValue();
              descripcio = tableHeader.getDescriptionFieldValue();

              signantLabel = tableHeader.getSignatureLabel();
              resumLabel = tableHeader.getTitle();
              titolLabel = tableHeader.getTitleFieldLabel();
              descLabel = tableHeader.getDescriptionFieldLabel();
            }

            /*
             * 
             * 
             * adaptat, logoSegell, posicioTaulaFirmesID, titol, descripcio,
             * signantLabel, resumLabel, titolLabel, descLabel
             */

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
                log.error(msg, new Exception());
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
            log.warn("Tipus de Signatura " + pfis.getSignType() 
                + " no gestionat dins " + this.getClass().getName(), new Exception() );
          }
          
          // L'original és l'adaptat, per això el movem allà
          try {
            FileUtils.moveFile(original, adaptat);
          }  catch(Exception e) { 
            log.error(" Error movent fitxer des de " + original.getAbsolutePath()
                + " a " + adaptat.getAbsolutePath() );
            throw new I18NException("error.copyfile", original.getAbsolutePath(), adaptat.getAbsolutePath() );
          }
   
        }

      }
    } catch (I18NException i18n) {
      deleteSignaturesSet(signaturesSetID);
      throw i18n;
    }

    // URL on Iniciar el proces de firma
    final String absoluteURL = PropietatGlobalUtil.getAppUrl() + PASSARELA_CONTEXTPATH
        + "/start/" + signaturesSetID;
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
  public PassarelaSignatureStatus getStatusTransaction(String transactionID) throws I18NException {

    PassarelaSignaturesSetFull ss = readSignaturesSet(transactionID);

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
  public PassarelaSignaturesSetFull getSignaturesSetFullByTransactionID(String transactionID)
      throws I18NException {
    PassarelaSignaturesSetFull ss = readSignaturesSet(transactionID);
    return ss;
  }



  @Override
  public List<PassarelaSignatureResult> getSignatureResults(String transactionID)
      throws I18NException {
    PassarelaSignaturesSetFull ssf = readSignaturesSet(transactionID);
    if (ssf == null) {
      return null;
    }

    Map<String, PassarelaFileInfoSignature> fileInfoSignMap = new HashMap<String, PassarelaFileInfoSignature>();
    for (PassarelaFileInfoSignature pfis : ssf.getSignaturesSet().getFileInfoSignatureArray()) {
      fileInfoSignMap.put(pfis.getSignID(), pfis);
    }

    Map<String, PassarelaSignatureStatusFull> map = ssf.getStatusBySignatureID();
    Set<String> signsID = map.keySet();
    List<PassarelaSignatureResult> list = new ArrayList<PassarelaSignatureResult>();
    for (String id : signsID) {
      PassarelaSignatureStatusFull ss = map.get(id);

      PassarelaFileInfoSignature pfis = fileInfoSignMap.get(id);

      PassarelaSignatureResult psr = new PassarelaSignatureResult();

      psr.setStatus(ss.getStatus());
      psr.setErrorMessage(ss.getErrorMessage());
      psr.setErrorStackTrace(ss.getErrorStackTrace());
      psr.setSignID(id);

      if (ss.getFitxerFirmat() != null && ss.getFitxerFirmat().exists()) {
        DataSource fds = new FileDataSource(ss.getFitxerFirmat());

        FitxerBean signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getNom());
        signedFile.setMime(Constants.PDF_MIME_TYPE);
        signedFile.setTamany(ss.getFitxerFirmat().length());
        signedFile.setData(new DataHandler(fds));
        signedFile.setDescripcio("Signed Document");

        psr.setSignedFile(signedFile);
      }
      list.add(psr);
    }

    return list;

  }

  @Override
  public void closeTransaction(String transactionID) {
    deleteSignaturesSet(transactionID);
  }

  @Override
  public EntitatJPA getEntitat(String entitatID) throws I18NException {
    return entitatEjb.findByPrimaryKey(entitatID);
  }

  @Override
  public int getTimeStampPolicy(String entitatID) throws I18NException {

    Integer segellDeTemps = entitatEjb.executeQueryOne(EntitatFields.SEGELLDETEMPSVIAWEB,
        EntitatFields.ENTITATID.equal(entitatID));

    if (segellDeTemps == null) {
      return Constants.SEGELLDETEMPSVIAWEB_NOUSAR;
    } else {
      return segellDeTemps;
    }

  }
  
  
  @Override
  public boolean providesTimeStampGenerator(String signType, String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode) {
   
    
    
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<ISignatureWebPlugin> plugins = modulDeFirmaEjb.getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (ISignatureWebPlugin iSignatureWebPlugin : plugins) {
        if (iSignatureWebPlugin.providesTimeStampGenerator(signType)) {
          return true;
        }
      }
      
    } catch (I18NException e) {
      log.error("Error desconegut intentant esbrinar els plugins que suporten "
          + "Segellat de Temps per tipus de firma " + signType + ": " + e.getMessage() , e);
    }
    return false;
    
    
  }
  
  
  
  
  

  // Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
  @Override
  public String[] getSupportedSignatureTypes(String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode) {
    // TODO Falta CADes, ...

    Set<String> tipus = new HashSet<String>();
    
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<ISignatureWebPlugin> plugins = modulDeFirmaEjb.getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (ISignatureWebPlugin iSignatureWebPlugin : plugins) {
        String[] tipusA = iSignatureWebPlugin.getSupportedSignatureTypes(); 
        if (tipusA != null) {
          tipus.addAll(Arrays.asList(tipusA));
        }
      }
      
      return tipus.toArray(new String[tipus.size()]);
      
    } catch (I18NException e) {
      log.error("Error desconegut intentant esbrinar els tipus de firma"
          + " que suporten els plugins: " + e.getMessage() , e);
      return new String[0];
    }
    
    
    
  }

  // Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
  @Override
  public String[] getSupportedSignatureAlgorithms(String signType, String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode) {
   
    Set<String> tipusAlgo = new HashSet<String>();
    
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<ISignatureWebPlugin> plugins = modulDeFirmaEjb.getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (ISignatureWebPlugin iSignatureWebPlugin : plugins) {
        String[] tipusA = iSignatureWebPlugin.getSupportedSignatureAlgorithms(signType); 
        if (tipusA != null) {
          tipusAlgo.addAll(Arrays.asList(tipusA));
        }
      }
      
      return tipusAlgo.toArray(new String[tipusAlgo.size()]);
      
    } catch (I18NException e) {
      log.error("Error desconegut intentant esbrinar els algorismes de firma acceptats "
          + " pel tipus de firma " + signType + " que suporten els plugins: "
          + e.getMessage() , e);
      return new String[0];
    }
    
    
  }

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // --- UTILITATS FITXERS PADES: conversio i taula de firmes --------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  /**
   * 
   * @param usuariEntitat
   * @param id
   * @param autoFirmaForm
   * @return
   */
  protected void convertirDocumentAPDF(Fitxer srcInfo, File src, File dst)
      throws I18NException {

    try {

      Fitxer fitxerConvertit = PdfUtils.convertToPDF(src, srcInfo);

      if (fitxerConvertit == srcInfo) {
        // Es un PDF. Movem l'original a l'adaptat
        FileUtils.moveFile(src, dst);
      } else {
        // No és un PDF, ho substituim pel fitxer convertit

        InputStream is = fitxerConvertit.getData().getInputStream();

        FileUtils.copyInputStreamToFile(is, dst);

      }
      // OK

    } catch (Exception e) {
      log.error("Error desconegut convertint document a pdf: " + e.getMessage(), e);
      throw new I18NException("formatfitxer.conversio.error", new I18NArgumentString(
          e.getMessage()));
    }

  }

  protected void afegirTaulaDeFirmesCodiSegurVerificacio(File fitxerPDF,
      StampTaulaDeFirmes stampTaulaDeFirmes, StampCustodiaInfo stampCustodiaInfo)
          throws I18NException {

    // La pujada de fitxers des d'autofirma ho gestiona la classe
    // PortaFIBCommonsMultipartResolver
    final Long maxSizeFitxerAdaptat = null;
    try {
      File tmpDest = File.createTempFile("Passarela_Taula_de_Firmes", ".pdf");

      PdfUtils.add_TableSign_Attachments_CustodyInfo(fitxerPDF, tmpDest, null,
          maxSizeFitxerAdaptat, stampTaulaDeFirmes, stampCustodiaInfo);

      // Destí no pot existir !!!
      fitxerPDF.delete();

      FileUtils.moveFile(tmpDest, fitxerPDF);
    } catch (Exception e) {
      // TODO traduir
      String msg = "Error desconegut afegint taula de firmes a fitxer ("
          + fitxerPDF.getAbsolutePath() + "): " + e.getMessage();
      throw new I18NException("error.unknown", msg);
    }
  }

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -------------- DIRECTORI DE FITXERS TEMPORALS -------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  public static final String PASSARELA_DIRNAME = "PASSARELA";

  public static final String passarelaBasePath;

  static {
    final File base = new File(FileSystemManager.getFilesPath(), PASSARELA_DIRNAME);
    base.mkdirs();
    passarelaBasePath = base.getAbsolutePath();
  }

  public File getFitxerOriginalPath(String transactionID, String signID) {
    File p = new File(passarelaBasePath + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "original");
  }

  public File getFitxerAdaptatPath(String transactionID, String signID) {
    File p = new File(passarelaBasePath + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "adaptat");
  }

  public File getFitxerFirmatPath(String transactionID, String signID) {
    File p = new File(passarelaBasePath + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "firmat");
  }

  public static File getTransactionPath(String transactionID) {
    File p = new File(passarelaBasePath + File.separatorChar + transactionID);
    p.mkdirs();
    return p;
  }

  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T S ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------

  private static final Map<String, PassarelaSignaturesSetFull> passarelaSignaturesSets = new HashMap<String, PassarelaSignaturesSetFull>();

  private static long lastCheckFirmesCaducades = 0;

  /**
   * Fa neteja
   * 
   * @param signaturesSetID
   * @return
   */
  protected static PassarelaSignaturesSetFull readSignaturesSet(String transactionID) {

    checkExpiredSignaturesSet();

    synchronized (passarelaSignaturesSets) {
      return passarelaSignaturesSets.get(transactionID);
    }
  }

  protected static void storeSignaturesSet(PassarelaSignaturesSetFull signaturesSet) {
    checkExpiredSignaturesSet();
    synchronized (passarelaSignaturesSets) {
      passarelaSignaturesSets.put(signaturesSet.getSignaturesSet().getSignaturesSetID(),
          signaturesSet);
    }
  }

  protected static void deleteSignaturesSet(String transactionID) {

    PassarelaSignaturesSetFull pss = readSignaturesSet(transactionID);

    if (pss == null) {
      log.warn("NO Existeix signaturesSetID igual a " + transactionID);
      return;
    }

    deleteSignaturesSet(pss);
  }

  protected static void deleteSignaturesSet(PassarelaSignaturesSetFull pss) {

    final String signaturesSetID = pss.getSignaturesSet().getSignaturesSetID();

    // BORRAR TOT DIRECTORI
    File basePath = getTransactionPath(signaturesSetID);
    try {
      FileUtils.deleteDirectory(basePath);
    } catch (IOException e) {
      log.error(
          "Error eliminant directori " + basePath + "(S'ha de borrar manualment): "
              + e.getMessage(), e);
    }

    passarelaSignaturesSets.remove(signaturesSetID);
  }

  /**
   * Fer net peticions caducades SignaturesSet.getExpiryDate() Check si existeix
   * algun proces de firma caducat s'ha d'esborrar Com a mínim cada minut es
   * revisa si hi ha caducats
   */
  private static void checkExpiredSignaturesSet() {

    Long now = System.currentTimeMillis();

    final long un_minut_en_ms = 60 * 60 * 1000;

    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<PassarelaSignaturesSetFull> keysToDelete = new ArrayList<PassarelaSignaturesSetFull>();

      Set<String> ids = passarelaSignaturesSets.keySet();
      for (String id : ids) {
        PassarelaSignaturesSetFull ssf = passarelaSignaturesSets.get(id);
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

          for (PassarelaSignaturesSetFull pss : keysToDelete) {
            deleteSignaturesSet(pss);
          }
        }
      }
    }
  }


}
