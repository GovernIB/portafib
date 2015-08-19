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
import es.caib.portafib.back.form.webdb.FirmaForm;

import es.caib.portafib.back.validator.webdb.FirmaWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Firma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/firma")
@SessionAttributes(types = { FirmaForm.class, FirmaFilterForm.class })
public class FirmaController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<FirmaJPA, FirmaForm> implements FirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.FirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;

  @Autowired
  private FirmaWebValidator firmaWebValidator;

  @Autowired
  protected FirmaRefList firmaRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected BlocDeFirmesRefList blocDeFirmesRefList;

  // References 
  @Autowired
  protected TipusEstatDeFirmaFinalRefList tipusEstatDeFirmaFinalRefList;

  /**
   * Llistat de totes Firma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FirmaFilterForm ff;
    ff = (FirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Firma de forma paginada
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
    llistat(mav, request, getFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public FirmaFilterForm getFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FirmaFilterForm firmaFilterForm;
    firmaFilterForm = (FirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(firmaFilterForm == null) {
      firmaFilterForm = new FirmaFilterForm();
      firmaFilterForm.setContexte(getContextWeb());
      firmaFilterForm.setEntityNameCode(getEntityNameCode());
      firmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      firmaFilterForm.setNou(true);
    } else {
      firmaFilterForm.setNou(false);
    }
    firmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return firmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Firma de forma paginada
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
      @ModelAttribute FirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Firma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Firma> llistat(ModelAndView mav, HttpServletRequest request,
     FirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<Firma> firma = (List<Firma>) processarLlistat(firmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("firmaItems", firma);

    mav.addObject("firmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, firma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, firma);

    return firma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Firma> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field blocDeFirmaID
    {
      _listSKV = getReferenceListForBlocDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfBlocDeFirmesForBlocDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(BLOCDEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, BLOCDEFIRMAID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, OBLIGATORI);

    // Field tipusEstatDeFirmaFinalID
    {
      _listSKV = getReferenceListForTipusEstatDeFirmaFinalID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSESTATDEFIRMAFINALID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSESTATDEFIRMAFINALID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, MOSTRARRUBRICA);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    FirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Firma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(DESTINATARIID, filterForm.getMapOfUsuariEntitatForDestinatariID());
    __mapping.put(BLOCDEFIRMAID, filterForm.getMapOfBlocDeFirmesForBlocDeFirmaID());
    __mapping.put(TIPUSESTATDEFIRMAFINALID, filterForm.getMapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Firma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FirmaForm firmaForm = getFirmaForm(null, false, request, mav);
    mav.addObject("firmaForm" ,firmaForm);
    fillReferencesForForm(firmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FirmaForm getFirmaForm(FirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FirmaForm firmaForm;
    if(_jpa == null) {
      firmaForm = new FirmaForm(new FirmaJPA(), true);
    } else {
      firmaForm = new FirmaForm(_jpa, false);
      firmaForm.setView(__isView);
    }
    firmaForm.setContexte(getContextWeb());
    firmaForm.setEntityNameCode(getEntityNameCode());
    firmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return firmaForm;
  }

  public void fillReferencesForForm(FirmaForm firmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (firmaForm.getListOfUsuariEntitatForDestinatariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForDestinatariID(request, mav, firmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      firmaForm.setListOfUsuariEntitatForDestinatariID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (firmaForm.getListOfBlocDeFirmesForBlocDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForBlocDeFirmaID(request, mav, firmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      firmaForm.setListOfBlocDeFirmesForBlocDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (firmaForm.getListOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusEstatDeFirmaFinalID(request, mav, firmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      firmaForm.setListOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Firma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFirmaPost(@ModelAttribute FirmaForm firmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FirmaJPA firma = firmaForm.getFirma();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, firma, firmaForm); // FILE
      preValidate(request, firmaForm, result);
      getWebValidator().validate(firmaForm, result);
      postValidate(request,firmaForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        firma = create(request, firma);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", firma.getFirmaID());
        firmaForm.setFirma(firma);
        return getRedirectWhenCreated(request, firmaForm);
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

  @RequestMapping(value = "/view/{firmaID}", method = RequestMethod.GET)
  public ModelAndView veureFirmaGet(@PathVariable("firmaID") java.lang.Long firmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFirmaGet(firmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewFirmaGet(@PathVariable("firmaID") java.lang.Long firmaID,
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
    FirmaJPA firma = findByPrimaryKey(request, firmaID);

    if (firma == null) {
      createMessageWarning(request, "error.notfound", firmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, firmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FirmaForm firmaForm = getFirmaForm(firma, __isView, request, mav);
      firmaForm.setView(__isView);
      if(__isView) {
        firmaForm.setAllFieldsReadOnly(ALL_FIRMA_FIELDS);
        firmaForm.setSaveButtonVisible(false);
        firmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(firmaForm, request, mav);
      mav.addObject("firmaForm", firmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Firma existent
   */
  @RequestMapping(value = "/{firmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFirmaGet(@PathVariable("firmaID") java.lang.Long firmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFirmaGet(firmaID,
        request, response, false);
  }



  /**
   * Editar un Firma existent
   */
  @RequestMapping(value = "/{firmaID}/edit", method = RequestMethod.POST)
  public String editarFirmaPost(@ModelAttribute @Valid FirmaForm firmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FirmaJPA firma = firmaForm.getFirma();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, firma, firmaForm); // FILE
      preValidate(request, firmaForm, result);
      getWebValidator().validate(firma, result);
      postValidate(request, firmaForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        firma = update(request, firma);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", firma.getFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, firmaForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          firma.getFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, firmaForm, __e);
    }

  }


  /**
   * Eliminar un Firma existent
   */
  @RequestMapping(value = "/{firmaID}/delete")
  public String eliminarFirma(@PathVariable("firmaID") java.lang.Long firmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Firma firma = firmaEjb.findByPrimaryKey(firmaID);
      if (firma == null) {
        String __msg =createMessageError(request, "error.notfound", firmaID);
        return getRedirectWhenDelete(request, firmaID, new Exception(__msg));
      } else {
        delete(request, firma);
        createMessageSuccess(request, "success.deleted", firmaID);
        return getRedirectWhenDelete(request, firmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", firmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, firmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __firmaID, Throwable e) {
    java.lang.Long firmaID = (java.lang.Long)__firmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (firmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(firmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "firma.firma";
  }

  public String getEntityNameCodePlural() {
    return "firma.firma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("firma.firmaID");
  }

  @InitBinder("firmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("firmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("firmaID");

  }

  public FirmaWebValidator getWebValidator() {
    return firmaWebValidator;
  }


  public void setWebValidator(FirmaWebValidator __val) {
    if (__val != null) {
      this.firmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Firma
   */
  @RequestMapping(value = "/{firmaID}/cancel")
  public String cancelFirma(@PathVariable("firmaID") java.lang.Long firmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, firmaID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, FirmaJPA firma,
      FirmaForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerFirmatID(), form.isFitxerFirmatIDDelete(),
        form.isNou()? null : firma.getFitxerFirmat());
    firma.setFitxerFirmat(f);
    if (f != null) { 
      firma.setFitxerFirmatID(f.getFitxerID());
    } else {
      firma.setFitxerFirmatID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(FirmaJPA firma) {
    deleteFile(firma.getFitxerFirmatID());
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
       ModelAndView mav, FirmaForm firmaForm, Where where)  throws I18NException {
    if (firmaForm.isHiddenField(DESTINATARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  firmaForm.getStringOfField(DESTINATARIID);
    Where _where = null;
    if (firmaForm.isReadOnlyField(_fieldName)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(firmaForm.getFirma().getDestinatariID());
    }
    return getReferenceListForDestinatariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForDestinatariID(HttpServletRequest request,
       ModelAndView mav, FirmaFilterForm firmaFilterForm,
       List<Firma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (firmaFilterForm.isHiddenField(DESTINATARIID)
      && !firmaFilterForm.isGroupByField(DESTINATARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESTINATARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (Firma _item : list) {
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


  public List<StringKeyValue> getReferenceListForBlocDeFirmaID(HttpServletRequest request,
       ModelAndView mav, FirmaForm firmaForm, Where where)  throws I18NException {
    if (firmaForm.isHiddenField(BLOCDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  firmaForm.getStringOfField(BLOCDEFIRMAID);
    Where _where = null;
    if (firmaForm.isReadOnlyField(_fieldName)) {
      _where = BlocDeFirmesFields.BLOCDEFIRMESID.equal(firmaForm.getFirma().getBlocDeFirmaID());
    }
    return getReferenceListForBlocDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForBlocDeFirmaID(HttpServletRequest request,
       ModelAndView mav, FirmaFilterForm firmaFilterForm,
       List<Firma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (firmaFilterForm.isHiddenField(BLOCDEFIRMAID)
      && !firmaFilterForm.isGroupByField(BLOCDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(BLOCDEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Firma _item : list) {
        _pkList.add(_item.getBlocDeFirmaID());
        }
        _w = BlocDeFirmesFields.BLOCDEFIRMESID.in(_pkList);
      }
    return getReferenceListForBlocDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForBlocDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return blocDeFirmesRefList.getReferenceList(BlocDeFirmesFields.BLOCDEFIRMESID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaFinalID(HttpServletRequest request,
       ModelAndView mav, FirmaForm firmaForm, Where where)  throws I18NException {
    if (firmaForm.isHiddenField(TIPUSESTATDEFIRMAFINALID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  firmaForm.getStringOfField(TIPUSESTATDEFIRMAFINALID);
    Where _where = null;
    if (firmaForm.isReadOnlyField(_fieldName)) {
      _where = TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID.equal(firmaForm.getFirma().getTipusEstatDeFirmaFinalID());
    }
    return getReferenceListForTipusEstatDeFirmaFinalID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaFinalID(HttpServletRequest request,
       ModelAndView mav, FirmaFilterForm firmaFilterForm,
       List<Firma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (firmaFilterForm.isHiddenField(TIPUSESTATDEFIRMAFINALID)
      && !firmaFilterForm.isGroupByField(TIPUSESTATDEFIRMAFINALID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSESTATDEFIRMAFINALID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Firma _item : list) {
        if(_item.getTipusEstatDeFirmaFinalID() == null) { continue; };
        _pkList.add(_item.getTipusEstatDeFirmaFinalID());
        }
        _w = TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID.in(_pkList);
      }
    return getReferenceListForTipusEstatDeFirmaFinalID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaFinalID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusEstatDeFirmaFinalRefList.getReferenceList(TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID, where );
  }


  public void preValidate(HttpServletRequest request,FirmaForm firmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FirmaForm firmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FirmaFilterForm filterForm,  List<Firma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FirmaForm firmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FirmaForm firmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long firmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long firmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "firmaFormWebDB";
  }

  public String getTileList() {
    return "firmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "FirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long firmaID) throws I18NException {
    return (FirmaJPA) firmaEjb.findByPrimaryKey(firmaID);
  }


  public FirmaJPA create(HttpServletRequest request, FirmaJPA firma)
    throws Exception,I18NException, I18NValidationException {
    return (FirmaJPA) firmaEjb.create(firma);
  }


  public FirmaJPA update(HttpServletRequest request, FirmaJPA firma)
    throws Exception,I18NException, I18NValidationException {
    return (FirmaJPA) firmaEjb.update(firma);
  }


  public void delete(HttpServletRequest request, Firma firma) throws Exception,I18NException {
    firmaEjb.delete(firma);
  }

} // Final de Classe

