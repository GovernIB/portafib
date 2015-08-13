package es.caib.portafib.back.controller.webdb;

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
import es.caib.portafib.back.form.webdb.RoleForm;

import es.caib.portafib.back.validator.webdb.RoleWebValidator;

import es.caib.portafib.jpa.RoleJPA;
import es.caib.portafib.model.entity.Role;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Role
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/role")
@SessionAttributes(types = { RoleForm.class, RoleFilterForm.class })
public class RoleController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements RoleFields {

  @EJB(mappedName = es.caib.portafib.ejb.RoleLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleLocal roleEjb;

  @Autowired
  private RoleWebValidator roleWebValidator;

  @Autowired
  protected RoleRefList roleRefList;

  /**
   * Llistat de totes Role
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RoleFilterForm ff;
    ff = (RoleFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Role de forma paginada
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
    llistat(mav, request, getRoleFilterForm(pagina, mav, request));
    return mav;
  }

  public RoleFilterForm getRoleFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RoleFilterForm roleFilterForm;
    roleFilterForm = (RoleFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(roleFilterForm == null) {
      roleFilterForm = new RoleFilterForm();
      roleFilterForm.setContexte(getContextWeb());
      roleFilterForm.setEntityNameCode(getEntityNameCode());
      roleFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      roleFilterForm.setNou(true);
    } else {
      roleFilterForm.setNou(false);
    }
    roleFilterForm.setPage(pagina == null ? 1 : pagina);
    return roleFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Role de forma paginada
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
      @ModelAttribute RoleFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRoleFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Role de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Role> llistat(ModelAndView mav, HttpServletRequest request,
     RoleFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<Role> role = (List<Role>) processarLlistat(roleEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("roleItems", role);

    mav.addObject("roleFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, role, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, role);

    return role;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RoleFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Role> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    RoleFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Role> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ROLE_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Role
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRoleGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RoleForm roleForm = getRoleForm(null, false, request, mav);
    mav.addObject("roleForm" ,roleForm);
    fillReferencesForForm(roleForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RoleForm getRoleForm(RoleJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RoleForm roleForm;
    if(_jpa == null) {
      roleForm = new RoleForm(new RoleJPA(), true);
    } else {
      roleForm = new RoleForm(_jpa, false);
      roleForm.setView(__isView);
    }
    roleForm.setContexte(getContextWeb());
    roleForm.setEntityNameCode(getEntityNameCode());
    roleForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return roleForm;
  }

  public void fillReferencesForForm(RoleForm roleForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Role
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRolePost(@ModelAttribute RoleForm roleForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RoleJPA role = roleForm.getRole();

    try {
      preValidate(request, roleForm, result);
      getWebValidator().validate(roleForm, result);
      postValidate(request,roleForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        role = create(request, role);
        createMessageSuccess(request, "success.creation", role.getRoleID());
        roleForm.setRole(role);
        return getRedirectWhenCreated(roleForm);
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

  @RequestMapping(value = "/view/{roleID}", method = RequestMethod.GET)
  public ModelAndView veureRoleGet(@PathVariable("roleID") java.lang.String roleID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRoleGet(roleID,
        request, response, true);
  }


  protected ModelAndView editAndViewRoleGet(@PathVariable("roleID") java.lang.String roleID,
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
    RoleJPA role = findByPrimaryKey(request, roleID);

    if (role == null) {
      createMessageWarning(request, "error.notfound", roleID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(roleID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RoleForm roleForm = getRoleForm(role, __isView, request, mav);
      roleForm.setView(__isView);
      if(__isView) {
        roleForm.setAllFieldsReadOnly(ALL_ROLE_FIELDS);
        roleForm.setSaveButtonVisible(false);
        roleForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(roleForm, request, mav);
      mav.addObject("roleForm", roleForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Role existent
   */
  @RequestMapping(value = "/{roleID}/edit", method = RequestMethod.GET)
  public ModelAndView editarRoleGet(@PathVariable("roleID") java.lang.String roleID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRoleGet(roleID,
        request, response, false);
  }



  /**
   * Editar un Role existent
   */
  @RequestMapping(value = "/{roleID}/edit", method = RequestMethod.POST)
  public String editarRolePost(@ModelAttribute @Valid RoleForm roleForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RoleJPA role = roleForm.getRole();

    try {
      preValidate(request, roleForm, result);
      getWebValidator().validate(role, result);
      postValidate(request, roleForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        role = update(request, role);
        createMessageSuccess(request, "success.modification", role.getRoleID());
        status.setComplete();
        return getRedirectWhenModified(roleForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          role.getRoleID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(roleForm, __e);
    }

  }


  /**
   * Eliminar un Role existent
   */
  @RequestMapping(value = "/{roleID}/delete")
  public String eliminarRole(@PathVariable("roleID") java.lang.String roleID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Role role = roleEjb.findByPrimaryKey(roleID);
      if (role == null) {
        String __msg =createMessageError(request, "error.notfound", roleID);
        return getRedirectWhenDelete(roleID, new Exception(__msg));
      } else {
        delete(request, role);
        createMessageSuccess(request, "success.deleted", roleID);
        return getRedirectWhenDelete(roleID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", roleID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(roleID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute RoleFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRole(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(null,null);
  }

  return redirect;
}



public java.lang.String stringToPK(String value) {
  return value;
}

  @Override
  public String[] getArgumentsMissatge(Object __roleID, Throwable e) {
    java.lang.String roleID = (java.lang.String)__roleID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (roleID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(roleID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "role.role";
  }

  public String getEntityNameCodePlural() {
    return "role.role.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("role.roleID");
  }

  @InitBinder("roleFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("roleForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public RoleWebValidator getWebValidator() {
    return roleWebValidator;
  }


  public void setWebValidator(RoleWebValidator __val) {
    if (__val != null) {
      this.roleWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Role
   */
  @RequestMapping(value = "/{roleID}/cancel")
  public String cancelRole(@PathVariable("roleID") java.lang.String roleID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(roleID);
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


  public void preValidate(HttpServletRequest request,RoleForm roleForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RoleForm roleForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RoleFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RoleFilterForm filterForm,  List<Role> list) throws I18NException {
  }

  public String getRedirectWhenCreated(RoleForm roleForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(RoleForm roleForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(java.lang.String roleID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(java.lang.String roleID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "roleFormWebDB";
  }

  public String getTileList() {
    return "roleListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "RoleWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RoleJPA findByPrimaryKey(HttpServletRequest request, java.lang.String roleID) throws I18NException {
    return (RoleJPA) roleEjb.findByPrimaryKey(roleID);
  }


  public RoleJPA create(HttpServletRequest request, RoleJPA role)
    throws Exception,I18NException, I18NValidationException {
    return (RoleJPA) roleEjb.create(role);
  }


  public RoleJPA update(HttpServletRequest request, RoleJPA role)
    throws Exception,I18NException, I18NValidationException {
    return (RoleJPA) roleEjb.update(role);
  }


  public void delete(HttpServletRequest request, Role role) throws Exception,I18NException {
    roleEjb.delete(role);
  }

} // Final de Classe

