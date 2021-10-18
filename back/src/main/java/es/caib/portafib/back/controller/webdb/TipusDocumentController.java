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
import es.caib.portafib.back.form.webdb.TipusDocumentForm;

import es.caib.portafib.back.validator.webdb.TipusDocumentWebValidator;

import es.caib.portafib.persistence.TipusDocumentJPA;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un TipusDocument
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/tipusDocument")
@SessionAttributes(types = { TipusDocumentForm.class, TipusDocumentFilterForm.class })
public class TipusDocumentController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<TipusDocument, java.lang.Long> implements TipusDocumentFields {

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;

  @Autowired
  private TipusDocumentWebValidator tipusDocumentWebValidator;

  @Autowired
  protected TipusDocumentRefList tipusDocumentRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  /**
   * Llistat de totes TipusDocument
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TipusDocumentFilterForm ff;
    ff = (TipusDocumentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar TipusDocument de forma paginada
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
    llistat(mav, request, getTipusDocumentFilterForm(pagina, mav, request));
    return mav;
  }

  public TipusDocumentFilterForm getTipusDocumentFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TipusDocumentFilterForm tipusDocumentFilterForm;
    tipusDocumentFilterForm = (TipusDocumentFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(tipusDocumentFilterForm == null) {
      tipusDocumentFilterForm = new TipusDocumentFilterForm();
      tipusDocumentFilterForm.setContexte(getContextWeb());
      tipusDocumentFilterForm.setEntityNameCode(getEntityNameCode());
      tipusDocumentFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      tipusDocumentFilterForm.setNou(true);
    } else {
      tipusDocumentFilterForm.setNou(false);
    }
    tipusDocumentFilterForm.setPage(pagina == null ? 1 : pagina);
    return tipusDocumentFilterForm;
  }

  /**
   * Segona i següent peticions per llistar TipusDocument de forma paginada
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
      @ModelAttribute TipusDocumentFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTipusDocumentFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de TipusDocument de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<TipusDocument> llistat(ModelAndView mav, HttpServletRequest request,
     TipusDocumentFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<TipusDocument> tipusDocument = processarLlistat(tipusDocumentEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("tipusDocumentItems", tipusDocument);

    mav.addObject("tipusDocumentFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, tipusDocument, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, tipusDocument);

    return tipusDocument;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TipusDocumentFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<TipusDocument> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field nomID
    {
      _listSKV = getReferenceListForNomID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForNomID(_tmp);
      if (filterForm.getGroupByFields().contains(NOMID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOMID, false);
      };
    }

    // Field tipusDocumentBaseID
    {
      _listSKV = getReferenceListForTipusDocumentBaseID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipusDocumentBaseID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSDOCUMENTBASEID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSDOCUMENTBASEID, false);
      };
    }

    // Field usuariAplicacioID
    {
      _listSKV = getReferenceListForUsuariAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioForUsuariAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TipusDocumentFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<TipusDocument> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TIPUSDOCUMENT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    __mapping.put(TIPUSDOCUMENTBASEID, filterForm.getMapOfValuesForTipusDocumentBaseID());
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou TipusDocument
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTipusDocumentGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TipusDocumentForm tipusDocumentForm = getTipusDocumentForm(null, false, request, mav);
    
    if (tipusDocumentForm.getTipusDocument().getNom() == null){
      es.caib.portafib.persistence.TraduccioJPA trad = new es.caib.portafib.persistence.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : tipusDocumentForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.persistence.TraduccioMapJPA());
      }
      tipusDocumentForm.getTipusDocument().setNom(trad);
    }

    mav.addObject("tipusDocumentForm" ,tipusDocumentForm);
    fillReferencesForForm(tipusDocumentForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TipusDocumentForm getTipusDocumentForm(TipusDocumentJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TipusDocumentForm tipusDocumentForm;
    if(_jpa == null) {
      tipusDocumentForm = new TipusDocumentForm(new TipusDocumentJPA(), true);
    } else {
      tipusDocumentForm = new TipusDocumentForm(_jpa, false);
      tipusDocumentForm.setView(__isView);
    }
    tipusDocumentForm.setContexte(getContextWeb());
    tipusDocumentForm.setEntityNameCode(getEntityNameCode());
    tipusDocumentForm.setEntityNameCodePlural(getEntityNameCodePlural());
    tipusDocumentForm.setIdiomesTraduccio(getIdiomesSuportats());
    return tipusDocumentForm;
  }

  public void fillReferencesForForm(TipusDocumentForm tipusDocumentForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (tipusDocumentForm.getListOfValuesForTipusDocumentBaseID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusDocumentBaseID(request, mav, tipusDocumentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tipusDocumentForm.setListOfValuesForTipusDocumentBaseID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (tipusDocumentForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, tipusDocumentForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      tipusDocumentForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    
  }


  public List<es.caib.portafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.portafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.portafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou TipusDocument
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTipusDocumentPost(@ModelAttribute TipusDocumentForm tipusDocumentForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TipusDocumentJPA tipusDocument = tipusDocumentForm.getTipusDocument();

    try {
      preValidate(request, tipusDocumentForm, result);
      getWebValidator().validate(tipusDocumentForm, result);
      postValidate(request,tipusDocumentForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tipusDocument = create(request, tipusDocument);
        createMessageSuccess(request, "success.creation", tipusDocument.getTipusDocumentID());
        tipusDocumentForm.setTipusDocument(tipusDocument);
        return getRedirectWhenCreated(request, tipusDocumentForm);
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

  @RequestMapping(value = "/view/{tipusDocumentID}", method = RequestMethod.GET)
  public ModelAndView veureTipusDocumentGet(@PathVariable("tipusDocumentID") java.lang.Long tipusDocumentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusDocumentGet(tipusDocumentID,
        request, response, true);
  }


  protected ModelAndView editAndViewTipusDocumentGet(@PathVariable("tipusDocumentID") java.lang.Long tipusDocumentID,
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
    TipusDocumentJPA tipusDocument = findByPrimaryKey(request, tipusDocumentID);

    if (tipusDocument == null) {
      createMessageWarning(request, "error.notfound", tipusDocumentID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, tipusDocumentID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TipusDocumentForm tipusDocumentForm = getTipusDocumentForm(tipusDocument, __isView, request, mav);
      tipusDocumentForm.setView(__isView);
      if(__isView) {
        tipusDocumentForm.setAllFieldsReadOnly(ALL_TIPUSDOCUMENT_FIELDS);
        tipusDocumentForm.setSaveButtonVisible(false);
        tipusDocumentForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(tipusDocumentForm, request, mav);
      mav.addObject("tipusDocumentForm", tipusDocumentForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un TipusDocument existent
   */
  @RequestMapping(value = "/{tipusDocumentID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTipusDocumentGet(@PathVariable("tipusDocumentID") java.lang.Long tipusDocumentID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTipusDocumentGet(tipusDocumentID,
        request, response, false);
  }



  /**
   * Editar un TipusDocument existent
   */
  @RequestMapping(value = "/{tipusDocumentID}/edit", method = RequestMethod.POST)
  public String editarTipusDocumentPost(@ModelAttribute TipusDocumentForm tipusDocumentForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TipusDocumentJPA tipusDocument = tipusDocumentForm.getTipusDocument();

    try {
      preValidate(request, tipusDocumentForm, result);
      getWebValidator().validate(tipusDocumentForm, result);
      postValidate(request, tipusDocumentForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        tipusDocument = update(request, tipusDocument);
        createMessageSuccess(request, "success.modification", tipusDocument.getTipusDocumentID());
        status.setComplete();
        return getRedirectWhenModified(request, tipusDocumentForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          tipusDocument.getTipusDocumentID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, tipusDocumentForm, __e);
    }

  }


  /**
   * Eliminar un TipusDocument existent
   */
  @RequestMapping(value = "/{tipusDocumentID}/delete")
  public String eliminarTipusDocument(@PathVariable("tipusDocumentID") java.lang.Long tipusDocumentID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      TipusDocument tipusDocument = tipusDocumentEjb.findByPrimaryKey(tipusDocumentID);
      if (tipusDocument == null) {
        String __msg =createMessageError(request, "error.notfound", tipusDocumentID);
        return getRedirectWhenDelete(request, tipusDocumentID, new Exception(__msg));
      } else {
        delete(request, tipusDocument);
        createMessageSuccess(request, "success.deleted", tipusDocumentID);
        return getRedirectWhenDelete(request, tipusDocumentID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", tipusDocumentID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, tipusDocumentID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TipusDocumentFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTipusDocument(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __tipusDocumentID, Throwable e) {
    java.lang.Long tipusDocumentID = (java.lang.Long)__tipusDocumentID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (tipusDocumentID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(tipusDocumentID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "tipusDocument.tipusDocument";
  }

  public String getEntityNameCodePlural() {
    return "tipusDocument.tipusDocument.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("tipusDocument.tipusDocumentID");
  }

  @InitBinder("tipusDocumentFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("tipusDocumentForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public TipusDocumentWebValidator getWebValidator() {
    return tipusDocumentWebValidator;
  }


  public void setWebValidator(TipusDocumentWebValidator __val) {
    if (__val != null) {
      this.tipusDocumentWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de TipusDocument
   */
  @RequestMapping(value = "/{tipusDocumentID}/cancel")
  public String cancelTipusDocument(@PathVariable("tipusDocumentID") java.lang.Long tipusDocumentID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, tipusDocumentID);
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

  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentFilterForm tipusDocumentFilterForm,
       List<TipusDocument> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tipusDocumentFilterForm.isHiddenField(NOMID)
      && !tipusDocumentFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (TipusDocument _item : list) {
        _pkList.add(_item.getNomID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForNomID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentBaseID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentForm tipusDocumentForm, Where where)  throws I18NException {
    if (tipusDocumentForm.isHiddenField(TIPUSDOCUMENTBASEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipusDocumentBaseID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentBaseID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentFilterForm tipusDocumentFilterForm,
       List<TipusDocument> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tipusDocumentFilterForm.isHiddenField(TIPUSDOCUMENTBASEID)
      && !tipusDocumentFilterForm.isGroupByField(TIPUSDOCUMENTBASEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipusDocumentBaseID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusDocumentBaseID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    __tmp.add(new StringKeyValue("6" , "6"));
    __tmp.add(new StringKeyValue("7" , "7"));
    __tmp.add(new StringKeyValue("8" , "8"));
    __tmp.add(new StringKeyValue("9" , "9"));
    __tmp.add(new StringKeyValue("10" , "10"));
    __tmp.add(new StringKeyValue("11" , "11"));
    __tmp.add(new StringKeyValue("12" , "12"));
    __tmp.add(new StringKeyValue("13" , "13"));
    __tmp.add(new StringKeyValue("14" , "14"));
    __tmp.add(new StringKeyValue("15" , "15"));
    __tmp.add(new StringKeyValue("16" , "16"));
    __tmp.add(new StringKeyValue("17" , "17"));
    __tmp.add(new StringKeyValue("18" , "18"));
    __tmp.add(new StringKeyValue("19" , "19"));
    __tmp.add(new StringKeyValue("20" , "20"));
    __tmp.add(new StringKeyValue("69" , "69"));
    __tmp.add(new StringKeyValue("68" , "68"));
    __tmp.add(new StringKeyValue("67" , "67"));
    __tmp.add(new StringKeyValue("66" , "66"));
    __tmp.add(new StringKeyValue("65" , "65"));
    __tmp.add(new StringKeyValue("64" , "64"));
    __tmp.add(new StringKeyValue("63" , "63"));
    __tmp.add(new StringKeyValue("62" , "62"));
    __tmp.add(new StringKeyValue("61" , "61"));
    __tmp.add(new StringKeyValue("60" , "60"));
    __tmp.add(new StringKeyValue("59" , "59"));
    __tmp.add(new StringKeyValue("58" , "58"));
    __tmp.add(new StringKeyValue("57" , "57"));
    __tmp.add(new StringKeyValue("56" , "56"));
    __tmp.add(new StringKeyValue("55" , "55"));
    __tmp.add(new StringKeyValue("54" , "54"));
    __tmp.add(new StringKeyValue("53" , "53"));
    __tmp.add(new StringKeyValue("52" , "52"));
    __tmp.add(new StringKeyValue("51" , "51"));
    __tmp.add(new StringKeyValue("99" , "99"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentForm tipusDocumentForm, Where where)  throws I18NException {
    if (tipusDocumentForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (tipusDocumentForm.isReadOnlyField(USUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(tipusDocumentForm.getTipusDocument().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, TipusDocumentFilterForm tipusDocumentFilterForm,
       List<TipusDocument> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (tipusDocumentFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !tipusDocumentFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (TipusDocument _item : list) {
        if(_item.getUsuariAplicacioID() == null) { continue; };
        _pkList.add(_item.getUsuariAplicacioID());
        }
        _w = UsuariAplicacioFields.USUARIAPLICACIOID.in(_pkList);
      }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioRefList.getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID, where );
  }


  public void preValidate(HttpServletRequest request,TipusDocumentForm tipusDocumentForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TipusDocumentForm tipusDocumentForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TipusDocumentFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TipusDocumentFilterForm filterForm,  List<TipusDocument> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TipusDocumentForm tipusDocumentForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TipusDocumentForm tipusDocumentForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long tipusDocumentID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long tipusDocumentID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "tipusDocumentFormWebDB";
  }

  public String getTileList() {
    return "tipusDocumentListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "TipusDocumentWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TipusDocumentJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long tipusDocumentID) throws I18NException {
    return (TipusDocumentJPA) tipusDocumentEjb.findByPrimaryKey(tipusDocumentID);
  }


  public TipusDocumentJPA create(HttpServletRequest request, TipusDocumentJPA tipusDocument)
    throws Exception,I18NException, I18NValidationException {
    return (TipusDocumentJPA) tipusDocumentEjb.create(tipusDocument);
  }


  public TipusDocumentJPA update(HttpServletRequest request, TipusDocumentJPA tipusDocument)
    throws Exception,I18NException, I18NValidationException {
    return (TipusDocumentJPA) tipusDocumentEjb.update(tipusDocument);
  }


  public void delete(HttpServletRequest request, TipusDocument tipusDocument) throws Exception,I18NException {
    tipusDocumentEjb.delete(tipusDocument);
  }

} // Final de Classe

