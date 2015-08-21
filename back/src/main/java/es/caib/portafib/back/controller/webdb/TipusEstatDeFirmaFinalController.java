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
import es.caib.portafib.back.form.webdb.TipusEstatDeFirmaFinalForm;

import es.caib.portafib.back.validator.webdb.TipusEstatDeFirmaFinalWebValidator;

import es.caib.portafib.jpa.TipusEstatDeFirmaFinalJPA;
import es.caib.portafib.model.entity.TipusEstatDeFirmaFinal;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusEstatDeFirmaFinal
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusEstatDeFirmaFinal")
@SessionAttributes(types = { TipusEstatDeFirmaFinalForm.class, TipusEstatDeFirmaFinalFilterForm.class })
public class TipusEstatDeFirmaFinalController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<TipusEstatDeFirmaFinal, java.lang.Long> implements TipusEstatDeFirmaFinalFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusEstatDeFirmaFinalLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusEstatDeFirmaFinalLocal tipusEstatDeFirmaFinalEjb;

  @Autowired
  private TipusEstatDeFirmaFinalWebValidator tipusEstatDeFirmaFinalWebValidator;

  @Autowired
  protected TipusEstatDeFirmaFinalRefList tipusEstatDeFirmaFinalRefList;

  /**
   * Llistat de totes TipusEstatDeFirmaFinal
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusEstatDeFirmaFinalFilterForm ff;
    ff = (TipusEstatDeFirmaFinalFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusEstatDeFirmaFinal de forma paginada
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
    llistat(mav, request, getTipusEstatDeFirmaFinalFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusEstatDeFirmaFinalFilterForm getTipusEstatDeFirmaFinalFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusEstatDeFirmaFinalFilterForm tipusEstatDeFirmaFinalFilterForm;
    tipusEstatDeFirmaFinalFilterForm = (TipusEstatDeFirmaFinalFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusEstatDeFirmaFinalFilterForm == null) {
      tipusEstatDeFirmaFinalFilterForm = new TipusEstatDeFirmaFinalFilterForm();
      tipusEstatDeFirmaFinalFilterForm.setContexte(getContextWeb());
      tipusEstatDeFirmaFinalFilterForm.setEntityNameCode(getEntityNameCode());
      tipusEstatDeFirmaFinalFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusEstatDeFirmaFinalFilterForm.setNou(true);
    } else {
      tipusEstatDeFirmaFinalFilterForm.setNou(false);
    }
    tipusEstatDeFirmaFinalFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusEstatDeFirmaFinalFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusEstatDeFirmaFinal de forma paginada
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
      @ModelAttribute TipusEstatDeFirmaFinalFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusEstatDeFirmaFinalFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusEstatDeFirmaFinal de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusEstatDeFirmaFinal> llistat(ModelAndView mav, HttpServletRequest request,
     TipusEstatDeFirmaFinalFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<TipusEstatDeFirmaFinal> tipusEstatDeFirmaFinal = (List<TipusEstatDeFirmaFinal>) processarLlistat(tipusEstatDeFirmaFinalEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusEstatDeFirmaFinalItems", tipusEstatDeFirmaFinal);

    mav.addObject("tipusEstatDeFirmaFinalFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusEstatDeFirmaFinal, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusEstatDeFirmaFinal);

    return tipusEstatDeFirmaFinal;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusEstatDeFirmaFinalFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusEstatDeFirmaFinal> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusEstatDeFirmaFinalFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusEstatDeFirmaFinal> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSESTATDEFIRMAFINAL_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusEstatDeFirmaFinal
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusEstatDeFirmaFinalGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm = getTipusEstatDeFirmaFinalForm(null, false, request, mav);
    mav.addObject("tipusEstatDeFirmaFinalForm" ,tipusEstatDeFirmaFinalForm);
    fillReferencesForForm(tipusEstatDeFirmaFinalForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusEstatDeFirmaFinalForm getTipusEstatDeFirmaFinalForm(TipusEstatDeFirmaFinalJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm;
    if(_jpa == null) {
      tipusEstatDeFirmaFinalForm = new TipusEstatDeFirmaFinalForm(new TipusEstatDeFirmaFinalJPA(), true);
    } else {
      tipusEstatDeFirmaFinalForm = new TipusEstatDeFirmaFinalForm(_jpa, false);
      tipusEstatDeFirmaFinalForm.setView(__isView);
    }
    tipusEstatDeFirmaFinalForm.setContexte(getContextWeb());
    tipusEstatDeFirmaFinalForm.setEntityNameCode(getEntityNameCode());
    tipusEstatDeFirmaFinalForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusEstatDeFirmaFinalForm;
  }

  public void fillReferencesForForm(TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusEstatDeFirmaFinal
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusEstatDeFirmaFinalPost(@ModelAttribute TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinalForm.getTipusEstatDeFirmaFinal();

    try {
      preValidate(request, tipusEstatDeFirmaFinalForm, result);
      getWebValidator().validate(tipusEstatDeFirmaFinalForm, result);
      postValidate(request,tipusEstatDeFirmaFinalForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusEstatDeFirmaFinal = create(request, tipusEstatDeFirmaFinal);
        createMessageSuccess(request, "success.creation", tipusEstatDeFirmaFinal.getTipusEstatDeFirmaFinalID());
        tipusEstatDeFirmaFinalForm.setTipusEstatDeFirmaFinal(tipusEstatDeFirmaFinal);
        return getRedirectWhenCreated(request, tipusEstatDeFirmaFinalForm);
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

  @RequestMapping(value = "/view/{tipusEstatDeFirmaFinalID}", method = RequestMethod.GET)
  public ModelAndView veureTipusEstatDeFirmaFinalGet(@PathVariable("tipusEstatDeFirmaFinalID") java.lang.Long tipusEstatDeFirmaFinalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusEstatDeFirmaFinalGet(tipusEstatDeFirmaFinalID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusEstatDeFirmaFinalGet(@PathVariable("tipusEstatDeFirmaFinalID") java.lang.Long tipusEstatDeFirmaFinalID,
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
    TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal = findByPrimaryKey(request, tipusEstatDeFirmaFinalID);

    if (tipusEstatDeFirmaFinal == null) {
      createMessageWarning(request, "error.notfound", tipusEstatDeFirmaFinalID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusEstatDeFirmaFinalID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm = getTipusEstatDeFirmaFinalForm(tipusEstatDeFirmaFinal, __isView, request, mav);
      tipusEstatDeFirmaFinalForm.setView(__isView);
      if(__isView) {
        tipusEstatDeFirmaFinalForm.setAllFieldsReadOnly(ALL_TIPUSESTATDEFIRMAFINAL_FIELDS);
        tipusEstatDeFirmaFinalForm.setSaveButtonVisible(false);
        tipusEstatDeFirmaFinalForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusEstatDeFirmaFinalForm, request, mav);
      mav.addObject("tipusEstatDeFirmaFinalForm", tipusEstatDeFirmaFinalForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusEstatDeFirmaFinal existent
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaFinalID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusEstatDeFirmaFinalGet(@PathVariable("tipusEstatDeFirmaFinalID") java.lang.Long tipusEstatDeFirmaFinalID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusEstatDeFirmaFinalGet(tipusEstatDeFirmaFinalID,
        request, response, false);
  }



  /**
   * Editar un TipusEstatDeFirmaFinal existent
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaFinalID}/edit", method = RequestMethod.POST)
  public String editarTipusEstatDeFirmaFinalPost(@ModelAttribute @Valid TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinalForm.getTipusEstatDeFirmaFinal();

    try {
      preValidate(request, tipusEstatDeFirmaFinalForm, result);
      getWebValidator().validate(tipusEstatDeFirmaFinal, result);
      postValidate(request, tipusEstatDeFirmaFinalForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusEstatDeFirmaFinal = update(request, tipusEstatDeFirmaFinal);
        createMessageSuccess(request, "success.modification", tipusEstatDeFirmaFinal.getTipusEstatDeFirmaFinalID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusEstatDeFirmaFinalForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusEstatDeFirmaFinal.getTipusEstatDeFirmaFinalID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusEstatDeFirmaFinalForm, __e);
    }

  }


  /**
   * Eliminar un TipusEstatDeFirmaFinal existent
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaFinalID}/delete")
  public String eliminarTipusEstatDeFirmaFinal(@PathVariable("tipusEstatDeFirmaFinalID") java.lang.Long tipusEstatDeFirmaFinalID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusEstatDeFirmaFinal tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinalEjb.findByPrimaryKey(tipusEstatDeFirmaFinalID);
      if (tipusEstatDeFirmaFinal == null) {
        String __msg =createMessageError(request, "error.notfound", tipusEstatDeFirmaFinalID);
        return getRedirectWhenDelete(request, tipusEstatDeFirmaFinalID, new Exception(__msg));
      } else {
        delete(request, tipusEstatDeFirmaFinal);
        createMessageSuccess(request, "success.deleted", tipusEstatDeFirmaFinalID);
        return getRedirectWhenDelete(request, tipusEstatDeFirmaFinalID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusEstatDeFirmaFinalID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusEstatDeFirmaFinalID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusEstatDeFirmaFinalFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusEstatDeFirmaFinal(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusEstatDeFirmaFinalID, Throwable e) {
    java.lang.Long tipusEstatDeFirmaFinalID = (java.lang.Long)__tipusEstatDeFirmaFinalID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusEstatDeFirmaFinalID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusEstatDeFirmaFinalID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal";
  }

  public String getEntityNameCodePlural() {
    return "tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinalID");
  }

  @InitBinder("tipusEstatDeFirmaFinalFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusEstatDeFirmaFinalForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusEstatDeFirmaFinalWebValidator getWebValidator() {
    return tipusEstatDeFirmaFinalWebValidator;
  }


  public void setWebValidator(TipusEstatDeFirmaFinalWebValidator __val) {
    if (__val != null) {
      this.tipusEstatDeFirmaFinalWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusEstatDeFirmaFinal
   */
  @RequestMapping(value = "/{tipusEstatDeFirmaFinalID}/cancel")
  public String cancelTipusEstatDeFirmaFinal(@PathVariable("tipusEstatDeFirmaFinalID") java.lang.Long tipusEstatDeFirmaFinalID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusEstatDeFirmaFinalID);
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


  public void preValidate(HttpServletRequest request,TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusEstatDeFirmaFinalFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusEstatDeFirmaFinalFilterForm filterForm,  List<TipusEstatDeFirmaFinal> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusEstatDeFirmaFinalForm tipusEstatDeFirmaFinalForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tipusEstatDeFirmaFinalID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tipusEstatDeFirmaFinalID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusEstatDeFirmaFinalFormWebDB";
  }

  public String getTileList() {
    return "tipusEstatDeFirmaFinalListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusEstatDeFirmaFinalWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusEstatDeFirmaFinalJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tipusEstatDeFirmaFinalID) throws I18NException {
    return (TipusEstatDeFirmaFinalJPA) tipusEstatDeFirmaFinalEjb.findByPrimaryKey(tipusEstatDeFirmaFinalID);
  }


  public TipusEstatDeFirmaFinalJPA create(HttpServletRequest request, TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal)
    throws Exception,I18NException, I18NValidationException {
    return (TipusEstatDeFirmaFinalJPA) tipusEstatDeFirmaFinalEjb.create(tipusEstatDeFirmaFinal);
  }


  public TipusEstatDeFirmaFinalJPA update(HttpServletRequest request, TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal)
    throws Exception,I18NException, I18NValidationException {
    return (TipusEstatDeFirmaFinalJPA) tipusEstatDeFirmaFinalEjb.update(tipusEstatDeFirmaFinal);
  }


  public void delete(HttpServletRequest request, TipusEstatDeFirmaFinal tipusEstatDeFirmaFinal) throws Exception,I18NException {
    tipusEstatDeFirmaFinalEjb.delete(tipusEstatDeFirmaFinal);
  }

} // Final de Classe

