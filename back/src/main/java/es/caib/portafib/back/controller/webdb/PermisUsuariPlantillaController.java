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
import es.caib.portafib.back.form.webdb.PermisUsuariPlantillaForm;

import es.caib.portafib.back.validator.webdb.PermisUsuariPlantillaWebValidator;

import es.caib.portafib.persistence.PermisUsuariPlantillaJPA;
import es.caib.portafib.model.entity.PermisUsuariPlantilla;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PermisUsuariPlantilla
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/permisUsuariPlantilla")
@SessionAttributes(types = { PermisUsuariPlantillaForm.class, PermisUsuariPlantillaFilterForm.class })
public class PermisUsuariPlantillaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PermisUsuariPlantilla, java.lang.Long> implements PermisUsuariPlantillaFields {

  @EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisUsuariPlantillaService permisUsuariPlantillaEjb;

  @Autowired
  private PermisUsuariPlantillaWebValidator permisUsuariPlantillaWebValidator;

  @Autowired
  protected PermisUsuariPlantillaRefList permisUsuariPlantillaRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected PlantillaFluxDeFirmesRefList plantillaFluxDeFirmesRefList;

  /**
   * Llistat de totes PermisUsuariPlantilla
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PermisUsuariPlantillaFilterForm ff;
    ff = (PermisUsuariPlantillaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PermisUsuariPlantilla de forma paginada
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
    llistat(mav, request, getPermisUsuariPlantillaFilterForm(pagina, mav, request));
    return mav;
  }

  public PermisUsuariPlantillaFilterForm getPermisUsuariPlantillaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PermisUsuariPlantillaFilterForm permisUsuariPlantillaFilterForm;
    permisUsuariPlantillaFilterForm = (PermisUsuariPlantillaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(permisUsuariPlantillaFilterForm == null) {
      permisUsuariPlantillaFilterForm = new PermisUsuariPlantillaFilterForm();
      permisUsuariPlantillaFilterForm.setContexte(getContextWeb());
      permisUsuariPlantillaFilterForm.setEntityNameCode(getEntityNameCode());
      permisUsuariPlantillaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      permisUsuariPlantillaFilterForm.setNou(true);
    } else {
      permisUsuariPlantillaFilterForm.setNou(false);
    }
    permisUsuariPlantillaFilterForm.setPage(pagina == null ? 1 : pagina);
    return permisUsuariPlantillaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PermisUsuariPlantilla de forma paginada
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
      @ModelAttribute PermisUsuariPlantillaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPermisUsuariPlantillaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PermisUsuariPlantilla de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PermisUsuariPlantilla> llistat(ModelAndView mav, HttpServletRequest request,
     PermisUsuariPlantillaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PermisUsuariPlantilla> permisUsuariPlantilla = processarLlistat(permisUsuariPlantillaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("permisUsuariPlantillaItems", permisUsuariPlantilla);

    mav.addObject("permisUsuariPlantillaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, permisUsuariPlantilla, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, permisUsuariPlantilla);

    return permisUsuariPlantilla;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PermisUsuariPlantillaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PermisUsuariPlantilla> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field plantillaFluxDeFirmesID
    {
      _listSKV = getReferenceListForPlantillaFluxDeFirmesID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID(_tmp);
      if (filterForm.getGroupByFields().contains(PLANTILLAFLUXDEFIRMESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLANTILLAFLUXDEFIRMESID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PermisUsuariPlantillaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PermisUsuariPlantilla> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PERMISUSUARIPLANTILLA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(PLANTILLAFLUXDEFIRMESID, filterForm.getMapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PermisUsuariPlantilla
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPermisUsuariPlantillaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PermisUsuariPlantillaForm permisUsuariPlantillaForm = getPermisUsuariPlantillaForm(null, false, request, mav);
    mav.addObject("permisUsuariPlantillaForm" ,permisUsuariPlantillaForm);
    fillReferencesForForm(permisUsuariPlantillaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PermisUsuariPlantillaForm getPermisUsuariPlantillaForm(PermisUsuariPlantillaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PermisUsuariPlantillaForm permisUsuariPlantillaForm;
    if(_jpa == null) {
      permisUsuariPlantillaForm = new PermisUsuariPlantillaForm(new PermisUsuariPlantillaJPA(), true);
    } else {
      permisUsuariPlantillaForm = new PermisUsuariPlantillaForm(_jpa, false);
      permisUsuariPlantillaForm.setView(__isView);
    }
    permisUsuariPlantillaForm.setContexte(getContextWeb());
    permisUsuariPlantillaForm.setEntityNameCode(getEntityNameCode());
    permisUsuariPlantillaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return permisUsuariPlantillaForm;
  }

  public void fillReferencesForForm(PermisUsuariPlantillaForm permisUsuariPlantillaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (permisUsuariPlantillaForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, permisUsuariPlantillaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      permisUsuariPlantillaForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (permisUsuariPlantillaForm.getListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPlantillaFluxDeFirmesID(request, mav, permisUsuariPlantillaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      permisUsuariPlantillaForm.setListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PermisUsuariPlantilla
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPermisUsuariPlantillaPost(@ModelAttribute PermisUsuariPlantillaForm permisUsuariPlantillaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PermisUsuariPlantillaJPA permisUsuariPlantilla = permisUsuariPlantillaForm.getPermisUsuariPlantilla();

    try {
      preValidate(request, permisUsuariPlantillaForm, result);
      getWebValidator().validate(permisUsuariPlantillaForm, result);
      postValidate(request,permisUsuariPlantillaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        permisUsuariPlantilla = create(request, permisUsuariPlantilla);
        createMessageSuccess(request, "success.creation", permisUsuariPlantilla.getPermisUsuariPlantillaID());
        permisUsuariPlantillaForm.setPermisUsuariPlantilla(permisUsuariPlantilla);
        return getRedirectWhenCreated(request, permisUsuariPlantillaForm);
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

  @RequestMapping(value = "/view/{permisUsuariPlantillaID}", method = RequestMethod.GET)
  public ModelAndView veurePermisUsuariPlantillaGet(@PathVariable("permisUsuariPlantillaID") java.lang.Long permisUsuariPlantillaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPermisUsuariPlantillaGet(permisUsuariPlantillaID,
        request, response, true);
  }


  protected ModelAndView editAndViewPermisUsuariPlantillaGet(@PathVariable("permisUsuariPlantillaID") java.lang.Long permisUsuariPlantillaID,
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
    PermisUsuariPlantillaJPA permisUsuariPlantilla = findByPrimaryKey(request, permisUsuariPlantillaID);

    if (permisUsuariPlantilla == null) {
      createMessageWarning(request, "error.notfound", permisUsuariPlantillaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, permisUsuariPlantillaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PermisUsuariPlantillaForm permisUsuariPlantillaForm = getPermisUsuariPlantillaForm(permisUsuariPlantilla, __isView, request, mav);
      permisUsuariPlantillaForm.setView(__isView);
      if(__isView) {
        permisUsuariPlantillaForm.setAllFieldsReadOnly(ALL_PERMISUSUARIPLANTILLA_FIELDS);
        permisUsuariPlantillaForm.setSaveButtonVisible(false);
        permisUsuariPlantillaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(permisUsuariPlantillaForm, request, mav);
      mav.addObject("permisUsuariPlantillaForm", permisUsuariPlantillaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PermisUsuariPlantilla existent
   */
  @RequestMapping(value = "/{permisUsuariPlantillaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPermisUsuariPlantillaGet(@PathVariable("permisUsuariPlantillaID") java.lang.Long permisUsuariPlantillaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPermisUsuariPlantillaGet(permisUsuariPlantillaID,
        request, response, false);
  }



  /**
   * Editar un PermisUsuariPlantilla existent
   */
  @RequestMapping(value = "/{permisUsuariPlantillaID}/edit", method = RequestMethod.POST)
  public String editarPermisUsuariPlantillaPost(@ModelAttribute PermisUsuariPlantillaForm permisUsuariPlantillaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PermisUsuariPlantillaJPA permisUsuariPlantilla = permisUsuariPlantillaForm.getPermisUsuariPlantilla();

    try {
      preValidate(request, permisUsuariPlantillaForm, result);
      getWebValidator().validate(permisUsuariPlantillaForm, result);
      postValidate(request, permisUsuariPlantillaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        permisUsuariPlantilla = update(request, permisUsuariPlantilla);
        createMessageSuccess(request, "success.modification", permisUsuariPlantilla.getPermisUsuariPlantillaID());
        status.setComplete();
        return getRedirectWhenModified(request, permisUsuariPlantillaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          permisUsuariPlantilla.getPermisUsuariPlantillaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, permisUsuariPlantillaForm, __e);
    }

  }


  /**
   * Eliminar un PermisUsuariPlantilla existent
   */
  @RequestMapping(value = "/{permisUsuariPlantillaID}/delete")
  public String eliminarPermisUsuariPlantilla(@PathVariable("permisUsuariPlantillaID") java.lang.Long permisUsuariPlantillaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PermisUsuariPlantilla permisUsuariPlantilla = this.findByPrimaryKey(request, permisUsuariPlantillaID);
      if (permisUsuariPlantilla == null) {
        String __msg = createMessageError(request, "error.notfound", permisUsuariPlantillaID);
        return getRedirectWhenDelete(request, permisUsuariPlantillaID, new Exception(__msg));
      } else {
        delete(request, permisUsuariPlantilla);
        createMessageSuccess(request, "success.deleted", permisUsuariPlantillaID);
        return getRedirectWhenDelete(request, permisUsuariPlantillaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", permisUsuariPlantillaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, permisUsuariPlantillaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PermisUsuariPlantillaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPermisUsuariPlantilla(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __permisUsuariPlantillaID, Throwable e) {
    java.lang.Long permisUsuariPlantillaID = (java.lang.Long)__permisUsuariPlantillaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (permisUsuariPlantillaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(permisUsuariPlantillaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "permisUsuariPlantilla.permisUsuariPlantilla";
  }

  public String getEntityNameCodePlural() {
    return "permisUsuariPlantilla.permisUsuariPlantilla.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("permisUsuariPlantilla.permisUsuariPlantillaID");
  }

  @InitBinder("permisUsuariPlantillaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("permisUsuariPlantillaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "permisUsuariPlantilla.permisUsuariPlantillaID");
  }

  public PermisUsuariPlantillaWebValidator getWebValidator() {
    return permisUsuariPlantillaWebValidator;
  }


  public void setWebValidator(PermisUsuariPlantillaWebValidator __val) {
    if (__val != null) {
      this.permisUsuariPlantillaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PermisUsuariPlantilla
   */
  @RequestMapping(value = "/{permisUsuariPlantillaID}/cancel")
  public String cancelPermisUsuariPlantilla(@PathVariable("permisUsuariPlantillaID") java.lang.Long permisUsuariPlantillaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, permisUsuariPlantillaID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de PermisUsuariPlantilla
   */
  @RequestMapping(value = "/cancel")
  public String cancelPermisUsuariPlantilla(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
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
       ModelAndView mav, PermisUsuariPlantillaForm permisUsuariPlantillaForm, Where where)  throws I18NException {
    if (permisUsuariPlantillaForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (permisUsuariPlantillaForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(permisUsuariPlantillaForm.getPermisUsuariPlantilla().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, PermisUsuariPlantillaFilterForm permisUsuariPlantillaFilterForm,
       List<PermisUsuariPlantilla> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (permisUsuariPlantillaFilterForm.isHiddenField(USUARIENTITATID)
       && !permisUsuariPlantillaFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PermisUsuariPlantilla _item : list) {
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


  public List<StringKeyValue> getReferenceListForPlantillaFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PermisUsuariPlantillaForm permisUsuariPlantillaForm, Where where)  throws I18NException {
    if (permisUsuariPlantillaForm.isHiddenField(PLANTILLAFLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (permisUsuariPlantillaForm.isReadOnlyField(PLANTILLAFLUXDEFIRMESID)) {
      _where = PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.equal(permisUsuariPlantillaForm.getPermisUsuariPlantilla().getPlantillaFluxDeFirmesID());
    }
    return getReferenceListForPlantillaFluxDeFirmesID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPlantillaFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PermisUsuariPlantillaFilterForm permisUsuariPlantillaFilterForm,
       List<PermisUsuariPlantilla> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (permisUsuariPlantillaFilterForm.isHiddenField(PLANTILLAFLUXDEFIRMESID)
       && !permisUsuariPlantillaFilterForm.isGroupByField(PLANTILLAFLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLANTILLAFLUXDEFIRMESID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PermisUsuariPlantilla _item : list) {
        _pkList.add(_item.getPlantillaFluxDeFirmesID());
        }
        _w = PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(_pkList);
      }
    return getReferenceListForPlantillaFluxDeFirmesID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPlantillaFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return plantillaFluxDeFirmesRefList.getReferenceList(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PermisUsuariPlantillaForm permisUsuariPlantillaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PermisUsuariPlantillaForm permisUsuariPlantillaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PermisUsuariPlantillaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PermisUsuariPlantillaFilterForm filterForm,  List<PermisUsuariPlantilla> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PermisUsuariPlantillaForm permisUsuariPlantillaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PermisUsuariPlantillaForm permisUsuariPlantillaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long permisUsuariPlantillaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long permisUsuariPlantillaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "permisUsuariPlantillaFormWebDB";
  }

  public String getTileList() {
    return "permisUsuariPlantillaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PermisUsuariPlantilla_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PermisUsuariPlantillaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long permisUsuariPlantillaID) throws I18NException {
    return (PermisUsuariPlantillaJPA) permisUsuariPlantillaEjb.findByPrimaryKey(permisUsuariPlantillaID);
  }


  public PermisUsuariPlantillaJPA create(HttpServletRequest request, PermisUsuariPlantillaJPA permisUsuariPlantilla)
    throws I18NException, I18NValidationException {
    return (PermisUsuariPlantillaJPA) permisUsuariPlantillaEjb.create(permisUsuariPlantilla);
  }


  public PermisUsuariPlantillaJPA update(HttpServletRequest request, PermisUsuariPlantillaJPA permisUsuariPlantilla)
    throws I18NException, I18NValidationException {
    return (PermisUsuariPlantillaJPA) permisUsuariPlantillaEjb.update(permisUsuariPlantilla);
  }


  public void delete(HttpServletRequest request, PermisUsuariPlantilla permisUsuariPlantilla) throws I18NException {
    permisUsuariPlantillaEjb.delete(permisUsuariPlantilla);
  }

} // Final de Classe

