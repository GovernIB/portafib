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

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;

import es.caib.portafib.back.validator.webdb.ColaboracioDelegacioWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.persistence.ColaboracioDelegacioJPA;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un ColaboracioDelegacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/colaboracioDelegacio")
@SessionAttributes(types = { ColaboracioDelegacioForm.class, ColaboracioDelegacioFilterForm.class })
public class ColaboracioDelegacioController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<ColaboracioDelegacio, java.lang.Long, ColaboracioDelegacioForm> implements ColaboracioDelegacioFields {

  @EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioService colaboracioDelegacioEjb;

  @Autowired
  private ColaboracioDelegacioWebValidator colaboracioDelegacioWebValidator;

  @Autowired
  protected ColaboracioDelegacioRefList colaboracioDelegacioRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes ColaboracioDelegacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ColaboracioDelegacioFilterForm ff;
    ff = (ColaboracioDelegacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ColaboracioDelegacio de forma paginada
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
    llistat(mav, request, getColaboracioDelegacioFilterForm(pagina, mav, request));
    return mav;
  }

  public ColaboracioDelegacioFilterForm getColaboracioDelegacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ColaboracioDelegacioFilterForm colaboracioDelegacioFilterForm;
    colaboracioDelegacioFilterForm = (ColaboracioDelegacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(colaboracioDelegacioFilterForm == null) {
      colaboracioDelegacioFilterForm = new ColaboracioDelegacioFilterForm();
      colaboracioDelegacioFilterForm.setContexte(getContextWeb());
      colaboracioDelegacioFilterForm.setEntityNameCode(getEntityNameCode());
      colaboracioDelegacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      colaboracioDelegacioFilterForm.setNou(true);
    } else {
      colaboracioDelegacioFilterForm.setNou(false);
    }
    colaboracioDelegacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return colaboracioDelegacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ColaboracioDelegacio de forma paginada
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
      @ModelAttribute ColaboracioDelegacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getColaboracioDelegacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ColaboracioDelegacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ColaboracioDelegacio> llistat(ModelAndView mav, HttpServletRequest request,
     ColaboracioDelegacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ColaboracioDelegacio> colaboracioDelegacio = processarLlistat(colaboracioDelegacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("colaboracioDelegacioItems", colaboracioDelegacio);

    mav.addObject("colaboracioDelegacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, colaboracioDelegacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, colaboracioDelegacio);

    return colaboracioDelegacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ColaboracioDelegacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ColaboracioDelegacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field destinatariID
    {
      _listSKV = getReferenceListForDestinatariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForDestinatariID(_tmp);
      if (filterForm.getGroupByFields().contains(DESTINATARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DESTINATARIID, false);
      };
    }

    // Field colaboradorDelegatID
    {
      _listSKV = getReferenceListForColaboradorDelegatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForColaboradorDelegatID(_tmp);
      if (filterForm.getGroupByFields().contains(COLABORADORDELEGATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, COLABORADORDELEGATID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ESDELEGAT);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIVA);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, REVISOR);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ColaboracioDelegacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ColaboracioDelegacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_COLABORACIODELEGACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(DESTINATARIID, filterForm.getMapOfUsuariEntitatForDestinatariID());
    __mapping.put(COLABORADORDELEGATID, filterForm.getMapOfUsuariEntitatForColaboradorDelegatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ColaboracioDelegacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearColaboracioDelegacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ColaboracioDelegacioForm colaboracioDelegacioForm = getColaboracioDelegacioForm(null, false, request, mav);
    mav.addObject("colaboracioDelegacioForm" ,colaboracioDelegacioForm);
    fillReferencesForForm(colaboracioDelegacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ColaboracioDelegacioForm getColaboracioDelegacioForm(ColaboracioDelegacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ColaboracioDelegacioForm colaboracioDelegacioForm;
    if(_jpa == null) {
      colaboracioDelegacioForm = new ColaboracioDelegacioForm(new ColaboracioDelegacioJPA(), true);
    } else {
      colaboracioDelegacioForm = new ColaboracioDelegacioForm(_jpa, false);
      colaboracioDelegacioForm.setView(__isView);
    }
    colaboracioDelegacioForm.setContexte(getContextWeb());
    colaboracioDelegacioForm.setEntityNameCode(getEntityNameCode());
    colaboracioDelegacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return colaboracioDelegacioForm;
  }

  public void fillReferencesForForm(ColaboracioDelegacioForm colaboracioDelegacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (colaboracioDelegacioForm.getListOfUsuariEntitatForDestinatariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForDestinatariID(request, mav, colaboracioDelegacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      colaboracioDelegacioForm.setListOfUsuariEntitatForDestinatariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (colaboracioDelegacioForm.getListOfUsuariEntitatForColaboradorDelegatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForColaboradorDelegatID(request, mav, colaboracioDelegacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      colaboracioDelegacioForm.setListOfUsuariEntitatForColaboradorDelegatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou ColaboracioDelegacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearColaboracioDelegacioPost(@ModelAttribute ColaboracioDelegacioForm colaboracioDelegacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ColaboracioDelegacioJPA colaboracioDelegacio = colaboracioDelegacioForm.getColaboracioDelegacio();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, colaboracioDelegacio, colaboracioDelegacioForm); // FILE
      preValidate(request, colaboracioDelegacioForm, result);
      getWebValidator().validate(colaboracioDelegacioForm, result);
      postValidate(request,colaboracioDelegacioForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        colaboracioDelegacio = create(request, colaboracioDelegacio);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", colaboracioDelegacio.getColaboracioDelegacioID());
        colaboracioDelegacioForm.setColaboracioDelegacio(colaboracioDelegacio);
        return getRedirectWhenCreated(request, colaboracioDelegacioForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{colaboracioDelegacioID}", method = RequestMethod.GET)
  public ModelAndView veureColaboracioDelegacioGet(@PathVariable("colaboracioDelegacioID") java.lang.Long colaboracioDelegacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewColaboracioDelegacioGet(colaboracioDelegacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewColaboracioDelegacioGet(@PathVariable("colaboracioDelegacioID") java.lang.Long colaboracioDelegacioID,
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
    ColaboracioDelegacioJPA colaboracioDelegacio = findByPrimaryKey(request, colaboracioDelegacioID);

    if (colaboracioDelegacio == null) {
      createMessageWarning(request, "error.notfound", colaboracioDelegacioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, colaboracioDelegacioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ColaboracioDelegacioForm colaboracioDelegacioForm = getColaboracioDelegacioForm(colaboracioDelegacio, __isView, request, mav);
      colaboracioDelegacioForm.setView(__isView);
      if(__isView) {
        colaboracioDelegacioForm.setAllFieldsReadOnly(ALL_COLABORACIODELEGACIO_FIELDS);
        colaboracioDelegacioForm.setSaveButtonVisible(false);
        colaboracioDelegacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(colaboracioDelegacioForm, request, mav);
      mav.addObject("colaboracioDelegacioForm", colaboracioDelegacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ColaboracioDelegacio existent
   */
  @RequestMapping(value = "/{colaboracioDelegacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarColaboracioDelegacioGet(@PathVariable("colaboracioDelegacioID") java.lang.Long colaboracioDelegacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewColaboracioDelegacioGet(colaboracioDelegacioID,
        request, response, false);
  }



  /**
   * Editar un ColaboracioDelegacio existent
   */
  @RequestMapping(value = "/{colaboracioDelegacioID}/edit", method = RequestMethod.POST)
  public String editarColaboracioDelegacioPost(@ModelAttribute ColaboracioDelegacioForm colaboracioDelegacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ColaboracioDelegacioJPA colaboracioDelegacio = colaboracioDelegacioForm.getColaboracioDelegacio();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, colaboracioDelegacio, colaboracioDelegacioForm); // FILE
      preValidate(request, colaboracioDelegacioForm, result);
      getWebValidator().validate(colaboracioDelegacioForm, result);
      postValidate(request, colaboracioDelegacioForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        colaboracioDelegacio = update(request, colaboracioDelegacio);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", colaboracioDelegacio.getColaboracioDelegacioID());
        status.setComplete();
        return getRedirectWhenModified(request, colaboracioDelegacioForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          colaboracioDelegacio.getColaboracioDelegacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, colaboracioDelegacioForm, __e);
    }

  }


  /**
   * Eliminar un ColaboracioDelegacio existent
   */
  @RequestMapping(value = "/{colaboracioDelegacioID}/delete")
  public String eliminarColaboracioDelegacio(@PathVariable("colaboracioDelegacioID") java.lang.Long colaboracioDelegacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ColaboracioDelegacio colaboracioDelegacio = this.findByPrimaryKey(request, colaboracioDelegacioID);
      if (colaboracioDelegacio == null) {
        String __msg = createMessageError(request, "error.notfound", colaboracioDelegacioID);
        return getRedirectWhenDelete(request, colaboracioDelegacioID, new Exception(__msg));
      } else {
        delete(request, colaboracioDelegacio);
        createMessageSuccess(request, "success.deleted", colaboracioDelegacioID);
        return getRedirectWhenDelete(request, colaboracioDelegacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", colaboracioDelegacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, colaboracioDelegacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ColaboracioDelegacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarColaboracioDelegacio(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __colaboracioDelegacioID, Throwable e) {
    java.lang.Long colaboracioDelegacioID = (java.lang.Long)__colaboracioDelegacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (colaboracioDelegacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(colaboracioDelegacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "colaboracioDelegacio.colaboracioDelegacio";
  }

  public String getEntityNameCodePlural() {
    return "colaboracioDelegacio.colaboracioDelegacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("colaboracioDelegacio.colaboracioDelegacioID");
  }

  @InitBinder("colaboracioDelegacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("colaboracioDelegacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "colaboracioDelegacio.colaboracioDelegacioID");
  }

  public ColaboracioDelegacioWebValidator getWebValidator() {
    return colaboracioDelegacioWebValidator;
  }


  public void setWebValidator(ColaboracioDelegacioWebValidator __val) {
    if (__val != null) {
      this.colaboracioDelegacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ColaboracioDelegacio
   */
  @RequestMapping(value = "/{colaboracioDelegacioID}/cancel")
  public String cancelColaboracioDelegacio(@PathVariable("colaboracioDelegacioID") java.lang.Long colaboracioDelegacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, colaboracioDelegacioID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de ColaboracioDelegacio
   */
  @RequestMapping(value = "/cancel")
  public String cancelColaboracioDelegacio(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, ColaboracioDelegacio colaboracioDelegacio,
      ColaboracioDelegacioForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerAutoritzacioID(), form.isFitxerAutoritzacioIDDelete(),
        form.isNou()? null : colaboracioDelegacio.getFitxerAutoritzacio());
    ((ColaboracioDelegacioJPA)colaboracioDelegacio).setFitxerAutoritzacio(f);
    if (f != null) { 
      colaboracioDelegacio.setFitxerAutoritzacioID(f.getFitxerID());
    } else {
      colaboracioDelegacio.setFitxerAutoritzacioID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(ColaboracioDelegacio colaboracioDelegacio) {
    deleteFile(colaboracioDelegacio.getFitxerAutoritzacioID());
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


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, ColaboracioDelegacioForm colaboracioDelegacioForm, Where where)  throws I18NException {
    if (colaboracioDelegacioForm.isHiddenField(DESTINATARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (colaboracioDelegacioForm.isReadOnlyField(DESTINATARIID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(colaboracioDelegacioForm.getColaboracioDelegacio().getDestinatariID());
    }
    return getReferenceListForDestinatariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, ColaboracioDelegacioFilterForm colaboracioDelegacioFilterForm,
       List<ColaboracioDelegacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (colaboracioDelegacioFilterForm.isHiddenField(DESTINATARIID)
       && !colaboracioDelegacioFilterForm.isGroupByField(DESTINATARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESTINATARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (ColaboracioDelegacio _item : list) {
        _pkList.add(_item.getDestinatariID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForDestinatariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForColaboradorDelegatID(HttpServletRequest request,
       ModelAndView mav, ColaboracioDelegacioForm colaboracioDelegacioForm, Where where)  throws I18NException {
    if (colaboracioDelegacioForm.isHiddenField(COLABORADORDELEGATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (colaboracioDelegacioForm.isReadOnlyField(COLABORADORDELEGATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(colaboracioDelegacioForm.getColaboracioDelegacio().getColaboradorDelegatID());
    }
    return getReferenceListForColaboradorDelegatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForColaboradorDelegatID(HttpServletRequest request,
       ModelAndView mav, ColaboracioDelegacioFilterForm colaboracioDelegacioFilterForm,
       List<ColaboracioDelegacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (colaboracioDelegacioFilterForm.isHiddenField(COLABORADORDELEGATID)
       && !colaboracioDelegacioFilterForm.isGroupByField(COLABORADORDELEGATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(COLABORADORDELEGATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (ColaboracioDelegacio _item : list) {
        _pkList.add(_item.getColaboradorDelegatID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForColaboradorDelegatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForColaboradorDelegatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,ColaboracioDelegacioForm colaboracioDelegacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ColaboracioDelegacioForm colaboracioDelegacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ColaboracioDelegacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ColaboracioDelegacioFilterForm filterForm,  List<ColaboracioDelegacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ColaboracioDelegacioForm colaboracioDelegacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ColaboracioDelegacioForm colaboracioDelegacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long colaboracioDelegacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long colaboracioDelegacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "colaboracioDelegacioFormWebDB";
  }

  public String getTileList() {
    return "colaboracioDelegacioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "ColaboracioDelegacio_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ColaboracioDelegacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long colaboracioDelegacioID) throws I18NException {
    return (ColaboracioDelegacioJPA) colaboracioDelegacioEjb.findByPrimaryKey(colaboracioDelegacioID);
  }


  public ColaboracioDelegacioJPA create(HttpServletRequest request, ColaboracioDelegacioJPA colaboracioDelegacio)
    throws I18NException, I18NValidationException {
    return (ColaboracioDelegacioJPA) colaboracioDelegacioEjb.create(colaboracioDelegacio);
  }


  public ColaboracioDelegacioJPA update(HttpServletRequest request, ColaboracioDelegacioJPA colaboracioDelegacio)
    throws I18NException, I18NValidationException {
    return (ColaboracioDelegacioJPA) colaboracioDelegacioEjb.update(colaboracioDelegacio);
  }


  public void delete(HttpServletRequest request, ColaboracioDelegacio colaboracioDelegacio) throws I18NException {
    colaboracioDelegacioEjb.delete(colaboracioDelegacio);
  }

} // Final de Classe

