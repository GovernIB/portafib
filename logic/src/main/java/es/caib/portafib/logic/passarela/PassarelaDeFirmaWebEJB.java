package es.caib.portafib.logic.passarela;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.utils.Constants;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PassarelaDeFirmaWebEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class PassarelaDeFirmaWebEJB
  extends AbstractPassarelaDeFirmaEJB<ISignatureWebPlugin>
  implements PassarelaDeFirmaWebLocal {


  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  private EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;
  
  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;


  SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  protected AbstractPluginLogicaLocal<ISignatureWebPlugin> getModulDeFirmaEJB() {
    return modulDeFirmaEjb;
  }
  
  
  @Override
  public String startTransaction(PassarelaSignaturesSet signaturesSet, String entitatID)
      throws I18NException, I18NValidationException {

    // Validar
    SignaturesSetBeanValidator ssbv = new SignaturesSetBeanValidator(validator, this,
        entitatID);
    final boolean isNou = true;
    ssbv.throwValidationExceptionIfErrors(signaturesSet, isNou);

    final String signaturesSetID = signaturesSet.getSignaturesSetID();


    // Canviar llista buida per NULL
    List<Long> filterPluginsByIDs = signaturesSet.getCommonInfoSignature().getAcceptedPlugins();
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

      PassarelaFileInfoSignature[] fileInfoSignatureArray = signaturesSet.getFileInfoSignatureArray();
      int[] originalNumberOfSignsArray = new int[fileInfoSignatureArray.length];


      int count = 0;
      for (int i = 0; i < fileInfoSignatureArray.length; i++) {
        
         PassarelaFileInfoSignature pfis = fileInfoSignatureArray[i];

        final String signID = pfis.getSignID();
        File original = getFitxerOriginalPath(signaturesSetID, signID);

        // obtenir ruta on guardar fitxer adaptat
        File adaptat = getFitxerAdaptatPath(signaturesSetID, signID);
        originalNumberOfSignsArray[count] = processFileToSign(locale, entitatID, pfis, original, adaptat);
        count++;
      }
      
      // Guardar
      storeSignaturesSet(new PassarelaSignaturesSetWebInternalUse(entitatID, originalNumberOfSignsArray, signaturesSet));
      
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
  public PassarelaSignaturesSetWebInternalUse getSignaturesSetFullByTransactionID(String transactionID)
      throws I18NException {
    PassarelaSignaturesSetWebInternalUse ss = readSignaturesSet(transactionID);
    return ss;
  }



  @Override
  public List<PassarelaSignatureResult> getSignatureResults(String transactionID)
      throws I18NException {
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
   * @param signaturesSetID
   * @return
   */
  protected PassarelaSignaturesSetWebInternalUse readSignaturesSet(String transactionID) {

    checkExpiredSignaturesSet();

    synchronized (passarelaSignaturesSets) {
      return passarelaSignaturesSets.get(transactionID);
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
