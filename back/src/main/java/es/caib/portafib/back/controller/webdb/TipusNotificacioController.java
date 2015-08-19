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
import es.caib.portafib.back.form.webdb.TipusNotificacioForm;

import es.caib.portafib.back.validator.webdb.TipusNotificacioWebValidator;

import es.caib.portafib.jpa.TipusNotificacioJPA;
import es.caib.portafib.model.entity.TipusNotificacio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusNotificacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusNotificacio")
@SessionAttributes(types = { TipusNotificacioForm.class, TipusNotificacioFilterForm.class })
public class TipusNotificacioController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements TipusNotificacioFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusNotificacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusNotificacioLocal tipusNotificacioEjb;

  @Autowired
  private TipusNotificacioWebValidator tipusNotificacioWebValidator;

  @Autowired
  protected TipusNotificacioRefList tipusNotificacioRefList;

  /**
   * Llistat de totes TipusNotificacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusNotificacioFilterForm ff;
    ff = (TipusNotificacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusNotificacio de forma paginada
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
    llistat(mav, request, getTipusNotificacioFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusNotificacioFilterForm getTipusNotificacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusNotificacioFilterForm tipusNotificacioFilterForm;
    tipusNotificacioFilterForm = (TipusNotificacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusNotificacioFilterForm == null) {
      tipusNotificacioFilterForm = new TipusNotificacioFilterForm();
      tipusNotificacioFilterForm.setContexte(getContextWeb());
      tipusNotificacioFilterForm.setEntityNameCode(getEntityNameCode());
      tipusNotificacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusNotificacioFilterForm.setNou(true);
    } else {
      tipusNotificacioFilterForm.setNou(false);
    }
    tipusNotificacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusNotificacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusNotificacio de forma paginada
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
      @ModelAttribute TipusNotificacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusNotificacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusNotificacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusNotificacio> llistat(ModelAndView mav, HttpServletRequest request,
     TipusNotificacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<TipusNotificacio> tipusNotificacio = (List<TipusNotificacio>) processarLlistat(tipusNotificacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusNotificacioItems", tipusNotificacio);

    mav.addObject("tipusNotificacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusNotificacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusNotificacio);

    return tipusNotificacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusNotificacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusNotificacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ESAVIS);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusNotificacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusNotificacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSNOTIFICACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusNotificacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusNotificacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusNotificacioForm tipusNotificacioForm = getTipusNotificacioForm(null, false, request, mav);
    mav.addObject("tipusNotificacioForm" ,tipusNotificacioForm);
    fillReferencesForForm(tipusNotificacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusNotificacioForm getTipusNotificacioForm(TipusNotificacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusNotificacioForm tipusNotificacioForm;
    if(_jpa == null) {
      tipusNotificacioForm = new TipusNotificacioForm(new TipusNotificacioJPA(), true);
    } else {
      tipusNotificacioForm = new TipusNotificacioForm(_jpa, false);
      tipusNotificacioForm.setView(__isView);
    }
    tipusNotificacioForm.setContexte(getContextWeb());
    tipusNotificacioForm.setEntityNameCode(getEntityNameCode());
    tipusNotificacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusNotificacioForm;
  }

  public void fillReferencesForForm(TipusNotificacioForm tipusNotificacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusNotificacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusNotificacioPost(@ModelAttribute TipusNotificacioForm tipusNotificacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusNotificacioJPA tipusNotificacio = tipusNotificacioForm.getTipusNotificacio();

    try {
      preValidate(request, tipusNotificacioForm, result);
      getWebValidator().validate(tipusNotificacioForm, result);
      postValidate(request,tipusNotificacioForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusNotificacio = create(request, tipusNotificacio);
        createMessageSuccess(request, "success.creation", tipusNotificacio.getTipusNotificacioID());
        tipusNotificacioForm.setTipusNotificacio(tipusNotificacio);
        return getRedirectWhenCreated(request, tipusNotificacioForm);
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

  @RequestMapping(value = "/view/{tipusNotificacioID}", method = RequestMethod.GET)
  public ModelAndView veureTipusNotificacioGet(@PathVariable("tipusNotificacioID") java.lang.Long tipusNotificacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusNotificacioGet(tipusNotificacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusNotificacioGet(@PathVariable("tipusNotificacioID") java.lang.Long tipusNotificacioID,
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
    TipusNotificacioJPA tipusNotificacio = findByPrimaryKey(request, tipusNotificacioID);

    if (tipusNotificacio == null) {
      createMessageWarning(request, "error.notfound", tipusNotificacioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusNotificacioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusNotificacioForm tipusNotificacioForm = getTipusNotificacioForm(tipusNotificacio, __isView, request, mav);
      tipusNotificacioForm.setView(__isView);
      if(__isView) {
        tipusNotificacioForm.setAllFieldsReadOnly(ALL_TIPUSNOTIFICACIO_FIELDS);
        tipusNotificacioForm.setSaveButtonVisible(false);
        tipusNotificacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusNotificacioForm, request, mav);
      mav.addObject("tipusNotificacioForm", tipusNotificacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusNotificacio existent
   */
  @RequestMapping(value = "/{tipusNotificacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusNotificacioGet(@PathVariable("tipusNotificacioID") java.lang.Long tipusNotificacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusNotificacioGet(tipusNotificacioID,
        request, response, false);
  }



  /**
   * Editar un TipusNotificacio existent
   */
  @RequestMapping(value = "/{tipusNotificacioID}/edit", method = RequestMethod.POST)
  public String editarTipusNotificacioPost(@ModelAttribute @Valid TipusNotificacioForm tipusNotificacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusNotificacioJPA tipusNotificacio = tipusNotificacioForm.getTipusNotificacio();

    try {
      preValidate(request, tipusNotificacioForm, result);
      getWebValidator().validate(tipusNotificacio, result);
      postValidate(request, tipusNotificacioForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusNotificacio = update(request, tipusNotificacio);
        createMessageSuccess(request, "success.modification", tipusNotificacio.getTipusNotificacioID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusNotificacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusNotificacio.getTipusNotificacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusNotificacioForm, __e);
    }

  }


  /**
   * Eliminar un TipusNotificacio existent
   */
  @RequestMapping(value = "/{tipusNotificacioID}/delete")
  public String eliminarTipusNotificacio(@PathVariable("tipusNotificacioID") java.lang.Long tipusNotificacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusNotificacio tipusNotificacio = tipusNotificacioEjb.findByPrimaryKey(tipusNotificacioID);
      if (tipusNotificacio == null) {
        String __msg =createMessageError(request, "error.notfound", tipusNotificacioID);
        return getRedirectWhenDelete(request, tipusNotificacioID, new Exception(__msg));
      } else {
        delete(request, tipusNotificacio);
        createMessageSuccess(request, "success.deleted", tipusNotificacioID);
        return getRedirectWhenDelete(request, tipusNotificacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusNotificacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusNotificacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusNotificacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusNotificacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusNotificacioID, Throwable e) {
    java.lang.Long tipusNotificacioID = (java.lang.Long)__tipusNotificacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusNotificacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusNotificacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusNotificacio.tipusNotificacio";
  }

  public String getEntityNameCodePlural() {
    return "tipusNotificacio.tipusNotificacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusNotificacio.tipusNotificacioID");
  }

  @InitBinder("tipusNotificacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusNotificacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusNotificacioWebValidator getWebValidator() {
    return tipusNotificacioWebValidator;
  }


  public void setWebValidator(TipusNotificacioWebValidator __val) {
    if (__val != null) {
      this.tipusNotificacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusNotificacio
   */
  @RequestMapping(value = "/{tipusNotificacioID}/cancel")
  public String cancelTipusNotificacio(@PathVariable("tipusNotificacioID") java.lang.Long tipusNotificacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusNotificacioID);
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


  public void preValidate(HttpServletRequest request,TipusNotificacioForm tipusNotificacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusNotificacioForm tipusNotificacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusNotificacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusNotificacioFilterForm filterForm,  List<TipusNotificacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusNotificacioForm tipusNotificacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusNotificacioForm tipusNotificacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tipusNotificacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tipusNotificacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusNotificacioFormWebDB";
  }

  public String getTileList() {
    return "tipusNotificacioListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusNotificacioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusNotificacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tipusNotificacioID) throws I18NException {
    return (TipusNotificacioJPA) tipusNotificacioEjb.findByPrimaryKey(tipusNotificacioID);
  }


  public TipusNotificacioJPA create(HttpServletRequest request, TipusNotificacioJPA tipusNotificacio)
    throws Exception,I18NException, I18NValidationException {
    return (TipusNotificacioJPA) tipusNotificacioEjb.create(tipusNotificacio);
  }


  public TipusNotificacioJPA update(HttpServletRequest request, TipusNotificacioJPA tipusNotificacio)
    throws Exception,I18NException, I18NValidationException {
    return (TipusNotificacioJPA) tipusNotificacioEjb.update(tipusNotificacio);
  }


  public void delete(HttpServletRequest request, TipusNotificacio tipusNotificacio) throws Exception,I18NException {
    tipusNotificacioEjb.delete(tipusNotificacio);
  }

} // Final de Classe

