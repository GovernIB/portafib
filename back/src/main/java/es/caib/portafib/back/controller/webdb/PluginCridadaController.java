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
import es.caib.portafib.back.form.webdb.PluginCridadaForm;

import es.caib.portafib.back.validator.webdb.PluginCridadaWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.persistence.PluginCridadaJPA;
import es.caib.portafib.model.entity.PluginCridada;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PluginCridada
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/pluginCridada")
@SessionAttributes(types = { PluginCridadaForm.class, PluginCridadaFilterForm.class })
public class PluginCridadaController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<PluginCridada, java.lang.Long, PluginCridadaForm> implements PluginCridadaFields {

  @EJB(mappedName = es.caib.portafib.ejb.PluginCridadaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginCridadaService pluginCridadaEjb;

  @Autowired
  private PluginCridadaWebValidator pluginCridadaWebValidator;

  @Autowired
  protected PluginCridadaRefList pluginCridadaRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes PluginCridada
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PluginCridadaFilterForm ff;
    ff = (PluginCridadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PluginCridada de forma paginada
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
    llistat(mav, request, getPluginCridadaFilterForm(pagina, mav, request));
    return mav;
  }

  public PluginCridadaFilterForm getPluginCridadaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PluginCridadaFilterForm pluginCridadaFilterForm;
    pluginCridadaFilterForm = (PluginCridadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pluginCridadaFilterForm == null) {
      pluginCridadaFilterForm = new PluginCridadaFilterForm();
      pluginCridadaFilterForm.setContexte(getContextWeb());
      pluginCridadaFilterForm.setEntityNameCode(getEntityNameCode());
      pluginCridadaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pluginCridadaFilterForm.setNou(true);
    } else {
      pluginCridadaFilterForm.setNou(false);
    }
    pluginCridadaFilterForm.setPage(pagina == null ? 1 : pagina);
    return pluginCridadaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PluginCridada de forma paginada
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
      @ModelAttribute PluginCridadaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPluginCridadaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PluginCridada de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PluginCridada> llistat(ModelAndView mav, HttpServletRequest request,
     PluginCridadaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PluginCridada> pluginCridada = processarLlistat(pluginCridadaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pluginCridadaItems", pluginCridada);

    mav.addObject("pluginCridadaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pluginCridada, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pluginCridada);

    return pluginCridada;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PluginCridadaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PluginCridada> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field pluginID
    {
      _listSKV = getReferenceListForPluginID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PluginCridadaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PluginCridada> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PLUGINCRIDADA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    __mapping.put(PLUGINID, filterForm.getMapOfPluginForPluginID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PluginCridada
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPluginCridadaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PluginCridadaForm pluginCridadaForm = getPluginCridadaForm(null, false, request, mav);
    mav.addObject("pluginCridadaForm" ,pluginCridadaForm);
    fillReferencesForForm(pluginCridadaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PluginCridadaForm getPluginCridadaForm(PluginCridadaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PluginCridadaForm pluginCridadaForm;
    if(_jpa == null) {
      pluginCridadaForm = new PluginCridadaForm(new PluginCridadaJPA(), true);
    } else {
      pluginCridadaForm = new PluginCridadaForm(_jpa, false);
      pluginCridadaForm.setView(__isView);
    }
    pluginCridadaForm.setContexte(getContextWeb());
    pluginCridadaForm.setEntityNameCode(getEntityNameCode());
    pluginCridadaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pluginCridadaForm;
  }

  public void fillReferencesForForm(PluginCridadaForm pluginCridadaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pluginCridadaForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, pluginCridadaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginCridadaForm.setListOfEntitatForEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginCridadaForm.getListOfPluginForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, pluginCridadaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginCridadaForm.setListOfPluginForPluginID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PluginCridada
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPluginCridadaPost(@ModelAttribute PluginCridadaForm pluginCridadaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PluginCridadaJPA pluginCridada = pluginCridadaForm.getPluginCridada();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, pluginCridada, pluginCridadaForm); // FILE
      preValidate(request, pluginCridadaForm, result);
      getWebValidator().validate(pluginCridadaForm, result);
      postValidate(request,pluginCridadaForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginCridada = create(request, pluginCridada);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", pluginCridada.getPluginCridadaID());
        pluginCridadaForm.setPluginCridada(pluginCridada);
        return getRedirectWhenCreated(request, pluginCridadaForm);
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

  @RequestMapping(value = "/view/{pluginCridadaID}", method = RequestMethod.GET)
  public ModelAndView veurePluginCridadaGet(@PathVariable("pluginCridadaID") java.lang.Long pluginCridadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginCridadaGet(pluginCridadaID,
        request, response, true);
  }


  protected ModelAndView editAndViewPluginCridadaGet(@PathVariable("pluginCridadaID") java.lang.Long pluginCridadaID,
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
    PluginCridadaJPA pluginCridada = findByPrimaryKey(request, pluginCridadaID);

    if (pluginCridada == null) {
      createMessageWarning(request, "error.notfound", pluginCridadaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pluginCridadaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PluginCridadaForm pluginCridadaForm = getPluginCridadaForm(pluginCridada, __isView, request, mav);
      pluginCridadaForm.setView(__isView);
      if(__isView) {
        pluginCridadaForm.setAllFieldsReadOnly(ALL_PLUGINCRIDADA_FIELDS);
        pluginCridadaForm.setSaveButtonVisible(false);
        pluginCridadaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pluginCridadaForm, request, mav);
      mav.addObject("pluginCridadaForm", pluginCridadaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PluginCridada existent
   */
  @RequestMapping(value = "/{pluginCridadaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPluginCridadaGet(@PathVariable("pluginCridadaID") java.lang.Long pluginCridadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginCridadaGet(pluginCridadaID,
        request, response, false);
  }



  /**
   * Editar un PluginCridada existent
   */
  @RequestMapping(value = "/{pluginCridadaID}/edit", method = RequestMethod.POST)
  public String editarPluginCridadaPost(@ModelAttribute PluginCridadaForm pluginCridadaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PluginCridadaJPA pluginCridada = pluginCridadaForm.getPluginCridada();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, pluginCridada, pluginCridadaForm); // FILE
      preValidate(request, pluginCridadaForm, result);
      getWebValidator().validate(pluginCridadaForm, result);
      postValidate(request, pluginCridadaForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginCridada = update(request, pluginCridada);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", pluginCridada.getPluginCridadaID());
        status.setComplete();
        return getRedirectWhenModified(request, pluginCridadaForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pluginCridada.getPluginCridadaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pluginCridadaForm, __e);
    }

  }


  /**
   * Eliminar un PluginCridada existent
   */
  @RequestMapping(value = "/{pluginCridadaID}/delete")
  public String eliminarPluginCridada(@PathVariable("pluginCridadaID") java.lang.Long pluginCridadaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PluginCridada pluginCridada = this.findByPrimaryKey(request, pluginCridadaID);
      if (pluginCridada == null) {
        String __msg = createMessageError(request, "error.notfound", pluginCridadaID);
        return getRedirectWhenDelete(request, pluginCridadaID, new Exception(__msg));
      } else {
        delete(request, pluginCridada);
        createMessageSuccess(request, "success.deleted", pluginCridadaID);
        return getRedirectWhenDelete(request, pluginCridadaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pluginCridadaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pluginCridadaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PluginCridadaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPluginCridada(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __pluginCridadaID, Throwable e) {
    java.lang.Long pluginCridadaID = (java.lang.Long)__pluginCridadaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pluginCridadaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pluginCridadaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "pluginCridada.pluginCridada";
  }

  public String getEntityNameCodePlural() {
    return "pluginCridada.pluginCridada.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pluginCridada.pluginCridadaID");
  }

  @InitBinder("pluginCridadaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pluginCridadaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "pluginCridada.pluginCridadaID");
  }

  public PluginCridadaWebValidator getWebValidator() {
    return pluginCridadaWebValidator;
  }


  public void setWebValidator(PluginCridadaWebValidator __val) {
    if (__val != null) {
      this.pluginCridadaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PluginCridada
   */
  @RequestMapping(value = "/{pluginCridadaID}/cancel")
  public String cancelPluginCridada(@PathVariable("pluginCridadaID") java.lang.Long pluginCridadaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pluginCridadaID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, PluginCridada pluginCridada,
      PluginCridadaForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getParametresFitxerID(), form.isParametresFitxerIDDelete(),
        form.isNou()? null : pluginCridada.getParametresFitxer());
    ((PluginCridadaJPA)pluginCridada).setParametresFitxer(f);
    if (f != null) { 
      pluginCridada.setParametresFitxerID(f.getFitxerID());
    } else {
      pluginCridada.setParametresFitxerID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getRetornFitxerID(), form.isRetornFitxerIDDelete(),
        form.isNou()? null : pluginCridada.getRetornFitxer());
    ((PluginCridadaJPA)pluginCridada).setRetornFitxer(f);
    if (f != null) { 
      pluginCridada.setRetornFitxerID(f.getFitxerID());
    } else {
      pluginCridada.setRetornFitxerID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(PluginCridada pluginCridada) {
    deleteFile(pluginCridada.getParametresFitxerID());
    deleteFile(pluginCridada.getRetornFitxerID());
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
       ModelAndView mav, PluginCridadaForm pluginCridadaForm, Where where)  throws I18NException {
    if (pluginCridadaForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginCridadaForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(pluginCridadaForm.getPluginCridada().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginCridadaFilterForm pluginCridadaFilterForm,
       List<PluginCridada> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginCridadaFilterForm.isHiddenField(ENTITATID)
       && !pluginCridadaFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PluginCridada _item : list) {
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


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, PluginCridadaForm pluginCridadaForm, Where where)  throws I18NException {
    if (pluginCridadaForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginCridadaForm.isReadOnlyField(PLUGINID)) {
      _where = PluginFields.PLUGINID.equal(pluginCridadaForm.getPluginCridada().getPluginID());
    }
    return getReferenceListForPluginID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, PluginCridadaFilterForm pluginCridadaFilterForm,
       List<PluginCridada> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginCridadaFilterForm.isHiddenField(PLUGINID)
       && !pluginCridadaFilterForm.isGroupByField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PluginCridada _item : list) {
        _pkList.add(_item.getPluginID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PluginCridadaForm pluginCridadaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PluginCridadaForm pluginCridadaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PluginCridadaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PluginCridadaFilterForm filterForm,  List<PluginCridada> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PluginCridadaForm pluginCridadaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PluginCridadaForm pluginCridadaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pluginCridadaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pluginCridadaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pluginCridadaFormWebDB";
  }

  public String getTileList() {
    return "pluginCridadaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PluginCridada_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PluginCridadaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pluginCridadaID) throws I18NException {
    return (PluginCridadaJPA) pluginCridadaEjb.findByPrimaryKey(pluginCridadaID);
  }


  public PluginCridadaJPA create(HttpServletRequest request, PluginCridadaJPA pluginCridada)
    throws I18NException, I18NValidationException {
    return (PluginCridadaJPA) pluginCridadaEjb.create(pluginCridada);
  }


  public PluginCridadaJPA update(HttpServletRequest request, PluginCridadaJPA pluginCridada)
    throws I18NException, I18NValidationException {
    return (PluginCridadaJPA) pluginCridadaEjb.update(pluginCridada);
  }


  public void delete(HttpServletRequest request, PluginCridada pluginCridada) throws I18NException {
    pluginCridadaEjb.delete(pluginCridada);
  }

} // Final de Classe

