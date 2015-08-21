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
import es.caib.portafib.back.form.webdb.TipusMetadadaForm;

import es.caib.portafib.back.validator.webdb.TipusMetadadaWebValidator;

import es.caib.portafib.jpa.TipusMetadadaJPA;
import es.caib.portafib.model.entity.TipusMetadada;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusMetadada
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusMetadada")
@SessionAttributes(types = { TipusMetadadaForm.class, TipusMetadadaFilterForm.class })
public class TipusMetadadaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<TipusMetadada, java.lang.Integer> implements TipusMetadadaFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusMetadadaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusMetadadaLocal tipusMetadadaEjb;

  @Autowired
  private TipusMetadadaWebValidator tipusMetadadaWebValidator;

  @Autowired
  protected TipusMetadadaRefList tipusMetadadaRefList;

  /**
   * Llistat de totes TipusMetadada
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusMetadadaFilterForm ff;
    ff = (TipusMetadadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusMetadada de forma paginada
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
    llistat(mav, request, getTipusMetadadaFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusMetadadaFilterForm getTipusMetadadaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusMetadadaFilterForm tipusMetadadaFilterForm;
    tipusMetadadaFilterForm = (TipusMetadadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusMetadadaFilterForm == null) {
      tipusMetadadaFilterForm = new TipusMetadadaFilterForm();
      tipusMetadadaFilterForm.setContexte(getContextWeb());
      tipusMetadadaFilterForm.setEntityNameCode(getEntityNameCode());
      tipusMetadadaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusMetadadaFilterForm.setNou(true);
    } else {
      tipusMetadadaFilterForm.setNou(false);
    }
    tipusMetadadaFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusMetadadaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusMetadada de forma paginada
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
      @ModelAttribute TipusMetadadaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusMetadadaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusMetadada de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusMetadada> llistat(ModelAndView mav, HttpServletRequest request,
     TipusMetadadaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TipusMetadada> tipusMetadada = processarLlistat(tipusMetadadaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusMetadadaItems", tipusMetadada);

    mav.addObject("tipusMetadadaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusMetadada, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusMetadada);

    return tipusMetadada;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusMetadadaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusMetadada> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusMetadadaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusMetadada> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSMETADADA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusMetadada
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusMetadadaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusMetadadaForm tipusMetadadaForm = getTipusMetadadaForm(null, false, request, mav);
    mav.addObject("tipusMetadadaForm" ,tipusMetadadaForm);
    fillReferencesForForm(tipusMetadadaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusMetadadaForm getTipusMetadadaForm(TipusMetadadaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusMetadadaForm tipusMetadadaForm;
    if(_jpa == null) {
      tipusMetadadaForm = new TipusMetadadaForm(new TipusMetadadaJPA(), true);
    } else {
      tipusMetadadaForm = new TipusMetadadaForm(_jpa, false);
      tipusMetadadaForm.setView(__isView);
    }
    tipusMetadadaForm.setContexte(getContextWeb());
    tipusMetadadaForm.setEntityNameCode(getEntityNameCode());
    tipusMetadadaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusMetadadaForm;
  }

  public void fillReferencesForForm(TipusMetadadaForm tipusMetadadaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusMetadada
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusMetadadaPost(@ModelAttribute TipusMetadadaForm tipusMetadadaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusMetadadaJPA tipusMetadada = tipusMetadadaForm.getTipusMetadada();

    try {
      preValidate(request, tipusMetadadaForm, result);
      getWebValidator().validate(tipusMetadadaForm, result);
      postValidate(request,tipusMetadadaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusMetadada = create(request, tipusMetadada);
        createMessageSuccess(request, "success.creation", tipusMetadada.getTipusMetadadaID());
        tipusMetadadaForm.setTipusMetadada(tipusMetadada);
        return getRedirectWhenCreated(request, tipusMetadadaForm);
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

  @RequestMapping(value = "/view/{tipusMetadadaID}", method = RequestMethod.GET)
  public ModelAndView veureTipusMetadadaGet(@PathVariable("tipusMetadadaID") java.lang.Integer tipusMetadadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusMetadadaGet(tipusMetadadaID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusMetadadaGet(@PathVariable("tipusMetadadaID") java.lang.Integer tipusMetadadaID,
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
    TipusMetadadaJPA tipusMetadada = findByPrimaryKey(request, tipusMetadadaID);

    if (tipusMetadada == null) {
      createMessageWarning(request, "error.notfound", tipusMetadadaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusMetadadaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusMetadadaForm tipusMetadadaForm = getTipusMetadadaForm(tipusMetadada, __isView, request, mav);
      tipusMetadadaForm.setView(__isView);
      if(__isView) {
        tipusMetadadaForm.setAllFieldsReadOnly(ALL_TIPUSMETADADA_FIELDS);
        tipusMetadadaForm.setSaveButtonVisible(false);
        tipusMetadadaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusMetadadaForm, request, mav);
      mav.addObject("tipusMetadadaForm", tipusMetadadaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusMetadada existent
   */
  @RequestMapping(value = "/{tipusMetadadaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusMetadadaGet(@PathVariable("tipusMetadadaID") java.lang.Integer tipusMetadadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusMetadadaGet(tipusMetadadaID,
        request, response, false);
  }



  /**
   * Editar un TipusMetadada existent
   */
  @RequestMapping(value = "/{tipusMetadadaID}/edit", method = RequestMethod.POST)
  public String editarTipusMetadadaPost(@ModelAttribute @Valid TipusMetadadaForm tipusMetadadaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusMetadadaJPA tipusMetadada = tipusMetadadaForm.getTipusMetadada();

    try {
      preValidate(request, tipusMetadadaForm, result);
      getWebValidator().validate(tipusMetadada, result);
      postValidate(request, tipusMetadadaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusMetadada = update(request, tipusMetadada);
        createMessageSuccess(request, "success.modification", tipusMetadada.getTipusMetadadaID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusMetadadaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusMetadada.getTipusMetadadaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusMetadadaForm, __e);
    }

  }


  /**
   * Eliminar un TipusMetadada existent
   */
  @RequestMapping(value = "/{tipusMetadadaID}/delete")
  public String eliminarTipusMetadada(@PathVariable("tipusMetadadaID") java.lang.Integer tipusMetadadaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusMetadada tipusMetadada = tipusMetadadaEjb.findByPrimaryKey(tipusMetadadaID);
      if (tipusMetadada == null) {
        String __msg =createMessageError(request, "error.notfound", tipusMetadadaID);
        return getRedirectWhenDelete(request, tipusMetadadaID, new Exception(__msg));
      } else {
        delete(request, tipusMetadada);
        createMessageSuccess(request, "success.deleted", tipusMetadadaID);
        return getRedirectWhenDelete(request, tipusMetadadaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusMetadadaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusMetadadaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusMetadadaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusMetadada(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Integer stringToPK(String value) {
  return new java.lang.Integer(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __tipusMetadadaID, Throwable e) {
    java.lang.Integer tipusMetadadaID = (java.lang.Integer)__tipusMetadadaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusMetadadaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusMetadadaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusMetadada.tipusMetadada";
  }

  public String getEntityNameCodePlural() {
    return "tipusMetadada.tipusMetadada.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusMetadada.tipusMetadadaID");
  }

  @InitBinder("tipusMetadadaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusMetadadaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusMetadadaWebValidator getWebValidator() {
    return tipusMetadadaWebValidator;
  }


  public void setWebValidator(TipusMetadadaWebValidator __val) {
    if (__val != null) {
      this.tipusMetadadaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusMetadada
   */
  @RequestMapping(value = "/{tipusMetadadaID}/cancel")
  public String cancelTipusMetadada(@PathVariable("tipusMetadadaID") java.lang.Integer tipusMetadadaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusMetadadaID);
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


  public void preValidate(HttpServletRequest request,TipusMetadadaForm tipusMetadadaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusMetadadaForm tipusMetadadaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusMetadadaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusMetadadaFilterForm filterForm,  List<TipusMetadada> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusMetadadaForm tipusMetadadaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusMetadadaForm tipusMetadadaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Integer tipusMetadadaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Integer tipusMetadadaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusMetadadaFormWebDB";
  }

  public String getTileList() {
    return "tipusMetadadaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusMetadadaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusMetadadaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Integer tipusMetadadaID) throws I18NException {
    return (TipusMetadadaJPA) tipusMetadadaEjb.findByPrimaryKey(tipusMetadadaID);
  }


  public TipusMetadadaJPA create(HttpServletRequest request, TipusMetadadaJPA tipusMetadada)
    throws Exception,I18NException, I18NValidationException {
    return (TipusMetadadaJPA) tipusMetadadaEjb.create(tipusMetadada);
  }


  public TipusMetadadaJPA update(HttpServletRequest request, TipusMetadadaJPA tipusMetadada)
    throws Exception,I18NException, I18NValidationException {
    return (TipusMetadadaJPA) tipusMetadadaEjb.update(tipusMetadada);
  }


  public void delete(HttpServletRequest request, TipusMetadada tipusMetadada) throws Exception,I18NException {
    tipusMetadadaEjb.delete(tipusMetadada);
  }

} // Final de Classe

