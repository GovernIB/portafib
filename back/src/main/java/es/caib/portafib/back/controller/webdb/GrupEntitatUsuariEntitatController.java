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
import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatForm;

import es.caib.portafib.back.validator.webdb.GrupEntitatUsuariEntitatWebValidator;

import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPA;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un GrupEntitatUsuariEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/grupEntitatUsuariEntitat")
@SessionAttributes(types = { GrupEntitatUsuariEntitatForm.class, GrupEntitatUsuariEntitatFilterForm.class })
public class GrupEntitatUsuariEntitatController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<GrupEntitatUsuariEntitat, java.lang.Long> implements GrupEntitatUsuariEntitatFields {

  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatService grupEntitatUsuariEntitatEjb;

  @Autowired
  private GrupEntitatUsuariEntitatWebValidator grupEntitatUsuariEntitatWebValidator;

  @Autowired
  protected GrupEntitatUsuariEntitatRefList grupEntitatUsuariEntitatRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected GrupEntitatRefList grupEntitatRefList;

  /**
   * Llistat de totes GrupEntitatUsuariEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    GrupEntitatUsuariEntitatFilterForm ff;
    ff = (GrupEntitatUsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar GrupEntitatUsuariEntitat de forma paginada
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
    llistat(mav, request, getGrupEntitatUsuariEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public GrupEntitatUsuariEntitatFilterForm getGrupEntitatUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm;
    grupEntitatUsuariEntitatFilterForm = (GrupEntitatUsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(grupEntitatUsuariEntitatFilterForm == null) {
      grupEntitatUsuariEntitatFilterForm = new GrupEntitatUsuariEntitatFilterForm();
      grupEntitatUsuariEntitatFilterForm.setContexte(getContextWeb());
      grupEntitatUsuariEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      grupEntitatUsuariEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      grupEntitatUsuariEntitatFilterForm.setNou(true);
    } else {
      grupEntitatUsuariEntitatFilterForm.setNou(false);
    }
    grupEntitatUsuariEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return grupEntitatUsuariEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar GrupEntitatUsuariEntitat de forma paginada
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
      @ModelAttribute GrupEntitatUsuariEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getGrupEntitatUsuariEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de GrupEntitatUsuariEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<GrupEntitatUsuariEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     GrupEntitatUsuariEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<GrupEntitatUsuariEntitat> grupEntitatUsuariEntitat = processarLlistat(grupEntitatUsuariEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("grupEntitatUsuariEntitatItems", grupEntitatUsuariEntitat);

    mav.addObject("grupEntitatUsuariEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, grupEntitatUsuariEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, grupEntitatUsuariEntitat);

    return grupEntitatUsuariEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(GrupEntitatUsuariEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<GrupEntitatUsuariEntitat> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field grupEntitatID
    {
      _listSKV = getReferenceListForGrupEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfGrupEntitatForGrupEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(GRUPENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, GRUPENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    GrupEntitatUsuariEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<GrupEntitatUsuariEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_GRUPENTITATUSUARIENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(GRUPENTITATID, filterForm.getMapOfGrupEntitatForGrupEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou GrupEntitatUsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearGrupEntitatUsuariEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm = getGrupEntitatUsuariEntitatForm(null, false, request, mav);
    mav.addObject("grupEntitatUsuariEntitatForm" ,grupEntitatUsuariEntitatForm);
    fillReferencesForForm(grupEntitatUsuariEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public GrupEntitatUsuariEntitatForm getGrupEntitatUsuariEntitatForm(GrupEntitatUsuariEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm;
    if(_jpa == null) {
      grupEntitatUsuariEntitatForm = new GrupEntitatUsuariEntitatForm(new GrupEntitatUsuariEntitatJPA(), true);
    } else {
      grupEntitatUsuariEntitatForm = new GrupEntitatUsuariEntitatForm(_jpa, false);
      grupEntitatUsuariEntitatForm.setView(__isView);
    }
    grupEntitatUsuariEntitatForm.setContexte(getContextWeb());
    grupEntitatUsuariEntitatForm.setEntityNameCode(getEntityNameCode());
    grupEntitatUsuariEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return grupEntitatUsuariEntitatForm;
  }

  public void fillReferencesForForm(GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (grupEntitatUsuariEntitatForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, grupEntitatUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      grupEntitatUsuariEntitatForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (grupEntitatUsuariEntitatForm.getListOfGrupEntitatForGrupEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGrupEntitatID(request, mav, grupEntitatUsuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      grupEntitatUsuariEntitatForm.setListOfGrupEntitatForGrupEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou GrupEntitatUsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearGrupEntitatUsuariEntitatPost(@ModelAttribute GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat = grupEntitatUsuariEntitatForm.getGrupEntitatUsuariEntitat();

    try {
      preValidate(request, grupEntitatUsuariEntitatForm, result);
      getWebValidator().validate(grupEntitatUsuariEntitatForm, result);
      postValidate(request,grupEntitatUsuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupEntitatUsuariEntitat = create(request, grupEntitatUsuariEntitat);
        createMessageSuccess(request, "success.creation", grupEntitatUsuariEntitat.getGrupEntitatUsuariEntitatID());
        grupEntitatUsuariEntitatForm.setGrupEntitatUsuariEntitat(grupEntitatUsuariEntitat);
        return getRedirectWhenCreated(request, grupEntitatUsuariEntitatForm);
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

  @RequestMapping(value = "/view/{grupEntitatUsuariEntitatID}", method = RequestMethod.GET)
  public ModelAndView veureGrupEntitatUsuariEntitatGet(@PathVariable("grupEntitatUsuariEntitatID") java.lang.Long grupEntitatUsuariEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupEntitatUsuariEntitatGet(grupEntitatUsuariEntitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewGrupEntitatUsuariEntitatGet(@PathVariable("grupEntitatUsuariEntitatID") java.lang.Long grupEntitatUsuariEntitatID,
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
    GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat = findByPrimaryKey(request, grupEntitatUsuariEntitatID);

    if (grupEntitatUsuariEntitat == null) {
      createMessageWarning(request, "error.notfound", grupEntitatUsuariEntitatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, grupEntitatUsuariEntitatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm = getGrupEntitatUsuariEntitatForm(grupEntitatUsuariEntitat, __isView, request, mav);
      grupEntitatUsuariEntitatForm.setView(__isView);
      if(__isView) {
        grupEntitatUsuariEntitatForm.setAllFieldsReadOnly(ALL_GRUPENTITATUSUARIENTITAT_FIELDS);
        grupEntitatUsuariEntitatForm.setSaveButtonVisible(false);
        grupEntitatUsuariEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(grupEntitatUsuariEntitatForm, request, mav);
      mav.addObject("grupEntitatUsuariEntitatForm", grupEntitatUsuariEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un GrupEntitatUsuariEntitat existent
   */
  @RequestMapping(value = "/{grupEntitatUsuariEntitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarGrupEntitatUsuariEntitatGet(@PathVariable("grupEntitatUsuariEntitatID") java.lang.Long grupEntitatUsuariEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupEntitatUsuariEntitatGet(grupEntitatUsuariEntitatID,
        request, response, false);
  }



  /**
   * Editar un GrupEntitatUsuariEntitat existent
   */
  @RequestMapping(value = "/{grupEntitatUsuariEntitatID}/edit", method = RequestMethod.POST)
  public String editarGrupEntitatUsuariEntitatPost(@ModelAttribute GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat = grupEntitatUsuariEntitatForm.getGrupEntitatUsuariEntitat();

    try {
      preValidate(request, grupEntitatUsuariEntitatForm, result);
      getWebValidator().validate(grupEntitatUsuariEntitatForm, result);
      postValidate(request, grupEntitatUsuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupEntitatUsuariEntitat = update(request, grupEntitatUsuariEntitat);
        createMessageSuccess(request, "success.modification", grupEntitatUsuariEntitat.getGrupEntitatUsuariEntitatID());
        status.setComplete();
        return getRedirectWhenModified(request, grupEntitatUsuariEntitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          grupEntitatUsuariEntitat.getGrupEntitatUsuariEntitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, grupEntitatUsuariEntitatForm, __e);
    }

  }


  /**
   * Eliminar un GrupEntitatUsuariEntitat existent
   */
  @RequestMapping(value = "/{grupEntitatUsuariEntitatID}/delete")
  public String eliminarGrupEntitatUsuariEntitat(@PathVariable("grupEntitatUsuariEntitatID") java.lang.Long grupEntitatUsuariEntitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      GrupEntitatUsuariEntitat grupEntitatUsuariEntitat = grupEntitatUsuariEntitatEjb.findByPrimaryKey(grupEntitatUsuariEntitatID);
      if (grupEntitatUsuariEntitat == null) {
        String __msg =createMessageError(request, "error.notfound", grupEntitatUsuariEntitatID);
        return getRedirectWhenDelete(request, grupEntitatUsuariEntitatID, new Exception(__msg));
      } else {
        delete(request, grupEntitatUsuariEntitat);
        createMessageSuccess(request, "success.deleted", grupEntitatUsuariEntitatID);
        return getRedirectWhenDelete(request, grupEntitatUsuariEntitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", grupEntitatUsuariEntitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, grupEntitatUsuariEntitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute GrupEntitatUsuariEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarGrupEntitatUsuariEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __grupEntitatUsuariEntitatID, Throwable e) {
    java.lang.Long grupEntitatUsuariEntitatID = (java.lang.Long)__grupEntitatUsuariEntitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (grupEntitatUsuariEntitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(grupEntitatUsuariEntitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "grupEntitatUsuariEntitat.grupEntitatUsuariEntitat";
  }

  public String getEntityNameCodePlural() {
    return "grupEntitatUsuariEntitat.grupEntitatUsuariEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("grupEntitatUsuariEntitat.grupEntitatUsuariEntitatID");
  }

  @InitBinder("grupEntitatUsuariEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("grupEntitatUsuariEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("grupEntitatUsuariEntitatID");

  }

  public GrupEntitatUsuariEntitatWebValidator getWebValidator() {
    return grupEntitatUsuariEntitatWebValidator;
  }


  public void setWebValidator(GrupEntitatUsuariEntitatWebValidator __val) {
    if (__val != null) {
      this.grupEntitatUsuariEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de GrupEntitatUsuariEntitat
   */
  @RequestMapping(value = "/{grupEntitatUsuariEntitatID}/cancel")
  public String cancelGrupEntitatUsuariEntitat(@PathVariable("grupEntitatUsuariEntitatID") java.lang.Long grupEntitatUsuariEntitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, grupEntitatUsuariEntitatID);
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
       ModelAndView mav, GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm, Where where)  throws I18NException {
    if (grupEntitatUsuariEntitatForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (grupEntitatUsuariEntitatForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(grupEntitatUsuariEntitatForm.getGrupEntitatUsuariEntitat().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm,
       List<GrupEntitatUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupEntitatUsuariEntitatFilterForm.isHiddenField(USUARIENTITATID)
      && !grupEntitatUsuariEntitatFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (GrupEntitatUsuariEntitat _item : list) {
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


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm, Where where)  throws I18NException {
    if (grupEntitatUsuariEntitatForm.isHiddenField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (grupEntitatUsuariEntitatForm.isReadOnlyField(GRUPENTITATID)) {
      _where = GrupEntitatFields.GRUPENTITATID.equal(grupEntitatUsuariEntitatForm.getGrupEntitatUsuariEntitat().getGrupEntitatID());
    }
    return getReferenceListForGrupEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForGrupEntitatID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatUsuariEntitatFilterForm grupEntitatUsuariEntitatFilterForm,
       List<GrupEntitatUsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupEntitatUsuariEntitatFilterForm.isHiddenField(GRUPENTITATID)
      && !grupEntitatUsuariEntitatFilterForm.isGroupByField(GRUPENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(GRUPENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (GrupEntitatUsuariEntitat _item : list) {
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


  public void preValidate(HttpServletRequest request,GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, GrupEntitatUsuariEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, GrupEntitatUsuariEntitatFilterForm filterForm,  List<GrupEntitatUsuariEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, GrupEntitatUsuariEntitatForm grupEntitatUsuariEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long grupEntitatUsuariEntitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupEntitatUsuariEntitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "grupEntitatUsuariEntitatFormWebDB";
  }

  public String getTileList() {
    return "grupEntitatUsuariEntitatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "GrupEntitatUsuariEntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public GrupEntitatUsuariEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long grupEntitatUsuariEntitatID) throws I18NException {
    return (GrupEntitatUsuariEntitatJPA) grupEntitatUsuariEntitatEjb.findByPrimaryKey(grupEntitatUsuariEntitatID);
  }


  public GrupEntitatUsuariEntitatJPA create(HttpServletRequest request, GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (GrupEntitatUsuariEntitatJPA) grupEntitatUsuariEntitatEjb.create(grupEntitatUsuariEntitat);
  }


  public GrupEntitatUsuariEntitatJPA update(HttpServletRequest request, GrupEntitatUsuariEntitatJPA grupEntitatUsuariEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (GrupEntitatUsuariEntitatJPA) grupEntitatUsuariEntitatEjb.update(grupEntitatUsuariEntitat);
  }


  public void delete(HttpServletRequest request, GrupEntitatUsuariEntitat grupEntitatUsuariEntitat) throws Exception,I18NException {
    grupEntitatUsuariEntitatEjb.delete(grupEntitatUsuariEntitat);
  }

} // Final de Classe

