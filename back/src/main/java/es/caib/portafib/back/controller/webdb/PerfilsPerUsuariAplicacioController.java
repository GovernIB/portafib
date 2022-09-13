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
import es.caib.portafib.back.form.webdb.PerfilsPerUsuariAplicacioForm;

import es.caib.portafib.back.validator.webdb.PerfilsPerUsuariAplicacioWebValidator;

import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PerfilsPerUsuariAplicacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/perfilsPerUsuariAplicacio")
@SessionAttributes(types = { PerfilsPerUsuariAplicacioForm.class, PerfilsPerUsuariAplicacioFilterForm.class })
public class PerfilsPerUsuariAplicacioController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PerfilsPerUsuariAplicacio, java.lang.Long> implements PerfilsPerUsuariAplicacioFields {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

  @Autowired
  private PerfilsPerUsuariAplicacioWebValidator perfilsPerUsuariAplicacioWebValidator;

  @Autowired
  protected PerfilsPerUsuariAplicacioRefList perfilsPerUsuariAplicacioRefList;

  // References 
  @Autowired
  protected PerfilDeFirmaRefList perfilDeFirmaRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  /**
   * Llistat de totes PerfilsPerUsuariAplicacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PerfilsPerUsuariAplicacioFilterForm ff;
    ff = (PerfilsPerUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PerfilsPerUsuariAplicacio de forma paginada
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
    llistat(mav, request, getPerfilsPerUsuariAplicacioFilterForm(pagina, mav, request));
    return mav;
  }

  public PerfilsPerUsuariAplicacioFilterForm getPerfilsPerUsuariAplicacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PerfilsPerUsuariAplicacioFilterForm perfilsPerUsuariAplicacioFilterForm;
    perfilsPerUsuariAplicacioFilterForm = (PerfilsPerUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(perfilsPerUsuariAplicacioFilterForm == null) {
      perfilsPerUsuariAplicacioFilterForm = new PerfilsPerUsuariAplicacioFilterForm();
      perfilsPerUsuariAplicacioFilterForm.setContexte(getContextWeb());
      perfilsPerUsuariAplicacioFilterForm.setEntityNameCode(getEntityNameCode());
      perfilsPerUsuariAplicacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      perfilsPerUsuariAplicacioFilterForm.setNou(true);
    } else {
      perfilsPerUsuariAplicacioFilterForm.setNou(false);
    }
    perfilsPerUsuariAplicacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return perfilsPerUsuariAplicacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PerfilsPerUsuariAplicacio de forma paginada
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
      @ModelAttribute PerfilsPerUsuariAplicacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPerfilsPerUsuariAplicacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PerfilsPerUsuariAplicacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PerfilsPerUsuariAplicacio> llistat(ModelAndView mav, HttpServletRequest request,
     PerfilsPerUsuariAplicacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PerfilsPerUsuariAplicacio> perfilsPerUsuariAplicacio = processarLlistat(perfilsPerUsuariAplicacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("perfilsPerUsuariAplicacioItems", perfilsPerUsuariAplicacio);

    mav.addObject("perfilsPerUsuariAplicacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, perfilsPerUsuariAplicacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, perfilsPerUsuariAplicacio);

    return perfilsPerUsuariAplicacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PerfilsPerUsuariAplicacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PerfilsPerUsuariAplicacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field perfilDeFirmaID
    {
      _listSKV = getReferenceListForPerfilDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPerfilDeFirmaForPerfilDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(PERFILDEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PERFILDEFIRMAID, false);
      };
    }

    // Field usuariAplicacioID
    {
      _listSKV = getReferenceListForUsuariAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioForUsuariAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PerfilsPerUsuariAplicacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PerfilsPerUsuariAplicacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PERFILSPERUSUARIAPLICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PERFILDEFIRMAID, filterForm.getMapOfPerfilDeFirmaForPerfilDeFirmaID());
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PerfilsPerUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPerfilsPerUsuariAplicacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm = getPerfilsPerUsuariAplicacioForm(null, false, request, mav);
    mav.addObject("perfilsPerUsuariAplicacioForm" ,perfilsPerUsuariAplicacioForm);
    fillReferencesForForm(perfilsPerUsuariAplicacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PerfilsPerUsuariAplicacioForm getPerfilsPerUsuariAplicacioForm(PerfilsPerUsuariAplicacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm;
    if(_jpa == null) {
      perfilsPerUsuariAplicacioForm = new PerfilsPerUsuariAplicacioForm(new PerfilsPerUsuariAplicacioJPA(), true);
    } else {
      perfilsPerUsuariAplicacioForm = new PerfilsPerUsuariAplicacioForm(_jpa, false);
      perfilsPerUsuariAplicacioForm.setView(__isView);
    }
    perfilsPerUsuariAplicacioForm.setContexte(getContextWeb());
    perfilsPerUsuariAplicacioForm.setEntityNameCode(getEntityNameCode());
    perfilsPerUsuariAplicacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return perfilsPerUsuariAplicacioForm;
  }

  public void fillReferencesForForm(PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (perfilsPerUsuariAplicacioForm.getListOfPerfilDeFirmaForPerfilDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPerfilDeFirmaID(request, mav, perfilsPerUsuariAplicacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilsPerUsuariAplicacioForm.setListOfPerfilDeFirmaForPerfilDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (perfilsPerUsuariAplicacioForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, perfilsPerUsuariAplicacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilsPerUsuariAplicacioForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PerfilsPerUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPerfilsPerUsuariAplicacioPost(@ModelAttribute PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio = perfilsPerUsuariAplicacioForm.getPerfilsPerUsuariAplicacio();

    try {
      preValidate(request, perfilsPerUsuariAplicacioForm, result);
      getWebValidator().validate(perfilsPerUsuariAplicacioForm, result);
      postValidate(request,perfilsPerUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        perfilsPerUsuariAplicacio = create(request, perfilsPerUsuariAplicacio);
        createMessageSuccess(request, "success.creation", perfilsPerUsuariAplicacio.getPerfilsPerUsrAppID());
        perfilsPerUsuariAplicacioForm.setPerfilsPerUsuariAplicacio(perfilsPerUsuariAplicacio);
        return getRedirectWhenCreated(request, perfilsPerUsuariAplicacioForm);
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

  @RequestMapping(value = "/view/{perfilsPerUsrAppID}", method = RequestMethod.GET)
  public ModelAndView veurePerfilsPerUsuariAplicacioGet(@PathVariable("perfilsPerUsrAppID") java.lang.Long perfilsPerUsrAppID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPerfilsPerUsuariAplicacioGet(perfilsPerUsrAppID,
        request, response, true);
  }


  protected ModelAndView editAndViewPerfilsPerUsuariAplicacioGet(@PathVariable("perfilsPerUsrAppID") java.lang.Long perfilsPerUsrAppID,
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
    PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio = findByPrimaryKey(request, perfilsPerUsrAppID);

    if (perfilsPerUsuariAplicacio == null) {
      createMessageWarning(request, "error.notfound", perfilsPerUsrAppID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, perfilsPerUsrAppID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm = getPerfilsPerUsuariAplicacioForm(perfilsPerUsuariAplicacio, __isView, request, mav);
      perfilsPerUsuariAplicacioForm.setView(__isView);
      if(__isView) {
        perfilsPerUsuariAplicacioForm.setAllFieldsReadOnly(ALL_PERFILSPERUSUARIAPLICACIO_FIELDS);
        perfilsPerUsuariAplicacioForm.setSaveButtonVisible(false);
        perfilsPerUsuariAplicacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(perfilsPerUsuariAplicacioForm, request, mav);
      mav.addObject("perfilsPerUsuariAplicacioForm", perfilsPerUsuariAplicacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PerfilsPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{perfilsPerUsrAppID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPerfilsPerUsuariAplicacioGet(@PathVariable("perfilsPerUsrAppID") java.lang.Long perfilsPerUsrAppID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPerfilsPerUsuariAplicacioGet(perfilsPerUsrAppID,
        request, response, false);
  }



  /**
   * Editar un PerfilsPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{perfilsPerUsrAppID}/edit", method = RequestMethod.POST)
  public String editarPerfilsPerUsuariAplicacioPost(@ModelAttribute PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio = perfilsPerUsuariAplicacioForm.getPerfilsPerUsuariAplicacio();

    try {
      preValidate(request, perfilsPerUsuariAplicacioForm, result);
      getWebValidator().validate(perfilsPerUsuariAplicacioForm, result);
      postValidate(request, perfilsPerUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        perfilsPerUsuariAplicacio = update(request, perfilsPerUsuariAplicacio);
        createMessageSuccess(request, "success.modification", perfilsPerUsuariAplicacio.getPerfilsPerUsrAppID());
        status.setComplete();
        return getRedirectWhenModified(request, perfilsPerUsuariAplicacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          perfilsPerUsuariAplicacio.getPerfilsPerUsrAppID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, perfilsPerUsuariAplicacioForm, __e);
    }

  }


  /**
   * Eliminar un PerfilsPerUsuariAplicacio existent
   */
  @RequestMapping(value = "/{perfilsPerUsrAppID}/delete")
  public String eliminarPerfilsPerUsuariAplicacio(@PathVariable("perfilsPerUsrAppID") java.lang.Long perfilsPerUsrAppID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PerfilsPerUsuariAplicacio perfilsPerUsuariAplicacio = perfilsPerUsuariAplicacioEjb.findByPrimaryKey(perfilsPerUsrAppID);
      if (perfilsPerUsuariAplicacio == null) {
        String __msg =createMessageError(request, "error.notfound", perfilsPerUsrAppID);
        return getRedirectWhenDelete(request, perfilsPerUsrAppID, new Exception(__msg));
      } else {
        delete(request, perfilsPerUsuariAplicacio);
        createMessageSuccess(request, "success.deleted", perfilsPerUsrAppID);
        return getRedirectWhenDelete(request, perfilsPerUsrAppID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", perfilsPerUsrAppID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, perfilsPerUsrAppID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PerfilsPerUsuariAplicacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPerfilsPerUsuariAplicacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __perfilsPerUsrAppID, Throwable e) {
    java.lang.Long perfilsPerUsrAppID = (java.lang.Long)__perfilsPerUsrAppID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (perfilsPerUsrAppID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(perfilsPerUsrAppID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio";
  }

  public String getEntityNameCodePlural() {
    return "perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("perfilsPerUsuariAplicacio.perfilsPerUsrAppID");
  }

  @InitBinder("perfilsPerUsuariAplicacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("perfilsPerUsuariAplicacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "perfilsPerUsuariAplicacio.perfilsPerUsrAppID");
  }

  public PerfilsPerUsuariAplicacioWebValidator getWebValidator() {
    return perfilsPerUsuariAplicacioWebValidator;
  }


  public void setWebValidator(PerfilsPerUsuariAplicacioWebValidator __val) {
    if (__val != null) {
      this.perfilsPerUsuariAplicacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PerfilsPerUsuariAplicacio
   */
  @RequestMapping(value = "/{perfilsPerUsrAppID}/cancel")
  public String cancelPerfilsPerUsuariAplicacio(@PathVariable("perfilsPerUsrAppID") java.lang.Long perfilsPerUsrAppID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, perfilsPerUsrAppID);
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


  public List<StringKeyValue> getReferenceListForPerfilDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (perfilsPerUsuariAplicacioForm.isHiddenField(PERFILDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilsPerUsuariAplicacioForm.isReadOnlyField(PERFILDEFIRMAID)) {
      _where = PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.equal(perfilsPerUsuariAplicacioForm.getPerfilsPerUsuariAplicacio().getPerfilDeFirmaID());
    }
    return getReferenceListForPerfilDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPerfilDeFirmaID(HttpServletRequest request,
       ModelAndView mav, PerfilsPerUsuariAplicacioFilterForm perfilsPerUsuariAplicacioFilterForm,
       List<PerfilsPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilsPerUsuariAplicacioFilterForm.isHiddenField(PERFILDEFIRMAID)
      && !perfilsPerUsuariAplicacioFilterForm.isGroupByField(PERFILDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PERFILDEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PerfilsPerUsuariAplicacio _item : list) {
        _pkList.add(_item.getPerfilDeFirmaID());
        }
        _w = PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(_pkList);
      }
    return getReferenceListForPerfilDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPerfilDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return perfilDeFirmaRefList.getReferenceList(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID, where );
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm, Where where)  throws I18NException {
    if (perfilsPerUsuariAplicacioForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilsPerUsuariAplicacioForm.isReadOnlyField(USUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(perfilsPerUsuariAplicacioForm.getPerfilsPerUsuariAplicacio().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PerfilsPerUsuariAplicacioFilterForm perfilsPerUsuariAplicacioFilterForm,
       List<PerfilsPerUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilsPerUsuariAplicacioFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !perfilsPerUsuariAplicacioFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PerfilsPerUsuariAplicacio _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PerfilsPerUsuariAplicacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PerfilsPerUsuariAplicacioFilterForm filterForm,  List<PerfilsPerUsuariAplicacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PerfilsPerUsuariAplicacioForm perfilsPerUsuariAplicacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long perfilsPerUsrAppID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long perfilsPerUsrAppID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "perfilsPerUsuariAplicacioFormWebDB";
  }

  public String getTileList() {
    return "perfilsPerUsuariAplicacioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PerfilsPerUsuariAplicacioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PerfilsPerUsuariAplicacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long perfilsPerUsrAppID) throws I18NException {
    return (PerfilsPerUsuariAplicacioJPA) perfilsPerUsuariAplicacioEjb.findByPrimaryKey(perfilsPerUsrAppID);
  }


  public PerfilsPerUsuariAplicacioJPA create(HttpServletRequest request, PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio)
    throws I18NException, I18NValidationException {
    return (PerfilsPerUsuariAplicacioJPA) perfilsPerUsuariAplicacioEjb.create(perfilsPerUsuariAplicacio);
  }


  public PerfilsPerUsuariAplicacioJPA update(HttpServletRequest request, PerfilsPerUsuariAplicacioJPA perfilsPerUsuariAplicacio)
    throws I18NException, I18NValidationException {
    return (PerfilsPerUsuariAplicacioJPA) perfilsPerUsuariAplicacioEjb.update(perfilsPerUsuariAplicacio);
  }


  public void delete(HttpServletRequest request, PerfilsPerUsuariAplicacio perfilsPerUsuariAplicacio) throws I18NException {
    perfilsPerUsuariAplicacioEjb.delete(perfilsPerUsuariAplicacio);
  }

} // Final de Classe

