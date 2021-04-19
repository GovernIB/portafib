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
import es.caib.portafib.back.form.webdb.RebreAvisForm;

import es.caib.portafib.back.validator.webdb.RebreAvisWebValidator;

import es.caib.portafib.jpa.RebreAvisJPA;
import es.caib.portafib.model.entity.RebreAvis;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un RebreAvis
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/rebreAvis")
@SessionAttributes(types = { RebreAvisForm.class, RebreAvisFilterForm.class })
public class RebreAvisController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<RebreAvis, java.lang.Long> implements RebreAvisFields {

  @EJB(mappedName = es.caib.portafib.ejb.RebreAvisLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RebreAvisLocal rebreAvisEjb;

  @Autowired
  private RebreAvisWebValidator rebreAvisWebValidator;

  @Autowired
  protected RebreAvisRefList rebreAvisRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected TipusNotificacioRefList tipusNotificacioRefList;

  /**
   * Llistat de totes RebreAvis
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RebreAvisFilterForm ff;
    ff = (RebreAvisFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar RebreAvis de forma paginada
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
    llistat(mav, request, getRebreAvisFilterForm(pagina, mav, request));
    return mav;
  }

  public RebreAvisFilterForm getRebreAvisFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RebreAvisFilterForm rebreAvisFilterForm;
    rebreAvisFilterForm = (RebreAvisFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(rebreAvisFilterForm == null) {
      rebreAvisFilterForm = new RebreAvisFilterForm();
      rebreAvisFilterForm.setContexte(getContextWeb());
      rebreAvisFilterForm.setEntityNameCode(getEntityNameCode());
      rebreAvisFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      rebreAvisFilterForm.setNou(true);
    } else {
      rebreAvisFilterForm.setNou(false);
    }
    rebreAvisFilterForm.setPage(pagina == null ? 1 : pagina);
    return rebreAvisFilterForm;
  }

  /**
   * Segona i següent peticions per llistar RebreAvis de forma paginada
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
      @ModelAttribute RebreAvisFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRebreAvisFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de RebreAvis de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<RebreAvis> llistat(ModelAndView mav, HttpServletRequest request,
     RebreAvisFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<RebreAvis> rebreAvis = processarLlistat(rebreAvisEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("rebreAvisItems", rebreAvis);

    mav.addObject("rebreAvisFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, rebreAvis, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, rebreAvis);

    return rebreAvis;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RebreAvisFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<RebreAvis> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field tipusNotificacioID
    {
      _listSKV = getReferenceListForTipusNotificacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusNotificacioForTipusNotificacioID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSNOTIFICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSNOTIFICACIOID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, REBREAGRUPAT);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    RebreAvisFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<RebreAvis> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_REBREAVIS_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(TIPUSNOTIFICACIOID, filterForm.getMapOfTipusNotificacioForTipusNotificacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou RebreAvis
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRebreAvisGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RebreAvisForm rebreAvisForm = getRebreAvisForm(null, false, request, mav);
    mav.addObject("rebreAvisForm" ,rebreAvisForm);
    fillReferencesForForm(rebreAvisForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RebreAvisForm getRebreAvisForm(RebreAvisJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RebreAvisForm rebreAvisForm;
    if(_jpa == null) {
      rebreAvisForm = new RebreAvisForm(new RebreAvisJPA(), true);
    } else {
      rebreAvisForm = new RebreAvisForm(_jpa, false);
      rebreAvisForm.setView(__isView);
    }
    rebreAvisForm.setContexte(getContextWeb());
    rebreAvisForm.setEntityNameCode(getEntityNameCode());
    rebreAvisForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return rebreAvisForm;
  }

  public void fillReferencesForForm(RebreAvisForm rebreAvisForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (rebreAvisForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, rebreAvisForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      rebreAvisForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (rebreAvisForm.getListOfTipusNotificacioForTipusNotificacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusNotificacioID(request, mav, rebreAvisForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      rebreAvisForm.setListOfTipusNotificacioForTipusNotificacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou RebreAvis
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRebreAvisPost(@ModelAttribute RebreAvisForm rebreAvisForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RebreAvisJPA rebreAvis = rebreAvisForm.getRebreAvis();

    try {
      preValidate(request, rebreAvisForm, result);
      getWebValidator().validate(rebreAvisForm, result);
      postValidate(request,rebreAvisForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        rebreAvis = create(request, rebreAvis);
        createMessageSuccess(request, "success.creation", rebreAvis.getId());
        rebreAvisForm.setRebreAvis(rebreAvis);
        return getRedirectWhenCreated(request, rebreAvisForm);
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
  public ModelAndView veureRebreAvisGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRebreAvisGet(id,
        request, response, true);
  }


  protected ModelAndView editAndViewRebreAvisGet(@PathVariable("id") java.lang.Long id,
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
    RebreAvisJPA rebreAvis = findByPrimaryKey(request, id);

    if (rebreAvis == null) {
      createMessageWarning(request, "error.notfound", id);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, id), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RebreAvisForm rebreAvisForm = getRebreAvisForm(rebreAvis, __isView, request, mav);
      rebreAvisForm.setView(__isView);
      if(__isView) {
        rebreAvisForm.setAllFieldsReadOnly(ALL_REBREAVIS_FIELDS);
        rebreAvisForm.setSaveButtonVisible(false);
        rebreAvisForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(rebreAvisForm, request, mav);
      mav.addObject("rebreAvisForm", rebreAvisForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un RebreAvis existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView editarRebreAvisGet(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRebreAvisGet(id,
        request, response, false);
  }



  /**
   * Editar un RebreAvis existent
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public String editarRebreAvisPost(@ModelAttribute @Valid RebreAvisForm rebreAvisForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RebreAvisJPA rebreAvis = rebreAvisForm.getRebreAvis();

    try {
      preValidate(request, rebreAvisForm, result);
      getWebValidator().validate(rebreAvis, result);
      postValidate(request, rebreAvisForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        rebreAvis = update(request, rebreAvis);
        createMessageSuccess(request, "success.modification", rebreAvis.getId());
        status.setComplete();
        return getRedirectWhenModified(request, rebreAvisForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          rebreAvis.getId(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, rebreAvisForm, __e);
    }

  }


  /**
   * Eliminar un RebreAvis existent
   */
  @RequestMapping(value = "/{id}/delete")
  public String eliminarRebreAvis(@PathVariable("id") java.lang.Long id,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      RebreAvis rebreAvis = rebreAvisEjb.findByPrimaryKey(id);
      if (rebreAvis == null) {
        String __msg =createMessageError(request, "error.notfound", id);
        return getRedirectWhenDelete(request, id, new Exception(__msg));
      } else {
        delete(request, rebreAvis);
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
    @ModelAttribute RebreAvisFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRebreAvis(stringToPK(seleccionats[i]), request, response);
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
    return "rebreAvis.rebreAvis";
  }

  public String getEntityNameCodePlural() {
    return "rebreAvis.rebreAvis.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("rebreAvis.id");
  }

  @InitBinder("rebreAvisFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("rebreAvisForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "rebreAvis.id");
  }

  public RebreAvisWebValidator getWebValidator() {
    return rebreAvisWebValidator;
  }


  public void setWebValidator(RebreAvisWebValidator __val) {
    if (__val != null) {
      this.rebreAvisWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de RebreAvis
   */
  @RequestMapping(value = "/{id}/cancel")
  public String cancelRebreAvis(@PathVariable("id") java.lang.Long id,
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


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, RebreAvisForm rebreAvisForm, Where where)  throws I18NException {
    if (rebreAvisForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (rebreAvisForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(rebreAvisForm.getRebreAvis().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, RebreAvisFilterForm rebreAvisFilterForm,
       List<RebreAvis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (rebreAvisFilterForm.isHiddenField(USUARIENTITATID)
      && !rebreAvisFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RebreAvis _item : list) {
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


  public List<StringKeyValue> getReferenceListForTipusNotificacioID(HttpServletRequest request,
       ModelAndView mav, RebreAvisForm rebreAvisForm, Where where)  throws I18NException {
    if (rebreAvisForm.isHiddenField(TIPUSNOTIFICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (rebreAvisForm.isReadOnlyField(TIPUSNOTIFICACIOID)) {
      _where = TipusNotificacioFields.TIPUSNOTIFICACIOID.equal(rebreAvisForm.getRebreAvis().getTipusNotificacioID());
    }
    return getReferenceListForTipusNotificacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusNotificacioID(HttpServletRequest request,
       ModelAndView mav, RebreAvisFilterForm rebreAvisFilterForm,
       List<RebreAvis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (rebreAvisFilterForm.isHiddenField(TIPUSNOTIFICACIOID)
      && !rebreAvisFilterForm.isGroupByField(TIPUSNOTIFICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSNOTIFICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (RebreAvis _item : list) {
        _pkList.add(_item.getTipusNotificacioID());
        }
        _w = TipusNotificacioFields.TIPUSNOTIFICACIOID.in(_pkList);
      }
    return getReferenceListForTipusNotificacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusNotificacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusNotificacioRefList.getReferenceList(TipusNotificacioFields.TIPUSNOTIFICACIOID, where );
  }


  public void preValidate(HttpServletRequest request,RebreAvisForm rebreAvisForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RebreAvisForm rebreAvisForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RebreAvisFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RebreAvisFilterForm filterForm,  List<RebreAvis> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, RebreAvisForm rebreAvisForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, RebreAvisForm rebreAvisForm, Throwable __e) {
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
    return "rebreAvisFormWebDB";
  }

  public String getTileList() {
    return "rebreAvisListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "RebreAvisWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RebreAvisJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
    return (RebreAvisJPA) rebreAvisEjb.findByPrimaryKey(id);
  }


  public RebreAvisJPA create(HttpServletRequest request, RebreAvisJPA rebreAvis)
    throws Exception,I18NException, I18NValidationException {
    return (RebreAvisJPA) rebreAvisEjb.create(rebreAvis);
  }


  public RebreAvisJPA update(HttpServletRequest request, RebreAvisJPA rebreAvis)
    throws Exception,I18NException, I18NValidationException {
    return (RebreAvisJPA) rebreAvisEjb.update(rebreAvis);
  }


  public void delete(HttpServletRequest request, RebreAvis rebreAvis) throws Exception,I18NException {
    rebreAvisEjb.delete(rebreAvis);
  }

} // Final de Classe

