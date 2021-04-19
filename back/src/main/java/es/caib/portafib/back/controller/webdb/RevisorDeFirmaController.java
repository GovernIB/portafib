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
import es.caib.portafib.back.form.webdb.RevisorDeFirmaForm;

import es.caib.portafib.back.validator.webdb.RevisorDeFirmaWebValidator;

import es.caib.portafib.jpa.RevisorDeFirmaJPA;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un RevisorDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/revisorDeFirma")
@SessionAttributes(types = { RevisorDeFirmaForm.class, RevisorDeFirmaFilterForm.class })
public class RevisorDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<RevisorDeFirma, java.lang.Long> implements RevisorDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeFirmaLocal revisorDeFirmaEjb;

  @Autowired
  private RevisorDeFirmaWebValidator revisorDeFirmaWebValidator;

  @Autowired
  protected RevisorDeFirmaRefList revisorDeFirmaRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected FirmaRefList firmaRefList;

  /**
   * Llistat de totes RevisorDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RevisorDeFirmaFilterForm ff;
    ff = (RevisorDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar RevisorDeFirma de forma paginada
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
    llistat(mav, request, getRevisorDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public RevisorDeFirmaFilterForm getRevisorDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RevisorDeFirmaFilterForm revisorDeFirmaFilterForm;
    revisorDeFirmaFilterForm = (RevisorDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(revisorDeFirmaFilterForm == null) {
      revisorDeFirmaFilterForm = new RevisorDeFirmaFilterForm();
      revisorDeFirmaFilterForm.setContexte(getContextWeb());
      revisorDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      revisorDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      revisorDeFirmaFilterForm.setNou(true);
    } else {
      revisorDeFirmaFilterForm.setNou(false);
    }
    revisorDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return revisorDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar RevisorDeFirma de forma paginada
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
      @ModelAttribute RevisorDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRevisorDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de RevisorDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<RevisorDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     RevisorDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<RevisorDeFirma> revisorDeFirma = processarLlistat(revisorDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("revisorDeFirmaItems", revisorDeFirma);

    mav.addObject("revisorDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, revisorDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, revisorDeFirma);

    return revisorDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RevisorDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<RevisorDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field firmaID
    {
      _listSKV = getReferenceListForFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFirmaForFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(FIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FIRMAID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, OBLIGATORI);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    RevisorDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<RevisorDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_REVISORDEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(FIRMAID, filterForm.getMapOfFirmaForFirmaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou RevisorDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRevisorDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RevisorDeFirmaForm revisorDeFirmaForm = getRevisorDeFirmaForm(null, false, request, mav);
    mav.addObject("revisorDeFirmaForm" ,revisorDeFirmaForm);
    fillReferencesForForm(revisorDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RevisorDeFirmaForm getRevisorDeFirmaForm(RevisorDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RevisorDeFirmaForm revisorDeFirmaForm;
    if(_jpa == null) {
      revisorDeFirmaForm = new RevisorDeFirmaForm(new RevisorDeFirmaJPA(), true);
    } else {
      revisorDeFirmaForm = new RevisorDeFirmaForm(_jpa, false);
      revisorDeFirmaForm.setView(__isView);
    }
    revisorDeFirmaForm.setContexte(getContextWeb());
    revisorDeFirmaForm.setEntityNameCode(getEntityNameCode());
    revisorDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return revisorDeFirmaForm;
  }

  public void fillReferencesForForm(RevisorDeFirmaForm revisorDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (revisorDeFirmaForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, revisorDeFirmaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      revisorDeFirmaForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (revisorDeFirmaForm.getListOfFirmaForFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFirmaID(request, mav, revisorDeFirmaForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      revisorDeFirmaForm.setListOfFirmaForFirmaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou RevisorDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRevisorDeFirmaPost(@ModelAttribute RevisorDeFirmaForm revisorDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RevisorDeFirmaJPA revisorDeFirma = revisorDeFirmaForm.getRevisorDeFirma();

    try {
      preValidate(request, revisorDeFirmaForm, result);
      getWebValidator().validate(revisorDeFirmaForm, result);
      postValidate(request,revisorDeFirmaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        revisorDeFirma = create(request, revisorDeFirma);
        createMessageSuccess(request, "success.creation", revisorDeFirma.getRevisorDeFirmaID());
        revisorDeFirmaForm.setRevisorDeFirma(revisorDeFirma);
        return getRedirectWhenCreated(request, revisorDeFirmaForm);
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

  @RequestMapping(value = "/view/{revisorDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView veureRevisorDeFirmaGet(@PathVariable("revisorDeFirmaID") java.lang.Long revisorDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRevisorDeFirmaGet(revisorDeFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewRevisorDeFirmaGet(@PathVariable("revisorDeFirmaID") java.lang.Long revisorDeFirmaID,
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
    RevisorDeFirmaJPA revisorDeFirma = findByPrimaryKey(request, revisorDeFirmaID);

    if (revisorDeFirma == null) {
      createMessageWarning(request, "error.notfound", revisorDeFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, revisorDeFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RevisorDeFirmaForm revisorDeFirmaForm = getRevisorDeFirmaForm(revisorDeFirma, __isView, request, mav);
      revisorDeFirmaForm.setView(__isView);
      if(__isView) {
        revisorDeFirmaForm.setAllFieldsReadOnly(ALL_REVISORDEFIRMA_FIELDS);
        revisorDeFirmaForm.setSaveButtonVisible(false);
        revisorDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(revisorDeFirmaForm, request, mav);
      mav.addObject("revisorDeFirmaForm", revisorDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un RevisorDeFirma existent
   */
  @RequestMapping(value = "/{revisorDeFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarRevisorDeFirmaGet(@PathVariable("revisorDeFirmaID") java.lang.Long revisorDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRevisorDeFirmaGet(revisorDeFirmaID,
        request, response, false);
  }



  /**
   * Editar un RevisorDeFirma existent
   */
  @RequestMapping(value = "/{revisorDeFirmaID}/edit", method = RequestMethod.POST)
  public String editarRevisorDeFirmaPost(@ModelAttribute @Valid RevisorDeFirmaForm revisorDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RevisorDeFirmaJPA revisorDeFirma = revisorDeFirmaForm.getRevisorDeFirma();

    try {
      preValidate(request, revisorDeFirmaForm, result);
      getWebValidator().validate(revisorDeFirma, result);
      postValidate(request, revisorDeFirmaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        revisorDeFirma = update(request, revisorDeFirma);
        createMessageSuccess(request, "success.modification", revisorDeFirma.getRevisorDeFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, revisorDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          revisorDeFirma.getRevisorDeFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, revisorDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un RevisorDeFirma existent
   */
  @RequestMapping(value = "/{revisorDeFirmaID}/delete")
  public String eliminarRevisorDeFirma(@PathVariable("revisorDeFirmaID") java.lang.Long revisorDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      RevisorDeFirma revisorDeFirma = revisorDeFirmaEjb.findByPrimaryKey(revisorDeFirmaID);
      if (revisorDeFirma == null) {
        String __msg =createMessageError(request, "error.notfound", revisorDeFirmaID);
        return getRedirectWhenDelete(request, revisorDeFirmaID, new Exception(__msg));
      } else {
        delete(request, revisorDeFirma);
        createMessageSuccess(request, "success.deleted", revisorDeFirmaID);
        return getRedirectWhenDelete(request, revisorDeFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", revisorDeFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, revisorDeFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute RevisorDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRevisorDeFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __revisorDeFirmaID, Throwable e) {
    java.lang.Long revisorDeFirmaID = (java.lang.Long)__revisorDeFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (revisorDeFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(revisorDeFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "revisorDeFirma.revisorDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "revisorDeFirma.revisorDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("revisorDeFirma.revisorDeFirmaID");
  }

  @InitBinder("revisorDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("revisorDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "revisorDeFirma.revisorDeFirmaID");
  }

  public RevisorDeFirmaWebValidator getWebValidator() {
    return revisorDeFirmaWebValidator;
  }


  public void setWebValidator(RevisorDeFirmaWebValidator __val) {
    if (__val != null) {
      this.revisorDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de RevisorDeFirma
   */
  @RequestMapping(value = "/{revisorDeFirmaID}/cancel")
  public String cancelRevisorDeFirma(@PathVariable("revisorDeFirmaID") java.lang.Long revisorDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, revisorDeFirmaID);
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
       ModelAndView mav, RevisorDeFirmaForm revisorDeFirmaForm, Where where)  throws I18NException {
    if (revisorDeFirmaForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (revisorDeFirmaForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(revisorDeFirmaForm.getRevisorDeFirma().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, RevisorDeFirmaFilterForm revisorDeFirmaFilterForm,
       List<RevisorDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (revisorDeFirmaFilterForm.isHiddenField(USUARIENTITATID)
      && !revisorDeFirmaFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RevisorDeFirma _item : list) {
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


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, RevisorDeFirmaForm revisorDeFirmaForm, Where where)  throws I18NException {
    if (revisorDeFirmaForm.isHiddenField(FIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (revisorDeFirmaForm.isReadOnlyField(FIRMAID)) {
      _where = FirmaFields.FIRMAID.equal(revisorDeFirmaForm.getRevisorDeFirma().getFirmaID());
    }
    return getReferenceListForFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, RevisorDeFirmaFilterForm revisorDeFirmaFilterForm,
       List<RevisorDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (revisorDeFirmaFilterForm.isHiddenField(FIRMAID)
      && !revisorDeFirmaFilterForm.isGroupByField(FIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (RevisorDeFirma _item : list) {
        _pkList.add(_item.getFirmaID());
        }
        _w = FirmaFields.FIRMAID.in(_pkList);
      }
    return getReferenceListForFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return firmaRefList.getReferenceList(FirmaFields.FIRMAID, where );
  }


  public void preValidate(HttpServletRequest request,RevisorDeFirmaForm revisorDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RevisorDeFirmaForm revisorDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RevisorDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RevisorDeFirmaFilterForm filterForm,  List<RevisorDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, RevisorDeFirmaForm revisorDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, RevisorDeFirmaForm revisorDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long revisorDeFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long revisorDeFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "revisorDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "revisorDeFirmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "RevisorDeFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RevisorDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long revisorDeFirmaID) throws I18NException {
    return (RevisorDeFirmaJPA) revisorDeFirmaEjb.findByPrimaryKey(revisorDeFirmaID);
  }


  public RevisorDeFirmaJPA create(HttpServletRequest request, RevisorDeFirmaJPA revisorDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (RevisorDeFirmaJPA) revisorDeFirmaEjb.create(revisorDeFirma);
  }


  public RevisorDeFirmaJPA update(HttpServletRequest request, RevisorDeFirmaJPA revisorDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (RevisorDeFirmaJPA) revisorDeFirmaEjb.update(revisorDeFirma);
  }


  public void delete(HttpServletRequest request, RevisorDeFirma revisorDeFirma) throws Exception,I18NException {
    revisorDeFirmaEjb.delete(revisorDeFirma);
  }

} // Final de Classe

