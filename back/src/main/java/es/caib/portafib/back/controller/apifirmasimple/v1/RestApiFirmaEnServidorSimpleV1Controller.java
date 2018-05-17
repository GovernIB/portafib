package es.caib.portafib.back.controller.apifirmasimple.v1;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleKeyValue;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsRequest;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignDocumentsResponse;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.ISignaturePlugin;
import org.fundaciobit.plugins.signature.api.ITimeStampGenerator;
import org.fundaciobit.plugins.signature.api.PdfVisibleSignature;
import org.fundaciobit.plugins.signature.api.SecureVerificationCodeStampInfo;
import org.fundaciobit.plugins.signature.api.SignaturesTableHeader;
import org.fundaciobit.plugins.signatureserver.api.AbstractSignatureServerPlugin;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.passarela.NoCompatibleSignaturePluginException;
import es.caib.portafib.logic.passarela.PassarelaKeyValue;
import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFullResults;
import es.caib.portafib.logic.passarela.api.PassarelaPolicyInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaSecureVerificationCodeStampInfo;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureResult;
import es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesTableHeader;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.utils.ConstantsPortaFIB;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created 06/02/18 10:10
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = RestApiFirmaEnServidorSimpleV1Controller.CONTEXT)
public class RestApiFirmaEnServidorSimpleV1Controller extends RestApiFirmaUtils {

  public static final String CONTEXT = "/common/rest/apifirmaenservidorsimple/v1";

  @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
  protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

  @EJB(mappedName = es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal.JNDI_NAME)
  protected es.caib.portafib.logic.passarela.PassarelaDeFirmaEnServidorLocal passarelaDeFirmaEnServidorEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  // @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal.JNDI_NAME)
  // protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal
  // usuariAplicacioConfiguracioEjb;

  @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
  protected ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.GETMAXNUMBEROFSIGNATURESBYTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getMaxNumberOfSignaturesByTransaction(HttpServletRequest request) {

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    String languageUI = "ca";

    try {

      LoginInfo loginInfo = LoginInfo.getInstance();

      // Checks usuari aplicacio
      final UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();

      languageUI = usuariAplicacio.getIdiomaID();

      final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

      // Cercam que tengui configuracio
      final UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacio(usuariAplicacioID);

      Integer max = config.getMaxFirmesEnServidor();

      log.info(" XYZ ZZZ getMaxNumberOfSignaturesByTransaction() => " + max);
      String strValue = (max == null) ? "" : String.valueOf(max);
      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(strValue, headers, HttpStatus.OK);

      return re;

    } catch (I18NException i18ne) {
      // XYZ ZZZ String idioma = simpleSignaturesSet.getCommonInfo().getLanguageUI();

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      String msg = "Error desconegut intentant obtenir el numero màxim "
          + "de firmes per transacció de Firma en Servidor: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }



  @RequestMapping(value = "/" + ApiFirmaEnServidorSimple.SIGNDOCUMENTS, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> signDocuments(HttpServletRequest request,
      @RequestBody FirmaSimpleSignDocumentsRequest simpleSignaturesSet) {
    
    log.info(" XYZ ZZZ eNTRA A signDocuments => simpleSignaturesSet: " + simpleSignaturesSet);

    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }
    

    final String type = "SERVER";
    final String virtualTransactionID = internalGetTransacction();
    String languageUI = "ca";
    try {

      // / XYZ ZZZ
      final boolean esFirmaEnServidor = true;

      LoginInfo loginInfo = LoginInfo.getInstance();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        // TODO XYZ ZZZ Traduir
        return generateServerError("Aquest servei només el poden fer servir els usuari-aplicació");
      }

      // Checks usuari aplicacio
      final UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
      final String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

      // Cercam que tengui configuracio
      final UsuariAplicacioConfiguracio config;
      config = configuracioUsuariAplicacioLogicaLocalEjb
          .getConfiguracioUsuariAplicacio(usuariAplicacioID);

      if (esFirmaEnServidor) {
        Long pluginId = config.getPluginFirmaServidorID();
        if (pluginId == null) {
          // XYZ ZZZ Traduir
          return generateServerError("No es permeten firmes en servidor a través de l'usuari aplicació "
              + usuariAplicacioID + "(Consulti amb l'administrador de PortaFIB)");
        }
      }
      
      
      

      // ================== CODI COMU ==============

      // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet
      if (simpleSignaturesSet == null) {
        // Traduir
        return generateServerError("FirmaSimpleSignDocumentsRequest val null");
      }
      
      
      FirmaSimpleCommonInfo commonInfo = simpleSignaturesSet.getCommonInfo();
      if (commonInfo == null) {
        return generateServerError("L'atribut commonInfo val null");
      }

      languageUI = commonInfo.getLanguageUI();
      log.info(" XYZ ZZZ LanguageUI() => " + languageUI);
      if (languageUI == null || languageUI.trim().length() == 0) {
        return generateServerError("El camp languageUI de l'atribut commonInfo val null o està buit");
      }

      // TODO XYZ FALTA CHECK
      FirmaSimpleFileInfoSignature[] fitxersAFirmar = simpleSignaturesSet
          .getFileInfoSignatureArray();
      
      if (fitxersAFirmar == null || fitxersAFirmar.length == 0) {
        // XYZ ZZZ 
        return generateServerError("No ha enviat fitxers a firmar.");
      }

      if (esFirmaEnServidor) {
        Integer max = config.getMaxFirmesEnServidor();;
        if (max != null) {
          if (fitxersAFirmar.length > max) {
            // XYZ ZZZ Traduir !!!!
            return generateServerError("El màxim de fitxers a firmar en una transacció és de "
                + max);
          }
        }
      }


      EntitatJPA entitatJPA = loginInfo.getEntitat();

      final String entitatID = entitatJPA.getEntitatID();

      // Vull suposar que abans de 10 minuts haurà firmat
      // Proporcional al numero de firmes !!!!
      Calendar expiryDate = Calendar.getInstance();
      expiryDate.add(Calendar.MINUTE, 5 + fitxersAFirmar.length);

      // ========== FILTRE DE CERTIFICATS
      // Cercar-ho a info de l'usuari-app.Si val null o buit cercar-ho de les
      // DADES DE l'ENTITAT
      String filtreCertificats = config.getFiltreCertificats();
      if (filtreCertificats == null || filtreCertificats.trim().length() == 0) {
        filtreCertificats = entitatJPA.getFiltreCertificats();
      }

      // ========== POLITICA DE FIRMA
      // Cercar l'ús de la politica de firma i actuar al respecte
      final PassarelaPolicyInfoSignature policyInfoSignature;
      {
        int usPoliticaDeFirma = config.getUsPoliticaDeFirma();
        boolean obtenirDeEntitat = false;
        if (usPoliticaDeFirma == ConstantsPortaFIB.US_POLITICA_DE_FIRMA_DEFINIT_EN_ENTITAT) {
          usPoliticaDeFirma = entitatJPA.getUsPoliticaDeFirma();
          obtenirDeEntitat = true;
        }

        switch (usPoliticaDeFirma) {

        // 0 => no usar politica de firma,
        case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_NO_USAR:
          policyInfoSignature = null;
          break;

        // 1=> usar politica d'aquesta configuracio
        case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OBLIGATORI_DEFINIT:
          if (obtenirDeEntitat) {
            policyInfoSignature = new PassarelaPolicyInfoSignature(
                entitatJPA.getPolicyIdentifier(), entitatJPA.getPolicyIdentifierHash(),
                entitatJPA.getPolicyIdentifierHashAlgorithm(),
                entitatJPA.getPolicyUrlDocument());
          } else {
            policyInfoSignature = new PassarelaPolicyInfoSignature(
                config.getPolicyIdentifier(), config.getPolicyIdentifierHash(),
                config.getPolicyIdentifierHashAlgorithm(), config.getPolicyUrlDocument());
          }
          break;

        // 2 => L'usuari web o usuari-app elegeixen la politica de firma
        case ConstantsPortaFIB.US_POLITICA_DE_FIRMA_OPCIONAL:
          // XYZ ZZZ Traduir
          return generateServerError("Ús de Politica de Firma no suportada en API Firma Simple ("
              + usPoliticaDeFirma + ") en usuari aplicació " + usuariAplicacioID);

        default:
          // XYZ ZZZ Traduir
          return generateServerError("Ús de Politica de Firma desconeguda ("
              + usPoliticaDeFirma + ") en usuari aplicació " + usuariAplicacioID);

        }
      }
      if (policyInfoSignature == null) {
        log.info("No usam politica de firma");
      } else {
        log.info("Usam politica de firma: " + policyInfoSignature.getPolicyIdentifier() + "("
            + policyInfoSignature.getPolicyUrlDocument() + ")");
      }

      final String username = commonInfo.getUsername();
      final String administrationID = commonInfo.getAdministrationID();
      PassarelaCommonInfoSignature commonInfoSignature = new PassarelaCommonInfoSignature(
          languageUI, filtreCertificats, username, administrationID, null, policyInfoSignature);

      FirmaSimpleFileInfoSignature[] simpleFileInfoSignatureArray;
      simpleFileInfoSignatureArray = simpleSignaturesSet.getFileInfoSignatureArray();

      PassarelaFileInfoSignature[] fileInfoSignatureArray;
      fileInfoSignatureArray = new PassarelaFileInfoSignature[simpleFileInfoSignatureArray.length];

      // final FileInfoSignature[] aFirmar = new
      // FileInfoSignature[simpleFileInfoSignatureArray.length];

      for (int i = 0; i < simpleFileInfoSignatureArray.length; i++) {

        FirmaSimpleFileInfoSignature sfis = simpleFileInfoSignatureArray[i];

        String signID = sfis.getSignID();

        FitxerBean fileToSign = convertFirmaSimpleFileToFitxerBean(sfis.getFileToSign(), type,
            virtualTransactionID, signID);

        // XYZ ZZZ FALTA ENCARA NO SUPORTAT
        FitxerBean prevSign = null;
        if (sfis.getPreviusSignatureDetachedFile() != null) {
          prevSign = convertFirmaSimpleFileToFitxerBean(
              sfis.getPreviusSignatureDetachedFile(), type, virtualTransactionID, signID);
        }

        String name = sfis.getName();
        String reason = sfis.getReason();
        String location = sfis.getLocation();
        String signerEmail = sfis.getSignerEmail();
        int signNumber = sfis.getSignNumber();
        String languageSign = sfis.getLanguageSign();

        final String expedientCodi = sfis.getExpedientCodi();

        final String expedientNom = sfis.getExpedientNom();

        final String expedientUrl = sfis.getExpedientUrl();

        final String procedimentCodi = sfis.getProcedimentCodi();

        final String procedimentNom = sfis.getProcedimentNom();

        final List<PassarelaKeyValue> additionalInformation;
        {
          List<FirmaSimpleKeyValue> additionalInfoList = sfis.getAdditionalInformation();
          if (additionalInfoList == null || additionalInfoList.size() == 0) {
            additionalInformation = null;
          } else {
            additionalInformation = new ArrayList<PassarelaKeyValue>();
            for (FirmaSimpleKeyValue firmaSimpleKeyValue : additionalInfoList) {
              additionalInformation.add(new PassarelaKeyValue(firmaSimpleKeyValue.getKey(),
                  firmaSimpleKeyValue.getValue()));
            }
          }
        }

        // ============ FIRMA

        // Operacio de Firma (FIRMA,COFIRMA,CONTRAFIRMA)
        final int signOperation = config.getTipusOperacioFirma();

        // TIPUS DE FIRMA
        final String signType = SignatureUtils.portafibSignTypeToApiSignType(config
            .getTipusFirmaID());
        Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
        if (signAlgorithmID == null) {
          // Si val null cercar-ho a les DADES DE l'ENTITAT
          signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
        }

        log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithmID] = " + signAlgorithmID);
        
        // ALGORISME DE FIRMA
        String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
        log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithm] = " + signAlgorithm);

        // Mode de Firma
        final int signMode;
        if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
          // SI és una pADES llavors val implicit
          signMode = FileInfoSignature.SIGN_MODE_IMPLICIT;
        } else {
          signMode = SignatureUtils.portafibModeSign2ApiModeSign(config.isModeDeFirma());
        }

        // TAULA DE FIRMES
        final int signaturesTableLocation; // =
                                           // FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        if (config.getTipusFirmaID() == ConstantsV2.TIPUSFIRMA_PADES) {
          int politicaTaulaDeFirmes = config.getPoliticaTaulaFirmes();
          boolean obtenirDeEntitat = false;
          if (politicaTaulaDeFirmes == ConstantsPortaFIB.POLITICA_TAULA_FIRMES_DEFINIT_EN_ENTITAT) {
            politicaTaulaDeFirmes = entitatJPA.getPoliticaTaulaFirmes();
            obtenirDeEntitat = true;
          }

          switch (politicaTaulaDeFirmes) {
          // 0 no es permet taules de firmes

          case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_NO_ES_PERMET:
            signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
            break;

          // 1 obligatori politica definida en la configuració d'usuari aplicació o entitat
          case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OBLIGATORI_DEFINIT:
            if (obtenirDeEntitat) {
              signaturesTableLocation = entitatJPA.getPosicioTaulaFirmes();
            } else {
              signaturesTableLocation = config.getPosicioTaulaFirmesID();
            }
            break;

          // 2 opcional, per defecte el definit a l'entitat o conf. de usuari aplicacio
          case ConstantsPortaFIB.POLITICA_TAULA_FIRMES_OPCIONAL_PER_DEFECTE_DEFINIT_EN_CONF:
            // XYZ ZZZ Que faig: sense taula de firmes o llançar una excepció indicant
            // que aquest valor no es vàlid per API Firma Simple ??
            signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
            break;

          default:
            // XYZ ZZZ Traduir
            return generateServerError("Politica de Taules de Firmes desconeguda ("
                + politicaTaulaDeFirmes + ") en usuari aplicació " + usuariAplicacioID);
          }

        } else {
          // XADES, CADES, ...
          signaturesTableLocation = FileInfoSignature.SIGNATURESTABLELOCATION_WITHOUT;
        }

        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. #
        // PENDENT: Configuració etiquetes de la Taula de Firmes #176
        // Camp config.getPropietatsTaulaFirmes()
        PassarelaSignaturesTableHeader signaturesTableHeader = null;

        // TODO XYZ ZZZ Cercar-ho a info de l'usuari-app. Ara cercar-ho de les
        // DADES DE l'ENTITAT
        final boolean useTimeStamp;
        {
          int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

          boolean obtenirDeEntitat = false;

          if (politicaSegellatDeTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT) {
            obtenirDeEntitat = true;
            politicaSegellatDeTemps = entitatJPA.getPoliticaSegellatDeTemps();
          }

          switch (politicaSegellatDeTemps) {
          case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
            useTimeStamp = false;
            break;

          case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
            useTimeStamp = true;
            break;

          case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
            useTimeStamp = true;
            break;
          case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
            useTimeStamp = false;
            break;

          default:
            // XYZ ZZZ Traduir
            return generateServerError("Politica de segellat de temps desconeguda ("
                + politicaSegellatDeTemps + ") en usuari aplicació " + usuariAplicacioID);
          }
        }

        // TODO #165 De la configuracio de usr-app s'ha obtenir un
        // "CustodiaInfoBean custodiaInfo" i convertir-lo a
        // secureVerificationCodeStampInfo
        final PassarelaSecureVerificationCodeStampInfo secureVerificationCodeStampInfo;
        {
          // CustodiaInfoBean custodiaInfo = config.getCustodiaInfoID()
          int politicaCustodia = config.getPoliticaCustodia();
          boolean obtenirDeEntitat = false;
          if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_EL_DEFINIT_EN_ENTITAT) {
            obtenirDeEntitat = true;
            politicaCustodia = entitatJPA.getPoliticaCustodia();
          }

          switch (politicaCustodia) {

          case ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE:
            secureVerificationCodeStampInfo = null;
            break;
          case ConstantsV2.POLITICA_CUSTODIA_NOMES_PLANTILLES_ENTITAT:
            // XYZ ZZZ Traduir #165
            return generateServerError("Politica de Custodia no suportada per PortaFIB (Usuari aplicació "
                + usuariAplicacioID + ")");

          case ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_ACTIU:
          case ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA:
            long custodiaInfoID = entitatJPA.getCustodiaInfoID();
            if (obtenirDeEntitat) {
              custodiaInfoID = entitatJPA.getCustodiaInfoID();
            } else {
              custodiaInfoID = config.getCustodiaInfoID();
            }

            CustodiaInfo custodiaInfo = custodiaInfoEjb.findByPrimaryKey(custodiaInfoID);

            secureVerificationCodeStampInfo = new PassarelaSecureVerificationCodeStampInfo();

            secureVerificationCodeStampInfo.setBarCodePosition((int) custodiaInfo
                .getCodiBarresPosicioPaginaID());
            secureVerificationCodeStampInfo.setBarCodeText(custodiaInfo.getCodiBarresText());

            String codiBarresID = custodiaInfo.getCodiBarresID();

            String codiBarresNom = codiBarresEjb.executeQueryOne(CodiBarresFields.NOM,
                CodiBarresFields.CODIBARRESID.equal(codiBarresID));

            if (codiBarresNom == null) {
              // TODO Traduir XYZ ZZZ
              String msg = "No s'ha trobat cap plugin de Codi de Barres amb nom "
                  + codiBarresNom;
              throw new I18NException("error.unknown", msg);
            }

            secureVerificationCodeStampInfo.setBarCodeType(codiBarresNom);

            long messagePosition = custodiaInfo.getMissatgePosicioPaginaID();
            secureVerificationCodeStampInfo.setMessagePosition((int) messagePosition);
            secureVerificationCodeStampInfo.setMessage(custodiaInfo.getMissatge());
            secureVerificationCodeStampInfo.setPages(custodiaInfo.getPagines());
            break;

          case ConstantsV2.POLITICA_CUSTODIA_OPCIONAL_PLANTILLA_DEFINIDA_DEFECTE_NO_ACTIU:
            secureVerificationCodeStampInfo = null;
            break;

          case ConstantsV2.POLITICA_CUSTODIA_LLIBERTAT_TOTAL:
            return generateServerError("Politica de Custodia no suportada per API FIRMA SIMPLE "
                + "(Usuari aplicació " + usuariAplicacioID + ")");

          default:
            // XYZ ZZZ Traduir
            return generateServerError("Politica de Custòdia desconeguda (" + politicaCustodia
                + ") en usuari aplicació " + usuariAplicacioID);

          }
          ;

        }

        fileInfoSignatureArray[i] = new PassarelaFileInfoSignature(fileToSign, prevSign,
            signID, name, reason, location, signerEmail, signNumber, languageSign,
            signOperation, signType, signAlgorithm, signMode, signaturesTableLocation,
            signaturesTableHeader, secureVerificationCodeStampInfo, useTimeStamp,
            expedientCodi, expedientNom, expedientUrl, procedimentCodi, procedimentNom,
            additionalInformation);
        /*
         * 
         * File fileToSign2 = null; String mimeType = fileToSign.getMime();
         * 
         * SignaturesTableHeader signaturesTableHeader2 = null; PdfVisibleSignature
         * pdfVisibleSignature = null; SecureVerificationCodeStampInfo
         * secureVerificationCodeStampInfo2 = null; ITimeStampGenerator timeStampGenerator =
         * null;
         * 
         * aFirmar[i] = new FileInfoSignature(signID, fileToSign2, mimeType, name, reason,
         * location, signerEmail, signNumber, languageSign, signType, signAlgorithm, signMode,
         * signaturesTableLocation, signaturesTableHeader2, pdfVisibleSignature,
         * secureVerificationCodeStampInfo2, useTimeStamp, timeStampGenerator);
         */
      } // FINAL FOR DE TOTS

      String transactionID = "" + System.currentTimeMillis();

      PassarelaSignaturesSet pss = new PassarelaSignaturesSet(transactionID,
          expiryDate.getTime(), commonInfoSignature, fileInfoSignatureArray);

      // FALTA PASSAR FILTRE
      /*
       * { ISignaturePlugin plugin = new VirtualSignaturePlugin(entitatID); final boolean
       * suportXAdES_T = false; boolean filter =
       * AbstractSignatureServerPlugin.checkFilter(plugin, aFirmar, suportXAdES_T, log);
       * log.info("XYZ ZZZ PASSA FILTRE " + filter); if (!filter) {
       * log.info("XYZ ZZZ Cridant a generateNoAvailablePlugin !!!!!"); return
       * generateNoAvailablePlugin(languageUI); } }
       */

      // CRIDAR A SIGNDOCUMENT
      //LoginInfo li = LoginInfo.getInstance();
      
      log.info("XYZ ZZZ  ======>   USERNAME = ]" + pss.getCommonInfoSignature().getUsername() + "[");

      PassarelaFullResults fullResults = passarelaDeFirmaEnServidorEjb.signDocuments(pss,
          loginInfo.getEntitat(), loginInfo.getUsuariAplicacio(), config);

      PassarelaSignatureStatus passarelaSS = fullResults.getSignaturesSetStatus();

      FirmaSimpleStatus statusSignatureProcess = new FirmaSimpleStatus(
          passarelaSS.getStatus(), passarelaSS.getErrorMessage(),
          passarelaSS.getErrorStackTrace());

      List<PassarelaSignatureResult> passarelaSR = fullResults.getSignResults();

      List<FirmaSimpleSignatureResult> results = new ArrayList<FirmaSimpleSignatureResult>();

      for (PassarelaSignatureResult psr : passarelaSR) {

        // TODO XYZ ZZZ #165 Si s'ha definit una CUSTODIA llavors s'ha de guardar el document
        // al SISTEMA DE CUSTODIA I retornar informacio al respecte
        java.lang.String custodyFileID = psr.getCustodyFileID();
        java.lang.String custodyFileURL = psr.getCustodyFileURL();

        results.add(new FirmaSimpleSignatureResult(psr.getSignID(), new FirmaSimpleStatus(psr
            .getStatus(), psr.getErrorMessage(), psr.getErrorStackTrace()),
            convertFitxerBeanToFirmaSimpleFile(psr.getSignedFile()), custodyFileID,
            custodyFileURL));
      }

      FirmaSimpleSignDocumentsResponse fssfr;
      fssfr = new FirmaSimpleSignDocumentsResponse(statusSignatureProcess, results);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FirmaSimpleSignDocumentsResponse>(fssfr,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ Surt de signDocuments => FINAL OK");

      return re;
    } catch (NoCompatibleSignaturePluginException nape) {

      return generateNoAvailablePlugin(languageUI);

    } catch (I18NException i18ne) {
      // XYZ ZZZ String idioma = simpleSignaturesSet.getCommonInfo().getLanguageUI();

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      String msg = "Error desconegut iniciant el proces de Firma: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    } finally {
      if (virtualTransactionID != null) {
        try {
          File transactionFolder = getTransactionFolder(type, virtualTransactionID);
          FileUtils.deleteDirectory(transactionFolder);
        } catch (Exception e) {
          log.error("Error desconegut fent neteja dels fitxers "
              + "de ApiFirmaEnServidorSimple de la transacció " + virtualTransactionID + ":"
              + e.getMessage(), e);
        }
      }

    }

  }

  /**
   * XYZ ZZZ Eliminar
   * 
   * @author anadal
   *
   */
//  public class VirtualSignaturePlugin implements ISignaturePlugin {
//
//    protected String entitatID;
//
//    protected List<Long> filterByPluginIDList;
//
//    /**
//     * @param entitatID
//     */
//    public VirtualSignaturePlugin(String entitatID) {
//      super();
//      this.entitatID = entitatID;
//    }
//
//    @Override
//    public String getName(Locale locale) {
//      return "VirtualSignaturePlugin";
//    }
//
//    public List<Long> getFilterByPluginIDList() {
//      return this.filterByPluginIDList;
//    }
//
//    @Override
//    public String[] getSupportedSignatureTypes() {
//      return passarelaDeFirmaEnServidorEjb.getSupportedSignatureTypes(entitatID,
//          getFilterByPluginIDList(), null);
//    }
//
//    @Override
//    public String[] getSupportedSignatureAlgorithms(String signType) {
//      return passarelaDeFirmaEnServidorEjb.getSupportedSignatureAlgorithms(signType,
//          entitatID, getFilterByPluginIDList(), null);
//    }
//
//    @Override
//    public List<String> getSupportedBarCodeTypes() {
//      try {
//        return passarelaDeFirmaEnServidorEjb.getSupportedBarCodeTypes();
//      } catch (I18NException e) {
//        log.error(
//            " Error cridant a passarelaDeFirmaWebEjb.getSupportedBarCodeTypes(): "
//                + e.getMessage(), e);
//        return null;
//      }
//    }
//
//    /**
//     * @return true true indica que el plugin accepta generadors de Segell de Temps definits
//     *         dins FileInfoSignature.timeStampGenerator
//     */
//    @Override
//    public boolean acceptExternalTimeStampGenerator(String signType) {
//      return false;
//    }
//
//    /**
//     * 
//     * @return true, indica que el plugin internament ofereix un generador de segellat de
//     *         temps.
//     */
//    @Override
//    public boolean providesTimeStampGenerator(String signType) {
//
//      // S'ha de fer una cridada a PortaFIB per a que passi per tots
//      // els plugins a veure si suporten estampació de segellat de temps per
//      // aquest tipus
//      try {
//        return passarelaDeFirmaEnServidorEjb.providesTimeStampGenerator(signType, entitatID,
//            getFilterByPluginIDList(), null);
//      } catch (Exception e) {
//        log.error(e.getMessage(), e);
//      }
//
//      return false;
//
//    }
//
//    @Override
//    public boolean acceptExternalRubricGenerator() {
//      return false;
//    }
//
//    @Override
//    public boolean providesRubricGenerator() {
//      return true;
//    }
//
//    @Override
//    public boolean acceptExternalSecureVerificationCodeStamper() {
//      return false;
//    }
//
//    @Override
//    public boolean providesSecureVerificationCodeStamper() {
//      return true;
//    }
//  }

}
