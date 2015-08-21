package es.caib.portafib.back.controller.webdb;

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
import es.caib.portafib.back.form.webdb.TipusEstatDeFirmaInicialForm;

import es.caib.portafib.back.validator.webdb.TipusEstatDeFirmaInicialWebValidator;

import es.caib.portafib.jpa.TipusEstatDeFirmaInicialJPA;
import es.caib.portafib.model.entity.TipusEstatDeFirmaInicial;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusEstatDeFirmaInicial
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusEstatDeFirmaInicial")
@SessionAttributes(types = { TipusEstatDeFirmaInicialForm.class, TipusEstatDeFirmaInicialFilterForm.class })
public class TipusEstatDeFirmaInicialController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<TipusEstatDeFirmaInicial, java.lang.Long> implements TipusEstatDeFirmaInicialFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusEstatDeFirmaInicialLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusEstatDeFirmaInicialLocal tipusEstatDeFirmaInicialEjb;

  @Autowired
  private TipusEstatDeFirmaInicialWebValidator tipusEstatDeFirmaInicialWebValidator;

  @Autowired
  protected TipusEstatDeFirmaInicialRefList tipusEstatDeFirmaInicialRefList;

  /**
   * Llistat de totes TipusEstatDeFirmaInicial
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusEstatDeFirmaInicialFilterForm ff;
    ff = (TipusEstatDeFirmaInicialFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusEstatDeFirmaInicial de forma paginada
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
    llistat(mav, request, getTipusEstatDeFirmaInicialFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusEstatDeFirmaInicialFilterForm getTipusEstatDeFirmaInicialFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusEstatDeFirmaInicialFilterForm tipusEstatDeFirmaInicialFilterForm;
    tipusEstatDeFirmaInicialFilterForm = (TipusEstatDeFirmaInicialFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusEstatDeFirmaInicialFilterForm == null) {
      tipusEstatDeFirmaInicialFilterForm = new TipusEstatDeFirmaInicialFilterForm();
      tipusEstatDeFirmaInicialFilterForm.setContexte(getContextWeb());
      tipusEstatDeFirmaInicialFilterForm.setEntityNameCode(getEntityNameCode());
      tipusEstatDeFirmaInicialFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusEstatDeFirmaInicialFilterForm.setNou(true);
    } else {
      tipusEstatDeFirmaInicialFilterForm.setNou(false);
    }
    tipusEstatDeFirmaInicialFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusEstatDeFirmaInicialFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusEstatDeFirmaInicial de forma paginada
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
      @ModelAttribute TipusEstatDeFirmaInicialFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusEstatDeFirmaInicialFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusEstatDeFirmaInicial de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusEstatDeFirmaInicial> llistat(ModelAndView mav, HttpServletRequest request,
     TipusEstatDeFirmaInicialFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TipusEstatDeFirmaInicial> tipusEstatDeFirmaInicial = processarLlistat(tipusEstatDeFirmaInicialEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusEstatDeFirmaInicialItems", tipusEstatDeFirmaInicial);

    mav.addObject("tipusEstatDeFirmaInicialFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusEstatDeFirmaInicial, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusEstatDeFirmaInicial);

    return tipusEstatDeFirmaInicial;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusEstatDeFirmaInicialFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusEstatDeFirmaInicial> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusEstatDeFirmaInicialFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusEstatDeFirmaInicial> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSESTATDEFIRMAINICIAL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusEstatDeFirmaInicial
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusEstatDeFirmaInicialGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm = getTipusEstatDeFirmaInicialForm(null, false, request, mav);
    mav.addObject("tipusEstatDeFirmaInicialForm" ,tipusEstatDeFirmaInicialForm);
    fillReferencesForForm(tipusEstatDeFirmaInicialForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusEstatDeFirmaInicialForm getTipusEstatDeFirmaInicialForm(TipusEstatDeFirmaInicialJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm;
    if(_jpa == null) {
      tipusEstatDeFirmaInicialForm = new TipusEstatDeFirmaInicialForm(new TipusEstatDeFirmaInicialJPA(), true);
    } else {
      tipusEstatDeFirmaInicialForm = new TipusEstatDeFirmaInicialForm(_jpa, false);
      tipusEstatDeFirmaInicialForm.setView(__isView);
    }
    tipusEstatDeFirmaInicialForm.setContexte(getContextWeb());
    tipusEstatDeFirmaInicialForm.setEntityNameCode(getEntityNameCode());
    tipusEstatDeFirmaInicialForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusEstatDeFirmaInicialForm;
  }

  public void fillReferencesForForm(TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusEstatDeFirmaInicial
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusEstatDeFirmaInicialPost(@ModelAttribute TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial = tipusEstatDeFirmaInicialForm.getTipusEstatDeFirmaInicial();

    try {
      preValidate(request, tipusEstatDeFirmaInicialForm, result);
      getWebValidator().validate(tipusEstatDeFirmaInicialForm, result);
      postValidate(request,tipusEstatDeFirmaInicialForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusEstatDeFirmaInicial = create(request, tipusEstatDeFirmaInicial);
        createMessageSuccess(request, "success.creation", tipusEstatDeFirmaInicial.getTipusEstatDeFirmaInicialID());
        tipusEstatDeFirmaInicialForm.setTipusEstatDeFirmaInicial(tipusEstatDeFirmaInicial);
        return getRedirectWhenCreated(request, tipusEstatDeFirmaInicialForm);
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

  @RequestMapping(value = "/view/{tipusEstatDeFirmaInicialID}", method = RequestMethod.GET)
  public ModelAndView veureTipusEstatDeFirmaInicialGet(@PathVariable("tipusEstatDeFirmaInicialID") java.lang.Long tipusEstatDeFirmaInicialID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusEstatDeFirmaInicialGet(tipusEstatDeFirmaInicialID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusEstatDeFirmaInicialGet(@PathVariable("tipusEstatDeFirmaInicialID") java.lang.Long tipusEstatDeFirmaInicialID,
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
    TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial = findByPrimaryKey(request, tipusEstatDeFirmaInicialID);

    if (tipusEstatDeFirmaInicial == null) {
      createMessageWarning(request, "error.notfound", tipusEstatDeFirmaInicialID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusEstatDeFirmaInicialID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm = getTipusEstatDeFirmaInicialForm(tipusEstatDeFirmaInicial, __isView, request, mav);
      tipusEstatDeFirmaInicialForm.setView(__isView);
      if(__isView) {
        tipusEstatDeFirmaInicialForm.setAllFieldsReadOnly(ALL_TIPUSESTATDEFIRMAINICIAL_FIELDS);
        tipusEstatDeFirmaInicialForm.setSaveButtonVisible(false);
        tipusEstatDeFirmaInicialForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusEstatDeFirmaInicialForm, request, mav);
      mav.addObject("tipusEstatDeFirmaInicialForm", tipusEstatDeFirmaInicialForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusEstatDeFirmaInicial existent
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaInicialID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusEstatDeFirmaInicialGet(@PathVariable("tipusEstatDeFirmaInicialID") java.lang.Long tipusEstatDeFirmaInicialID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusEstatDeFirmaInicialGet(tipusEstatDeFirmaInicialID,
        request, response, false);
  }



  /**
   * Editar un TipusEstatDeFirmaInicial existent
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaInicialID}/edit", method = RequestMethod.POST)
  public String editarTipusEstatDeFirmaInicialPost(@ModelAttribute @Valid TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial = tipusEstatDeFirmaInicialForm.getTipusEstatDeFirmaInicial();

    try {
      preValidate(request, tipusEstatDeFirmaInicialForm, result);
      getWebValidator().validate(tipusEstatDeFirmaInicial, result);
      postValidate(request, tipusEstatDeFirmaInicialForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusEstatDeFirmaInicial = update(request, tipusEstatDeFirmaInicial);
        createMessageSuccess(request, "success.modification", tipusEstatDeFirmaInicial.getTipusEstatDeFirmaInicialID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusEstatDeFirmaInicialForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusEstatDeFirmaInicial.getTipusEstatDeFirmaInicialID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusEstatDeFirmaInicialForm, __e);
    }

  }


  /**
   * Eliminar un TipusEstatDeFirmaInicial existent
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaInicialID}/delete")
  public String eliminarTipusEstatDeFirmaInicial(@PathVariable("tipusEstatDeFirmaInicialID") java.lang.Long tipusEstatDeFirmaInicialID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusEstatDeFirmaInicial tipusEstatDeFirmaInicial = tipusEstatDeFirmaInicialEjb.findByPrimaryKey(tipusEstatDeFirmaInicialID);
      if (tipusEstatDeFirmaInicial == null) {
        String __msg =createMessageError(request, "error.notfound", tipusEstatDeFirmaInicialID);
        return getRedirectWhenDelete(request, tipusEstatDeFirmaInicialID, new Exception(__msg));
      } else {
        delete(request, tipusEstatDeFirmaInicial);
        createMessageSuccess(request, "success.deleted", tipusEstatDeFirmaInicialID);
        return getRedirectWhenDelete(request, tipusEstatDeFirmaInicialID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusEstatDeFirmaInicialID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusEstatDeFirmaInicialID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusEstatDeFirmaInicialFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusEstatDeFirmaInicial(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusEstatDeFirmaInicialID, Throwable e) {
    java.lang.Long tipusEstatDeFirmaInicialID = (java.lang.Long)__tipusEstatDeFirmaInicialID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusEstatDeFirmaInicialID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusEstatDeFirmaInicialID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial";
  }

  public String getEntityNameCodePlural() {
    return "tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID");
  }

  @InitBinder("tipusEstatDeFirmaInicialFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusEstatDeFirmaInicialForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusEstatDeFirmaInicialWebValidator getWebValidator() {
    return tipusEstatDeFirmaInicialWebValidator;
  }


  public void setWebValidator(TipusEstatDeFirmaInicialWebValidator __val) {
    if (__val != null) {
      this.tipusEstatDeFirmaInicialWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusEstatDeFirmaInicial
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaInicialID}/cancel")
  public String cancelTipusEstatDeFirmaInicial(@PathVariable("tipusEstatDeFirmaInicialID") java.lang.Long tipusEstatDeFirmaInicialID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusEstatDeFirmaInicialID);
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


  public void preValidate(HttpServletRequest request,TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusEstatDeFirmaInicialFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusEstatDeFirmaInicialFilterForm filterForm,  List<TipusEstatDeFirmaInicial> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusEstatDeFirmaInicialForm tipusEstatDeFirmaInicialForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tipusEstatDeFirmaInicialID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tipusEstatDeFirmaInicialID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusEstatDeFirmaInicialFormWebDB";
  }

  public String getTileList() {
    return "tipusEstatDeFirmaInicialListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusEstatDeFirmaInicialWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusEstatDeFirmaInicialJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tipusEstatDeFirmaInicialID) throws I18NException {
    return (TipusEstatDeFirmaInicialJPA) tipusEstatDeFirmaInicialEjb.findByPrimaryKey(tipusEstatDeFirmaInicialID);
  }


  public TipusEstatDeFirmaInicialJPA create(HttpServletRequest request, TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial)
    throws Exception,I18NException, I18NValidationException {
    return (TipusEstatDeFirmaInicialJPA) tipusEstatDeFirmaInicialEjb.create(tipusEstatDeFirmaInicial);
  }


  public TipusEstatDeFirmaInicialJPA update(HttpServletRequest request, TipusEstatDeFirmaInicialJPA tipusEstatDeFirmaInicial)
    throws Exception,I18NException, I18NValidationException {
    return (TipusEstatDeFirmaInicialJPA) tipusEstatDeFirmaInicialEjb.update(tipusEstatDeFirmaInicial);
  }


  public void delete(HttpServletRequest request, TipusEstatDeFirmaInicial tipusEstatDeFirmaInicial) throws Exception,I18NException {
    tipusEstatDeFirmaInicialEjb.delete(tipusEstatDeFirmaInicial);
  }

} // Final de Classe

