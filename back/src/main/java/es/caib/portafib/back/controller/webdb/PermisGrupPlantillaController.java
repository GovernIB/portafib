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
import es.caib.portafib.back.form.webdb.PermisGrupPlantillaForm;

import es.caib.portafib.back.validator.webdb.PermisGrupPlantillaWebValidator;

import es.caib.portafib.jpa.PermisGrupPlantillaJPA;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PermisGrupPlantilla
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/permisGrupPlantilla")
@SessionAttributes(types = { PermisGrupPlantillaForm.class, PermisGrupPlantillaFilterForm.class })
public class PermisGrupPlantillaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PermisGrupPlantilla, java.lang.Long> implements PermisGrupPlantillaFields {

  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaLocal permisGrupPlantillaEjb;

  @Autowired
  private PermisGrupPlantillaWebValidator permisGrupPlantillaWebValidator;

  @Autowired
  protected PermisGrupPlantillaRefList permisGrupPlantillaRefList;

  // References 
  @Autowired
  protected GrupEntitatRefList grupEntitatRefList;

  // References 
  @Autowired
  protected PlantillaFluxDeFirmesRefList plantillaFluxDeFirmesRefList;

  /**
   * Llistat de totes PermisGrupPlantilla
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PermisGrupPlantillaFilterForm ff;
    ff = (PermisGrupPlantillaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PermisGrupPlantilla de forma paginada
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
    llistat(mav, request, getPermisGrupPlantillaFilterForm(pagina, mav, request));
    return mav;
  }

  public PermisGrupPlantillaFilterForm getPermisGrupPlantillaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PermisGrupPlantillaFilterForm permisGrupPlantillaFilterForm;
    permisGrupPlantillaFilterForm = (PermisGrupPlantillaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(permisGrupPlantillaFilterForm == null) {
      permisGrupPlantillaFilterForm = new PermisGrupPlantillaFilterForm();
      permisGrupPlantillaFilterForm.setContexte(getContextWeb());
      permisGrupPlantillaFilterForm.setEntityNameCode(getEntityNameCode());
      permisGrupPlantillaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      permisGrupPlantillaFilterForm.setNou(true);
    } else {
      permisGrupPlantillaFilterForm.setNou(false);
    }
    permisGrupPlantillaFilterForm.setPage(pagina == null ? 1 : pagina);
    return permisGrupPlantillaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PermisGrupPlantilla de forma paginada
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
      @ModelAttribute PermisGrupPlantillaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPermisGrupPlantillaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PermisGrupPlantilla de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PermisGrupPlantilla> llistat(ModelAndView mav, HttpServletRequest request,
     PermisGrupPlantillaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PermisGrupPlantilla> permisGrupPlantilla = processarLlistat(permisGrupPlantillaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("permisGrupPlantillaItems", permisGrupPlantilla);

    mav.addObject("permisGrupPlantillaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, permisGrupPlantilla, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, permisGrupPlantilla);

    return permisGrupPlantilla;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PermisGrupPlantillaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PermisGrupPlantilla> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field grupEntitatID
    {
      _listSKV = getReferenceListForGrupEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfGrupEntitatForGrupEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(GRUPENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, GRUPENTITATID, false);
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
    PermisGrupPlantillaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PermisGrupPlantilla> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PERMISGRUPPLANTILLA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(GRUPENTITATID, filterForm.getMapOfGrupEntitatForGrupEntitatID());
    __mapping.put(PLANTILLAFLUXDEFIRMESID, filterForm.getMapOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PermisGrupPlantilla
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPermisGrupPlantillaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PermisGrupPlantillaForm permisGrupPlantillaForm = getPermisGrupPlantillaForm(null, false, request, mav);
    mav.addObject("permisGrupPlantillaForm" ,permisGrupPlantillaForm);
    fillReferencesForForm(permisGrupPlantillaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PermisGrupPlantillaForm getPermisGrupPlantillaForm(PermisGrupPlantillaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PermisGrupPlantillaForm permisGrupPlantillaForm;
    if(_jpa == null) {
      permisGrupPlantillaForm = new PermisGrupPlantillaForm(new PermisGrupPlantillaJPA(), true);
    } else {
      permisGrupPlantillaForm = new PermisGrupPlantillaForm(_jpa, false);
      permisGrupPlantillaForm.setView(__isView);
    }
    permisGrupPlantillaForm.setContexte(getContextWeb());
    permisGrupPlantillaForm.setEntityNameCode(getEntityNameCode());
    permisGrupPlantillaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return permisGrupPlantillaForm;
  }

  public void fillReferencesForForm(PermisGrupPlantillaForm permisGrupPlantillaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (permisGrupPlantillaForm.getListOfGrupEntitatForGrupEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGrupEntitatID(request, mav, permisGrupPlantillaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      permisGrupPlantillaForm.setListOfGrupEntitatForGrupEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (permisGrupPlantillaForm.getListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPlantillaFluxDeFirmesID(request, mav, permisGrupPlantillaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      permisGrupPlantillaForm.setListOfPlantillaFluxDeFirmesForPlantillaFluxDeFirmesID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PermisGrupPlantilla
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPermisGrupPlantillaPost(@ModelAttribute PermisGrupPlantillaForm permisGrupPlantillaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PermisGrupPlantillaJPA permisGrupPlantilla = permisGrupPlantillaForm.getPermisGrupPlantilla();

    try {
      preValidate(request, permisGrupPlantillaForm, result);
      getWebValidator().validate(permisGrupPlantillaForm, result);
      postValidate(request,permisGrupPlantillaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        permisGrupPlantilla = create(request, permisGrupPlantilla);
        createMessageSuccess(request, "success.creation", permisGrupPlantilla.getPermisGrupPlantillaID());
        permisGrupPlantillaForm.setPermisGrupPlantilla(permisGrupPlantilla);
        return getRedirectWhenCreated(request, permisGrupPlantillaForm);
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

  @RequestMapping(value = "/view/{permisGrupPlantillaID}", method = RequestMethod.GET)
  public ModelAndView veurePermisGrupPlantillaGet(@PathVariable("permisGrupPlantillaID") java.lang.Long permisGrupPlantillaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPermisGrupPlantillaGet(permisGrupPlantillaID,
        request, response, true);
  }


  protected ModelAndView editAndViewPermisGrupPlantillaGet(@PathVariable("permisGrupPlantillaID") java.lang.Long permisGrupPlantillaID,
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
    PermisGrupPlantillaJPA permisGrupPlantilla = findByPrimaryKey(request, permisGrupPlantillaID);

    if (permisGrupPlantilla == null) {
      createMessageWarning(request, "error.notfound", permisGrupPlantillaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, permisGrupPlantillaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PermisGrupPlantillaForm permisGrupPlantillaForm = getPermisGrupPlantillaForm(permisGrupPlantilla, __isView, request, mav);
      permisGrupPlantillaForm.setView(__isView);
      if(__isView) {
        permisGrupPlantillaForm.setAllFieldsReadOnly(ALL_PERMISGRUPPLANTILLA_FIELDS);
        permisGrupPlantillaForm.setSaveButtonVisible(false);
        permisGrupPlantillaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(permisGrupPlantillaForm, request, mav);
      mav.addObject("permisGrupPlantillaForm", permisGrupPlantillaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PermisGrupPlantilla existent
   */
  @RequestMapping(value = "/{permisGrupPlantillaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPermisGrupPlantillaGet(@PathVariable("permisGrupPlantillaID") java.lang.Long permisGrupPlantillaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPermisGrupPlantillaGet(permisGrupPlantillaID,
        request, response, false);
  }



  /**
   * Editar un PermisGrupPlantilla existent
   */
  @RequestMapping(value = "/{permisGrupPlantillaID}/edit", method = RequestMethod.POST)
  public String editarPermisGrupPlantillaPost(@ModelAttribute @Valid PermisGrupPlantillaForm permisGrupPlantillaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PermisGrupPlantillaJPA permisGrupPlantilla = permisGrupPlantillaForm.getPermisGrupPlantilla();

    try {
      preValidate(request, permisGrupPlantillaForm, result);
      getWebValidator().validate(permisGrupPlantilla, result);
      postValidate(request, permisGrupPlantillaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        permisGrupPlantilla = update(request, permisGrupPlantilla);
        createMessageSuccess(request, "success.modification", permisGrupPlantilla.getPermisGrupPlantillaID());
        status.setComplete();
        return getRedirectWhenModified(request, permisGrupPlantillaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          permisGrupPlantilla.getPermisGrupPlantillaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, permisGrupPlantillaForm, __e);
    }

  }


  /**
   * Eliminar un PermisGrupPlantilla existent
   */
  @RequestMapping(value = "/{permisGrupPlantillaID}/delete")
  public String eliminarPermisGrupPlantilla(@PathVariable("permisGrupPlantillaID") java.lang.Long permisGrupPlantillaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PermisGrupPlantilla permisGrupPlantilla = permisGrupPlantillaEjb.findByPrimaryKey(permisGrupPlantillaID);
      if (permisGrupPlantilla == null) {
        String __msg =createMessageError(request, "error.notfound", permisGrupPlantillaID);
        return getRedirectWhenDelete(request, permisGrupPlantillaID, new Exception(__msg));
      } else {
        delete(request, permisGrupPlantilla);
        createMessageSuccess(request, "success.deleted", permisGrupPlantillaID);
        return getRedirectWhenDelete(request, permisGrupPlantillaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", permisGrupPlantillaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, permisGrupPlantillaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PermisGrupPlantillaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPermisGrupPlantilla(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __permisGrupPlantillaID, Throwable e) {
    java.lang.Long permisGrupPlantillaID = (java.lang.Long)__permisGrupPlantillaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (permisGrupPlantillaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(permisGrupPlantillaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "permisGrupPlantilla.permisGrupPlantilla";
  }

  public String getEntityNameCodePlural() {
    return "permisGrupPlantilla.permisGrupPlantilla.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("permisGrupPlantilla.permisGrupPlantillaID");
  }

  @InitBinder("permisGrupPlantillaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("permisGrupPlantillaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("permisGrupPlantillaID");

  }

  public PermisGrupPlantillaWebValidator getWebValidator() {
    return permisGrupPlantillaWebValidator;
  }


  public void setWebValidator(PermisGrupPlantillaWebValidator __val) {
    if (__val != null) {
      this.permisGrupPlantillaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PermisGrupPlantilla
   */
  @RequestMapping(value = "/{permisGrupPlantillaID}/cancel")
  public String cancelPermisGrupPlantilla(@PathVariable("permisGrupPlantillaID") java.lang.Long permisGrupPlantillaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, permisGrupPlantillaID);
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


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, PermisGrupPlantillaForm permisGrupPlantillaForm, Where where)  throws I18NException {
    if (permisGrupPlantillaForm.isHiddenField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (permisGrupPlantillaForm.isReadOnlyField(GRUPENTITATID)) {
      _where = GrupEntitatFields.GRUPENTITATID.equal(permisGrupPlantillaForm.getPermisGrupPlantilla().getGrupEntitatID());
    }
    return getReferenceListForGrupEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, PermisGrupPlantillaFilterForm permisGrupPlantillaFilterForm,
       List<PermisGrupPlantilla> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (permisGrupPlantillaFilterForm.isHiddenField(GRUPENTITATID)
      && !permisGrupPlantillaFilterForm.isGroupByField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(GRUPENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PermisGrupPlantilla _item : list) {
        _pkList.add(_item.getGrupEntitatID());
        }
        _w = GrupEntitatFields.GRUPENTITATID.in(_pkList);
      }
    return getReferenceListForGrupEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return grupEntitatRefList.getReferenceList(GrupEntitatFields.GRUPENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForPlantillaFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PermisGrupPlantillaForm permisGrupPlantillaForm, Where where)  throws I18NException {
    if (permisGrupPlantillaForm.isHiddenField(PLANTILLAFLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (permisGrupPlantillaForm.isReadOnlyField(PLANTILLAFLUXDEFIRMESID)) {
      _where = PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.equal(permisGrupPlantillaForm.getPermisGrupPlantilla().getPlantillaFluxDeFirmesID());
    }
    return getReferenceListForPlantillaFluxDeFirmesID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPlantillaFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PermisGrupPlantillaFilterForm permisGrupPlantillaFilterForm,
       List<PermisGrupPlantilla> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (permisGrupPlantillaFilterForm.isHiddenField(PLANTILLAFLUXDEFIRMESID)
      && !permisGrupPlantillaFilterForm.isGroupByField(PLANTILLAFLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLANTILLAFLUXDEFIRMESID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PermisGrupPlantilla _item : list) {
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


  public void preValidate(HttpServletRequest request,PermisGrupPlantillaForm permisGrupPlantillaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PermisGrupPlantillaForm permisGrupPlantillaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PermisGrupPlantillaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PermisGrupPlantillaFilterForm filterForm,  List<PermisGrupPlantilla> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PermisGrupPlantillaForm permisGrupPlantillaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PermisGrupPlantillaForm permisGrupPlantillaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long permisGrupPlantillaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long permisGrupPlantillaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "permisGrupPlantillaFormWebDB";
  }

  public String getTileList() {
    return "permisGrupPlantillaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PermisGrupPlantillaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PermisGrupPlantillaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long permisGrupPlantillaID) throws I18NException {
    return (PermisGrupPlantillaJPA) permisGrupPlantillaEjb.findByPrimaryKey(permisGrupPlantillaID);
  }


  public PermisGrupPlantillaJPA create(HttpServletRequest request, PermisGrupPlantillaJPA permisGrupPlantilla)
    throws Exception,I18NException, I18NValidationException {
    return (PermisGrupPlantillaJPA) permisGrupPlantillaEjb.create(permisGrupPlantilla);
  }


  public PermisGrupPlantillaJPA update(HttpServletRequest request, PermisGrupPlantillaJPA permisGrupPlantilla)
    throws Exception,I18NException, I18NValidationException {
    return (PermisGrupPlantillaJPA) permisGrupPlantillaEjb.update(permisGrupPlantilla);
  }


  public void delete(HttpServletRequest request, PermisGrupPlantilla permisGrupPlantilla) throws Exception,I18NException {
    permisGrupPlantillaEjb.delete(permisGrupPlantilla);
  }

} // Final de Classe

