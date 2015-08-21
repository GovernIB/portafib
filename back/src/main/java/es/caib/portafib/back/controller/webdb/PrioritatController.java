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
import es.caib.portafib.back.form.webdb.PrioritatForm;

import es.caib.portafib.back.validator.webdb.PrioritatWebValidator;

import es.caib.portafib.jpa.PrioritatJPA;
import es.caib.portafib.model.entity.Prioritat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Prioritat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/prioritat")
@SessionAttributes(types = { PrioritatForm.class, PrioritatFilterForm.class })
public class PrioritatController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<Prioritat, java.lang.Integer> implements PrioritatFields {

  @EJB(mappedName = es.caib.portafib.ejb.PrioritatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PrioritatLocal prioritatEjb;

  @Autowired
  private PrioritatWebValidator prioritatWebValidator;

  @Autowired
  protected PrioritatRefList prioritatRefList;

  /**
   * Llistat de totes Prioritat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PrioritatFilterForm ff;
    ff = (PrioritatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Prioritat de forma paginada
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
    llistat(mav, request, getPrioritatFilterForm(pagina, mav, request));
    return mav;
  }

  public PrioritatFilterForm getPrioritatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PrioritatFilterForm prioritatFilterForm;
    prioritatFilterForm = (PrioritatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(prioritatFilterForm == null) {
      prioritatFilterForm = new PrioritatFilterForm();
      prioritatFilterForm.setContexte(getContextWeb());
      prioritatFilterForm.setEntityNameCode(getEntityNameCode());
      prioritatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      prioritatFilterForm.setNou(true);
    } else {
      prioritatFilterForm.setNou(false);
    }
    prioritatFilterForm.setPage(pagina == null ? 1 : pagina);
    return prioritatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Prioritat de forma paginada
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
      @ModelAttribute PrioritatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPrioritatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Prioritat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Prioritat> llistat(ModelAndView mav, HttpServletRequest request,
     PrioritatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Prioritat> prioritat = processarLlistat(prioritatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("prioritatItems", prioritat);

    mav.addObject("prioritatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, prioritat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, prioritat);

    return prioritat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PrioritatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Prioritat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PrioritatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Prioritat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PRIORITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Prioritat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPrioritatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PrioritatForm prioritatForm = getPrioritatForm(null, false, request, mav);
    mav.addObject("prioritatForm" ,prioritatForm);
    fillReferencesForForm(prioritatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PrioritatForm getPrioritatForm(PrioritatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PrioritatForm prioritatForm;
    if(_jpa == null) {
      prioritatForm = new PrioritatForm(new PrioritatJPA(), true);
    } else {
      prioritatForm = new PrioritatForm(_jpa, false);
      prioritatForm.setView(__isView);
    }
    prioritatForm.setContexte(getContextWeb());
    prioritatForm.setEntityNameCode(getEntityNameCode());
    prioritatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return prioritatForm;
  }

  public void fillReferencesForForm(PrioritatForm prioritatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Prioritat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPrioritatPost(@ModelAttribute PrioritatForm prioritatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PrioritatJPA prioritat = prioritatForm.getPrioritat();

    try {
      preValidate(request, prioritatForm, result);
      getWebValidator().validate(prioritatForm, result);
      postValidate(request,prioritatForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        prioritat = create(request, prioritat);
        createMessageSuccess(request, "success.creation", prioritat.getPrioritatID());
        prioritatForm.setPrioritat(prioritat);
        return getRedirectWhenCreated(request, prioritatForm);
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

  @RequestMapping(value = "/view/{prioritatID}", method = RequestMethod.GET)
  public ModelAndView veurePrioritatGet(@PathVariable("prioritatID") java.lang.Integer prioritatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPrioritatGet(prioritatID,
        request, response, true);
  }


  protected ModelAndView editAndViewPrioritatGet(@PathVariable("prioritatID") java.lang.Integer prioritatID,
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
    PrioritatJPA prioritat = findByPrimaryKey(request, prioritatID);

    if (prioritat == null) {
      createMessageWarning(request, "error.notfound", prioritatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, prioritatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PrioritatForm prioritatForm = getPrioritatForm(prioritat, __isView, request, mav);
      prioritatForm.setView(__isView);
      if(__isView) {
        prioritatForm.setAllFieldsReadOnly(ALL_PRIORITAT_FIELDS);
        prioritatForm.setSaveButtonVisible(false);
        prioritatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(prioritatForm, request, mav);
      mav.addObject("prioritatForm", prioritatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Prioritat existent
   */
  @RequestMapping(value = "/{prioritatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPrioritatGet(@PathVariable("prioritatID") java.lang.Integer prioritatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPrioritatGet(prioritatID,
        request, response, false);
  }



  /**
   * Editar un Prioritat existent
   */
  @RequestMapping(value = "/{prioritatID}/edit", method = RequestMethod.POST)
  public String editarPrioritatPost(@ModelAttribute @Valid PrioritatForm prioritatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PrioritatJPA prioritat = prioritatForm.getPrioritat();

    try {
      preValidate(request, prioritatForm, result);
      getWebValidator().validate(prioritat, result);
      postValidate(request, prioritatForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        prioritat = update(request, prioritat);
        createMessageSuccess(request, "success.modification", prioritat.getPrioritatID());
        status.setComplete();
        return getRedirectWhenModified(request, prioritatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          prioritat.getPrioritatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, prioritatForm, __e);
    }

  }


  /**
   * Eliminar un Prioritat existent
   */
  @RequestMapping(value = "/{prioritatID}/delete")
  public String eliminarPrioritat(@PathVariable("prioritatID") java.lang.Integer prioritatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Prioritat prioritat = prioritatEjb.findByPrimaryKey(prioritatID);
      if (prioritat == null) {
        String __msg =createMessageError(request, "error.notfound", prioritatID);
        return getRedirectWhenDelete(request, prioritatID, new Exception(__msg));
      } else {
        delete(request, prioritat);
        createMessageSuccess(request, "success.deleted", prioritatID);
        return getRedirectWhenDelete(request, prioritatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", prioritatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, prioritatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PrioritatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPrioritat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __prioritatID, Throwable e) {
    java.lang.Integer prioritatID = (java.lang.Integer)__prioritatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (prioritatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(prioritatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "prioritat.prioritat";
  }

  public String getEntityNameCodePlural() {
    return "prioritat.prioritat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("prioritat.prioritatID");
  }

  @InitBinder("prioritatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("prioritatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public PrioritatWebValidator getWebValidator() {
    return prioritatWebValidator;
  }


  public void setWebValidator(PrioritatWebValidator __val) {
    if (__val != null) {
      this.prioritatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Prioritat
   */
  @RequestMapping(value = "/{prioritatID}/cancel")
  public String cancelPrioritat(@PathVariable("prioritatID") java.lang.Integer prioritatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, prioritatID);
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


  public void preValidate(HttpServletRequest request,PrioritatForm prioritatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PrioritatForm prioritatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PrioritatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PrioritatFilterForm filterForm,  List<Prioritat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PrioritatForm prioritatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PrioritatForm prioritatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Integer prioritatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Integer prioritatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "prioritatFormWebDB";
  }

  public String getTileList() {
    return "prioritatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PrioritatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PrioritatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Integer prioritatID) throws I18NException {
    return (PrioritatJPA) prioritatEjb.findByPrimaryKey(prioritatID);
  }


  public PrioritatJPA create(HttpServletRequest request, PrioritatJPA prioritat)
    throws Exception,I18NException, I18NValidationException {
    return (PrioritatJPA) prioritatEjb.create(prioritat);
  }


  public PrioritatJPA update(HttpServletRequest request, PrioritatJPA prioritat)
    throws Exception,I18NException, I18NValidationException {
    return (PrioritatJPA) prioritatEjb.update(prioritat);
  }


  public void delete(HttpServletRequest request, Prioritat prioritat) throws Exception,I18NException {
    prioritatEjb.delete(prioritat);
  }

} // Final de Classe

