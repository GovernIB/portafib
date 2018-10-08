package es.caib.portafib.logic.passarela;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ValidacioFirmesLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.logic.validator.SignaturesSetBeanValidator;
import es.caib.portafib.logic.validator.SignaturesSetValidator;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.utils.ConstantsV2;

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
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSetWeb;
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
  
  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal usuariAplicacioConfiguracioEjb;
  
  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;
  
  @EJB(mappedName = ValidacioFirmesLogicaLocal.JNDI_NAME)
  protected ValidacioFirmesLogicaLocal validacioFirmesEjb;


  SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  protected AbstractPluginLogicaLocal<ISignatureWebPlugin> getModulDeFirmaEJB() {
    return modulDeFirmaEjb;
  }
  
  
  @Override
  public String startTransaction(PassarelaSignaturesSet signaturesSet, String entitatID, 
      boolean fullView, UsuariAplicacioJPA usuariAplicacio)
      throws I18NException, I18NValidationException {

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
          passarelaFileInfoSignature.setSignaturesTableLocation(ConstantsV2.TAULADEFIRMES_SENSETAULA); // = 0
          passarelaFileInfoSignature.setSignaturesTableHeader(null);
        }
      }
    }
    

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
        originalNumberOfSignsArray[count] = processFileToSign(locale, entitatID, pfis,
            original, adaptat, usuariAplicacio);
        count++;
      }
      
      // Guardar
      storeSignaturesSet(new PassarelaSignaturesSetWebInternalUse(entitatID,
          originalNumberOfSignsArray, fullView, signaturesSet, usuariAplicacio.getUsuariAplicacioID()));

    } catch (I18NException i18n) {
      deleteSignaturesSet(signaturesSetID);
      throw i18n;
    }

    // URL on Iniciar el proces de firma
    // XYZ ZZZ TODO Això ho ha de collir de la propietat URL PortaFIB de UsuariApplicacioConfig
    // XYZ ZZZ TODO Configurar que si getAppUrl val null llavors llanci excepció
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
  public List<PassarelaSignatureResult> getSignatureResults(String transactionID, boolean addFiles)
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

      PassarelaSignatureResult psr = convertToPassarelaSignatureResult(addFiles, ss, pfis);
      list.add(psr);
    }

    return list;

  }


  protected PassarelaSignatureResult convertToPassarelaSignatureResult(boolean addFiles,
      PassarelaSignatureStatusWebInternalUse ss, PassarelaFileInfoSignature pfis) {
    
    
    final String id = pfis.getSignID();
    
    PassarelaSignatureResult psr = new PassarelaSignatureResult();

    psr.setStatus(ss.getStatus());
    psr.setErrorMessage(ss.getErrorMessage());
    psr.setErrorStackTrace(ss.getErrorStackTrace());
    psr.setSignID(id);

    if (addFiles) {
      if (ss.getFitxerFirmat() != null && ss.getFitxerFirmat().exists()) {
        DataSource fds = new FileDataSource(ss.getFitxerFirmat());
 
        FitxerBean signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getNom());
        signedFile.setMime(ConstantsV2.PDF_MIME_TYPE);
        signedFile.setTamany(ss.getFitxerFirmat().length());
        signedFile.setData(new DataHandler(fds));
        signedFile.setDescripcio("Signed Document");
 
        psr.setSignedFile(signedFile);
      }
    }
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
        return  convertToPassarelaSignatureResult(true, ss, pfis);
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
    StatusSignaturesSet sss = ss.getStatusSignaturesSet();
    
    StatusSignaturesSet statusFinal = null;

    Map<String, File> fitxersFirmatsBySignID = new HashMap<String, File>();
    
    PassarelaSignaturesSetWebInternalUse ssf;
    ssf = getSignaturesSetFullByTransactionID(transactionID);
    if (ssf == null) {
      //  "Ha tardat massa temps en firmar. Torni a intentar-ho."
      throw new I18NException("firmar.tempsexcedit");
    }
        
    Map<String, PassarelaSignatureStatusWebInternalUse> statusBySignID = ssf.getStatusBySignatureID();
    
    
    switch(sss.getStatus()) {
    
      case StatusSignaturesSet.STATUS_FINAL_OK:
        {
          // Revisam les firma
          
          statusFinal = sss;
          for(FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
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
                  + signID + "(" + fis.getName() + ")"
                  + ", el fitxer firmat o no s'ha definit o no existeix";
                status.setErrorMsg(msg);
                statusFinal = status;
                
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
          validateSignatures(ssf);
          
        }
        
      
      break;
      
      case StatusSignaturesSet.STATUS_FINAL_ERROR:

        if (sss.getErrorException() == null) {
          log.error("ERROR EN PASSARELA PORTAFIB" + sss.getErrorMsg());
        } else {
          log.error("ERROR EN PASSARELA PORTAFIB" + sss.getErrorMsg(), sss.getErrorException());
        }

        statusFinal = sss;
      break;
      
      
      case StatusSignaturesSet.STATUS_CANCELLED:
        if (sss.getErrorMsg() == null) {
          sss.setErrorMsg(I18NLogicUtils.tradueix(
              new Locale(ss.getCommonInfoSignature().getLanguageUI()), "plugindefirma.cancelat"));
        }
        statusFinal = sss;
      break;
        
      default:
        String inconsistentState = "El mòdul de firma ha finalitzat inesperadament "
            + "(no ha establit l'estat final del procés de firma)";
        sss.setErrorMsg(inconsistentState);
        sss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
        statusFinal = sss;
        log.error(inconsistentState, new Exception());
    
    }
    
    
    
    
    // Copiar Estat General
    ssf.setStatus(statusFinal.getStatus());
    ssf.setErrorMessage(statusFinal.getErrorMsg());
    
    if (statusFinal.getErrorException() != null) {
      StringWriter trace= new StringWriter();
      statusFinal.getErrorException().printStackTrace(new java.io.PrintWriter(trace));
      ssf.setErrorStackTrace(trace.toString());
    }
    return ssf;
  }

  
  /**
   *  
   * Validar certificat i firmes, i comprovar que els NIFs corresponen
   * @param ssf
   */
  protected void validateSignatures(PassarelaSignaturesSetWebInternalUse ssf) {
    
    Map<String, PassarelaSignatureStatusWebInternalUse> statusBySignID = ssf.getStatusBySignatureID();
    
    final String languageUI = ssf.getSignaturesSet().getCommonInfoSignature().getLanguageUI();
    
    final UsuariAplicacio usuariAplicacio;
    final UsuariAplicacioConfiguracio usuariAplicacioConfig;
    {
      final String applicationID = ssf.getApplicationID();
      
      List<UsuariAplicacioConfiguracio> list;
      try {
        list = usuariAplicacioConfiguracioEjb.select(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID.equal(applicationID));
      } catch (I18NException e) {
        // TODO Auto-generated catch block
        log.error("Error cercant UsuariAplicacioConfiguracio per usuariaplicacio = " + applicationID ,e);
        list =null;
      }
      
      if (list == null || list.size() == 0) {
        log.info("XYZ ZZZ No s'ha definit configuració per l´Usuari Aplicació " + applicationID);      
        return;
      }
    
      usuariAplicacioConfig = list.get(0);
      
      usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(applicationID);
      
    }
    
    final String entitatID = usuariAplicacio.getEntitatID();
    
    for(PassarelaFileInfoSignature fis : ssf.getSignaturesSet().getFileInfoSignatureArray()) {
        // TODO check null
      final String signID = fis.getSignID();
      PassarelaSignatureStatusWebInternalUse status = statusBySignID.get(signID);
       
       
       if (status.getStatus() != StatusSignature.STATUS_FINAL_OK) {
         continue;
       }

       
       
       
      
      // (a) Verificar que el certificat emprat en la firma es correcte (vàlid)
      /* XYZ ZZZ
      int tipusFirma = peticioDeFirma.getTipusFirmaID();
      String tipusFirmaNom;
      String mime;
      String extension;
      String nifFirmant;
      switch (tipusFirma) {
      case ConstantsV2.TIPUSFIRMA_PADES:
        extension = "pdf";
        mime = ConstantsV2.PDF_MIME_TYPE;
        
        tipusFirmaNom = "PAdES";
  
        Map<Integer, Long> fitxersByNumFirma = null;
        if (numFirmaPortaFIB != 1) {
          fitxersByNumFirma = getFitxersFirmatsOfPeticioDeFirma(peticioDeFirma
              .getPeticioDeFirmaID());
        }
  
        Long fitxerOriginalID = peticioDeFirma.getFitxerAdaptatID();
  
        final boolean ignoreCheckPostSign = PropietatGlobalUtil.ignoreCheckPostSign(entitatID);
  
        InformacioCertificat info;
        info = PdfUtils.checkCertificatePADES(fitxerOriginalID, fitxersByNumFirma, signatureFile,
            numFirmaPortaFIB, numFirmesOriginals, ignoreCheckPostSign);
  
        // Obtenir informació del certificat
        final boolean isDebug = log.isDebugEnabled();
        if (isDebug) {
          log.debug("PropietatGlobalUtil.ignoreCheckPostSign: " + ignoreCheckPostSign);
          log.debug("NumeroSerieCertificat = " + info.getNumeroSerie());
          log.debug("Emissor = " + info.getEmissorID());
          log.debug("Subject = " + info.getSubject());
          log.debug("NIF = " + info.getNifResponsable());
        }
        firma.setNumeroSerieCertificat(info.getNumeroSerie());
        firma.setEmissorCertificat(info.getEmissorID());
        firma.setNomCertificat(info.getSubject());
        nifFirmant = info.getNifResponsable();
  
        break;
  
      default:
        throw new Exception("No esta implementada la verificació de fitxers firmats"
            + " amb tipus de firma " + tipusFirma);
      }
      */
  
      // (b) Verificar que el NIF del certificat correspon amb qui tenia que
      // firmar
      /* XYZ ZZZ
      Boolean comprovarNifFirma = usuariEntitatEjb.executeQueryOne(
          new UsuariEntitatQueryPath().ENTITAT().COMPROVARNIFFIRMA(),
          UsuariEntitatFields.USUARIENTITATID.equal(estatDeFirma.getUsuariEntitatID()));
  
      // Obtenir informació del certificat
      if (comprovarNifFirma != null && comprovarNifFirma == true) {
        final StringField NIF = new UsuariEntitatQueryPath().USUARIPERSONA().NIF();
        final Where where = UsuariEntitatFields.USUARIENTITATID.equal(estatDeFirma
            .getUsuariEntitatID());
        String expectedNif = usuariEntitatEjb.executeQueryOne(NIF, where);
        LogicUtils.checkExpectedNif(nifFirmant, expectedNif);
      }
      */

      // (c) // Validar la Firma
      try {
        
        final Long pluginValidateSignatureID = entitatEjb.executeQueryOne(EntitatFields.PLUGINVALIDAFIRMESID,
            EntitatFields.ENTITATID.equal(entitatID));
        
        
        // XYZ ZZZ Falta si validarFirma està a true en la configuració de l'UsrApp
        Boolean isValidarFirma = usuariAplicacioConfig.getValidarFirma();
        if (isValidarFirma == null) {
          // El que digui la entitat: si està definit el plugin llavors
          // es valida, en cas contrari no es valida
        } else {
          
          if (pluginValidateSignatureID == null && isValidarFirma.booleanValue() == true) {
            // Com que des de UsuariAplicació ens requereixen Signar, però l'entitat
            // no té definit el Plugin de Validació llavors llançam un error
            status.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
            status.setErrorMessage(I18NLogicUtils.tradueix(new Locale(languageUI),
                "error.passarela.sensevalidadorfirmesdinsentitat"));
            continue;            
          }
          
          
        }
        

        if (pluginValidateSignatureID == null) {
          log.info("XYZ ZZZ debug No s'ha definit plugin De validacio dins de l'entitat");
        } else {
          InputStream documentDetachedFile;
          {
            FitxerBean firmaOriginalDetached = fis.getPreviusSignatureDetachedFile();
            if (firmaOriginalDetached != null) {
              try {
                documentDetachedFile = firmaOriginalDetached.getData().getInputStream();
              } catch (IOException e) {
                // XYZ ZZZ traduir
                final String msg = "Error desconegut al intentar obtenir contingut del fitxer detached: " + e.getMessage();
                log.error(msg, e);
                throw new I18NException("genapp.comodi", msg);                
              }
            } else {
              documentDetachedFile = null;
            }
          }
          validacioFirmesEjb.validateSignature(fis.getSignType(), pluginValidateSignatureID, documentDetachedFile,
              status.getFitxerFirmat(), languageUI);
        }
      
      } catch(I18NException i18n) {
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
   * @param signaturesSetID
   * @return
   */
  protected PassarelaSignaturesSetWebInternalUse readSignaturesSet(String transactionID) {

    checkExpiredSignaturesSet();
    
    log.info("XYZ ZZZ  Calling readSignaturesSet("  + transactionID + ")");

    synchronized (passarelaSignaturesSets) {

      PassarelaSignaturesSetWebInternalUse pss = passarelaSignaturesSets.get(transactionID);
      if (pss == null) {
        
        log.info("XYZ ZZZ  La transacció "  + transactionID + " no existeix !!!!!");

        if (passarelaSignaturesSets.size() == 0) {
          log.info("XYZ ZZZ  passarelaSignaturesSets ESTA BUIT  !!!!!");
        } else {
          log.info("XYZ ZZZ Contingut de  passarelaSignaturesSets:");
          for(String id: passarelaSignaturesSets.keySet()) {
            log.info("          XYZ ZZZ  EXISTEIX ID :  "  + id );
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

    // BORRAR TOT DIRECTORI
    File basePath = getTransactionPath(signaturesSetID);
    try {
      FileUtils.deleteDirectory(basePath);
    } catch (IOException e) {
      log.error(
          "Error eliminant directori " + basePath + "(S'ha d'esborrar manualment): "
              + e.getMessage(), e);
    }
    
    if (log.isDebugEnabled()) {
      log.debug("Eliminant transacció WEB ]" +  signaturesSetID + "[");
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
