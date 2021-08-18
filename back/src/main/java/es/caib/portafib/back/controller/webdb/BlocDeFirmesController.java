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
import es.caib.portafib.back.form.webdb.BlocDeFirmesForm;

import es.caib.portafib.back.validator.webdb.BlocDeFirmesWebValidator;

import es.caib.portafib.jpa.BlocDeFirmesJPA;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un BlocDeFirmes
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/blocDeFirmes")
@SessionAttributes(types = { BlocDeFirmesForm.class, BlocDeFirmesFilterForm.class })
public class BlocDeFirmesController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<BlocDeFirmes, java.lang.Long> implements BlocDeFirmesFields {

  @EJB(mappedName = es.caib.portafib.ejb.BlocDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.BlocDeFirmesLocal blocDeFirmesEjb;

  @Autowired
  private BlocDeFirmesWebValidator blocDeFirmesWebValidator;

  @Autowired
  protected BlocDeFirmesRefList blocDeFirmesRefList;

  // References 
  @Autowired
  protected FluxDeFirmesRefList fluxDeFirmesRefList;

  /**
   * Llistat de totes BlocDeFirmes
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    BlocDeFirmesFilterForm ff;
    ff = (BlocDeFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar BlocDeFirmes de forma paginada
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
    llistat(mav, request, getBlocDeFirmesFilterForm(pagina, mav, request));
    return mav;
  }

  public BlocDeFirmesFilterForm getBlocDeFirmesFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    BlocDeFirmesFilterForm blocDeFirmesFilterForm;
    blocDeFirmesFilterForm = (BlocDeFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(blocDeFirmesFilterForm == null) {
      blocDeFirmesFilterForm = new BlocDeFirmesFilterForm();
      blocDeFirmesFilterForm.setContexte(getContextWeb());
      blocDeFirmesFilterForm.setEntityNameCode(getEntityNameCode());
      blocDeFirmesFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      blocDeFirmesFilterForm.setNou(true);
    } else {
      blocDeFirmesFilterForm.setNou(false);
    }
    blocDeFirmesFilterForm.setPage(pagina == null ? 1 : pagina);
    return blocDeFirmesFilterForm;
  }

  /**
   * Segona i següent peticions per llistar BlocDeFirmes de forma paginada
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
      @ModelAttribute BlocDeFirmesFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getBlocDeFirmesFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de BlocDeFirmes de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<BlocDeFirmes> llistat(ModelAndView mav, HttpServletRequest request,
     BlocDeFirmesFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<BlocDeFirmes> blocDeFirmes = processarLlistat(blocDeFirmesEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("blocDeFirmesItems", blocDeFirmes);

    mav.addObject("blocDeFirmesFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, blocDeFirmes, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, blocDeFirmes);

    return blocDeFirmes;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(BlocDeFirmesFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<BlocDeFirmes> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field fluxDeFirmesID
    {
      _listSKV = getReferenceListForFluxDeFirmesID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFluxDeFirmesForFluxDeFirmesID(_tmp);
      if (filterForm.getGroupByFields().contains(FLUXDEFIRMESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FLUXDEFIRMESID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    BlocDeFirmesFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<BlocDeFirmes> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_BLOCDEFIRMES_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FLUXDEFIRMESID, filterForm.getMapOfFluxDeFirmesForFluxDeFirmesID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou BlocDeFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearBlocDeFirmesGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    BlocDeFirmesForm blocDeFirmesForm = getBlocDeFirmesForm(null, false, request, mav);
    mav.addObject("blocDeFirmesForm" ,blocDeFirmesForm);
    fillReferencesForForm(blocDeFirmesForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public BlocDeFirmesForm getBlocDeFirmesForm(BlocDeFirmesJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    BlocDeFirmesForm blocDeFirmesForm;
    if(_jpa == null) {
      blocDeFirmesForm = new BlocDeFirmesForm(new BlocDeFirmesJPA(), true);
    } else {
      blocDeFirmesForm = new BlocDeFirmesForm(_jpa, false);
      blocDeFirmesForm.setView(__isView);
    }
    blocDeFirmesForm.setContexte(getContextWeb());
    blocDeFirmesForm.setEntityNameCode(getEntityNameCode());
    blocDeFirmesForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return blocDeFirmesForm;
  }

  public void fillReferencesForForm(BlocDeFirmesForm blocDeFirmesForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (blocDeFirmesForm.getListOfFluxDeFirmesForFluxDeFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFluxDeFirmesID(request, mav, blocDeFirmesForm, null);

 if (!_listSKV.isEmpty())    {
      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
    }
      blocDeFirmesForm.setListOfFluxDeFirmesForFluxDeFirmesID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou BlocDeFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearBlocDeFirmesPost(@ModelAttribute BlocDeFirmesForm blocDeFirmesForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    BlocDeFirmesJPA blocDeFirmes = blocDeFirmesForm.getBlocDeFirmes();

    try {
      preValidate(request, blocDeFirmesForm, result);
      getWebValidator().validate(blocDeFirmesForm, result);
      postValidate(request,blocDeFirmesForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        blocDeFirmes = create(request, blocDeFirmes);
        createMessageSuccess(request, "success.creation", blocDeFirmes.getBlocDeFirmesID());
        blocDeFirmesForm.setBlocDeFirmes(blocDeFirmes);
        return getRedirectWhenCreated(request, blocDeFirmesForm);
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

  @RequestMapping(value = "/view/{blocDeFirmesID}", method = RequestMethod.GET)
  public ModelAndView veureBlocDeFirmesGet(@PathVariable("blocDeFirmesID") java.lang.Long blocDeFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewBlocDeFirmesGet(blocDeFirmesID,
        request, response, true);
  }


  protected ModelAndView editAndViewBlocDeFirmesGet(@PathVariable("blocDeFirmesID") java.lang.Long blocDeFirmesID,
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
    BlocDeFirmesJPA blocDeFirmes = findByPrimaryKey(request, blocDeFirmesID);

    if (blocDeFirmes == null) {
      createMessageWarning(request, "error.notfound", blocDeFirmesID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, blocDeFirmesID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      BlocDeFirmesForm blocDeFirmesForm = getBlocDeFirmesForm(blocDeFirmes, __isView, request, mav);
      blocDeFirmesForm.setView(__isView);
      if(__isView) {
        blocDeFirmesForm.setAllFieldsReadOnly(ALL_BLOCDEFIRMES_FIELDS);
        blocDeFirmesForm.setSaveButtonVisible(false);
        blocDeFirmesForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(blocDeFirmesForm, request, mav);
      mav.addObject("blocDeFirmesForm", blocDeFirmesForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un BlocDeFirmes existent
   */
  @RequestMapping(value = "/{blocDeFirmesID}/edit", method = RequestMethod.GET)
  public ModelAndView editarBlocDeFirmesGet(@PathVariable("blocDeFirmesID") java.lang.Long blocDeFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewBlocDeFirmesGet(blocDeFirmesID,
        request, response, false);
  }



  /**
   * Editar un BlocDeFirmes existent
   */
  @RequestMapping(value = "/{blocDeFirmesID}/edit", method = RequestMethod.POST)
  public String editarBlocDeFirmesPost(@ModelAttribute BlocDeFirmesForm blocDeFirmesForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    BlocDeFirmesJPA blocDeFirmes = blocDeFirmesForm.getBlocDeFirmes();

    try {
      preValidate(request, blocDeFirmesForm, result);
      getWebValidator().validate(blocDeFirmes, result);
      postValidate(request, blocDeFirmesForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        blocDeFirmes = update(request, blocDeFirmes);
        createMessageSuccess(request, "success.modification", blocDeFirmes.getBlocDeFirmesID());
        status.setComplete();
        return getRedirectWhenModified(request, blocDeFirmesForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          blocDeFirmes.getBlocDeFirmesID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, blocDeFirmesForm, __e);
    }

  }


  /**
   * Eliminar un BlocDeFirmes existent
   */
  @RequestMapping(value = "/{blocDeFirmesID}/delete")
  public String eliminarBlocDeFirmes(@PathVariable("blocDeFirmesID") java.lang.Long blocDeFirmesID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      BlocDeFirmes blocDeFirmes = findByPrimaryKey(request, blocDeFirmesID);
      if (blocDeFirmes == null) {
        String __msg =createMessageError(request, "error.notfound", blocDeFirmesID);
        return getRedirectWhenDelete(request, blocDeFirmesID, new Exception(__msg));
      } else {
        delete(request, blocDeFirmes);
        createMessageSuccess(request, "success.deleted", blocDeFirmesID);
        return getRedirectWhenDelete(request, blocDeFirmesID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", blocDeFirmesID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, blocDeFirmesID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute BlocDeFirmesFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarBlocDeFirmes(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __blocDeFirmesID, Throwable e) {
    java.lang.Long blocDeFirmesID = (java.lang.Long)__blocDeFirmesID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (blocDeFirmesID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(blocDeFirmesID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "blocDeFirmes.blocDeFirmes";
  }

  public String getEntityNameCodePlural() {
    return "blocDeFirmes.blocDeFirmes.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("blocDeFirmes.blocDeFirmesID");
  }

  @InitBinder("blocDeFirmesFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("blocDeFirmesForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    initDisallowedFields(binder, "blocDeFirmes.blocDeFirmesID");
  }

  public BlocDeFirmesWebValidator getWebValidator() {
    return blocDeFirmesWebValidator;
  }


  public void setWebValidator(BlocDeFirmesWebValidator __val) {
    if (__val != null) {
      this.blocDeFirmesWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de BlocDeFirmes
   */
  @RequestMapping(value = "/{blocDeFirmesID}/cancel")
  public String cancelBlocDeFirmes(@PathVariable("blocDeFirmesID") java.lang.Long blocDeFirmesID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, blocDeFirmesID);
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


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, BlocDeFirmesForm blocDeFirmesForm, Where where)  throws I18NException {
    if (blocDeFirmesForm.isHiddenField(FLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _where = null;
    if (blocDeFirmesForm.isReadOnlyField(FLUXDEFIRMESID)) {
      _where = FluxDeFirmesFields.FLUXDEFIRMESID.equal(blocDeFirmesForm.getBlocDeFirmes().getFluxDeFirmesID());
    }
    return getReferenceListForFluxDeFirmesID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, BlocDeFirmesFilterForm blocDeFirmesFilterForm,
       List<BlocDeFirmes> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (blocDeFirmesFilterForm.isHiddenField(FLUXDEFIRMESID)
      && !blocDeFirmesFilterForm.isGroupByField(FLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST_UNMODIFIABLE;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FLUXDEFIRMESID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (BlocDeFirmes _item : list) {
        _pkList.add(_item.getFluxDeFirmesID());
        }
        _w = FluxDeFirmesFields.FLUXDEFIRMESID.in(_pkList);
      }
    return getReferenceListForFluxDeFirmesID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return fluxDeFirmesRefList.getReferenceList(FluxDeFirmesFields.FLUXDEFIRMESID, where );
  }


  public void preValidate(HttpServletRequest request,BlocDeFirmesForm blocDeFirmesForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,BlocDeFirmesForm blocDeFirmesForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, BlocDeFirmesFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, BlocDeFirmesFilterForm filterForm,  List<BlocDeFirmes> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, BlocDeFirmesForm blocDeFirmesForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, BlocDeFirmesForm blocDeFirmesForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long blocDeFirmesID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long blocDeFirmesID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "blocDeFirmesFormWebDB";
  }

  public String getTileList() {
    return "blocDeFirmesListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "BlocDeFirmesWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public BlocDeFirmesJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long blocDeFirmesID) throws I18NException {
    return (BlocDeFirmesJPA) blocDeFirmesEjb.findByPrimaryKey(blocDeFirmesID);
  }


  public BlocDeFirmesJPA create(HttpServletRequest request, BlocDeFirmesJPA blocDeFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (BlocDeFirmesJPA) blocDeFirmesEjb.create(blocDeFirmes);
  }


  public BlocDeFirmesJPA update(HttpServletRequest request, BlocDeFirmesJPA blocDeFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (BlocDeFirmesJPA) blocDeFirmesEjb.update(blocDeFirmes);
  }


  public void delete(HttpServletRequest request, BlocDeFirmes blocDeFirmes) throws Exception,I18NException {
    blocDeFirmesEjb.delete(blocDeFirmes);
  }

} // Final de Classe

