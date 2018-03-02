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
import es.caib.portafib.back.form.webdb.EntitatForm;

import es.caib.portafib.back.validator.webdb.EntitatWebValidator;

import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.jpa.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Entitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatController
    extends es.caib.portafib.back.controller.PortaFIBFilesBaseController<Entitat, java.lang.String, EntitatForm> implements EntitatFields {

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @Autowired
  private EntitatWebValidator entitatWebValidator;

  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected AlgorismeDeFirmaRefList algorismeDeFirmaRefList;

  // References 
  @Autowired
  protected CustodiaInfoRefList custodiaInfoRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes Entitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EntitatFilterForm ff;
    ff = (EntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Entitat de forma paginada
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
    llistat(mav, request, getEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EntitatFilterForm entitatFilterForm;
    entitatFilterForm = (EntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(entitatFilterForm == null) {
      entitatFilterForm = new EntitatFilterForm();
      entitatFilterForm.setContexte(getContextWeb());
      entitatFilterForm.setEntityNameCode(getEntityNameCode());
      entitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      entitatFilterForm.setNou(true);
    } else {
      entitatFilterForm.setNou(false);
    }
    entitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return entitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Entitat de forma paginada
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
      @ModelAttribute EntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Entitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Entitat> llistat(ModelAndView mav, HttpServletRequest request,
     EntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Entitat> entitat = processarLlistat(entitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("entitatItems", entitat);

    mav.addObject("entitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, entitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, entitat);

    return entitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Entitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIVA);

    // Field usuariAplicacioID
    {
      _listSKV = getReferenceListForUsuariAplicacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioForUsuariAplicacioID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOID, false);
      };
    }

    // Field motiuDelegacioID
    {
      _listSKV = getReferenceListForMotiuDelegacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForMotiuDelegacioID(_tmp);
      if (filterForm.getGroupByFields().contains(MOTIUDELEGACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MOTIUDELEGACIOID, false);
      };
    }

    // Field firmatPerFormatID
    {
      _listSKV = getReferenceListForFirmatPerFormatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForFirmatPerFormatID(_tmp);
      if (filterForm.getGroupByFields().contains(FIRMATPERFORMATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FIRMATPERFORMATID, false);
      };
    }

    // Field algorismeDeFirmaID
    {
      _listSKV = getReferenceListForAlgorismeDeFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfAlgorismeDeFirmaForAlgorismeDeFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(ALGORISMEDEFIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ALGORISMEDEFIRMAID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, COMPROVARNIFFIRMA);

    // Field custodiaInfoID
    {
      _listSKV = getReferenceListForCustodiaInfoID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfCustodiaInfoForCustodiaInfoID(_tmp);
      if (filterForm.getGroupByFields().contains(CUSTODIAINFOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CUSTODIAINFOID, false);
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

    // Field segellDeTempsViaWeb
    {
      _listSKV = getReferenceListForSegellDeTempsViaWeb(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForSegellDeTempsViaWeb(_tmp);
      if (filterForm.getGroupByFields().contains(SEGELLDETEMPSVIAWEB)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SEGELLDETEMPSVIAWEB, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Entitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    __mapping.put(MOTIUDELEGACIOID, filterForm.getMapOfTraduccioForMotiuDelegacioID());
    __mapping.put(FIRMATPERFORMATID, filterForm.getMapOfTraduccioForFirmatPerFormatID());
    __mapping.put(ALGORISMEDEFIRMAID, filterForm.getMapOfAlgorismeDeFirmaForAlgorismeDeFirmaID());
    __mapping.put(CUSTODIAINFOID, filterForm.getMapOfCustodiaInfoForCustodiaInfoID());
    __mapping.put(PLUGINID, filterForm.getMapOfPluginForPluginID());
    __mapping.put(SEGELLDETEMPSVIAWEB, filterForm.getMapOfValuesForSegellDeTempsViaWeb());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Entitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EntitatForm entitatForm = getEntitatForm(null, false, request, mav);
    
    if (entitatForm.getEntitat().getMotiuDelegacio() == null){
      es.caib.portafib.jpa.TraduccioJPA trad = new es.caib.portafib.jpa.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : entitatForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.jpa.TraduccioMapJPA());
      }
      entitatForm.getEntitat().setMotiuDelegacio(trad);
    }

    
    if (entitatForm.getEntitat().getFirmatPerFormat() == null){
      es.caib.portafib.jpa.TraduccioJPA trad = new es.caib.portafib.jpa.TraduccioJPA();
      for (es.caib.portafib.model.entity.Idioma idioma : entitatForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.portafib.jpa.TraduccioMapJPA());
      }
      entitatForm.getEntitat().setFirmatPerFormat(trad);
    }

    mav.addObject("entitatForm" ,entitatForm);
    fillReferencesForForm(entitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EntitatForm getEntitatForm(EntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EntitatForm entitatForm;
    if(_jpa == null) {
      entitatForm = new EntitatForm(new EntitatJPA(), true);
    } else {
      entitatForm = new EntitatForm(_jpa, false);
      entitatForm.setView(__isView);
    }
    entitatForm.setContexte(getContextWeb());
    entitatForm.setEntityNameCode(getEntityNameCode());
    entitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    entitatForm.setIdiomesTraduccio(getIdiomesSuportats());
    return entitatForm;
  }

  public void fillReferencesForForm(EntitatForm entitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (entitatForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, entitatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      entitatForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (entitatForm.getListOfAlgorismeDeFirmaForAlgorismeDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForAlgorismeDeFirmaID(request, mav, entitatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      entitatForm.setListOfAlgorismeDeFirmaForAlgorismeDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (entitatForm.getListOfCustodiaInfoForCustodiaInfoID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCustodiaInfoID(request, mav, entitatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      entitatForm.setListOfCustodiaInfoForCustodiaInfoID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (entitatForm.getListOfPluginForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, entitatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      entitatForm.setListOfPluginForPluginID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (entitatForm.getListOfValuesForSegellDeTempsViaWeb() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSegellDeTempsViaWeb(request, mav, entitatForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      entitatForm.setListOfValuesForSegellDeTempsViaWeb(_listSKV);
    }
    
  }


  public List<es.caib.portafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.portafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.portafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Entitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEntitatPost(@ModelAttribute EntitatForm entitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EntitatJPA entitat = entitatForm.getEntitat();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, entitat, entitatForm); // FILE
      preValidate(request, entitatForm, result);
      getWebValidator().validate(entitatForm, result);
      postValidate(request,entitatForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        entitat = create(request, entitat);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", entitat.getEntitatID());
        entitatForm.setEntitat(entitat);
        return getRedirectWhenCreated(request, entitatForm);
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

  @RequestMapping(value = "/view/{entitatID}", method = RequestMethod.GET)
  public ModelAndView veureEntitatGet(@PathVariable("entitatID") java.lang.String entitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntitatGet(entitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewEntitatGet(@PathVariable("entitatID") java.lang.String entitatID,
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
    EntitatJPA entitat = findByPrimaryKey(request, entitatID);

    if (entitat == null) {
      createMessageWarning(request, "error.notfound", entitatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, entitatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EntitatForm entitatForm = getEntitatForm(entitat, __isView, request, mav);
      entitatForm.setView(__isView);
      if(__isView) {
        entitatForm.setAllFieldsReadOnly(ALL_ENTITAT_FIELDS);
        entitatForm.setSaveButtonVisible(false);
        entitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(entitatForm, request, mav);
      mav.addObject("entitatForm", entitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Entitat existent
   */
  @RequestMapping(value = "/{entitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEntitatGet(@PathVariable("entitatID") java.lang.String entitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEntitatGet(entitatID,
        request, response, false);
  }



  /**
   * Editar un Entitat existent
   */
  @RequestMapping(value = "/{entitatID}/edit", method = RequestMethod.POST)
  public String editarEntitatPost(@ModelAttribute @Valid EntitatForm entitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EntitatJPA entitat = entitatForm.getEntitat();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, entitat, entitatForm); // FILE
      preValidate(request, entitatForm, result);
      getWebValidator().validate(entitat, result);
      postValidate(request, entitatForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        return getTileForm();
      } else {
        entitat = update(request, entitat);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", entitat.getEntitatID());
        status.setComplete();
        return getRedirectWhenModified(request, entitatForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          entitat.getEntitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, entitatForm, __e);
    }

  }


  /**
   * Eliminar un Entitat existent
   */
  @RequestMapping(value = "/{entitatID}/delete")
  public String eliminarEntitat(@PathVariable("entitatID") java.lang.String entitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Entitat entitat = entitatEjb.findByPrimaryKey(entitatID);
      if (entitat == null) {
        String __msg =createMessageError(request, "error.notfound", entitatID);
        return getRedirectWhenDelete(request, entitatID, new Exception(__msg));
      } else {
        delete(request, entitat);
        createMessageSuccess(request, "success.deleted", entitatID);
        return getRedirectWhenDelete(request, entitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", entitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, entitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __entitatID, Throwable e) {
    java.lang.String entitatID = (java.lang.String)__entitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (entitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(entitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "entitat.entitat";
  }

  public String getEntityNameCodePlural() {
    return "entitat.entitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("entitat.entitatID");
  }

  @InitBinder("entitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("entitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public EntitatWebValidator getWebValidator() {
    return entitatWebValidator;
  }


  public void setWebValidator(EntitatWebValidator __val) {
    if (__val != null) {
      this.entitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Entitat
   */
  @RequestMapping(value = "/{entitatID}/cancel")
  public String cancelEntitat(@PathVariable("entitatID") java.lang.String entitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, entitatID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Entitat entitat,
      EntitatForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFaviconID(), form.isFaviconIDDelete(),
        form.isNou()? null : entitat.getFavicon());
    ((EntitatJPA)entitat).setFavicon(f);
    if (f != null) { 
      entitat.setFaviconID(f.getFitxerID());
    } else {
      entitat.setFaviconID(null);
    }

    f = (FitxerJPA)afm.preProcessFile(form.getLogoWebID(), form.isLogoWebIDDelete(),
        form.isNou()? null : entitat.getLogoWeb());
    ((EntitatJPA)entitat).setLogoWeb(f);
    if (f != null) { 
      entitat.setLogoWebID(f.getFitxerID());
    } else {
      entitat.setLogoWebID(null);
    }

    f = (FitxerJPA)afm.preProcessFile(form.getLogoWebPeuID(), form.isLogoWebPeuIDDelete(),
        form.isNou()? null : entitat.getLogoWebPeu());
    ((EntitatJPA)entitat).setLogoWebPeu(f);
    if (f != null) { 
      entitat.setLogoWebPeuID(f.getFitxerID());
    } else {
      entitat.setLogoWebPeuID(null);
    }

    f = (FitxerJPA)afm.preProcessFile(form.getLogoSegellID(), form.isLogoSegellIDDelete(),
        form.isNou()? null : entitat.getLogoSegell());
    ((EntitatJPA)entitat).setLogoSegell(f);
    if (f != null) { 
      entitat.setLogoSegellID(f.getFitxerID());
    } else {
      entitat.setLogoSegellID(null);
    }

    f = (FitxerJPA)afm.preProcessFile(form.getPdfAutoritzacioDelegacioID(), form.isPdfAutoritzacioDelegacioIDDelete(),
        form.isNou()? null : entitat.getPdfAutoritzacioDelegacio());
    ((EntitatJPA)entitat).setPdfAutoritzacioDelegacio(f);
    if (f != null) { 
      entitat.setPdfAutoritzacioDelegacioID(f.getFitxerID());
    } else {
      entitat.setPdfAutoritzacioDelegacioID(null);
    }

  }

  // FILE
  @Override
  public void deleteFiles(Entitat entitat) {
    deleteFile(entitat.getFaviconID());
    deleteFile(entitat.getLogoWebID());
    deleteFile(entitat.getLogoWebPeuID());
    deleteFile(entitat.getLogoSegellID());
    deleteFile(entitat.getPdfAutoritzacioDelegacioID());
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


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    if (entitatForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entitatForm.isReadOnlyField(USUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(entitatForm.getEntitat().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !entitatFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (Entitat _item : list) {
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

  public List<StringKeyValue> getReferenceListForMotiuDelegacioID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(MOTIUDELEGACIOID)
      && !entitatFilterForm.isGroupByField(MOTIUDELEGACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(MOTIUDELEGACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Entitat _item : list) {
        if(_item.getMotiuDelegacioID() == null) { continue; };
        _pkList.add(_item.getMotiuDelegacioID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForMotiuDelegacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForMotiuDelegacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForFirmatPerFormatID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(FIRMATPERFORMATID)
      && !entitatFilterForm.isGroupByField(FIRMATPERFORMATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FIRMATPERFORMATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Entitat _item : list) {
        if(_item.getFirmatPerFormatID() == null) { continue; };
        _pkList.add(_item.getFirmatPerFormatID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForFirmatPerFormatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFirmatPerFormatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    if (entitatForm.isHiddenField(ALGORISMEDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entitatForm.isReadOnlyField(ALGORISMEDEFIRMAID)) {
      _where = AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID.equal(entitatForm.getEntitat().getAlgorismeDeFirmaID());
    }
    return getReferenceListForAlgorismeDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(ALGORISMEDEFIRMAID)
      && !entitatFilterForm.isGroupByField(ALGORISMEDEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ALGORISMEDEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Integer> _pkList = new java.util.HashSet<java.lang.Integer>();
      for (Entitat _item : list) {
        _pkList.add(_item.getAlgorismeDeFirmaID());
        }
        _w = AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID.in(_pkList);
      }
    return getReferenceListForAlgorismeDeFirmaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return algorismeDeFirmaRefList.getReferenceList(AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID, where );
  }


  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
       ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    if (entitatForm.isHiddenField(CUSTODIAINFOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entitatForm.isReadOnlyField(CUSTODIAINFOID)) {
      _where = CustodiaInfoFields.CUSTODIAINFOID.equal(entitatForm.getEntitat().getCustodiaInfoID());
    }
    return getReferenceListForCustodiaInfoID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(CUSTODIAINFOID)
      && !entitatFilterForm.isGroupByField(CUSTODIAINFOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CUSTODIAINFOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Entitat _item : list) {
        if(_item.getCustodiaInfoID() == null) { continue; };
        _pkList.add(_item.getCustodiaInfoID());
        }
        _w = CustodiaInfoFields.CUSTODIAINFOID.in(_pkList);
      }
    return getReferenceListForCustodiaInfoID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return custodiaInfoRefList.getReferenceList(CustodiaInfoFields.CUSTODIAINFOID, where );
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    if (entitatForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (entitatForm.isReadOnlyField(PLUGINID)) {
      _where = PluginFields.PLUGINID.equal(entitatForm.getEntitat().getPluginID());
    }
    return getReferenceListForPluginID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(PLUGINID)
      && !entitatFilterForm.isGroupByField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Entitat _item : list) {
        if(_item.getPluginID() == null) { continue; };
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


  public List<StringKeyValue> getReferenceListForSegellDeTempsViaWeb(HttpServletRequest request,
       ModelAndView mav, EntitatForm entitatForm, Where where)  throws I18NException {
    if (entitatForm.isHiddenField(SEGELLDETEMPSVIAWEB)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForSegellDeTempsViaWeb(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForSegellDeTempsViaWeb(HttpServletRequest request,
       ModelAndView mav, EntitatFilterForm entitatFilterForm,
       List<Entitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (entitatFilterForm.isHiddenField(SEGELLDETEMPSVIAWEB)
      && !entitatFilterForm.isGroupByField(SEGELLDETEMPSVIAWEB)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForSegellDeTempsViaWeb(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSegellDeTempsViaWeb(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,EntitatForm entitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EntitatForm entitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EntitatFilterForm filterForm,  List<Entitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EntitatForm entitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EntitatForm entitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.String entitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.String entitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "entitatFormWebDB";
  }

  public String getTileList() {
    return "entitatListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.String entitatID) throws I18NException {
    return (EntitatJPA) entitatEjb.findByPrimaryKey(entitatID);
  }


  public EntitatJPA create(HttpServletRequest request, EntitatJPA entitat)
    throws Exception,I18NException, I18NValidationException {
    return (EntitatJPA) entitatEjb.create(entitat);
  }


  public EntitatJPA update(HttpServletRequest request, EntitatJPA entitat)
    throws Exception,I18NException, I18NValidationException {
    return (EntitatJPA) entitatEjb.update(entitat);
  }


  public void delete(HttpServletRequest request, Entitat entitat) throws Exception,I18NException {
    entitatEjb.delete(entitat);
  }

} // Final de Classe

