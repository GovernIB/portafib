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
import es.caib.portafib.back.form.webdb.TipusDocumentColaboracioDelegacioForm;

import es.caib.portafib.back.validator.webdb.TipusDocumentColaboracioDelegacioWebValidator;

import es.caib.portafib.jpa.TipusDocumentColaboracioDelegacioJPA;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusDocumentColaboracioDelegacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusDocumentColaboracioDelegacio")
@SessionAttributes(types = { TipusDocumentColaboracioDelegacioForm.class, TipusDocumentColaboracioDelegacioFilterForm.class })
public class TipusDocumentColaboracioDelegacioController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<TipusDocumentColaboracioDelegacio, java.lang.Long> implements TipusDocumentColaboracioDelegacioFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;

  @Autowired
  private TipusDocumentColaboracioDelegacioWebValidator tipusDocumentColaboracioDelegacioWebValidator;

  @Autowired
  protected TipusDocumentColaboracioDelegacioRefList tipusDocumentColaboracioDelegacioRefList;

  // References 
  @Autowired
  protected ColaboracioDelegacioRefList colaboracioDelegacioRefList;

  // References 
  @Autowired
  protected TipusDocumentRefList tipusDocumentRefList;

  /**
   * Llistat de totes TipusDocumentColaboracioDelegacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusDocumentColaboracioDelegacioFilterForm ff;
    ff = (TipusDocumentColaboracioDelegacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusDocumentColaboracioDelegacio de forma paginada
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
    llistat(mav, request, getTipusDocumentColaboracioDelegacioFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusDocumentColaboracioDelegacioFilterForm getTipusDocumentColaboracioDelegacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusDocumentColaboracioDelegacioFilterForm tipusDocumentColaboracioDelegacioFilterForm;
    tipusDocumentColaboracioDelegacioFilterForm = (TipusDocumentColaboracioDelegacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusDocumentColaboracioDelegacioFilterForm == null) {
      tipusDocumentColaboracioDelegacioFilterForm = new TipusDocumentColaboracioDelegacioFilterForm();
      tipusDocumentColaboracioDelegacioFilterForm.setContexte(getContextWeb());
      tipusDocumentColaboracioDelegacioFilterForm.setEntityNameCode(getEntityNameCode());
      tipusDocumentColaboracioDelegacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusDocumentColaboracioDelegacioFilterForm.setNou(true);
    } else {
      tipusDocumentColaboracioDelegacioFilterForm.setNou(false);
    }
    tipusDocumentColaboracioDelegacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusDocumentColaboracioDelegacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusDocumentColaboracioDelegacio de forma paginada
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
      @ModelAttribute TipusDocumentColaboracioDelegacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusDocumentColaboracioDelegacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusDocumentColaboracioDelegacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusDocumentColaboracioDelegacio> llistat(ModelAndView mav, HttpServletRequest request,
     TipusDocumentColaboracioDelegacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TipusDocumentColaboracioDelegacio> tipusDocumentColaboracioDelegacio = processarLlistat(tipusDocumentColaboracioDelegacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusDocumentColaboracioDelegacioItems", tipusDocumentColaboracioDelegacio);

    mav.addObject("tipusDocumentColaboracioDelegacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusDocumentColaboracioDelegacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusDocumentColaboracioDelegacio);

    return tipusDocumentColaboracioDelegacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusDocumentColaboracioDelegacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusDocumentColaboracioDelegacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field colaboracioDelegacioID
    {
      _listSKV = getReferenceListForColaboracioDelegacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfColaboracioDelegacioForColaboracioDelegacioID(_tmp);
      if (filterForm.getGroupByFields().contains(COLABORACIODELEGACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, COLABORACIODELEGACIOID, false);
      };
    }

    // Field tipusDocumentID
    {
      _listSKV = getReferenceListForTipusDocumentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusDocumentForTipusDocumentID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSDOCUMENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSDOCUMENTID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusDocumentColaboracioDelegacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusDocumentColaboracioDelegacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSDOCUMENTCOLABORACIODELEGACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(COLABORACIODELEGACIOID, filterForm.getMapOfColaboracioDelegacioForColaboracioDelegacioID());
    __mapping.put(TIPUSDOCUMENTID, filterForm.getMapOfTipusDocumentForTipusDocumentID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusDocumentColaboracioDelegacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusDocumentColaboracioDelegacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm = getTipusDocumentColaboracioDelegacioForm(null, false, request, mav);
    mav.addObject("tipusDocumentColaboracioDelegacioForm" ,tipusDocumentColaboracioDelegacioForm);
    fillReferencesForForm(tipusDocumentColaboracioDelegacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusDocumentColaboracioDelegacioForm getTipusDocumentColaboracioDelegacioForm(TipusDocumentColaboracioDelegacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm;
    if(_jpa == null) {
      tipusDocumentColaboracioDelegacioForm = new TipusDocumentColaboracioDelegacioForm(new TipusDocumentColaboracioDelegacioJPA(), true);
    } else {
      tipusDocumentColaboracioDelegacioForm = new TipusDocumentColaboracioDelegacioForm(_jpa, false);
      tipusDocumentColaboracioDelegacioForm.setView(__isView);
    }
    tipusDocumentColaboracioDelegacioForm.setContexte(getContextWeb());
    tipusDocumentColaboracioDelegacioForm.setEntityNameCode(getEntityNameCode());
    tipusDocumentColaboracioDelegacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusDocumentColaboracioDelegacioForm;
  }

  public void fillReferencesForForm(TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tipusDocumentColaboracioDelegacioForm.getListOfColaboracioDelegacioForColaboracioDelegacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForColaboracioDelegacioID(request, mav, tipusDocumentColaboracioDelegacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      tipusDocumentColaboracioDelegacioForm.setListOfColaboracioDelegacioForColaboracioDelegacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tipusDocumentColaboracioDelegacioForm.getListOfTipusDocumentForTipusDocumentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusDocumentID(request, mav, tipusDocumentColaboracioDelegacioForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      tipusDocumentColaboracioDelegacioForm.setListOfTipusDocumentForTipusDocumentID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou TipusDocumentColaboracioDelegacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusDocumentColaboracioDelegacioPost(@ModelAttribute TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio = tipusDocumentColaboracioDelegacioForm.getTipusDocumentColaboracioDelegacio();

    try {
      preValidate(request, tipusDocumentColaboracioDelegacioForm, result);
      getWebValidator().validate(tipusDocumentColaboracioDelegacioForm, result);
      postValidate(request,tipusDocumentColaboracioDelegacioForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusDocumentColaboracioDelegacio = create(request, tipusDocumentColaboracioDelegacio);
        createMessageSuccess(request, "success.creation", tipusDocumentColaboracioDelegacio.getId());
        tipusDocumentColaboracioDelegacioForm.setTipusDocumentColaboracioDelegacio(tipusDocumentColaboracioDelegacio);
        return getRedirectWhenCreated(request, tipusDocumentColaboracioDelegacioForm);
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
  public ModelAndView veureTipusDocumentColaboracioDelegacioGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusDocumentColaboracioDelegacioGet(id,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusDocumentColaboracioDelegacioGet(@PathVariable("id") java.lang.Long id,
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
    TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio = findByPrimaryKey(request, id);

    if (tipusDocumentColaboracioDelegacio == null) {
      createMessageWarning(request, "error.notfound", id);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, id), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm = getTipusDocumentColaboracioDelegacioForm(tipusDocumentColaboracioDelegacio, __isView, request, mav);
      tipusDocumentColaboracioDelegacioForm.setView(__isView);
      if(__isView) {
        tipusDocumentColaboracioDelegacioForm.setAllFieldsReadOnly(ALL_TIPUSDOCUMENTCOLABORACIODELEGACIO_FIELDS);
        tipusDocumentColaboracioDelegacioForm.setSaveButtonVisible(false);
        tipusDocumentColaboracioDelegacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusDocumentColaboracioDelegacioForm, request, mav);
      mav.addObject("tipusDocumentColaboracioDelegacioForm", tipusDocumentColaboracioDelegacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusDocumentColaboracioDelegacio existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusDocumentColaboracioDelegacioGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusDocumentColaboracioDelegacioGet(id,
        request, response, false);
  }



  /**
   * Editar un TipusDocumentColaboracioDelegacio existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public String editarTipusDocumentColaboracioDelegacioPost(@ModelAttribute @Valid TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio = tipusDocumentColaboracioDelegacioForm.getTipusDocumentColaboracioDelegacio();

    try {
      preValidate(request, tipusDocumentColaboracioDelegacioForm, result);
      getWebValidator().validate(tipusDocumentColaboracioDelegacio, result);
      postValidate(request, tipusDocumentColaboracioDelegacioForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusDocumentColaboracioDelegacio = update(request, tipusDocumentColaboracioDelegacio);
        createMessageSuccess(request, "success.modification", tipusDocumentColaboracioDelegacio.getId());
        status.setComplete();
        return getRedirectWhenModified(request, tipusDocumentColaboracioDelegacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusDocumentColaboracioDelegacio.getId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusDocumentColaboracioDelegacioForm, __e);
    }

  }


  /**
   * Eliminar un TipusDocumentColaboracioDelegacio existent
   */
  @RequestMapping(value = "/{id}/delete")
  public String eliminarTipusDocumentColaboracioDelegacio(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusDocumentColaboracioDelegacio tipusDocumentColaboracioDelegacio = tipusDocumentColaboracioDelegacioEjb.findByPrimaryKey(id);
      if (tipusDocumentColaboracioDelegacio == null) {
        String __msg =createMessageError(request, "error.notfound", id);
        return getRedirectWhenDelete(request, id, new Exception(__msg));
      } else {
        delete(request, tipusDocumentColaboracioDelegacio);
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
    @ModelAttribute TipusDocumentColaboracioDelegacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusDocumentColaboracioDelegacio(stringToPK(seleccionats[i]), request, response);
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
    return "tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio";
  }

  public String getEntityNameCodePlural() {
    return "tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusDocumentColaboracioDelegacio.id");
  }

  @InitBinder("tipusDocumentColaboracioDelegacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusDocumentColaboracioDelegacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("id");

  }

  public TipusDocumentColaboracioDelegacioWebValidator getWebValidator() {
    return tipusDocumentColaboracioDelegacioWebValidator;
  }


  public void setWebValidator(TipusDocumentColaboracioDelegacioWebValidator __val) {
    if (__val != null) {
      this.tipusDocumentColaboracioDelegacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusDocumentColaboracioDelegacio
   */
  @RequestMapping(value = "/{id}/cancel")
  public String cancelTipusDocumentColaboracioDelegacio(@PathVariable("id") java.lang.Long id,
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


  public List<StringKeyValue> getReferenceListForColaboracioDelegacioID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm, Where where)  throws I18NException {
    if (tipusDocumentColaboracioDelegacioForm.isHiddenField(COLABORACIODELEGACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  tipusDocumentColaboracioDelegacioForm.getStringOfField(COLABORACIODELEGACIOID);
    Where _where = null;
    if (tipusDocumentColaboracioDelegacioForm.isReadOnlyField(_fieldName)) {
      _where = ColaboracioDelegacioFields.COLABORACIODELEGACIOID.equal(tipusDocumentColaboracioDelegacioForm.getTipusDocumentColaboracioDelegacio().getColaboracioDelegacioID());
    }
    return getReferenceListForColaboracioDelegacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForColaboracioDelegacioID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentColaboracioDelegacioFilterForm tipusDocumentColaboracioDelegacioFilterForm,
       List<TipusDocumentColaboracioDelegacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tipusDocumentColaboracioDelegacioFilterForm.isHiddenField(COLABORACIODELEGACIOID)
      && !tipusDocumentColaboracioDelegacioFilterForm.isGroupByField(COLABORACIODELEGACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(COLABORACIODELEGACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TipusDocumentColaboracioDelegacio _item : list) {
        _pkList.add(_item.getColaboracioDelegacioID());
        }
        _w = ColaboracioDelegacioFields.COLABORACIODELEGACIOID.in(_pkList);
      }
    return getReferenceListForColaboracioDelegacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForColaboracioDelegacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return colaboracioDelegacioRefList.getReferenceList(ColaboracioDelegacioFields.COLABORACIODELEGACIOID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm, Where where)  throws I18NException {
    if (tipusDocumentColaboracioDelegacioForm.isHiddenField(TIPUSDOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  tipusDocumentColaboracioDelegacioForm.getStringOfField(TIPUSDOCUMENTID);
    Where _where = null;
    if (tipusDocumentColaboracioDelegacioForm.isReadOnlyField(_fieldName)) {
      _where = TipusDocumentFields.TIPUSDOCUMENTID.equal(tipusDocumentColaboracioDelegacioForm.getTipusDocumentColaboracioDelegacio().getTipusDocumentID());
    }
    return getReferenceListForTipusDocumentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentColaboracioDelegacioFilterForm tipusDocumentColaboracioDelegacioFilterForm,
       List<TipusDocumentColaboracioDelegacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tipusDocumentColaboracioDelegacioFilterForm.isHiddenField(TIPUSDOCUMENTID)
      && !tipusDocumentColaboracioDelegacioFilterForm.isGroupByField(TIPUSDOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSDOCUMENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TipusDocumentColaboracioDelegacio _item : list) {
        _pkList.add(_item.getTipusDocumentID());
        }
        _w = TipusDocumentFields.TIPUSDOCUMENTID.in(_pkList);
      }
    return getReferenceListForTipusDocumentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusDocumentRefList.getReferenceList(TipusDocumentFields.TIPUSDOCUMENTID, where );
  }


  public void preValidate(HttpServletRequest request,TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusDocumentColaboracioDelegacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusDocumentColaboracioDelegacioFilterForm filterForm,  List<TipusDocumentColaboracioDelegacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusDocumentColaboracioDelegacioForm tipusDocumentColaboracioDelegacioForm, Throwable __e) {
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
    return "tipusDocumentColaboracioDelegacioFormWebDB";
  }

  public String getTileList() {
    return "tipusDocumentColaboracioDelegacioListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusDocumentColaboracioDelegacioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusDocumentColaboracioDelegacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
    return (TipusDocumentColaboracioDelegacioJPA) tipusDocumentColaboracioDelegacioEjb.findByPrimaryKey(id);
  }


  public TipusDocumentColaboracioDelegacioJPA create(HttpServletRequest request, TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio)
    throws Exception,I18NException, I18NValidationException {
    return (TipusDocumentColaboracioDelegacioJPA) tipusDocumentColaboracioDelegacioEjb.create(tipusDocumentColaboracioDelegacio);
  }


  public TipusDocumentColaboracioDelegacioJPA update(HttpServletRequest request, TipusDocumentColaboracioDelegacioJPA tipusDocumentColaboracioDelegacio)
    throws Exception,I18NException, I18NValidationException {
    return (TipusDocumentColaboracioDelegacioJPA) tipusDocumentColaboracioDelegacioEjb.update(tipusDocumentColaboracioDelegacio);
  }


  public void delete(HttpServletRequest request, TipusDocumentColaboracioDelegacio tipusDocumentColaboracioDelegacio) throws Exception,I18NException {
    tipusDocumentColaboracioDelegacioEjb.delete(tipusDocumentColaboracioDelegacio);
  }

} // Final de Classe

