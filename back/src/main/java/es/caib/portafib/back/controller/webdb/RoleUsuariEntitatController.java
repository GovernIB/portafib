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
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;

import es.caib.portafib.back.validator.webdb.RoleUsuariEntitatWebValidator;

import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un RoleUsuariEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/roleUsuariEntitat")
@SessionAttributes(types = { RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class })
public class RoleUsuariEntitatController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<RoleUsuariEntitat, java.lang.Long> implements RoleUsuariEntitatFields {

  @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleUsuariEntitatService roleUsuariEntitatEjb;

  @Autowired
  private RoleUsuariEntitatWebValidator roleUsuariEntitatWebValidator;

  @Autowired
  protected RoleUsuariEntitatRefList roleUsuariEntitatRefList;

  // References 
  @Autowired
  protected RoleRefList roleRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes RoleUsuariEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RoleUsuariEntitatFilterForm ff;
    ff = (RoleUsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar RoleUsuariEntitat de forma paginada
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
    llistat(mav, request, getRoleUsuariEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public RoleUsuariEntitatFilterForm getRoleUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RoleUsuariEntitatFilterForm roleUsuariEntitatFilterForm;
    roleUsuariEntitatFilterForm = (RoleUsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(roleUsuariEntitatFilterForm == null) {
      roleUsuariEntitatFilterForm = new RoleUsuariEntitatFilterForm();
      roleUsuariEntitatFilterForm.setContexte(getContextWeb());
      roleUsuariEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      roleUsuariEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      roleUsuariEntitatFilterForm.setNou(true);
    } else {
      roleUsuariEntitatFilterForm.setNou(false);
    }
    roleUsuariEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return roleUsuariEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar RoleUsuariEntitat de forma paginada
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
      @ModelAttribute RoleUsuariEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRoleUsuariEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de RoleUsuariEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<RoleUsuariEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     RoleUsuariEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<RoleUsuariEntitat> roleUsuariEntitat = processarLlistat(roleUsuariEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("roleUsuariEntitatItems", roleUsuariEntitat);

    mav.addObject("roleUsuariEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, roleUsuariEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, roleUsuariEntitat);

    return roleUsuariEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RoleUsuariEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<RoleUsuariEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field roleID
    {
      _listSKV = getReferenceListForRoleID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfRoleForRoleID(_tmp);
      if (filterForm.getGroupByFields().contains(ROLEID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ROLEID, false);
      };
    }

    // Field usuariEntitatID
    {
      _listSKV = getReferenceListForUsuariEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForUsuariEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    RoleUsuariEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<RoleUsuariEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ROLEUSUARIENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ROLEID, filterForm.getMapOfRoleForRoleID());
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou RoleUsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRoleUsuariEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RoleUsuariEntitatForm roleUsuariEntitatForm = getRoleUsuariEntitatForm(null, false, request, mav);
    mav.addObject("roleUsuariEntitatForm" ,roleUsuariEntitatForm);
    fillReferencesForForm(roleUsuariEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RoleUsuariEntitatForm getRoleUsuariEntitatForm(RoleUsuariEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RoleUsuariEntitatForm roleUsuariEntitatForm;
    if(_jpa == null) {
      roleUsuariEntitatForm = new RoleUsuariEntitatForm(new RoleUsuariEntitatJPA(), true);
    } else {
      roleUsuariEntitatForm = new RoleUsuariEntitatForm(_jpa, false);
      roleUsuariEntitatForm.setView(__isView);
    }
    roleUsuariEntitatForm.setContexte(getContextWeb());
    roleUsuariEntitatForm.setEntityNameCode(getEntityNameCode());
    roleUsuariEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return roleUsuariEntitatForm;
  }

  public void fillReferencesForForm(RoleUsuariEntitatForm roleUsuariEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (roleUsuariEntitatForm.getListOfRoleForRoleID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForRoleID(request, mav, roleUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      roleUsuariEntitatForm.setListOfRoleForRoleID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (roleUsuariEntitatForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, roleUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      roleUsuariEntitatForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou RoleUsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRoleUsuariEntitatPost(@ModelAttribute RoleUsuariEntitatForm roleUsuariEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RoleUsuariEntitatJPA roleUsuariEntitat = roleUsuariEntitatForm.getRoleUsuariEntitat();

    try {
      preValidate(request, roleUsuariEntitatForm, result);
      getWebValidator().validate(roleUsuariEntitatForm, result);
      postValidate(request,roleUsuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        roleUsuariEntitat = create(request, roleUsuariEntitat);
        createMessageSuccess(request, "success.creation", roleUsuariEntitat.getId());
        roleUsuariEntitatForm.setRoleUsuariEntitat(roleUsuariEntitat);
        return getRedirectWhenCreated(request, roleUsuariEntitatForm);
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

  @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
  public ModelAndView veureRoleUsuariEntitatGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRoleUsuariEntitatGet(id,
        request, response, true);
  }


  protected ModelAndView editAndViewRoleUsuariEntitatGet(@PathVariable("id") java.lang.Long id,
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
    RoleUsuariEntitatJPA roleUsuariEntitat = findByPrimaryKey(request, id);

    if (roleUsuariEntitat == null) {
      createMessageWarning(request, "error.notfound", id);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, id), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RoleUsuariEntitatForm roleUsuariEntitatForm = getRoleUsuariEntitatForm(roleUsuariEntitat, __isView, request, mav);
      roleUsuariEntitatForm.setView(__isView);
      if(__isView) {
        roleUsuariEntitatForm.setAllFieldsReadOnly(ALL_ROLEUSUARIENTITAT_FIELDS);
        roleUsuariEntitatForm.setSaveButtonVisible(false);
        roleUsuariEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(roleUsuariEntitatForm, request, mav);
      mav.addObject("roleUsuariEntitatForm", roleUsuariEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un RoleUsuariEntitat existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editarRoleUsuariEntitatGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRoleUsuariEntitatGet(id,
        request, response, false);
  }



  /**
   * Editar un RoleUsuariEntitat existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public String editarRoleUsuariEntitatPost(@ModelAttribute RoleUsuariEntitatForm roleUsuariEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RoleUsuariEntitatJPA roleUsuariEntitat = roleUsuariEntitatForm.getRoleUsuariEntitat();

    try {
      preValidate(request, roleUsuariEntitatForm, result);
      getWebValidator().validate(roleUsuariEntitatForm, result);
      postValidate(request, roleUsuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        roleUsuariEntitat = update(request, roleUsuariEntitat);
        createMessageSuccess(request, "success.modification", roleUsuariEntitat.getId());
        status.setComplete();
        return getRedirectWhenModified(request, roleUsuariEntitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          roleUsuariEntitat.getId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, roleUsuariEntitatForm, __e);
    }

  }


  /**
   * Eliminar un RoleUsuariEntitat existent
   */
  @RequestMapping(value = "/{id}/delete")
  public String eliminarRoleUsuariEntitat(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      RoleUsuariEntitat roleUsuariEntitat = roleUsuariEntitatEjb.findByPrimaryKey(id);
      if (roleUsuariEntitat == null) {
        String __msg =createMessageError(request, "error.notfound", id);
        return getRedirectWhenDelete(request, id, new Exception(__msg));
      } else {
        delete(request, roleUsuariEntitat);
        createMessageSuccess(request, "success.deleted", id);
        return getRedirectWhenDelete(request, id,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", id, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, id, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute RoleUsuariEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRoleUsuariEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __id, Throwable e) {
    java.lang.Long id = (java.lang.Long)__id;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (id == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(id),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "roleUsuariEntitat.roleUsuariEntitat";
  }

  public String getEntityNameCodePlural() {
    return "roleUsuariEntitat.roleUsuariEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("roleUsuariEntitat.id");
  }

  @InitBinder("roleUsuariEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("roleUsuariEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("id");

  }

  public RoleUsuariEntitatWebValidator getWebValidator() {
    return roleUsuariEntitatWebValidator;
  }


  public void setWebValidator(RoleUsuariEntitatWebValidator __val) {
    if (__val != null) {
      this.roleUsuariEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de RoleUsuariEntitat
   */
  @RequestMapping(value = "/{id}/cancel")
  public String cancelRoleUsuariEntitat(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, id);
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


  public List<StringKeyValue> getReferenceListForRoleID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariEntitatForm roleUsuariEntitatForm, Where where)  throws I18NException {
    if (roleUsuariEntitatForm.isHiddenField(ROLEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (roleUsuariEntitatForm.isReadOnlyField(ROLEID)) {
      _where = RoleFields.ROLEID.equal(roleUsuariEntitatForm.getRoleUsuariEntitat().getRoleID());
    }
    return getReferenceListForRoleID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForRoleID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariEntitatFilterForm roleUsuariEntitatFilterForm,
       List<RoleUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (roleUsuariEntitatFilterForm.isHiddenField(ROLEID)
      && !roleUsuariEntitatFilterForm.isGroupByField(ROLEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ROLEID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RoleUsuariEntitat _item : list) {
        _pkList.add(_item.getRoleID());
        }
        _w = RoleFields.ROLEID.in(_pkList);
      }
    return getReferenceListForRoleID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForRoleID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return roleRefList.getReferenceList(RoleFields.ROLEID, where );
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariEntitatForm roleUsuariEntitatForm, Where where)  throws I18NException {
    if (roleUsuariEntitatForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (roleUsuariEntitatForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(roleUsuariEntitatForm.getRoleUsuariEntitat().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariEntitatFilterForm roleUsuariEntitatFilterForm,
       List<RoleUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (roleUsuariEntitatFilterForm.isHiddenField(USUARIENTITATID)
      && !roleUsuariEntitatFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RoleUsuariEntitat _item : list) {
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


  public void preValidate(HttpServletRequest request,RoleUsuariEntitatForm roleUsuariEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RoleUsuariEntitatForm roleUsuariEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RoleUsuariEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RoleUsuariEntitatFilterForm filterForm,  List<RoleUsuariEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, RoleUsuariEntitatForm roleUsuariEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, RoleUsuariEntitatForm roleUsuariEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long id, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long id) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "roleUsuariEntitatFormWebDB";
  }

  public String getTileList() {
    return "roleUsuariEntitatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "RoleUsuariEntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RoleUsuariEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
    return (RoleUsuariEntitatJPA) roleUsuariEntitatEjb.findByPrimaryKey(id);
  }


  public RoleUsuariEntitatJPA create(HttpServletRequest request, RoleUsuariEntitatJPA roleUsuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (RoleUsuariEntitatJPA) roleUsuariEntitatEjb.create(roleUsuariEntitat);
  }


  public RoleUsuariEntitatJPA update(HttpServletRequest request, RoleUsuariEntitatJPA roleUsuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (RoleUsuariEntitatJPA) roleUsuariEntitatEjb.update(roleUsuariEntitat);
  }


  public void delete(HttpServletRequest request, RoleUsuariEntitat roleUsuariEntitat) throws Exception,I18NException {
    roleUsuariEntitatEjb.delete(roleUsuariEntitat);
  }

} // Final de Classe

