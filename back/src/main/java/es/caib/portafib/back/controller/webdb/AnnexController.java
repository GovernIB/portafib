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
import es.caib.portafib.back.form.webdb.AnnexForm;

import es.caib.portafib.back.validator.webdb.AnnexWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.jpa.AnnexJPA;
import es.caib.portafib.model.entity.Annex;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Annex
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/annex")
@SessionAttributes(types = { AnnexForm.class, AnnexFilterForm.class })
public class AnnexController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<Annex, java.lang.Long, AnnexForm> implements AnnexFields {

  @EJB(mappedName = es.caib.portafib.ejb.AnnexLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.AnnexLocal annexEjb;

  @Autowired
  private AnnexWebValidator annexWebValidator;

  @Autowired
  protected AnnexRefList annexRefList;

  // References 
  @Autowired
  protected PeticioDeFirmaRefList peticioDeFirmaRefList;

  /**
   * Llistat de totes Annex
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AnnexFilterForm ff;
    ff = (AnnexFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Annex de forma paginada
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
    llistat(mav, request, getAnnexFilterForm(pagina, mav, request));
    return mav;
  }

  public AnnexFilterForm getAnnexFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AnnexFilterForm annexFilterForm;
    annexFilterForm = (AnnexFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(annexFilterForm == null) {
      annexFilterForm = new AnnexFilterForm();
      annexFilterForm.setContexte(getContextWeb());
      annexFilterForm.setEntityNameCode(getEntityNameCode());
      annexFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      annexFilterForm.setNou(true);
    } else {
      annexFilterForm.setNou(false);
    }
    annexFilterForm.setPage(pagina == null ? 1 : pagina);
    return annexFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Annex de forma paginada
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
      @ModelAttribute AnnexFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAnnexFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Annex de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Annex> llistat(ModelAndView mav, HttpServletRequest request,
     AnnexFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Annex> annex = processarLlistat(annexEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("annexItems", annex);

    mav.addObject("annexFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, annex, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, annex);

    return annex;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AnnexFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Annex> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field peticioDeFirmaID
    {
      _listSKV = getReferenceListForPeticioDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPeticioDeFirmaForPeticioDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(PETICIODEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PETICIODEFIRMAID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ADJUNTAR);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, FIRMAR);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AnnexFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Annex> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ANNEX_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PETICIODEFIRMAID, filterForm.getMapOfPeticioDeFirmaForPeticioDeFirmaID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Annex
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAnnexGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AnnexForm annexForm = getAnnexForm(null, false, request, mav);
    mav.addObject("annexForm" ,annexForm);
    fillReferencesForForm(annexForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AnnexForm getAnnexForm(AnnexJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AnnexForm annexForm;
    if(_jpa == null) {
      annexForm = new AnnexForm(new AnnexJPA(), true);
    } else {
      annexForm = new AnnexForm(_jpa, false);
      annexForm.setView(__isView);
    }
    annexForm.setContexte(getContextWeb());
    annexForm.setEntityNameCode(getEntityNameCode());
    annexForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return annexForm;
  }

  public void fillReferencesForForm(AnnexForm annexForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (annexForm.getListOfPeticioDeFirmaForPeticioDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPeticioDeFirmaID(request, mav, annexForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      annexForm.setListOfPeticioDeFirmaForPeticioDeFirmaID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Annex
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAnnexPost(@ModelAttribute AnnexForm annexForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AnnexJPA annex = annexForm.getAnnex();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, annex, annexForm); // FILE
      preValidate(request, annexForm, result);
      getWebValidator().validate(annexForm, result);
      postValidate(request,annexForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        annex = create(request, annex);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", annex.getAnnexID());
        annexForm.setAnnex(annex);
        return getRedirectWhenCreated(request, annexForm);
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

  @RequestMapping(value = "/view/{annexID}", method = RequestMethod.GET)
  public ModelAndView veureAnnexGet(@PathVariable("annexID") java.lang.Long annexID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAnnexGet(annexID,
        request, response, true);
  }


  protected ModelAndView editAndViewAnnexGet(@PathVariable("annexID") java.lang.Long annexID,
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
    AnnexJPA annex = findByPrimaryKey(request, annexID);

    if (annex == null) {
      createMessageWarning(request, "error.notfound", annexID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, annexID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AnnexForm annexForm = getAnnexForm(annex, __isView, request, mav);
      annexForm.setView(__isView);
      if(__isView) {
        annexForm.setAllFieldsReadOnly(ALL_ANNEX_FIELDS);
        annexForm.setSaveButtonVisible(false);
        annexForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(annexForm, request, mav);
      mav.addObject("annexForm", annexForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Annex existent
   */
  @RequestMapping(value = "/{annexID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAnnexGet(@PathVariable("annexID") java.lang.Long annexID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAnnexGet(annexID,
        request, response, false);
  }



  /**
   * Editar un Annex existent
   */
  @RequestMapping(value = "/{annexID}/edit", method = RequestMethod.POST)
  public String editarAnnexPost(@ModelAttribute @Valid AnnexForm annexForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AnnexJPA annex = annexForm.getAnnex();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, annex, annexForm); // FILE
      preValidate(request, annexForm, result);
      getWebValidator().validate(annex, result);
      postValidate(request, annexForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        annex = update(request, annex);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", annex.getAnnexID());
        status.setComplete();
        return getRedirectWhenModified(request, annexForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          annex.getAnnexID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, annexForm, __e);
    }

  }


  /**
   * Eliminar un Annex existent
   */
  @RequestMapping(value = "/{annexID}/delete")
  public String eliminarAnnex(@PathVariable("annexID") java.lang.Long annexID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Annex annex = annexEjb.findByPrimaryKey(annexID);
      if (annex == null) {
        String __msg =createMessageError(request, "error.notfound", annexID);
        return getRedirectWhenDelete(request, annexID, new Exception(__msg));
      } else {
        delete(request, annex);
        createMessageSuccess(request, "success.deleted", annexID);
        return getRedirectWhenDelete(request, annexID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", annexID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, annexID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AnnexFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAnnex(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __annexID, Throwable e) {
    java.lang.Long annexID = (java.lang.Long)__annexID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (annexID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(annexID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "annex.annex";
  }

  public String getEntityNameCodePlural() {
    return "annex.annex.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("annex.annexID");
  }

  @InitBinder("annexFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("annexForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("annexID");

  }

  public AnnexWebValidator getWebValidator() {
    return annexWebValidator;
  }


  public void setWebValidator(AnnexWebValidator __val) {
    if (__val != null) {
      this.annexWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Annex
   */
  @RequestMapping(value = "/{annexID}/cancel")
  public String cancelAnnex(@PathVariable("annexID") java.lang.Long annexID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, annexID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Annex annex,
      AnnexForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : annex.getFitxer());
    ((AnnexJPA)annex).setFitxer(f);
    if (f != null) { 
      annex.setFitxerID(f.getFitxerID());
    } else {
      annex.setFitxerID(0);
    }

  }

  // FILE
  @Override
  public void deleteFiles(Annex annex) {
    deleteFile(annex.getFitxerID());
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


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, AnnexForm annexForm, Where where)  throws I18NException {
    if (annexForm.isHiddenField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (annexForm.isReadOnlyField(PETICIODEFIRMAID)) {
      _where = PeticioDeFirmaFields.PETICIODEFIRMAID.equal(annexForm.getAnnex().getPeticioDeFirmaID());
    }
    return getReferenceListForPeticioDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, AnnexFilterForm annexFilterForm,
       List<Annex> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (annexFilterForm.isHiddenField(PETICIODEFIRMAID)
      && !annexFilterForm.isGroupByField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PETICIODEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Annex _item : list) {
        _pkList.add(_item.getPeticioDeFirmaID());
        }
        _w = PeticioDeFirmaFields.PETICIODEFIRMAID.in(_pkList);
      }
    return getReferenceListForPeticioDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return peticioDeFirmaRefList.getReferenceList(PeticioDeFirmaFields.PETICIODEFIRMAID, where );
  }


  public void preValidate(HttpServletRequest request,AnnexForm annexForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AnnexForm annexForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AnnexFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AnnexFilterForm filterForm,  List<Annex> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AnnexForm annexForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AnnexForm annexForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long annexID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long annexID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "annexFormWebDB";
  }

  public String getTileList() {
    return "annexListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AnnexWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AnnexJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long annexID) throws I18NException {
    return (AnnexJPA) annexEjb.findByPrimaryKey(annexID);
  }


  public AnnexJPA create(HttpServletRequest request, AnnexJPA annex)
    throws Exception,I18NException, I18NValidationException {
    return (AnnexJPA) annexEjb.create(annex);
  }


  public AnnexJPA update(HttpServletRequest request, AnnexJPA annex)
    throws Exception,I18NException, I18NValidationException {
    return (AnnexJPA) annexEjb.update(annex);
  }


  public void delete(HttpServletRequest request, Annex annex) throws Exception,I18NException {
    annexEjb.delete(annex);
  }

} // Final de Classe

