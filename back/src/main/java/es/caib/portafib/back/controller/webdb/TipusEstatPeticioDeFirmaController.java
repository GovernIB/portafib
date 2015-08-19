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
import es.caib.portafib.back.form.webdb.TipusEstatPeticioDeFirmaForm;

import es.caib.portafib.back.validator.webdb.TipusEstatPeticioDeFirmaWebValidator;

import es.caib.portafib.jpa.TipusEstatPeticioDeFirmaJPA;
import es.caib.portafib.model.entity.TipusEstatPeticioDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusEstatPeticioDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusEstatPeticioDeFirma")
@SessionAttributes(types = { TipusEstatPeticioDeFirmaForm.class, TipusEstatPeticioDeFirmaFilterForm.class })
public class TipusEstatPeticioDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements TipusEstatPeticioDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.TipusEstatPeticioDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusEstatPeticioDeFirmaLocal tipusEstatPeticioDeFirmaEjb;

  @Autowired
  private TipusEstatPeticioDeFirmaWebValidator tipusEstatPeticioDeFirmaWebValidator;

  @Autowired
  protected TipusEstatPeticioDeFirmaRefList tipusEstatPeticioDeFirmaRefList;

  /**
   * Llistat de totes TipusEstatPeticioDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusEstatPeticioDeFirmaFilterForm ff;
    ff = (TipusEstatPeticioDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusEstatPeticioDeFirma de forma paginada
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
    llistat(mav, request, getTipusEstatPeticioDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusEstatPeticioDeFirmaFilterForm getTipusEstatPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusEstatPeticioDeFirmaFilterForm tipusEstatPeticioDeFirmaFilterForm;
    tipusEstatPeticioDeFirmaFilterForm = (TipusEstatPeticioDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusEstatPeticioDeFirmaFilterForm == null) {
      tipusEstatPeticioDeFirmaFilterForm = new TipusEstatPeticioDeFirmaFilterForm();
      tipusEstatPeticioDeFirmaFilterForm.setContexte(getContextWeb());
      tipusEstatPeticioDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      tipusEstatPeticioDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusEstatPeticioDeFirmaFilterForm.setNou(true);
    } else {
      tipusEstatPeticioDeFirmaFilterForm.setNou(false);
    }
    tipusEstatPeticioDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusEstatPeticioDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusEstatPeticioDeFirma de forma paginada
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
      @ModelAttribute TipusEstatPeticioDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusEstatPeticioDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusEstatPeticioDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusEstatPeticioDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     TipusEstatPeticioDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<TipusEstatPeticioDeFirma> tipusEstatPeticioDeFirma = (List<TipusEstatPeticioDeFirma>) processarLlistat(tipusEstatPeticioDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusEstatPeticioDeFirmaItems", tipusEstatPeticioDeFirma);

    mav.addObject("tipusEstatPeticioDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusEstatPeticioDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusEstatPeticioDeFirma);

    return tipusEstatPeticioDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusEstatPeticioDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusEstatPeticioDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusEstatPeticioDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusEstatPeticioDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSESTATPETICIODEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusEstatPeticioDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusEstatPeticioDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm = getTipusEstatPeticioDeFirmaForm(null, false, request, mav);
    mav.addObject("tipusEstatPeticioDeFirmaForm" ,tipusEstatPeticioDeFirmaForm);
    fillReferencesForForm(tipusEstatPeticioDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusEstatPeticioDeFirmaForm getTipusEstatPeticioDeFirmaForm(TipusEstatPeticioDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm;
    if(_jpa == null) {
      tipusEstatPeticioDeFirmaForm = new TipusEstatPeticioDeFirmaForm(new TipusEstatPeticioDeFirmaJPA(), true);
    } else {
      tipusEstatPeticioDeFirmaForm = new TipusEstatPeticioDeFirmaForm(_jpa, false);
      tipusEstatPeticioDeFirmaForm.setView(__isView);
    }
    tipusEstatPeticioDeFirmaForm.setContexte(getContextWeb());
    tipusEstatPeticioDeFirmaForm.setEntityNameCode(getEntityNameCode());
    tipusEstatPeticioDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return tipusEstatPeticioDeFirmaForm;
  }

  public void fillReferencesForForm(TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou TipusEstatPeticioDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusEstatPeticioDeFirmaPost(@ModelAttribute TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma = tipusEstatPeticioDeFirmaForm.getTipusEstatPeticioDeFirma();

    try {
      preValidate(request, tipusEstatPeticioDeFirmaForm, result);
      getWebValidator().validate(tipusEstatPeticioDeFirmaForm, result);
      postValidate(request,tipusEstatPeticioDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusEstatPeticioDeFirma = create(request, tipusEstatPeticioDeFirma);
        createMessageSuccess(request, "success.creation", tipusEstatPeticioDeFirma.getTipusEstatPeticioDeFirmaID());
        tipusEstatPeticioDeFirmaForm.setTipusEstatPeticioDeFirma(tipusEstatPeticioDeFirma);
        return getRedirectWhenCreated(request, tipusEstatPeticioDeFirmaForm);
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

  @RequestMapping(value = "/view/{tipusEstatPeticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView veureTipusEstatPeticioDeFirmaGet(@PathVariable("tipusEstatPeticioDeFirmaID") java.lang.Integer tipusEstatPeticioDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusEstatPeticioDeFirmaGet(tipusEstatPeticioDeFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusEstatPeticioDeFirmaGet(@PathVariable("tipusEstatPeticioDeFirmaID") java.lang.Integer tipusEstatPeticioDeFirmaID,
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
    TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma = findByPrimaryKey(request, tipusEstatPeticioDeFirmaID);

    if (tipusEstatPeticioDeFirma == null) {
      createMessageWarning(request, "error.notfound", tipusEstatPeticioDeFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusEstatPeticioDeFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm = getTipusEstatPeticioDeFirmaForm(tipusEstatPeticioDeFirma, __isView, request, mav);
      tipusEstatPeticioDeFirmaForm.setView(__isView);
      if(__isView) {
        tipusEstatPeticioDeFirmaForm.setAllFieldsReadOnly(ALL_TIPUSESTATPETICIODEFIRMA_FIELDS);
        tipusEstatPeticioDeFirmaForm.setSaveButtonVisible(false);
        tipusEstatPeticioDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusEstatPeticioDeFirmaForm, request, mav);
      mav.addObject("tipusEstatPeticioDeFirmaForm", tipusEstatPeticioDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusEstatPeticioDeFirma existent
   */
  @RequestMapping(value = "/{tipusEstatPeticioDeFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusEstatPeticioDeFirmaGet(@PathVariable("tipusEstatPeticioDeFirmaID") java.lang.Integer tipusEstatPeticioDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusEstatPeticioDeFirmaGet(tipusEstatPeticioDeFirmaID,
        request, response, false);
  }



  /**
   * Editar un TipusEstatPeticioDeFirma existent
   */
  @RequestMapping(value = "/{tipusEstatPeticioDeFirmaID}/edit", method = RequestMethod.POST)
  public String editarTipusEstatPeticioDeFirmaPost(@ModelAttribute @Valid TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma = tipusEstatPeticioDeFirmaForm.getTipusEstatPeticioDeFirma();

    try {
      preValidate(request, tipusEstatPeticioDeFirmaForm, result);
      getWebValidator().validate(tipusEstatPeticioDeFirma, result);
      postValidate(request, tipusEstatPeticioDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        tipusEstatPeticioDeFirma = update(request, tipusEstatPeticioDeFirma);
        createMessageSuccess(request, "success.modification", tipusEstatPeticioDeFirma.getTipusEstatPeticioDeFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusEstatPeticioDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusEstatPeticioDeFirma.getTipusEstatPeticioDeFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusEstatPeticioDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un TipusEstatPeticioDeFirma existent
   */
  @RequestMapping(value = "/{tipusEstatPeticioDeFirmaID}/delete")
  public String eliminarTipusEstatPeticioDeFirma(@PathVariable("tipusEstatPeticioDeFirmaID") java.lang.Integer tipusEstatPeticioDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusEstatPeticioDeFirma tipusEstatPeticioDeFirma = tipusEstatPeticioDeFirmaEjb.findByPrimaryKey(tipusEstatPeticioDeFirmaID);
      if (tipusEstatPeticioDeFirma == null) {
        String __msg =createMessageError(request, "error.notfound", tipusEstatPeticioDeFirmaID);
        return getRedirectWhenDelete(request, tipusEstatPeticioDeFirmaID, new Exception(__msg));
      } else {
        delete(request, tipusEstatPeticioDeFirma);
        createMessageSuccess(request, "success.deleted", tipusEstatPeticioDeFirmaID);
        return getRedirectWhenDelete(request, tipusEstatPeticioDeFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusEstatPeticioDeFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusEstatPeticioDeFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusEstatPeticioDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusEstatPeticioDeFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusEstatPeticioDeFirmaID, Throwable e) {
    java.lang.Integer tipusEstatPeticioDeFirmaID = (java.lang.Integer)__tipusEstatPeticioDeFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusEstatPeticioDeFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusEstatPeticioDeFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirmaID");
  }

  @InitBinder("tipusEstatPeticioDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusEstatPeticioDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusEstatPeticioDeFirmaWebValidator getWebValidator() {
    return tipusEstatPeticioDeFirmaWebValidator;
  }


  public void setWebValidator(TipusEstatPeticioDeFirmaWebValidator __val) {
    if (__val != null) {
      this.tipusEstatPeticioDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusEstatPeticioDeFirma
   */
  @RequestMapping(value = "/{tipusEstatPeticioDeFirmaID}/cancel")
  public String cancelTipusEstatPeticioDeFirma(@PathVariable("tipusEstatPeticioDeFirmaID") java.lang.Integer tipusEstatPeticioDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusEstatPeticioDeFirmaID);
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


  public void preValidate(HttpServletRequest request,TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusEstatPeticioDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusEstatPeticioDeFirmaFilterForm filterForm,  List<TipusEstatPeticioDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusEstatPeticioDeFirmaForm tipusEstatPeticioDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Integer tipusEstatPeticioDeFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Integer tipusEstatPeticioDeFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusEstatPeticioDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "tipusEstatPeticioDeFirmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusEstatPeticioDeFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusEstatPeticioDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Integer tipusEstatPeticioDeFirmaID) throws I18NException {
    return (TipusEstatPeticioDeFirmaJPA) tipusEstatPeticioDeFirmaEjb.findByPrimaryKey(tipusEstatPeticioDeFirmaID);
  }


  public TipusEstatPeticioDeFirmaJPA create(HttpServletRequest request, TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (TipusEstatPeticioDeFirmaJPA) tipusEstatPeticioDeFirmaEjb.create(tipusEstatPeticioDeFirma);
  }


  public TipusEstatPeticioDeFirmaJPA update(HttpServletRequest request, TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (TipusEstatPeticioDeFirmaJPA) tipusEstatPeticioDeFirmaEjb.update(tipusEstatPeticioDeFirma);
  }


  public void delete(HttpServletRequest request, TipusEstatPeticioDeFirma tipusEstatPeticioDeFirma) throws Exception,I18NException {
    tipusEstatPeticioDeFirmaEjb.delete(tipusEstatPeticioDeFirma);
  }

} // Final de Classe

