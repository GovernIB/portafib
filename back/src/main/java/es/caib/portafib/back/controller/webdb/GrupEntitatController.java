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
import es.caib.portafib.back.form.webdb.GrupEntitatForm;

import es.caib.portafib.back.validator.webdb.GrupEntitatWebValidator;

import es.caib.portafib.jpa.GrupEntitatJPA;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un GrupEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/grupEntitat")
@SessionAttributes(types = { GrupEntitatForm.class, GrupEntitatFilterForm.class })
public class GrupEntitatController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<GrupEntitat, java.lang.Long> implements GrupEntitatFields {

  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;

  @Autowired
  private GrupEntitatWebValidator grupEntitatWebValidator;

  @Autowired
  protected GrupEntitatRefList grupEntitatRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes GrupEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    GrupEntitatFilterForm ff;
    ff = (GrupEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar GrupEntitat de forma paginada
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
    llistat(mav, request, getGrupEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public GrupEntitatFilterForm getGrupEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    GrupEntitatFilterForm grupEntitatFilterForm;
    grupEntitatFilterForm = (GrupEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(grupEntitatFilterForm == null) {
      grupEntitatFilterForm = new GrupEntitatFilterForm();
      grupEntitatFilterForm.setContexte(getContextWeb());
      grupEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      grupEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      grupEntitatFilterForm.setNou(true);
    } else {
      grupEntitatFilterForm.setNou(false);
    }
    grupEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return grupEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar GrupEntitat de forma paginada
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
      @ModelAttribute GrupEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getGrupEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de GrupEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<GrupEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     GrupEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<GrupEntitat> grupEntitat = processarLlistat(grupEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("grupEntitatItems", grupEntitat);

    mav.addObject("grupEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, grupEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, grupEntitat);

    return grupEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(GrupEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<GrupEntitat> list, List<GroupByItem> groupItems) throws I18NException {
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
    GrupEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<GrupEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_GRUPENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou GrupEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearGrupEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    GrupEntitatForm grupEntitatForm = getGrupEntitatForm(null, false, request, mav);
    mav.addObject("grupEntitatForm" ,grupEntitatForm);
    fillReferencesForForm(grupEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public GrupEntitatForm getGrupEntitatForm(GrupEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    GrupEntitatForm grupEntitatForm;
    if(_jpa == null) {
      grupEntitatForm = new GrupEntitatForm(new GrupEntitatJPA(), true);
    } else {
      grupEntitatForm = new GrupEntitatForm(_jpa, false);
      grupEntitatForm.setView(__isView);
    }
    grupEntitatForm.setContexte(getContextWeb());
    grupEntitatForm.setEntityNameCode(getEntityNameCode());
    grupEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return grupEntitatForm;
  }

  public void fillReferencesForForm(GrupEntitatForm grupEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (grupEntitatForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, grupEntitatForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      grupEntitatForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou GrupEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearGrupEntitatPost(@ModelAttribute GrupEntitatForm grupEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    GrupEntitatJPA grupEntitat = grupEntitatForm.getGrupEntitat();

    try {
      preValidate(request, grupEntitatForm, result);
      getWebValidator().validate(grupEntitatForm, result);
      postValidate(request,grupEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupEntitat = create(request, grupEntitat);
        createMessageSuccess(request, "success.creation", grupEntitat.getGrupEntitatID());
        grupEntitatForm.setGrupEntitat(grupEntitat);
        return getRedirectWhenCreated(request, grupEntitatForm);
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

  @RequestMapping(value = "/view/{grupEntitatID}", method = RequestMethod.GET)
  public ModelAndView veureGrupEntitatGet(@PathVariable("grupEntitatID") java.lang.Long grupEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupEntitatGet(grupEntitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewGrupEntitatGet(@PathVariable("grupEntitatID") java.lang.Long grupEntitatID,
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
    GrupEntitatJPA grupEntitat = findByPrimaryKey(request, grupEntitatID);

    if (grupEntitat == null) {
      createMessageWarning(request, "error.notfound", grupEntitatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, grupEntitatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      GrupEntitatForm grupEntitatForm = getGrupEntitatForm(grupEntitat, __isView, request, mav);
      grupEntitatForm.setView(__isView);
      if(__isView) {
        grupEntitatForm.setAllFieldsReadOnly(ALL_GRUPENTITAT_FIELDS);
        grupEntitatForm.setSaveButtonVisible(false);
        grupEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(grupEntitatForm, request, mav);
      mav.addObject("grupEntitatForm", grupEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un GrupEntitat existent
   */
  @RequestMapping(value = "/{grupEntitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarGrupEntitatGet(@PathVariable("grupEntitatID") java.lang.Long grupEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupEntitatGet(grupEntitatID,
        request, response, false);
  }



  /**
   * Editar un GrupEntitat existent
   */
  @RequestMapping(value = "/{grupEntitatID}/edit", method = RequestMethod.POST)
  public String editarGrupEntitatPost(@ModelAttribute @Valid GrupEntitatForm grupEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    GrupEntitatJPA grupEntitat = grupEntitatForm.getGrupEntitat();

    try {
      preValidate(request, grupEntitatForm, result);
      getWebValidator().validate(grupEntitat, result);
      postValidate(request, grupEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupEntitat = update(request, grupEntitat);
        createMessageSuccess(request, "success.modification", grupEntitat.getGrupEntitatID());
        status.setComplete();
        return getRedirectWhenModified(request, grupEntitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          grupEntitat.getGrupEntitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, grupEntitatForm, __e);
    }

  }


  /**
   * Eliminar un GrupEntitat existent
   */
  @RequestMapping(value = "/{grupEntitatID}/delete")
  public String eliminarGrupEntitat(@PathVariable("grupEntitatID") java.lang.Long grupEntitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      GrupEntitat grupEntitat = findByPrimaryKey(request, grupEntitatID);
      if (grupEntitat == null) {
        String __msg =createMessageError(request, "error.notfound", grupEntitatID);
        return getRedirectWhenDelete(request, grupEntitatID, new Exception(__msg));
      } else {
        delete(request, grupEntitat);
        createMessageSuccess(request, "success.deleted", grupEntitatID);
        return getRedirectWhenDelete(request, grupEntitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", grupEntitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, grupEntitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute GrupEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarGrupEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __grupEntitatID, Throwable e) {
    java.lang.Long grupEntitatID = (java.lang.Long)__grupEntitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (grupEntitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(grupEntitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "grupEntitat.grupEntitat";
  }

  public String getEntityNameCodePlural() {
    return "grupEntitat.grupEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("grupEntitat.grupEntitatID");
  }

  @InitBinder("grupEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("grupEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "grupEntitat.grupEntitatID");
  }

  public GrupEntitatWebValidator getWebValidator() {
    return grupEntitatWebValidator;
  }


  public void setWebValidator(GrupEntitatWebValidator __val) {
    if (__val != null) {
      this.grupEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de GrupEntitat
   */
  @RequestMapping(value = "/{grupEntitatID}/cancel")
  public String cancelGrupEntitat(@PathVariable("grupEntitatID") java.lang.Long grupEntitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, grupEntitatID);
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
       ModelAndView mav, GrupEntitatForm grupEntitatForm, Where where)  throws I18NException {
    if (grupEntitatForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (grupEntitatForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(grupEntitatForm.getGrupEntitat().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, GrupEntitatFilterForm grupEntitatFilterForm,
       List<GrupEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupEntitatFilterForm.isHiddenField(ENTITATID)
      && !grupEntitatFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (GrupEntitat _item : list) {
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


  public void preValidate(HttpServletRequest request,GrupEntitatForm grupEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,GrupEntitatForm grupEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, GrupEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, GrupEntitatFilterForm filterForm,  List<GrupEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, GrupEntitatForm grupEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, GrupEntitatForm grupEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long grupEntitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupEntitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "grupEntitatFormWebDB";
  }

  public String getTileList() {
    return "grupEntitatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "GrupEntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public GrupEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long grupEntitatID) throws I18NException {
    return (GrupEntitatJPA) grupEntitatEjb.findByPrimaryKey(grupEntitatID);
  }


  public GrupEntitatJPA create(HttpServletRequest request, GrupEntitatJPA grupEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (GrupEntitatJPA) grupEntitatEjb.create(grupEntitat);
  }


  public GrupEntitatJPA update(HttpServletRequest request, GrupEntitatJPA grupEntitat)
    throws Exception,I18NException, I18NValidationException {
    return (GrupEntitatJPA) grupEntitatEjb.update(grupEntitat);
  }


  public void delete(HttpServletRequest request, GrupEntitat grupEntitat) throws Exception,I18NException {
    grupEntitatEjb.delete(grupEntitat);
  }

} // Final de Classe

