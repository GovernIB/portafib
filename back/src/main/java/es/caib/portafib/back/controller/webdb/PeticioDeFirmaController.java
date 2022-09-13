package es.caib.portafib.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;

import es.caib.portafib.back.validator.webdb.PeticioDeFirmaWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PeticioDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/peticioDeFirma")
@SessionAttributes(types = { PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class })
public class PeticioDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<PeticioDeFirma, java.lang.Long, PeticioDeFirmaForm> implements PeticioDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.PeticioDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PeticioDeFirmaService peticioDeFirmaEjb;

  @Autowired
  private PeticioDeFirmaWebValidator peticioDeFirmaWebValidator;

  @Autowired
  protected PeticioDeFirmaRefList peticioDeFirmaRefList;

  // References 
  @Autowired
  protected TipusDocumentRefList tipusDocumentRefList;

  // References 
  @Autowired
  protected IdiomaRefList idiomaRefList;

  // References 
  @Autowired
  protected FluxDeFirmesRefList fluxDeFirmesRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  // References 
  @Autowired
  protected CustodiaInfoRefList custodiaInfoRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected UsuariAplicacioConfiguracioRefList usuariAplicacioConfiguracioRefList;

  /**
   * Llistat de totes PeticioDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PeticioDeFirmaFilterForm ff;
    ff = (PeticioDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PeticioDeFirma de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getPeticioDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
    peticioDeFirmaFilterForm = (PeticioDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(peticioDeFirmaFilterForm == null) {
      peticioDeFirmaFilterForm = new PeticioDeFirmaFilterForm();
      peticioDeFirmaFilterForm.setContexte(getContextWeb());
      peticioDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      peticioDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      peticioDeFirmaFilterForm.setNou(true);
    } else {
      peticioDeFirmaFilterForm.setNou(false);
    }
    peticioDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return peticioDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PeticioDeFirma de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPeticioDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PeticioDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PeticioDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     PeticioDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PeticioDeFirma> peticioDeFirma = processarLlistat(peticioDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("peticioDeFirmaItems", peticioDeFirma);

    mav.addObject("peticioDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, peticioDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, peticioDeFirma);

    return peticioDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PeticioDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PeticioDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipusDocumentID
    {
      _listSKV = getReferenceListForTipusDocumentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusDocumentForTipusDocumentID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSDOCUMENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSDOCUMENTID, false);
      };
    }

    // Field tipusOperacioFirma
    {
      _listSKV = getReferenceListForTipusOperacioFirma(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusOperacioFirma(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSOPERACIOFIRMA)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSOPERACIOFIRMA, false);
      };
    }

    // Field tipusFirmaID
    {
      _listSKV = getReferenceListForTipusFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSFIRMAID, false);
      };
    }

    // Field algorismeDeFirmaID
    {
      _listSKV = getReferenceListForAlgorismeDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForAlgorismeDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(ALGORISMEDEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ALGORISMEDEFIRMAID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("modedefirma", groupByItemsMap, MODEDEFIRMA);

    // Field posicioTaulaFirmesID
    {
      _listSKV = getReferenceListForPosicioTaulaFirmesID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPosicioTaulaFirmesID(_tmp);
      if (filterForm.getGroupByFields().contains(POSICIOTAULAFIRMESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, POSICIOTAULAFIRMESID, false);
      };
    }

    // Field tipusEstatPeticioDeFirmaID
    {
      _listSKV = getReferenceListForTipusEstatPeticioDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusEstatPeticioDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSESTATPETICIODEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSESTATPETICIODEFIRMAID, false);
      };
    }

    // Field idiomaID
    {
      _listSKV = getReferenceListForIdiomaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfIdiomaForIdiomaID(_tmp);
      if (filterForm.getGroupByFields().contains(IDIOMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, IDIOMAID, false);
      };
    }

    // Field prioritatID
    {
      _listSKV = getReferenceListForPrioritatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPrioritatID(_tmp);
      if (filterForm.getGroupByFields().contains(PRIORITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PRIORITATID, false);
      };
    }

    // Field fluxDeFirmesID
    {
      _listSKV = getReferenceListForFluxDeFirmesID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFluxDeFirmesForFluxDeFirmesID(_tmp);
      if (filterForm.getGroupByFields().contains(FLUXDEFIRMESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FLUXDEFIRMESID, false);
      };
    }

    // Field solicitantUsuariAplicacioID
    {
      _listSKV = getReferenceListForSolicitantUsuariAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioForSolicitantUsuariAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITANTUSUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITANTUSUARIAPLICACIOID, false);
      };
    }

    // Field custodiaInfoID
    {
      _listSKV = getReferenceListForCustodiaInfoID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfCustodiaInfoForCustodiaInfoID(_tmp);
      if (filterForm.getGroupByFields().contains(CUSTODIAINFOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CUSTODIAINFOID, false);
      };
    }

    // Field solicitantUsuariEntitat1ID
    {
      _listSKV = getReferenceListForSolicitantUsuariEntitat1ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForSolicitantUsuariEntitat1ID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITANTUSUARIENTITAT1ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITANTUSUARIENTITAT1ID, false);
      };
    }

    // Field solicitantUsuariEntitat2ID
    {
      _listSKV = getReferenceListForSolicitantUsuariEntitat2ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForSolicitantUsuariEntitat2ID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITANTUSUARIENTITAT2ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITANTUSUARIENTITAT2ID, false);
      };
    }

    // Field solicitantUsuariEntitat3ID
    {
      _listSKV = getReferenceListForSolicitantUsuariEntitat3ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForSolicitantUsuariEntitat3ID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITANTUSUARIENTITAT3ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITANTUSUARIENTITAT3ID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, AVISWEB);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, SEGELLATDETEMPS);

    // Field origenPeticioDeFirma
    {
      _listSKV = getReferenceListForOrigenPeticioDeFirma(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForOrigenPeticioDeFirma(_tmp);
      if (filterForm.getGroupByFields().contains(ORIGENPETICIODEFIRMA)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ORIGENPETICIODEFIRMA, false);
      };
    }

    // Field configuracioDeFirmaID
    {
      _listSKV = getReferenceListForConfiguracioDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(CONFIGURACIODEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONFIGURACIODEFIRMAID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PeticioDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PeticioDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PETICIODEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUSDOCUMENTID, filterForm.getMapOfTipusDocumentForTipusDocumentID());
    __mapping.put(TIPUSOPERACIOFIRMA, filterForm.getMapOfValuesForTipusOperacioFirma());
    __mapping.put(TIPUSFIRMAID, filterForm.getMapOfValuesForTipusFirmaID());
    __mapping.put(ALGORISMEDEFIRMAID, filterForm.getMapOfValuesForAlgorismeDeFirmaID());
    __mapping.put(POSICIOTAULAFIRMESID, filterForm.getMapOfValuesForPosicioTaulaFirmesID());
    __mapping.put(TIPUSESTATPETICIODEFIRMAID, filterForm.getMapOfValuesForTipusEstatPeticioDeFirmaID());
    __mapping.put(IDIOMAID, filterForm.getMapOfIdiomaForIdiomaID());
    __mapping.put(PRIORITATID, filterForm.getMapOfValuesForPrioritatID());
    __mapping.put(FLUXDEFIRMESID, filterForm.getMapOfFluxDeFirmesForFluxDeFirmesID());
    __mapping.put(SOLICITANTUSUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForSolicitantUsuariAplicacioID());
    __mapping.put(CUSTODIAINFOID, filterForm.getMapOfCustodiaInfoForCustodiaInfoID());
    __mapping.put(SOLICITANTUSUARIENTITAT1ID, filterForm.getMapOfUsuariEntitatForSolicitantUsuariEntitat1ID());
    __mapping.put(SOLICITANTUSUARIENTITAT2ID, filterForm.getMapOfUsuariEntitatForSolicitantUsuariEntitat2ID());
    __mapping.put(SOLICITANTUSUARIENTITAT3ID, filterForm.getMapOfUsuariEntitatForSolicitantUsuariEntitat3ID());
    __mapping.put(ORIGENPETICIODEFIRMA, filterForm.getMapOfValuesForOrigenPeticioDeFirma());
    __mapping.put(CONFIGURACIODEFIRMAID, filterForm.getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PeticioDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPeticioDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PeticioDeFirmaForm peticioDeFirmaForm = getPeticioDeFirmaForm(null, false, request, mav);
    mav.addObject("peticioDeFirmaForm" ,peticioDeFirmaForm);
    fillReferencesForForm(peticioDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PeticioDeFirmaForm getPeticioDeFirmaForm(PeticioDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PeticioDeFirmaForm peticioDeFirmaForm;
    if(_jpa == null) {
      peticioDeFirmaForm = new PeticioDeFirmaForm(new PeticioDeFirmaJPA(), true);
    } else {
      peticioDeFirmaForm = new PeticioDeFirmaForm(_jpa, false);
      peticioDeFirmaForm.setView(__isView);
    }
    peticioDeFirmaForm.setContexte(getContextWeb());
    peticioDeFirmaForm.setEntityNameCode(getEntityNameCode());
    peticioDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return peticioDeFirmaForm;
  }

  public void fillReferencesForForm(PeticioDeFirmaForm peticioDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfTipusDocumentForTipusDocumentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusDocumentID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfTipusDocumentForTipusDocumentID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForTipusOperacioFirma() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusOperacioFirma(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForTipusOperacioFirma(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForTipusFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusFirmaID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForTipusFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForAlgorismeDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAlgorismeDeFirmaID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForAlgorismeDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForPosicioTaulaFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPosicioTaulaFirmesID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForPosicioTaulaFirmesID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForTipusEstatPeticioDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusEstatPeticioDeFirmaID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForTipusEstatPeticioDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfIdiomaForIdiomaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForIdiomaID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfIdiomaForIdiomaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForPrioritatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPrioritatID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForPrioritatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfFluxDeFirmesForFluxDeFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFluxDeFirmesID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfFluxDeFirmesForFluxDeFirmesID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfUsuariAplicacioForSolicitantUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitantUsuariAplicacioID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfUsuariAplicacioForSolicitantUsuariAplicacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfCustodiaInfoForCustodiaInfoID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCustodiaInfoID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfCustodiaInfoForCustodiaInfoID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfUsuariEntitatForSolicitantUsuariEntitat1ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitantUsuariEntitat1ID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfUsuariEntitatForSolicitantUsuariEntitat1ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfUsuariEntitatForSolicitantUsuariEntitat2ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitantUsuariEntitat2ID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfUsuariEntitatForSolicitantUsuariEntitat2ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfUsuariEntitatForSolicitantUsuariEntitat3ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitantUsuariEntitat3ID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfUsuariEntitatForSolicitantUsuariEntitat3ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfValuesForOrigenPeticioDeFirma() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForOrigenPeticioDeFirma(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfValuesForOrigenPeticioDeFirma(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioDeFirmaForm.getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConfiguracioDeFirmaID(request, mav, peticioDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioDeFirmaForm.setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirmaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PeticioDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPeticioDeFirmaPost(@ModelAttribute PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaForm.getPeticioDeFirma();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, peticioDeFirma, peticioDeFirmaForm); // FILE
      preValidate(request, peticioDeFirmaForm, result);
      getWebValidator().validate(peticioDeFirmaForm, result);
      postValidate(request,peticioDeFirmaForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        peticioDeFirma = create(request, peticioDeFirma);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", peticioDeFirma.getPeticioDeFirmaID());
        peticioDeFirmaForm.setPeticioDeFirma(peticioDeFirma);
        return getRedirectWhenCreated(request, peticioDeFirmaForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{peticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView veurePeticioDeFirmaGet(@PathVariable("peticioDeFirmaID") java.lang.Long peticioDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPeticioDeFirmaGet(peticioDeFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewPeticioDeFirmaGet(@PathVariable("peticioDeFirmaID") java.lang.Long peticioDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    PeticioDeFirmaJPA peticioDeFirma = findByPrimaryKey(request, peticioDeFirmaID);

    if (peticioDeFirma == null) {
      createMessageWarning(request, "error.notfound", peticioDeFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, peticioDeFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PeticioDeFirmaForm peticioDeFirmaForm = getPeticioDeFirmaForm(peticioDeFirma, __isView, request, mav);
      peticioDeFirmaForm.setView(__isView);
      if(__isView) {
        peticioDeFirmaForm.setAllFieldsReadOnly(ALL_PETICIODEFIRMA_FIELDS);
        peticioDeFirmaForm.setSaveButtonVisible(false);
        peticioDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(peticioDeFirmaForm, request, mav);
      mav.addObject("peticioDeFirmaForm", peticioDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PeticioDeFirma existent
   */
  @RequestMapping(value = "/{peticioDeFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPeticioDeFirmaGet(@PathVariable("peticioDeFirmaID") java.lang.Long peticioDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPeticioDeFirmaGet(peticioDeFirmaID,
        request, response, false);
  }



  /**
   * Editar un PeticioDeFirma existent
   */
  @RequestMapping(value = "/{peticioDeFirmaID}/edit", method = RequestMethod.POST)
  public String editarPeticioDeFirmaPost(@ModelAttribute PeticioDeFirmaForm peticioDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaForm.getPeticioDeFirma();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, peticioDeFirma, peticioDeFirmaForm); // FILE
      preValidate(request, peticioDeFirmaForm, result);
      getWebValidator().validate(peticioDeFirmaForm, result);
      postValidate(request, peticioDeFirmaForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        peticioDeFirma = update(request, peticioDeFirma);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", peticioDeFirma.getPeticioDeFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, peticioDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          peticioDeFirma.getPeticioDeFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, peticioDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un PeticioDeFirma existent
   */
  @RequestMapping(value = "/{peticioDeFirmaID}/delete")
  public String eliminarPeticioDeFirma(@PathVariable("peticioDeFirmaID") java.lang.Long peticioDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PeticioDeFirma peticioDeFirma = peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);
      if (peticioDeFirma == null) {
        String __msg =createMessageError(request, "error.notfound", peticioDeFirmaID);
        return getRedirectWhenDelete(request, peticioDeFirmaID, new Exception(__msg));
      } else {
        delete(request, peticioDeFirma);
        createMessageSuccess(request, "success.deleted", peticioDeFirmaID);
        return getRedirectWhenDelete(request, peticioDeFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", peticioDeFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, peticioDeFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PeticioDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPeticioDeFirma(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __peticioDeFirmaID, Throwable e) {
    java.lang.Long peticioDeFirmaID = (java.lang.Long)__peticioDeFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (peticioDeFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(peticioDeFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "peticioDeFirma.peticioDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "peticioDeFirma.peticioDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("peticioDeFirma.peticioDeFirmaID");
  }

  @InitBinder("peticioDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("peticioDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "peticioDeFirma.peticioDeFirmaID");
  }

  public PeticioDeFirmaWebValidator getWebValidator() {
    return peticioDeFirmaWebValidator;
  }


  public void setWebValidator(PeticioDeFirmaWebValidator __val) {
    if (__val != null) {
      this.peticioDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PeticioDeFirma
   */
  @RequestMapping(value = "/{peticioDeFirmaID}/cancel")
  public String cancelPeticioDeFirma(@PathVariable("peticioDeFirmaID") java.lang.Long peticioDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, peticioDeFirmaID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, PeticioDeFirma peticioDeFirma,
      PeticioDeFirmaForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerAFirmarID(), form.isFitxerAFirmarIDDelete(),
        form.isNou()? null : peticioDeFirma.getFitxerAFirmar());
    ((PeticioDeFirmaJPA)peticioDeFirma).setFitxerAFirmar(f);
    if (f != null) { 
      peticioDeFirma.setFitxerAFirmarID(f.getFitxerID());
    } else {
      peticioDeFirma.setFitxerAFirmarID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFirmaOriginalDetachedID(), form.isFirmaOriginalDetachedIDDelete(),
        form.isNou()? null : peticioDeFirma.getFirmaOriginalDetached());
    ((PeticioDeFirmaJPA)peticioDeFirma).setFirmaOriginalDetached(f);
    if (f != null) { 
      peticioDeFirma.setFirmaOriginalDetachedID(f.getFitxerID());
    } else {
      peticioDeFirma.setFirmaOriginalDetachedID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxerAdaptatID(), form.isFitxerAdaptatIDDelete(),
        form.isNou()? null : peticioDeFirma.getFitxerAdaptat());
    ((PeticioDeFirmaJPA)peticioDeFirma).setFitxerAdaptat(f);
    if (f != null) { 
      peticioDeFirma.setFitxerAdaptatID(f.getFitxerID());
    } else {
      peticioDeFirma.setFitxerAdaptatID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getLogoSegellID(), form.isLogoSegellIDDelete(),
        form.isNou()? null : peticioDeFirma.getLogoSegell());
    ((PeticioDeFirmaJPA)peticioDeFirma).setLogoSegell(f);
    if (f != null) { 
      peticioDeFirma.setLogoSegellID(f.getFitxerID());
    } else {
      peticioDeFirma.setLogoSegellID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(PeticioDeFirma peticioDeFirma) {
    deleteFile(peticioDeFirma.getFitxerAFirmarID());
    deleteFile(peticioDeFirma.getFirmaOriginalDetachedID());
    deleteFile(peticioDeFirma.getFitxerAdaptatID());
    deleteFile(peticioDeFirma.getLogoSegellID());
  }
  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(TIPUSDOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(TIPUSDOCUMENTID)) {
      _where = TipusDocumentFields.TIPUSDOCUMENTID.equal(peticioDeFirmaForm.getPeticioDeFirma().getTipusDocumentID());
    }
    return getReferenceListForTipusDocumentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(TIPUSDOCUMENTID)
      && !peticioDeFirmaFilterForm.isGroupByField(TIPUSDOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSDOCUMENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PeticioDeFirma _item : list) {
        _pkList.add(_item.getTipusDocumentID());
        }
        _w = TipusDocumentFields.TIPUSDOCUMENTID.in(_pkList);
      }
    return getReferenceListForTipusDocumentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusDocumentRefList.getReferenceList(TipusDocumentFields.TIPUSDOCUMENTID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusOperacioFirma(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(TIPUSOPERACIOFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusOperacioFirma(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusOperacioFirma(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(TIPUSOPERACIOFIRMA)
      && !peticioDeFirmaFilterForm.isGroupByField(TIPUSOPERACIOFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusOperacioFirma(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusOperacioFirma(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(TIPUSFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusFirmaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(TIPUSFIRMAID)
      && !peticioDeFirmaFilterForm.isGroupByField(TIPUSFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(ALGORISMEDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForAlgorismeDeFirmaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(ALGORISMEDEFIRMAID)
      && !peticioDeFirmaFilterForm.isGroupByField(ALGORISMEDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForAlgorismeDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(POSICIOTAULAFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPosicioTaulaFirmesID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(POSICIOTAULAFIRMESID)
      && !peticioDeFirmaFilterForm.isGroupByField(POSICIOTAULAFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForPosicioTaulaFirmesID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForTipusEstatPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(TIPUSESTATPETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusEstatPeticioDeFirmaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusEstatPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(TIPUSESTATPETICIODEFIRMAID)
      && !peticioDeFirmaFilterForm.isGroupByField(TIPUSESTATPETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusEstatPeticioDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusEstatPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(IDIOMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(IDIOMAID)) {
      _where = IdiomaFields.IDIOMAID.equal(peticioDeFirmaForm.getPeticioDeFirma().getIdiomaID());
    }
    return getReferenceListForIdiomaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(IDIOMAID)
      && !peticioDeFirmaFilterForm.isGroupByField(IDIOMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(IDIOMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PeticioDeFirma _item : list) {
        _pkList.add(_item.getIdiomaID());
        }
        _w = IdiomaFields.IDIOMAID.in(_pkList);
      }
    return getReferenceListForIdiomaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, where );
  }


  public List<StringKeyValue> getReferenceListForPrioritatID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(PRIORITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPrioritatID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPrioritatID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(PRIORITATID)
      && !peticioDeFirmaFilterForm.isGroupByField(PRIORITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForPrioritatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPrioritatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    __tmp.add(new StringKeyValue("6" , "6"));
    __tmp.add(new StringKeyValue("7" , "7"));
    __tmp.add(new StringKeyValue("8" , "8"));
    __tmp.add(new StringKeyValue("9" , "9"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(FLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(FLUXDEFIRMESID)) {
      _where = FluxDeFirmesFields.FLUXDEFIRMESID.equal(peticioDeFirmaForm.getPeticioDeFirma().getFluxDeFirmesID());
    }
    return getReferenceListForFluxDeFirmesID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(FLUXDEFIRMESID)
      && !peticioDeFirmaFilterForm.isGroupByField(FLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FLUXDEFIRMESID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PeticioDeFirma _item : list) {
        _pkList.add(_item.getFluxDeFirmesID());
        }
        _w = FluxDeFirmesFields.FLUXDEFIRMESID.in(_pkList);
      }
    return getReferenceListForFluxDeFirmesID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return fluxDeFirmesRefList.getReferenceList(FluxDeFirmesFields.FLUXDEFIRMESID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(SOLICITANTUSUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(SOLICITANTUSUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(peticioDeFirmaForm.getPeticioDeFirma().getSolicitantUsuariAplicacioID());
    }
    return getReferenceListForSolicitantUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(SOLICITANTUSUARIAPLICACIOID)
      && !peticioDeFirmaFilterForm.isGroupByField(SOLICITANTUSUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITANTUSUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PeticioDeFirma _item : list) {
        _pkList.add(_item.getSolicitantUsuariAplicacioID());
        }
        _w = UsuariAplicacioFields.USUARIAPLICACIOID.in(_pkList);
      }
    return getReferenceListForSolicitantUsuariAplicacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioRefList.getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID, where );
  }


  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(CUSTODIAINFOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(CUSTODIAINFOID)) {
      _where = CustodiaInfoFields.CUSTODIAINFOID.equal(peticioDeFirmaForm.getPeticioDeFirma().getCustodiaInfoID());
    }
    return getReferenceListForCustodiaInfoID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(CUSTODIAINFOID)
      && !peticioDeFirmaFilterForm.isGroupByField(CUSTODIAINFOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CUSTODIAINFOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PeticioDeFirma _item : list) {
        if(_item.getCustodiaInfoID() == null) { continue; };
        _pkList.add(_item.getCustodiaInfoID());
        }
        _w = CustodiaInfoFields.CUSTODIAINFOID.in(_pkList);
      }
    return getReferenceListForCustodiaInfoID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return custodiaInfoRefList.getReferenceList(CustodiaInfoFields.CUSTODIAINFOID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat1ID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(SOLICITANTUSUARIENTITAT1ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(SOLICITANTUSUARIENTITAT1ID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(peticioDeFirmaForm.getPeticioDeFirma().getSolicitantUsuariEntitat1ID());
    }
    return getReferenceListForSolicitantUsuariEntitat1ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat1ID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(SOLICITANTUSUARIENTITAT1ID)
      && !peticioDeFirmaFilterForm.isGroupByField(SOLICITANTUSUARIENTITAT1ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITANTUSUARIENTITAT1ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PeticioDeFirma _item : list) {
        if(_item.getSolicitantUsuariEntitat1ID() == null) { continue; };
        _pkList.add(_item.getSolicitantUsuariEntitat1ID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForSolicitantUsuariEntitat1ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat1ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat2ID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(SOLICITANTUSUARIENTITAT2ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(SOLICITANTUSUARIENTITAT2ID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(peticioDeFirmaForm.getPeticioDeFirma().getSolicitantUsuariEntitat2ID());
    }
    return getReferenceListForSolicitantUsuariEntitat2ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat2ID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(SOLICITANTUSUARIENTITAT2ID)
      && !peticioDeFirmaFilterForm.isGroupByField(SOLICITANTUSUARIENTITAT2ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITANTUSUARIENTITAT2ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PeticioDeFirma _item : list) {
        if(_item.getSolicitantUsuariEntitat2ID() == null) { continue; };
        _pkList.add(_item.getSolicitantUsuariEntitat2ID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForSolicitantUsuariEntitat2ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat2ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat3ID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(SOLICITANTUSUARIENTITAT3ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(SOLICITANTUSUARIENTITAT3ID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(peticioDeFirmaForm.getPeticioDeFirma().getSolicitantUsuariEntitat3ID());
    }
    return getReferenceListForSolicitantUsuariEntitat3ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat3ID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(SOLICITANTUSUARIENTITAT3ID)
      && !peticioDeFirmaFilterForm.isGroupByField(SOLICITANTUSUARIENTITAT3ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITANTUSUARIENTITAT3ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PeticioDeFirma _item : list) {
        if(_item.getSolicitantUsuariEntitat3ID() == null) { continue; };
        _pkList.add(_item.getSolicitantUsuariEntitat3ID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForSolicitantUsuariEntitat3ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitantUsuariEntitat3ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForOrigenPeticioDeFirma(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(ORIGENPETICIODEFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForOrigenPeticioDeFirma(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForOrigenPeticioDeFirma(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(ORIGENPETICIODEFIRMA)
      && !peticioDeFirmaFilterForm.isGroupByField(ORIGENPETICIODEFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForOrigenPeticioDeFirma(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForOrigenPeticioDeFirma(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaForm peticioDeFirmaForm, Where where)  throws I18NException {
    if (peticioDeFirmaForm.isHiddenField(CONFIGURACIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioDeFirmaForm.isReadOnlyField(CONFIGURACIODEFIRMAID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(peticioDeFirmaForm.getPeticioDeFirma().getConfiguracioDeFirmaID());
    }
    return getReferenceListForConfiguracioDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PeticioDeFirmaFilterForm peticioDeFirmaFilterForm,
       List<PeticioDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioDeFirmaFilterForm.isHiddenField(CONFIGURACIODEFIRMAID)
      && !peticioDeFirmaFilterForm.isGroupByField(CONFIGURACIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CONFIGURACIODEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PeticioDeFirma _item : list) {
        if(_item.getConfiguracioDeFirmaID() == null) { continue; };
        _pkList.add(_item.getConfiguracioDeFirmaID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForConfiguracioDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PeticioDeFirmaForm peticioDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PeticioDeFirmaForm peticioDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PeticioDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PeticioDeFirmaFilterForm filterForm,  List<PeticioDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PeticioDeFirmaForm peticioDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long peticioDeFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long peticioDeFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "peticioDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "peticioDeFirmaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PeticioDeFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PeticioDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long peticioDeFirmaID) throws I18NException {
    return (PeticioDeFirmaJPA) peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);
  }


  public PeticioDeFirmaJPA create(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma)
    throws I18NException, I18NValidationException {
    return (PeticioDeFirmaJPA) peticioDeFirmaEjb.create(peticioDeFirma);
  }


  public PeticioDeFirmaJPA update(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma)
    throws I18NException, I18NValidationException {
    return (PeticioDeFirmaJPA) peticioDeFirmaEjb.update(peticioDeFirma);
  }


  public void delete(HttpServletRequest request, PeticioDeFirma peticioDeFirma) throws I18NException {
    peticioDeFirmaEjb.delete(peticioDeFirma);
  }

} // Final de Classe

