package es.caib.portafib.back.controller.common;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signatureweb.api.CommonInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.FileInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signatureweb.api.PolicyInfoSignature;
import org.fundaciobit.plugins.signatureweb.api.SignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.StatusSignature;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.common.SignatureModuleController;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.back.utils.PortaFIBTimeStampGenerator;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.ModulDeFirmaPublicLogicaLocal;
import es.caib.portafib.logic.PassarelaDeFirmaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatusFull;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetFull;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.utils.Constants;


/**
 *
 * @author anadal
 *
 *
 */
@Controller
@RequestMapping(value = PassarelaDeFirmaLocal.PASSARELA_CONTEXTPATH)
public class PassarelaDeFirmaController  {

  protected final Logger log = Logger.getLogger(this.getClass());

  @EJB(mappedName = PassarelaDeFirmaLocal.JNDI_NAME)
  protected PassarelaDeFirmaLocal passarelaDeFirmaEjb;

  @EJB(mappedName = ModulDeFirmaPublicLogicaLocal.JNDI_NAME)
  protected ModulDeFirmaPublicLogicaLocal modulDeFirmaPublicEjb;
  
  @EJB(mappedName = SegellDeTempsPublicLogicaLocal.JNDI_NAME)
  protected SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb;



  /**
   * 
   */
  public PassarelaDeFirmaController() {
    super();
  }


  
    
  @RequestMapping(value = "/start/{transactionID}", method = RequestMethod.GET)
  public ModelAndView passarelaGet(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("transactionID") String transactionID) throws Exception, I18NException {


    PassarelaSignaturesSetFull ssf = passarelaDeFirmaEjb.getSignaturesSetFullByTransactionID(transactionID);

    PassarelaSignaturesSet ss = ssf.getSignaturesSet();

    EntitatJPA entitat = passarelaDeFirmaEjb.getEntitat(ssf.getEntitatID());
    
    FileInfoSignature[] fileInfoSignatureArray = new FileInfoSignature[ss.getFileInfoSignatureArray().length];
    int count = 0;
    for (PassarelaFileInfoSignature pfis : ss.getFileInfoSignatureArray()) {
      
      // NOTA Convertir Document a PDF i Afegir Taula de Firmes ja s'ha fet durant 
      // l'startTransacction via WS
      
      // Preparar pàgina
      final String idname = pfis.getName();

      final String reason = pfis.getReason();      
      final String location = pfis.getLocation();
      final String signerEmail = pfis.getSignerEmail();
      
      final int sign_number = 1;

      final String langUI = ss.getCommonInfoSignature().getLanguageUI();

      final String signID = pfis.getSignID();
      
      final int posicioTaulaFirmesID = pfis.getSignaturesTableLocation();
      

      // Ve d'un camp d'Autofirma que indica si l'usuari vol Segellat de Temps
      boolean userRequiresTimeStamp = pfis.isUseTimeStamp();

      File pdfAdaptat = passarelaDeFirmaEjb.getFitxerAdaptatPath(transactionID, signID);
      
      ITimeStampGenerator timeStampGenerator = null;
      
      timeStampGenerator = PortaFIBTimeStampGenerator.getInstance(segellDeTempsPublicEjb, 
          entitat, userRequiresTimeStamp );
      
      int signTypeID = getSignTypeToPortaFIB(pfis.getSignType());
      
      int signAlgorithm = getSignAlgorithmToPortaFIB(pfis.getSignAlgorithm());
      
      boolean signMode = getSignModeToPortaFIB(pfis.getSignMode());
            
      FileInfoSignature fis = SignatureModuleController.getFileInfoSignature(signID,
          pdfAdaptat, FileInfoSignature.PDF_MIME_TYPE, idname,
          posicioTaulaFirmesID, reason, location, signerEmail, sign_number, 
          langUI, signTypeID, signAlgorithm,
          signMode,
          Utils.getFirmatPerFormat(entitat, langUI), timeStampGenerator);
      
      
      fileInfoSignatureArray[count] = fis;
      count++;
      
    } 
  
    
    CommonInfoSignature commonInfoSignature;
    {
      
      String relativeControllerBase = SignatureModuleController.getRelativeControllerBase(request, PassarelaDeFirmaLocal.PASSARELA_CONTEXTPATH);
      final String urlFinal = relativeControllerBase + PassarelaDeFirmaLocal.PASSARELA_CONTEXTPATH_FINAL + "/" + transactionID;
      
      PassarelaCommonInfoSignature cis = ss.getCommonInfoSignature();
      final String username = cis.getUsername();
      final String administrationID = cis.getAdministrationID();
      final String langUI = cis.getLanguageUI();
      commonInfoSignature = SignatureModuleController.getCommonInfoSignature(entitat, 
          langUI, username, administrationID, urlFinal);
      
      PassarelaPolicyInfoSignature ppis = cis.getPolicyInfoSignature();
      if (ppis != null) {
        
        if (commonInfoSignature.getPolicyInfoSignature() != null) {
          log.warn("Ja s'ha definit una politica de Firma de l'entitat, "
              + " però la firma via passarel·la  n'ha definida una altra !!!. "
              + "S'utilitzarà la de la Passarel·la");
          
        }

        commonInfoSignature.setPolicyInfoSignature(
            new PolicyInfoSignature(ppis.getPolicyIdentifier(), ppis.getPolicyIdentifierHash(),
                ppis.getPolicyIdentifierHashAlgorithm(), ppis.getPolicyUrlDocument()));

      }
    }
    
    
    List<Long> filterByPluginsID = ss.getCommonInfoSignature().getAcceptedPlugins();
    
    if (filterByPluginsID != null && filterByPluginsID.size() == 0) {
      filterByPluginsID = null;
    }
    
    // Vull suposar que abans de 10 minuts haurà firmat
    java.util.Date caducitat = ss.getExpiryDate();

    PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(transactionID,
        caducitat, commonInfoSignature, fileInfoSignatureArray, entitat);
    
    // Filtres definits en l'Aplicació CLient
    signaturesSet.setFilterByPluginID(filterByPluginsID);
    
    // No tenim cap restricció de plugins per tipus de document
    signaturesSet.setPluginsFirmaBySignatureID(null);


    final String view = "PluginDeFirmaContenidor_Passarela";
    ModelAndView mav = SignatureModuleController.startPublicSignatureProcess(request, view, signaturesSet);

    if (log.isDebugEnabled()) {
      log.debug(" ===startPublicSignatureProcess() ==> signaturesSetID: " + transactionID);
      log.debug(" ===startPublicSignatureProcess() ==> urlFinal: " + commonInfoSignature.getUrlFinal());
    }
    
    return mav;

  }


  
  public static int getSignTypeToPortaFIB(String signType) throws I18NException {
    
    if(FileInfoSignature.SIGN_TYPE_PADES.equals(signType)) {
      return  Constants.TIPUSFIRMA_PADES;
    } else if (FileInfoSignature.SIGN_TYPE_CADES.equals(signType)) {
      return Constants.TIPUSFIRMA_CADES;
    } else if (FileInfoSignature.SIGN_TYPE_XADES.equals(signType)) {
      return Constants.TIPUSFIRMA_XADES;
    } else {
      // TODO Traduir
      throw new I18NException("error.unknown", "Tipus de firma no suportada: " + signType);
    }
  }

  public static int getSignAlgorithmToPortaFIB(String signAlgorithm) throws I18NException {
    if (FileInfoSignature.SIGN_ALGORITHM_SHA1.equals(signAlgorithm)) {
      return Constants.SIGN_ALGORITHM_SHA1WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA256.equals(signAlgorithm)) {
      return Constants.SIGN_ALGORITHM_SHA256WITHRSA;
    } else if ( FileInfoSignature.SIGN_ALGORITHM_SHA384.equals(signAlgorithm)) {
        return Constants.SIGN_ALGORITHM_SHA384WITHRSA;
    } else if (FileInfoSignature.SIGN_ALGORITHM_SHA512.equals(signAlgorithm)) {
        return Constants.SIGN_ALGORITHM_SHA512WITHRSA;
    } else {
      throw new I18NException("error.unknown", "Tipus d'algorisme no suportat " + signAlgorithm);
    }
  }
  
  
  public static boolean getSignModeToPortaFIB(int signMode) throws I18NException{
    if (FileInfoSignature.SIGN_MODE_IMPLICIT == signMode) {
      return Constants.SIGN_MODE_IMPLICIT;
    } else if (FileInfoSignature.SIGN_MODE_EXPLICIT == signMode) {
      return Constants.SIGN_MODE_EXPLICIT;
    } else {
      throw new I18NException("error.unknown", "Tipus de mode de firma no suportat " + signMode);
    } 
  }
  

  @RequestMapping(value =  PassarelaDeFirmaLocal.PASSARELA_CONTEXTPATH_FINAL + "/{transactionID}")
  public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("transactionID") String transactionID)throws Exception, I18NException {
  
    final boolean debug = log.isDebugEnabled();
  
    if(debug) {
      log.debug(" ===finalProcesDeFirma() ==> signaturesSetID: " + transactionID);
    }
  
    SignaturesSet ss;
    ss = SignatureModuleController.getSignaturesSetByID(request, transactionID, modulDeFirmaPublicEjb);
    
    // TODO  CHECK NULL i MOSTRAR MISSATGE DE FIRMA CADUCADA
    if (ss == null) {
      int count = 0;
      for (String key : SignatureModuleController.portaFIBSignaturesSets.keySet()) {
        log.error(" SIGNATURES SET = " + key);
        count++;
      }
      
      if (count == 0) {
        log.error("SIGNATURES SET ES BUITTTT ");
      }
      
    }
    

    StatusSignaturesSet sss = ss.getStatusSignaturesSet();
    
    StatusSignaturesSet statusFinal = null;

    Map<String, File> fitxersFirmatsBySignID = new HashMap<String, File>();
    
    PassarelaSignaturesSetFull ssf;
    ssf = passarelaDeFirmaEjb.getSignaturesSetFullByTransactionID(transactionID);
    if (ssf == null) {
      throw new Exception("Ha tardat massa temps en firmar. Torni a intentar-ho.");
    }
        
    Map<String, PassarelaSignatureStatusFull> statusBySignID = ssf.getStatusBySignatureID();
    
    
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
              PassarelaSignatureStatusFull pss = statusBySignID.get(signID);
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
                File firmat = passarelaDeFirmaEjb.getFitxerFirmatPath(transactionID, signID);
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
    
    // Eliminam la informació dins SignatureModuleController ja que tenim gurardada la 
    // informació dins la capa EJB
    SignatureModuleController.closeSignaturesSet(request, transactionID, modulDeFirmaPublicEjb);

    return new ModelAndView(new RedirectView(ssf.getSignaturesSet().getCommonInfoSignature().getUrlFinal()));
    
  }

}
