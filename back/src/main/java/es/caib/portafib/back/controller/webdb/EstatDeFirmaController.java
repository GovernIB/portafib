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
import es.caib.portafib.back.form.webdb.EstatDeFirmaForm;

import es.caib.portafib.back.validator.webdb.EstatDeFirmaWebValidator;

import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un EstatDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/estatDeFirma")
@SessionAttributes(types = { EstatDeFirmaForm.class, EstatDeFirmaFilterForm.class })
public class EstatDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements EstatDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.EstatDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EstatDeFirmaLocal estatDeFirmaEjb;

  @Autowired
  private EstatDeFirmaWebValidator estatDeFirmaWebValidator;

  @Autowired
  protected EstatDeFirmaRefList estatDeFirmaRefList;

  // References 
  @Autowired
  protected FirmaRefList firmaRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected TipusEstatDeFirmaInicialRefList tipusEstatDeFirmaInicialRefList;

  // References 
  @Autowired
  protected TipusEstatDeFirmaFinalRefList tipusEstatDeFirmaFinalRefList;

  // References 
  @Autowired
  protected ColaboracioDelegacioRefList colaboracioDelegacioRefList;

  /**
   * Llistat de totes EstatDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EstatDeFirmaFilterForm ff;
    ff = (EstatDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar EstatDeFirma de forma paginada
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
    llistat(mav, request, getEstatDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public EstatDeFirmaFilterForm getEstatDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EstatDeFirmaFilterForm estatDeFirmaFilterForm;
    estatDeFirmaFilterForm = (EstatDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(estatDeFirmaFilterForm == null) {
      estatDeFirmaFilterForm = new EstatDeFirmaFilterForm();
      estatDeFirmaFilterForm.setContexte(getContextWeb());
      estatDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      estatDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      estatDeFirmaFilterForm.setNou(true);
    } else {
      estatDeFirmaFilterForm.setNou(false);
    }
    estatDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return estatDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar EstatDeFirma de forma paginada
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
      @ModelAttribute EstatDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEstatDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de EstatDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<EstatDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     EstatDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<EstatDeFirma> estatDeFirma = (List<EstatDeFirma>) processarLlistat(estatDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("estatDeFirmaItems", estatDeFirma);

    mav.addObject("estatDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, estatDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, estatDeFirma);

    return estatDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EstatDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<EstatDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field firmaID
    {
      _listSKV = getReferenceListForFirmaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFirmaForFirmaID(_tmp);
      if (filterForm.getGroupByFields().contains(FIRMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FIRMAID, false);
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

    // Field tipusEstatDeFirmaInicialID
    {
      _listSKV = getReferenceListForTipusEstatDeFirmaInicialID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSESTATDEFIRMAINICIALID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSESTATDEFIRMAINICIALID, false);
      };
    }

    // Field tipusEstatDeFirmaFinalID
    {
      _listSKV = getReferenceListForTipusEstatDeFirmaFinalID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUSESTATDEFIRMAFINALID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUSESTATDEFIRMAFINALID, false);
      };
    }

    // Field colaboracioDelegacioID
    {
      _listSKV = getReferenceListForColaboracioDelegacioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfColaboracioDelegacioForColaboracioDelegacioID(_tmp);
      if (filterForm.getGroupByFields().contains(COLABORACIODELEGACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, COLABORACIODELEGACIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EstatDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<EstatDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ESTATDEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FIRMAID, filterForm.getMapOfFirmaForFirmaID());
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(TIPUSESTATDEFIRMAINICIALID, filterForm.getMapOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID());
    __mapping.put(TIPUSESTATDEFIRMAFINALID, filterForm.getMapOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID());
    __mapping.put(COLABORACIODELEGACIOID, filterForm.getMapOfColaboracioDelegacioForColaboracioDelegacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou EstatDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEstatDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EstatDeFirmaForm estatDeFirmaForm = getEstatDeFirmaForm(null, false, request, mav);
    mav.addObject("estatDeFirmaForm" ,estatDeFirmaForm);
    fillReferencesForForm(estatDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EstatDeFirmaForm getEstatDeFirmaForm(EstatDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EstatDeFirmaForm estatDeFirmaForm;
    if(_jpa == null) {
      estatDeFirmaForm = new EstatDeFirmaForm(new EstatDeFirmaJPA(), true);
    } else {
      estatDeFirmaForm = new EstatDeFirmaForm(_jpa, false);
      estatDeFirmaForm.setView(__isView);
    }
    estatDeFirmaForm.setContexte(getContextWeb());
    estatDeFirmaForm.setEntityNameCode(getEntityNameCode());
    estatDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return estatDeFirmaForm;
  }

  public void fillReferencesForForm(EstatDeFirmaForm estatDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (estatDeFirmaForm.getListOfFirmaForFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFirmaID(request, mav, estatDeFirmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      estatDeFirmaForm.setListOfFirmaForFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (estatDeFirmaForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, estatDeFirmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      estatDeFirmaForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (estatDeFirmaForm.getListOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusEstatDeFirmaInicialID(request, mav, estatDeFirmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      estatDeFirmaForm.setListOfTipusEstatDeFirmaInicialForTipusEstatDeFirmaInicialID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (estatDeFirmaForm.getListOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipusEstatDeFirmaFinalID(request, mav, estatDeFirmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      estatDeFirmaForm.setListOfTipusEstatDeFirmaFinalForTipusEstatDeFirmaFinalID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (estatDeFirmaForm.getListOfColaboracioDelegacioForColaboracioDelegacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForColaboracioDelegacioID(request, mav, estatDeFirmaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      estatDeFirmaForm.setListOfColaboracioDelegacioForColaboracioDelegacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou EstatDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEstatDeFirmaPost(@ModelAttribute EstatDeFirmaForm estatDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EstatDeFirmaJPA estatDeFirma = estatDeFirmaForm.getEstatDeFirma();

    try {
      preValidate(request, estatDeFirmaForm, result);
      getWebValidator().validate(estatDeFirmaForm, result);
      postValidate(request,estatDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        estatDeFirma = create(request, estatDeFirma);
        createMessageSuccess(request, "success.creation", estatDeFirma.getEstatDeFirmaID());
        estatDeFirmaForm.setEstatDeFirma(estatDeFirma);
        return getRedirectWhenCreated(request, estatDeFirmaForm);
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

  @RequestMapping(value = "/view/{estatDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView veureEstatDeFirmaGet(@PathVariable("estatDeFirmaID") java.lang.Long estatDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatDeFirmaGet(estatDeFirmaID,
        request, response, true);
  }


  protected ModelAndView editAndViewEstatDeFirmaGet(@PathVariable("estatDeFirmaID") java.lang.Long estatDeFirmaID,
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
    EstatDeFirmaJPA estatDeFirma = findByPrimaryKey(request, estatDeFirmaID);

    if (estatDeFirma == null) {
      createMessageWarning(request, "error.notfound", estatDeFirmaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, estatDeFirmaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EstatDeFirmaForm estatDeFirmaForm = getEstatDeFirmaForm(estatDeFirma, __isView, request, mav);
      estatDeFirmaForm.setView(__isView);
      if(__isView) {
        estatDeFirmaForm.setAllFieldsReadOnly(ALL_ESTATDEFIRMA_FIELDS);
        estatDeFirmaForm.setSaveButtonVisible(false);
        estatDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(estatDeFirmaForm, request, mav);
      mav.addObject("estatDeFirmaForm", estatDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un EstatDeFirma existent
   */
  @RequestMapping(value = "/{estatDeFirmaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEstatDeFirmaGet(@PathVariable("estatDeFirmaID") java.lang.Long estatDeFirmaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEstatDeFirmaGet(estatDeFirmaID,
        request, response, false);
  }



  /**
   * Editar un EstatDeFirma existent
   */
  @RequestMapping(value = "/{estatDeFirmaID}/edit", method = RequestMethod.POST)
  public String editarEstatDeFirmaPost(@ModelAttribute @Valid EstatDeFirmaForm estatDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EstatDeFirmaJPA estatDeFirma = estatDeFirmaForm.getEstatDeFirma();

    try {
      preValidate(request, estatDeFirmaForm, result);
      getWebValidator().validate(estatDeFirma, result);
      postValidate(request, estatDeFirmaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        estatDeFirma = update(request, estatDeFirma);
        createMessageSuccess(request, "success.modification", estatDeFirma.getEstatDeFirmaID());
        status.setComplete();
        return getRedirectWhenModified(request, estatDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          estatDeFirma.getEstatDeFirmaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, estatDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un EstatDeFirma existent
   */
  @RequestMapping(value = "/{estatDeFirmaID}/delete")
  public String eliminarEstatDeFirma(@PathVariable("estatDeFirmaID") java.lang.Long estatDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      EstatDeFirma estatDeFirma = estatDeFirmaEjb.findByPrimaryKey(estatDeFirmaID);
      if (estatDeFirma == null) {
        String __msg =createMessageError(request, "error.notfound", estatDeFirmaID);
        return getRedirectWhenDelete(request, estatDeFirmaID, new Exception(__msg));
      } else {
        delete(request, estatDeFirma);
        createMessageSuccess(request, "success.deleted", estatDeFirmaID);
        return getRedirectWhenDelete(request, estatDeFirmaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", estatDeFirmaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, estatDeFirmaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EstatDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEstatDeFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __estatDeFirmaID, Throwable e) {
    java.lang.Long estatDeFirmaID = (java.lang.Long)__estatDeFirmaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (estatDeFirmaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(estatDeFirmaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "estatDeFirma.estatDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "estatDeFirma.estatDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("estatDeFirma.estatDeFirmaID");
  }

  @InitBinder("estatDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("estatDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("estatDeFirmaID");

  }

  public EstatDeFirmaWebValidator getWebValidator() {
    return estatDeFirmaWebValidator;
  }


  public void setWebValidator(EstatDeFirmaWebValidator __val) {
    if (__val != null) {
      this.estatDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de EstatDeFirma
   */
  @RequestMapping(value = "/{estatDeFirmaID}/cancel")
  public String cancelEstatDeFirma(@PathVariable("estatDeFirmaID") java.lang.Long estatDeFirmaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, estatDeFirmaID);
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


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaForm estatDeFirmaForm, Where where)  throws I18NException {
    if (estatDeFirmaForm.isHiddenField(FIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  estatDeFirmaForm.getStringOfField(FIRMAID);
    Where _where = null;
    if (estatDeFirmaForm.isReadOnlyField(_fieldName)) {
      _where = FirmaFields.FIRMAID.equal(estatDeFirmaForm.getEstatDeFirma().getFirmaID());
    }
    return getReferenceListForFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFirmaID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaFilterForm estatDeFirmaFilterForm,
       List<EstatDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estatDeFirmaFilterForm.isHiddenField(FIRMAID)
      && !estatDeFirmaFilterForm.isGroupByField(FIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (EstatDeFirma _item : list) {
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


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaForm estatDeFirmaForm, Where where)  throws I18NException {
    if (estatDeFirmaForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  estatDeFirmaForm.getStringOfField(USUARIENTITATID);
    Where _where = null;
    if (estatDeFirmaForm.isReadOnlyField(_fieldName)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(estatDeFirmaForm.getEstatDeFirma().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaFilterForm estatDeFirmaFilterForm,
       List<EstatDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estatDeFirmaFilterForm.isHiddenField(USUARIENTITATID)
      && !estatDeFirmaFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (EstatDeFirma _item : list) {
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


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaInicialID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaForm estatDeFirmaForm, Where where)  throws I18NException {
    if (estatDeFirmaForm.isHiddenField(TIPUSESTATDEFIRMAINICIALID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  estatDeFirmaForm.getStringOfField(TIPUSESTATDEFIRMAINICIALID);
    Where _where = null;
    if (estatDeFirmaForm.isReadOnlyField(_fieldName)) {
      _where = TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID.equal(estatDeFirmaForm.getEstatDeFirma().getTipusEstatDeFirmaInicialID());
    }
    return getReferenceListForTipusEstatDeFirmaInicialID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaInicialID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaFilterForm estatDeFirmaFilterForm,
       List<EstatDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estatDeFirmaFilterForm.isHiddenField(TIPUSESTATDEFIRMAINICIALID)
      && !estatDeFirmaFilterForm.isGroupByField(TIPUSESTATDEFIRMAINICIALID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSESTATDEFIRMAINICIALID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (EstatDeFirma _item : list) {
        _pkList.add(_item.getTipusEstatDeFirmaInicialID());
        }
        _w = TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID.in(_pkList);
      }
    return getReferenceListForTipusEstatDeFirmaInicialID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaInicialID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return tipusEstatDeFirmaInicialRefList.getReferenceList(TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID, where );
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaFinalID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaForm estatDeFirmaForm, Where where)  throws I18NException {
    if (estatDeFirmaForm.isHiddenField(TIPUSESTATDEFIRMAFINALID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  estatDeFirmaForm.getStringOfField(TIPUSESTATDEFIRMAFINALID);
    Where _where = null;
    if (estatDeFirmaForm.isReadOnlyField(_fieldName)) {
      _where = TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID.equal(estatDeFirmaForm.getEstatDeFirma().getTipusEstatDeFirmaFinalID());
    }
    return getReferenceListForTipusEstatDeFirmaFinalID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForTipusEstatDeFirmaFinalID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaFilterForm estatDeFirmaFilterForm,
       List<EstatDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estatDeFirmaFilterForm.isHiddenField(TIPUSESTATDEFIRMAFINALID)
      && !estatDeFirmaFilterForm.isGroupByField(TIPUSESTATDEFIRMAFINALID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TIPUSESTATDEFIRMAFINALID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (EstatDeFirma _item : list) {
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


  public List<StringKeyValue> getReferenceListForColaboracioDelegacioID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaForm estatDeFirmaForm, Where where)  throws I18NException {
    if (estatDeFirmaForm.isHiddenField(COLABORACIODELEGACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  estatDeFirmaForm.getStringOfField(COLABORACIODELEGACIOID);
    Where _where = null;
    if (estatDeFirmaForm.isReadOnlyField(_fieldName)) {
      _where = ColaboracioDelegacioFields.COLABORACIODELEGACIOID.equal(estatDeFirmaForm.getEstatDeFirma().getColaboracioDelegacioID());
    }
    return getReferenceListForColaboracioDelegacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForColaboracioDelegacioID(HttpServletRequest request,
       ModelAndView mav, EstatDeFirmaFilterForm estatDeFirmaFilterForm,
       List<EstatDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (estatDeFirmaFilterForm.isHiddenField(COLABORACIODELEGACIOID)
      && !estatDeFirmaFilterForm.isGroupByField(COLABORACIODELEGACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(COLABORACIODELEGACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (EstatDeFirma _item : list) {
        if(_item.getColaboracioDelegacioID() == null) { continue; };
        _pkList.add(_item.getColaboracioDelegacioID());
        }
        _w = ColaboracioDelegacioFields.COLABORACIODELEGACIOID.in(_pkList);
      }
    return getReferenceListForColaboracioDelegacioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForColaboracioDelegacioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return colaboracioDelegacioRefList.getReferenceList(ColaboracioDelegacioFields.COLABORACIODELEGACIOID, where );
  }


  public void preValidate(HttpServletRequest request,EstatDeFirmaForm estatDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EstatDeFirmaForm estatDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EstatDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EstatDeFirmaFilterForm filterForm,  List<EstatDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EstatDeFirmaForm estatDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EstatDeFirmaForm estatDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long estatDeFirmaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long estatDeFirmaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "estatDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "estatDeFirmaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EstatDeFirmaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EstatDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long estatDeFirmaID) throws I18NException {
    return (EstatDeFirmaJPA) estatDeFirmaEjb.findByPrimaryKey(estatDeFirmaID);
  }


  public EstatDeFirmaJPA create(HttpServletRequest request, EstatDeFirmaJPA estatDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (EstatDeFirmaJPA) estatDeFirmaEjb.create(estatDeFirma);
  }


  public EstatDeFirmaJPA update(HttpServletRequest request, EstatDeFirmaJPA estatDeFirma)
    throws Exception,I18NException, I18NValidationException {
    return (EstatDeFirmaJPA) estatDeFirmaEjb.update(estatDeFirma);
  }


  public void delete(HttpServletRequest request, EstatDeFirma estatDeFirma) throws Exception,I18NException {
    estatDeFirmaEjb.delete(estatDeFirma);
  }

} // Final de Classe

