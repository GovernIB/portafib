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
import es.caib.portafib.back.form.webdb.EstadisticaForm;

import es.caib.portafib.back.validator.webdb.EstadisticaWebValidator;

import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Estadistica
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/estadistica")
@SessionAttributes(types = { EstadisticaForm.class, EstadisticaFilterForm.class })
public class EstadisticaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<Estadistica, java.lang.Long> implements EstadisticaFields {

  @EJB(mappedName = es.caib.portafib.ejb.EstadisticaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EstadisticaLocal estadisticaEjb;

  @Autowired
  private EstadisticaWebValidator estadisticaWebValidator;

  @Autowired
  protected EstadisticaRefList estadisticaRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Estadistica
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EstadisticaFilterForm ff;
    ff = (EstadisticaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Estadistica de forma paginada
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
    llistat(mav, request, getEstadisticaFilterForm(pagina, mav, request));
    return mav;
  }

  public EstadisticaFilterForm getEstadisticaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EstadisticaFilterForm estadisticaFilterForm;
    estadisticaFilterForm = (EstadisticaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(estadisticaFilterForm == null) {
      estadisticaFilterForm = new EstadisticaFilterForm();
      estadisticaFilterForm.setContexte(getContextWeb());
      estadisticaFilterForm.setEntityNameCode(getEntityNameCode());
      estadisticaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      estadisticaFilterForm.setNou(true);
    } else {
      estadisticaFilterForm.setNou(false);
    }
    estadisticaFilterForm.setPage(pagina == null ? 1 : pagina);
    return estadisticaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Estadistica de forma paginada
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
      @ModelAttribute EstadisticaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEstadisticaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Estadistica de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Estadistica> llistat(ModelAndView mav, HttpServletRequest request,
     EstadisticaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Estadistica> estadistica = processarLlistat(estadisticaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("estadisticaItems", estadistica);

    mav.addObject("estadisticaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, estadistica, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, estadistica);

    return estadistica;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EstadisticaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Estadistica> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }

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
    EstadisticaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Estadistica> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ESTADISTICA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Estadistica
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEstadisticaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EstadisticaForm estadisticaForm = getEstadisticaForm(null, false, request, mav);
    mav.addObject("estadisticaForm" ,estadisticaForm);
    fillReferencesForForm(estadisticaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EstadisticaForm getEstadisticaForm(EstadisticaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EstadisticaForm estadisticaForm;
    if(_jpa == null) {
      estadisticaForm = new EstadisticaForm(new EstadisticaJPA(), true);
    } else {
      estadisticaForm = new EstadisticaForm(_jpa, false);
      estadisticaForm.setView(__isView);
    }
    estadisticaForm.setContexte(getContextWeb());
    estadisticaForm.setEntityNameCode(getEntityNameCode());
    estadisticaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return estadisticaForm;
  }

  public void fillReferencesForForm(EstadisticaForm estadisticaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (estadisticaForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, estadisticaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      estadisticaForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (estadisticaForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, estadisticaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      estadisticaForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Estadistica
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEstadisticaPost(@ModelAttribute EstadisticaForm estadisticaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EstadisticaJPA estadistica = estadisticaForm.getEstadistica();

    try {
      preValidate(request, estadisticaForm, result);
      getWebValidator().validate(estadisticaForm, result);
      postValidate(request,estadisticaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estadistica = create(request, estadistica);
        createMessageSuccess(request, "success.creation", estadistica.getEstadisticaID());
        estadisticaForm.setEstadistica(estadistica);
        return getRedirectWhenCreated(request, estadisticaForm);
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

  @RequestMapping(value = "/view/{estadisticaID}", method = RequestMethod.GET)
  public ModelAndView veureEstadisticaGet(@PathVariable("estadisticaID") java.lang.Long estadisticaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstadisticaGet(estadisticaID,
        request, response, true);
  }


  protected ModelAndView editAndViewEstadisticaGet(@PathVariable("estadisticaID") java.lang.Long estadisticaID,
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
    EstadisticaJPA estadistica = findByPrimaryKey(request, estadisticaID);

    if (estadistica == null) {
      createMessageWarning(request, "error.notfound", estadisticaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, estadisticaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EstadisticaForm estadisticaForm = getEstadisticaForm(estadistica, __isView, request, mav);
      estadisticaForm.setView(__isView);
      if(__isView) {
        estadisticaForm.setAllFieldsReadOnly(ALL_ESTADISTICA_FIELDS);
        estadisticaForm.setSaveButtonVisible(false);
        estadisticaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(estadisticaForm, request, mav);
      mav.addObject("estadisticaForm", estadisticaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Estadistica existent
   */
  @RequestMapping(value = "/{estadisticaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEstadisticaGet(@PathVariable("estadisticaID") java.lang.Long estadisticaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstadisticaGet(estadisticaID,
        request, response, false);
  }



  /**
   * Editar un Estadistica existent
   */
  @RequestMapping(value = "/{estadisticaID}/edit", method = RequestMethod.POST)
  public String editarEstadisticaPost(@ModelAttribute @Valid EstadisticaForm estadisticaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EstadisticaJPA estadistica = estadisticaForm.getEstadistica();

    try {
      preValidate(request, estadisticaForm, result);
      getWebValidator().validate(estadistica, result);
      postValidate(request, estadisticaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        estadistica = update(request, estadistica);
        createMessageSuccess(request, "success.modification", estadistica.getEstadisticaID());
        status.setComplete();
        return getRedirectWhenModified(request, estadisticaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          estadistica.getEstadisticaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, estadisticaForm, __e);
    }

  }


  /**
   * Eliminar un Estadistica existent
   */
  @RequestMapping(value = "/{estadisticaID}/delete")
  public String eliminarEstadistica(@PathVariable("estadisticaID") java.lang.Long estadisticaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Estadistica estadistica = estadisticaEjb.findByPrimaryKey(estadisticaID);
      if (estadistica == null) {
        String __msg =createMessageError(request, "error.notfound", estadisticaID);
        return getRedirectWhenDelete(request, estadisticaID, new Exception(__msg));
      } else {
        delete(request, estadistica);
        createMessageSuccess(request, "success.deleted", estadisticaID);
        return getRedirectWhenDelete(request, estadisticaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", estadisticaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, estadisticaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EstadisticaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEstadistica(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __estadisticaID, Throwable e) {
    java.lang.Long estadisticaID = (java.lang.Long)__estadisticaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (estadisticaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(estadisticaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "estadistica.estadistica";
  }

  public String getEntityNameCodePlural() {
    return "estadistica.estadistica.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("estadistica.estadisticaID");
  }

  @InitBinder("estadisticaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("estadisticaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("estadisticaID");

  }

  public EstadisticaWebValidator getWebValidator() {
    return estadisticaWebValidator;
  }


  public void setWebValidator(EstadisticaWebValidator __val) {
    if (__val != null) {
      this.estadisticaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Estadistica
   */
  @RequestMapping(value = "/{estadisticaID}/cancel")
  public String cancelEstadistica(@PathVariable("estadisticaID") java.lang.Long estadisticaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, estadisticaID);
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, EstadisticaForm estadisticaForm, Where where)  throws I18NException {
    if (estadisticaForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, EstadisticaFilterForm estadisticaFilterForm,
       List<Estadistica> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estadisticaFilterForm.isHiddenField(TIPUS)
      && !estadisticaFilterForm.isGroupByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, EstadisticaForm estadisticaForm, Where where)  throws I18NException {
    if (estadisticaForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (estadisticaForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(estadisticaForm.getEstadistica().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, EstadisticaFilterForm estadisticaFilterForm,
       List<Estadistica> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estadisticaFilterForm.isHiddenField(ENTITATID)
      && !estadisticaFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (Estadistica _item : list) {
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


  public void preValidate(HttpServletRequest request,EstadisticaForm estadisticaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EstadisticaForm estadisticaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EstadisticaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EstadisticaFilterForm filterForm,  List<Estadistica> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EstadisticaForm estadisticaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EstadisticaForm estadisticaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long estadisticaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long estadisticaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "estadisticaFormWebDB";
  }

  public String getTileList() {
    return "estadisticaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EstadisticaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EstadisticaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long estadisticaID) throws I18NException {
    return (EstadisticaJPA) estadisticaEjb.findByPrimaryKey(estadisticaID);
  }


  public EstadisticaJPA create(HttpServletRequest request, EstadisticaJPA estadistica)
    throws Exception,I18NException, I18NValidationException {
    return (EstadisticaJPA) estadisticaEjb.create(estadistica);
  }


  public EstadisticaJPA update(HttpServletRequest request, EstadisticaJPA estadistica)
    throws Exception,I18NException, I18NValidationException {
    return (EstadisticaJPA) estadisticaEjb.update(estadistica);
  }


  public void delete(HttpServletRequest request, Estadistica estadistica) throws Exception,I18NException {
    estadisticaEjb.delete(estadistica);
  }

} // Final de Classe

