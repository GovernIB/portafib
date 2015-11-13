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
import es.caib.portafib.back.form.webdb.ModulDeFirmaForm;

import es.caib.portafib.back.validator.webdb.ModulDeFirmaWebValidator;

import es.caib.portafib.jpa.ModulDeFirmaJPA;
import es.caib.portafib.model.entity.ModulDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un ModulDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/modulDeFirma")
@SessionAttributes(types = { ModulDeFirmaForm.class, ModulDeFirmaFilterForm.class })
public class ModulDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<ModulDeFirma, java.lang.Long> implements ModulDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.ModulDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.ModulDeFirmaLocal modulDeFirmaEjb;

  @Autowired
  private ModulDeFirmaWebValidator modulDeFirmaWebValidator;

  @Autowired
  protected ModulDeFirmaRefList modulDeFirmaRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes ModulDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    ModulDeFirmaFilterForm ff;
    ff = (ModulDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar ModulDeFirma de forma paginada
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
    llistat(mav, request, getModulDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public ModulDeFirmaFilterForm getModulDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    ModulDeFirmaFilterForm modulDeFirmaFilterForm;
    modulDeFirmaFilterForm = (ModulDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(modulDeFirmaFilterForm == null) {
      modulDeFirmaFilterForm = new ModulDeFirmaFilterForm();
      modulDeFirmaFilterForm.setContexte(getContextWeb());
      modulDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      modulDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      modulDeFirmaFilterForm.setNou(true);
    } else {
      modulDeFirmaFilterForm.setNou(false);
    }
    modulDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return modulDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar ModulDeFirma de forma paginada
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
      @ModelAttribute ModulDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getModulDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de ModulDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<ModulDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     ModulDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<ModulDeFirma> modulDeFirma = processarLlistat(modulDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("modulDeFirmaItems", modulDeFirma);

    mav.addObject("modulDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, modulDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, modulDeFirma);

    return modulDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(ModulDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<ModulDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field descripcioCurtaID
    {
      _listSKV = getReferenceListForDescripcioCurtaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForDescripcioCurtaID(_tmp);
      if (filterForm.getGroupByFields().contains(DESCRIPCIOCURTAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DESCRIPCIOCURTAID, false);
      };
    }

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIU);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    ModulDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<ModulDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_MODULDEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    __mapping.put(DESCRIPCIOCURTAID, filterForm.getMapOfTraduccioForDescripcioCurtaID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou ModulDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearModulDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    ModulDeFirmaForm modulDeFirmaForm = getModulDeFirmaForm(null, false, request, mav);
    
    if (modulDeFirmaForm.getModulDeFirma().getNom() == null){
      es.caib.portafib.jpa.TraduccioJPA trad = new es.caib.portafib.jpa.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : modulDeFirmaForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.jpa.TraduccioMapJPA());
      }
      modulDeFirmaForm.getModulDeFirma().setNom(trad);
    }

    
    if (modulDeFirmaForm.getModulDeFirma().getDescripcioCurta() == null){
      es.caib.portafib.jpa.TraduccioJPA trad = new es.caib.portafib.jpa.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : modulDeFirmaForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.jpa.TraduccioMapJPA());
      }
      modulDeFirmaForm.getModulDeFirma().setDescripcioCurta(trad);
    }

    mav.addObject("modulDeFirmaForm" ,modulDeFirmaForm);
    fillReferencesForForm(modulDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public ModulDeFirmaForm getModulDeFirmaForm(ModulDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    ModulDeFirmaForm modulDeFirmaForm;
    if(_jpa == null) {
      modulDeFirmaForm = new ModulDeFirmaForm(new ModulDeFirmaJPA(), true);
    } else {
      modulDeFirmaForm = new ModulDeFirmaForm(_jpa, false);
      modulDeFirmaForm.setView(__isView);
    }
    modulDeFirmaForm.setContexte(getContextWeb());
    modulDeFirmaForm.setEntityNameCode(getEntityNameCode());
    modulDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    modulDeFirmaForm.setIdiomesTraduccio(getIdiomesSuportats());
    return modulDeFirmaForm;
  }

  public void fillReferencesForForm(ModulDeFirmaForm modulDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (modulDeFirmaForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, modulDeFirmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      modulDeFirmaForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }


  public List<es.caib.portafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.portafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.portafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou ModulDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearModulDeFirmaPost(@ModelAttribute ModulDeFirmaForm modulDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModulDeFirmaJPA modulDeFirma = modulDeFirmaForm.getModulDeFirma();

    try {
      preValidate(request, modulDeFirmaForm, result);
      getWebValidator().validate(modulDeFirmaForm, result);
      postValidate(request,modulDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        modulDeFirma = create(request, modulDeFirma);
        createMessageSuccess(request, "success.creation", modulDeFirma.getModulDeFirmaID());
        modulDeFirmaForm.setModulDeFirma(modulDeFirma);
        return getRedirectWhenCreated(request, modulDeFirmaForm);
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

  @RequestMapping(value = "/view/{modulDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView veureModulDeFirmaGet(@PathVariable("modulDeFirmaID") java.lang.Long modulDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModulDeFirmaGet(modulDeFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewModulDeFirmaGet(@PathVariable("modulDeFirmaID") java.lang.Long modulDeFirmaID,
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
    ModulDeFirmaJPA modulDeFirma = findByPrimaryKey(request, modulDeFirmaID);

    if (modulDeFirma == null) {
      createMessageWarning(request, "error.notfound", modulDeFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, modulDeFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      ModulDeFirmaForm modulDeFirmaForm = getModulDeFirmaForm(modulDeFirma, __isView, request, mav);
      modulDeFirmaForm.setView(__isView);
      if(__isView) {
        modulDeFirmaForm.setAllFieldsReadOnly(ALL_MODULDEFIRMA_FIELDS);
        modulDeFirmaForm.setSaveButtonVisible(false);
        modulDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(modulDeFirmaForm, request, mav);
      mav.addObject("modulDeFirmaForm", modulDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un ModulDeFirma existent
   */
  @RequestMapping(value = "/{modulDeFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarModulDeFirmaGet(@PathVariable("modulDeFirmaID") java.lang.Long modulDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewModulDeFirmaGet(modulDeFirmaID,
        request, response, false);
  }



  /**
   * Editar un ModulDeFirma existent
   */
  @RequestMapping(value = "/{modulDeFirmaID}/edit", method = RequestMethod.POST)
  public String editarModulDeFirmaPost(@ModelAttribute @Valid ModulDeFirmaForm modulDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModulDeFirmaJPA modulDeFirma = modulDeFirmaForm.getModulDeFirma();

    try {
      preValidate(request, modulDeFirmaForm, result);
      getWebValidator().validate(modulDeFirma, result);
      postValidate(request, modulDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        modulDeFirma = update(request, modulDeFirma);
        createMessageSuccess(request, "success.modification", modulDeFirma.getModulDeFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, modulDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          modulDeFirma.getModulDeFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, modulDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un ModulDeFirma existent
   */
  @RequestMapping(value = "/{modulDeFirmaID}/delete")
  public String eliminarModulDeFirma(@PathVariable("modulDeFirmaID") java.lang.Long modulDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      ModulDeFirma modulDeFirma = modulDeFirmaEjb.findByPrimaryKey(modulDeFirmaID);
      if (modulDeFirma == null) {
        String __msg =createMessageError(request, "error.notfound", modulDeFirmaID);
        return getRedirectWhenDelete(request, modulDeFirmaID, new Exception(__msg));
      } else {
        delete(request, modulDeFirma);
        createMessageSuccess(request, "success.deleted", modulDeFirmaID);
        return getRedirectWhenDelete(request, modulDeFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", modulDeFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, modulDeFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute ModulDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarModulDeFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __modulDeFirmaID, Throwable e) {
    java.lang.Long modulDeFirmaID = (java.lang.Long)__modulDeFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (modulDeFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(modulDeFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "modulDeFirma.modulDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "modulDeFirma.modulDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("modulDeFirma.modulDeFirmaID");
  }

  @InitBinder("modulDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("modulDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("modulDeFirmaID");

  }

  public ModulDeFirmaWebValidator getWebValidator() {
    return modulDeFirmaWebValidator;
  }


  public void setWebValidator(ModulDeFirmaWebValidator __val) {
    if (__val != null) {
      this.modulDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de ModulDeFirma
   */
  @RequestMapping(value = "/{modulDeFirmaID}/cancel")
  public String cancelModulDeFirma(@PathVariable("modulDeFirmaID") java.lang.Long modulDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, modulDeFirmaID);
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
       ModelAndView mav, ModulDeFirmaFilterForm modulDeFirmaFilterForm,
       List<ModulDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modulDeFirmaFilterForm.isHiddenField(NOMID)
      && !modulDeFirmaFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModulDeFirma _item : list) {
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

  public List<StringKeyValue> getReferenceListForDescripcioCurtaID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaFilterForm modulDeFirmaFilterForm,
       List<ModulDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modulDeFirmaFilterForm.isHiddenField(DESCRIPCIOCURTAID)
      && !modulDeFirmaFilterForm.isGroupByField(DESCRIPCIOCURTAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESCRIPCIOCURTAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (ModulDeFirma _item : list) {
        _pkList.add(_item.getDescripcioCurtaID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForDescripcioCurtaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDescripcioCurtaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaForm modulDeFirmaForm, Where where)  throws I18NException {
    if (modulDeFirmaForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  modulDeFirmaForm.getStringOfField(ENTITATID);
    Where _where = null;
    if (modulDeFirmaForm.isReadOnlyField(_fieldName)) {
      _where = EntitatFields.ENTITATID.equal(modulDeFirmaForm.getModulDeFirma().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, ModulDeFirmaFilterForm modulDeFirmaFilterForm,
       List<ModulDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (modulDeFirmaFilterForm.isHiddenField(ENTITATID)
      && !modulDeFirmaFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (ModulDeFirma _item : list) {
        if(_item.getEntitatID() == null) { continue; };
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  public void preValidate(HttpServletRequest request,ModulDeFirmaForm modulDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,ModulDeFirmaForm modulDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, ModulDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, ModulDeFirmaFilterForm filterForm,  List<ModulDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, ModulDeFirmaForm modulDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, ModulDeFirmaForm modulDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long modulDeFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long modulDeFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "modulDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "modulDeFirmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "ModulDeFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public ModulDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long modulDeFirmaID) throws I18NException {
    return (ModulDeFirmaJPA) modulDeFirmaEjb.findByPrimaryKey(modulDeFirmaID);
  }


  public ModulDeFirmaJPA create(HttpServletRequest request, ModulDeFirmaJPA modulDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (ModulDeFirmaJPA) modulDeFirmaEjb.create(modulDeFirma);
  }


  public ModulDeFirmaJPA update(HttpServletRequest request, ModulDeFirmaJPA modulDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (ModulDeFirmaJPA) modulDeFirmaEjb.update(modulDeFirma);
  }


  public void delete(HttpServletRequest request, ModulDeFirma modulDeFirma) throws Exception,I18NException {
    modulDeFirmaEjb.delete(modulDeFirma);
  }

} // Final de Classe

