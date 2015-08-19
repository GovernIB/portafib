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
import es.caib.portafib.back.form.webdb.TipusFirmaForm;

import es.caib.portafib.back.validator.webdb.TipusFirmaWebValidator;

import es.caib.portafib.jpa.TipusFirmaJPA;
import es.caib.portafib.model.entity.TipusFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusFirma")
@SessionAttributes(types = { TipusFirmaForm.class, TipusFirmaFilterForm.class })
public class TipusFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements TipusFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusFirmaLocal tipusFirmaEjb;

  @Autowired
  private TipusFirmaWebValidator tipusFirmaWebValidator;

  @Autowired
  protected TipusFirmaRefList tipusFirmaRefList;

  /**
   * Llistat de totes TipusFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusFirmaFilterForm ff;
    ff = (TipusFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusFirma de forma paginada
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
    llistat(mav, request, getTipusFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusFirmaFilterForm getTipusFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusFirmaFilterForm tipusFirmaFilterForm;
    tipusFirmaFilterForm = (TipusFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusFirmaFilterForm == null) {
      tipusFirmaFilterForm = new TipusFirmaFilterForm();
      tipusFirmaFilterForm.setContexte(getContextWeb());
      tipusFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      tipusFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusFirmaFilterForm.setNou(true);
    } else {
      tipusFirmaFilterForm.setNou(false);
    }
    tipusFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusFirma de forma paginada
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
      @ModelAttribute TipusFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusFirma> llistat(ModelAndView mav, HttpServletRequest request,
     TipusFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<TipusFirma> tipusFirma = (List<TipusFirma>) processarLlistat(tipusFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusFirmaItems", tipusFirma);

    mav.addObject("tipusFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusFirma);

    return tipusFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusFirma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, SUPORTADA);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusFirmaForm tipusFirmaForm = getTipusFirmaForm(null, false, request, mav);
    mav.addObject("tipusFirmaForm" ,tipusFirmaForm);
    fillReferencesForForm(tipusFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusFirmaForm getTipusFirmaForm(TipusFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusFirmaForm tipusFirmaForm;
    if(_jpa == null) {
      tipusFirmaForm = new TipusFirmaForm(new TipusFirmaJPA(), true);
    } else {
      tipusFirmaForm = new TipusFirmaForm(_jpa, false);
      tipusFirmaForm.setView(__isView);
    }
    tipusFirmaForm.setContexte(getContextWeb());
    tipusFirmaForm.setEntityNameCode(getEntityNameCode());
    tipusFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusFirmaForm;
  }

  public void fillReferencesForForm(TipusFirmaForm tipusFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusFirmaPost(@ModelAttribute TipusFirmaForm tipusFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusFirmaJPA tipusFirma = tipusFirmaForm.getTipusFirma();

    try {
      preValidate(request, tipusFirmaForm, result);
      getWebValidator().validate(tipusFirmaForm, result);
      postValidate(request,tipusFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusFirma = create(request, tipusFirma);
        createMessageSuccess(request, "success.creation", tipusFirma.getTipusFirmaID());
        tipusFirmaForm.setTipusFirma(tipusFirma);
        return getRedirectWhenCreated(request, tipusFirmaForm);
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

  @RequestMapping(value = "/view/{tipusFirmaID}", method = RequestMethod.GET)
  public ModelAndView veureTipusFirmaGet(@PathVariable("tipusFirmaID") java.lang.Integer tipusFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusFirmaGet(tipusFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusFirmaGet(@PathVariable("tipusFirmaID") java.lang.Integer tipusFirmaID,
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
    TipusFirmaJPA tipusFirma = findByPrimaryKey(request, tipusFirmaID);

    if (tipusFirma == null) {
      createMessageWarning(request, "error.notfound", tipusFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusFirmaForm tipusFirmaForm = getTipusFirmaForm(tipusFirma, __isView, request, mav);
      tipusFirmaForm.setView(__isView);
      if(__isView) {
        tipusFirmaForm.setAllFieldsReadOnly(ALL_TIPUSFIRMA_FIELDS);
        tipusFirmaForm.setSaveButtonVisible(false);
        tipusFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusFirmaForm, request, mav);
      mav.addObject("tipusFirmaForm", tipusFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusFirma existent
   */
  @RequestMapping(value = "/{tipusFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusFirmaGet(@PathVariable("tipusFirmaID") java.lang.Integer tipusFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusFirmaGet(tipusFirmaID,
        request, response, false);
  }



  /**
   * Editar un TipusFirma existent
   */
  @RequestMapping(value = "/{tipusFirmaID}/edit", method = RequestMethod.POST)
  public String editarTipusFirmaPost(@ModelAttribute @Valid TipusFirmaForm tipusFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusFirmaJPA tipusFirma = tipusFirmaForm.getTipusFirma();

    try {
      preValidate(request, tipusFirmaForm, result);
      getWebValidator().validate(tipusFirma, result);
      postValidate(request, tipusFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusFirma = update(request, tipusFirma);
        createMessageSuccess(request, "success.modification", tipusFirma.getTipusFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusFirma.getTipusFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusFirmaForm, __e);
    }

  }


  /**
   * Eliminar un TipusFirma existent
   */
  @RequestMapping(value = "/{tipusFirmaID}/delete")
  public String eliminarTipusFirma(@PathVariable("tipusFirmaID") java.lang.Integer tipusFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusFirma tipusFirma = tipusFirmaEjb.findByPrimaryKey(tipusFirmaID);
      if (tipusFirma == null) {
        String __msg =createMessageError(request, "error.notfound", tipusFirmaID);
        return getRedirectWhenDelete(request, tipusFirmaID, new Exception(__msg));
      } else {
        delete(request, tipusFirma);
        createMessageSuccess(request, "success.deleted", tipusFirmaID);
        return getRedirectWhenDelete(request, tipusFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusFirmaID, Throwable e) {
    java.lang.Integer tipusFirmaID = (java.lang.Integer)__tipusFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusFirma.tipusFirma";
  }

  public String getEntityNameCodePlural() {
    return "tipusFirma.tipusFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusFirma.tipusFirmaID");
  }

  @InitBinder("tipusFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusFirmaWebValidator getWebValidator() {
    return tipusFirmaWebValidator;
  }


  public void setWebValidator(TipusFirmaWebValidator __val) {
    if (__val != null) {
      this.tipusFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusFirma
   */
  @RequestMapping(value = "/{tipusFirmaID}/cancel")
  public String cancelTipusFirma(@PathVariable("tipusFirmaID") java.lang.Integer tipusFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusFirmaID);
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


  public void preValidate(HttpServletRequest request,TipusFirmaForm tipusFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusFirmaForm tipusFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusFirmaFilterForm filterForm,  List<TipusFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusFirmaForm tipusFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusFirmaForm tipusFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Integer tipusFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Integer tipusFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusFirmaFormWebDB";
  }

  public String getTileList() {
    return "tipusFirmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Integer tipusFirmaID) throws I18NException {
    return (TipusFirmaJPA) tipusFirmaEjb.findByPrimaryKey(tipusFirmaID);
  }


  public TipusFirmaJPA create(HttpServletRequest request, TipusFirmaJPA tipusFirma)
    throws Exception,I18NException, I18NValidationException {
    return (TipusFirmaJPA) tipusFirmaEjb.create(tipusFirma);
  }


  public TipusFirmaJPA update(HttpServletRequest request, TipusFirmaJPA tipusFirma)
    throws Exception,I18NException, I18NValidationException {
    return (TipusFirmaJPA) tipusFirmaEjb.update(tipusFirma);
  }


  public void delete(HttpServletRequest request, TipusFirma tipusFirma) throws Exception,I18NException {
    tipusFirmaEjb.delete(tipusFirma);
  }

} // Final de Classe

