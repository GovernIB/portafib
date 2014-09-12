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
import es.caib.portafib.back.form.webdb.PosicioTaulaFirmesForm;

import es.caib.portafib.back.validator.webdb.PosicioTaulaFirmesWebValidator;

import es.caib.portafib.jpa.PosicioTaulaFirmesJPA;
import es.caib.portafib.model.entity.PosicioTaulaFirmes;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PosicioTaulaFirmes
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/posicioTaulaFirmes")
@SessionAttributes(types = { PosicioTaulaFirmesForm.class, PosicioTaulaFirmesFilterForm.class })
public class PosicioTaulaFirmesController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements PosicioTaulaFirmesFields {

  @EJB(mappedName = es.caib.portafib.ejb.PosicioTaulaFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PosicioTaulaFirmesLocal posicioTaulaFirmesEjb;

  @Autowired
  private PosicioTaulaFirmesWebValidator posicioTaulaFirmesWebValidator;

  @Autowired
  protected PosicioTaulaFirmesRefList posicioTaulaFirmesRefList;

  /**
   * Llistat de totes PosicioTaulaFirmes
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PosicioTaulaFirmesFilterForm ff;
    ff = (PosicioTaulaFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PosicioTaulaFirmes de forma paginada
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
    llistat(mav, request, getPosicioTaulaFirmesFilterForm(pagina, mav, request));
    return mav;
  }

  public PosicioTaulaFirmesFilterForm getPosicioTaulaFirmesFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PosicioTaulaFirmesFilterForm posicioTaulaFirmesFilterForm;
    posicioTaulaFirmesFilterForm = (PosicioTaulaFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(posicioTaulaFirmesFilterForm == null) {
      posicioTaulaFirmesFilterForm = new PosicioTaulaFirmesFilterForm();
      posicioTaulaFirmesFilterForm.setContexte(getContextWeb());
      posicioTaulaFirmesFilterForm.setEntityNameCode(getEntityNameCode());
      posicioTaulaFirmesFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      posicioTaulaFirmesFilterForm.setNou(true);
    } else {
      posicioTaulaFirmesFilterForm.setNou(false);
    }
    posicioTaulaFirmesFilterForm.setPage(pagina == null ? 1 : pagina);
    return posicioTaulaFirmesFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PosicioTaulaFirmes de forma paginada
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
      @ModelAttribute PosicioTaulaFirmesFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPosicioTaulaFirmesFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PosicioTaulaFirmes de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PosicioTaulaFirmes> llistat(ModelAndView mav, HttpServletRequest request,
     PosicioTaulaFirmesFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<PosicioTaulaFirmes> posicioTaulaFirmes = (List<PosicioTaulaFirmes>) processarLlistat(posicioTaulaFirmesEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("posicioTaulaFirmesItems", posicioTaulaFirmes);

    mav.addObject("posicioTaulaFirmesFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, posicioTaulaFirmes, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, posicioTaulaFirmes);

    return posicioTaulaFirmes;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PosicioTaulaFirmesFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PosicioTaulaFirmes> list, List<GroupByItem> groupItems) throws I18NException {
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
    PosicioTaulaFirmesFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PosicioTaulaFirmes> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_POSICIOTAULAFIRMES_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PosicioTaulaFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPosicioTaulaFirmesGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PosicioTaulaFirmesForm posicioTaulaFirmesForm = getPosicioTaulaFirmesForm(null, false, request, mav);
    mav.addObject("posicioTaulaFirmesForm" ,posicioTaulaFirmesForm);
    fillReferencesForForm(posicioTaulaFirmesForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PosicioTaulaFirmesForm getPosicioTaulaFirmesForm(PosicioTaulaFirmesJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PosicioTaulaFirmesForm posicioTaulaFirmesForm;
    if(_jpa == null) {
      posicioTaulaFirmesForm = new PosicioTaulaFirmesForm(new PosicioTaulaFirmesJPA(), true);
    } else {
      posicioTaulaFirmesForm = new PosicioTaulaFirmesForm(_jpa, false);
      posicioTaulaFirmesForm.setView(__isView);
    }
    posicioTaulaFirmesForm.setContexte(getContextWeb());
    posicioTaulaFirmesForm.setEntityNameCode(getEntityNameCode());
    posicioTaulaFirmesForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return posicioTaulaFirmesForm;
  }

  public void fillReferencesForForm(PosicioTaulaFirmesForm posicioTaulaFirmesForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou PosicioTaulaFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPosicioTaulaFirmesPost(@ModelAttribute PosicioTaulaFirmesForm posicioTaulaFirmesForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PosicioTaulaFirmesJPA posicioTaulaFirmes = posicioTaulaFirmesForm.getPosicioTaulaFirmes();

    try {
      preValidate(posicioTaulaFirmesForm, result);
      getWebValidator().validate(posicioTaulaFirmesForm, result);
      postValidate(posicioTaulaFirmesForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        posicioTaulaFirmes = create(posicioTaulaFirmes);
        createMessageSuccess(request, "success.creation", posicioTaulaFirmes.getPosicioTaulaFirmesID());
        posicioTaulaFirmesForm.setPosicioTaulaFirmes(posicioTaulaFirmes);
        return getRedirectWhenCreated(posicioTaulaFirmesForm);
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

  @RequestMapping(value = "/view/{posicioTaulaFirmesID}", method = RequestMethod.GET)
  public ModelAndView veurePosicioTaulaFirmesGet(@PathVariable("posicioTaulaFirmesID") java.lang.Integer posicioTaulaFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPosicioTaulaFirmesGet(posicioTaulaFirmesID,
        request, response, true);
  }


  protected ModelAndView editAndViewPosicioTaulaFirmesGet(@PathVariable("posicioTaulaFirmesID") java.lang.Integer posicioTaulaFirmesID,
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
    PosicioTaulaFirmesJPA posicioTaulaFirmes = findByPrimaryKey(posicioTaulaFirmesID);

    if (posicioTaulaFirmes == null) {
      createMessageWarning(request, "error.notfound", posicioTaulaFirmesID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(posicioTaulaFirmesID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PosicioTaulaFirmesForm posicioTaulaFirmesForm = getPosicioTaulaFirmesForm(posicioTaulaFirmes, __isView, request, mav);
      posicioTaulaFirmesForm.setView(__isView);
      if(__isView) {
        posicioTaulaFirmesForm.setAllFieldsReadOnly(ALL_POSICIOTAULAFIRMES_FIELDS);
        posicioTaulaFirmesForm.setSaveButtonVisible(false);
        posicioTaulaFirmesForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(posicioTaulaFirmesForm, request, mav);
      mav.addObject("posicioTaulaFirmesForm", posicioTaulaFirmesForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PosicioTaulaFirmes existent
   */
  @RequestMapping(value = "/{posicioTaulaFirmesID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPosicioTaulaFirmesGet(@PathVariable("posicioTaulaFirmesID") java.lang.Integer posicioTaulaFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPosicioTaulaFirmesGet(posicioTaulaFirmesID,
        request, response, false);
  }



  /**
   * Editar un PosicioTaulaFirmes existent
   */
  @RequestMapping(value = "/{posicioTaulaFirmesID}/edit", method = RequestMethod.POST)
  public String editarPosicioTaulaFirmesPost(@ModelAttribute @Valid PosicioTaulaFirmesForm posicioTaulaFirmesForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PosicioTaulaFirmesJPA posicioTaulaFirmes = posicioTaulaFirmesForm.getPosicioTaulaFirmes();

    try {
      preValidate(posicioTaulaFirmesForm, result);
      getWebValidator().validate(posicioTaulaFirmes, result);
      postValidate(posicioTaulaFirmesForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        posicioTaulaFirmes = update(posicioTaulaFirmes);
        createMessageSuccess(request, "success.modification", posicioTaulaFirmes.getPosicioTaulaFirmesID());
        status.setComplete();
        return getRedirectWhenModified(posicioTaulaFirmesForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          posicioTaulaFirmes.getPosicioTaulaFirmesID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(posicioTaulaFirmesForm, __e);
    }

  }


  /**
   * Eliminar un PosicioTaulaFirmes existent
   */
  @RequestMapping(value = "/{posicioTaulaFirmesID}/delete")
  public String eliminarPosicioTaulaFirmes(@PathVariable("posicioTaulaFirmesID") java.lang.Integer posicioTaulaFirmesID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PosicioTaulaFirmes posicioTaulaFirmes = posicioTaulaFirmesEjb.findByPrimaryKey(posicioTaulaFirmesID);
      if (posicioTaulaFirmes == null) {
        String __msg =createMessageError(request, "error.notfound", posicioTaulaFirmesID);
        return getRedirectWhenDelete(posicioTaulaFirmesID, new Exception(__msg));
      } else {
        delete(request, posicioTaulaFirmes);
        createMessageSuccess(request, "success.deleted", posicioTaulaFirmesID);
        return getRedirectWhenDelete(posicioTaulaFirmesID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", posicioTaulaFirmesID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(posicioTaulaFirmesID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PosicioTaulaFirmesFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPosicioTaulaFirmes(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(null,null);
  }

  return redirect;
}



public java.lang.Integer stringToPK(String value) {
  return new java.lang.Integer(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __posicioTaulaFirmesID, Throwable e) {
    java.lang.Integer posicioTaulaFirmesID = (java.lang.Integer)__posicioTaulaFirmesID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (posicioTaulaFirmesID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(posicioTaulaFirmesID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "posicioTaulaFirmes.posicioTaulaFirmes";
  }

  public String getEntityNameCodePlural() {
    return "posicioTaulaFirmes.posicioTaulaFirmes.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("posicioTaulaFirmes.posicioTaulaFirmesID");
  }

  @InitBinder("posicioTaulaFirmesFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("posicioTaulaFirmesForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public PosicioTaulaFirmesWebValidator getWebValidator() {
    return posicioTaulaFirmesWebValidator;
  }


  public void setWebValidator(PosicioTaulaFirmesWebValidator __val) {
    if (__val != null) {
      this.posicioTaulaFirmesWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PosicioTaulaFirmes
   */
  @RequestMapping(value = "/{posicioTaulaFirmesID}/cancel")
  public String cancelPosicioTaulaFirmes(@PathVariable("posicioTaulaFirmesID") java.lang.Integer posicioTaulaFirmesID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(posicioTaulaFirmesID);
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


  public void preValidate(PosicioTaulaFirmesForm posicioTaulaFirmesForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(PosicioTaulaFirmesForm posicioTaulaFirmesForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PosicioTaulaFirmesFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PosicioTaulaFirmesFilterForm filterForm,  List<PosicioTaulaFirmes> list) throws I18NException {
  }

  public String getRedirectWhenCreated(PosicioTaulaFirmesForm posicioTaulaFirmesForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(PosicioTaulaFirmesForm posicioTaulaFirmesForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(java.lang.Integer posicioTaulaFirmesID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(java.lang.Integer posicioTaulaFirmesID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "posicioTaulaFirmesFormWebDB";
  }

  public String getTileList() {
    return "posicioTaulaFirmesListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PosicioTaulaFirmesWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PosicioTaulaFirmesJPA findByPrimaryKey(java.lang.Integer posicioTaulaFirmesID) throws I18NException {
    return (PosicioTaulaFirmesJPA) posicioTaulaFirmesEjb.findByPrimaryKey(posicioTaulaFirmesID);
  }


  public PosicioTaulaFirmesJPA create(PosicioTaulaFirmesJPA posicioTaulaFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (PosicioTaulaFirmesJPA) posicioTaulaFirmesEjb.create(posicioTaulaFirmes);
  }


  public void delete(HttpServletRequest request, PosicioTaulaFirmes posicioTaulaFirmes) throws Exception,I18NException {
    posicioTaulaFirmesEjb.delete(posicioTaulaFirmes);
  }


  public PosicioTaulaFirmesJPA update(PosicioTaulaFirmesJPA posicioTaulaFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (PosicioTaulaFirmesJPA) posicioTaulaFirmesEjb.update(posicioTaulaFirmes);
  }

} // Final de Classe

