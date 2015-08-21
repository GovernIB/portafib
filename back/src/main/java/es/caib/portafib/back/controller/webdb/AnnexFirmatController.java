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
import es.caib.portafib.back.form.webdb.AnnexFirmatForm;

import es.caib.portafib.back.validator.webdb.AnnexFirmatWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.jpa.AnnexFirmatJPA;
import es.caib.portafib.model.entity.AnnexFirmat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un AnnexFirmat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/annexFirmat")
@SessionAttributes(types = { AnnexFirmatForm.class, AnnexFirmatFilterForm.class })
public class AnnexFirmatController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<AnnexFirmat, java.lang.Long, AnnexFirmatForm> implements AnnexFirmatFields {

  @EJB(mappedName = es.caib.portafib.ejb.AnnexFirmatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.AnnexFirmatLocal annexFirmatEjb;

  @Autowired
  private AnnexFirmatWebValidator annexFirmatWebValidator;

  @Autowired
  protected AnnexFirmatRefList annexFirmatRefList;

  // References 
  @Autowired
  protected AnnexRefList annexRefList;

  // References 
  @Autowired
  protected FirmaRefList firmaRefList;

  /**
   * Llistat de totes AnnexFirmat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AnnexFirmatFilterForm ff;
    ff = (AnnexFirmatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar AnnexFirmat de forma paginada
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
    llistat(mav, request, getAnnexFirmatFilterForm(pagina, mav, request));
    return mav;
  }

  public AnnexFirmatFilterForm getAnnexFirmatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AnnexFirmatFilterForm annexFirmatFilterForm;
    annexFirmatFilterForm = (AnnexFirmatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(annexFirmatFilterForm == null) {
      annexFirmatFilterForm = new AnnexFirmatFilterForm();
      annexFirmatFilterForm.setContexte(getContextWeb());
      annexFirmatFilterForm.setEntityNameCode(getEntityNameCode());
      annexFirmatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      annexFirmatFilterForm.setNou(true);
    } else {
      annexFirmatFilterForm.setNou(false);
    }
    annexFirmatFilterForm.setPage(pagina == null ? 1 : pagina);
    return annexFirmatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar AnnexFirmat de forma paginada
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
      @ModelAttribute AnnexFirmatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAnnexFirmatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de AnnexFirmat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<AnnexFirmat> llistat(ModelAndView mav, HttpServletRequest request,
     AnnexFirmatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<AnnexFirmat> annexFirmat = processarLlistat(annexFirmatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("annexFirmatItems", annexFirmat);

    mav.addObject("annexFirmatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, annexFirmat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, annexFirmat);

    return annexFirmat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AnnexFirmatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<AnnexFirmat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field annexID
    {
      _listSKV = getReferenceListForAnnexID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfAnnexForAnnexID(_tmp);
      if (filterForm.getGroupByFields().contains(ANNEXID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ANNEXID, false);
      };
    }

    // Field firmaID
    {
      _listSKV = getReferenceListForFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFirmaForFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(FIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FIRMAID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AnnexFirmatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<AnnexFirmat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ANNEXFIRMAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ANNEXID, filterForm.getMapOfAnnexForAnnexID());
    __mapping.put(FIRMAID, filterForm.getMapOfFirmaForFirmaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou AnnexFirmat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAnnexFirmatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AnnexFirmatForm annexFirmatForm = getAnnexFirmatForm(null, false, request, mav);
    mav.addObject("annexFirmatForm" ,annexFirmatForm);
    fillReferencesForForm(annexFirmatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AnnexFirmatForm getAnnexFirmatForm(AnnexFirmatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AnnexFirmatForm annexFirmatForm;
    if(_jpa == null) {
      annexFirmatForm = new AnnexFirmatForm(new AnnexFirmatJPA(), true);
    } else {
      annexFirmatForm = new AnnexFirmatForm(_jpa, false);
      annexFirmatForm.setView(__isView);
    }
    annexFirmatForm.setContexte(getContextWeb());
    annexFirmatForm.setEntityNameCode(getEntityNameCode());
    annexFirmatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return annexFirmatForm;
  }

  public void fillReferencesForForm(AnnexFirmatForm annexFirmatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (annexFirmatForm.getListOfAnnexForAnnexID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAnnexID(request, mav, annexFirmatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      annexFirmatForm.setListOfAnnexForAnnexID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (annexFirmatForm.getListOfFirmaForFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFirmaID(request, mav, annexFirmatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      annexFirmatForm.setListOfFirmaForFirmaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou AnnexFirmat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAnnexFirmatPost(@ModelAttribute AnnexFirmatForm annexFirmatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AnnexFirmatJPA annexFirmat = annexFirmatForm.getAnnexFirmat();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, annexFirmat, annexFirmatForm); // FILE
      preValidate(request, annexFirmatForm, result);
      getWebValidator().validate(annexFirmatForm, result);
      postValidate(request,annexFirmatForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        annexFirmat = create(request, annexFirmat);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", annexFirmat.getAnnexfirmatID());
        annexFirmatForm.setAnnexFirmat(annexFirmat);
        return getRedirectWhenCreated(request, annexFirmatForm);
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

  @RequestMapping(value = "/view/{annexfirmatID}", method = RequestMethod.GET)
  public ModelAndView veureAnnexFirmatGet(@PathVariable("annexfirmatID") java.lang.Long annexfirmatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAnnexFirmatGet(annexfirmatID,
        request, response, true);
  }


  protected ModelAndView editAndViewAnnexFirmatGet(@PathVariable("annexfirmatID") java.lang.Long annexfirmatID,
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
    AnnexFirmatJPA annexFirmat = findByPrimaryKey(request, annexfirmatID);

    if (annexFirmat == null) {
      createMessageWarning(request, "error.notfound", annexfirmatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, annexfirmatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AnnexFirmatForm annexFirmatForm = getAnnexFirmatForm(annexFirmat, __isView, request, mav);
      annexFirmatForm.setView(__isView);
      if(__isView) {
        annexFirmatForm.setAllFieldsReadOnly(ALL_ANNEXFIRMAT_FIELDS);
        annexFirmatForm.setSaveButtonVisible(false);
        annexFirmatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(annexFirmatForm, request, mav);
      mav.addObject("annexFirmatForm", annexFirmatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un AnnexFirmat existent
   */
  @RequestMapping(value = "/{annexfirmatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAnnexFirmatGet(@PathVariable("annexfirmatID") java.lang.Long annexfirmatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAnnexFirmatGet(annexfirmatID,
        request, response, false);
  }



  /**
   * Editar un AnnexFirmat existent
   */
  @RequestMapping(value = "/{annexfirmatID}/edit", method = RequestMethod.POST)
  public String editarAnnexFirmatPost(@ModelAttribute @Valid AnnexFirmatForm annexFirmatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AnnexFirmatJPA annexFirmat = annexFirmatForm.getAnnexFirmat();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, annexFirmat, annexFirmatForm); // FILE
      preValidate(request, annexFirmatForm, result);
      getWebValidator().validate(annexFirmat, result);
      postValidate(request, annexFirmatForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        annexFirmat = update(request, annexFirmat);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", annexFirmat.getAnnexfirmatID());
        status.setComplete();
        return getRedirectWhenModified(request, annexFirmatForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          annexFirmat.getAnnexfirmatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, annexFirmatForm, __e);
    }

  }


  /**
   * Eliminar un AnnexFirmat existent
   */
  @RequestMapping(value = "/{annexfirmatID}/delete")
  public String eliminarAnnexFirmat(@PathVariable("annexfirmatID") java.lang.Long annexfirmatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      AnnexFirmat annexFirmat = annexFirmatEjb.findByPrimaryKey(annexfirmatID);
      if (annexFirmat == null) {
        String __msg =createMessageError(request, "error.notfound", annexfirmatID);
        return getRedirectWhenDelete(request, annexfirmatID, new Exception(__msg));
      } else {
        delete(request, annexFirmat);
        createMessageSuccess(request, "success.deleted", annexfirmatID);
        return getRedirectWhenDelete(request, annexfirmatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", annexfirmatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, annexfirmatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AnnexFirmatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAnnexFirmat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __annexfirmatID, Throwable e) {
    java.lang.Long annexfirmatID = (java.lang.Long)__annexfirmatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (annexfirmatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(annexfirmatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "annexFirmat.annexFirmat";
  }

  public String getEntityNameCodePlural() {
    return "annexFirmat.annexFirmat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("annexFirmat.annexfirmatID");
  }

  @InitBinder("annexFirmatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("annexFirmatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("annexfirmatID");

  }

  public AnnexFirmatWebValidator getWebValidator() {
    return annexFirmatWebValidator;
  }


  public void setWebValidator(AnnexFirmatWebValidator __val) {
    if (__val != null) {
      this.annexFirmatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de AnnexFirmat
   */
  @RequestMapping(value = "/{annexfirmatID}/cancel")
  public String cancelAnnexFirmat(@PathVariable("annexfirmatID") java.lang.Long annexfirmatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, annexfirmatID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, AnnexFirmat annexFirmat,
      AnnexFirmatForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : annexFirmat.getFitxer());
    ((AnnexFirmatJPA)annexFirmat).setFitxer(f);
    if (f != null) { 
      annexFirmat.setFitxerID(f.getFitxerID());
    } else {
      annexFirmat.setFitxerID(0);
    }

  }

  // FILE
  @Override
  public void deleteFiles(AnnexFirmat annexFirmat) {
    deleteFile(annexFirmat.getFitxerID());
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


  public List<StringKeyValue> getReferenceListForAnnexID(HttpServletRequest request,
       ModelAndView mav, AnnexFirmatForm annexFirmatForm, Where where)  throws I18NException {
    if (annexFirmatForm.isHiddenField(ANNEXID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  annexFirmatForm.getStringOfField(ANNEXID);
    Where _where = null;
    if (annexFirmatForm.isReadOnlyField(_fieldName)) {
      _where = AnnexFields.ANNEXID.equal(annexFirmatForm.getAnnexFirmat().getAnnexID());
    }
    return getReferenceListForAnnexID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForAnnexID(HttpServletRequest request,
       ModelAndView mav, AnnexFirmatFilterForm annexFirmatFilterForm,
       List<AnnexFirmat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (annexFirmatFilterForm.isHiddenField(ANNEXID)
      && !annexFirmatFilterForm.isGroupByField(ANNEXID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ANNEXID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (AnnexFirmat _item : list) {
        _pkList.add(_item.getAnnexID());
        }
        _w = AnnexFields.ANNEXID.in(_pkList);
      }
    return getReferenceListForAnnexID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAnnexID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return annexRefList.getReferenceList(AnnexFields.ANNEXID, where );
  }


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, AnnexFirmatForm annexFirmatForm, Where where)  throws I18NException {
    if (annexFirmatForm.isHiddenField(FIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  annexFirmatForm.getStringOfField(FIRMAID);
    Where _where = null;
    if (annexFirmatForm.isReadOnlyField(_fieldName)) {
      _where = FirmaFields.FIRMAID.equal(annexFirmatForm.getAnnexFirmat().getFirmaID());
    }
    return getReferenceListForFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, AnnexFirmatFilterForm annexFirmatFilterForm,
       List<AnnexFirmat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (annexFirmatFilterForm.isHiddenField(FIRMAID)
      && !annexFirmatFilterForm.isGroupByField(FIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (AnnexFirmat _item : list) {
        _pkList.add(_item.getFirmaID());
        }
        _w = FirmaFields.FIRMAID.in(_pkList);
      }
    return getReferenceListForFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return firmaRefList.getReferenceList(FirmaFields.FIRMAID, where );
  }


  public void preValidate(HttpServletRequest request,AnnexFirmatForm annexFirmatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AnnexFirmatForm annexFirmatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AnnexFirmatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AnnexFirmatFilterForm filterForm,  List<AnnexFirmat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AnnexFirmatForm annexFirmatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AnnexFirmatForm annexFirmatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long annexfirmatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long annexfirmatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "annexFirmatFormWebDB";
  }

  public String getTileList() {
    return "annexFirmatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AnnexFirmatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AnnexFirmatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long annexfirmatID) throws I18NException {
    return (AnnexFirmatJPA) annexFirmatEjb.findByPrimaryKey(annexfirmatID);
  }


  public AnnexFirmatJPA create(HttpServletRequest request, AnnexFirmatJPA annexFirmat)
    throws Exception,I18NException, I18NValidationException {
    return (AnnexFirmatJPA) annexFirmatEjb.create(annexFirmat);
  }


  public AnnexFirmatJPA update(HttpServletRequest request, AnnexFirmatJPA annexFirmat)
    throws Exception,I18NException, I18NValidationException {
    return (AnnexFirmatJPA) annexFirmatEjb.update(annexFirmat);
  }


  public void delete(HttpServletRequest request, AnnexFirmat annexFirmat) throws Exception,I18NException {
    annexFirmatEjb.delete(annexFirmat);
  }

} // Final de Classe

