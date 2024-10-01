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
import es.caib.portafib.back.form.webdb.PerfilDeFirmaForm;

import es.caib.portafib.back.validator.webdb.PerfilDeFirmaWebValidator;

import es.caib.portafib.persistence.PerfilDeFirmaJPA;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PerfilDeFirma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/perfilDeFirma")
@SessionAttributes(types = { PerfilDeFirmaForm.class, PerfilDeFirmaFilterForm.class })
public class PerfilDeFirmaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PerfilDeFirma, java.lang.Long> implements PerfilDeFirmaFields {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

  @Autowired
  private PerfilDeFirmaWebValidator perfilDeFirmaWebValidator;

  @Autowired
  protected PerfilDeFirmaRefList perfilDeFirmaRefList;

  // References 
  @Autowired
  protected UsuariAplicacioConfiguracioRefList usuariAplicacioConfiguracioRefList;

  /**
   * Llistat de totes PerfilDeFirma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PerfilDeFirmaFilterForm ff;
    ff = (PerfilDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PerfilDeFirma de forma paginada
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
    llistat(mav, request, getPerfilDeFirmaFilterForm(pagina, mav, request));
    return mav;
  }

  public PerfilDeFirmaFilterForm getPerfilDeFirmaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PerfilDeFirmaFilterForm perfilDeFirmaFilterForm;
    perfilDeFirmaFilterForm = (PerfilDeFirmaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(perfilDeFirmaFilterForm == null) {
      perfilDeFirmaFilterForm = new PerfilDeFirmaFilterForm();
      perfilDeFirmaFilterForm.setContexte(getContextWeb());
      perfilDeFirmaFilterForm.setEntityNameCode(getEntityNameCode());
      perfilDeFirmaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      perfilDeFirmaFilterForm.setNou(true);
    } else {
      perfilDeFirmaFilterForm.setNou(false);
    }
    perfilDeFirmaFilterForm.setPage(pagina == null ? 1 : pagina);
    return perfilDeFirmaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PerfilDeFirma de forma paginada
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
      @ModelAttribute PerfilDeFirmaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPerfilDeFirmaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PerfilDeFirma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PerfilDeFirma> llistat(ModelAndView mav, HttpServletRequest request,
     PerfilDeFirmaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PerfilDeFirma> perfilDeFirma = processarLlistat(perfilDeFirmaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("perfilDeFirmaItems", perfilDeFirma);

    mav.addObject("perfilDeFirmaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, perfilDeFirma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, perfilDeFirma);

    return perfilDeFirma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PerfilDeFirmaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PerfilDeFirma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field configuracioDeFirma1ID
    {
      _listSKV = getReferenceListForConfiguracioDeFirma1ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID(_tmp);
      if (filterForm.getGroupByFields().contains(CONFIGURACIODEFIRMA1ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONFIGURACIODEFIRMA1ID, false);
      };
    }

    // Field configuracioDeFirma2ID
    {
      _listSKV = getReferenceListForConfiguracioDeFirma2ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID(_tmp);
      if (filterForm.getGroupByFields().contains(CONFIGURACIODEFIRMA2ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONFIGURACIODEFIRMA2ID, false);
      };
    }

    // Field configuracioDeFirma3ID
    {
      _listSKV = getReferenceListForConfiguracioDeFirma3ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID(_tmp);
      if (filterForm.getGroupByFields().contains(CONFIGURACIODEFIRMA3ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONFIGURACIODEFIRMA3ID, false);
      };
    }

    // Field configuracioDeFirma4ID
    {
      _listSKV = getReferenceListForConfiguracioDeFirma4ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID(_tmp);
      if (filterForm.getGroupByFields().contains(CONFIGURACIODEFIRMA4ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONFIGURACIODEFIRMA4ID, false);
      };
    }

    // Field configuracioDeFirma5ID
    {
      _listSKV = getReferenceListForConfiguracioDeFirma5ID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID(_tmp);
      if (filterForm.getGroupByFields().contains(CONFIGURACIODEFIRMA5ID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, CONFIGURACIODEFIRMA5ID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PerfilDeFirmaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PerfilDeFirma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PERFILDEFIRMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(CONFIGURACIODEFIRMA1ID, filterForm.getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID());
    __mapping.put(CONFIGURACIODEFIRMA2ID, filterForm.getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID());
    __mapping.put(CONFIGURACIODEFIRMA3ID, filterForm.getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID());
    __mapping.put(CONFIGURACIODEFIRMA4ID, filterForm.getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID());
    __mapping.put(CONFIGURACIODEFIRMA5ID, filterForm.getMapOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PerfilDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPerfilDeFirmaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PerfilDeFirmaForm perfilDeFirmaForm = getPerfilDeFirmaForm(null, false, request, mav);
    mav.addObject("perfilDeFirmaForm" ,perfilDeFirmaForm);
    fillReferencesForForm(perfilDeFirmaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PerfilDeFirmaForm getPerfilDeFirmaForm(PerfilDeFirmaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PerfilDeFirmaForm perfilDeFirmaForm;
    if(_jpa == null) {
      perfilDeFirmaForm = new PerfilDeFirmaForm(new PerfilDeFirmaJPA(), true);
    } else {
      perfilDeFirmaForm = new PerfilDeFirmaForm(_jpa, false);
      perfilDeFirmaForm.setView(__isView);
    }
    perfilDeFirmaForm.setContexte(getContextWeb());
    perfilDeFirmaForm.setEntityNameCode(getEntityNameCode());
    perfilDeFirmaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return perfilDeFirmaForm;
  }

  public void fillReferencesForForm(PerfilDeFirmaForm perfilDeFirmaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (perfilDeFirmaForm.getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConfiguracioDeFirma1ID(request, mav, perfilDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilDeFirmaForm.setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma1ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (perfilDeFirmaForm.getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConfiguracioDeFirma2ID(request, mav, perfilDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilDeFirmaForm.setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma2ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (perfilDeFirmaForm.getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConfiguracioDeFirma3ID(request, mav, perfilDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilDeFirmaForm.setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma3ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (perfilDeFirmaForm.getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConfiguracioDeFirma4ID(request, mav, perfilDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilDeFirmaForm.setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma4ID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (perfilDeFirmaForm.getListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForConfiguracioDeFirma5ID(request, mav, perfilDeFirmaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      perfilDeFirmaForm.setListOfUsuariAplicacioConfiguracioForConfiguracioDeFirma5ID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PerfilDeFirma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPerfilDeFirmaPost(@ModelAttribute PerfilDeFirmaForm perfilDeFirmaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PerfilDeFirmaJPA perfilDeFirma = perfilDeFirmaForm.getPerfilDeFirma();

    try {
      preValidate(request, perfilDeFirmaForm, result);
      getWebValidator().validate(perfilDeFirmaForm, result);
      postValidate(request,perfilDeFirmaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        perfilDeFirma = create(request, perfilDeFirma);
        createMessageSuccess(request, "success.creation", perfilDeFirma.getUsuariAplicacioPerfilID());
        perfilDeFirmaForm.setPerfilDeFirma(perfilDeFirma);
        return getRedirectWhenCreated(request, perfilDeFirmaForm);
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

  @RequestMapping(value = "/view/{usuariAplicacioPerfilID}", method = RequestMethod.GET)
  public ModelAndView veurePerfilDeFirmaGet(@PathVariable("usuariAplicacioPerfilID") java.lang.Long usuariAplicacioPerfilID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPerfilDeFirmaGet(usuariAplicacioPerfilID,
        request, response, true);
  }


  protected ModelAndView editAndViewPerfilDeFirmaGet(@PathVariable("usuariAplicacioPerfilID") java.lang.Long usuariAplicacioPerfilID,
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
    PerfilDeFirmaJPA perfilDeFirma = findByPrimaryKey(request, usuariAplicacioPerfilID);

    if (perfilDeFirma == null) {
      createMessageWarning(request, "error.notfound", usuariAplicacioPerfilID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, usuariAplicacioPerfilID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PerfilDeFirmaForm perfilDeFirmaForm = getPerfilDeFirmaForm(perfilDeFirma, __isView, request, mav);
      perfilDeFirmaForm.setView(__isView);
      if(__isView) {
        perfilDeFirmaForm.setAllFieldsReadOnly(ALL_PERFILDEFIRMA_FIELDS);
        perfilDeFirmaForm.setSaveButtonVisible(false);
        perfilDeFirmaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(perfilDeFirmaForm, request, mav);
      mav.addObject("perfilDeFirmaForm", perfilDeFirmaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PerfilDeFirma existent
   */
  @RequestMapping(value = "/{usuariAplicacioPerfilID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPerfilDeFirmaGet(@PathVariable("usuariAplicacioPerfilID") java.lang.Long usuariAplicacioPerfilID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPerfilDeFirmaGet(usuariAplicacioPerfilID,
        request, response, false);
  }



  /**
   * Editar un PerfilDeFirma existent
   */
  @RequestMapping(value = "/{usuariAplicacioPerfilID}/edit", method = RequestMethod.POST)
  public String editarPerfilDeFirmaPost(@ModelAttribute PerfilDeFirmaForm perfilDeFirmaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PerfilDeFirmaJPA perfilDeFirma = perfilDeFirmaForm.getPerfilDeFirma();

    try {
      preValidate(request, perfilDeFirmaForm, result);
      getWebValidator().validate(perfilDeFirmaForm, result);
      postValidate(request, perfilDeFirmaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        perfilDeFirma = update(request, perfilDeFirma);
        createMessageSuccess(request, "success.modification", perfilDeFirma.getUsuariAplicacioPerfilID());
        status.setComplete();
        return getRedirectWhenModified(request, perfilDeFirmaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          perfilDeFirma.getUsuariAplicacioPerfilID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, perfilDeFirmaForm, __e);
    }

  }


  /**
   * Eliminar un PerfilDeFirma existent
   */
  @RequestMapping(value = "/{usuariAplicacioPerfilID}/delete")
  public String eliminarPerfilDeFirma(@PathVariable("usuariAplicacioPerfilID") java.lang.Long usuariAplicacioPerfilID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PerfilDeFirma perfilDeFirma = this.findByPrimaryKey(request, usuariAplicacioPerfilID);
      if (perfilDeFirma == null) {
        String __msg = createMessageError(request, "error.notfound", usuariAplicacioPerfilID);
        return getRedirectWhenDelete(request, usuariAplicacioPerfilID, new Exception(__msg));
      } else {
        delete(request, perfilDeFirma);
        createMessageSuccess(request, "success.deleted", usuariAplicacioPerfilID);
        return getRedirectWhenDelete(request, usuariAplicacioPerfilID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", usuariAplicacioPerfilID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, usuariAplicacioPerfilID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PerfilDeFirmaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPerfilDeFirma(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __usuariAplicacioPerfilID, Throwable e) {
    java.lang.Long usuariAplicacioPerfilID = (java.lang.Long)__usuariAplicacioPerfilID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (usuariAplicacioPerfilID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(usuariAplicacioPerfilID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "perfilDeFirma.perfilDeFirma";
  }

  public String getEntityNameCodePlural() {
    return "perfilDeFirma.perfilDeFirma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("perfilDeFirma.usuariAplicacioPerfilID");
  }

  @InitBinder("perfilDeFirmaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("perfilDeFirmaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "perfilDeFirma.usuariAplicacioPerfilID");
  }

  public PerfilDeFirmaWebValidator getWebValidator() {
    return perfilDeFirmaWebValidator;
  }


  public void setWebValidator(PerfilDeFirmaWebValidator __val) {
    if (__val != null) {
      this.perfilDeFirmaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PerfilDeFirma
   */
  @RequestMapping(value = "/{usuariAplicacioPerfilID}/cancel")
  public String cancelPerfilDeFirma(@PathVariable("usuariAplicacioPerfilID") java.lang.Long usuariAplicacioPerfilID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, usuariAplicacioPerfilID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de PerfilDeFirma
   */
  @RequestMapping(value = "/cancel")
  public String cancelPerfilDeFirma(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
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


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma1ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaForm perfilDeFirmaForm, Where where)  throws I18NException {
    if (perfilDeFirmaForm.isHiddenField(CONFIGURACIODEFIRMA1ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilDeFirmaForm.isReadOnlyField(CONFIGURACIODEFIRMA1ID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(perfilDeFirmaForm.getPerfilDeFirma().getConfiguracioDeFirma1ID());
    }
    return getReferenceListForConfiguracioDeFirma1ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma1ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaFilterForm perfilDeFirmaFilterForm,
       List<PerfilDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilDeFirmaFilterForm.isHiddenField(CONFIGURACIODEFIRMA1ID)
       && !perfilDeFirmaFilterForm.isGroupByField(CONFIGURACIODEFIRMA1ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CONFIGURACIODEFIRMA1ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PerfilDeFirma _item : list) {
        _pkList.add(_item.getConfiguracioDeFirma1ID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForConfiguracioDeFirma1ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma1ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma2ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaForm perfilDeFirmaForm, Where where)  throws I18NException {
    if (perfilDeFirmaForm.isHiddenField(CONFIGURACIODEFIRMA2ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilDeFirmaForm.isReadOnlyField(CONFIGURACIODEFIRMA2ID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(perfilDeFirmaForm.getPerfilDeFirma().getConfiguracioDeFirma2ID());
    }
    return getReferenceListForConfiguracioDeFirma2ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma2ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaFilterForm perfilDeFirmaFilterForm,
       List<PerfilDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilDeFirmaFilterForm.isHiddenField(CONFIGURACIODEFIRMA2ID)
       && !perfilDeFirmaFilterForm.isGroupByField(CONFIGURACIODEFIRMA2ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CONFIGURACIODEFIRMA2ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PerfilDeFirma _item : list) {
        if(_item.getConfiguracioDeFirma2ID() == null) { continue; };
        _pkList.add(_item.getConfiguracioDeFirma2ID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForConfiguracioDeFirma2ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma2ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma3ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaForm perfilDeFirmaForm, Where where)  throws I18NException {
    if (perfilDeFirmaForm.isHiddenField(CONFIGURACIODEFIRMA3ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilDeFirmaForm.isReadOnlyField(CONFIGURACIODEFIRMA3ID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(perfilDeFirmaForm.getPerfilDeFirma().getConfiguracioDeFirma3ID());
    }
    return getReferenceListForConfiguracioDeFirma3ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma3ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaFilterForm perfilDeFirmaFilterForm,
       List<PerfilDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilDeFirmaFilterForm.isHiddenField(CONFIGURACIODEFIRMA3ID)
       && !perfilDeFirmaFilterForm.isGroupByField(CONFIGURACIODEFIRMA3ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CONFIGURACIODEFIRMA3ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PerfilDeFirma _item : list) {
        if(_item.getConfiguracioDeFirma3ID() == null) { continue; };
        _pkList.add(_item.getConfiguracioDeFirma3ID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForConfiguracioDeFirma3ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma3ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma4ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaForm perfilDeFirmaForm, Where where)  throws I18NException {
    if (perfilDeFirmaForm.isHiddenField(CONFIGURACIODEFIRMA4ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilDeFirmaForm.isReadOnlyField(CONFIGURACIODEFIRMA4ID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(perfilDeFirmaForm.getPerfilDeFirma().getConfiguracioDeFirma4ID());
    }
    return getReferenceListForConfiguracioDeFirma4ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma4ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaFilterForm perfilDeFirmaFilterForm,
       List<PerfilDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilDeFirmaFilterForm.isHiddenField(CONFIGURACIODEFIRMA4ID)
       && !perfilDeFirmaFilterForm.isGroupByField(CONFIGURACIODEFIRMA4ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CONFIGURACIODEFIRMA4ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PerfilDeFirma _item : list) {
        if(_item.getConfiguracioDeFirma4ID() == null) { continue; };
        _pkList.add(_item.getConfiguracioDeFirma4ID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForConfiguracioDeFirma4ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma4ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma5ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaForm perfilDeFirmaForm, Where where)  throws I18NException {
    if (perfilDeFirmaForm.isHiddenField(CONFIGURACIODEFIRMA5ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (perfilDeFirmaForm.isReadOnlyField(CONFIGURACIODEFIRMA5ID)) {
      _where = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(perfilDeFirmaForm.getPerfilDeFirma().getConfiguracioDeFirma5ID());
    }
    return getReferenceListForConfiguracioDeFirma5ID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma5ID(HttpServletRequest request,
       ModelAndView mav, PerfilDeFirmaFilterForm perfilDeFirmaFilterForm,
       List<PerfilDeFirma> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (perfilDeFirmaFilterForm.isHiddenField(CONFIGURACIODEFIRMA5ID)
       && !perfilDeFirmaFilterForm.isGroupByField(CONFIGURACIODEFIRMA5ID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(CONFIGURACIODEFIRMA5ID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PerfilDeFirma _item : list) {
        if(_item.getConfiguracioDeFirma5ID() == null) { continue; };
        _pkList.add(_item.getConfiguracioDeFirma5ID());
        }
        _w = UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.in(_pkList);
      }
    return getReferenceListForConfiguracioDeFirma5ID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForConfiguracioDeFirma5ID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariAplicacioConfiguracioRefList.getReferenceList(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PerfilDeFirmaForm perfilDeFirmaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PerfilDeFirmaForm perfilDeFirmaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PerfilDeFirmaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PerfilDeFirmaFilterForm filterForm,  List<PerfilDeFirma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PerfilDeFirmaForm perfilDeFirmaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PerfilDeFirmaForm perfilDeFirmaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long usuariAplicacioPerfilID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariAplicacioPerfilID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "perfilDeFirmaFormWebDB";
  }

  public String getTileList() {
    return "perfilDeFirmaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PerfilDeFirma_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PerfilDeFirmaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long usuariAplicacioPerfilID) throws I18NException {
    return (PerfilDeFirmaJPA) perfilDeFirmaEjb.findByPrimaryKey(usuariAplicacioPerfilID);
  }


  public PerfilDeFirmaJPA create(HttpServletRequest request, PerfilDeFirmaJPA perfilDeFirma)
    throws I18NException, I18NValidationException {
    return (PerfilDeFirmaJPA) perfilDeFirmaEjb.create(perfilDeFirma);
  }


  public PerfilDeFirmaJPA update(HttpServletRequest request, PerfilDeFirmaJPA perfilDeFirma)
    throws I18NException, I18NValidationException {
    return (PerfilDeFirmaJPA) perfilDeFirmaEjb.update(perfilDeFirma);
  }


  public void delete(HttpServletRequest request, PerfilDeFirma perfilDeFirma) throws I18NException {
    perfilDeFirmaEjb.delete(perfilDeFirma);
  }

} // Final de Classe

