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
import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritForm;

import es.caib.portafib.back.validator.webdb.UsuariEntitatFavoritWebValidator;

import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un UsuariEntitatFavorit
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/usuariEntitatFavorit")
@SessionAttributes(types = { UsuariEntitatFavoritForm.class, UsuariEntitatFavoritFilterForm.class })
public class UsuariEntitatFavoritController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<UsuariEntitatFavorit, java.lang.Long> implements UsuariEntitatFavoritFields {

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatFavoritLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatFavoritLocal usuariEntitatFavoritEjb;

  @Autowired
  private UsuariEntitatFavoritWebValidator usuariEntitatFavoritWebValidator;

  @Autowired
  protected UsuariEntitatFavoritRefList usuariEntitatFavoritRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes UsuariEntitatFavorit
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    UsuariEntitatFavoritFilterForm ff;
    ff = (UsuariEntitatFavoritFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar UsuariEntitatFavorit de forma paginada
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
    llistat(mav, request, getUsuariEntitatFavoritFilterForm(pagina, mav, request));
    return mav;
  }

  public UsuariEntitatFavoritFilterForm getUsuariEntitatFavoritFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    UsuariEntitatFavoritFilterForm usuariEntitatFavoritFilterForm;
    usuariEntitatFavoritFilterForm = (UsuariEntitatFavoritFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(usuariEntitatFavoritFilterForm == null) {
      usuariEntitatFavoritFilterForm = new UsuariEntitatFavoritFilterForm();
      usuariEntitatFavoritFilterForm.setContexte(getContextWeb());
      usuariEntitatFavoritFilterForm.setEntityNameCode(getEntityNameCode());
      usuariEntitatFavoritFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      usuariEntitatFavoritFilterForm.setNou(true);
    } else {
      usuariEntitatFavoritFilterForm.setNou(false);
    }
    usuariEntitatFavoritFilterForm.setPage(pagina == null ? 1 : pagina);
    return usuariEntitatFavoritFilterForm;
  }

  /**
   * Segona i següent peticions per llistar UsuariEntitatFavorit de forma paginada
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
      @ModelAttribute UsuariEntitatFavoritFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getUsuariEntitatFavoritFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de UsuariEntitatFavorit de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<UsuariEntitatFavorit> llistat(ModelAndView mav, HttpServletRequest request,
     UsuariEntitatFavoritFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<UsuariEntitatFavorit> usuariEntitatFavorit = processarLlistat(usuariEntitatFavoritEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("usuariEntitatFavoritItems", usuariEntitatFavorit);

    mav.addObject("usuariEntitatFavoritFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, usuariEntitatFavorit, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, usuariEntitatFavorit);

    return usuariEntitatFavorit;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(UsuariEntitatFavoritFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<UsuariEntitatFavorit> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field origenID
    {
      _listSKV = getReferenceListForOrigenID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForOrigenID(_tmp);
      if (filterForm.getGroupByFields().contains(ORIGENID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ORIGENID, false);
      };
    }

    // Field favoritID
    {
      _listSKV = getReferenceListForFavoritID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForFavoritID(_tmp);
      if (filterForm.getGroupByFields().contains(FAVORITID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FAVORITID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    UsuariEntitatFavoritFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<UsuariEntitatFavorit> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_USUARIENTITATFAVORIT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ORIGENID, filterForm.getMapOfUsuariEntitatForOrigenID());
    __mapping.put(FAVORITID, filterForm.getMapOfUsuariEntitatForFavoritID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou UsuariEntitatFavorit
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearUsuariEntitatFavoritGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    UsuariEntitatFavoritForm usuariEntitatFavoritForm = getUsuariEntitatFavoritForm(null, false, request, mav);
    mav.addObject("usuariEntitatFavoritForm" ,usuariEntitatFavoritForm);
    fillReferencesForForm(usuariEntitatFavoritForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public UsuariEntitatFavoritForm getUsuariEntitatFavoritForm(UsuariEntitatFavoritJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    UsuariEntitatFavoritForm usuariEntitatFavoritForm;
    if(_jpa == null) {
      usuariEntitatFavoritForm = new UsuariEntitatFavoritForm(new UsuariEntitatFavoritJPA(), true);
    } else {
      usuariEntitatFavoritForm = new UsuariEntitatFavoritForm(_jpa, false);
      usuariEntitatFavoritForm.setView(__isView);
    }
    usuariEntitatFavoritForm.setContexte(getContextWeb());
    usuariEntitatFavoritForm.setEntityNameCode(getEntityNameCode());
    usuariEntitatFavoritForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return usuariEntitatFavoritForm;
  }

  public void fillReferencesForForm(UsuariEntitatFavoritForm usuariEntitatFavoritForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (usuariEntitatFavoritForm.getListOfUsuariEntitatForOrigenID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForOrigenID(request, mav, usuariEntitatFavoritForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      usuariEntitatFavoritForm.setListOfUsuariEntitatForOrigenID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariEntitatFavoritForm.getListOfUsuariEntitatForFavoritID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFavoritID(request, mav, usuariEntitatFavoritForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      usuariEntitatFavoritForm.setListOfUsuariEntitatForFavoritID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou UsuariEntitatFavorit
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearUsuariEntitatFavoritPost(@ModelAttribute UsuariEntitatFavoritForm usuariEntitatFavoritForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    UsuariEntitatFavoritJPA usuariEntitatFavorit = usuariEntitatFavoritForm.getUsuariEntitatFavorit();

    try {
      preValidate(request, usuariEntitatFavoritForm, result);
      getWebValidator().validate(usuariEntitatFavoritForm, result);
      postValidate(request,usuariEntitatFavoritForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        usuariEntitatFavorit = create(request, usuariEntitatFavorit);
        createMessageSuccess(request, "success.creation", usuariEntitatFavorit.getID());
        usuariEntitatFavoritForm.setUsuariEntitatFavorit(usuariEntitatFavorit);
        return getRedirectWhenCreated(request, usuariEntitatFavoritForm);
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

  @RequestMapping(value = "/view/{iD}", method = RequestMethod.GET)
  public ModelAndView veureUsuariEntitatFavoritGet(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariEntitatFavoritGet(iD,
        request, response, true);
  }


  protected ModelAndView editAndViewUsuariEntitatFavoritGet(@PathVariable("iD") java.lang.Long iD,
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
    UsuariEntitatFavoritJPA usuariEntitatFavorit = findByPrimaryKey(request, iD);

    if (usuariEntitatFavorit == null) {
      createMessageWarning(request, "error.notfound", iD);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, iD), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      UsuariEntitatFavoritForm usuariEntitatFavoritForm = getUsuariEntitatFavoritForm(usuariEntitatFavorit, __isView, request, mav);
      usuariEntitatFavoritForm.setView(__isView);
      if(__isView) {
        usuariEntitatFavoritForm.setAllFieldsReadOnly(ALL_USUARIENTITATFAVORIT_FIELDS);
        usuariEntitatFavoritForm.setSaveButtonVisible(false);
        usuariEntitatFavoritForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(usuariEntitatFavoritForm, request, mav);
      mav.addObject("usuariEntitatFavoritForm", usuariEntitatFavoritForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un UsuariEntitatFavorit existent
   */
  @RequestMapping(value = "/{iD}/edit", method = RequestMethod.GET)
  public ModelAndView editarUsuariEntitatFavoritGet(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariEntitatFavoritGet(iD,
        request, response, false);
  }



  /**
   * Editar un UsuariEntitatFavorit existent
   */
  @RequestMapping(value = "/{iD}/edit", method = RequestMethod.POST)
  public String editarUsuariEntitatFavoritPost(@ModelAttribute @Valid UsuariEntitatFavoritForm usuariEntitatFavoritForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    UsuariEntitatFavoritJPA usuariEntitatFavorit = usuariEntitatFavoritForm.getUsuariEntitatFavorit();

    try {
      preValidate(request, usuariEntitatFavoritForm, result);
      getWebValidator().validate(usuariEntitatFavorit, result);
      postValidate(request, usuariEntitatFavoritForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        usuariEntitatFavorit = update(request, usuariEntitatFavorit);
        createMessageSuccess(request, "success.modification", usuariEntitatFavorit.getID());
        status.setComplete();
        return getRedirectWhenModified(request, usuariEntitatFavoritForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          usuariEntitatFavorit.getID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, usuariEntitatFavoritForm, __e);
    }

  }


  /**
   * Eliminar un UsuariEntitatFavorit existent
   */
  @RequestMapping(value = "/{iD}/delete")
  public String eliminarUsuariEntitatFavorit(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      UsuariEntitatFavorit usuariEntitatFavorit = usuariEntitatFavoritEjb.findByPrimaryKey(iD);
      if (usuariEntitatFavorit == null) {
        String __msg =createMessageError(request, "error.notfound", iD);
        return getRedirectWhenDelete(request, iD, new Exception(__msg));
      } else {
        delete(request, usuariEntitatFavorit);
        createMessageSuccess(request, "success.deleted", iD);
        return getRedirectWhenDelete(request, iD,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", iD, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, iD, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute UsuariEntitatFavoritFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarUsuariEntitatFavorit(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __iD, Throwable e) {
    java.lang.Long iD = (java.lang.Long)__iD;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (iD == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(iD),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "usuariEntitatFavorit.usuariEntitatFavorit";
  }

  public String getEntityNameCodePlural() {
    return "usuariEntitatFavorit.usuariEntitatFavorit.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("usuariEntitatFavorit.ID");
  }

  @InitBinder("usuariEntitatFavoritFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("usuariEntitatFavoritForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("ID");

  }

  public UsuariEntitatFavoritWebValidator getWebValidator() {
    return usuariEntitatFavoritWebValidator;
  }


  public void setWebValidator(UsuariEntitatFavoritWebValidator __val) {
    if (__val != null) {
      this.usuariEntitatFavoritWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de UsuariEntitatFavorit
   */
  @RequestMapping(value = "/{iD}/cancel")
  public String cancelUsuariEntitatFavorit(@PathVariable("iD") java.lang.Long iD,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, iD);
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


  public List<StringKeyValue> getReferenceListForOrigenID(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatFavoritForm usuariEntitatFavoritForm, Where where)  throws I18NException {
    if (usuariEntitatFavoritForm.isHiddenField(ORIGENID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  usuariEntitatFavoritForm.getStringOfField(ORIGENID);
    Where _where = null;
    if (usuariEntitatFavoritForm.isReadOnlyField(_fieldName)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatFavoritForm.getUsuariEntitatFavorit().getOrigenID());
    }
    return getReferenceListForOrigenID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForOrigenID(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatFavoritFilterForm usuariEntitatFavoritFilterForm,
       List<UsuariEntitatFavorit> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariEntitatFavoritFilterForm.isHiddenField(ORIGENID)
      && !usuariEntitatFavoritFilterForm.isGroupByField(ORIGENID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ORIGENID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (UsuariEntitatFavorit _item : list) {
        _pkList.add(_item.getOrigenID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForOrigenID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForOrigenID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForFavoritID(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatFavoritForm usuariEntitatFavoritForm, Where where)  throws I18NException {
    if (usuariEntitatFavoritForm.isHiddenField(FAVORITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  usuariEntitatFavoritForm.getStringOfField(FAVORITID);
    Where _where = null;
    if (usuariEntitatFavoritForm.isReadOnlyField(_fieldName)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatFavoritForm.getUsuariEntitatFavorit().getFavoritID());
    }
    return getReferenceListForFavoritID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFavoritID(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatFavoritFilterForm usuariEntitatFavoritFilterForm,
       List<UsuariEntitatFavorit> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariEntitatFavoritFilterForm.isHiddenField(FAVORITID)
      && !usuariEntitatFavoritFilterForm.isGroupByField(FAVORITID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FAVORITID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (UsuariEntitatFavorit _item : list) {
        _pkList.add(_item.getFavoritID());
        }
        _w = UsuariEntitatFields.USUARIENTITATID.in(_pkList);
      }
    return getReferenceListForFavoritID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFavoritID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariEntitatRefList.getReferenceList(UsuariEntitatFields.USUARIENTITATID, where );
  }


  public void preValidate(HttpServletRequest request,UsuariEntitatFavoritForm usuariEntitatFavoritForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,UsuariEntitatFavoritForm usuariEntitatFavoritForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, UsuariEntitatFavoritFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, UsuariEntitatFavoritFilterForm filterForm,  List<UsuariEntitatFavorit> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, UsuariEntitatFavoritForm usuariEntitatFavoritForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, UsuariEntitatFavoritForm usuariEntitatFavoritForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long iD, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long iD) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "usuariEntitatFavoritFormWebDB";
  }

  public String getTileList() {
    return "usuariEntitatFavoritListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "UsuariEntitatFavoritWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public UsuariEntitatFavoritJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long iD) throws I18NException {
    return (UsuariEntitatFavoritJPA) usuariEntitatFavoritEjb.findByPrimaryKey(iD);
  }


  public UsuariEntitatFavoritJPA create(HttpServletRequest request, UsuariEntitatFavoritJPA usuariEntitatFavorit)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatFavoritJPA) usuariEntitatFavoritEjb.create(usuariEntitatFavorit);
  }


  public UsuariEntitatFavoritJPA update(HttpServletRequest request, UsuariEntitatFavoritJPA usuariEntitatFavorit)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariEntitatFavoritJPA) usuariEntitatFavoritEjb.update(usuariEntitatFavorit);
  }


  public void delete(HttpServletRequest request, UsuariEntitatFavorit usuariEntitatFavorit) throws Exception,I18NException {
    usuariEntitatFavoritEjb.delete(usuariEntitatFavorit);
  }

} // Final de Classe

