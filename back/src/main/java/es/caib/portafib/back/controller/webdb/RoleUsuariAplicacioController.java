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
import es.caib.portafib.back.form.webdb.RoleUsuariAplicacioForm;

import es.caib.portafib.back.validator.webdb.RoleUsuariAplicacioWebValidator;

import es.caib.portafib.jpa.RoleUsuariAplicacioJPA;
import es.caib.portafib.model.entity.RoleUsuariAplicacio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un RoleUsuariAplicacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/roleUsuariAplicacio")
@SessionAttributes(types = { RoleUsuariAplicacioForm.class, RoleUsuariAplicacioFilterForm.class })
public class RoleUsuariAplicacioController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<RoleUsuariAplicacio, java.lang.Long> implements RoleUsuariAplicacioFields {

  @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleUsuariAplicacioLocal roleUsuariAplicacioEjb;

  @Autowired
  private RoleUsuariAplicacioWebValidator roleUsuariAplicacioWebValidator;

  @Autowired
  protected RoleUsuariAplicacioRefList roleUsuariAplicacioRefList;

  // References 
  @Autowired
  protected RoleRefList roleRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  /**
   * Llistat de totes RoleUsuariAplicacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RoleUsuariAplicacioFilterForm ff;
    ff = (RoleUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar RoleUsuariAplicacio de forma paginada
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
    llistat(mav, request, getRoleUsuariAplicacioFilterForm(pagina, mav, request));
    return mav;
  }

  public RoleUsuariAplicacioFilterForm getRoleUsuariAplicacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RoleUsuariAplicacioFilterForm roleUsuariAplicacioFilterForm;
    roleUsuariAplicacioFilterForm = (RoleUsuariAplicacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(roleUsuariAplicacioFilterForm == null) {
      roleUsuariAplicacioFilterForm = new RoleUsuariAplicacioFilterForm();
      roleUsuariAplicacioFilterForm.setContexte(getContextWeb());
      roleUsuariAplicacioFilterForm.setEntityNameCode(getEntityNameCode());
      roleUsuariAplicacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      roleUsuariAplicacioFilterForm.setNou(true);
    } else {
      roleUsuariAplicacioFilterForm.setNou(false);
    }
    roleUsuariAplicacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return roleUsuariAplicacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar RoleUsuariAplicacio de forma paginada
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
      @ModelAttribute RoleUsuariAplicacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRoleUsuariAplicacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de RoleUsuariAplicacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<RoleUsuariAplicacio> llistat(ModelAndView mav, HttpServletRequest request,
     RoleUsuariAplicacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<RoleUsuariAplicacio> roleUsuariAplicacio = (List<RoleUsuariAplicacio>) processarLlistat(roleUsuariAplicacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("roleUsuariAplicacioItems", roleUsuariAplicacio);

    mav.addObject("roleUsuariAplicacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, roleUsuariAplicacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, roleUsuariAplicacio);

    return roleUsuariAplicacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RoleUsuariAplicacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<RoleUsuariAplicacio> list, List<GroupByItem> groupItems) throws I18NException {
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
    RoleUsuariAplicacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<RoleUsuariAplicacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ROLEUSUARIAPLICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ROLEID, filterForm.getMapOfRoleForRoleID());
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou RoleUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRoleUsuariAplicacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RoleUsuariAplicacioForm roleUsuariAplicacioForm = getRoleUsuariAplicacioForm(null, false, request, mav);
    mav.addObject("roleUsuariAplicacioForm" ,roleUsuariAplicacioForm);
    fillReferencesForForm(roleUsuariAplicacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RoleUsuariAplicacioForm getRoleUsuariAplicacioForm(RoleUsuariAplicacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RoleUsuariAplicacioForm roleUsuariAplicacioForm;
    if(_jpa == null) {
      roleUsuariAplicacioForm = new RoleUsuariAplicacioForm(new RoleUsuariAplicacioJPA(), true);
    } else {
      roleUsuariAplicacioForm = new RoleUsuariAplicacioForm(_jpa, false);
      roleUsuariAplicacioForm.setView(__isView);
    }
    roleUsuariAplicacioForm.setContexte(getContextWeb());
    roleUsuariAplicacioForm.setEntityNameCode(getEntityNameCode());
    roleUsuariAplicacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return roleUsuariAplicacioForm;
  }

  public void fillReferencesForForm(RoleUsuariAplicacioForm roleUsuariAplicacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (roleUsuariAplicacioForm.getListOfRoleForRoleID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForRoleID(request, mav, roleUsuariAplicacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      roleUsuariAplicacioForm.setListOfRoleForRoleID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (roleUsuariAplicacioForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, roleUsuariAplicacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      roleUsuariAplicacioForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou RoleUsuariAplicacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRoleUsuariAplicacioPost(@ModelAttribute RoleUsuariAplicacioForm roleUsuariAplicacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RoleUsuariAplicacioJPA roleUsuariAplicacio = roleUsuariAplicacioForm.getRoleUsuariAplicacio();

    try {
      preValidate(request, roleUsuariAplicacioForm, result);
      getWebValidator().validate(roleUsuariAplicacioForm, result);
      postValidate(request,roleUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        roleUsuariAplicacio = create(request, roleUsuariAplicacio);
        createMessageSuccess(request, "success.creation", roleUsuariAplicacio.getId());
        roleUsuariAplicacioForm.setRoleUsuariAplicacio(roleUsuariAplicacio);
        return getRedirectWhenCreated(request, roleUsuariAplicacioForm);
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
  public ModelAndView veureRoleUsuariAplicacioGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRoleUsuariAplicacioGet(id,
        request, response, true);
  }


  protected ModelAndView editAndViewRoleUsuariAplicacioGet(@PathVariable("id") java.lang.Long id,
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
    RoleUsuariAplicacioJPA roleUsuariAplicacio = findByPrimaryKey(request, id);

    if (roleUsuariAplicacio == null) {
      createMessageWarning(request, "error.notfound", id);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, id), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RoleUsuariAplicacioForm roleUsuariAplicacioForm = getRoleUsuariAplicacioForm(roleUsuariAplicacio, __isView, request, mav);
      roleUsuariAplicacioForm.setView(__isView);
      if(__isView) {
        roleUsuariAplicacioForm.setAllFieldsReadOnly(ALL_ROLEUSUARIAPLICACIO_FIELDS);
        roleUsuariAplicacioForm.setSaveButtonVisible(false);
        roleUsuariAplicacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(roleUsuariAplicacioForm, request, mav);
      mav.addObject("roleUsuariAplicacioForm", roleUsuariAplicacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un RoleUsuariAplicacio existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editarRoleUsuariAplicacioGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRoleUsuariAplicacioGet(id,
        request, response, false);
  }



  /**
   * Editar un RoleUsuariAplicacio existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public String editarRoleUsuariAplicacioPost(@ModelAttribute @Valid RoleUsuariAplicacioForm roleUsuariAplicacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RoleUsuariAplicacioJPA roleUsuariAplicacio = roleUsuariAplicacioForm.getRoleUsuariAplicacio();

    try {
      preValidate(request, roleUsuariAplicacioForm, result);
      getWebValidator().validate(roleUsuariAplicacio, result);
      postValidate(request, roleUsuariAplicacioForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        roleUsuariAplicacio = update(request, roleUsuariAplicacio);
        createMessageSuccess(request, "success.modification", roleUsuariAplicacio.getId());
        status.setComplete();
        return getRedirectWhenModified(request, roleUsuariAplicacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          roleUsuariAplicacio.getId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, roleUsuariAplicacioForm, __e);
    }

  }


  /**
   * Eliminar un RoleUsuariAplicacio existent
   */
  @RequestMapping(value = "/{id}/delete")
  public String eliminarRoleUsuariAplicacio(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      RoleUsuariAplicacio roleUsuariAplicacio = roleUsuariAplicacioEjb.findByPrimaryKey(id);
      if (roleUsuariAplicacio == null) {
        String __msg =createMessageError(request, "error.notfound", id);
        return getRedirectWhenDelete(request, id, new Exception(__msg));
      } else {
        delete(request, roleUsuariAplicacio);
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
    @ModelAttribute RoleUsuariAplicacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRoleUsuariAplicacio(stringToPK(seleccionats[i]), request, response);
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
    return "roleUsuariAplicacio.roleUsuariAplicacio";
  }

  public String getEntityNameCodePlural() {
    return "roleUsuariAplicacio.roleUsuariAplicacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("roleUsuariAplicacio.id");
  }

  @InitBinder("roleUsuariAplicacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("roleUsuariAplicacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("id");

  }

  public RoleUsuariAplicacioWebValidator getWebValidator() {
    return roleUsuariAplicacioWebValidator;
  }


  public void setWebValidator(RoleUsuariAplicacioWebValidator __val) {
    if (__val != null) {
      this.roleUsuariAplicacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de RoleUsuariAplicacio
   */
  @RequestMapping(value = "/{id}/cancel")
  public String cancelRoleUsuariAplicacio(@PathVariable("id") java.lang.Long id,
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
       ModelAndView mav, RoleUsuariAplicacioForm roleUsuariAplicacioForm, Where where)  throws I18NException {
    if (roleUsuariAplicacioForm.isHiddenField(ROLEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  roleUsuariAplicacioForm.getStringOfField(ROLEID);
    Where _where = null;
    if (roleUsuariAplicacioForm.isReadOnlyField(_fieldName)) {
      _where = RoleFields.ROLEID.equal(roleUsuariAplicacioForm.getRoleUsuariAplicacio().getRoleID());
    }
    return getReferenceListForRoleID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForRoleID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariAplicacioFilterForm roleUsuariAplicacioFilterForm,
       List<RoleUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (roleUsuariAplicacioFilterForm.isHiddenField(ROLEID)
      && !roleUsuariAplicacioFilterForm.isGroupByField(ROLEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ROLEID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RoleUsuariAplicacio _item : list) {
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


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariAplicacioForm roleUsuariAplicacioForm, Where where)  throws I18NException {
    if (roleUsuariAplicacioForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  roleUsuariAplicacioForm.getStringOfField(USUARIAPLICACIOID);
    Where _where = null;
    if (roleUsuariAplicacioForm.isReadOnlyField(_fieldName)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(roleUsuariAplicacioForm.getRoleUsuariAplicacio().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, RoleUsuariAplicacioFilterForm roleUsuariAplicacioFilterForm,
       List<RoleUsuariAplicacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (roleUsuariAplicacioFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !roleUsuariAplicacioFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RoleUsuariAplicacio _item : list) {
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


  public void preValidate(HttpServletRequest request,RoleUsuariAplicacioForm roleUsuariAplicacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RoleUsuariAplicacioForm roleUsuariAplicacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RoleUsuariAplicacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RoleUsuariAplicacioFilterForm filterForm,  List<RoleUsuariAplicacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, RoleUsuariAplicacioForm roleUsuariAplicacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, RoleUsuariAplicacioForm roleUsuariAplicacioForm, Throwable __e) {
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
    return "roleUsuariAplicacioFormWebDB";
  }

  public String getTileList() {
    return "roleUsuariAplicacioListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "RoleUsuariAplicacioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RoleUsuariAplicacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
    return (RoleUsuariAplicacioJPA) roleUsuariAplicacioEjb.findByPrimaryKey(id);
  }


  public RoleUsuariAplicacioJPA create(HttpServletRequest request, RoleUsuariAplicacioJPA roleUsuariAplicacio)
    throws Exception,I18NException, I18NValidationException {
    return (RoleUsuariAplicacioJPA) roleUsuariAplicacioEjb.create(roleUsuariAplicacio);
  }


  public RoleUsuariAplicacioJPA update(HttpServletRequest request, RoleUsuariAplicacioJPA roleUsuariAplicacio)
    throws Exception,I18NException, I18NValidationException {
    return (RoleUsuariAplicacioJPA) roleUsuariAplicacioEjb.update(roleUsuariAplicacio);
  }


  public void delete(HttpServletRequest request, RoleUsuariAplicacio roleUsuariAplicacio) throws Exception,I18NException {
    roleUsuariAplicacioEjb.delete(roleUsuariAplicacio);
  }

} // Final de Classe

