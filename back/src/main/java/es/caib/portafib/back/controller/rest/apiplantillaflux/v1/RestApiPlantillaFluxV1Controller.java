package es.caib.portafib.back.controller.rest.apiplantillaflux.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.TextNode;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleBlock;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleEditFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleExternalSigner;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleReviser;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleSignature;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleSigner;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.controller.common.PlantillaDeFluxDeFirmesRestController;
import es.caib.portafib.back.controller.rest.RestUtilsErrorManager;
import es.caib.portafib.back.controller.rest.apifirmaasyncsimple.v2.RestApiFirmaAsyncSimpleV2Controller.ComparatorBlocDeFirmesJPA;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.PlantillaFluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesQueryPath;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = RestApiPlantillaFluxV1Controller.CONTEXT)
public class RestApiPlantillaFluxV1Controller extends RestUtilsErrorManager {

  public static final String CONTEXT = "/common/rest/apiflowtemplatesimple/v1";

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = PlantillaFluxDeFirmesLogicaLocal.JNDI_NAME)
  private PlantillaFluxDeFirmesLogicaLocal plantillaFluxDeFirmesEjb;

  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  public static final Map<String, TransactionInfo> currentTransactions = new HashMap<String, TransactionInfo>();

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.AVAILABLELANGUAGES, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAvailableLanguages(HttpServletRequest request,
          @RequestBody TextNode languageUITextNode) {

      final String languageUI = languageUITextNode.asText();

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {
      // LoginInfo loginInfo =
      commonChecks();

      // Check XYZ ZZZ languageUI

      List<StringKeyValue> idiomes;

      SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
          IdiomaFields.IDIOMAID.select, IdiomaFields.NOM.select);

      idiomes = idiomaEjb.executeQuery(smskv, IdiomaFields.SUPORTAT.equal(true));

      List<FlowTemplateSimpleKeyValue> list = new ArrayList<FlowTemplateSimpleKeyValue>();

      for (StringKeyValue skv : idiomes) {
        list.add(new FlowTemplateSimpleKeyValue(skv.getKey(), skv.getValue()));
      }

      HttpHeaders headers = addAccessControllAllowOrigin();

      ResponseEntity<List<FlowTemplateSimpleKeyValue>> res;
      res = new ResponseEntity<List<FlowTemplateSimpleKeyValue>>(list, headers, HttpStatus.OK);

      return res;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut cridant a getAvailableLanguages: " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETTRANSACTIONID, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getTransactionID(HttpServletRequest request,
      @RequestBody FlowTemplateSimpleGetTransactionIdRequest transactionIDRequest) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // Fer neteja de transaccions Obsoletes !!!!
    cleanExpiredTransactions();

    // Check de commonInfo
    if (transactionIDRequest == null) {
      return generateServerError(
          "El parametre d'entrada de tipus FlowTemplateSimpleGetTransactionIdRequest no pot ser null.");
    }

    String lang = transactionIDRequest.getLanguageUI();
    if (lang == null || lang.trim().length() == 0) {

      return generateServerError(
          "El camp LanguageUI del tipus FlowTemplateSimpleGetTransactionIdRequest no pot ser null o buit.");
    }

    String transactionID = internalGetTransacction();

    HttpHeaders headers = addAccessControllAllowOrigin();

    ResponseEntity<String> res = new ResponseEntity<String>(transactionID, headers,
        HttpStatus.OK);

    final UsuariAplicacioJPA usuariAplicacio = LoginInfo.getInstance().getUsuariAplicacio();
    currentTransactions.put(transactionID,
        new TransactionInfo(transactionID, usuariAplicacio, transactionIDRequest));

    return res;

  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.STARTTRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> startTransaction(HttpServletRequest request,
      @RequestBody FlowTemplateSimpleStartTransactionRequest startTransactionInfo) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ Canviar per idioma per defecte
    String languageUI = "ca";

    try {
      log.info(
          " XYZ ZZZ eNTRA A startTransaction => FlowTemplateSimpleStartTransactionRequest: "
              + startTransactionInfo);

      // TODO XYZ ZZZ CHECKS DE LOGIN
      LoginInfo loginInfo = commonChecks();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Validar simpleSignature
      cleanExpiredTransactions();

      // CHECKS DE variable
      final String transactionID = startTransactionInfo.getTransactionID();

      log.info(" XYZ ZZZ startTransaction::transactionID => |" + transactionID + "|");
      log.info(" XYZ ZZZ startTransaction::currentTransactions.size() => "
          + currentTransactions.size());

      TransactionInfo ti = currentTransactions.get(transactionID);

      if (ti == null) {
        // TODO XYZ ZZZ Traduir
        return generateServerError("No existeix cap transacció amb ID " + transactionID);
      }

      if (ti.getStatus().getStatus() != FlowTemplateSimpleStatus.STATUS_RESERVED_ID) {
        // TODO XYZ ZZZ Traduir
        return generateServerError(
            "La transacció " + transactionID + " es troba en un estat ja iniciat");
      }

      languageUI = ti.getTransactionInfo().getLanguageUI();

      // XYZ ZZZ TODO
      // Falta verificar estructura de

      // XYZ ZZZ final String languageUI = ti.getCommonInfo().getLanguageUI();

      Date dataCreacio = ti.getStartTime();

      if (dataCreacio.getTime() + TransactionInfo.MAX_TIME < System.currentTimeMillis()) {
        // TODO XYZ ZZZ Traduir
        currentTransactions.remove(transactionID);
        return generateServerError("La transacció amb ID " + transactionID + " ha expirat");
      }

      // TODO XYZ ZZZ VALIDAR ESTRUCTURA simpleSignaturesSet

      // Checks Globals
      if (loginInfo.getUsuariEntitat() != null) {
        // XYZ ZZZ ZZZ
        throw new Exception("Aquest servei només el poden fer servir el usuariAPP XYZ ZZZ");
      }

      // Checks usuari aplicacio
      // UsuariAplicacioJPA usuariAplicacio = loginInfo.getUsuariAplicacio();
      //
      // String usuariAplicacioID = usuariAplicacio.getUsuariAplicacioID();
      //
      // EntitatJPA entitatJPA = loginInfo.getEntitat();

      // XYZ ZZZ String entitatID = entitatJPA.getEntitatID();

      // CRIDAR A START TRANSACION

      String urlBase = PropietatGlobalUtil.getUrlBaseForFlowTemplate();

      // String redirectUrl = http://10.215.216.175:8080/portafib/public/plantilla/new
      String redirectUrl = urlBase + PlantillaDeFluxDeFirmesRestController.CONTEXT + "/new/"
          + transactionID;

      ti.setStartTransactionInfo(startTransactionInfo);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(redirectUrl, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ SURT DE startTransaction => FINAL OK");

      ti.getStatus().setStatus(FlowTemplateSimpleStatus.STATUS_IN_PROGRESS);

      return re;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut iniciant la pantalla de Plantilla de Flux de Firmes: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETALLFLOWTEMPLATESBYFILTER, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAllFlowTemplatesByFilter(HttpServletRequest request,
      @RequestBody FlowTemplateSimpleFilterGetAllByFilter filterBy) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ Canviar per idioma per defecte
    String languageUI = "ca";

    try {
      log.info(" XYZ ZZZ eNTRA A getAllFlowTemplatesByFilter => filterBy: " + filterBy);

      // TODO XYZ ZZZ CHECKS DE LOGIN
      LoginInfo loginInfo = commonChecks();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Validar simpleSignature
      cleanExpiredTransactions();

      FlowTemplateSimpleFlowTemplateList list = internalGetAll(filterBy.getNameFilter(),
          filterBy.getDescriptionFilter());

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FlowTemplateSimpleFlowTemplateList>(list,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ SURT DE getAllFlowTemplatesByFilter => FINAL OK");

      return re;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut iniciant la pantalla de Plantilla de Flux de Firmes: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETFLOWINFOBYFLOWTEMPLATEID, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getFlowInfoByFlowTemplateID(HttpServletRequest request,
      @RequestBody FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest) {

    
    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    // XYZ ZZZ Canviar per idioma per defecte
    String languageUI = flowTemplateRequest.getLanguageUI();
    
    String encryptedFlowTemplateID = flowTemplateRequest.getFlowTemplateId();

    try {
      log.info(" XYZ ZZZ eNTRA A getFlowInfoByFlowTemplateID => flowTemplateID: "
          + encryptedFlowTemplateID);

      // TODO XYZ ZZZ CHECKS DE LOGIN
      LoginInfo loginInfo = commonChecks();

      log.info(" XYZ ZZZ LOGININFO => " + loginInfo);

      // Validar simpleSignature
      cleanExpiredTransactions();

      FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();

      String decriptStrFlowTemplateID = encrypter.decrypt(encryptedFlowTemplateID);

      Long decriptFlowTemplateID = Long.parseLong(decriptStrFlowTemplateID);

      FlowTemplateSimpleFlowTemplate info = getFlowTemplateInfo(decriptFlowTemplateID);

      info.setIntermediateServerFlowTemplateId(encryptedFlowTemplateID);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FlowTemplateSimpleFlowTemplate>(info, headers,
          HttpStatus.OK);

      return re;

    } catch (I18NException i18ne) {

      String msg = I18NLogicUtils.getMessage(i18ne, new Locale(languageUI));

      log.error(msg, i18ne);

      return generateServerError(msg);

    } catch (Throwable th) {

      // XYZ ZZZ TRA
      String msg = "Error desconegut iniciant la pantalla de Plantilla de Flux de Firmes: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }



  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETFLOWTEMPLATERESULT, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getFlowTemplateResult(HttpServletRequest request, HttpServletResponse response,
      @RequestBody TextNode textNodeTransactionID) {
      String transactionID = textNodeTransactionID.asText();

    try {

      String error = autenticateUsrApp(request);
      if (error != null) {
        return generateServerError(error, HttpStatus.UNAUTHORIZED);
      }

      TransactionInfo ti = currentTransactions.get(transactionID);

      if (ti == null) {
        // TODO XYZ ZZZ Traduir
        return generateServerError("No existeix cap transacció amb ID " + transactionID);
      }

      FlowTemplateSimpleStatus status = ti.getStatus();

      FlowTemplateSimpleFlowTemplate flowInfo = null;
      if (status.getStatus() == FlowTemplateSimpleStatus.STATUS_FINAL_OK) {

        Long fluxDeFirmesID = ti.getFluxDeFirmesID();

        flowInfo = getFlowTemplateInfo(fluxDeFirmesID);

        String returnedFluxID;
        if (ti.getTransactionInfo().isSaveOnServer()) {
          FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
          returnedFluxID = encrypter.encrypt(String.valueOf(fluxDeFirmesID));
        } else {
          returnedFluxID = null;
        }
        flowInfo.setIntermediateServerFlowTemplateId(returnedFluxID);

      }

      List<FlowTemplateSimpleKeyValue> properties = null;

      FlowTemplateSimpleGetFlowResultResponse result = new FlowTemplateSimpleGetFlowResultResponse(
          status, flowInfo, properties);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FlowTemplateSimpleGetFlowResultResponse>(
          result, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL");

      return re;

    } catch (Throwable th) {
      final String msg = "Error desconegut intentant recuperar informació del Flux de Firmes "
          + transactionID + ": " + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }

  }

  protected FlowTemplateSimpleFlowTemplate getFlowTemplateInfo(Long fluxDeFirmesID)
      throws I18NException, Exception {
    FlowTemplateSimpleFlowTemplate flowInfo;

    FluxDeFirmesJPA flux;
    flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(fluxDeFirmesID);

    String name = flux.getNom();
    String descripcio = flux.getPlantillaFluxDeFirmes().getDescripcio();

    List<BlocDeFirmesJPA> blocsJPA = new ArrayList<BlocDeFirmesJPA>(flux.getBlocDeFirmess());
    Collections.sort(blocsJPA, new ComparatorBlocDeFirmesJPA());

    List<FlowTemplateSimpleBlock> blocks = new ArrayList<FlowTemplateSimpleBlock>();
    for (BlocDeFirmesJPA blocJPA : blocsJPA) {

      List<FlowTemplateSimpleSignature> signatures = new ArrayList<FlowTemplateSimpleSignature>();

      for (FirmaJPA firmaJPA : blocJPA.getFirmas()) {

        FlowTemplateSimpleSigner signer = new FlowTemplateSimpleSigner();

        if (firmaJPA.getUsuariExternEmail() == null) {

          // No és usuari extern: és un usuri entitat que pot ser un càrrec
          if (firmaJPA.getUsuariEntitat().getCarrec() == null) {
            signer.setIntermediateServerUsername(firmaJPA.getDestinatariID());
          } else {
            signer.setPositionInTheCompany(firmaJPA.getDestinatariID());
          }

        } else {

          String administrationId = firmaJPA.getUsuariEntitat().getUsuariPersona().getNif();

          String uename = firmaJPA.getUsuariExternNom();
          String surnames = firmaJPA.getUsuariExternLlinatges();
          String email = firmaJPA.getUsuariExternEmail();
          String language = firmaJPA.getUsuariExternIdioma();
          int securityLevel = firmaJPA.getUsuariExternNivellSeguretat();

          FlowTemplateSimpleExternalSigner externalSigner = new FlowTemplateSimpleExternalSigner(
              administrationId, uename, surnames, email, language, securityLevel);

          signer.setExternalSigner(externalSigner);

        }

        boolean required = firmaJPA.isObligatori();
        String reason = firmaJPA.getMotiu();
        int minimumNumberOfRevisers = firmaJPA.getMinimDeRevisors();

        List<FlowTemplateSimpleReviser> revisers = null;

        Set<RevisorDeFirmaJPA> revisors = firmaJPA.getRevisorDeFirmas();

        if (revisors != null && revisors.size() != 0) {

          revisers = new ArrayList<FlowTemplateSimpleReviser>();

          for (RevisorDeFirmaJPA revisor : revisors) {

            FlowTemplateSimpleReviser reviser = new FlowTemplateSimpleReviser();
            reviser.setIntermediateServerUsername(revisor.getUsuariEntitatID());
            reviser.setRequired(revisor.isObligatori());

            revisers.add(reviser);
          }

        }

        FlowTemplateSimpleSignature signature = new FlowTemplateSimpleSignature(signer,
            required, reason, minimumNumberOfRevisers, revisers);

        signatures.add(signature);
      }

      FlowTemplateSimpleBlock newBlock = new FlowTemplateSimpleBlock(blocJPA.getOrdre(),
          blocJPA.getMinimDeFirmes(), signatures);
      blocks.add(newBlock);
    }

    String returnedFluxID = null;

    flowInfo = new FlowTemplateSimpleFlowTemplate(returnedFluxID, name, descripcio, blocks);
    return flowInfo;
  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.CLOSETRANSACTION, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void closeTransaction(HttpServletRequest request,
      HttpServletResponse response, @RequestBody TextNode textNodeTransactionID) {

      String transactionID = textNodeTransactionID.asText();

    log.info(" XYZ ZZZ closeTransaction => ENTRA");

    String error = autenticateUsrApp(request);
    if (error != null) {
      try {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, error);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
      return;
    }

    internalCloseTransaction(transactionID);

    log.info(" XYZ ZZZ closeTransaction => FINAL OK");

  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETALLFLOWTEMPLATES, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getAllFlowTemplates(HttpServletRequest request, HttpServletResponse response,
          @RequestBody TextNode languageUITextNode) {

final String languageUI = languageUITextNode.asText();

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {

      FlowTemplateSimpleFlowTemplateList result = internalGetAll(null, null);

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<FlowTemplateSimpleFlowTemplateList>(result,
          headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getAllFlowTemplates => FINAL");

      return re;

    } catch (Throwable th) {
      final String msg = "Error desconegut intentant recuperar informació "
          + "dels Flux de Firmes d'un uauari apicació:" + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }

  protected FlowTemplateSimpleFlowTemplateList internalGetAll(String name, String description)
      throws I18NException, Exception {

    String usuariAplicacioID = LoginInfo.getInstance().getUsuariAplicacio().getUsuariAplicacioID();

    SelectMultipleStringKeyValue select = new SelectMultipleStringKeyValue(
        PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.select,
        new PlantillaFluxDeFirmesQueryPath().FLUXDEFIRMES().NOM().select);

    Where where = PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID);

    if (name != null && name.trim().length() != 0) {
      where = Where.AND(where,
          new PlantillaFluxDeFirmesQueryPath().FLUXDEFIRMES().NOM().like("%" + name + "%"));
    }

    if (description != null && description.trim().length() != 0) {
      where = Where.AND(PlantillaFluxDeFirmesFields.DESCRIPCIO.like("%" + description + "%"));
    }

    List<StringKeyValue> listKV = plantillaFluxDeFirmesEjb.executeQuery(select, where);

    List<FlowTemplateSimpleKeyValue> list = new ArrayList<FlowTemplateSimpleKeyValue>();

    FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();

    for (StringKeyValue skv : listKV) {
      list.add(
          new FlowTemplateSimpleKeyValue(encrypter.encrypt(skv.getKey()), skv.getValue()));
    }

    FlowTemplateSimpleFlowTemplateList result = new FlowTemplateSimpleFlowTemplateList(list);
    return result;
  }

  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETURLTOVIEWFLOWTEMPLATE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getUrlToViewFlowTemplate(
      @RequestBody FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest,
      HttpServletRequest request, HttpServletResponse response) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {

      // String usuariAplicacioID = usuariAplicacioCache.get().getUsuariAplicacioID();

      // XYZ ZZZ ZZZ REVISAR CERTA CACHE PER SEGURETAT !!!!
      String result = PropietatGlobalUtil.getUrlBaseForFlowTemplate()
          + PlantillaDeFluxDeFirmesRestController.CONTEXT + "/viewflux/"
          + viewFlowRequest.getFlowTemplateID(); // + "?readOnly=true";

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(result, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL");

      return re;

    } catch (Throwable th) {
      final String msg = "Error desconegut intentant recuperar informació "
          + "dels Flux de Firmes d'un uauari apicació:" + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }
  
  
  
  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.GETURLTOEDITFLOWTEMPLATE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> getUrlToEditFlowTemplate(
      @RequestBody FlowTemplateSimpleEditFlowTemplateRequest editFlowTemplate,
      HttpServletRequest request, HttpServletResponse response) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {
      
      
      String transactionID = internalGetTransacction();

      
      String fluxDeFirmesIDStr = editFlowTemplate.getFlowTemplateId();
      String languageUI = editFlowTemplate.getLanguageUI();
      
      java.lang.Long fluxDeFirmesID;

      FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
      try {
        fluxDeFirmesID = Long.parseLong(encrypter.decrypt(fluxDeFirmesIDStr));
      } catch (Exception e) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "Error desencriptant identificador de Flux de Firmes");
      }
      
      
     
      

      // Verificar que existeix
      FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(fluxDeFirmesID);
      
      if (flux == null) {
        // XYZ ZZZ TRA
        String msg = "No existeix la plantilla de Flux de Firmes amb ID " + fluxDeFirmesIDStr;
        
        log.error(msg + "( ID BBDD => " + fluxDeFirmesID + ")");
        
        throw new I18NException("genapp.comodi", msg);
      }

      String name = flux.getNom();
      String description = flux.getPlantillaFluxDeFirmes().getDescripcio(); 
      boolean visibleDescription = false;
      
      FlowTemplateSimpleGetTransactionIdRequest transactionIDRequest;
      transactionIDRequest = new FlowTemplateSimpleGetTransactionIdRequest(languageUI, true, name, description, visibleDescription);
      
      FlowTemplateSimpleStartTransactionRequest startTransactionInfo;
      startTransactionInfo = new FlowTemplateSimpleStartTransactionRequest(transactionID, editFlowTemplate.getReturnUrl());

      final UsuariAplicacioJPA usuariAplicacio = LoginInfo.getInstance().getUsuariAplicacio();
      TransactionInfo info = new TransactionInfo(transactionID, usuariAplicacio, transactionIDRequest);
      
      info.setStartTransactionInfo(startTransactionInfo);
      info.setFluxDeFirmesID(fluxDeFirmesID);

      currentTransactions.put(transactionID, info);

      // String usuariAplicacioID = usuariAplicacioCache.get().getUsuariAplicacioID();
      // XYZ ZZZ ZZZ REVISAR CERTA CACHE PER SEGURETAT !!!!
      String result = PropietatGlobalUtil.getUrlBaseForFlowTemplate()
          + PlantillaDeFluxDeFirmesRestController.CONTEXT + "/editflux/"
          + transactionID; 

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<String>(result, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getUrlToEditFlowTemplate => FINAL");

      return re;

    } catch (Throwable th) {
      
      // XYZ ZZZ ZZZZ
      final String msg = "Error desconegut intentant recuperar informació "
          + "dels Flux de Firmes d'un uauari apicació:" + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }
  
  
  
  @RequestMapping(value = "/"
      + ApiFlowTemplateSimple.DELETEFLOWTEMPLATE, method = RequestMethod.POST)
  @ResponseBody
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ResponseEntity<?> deleteFlowTemplate(
      @RequestBody FlowTemplateSimpleFlowTemplateRequest flowTemplaterequest,
      HttpServletRequest request, HttpServletResponse response) {

    String error = autenticateUsrApp(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {

      Long fluxDeFirmesID;

      FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
      try {
        fluxDeFirmesID = Long.parseLong(encrypter.decrypt(flowTemplaterequest.getFlowTemplateId()));
      } catch (Exception e) {
        throw new I18NException("genapp.comodi",
            "Error desencriptant identificador de Flux de Firmes");
      }

      fluxDeFirmesLogicaEjb.deleteFull(fluxDeFirmesID);

      Boolean result = true;

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<Boolean>(result, headers, HttpStatus.OK);
      log.info(" XYZ ZZZ surt de  getTransactionStatus => FINAL");

      return re;

    } catch (Throwable th) {

      // XYZ ZZZ ZZZZ
      final String msg = "Error desconegut intentant esborrar Plantilla de "
          + " Flux de Firmes d'un usuari apicació:" + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }
  
  
  

  protected void internalCloseTransaction(String transactionID) {

    TransactionInfo ti = currentTransactions.get(transactionID);

    if (!ti.getTransactionInfo().isSaveOnServer()) {
      try {
        fluxDeFirmesLogicaEjb.deleteFull(ti.getFluxDeFirmesID());
      } catch (I18NException e) {
        log.error("No s'ha pogut esborrar la plantilla de Flux de Firmes amb ID = "
            + ti.getFluxDeFirmesID(), e);
      }
    }

    currentTransactions.remove(transactionID);

  }

  /**
   * Fer neteja de transaccions Obsoletes
   */
  protected void cleanExpiredTransactions() {

    final long now = System.currentTimeMillis();
    for (TransactionInfo info : new ArrayList<TransactionInfo>(currentTransactions.values())) {
      try {
        // 15 minutes
        if (info.getStartTime().getTime() + 900000 < now) {
          internalCloseTransaction(info.getTransactionID());
        }
      } catch (Exception e) {
        log.error("Error desconegut" + " netejant transaccions expirades de l'APIFirmaSimple: "
            + e.getMessage(), e);
      }
    }
  }

  /**
   * 
   * @author anadal
   *
   */
  public class TransactionInfo {

    // 15 minuts
    public static final long MAX_TIME = 900000L;

    public static final int STATUS_IN_PROGRESS = 1;

    protected final String transactionID;

    protected final UsuariAplicacioJPA usuariAplicacio;

    protected final FlowTemplateSimpleGetTransactionIdRequest transactionInfo;

    protected FlowTemplateSimpleStartTransactionRequest startTransactionInfo;

    protected final Date startTime;

    protected final FlowTemplateSimpleStatus status;

    protected Long fluxDeFirmesID;

    public TransactionInfo(String transactionID, UsuariAplicacioJPA usuariAplicacio,
        FlowTemplateSimpleGetTransactionIdRequest transactionIInfo) {
      super();
      this.transactionID = transactionID;
      this.usuariAplicacio = usuariAplicacio;
      this.startTime = new Date();
      this.transactionInfo = transactionIInfo;
      this.status = new FlowTemplateSimpleStatus(FlowTemplateSimpleStatus.STATUS_RESERVED_ID);
    }

    public FlowTemplateSimpleStartTransactionRequest getStartTransactionInfo() {
      return startTransactionInfo;
    }

    public void setStartTransactionInfo(
        FlowTemplateSimpleStartTransactionRequest startTransactionInfo) {
      this.startTransactionInfo = startTransactionInfo;
    }

    public FlowTemplateSimpleStatus getStatus() {
      return status;
    }

    public Long getFluxDeFirmesID() {
      return fluxDeFirmesID;
    }

    public void setFluxDeFirmesID(Long fluxDeFirmesID) {
      this.fluxDeFirmesID = fluxDeFirmesID;
    }

    public UsuariAplicacioJPA getUsuariAplicacio() {
      return usuariAplicacio;
    }

    public String getTransactionID() {
      return transactionID;
    }

    public FlowTemplateSimpleGetTransactionIdRequest getTransactionInfo() {
      return transactionInfo;
    }

    public Date getStartTime() {
      return startTime;
    }

  }

}
