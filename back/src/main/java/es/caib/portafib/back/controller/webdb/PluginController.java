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
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.form.webdb.PluginForm;

import es.caib.portafib.back.validator.webdb.PluginWebValidator;

import es.caib.portafib.jpa.PluginJPA;
import es.caib.portafib.model.entity.Plugin;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Plugin
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/plugin")
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class PluginController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<Plugin, java.lang.Long> implements PluginFields {

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @Autowired
  private PluginWebValidator pluginWebValidator;

  @Autowired
  protected PluginRefList pluginRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Plugin
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PluginFilterForm ff;
    ff = (PluginFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Plugin de forma paginada
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
    llistat(mav, request, getPluginFilterForm(pagina, mav, request));
    return mav;
  }

  public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PluginFilterForm pluginFilterForm;
    pluginFilterForm = (PluginFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pluginFilterForm == null) {
      pluginFilterForm = new PluginFilterForm();
      pluginFilterForm.setContexte(getContextWeb());
      pluginFilterForm.setEntityNameCode(getEntityNameCode());
      pluginFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pluginFilterForm.setNou(true);
    } else {
      pluginFilterForm.setNou(false);
    }
    pluginFilterForm.setPage(pagina == null ? 1 : pagina);
    return pluginFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Plugin de forma paginada
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
      @ModelAttribute PluginFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPluginFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Plugin de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Plugin> llistat(ModelAndView mav, HttpServletRequest request,
     PluginFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Plugin> plugin = processarLlistat(pluginEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pluginItems", plugin);

    mav.addObject("pluginFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, plugin, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, plugin);

    return plugin;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PluginFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Plugin> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field nomID
    {
      _listSKV = getReferenceListForNomID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForNomID(_tmp);
      if (filterForm.getGroupByFields().contains(NOMID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOMID, false);
      };
    }

    // Field descripcioCurtaID
    {
      _listSKV = getReferenceListForDescripcioCurtaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForDescripcioCurtaID(_tmp);
      if (filterForm.getGroupByFields().contains(DESCRIPCIOCURTAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DESCRIPCIOCURTAID, false);
      };
    }

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIU);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PluginFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Plugin> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PLUGIN_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    __mapping.put(DESCRIPCIOCURTAID, filterForm.getMapOfTraduccioForDescripcioCurtaID());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Plugin
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPluginGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PluginForm pluginForm = getPluginForm(null, false, request, mav);
    
    if (pluginForm.getPlugin().getNom() == null){
      es.caib.portafib.jpa.TraduccioJPA trad = new es.caib.portafib.jpa.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : pluginForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.jpa.TraduccioMapJPA());
      }
      pluginForm.getPlugin().setNom(trad);
    }

    
    if (pluginForm.getPlugin().getDescripcioCurta() == null){
      es.caib.portafib.jpa.TraduccioJPA trad = new es.caib.portafib.jpa.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : pluginForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.jpa.TraduccioMapJPA());
      }
      pluginForm.getPlugin().setDescripcioCurta(trad);
    }

    mav.addObject("pluginForm" ,pluginForm);
    fillReferencesForForm(pluginForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PluginForm getPluginForm(PluginJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PluginForm pluginForm;
    if(_jpa == null) {
      pluginForm = new PluginForm(new PluginJPA(), true);
    } else {
      pluginForm = new PluginForm(_jpa, false);
      pluginForm.setView(__isView);
    }
    pluginForm.setContexte(getContextWeb());
    pluginForm.setEntityNameCode(getEntityNameCode());
    pluginForm.setEntityNameCodePlural(getEntityNameCodePlural());
    pluginForm.setIdiomesTraduccio(getIdiomesSuportats());
    return pluginForm;
  }

  public void fillReferencesForForm(PluginForm pluginForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pluginForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, pluginForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      pluginForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, pluginForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      pluginForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }


  public List<es.caib.portafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.portafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.portafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Plugin
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPluginPost(@ModelAttribute PluginForm pluginForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PluginJPA plugin = pluginForm.getPlugin();

    try {
      preValidate(request, pluginForm, result);
      getWebValidator().validate(pluginForm, result);
      postValidate(request,pluginForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        plugin = create(request, plugin);
        createMessageSuccess(request, "success.creation", plugin.getPluginID());
        pluginForm.setPlugin(plugin);
        return getRedirectWhenCreated(request, pluginForm);
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

  @RequestMapping(value = "/view/{pluginID}", method = RequestMethod.GET)
  public ModelAndView veurePluginGet(@PathVariable("pluginID") java.lang.Long pluginID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginGet(pluginID,
        request, response, true);
  }


  protected ModelAndView editAndViewPluginGet(@PathVariable("pluginID") java.lang.Long pluginID,
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
    PluginJPA plugin = findByPrimaryKey(request, pluginID);

    if (plugin == null) {
      createMessageWarning(request, "error.notfound", pluginID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pluginID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PluginForm pluginForm = getPluginForm(plugin, __isView, request, mav);
      pluginForm.setView(__isView);
      if(__isView) {
        pluginForm.setAllFieldsReadOnly(ALL_PLUGIN_FIELDS);
        pluginForm.setSaveButtonVisible(false);
        pluginForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pluginForm, request, mav);
      mav.addObject("pluginForm", pluginForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Plugin existent
   */
  @RequestMapping(value = "/{pluginID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPluginGet(@PathVariable("pluginID") java.lang.Long pluginID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginGet(pluginID,
        request, response, false);
  }



  /**
   * Editar un Plugin existent
   */
  @RequestMapping(value = "/{pluginID}/edit", method = RequestMethod.POST)
  public String editarPluginPost(@ModelAttribute @Valid PluginForm pluginForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PluginJPA plugin = pluginForm.getPlugin();

    try {
      preValidate(request, pluginForm, result);
      getWebValidator().validate(plugin, result);
      postValidate(request, pluginForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        plugin = update(request, plugin);
        createMessageSuccess(request, "success.modification", plugin.getPluginID());
        status.setComplete();
        return getRedirectWhenModified(request, pluginForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          plugin.getPluginID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pluginForm, __e);
    }

  }


  /**
   * Eliminar un Plugin existent
   */
  @RequestMapping(value = "/{pluginID}/delete")
  public String eliminarPlugin(@PathVariable("pluginID") java.lang.Long pluginID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Plugin plugin = pluginEjb.findByPrimaryKey(pluginID);
      if (plugin == null) {
        String __msg =createMessageError(request, "error.notfound", pluginID);
        return getRedirectWhenDelete(request, pluginID, new Exception(__msg));
      } else {
        delete(request, plugin);
        createMessageSuccess(request, "success.deleted", pluginID);
        return getRedirectWhenDelete(request, pluginID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pluginID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pluginID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PluginFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPlugin(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return new java.lang.Long(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __pluginID, Throwable e) {
    java.lang.Long pluginID = (java.lang.Long)__pluginID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pluginID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pluginID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "plugin.plugin";
  }

  public String getEntityNameCodePlural() {
    return "plugin.plugin.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("plugin.pluginID");
  }

  @InitBinder("pluginFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pluginForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("pluginID");

  }

  public PluginWebValidator getWebValidator() {
    return pluginWebValidator;
  }


  public void setWebValidator(PluginWebValidator __val) {
    if (__val != null) {
      this.pluginWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Plugin
   */
  @RequestMapping(value = "/{pluginID}/cancel")
  public String cancelPlugin(@PathVariable("pluginID") java.lang.Long pluginID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pluginID);
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

  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, PluginFilterForm pluginFilterForm,
       List<Plugin> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFilterForm.isHiddenField(NOMID)
      && !pluginFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Plugin _item : list) {
        _pkList.add(_item.getNomID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForNomID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForDescripcioCurtaID(HttpServletRequest request,
       ModelAndView mav, PluginFilterForm pluginFilterForm,
       List<Plugin> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFilterForm.isHiddenField(DESCRIPCIOCURTAID)
      && !pluginFilterForm.isGroupByField(DESCRIPCIOCURTAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESCRIPCIOCURTAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Plugin _item : list) {
        _pkList.add(_item.getDescripcioCurtaID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForDescripcioCurtaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDescripcioCurtaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, PluginForm pluginForm, Where where)  throws I18NException {
    if (pluginForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, PluginFilterForm pluginFilterForm,
       List<Plugin> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFilterForm.isHiddenField(TIPUS)
      && !pluginFilterForm.isGroupByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginForm pluginForm, Where where)  throws I18NException {
    if (pluginForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(pluginForm.getPlugin().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginFilterForm pluginFilterForm,
       List<Plugin> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFilterForm.isHiddenField(ENTITATID)
      && !pluginFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (Plugin _item : list) {
        if(_item.getEntitatID() == null) { continue; };
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


  public void preValidate(HttpServletRequest request,PluginForm pluginForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PluginForm pluginForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PluginFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PluginFilterForm filterForm,  List<Plugin> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PluginForm pluginForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PluginForm pluginForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pluginID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pluginID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pluginFormWebDB";
  }

  public String getTileList() {
    return "pluginListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PluginWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PluginJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pluginID) throws I18NException {
    return (PluginJPA) pluginEjb.findByPrimaryKey(pluginID);
  }


  public PluginJPA create(HttpServletRequest request, PluginJPA plugin)
    throws Exception,I18NException, I18NValidationException {
    return (PluginJPA) pluginEjb.create(plugin);
  }


  public PluginJPA update(HttpServletRequest request, PluginJPA plugin)
    throws Exception,I18NException, I18NValidationException {
    return (PluginJPA) pluginEjb.update(plugin);
  }


  public void delete(HttpServletRequest request, Plugin plugin) throws Exception,I18NException {
    pluginEjb.delete(plugin);
  }

} // Final de Classe

