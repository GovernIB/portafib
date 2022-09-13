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
import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariEntitatForm;

import es.caib.portafib.back.validator.webdb.PluginFirmaWebPerUsuariEntitatWebValidator;

import es.caib.portafib.persistence.PluginFirmaWebPerUsuariEntitatJPA;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PluginFirmaWebPerUsuariEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/pluginFirmaWebPerUsuariEntitat")
@SessionAttributes(types = { PluginFirmaWebPerUsuariEntitatForm.class, PluginFirmaWebPerUsuariEntitatFilterForm.class })
public class PluginFirmaWebPerUsuariEntitatController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PluginFirmaWebPerUsuariEntitat, java.lang.Long> implements PluginFirmaWebPerUsuariEntitatFields {

  @EJB(mappedName = es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatService pluginFirmaWebPerUsuariEntitatEjb;

  @Autowired
  private PluginFirmaWebPerUsuariEntitatWebValidator pluginFirmaWebPerUsuariEntitatWebValidator;

  @Autowired
  protected PluginFirmaWebPerUsuariEntitatRefList pluginFirmaWebPerUsuariEntitatRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes PluginFirmaWebPerUsuariEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PluginFirmaWebPerUsuariEntitatFilterForm ff;
    ff = (PluginFirmaWebPerUsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PluginFirmaWebPerUsuariEntitat de forma paginada
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
    llistat(mav, request, getPluginFirmaWebPerUsuariEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public PluginFirmaWebPerUsuariEntitatFilterForm getPluginFirmaWebPerUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PluginFirmaWebPerUsuariEntitatFilterForm pluginFirmaWebPerUsuariEntitatFilterForm;
    pluginFirmaWebPerUsuariEntitatFilterForm = (PluginFirmaWebPerUsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pluginFirmaWebPerUsuariEntitatFilterForm == null) {
      pluginFirmaWebPerUsuariEntitatFilterForm = new PluginFirmaWebPerUsuariEntitatFilterForm();
      pluginFirmaWebPerUsuariEntitatFilterForm.setContexte(getContextWeb());
      pluginFirmaWebPerUsuariEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      pluginFirmaWebPerUsuariEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pluginFirmaWebPerUsuariEntitatFilterForm.setNou(true);
    } else {
      pluginFirmaWebPerUsuariEntitatFilterForm.setNou(false);
    }
    pluginFirmaWebPerUsuariEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return pluginFirmaWebPerUsuariEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PluginFirmaWebPerUsuariEntitat de forma paginada
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
      @ModelAttribute PluginFirmaWebPerUsuariEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPluginFirmaWebPerUsuariEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PluginFirmaWebPerUsuariEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PluginFirmaWebPerUsuariEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     PluginFirmaWebPerUsuariEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PluginFirmaWebPerUsuariEntitat> pluginFirmaWebPerUsuariEntitat = processarLlistat(pluginFirmaWebPerUsuariEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pluginFirmaWebPerUsuariEntitatItems", pluginFirmaWebPerUsuariEntitat);

    mav.addObject("pluginFirmaWebPerUsuariEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pluginFirmaWebPerUsuariEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pluginFirmaWebPerUsuariEntitat);

    return pluginFirmaWebPerUsuariEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PluginFirmaWebPerUsuariEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PluginFirmaWebPerUsuariEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field usuariEntitatID
    {
      _listSKV = getReferenceListForUsuariEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForUsuariEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIENTITATID, false);
      };
    }

    // Field pluginFirmaWebID
    {
      _listSKV = getReferenceListForPluginFirmaWebID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginFirmaWebID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINFIRMAWEBID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINFIRMAWEBID, false);
      };
    }

    // Field accio
    {
      _listSKV = getReferenceListForAccio(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForAccio(_tmp);
      if (filterForm.getGroupByFields().contains(ACCIO)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ACCIO, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PluginFirmaWebPerUsuariEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PluginFirmaWebPerUsuariEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PLUGINFIRMAWEBPERUSUARIENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(PLUGINFIRMAWEBID, filterForm.getMapOfPluginForPluginFirmaWebID());
    __mapping.put(ACCIO, filterForm.getMapOfValuesForAccio());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PluginFirmaWebPerUsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPluginFirmaWebPerUsuariEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm = getPluginFirmaWebPerUsuariEntitatForm(null, false, request, mav);
    mav.addObject("pluginFirmaWebPerUsuariEntitatForm" ,pluginFirmaWebPerUsuariEntitatForm);
    fillReferencesForForm(pluginFirmaWebPerUsuariEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PluginFirmaWebPerUsuariEntitatForm getPluginFirmaWebPerUsuariEntitatForm(PluginFirmaWebPerUsuariEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm;
    if(_jpa == null) {
      pluginFirmaWebPerUsuariEntitatForm = new PluginFirmaWebPerUsuariEntitatForm(new PluginFirmaWebPerUsuariEntitatJPA(), true);
    } else {
      pluginFirmaWebPerUsuariEntitatForm = new PluginFirmaWebPerUsuariEntitatForm(_jpa, false);
      pluginFirmaWebPerUsuariEntitatForm.setView(__isView);
    }
    pluginFirmaWebPerUsuariEntitatForm.setContexte(getContextWeb());
    pluginFirmaWebPerUsuariEntitatForm.setEntityNameCode(getEntityNameCode());
    pluginFirmaWebPerUsuariEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pluginFirmaWebPerUsuariEntitatForm;
  }

  public void fillReferencesForForm(PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pluginFirmaWebPerUsuariEntitatForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, pluginFirmaWebPerUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginFirmaWebPerUsuariEntitatForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginFirmaWebPerUsuariEntitatForm.getListOfPluginForPluginFirmaWebID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginFirmaWebID(request, mav, pluginFirmaWebPerUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginFirmaWebPerUsuariEntitatForm.setListOfPluginForPluginFirmaWebID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginFirmaWebPerUsuariEntitatForm.getListOfValuesForAccio() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAccio(request, mav, pluginFirmaWebPerUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginFirmaWebPerUsuariEntitatForm.setListOfValuesForAccio(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PluginFirmaWebPerUsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPluginFirmaWebPerUsuariEntitatPost(@ModelAttribute PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat = pluginFirmaWebPerUsuariEntitatForm.getPluginFirmaWebPerUsuariEntitat();

    try {
      preValidate(request, pluginFirmaWebPerUsuariEntitatForm, result);
      getWebValidator().validate(pluginFirmaWebPerUsuariEntitatForm, result);
      postValidate(request,pluginFirmaWebPerUsuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginFirmaWebPerUsuariEntitat = create(request, pluginFirmaWebPerUsuariEntitat);
        createMessageSuccess(request, "success.creation", pluginFirmaWebPerUsuariEntitat.getPluginFirmaWebPerUsrEntID());
        pluginFirmaWebPerUsuariEntitatForm.setPluginFirmaWebPerUsuariEntitat(pluginFirmaWebPerUsuariEntitat);
        return getRedirectWhenCreated(request, pluginFirmaWebPerUsuariEntitatForm);
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

  @RequestMapping(value = "/view/{pluginFirmaWebPerUsrEntID}", method = RequestMethod.GET)
  public ModelAndView veurePluginFirmaWebPerUsuariEntitatGet(@PathVariable("pluginFirmaWebPerUsrEntID") java.lang.Long pluginFirmaWebPerUsrEntID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginFirmaWebPerUsuariEntitatGet(pluginFirmaWebPerUsrEntID,
        request, response, true);
  }


  protected ModelAndView editAndViewPluginFirmaWebPerUsuariEntitatGet(@PathVariable("pluginFirmaWebPerUsrEntID") java.lang.Long pluginFirmaWebPerUsrEntID,
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
    PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat = findByPrimaryKey(request, pluginFirmaWebPerUsrEntID);

    if (pluginFirmaWebPerUsuariEntitat == null) {
      createMessageWarning(request, "error.notfound", pluginFirmaWebPerUsrEntID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pluginFirmaWebPerUsrEntID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm = getPluginFirmaWebPerUsuariEntitatForm(pluginFirmaWebPerUsuariEntitat, __isView, request, mav);
      pluginFirmaWebPerUsuariEntitatForm.setView(__isView);
      if(__isView) {
        pluginFirmaWebPerUsuariEntitatForm.setAllFieldsReadOnly(ALL_PLUGINFIRMAWEBPERUSUARIENTITAT_FIELDS);
        pluginFirmaWebPerUsuariEntitatForm.setSaveButtonVisible(false);
        pluginFirmaWebPerUsuariEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pluginFirmaWebPerUsuariEntitatForm, request, mav);
      mav.addObject("pluginFirmaWebPerUsuariEntitatForm", pluginFirmaWebPerUsuariEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PluginFirmaWebPerUsuariEntitat existent
   */
  @RequestMapping(value = "/{pluginFirmaWebPerUsrEntID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPluginFirmaWebPerUsuariEntitatGet(@PathVariable("pluginFirmaWebPerUsrEntID") java.lang.Long pluginFirmaWebPerUsrEntID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginFirmaWebPerUsuariEntitatGet(pluginFirmaWebPerUsrEntID,
        request, response, false);
  }



  /**
   * Editar un PluginFirmaWebPerUsuariEntitat existent
   */
  @RequestMapping(value = "/{pluginFirmaWebPerUsrEntID}/edit", method = RequestMethod.POST)
  public String editarPluginFirmaWebPerUsuariEntitatPost(@ModelAttribute PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat = pluginFirmaWebPerUsuariEntitatForm.getPluginFirmaWebPerUsuariEntitat();

    try {
      preValidate(request, pluginFirmaWebPerUsuariEntitatForm, result);
      getWebValidator().validate(pluginFirmaWebPerUsuariEntitatForm, result);
      postValidate(request, pluginFirmaWebPerUsuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginFirmaWebPerUsuariEntitat = update(request, pluginFirmaWebPerUsuariEntitat);
        createMessageSuccess(request, "success.modification", pluginFirmaWebPerUsuariEntitat.getPluginFirmaWebPerUsrEntID());
        status.setComplete();
        return getRedirectWhenModified(request, pluginFirmaWebPerUsuariEntitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pluginFirmaWebPerUsuariEntitat.getPluginFirmaWebPerUsrEntID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pluginFirmaWebPerUsuariEntitatForm, __e);
    }

  }


  /**
   * Eliminar un PluginFirmaWebPerUsuariEntitat existent
   */
  @RequestMapping(value = "/{pluginFirmaWebPerUsrEntID}/delete")
  public String eliminarPluginFirmaWebPerUsuariEntitat(@PathVariable("pluginFirmaWebPerUsrEntID") java.lang.Long pluginFirmaWebPerUsrEntID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PluginFirmaWebPerUsuariEntitat pluginFirmaWebPerUsuariEntitat = pluginFirmaWebPerUsuariEntitatEjb.findByPrimaryKey(pluginFirmaWebPerUsrEntID);
      if (pluginFirmaWebPerUsuariEntitat == null) {
        String __msg =createMessageError(request, "error.notfound", pluginFirmaWebPerUsrEntID);
        return getRedirectWhenDelete(request, pluginFirmaWebPerUsrEntID, new Exception(__msg));
      } else {
        delete(request, pluginFirmaWebPerUsuariEntitat);
        createMessageSuccess(request, "success.deleted", pluginFirmaWebPerUsrEntID);
        return getRedirectWhenDelete(request, pluginFirmaWebPerUsrEntID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pluginFirmaWebPerUsrEntID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pluginFirmaWebPerUsrEntID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PluginFirmaWebPerUsuariEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPluginFirmaWebPerUsuariEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __pluginFirmaWebPerUsrEntID, Throwable e) {
    java.lang.Long pluginFirmaWebPerUsrEntID = (java.lang.Long)__pluginFirmaWebPerUsrEntID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pluginFirmaWebPerUsrEntID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pluginFirmaWebPerUsrEntID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat";
  }

  public String getEntityNameCodePlural() {
    return "pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsrEntID");
  }

  @InitBinder("pluginFirmaWebPerUsuariEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pluginFirmaWebPerUsuariEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsrEntID");
  }

  public PluginFirmaWebPerUsuariEntitatWebValidator getWebValidator() {
    return pluginFirmaWebPerUsuariEntitatWebValidator;
  }


  public void setWebValidator(PluginFirmaWebPerUsuariEntitatWebValidator __val) {
    if (__val != null) {
      this.pluginFirmaWebPerUsuariEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PluginFirmaWebPerUsuariEntitat
   */
  @RequestMapping(value = "/{pluginFirmaWebPerUsrEntID}/cancel")
  public String cancelPluginFirmaWebPerUsuariEntitat(@PathVariable("pluginFirmaWebPerUsrEntID") java.lang.Long pluginFirmaWebPerUsrEntID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pluginFirmaWebPerUsrEntID);
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


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariEntitatForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginFirmaWebPerUsuariEntitatForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(pluginFirmaWebPerUsuariEntitatForm.getPluginFirmaWebPerUsuariEntitat().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariEntitatFilterForm pluginFirmaWebPerUsuariEntitatFilterForm,
       List<PluginFirmaWebPerUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariEntitatFilterForm.isHiddenField(USUARIENTITATID)
      && !pluginFirmaWebPerUsuariEntitatFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PluginFirmaWebPerUsuariEntitat _item : list) {
        _pkList.add(_item.getUsuariEntitatID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaWebID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariEntitatForm.isHiddenField(PLUGINFIRMAWEBID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginFirmaWebPerUsuariEntitatForm.isReadOnlyField(PLUGINFIRMAWEBID)) {
      _where = PluginFields.PLUGINID.equal(pluginFirmaWebPerUsuariEntitatForm.getPluginFirmaWebPerUsuariEntitat().getPluginFirmaWebID());
    }
    return getReferenceListForPluginFirmaWebID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaWebID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariEntitatFilterForm pluginFirmaWebPerUsuariEntitatFilterForm,
       List<PluginFirmaWebPerUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariEntitatFilterForm.isHiddenField(PLUGINFIRMAWEBID)
      && !pluginFirmaWebPerUsuariEntitatFilterForm.isGroupByField(PLUGINFIRMAWEBID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINFIRMAWEBID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PluginFirmaWebPerUsuariEntitat _item : list) {
        _pkList.add(_item.getPluginFirmaWebID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginFirmaWebID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaWebID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public List<StringKeyValue> getReferenceListForAccio(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariEntitatForm.isHiddenField(ACCIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForAccio(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForAccio(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariEntitatFilterForm pluginFirmaWebPerUsuariEntitatFilterForm,
       List<PluginFirmaWebPerUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariEntitatFilterForm.isHiddenField(ACCIO)
      && !pluginFirmaWebPerUsuariEntitatFilterForm.isGroupByField(ACCIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForAccio(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAccio(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("-1" , "-1"));
    __tmp.add(new StringKeyValue("1" , "1"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PluginFirmaWebPerUsuariEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PluginFirmaWebPerUsuariEntitatFilterForm filterForm,  List<PluginFirmaWebPerUsuariEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PluginFirmaWebPerUsuariEntitatForm pluginFirmaWebPerUsuariEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pluginFirmaWebPerUsrEntID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pluginFirmaWebPerUsrEntID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pluginFirmaWebPerUsuariEntitatFormWebDB";
  }

  public String getTileList() {
    return "pluginFirmaWebPerUsuariEntitatListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PluginFirmaWebPerUsuariEntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PluginFirmaWebPerUsuariEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pluginFirmaWebPerUsrEntID) throws I18NException {
    return (PluginFirmaWebPerUsuariEntitatJPA) pluginFirmaWebPerUsuariEntitatEjb.findByPrimaryKey(pluginFirmaWebPerUsrEntID);
  }


  public PluginFirmaWebPerUsuariEntitatJPA create(HttpServletRequest request, PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat)
    throws I18NException, I18NValidationException {
    return (PluginFirmaWebPerUsuariEntitatJPA) pluginFirmaWebPerUsuariEntitatEjb.create(pluginFirmaWebPerUsuariEntitat);
  }


  public PluginFirmaWebPerUsuariEntitatJPA update(HttpServletRequest request, PluginFirmaWebPerUsuariEntitatJPA pluginFirmaWebPerUsuariEntitat)
    throws I18NException, I18NValidationException {
    return (PluginFirmaWebPerUsuariEntitatJPA) pluginFirmaWebPerUsuariEntitatEjb.update(pluginFirmaWebPerUsuariEntitat);
  }


  public void delete(HttpServletRequest request, PluginFirmaWebPerUsuariEntitat pluginFirmaWebPerUsuariEntitat) throws I18NException {
    pluginFirmaWebPerUsuariEntitatEjb.delete(pluginFirmaWebPerUsuariEntitat);
  }

} // Final de Classe

