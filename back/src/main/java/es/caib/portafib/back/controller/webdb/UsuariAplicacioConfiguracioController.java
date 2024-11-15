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
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioForm;

import es.caib.portafib.back.validator.webdb.UsuariAplicacioConfiguracioWebValidator;

import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un UsuariAplicacioConfiguracio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/usuariAplicacioConfiguracio")
@SessionAttributes(types = { UsuariAplicacioConfiguracioForm.class, UsuariAplicacioConfiguracioFilterForm.class })
public class UsuariAplicacioConfiguracioController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<UsuariAplicacioConfiguracio, java.lang.Long> implements UsuariAplicacioConfiguracioFields {

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;

  @Autowired
  private UsuariAplicacioConfiguracioWebValidator usuariAplicacioConfiguracioWebValidator;

  @Autowired
  protected UsuariAplicacioConfiguracioRefList usuariAplicacioConfiguracioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes UsuariAplicacioConfiguracio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    UsuariAplicacioConfiguracioFilterForm ff;
    ff = (UsuariAplicacioConfiguracioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar UsuariAplicacioConfiguracio de forma paginada
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
    llistat(mav, request, getUsuariAplicacioConfiguracioFilterForm(pagina, mav, request));
    return mav;
  }

  public UsuariAplicacioConfiguracioFilterForm getUsuariAplicacioConfiguracioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm;
    usuariAplicacioConfiguracioFilterForm = (UsuariAplicacioConfiguracioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(usuariAplicacioConfiguracioFilterForm == null) {
      usuariAplicacioConfiguracioFilterForm = new UsuariAplicacioConfiguracioFilterForm();
      usuariAplicacioConfiguracioFilterForm.setContexte(getContextWeb());
      usuariAplicacioConfiguracioFilterForm.setEntityNameCode(getEntityNameCode());
      usuariAplicacioConfiguracioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      usuariAplicacioConfiguracioFilterForm.setNou(true);
    } else {
      usuariAplicacioConfiguracioFilterForm.setNou(false);
    }
    usuariAplicacioConfiguracioFilterForm.setPage(pagina == null ? 1 : pagina);
    return usuariAplicacioConfiguracioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar UsuariAplicacioConfiguracio de forma paginada
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
      @ModelAttribute UsuariAplicacioConfiguracioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getUsuariAplicacioConfiguracioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de UsuariAplicacioConfiguracio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<UsuariAplicacioConfiguracio> llistat(ModelAndView mav, HttpServletRequest request,
     UsuariAplicacioConfiguracioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<UsuariAplicacioConfiguracio> usuariAplicacioConfiguracio = processarLlistat(usuariAplicacioConfiguracioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("usuariAplicacioConfiguracioItems", usuariAplicacioConfiguracio);

    mav.addObject("usuariAplicacioConfiguracioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, usuariAplicacioConfiguracio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, usuariAplicacioConfiguracio);

    return usuariAplicacioConfiguracio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(UsuariAplicacioConfiguracioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<UsuariAplicacioConfiguracio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAAPISIMPLESERVIDOR);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAAPISIMPLEWEB);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAWEB);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAWS1);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAASYNCREST2);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAPASSARELASERVIDOR);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, USENFIRMAPASSARELAWEB);

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

    // Field modeDeFirma
    {
      _listSKV = getReferenceListForModeDeFirma(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForModeDeFirma(_tmp);
      if (filterForm.getGroupByFields().contains(MODEDEFIRMA)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MODEDEFIRMA, false);
      };
    }

    // Field usPoliticaDeFirma
    {
      _listSKV = getReferenceListForUsPoliticaDeFirma(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForUsPoliticaDeFirma(_tmp);
      if (filterForm.getGroupByFields().contains(USPOLITICADEFIRMA)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USPOLITICADEFIRMA, false);
      };
    }

    // Field motiuDelegacioID
    {
      _listSKV = getReferenceListForMotiuDelegacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForMotiuDelegacioID(_tmp);
      if (filterForm.getGroupByFields().contains(MOTIUDELEGACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MOTIUDELEGACIOID, false);
      };
    }

    // Field politicaTaulaFirmes
    {
      _listSKV = getReferenceListForPoliticaTaulaFirmes(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPoliticaTaulaFirmes(_tmp);
      if (filterForm.getGroupByFields().contains(POLITICATAULAFIRMES)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, POLITICATAULAFIRMES, false);
      };
    }

    // Field posicioTaulaFirmesID
    {
      _listSKV = getReferenceListForPosicioTaulaFirmesID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPosicioTaulaFirmesID(_tmp);
      if (filterForm.getGroupByFields().contains(POSICIOTAULAFIRMESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, POSICIOTAULAFIRMESID, false);
      };
    }

    // Field firmatPerFormatID
    {
      _listSKV = getReferenceListForFirmatPerFormatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForFirmatPerFormatID(_tmp);
      if (filterForm.getGroupByFields().contains(FIRMATPERFORMATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FIRMATPERFORMATID, false);
      };
    }

    // Field politicaSegellatDeTemps
    {
      _listSKV = getReferenceListForPoliticaSegellatDeTemps(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPoliticaSegellatDeTemps(_tmp);
      if (filterForm.getGroupByFields().contains(POLITICASEGELLATDETEMPS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, POLITICASEGELLATDETEMPS, false);
      };
    }

    // Field pluginSegellatID
    {
      _listSKV = getReferenceListForPluginSegellatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginSegellatID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINSEGELLATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINSEGELLATID, false);
      };
    }

    // Field pluginFirmaServidorID
    {
      _listSKV = getReferenceListForPluginFirmaServidorID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginFirmaServidorID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINFIRMASERVIDORID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINFIRMASERVIDORID, false);
      };
    }

    // Field upgradeSignFormat
    {
      _listSKV = getReferenceListForUpgradeSignFormat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForUpgradeSignFormat(_tmp);
      if (filterForm.getGroupByFields().contains(UPGRADESIGNFORMAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, UPGRADESIGNFORMAT, false);
      };
    }


      fillValuesToGroupByItemsBoolean("definitenentitat", groupByItemsMap, VALIDARFIRMA);


      fillValuesToGroupByItemsBoolean("definitenentitat", groupByItemsMap, CHECKCANVIATDOCFIRMAT);


      fillValuesToGroupByItemsBoolean("definitenentitat", groupByItemsMap, COMPROVARNIFFIRMA);


      fillValuesToGroupByItemsBoolean("definitenentitat", groupByItemsMap, VALIDARCERTIFICAT);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ESDEPETICIO);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    UsuariAplicacioConfiguracioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<UsuariAplicacioConfiguracio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    __mapping.put(TIPUSOPERACIOFIRMA, filterForm.getMapOfValuesForTipusOperacioFirma());
    __mapping.put(TIPUSFIRMAID, filterForm.getMapOfValuesForTipusFirmaID());
    __mapping.put(ALGORISMEDEFIRMAID, filterForm.getMapOfValuesForAlgorismeDeFirmaID());
    __mapping.put(MODEDEFIRMA, filterForm.getMapOfValuesForModeDeFirma());
    __mapping.put(USPOLITICADEFIRMA, filterForm.getMapOfValuesForUsPoliticaDeFirma());
    __mapping.put(MOTIUDELEGACIOID, filterForm.getMapOfTraduccioForMotiuDelegacioID());
    __mapping.put(POLITICATAULAFIRMES, filterForm.getMapOfValuesForPoliticaTaulaFirmes());
    __mapping.put(POSICIOTAULAFIRMESID, filterForm.getMapOfValuesForPosicioTaulaFirmesID());
    __mapping.put(FIRMATPERFORMATID, filterForm.getMapOfTraduccioForFirmatPerFormatID());
    __mapping.put(POLITICASEGELLATDETEMPS, filterForm.getMapOfValuesForPoliticaSegellatDeTemps());
    __mapping.put(PLUGINSEGELLATID, filterForm.getMapOfPluginForPluginSegellatID());
    __mapping.put(PLUGINFIRMASERVIDORID, filterForm.getMapOfPluginForPluginFirmaServidorID());
    __mapping.put(UPGRADESIGNFORMAT, filterForm.getMapOfValuesForUpgradeSignFormat());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou UsuariAplicacioConfiguracio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearUsuariAplicacioConfiguracioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm = getUsuariAplicacioConfiguracioForm(null, false, request, mav);
    
    if (usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().getMotiuDelegacio() == null){
      es.caib.portafib.persistence.TraduccioJPA trad = new es.caib.portafib.persistence.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : usuariAplicacioConfiguracioForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.persistence.TraduccioMapJPA());
      }
      usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().setMotiuDelegacio(trad);
    }

    
    if (usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().getFirmatPerFormat() == null){
      es.caib.portafib.persistence.TraduccioJPA trad = new es.caib.portafib.persistence.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : usuariAplicacioConfiguracioForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.persistence.TraduccioMapJPA());
      }
      usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().setFirmatPerFormat(trad);
    }

    mav.addObject("usuariAplicacioConfiguracioForm" ,usuariAplicacioConfiguracioForm);
    fillReferencesForForm(usuariAplicacioConfiguracioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public UsuariAplicacioConfiguracioForm getUsuariAplicacioConfiguracioForm(UsuariAplicacioConfiguracioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm;
    if(_jpa == null) {
      usuariAplicacioConfiguracioForm = new UsuariAplicacioConfiguracioForm(new UsuariAplicacioConfiguracioJPA(), true);
    } else {
      usuariAplicacioConfiguracioForm = new UsuariAplicacioConfiguracioForm(_jpa, false);
      usuariAplicacioConfiguracioForm.setView(__isView);
    }
    usuariAplicacioConfiguracioForm.setContexte(getContextWeb());
    usuariAplicacioConfiguracioForm.setEntityNameCode(getEntityNameCode());
    usuariAplicacioConfiguracioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    usuariAplicacioConfiguracioForm.setIdiomesTraduccio(getIdiomesSuportats());
    return usuariAplicacioConfiguracioForm;
  }

  public void fillReferencesForForm(UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfEntitatForEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForTipusOperacioFirma() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusOperacioFirma(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForTipusOperacioFirma(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForTipusFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusFirmaID(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForTipusFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForAlgorismeDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAlgorismeDeFirmaID(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForAlgorismeDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForModeDeFirma() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForModeDeFirma(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForModeDeFirma(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForUsPoliticaDeFirma() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsPoliticaDeFirma(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForUsPoliticaDeFirma(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForPoliticaTaulaFirmes() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPoliticaTaulaFirmes(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForPoliticaTaulaFirmes(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForPosicioTaulaFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPosicioTaulaFirmesID(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForPosicioTaulaFirmesID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForPoliticaSegellatDeTemps() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPoliticaSegellatDeTemps(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForPoliticaSegellatDeTemps(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfPluginForPluginSegellatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginSegellatID(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfPluginForPluginSegellatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfPluginForPluginFirmaServidorID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginFirmaServidorID(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfPluginForPluginFirmaServidorID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariAplicacioConfiguracioForm.getListOfValuesForUpgradeSignFormat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUpgradeSignFormat(request, mav, usuariAplicacioConfiguracioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariAplicacioConfiguracioForm.setListOfValuesForUpgradeSignFormat(_listSKV);
    }
    
  }


  public List<es.caib.portafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.portafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.portafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou UsuariAplicacioConfiguracio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearUsuariAplicacioConfiguracioPost(@ModelAttribute UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio = usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio();

    try {
      preValidate(request, usuariAplicacioConfiguracioForm, result);
      getWebValidator().validate(usuariAplicacioConfiguracioForm, result);
      postValidate(request,usuariAplicacioConfiguracioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        usuariAplicacioConfiguracio = create(request, usuariAplicacioConfiguracio);
        createMessageSuccess(request, "success.creation", usuariAplicacioConfiguracio.getUsuariAplicacioConfigID());
        usuariAplicacioConfiguracioForm.setUsuariAplicacioConfiguracio(usuariAplicacioConfiguracio);
        return getRedirectWhenCreated(request, usuariAplicacioConfiguracioForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{usuariAplicacioConfigID}", method = RequestMethod.GET)
  public ModelAndView veureUsuariAplicacioConfiguracioGet(@PathVariable("usuariAplicacioConfigID") java.lang.Long usuariAplicacioConfigID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariAplicacioConfiguracioGet(usuariAplicacioConfigID,
        request, response, true);
  }


  protected ModelAndView editAndViewUsuariAplicacioConfiguracioGet(@PathVariable("usuariAplicacioConfigID") java.lang.Long usuariAplicacioConfigID,
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
    UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio = findByPrimaryKey(request, usuariAplicacioConfigID);

    if (usuariAplicacioConfiguracio == null) {
      createMessageWarning(request, "error.notfound", usuariAplicacioConfigID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, usuariAplicacioConfigID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm = getUsuariAplicacioConfiguracioForm(usuariAplicacioConfiguracio, __isView, request, mav);
      usuariAplicacioConfiguracioForm.setView(__isView);
      if(__isView) {
        usuariAplicacioConfiguracioForm.setAllFieldsReadOnly(ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS);
        usuariAplicacioConfiguracioForm.setSaveButtonVisible(false);
        usuariAplicacioConfiguracioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(usuariAplicacioConfiguracioForm, request, mav);
      mav.addObject("usuariAplicacioConfiguracioForm", usuariAplicacioConfiguracioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un UsuariAplicacioConfiguracio existent
   */
  @RequestMapping(value = "/{usuariAplicacioConfigID}/edit", method = RequestMethod.GET)
  public ModelAndView editarUsuariAplicacioConfiguracioGet(@PathVariable("usuariAplicacioConfigID") java.lang.Long usuariAplicacioConfigID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariAplicacioConfiguracioGet(usuariAplicacioConfigID,
        request, response, false);
  }



  /**
   * Editar un UsuariAplicacioConfiguracio existent
   */
  @RequestMapping(value = "/{usuariAplicacioConfigID}/edit", method = RequestMethod.POST)
  public String editarUsuariAplicacioConfiguracioPost(@ModelAttribute UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio = usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio();

    try {
      preValidate(request, usuariAplicacioConfiguracioForm, result);
      getWebValidator().validate(usuariAplicacioConfiguracioForm, result);
      postValidate(request, usuariAplicacioConfiguracioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        usuariAplicacioConfiguracio = update(request, usuariAplicacioConfiguracio);
        createMessageSuccess(request, "success.modification", usuariAplicacioConfiguracio.getUsuariAplicacioConfigID());
        status.setComplete();
        return getRedirectWhenModified(request, usuariAplicacioConfiguracioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          usuariAplicacioConfiguracio.getUsuariAplicacioConfigID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, usuariAplicacioConfiguracioForm, __e);
    }

  }


  /**
   * Eliminar un UsuariAplicacioConfiguracio existent
   */
  @RequestMapping(value = "/{usuariAplicacioConfigID}/delete")
  public String eliminarUsuariAplicacioConfiguracio(@PathVariable("usuariAplicacioConfigID") java.lang.Long usuariAplicacioConfigID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      UsuariAplicacioConfiguracio usuariAplicacioConfiguracio = this.findByPrimaryKey(request, usuariAplicacioConfigID);
      if (usuariAplicacioConfiguracio == null) {
        String __msg = createMessageError(request, "error.notfound", usuariAplicacioConfigID);
        return getRedirectWhenDelete(request, usuariAplicacioConfigID, new Exception(__msg));
      } else {
        delete(request, usuariAplicacioConfiguracio);
        createMessageSuccess(request, "success.deleted", usuariAplicacioConfigID);
        return getRedirectWhenDelete(request, usuariAplicacioConfigID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", usuariAplicacioConfigID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, usuariAplicacioConfigID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute UsuariAplicacioConfiguracioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarUsuariAplicacioConfiguracio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __usuariAplicacioConfigID, Throwable e) {
    java.lang.Long usuariAplicacioConfigID = (java.lang.Long)__usuariAplicacioConfigID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (usuariAplicacioConfigID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(usuariAplicacioConfigID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "usuariAplicacioConfiguracio.usuariAplicacioConfiguracio";
  }

  public String getEntityNameCodePlural() {
    return "usuariAplicacioConfiguracio.usuariAplicacioConfiguracio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("usuariAplicacioConfiguracio.usuariAplicacioConfigID");
  }

  @InitBinder("usuariAplicacioConfiguracioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("usuariAplicacioConfiguracioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "usuariAplicacioConfiguracio.usuariAplicacioConfigID");
  }

  public UsuariAplicacioConfiguracioWebValidator getWebValidator() {
    return usuariAplicacioConfiguracioWebValidator;
  }


  public void setWebValidator(UsuariAplicacioConfiguracioWebValidator __val) {
    if (__val != null) {
      this.usuariAplicacioConfiguracioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de UsuariAplicacioConfiguracio
   */
  @RequestMapping(value = "/{usuariAplicacioConfigID}/cancel")
  public String cancelUsuariAplicacioConfiguracio(@PathVariable("usuariAplicacioConfigID") java.lang.Long usuariAplicacioConfigID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, usuariAplicacioConfigID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de UsuariAplicacioConfiguracio
   */
  @RequestMapping(value = "/cancel")
  public String cancelUsuariAplicacioConfiguracio(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
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


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (usuariAplicacioConfiguracioForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(ENTITATID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (UsuariAplicacioConfiguracio _item : list) {
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusOperacioFirma(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(TIPUSOPERACIOFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusOperacioFirma(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusOperacioFirma(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(TIPUSOPERACIOFIRMA)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(TIPUSOPERACIOFIRMA)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(TIPUSOPERACIOFIRMA)) {
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
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(TIPUSFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusFirmaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(TIPUSFIRMAID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(TIPUSFIRMAID)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(TIPUSFIRMAID)) {
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
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(ALGORISMEDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForAlgorismeDeFirmaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(ALGORISMEDEFIRMAID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(ALGORISMEDEFIRMAID)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(ALGORISMEDEFIRMAID)) {
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


  public List<StringKeyValue> getReferenceListForModeDeFirma(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(MODEDEFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForModeDeFirma(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForModeDeFirma(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(MODEDEFIRMA)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(MODEDEFIRMA)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(MODEDEFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForModeDeFirma(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForModeDeFirma(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForUsPoliticaDeFirma(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(USPOLITICADEFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForUsPoliticaDeFirma(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForUsPoliticaDeFirma(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(USPOLITICADEFIRMA)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(USPOLITICADEFIRMA)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(USPOLITICADEFIRMA)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForUsPoliticaDeFirma(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsPoliticaDeFirma(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }

  public List<StringKeyValue> getReferenceListForMotiuDelegacioID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(MOTIUDELEGACIOID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(MOTIUDELEGACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(MOTIUDELEGACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (UsuariAplicacioConfiguracio _item : list) {
        if(_item.getMotiuDelegacioID() == null) { continue; };
        _pkList.add(_item.getMotiuDelegacioID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForMotiuDelegacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForMotiuDelegacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForPoliticaTaulaFirmes(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(POLITICATAULAFIRMES)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPoliticaTaulaFirmes(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPoliticaTaulaFirmes(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(POLITICATAULAFIRMES)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(POLITICATAULAFIRMES)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(POLITICATAULAFIRMES)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForPoliticaTaulaFirmes(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPoliticaTaulaFirmes(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(POSICIOTAULAFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPosicioTaulaFirmesID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(POSICIOTAULAFIRMESID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(POSICIOTAULAFIRMESID)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(POSICIOTAULAFIRMESID)) {
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

  public List<StringKeyValue> getReferenceListForFirmatPerFormatID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(FIRMATPERFORMATID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(FIRMATPERFORMATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FIRMATPERFORMATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (UsuariAplicacioConfiguracio _item : list) {
        if(_item.getFirmatPerFormatID() == null) { continue; };
        _pkList.add(_item.getFirmatPerFormatID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForFirmatPerFormatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFirmatPerFormatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(POLITICASEGELLATDETEMPS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPoliticaSegellatDeTemps(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(POLITICASEGELLATDETEMPS)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(POLITICASEGELLATDETEMPS)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(POLITICASEGELLATDETEMPS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForPoliticaSegellatDeTemps(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPoliticaSegellatDeTemps(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForPluginSegellatID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(PLUGINSEGELLATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (usuariAplicacioConfiguracioForm.isReadOnlyField(PLUGINSEGELLATID)) {
      _where = PluginFields.PLUGINID.equal(usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().getPluginSegellatID());
    }
    return getReferenceListForPluginSegellatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginSegellatID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(PLUGINSEGELLATID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(PLUGINSEGELLATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINSEGELLATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (UsuariAplicacioConfiguracio _item : list) {
        if(_item.getPluginSegellatID() == null) { continue; };
        _pkList.add(_item.getPluginSegellatID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginSegellatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginSegellatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaServidorID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(PLUGINFIRMASERVIDORID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (usuariAplicacioConfiguracioForm.isReadOnlyField(PLUGINFIRMASERVIDORID)) {
      _where = PluginFields.PLUGINID.equal(usuariAplicacioConfiguracioForm.getUsuariAplicacioConfiguracio().getPluginFirmaServidorID());
    }
    return getReferenceListForPluginFirmaServidorID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaServidorID(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(PLUGINFIRMASERVIDORID)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(PLUGINFIRMASERVIDORID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINFIRMASERVIDORID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (UsuariAplicacioConfiguracio _item : list) {
        if(_item.getPluginFirmaServidorID() == null) { continue; };
        _pkList.add(_item.getPluginFirmaServidorID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginFirmaServidorID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaServidorID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public List<StringKeyValue> getReferenceListForUpgradeSignFormat(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioForm.isHiddenField(UPGRADESIGNFORMAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForUpgradeSignFormat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForUpgradeSignFormat(HttpServletRequest request,
       ModelAndView mav, UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm,
       List<UsuariAplicacioConfiguracio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariAplicacioConfiguracioFilterForm.isHiddenField(UPGRADESIGNFORMAT)
       && !usuariAplicacioConfiguracioFilterForm.isGroupByField(UPGRADESIGNFORMAT)
       && !usuariAplicacioConfiguracioFilterForm.isFilterByField(UPGRADESIGNFORMAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForUpgradeSignFormat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUpgradeSignFormat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    __tmp.add(new StringKeyValue("6" , "6"));
    __tmp.add(new StringKeyValue("7" , "7"));
    __tmp.add(new StringKeyValue("8" , "8"));
    __tmp.add(new StringKeyValue("9" , "9"));
    __tmp.add(new StringKeyValue("10" , "10"));
    __tmp.add(new StringKeyValue("11" , "11"));
    __tmp.add(new StringKeyValue("12" , "12"));
    __tmp.add(new StringKeyValue("13" , "13"));
    __tmp.add(new StringKeyValue("14" , "14"));
    __tmp.add(new StringKeyValue("15" , "15"));
    __tmp.add(new StringKeyValue("16" , "16"));
    __tmp.add(new StringKeyValue("17" , "17"));
    __tmp.add(new StringKeyValue("18" , "18"));
    __tmp.add(new StringKeyValue("19" , "19"));
    __tmp.add(new StringKeyValue("20" , "20"));
    __tmp.add(new StringKeyValue("21" , "21"));
    __tmp.add(new StringKeyValue("22" , "22"));
    __tmp.add(new StringKeyValue("23" , "23"));
    __tmp.add(new StringKeyValue("24" , "24"));
    __tmp.add(new StringKeyValue("25" , "25"));
    __tmp.add(new StringKeyValue("26" , "26"));
    __tmp.add(new StringKeyValue("27" , "27"));
    __tmp.add(new StringKeyValue("28" , "28"));
    __tmp.add(new StringKeyValue("29" , "29"));
    __tmp.add(new StringKeyValue("30" , "30"));
    __tmp.add(new StringKeyValue("31" , "31"));
    __tmp.add(new StringKeyValue("32" , "32"));
    __tmp.add(new StringKeyValue("33" , "33"));
    __tmp.add(new StringKeyValue("34" , "34"));
    __tmp.add(new StringKeyValue("35" , "35"));
    __tmp.add(new StringKeyValue("36" , "36"));
    __tmp.add(new StringKeyValue("37" , "37"));
    __tmp.add(new StringKeyValue("38" , "38"));
    __tmp.add(new StringKeyValue("39" , "39"));
    __tmp.add(new StringKeyValue("40" , "40"));
    __tmp.add(new StringKeyValue("41" , "41"));
    __tmp.add(new StringKeyValue("42" , "42"));
    __tmp.add(new StringKeyValue("43" , "43"));
    __tmp.add(new StringKeyValue("44" , "44"));
    __tmp.add(new StringKeyValue("45" , "45"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, UsuariAplicacioConfiguracioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, UsuariAplicacioConfiguracioFilterForm filterForm,  List<UsuariAplicacioConfiguracio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, UsuariAplicacioConfiguracioForm usuariAplicacioConfiguracioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long usuariAplicacioConfigID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariAplicacioConfigID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "usuariAplicacioConfiguracioFormWebDB";
  }

  public String getTileList() {
    return "usuariAplicacioConfiguracioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "UsuariAplicacioConfiguracio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public UsuariAplicacioConfiguracioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long usuariAplicacioConfigID) throws I18NException {
    return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioEjb.findByPrimaryKey(usuariAplicacioConfigID);
  }


  public UsuariAplicacioConfiguracioJPA create(HttpServletRequest request, UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio)
    throws I18NException, I18NValidationException {
    return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioEjb.create(usuariAplicacioConfiguracio);
  }


  public UsuariAplicacioConfiguracioJPA update(HttpServletRequest request, UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio)
    throws I18NException, I18NValidationException {
    return (UsuariAplicacioConfiguracioJPA) usuariAplicacioConfiguracioEjb.update(usuariAplicacioConfiguracio);
  }


  public void delete(HttpServletRequest request, UsuariAplicacioConfiguracio usuariAplicacioConfiguracio) throws I18NException {
    usuariAplicacioConfiguracioEjb.delete(usuariAplicacioConfiguracio);
  }

} // Final de Classe

