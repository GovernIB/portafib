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

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.form.webdb.CodiBarresForm;

import es.caib.portafib.back.validator.webdb.CodiBarresWebValidator;

import es.caib.portafib.persistence.CodiBarresJPA;
import es.caib.portafib.model.entity.CodiBarres;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un CodiBarres
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/codiBarres")
@SessionAttributes(types = { CodiBarresForm.class, CodiBarresFilterForm.class })
public class CodiBarresController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<CodiBarres, java.lang.String> implements CodiBarresFields {

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresService.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresService codiBarresEjb;

  @Autowired
  private CodiBarresWebValidator codiBarresWebValidator;

  @Autowired
  protected CodiBarresRefList codiBarresRefList;

  /**
   * Llistat de totes CodiBarres
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    CodiBarresFilterForm ff;
    ff = (CodiBarresFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar CodiBarres de forma paginada
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
    llistat(mav, request, getCodiBarresFilterForm(pagina, mav, request));
    return mav;
  }

  public CodiBarresFilterForm getCodiBarresFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    CodiBarresFilterForm codiBarresFilterForm;
    codiBarresFilterForm = (CodiBarresFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(codiBarresFilterForm == null) {
      codiBarresFilterForm = new CodiBarresFilterForm();
      codiBarresFilterForm.setContexte(getContextWeb());
      codiBarresFilterForm.setEntityNameCode(getEntityNameCode());
      codiBarresFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      codiBarresFilterForm.setNou(true);
    } else {
      codiBarresFilterForm.setNou(false);
    }
    codiBarresFilterForm.setPage(pagina == null ? 1 : pagina);
    return codiBarresFilterForm;
  }

  /**
   * Segona i següent peticions per llistar CodiBarres de forma paginada
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
      @ModelAttribute CodiBarresFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getCodiBarresFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de CodiBarres de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<CodiBarres> llistat(ModelAndView mav, HttpServletRequest request,
     CodiBarresFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<CodiBarres> codiBarres = processarLlistat(codiBarresEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("codiBarresItems", codiBarres);

    mav.addObject("codiBarresFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, codiBarres, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, codiBarres);

    return codiBarres;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(CodiBarresFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<CodiBarres> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    CodiBarresFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<CodiBarres> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CODIBARRES_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou CodiBarres
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearCodiBarresGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    CodiBarresForm codiBarresForm = getCodiBarresForm(null, false, request, mav);
    mav.addObject("codiBarresForm" ,codiBarresForm);
    fillReferencesForForm(codiBarresForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public CodiBarresForm getCodiBarresForm(CodiBarresJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    CodiBarresForm codiBarresForm;
    if(_jpa == null) {
      codiBarresForm = new CodiBarresForm(new CodiBarresJPA(), true);
    } else {
      codiBarresForm = new CodiBarresForm(_jpa, false);
      codiBarresForm.setView(__isView);
    }
    codiBarresForm.setContexte(getContextWeb());
    codiBarresForm.setEntityNameCode(getEntityNameCode());
    codiBarresForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return codiBarresForm;
  }

  public void fillReferencesForForm(CodiBarresForm codiBarresForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou CodiBarres
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearCodiBarresPost(@ModelAttribute CodiBarresForm codiBarresForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    CodiBarresJPA codiBarres = codiBarresForm.getCodiBarres();

    try {
      preValidate(request, codiBarresForm, result);
      getWebValidator().validate(codiBarresForm, result);
      postValidate(request,codiBarresForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        codiBarres = create(request, codiBarres);
        createMessageSuccess(request, "success.creation", codiBarres.getCodiBarresID());
        codiBarresForm.setCodiBarres(codiBarres);
        return getRedirectWhenCreated(request, codiBarresForm);
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

  @RequestMapping(value = "/view/{codiBarresID}", method = RequestMethod.GET)
  public ModelAndView veureCodiBarresGet(@PathVariable("codiBarresID") java.lang.String codiBarresID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCodiBarresGet(codiBarresID,
        request, response, true);
  }


  protected ModelAndView editAndViewCodiBarresGet(@PathVariable("codiBarresID") java.lang.String codiBarresID,
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
    CodiBarresJPA codiBarres = findByPrimaryKey(request, codiBarresID);

    if (codiBarres == null) {
      createMessageWarning(request, "error.notfound", codiBarresID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, codiBarresID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      CodiBarresForm codiBarresForm = getCodiBarresForm(codiBarres, __isView, request, mav);
      codiBarresForm.setView(__isView);
      if(__isView) {
        codiBarresForm.setAllFieldsReadOnly(ALL_CODIBARRES_FIELDS);
        codiBarresForm.setSaveButtonVisible(false);
        codiBarresForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(codiBarresForm, request, mav);
      mav.addObject("codiBarresForm", codiBarresForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un CodiBarres existent
   */
  @RequestMapping(value = "/{codiBarresID}/edit", method = RequestMethod.GET)
  public ModelAndView editarCodiBarresGet(@PathVariable("codiBarresID") java.lang.String codiBarresID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCodiBarresGet(codiBarresID,
        request, response, false);
  }



  /**
   * Editar un CodiBarres existent
   */
  @RequestMapping(value = "/{codiBarresID}/edit", method = RequestMethod.POST)
  public String editarCodiBarresPost(@ModelAttribute CodiBarresForm codiBarresForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    CodiBarresJPA codiBarres = codiBarresForm.getCodiBarres();

    try {
      preValidate(request, codiBarresForm, result);
      getWebValidator().validate(codiBarresForm, result);
      postValidate(request, codiBarresForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        codiBarres = update(request, codiBarres);
        createMessageSuccess(request, "success.modification", codiBarres.getCodiBarresID());
        status.setComplete();
        return getRedirectWhenModified(request, codiBarresForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          codiBarres.getCodiBarresID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, codiBarresForm, __e);
    }

  }


  /**
   * Eliminar un CodiBarres existent
   */
  @RequestMapping(value = "/{codiBarresID}/delete")
  public String eliminarCodiBarres(@PathVariable("codiBarresID") java.lang.String codiBarresID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      CodiBarres codiBarres = this.findByPrimaryKey(request, codiBarresID);
      if (codiBarres == null) {
        String __msg = createMessageError(request, "error.notfound", codiBarresID);
        return getRedirectWhenDelete(request, codiBarresID, new Exception(__msg));
      } else {
        delete(request, codiBarres);
        createMessageSuccess(request, "success.deleted", codiBarresID);
        return getRedirectWhenDelete(request, codiBarresID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", codiBarresID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, codiBarresID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute CodiBarresFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarCodiBarres(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.String stringToPK(String value) {
  return value;
}

  @Override
  public String[] getArgumentsMissatge(Object __codiBarresID, Throwable e) {
    java.lang.String codiBarresID = (java.lang.String)__codiBarresID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (codiBarresID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(codiBarresID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "codiBarres.codiBarres";
  }

  public String getEntityNameCodePlural() {
    return "codiBarres.codiBarres.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("codiBarres.codiBarresID");
  }

  @InitBinder("codiBarresFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("codiBarresForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder);
  }

  public CodiBarresWebValidator getWebValidator() {
    return codiBarresWebValidator;
  }


  public void setWebValidator(CodiBarresWebValidator __val) {
    if (__val != null) {
      this.codiBarresWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de CodiBarres
   */
  @RequestMapping(value = "/{codiBarresID}/cancel")
  public String cancelCodiBarres(@PathVariable("codiBarresID") java.lang.String codiBarresID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, codiBarresID);
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,CodiBarresForm codiBarresForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,CodiBarresForm codiBarresForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, CodiBarresFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, CodiBarresFilterForm filterForm,  List<CodiBarres> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, CodiBarresForm codiBarresForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, CodiBarresForm codiBarresForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.String codiBarresID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.String codiBarresID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "codiBarresFormWebDB";
  }

  public String getTileList() {
    return "codiBarresListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "CodiBarres_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public CodiBarresJPA findByPrimaryKey(HttpServletRequest request, java.lang.String codiBarresID) throws I18NException {
    return (CodiBarresJPA) codiBarresEjb.findByPrimaryKey(codiBarresID);
  }


  public CodiBarresJPA create(HttpServletRequest request, CodiBarresJPA codiBarres)
    throws I18NException, I18NValidationException {
    return (CodiBarresJPA) codiBarresEjb.create(codiBarres);
  }


  public CodiBarresJPA update(HttpServletRequest request, CodiBarresJPA codiBarres)
    throws I18NException, I18NValidationException {
    return (CodiBarresJPA) codiBarresEjb.update(codiBarres);
  }


  public void delete(HttpServletRequest request, CodiBarres codiBarres) throws I18NException {
    codiBarresEjb.delete(codiBarres);
  }

} // Final de Classe

