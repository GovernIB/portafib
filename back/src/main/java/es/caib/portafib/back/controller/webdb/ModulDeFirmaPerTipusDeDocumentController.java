package es.caib.portafib.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
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
import es.caib.portafib.back.form.webdb.ModulDeFirmaPerTipusDeDocumentForm;

import es.caib.portafib.back.validator.webdb.ModulDeFirmaPerTipusDeDocumentWebValidator;

import es.caib.portafib.jpa.ModulDeFirmaPerTipusDeDocumentJPA;
import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un ModulDeFirmaPerTipusDeDocument
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/modulDeFirmaPerTipusDeDocument")
@SessionAttributes(types = { ModulDeFirmaPerTipusDeDocumentForm.class, ModulDeFirmaPerTipusDeDocumentFilterForm.class })
public class ModulDeFirmaPerTipusDeDocumentController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<ModulDeFirmaPerTipusDeDocument, java.lang.Long> implements ModulDeFirmaPerTipusDeDocumentFields {

  @EJB(mappedName = es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal modulDeFirmaPerTipusDeDocumentEjb;

  @Autowired
  private ModulDeFirmaPerTipusDeDocumentWebValidator modulDeFirmaPerTipusDeDocumentWebValidator;

  @Autowired
  protected ModulDeFirmaPerTipusDeDocumentRefList modulDeFirmaPerTipusDeDocumentRefList;

  // References 
  @Autowired
  protected TipusDocumentRefList tipusDocumentRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes ModulDeFirmaPerTipusDeDocument
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ModulDeFirmaPerTipusDeDocumentFilterForm ff;
    ff = (ModulDeFirmaPerTipusDeDocumentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ModulDeFirmaPerTipusDeDocument de forma paginada
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
    llistat(mav, request, getModulDeFirmaPerTipusDeDocumentFilterForm(pagina, mav, request));
    return mav;
  }

  public ModulDeFirmaPerTipusDeDocumentFilterForm getModulDeFirmaPerTipusDeDocumentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ModulDeFirmaPerTipusDeDocumentFilterForm modulDeFirmaPerTipusDeDocumentFilterForm;
    modulDeFirmaPerTipusDeDocumentFilterForm = (ModulDeFirmaPerTipusDeDocumentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(modulDeFirmaPerTipusDeDocumentFilterForm == null) {
      modulDeFirmaPerTipusDeDocumentFilterForm = new ModulDeFirmaPerTipusDeDocumentFilterForm();
      modulDeFirmaPerTipusDeDocumentFilterForm.setContexte(getContextWeb());
      modulDeFirmaPerTipusDeDocumentFilterForm.setEntityNameCode(getEntityNameCode());
      modulDeFirmaPerTipusDeDocumentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      modulDeFirmaPerTipusDeDocumentFilterForm.setNou(true);
    } else {
      modulDeFirmaPerTipusDeDocumentFilterForm.setNou(false);
    }
    modulDeFirmaPerTipusDeDocumentFilterForm.setPage(pagina == null ? 1 : pagina);
    return modulDeFirmaPerTipusDeDocumentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ModulDeFirmaPerTipusDeDocument de forma paginada
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
      @ModelAttribute ModulDeFirmaPerTipusDeDocumentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getModulDeFirmaPerTipusDeDocumentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ModulDeFirmaPerTipusDeDocument de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ModulDeFirmaPerTipusDeDocument> llistat(ModelAndView mav, HttpServletRequest request,
     ModulDeFirmaPerTipusDeDocumentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ModulDeFirmaPerTipusDeDocument> modulDeFirmaPerTipusDeDocument = processarLlistat(modulDeFirmaPerTipusDeDocumentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("modulDeFirmaPerTipusDeDocumentItems", modulDeFirmaPerTipusDeDocument);

    mav.addObject("modulDeFirmaPerTipusDeDocumentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, modulDeFirmaPerTipusDeDocument, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, modulDeFirmaPerTipusDeDocument);

    return modulDeFirmaPerTipusDeDocument;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ModulDeFirmaPerTipusDeDocumentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ModulDeFirmaPerTipusDeDocument> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipusDocumentID
    {
      _listSKV = getReferenceListForTipusDocumentID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusDocumentForTipusDocumentID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSDOCUMENTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSDOCUMENTID, false);
      };
    }

    // Field pluginID
    {
      _listSKV = getReferenceListForPluginID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ModulDeFirmaPerTipusDeDocumentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ModulDeFirmaPerTipusDeDocument> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_MODULDEFIRMAPERTIPUSDEDOCUMENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUSDOCUMENTID, filterForm.getMapOfTipusDocumentForTipusDocumentID());
    __mapping.put(PLUGINID, filterForm.getMapOfPluginForPluginID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ModulDeFirmaPerTipusDeDocument
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearModulDeFirmaPerTipusDeDocumentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm = getModulDeFirmaPerTipusDeDocumentForm(null, false, request, mav);
    mav.addObject("modulDeFirmaPerTipusDeDocumentForm" ,modulDeFirmaPerTipusDeDocumentForm);
    fillReferencesForForm(modulDeFirmaPerTipusDeDocumentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ModulDeFirmaPerTipusDeDocumentForm getModulDeFirmaPerTipusDeDocumentForm(ModulDeFirmaPerTipusDeDocumentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm;
    if(_jpa == null) {
      modulDeFirmaPerTipusDeDocumentForm = new ModulDeFirmaPerTipusDeDocumentForm(new ModulDeFirmaPerTipusDeDocumentJPA(), true);
    } else {
      modulDeFirmaPerTipusDeDocumentForm = new ModulDeFirmaPerTipusDeDocumentForm(_jpa, false);
      modulDeFirmaPerTipusDeDocumentForm.setView(__isView);
    }
    modulDeFirmaPerTipusDeDocumentForm.setContexte(getContextWeb());
    modulDeFirmaPerTipusDeDocumentForm.setEntityNameCode(getEntityNameCode());
    modulDeFirmaPerTipusDeDocumentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return modulDeFirmaPerTipusDeDocumentForm;
  }

  public void fillReferencesForForm(ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (modulDeFirmaPerTipusDeDocumentForm.getListOfTipusDocumentForTipusDocumentID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusDocumentID(request, mav, modulDeFirmaPerTipusDeDocumentForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      modulDeFirmaPerTipusDeDocumentForm.setListOfTipusDocumentForTipusDocumentID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (modulDeFirmaPerTipusDeDocumentForm.getListOfPluginForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, modulDeFirmaPerTipusDeDocumentForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      modulDeFirmaPerTipusDeDocumentForm.setListOfPluginForPluginID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou ModulDeFirmaPerTipusDeDocument
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearModulDeFirmaPerTipusDeDocumentPost(@ModelAttribute ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument = modulDeFirmaPerTipusDeDocumentForm.getModulDeFirmaPerTipusDeDocument();

    try {
      preValidate(request, modulDeFirmaPerTipusDeDocumentForm, result);
      getWebValidator().validate(modulDeFirmaPerTipusDeDocumentForm, result);
      postValidate(request,modulDeFirmaPerTipusDeDocumentForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        modulDeFirmaPerTipusDeDocument = create(request, modulDeFirmaPerTipusDeDocument);
        createMessageSuccess(request, "success.creation", modulDeFirmaPerTipusDeDocument.getID());
        modulDeFirmaPerTipusDeDocumentForm.setModulDeFirmaPerTipusDeDocument(modulDeFirmaPerTipusDeDocument);
        return getRedirectWhenCreated(request, modulDeFirmaPerTipusDeDocumentForm);
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

  @RequestMapping(value = "/view/{iD}", method = RequestMethod.GET)
  public ModelAndView veureModulDeFirmaPerTipusDeDocumentGet(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModulDeFirmaPerTipusDeDocumentGet(iD,
        request, response, true);
  }


  protected ModelAndView editAndViewModulDeFirmaPerTipusDeDocumentGet(@PathVariable("iD") java.lang.Long iD,
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
    ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument = findByPrimaryKey(request, iD);

    if (modulDeFirmaPerTipusDeDocument == null) {
      createMessageWarning(request, "error.notfound", iD);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, iD), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm = getModulDeFirmaPerTipusDeDocumentForm(modulDeFirmaPerTipusDeDocument, __isView, request, mav);
      modulDeFirmaPerTipusDeDocumentForm.setView(__isView);
      if(__isView) {
        modulDeFirmaPerTipusDeDocumentForm.setAllFieldsReadOnly(ALL_MODULDEFIRMAPERTIPUSDEDOCUMENT_FIELDS);
        modulDeFirmaPerTipusDeDocumentForm.setSaveButtonVisible(false);
        modulDeFirmaPerTipusDeDocumentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(modulDeFirmaPerTipusDeDocumentForm, request, mav);
      mav.addObject("modulDeFirmaPerTipusDeDocumentForm", modulDeFirmaPerTipusDeDocumentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ModulDeFirmaPerTipusDeDocument existent
   */
  @RequestMapping(value = "/{iD}/edit", method = RequestMethod.GET)
  public ModelAndView editarModulDeFirmaPerTipusDeDocumentGet(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModulDeFirmaPerTipusDeDocumentGet(iD,
        request, response, false);
  }



  /**
   * Editar un ModulDeFirmaPerTipusDeDocument existent
   */
  @RequestMapping(value = "/{iD}/edit", method = RequestMethod.POST)
  public String editarModulDeFirmaPerTipusDeDocumentPost(@ModelAttribute @Valid ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument = modulDeFirmaPerTipusDeDocumentForm.getModulDeFirmaPerTipusDeDocument();

    try {
      preValidate(request, modulDeFirmaPerTipusDeDocumentForm, result);
      getWebValidator().validate(modulDeFirmaPerTipusDeDocument, result);
      postValidate(request, modulDeFirmaPerTipusDeDocumentForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        modulDeFirmaPerTipusDeDocument = update(request, modulDeFirmaPerTipusDeDocument);
        createMessageSuccess(request, "success.modification", modulDeFirmaPerTipusDeDocument.getID());
        status.setComplete();
        return getRedirectWhenModified(request, modulDeFirmaPerTipusDeDocumentForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          modulDeFirmaPerTipusDeDocument.getID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, modulDeFirmaPerTipusDeDocumentForm, __e);
    }

  }


  /**
   * Eliminar un ModulDeFirmaPerTipusDeDocument existent
   */
  @RequestMapping(value = "/{iD}/delete")
  public String eliminarModulDeFirmaPerTipusDeDocument(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ModulDeFirmaPerTipusDeDocument modulDeFirmaPerTipusDeDocument = modulDeFirmaPerTipusDeDocumentEjb.findByPrimaryKey(iD);
      if (modulDeFirmaPerTipusDeDocument == null) {
        String __msg =createMessageError(request, "error.notfound", iD);
        return getRedirectWhenDelete(request, iD, new Exception(__msg));
      } else {
        delete(request, modulDeFirmaPerTipusDeDocument);
        createMessageSuccess(request, "success.deleted", iD);
        return getRedirectWhenDelete(request, iD,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", iD, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, iD, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ModulDeFirmaPerTipusDeDocumentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarModulDeFirmaPerTipusDeDocument(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __iD, Throwable e) {
    java.lang.Long iD = (java.lang.Long)__iD;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (iD == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(iD),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument";
  }

  public String getEntityNameCodePlural() {
    return "modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("modulDeFirmaPerTipusDeDocument.ID");
  }

  @InitBinder("modulDeFirmaPerTipusDeDocumentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("modulDeFirmaPerTipusDeDocumentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("ID");

  }

  public ModulDeFirmaPerTipusDeDocumentWebValidator getWebValidator() {
    return modulDeFirmaPerTipusDeDocumentWebValidator;
  }


  public void setWebValidator(ModulDeFirmaPerTipusDeDocumentWebValidator __val) {
    if (__val != null) {
      this.modulDeFirmaPerTipusDeDocumentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ModulDeFirmaPerTipusDeDocument
   */
  @RequestMapping(value = "/{iD}/cancel")
  public String cancelModulDeFirmaPerTipusDeDocument(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, iD);
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


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm, Where where)  throws I18NException {
    if (modulDeFirmaPerTipusDeDocumentForm.isHiddenField(TIPUSDOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  modulDeFirmaPerTipusDeDocumentForm.getStringOfField(TIPUSDOCUMENTID);
    Where _where = null;
    if (modulDeFirmaPerTipusDeDocumentForm.isReadOnlyField(_fieldName)) {
      _where = TipusDocumentFields.TIPUSDOCUMENTID.equal(modulDeFirmaPerTipusDeDocumentForm.getModulDeFirmaPerTipusDeDocument().getTipusDocumentID());
    }
    return getReferenceListForTipusDocumentID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaPerTipusDeDocumentFilterForm modulDeFirmaPerTipusDeDocumentFilterForm,
       List<ModulDeFirmaPerTipusDeDocument> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modulDeFirmaPerTipusDeDocumentFilterForm.isHiddenField(TIPUSDOCUMENTID)
      && !modulDeFirmaPerTipusDeDocumentFilterForm.isGroupByField(TIPUSDOCUMENTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSDOCUMENTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModulDeFirmaPerTipusDeDocument _item : list) {
        _pkList.add(_item.getTipusDocumentID());
        }
        _w = TipusDocumentFields.TIPUSDOCUMENTID.in(_pkList);
      }
    return getReferenceListForTipusDocumentID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusDocumentRefList.getReferenceList(TipusDocumentFields.TIPUSDOCUMENTID, where );
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm, Where where)  throws I18NException {
    if (modulDeFirmaPerTipusDeDocumentForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  modulDeFirmaPerTipusDeDocumentForm.getStringOfField(PLUGINID);
    Where _where = null;
    if (modulDeFirmaPerTipusDeDocumentForm.isReadOnlyField(_fieldName)) {
      _where = PluginFields.PLUGINID.equal(modulDeFirmaPerTipusDeDocumentForm.getModulDeFirmaPerTipusDeDocument().getPluginID());
    }
    return getReferenceListForPluginID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaPerTipusDeDocumentFilterForm modulDeFirmaPerTipusDeDocumentFilterForm,
       List<ModulDeFirmaPerTipusDeDocument> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modulDeFirmaPerTipusDeDocumentFilterForm.isHiddenField(PLUGINID)
      && !modulDeFirmaPerTipusDeDocumentFilterForm.isGroupByField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModulDeFirmaPerTipusDeDocument _item : list) {
        _pkList.add(_item.getPluginID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public void preValidate(HttpServletRequest request,ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ModulDeFirmaPerTipusDeDocumentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ModulDeFirmaPerTipusDeDocumentFilterForm filterForm,  List<ModulDeFirmaPerTipusDeDocument> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ModulDeFirmaPerTipusDeDocumentForm modulDeFirmaPerTipusDeDocumentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long iD, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long iD) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "modulDeFirmaPerTipusDeDocumentFormWebDB";
  }

  public String getTileList() {
    return "modulDeFirmaPerTipusDeDocumentListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "ModulDeFirmaPerTipusDeDocumentWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ModulDeFirmaPerTipusDeDocumentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long iD) throws I18NException {
    return (ModulDeFirmaPerTipusDeDocumentJPA) modulDeFirmaPerTipusDeDocumentEjb.findByPrimaryKey(iD);
  }


  public ModulDeFirmaPerTipusDeDocumentJPA create(HttpServletRequest request, ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument)
    throws Exception,I18NException, I18NValidationException {
    return (ModulDeFirmaPerTipusDeDocumentJPA) modulDeFirmaPerTipusDeDocumentEjb.create(modulDeFirmaPerTipusDeDocument);
  }


  public ModulDeFirmaPerTipusDeDocumentJPA update(HttpServletRequest request, ModulDeFirmaPerTipusDeDocumentJPA modulDeFirmaPerTipusDeDocument)
    throws Exception,I18NException, I18NValidationException {
    return (ModulDeFirmaPerTipusDeDocumentJPA) modulDeFirmaPerTipusDeDocumentEjb.update(modulDeFirmaPerTipusDeDocument);
  }


  public void delete(HttpServletRequest request, ModulDeFirmaPerTipusDeDocument modulDeFirmaPerTipusDeDocument) throws Exception,I18NException {
    modulDeFirmaPerTipusDeDocumentEjb.delete(modulDeFirmaPerTipusDeDocument);
  }

} // Final de Classe

