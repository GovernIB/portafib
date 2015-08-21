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
import es.caib.portafib.back.form.webdb.PosicioPaginaForm;

import es.caib.portafib.back.validator.webdb.PosicioPaginaWebValidator;

import es.caib.portafib.jpa.PosicioPaginaJPA;
import es.caib.portafib.model.entity.PosicioPagina;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PosicioPagina
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/posicioPagina")
@SessionAttributes(types = { PosicioPaginaForm.class, PosicioPaginaFilterForm.class })
public class PosicioPaginaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PosicioPagina, java.lang.Long> implements PosicioPaginaFields {

  @EJB(mappedName = es.caib.portafib.ejb.PosicioPaginaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PosicioPaginaLocal posicioPaginaEjb;

  @Autowired
  private PosicioPaginaWebValidator posicioPaginaWebValidator;

  @Autowired
  protected PosicioPaginaRefList posicioPaginaRefList;

  /**
   * Llistat de totes PosicioPagina
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PosicioPaginaFilterForm ff;
    ff = (PosicioPaginaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PosicioPagina de forma paginada
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
    llistat(mav, request, getPosicioPaginaFilterForm(pagina, mav, request));
    return mav;
  }

  public PosicioPaginaFilterForm getPosicioPaginaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PosicioPaginaFilterForm posicioPaginaFilterForm;
    posicioPaginaFilterForm = (PosicioPaginaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(posicioPaginaFilterForm == null) {
      posicioPaginaFilterForm = new PosicioPaginaFilterForm();
      posicioPaginaFilterForm.setContexte(getContextWeb());
      posicioPaginaFilterForm.setEntityNameCode(getEntityNameCode());
      posicioPaginaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      posicioPaginaFilterForm.setNou(true);
    } else {
      posicioPaginaFilterForm.setNou(false);
    }
    posicioPaginaFilterForm.setPage(pagina == null ? 1 : pagina);
    return posicioPaginaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PosicioPagina de forma paginada
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
      @ModelAttribute PosicioPaginaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPosicioPaginaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PosicioPagina de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PosicioPagina> llistat(ModelAndView mav, HttpServletRequest request,
     PosicioPaginaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PosicioPagina> posicioPagina = processarLlistat(posicioPaginaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("posicioPaginaItems", posicioPagina);

    mav.addObject("posicioPaginaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, posicioPagina, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, posicioPagina);

    return posicioPagina;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PosicioPaginaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PosicioPagina> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PosicioPaginaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PosicioPagina> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_POSICIOPAGINA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PosicioPagina
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPosicioPaginaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PosicioPaginaForm posicioPaginaForm = getPosicioPaginaForm(null, false, request, mav);
    mav.addObject("posicioPaginaForm" ,posicioPaginaForm);
    fillReferencesForForm(posicioPaginaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PosicioPaginaForm getPosicioPaginaForm(PosicioPaginaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PosicioPaginaForm posicioPaginaForm;
    if(_jpa == null) {
      posicioPaginaForm = new PosicioPaginaForm(new PosicioPaginaJPA(), true);
    } else {
      posicioPaginaForm = new PosicioPaginaForm(_jpa, false);
      posicioPaginaForm.setView(__isView);
    }
    posicioPaginaForm.setContexte(getContextWeb());
    posicioPaginaForm.setEntityNameCode(getEntityNameCode());
    posicioPaginaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return posicioPaginaForm;
  }

  public void fillReferencesForForm(PosicioPaginaForm posicioPaginaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou PosicioPagina
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPosicioPaginaPost(@ModelAttribute PosicioPaginaForm posicioPaginaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PosicioPaginaJPA posicioPagina = posicioPaginaForm.getPosicioPagina();

    try {
      preValidate(request, posicioPaginaForm, result);
      getWebValidator().validate(posicioPaginaForm, result);
      postValidate(request,posicioPaginaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        posicioPagina = create(request, posicioPagina);
        createMessageSuccess(request, "success.creation", posicioPagina.getPosicioPaginaID());
        posicioPaginaForm.setPosicioPagina(posicioPagina);
        return getRedirectWhenCreated(request, posicioPaginaForm);
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

  @RequestMapping(value = "/view/{posicioPaginaID}", method = RequestMethod.GET)
  public ModelAndView veurePosicioPaginaGet(@PathVariable("posicioPaginaID") java.lang.Long posicioPaginaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPosicioPaginaGet(posicioPaginaID,
        request, response, true);
  }


  protected ModelAndView editAndViewPosicioPaginaGet(@PathVariable("posicioPaginaID") java.lang.Long posicioPaginaID,
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
    PosicioPaginaJPA posicioPagina = findByPrimaryKey(request, posicioPaginaID);

    if (posicioPagina == null) {
      createMessageWarning(request, "error.notfound", posicioPaginaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, posicioPaginaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PosicioPaginaForm posicioPaginaForm = getPosicioPaginaForm(posicioPagina, __isView, request, mav);
      posicioPaginaForm.setView(__isView);
      if(__isView) {
        posicioPaginaForm.setAllFieldsReadOnly(ALL_POSICIOPAGINA_FIELDS);
        posicioPaginaForm.setSaveButtonVisible(false);
        posicioPaginaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(posicioPaginaForm, request, mav);
      mav.addObject("posicioPaginaForm", posicioPaginaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PosicioPagina existent
   */
  @RequestMapping(value = "/{posicioPaginaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPosicioPaginaGet(@PathVariable("posicioPaginaID") java.lang.Long posicioPaginaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPosicioPaginaGet(posicioPaginaID,
        request, response, false);
  }



  /**
   * Editar un PosicioPagina existent
   */
  @RequestMapping(value = "/{posicioPaginaID}/edit", method = RequestMethod.POST)
  public String editarPosicioPaginaPost(@ModelAttribute @Valid PosicioPaginaForm posicioPaginaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PosicioPaginaJPA posicioPagina = posicioPaginaForm.getPosicioPagina();

    try {
      preValidate(request, posicioPaginaForm, result);
      getWebValidator().validate(posicioPagina, result);
      postValidate(request, posicioPaginaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        posicioPagina = update(request, posicioPagina);
        createMessageSuccess(request, "success.modification", posicioPagina.getPosicioPaginaID());
        status.setComplete();
        return getRedirectWhenModified(request, posicioPaginaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          posicioPagina.getPosicioPaginaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, posicioPaginaForm, __e);
    }

  }


  /**
   * Eliminar un PosicioPagina existent
   */
  @RequestMapping(value = "/{posicioPaginaID}/delete")
  public String eliminarPosicioPagina(@PathVariable("posicioPaginaID") java.lang.Long posicioPaginaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PosicioPagina posicioPagina = posicioPaginaEjb.findByPrimaryKey(posicioPaginaID);
      if (posicioPagina == null) {
        String __msg =createMessageError(request, "error.notfound", posicioPaginaID);
        return getRedirectWhenDelete(request, posicioPaginaID, new Exception(__msg));
      } else {
        delete(request, posicioPagina);
        createMessageSuccess(request, "success.deleted", posicioPaginaID);
        return getRedirectWhenDelete(request, posicioPaginaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", posicioPaginaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, posicioPaginaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PosicioPaginaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPosicioPagina(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __posicioPaginaID, Throwable e) {
    java.lang.Long posicioPaginaID = (java.lang.Long)__posicioPaginaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (posicioPaginaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(posicioPaginaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "posicioPagina.posicioPagina";
  }

  public String getEntityNameCodePlural() {
    return "posicioPagina.posicioPagina.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("posicioPagina.posicioPaginaID");
  }

  @InitBinder("posicioPaginaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("posicioPaginaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public PosicioPaginaWebValidator getWebValidator() {
    return posicioPaginaWebValidator;
  }


  public void setWebValidator(PosicioPaginaWebValidator __val) {
    if (__val != null) {
      this.posicioPaginaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PosicioPagina
   */
  @RequestMapping(value = "/{posicioPaginaID}/cancel")
  public String cancelPosicioPagina(@PathVariable("posicioPaginaID") java.lang.Long posicioPaginaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, posicioPaginaID);
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


  public void preValidate(HttpServletRequest request,PosicioPaginaForm posicioPaginaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PosicioPaginaForm posicioPaginaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PosicioPaginaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PosicioPaginaFilterForm filterForm,  List<PosicioPagina> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PosicioPaginaForm posicioPaginaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PosicioPaginaForm posicioPaginaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long posicioPaginaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long posicioPaginaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "posicioPaginaFormWebDB";
  }

  public String getTileList() {
    return "posicioPaginaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PosicioPaginaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PosicioPaginaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long posicioPaginaID) throws I18NException {
    return (PosicioPaginaJPA) posicioPaginaEjb.findByPrimaryKey(posicioPaginaID);
  }


  public PosicioPaginaJPA create(HttpServletRequest request, PosicioPaginaJPA posicioPagina)
    throws Exception,I18NException, I18NValidationException {
    return (PosicioPaginaJPA) posicioPaginaEjb.create(posicioPagina);
  }


  public PosicioPaginaJPA update(HttpServletRequest request, PosicioPaginaJPA posicioPagina)
    throws Exception,I18NException, I18NValidationException {
    return (PosicioPaginaJPA) posicioPaginaEjb.update(posicioPagina);
  }


  public void delete(HttpServletRequest request, PosicioPagina posicioPagina) throws Exception,I18NException {
    posicioPaginaEjb.delete(posicioPagina);
  }

} // Final de Classe

