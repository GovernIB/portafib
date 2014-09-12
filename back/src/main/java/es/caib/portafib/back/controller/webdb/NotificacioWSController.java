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
import es.caib.portafib.back.form.webdb.NotificacioWSForm;

import es.caib.portafib.back.validator.webdb.NotificacioWSWebValidator;

import es.caib.portafib.jpa.NotificacioWSJPA;
import es.caib.portafib.model.entity.NotificacioWS;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un NotificacioWS
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/notificacioWS")
@SessionAttributes(types = { NotificacioWSForm.class, NotificacioWSFilterForm.class })
public class NotificacioWSController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements NotificacioWSFields {

  @EJB(mappedName = es.caib.portafib.ejb.NotificacioWSLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.NotificacioWSLocal notificacioWSEjb;

  @Autowired
  private NotificacioWSWebValidator notificacioWSWebValidator;

  @Autowired
  protected NotificacioWSRefList notificacioWSRefList;

  // References 
  @Autowired
  protected PeticioDeFirmaRefList peticioDeFirmaRefList;

  // References 
  @Autowired
  protected TipusNotificacioRefList tipusNotificacioRefList;

  /**
   * Llistat de totes NotificacioWS
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    NotificacioWSFilterForm ff;
    ff = (NotificacioWSFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar NotificacioWS de forma paginada
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
    llistat(mav, request, getNotificacioWSFilterForm(pagina, mav, request));
    return mav;
  }

  public NotificacioWSFilterForm getNotificacioWSFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    NotificacioWSFilterForm notificacioWSFilterForm;
    notificacioWSFilterForm = (NotificacioWSFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(notificacioWSFilterForm == null) {
      notificacioWSFilterForm = new NotificacioWSFilterForm();
      notificacioWSFilterForm.setContexte(getContextWeb());
      notificacioWSFilterForm.setEntityNameCode(getEntityNameCode());
      notificacioWSFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      notificacioWSFilterForm.setNou(true);
    } else {
      notificacioWSFilterForm.setNou(false);
    }
    notificacioWSFilterForm.setPage(pagina == null ? 1 : pagina);
    return notificacioWSFilterForm;
  }

  /**
   * Segona i següent peticions per llistar NotificacioWS de forma paginada
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
      @ModelAttribute NotificacioWSFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getNotificacioWSFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de NotificacioWS de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<NotificacioWS> llistat(ModelAndView mav, HttpServletRequest request,
     NotificacioWSFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<NotificacioWS> notificacioWS = (List<NotificacioWS>) processarLlistat(notificacioWSEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("notificacioWSItems", notificacioWS);

    mav.addObject("notificacioWSFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, notificacioWS, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, notificacioWS);

    return notificacioWS;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(NotificacioWSFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<NotificacioWS> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field tipusNotificacioID
    {
      _listSKV = getReferenceListForTipusNotificacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusNotificacioForTipusNotificacioID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSNOTIFICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSNOTIFICACIOID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, BLOQUEJADA);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    NotificacioWSFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<NotificacioWS> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_NOTIFICACIOWS_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PETICIODEFIRMAID, filterForm.getMapOfPeticioDeFirmaForPeticioDeFirmaID());
    __mapping.put(TIPUSNOTIFICACIOID, filterForm.getMapOfTipusNotificacioForTipusNotificacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou NotificacioWS
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearNotificacioWSGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    NotificacioWSForm notificacioWSForm = getNotificacioWSForm(null, false, request, mav);
    mav.addObject("notificacioWSForm" ,notificacioWSForm);
    fillReferencesForForm(notificacioWSForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public NotificacioWSForm getNotificacioWSForm(NotificacioWSJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    NotificacioWSForm notificacioWSForm;
    if(_jpa == null) {
      notificacioWSForm = new NotificacioWSForm(new NotificacioWSJPA(), true);
    } else {
      notificacioWSForm = new NotificacioWSForm(_jpa, false);
      notificacioWSForm.setView(__isView);
    }
    notificacioWSForm.setContexte(getContextWeb());
    notificacioWSForm.setEntityNameCode(getEntityNameCode());
    notificacioWSForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return notificacioWSForm;
  }

  public void fillReferencesForForm(NotificacioWSForm notificacioWSForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (notificacioWSForm.getListOfPeticioDeFirmaForPeticioDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPeticioDeFirmaID(request, mav, notificacioWSForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      notificacioWSForm.setListOfPeticioDeFirmaForPeticioDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (notificacioWSForm.getListOfTipusNotificacioForTipusNotificacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusNotificacioID(request, mav, notificacioWSForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      notificacioWSForm.setListOfTipusNotificacioForTipusNotificacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou NotificacioWS
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearNotificacioWSPost(@ModelAttribute NotificacioWSForm notificacioWSForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    NotificacioWSJPA notificacioWS = notificacioWSForm.getNotificacioWS();

    try {
      preValidate(notificacioWSForm, result);
      getWebValidator().validate(notificacioWSForm, result);
      postValidate(notificacioWSForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        notificacioWS = create(notificacioWS);
        createMessageSuccess(request, "success.creation", notificacioWS.getNotificacioID());
        notificacioWSForm.setNotificacioWS(notificacioWS);
        return getRedirectWhenCreated(notificacioWSForm);
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

  @RequestMapping(value = "/view/{notificacioID}", method = RequestMethod.GET)
  public ModelAndView veureNotificacioWSGet(@PathVariable("notificacioID") java.lang.Long notificacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewNotificacioWSGet(notificacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewNotificacioWSGet(@PathVariable("notificacioID") java.lang.Long notificacioID,
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
    NotificacioWSJPA notificacioWS = findByPrimaryKey(notificacioID);

    if (notificacioWS == null) {
      createMessageWarning(request, "error.notfound", notificacioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(notificacioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      NotificacioWSForm notificacioWSForm = getNotificacioWSForm(notificacioWS, __isView, request, mav);
      notificacioWSForm.setView(__isView);
      if(__isView) {
        notificacioWSForm.setAllFieldsReadOnly(ALL_NOTIFICACIOWS_FIELDS);
        notificacioWSForm.setSaveButtonVisible(false);
        notificacioWSForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(notificacioWSForm, request, mav);
      mav.addObject("notificacioWSForm", notificacioWSForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un NotificacioWS existent
   */
  @RequestMapping(value = "/{notificacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarNotificacioWSGet(@PathVariable("notificacioID") java.lang.Long notificacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewNotificacioWSGet(notificacioID,
        request, response, false);
  }



  /**
   * Editar un NotificacioWS existent
   */
  @RequestMapping(value = "/{notificacioID}/edit", method = RequestMethod.POST)
  public String editarNotificacioWSPost(@ModelAttribute @Valid NotificacioWSForm notificacioWSForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    NotificacioWSJPA notificacioWS = notificacioWSForm.getNotificacioWS();

    try {
      preValidate(notificacioWSForm, result);
      getWebValidator().validate(notificacioWS, result);
      postValidate(notificacioWSForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        notificacioWS = update(notificacioWS);
        createMessageSuccess(request, "success.modification", notificacioWS.getNotificacioID());
        status.setComplete();
        return getRedirectWhenModified(notificacioWSForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          notificacioWS.getNotificacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(notificacioWSForm, __e);
    }

  }


  /**
   * Eliminar un NotificacioWS existent
   */
  @RequestMapping(value = "/{notificacioID}/delete")
  public String eliminarNotificacioWS(@PathVariable("notificacioID") java.lang.Long notificacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      NotificacioWS notificacioWS = notificacioWSEjb.findByPrimaryKey(notificacioID);
      if (notificacioWS == null) {
        String __msg =createMessageError(request, "error.notfound", notificacioID);
        return getRedirectWhenDelete(notificacioID, new Exception(__msg));
      } else {
        delete(request, notificacioWS);
        createMessageSuccess(request, "success.deleted", notificacioID);
        return getRedirectWhenDelete(notificacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", notificacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(notificacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute NotificacioWSFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarNotificacioWS(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return new java.lang.Long(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __notificacioID, Throwable e) {
    java.lang.Long notificacioID = (java.lang.Long)__notificacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (notificacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(notificacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "notificacioWS.notificacioWS";
  }

  public String getEntityNameCodePlural() {
    return "notificacioWS.notificacioWS.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("notificacioWS.notificacioID");
  }

  @InitBinder("notificacioWSFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("notificacioWSForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("notificacioID");

  }

  public NotificacioWSWebValidator getWebValidator() {
    return notificacioWSWebValidator;
  }


  public void setWebValidator(NotificacioWSWebValidator __val) {
    if (__val != null) {
      this.notificacioWSWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de NotificacioWS
   */
  @RequestMapping(value = "/{notificacioID}/cancel")
  public String cancelNotificacioWS(@PathVariable("notificacioID") java.lang.Long notificacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(notificacioID);
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
       ModelAndView mav, NotificacioWSForm notificacioWSForm, Where where)  throws I18NException {
    if (notificacioWSForm.isHiddenField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  notificacioWSForm.getStringOfField(PETICIODEFIRMAID);
    Where _where = null;
    if (notificacioWSForm.isReadOnlyField(_fieldName)) {
      _where = PeticioDeFirmaFields.PETICIODEFIRMAID.equal(notificacioWSForm.getNotificacioWS().getPeticioDeFirmaID());
    }
    return getReferenceListForPeticioDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, NotificacioWSFilterForm notificacioWSFilterForm,
       List<NotificacioWS> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (notificacioWSFilterForm.isHiddenField(PETICIODEFIRMAID)
      && !notificacioWSFilterForm.isGroupByField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PETICIODEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (NotificacioWS _item : list) {
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


  public List<StringKeyValue> getReferenceListForTipusNotificacioID(HttpServletRequest request,
       ModelAndView mav, NotificacioWSForm notificacioWSForm, Where where)  throws I18NException {
    if (notificacioWSForm.isHiddenField(TIPUSNOTIFICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  notificacioWSForm.getStringOfField(TIPUSNOTIFICACIOID);
    Where _where = null;
    if (notificacioWSForm.isReadOnlyField(_fieldName)) {
      _where = TipusNotificacioFields.TIPUSNOTIFICACIOID.equal(notificacioWSForm.getNotificacioWS().getTipusNotificacioID());
    }
    return getReferenceListForTipusNotificacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusNotificacioID(HttpServletRequest request,
       ModelAndView mav, NotificacioWSFilterForm notificacioWSFilterForm,
       List<NotificacioWS> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (notificacioWSFilterForm.isHiddenField(TIPUSNOTIFICACIOID)
      && !notificacioWSFilterForm.isGroupByField(TIPUSNOTIFICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSNOTIFICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (NotificacioWS _item : list) {
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


  public void preValidate(NotificacioWSForm notificacioWSForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(NotificacioWSForm notificacioWSForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, NotificacioWSFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, NotificacioWSFilterForm filterForm,  List<NotificacioWS> list) throws I18NException {
  }

  public String getRedirectWhenCreated(NotificacioWSForm notificacioWSForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(NotificacioWSForm notificacioWSForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(java.lang.Long notificacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(java.lang.Long notificacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "notificacioWSFormWebDB";
  }

  public String getTileList() {
    return "notificacioWSListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "NotificacioWSWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public NotificacioWSJPA findByPrimaryKey(java.lang.Long notificacioID) throws I18NException {
    return (NotificacioWSJPA) notificacioWSEjb.findByPrimaryKey(notificacioID);
  }


  public NotificacioWSJPA create(NotificacioWSJPA notificacioWS)
    throws Exception,I18NException, I18NValidationException {
    return (NotificacioWSJPA) notificacioWSEjb.create(notificacioWS);
  }


  public void delete(HttpServletRequest request, NotificacioWS notificacioWS) throws Exception,I18NException {
    notificacioWSEjb.delete(notificacioWS);
  }


  public NotificacioWSJPA update(NotificacioWSJPA notificacioWS)
    throws Exception,I18NException, I18NValidationException {
    return (NotificacioWSJPA) notificacioWSEjb.update(notificacioWS);
  }

} // Final de Classe

