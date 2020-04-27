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
import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariAplicacioForm;

import es.caib.portafib.back.validator.webdb.PluginFirmaWebPerUsuariAplicacioWebValidator;

import es.caib.portafib.jpa.PluginFirmaWebPerUsuariAplicacioJPA;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PluginFirmaWebPerUsuariAplicacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/pluginFirmaWebPerUsuariAplicacio")
@SessionAttributes(types = { PluginFirmaWebPerUsuariAplicacioForm.class, PluginFirmaWebPerUsuariAplicacioFilterForm.class })
public class PluginFirmaWebPerUsuariAplicacioController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PluginFirmaWebPerUsuariAplicacio, java.lang.Long> implements PluginFirmaWebPerUsuariAplicacioFields {

  @EJB(mappedName = es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal pluginFirmaWebPerUsuariAplicacioEjb;

  @Autowired
  private PluginFirmaWebPerUsuariAplicacioWebValidator pluginFirmaWebPerUsuariAplicacioWebValidator;

  @Autowired
  protected PluginFirmaWebPerUsuariAplicacioRefList pluginFirmaWebPerUsuariAplicacioRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes PluginFirmaWebPerUsuariAplicacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PluginFirmaWebPerUsuariAplicacioFilterForm ff;
    ff = (PluginFirmaWebPerUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PluginFirmaWebPerUsuariAplicacio de forma paginada
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
    llistat(mav, request, getPluginFirmaWebPerUsuariAplicacioFilterForm(pagina, mav, request));
    return mav;
  }

  public PluginFirmaWebPerUsuariAplicacioFilterForm getPluginFirmaWebPerUsuariAplicacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PluginFirmaWebPerUsuariAplicacioFilterForm pluginFirmaWebPerUsuariAplicacioFilterForm;
    pluginFirmaWebPerUsuariAplicacioFilterForm = (PluginFirmaWebPerUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pluginFirmaWebPerUsuariAplicacioFilterForm == null) {
      pluginFirmaWebPerUsuariAplicacioFilterForm = new PluginFirmaWebPerUsuariAplicacioFilterForm();
      pluginFirmaWebPerUsuariAplicacioFilterForm.setContexte(getContextWeb());
      pluginFirmaWebPerUsuariAplicacioFilterForm.setEntityNameCode(getEntityNameCode());
      pluginFirmaWebPerUsuariAplicacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pluginFirmaWebPerUsuariAplicacioFilterForm.setNou(true);
    } else {
      pluginFirmaWebPerUsuariAplicacioFilterForm.setNou(false);
    }
    pluginFirmaWebPerUsuariAplicacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return pluginFirmaWebPerUsuariAplicacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PluginFirmaWebPerUsuariAplicacio de forma paginada
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
      @ModelAttribute PluginFirmaWebPerUsuariAplicacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPluginFirmaWebPerUsuariAplicacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PluginFirmaWebPerUsuariAplicacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PluginFirmaWebPerUsuariAplicacio> llistat(ModelAndView mav, HttpServletRequest request,
     PluginFirmaWebPerUsuariAplicacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PluginFirmaWebPerUsuariAplicacio> pluginFirmaWebPerUsuariAplicacio = processarLlistat(pluginFirmaWebPerUsuariAplicacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pluginFirmaWebPerUsuariAplicacioItems", pluginFirmaWebPerUsuariAplicacio);

    mav.addObject("pluginFirmaWebPerUsuariAplicacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pluginFirmaWebPerUsuariAplicacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pluginFirmaWebPerUsuariAplicacio);

    return pluginFirmaWebPerUsuariAplicacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PluginFirmaWebPerUsuariAplicacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PluginFirmaWebPerUsuariAplicacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field usuariAplicacioID
    {
      _listSKV = getReferenceListForUsuariAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioForUsuariAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOID, false);
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
    PluginFirmaWebPerUsuariAplicacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PluginFirmaWebPerUsuariAplicacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PLUGINFIRMAWEBPERUSUARIAPLICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    __mapping.put(PLUGINFIRMAWEBID, filterForm.getMapOfPluginForPluginFirmaWebID());
    __mapping.put(ACCIO, filterForm.getMapOfValuesForAccio());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PluginFirmaWebPerUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPluginFirmaWebPerUsuariAplicacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm = getPluginFirmaWebPerUsuariAplicacioForm(null, false, request, mav);
    mav.addObject("pluginFirmaWebPerUsuariAplicacioForm" ,pluginFirmaWebPerUsuariAplicacioForm);
    fillReferencesForForm(pluginFirmaWebPerUsuariAplicacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PluginFirmaWebPerUsuariAplicacioForm getPluginFirmaWebPerUsuariAplicacioForm(PluginFirmaWebPerUsuariAplicacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm;
    if(_jpa == null) {
      pluginFirmaWebPerUsuariAplicacioForm = new PluginFirmaWebPerUsuariAplicacioForm(new PluginFirmaWebPerUsuariAplicacioJPA(), true);
    } else {
      pluginFirmaWebPerUsuariAplicacioForm = new PluginFirmaWebPerUsuariAplicacioForm(_jpa, false);
      pluginFirmaWebPerUsuariAplicacioForm.setView(__isView);
    }
    pluginFirmaWebPerUsuariAplicacioForm.setContexte(getContextWeb());
    pluginFirmaWebPerUsuariAplicacioForm.setEntityNameCode(getEntityNameCode());
    pluginFirmaWebPerUsuariAplicacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pluginFirmaWebPerUsuariAplicacioForm;
  }

  public void fillReferencesForForm(PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pluginFirmaWebPerUsuariAplicacioForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, pluginFirmaWebPerUsuariAplicacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      pluginFirmaWebPerUsuariAplicacioForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginFirmaWebPerUsuariAplicacioForm.getListOfPluginForPluginFirmaWebID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginFirmaWebID(request, mav, pluginFirmaWebPerUsuariAplicacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      pluginFirmaWebPerUsuariAplicacioForm.setListOfPluginForPluginFirmaWebID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginFirmaWebPerUsuariAplicacioForm.getListOfValuesForAccio() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAccio(request, mav, pluginFirmaWebPerUsuariAplicacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      pluginFirmaWebPerUsuariAplicacioForm.setListOfValuesForAccio(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PluginFirmaWebPerUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPluginFirmaWebPerUsuariAplicacioPost(@ModelAttribute PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio = pluginFirmaWebPerUsuariAplicacioForm.getPluginFirmaWebPerUsuariAplicacio();

    try {
      preValidate(request, pluginFirmaWebPerUsuariAplicacioForm, result);
      getWebValidator().validate(pluginFirmaWebPerUsuariAplicacioForm, result);
      postValidate(request,pluginFirmaWebPerUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginFirmaWebPerUsuariAplicacio = create(request, pluginFirmaWebPerUsuariAplicacio);
        createMessageSuccess(request, "success.creation", pluginFirmaWebPerUsuariAplicacio.getPluginfirmawebperusrappid());
        pluginFirmaWebPerUsuariAplicacioForm.setPluginFirmaWebPerUsuariAplicacio(pluginFirmaWebPerUsuariAplicacio);
        return getRedirectWhenCreated(request, pluginFirmaWebPerUsuariAplicacioForm);
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

  @RequestMapping(value = "/view/{pluginfirmawebperusrappid}", method = RequestMethod.GET)
  public ModelAndView veurePluginFirmaWebPerUsuariAplicacioGet(@PathVariable("pluginfirmawebperusrappid") java.lang.Long pluginfirmawebperusrappid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginFirmaWebPerUsuariAplicacioGet(pluginfirmawebperusrappid,
        request, response, true);
  }


  protected ModelAndView editAndViewPluginFirmaWebPerUsuariAplicacioGet(@PathVariable("pluginfirmawebperusrappid") java.lang.Long pluginfirmawebperusrappid,
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
    PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio = findByPrimaryKey(request, pluginfirmawebperusrappid);

    if (pluginFirmaWebPerUsuariAplicacio == null) {
      createMessageWarning(request, "error.notfound", pluginfirmawebperusrappid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pluginfirmawebperusrappid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm = getPluginFirmaWebPerUsuariAplicacioForm(pluginFirmaWebPerUsuariAplicacio, __isView, request, mav);
      pluginFirmaWebPerUsuariAplicacioForm.setView(__isView);
      if(__isView) {
        pluginFirmaWebPerUsuariAplicacioForm.setAllFieldsReadOnly(ALL_PLUGINFIRMAWEBPERUSUARIAPLICACIO_FIELDS);
        pluginFirmaWebPerUsuariAplicacioForm.setSaveButtonVisible(false);
        pluginFirmaWebPerUsuariAplicacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pluginFirmaWebPerUsuariAplicacioForm, request, mav);
      mav.addObject("pluginFirmaWebPerUsuariAplicacioForm", pluginFirmaWebPerUsuariAplicacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PluginFirmaWebPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{pluginfirmawebperusrappid}/edit", method = RequestMethod.GET)
  public ModelAndView editarPluginFirmaWebPerUsuariAplicacioGet(@PathVariable("pluginfirmawebperusrappid") java.lang.Long pluginfirmawebperusrappid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginFirmaWebPerUsuariAplicacioGet(pluginfirmawebperusrappid,
        request, response, false);
  }



  /**
   * Editar un PluginFirmaWebPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{pluginfirmawebperusrappid}/edit", method = RequestMethod.POST)
  public String editarPluginFirmaWebPerUsuariAplicacioPost(@ModelAttribute @Valid PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio = pluginFirmaWebPerUsuariAplicacioForm.getPluginFirmaWebPerUsuariAplicacio();

    try {
      preValidate(request, pluginFirmaWebPerUsuariAplicacioForm, result);
      getWebValidator().validate(pluginFirmaWebPerUsuariAplicacio, result);
      postValidate(request, pluginFirmaWebPerUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginFirmaWebPerUsuariAplicacio = update(request, pluginFirmaWebPerUsuariAplicacio);
        createMessageSuccess(request, "success.modification", pluginFirmaWebPerUsuariAplicacio.getPluginfirmawebperusrappid());
        status.setComplete();
        return getRedirectWhenModified(request, pluginFirmaWebPerUsuariAplicacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pluginFirmaWebPerUsuariAplicacio.getPluginfirmawebperusrappid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pluginFirmaWebPerUsuariAplicacioForm, __e);
    }

  }


  /**
   * Eliminar un PluginFirmaWebPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{pluginfirmawebperusrappid}/delete")
  public String eliminarPluginFirmaWebPerUsuariAplicacio(@PathVariable("pluginfirmawebperusrappid") java.lang.Long pluginfirmawebperusrappid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PluginFirmaWebPerUsuariAplicacio pluginFirmaWebPerUsuariAplicacio = pluginFirmaWebPerUsuariAplicacioEjb.findByPrimaryKey(pluginfirmawebperusrappid);
      if (pluginFirmaWebPerUsuariAplicacio == null) {
        String __msg =createMessageError(request, "error.notfound", pluginfirmawebperusrappid);
        return getRedirectWhenDelete(request, pluginfirmawebperusrappid, new Exception(__msg));
      } else {
        delete(request, pluginFirmaWebPerUsuariAplicacio);
        createMessageSuccess(request, "success.deleted", pluginfirmawebperusrappid);
        return getRedirectWhenDelete(request, pluginfirmawebperusrappid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pluginfirmawebperusrappid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pluginfirmawebperusrappid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PluginFirmaWebPerUsuariAplicacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPluginFirmaWebPerUsuariAplicacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __pluginfirmawebperusrappid, Throwable e) {
    java.lang.Long pluginfirmawebperusrappid = (java.lang.Long)__pluginfirmawebperusrappid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pluginfirmawebperusrappid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pluginfirmawebperusrappid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio";
  }

  public String getEntityNameCodePlural() {
    return "pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pluginFirmaWebPerUsuariAplicacio.pluginfirmawebperusrappid");
  }

  @InitBinder("pluginFirmaWebPerUsuariAplicacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pluginFirmaWebPerUsuariAplicacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("pluginfirmawebperusrappid");

  }

  public PluginFirmaWebPerUsuariAplicacioWebValidator getWebValidator() {
    return pluginFirmaWebPerUsuariAplicacioWebValidator;
  }


  public void setWebValidator(PluginFirmaWebPerUsuariAplicacioWebValidator __val) {
    if (__val != null) {
      this.pluginFirmaWebPerUsuariAplicacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PluginFirmaWebPerUsuariAplicacio
   */
  @RequestMapping(value = "/{pluginfirmawebperusrappid}/cancel")
  public String cancelPluginFirmaWebPerUsuariAplicacio(@PathVariable("pluginfirmawebperusrappid") java.lang.Long pluginfirmawebperusrappid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pluginfirmawebperusrappid);
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


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariAplicacioForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginFirmaWebPerUsuariAplicacioForm.isReadOnlyField(USUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(pluginFirmaWebPerUsuariAplicacioForm.getPluginFirmaWebPerUsuariAplicacio().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariAplicacioFilterForm pluginFirmaWebPerUsuariAplicacioFilterForm,
       List<PluginFirmaWebPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariAplicacioFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !pluginFirmaWebPerUsuariAplicacioFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PluginFirmaWebPerUsuariAplicacio _item : list) {
        _pkList.add(_item.getUsuariAplicacioID());
        }
        _w = UsuariAplicacioFields.USUARIAPLICACIOID.in(_pkList);
      }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioRefList.getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID, where );
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaWebID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariAplicacioForm.isHiddenField(PLUGINFIRMAWEBID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginFirmaWebPerUsuariAplicacioForm.isReadOnlyField(PLUGINFIRMAWEBID)) {
      _where = PluginFields.PLUGINID.equal(pluginFirmaWebPerUsuariAplicacioForm.getPluginFirmaWebPerUsuariAplicacio().getPluginFirmaWebID());
    }
    return getReferenceListForPluginFirmaWebID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginFirmaWebID(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariAplicacioFilterForm pluginFirmaWebPerUsuariAplicacioFilterForm,
       List<PluginFirmaWebPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariAplicacioFilterForm.isHiddenField(PLUGINFIRMAWEBID)
      && !pluginFirmaWebPerUsuariAplicacioFilterForm.isGroupByField(PLUGINFIRMAWEBID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINFIRMAWEBID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PluginFirmaWebPerUsuariAplicacio _item : list) {
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
       ModelAndView mav, PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariAplicacioForm.isHiddenField(ACCIO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForAccio(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForAccio(HttpServletRequest request,
       ModelAndView mav, PluginFirmaWebPerUsuariAplicacioFilterForm pluginFirmaWebPerUsuariAplicacioFilterForm,
       List<PluginFirmaWebPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginFirmaWebPerUsuariAplicacioFilterForm.isHiddenField(ACCIO)
      && !pluginFirmaWebPerUsuariAplicacioFilterForm.isGroupByField(ACCIO)) {
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


  public void preValidate(HttpServletRequest request,PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PluginFirmaWebPerUsuariAplicacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PluginFirmaWebPerUsuariAplicacioFilterForm filterForm,  List<PluginFirmaWebPerUsuariAplicacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PluginFirmaWebPerUsuariAplicacioForm pluginFirmaWebPerUsuariAplicacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pluginfirmawebperusrappid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pluginfirmawebperusrappid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pluginFirmaWebPerUsuariAplicacioFormWebDB";
  }

  public String getTileList() {
    return "pluginFirmaWebPerUsuariAplicacioListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PluginFirmaWebPerUsuariAplicacioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PluginFirmaWebPerUsuariAplicacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pluginfirmawebperusrappid) throws I18NException {
    return (PluginFirmaWebPerUsuariAplicacioJPA) pluginFirmaWebPerUsuariAplicacioEjb.findByPrimaryKey(pluginfirmawebperusrappid);
  }


  public PluginFirmaWebPerUsuariAplicacioJPA create(HttpServletRequest request, PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio)
    throws Exception,I18NException, I18NValidationException {
    return (PluginFirmaWebPerUsuariAplicacioJPA) pluginFirmaWebPerUsuariAplicacioEjb.create(pluginFirmaWebPerUsuariAplicacio);
  }


  public PluginFirmaWebPerUsuariAplicacioJPA update(HttpServletRequest request, PluginFirmaWebPerUsuariAplicacioJPA pluginFirmaWebPerUsuariAplicacio)
    throws Exception,I18NException, I18NValidationException {
    return (PluginFirmaWebPerUsuariAplicacioJPA) pluginFirmaWebPerUsuariAplicacioEjb.update(pluginFirmaWebPerUsuariAplicacio);
  }


  public void delete(HttpServletRequest request, PluginFirmaWebPerUsuariAplicacio pluginFirmaWebPerUsuariAplicacio) throws Exception,I18NException {
    pluginFirmaWebPerUsuariAplicacioEjb.delete(pluginFirmaWebPerUsuariAplicacio);
  }

} // Final de Classe

