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
import es.caib.portafib.back.form.webdb.RevisorDeDestinatariForm;

import es.caib.portafib.back.validator.webdb.RevisorDeDestinatariWebValidator;

import es.caib.portafib.persistence.RevisorDeDestinatariJPA;
import es.caib.portafib.model.entity.RevisorDeDestinatari;
import es.caib.portafib.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;

/**
 * Controller per gestionar un RevisorDeDestinatari
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="revisorDeDestinatari.revisorDeDestinatari.plural", order=310, group="WEBDB")
@Controller
@RequestMapping(value = "/webdb/revisorDeDestinatari")
@SessionAttributes(types = { RevisorDeDestinatariForm.class, RevisorDeDestinatariFilterForm.class })
public class RevisorDeDestinatariController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<RevisorDeDestinatari, java.lang.Long> implements RevisorDeDestinatariFields {

  @EJB(mappedName = es.caib.portafib.ejb.RevisorDeDestinatariService.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeDestinatariService revisorDeDestinatariEjb;

  @Autowired
  private RevisorDeDestinatariWebValidator revisorDeDestinatariWebValidator;

  @Autowired
  protected RevisorDeDestinatariRefList revisorDeDestinatariRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes RevisorDeDestinatari
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    RevisorDeDestinatariFilterForm ff;
    ff = (RevisorDeDestinatariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar RevisorDeDestinatari de forma paginada
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
    llistat(mav, request, getRevisorDeDestinatariFilterForm(pagina, mav, request));
    return mav;
  }

  public RevisorDeDestinatariFilterForm getRevisorDeDestinatariFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    RevisorDeDestinatariFilterForm revisorDeDestinatariFilterForm;
    revisorDeDestinatariFilterForm = (RevisorDeDestinatariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(revisorDeDestinatariFilterForm == null) {
      revisorDeDestinatariFilterForm = new RevisorDeDestinatariFilterForm();
      revisorDeDestinatariFilterForm.setContexte(getContextWeb());
      revisorDeDestinatariFilterForm.setEntityNameCode(getEntityNameCode());
      revisorDeDestinatariFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      revisorDeDestinatariFilterForm.setNou(true);
    } else {
      revisorDeDestinatariFilterForm.setNou(false);
    }
    revisorDeDestinatariFilterForm.setPage(pagina == null ? 1 : pagina);
    return revisorDeDestinatariFilterForm;
  }

  /**
   * Segona i següent peticions per llistar RevisorDeDestinatari de forma paginada
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
      @ModelAttribute RevisorDeDestinatariFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getRevisorDeDestinatariFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de RevisorDeDestinatari de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<RevisorDeDestinatari> llistat(ModelAndView mav, HttpServletRequest request,
     RevisorDeDestinatariFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<RevisorDeDestinatari> revisorDeDestinatari = processarLlistat(revisorDeDestinatariEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("revisorDeDestinatariItems", revisorDeDestinatari);

    mav.addObject("revisorDeDestinatariFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, revisorDeDestinatari, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, revisorDeDestinatari);

    return revisorDeDestinatari;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(RevisorDeDestinatariFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<RevisorDeDestinatari> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field destinatariID
    {
      _listSKV = getReferenceListForDestinatariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForDestinatariID(_tmp);
      if (filterForm.getGroupByFields().contains(DESTINATARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DESTINATARIID, false);
      };
    }

    // Field revisorID
    {
      _listSKV = getReferenceListForRevisorID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForRevisorID(_tmp);
      if (filterForm.getGroupByFields().contains(REVISORID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, REVISORID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    RevisorDeDestinatariFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<RevisorDeDestinatari> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_REVISORDEDESTINATARI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(DESTINATARIID, filterForm.getMapOfUsuariEntitatForDestinatariID());
    __mapping.put(REVISORID, filterForm.getMapOfUsuariEntitatForRevisorID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou RevisorDeDestinatari
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearRevisorDeDestinatariGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    RevisorDeDestinatariForm revisorDeDestinatariForm = getRevisorDeDestinatariForm(null, false, request, mav);
    mav.addObject("revisorDeDestinatariForm" ,revisorDeDestinatariForm);
    fillReferencesForForm(revisorDeDestinatariForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public RevisorDeDestinatariForm getRevisorDeDestinatariForm(RevisorDeDestinatariJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    RevisorDeDestinatariForm revisorDeDestinatariForm;
    if(_jpa == null) {
      revisorDeDestinatariForm = new RevisorDeDestinatariForm(new RevisorDeDestinatariJPA(), true);
    } else {
      revisorDeDestinatariForm = new RevisorDeDestinatariForm(_jpa, false);
      revisorDeDestinatariForm.setView(__isView);
    }
    revisorDeDestinatariForm.setContexte(getContextWeb());
    revisorDeDestinatariForm.setEntityNameCode(getEntityNameCode());
    revisorDeDestinatariForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return revisorDeDestinatariForm;
  }

  public void fillReferencesForForm(RevisorDeDestinatariForm revisorDeDestinatariForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (revisorDeDestinatariForm.getListOfUsuariEntitatForDestinatariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForDestinatariID(request, mav, revisorDeDestinatariForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      revisorDeDestinatariForm.setListOfUsuariEntitatForDestinatariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (revisorDeDestinatariForm.getListOfUsuariEntitatForRevisorID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForRevisorID(request, mav, revisorDeDestinatariForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      revisorDeDestinatariForm.setListOfUsuariEntitatForRevisorID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou RevisorDeDestinatari
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearRevisorDeDestinatariPost(@ModelAttribute RevisorDeDestinatariForm revisorDeDestinatariForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    RevisorDeDestinatariJPA revisorDeDestinatari = revisorDeDestinatariForm.getRevisorDeDestinatari();

    try {
      preValidate(request, revisorDeDestinatariForm, result);
      getWebValidator().validate(revisorDeDestinatariForm, result);
      postValidate(request,revisorDeDestinatariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        revisorDeDestinatari = create(request, revisorDeDestinatari);
        createMessageSuccess(request, "success.creation", revisorDeDestinatari.getRevisorDeDestinatariID());
        revisorDeDestinatariForm.setRevisorDeDestinatari(revisorDeDestinatari);
        return getRedirectWhenCreated(request, revisorDeDestinatariForm);
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

  @RequestMapping(value = "/view/{revisorDeDestinatariID}", method = RequestMethod.GET)
  public ModelAndView veureRevisorDeDestinatariGet(@PathVariable("revisorDeDestinatariID") java.lang.Long revisorDeDestinatariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRevisorDeDestinatariGet(revisorDeDestinatariID,
        request, response, true);
  }


  protected ModelAndView editAndViewRevisorDeDestinatariGet(@PathVariable("revisorDeDestinatariID") java.lang.Long revisorDeDestinatariID,
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
    RevisorDeDestinatariJPA revisorDeDestinatari = findByPrimaryKey(request, revisorDeDestinatariID);

    if (revisorDeDestinatari == null) {
      createMessageWarning(request, "error.notfound", revisorDeDestinatariID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, revisorDeDestinatariID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      RevisorDeDestinatariForm revisorDeDestinatariForm = getRevisorDeDestinatariForm(revisorDeDestinatari, __isView, request, mav);
      revisorDeDestinatariForm.setView(__isView);
      if(__isView) {
        revisorDeDestinatariForm.setAllFieldsReadOnly(ALL_REVISORDEDESTINATARI_FIELDS);
        revisorDeDestinatariForm.setSaveButtonVisible(false);
        revisorDeDestinatariForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(revisorDeDestinatariForm, request, mav);
      mav.addObject("revisorDeDestinatariForm", revisorDeDestinatariForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un RevisorDeDestinatari existent
   */
  @RequestMapping(value = "/{revisorDeDestinatariID}/edit", method = RequestMethod.GET)
  public ModelAndView editarRevisorDeDestinatariGet(@PathVariable("revisorDeDestinatariID") java.lang.Long revisorDeDestinatariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewRevisorDeDestinatariGet(revisorDeDestinatariID,
        request, response, false);
  }



  /**
   * Editar un RevisorDeDestinatari existent
   */
  @RequestMapping(value = "/{revisorDeDestinatariID}/edit", method = RequestMethod.POST)
  public String editarRevisorDeDestinatariPost(@ModelAttribute RevisorDeDestinatariForm revisorDeDestinatariForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    RevisorDeDestinatariJPA revisorDeDestinatari = revisorDeDestinatariForm.getRevisorDeDestinatari();

    try {
      preValidate(request, revisorDeDestinatariForm, result);
      getWebValidator().validate(revisorDeDestinatariForm, result);
      postValidate(request, revisorDeDestinatariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        revisorDeDestinatari = update(request, revisorDeDestinatari);
        createMessageSuccess(request, "success.modification", revisorDeDestinatari.getRevisorDeDestinatariID());
        status.setComplete();
        return getRedirectWhenModified(request, revisorDeDestinatariForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          revisorDeDestinatari.getRevisorDeDestinatariID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, revisorDeDestinatariForm, __e);
    }

  }


  /**
   * Eliminar un RevisorDeDestinatari existent
   */
  @RequestMapping(value = "/{revisorDeDestinatariID}/delete")
  public String eliminarRevisorDeDestinatari(@PathVariable("revisorDeDestinatariID") java.lang.Long revisorDeDestinatariID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      RevisorDeDestinatari revisorDeDestinatari = this.findByPrimaryKey(request, revisorDeDestinatariID);
      if (revisorDeDestinatari == null) {
        String __msg = createMessageError(request, "error.notfound", revisorDeDestinatariID);
        return getRedirectWhenDelete(request, revisorDeDestinatariID, new Exception(__msg));
      } else {
        delete(request, revisorDeDestinatari);
        createMessageSuccess(request, "success.deleted", revisorDeDestinatariID);
        return getRedirectWhenDelete(request, revisorDeDestinatariID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", revisorDeDestinatariID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, revisorDeDestinatariID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute RevisorDeDestinatariFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarRevisorDeDestinatari(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __revisorDeDestinatariID, Throwable e) {
    java.lang.Long revisorDeDestinatariID = (java.lang.Long)__revisorDeDestinatariID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (revisorDeDestinatariID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(revisorDeDestinatariID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "revisorDeDestinatari.revisorDeDestinatari";
  }

  public String getEntityNameCodePlural() {
    return "revisorDeDestinatari.revisorDeDestinatari.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("revisorDeDestinatari.revisorDeDestinatariID");
  }

  @InitBinder("revisorDeDestinatariFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("revisorDeDestinatariForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "revisorDeDestinatari.revisorDeDestinatariID");
  }

  public RevisorDeDestinatariWebValidator getWebValidator() {
    return revisorDeDestinatariWebValidator;
  }


  public void setWebValidator(RevisorDeDestinatariWebValidator __val) {
    if (__val != null) {
      this.revisorDeDestinatariWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de RevisorDeDestinatari
   */
  @RequestMapping(value = "/{revisorDeDestinatariID}/cancel")
  public String cancelRevisorDeDestinatari(@PathVariable("revisorDeDestinatariID") java.lang.Long revisorDeDestinatariID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, revisorDeDestinatariID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de RevisorDeDestinatari
   */
  @RequestMapping(value = "/cancel")
  public String cancelRevisorDeDestinatari(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, RevisorDeDestinatariForm revisorDeDestinatariForm, Where where)  throws I18NException {
    if (revisorDeDestinatariForm.isHiddenField(DESTINATARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (revisorDeDestinatariForm.isReadOnlyField(DESTINATARIID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(revisorDeDestinatariForm.getRevisorDeDestinatari().getDestinatariID());
    }
    return getReferenceListForDestinatariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, RevisorDeDestinatariFilterForm revisorDeDestinatariFilterForm,
       List<RevisorDeDestinatari> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (revisorDeDestinatariFilterForm.isHiddenField(DESTINATARIID)
       && !revisorDeDestinatariFilterForm.isGroupByField(DESTINATARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESTINATARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RevisorDeDestinatari _item : list) {
        _pkList.add(_item.getDestinatariID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForDestinatariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForRevisorID(HttpServletRequest request,
       ModelAndView mav, RevisorDeDestinatariForm revisorDeDestinatariForm, Where where)  throws I18NException {
    if (revisorDeDestinatariForm.isHiddenField(REVISORID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (revisorDeDestinatariForm.isReadOnlyField(REVISORID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(revisorDeDestinatariForm.getRevisorDeDestinatari().getRevisorID());
    }
    return getReferenceListForRevisorID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForRevisorID(HttpServletRequest request,
       ModelAndView mav, RevisorDeDestinatariFilterForm revisorDeDestinatariFilterForm,
       List<RevisorDeDestinatari> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (revisorDeDestinatariFilterForm.isHiddenField(REVISORID)
       && !revisorDeDestinatariFilterForm.isGroupByField(REVISORID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(REVISORID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (RevisorDeDestinatari _item : list) {
        _pkList.add(_item.getRevisorID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForRevisorID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForRevisorID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,RevisorDeDestinatariForm revisorDeDestinatariForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,RevisorDeDestinatariForm revisorDeDestinatariForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, RevisorDeDestinatariFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, RevisorDeDestinatariFilterForm filterForm,  List<RevisorDeDestinatari> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, RevisorDeDestinatariForm revisorDeDestinatariForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, RevisorDeDestinatariForm revisorDeDestinatariForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long revisorDeDestinatariID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long revisorDeDestinatariID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "revisorDeDestinatariFormWebDB";
  }

  public String getTileList() {
    return "revisorDeDestinatariListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "RevisorDeDestinatari_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public RevisorDeDestinatariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long revisorDeDestinatariID) throws I18NException {
    return (RevisorDeDestinatariJPA) revisorDeDestinatariEjb.findByPrimaryKey(revisorDeDestinatariID);
  }


  public RevisorDeDestinatariJPA create(HttpServletRequest request, RevisorDeDestinatariJPA revisorDeDestinatari)
    throws I18NException, I18NValidationException {
    return (RevisorDeDestinatariJPA) revisorDeDestinatariEjb.create(revisorDeDestinatari);
  }


  public RevisorDeDestinatariJPA update(HttpServletRequest request, RevisorDeDestinatariJPA revisorDeDestinatari)
    throws I18NException, I18NValidationException {
    return (RevisorDeDestinatariJPA) revisorDeDestinatariEjb.update(revisorDeDestinatari);
  }


  public void delete(HttpServletRequest request, RevisorDeDestinatari revisorDeDestinatari) throws I18NException {
    revisorDeDestinatariEjb.delete(revisorDeDestinatari);
  }

} // Final de Classe

