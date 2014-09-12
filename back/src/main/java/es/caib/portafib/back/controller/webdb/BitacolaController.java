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
import es.caib.portafib.back.form.webdb.BitacolaForm;

import es.caib.portafib.back.validator.webdb.BitacolaWebValidator;

import es.caib.portafib.jpa.BitacolaJPA;
import es.caib.portafib.model.entity.Bitacola;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un Bitacola
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/bitacola")
@SessionAttributes(types = { BitacolaForm.class, BitacolaFilterForm.class })
public class BitacolaController
    extends es.caib.portafib.back.controller.PortaFIBBaseController implements BitacolaFields {

  @EJB(mappedName = es.caib.portafib.ejb.BitacolaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.BitacolaLocal bitacolaEjb;

  @Autowired
  private BitacolaWebValidator bitacolaWebValidator;

  @Autowired
  protected BitacolaRefList bitacolaRefList;

  // References 
  @Autowired
  protected PeticioDeFirmaRefList peticioDeFirmaRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes Bitacola
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    BitacolaFilterForm ff;
    ff = (BitacolaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Bitacola de forma paginada
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
    llistat(mav, request, getBitacolaFilterForm(pagina, mav, request));
    return mav;
  }

  public BitacolaFilterForm getBitacolaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    BitacolaFilterForm bitacolaFilterForm;
    bitacolaFilterForm = (BitacolaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(bitacolaFilterForm == null) {
      bitacolaFilterForm = new BitacolaFilterForm();
      bitacolaFilterForm.setContexte(getContextWeb());
      bitacolaFilterForm.setEntityNameCode(getEntityNameCode());
      bitacolaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      bitacolaFilterForm.setNou(true);
    } else {
      bitacolaFilterForm.setNou(false);
    }
    bitacolaFilterForm.setPage(pagina == null ? 1 : pagina);
    return bitacolaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Bitacola de forma paginada
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
      @ModelAttribute BitacolaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getBitacolaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Bitacola de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Bitacola> llistat(ModelAndView mav, HttpServletRequest request,
     BitacolaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<Bitacola> bitacola = (List<Bitacola>) processarLlistat(bitacolaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("bitacolaItems", bitacola);

    mav.addObject("bitacolaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, bitacola, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, bitacola);

    return bitacola;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(BitacolaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Bitacola> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field usuariEntitatID
    {
      _listSKV = getReferenceListForUsuariEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariEntitatForUsuariEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    BitacolaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Bitacola> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_BITACOLA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PETICIODEFIRMAID, filterForm.getMapOfPeticioDeFirmaForPeticioDeFirmaID());
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Bitacola
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearBitacolaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    BitacolaForm bitacolaForm = getBitacolaForm(null, false, request, mav);
    mav.addObject("bitacolaForm" ,bitacolaForm);
    fillReferencesForForm(bitacolaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public BitacolaForm getBitacolaForm(BitacolaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    BitacolaForm bitacolaForm;
    if(_jpa == null) {
      bitacolaForm = new BitacolaForm(new BitacolaJPA(), true);
    } else {
      bitacolaForm = new BitacolaForm(_jpa, false);
      bitacolaForm.setView(__isView);
    }
    bitacolaForm.setContexte(getContextWeb());
    bitacolaForm.setEntityNameCode(getEntityNameCode());
    bitacolaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return bitacolaForm;
  }

  public void fillReferencesForForm(BitacolaForm bitacolaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (bitacolaForm.getListOfPeticioDeFirmaForPeticioDeFirmaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPeticioDeFirmaID(request, mav, bitacolaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      bitacolaForm.setListOfPeticioDeFirmaForPeticioDeFirmaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (bitacolaForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, bitacolaForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      bitacolaForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Bitacola
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearBitacolaPost(@ModelAttribute BitacolaForm bitacolaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    BitacolaJPA bitacola = bitacolaForm.getBitacola();

    try {
      preValidate(bitacolaForm, result);
      getWebValidator().validate(bitacolaForm, result);
      postValidate(bitacolaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        bitacola = create(bitacola);
        createMessageSuccess(request, "success.creation", bitacola.getBitacolaID());
        bitacolaForm.setBitacola(bitacola);
        return getRedirectWhenCreated(bitacolaForm);
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

  @RequestMapping(value = "/view/{bitacolaID}", method = RequestMethod.GET)
  public ModelAndView veureBitacolaGet(@PathVariable("bitacolaID") java.lang.Long bitacolaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewBitacolaGet(bitacolaID,
        request, response, true);
  }


  protected ModelAndView editAndViewBitacolaGet(@PathVariable("bitacolaID") java.lang.Long bitacolaID,
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
    BitacolaJPA bitacola = findByPrimaryKey(bitacolaID);

    if (bitacola == null) {
      createMessageWarning(request, "error.notfound", bitacolaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(bitacolaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      BitacolaForm bitacolaForm = getBitacolaForm(bitacola, __isView, request, mav);
      bitacolaForm.setView(__isView);
      if(__isView) {
        bitacolaForm.setAllFieldsReadOnly(ALL_BITACOLA_FIELDS);
        bitacolaForm.setSaveButtonVisible(false);
        bitacolaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(bitacolaForm, request, mav);
      mav.addObject("bitacolaForm", bitacolaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Bitacola existent
   */
  @RequestMapping(value = "/{bitacolaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarBitacolaGet(@PathVariable("bitacolaID") java.lang.Long bitacolaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewBitacolaGet(bitacolaID,
        request, response, false);
  }



  /**
   * Editar un Bitacola existent
   */
  @RequestMapping(value = "/{bitacolaID}/edit", method = RequestMethod.POST)
  public String editarBitacolaPost(@ModelAttribute @Valid BitacolaForm bitacolaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    BitacolaJPA bitacola = bitacolaForm.getBitacola();

    try {
      preValidate(bitacolaForm, result);
      getWebValidator().validate(bitacola, result);
      postValidate(bitacolaForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        bitacola = update(bitacola);
        createMessageSuccess(request, "success.modification", bitacola.getBitacolaID());
        status.setComplete();
        return getRedirectWhenModified(bitacolaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          bitacola.getBitacolaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(bitacolaForm, __e);
    }

  }


  /**
   * Eliminar un Bitacola existent
   */
  @RequestMapping(value = "/{bitacolaID}/delete")
  public String eliminarBitacola(@PathVariable("bitacolaID") java.lang.Long bitacolaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Bitacola bitacola = bitacolaEjb.findByPrimaryKey(bitacolaID);
      if (bitacola == null) {
        String __msg =createMessageError(request, "error.notfound", bitacolaID);
        return getRedirectWhenDelete(bitacolaID, new Exception(__msg));
      } else {
        delete(request, bitacola);
        createMessageSuccess(request, "success.deleted", bitacolaID);
        return getRedirectWhenDelete(bitacolaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", bitacolaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(bitacolaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute BitacolaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarBitacola(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return new java.lang.Long(value);
}

  @Override
  public String[] getArgumentsMissatge(Object __bitacolaID, Throwable e) {
    java.lang.Long bitacolaID = (java.lang.Long)__bitacolaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (bitacolaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(bitacolaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "bitacola.bitacola";
  }

  public String getEntityNameCodePlural() {
    return "bitacola.bitacola.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("bitacola.bitacolaID");
  }

  @InitBinder("bitacolaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("bitacolaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("bitacolaID");

  }

  public BitacolaWebValidator getWebValidator() {
    return bitacolaWebValidator;
  }


  public void setWebValidator(BitacolaWebValidator __val) {
    if (__val != null) {
      this.bitacolaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Bitacola
   */
  @RequestMapping(value = "/{bitacolaID}/cancel")
  public String cancelBitacola(@PathVariable("bitacolaID") java.lang.Long bitacolaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(bitacolaID);
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


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, BitacolaForm bitacolaForm, Where where)  throws I18NException {
    if (bitacolaForm.isHiddenField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  bitacolaForm.getStringOfField(PETICIODEFIRMAID);
    Where _where = null;
    if (bitacolaForm.isReadOnlyField(_fieldName)) {
      _where = PeticioDeFirmaFields.PETICIODEFIRMAID.equal(bitacolaForm.getBitacola().getPeticioDeFirmaID());
    }
    return getReferenceListForPeticioDeFirmaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPeticioDeFirmaID(HttpServletRequest request,
       ModelAndView mav, BitacolaFilterForm bitacolaFilterForm,
       List<Bitacola> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (bitacolaFilterForm.isHiddenField(PETICIODEFIRMAID)
      && !bitacolaFilterForm.isGroupByField(PETICIODEFIRMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PETICIODEFIRMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Bitacola _item : list) {
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


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, BitacolaForm bitacolaForm, Where where)  throws I18NException {
    if (bitacolaForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  bitacolaForm.getStringOfField(USUARIENTITATID);
    Where _where = null;
    if (bitacolaForm.isReadOnlyField(_fieldName)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(bitacolaForm.getBitacola().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, BitacolaFilterForm bitacolaFilterForm,
       List<Bitacola> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (bitacolaFilterForm.isHiddenField(USUARIENTITATID)
      && !bitacolaFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (Bitacola _item : list) {
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


  public void preValidate(BitacolaForm bitacolaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(BitacolaForm bitacolaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, BitacolaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, BitacolaFilterForm filterForm,  List<Bitacola> list) throws I18NException {
  }

  public String getRedirectWhenCreated(BitacolaForm bitacolaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(BitacolaForm bitacolaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(java.lang.Long bitacolaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(java.lang.Long bitacolaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "bitacolaFormWebDB";
  }

  public String getTileList() {
    return "bitacolaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "BitacolaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public BitacolaJPA findByPrimaryKey(java.lang.Long bitacolaID) throws I18NException {
    return (BitacolaJPA) bitacolaEjb.findByPrimaryKey(bitacolaID);
  }


  public BitacolaJPA create(BitacolaJPA bitacola)
    throws Exception,I18NException, I18NValidationException {
    return (BitacolaJPA) bitacolaEjb.create(bitacola);
  }


  public void delete(HttpServletRequest request, Bitacola bitacola) throws Exception,I18NException {
    bitacolaEjb.delete(bitacola);
  }


  public BitacolaJPA update(BitacolaJPA bitacola)
    throws Exception,I18NException, I18NValidationException {
    return (BitacolaJPA) bitacolaEjb.update(bitacola);
  }

} // Final de Classe

