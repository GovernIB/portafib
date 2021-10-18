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
import es.caib.portafib.back.form.webdb.CustodiaInfoForm;

import es.caib.portafib.back.validator.webdb.CustodiaInfoWebValidator;

import es.caib.portafib.persistence.CustodiaInfoJPA;
import es.caib.portafib.model.entity.CustodiaInfo;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un CustodiaInfo
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/custodiaInfo")
@SessionAttributes(types = { CustodiaInfoForm.class, CustodiaInfoFilterForm.class })
public class CustodiaInfoController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<CustodiaInfo, java.lang.Long> implements CustodiaInfoFields {

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoService.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoService custodiaInfoEjb;

  @Autowired
  private CustodiaInfoWebValidator custodiaInfoWebValidator;

  @Autowired
  protected CustodiaInfoRefList custodiaInfoRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  // References 
  @Autowired
  protected CodiBarresRefList codiBarresRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes CustodiaInfo
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    CustodiaInfoFilterForm ff;
    ff = (CustodiaInfoFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar CustodiaInfo de forma paginada
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
    llistat(mav, request, getCustodiaInfoFilterForm(pagina, mav, request));
    return mav;
  }

  public CustodiaInfoFilterForm getCustodiaInfoFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    CustodiaInfoFilterForm custodiaInfoFilterForm;
    custodiaInfoFilterForm = (CustodiaInfoFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(custodiaInfoFilterForm == null) {
      custodiaInfoFilterForm = new CustodiaInfoFilterForm();
      custodiaInfoFilterForm.setContexte(getContextWeb());
      custodiaInfoFilterForm.setEntityNameCode(getEntityNameCode());
      custodiaInfoFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      custodiaInfoFilterForm.setNou(true);
    } else {
      custodiaInfoFilterForm.setNou(false);
    }
    custodiaInfoFilterForm.setPage(pagina == null ? 1 : pagina);
    return custodiaInfoFilterForm;
  }

  /**
   * Segona i següent peticions per llistar CustodiaInfo de forma paginada
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
      @ModelAttribute CustodiaInfoFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getCustodiaInfoFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de CustodiaInfo de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<CustodiaInfo> llistat(ModelAndView mav, HttpServletRequest request,
     CustodiaInfoFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<CustodiaInfo> custodiaInfo = processarLlistat(custodiaInfoEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("custodiaInfoItems", custodiaInfo);

    mav.addObject("custodiaInfoFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, custodiaInfo, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, custodiaInfo);

    return custodiaInfo;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(CustodiaInfoFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<CustodiaInfo> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field pluginID
    {
      _listSKV = getReferenceListForPluginID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, CUSTODIAR);

    // Field missatgePosicioPaginaID
    {
      _listSKV = getReferenceListForMissatgePosicioPaginaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForMissatgePosicioPaginaID(_tmp);
      if (filterForm.getGroupByFields().contains(MISSATGEPOSICIOPAGINAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MISSATGEPOSICIOPAGINAID, false);
      };
    }

    // Field codiBarresID
    {
      _listSKV = getReferenceListForCodiBarresID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfCodiBarresForCodiBarresID(_tmp);
      if (filterForm.getGroupByFields().contains(CODIBARRESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CODIBARRESID, false);
      };
    }

    // Field codiBarresPosicioPaginaID
    {
      _listSKV = getReferenceListForCodiBarresPosicioPaginaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForCodiBarresPosicioPaginaID(_tmp);
      if (filterForm.getGroupByFields().contains(CODIBARRESPOSICIOPAGINAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CODIBARRESPOSICIOPAGINAID, false);
      };
    }

    // Field usuariEntitatID
    {
      _listSKV = getReferenceListForUsuariEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForUsuariEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIENTITATID, false);
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

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, EDITABLE);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    CustodiaInfoFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<CustodiaInfo> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CUSTODIAINFO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PLUGINID, filterForm.getMapOfPluginForPluginID());
    __mapping.put(MISSATGEPOSICIOPAGINAID, filterForm.getMapOfValuesForMissatgePosicioPaginaID());
    __mapping.put(CODIBARRESID, filterForm.getMapOfCodiBarresForCodiBarresID());
    __mapping.put(CODIBARRESPOSICIOPAGINAID, filterForm.getMapOfValuesForCodiBarresPosicioPaginaID());
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou CustodiaInfo
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearCustodiaInfoGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    CustodiaInfoForm custodiaInfoForm = getCustodiaInfoForm(null, false, request, mav);
    mav.addObject("custodiaInfoForm" ,custodiaInfoForm);
    fillReferencesForForm(custodiaInfoForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public CustodiaInfoForm getCustodiaInfoForm(CustodiaInfoJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    CustodiaInfoForm custodiaInfoForm;
    if(_jpa == null) {
      custodiaInfoForm = new CustodiaInfoForm(new CustodiaInfoJPA(), true);
    } else {
      custodiaInfoForm = new CustodiaInfoForm(_jpa, false);
      custodiaInfoForm.setView(__isView);
    }
    custodiaInfoForm.setContexte(getContextWeb());
    custodiaInfoForm.setEntityNameCode(getEntityNameCode());
    custodiaInfoForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return custodiaInfoForm;
  }

  public void fillReferencesForForm(CustodiaInfoForm custodiaInfoForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfPluginForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfPluginForPluginID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfValuesForMissatgePosicioPaginaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForMissatgePosicioPaginaID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfValuesForMissatgePosicioPaginaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfCodiBarresForCodiBarresID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCodiBarresID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfCodiBarresForCodiBarresID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfValuesForCodiBarresPosicioPaginaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForCodiBarresPosicioPaginaID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfValuesForCodiBarresPosicioPaginaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (custodiaInfoForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, custodiaInfoForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      custodiaInfoForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou CustodiaInfo
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearCustodiaInfoPost(@ModelAttribute CustodiaInfoForm custodiaInfoForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    CustodiaInfoJPA custodiaInfo = custodiaInfoForm.getCustodiaInfo();

    try {
      preValidate(request, custodiaInfoForm, result);
      getWebValidator().validate(custodiaInfoForm, result);
      postValidate(request,custodiaInfoForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        custodiaInfo = create(request, custodiaInfo);
        createMessageSuccess(request, "success.creation", custodiaInfo.getCustodiaInfoID());
        custodiaInfoForm.setCustodiaInfo(custodiaInfo);
        return getRedirectWhenCreated(request, custodiaInfoForm);
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

  @RequestMapping(value = "/view/{custodiaInfoID}", method = RequestMethod.GET)
  public ModelAndView veureCustodiaInfoGet(@PathVariable("custodiaInfoID") java.lang.Long custodiaInfoID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCustodiaInfoGet(custodiaInfoID,
        request, response, true);
  }


  protected ModelAndView editAndViewCustodiaInfoGet(@PathVariable("custodiaInfoID") java.lang.Long custodiaInfoID,
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
    CustodiaInfoJPA custodiaInfo = findByPrimaryKey(request, custodiaInfoID);

    if (custodiaInfo == null) {
      createMessageWarning(request, "error.notfound", custodiaInfoID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, custodiaInfoID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      CustodiaInfoForm custodiaInfoForm = getCustodiaInfoForm(custodiaInfo, __isView, request, mav);
      custodiaInfoForm.setView(__isView);
      if(__isView) {
        custodiaInfoForm.setAllFieldsReadOnly(ALL_CUSTODIAINFO_FIELDS);
        custodiaInfoForm.setSaveButtonVisible(false);
        custodiaInfoForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(custodiaInfoForm, request, mav);
      mav.addObject("custodiaInfoForm", custodiaInfoForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un CustodiaInfo existent
   */
  @RequestMapping(value = "/{custodiaInfoID}/edit", method = RequestMethod.GET)
  public ModelAndView editarCustodiaInfoGet(@PathVariable("custodiaInfoID") java.lang.Long custodiaInfoID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCustodiaInfoGet(custodiaInfoID,
        request, response, false);
  }



  /**
   * Editar un CustodiaInfo existent
   */
  @RequestMapping(value = "/{custodiaInfoID}/edit", method = RequestMethod.POST)
  public String editarCustodiaInfoPost(@ModelAttribute CustodiaInfoForm custodiaInfoForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    CustodiaInfoJPA custodiaInfo = custodiaInfoForm.getCustodiaInfo();

    try {
      preValidate(request, custodiaInfoForm, result);
      getWebValidator().validate(custodiaInfoForm, result);
      postValidate(request, custodiaInfoForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        custodiaInfo = update(request, custodiaInfo);
        createMessageSuccess(request, "success.modification", custodiaInfo.getCustodiaInfoID());
        status.setComplete();
        return getRedirectWhenModified(request, custodiaInfoForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          custodiaInfo.getCustodiaInfoID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, custodiaInfoForm, __e);
    }

  }


  /**
   * Eliminar un CustodiaInfo existent
   */
  @RequestMapping(value = "/{custodiaInfoID}/delete")
  public String eliminarCustodiaInfo(@PathVariable("custodiaInfoID") java.lang.Long custodiaInfoID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      CustodiaInfo custodiaInfo = custodiaInfoEjb.findByPrimaryKey(custodiaInfoID);
      if (custodiaInfo == null) {
        String __msg =createMessageError(request, "error.notfound", custodiaInfoID);
        return getRedirectWhenDelete(request, custodiaInfoID, new Exception(__msg));
      } else {
        delete(request, custodiaInfo);
        createMessageSuccess(request, "success.deleted", custodiaInfoID);
        return getRedirectWhenDelete(request, custodiaInfoID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", custodiaInfoID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, custodiaInfoID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute CustodiaInfoFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarCustodiaInfo(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __custodiaInfoID, Throwable e) {
    java.lang.Long custodiaInfoID = (java.lang.Long)__custodiaInfoID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (custodiaInfoID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(custodiaInfoID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "custodiaInfo.custodiaInfo";
  }

  public String getEntityNameCodePlural() {
    return "custodiaInfo.custodiaInfo.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("custodiaInfo.custodiaInfoID");
  }

  @InitBinder("custodiaInfoFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("custodiaInfoForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "custodiaInfo.custodiaInfoID");
  }

  public CustodiaInfoWebValidator getWebValidator() {
    return custodiaInfoWebValidator;
  }


  public void setWebValidator(CustodiaInfoWebValidator __val) {
    if (__val != null) {
      this.custodiaInfoWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de CustodiaInfo
   */
  @RequestMapping(value = "/{custodiaInfoID}/cancel")
  public String cancelCustodiaInfo(@PathVariable("custodiaInfoID") java.lang.Long custodiaInfoID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, custodiaInfoID);
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


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (custodiaInfoForm.isReadOnlyField(PLUGINID)) {
      _where = PluginFields.PLUGINID.equal(custodiaInfoForm.getCustodiaInfo().getPluginID());
    }
    return getReferenceListForPluginID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(PLUGINID)
      && !custodiaInfoFilterForm.isGroupByField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (CustodiaInfo _item : list) {
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


  public List<StringKeyValue> getReferenceListForMissatgePosicioPaginaID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(MISSATGEPOSICIOPAGINAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForMissatgePosicioPaginaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForMissatgePosicioPaginaID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(MISSATGEPOSICIOPAGINAID)
      && !custodiaInfoFilterForm.isGroupByField(MISSATGEPOSICIOPAGINAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForMissatgePosicioPaginaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForMissatgePosicioPaginaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForCodiBarresID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(CODIBARRESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (custodiaInfoForm.isReadOnlyField(CODIBARRESID)) {
      _where = CodiBarresFields.CODIBARRESID.equal(custodiaInfoForm.getCustodiaInfo().getCodiBarresID());
    }
    return getReferenceListForCodiBarresID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForCodiBarresID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(CODIBARRESID)
      && !custodiaInfoFilterForm.isGroupByField(CODIBARRESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CODIBARRESID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (CustodiaInfo _item : list) {
        _pkList.add(_item.getCodiBarresID());
        }
        _w = CodiBarresFields.CODIBARRESID.in(_pkList);
      }
    return getReferenceListForCodiBarresID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCodiBarresID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return codiBarresRefList.getReferenceList(CodiBarresFields.CODIBARRESID, where );
  }


  public List<StringKeyValue> getReferenceListForCodiBarresPosicioPaginaID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(CODIBARRESPOSICIOPAGINAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForCodiBarresPosicioPaginaID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForCodiBarresPosicioPaginaID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(CODIBARRESPOSICIOPAGINAID)
      && !custodiaInfoFilterForm.isGroupByField(CODIBARRESPOSICIOPAGINAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForCodiBarresPosicioPaginaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForCodiBarresPosicioPaginaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (custodiaInfoForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(custodiaInfoForm.getCustodiaInfo().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(USUARIENTITATID)
      && !custodiaInfoFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (CustodiaInfo _item : list) {
        if(_item.getUsuariEntitatID() == null) { continue; };
        _pkList.add(_item.getUsuariEntitatID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (custodiaInfoForm.isReadOnlyField(USUARIAPLICACIOID)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(custodiaInfoForm.getCustodiaInfo().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !custodiaInfoFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (CustodiaInfo _item : list) {
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


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoForm custodiaInfoForm, Where where)  throws I18NException {
    if (custodiaInfoForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (custodiaInfoForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(custodiaInfoForm.getCustodiaInfo().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, CustodiaInfoFilterForm custodiaInfoFilterForm,
       List<CustodiaInfo> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (custodiaInfoFilterForm.isHiddenField(ENTITATID)
      && !custodiaInfoFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (CustodiaInfo _item : list) {
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


  public void preValidate(HttpServletRequest request,CustodiaInfoForm custodiaInfoForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,CustodiaInfoForm custodiaInfoForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, CustodiaInfoFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, CustodiaInfoFilterForm filterForm,  List<CustodiaInfo> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, CustodiaInfoForm custodiaInfoForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, CustodiaInfoForm custodiaInfoForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long custodiaInfoID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long custodiaInfoID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "custodiaInfoFormWebDB";
  }

  public String getTileList() {
    return "custodiaInfoListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "CustodiaInfoWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public CustodiaInfoJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long custodiaInfoID) throws I18NException {
    return (CustodiaInfoJPA) custodiaInfoEjb.findByPrimaryKey(custodiaInfoID);
  }


  public CustodiaInfoJPA create(HttpServletRequest request, CustodiaInfoJPA custodiaInfo)
    throws Exception,I18NException, I18NValidationException {
    return (CustodiaInfoJPA) custodiaInfoEjb.create(custodiaInfo);
  }


  public CustodiaInfoJPA update(HttpServletRequest request, CustodiaInfoJPA custodiaInfo)
    throws Exception,I18NException, I18NValidationException {
    return (CustodiaInfoJPA) custodiaInfoEjb.update(custodiaInfo);
  }


  public void delete(HttpServletRequest request, CustodiaInfo custodiaInfo) throws Exception,I18NException {
    custodiaInfoEjb.delete(custodiaInfo);
  }

} // Final de Classe

