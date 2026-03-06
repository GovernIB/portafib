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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;

import es.caib.portafib.back.form.webdb.*;
import es.caib.portafib.back.form.webdb.CorreuAgrupatForm;

import es.caib.portafib.back.validator.webdb.CorreuAgrupatWebValidator;

import es.caib.portafib.persistence.CorreuAgrupatJPA;
import es.caib.portafib.model.entity.CorreuAgrupat;
import es.caib.portafib.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import es.caib.portafib.back.utils.Tab;

/**
 * Controller per gestionar un CorreuAgrupat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="correuAgrupat.correuAgrupat.plural", order=60, group=Tab.MENU_WEBDB)
@Controller
@RequestMapping(value = "/webdb/correuAgrupat")
@SessionAttributes(types = { CorreuAgrupatForm.class, CorreuAgrupatFilterForm.class })
@Tile(name="correuAgrupatFormWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe
    contentJsp="/WEB-INF/jsp/webdb/correuAgrupatForm.jsp", type=TileType.WEBDB_FORM,
    attributes={ @TileAttribute(name="titol", value="correuAgrupat.correuAgrupat")})
@Tile(name="correuAgrupatListWebDB", extendsTile=Tab.MENU_WEBDB,
    // Els següents atributs no són necessaris si heredes aquesta classe 
    contentJsp="/WEB-INF/jsp/webdb/correuAgrupatList.jsp", type=TileType.WEBDB_LIST,
    attributes={ @TileAttribute(name="titol", value="correuAgrupat.correuAgrupat")})
public class CorreuAgrupatController
    extends es.caib.portafib.back.controller.PortaFIBBaseController<CorreuAgrupat, java.lang.Long> implements CorreuAgrupatFields {

  @EJB(mappedName = es.caib.portafib.ejb.CorreuAgrupatService.JNDI_NAME)
  protected es.caib.portafib.ejb.CorreuAgrupatService correuAgrupatEjb;

  @Autowired
  private CorreuAgrupatWebValidator correuAgrupatWebValidator;

  @Autowired
  protected CorreuAgrupatRefList correuAgrupatRefList;

  // References 
  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  /**
   * Llistat de totes CorreuAgrupat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    CorreuAgrupatFilterForm ff;
    ff = (CorreuAgrupatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar CorreuAgrupat de forma paginada
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
    llistat(mav, request, getCorreuAgrupatFilterForm(pagina, mav, request));
    return mav;
  }

  public CorreuAgrupatFilterForm getCorreuAgrupatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    CorreuAgrupatFilterForm correuAgrupatFilterForm;
    correuAgrupatFilterForm = (CorreuAgrupatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(correuAgrupatFilterForm == null) {
      correuAgrupatFilterForm = new CorreuAgrupatFilterForm();
      correuAgrupatFilterForm.setContexte(getContextWeb());
      correuAgrupatFilterForm.setEntityNameCode(getEntityNameCode());
      correuAgrupatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      correuAgrupatFilterForm.setNou(true);
    } else {
      correuAgrupatFilterForm.setNou(false);
    }
    correuAgrupatFilterForm.setPage(pagina == null ? 1 : pagina);
    return correuAgrupatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar CorreuAgrupat de forma paginada
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
      @ModelAttribute CorreuAgrupatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getCorreuAgrupatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de CorreuAgrupat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<CorreuAgrupat> llistat(ModelAndView mav, HttpServletRequest request,
     CorreuAgrupatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<CorreuAgrupat> correuAgrupat = processarLlistat(correuAgrupatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("correuAgrupatItems", correuAgrupat);

    mav.addObject("correuAgrupatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, correuAgrupat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, correuAgrupat);

    return correuAgrupat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(CorreuAgrupatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<CorreuAgrupat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, HTML);

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
    CorreuAgrupatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<CorreuAgrupat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CORREUAGRUPAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIENTITATID, filterForm.getMapOfUsuariEntitatForUsuariEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou CorreuAgrupat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearCorreuAgrupatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    CorreuAgrupatForm correuAgrupatForm = getCorreuAgrupatForm(null, false, request, mav);
    mav.addObject("correuAgrupatForm" ,correuAgrupatForm);
    fillReferencesForForm(correuAgrupatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public CorreuAgrupatForm getCorreuAgrupatForm(CorreuAgrupatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    CorreuAgrupatForm correuAgrupatForm;
    if(_jpa == null) {
      correuAgrupatForm = new CorreuAgrupatForm(new CorreuAgrupatJPA(), true);
    } else {
      correuAgrupatForm = new CorreuAgrupatForm(_jpa, false);
      correuAgrupatForm.setView(__isView);
    }
    correuAgrupatForm.setContexte(getContextWeb());
    correuAgrupatForm.setEntityNameCode(getEntityNameCode());
    correuAgrupatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return correuAgrupatForm;
  }

  public void fillReferencesForForm(CorreuAgrupatForm correuAgrupatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (correuAgrupatForm.getListOfUsuariEntitatForUsuariEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariEntitatID(request, mav, correuAgrupatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      correuAgrupatForm.setListOfUsuariEntitatForUsuariEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou CorreuAgrupat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearCorreuAgrupatPost(@ModelAttribute CorreuAgrupatForm correuAgrupatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    CorreuAgrupatJPA correuAgrupat = correuAgrupatForm.getCorreuAgrupat();

    try {
      preValidate(request, correuAgrupatForm, result);
      getWebValidator().validate(correuAgrupatForm, result);
      postValidate(request,correuAgrupatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        correuAgrupat = create(request, correuAgrupat);
        createMessageSuccess(request, "success.creation", correuAgrupat.getCorreuAgrupatID());
        correuAgrupatForm.setCorreuAgrupat(correuAgrupat);
        return getRedirectWhenCreated(request, correuAgrupatForm);
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

  @RequestMapping(value = "/view/{correuAgrupatID}", method = RequestMethod.GET)
  public ModelAndView veureCorreuAgrupatGet(@PathVariable("correuAgrupatID") java.lang.Long correuAgrupatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCorreuAgrupatGet(correuAgrupatID,
        request, response, true);
  }


  protected ModelAndView editAndViewCorreuAgrupatGet(@PathVariable("correuAgrupatID") java.lang.Long correuAgrupatID,
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
    CorreuAgrupatJPA correuAgrupat = findByPrimaryKey(request, correuAgrupatID);

    if (correuAgrupat == null) {
      createMessageWarning(request, "error.notfound", correuAgrupatID);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      CorreuAgrupatForm correuAgrupatForm = getCorreuAgrupatForm(correuAgrupat, __isView, request, mav);
      correuAgrupatForm.setView(__isView);
      if(__isView) {
        correuAgrupatForm.setAllFieldsReadOnly(ALL_CORREUAGRUPAT_FIELDS);
        correuAgrupatForm.setSaveButtonVisible(false);
        correuAgrupatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(correuAgrupatForm, request, mav);
      mav.addObject("correuAgrupatForm", correuAgrupatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un CorreuAgrupat existent
   */
  @RequestMapping(value = "/{correuAgrupatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarCorreuAgrupatGet(@PathVariable("correuAgrupatID") java.lang.Long correuAgrupatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCorreuAgrupatGet(correuAgrupatID,
        request, response, false);
  }



  /**
   * Editar un CorreuAgrupat existent
   */
  @RequestMapping(value = "/{correuAgrupatID}/edit", method = RequestMethod.POST)
  public String editarCorreuAgrupatPost(@ModelAttribute CorreuAgrupatForm correuAgrupatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    CorreuAgrupatJPA correuAgrupat = correuAgrupatForm.getCorreuAgrupat();

    try {
      preValidate(request, correuAgrupatForm, result);
      getWebValidator().validate(correuAgrupatForm, result);
      postValidate(request, correuAgrupatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        correuAgrupat = update(request, correuAgrupat);
        createMessageSuccess(request, "success.modification", correuAgrupat.getCorreuAgrupatID());
        status.setComplete();
        return getRedirectWhenModified(request, correuAgrupatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          correuAgrupat.getCorreuAgrupatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, correuAgrupatForm, __e);
    }

  }


  /**
   * Eliminar un CorreuAgrupat existent
   */
  @RequestMapping(value = "/{correuAgrupatID}/delete")
  public String eliminarCorreuAgrupat(@PathVariable("correuAgrupatID") java.lang.Long correuAgrupatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      CorreuAgrupat correuAgrupat = this.findByPrimaryKey(request, correuAgrupatID);
      if (correuAgrupat == null) {
        String __msg = createMessageError(request, "error.notfound", correuAgrupatID);
        return getRedirectWhenDelete(request, correuAgrupatID, new Exception(__msg));
      } else {
        delete(request, correuAgrupat);
        createMessageSuccess(request, "success.deleted", correuAgrupatID);
        return getRedirectWhenDelete(request, correuAgrupatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", correuAgrupatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, correuAgrupatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute CorreuAgrupatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarCorreuAgrupat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __correuAgrupatID, Throwable e) {
    java.lang.Long correuAgrupatID = (java.lang.Long)__correuAgrupatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (correuAgrupatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(correuAgrupatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "correuAgrupat.correuAgrupat";
  }

  public String getEntityNameCodePlural() {
    return "correuAgrupat.correuAgrupat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("correuAgrupat.correuAgrupatID");
  }

  @InitBinder("correuAgrupatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("correuAgrupatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "correuAgrupat.correuAgrupatID");
  }

  public CorreuAgrupatWebValidator getWebValidator() {
    return correuAgrupatWebValidator;
  }


  public void setWebValidator(CorreuAgrupatWebValidator __val) {
    if (__val != null) {
      this.correuAgrupatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de CorreuAgrupat
   */
  @RequestMapping(value = "/{correuAgrupatID}/cancel")
  public String cancelCorreuAgrupat(@PathVariable("correuAgrupatID") java.lang.Long correuAgrupatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, correuAgrupatID);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de CorreuAgrupat
   */
  @RequestMapping(value = "/cancel")
  public String cancelCorreuAgrupat(HttpServletRequest request,HttpServletResponse response) {
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


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, CorreuAgrupatForm correuAgrupatForm, Where where)  throws I18NException {
    if (correuAgrupatForm.isHiddenField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (correuAgrupatForm.isReadOnlyField(USUARIENTITATID)) {
      _where = UsuariEntitatFields.USUARIENTITATID.equal(correuAgrupatForm.getCorreuAgrupat().getUsuariEntitatID());
    }
    return getReferenceListForUsuariEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request,
       ModelAndView mav, CorreuAgrupatFilterForm correuAgrupatFilterForm,
       List<CorreuAgrupat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (correuAgrupatFilterForm.isHiddenField(USUARIENTITATID)
       && !correuAgrupatFilterForm.isGroupByField(USUARIENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (CorreuAgrupat _item : list) {
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


    @Override
    /** Ha de ser igual que el RequestMapping de la Classe */
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        final String[] values = rm.value();
        if (values.length == 1) {
            return values[0];
        } else {
            final HttpServletRequest request;
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            final String servletPath = request.getServletPath();

            for (String webcontext : values) {
                if (servletPath.startsWith(webcontext)) {
                    return webcontext;
                }
            }

            log.warn(" No puc trobar el contextweb associat a la cridada.");
            log.warn(" ==== RequestMapping::value=" + Arrays.toString(values));
            log.warn(" ++++ getContextWeb::Scheme: " + request.getScheme());
            log.warn(" ++++ getContextWeb::PathInfo: " + request.getPathInfo());
            log.warn(" ++++ getContextWeb::PathTrans: " + request.getPathTranslated());
            log.warn(" ++++ getContextWeb::ContextPath: " + request.getContextPath());
            log.warn(" ++++ getContextWeb::ServletPath: " + request.getServletPath());
            log.warn(" ++++ getContextWeb::getRequestURI: " + request.getRequestURI());
            log.warn(" ++++ getContextWeb::getRequestURL: " + request.getRequestURL().toString());
            log.warn(" ++++ getContextWeb::getQueryString: " + request.getQueryString());

            return values[0];
        }  }

  public void preValidate(HttpServletRequest request,CorreuAgrupatForm correuAgrupatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,CorreuAgrupatForm correuAgrupatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, CorreuAgrupatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, CorreuAgrupatFilterForm filterForm,  List<CorreuAgrupat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, CorreuAgrupatForm correuAgrupatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, CorreuAgrupatForm correuAgrupatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long correuAgrupatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long correuAgrupatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_FORM) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileForm: " + e.getMessage(), e);
        }
    return "correuAgrupatFormWebDB";
  }

    public String getTileList() {
        try {
            Set<Tile> rm;
            rm=AnnotationUtils.getDeclaredRepeatableAnnotations(this.getClass(), Tile.class);
            if (rm != null && !rm.isEmpty()) {
                String trobada = null;
                for (Tile tile : rm) {
                    if (tile.type() == TileType.WEBDB_LIST) {
                        trobada = tile.name();
                    }
                }
                if (trobada != null) {
                    return trobada;
                }
            }
        } catch (Exception e) {
            log.error("Error en el getTileList: " + e.getMessage(), e);
        }
        return "correuAgrupatListWebDB";
    }

  public String getSessionAttributeFilterForm() {
    return "CorreuAgrupat_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public CorreuAgrupatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long correuAgrupatID) throws I18NException {
    return (CorreuAgrupatJPA) correuAgrupatEjb.findByPrimaryKey(correuAgrupatID);
  }


  public CorreuAgrupatJPA create(HttpServletRequest request, CorreuAgrupatJPA correuAgrupat)
    throws I18NException, I18NValidationException {
    return (CorreuAgrupatJPA) correuAgrupatEjb.create(correuAgrupat);
  }


  public CorreuAgrupatJPA update(HttpServletRequest request, CorreuAgrupatJPA correuAgrupat)
    throws I18NException, I18NValidationException {
    return (CorreuAgrupatJPA) correuAgrupatEjb.update(correuAgrupat);
  }


  public void delete(HttpServletRequest request, CorreuAgrupat correuAgrupat) throws I18NException {
    correuAgrupatEjb.delete(correuAgrupat);
  }

} // Final de Classe

