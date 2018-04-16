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
import es.caib.portafib.back.form.webdb.UsuariEntitatRevisorForm;

import es.caib.portafib.back.validator.webdb.UsuariEntitatRevisorWebValidator;

import es.caib.portafib.jpa.UsuariEntitatRevisorJPA;
import es.caib.portafib.model.entity.UsuariEntitatRevisor;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un UsuariEntitatRevisor
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/usuariEntitatRevisor")
@SessionAttributes(types = { UsuariEntitatRevisorForm.class, UsuariEntitatRevisorFilterForm.class })
public class UsuariEntitatRevisorController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<UsuariEntitatRevisor, java.lang.Long> implements UsuariEntitatRevisorFields {

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatRevisorLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatRevisorLocal usuariEntitatRevisorEjb;

  @Autowired
  private UsuariEntitatRevisorWebValidator usuariEntitatRevisorWebValidator;

  @Autowired
  protected UsuariEntitatRevisorRefList usuariEntitatRevisorRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes UsuariEntitatRevisor
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    UsuariEntitatRevisorFilterForm ff;
    ff = (UsuariEntitatRevisorFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar UsuariEntitatRevisor de forma paginada
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
    llistat(mav, request, getUsuariEntitatRevisorFilterForm(pagina, mav, request));
    return mav;
  }

  public UsuariEntitatRevisorFilterForm getUsuariEntitatRevisorFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    UsuariEntitatRevisorFilterForm usuariEntitatRevisorFilterForm;
    usuariEntitatRevisorFilterForm = (UsuariEntitatRevisorFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(usuariEntitatRevisorFilterForm == null) {
      usuariEntitatRevisorFilterForm = new UsuariEntitatRevisorFilterForm();
      usuariEntitatRevisorFilterForm.setContexte(getContextWeb());
      usuariEntitatRevisorFilterForm.setEntityNameCode(getEntityNameCode());
      usuariEntitatRevisorFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      usuariEntitatRevisorFilterForm.setNou(true);
    } else {
      usuariEntitatRevisorFilterForm.setNou(false);
    }
    usuariEntitatRevisorFilterForm.setPage(pagina == null ? 1 : pagina);
    return usuariEntitatRevisorFilterForm;
  }

  /**
   * Segona i següent peticions per llistar UsuariEntitatRevisor de forma paginada
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
      @ModelAttribute UsuariEntitatRevisorFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getUsuariEntitatRevisorFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de UsuariEntitatRevisor de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<UsuariEntitatRevisor> llistat(ModelAndView mav, HttpServletRequest request,
     UsuariEntitatRevisorFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<UsuariEntitatRevisor> usuariEntitatRevisor = processarLlistat(usuariEntitatRevisorEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("usuariEntitatRevisorItems", usuariEntitatRevisor);

    mav.addObject("usuariEntitatRevisorFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, usuariEntitatRevisor, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, usuariEntitatRevisor);

    return usuariEntitatRevisor;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(UsuariEntitatRevisorFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<UsuariEntitatRevisor> list, List<GroupByItem> groupItems) throws I18NException {
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


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIU);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    UsuariEntitatRevisorFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<UsuariEntitatRevisor> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_USUARIENTITATREVISOR_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou UsuariEntitatRevisor
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearUsuariEntitatRevisorGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    UsuariEntitatRevisorForm usuariEntitatRevisorForm = getUsuariEntitatRevisorForm(null, false, request, mav);
    mav.addObject("usuariEntitatRevisorForm" ,usuariEntitatRevisorForm);
    fillReferencesForForm(usuariEntitatRevisorForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public UsuariEntitatRevisorForm getUsuariEntitatRevisorForm(UsuariEntitatRevisorJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    UsuariEntitatRevisorForm usuariEntitatRevisorForm;
    if(_jpa == null) {
      usuariEntitatRevisorForm = new UsuariEntitatRevisorForm(new UsuariEntitatRevisorJPA(), true);
    } else {
      usuariEntitatRevisorForm = new UsuariEntitatRevisorForm(_jpa, false);
      usuariEntitatRevisorForm.setView(__isView);
    }
    usuariEntitatRevisorForm.setContexte(getContextWeb());
    usuariEntitatRevisorForm.setEntityNameCode(getEntityNameCode());
    usuariEntitatRevisorForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return usuariEntitatRevisorForm;
  }

  public void fillReferencesForForm(UsuariEntitatRevisorForm usuariEntitatRevisorForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (usuariEntitatRevisorForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, usuariEntitatRevisorForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      usuariEntitatRevisorForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou UsuariEntitatRevisor
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearUsuariEntitatRevisorPost(@ModelAttribute UsuariEntitatRevisorForm usuariEntitatRevisorForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    UsuariEntitatRevisorJPA usuariEntitatRevisor = usuariEntitatRevisorForm.getUsuariEntitatRevisor();

    try {
      preValidate(request, usuariEntitatRevisorForm, result);
      getWebValidator().validate(usuariEntitatRevisorForm, result);
      postValidate(request,usuariEntitatRevisorForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        usuariEntitatRevisor = create(request, usuariEntitatRevisor);
        createMessageSuccess(request, "success.creation", usuariEntitatRevisor.getUsuariEntitatRevisorId());
        usuariEntitatRevisorForm.setUsuariEntitatRevisor(usuariEntitatRevisor);
        return getRedirectWhenCreated(request, usuariEntitatRevisorForm);
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

  @RequestMapping(value = "/view/{usuariEntitatRevisorId}", method = RequestMethod.GET)
  public ModelAndView veureUsuariEntitatRevisorGet(@PathVariable("usuariEntitatRevisorId") java.lang.Long usuariEntitatRevisorId,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariEntitatRevisorGet(usuariEntitatRevisorId,
        request, response, true);
  }


  protected ModelAndView editAndViewUsuariEntitatRevisorGet(@PathVariable("usuariEntitatRevisorId") java.lang.Long usuariEntitatRevisorId,
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
    UsuariEntitatRevisorJPA usuariEntitatRevisor = findByPrimaryKey(request, usuariEntitatRevisorId);

    if (usuariEntitatRevisor == null) {
      createMessageWarning(request, "error.notfound", usuariEntitatRevisorId);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, usuariEntitatRevisorId), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      UsuariEntitatRevisorForm usuariEntitatRevisorForm = getUsuariEntitatRevisorForm(usuariEntitatRevisor, __isView, request, mav);
      usuariEntitatRevisorForm.setView(__isView);
      if(__isView) {
        usuariEntitatRevisorForm.setAllFieldsReadOnly(ALL_USUARIENTITATREVISOR_FIELDS);
        usuariEntitatRevisorForm.setSaveButtonVisible(false);
        usuariEntitatRevisorForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(usuariEntitatRevisorForm, request, mav);
      mav.addObject("usuariEntitatRevisorForm", usuariEntitatRevisorForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un UsuariEntitatRevisor existent
   */
  @RequestMapping(value = "/{usuariEntitatRevisorId}/edit", method = RequestMethod.GET)
  public ModelAndView editarUsuariEntitatRevisorGet(@PathVariable("usuariEntitatRevisorId") java.lang.Long usuariEntitatRevisorId,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariEntitatRevisorGet(usuariEntitatRevisorId,
        request, response, false);
  }



  /**
   * Editar un UsuariEntitatRevisor existent
   */
  @RequestMapping(value = "/{usuariEntitatRevisorId}/edit", method = RequestMethod.POST)
  public String editarUsuariEntitatRevisorPost(@ModelAttribute @Valid UsuariEntitatRevisorForm usuariEntitatRevisorForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    UsuariEntitatRevisorJPA usuariEntitatRevisor = usuariEntitatRevisorForm.getUsuariEntitatRevisor();

    try {
      preValidate(request, usuariEntitatRevisorForm, result);
      getWebValidator().validate(usuariEntitatRevisor, result);
      postValidate(request, usuariEntitatRevisorForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        usuariEntitatRevisor = update(request, usuariEntitatRevisor);
        createMessageSuccess(request, "success.modification", usuariEntitatRevisor.getUsuariEntitatRevisorId());
        status.setComplete();
        return getRedirectWhenModified(request, usuariEntitatRevisorForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          usuariEntitatRevisor.getUsuariEntitatRevisorId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, usuariEntitatRevisorForm, __e);
    }

  }


  /**
   * Eliminar un UsuariEntitatRevisor existent
   */
  @RequestMapping(value = "/{usuariEntitatRevisorId}/delete")
  public String eliminarUsuariEntitatRevisor(@PathVariable("usuariEntitatRevisorId") java.lang.Long usuariEntitatRevisorId,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      UsuariEntitatRevisor usuariEntitatRevisor = usuariEntitatRevisorEjb.findByPrimaryKey(usuariEntitatRevisorId);
      if (usuariEntitatRevisor == null) {
        String __msg =createMessageError(request, "error.notfound", usuariEntitatRevisorId);
        return getRedirectWhenDelete(request, usuariEntitatRevisorId, new Exception(__msg));
      } else {
        delete(request, usuariEntitatRevisor);
        createMessageSuccess(request, "success.deleted", usuariEntitatRevisorId);
        return getRedirectWhenDelete(request, usuariEntitatRevisorId,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", usuariEntitatRevisorId, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, usuariEntitatRevisorId, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute UsuariEntitatRevisorFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarUsuariEntitatRevisor(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __usuariEntitatRevisorId, Throwable e) {
    java.lang.Long usuariEntitatRevisorId = (java.lang.Long)__usuariEntitatRevisorId;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (usuariEntitatRevisorId == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(usuariEntitatRevisorId),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "usuariEntitatRevisor.usuariEntitatRevisor";
  }

  public String getEntityNameCodePlural() {
    return "usuariEntitatRevisor.usuariEntitatRevisor.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("usuariEntitatRevisor.usuariEntitatRevisorId");
  }

  @InitBinder("usuariEntitatRevisorFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("usuariEntitatRevisorForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("usuariEntitatRevisorId");

  }

  public UsuariEntitatRevisorWebValidator getWebValidator() {
    return usuariEntitatRevisorWebValidator;
  }


  public void setWebValidator(UsuariEntitatRevisorWebValidator __val) {
    if (__val != null) {
      this.usuariEntitatRevisorWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de UsuariEntitatRevisor
   */
  @RequestMapping(value = "/{usuariEntitatRevisorId}/cancel")
  public String cancelUsuariEntitatRevisor(@PathVariable("usuariEntitatRevisorId") java.lang.Long usuariEntitatRevisorId,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, usuariEntitatRevisorId);
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
       ModelAndView mav, UsuariEntitatRevisorForm usuariEntitatRevisorForm, Where where)  throws I18NException {
    if (usuariEntitatRevisorForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (usuariEntitatRevisorForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatRevisorForm.getUsuariEntitatRevisor().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatRevisorFilterForm usuariEntitatRevisorFilterForm,
       List<UsuariEntitatRevisor> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariEntitatRevisorFilterForm.isHiddenField(USUARIENTITATID)
      && !usuariEntitatRevisorFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (UsuariEntitatRevisor _item : list) {
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


  public void preValidate(HttpServletRequest request,UsuariEntitatRevisorForm usuariEntitatRevisorForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,UsuariEntitatRevisorForm usuariEntitatRevisorForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, UsuariEntitatRevisorFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, UsuariEntitatRevisorFilterForm filterForm,  List<UsuariEntitatRevisor> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, UsuariEntitatRevisorForm usuariEntitatRevisorForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, UsuariEntitatRevisorForm usuariEntitatRevisorForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long usuariEntitatRevisorId, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariEntitatRevisorId) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "usuariEntitatRevisorFormWebDB";
  }

  public String getTileList() {
    return "usuariEntitatRevisorListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "UsuariEntitatRevisorWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public UsuariEntitatRevisorJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long usuariEntitatRevisorId) throws I18NException {
    return (UsuariEntitatRevisorJPA) usuariEntitatRevisorEjb.findByPrimaryKey(usuariEntitatRevisorId);
  }


  public UsuariEntitatRevisorJPA create(HttpServletRequest request, UsuariEntitatRevisorJPA usuariEntitatRevisor)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatRevisorJPA) usuariEntitatRevisorEjb.create(usuariEntitatRevisor);
  }


  public UsuariEntitatRevisorJPA update(HttpServletRequest request, UsuariEntitatRevisorJPA usuariEntitatRevisor)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatRevisorJPA) usuariEntitatRevisorEjb.update(usuariEntitatRevisor);
  }


  public void delete(HttpServletRequest request, UsuariEntitatRevisor usuariEntitatRevisor) throws Exception,I18NException {
    usuariEntitatRevisorEjb.delete(usuariEntitatRevisor);
  }

} // Final de Classe

