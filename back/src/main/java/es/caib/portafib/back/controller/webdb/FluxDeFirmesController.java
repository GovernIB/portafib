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
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;

import es.caib.portafib.back.validator.webdb.FluxDeFirmesWebValidator;

import es.caib.portafib.jpa.FluxDeFirmesJPA;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un FluxDeFirmes
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/fluxDeFirmes")
@SessionAttributes(types = { FluxDeFirmesForm.class, FluxDeFirmesFilterForm.class })
public class FluxDeFirmesController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<FluxDeFirmes, java.lang.Long> implements FluxDeFirmesFields {

  @EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesLocal fluxDeFirmesEjb;

  @Autowired
  private FluxDeFirmesWebValidator fluxDeFirmesWebValidator;

  @Autowired
  protected FluxDeFirmesRefList fluxDeFirmesRefList;

  /**
   * Llistat de totes FluxDeFirmes
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FluxDeFirmesFilterForm ff;
    ff = (FluxDeFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar FluxDeFirmes de forma paginada
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
    llistat(mav, request, getFluxDeFirmesFilterForm(pagina, mav, request));
    return mav;
  }

  public FluxDeFirmesFilterForm getFluxDeFirmesFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FluxDeFirmesFilterForm fluxDeFirmesFilterForm;
    fluxDeFirmesFilterForm = (FluxDeFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(fluxDeFirmesFilterForm == null) {
      fluxDeFirmesFilterForm = new FluxDeFirmesFilterForm();
      fluxDeFirmesFilterForm.setContexte(getContextWeb());
      fluxDeFirmesFilterForm.setEntityNameCode(getEntityNameCode());
      fluxDeFirmesFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      fluxDeFirmesFilterForm.setNou(true);
    } else {
      fluxDeFirmesFilterForm.setNou(false);
    }
    fluxDeFirmesFilterForm.setPage(pagina == null ? 1 : pagina);
    return fluxDeFirmesFilterForm;
  }

  /**
   * Segona i següent peticions per llistar FluxDeFirmes de forma paginada
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
      @ModelAttribute FluxDeFirmesFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFluxDeFirmesFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de FluxDeFirmes de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<FluxDeFirmes> llistat(ModelAndView mav, HttpServletRequest request,
     FluxDeFirmesFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<FluxDeFirmes> fluxDeFirmes = processarLlistat(fluxDeFirmesEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("fluxDeFirmesItems", fluxDeFirmes);

    mav.addObject("fluxDeFirmesFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, fluxDeFirmes, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, fluxDeFirmes);

    return fluxDeFirmes;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FluxDeFirmesFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<FluxDeFirmes> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    FluxDeFirmesFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<FluxDeFirmes> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FLUXDEFIRMES_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou FluxDeFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFluxDeFirmesGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FluxDeFirmesForm fluxDeFirmesForm = getFluxDeFirmesForm(null, false, request, mav);
    mav.addObject("fluxDeFirmesForm" ,fluxDeFirmesForm);
    fillReferencesForForm(fluxDeFirmesForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FluxDeFirmesForm getFluxDeFirmesForm(FluxDeFirmesJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FluxDeFirmesForm fluxDeFirmesForm;
    if(_jpa == null) {
      fluxDeFirmesForm = new FluxDeFirmesForm(new FluxDeFirmesJPA(), true);
    } else {
      fluxDeFirmesForm = new FluxDeFirmesForm(_jpa, false);
      fluxDeFirmesForm.setView(__isView);
    }
    fluxDeFirmesForm.setContexte(getContextWeb());
    fluxDeFirmesForm.setEntityNameCode(getEntityNameCode());
    fluxDeFirmesForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return fluxDeFirmesForm;
  }

  public void fillReferencesForForm(FluxDeFirmesForm fluxDeFirmesForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou FluxDeFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFluxDeFirmesPost(@ModelAttribute FluxDeFirmesForm fluxDeFirmesForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FluxDeFirmesJPA fluxDeFirmes = fluxDeFirmesForm.getFluxDeFirmes();

    try {
      preValidate(request, fluxDeFirmesForm, result);
      getWebValidator().validate(fluxDeFirmesForm, result);
      postValidate(request,fluxDeFirmesForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        fluxDeFirmes = create(request, fluxDeFirmes);
        createMessageSuccess(request, "success.creation", fluxDeFirmes.getFluxDeFirmesID());
        fluxDeFirmesForm.setFluxDeFirmes(fluxDeFirmes);
        return getRedirectWhenCreated(request, fluxDeFirmesForm);
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

  @RequestMapping(value = "/view/{fluxDeFirmesID}", method = RequestMethod.GET)
  public ModelAndView veureFluxDeFirmesGet(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFluxDeFirmesGet(fluxDeFirmesID,
        request, response, true);
  }


  protected ModelAndView editAndViewFluxDeFirmesGet(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
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
    FluxDeFirmesJPA fluxDeFirmes = findByPrimaryKey(request, fluxDeFirmesID);

    if (fluxDeFirmes == null) {
      createMessageWarning(request, "error.notfound", fluxDeFirmesID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, fluxDeFirmesID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FluxDeFirmesForm fluxDeFirmesForm = getFluxDeFirmesForm(fluxDeFirmes, __isView, request, mav);
      fluxDeFirmesForm.setView(__isView);
      if(__isView) {
        fluxDeFirmesForm.setAllFieldsReadOnly(ALL_FLUXDEFIRMES_FIELDS);
        fluxDeFirmesForm.setSaveButtonVisible(false);
        fluxDeFirmesForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(fluxDeFirmesForm, request, mav);
      mav.addObject("fluxDeFirmesForm", fluxDeFirmesForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un FluxDeFirmes existent
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFluxDeFirmesGet(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFluxDeFirmesGet(fluxDeFirmesID,
        request, response, false);
  }



  /**
   * Editar un FluxDeFirmes existent
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/edit", method = RequestMethod.POST)
  public String editarFluxDeFirmesPost(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FluxDeFirmesJPA fluxDeFirmes = fluxDeFirmesForm.getFluxDeFirmes();

    try {
      preValidate(request, fluxDeFirmesForm, result);
      getWebValidator().validate(fluxDeFirmes, result);
      postValidate(request, fluxDeFirmesForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        fluxDeFirmes = update(request, fluxDeFirmes);
        createMessageSuccess(request, "success.modification", fluxDeFirmes.getFluxDeFirmesID());
        status.setComplete();
        return getRedirectWhenModified(request, fluxDeFirmesForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          fluxDeFirmes.getFluxDeFirmesID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, fluxDeFirmesForm, __e);
    }

  }


  /**
   * Eliminar un FluxDeFirmes existent
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/delete")
  public String eliminarFluxDeFirmes(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      FluxDeFirmes fluxDeFirmes = fluxDeFirmesEjb.findByPrimaryKey(fluxDeFirmesID);
      if (fluxDeFirmes == null) {
        String __msg =createMessageError(request, "error.notfound", fluxDeFirmesID);
        return getRedirectWhenDelete(request, fluxDeFirmesID, new Exception(__msg));
      } else {
        delete(request, fluxDeFirmes);
        createMessageSuccess(request, "success.deleted", fluxDeFirmesID);
        return getRedirectWhenDelete(request, fluxDeFirmesID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", fluxDeFirmesID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, fluxDeFirmesID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FluxDeFirmesFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFluxDeFirmes(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __fluxDeFirmesID, Throwable e) {
    java.lang.Long fluxDeFirmesID = (java.lang.Long)__fluxDeFirmesID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (fluxDeFirmesID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(fluxDeFirmesID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "fluxDeFirmes.fluxDeFirmes";
  }

  public String getEntityNameCodePlural() {
    return "fluxDeFirmes.fluxDeFirmes.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmesID");
  }

  @InitBinder("fluxDeFirmesFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("fluxDeFirmesForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("fluxDeFirmesID");

  }

  public FluxDeFirmesWebValidator getWebValidator() {
    return fluxDeFirmesWebValidator;
  }


  public void setWebValidator(FluxDeFirmesWebValidator __val) {
    if (__val != null) {
      this.fluxDeFirmesWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de FluxDeFirmes
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/cancel")
  public String cancelFluxDeFirmes(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, fluxDeFirmesID);
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


  public void preValidate(HttpServletRequest request,FluxDeFirmesForm fluxDeFirmesForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FluxDeFirmesForm fluxDeFirmesForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FluxDeFirmesFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FluxDeFirmesFilterForm filterForm,  List<FluxDeFirmes> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FluxDeFirmesForm fluxDeFirmesForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FluxDeFirmesForm fluxDeFirmesForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long fluxDeFirmesID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long fluxDeFirmesID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "fluxDeFirmesFormWebDB";
  }

  public String getTileList() {
    return "fluxDeFirmesListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "FluxDeFirmesWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FluxDeFirmesJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long fluxDeFirmesID) throws I18NException {
    return (FluxDeFirmesJPA) fluxDeFirmesEjb.findByPrimaryKey(fluxDeFirmesID);
  }


  public FluxDeFirmesJPA create(HttpServletRequest request, FluxDeFirmesJPA fluxDeFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (FluxDeFirmesJPA) fluxDeFirmesEjb.create(fluxDeFirmes);
  }


  public FluxDeFirmesJPA update(HttpServletRequest request, FluxDeFirmesJPA fluxDeFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (FluxDeFirmesJPA) fluxDeFirmesEjb.update(fluxDeFirmes);
  }


  public void delete(HttpServletRequest request, FluxDeFirmes fluxDeFirmes) throws Exception,I18NException {
    fluxDeFirmesEjb.delete(fluxDeFirmes);
  }

} // Final de Classe

