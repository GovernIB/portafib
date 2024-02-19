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
import es.caib.portafib.back.form.webdb.MetadadaForm;

import es.caib.portafib.back.validator.webdb.MetadadaWebValidator;

import es.caib.portafib.persistence.MetadadaJPA;
import es.caib.portafib.model.entity.Metadada;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Metadada
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/metadada")
@SessionAttributes(types = { MetadadaForm.class, MetadadaFilterForm.class })
public class MetadadaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<Metadada, java.lang.Long> implements MetadadaFields {

  @EJB(mappedName = es.caib.portafib.ejb.MetadadaService.JNDI_NAME)
  protected es.caib.portafib.ejb.MetadadaService metadadaEjb;

  @Autowired
  private MetadadaWebValidator metadadaWebValidator;

  @Autowired
  protected MetadadaRefList metadadaRefList;

  // References 
  @Autowired
  protected PeticioDeFirmaRefList peticioDeFirmaRefList;

  /**
   * Llistat de totes Metadada
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    MetadadaFilterForm ff;
    ff = (MetadadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Metadada de forma paginada
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
    llistat(mav, request, getMetadadaFilterForm(pagina, mav, request));
    return mav;
  }

  public MetadadaFilterForm getMetadadaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    MetadadaFilterForm metadadaFilterForm;
    metadadaFilterForm = (MetadadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(metadadaFilterForm == null) {
      metadadaFilterForm = new MetadadaFilterForm();
      metadadaFilterForm.setContexte(getContextWeb());
      metadadaFilterForm.setEntityNameCode(getEntityNameCode());
      metadadaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      metadadaFilterForm.setNou(true);
    } else {
      metadadaFilterForm.setNou(false);
    }
    metadadaFilterForm.setPage(pagina == null ? 1 : pagina);
    return metadadaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Metadada de forma paginada
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
      @ModelAttribute MetadadaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getMetadadaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Metadada de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Metadada> llistat(ModelAndView mav, HttpServletRequest request,
     MetadadaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Metadada> metadada = processarLlistat(metadadaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("metadadaItems", metadada);

    mav.addObject("metadadaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, metadada, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, metadada);

    return metadada;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(MetadadaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Metadada> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field peticioDeFirmaID
    {
      _listSKV = getReferenceListForPeticioDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPeticioDeFirmaForPeticioDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(PETICIODEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PETICIODEFIRMAID, false);
      };
    }

    // Field tipusMetadadaID
    {
      _listSKV = getReferenceListForTipusMetadadaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusMetadadaID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSMETADADAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSMETADADAID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    MetadadaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Metadada> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_METADADA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PETICIODEFIRMAID, filterForm.getMapOfPeticioDeFirmaForPeticioDeFirmaID());
    __mapping.put(TIPUSMETADADAID, filterForm.getMapOfValuesForTipusMetadadaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Metadada
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearMetadadaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    MetadadaForm metadadaForm = getMetadadaForm(null, false, request, mav);
    mav.addObject("metadadaForm" ,metadadaForm);
    fillReferencesForForm(metadadaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public MetadadaForm getMetadadaForm(MetadadaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    MetadadaForm metadadaForm;
    if(_jpa == null) {
      metadadaForm = new MetadadaForm(new MetadadaJPA(), true);
    } else {
      metadadaForm = new MetadadaForm(_jpa, false);
      metadadaForm.setView(__isView);
    }
    metadadaForm.setContexte(getContextWeb());
    metadadaForm.setEntityNameCode(getEntityNameCode());
    metadadaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return metadadaForm;
  }

  public void fillReferencesForForm(MetadadaForm metadadaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (metadadaForm.getListOfPeticioDeFirmaForPeticioDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPeticioDeFirmaID(request, mav, metadadaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      metadadaForm.setListOfPeticioDeFirmaForPeticioDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (metadadaForm.getListOfValuesForTipusMetadadaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusMetadadaID(request, mav, metadadaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      metadadaForm.setListOfValuesForTipusMetadadaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Metadada
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearMetadadaPost(@ModelAttribute MetadadaForm metadadaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    MetadadaJPA metadada = metadadaForm.getMetadada();

    try {
      preValidate(request, metadadaForm, result);
      getWebValidator().validate(metadadaForm, result);
      postValidate(request,metadadaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        metadada = create(request, metadada);
        createMessageSuccess(request, "success.creation", metadada.getMetadadaID());
        metadadaForm.setMetadada(metadada);
        return getRedirectWhenCreated(request, metadadaForm);
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

  @RequestMapping(value = "/view/{metadadaID}", method = RequestMethod.GET)
  public ModelAndView veureMetadadaGet(@PathVariable("metadadaID") java.lang.Long metadadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewMetadadaGet(metadadaID,
        request, response, true);
  }


  protected ModelAndView editAndViewMetadadaGet(@PathVariable("metadadaID") java.lang.Long metadadaID,
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
    MetadadaJPA metadada = findByPrimaryKey(request, metadadaID);

    if (metadada == null) {
      createMessageWarning(request, "error.notfound", metadadaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, metadadaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      MetadadaForm metadadaForm = getMetadadaForm(metadada, __isView, request, mav);
      metadadaForm.setView(__isView);
      if(__isView) {
        metadadaForm.setAllFieldsReadOnly(ALL_METADADA_FIELDS);
        metadadaForm.setSaveButtonVisible(false);
        metadadaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(metadadaForm, request, mav);
      mav.addObject("metadadaForm", metadadaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Metadada existent
   */
  @RequestMapping(value = "/{metadadaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarMetadadaGet(@PathVariable("metadadaID") java.lang.Long metadadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewMetadadaGet(metadadaID,
        request, response, false);
  }



  /**
   * Editar un Metadada existent
   */
  @RequestMapping(value = "/{metadadaID}/edit", method = RequestMethod.POST)
  public String editarMetadadaPost(@ModelAttribute MetadadaForm metadadaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    MetadadaJPA metadada = metadadaForm.getMetadada();

    try {
      preValidate(request, metadadaForm, result);
      getWebValidator().validate(metadadaForm, result);
      postValidate(request, metadadaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        metadada = update(request, metadada);
        createMessageSuccess(request, "success.modification", metadada.getMetadadaID());
        status.setComplete();
        return getRedirectWhenModified(request, metadadaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          metadada.getMetadadaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, metadadaForm, __e);
    }

  }


  /**
   * Eliminar un Metadada existent
   */
  @RequestMapping(value = "/{metadadaID}/delete")
  public String eliminarMetadada(@PathVariable("metadadaID") java.lang.Long metadadaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Metadada metadada = this.findByPrimaryKey(request, metadadaID);
      if (metadada == null) {
        String __msg = createMessageError(request, "error.notfound", metadadaID);
        return getRedirectWhenDelete(request, metadadaID, new Exception(__msg));
      } else {
        delete(request, metadada);
        createMessageSuccess(request, "success.deleted", metadadaID);
        return getRedirectWhenDelete(request, metadadaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", metadadaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, metadadaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute MetadadaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarMetadada(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __metadadaID, Throwable e) {
    java.lang.Long metadadaID = (java.lang.Long)__metadadaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (metadadaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(metadadaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "metadada.metadada";
  }

  public String getEntityNameCodePlural() {
    return "metadada.metadada.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("metadada.metadadaID");
  }

  @InitBinder("metadadaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("metadadaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "metadada.metadadaID");
  }

  public MetadadaWebValidator getWebValidator() {
    return metadadaWebValidator;
  }


  public void setWebValidator(MetadadaWebValidator __val) {
    if (__val != null) {
      this.metadadaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Metadada
   */
  @RequestMapping(value = "/{metadadaID}/cancel")
  public String cancelMetadada(@PathVariable("metadadaID") java.lang.Long metadadaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, metadadaID);
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


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, MetadadaForm metadadaForm, Where where)  throws I18NException {
    if (metadadaForm.isHiddenField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (metadadaForm.isReadOnlyField(PETICIODEFIRMAID)) {
      _where = PeticioDeFirmaFields.PETICIODEFIRMAID.equal(metadadaForm.getMetadada().getPeticioDeFirmaID());
    }
    return getReferenceListForPeticioDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, MetadadaFilterForm metadadaFilterForm,
       List<Metadada> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (metadadaFilterForm.isHiddenField(PETICIODEFIRMAID)
       && !metadadaFilterForm.isGroupByField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PETICIODEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Metadada _item : list) {
        _pkList.add(_item.getPeticioDeFirmaID());
        }
        _w = PeticioDeFirmaFields.PETICIODEFIRMAID.in(_pkList);
      }
    return getReferenceListForPeticioDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return peticioDeFirmaRefList.getReferenceList(PeticioDeFirmaFields.PETICIODEFIRMAID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusMetadadaID(HttpServletRequest request,
       ModelAndView mav, MetadadaForm metadadaForm, Where where)  throws I18NException {
    if (metadadaForm.isHiddenField(TIPUSMETADADAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusMetadadaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusMetadadaID(HttpServletRequest request,
       ModelAndView mav, MetadadaFilterForm metadadaFilterForm,
       List<Metadada> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (metadadaFilterForm.isHiddenField(TIPUSMETADADAID)
       && !metadadaFilterForm.isGroupByField(TIPUSMETADADAID)
       && !metadadaFilterForm.isFilterByField(TIPUSMETADADAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusMetadadaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusMetadadaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,MetadadaForm metadadaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,MetadadaForm metadadaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, MetadadaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, MetadadaFilterForm filterForm,  List<Metadada> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, MetadadaForm metadadaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, MetadadaForm metadadaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long metadadaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long metadadaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "metadadaFormWebDB";
  }

  public String getTileList() {
    return "metadadaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Metadada_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public MetadadaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long metadadaID) throws I18NException {
    return (MetadadaJPA) metadadaEjb.findByPrimaryKey(metadadaID);
  }


  public MetadadaJPA create(HttpServletRequest request, MetadadaJPA metadada)
    throws I18NException, I18NValidationException {
    return (MetadadaJPA) metadadaEjb.create(metadada);
  }


  public MetadadaJPA update(HttpServletRequest request, MetadadaJPA metadada)
    throws I18NException, I18NValidationException {
    return (MetadadaJPA) metadadaEjb.update(metadada);
  }


  public void delete(HttpServletRequest request, Metadada metadada) throws I18NException {
    metadadaEjb.delete(metadada);
  }

} // Final de Classe

