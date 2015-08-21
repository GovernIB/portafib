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
import es.caib.portafib.back.form.webdb.AlgorismeDeFirmaForm;

import es.caib.portafib.back.validator.webdb.AlgorismeDeFirmaWebValidator;

import es.caib.portafib.jpa.AlgorismeDeFirmaJPA;
import es.caib.portafib.model.entity.AlgorismeDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un AlgorismeDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/algorismeDeFirma")
@SessionAttributes(types = { AlgorismeDeFirmaForm.class, AlgorismeDeFirmaFilterForm.class })
public class AlgorismeDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<AlgorismeDeFirma, java.lang.Long> implements AlgorismeDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.AlgorismeDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.AlgorismeDeFirmaLocal algorismeDeFirmaEjb;

  @Autowired
  private AlgorismeDeFirmaWebValidator algorismeDeFirmaWebValidator;

  @Autowired
  protected AlgorismeDeFirmaRefList algorismeDeFirmaRefList;

  /**
   * Llistat de totes AlgorismeDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AlgorismeDeFirmaFilterForm ff;
    ff = (AlgorismeDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar AlgorismeDeFirma de forma paginada
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
    llistat(mav, request, getAlgorismeDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public AlgorismeDeFirmaFilterForm getAlgorismeDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AlgorismeDeFirmaFilterForm algorismeDeFirmaFilterForm;
    algorismeDeFirmaFilterForm = (AlgorismeDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(algorismeDeFirmaFilterForm == null) {
      algorismeDeFirmaFilterForm = new AlgorismeDeFirmaFilterForm();
      algorismeDeFirmaFilterForm.setContexte(getContextWeb());
      algorismeDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      algorismeDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      algorismeDeFirmaFilterForm.setNou(true);
    } else {
      algorismeDeFirmaFilterForm.setNou(false);
    }
    algorismeDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return algorismeDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar AlgorismeDeFirma de forma paginada
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
      @ModelAttribute AlgorismeDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAlgorismeDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de AlgorismeDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<AlgorismeDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     AlgorismeDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<AlgorismeDeFirma> algorismeDeFirma = processarLlistat(algorismeDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("algorismeDeFirmaItems", algorismeDeFirma);

    mav.addObject("algorismeDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, algorismeDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, algorismeDeFirma);

    return algorismeDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AlgorismeDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<AlgorismeDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, SUPORTAT);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AlgorismeDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<AlgorismeDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ALGORISMEDEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou AlgorismeDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAlgorismeDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AlgorismeDeFirmaForm algorismeDeFirmaForm = getAlgorismeDeFirmaForm(null, false, request, mav);
    mav.addObject("algorismeDeFirmaForm" ,algorismeDeFirmaForm);
    fillReferencesForForm(algorismeDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AlgorismeDeFirmaForm getAlgorismeDeFirmaForm(AlgorismeDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AlgorismeDeFirmaForm algorismeDeFirmaForm;
    if(_jpa == null) {
      algorismeDeFirmaForm = new AlgorismeDeFirmaForm(new AlgorismeDeFirmaJPA(), true);
    } else {
      algorismeDeFirmaForm = new AlgorismeDeFirmaForm(_jpa, false);
      algorismeDeFirmaForm.setView(__isView);
    }
    algorismeDeFirmaForm.setContexte(getContextWeb());
    algorismeDeFirmaForm.setEntityNameCode(getEntityNameCode());
    algorismeDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return algorismeDeFirmaForm;
  }

  public void fillReferencesForForm(AlgorismeDeFirmaForm algorismeDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou AlgorismeDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAlgorismeDeFirmaPost(@ModelAttribute AlgorismeDeFirmaForm algorismeDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AlgorismeDeFirmaJPA algorismeDeFirma = algorismeDeFirmaForm.getAlgorismeDeFirma();

    try {
      preValidate(request, algorismeDeFirmaForm, result);
      getWebValidator().validate(algorismeDeFirmaForm, result);
      postValidate(request,algorismeDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        algorismeDeFirma = create(request, algorismeDeFirma);
        createMessageSuccess(request, "success.creation", algorismeDeFirma.getAlgorismeDeFirmaID());
        algorismeDeFirmaForm.setAlgorismeDeFirma(algorismeDeFirma);
        return getRedirectWhenCreated(request, algorismeDeFirmaForm);
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

  @RequestMapping(value = "/view/{algorismeDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView veureAlgorismeDeFirmaGet(@PathVariable("algorismeDeFirmaID") java.lang.Long algorismeDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAlgorismeDeFirmaGet(algorismeDeFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewAlgorismeDeFirmaGet(@PathVariable("algorismeDeFirmaID") java.lang.Long algorismeDeFirmaID,
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
    AlgorismeDeFirmaJPA algorismeDeFirma = findByPrimaryKey(request, algorismeDeFirmaID);

    if (algorismeDeFirma == null) {
      createMessageWarning(request, "error.notfound", algorismeDeFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, algorismeDeFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AlgorismeDeFirmaForm algorismeDeFirmaForm = getAlgorismeDeFirmaForm(algorismeDeFirma, __isView, request, mav);
      algorismeDeFirmaForm.setView(__isView);
      if(__isView) {
        algorismeDeFirmaForm.setAllFieldsReadOnly(ALL_ALGORISMEDEFIRMA_FIELDS);
        algorismeDeFirmaForm.setSaveButtonVisible(false);
        algorismeDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(algorismeDeFirmaForm, request, mav);
      mav.addObject("algorismeDeFirmaForm", algorismeDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un AlgorismeDeFirma existent
   */
  @RequestMapping(value = "/{algorismeDeFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAlgorismeDeFirmaGet(@PathVariable("algorismeDeFirmaID") java.lang.Long algorismeDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAlgorismeDeFirmaGet(algorismeDeFirmaID,
        request, response, false);
  }



  /**
   * Editar un AlgorismeDeFirma existent
   */
  @RequestMapping(value = "/{algorismeDeFirmaID}/edit", method = RequestMethod.POST)
  public String editarAlgorismeDeFirmaPost(@ModelAttribute @Valid AlgorismeDeFirmaForm algorismeDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AlgorismeDeFirmaJPA algorismeDeFirma = algorismeDeFirmaForm.getAlgorismeDeFirma();

    try {
      preValidate(request, algorismeDeFirmaForm, result);
      getWebValidator().validate(algorismeDeFirma, result);
      postValidate(request, algorismeDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        algorismeDeFirma = update(request, algorismeDeFirma);
        createMessageSuccess(request, "success.modification", algorismeDeFirma.getAlgorismeDeFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, algorismeDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          algorismeDeFirma.getAlgorismeDeFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, algorismeDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un AlgorismeDeFirma existent
   */
  @RequestMapping(value = "/{algorismeDeFirmaID}/delete")
  public String eliminarAlgorismeDeFirma(@PathVariable("algorismeDeFirmaID") java.lang.Long algorismeDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      AlgorismeDeFirma algorismeDeFirma = algorismeDeFirmaEjb.findByPrimaryKey(algorismeDeFirmaID);
      if (algorismeDeFirma == null) {
        String __msg =createMessageError(request, "error.notfound", algorismeDeFirmaID);
        return getRedirectWhenDelete(request, algorismeDeFirmaID, new Exception(__msg));
      } else {
        delete(request, algorismeDeFirma);
        createMessageSuccess(request, "success.deleted", algorismeDeFirmaID);
        return getRedirectWhenDelete(request, algorismeDeFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", algorismeDeFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, algorismeDeFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AlgorismeDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAlgorismeDeFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __algorismeDeFirmaID, Throwable e) {
    java.lang.Long algorismeDeFirmaID = (java.lang.Long)__algorismeDeFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (algorismeDeFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(algorismeDeFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "algorismeDeFirma.algorismeDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "algorismeDeFirma.algorismeDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("algorismeDeFirma.algorismeDeFirmaID");
  }

  @InitBinder("algorismeDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("algorismeDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public AlgorismeDeFirmaWebValidator getWebValidator() {
    return algorismeDeFirmaWebValidator;
  }


  public void setWebValidator(AlgorismeDeFirmaWebValidator __val) {
    if (__val != null) {
      this.algorismeDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de AlgorismeDeFirma
   */
  @RequestMapping(value = "/{algorismeDeFirmaID}/cancel")
  public String cancelAlgorismeDeFirma(@PathVariable("algorismeDeFirmaID") java.lang.Long algorismeDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, algorismeDeFirmaID);
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


  public void preValidate(HttpServletRequest request,AlgorismeDeFirmaForm algorismeDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AlgorismeDeFirmaForm algorismeDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AlgorismeDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AlgorismeDeFirmaFilterForm filterForm,  List<AlgorismeDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AlgorismeDeFirmaForm algorismeDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AlgorismeDeFirmaForm algorismeDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long algorismeDeFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long algorismeDeFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "algorismeDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "algorismeDeFirmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AlgorismeDeFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AlgorismeDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long algorismeDeFirmaID) throws I18NException {
    return (AlgorismeDeFirmaJPA) algorismeDeFirmaEjb.findByPrimaryKey(algorismeDeFirmaID);
  }


  public AlgorismeDeFirmaJPA create(HttpServletRequest request, AlgorismeDeFirmaJPA algorismeDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (AlgorismeDeFirmaJPA) algorismeDeFirmaEjb.create(algorismeDeFirma);
  }


  public AlgorismeDeFirmaJPA update(HttpServletRequest request, AlgorismeDeFirmaJPA algorismeDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (AlgorismeDeFirmaJPA) algorismeDeFirmaEjb.update(algorismeDeFirma);
  }


  public void delete(HttpServletRequest request, AlgorismeDeFirma algorismeDeFirma) throws Exception,I18NException {
    algorismeDeFirmaEjb.delete(algorismeDeFirma);
  }

} // Final de Classe

