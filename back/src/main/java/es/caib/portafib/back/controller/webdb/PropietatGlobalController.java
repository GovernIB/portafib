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
import es.caib.portafib.back.form.webdb.PropietatGlobalForm;

import es.caib.portafib.back.validator.webdb.PropietatGlobalWebValidator;

import es.caib.portafib.persistence.PropietatGlobalJPA;
import es.caib.portafib.model.entity.PropietatGlobal;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PropietatGlobal
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/propietatGlobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PropietatGlobal, java.lang.Long> implements PropietatGlobalFields {

  @EJB(mappedName = es.caib.portafib.ejb.PropietatGlobalService.JNDI_NAME)
  protected es.caib.portafib.ejb.PropietatGlobalService propietatGlobalEjb;

  @Autowired
  private PropietatGlobalWebValidator propietatGlobalWebValidator;

  @Autowired
  protected PropietatGlobalRefList propietatGlobalRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes PropietatGlobal
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PropietatGlobalFilterForm ff;
    ff = (PropietatGlobalFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PropietatGlobal de forma paginada
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
    llistat(mav, request, getPropietatGlobalFilterForm(pagina, mav, request));
    return mav;
  }

  public PropietatGlobalFilterForm getPropietatGlobalFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PropietatGlobalFilterForm propietatGlobalFilterForm;
    propietatGlobalFilterForm = (PropietatGlobalFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(propietatGlobalFilterForm == null) {
      propietatGlobalFilterForm = new PropietatGlobalFilterForm();
      propietatGlobalFilterForm.setContexte(getContextWeb());
      propietatGlobalFilterForm.setEntityNameCode(getEntityNameCode());
      propietatGlobalFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      propietatGlobalFilterForm.setNou(true);
    } else {
      propietatGlobalFilterForm.setNou(false);
    }
    propietatGlobalFilterForm.setPage(pagina == null ? 1 : pagina);
    return propietatGlobalFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PropietatGlobal de forma paginada
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
      @ModelAttribute PropietatGlobalFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPropietatGlobalFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PropietatGlobal de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PropietatGlobal> llistat(ModelAndView mav, HttpServletRequest request,
     PropietatGlobalFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PropietatGlobal> propietatGlobal = processarLlistat(propietatGlobalEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("propietatGlobalItems", propietatGlobal);

    mav.addObject("propietatGlobalFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, propietatGlobal, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, propietatGlobal);

    return propietatGlobal;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PropietatGlobalFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PropietatGlobal> list, List<GroupByItem> groupItems) throws I18NException {
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


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PropietatGlobalFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PropietatGlobal> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PROPIETATGLOBAL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PropietatGlobal
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPropietatGlobalGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PropietatGlobalForm propietatGlobalForm = getPropietatGlobalForm(null, false, request, mav);
    mav.addObject("propietatGlobalForm" ,propietatGlobalForm);
    fillReferencesForForm(propietatGlobalForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PropietatGlobalForm propietatGlobalForm;
    if(_jpa == null) {
      propietatGlobalForm = new PropietatGlobalForm(new PropietatGlobalJPA(), true);
    } else {
      propietatGlobalForm = new PropietatGlobalForm(_jpa, false);
      propietatGlobalForm.setView(__isView);
    }
    propietatGlobalForm.setContexte(getContextWeb());
    propietatGlobalForm.setEntityNameCode(getEntityNameCode());
    propietatGlobalForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return propietatGlobalForm;
  }

  public void fillReferencesForForm(PropietatGlobalForm propietatGlobalForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (propietatGlobalForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, propietatGlobalForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      propietatGlobalForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PropietatGlobal
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPropietatGlobalPost(@ModelAttribute PropietatGlobalForm propietatGlobalForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PropietatGlobalJPA propietatGlobal = propietatGlobalForm.getPropietatGlobal();

    try {
      preValidate(request, propietatGlobalForm, result);
      getWebValidator().validate(propietatGlobalForm, result);
      postValidate(request,propietatGlobalForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        propietatGlobal = create(request, propietatGlobal);
        createMessageSuccess(request, "success.creation", propietatGlobal.getPropietatGlobalID());
        propietatGlobalForm.setPropietatGlobal(propietatGlobal);
        return getRedirectWhenCreated(request, propietatGlobalForm);
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

  @RequestMapping(value = "/view/{propietatGlobalID}", method = RequestMethod.GET)
  public ModelAndView veurePropietatGlobalGet(@PathVariable("propietatGlobalID") java.lang.Long propietatGlobalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPropietatGlobalGet(propietatGlobalID,
        request, response, true);
  }


  protected ModelAndView editAndViewPropietatGlobalGet(@PathVariable("propietatGlobalID") java.lang.Long propietatGlobalID,
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
    PropietatGlobalJPA propietatGlobal = findByPrimaryKey(request, propietatGlobalID);

    if (propietatGlobal == null) {
      createMessageWarning(request, "error.notfound", propietatGlobalID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, propietatGlobalID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PropietatGlobalForm propietatGlobalForm = getPropietatGlobalForm(propietatGlobal, __isView, request, mav);
      propietatGlobalForm.setView(__isView);
      if(__isView) {
        propietatGlobalForm.setAllFieldsReadOnly(ALL_PROPIETATGLOBAL_FIELDS);
        propietatGlobalForm.setSaveButtonVisible(false);
        propietatGlobalForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(propietatGlobalForm, request, mav);
      mav.addObject("propietatGlobalForm", propietatGlobalForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PropietatGlobal existent
   */
  @RequestMapping(value = "/{propietatGlobalID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPropietatGlobalGet(@PathVariable("propietatGlobalID") java.lang.Long propietatGlobalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPropietatGlobalGet(propietatGlobalID,
        request, response, false);
  }



  /**
   * Editar un PropietatGlobal existent
   */
  @RequestMapping(value = "/{propietatGlobalID}/edit", method = RequestMethod.POST)
  public String editarPropietatGlobalPost(@ModelAttribute PropietatGlobalForm propietatGlobalForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PropietatGlobalJPA propietatGlobal = propietatGlobalForm.getPropietatGlobal();

    try {
      preValidate(request, propietatGlobalForm, result);
      getWebValidator().validate(propietatGlobalForm, result);
      postValidate(request, propietatGlobalForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        propietatGlobal = update(request, propietatGlobal);
        createMessageSuccess(request, "success.modification", propietatGlobal.getPropietatGlobalID());
        status.setComplete();
        return getRedirectWhenModified(request, propietatGlobalForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          propietatGlobal.getPropietatGlobalID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, propietatGlobalForm, __e);
    }

  }


  /**
   * Eliminar un PropietatGlobal existent
   */
  @RequestMapping(value = "/{propietatGlobalID}/delete")
  public String eliminarPropietatGlobal(@PathVariable("propietatGlobalID") java.lang.Long propietatGlobalID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PropietatGlobal propietatGlobal = propietatGlobalEjb.findByPrimaryKey(propietatGlobalID);
      if (propietatGlobal == null) {
        String __msg =createMessageError(request, "error.notfound", propietatGlobalID);
        return getRedirectWhenDelete(request, propietatGlobalID, new Exception(__msg));
      } else {
        delete(request, propietatGlobal);
        createMessageSuccess(request, "success.deleted", propietatGlobalID);
        return getRedirectWhenDelete(request, propietatGlobalID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", propietatGlobalID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, propietatGlobalID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PropietatGlobalFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPropietatGlobal(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __propietatGlobalID, Throwable e) {
    java.lang.Long propietatGlobalID = (java.lang.Long)__propietatGlobalID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (propietatGlobalID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(propietatGlobalID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "propietatGlobal.propietatGlobal";
  }

  public String getEntityNameCodePlural() {
    return "propietatGlobal.propietatGlobal.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("propietatGlobal.propietatGlobalID");
  }

  @InitBinder("propietatGlobalFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("propietatGlobalForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "propietatGlobal.propietatGlobalID");
  }

  public PropietatGlobalWebValidator getWebValidator() {
    return propietatGlobalWebValidator;
  }


  public void setWebValidator(PropietatGlobalWebValidator __val) {
    if (__val != null) {
      this.propietatGlobalWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PropietatGlobal
   */
  @RequestMapping(value = "/{propietatGlobalID}/cancel")
  public String cancelPropietatGlobal(@PathVariable("propietatGlobalID") java.lang.Long propietatGlobalID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, propietatGlobalID);
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
       ModelAndView mav, PropietatGlobalForm propietatGlobalForm, Where where)  throws I18NException {
    if (propietatGlobalForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (propietatGlobalForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(propietatGlobalForm.getPropietatGlobal().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PropietatGlobalFilterForm propietatGlobalFilterForm,
       List<PropietatGlobal> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (propietatGlobalFilterForm.isHiddenField(ENTITATID)
      && !propietatGlobalFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PropietatGlobal _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PropietatGlobalForm propietatGlobalForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PropietatGlobalForm propietatGlobalForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PropietatGlobalFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PropietatGlobalFilterForm filterForm,  List<PropietatGlobal> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PropietatGlobalForm propietatGlobalForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PropietatGlobalForm propietatGlobalForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long propietatGlobalID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long propietatGlobalID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "propietatGlobalFormWebDB";
  }

  public String getTileList() {
    return "propietatGlobalListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PropietatGlobalWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PropietatGlobalJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long propietatGlobalID) throws I18NException {
    return (PropietatGlobalJPA) propietatGlobalEjb.findByPrimaryKey(propietatGlobalID);
  }


  public PropietatGlobalJPA create(HttpServletRequest request, PropietatGlobalJPA propietatGlobal)
    throws I18NException, I18NValidationException {
    return (PropietatGlobalJPA) propietatGlobalEjb.create(propietatGlobal);
  }


  public PropietatGlobalJPA update(HttpServletRequest request, PropietatGlobalJPA propietatGlobal)
    throws I18NException, I18NValidationException {
    return (PropietatGlobalJPA) propietatGlobalEjb.update(propietatGlobal);
  }


  public void delete(HttpServletRequest request, PropietatGlobal propietatGlobal) throws I18NException {
    propietatGlobalEjb.delete(propietatGlobal);
  }

} // Final de Classe

