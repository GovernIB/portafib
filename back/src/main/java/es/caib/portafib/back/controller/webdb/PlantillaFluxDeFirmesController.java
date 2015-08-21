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
import es.caib.portafib.back.form.webdb.PlantillaFluxDeFirmesForm;

import es.caib.portafib.back.validator.webdb.PlantillaFluxDeFirmesWebValidator;

import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.fields.*;

/**
 * Controller per gestionar un PlantillaFluxDeFirmes
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/plantillaFluxDeFirmes")
@SessionAttributes(types = { PlantillaFluxDeFirmesForm.class, PlantillaFluxDeFirmesFilterForm.class })
public class PlantillaFluxDeFirmesController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<PlantillaFluxDeFirmes, java.lang.Long> implements PlantillaFluxDeFirmesFields {

  @EJB(mappedName = es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;

  @Autowired
  private PlantillaFluxDeFirmesWebValidator plantillaFluxDeFirmesWebValidator;

  @Autowired
  protected PlantillaFluxDeFirmesRefList plantillaFluxDeFirmesRefList;

  // References 
  @Autowired
  protected FluxDeFirmesRefList fluxDeFirmesRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  /**
   * Llistat de totes PlantillaFluxDeFirmes
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PlantillaFluxDeFirmesFilterForm ff;
    ff = (PlantillaFluxDeFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PlantillaFluxDeFirmes de forma paginada
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
    llistat(mav, request, getPlantillaFluxDeFirmesFilterForm(pagina, mav, request));
    return mav;
  }

  public PlantillaFluxDeFirmesFilterForm getPlantillaFluxDeFirmesFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PlantillaFluxDeFirmesFilterForm plantillaFluxDeFirmesFilterForm;
    plantillaFluxDeFirmesFilterForm = (PlantillaFluxDeFirmesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(plantillaFluxDeFirmesFilterForm == null) {
      plantillaFluxDeFirmesFilterForm = new PlantillaFluxDeFirmesFilterForm();
      plantillaFluxDeFirmesFilterForm.setContexte(getContextWeb());
      plantillaFluxDeFirmesFilterForm.setEntityNameCode(getEntityNameCode());
      plantillaFluxDeFirmesFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      plantillaFluxDeFirmesFilterForm.setNou(true);
    } else {
      plantillaFluxDeFirmesFilterForm.setNou(false);
    }
    plantillaFluxDeFirmesFilterForm.setPage(pagina == null ? 1 : pagina);
    return plantillaFluxDeFirmesFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PlantillaFluxDeFirmes de forma paginada
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
      @ModelAttribute PlantillaFluxDeFirmesFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPlantillaFluxDeFirmesFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PlantillaFluxDeFirmes de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PlantillaFluxDeFirmes> llistat(ModelAndView mav, HttpServletRequest request,
     PlantillaFluxDeFirmesFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    preList(request, mav, filterForm);

    List<PlantillaFluxDeFirmes> plantillaFluxDeFirmes = (List<PlantillaFluxDeFirmes>) processarLlistat(plantillaFluxDeFirmesEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("plantillaFluxDeFirmesItems", plantillaFluxDeFirmes);

    mav.addObject("plantillaFluxDeFirmesFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, plantillaFluxDeFirmes, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, plantillaFluxDeFirmes);

    return plantillaFluxDeFirmes;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PlantillaFluxDeFirmesFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PlantillaFluxDeFirmes> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field fluxDeFirmesID
    {
      _listSKV = getReferenceListForFluxDeFirmesID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfFluxDeFirmesForFluxDeFirmesID(_tmp);
      if (filterForm.getGroupByFields().contains(FLUXDEFIRMESID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FLUXDEFIRMESID, false);
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


      fillValuesToGroupByItemsBoolean("compartirplantilla", groupByItemsMap, COMPARTIR);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PlantillaFluxDeFirmesFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PlantillaFluxDeFirmes> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PLANTILLAFLUXDEFIRMES_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(FLUXDEFIRMESID, filterForm.getMapOfFluxDeFirmesForFluxDeFirmesID());
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    __mapping.put(USUARIAPLICACIOID, filterForm.getMapOfUsuariAplicacioForUsuariAplicacioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PlantillaFluxDeFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPlantillaFluxDeFirmesGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm = getPlantillaFluxDeFirmesForm(null, false, request, mav);
    mav.addObject("plantillaFluxDeFirmesForm" ,plantillaFluxDeFirmesForm);
    fillReferencesForForm(plantillaFluxDeFirmesForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PlantillaFluxDeFirmesForm getPlantillaFluxDeFirmesForm(PlantillaFluxDeFirmesJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm;
    if(_jpa == null) {
      plantillaFluxDeFirmesForm = new PlantillaFluxDeFirmesForm(new PlantillaFluxDeFirmesJPA(), true);
    } else {
      plantillaFluxDeFirmesForm = new PlantillaFluxDeFirmesForm(_jpa, false);
      plantillaFluxDeFirmesForm.setView(__isView);
    }
    plantillaFluxDeFirmesForm.setContexte(getContextWeb());
    plantillaFluxDeFirmesForm.setEntityNameCode(getEntityNameCode());
    plantillaFluxDeFirmesForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return plantillaFluxDeFirmesForm;
  }

  public void fillReferencesForForm(PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (plantillaFluxDeFirmesForm.getListOfFluxDeFirmesForFluxDeFirmesID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFluxDeFirmesID(request, mav, plantillaFluxDeFirmesForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      plantillaFluxDeFirmesForm.setListOfFluxDeFirmesForFluxDeFirmesID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (plantillaFluxDeFirmesForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, plantillaFluxDeFirmesForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      plantillaFluxDeFirmesForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (plantillaFluxDeFirmesForm.getListOfUsuariAplicacioForUsuariAplicacioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariAplicacioID(request, mav, plantillaFluxDeFirmesForm, null);

      java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      plantillaFluxDeFirmesForm.setListOfUsuariAplicacioForUsuariAplicacioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PlantillaFluxDeFirmes
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPlantillaFluxDeFirmesPost(@ModelAttribute PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes();

    try {
      preValidate(request, plantillaFluxDeFirmesForm, result);
      getWebValidator().validate(plantillaFluxDeFirmesForm, result);
      postValidate(request,plantillaFluxDeFirmesForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        plantillaFluxDeFirmes = create(request, plantillaFluxDeFirmes);
        createMessageSuccess(request, "success.creation", plantillaFluxDeFirmes.getFluxDeFirmesID());
        plantillaFluxDeFirmesForm.setPlantillaFluxDeFirmes(plantillaFluxDeFirmes);
        return getRedirectWhenCreated(request, plantillaFluxDeFirmesForm);
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

  @RequestMapping(value = "/view/{fluxDeFirmesID}", method = RequestMethod.GET)
  public ModelAndView veurePlantillaFluxDeFirmesGet(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPlantillaFluxDeFirmesGet(fluxDeFirmesID,
        request, response, true);
  }


  protected ModelAndView editAndViewPlantillaFluxDeFirmesGet(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
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
    PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes = findByPrimaryKey(request, fluxDeFirmesID);

    if (plantillaFluxDeFirmes == null) {
      createMessageWarning(request, "error.notfound", fluxDeFirmesID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, fluxDeFirmesID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm = getPlantillaFluxDeFirmesForm(plantillaFluxDeFirmes, __isView, request, mav);
      plantillaFluxDeFirmesForm.setView(__isView);
      if(__isView) {
        plantillaFluxDeFirmesForm.setAllFieldsReadOnly(ALL_PLANTILLAFLUXDEFIRMES_FIELDS);
        plantillaFluxDeFirmesForm.setSaveButtonVisible(false);
        plantillaFluxDeFirmesForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(plantillaFluxDeFirmesForm, request, mav);
      mav.addObject("plantillaFluxDeFirmesForm", plantillaFluxDeFirmesForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PlantillaFluxDeFirmes existent
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPlantillaFluxDeFirmesGet(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPlantillaFluxDeFirmesGet(fluxDeFirmesID,
        request, response, false);
  }



  /**
   * Editar un PlantillaFluxDeFirmes existent
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/edit", method = RequestMethod.POST)
  public String editarPlantillaFluxDeFirmesPost(@ModelAttribute @Valid PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes();

    try {
      preValidate(request, plantillaFluxDeFirmesForm, result);
      getWebValidator().validate(plantillaFluxDeFirmes, result);
      postValidate(request, plantillaFluxDeFirmesForm, result);

      if (result.hasErrors()) {
        return getTileForm();
      } else {
        plantillaFluxDeFirmes = update(request, plantillaFluxDeFirmes);
        createMessageSuccess(request, "success.modification", plantillaFluxDeFirmes.getFluxDeFirmesID());
        status.setComplete();
        return getRedirectWhenModified(request, plantillaFluxDeFirmesForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          plantillaFluxDeFirmes.getFluxDeFirmesID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, plantillaFluxDeFirmesForm, __e);
    }

  }


  /**
   * Eliminar un PlantillaFluxDeFirmes existent
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/delete")
  public String eliminarPlantillaFluxDeFirmes(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PlantillaFluxDeFirmes plantillaFluxDeFirmes = plantillaFluxDeFirmesEjb.findByPrimaryKey(fluxDeFirmesID);
      if (plantillaFluxDeFirmes == null) {
        String __msg =createMessageError(request, "error.notfound", fluxDeFirmesID);
        return getRedirectWhenDelete(request, fluxDeFirmesID, new Exception(__msg));
      } else {
        delete(request, plantillaFluxDeFirmes);
        createMessageSuccess(request, "success.deleted", fluxDeFirmesID);
        return getRedirectWhenDelete(request, fluxDeFirmesID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", fluxDeFirmesID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, fluxDeFirmesID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PlantillaFluxDeFirmesFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPlantillaFluxDeFirmes(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __fluxDeFirmesID, Throwable e) {
    java.lang.Long fluxDeFirmesID = (java.lang.Long)__fluxDeFirmesID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (fluxDeFirmesID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(fluxDeFirmesID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "plantillaFluxDeFirmes.plantillaFluxDeFirmes";
  }

  public String getEntityNameCodePlural() {
    return "plantillaFluxDeFirmes.plantillaFluxDeFirmes.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("plantillaFluxDeFirmes.fluxDeFirmesID");
  }

  @InitBinder("plantillaFluxDeFirmesFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("plantillaFluxDeFirmesForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


  }

  public PlantillaFluxDeFirmesWebValidator getWebValidator() {
    return plantillaFluxDeFirmesWebValidator;
  }


  public void setWebValidator(PlantillaFluxDeFirmesWebValidator __val) {
    if (__val != null) {
      this.plantillaFluxDeFirmesWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PlantillaFluxDeFirmes
   */
  @RequestMapping(value = "/{fluxDeFirmesID}/cancel")
  public String cancelPlantillaFluxDeFirmes(@PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, fluxDeFirmesID);
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


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm, Where where)  throws I18NException {
    if (plantillaFluxDeFirmesForm.isHiddenField(FLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  plantillaFluxDeFirmesForm.getStringOfField(FLUXDEFIRMESID);
    Where _where = null;
    if (plantillaFluxDeFirmesForm.isReadOnlyField(_fieldName)) {
      _where = FluxDeFirmesFields.FLUXDEFIRMESID.equal(plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getFluxDeFirmesID());
    }
    return getReferenceListForFluxDeFirmesID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, PlantillaFluxDeFirmesFilterForm plantillaFluxDeFirmesFilterForm,
       List<PlantillaFluxDeFirmes> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (plantillaFluxDeFirmesFilterForm.isHiddenField(FLUXDEFIRMESID)
      && !plantillaFluxDeFirmesFilterForm.isGroupByField(FLUXDEFIRMESID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FLUXDEFIRMESID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PlantillaFluxDeFirmes _item : list) {
        _pkList.add(_item.getFluxDeFirmesID());
        }
        _w = FluxDeFirmesFields.FLUXDEFIRMESID.in(_pkList);
      }
    return getReferenceListForFluxDeFirmesID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFluxDeFirmesID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return fluxDeFirmesRefList.getReferenceList(FluxDeFirmesFields.FLUXDEFIRMESID, where );
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm, Where where)  throws I18NException {
    if (plantillaFluxDeFirmesForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  plantillaFluxDeFirmesForm.getStringOfField(USUARIENTITATID);
    Where _where = null;
    if (plantillaFluxDeFirmesForm.isReadOnlyField(_fieldName)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, PlantillaFluxDeFirmesFilterForm plantillaFluxDeFirmesFilterForm,
       List<PlantillaFluxDeFirmes> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (plantillaFluxDeFirmesFilterForm.isHiddenField(USUARIENTITATID)
      && !plantillaFluxDeFirmesFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PlantillaFluxDeFirmes _item : list) {
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
       ModelAndView mav, PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm, Where where)  throws I18NException {
    if (plantillaFluxDeFirmesForm.isHiddenField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    final String _fieldName =  plantillaFluxDeFirmesForm.getStringOfField(USUARIAPLICACIOID);
    Where _where = null;
    if (plantillaFluxDeFirmesForm.isReadOnlyField(_fieldName)) {
      _where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getUsuariAplicacioID());
    }
    return getReferenceListForUsuariAplicacioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
       ModelAndView mav, PlantillaFluxDeFirmesFilterForm plantillaFluxDeFirmesFilterForm,
       List<PlantillaFluxDeFirmes> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (plantillaFluxDeFirmesFilterForm.isHiddenField(USUARIAPLICACIOID)
      && !plantillaFluxDeFirmesFilterForm.isGroupByField(USUARIAPLICACIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIAPLICACIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (PlantillaFluxDeFirmes _item : list) {
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


  public void preValidate(HttpServletRequest request,PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PlantillaFluxDeFirmesFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PlantillaFluxDeFirmesFilterForm filterForm,  List<PlantillaFluxDeFirmes> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long fluxDeFirmesID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long fluxDeFirmesID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "plantillaFluxDeFirmesFormWebDB";
  }

  public String getTileList() {
    return "plantillaFluxDeFirmesListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PlantillaFluxDeFirmesWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PlantillaFluxDeFirmesJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long fluxDeFirmesID) throws I18NException {
    return (PlantillaFluxDeFirmesJPA) plantillaFluxDeFirmesEjb.findByPrimaryKey(fluxDeFirmesID);
  }


  public PlantillaFluxDeFirmesJPA create(HttpServletRequest request, PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (PlantillaFluxDeFirmesJPA) plantillaFluxDeFirmesEjb.create(plantillaFluxDeFirmes);
  }


  public PlantillaFluxDeFirmesJPA update(HttpServletRequest request, PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes)
    throws Exception,I18NException, I18NValidationException {
    return (PlantillaFluxDeFirmesJPA) plantillaFluxDeFirmesEjb.update(plantillaFluxDeFirmes);
  }


  public void delete(HttpServletRequest request, PlantillaFluxDeFirmes plantillaFluxDeFirmes) throws Exception,I18NException {
    plantillaFluxDeFirmesEjb.delete(plantillaFluxDeFirmes);
  }

} // Final de Classe

