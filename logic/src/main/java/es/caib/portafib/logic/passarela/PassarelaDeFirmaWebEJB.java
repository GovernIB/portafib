package es.caib.portafib.logic.passarela;

import es.caib.portafib.ejb.CodiBarresLocal;
import es.caib.portafib.ejb.EstadisticaLocal;
import es.caib.portafib.jpa.CustodiaInfoJPA;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.BitacolaLogicaLocal;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaCustodyInfo;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatusList;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaValidationInfo;
import es.caib.portafib.logic.utils.CustodiaForStartPeticioDeFirma;
import es.caib.portafib.logic.utils.I18NLogicUtils;
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
import es.caib.portafib.model.entity.CustodiaInfo;
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
 * @author areus
 */
@Stateless(name = "PassarelaDeFirmaWebEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class PassarelaDeFirmaWebEJB extends AbstractPassarelaDeFirmaEJB<ISignatureWebPlugin>
    implements PassarelaDeFirmaWebLocal {

  @EJB(mappedName = CodiBarresLocal.JNDI_NAME)
  protected CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = EstadisticaLocal.JNDI_NAME, beanName = "EstadisticaEJB")
  protected EstadisticaLocal estadisticaEjb;

  @EJB(mappedName = BitacolaLogicaLocal.JNDI_NAME)
  protected BitacolaLogicaLocal bitacolaLogicaEjb;

  @EJB(mappedName = ModulDeFirmaWebLogicaLocal.JNDI_NAME, beanName = "ModulDeFirmaWebLogicaEJB")
  protected ModulDeFirmaWebLogicaLocal modulDeFirmaEjb;

  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  @EJB(mappedName = ValidacioCompletaFirmaLogicaLocal.JNDI_NAME)
  protected ValidacioCompletaFirmaLogicaLocal validacioCompletaLogicaEjb;

  @EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
  protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

  protected SignaturesSetValidator<PassarelaSignaturesSet> validator = new SignaturesSetValidator<PassarelaSignaturesSet>();

  @Override
  protected AbstractPluginLogicaLocal<ISignatureWebPlugin> getModulDeFirmaEJB() {
    return modulDeFirmaEjb;
  }

  @Override
  public String startTransaction(PassarelaSignaturesSet signaturesSet, String entitatID,
      boolean fullView, UsuariAplicacioJPA usuariAplicacio, PerfilDeFirma perfilDeFirma,
      Map<String, UsuariAplicacioConfiguracioJPA> configBySignID,
      Map<String,Long> tipusDocumentalBySignID,
      int origenPeticioDeFirma) throws I18NException,
      I18NValidationException {

    final String urlBase = PropietatGlobalUtil.getUrlBaseForSignatureModule(perfilDeFirma);

    // Validar
    SignaturesSetBeanValidator ssbv = new SignaturesSetBeanValidator(validator, this,
        entitatID);
    final boolean isNou = true;
    ssbv.throwValidationExceptionIfErrors(signaturesSet, isNou);

    final String signaturesSetID = signaturesSet.getSignaturesSetID();

    // Tiquet # 186
    if (PropietatGlobalUtil.isDisabledSignaturesTable()) {
      PassarelaFileInfoSignature[] files = signaturesSet.getFileInfoSignatureArray();
      for (PassarelaFileInfoSignature passarelaFileInfoSignature : files) {
        passarelaFileInfoSignature
            .setSignaturesTableLocation(ConstantsV2.TAULADEFIRMES_SENSETAULA); // = 0
        passarelaFileInfoSignature.setSignaturesTableHeader(null);
      }
    }

    // Canviar llista buida per NULL
    List<Long> filterPluginsByIDs = signaturesSet.getCommonInfoSignature()
        .getAcceptedPlugins();
    if (filterPluginsByIDs != null && filterPluginsByIDs.size() == 0) {
      signaturesSet.getCommonInfoSignature().setAcceptedPlugins(null);
    }
    
    Map<String, CustodiaInfoJPA> custodiaBySignID = new HashMap<String, CustodiaInfoJPA>();

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

      String nom; 
      if (origenPeticioDeFirma == ConstantsV2.ORIGEN_PETICIO_DE_FIRMA_API_FIRMA_SIMPLE_WEB_V1) {
        nom = "APIFirmaSimple_Custòdia"; 
      } else {
        nom = "PassarelaWeb_Custòdia";
      }
      
      EntitatJPA entitatJPA = entitatEjb.findByPrimaryKey(entitatID);
      CustodiaInfo custodiaInfo = custodiaInfoLogicaEjb.getCustodiaUA(usuariAplicacio, null,
          nom, entitatJPA);

      PassarelaSecureVerificationCodeStampInfo psvcs = null;
      Map<String, PeticioDeFirmaJPA> peticioDeFirmaBySignID = new HashMap<String, PeticioDeFirmaJPA>();

      if (custodiaInfo != null) {
        psvcs = getCustodiaOfUsuariAplicacio(custodiaInfo, usuariAplicacio, entitatJPA);
      }

      int count = 0;
      for (int i = 0; i < fileInfoSignatureArray.length; i++) {

        PassarelaFileInfoSignature pfis = fileInfoSignatureArray[i];

        final String signID = pfis.getSignID();
        File original = getFitxerOriginalPath(signaturesSetID, signID);

        // obtenir ruta on guardar fitxer adaptat
        File adaptat = getFitxerAdaptatPath(signaturesSetID, signID);
        originalNumberOfSignsArray[count] = processFileToSign(locale, entitatID, pfis,
            original, adaptat, usuariAplicacio);
        
        if (custodiaInfo != null) {
          
          pfis.setSecureVerificationCodeStampInfo(psvcs);

          String titol = nom + "_" + signaturesSet.getSignaturesSetID() + "_" + signID;

          CustodiaInfoJPA cust  = CustodiaInfoJPA.toJPA(custodiaInfo);
          
          cust.setCustodiaInfoID(0);
          cust.setTitolPeticio(titol);
          
          cust = (CustodiaInfoJPA)custodiaInfoLogicaEjb.create(cust);
          
          if (log.isDebugEnabled()) {
            log.debug("\n\nCreada custodia per SIGID[" + signID + "] ==> " + cust);
          }
          custodiaBySignID.put(signID, cust);
          
          
          UsuariAplicacioConfiguracioJPA config = configBySignID.get(signID);
          
          Long tipusDocumentID = tipusDocumentalBySignID.get(signID);
          
          if (tipusDocumentID == null) {
            tipusDocumentID = 99L; // == TD99
          }
          
          PeticioDeFirmaJPA peticioDeFirma = convertPassarelaFileInfoSignature2PeticioDeFirma(
              titol, origenPeticioDeFirma, tipusDocumentID,
              usuariAplicacio, pfis, cust, config, entitatJPA);
          peticioDeFirmaBySignID.put(signID, peticioDeFirma);

          // Peticio de Firma
          CustodiaForStartPeticioDeFirma custStartInfo =
                custodiaInfoLogicaEjb.custodiaCommonActionsOnStartPeticioDeFirma(peticioDeFirma, cust);

          switch(peticioDeFirma.getTipusFirmaID()) {
          
            case ConstantsV2.TIPUSFIRMA_PADES: 
              custodiaInfoLogicaEjb.custodiaPAdESActionsOnStartPeticioDeFirma(peticioDeFirma, cust, custStartInfo, locale);
              break;
            
            case ConstantsV2.TIPUSFIRMA_CADES:
            case ConstantsV2.TIPUSFIRMA_XADES:
              // No s'ha de fer res especial
               break; 
            
            default:
              // XYZ ZZZ TRA
              String msg =  "ID de Tipus de Firma desconegut: " + peticioDeFirma.getTipusFirmaID();
              log.error(msg, new Exception());
              throw new I18NException("genapp.comodi", msg);
          }
        }

        count++;
      }

      // Guardar
      storeSignaturesSet(new PassarelaSignaturesSetWebInternalUse(entitatID,
          originalNumberOfSignsArray, fullView, signaturesSet,
          usuariAplicacio.getUsuariAplicacioID(), urlBase, perfilDeFirma, 
          configBySignID, custodiaBySignID, peticioDeFirmaBySignID));

      bitacolaLogicaEjb.createBitacola(entitatID,
            signaturesSetID,
            ConstantsV2.BITACOLA_TIPUS_FIRMASINCRONA,
            ConstantsV2.BITACOLA_OP_INICIAR,
            signaturesSet);

    } catch (I18NException i18n) {

      // Esborrar Custòdies
      for (CustodiaInfoJPA ci : custodiaBySignID.values()) {
        try {
          custodiaInfoLogicaEjb.delete(ci);
        } catch (Exception e) {
          log.error("Error esborrant custòdia: " + e.getMessage(), e);
        }
      } 

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


  
  protected PeticioDeFirmaJPA convertPassarelaFileInfoSignature2PeticioDeFirma(
      String titol,int origenPeticioDeFirma,  long tipusDocumentID,
      UsuariAplicacioJPA usuariAplicacio, PassarelaFileInfoSignature pfis,     
      CustodiaInfo custodiaInfo, UsuariAplicacioConfiguracioJPA config,
      EntitatJPA entitatJPA) throws I18NException {
    
    // XYZ ZZZ ZZZ 
    
    long peticioDeFirmaID = 0;
    java.lang.String descripcio = titol;
    java.lang.String motiu = titol;
    java.lang.Long fitxerAFirmarID = null;
    java.lang.Long firmaOriginalDetachedID = null;
    java.lang.Long fitxerAdaptatID = null;

    java.lang.String descripcioTipusDocument = null;
    int posicioTaulaFirmesID =pfis.getSignaturesTableLocation();
    java.sql.Timestamp dataSolicitud = new Timestamp(System.currentTimeMillis());
    java.sql.Timestamp dataFinal = null;
    java.sql.Timestamp dataCaducitat = new Timestamp(System.currentTimeMillis() + 10*60*1000);
    int tipusOperacioFirma = pfis.getSignOperation();
    int tipusFirmaID = SignatureUtils.convertApiSignTypeToPortafibSignType(pfis.getSignType());
    
    int algorismeDeFirmaID;
    {
       Integer algo = config.getAlgorismeDeFirmaID();
       if (algo == null) {
         // Cercar Entitat
         algorismeDeFirmaID = entitatJPA.getAlgorismeDeFirmaID();
       } else {
         algorismeDeFirmaID = algo;
       }
    }
    Boolean modeDeFirma = SignatureUtils.convertApiSignMode2PortafibSignMode(pfis.getSignMode());
    int tipusEstatPeticioDeFirmaID = ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES;
    String motiuDeRebuig = null;
    String idiomaID = pfis.getLanguageSign();
    int prioritatID = 5;
    long fluxDeFirmesID = 0;
    String solicitantUsuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
    String remitentNom = null;
    String remitentDescripcio = null;
    
    String expedientCodi = pfis.getExpedientCodi();
    String expedientNom = pfis.getExpedientNom();
    String expedientUrl = pfis.getExpedientUrl();
    
    String procedimentCodi = pfis.getProcedimentCodi();
    String procedimentNom = pfis.getProcedimentNom();
    String informacioAddicional = null;
    Double informacioAddicionalAvaluable = null;
    // XYZ ZZZ ZZZ llegir-ho de URSAPP
    Long logoSegellID = null;
    Long custodiaInfoID = 666L;
    String solicitantUsuariEntitat1ID = null;
    String solicitantUsuariEntitat2ID = null;
    String solicitantUsuariEntitat3ID = null;
    boolean avisWeb = false;
    // XYZ ZZZ ZZZ
    boolean segellatDeTemps = false;
    
    long configuracioDeFirmaID = config.getUsuariAplicacioConfigID(); 
    
    PeticioDeFirmaJPA peticio = new PeticioDeFirmaJPA(peticioDeFirmaID, titol, descripcio, motiu, fitxerAFirmarID,
          firmaOriginalDetachedID, fitxerAdaptatID, tipusDocumentID, descripcioTipusDocument, dataSolicitud, dataFinal,
          dataCaducitat, tipusOperacioFirma, tipusFirmaID, algorismeDeFirmaID, modeDeFirma, posicioTaulaFirmesID,
          tipusEstatPeticioDeFirmaID, motiuDeRebuig, idiomaID, prioritatID, fluxDeFirmesID, solicitantUsuariAplicacioID,
          remitentNom, remitentDescripcio, expedientCodi, expedientNom, expedientUrl, procedimentCodi, procedimentNom,
          informacioAddicional, informacioAddicionalAvaluable, logoSegellID, custodiaInfoID, solicitantUsuariEntitat1ID,
          solicitantUsuariEntitat2ID, solicitantUsuariEntitat3ID, avisWeb, segellatDeTemps, origenPeticioDeFirma,
          configuracioDeFirmaID);
    
    return peticio;
  }
  
  
  public PassarelaSecureVerificationCodeStampInfo getCustodiaOfUsuariAplicacio(
      CustodiaInfo custodiaInfo,
      final UsuariAplicacioJPA usuariAplicacio,
      EntitatJPA entitatJPA) throws I18NException, I18NValidationException {
    final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;
    
    

    secureVerificationCodeStampInfo = new PassarelaSecureVerificationCodeStampInfo();

    secureVerificationCodeStampInfo.setBarCodePosition((int) custodiaInfo
        .getCodiBarresPosicioPaginaID());
    secureVerificationCodeStampInfo.setBarCodeText(custodiaInfo.getCodiBarresText());

    String codiBarresID = custodiaInfo.getCodiBarresID();

    String codiBarresNom = codiBarresEjb.executeQueryOne(CodiBarresFields.NOM,
        CodiBarresFields.CODIBARRESID.equal(codiBarresID));

    if (codiBarresNom == null) {
      // TODO Traduir XYZ ZZZ
      String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom " + codiBarresNom;
      throw new I18NException("error.unknown", msg);
    }

    secureVerificationCodeStampInfo.setBarCodeType(codiBarresNom);

    long messagePosition = custodiaInfo.getMissatgePosicioPaginaID();
    secureVerificationCodeStampInfo.setMessagePosition((int) messagePosition);
    secureVerificationCodeStampInfo.setMessage(custodiaInfo.getMissatge());
    secureVerificationCodeStampInfo.setPages(custodiaInfo.getPagines());
    
    
    /*
     * XYZ ZZZ #165 { // CustodiaInfoBean custodiaInfo = config.getCustodiaInfoID() int
     * politicaCustodia = usuariAplicacio.getPoliticaCustodia(); boolean obtenirDeEntitat =
     * false; if (politicaCustodia ==
     * ConstantsV2.POLITICA_CUSTODIA_POLITICA_DE_CUSTODIA_DEFINIDA_EN_ENTITAT) {
     * obtenirDeEntitat = true; politicaCustodia = entitatJPA.getPoliticaCustodia(); }
     * 
     * switch (politicaCustodia) {
     * 
     * case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE: secureVerificationCodeStampInfo = null;
     * break; case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT: // XYZ ZZZ Traduir
     * #165 throw new I18NException("genapp.comodi",
     * "Politica de Custodia no suportada per PortaFIB (Usuari aplicació " +
     * usuariAplicacio.getUsuariAplicacioID() + ")");
     * 
     * case
     * ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_ENTITAT_PER_DEFECTE_ACTIU:
     * case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO: long
     * custodiaInfoID = entitatJPA.getCustodiaInfoID(); if (obtenirDeEntitat) { custodiaInfoID
     * = entitatJPA.getCustodiaInfoID(); } else { custodiaInfoID =
     * usuariAplicacio.getCustodiaInfoID(); }
     * 
     * CustodiaInfo custodiaInfo = custodiaInfoEjb.findByPrimaryKey(custodiaInfoID);
     * 
     * secureVerificationCodeStampInfo = new PassarelaSecureVerificationCodeStampInfo();
     * 
     * secureVerificationCodeStampInfo.setBarCodePosition((int) custodiaInfo
     * .getCodiBarresPosicioPaginaID());
     * secureVerificationCodeStampInfo.setBarCodeText(custodiaInfo.getCodiBarresText());
     * 
     * String codiBarresID = custodiaInfo.getCodiBarresID();
     * 
     * String codiBarresNom = codiBarresEjb.executeQueryOne(CodiBarresFields.NOM,
     * CodiBarresFields.CODIBARRESID.equal(codiBarresID));
     * 
     * if (codiBarresNom == null) { // TODO Traduir XYZ ZZZ String msg =
     * "No s'ha trobat cap plugin de Codi de Barres amb nom " + codiBarresNom; throw new
     * I18NException("error.unknown", msg); }
     * 
     * secureVerificationCodeStampInfo.setBarCodeType(codiBarresNom);
     * 
     * long messagePosition = custodiaInfo.getMissatgePosicioPaginaID();
     * secureVerificationCodeStampInfo.setMessagePosition((int) messagePosition);
     * secureVerificationCodeStampInfo.setMessage(custodiaInfo.getMissatge());
     * secureVerificationCodeStampInfo.setPages(custodiaInfo.getPagines()); break;
     * 
     * case
     * ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_ENTITAT_PER_DEFECTE_NO_ACTIU:
     * secureVerificationCodeStampInfo = null; break;
     * 
     * case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL: throw new
     * I18NException("genapp.comodi", "Politica de Custodia no suportada per API FIRMA SIMPLE "
     * + "(Usuari aplicació " + usuariAplicacio.getUsuariAplicacioID() + ")");
     * 
     * default: // XYZ ZZZ Traduir throw new I18NException("genapp.comodi",
     * "Politica de Custòdia desconeguda (" + politicaCustodia + ") en usuari aplicació " +
     * usuariAplicacio.getUsuariAplicacioID()); }
     * 
     * }
     */

    return secureVerificationCodeStampInfo;
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

    Map<String, CustodiaInfoJPA> custodiaBysignID = ssf.getCustodiaBySignID();
    Map<String, PassarelaSignatureStatusWebInternalUse> map = ssf.getStatusBySignatureID();
    Set<String> signsID = map.keySet();
    List<PassarelaSignatureResult> list = new ArrayList<PassarelaSignatureResult>();
    for (String id : signsID) {
      PassarelaSignatureStatusWebInternalUse ss = map.get(id);

      PassarelaFileInfoSignature pfis = fileInfoSignMap.get(id);
      
      CustodiaInfoJPA cust = custodiaBysignID.get(id); 
      

      PassarelaSignatureResult psr = convertToPassarelaSignatureResult(addFiles, ss, pfis, cust);
      list.add(psr);
    }

    return list;

  }

  protected PassarelaSignatureResult convertToPassarelaSignatureResult(boolean addFiles,
      PassarelaSignatureStatusWebInternalUse ss, PassarelaFileInfoSignature pfis,
      CustodiaInfoJPA custInfo) {

    final String signID = pfis.getSignID();

    FitxerBean signedFile = null;
    if (addFiles) {
      if (ss.getFitxerFirmat() != null && ss.getFitxerFirmat().exists()) {
        DataSource fds = new javax.activation.FileDataSource(ss.getFitxerFirmat());

        signedFile = new FitxerBean();
        signedFile.setNom("signed_" + pfis.getFileToSign().getNom());
        signedFile.setMime(ConstantsV2.MIME_TYPE_PDF);
        signedFile.setTamany(ss.getFitxerFirmat().length());
        signedFile.setData(new DataHandler(fds));
        signedFile.setDescripcio("Signed Document");
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
    
    
    
    // Obtenir la informació de Custòdia
    PassarelaCustodyInfo pCustodyInfo = null;    
    if (custInfo != null) {

      final String custodyFileID = custInfo.getCustodiaDocumentID();
      final String custodyFileURL = custInfo.getUrlFitxerCustodiat();
      final String custodyFileCSV = custInfo.getCsv();
      final String custodyFileCSVValidationWeb = custInfo.getCsvValidationWeb();
      final String custodyFileCSVGenerationDefinition = custInfo.getCsvGenerationDefinition();
      final String custodyFileOriginalFileDirectURL = custInfo.getOriginalFileDirectUrl();
      final String custodyFilePrintableFileDirectUrl= custInfo.getPrintableFileDirectUrl();
      final String custodyFileEniFileDirectUrl = custInfo.getEniFileDirectUrl();

      pCustodyInfo = new PassarelaCustodyInfo(custodyFileID, custodyFileURL,
          custodyFileCSV, custodyFileCSVValidationWeb,
          custodyFileCSVGenerationDefinition, custodyFileOriginalFileDirectURL,
          custodyFilePrintableFileDirectUrl, custodyFileEniFileDirectUrl);
    }
    
    
    
    PassarelaSignatureResult psr = new PassarelaSignatureResult(signID, ss.getStatus(),
        ss.getErrorMessage(),
        ss.getErrorStackTrace(), signedFile, pCustodyInfo,
        pvi);

    return psr;
  }


  @Override
  public PassarelaSignatureResult getSignatureResult(String transactionID, String signID)  throws I18NException {
    PassarelaSignaturesSetWebInternalUse ssf = readSignaturesSet(transactionID);
    if (ssf == null) {
      return null;
    }

    PassarelaSignatureStatusWebInternalUse ss = ssf.getStatusBySignatureID().get(signID);

    for (PassarelaFileInfoSignature pfis : ssf.getSignaturesSet().getFileInfoSignatureArray()) {
      if (pfis.getSignID().equals(signID)) {
       
        return convertToPassarelaSignatureResult(true, ss, pfis, ssf.getCustodiaBySignID().get(signID));
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
      SignaturesSetWeb ss) throws I18NException {

    log.info(" XYZ ZZZ ZZZ \n\n finalProcesDeFirma => ENTRA\n\n");

    StatusSignaturesSet sss = ss.getStatusSignaturesSet();

    PassarelaSignaturesSetWebInternalUse ssf;
    ssf = getSignaturesSetFullByTransactionID(transactionID);
    if (ssf == null) {
      // "Ha tardat massa temps en firmar. Torni a intentar-ho."
      throw new I18NException("firmar.tempsexcedit");
    }
    
    final String languageUI = ssf.getSignaturesSet().getCommonInfoSignature().getLanguageUI();

    Map<String, PassarelaSignatureStatusWebInternalUse> statusBySignID = ssf
        .getStatusBySignatureID();

    switch (sss.getStatus()) {

      case StatusSignaturesSet.STATUS_FINAL_OK: {
        // Revisam les firma

        final UsuariAplicacio usuariAplicacio;
        usuariAplicacio = usuariAplicacioLogicaEjb.findByPrimaryKey(ssf.getApplicationID());

        final String entitatID = usuariAplicacio.getEntitatID();

        FileInfoSignature[] fileInfoSignatureArray = ss.getFileInfoSignatureArray();

        PassarelaFileInfoSignature[] passarelafileInfoSignatureArray = ssf.getSignaturesSet()
            .getFileInfoSignatureArray();

        Map<String, UsuariAplicacioConfiguracioJPA> configBySignID = ssf.getConfigBySignID();
        Map<String, CustodiaInfoJPA> custodiaBySignID = ssf.getCustodiaBySignID();
        Map<String, PeticioDeFirmaJPA> peticioFirmaBySignID = ssf.getPeticioFirmaBySignID();

        for (int p = 0; p < fileInfoSignatureArray.length; p++) {

          FileInfoSignature fis = fileInfoSignatureArray[p];
          // TODO check null
          StatusSignature status = fis.getStatusSignature();
          final String signID = fis.getSignID();

          if (status.getStatus() == StatusSignature.STATUS_FINAL_OK) {
            PassarelaSignatureStatusWebInternalUse pss = statusBySignID.get(signID);
            // Check que status.getSignedData() != null
            if (status.getSignedData() == null || !status.getSignedData().exists()) {
              status.setStatus(StatusSignature.STATUS_FINAL_ERROR);
              // XYZ ZZZ TRA TODO traduir
              String msg = "L'estat indica que ha finalitzat correctament però en la signatura amb ID "
                  + signID
                  + "("
                  + fis.getName()
                  + ")"
                  + ", el fitxer firmat o no s'ha definit o no existeix";
              status.setErrorMsg(msg);
              // statusFinal = status;

              // Copiar estat
              pss.setErrorMessage(msg);
              pss.setStatus(StatusSignature.STATUS_FINAL_ERROR);
              pss.setErrorStackTrace(null);

              

            } else {

              CustodiaInfoJPA custInfo = custodiaBySignID.get(signID);
              try {
                // Copiar fitxers de Plugin a Ruta PortaFIB-Passarela
                File firmat = getFitxerFirmatPath(transactionID, signID);
                try {
                  FileUtils.moveFile(status.getSignedData(), firmat);
                } catch (IOException e) {
                  // XYZ ZZZ TRA
                  String msg = "Error movent fitxer de " + status.getSignedData() + " a "
                      + firmat.getAbsolutePath() + ": " + e.getMessage();
                  log.error(msg, e);
                  throw new I18NException("genapp.comodi", msg);
                  
                }
  
                // Validar certificat i firmes, i comprovar que els NIFs corresponen
                PassarelaFileInfoSignature pfis = passarelafileInfoSignatureArray[p];
                final int numFirmesOriginals = ssf.getOriginalNumberOfSignsArray()[p];
                ValidacioCompletaResponse validacioResponse = validateSignature(ssf, firmat,
                  entitatID, languageUI, configBySignID.get(signID), pfis, numFirmesOriginals);
                pss.setInfoValidacio(validacioResponse);
               
  
                // Custodia Documental
                log.info("\n\n finalProcesDeFirma.CUSTODIA => " + custInfo);
                if (custInfo != null) {
                  final FirmaJPA firma = null;
                  String fitxerAFirmarNom = ssf.signaturesSet.getSignaturesSetID() + "_"
                      + signID;
    
                  PeticioDeFirmaJPA peticioDeFirma = peticioFirmaBySignID.get(signID);
    
                  IPortaFIBDataSource originalFile = new FileDataSource(getFitxerAdaptatPath(transactionID, signID));
    
                  custodiaInfoLogicaEjb.custodiaThingToDoOnFinishPeticioDeFirma(
                      fitxerAFirmarNom, originalFile, firmat, peticioDeFirma, firma, custInfo);
                }
    
                // Copiar estat
                pss.setErrorMessage(status.getErrorMsg());
                pss.setStatus(status.getStatus());
                pss.setFitxerFirmat(firmat);
              
              } catch (I18NException i18n) {
                pss.setStatus(StatusSignaturesSet.STATUS_FINAL_ERROR);
                pss.setErrorMessage(I18NLogicUtils.getMessage(i18n, new Locale(languageUI)));

                // Esborrar fitxer firmat si existeix
                if (pss.getFitxerFirmat() != null) {
                  if (!pss.getFitxerFirmat().delete()) {
                    pss.getFitxerFirmat().deleteOnExit();
                  }
                }
                pss.setFitxerFirmat(null);
                
                // Esborrar Custòdia si existeix
                if (custInfo != null) {
                  // BBDD
                  custodiaInfoLogicaEjb.delete(custInfo);
                  // Map de custòdies per signID
                  custodiaBySignID.remove(signID);
                }
              }
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
    int signaturesValides = 0;
    if (ssf.getStatus() == StatusSignaturesSet.STATUS_FINAL_OK) {
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

    // Bitacola
    int tipusoperacio = ssf.getStatus() == StatusSignaturesSet.STATUS_FINAL_OK
          ? ConstantsV2.BITACOLA_OP_FINALITZAR
          : ConstantsV2.BITACOLA_OP_REBUTJAR;
    String descripcio;
    if (ssf.getStatus() == StatusSignaturesSet.STATUS_FINAL_OK) {
      if (signaturesValides < ss.getFileInfoSignatureArray().length) {
        descripcio = "Signatures realitzades correctament: " + signaturesValides + " de " + ss.getFileInfoSignatureArray().length;
      } else {
        descripcio = "Totes les signatures realitzades correctament: " + signaturesValides;
      }
    } else {
      descripcio = ssf.getErrorMessage();
    }
    PassarelaSignatureStatusList statusList = new PassarelaSignatureStatusList();
    statusList.getPassarelaSignatureStatus().addAll(ssf.getStatusBySignatureID().values());

    bitacolaLogicaEjb.createBitacola(
          ssf.getEntitatID(),
          transactionID,
          ConstantsV2.BITACOLA_TIPUS_FIRMASINCRONA,
          tipusoperacio,
          descripcio,
          statusList);

    return ssf;
  }


  /**
   * 
   * Validar certificat i firmes, i comprovar que els NIFs corresponen
   * 
   * @param ssf
   */
  /** XYZ ZZZ ZZZ 
  protected void validateSignatures2(PassarelaSignaturesSetWebInternalUse ssf,
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

      validateSignature(ssf, isDebug, statusBySignID, languageUI, entitatID, configBySignID,
          p, fis);

    }
  }
   * @throws I18NException 
  */

  protected ValidacioCompletaResponse validateSignature(PassarelaSignaturesSetWebInternalUse ssf,
      File fitxerFirmat, final String entitatID, final String languageUI,
       UsuariAplicacioConfiguracioJPA configuracio , 
      PassarelaFileInfoSignature fis, final int numFirmesOriginals) throws I18NException {

    // TODO check null
    final String signID = fis.getSignID();

    

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

          final boolean isDebug = log.isDebugEnabled();
          if (isDebug) {
            log.info("fis.getFileToSign() => " + fis.getFileToSign());

            log.info("fis.getFileToSign().getData() => " + fis.getFileToSign().getData());
            log.info("fis.getFileToSign().getNom() => " + fis.getFileToSign().getNom());

            log.info("fis.getFileToSign().getFitxerID() => "
                + fis.getFileToSign().getFitxerID());
          }

          // XYZ ZZZ ZZZ  Crear un DatahandlerDataSource i no convertir-ho a ByteArrayDataSource
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
          fitxerFirmat);

      final int signTypeID = SignatureUtils.convertApiSignTypeToPortafibSignType(fis
          .getSignType());

      final boolean signMode = SignatureUtils.convertApiSignMode2PortafibSignMode(fis
          .getSignMode());

      

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

      return validacioResponse;
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
  
  @Override
  public Map<String, PassarelaSignaturesSetWebInternalUse> getAllTransactionsByEntitatID(
      String entitatID) throws I18NException {
    
    checkExpiredSignaturesSet();

    Map<String, PassarelaSignaturesSetWebInternalUse> map = new HashMap<String, PassarelaSignaturesSetWebInternalUse>();

    synchronized (passarelaSignaturesSets) {

      for (Map.Entry<String, PassarelaSignaturesSetWebInternalUse> entry : passarelaSignaturesSets
          .entrySet()) {
        if (entry.getValue().getEntitatID().equals(entitatID)) {
          map.put(entry.getKey(), entry.getValue());
        }
      }
    }

    return map;

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

    synchronized (passarelaSignaturesSets) {
      passarelaSignaturesSets.remove(signaturesSetID);
    }
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
        for (PassarelaSignaturesSetWebInternalUse pss : keysToDelete) {
          deleteSignaturesSet(pss);

          bitacolaLogicaEjb.createBitacola(
                pss.getEntitatID(),
                pss.getSignaturesSet().getSignaturesSetID(),
                ConstantsV2.BITACOLA_TIPUS_FIRMASINCRONA,
                ConstantsV2.BITACOLA_OP_ESBORRAR,
                "Petició caducada");
        }
      }
    }
  }

}
