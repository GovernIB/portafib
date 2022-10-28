package es.caib.portafib.back.controller.soli;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import es.caib.portafib.back.security.LoginException;
import es.caib.portafib.logic.utils.BlocUtils;
import es.caib.portafib.logic.utils.FirmaUtils;
import org.fundaciobit.genapp.common.KeyValue.KeyValueComparator;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.apache.commons.lang.StringUtils;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.common.PlantillaDeFluxDeFirmesRestController;
import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.rest.apiplantillaflux.v1.RestApiPlantillaFluxV1Controller;
import es.caib.portafib.back.controller.rest.apiplantillaflux.v1.RestApiPlantillaFluxV1Controller.TransactionInfo;
import es.caib.portafib.back.controller.webdb.FluxDeFirmesController;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesFilterForm;
import es.caib.portafib.back.form.PlantillaDeFluxDeFirmesForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.MappingOrder;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.webdb.FluxDeFirmesWebValidator;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesService;
import es.caib.portafib.ejb.RoleUsuariEntitatService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.hibernate.HibernateFileUtil;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.EstatDeFirmaJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.persistence.RevisorDeFirmaJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.BlocDeFirmesLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.RevisorDeFirmaLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.UsuariExtern;
import es.caib.portafib.model.entity.FluxDeFirmes;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FluxDeFirmesQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/soli/plantilla")
@SessionAttributes(types = { PlantillaDeFluxDeFirmesFilterForm.class,
    PlantillaDeFluxDeFirmesForm.class, FluxDeFirmesForm.class, FluxDeFirmesFilterForm.class,
    SeleccioUsuariForm.class })
public class PlantillaDeFluxDeFirmesController extends FluxDeFirmesController
    implements ConstantsV2 {

  public static final StringField USUARIAPLICACIOID;

  public static final BooleanField COMPARTIR_PLANTILLA;

  static {
    USUARIAPLICACIOID = new FluxDeFirmesQueryPath().PLANTILLAFLUXDEFIRMES()
        .USUARIAPLICACIOID();

    COMPARTIR_PLANTILLA = new FluxDeFirmesQueryPath().PLANTILLAFLUXDEFIRMES().COMPARTIR();
  }

  @EJB(mappedName = PlantillaFluxDeFirmesService.JNDI_NAME)
  private PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

 
  @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
  protected UsuariAplicacioService usuariAplicacioEjb;

  @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
  protected FirmaLogicaLocal firmaLogicaEjb;

  @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
  protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

  @EJB(mappedName = BlocDeFirmesLogicaLocal.JNDI_NAME)
  protected BlocDeFirmesLogicaLocal blocDeFirmesLogicaEjb;

  @EJB(mappedName = RoleUsuariEntitatService.JNDI_NAME)
  protected RoleUsuariEntitatService roleUsuariEntitatEjb;

  @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME)
  protected PeticioDeFirmaService peticioDeFirmaEjb;

  @EJB(mappedName = RevisorDeFirmaLogicaLocal.JNDI_NAME)
  protected RevisorDeFirmaLogicaLocal revisorDeFirmaLogicaEjb;

//  @Autowired
//  protected PlantillaFluxDeFirmesWebValidator plantillaFluxDeFirmesValidator;

  @Autowired
  protected FluxDeFirmesWebValidator fluxDeFirmesValidator;

  // References
  @Autowired
  protected UsuariAplicacioRefList usuariAplicacioRefList;

  @Autowired
  protected es.caib.portafib.back.reflist.UsuariEntitatJSONRefList usuariEntitatRefList;

  @Autowired
  protected es.caib.portafib.back.reflist.CarrecJSONRefList carrecRefList;

  protected MappingOrder mappingOrder = new MappingOrder();

  /**
   * 
   */
  public PlantillaDeFluxDeFirmesController() {
    super();
    mappingOrder.addMapping(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID, USUARIAPLICACIOID);
    mappingOrder.addMapping(PlantillaFluxDeFirmesFields.COMPARTIR, COMPARTIR_PLANTILLA);
  }

  @Override
  public String getTileForm() {
    return "PlantillaDeFluxDeFirmesFormSoli";
  }

  @Override
  public String getTileList() {
    return "PlantillaDeFluxDeFirmesListSoli";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "PlantillaFluxDeFirmes_soli";
  }

  @PostConstruct
  public void init() {

    {
      usuariAplicacioRefList = new UsuariAplicacioRefList(usuariAplicacioRefList);

      usuariAplicacioRefList
          .setSelects(new Select<?>[] { UsuariAplicacioFields.USUARIAPLICACIOID.select });
      usuariAplicacioRefList.setSeparator("");
    }

  }

  @RequestMapping(value = "/viewfluxpeticioid/{peticiodeFirmaID}", method = RequestMethod.GET)
  public ModelAndView viewFluxDeFirmesPeticioIDGet(
      @PathVariable("peticiodeFirmaID") java.lang.Long peticiodeFirmaID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    Where w = PeticioDeFirmaFields.PETICIODEFIRMAID.equal(peticiodeFirmaID);
    // TODO canvia per onlyone result
    List<Long> fluxIDList = peticioDeFirmaEjb.executeQuery(PeticioDeFirmaFields.FLUXDEFIRMESID,
        w);

    // TODO Gestionar errors
    Long fluxDeFirmesID = fluxIDList.get(0);
    return viewFluxDeFirmesGet(String.valueOf(fluxDeFirmesID), request, response);

  }

  @RequestMapping(value = "/viewflux/{fluxDeFirmesID}", method = RequestMethod.GET)
  public ModelAndView viewFluxDeFirmesGet(
      @PathVariable("fluxDeFirmesID") String fluxDeFirmesIDStr, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    long fluxDeFirmesID;

    if (isPlantillaRest()) {
      FileIDEncrypter encrypter = HibernateFileUtil.getEncrypter();
      try {
        fluxDeFirmesID = Long.parseLong(encrypter.decrypt(fluxDeFirmesIDStr));
      } catch (Exception e) {
        throw new I18NException("genapp.comodi",
            "Error desencriptant identificador de Flux de Firmes");
      }
    } else {
      fluxDeFirmesID = Long.parseLong(fluxDeFirmesIDStr);
    }

    ModelAndView mav = veureFluxDeFirmesGet(fluxDeFirmesID, request, response);

    if (isPlantillaRest()) {
      mav.setViewName("PlantillaDeFluxDeFirmesFormRestPopup");
    } else {
      mav.setViewName("PlantillaDeFluxDeFirmesFormSoliPopup");
    }

    FluxDeFirmesForm fff = (FluxDeFirmesForm) mav.getModel().get("fluxDeFirmesForm");

    // Oculta informacio del propietari de la plantilla (si en té)
    fff.addHiddenField(PlantillaFluxDeFirmesFields.USUARIENTITATID);
    fff.addHiddenField(PlantillaFluxDeFirmesFields.COMPARTIR);

    // Oculta el boto de tornar
    // fff.getHiddenFields().add("tornar");
    fff.setAdditionalButtons(new ArrayList<AdditionalButton>());

    return mav;

  }
  

  
  

  @RequestMapping(value = "/viewonlyflux/{fluxDeFirmesID}", method = RequestMethod.GET)
  public ModelAndView viewOnlyFluxDeFirmesGet(
      @PathVariable("fluxDeFirmesID") String fluxDeFirmesID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    ModelAndView mav = viewFluxDeFirmesGet(fluxDeFirmesID, request, response);
    mav.addObject("onlyFlux", true);
    return mav;
  }

  @RequestMapping(value = "/viewonlyfluxofpeticio/{peticioDeFirmaID}", method = RequestMethod.GET)
  public ModelAndView viewOnlyFluxOfPeticioGet(
      @PathVariable("peticioDeFirmaID") java.lang.Long peticioDeFirmaID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    PeticioDeFirmaJPA peticio = peticioDeFirmaEjb.findByPrimaryKey(peticioDeFirmaID);

    ModelAndView mav = viewFluxDeFirmesGet(String.valueOf(peticio.getFluxDeFirmesID()),
        request, response);
    mav.addObject("onlyFlux", true);
    return mav;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

    SubQuery<PlantillaFluxDeFirmes, Long> subquery;
    if (isUsuariEntitat()) {
      // Flux de Firmes Usuari-Entitat
      String currentUser = LoginInfo.getInstance().getUsuariEntitatID();

      subquery = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID,
          PlantillaFluxDeFirmesFields.USUARIENTITATID.equal(currentUser));

    } else {
      // Només mostrar els Usuaris Aplicacio de la meva entitat !!!!
      SubQuery<UsuariAplicacio, String> subqueryUA;
      subqueryUA = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
          UsuariAplicacioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
      // Flux de Firmes Usuari-Aplicacio

      subquery = plantillaFluxDeFirmesEjb.getSubQuery(
          PlantillaFluxDeFirmesFields.FLUXDEFIRMESID,
          PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.in(subqueryUA));

    }

    return FLUXDEFIRMESID.in(subquery);
  }

  public boolean isUsuariEntitat() {
    // L'usuari solicitant no pot gestionar Plantilles de Flux de Firmes
    // de usuaris-aplicacio
    return true;
  }

  /**
   * @return Si val true indica que estam en gestió de flux via REST
   */
  @ModelAttribute("isPlantillaRest")
  public boolean isPlantillaRest() {
    return false;
  }


  @Override
  public void preList(HttpServletRequest request, ModelAndView mav,
      FluxDeFirmesFilterForm filterForm) throws I18NException {
    // TODO FICAR DINS FILTER FORM
    if (isUsuariEntitat()) {
      filterForm.addHiddenField(PlantillaFluxDeFirmesFields.USUARIENTITATID);
    }
  }

  @Override
  public FluxDeFirmesFilterForm getFluxDeFirmesFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    FluxDeFirmesFilterForm fluxDeFirmesFilterForm;
    fluxDeFirmesFilterForm = super.getFluxDeFirmesFilterForm(pagina, mav, request);

    if (fluxDeFirmesFilterForm.isNou()) {

      fluxDeFirmesFilterForm = new PlantillaDeFluxDeFirmesFilterForm(fluxDeFirmesFilterForm);

      fluxDeFirmesFilterForm.setAddButtonVisible(false);

      fluxDeFirmesFilterForm.addHiddenField(PlantillaFluxDeFirmesFields.USUARIENTITATID);
      if (isUsuariEntitat()) {
        fluxDeFirmesFilterForm.addHiddenField(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);
      }

      if (LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADMIN)
          || LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADEN)) {
        fluxDeFirmesFilterForm.addGroupByField(COMPARTIR_PLANTILLA);
      } else {
        fluxDeFirmesFilterForm.addHiddenField(PlantillaFluxDeFirmesFields.COMPARTIR);
      }

      fluxDeFirmesFilterForm.setVisibleMultipleSelection(true);

      if (!isUsuariEntitat()) {
        fluxDeFirmesFilterForm.addGroupByField(USUARIAPLICACIOID);

      }

    } else {
      // MAPEIG ORDRE
      mappingOrder.processDirectOrderMapping(fluxDeFirmesFilterForm);
    }

    log.info("usuariPersona = " + isUsuariEntitat());
    mav.addObject("usuariPersona", isUsuariEntitat());

    return fluxDeFirmesFilterForm;
  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      FluxDeFirmesFilterForm filterForm, List<FluxDeFirmes> list) throws I18NException {

    // DE-MAPEIG ORDRE
    mappingOrder.processIndirectOrderMapping(filterForm);

    String parentContext = isUsuariEntitat() ? "soli" : "aden";

    // Mostrar boto per editar usuaris que poden veure les meves plantilles
    if (isUsuariEntitat() && (LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADMIN)
        || !LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADEN))) {

      filterForm.getAdditionalButtonsByPK().clear();

      for (FluxDeFirmes flux : list) {
        if (((FluxDeFirmesJPA) flux).getPlantillaFluxDeFirmes().getCompartir() == null)
          filterForm.addAdditionalButtonByPK(flux.getFluxDeFirmesID(),
              new AdditionalButton("fas fa-lock", "fluxDeFirmes.permisos",
                  "/" + parentContext + "/permisosplantilla/view/{0}", "btn-warning"));
      }
    }

  }

  @Override
  public Map<Field<?>, GroupByItem> fillReferencesForList(FluxDeFirmesFilterForm filterForm,
      HttpServletRequest request, ModelAndView mav, List<FluxDeFirmes> list,
      List<GroupByItem> groupItems) throws I18NException {

    Map<Field<?>, GroupByItem> groupByItemsMap = super.fillReferencesForList(filterForm,
        request, mav, list, groupItems);

    // Field usuariAplicacioID
    if (!isUsuariEntitat()) {
      Map<String, String> _tmp;
      List<StringKeyValue> _listSKV;

      _listSKV = usuariAplicacioRefList
          .getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID, null);
      _tmp = org.fundaciobit.genapp.common.utils.Utils.listToMap(_listSKV);
      ((PlantillaDeFluxDeFirmesFilterForm) filterForm)
          .setMapOfUsuariAplicacioForUsuariAplicacioID(_tmp);

      if (filterForm.getGroupByFields().contains(USUARIAPLICACIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIAPLICACIOID, false);
        groupByItemsMap.get(USUARIAPLICACIOID)
            .setCodeLabel(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.fullName);
      }

      if (filterForm.getGroupByFields().contains(COMPARTIR_PLANTILLA)) {
        fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap,
            COMPARTIR_PLANTILLA);
        groupByItemsMap.get(COMPARTIR_PLANTILLA)
            .setCodeLabel(PlantillaFluxDeFirmesFields.COMPARTIR.fullName);
      }

    }

    /*
     * _listSKV = this.entitatRefList.getReferenceList(EntitatFields.NOM, null); _tmp =
     * org.fundaciobit.genapp.common.utils.Utils.listToMap(_listSKV);
     * groupByItemsMap.get(ENTITAT_NOM).setCodeLabel(EntitatFields._TABLE_MODEL + "." +
     * EntitatFields._TABLE_MODEL); fillValuesToGroupByItems(_tmp, groupByItemsMap,
     * ENTITAT_NOM, false);
     */

    return groupByItemsMap;

  }

  public boolean isEditingPlantilla() {
    return true;
  }

  @Override
  public FluxDeFirmesForm getFluxDeFirmesForm(FluxDeFirmesJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    FluxDeFirmesForm formBase = super.getFluxDeFirmesForm(_jpa, __isView, request, mav);

    PlantillaDeFluxDeFirmesForm form;
    form = new PlantillaDeFluxDeFirmesForm(formBase);

    // Per paràmetre ens han de passar si estam amb tipus usuari-aplicacio o
    // usuari-entitat
    TransactionInfo restTransaction = getTransactionInfoOfRest(__isView, request);

    if (form.isNou()) {

      // TODO llevar !!!!
      form.setRedirectOnModify(getContextWeb() + "/list/1");

      PlantillaFluxDeFirmesJPA ua = new PlantillaFluxDeFirmesJPA();
      ua.setCompartir(false);

      if (isUsuariEntitat()) {
        ua.setUsuariEntitatID(LoginInfo.getInstance().getUsuariEntitatID());
      } else {

        Where where;

        if (isPlantillaRest()) {

          log.info(" XYZ ZZZ READONLY PER CAMPS NOM, COMPARTIR i USUARI APP");

          form.addHiddenField(PlantillaFluxDeFirmesFields.COMPARTIR);
          //form.addReadOnlyField(NOM);
          
          if (!restTransaction.getTransactionInfo().isVisibleDescription()) {
            form.addHiddenField(PlantillaFluxDeFirmesFields.DESCRIPCIO);
          }
          
          form.addHiddenField(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);

          FluxDeFirmesJPA flux = form.getFluxDeFirmes();

          String appID = restTransaction.getUsuariAplicacio().getUsuariAplicacioID();

          flux.setNom(restTransaction.getTransactionInfo().getName());
          ua.setUsuariAplicacioID(appID);
          ua.setDescripcio(restTransaction.getTransactionInfo().getDescription());

          where = UsuariAplicacioFields.USUARIAPLICACIOID.equal(appID);

          // ERROR XYZ ZZZ
          /*
           * FlowTemplateSimpleStatus status = restTransaction.getStatus();
           * status.setStatus(FlowTemplateSimpleStatus.STATUS_FINAL_ERROR);
           * status.setErrorMessage();
           * 
           * mav.setView(new RedirectView(url, true));
           */
        } else {
          // Aquest where ha de filtrar només els usuaris aplicacio de la
          // meva entitat !!!!!
          where = UsuariAplicacioFields.ENTITATID
              .equal(LoginInfo.getInstance().getEntitatID());

        }

        List<StringKeyValue> usernames = usuariAplicacioRefList
            .getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID, where);

        Collections.sort(usernames, new KeyValueComparator<String>());

        form.setListOfUsuariAplicacioForUsuariAplicacioID(usernames);

        ua.setUsuariEntitatID(null);

      }

      form.setPlantillaFluxDeFirmes(ua);

      FluxDeFirmesJPA ff = form.getFluxDeFirmes();
      Set<BlocDeFirmesJPA> blocDeFirmess = new HashSet<BlocDeFirmesJPA>();
      ff.setBlocDeFirmess(blocDeFirmess);
    } else {
      // -- MODE EDICIO
      if (isPlantillaRest()) {

        // Passar a hidden
        form.addHiddenField(PlantillaFluxDeFirmesFields.COMPARTIR);
        form.addReadOnlyField(NOM);
        form.addHiddenField(PlantillaFluxDeFirmesFields.DESCRIPCIO);
        // XYZ ZZZ ZZZ Passar a hidden
        form.addHiddenField(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);

      }

      FluxDeFirmesJPA ff = form.getFluxDeFirmes();
      long fluxID = ff.getFluxDeFirmesID();

      // Miram READ ONLY
      String ro;
      if (isPlantillaRest()) {
        ro = String.valueOf(__isView);
      } else {
        ro = request.getParameter("readOnly");
      }
      form.setReadOnly("true".equals(ro));

      form.addHiddenField(PlantillaFluxDeFirmesFields.USUARIENTITATID);
      if (isUsuariEntitat()) {
        form.addHiddenField(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);
      } else {
        form.addReadOnlyField(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);
      }

      if (form.isReadOnly()) {
        form.addReadOnlyField(NOM);

        form.addReadOnlyField(PlantillaFluxDeFirmesFields.DESCRIPCIO);

        form.setDeleteButtonVisible(false);
        form.setSaveButtonVisible(false);
        form.setCancelButtonVisible(false);
      } else {
        if (!isEditingPlantilla()) {
          form.setDeleteButtonVisible(false);
        }
      }

      // Colors de les caixes de Bloc i Firmes
      procesaBackgroundColors(ff, form, ff.getPeticioDeFirma() != null);

      // Carregar plantilla de flux
      PlantillaFluxDeFirmesJPA pff;
      pff = (PlantillaFluxDeFirmesJPA) plantillaFluxDeFirmesEjb.findByPrimaryKey(fluxID);
      if (pff != null) {
        form.setPlantillaFluxDeFirmes(pff);
        if (pff.getUsuariAplicacioID() != null) {
          // No permetem modificar l'usuari seleccionat
          List<StringKeyValue> list = new ArrayList<StringKeyValue>();
          list.add(new StringKeyValue(pff.getUsuariAplicacioID(), pff.getUsuariAplicacioID()));
          // mav.addObject("listOfUsuariAplicacioForUsuariAplicacioID", list);
          form.setListOfUsuariAplicacioForUsuariAplicacioID(list);
        }
      } else {
        form.setPlantillaFluxDeFirmes(null);
      }

    }
    // Final mode edició

    if (!isPlantillaRest()) {
      if (!LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADMIN)
          && !LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADEN)) {
        form.addHiddenField(PlantillaFluxDeFirmesFields.COMPARTIR);
      }
    }

    String redirectOnModify = request.getParameter("redirectOnModify");
    if (redirectOnModify == null) {
      form.setRedirectOnModify(getContextWeb() + "/list/1");
    } else {
      form.setRedirectOnModify(redirectOnModify);
    }

    List<UsuariEntitatJPA> users = new ArrayList<UsuariEntitatJPA>();

    // Plantilla de Fluxos d'usuari i per fluxos de la peticio de firma
    PlantillaFluxDeFirmesJPA pff = form.getPlantillaFluxDeFirmes();
    if (pff == null || pff.getUsuariAplicacioID() == null) {

      try {
        // Seleccionem l'usuari actual
        LoginInfo loginInfo = LoginInfo.getInstance();
        String currentUser = loginInfo.getUsuariEntitatID();

        if (!form.isReadOnly() && (pff != null && pff.getUsuariEntitatID() != null)) {
          UsuariPersona up = loginInfo.getUsuariPersona();
          String nom = up.getNom() + " " + up.getLlinatges();
          Set<StringKeyValue> list = new HashSet<StringKeyValue>();
          list.add(new StringKeyValue(currentUser, nom));
          form.setListOfUsuariEntitatForUsuariEntitatID(list);

          if (pff.getUsuariEntitatID() != null) {
            pff.setUsuariEntitatID(currentUser);
          }
        }

        // Llista de posibles firmants

        // Seleccionem tots els usuaris favorits (ja contenen usuari-persona)
        final boolean incloureCarrecs = true;
        users = usuariEntitatLogicaEjb.selectFavorits(currentUser,
                null /* Constants.ROLE_DEST */, incloureCarrecs);

      } catch (LoginException e) {
        log.info("Visualització de plantilla per part d'un usuari extern");
      }

    } else {

      String entitatID;
      if (isPlantillaRest()) {
        if (__isView) {
          entitatID = null;
        } else {
          entitatID = restTransaction.getUsuariAplicacio().getEntitatID();
        }
      } else {
        entitatID = LoginInfo.getInstance().getEntitatID();
      }

      if (entitatID != null) {
        // La gent que pot estar dins un flux d'usuari aplicació son:
        // (1) Tots els usuaris-entitat actius amb role ROLE_DEST de la meva entitat i
        // (2) Tots els càrrecs actius de la meva entitat
        SubQuery<RoleUsuariEntitat, String> sq;
        sq = roleUsuariEntitatEjb.getSubQuery(RoleUsuariEntitatFields.USUARIENTITATID,
            RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_DEST));

        Where w = Where.AND(UsuariEntitatFields.ENTITATID.equal(entitatID), // de la meva
                                                                            // entitat
            UsuariEntitatFields.ACTIU.equal(true), // actius
            Where.OR(UsuariEntitatFields.USUARIENTITATID.in(sq), // Role_Dest o
                UsuariEntitatFields.CARREC.isNotNull() // Es càrrec
            ));

        users = usuariEntitatLogicaEjb.selectFull(w);
      }
    }

    // Llevar de la llista d'usuaris els usuaris que ja estan al flux
    // List<UsuariEntitatJPA> usuarisDelFlux = new ArrayList<UsuariEntitatJPA>();
    Set<BlocDeFirmesJPA> blocs = form.getFluxDeFirmes().getBlocDeFirmess();
    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
      for (FirmaJPA firmaJPA : firmes) {
        users.remove(firmaJPA.getUsuariEntitat());
        // usuarisDelFlux.add(firmaJPA.getUsuariEntitat());
      }
    }

    String urlCancel;
    if (isPlantillaRest()) {
      urlCancel = getContextWeb() + "/finalRestCanceled";
    } else {
      if (form.getRedirectOnModify() == null) {
         urlCancel = getContextWeb() + "/list/1";
      } else {
        urlCancel = form.getRedirectOnModify();
      }
    }
    form.addAdditionalButton(new AdditionalButton("", "tornar", urlCancel, "btn-secondary"));

    form.setCancelButtonVisible(false);

    form.addHiddenField(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID);
    form.addHiddenField(PlantillaFluxDeFirmesFields.USUARIENTITATID);
    if (isUsuariEntitat()) {
      form.addHiddenField(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);
    }

    mav.addObject("fluxDeFirmesForm", form);
    request.getSession().setAttribute("fluxDeFirmesForm", form);

    // FORMULARI SELECCIO USUARI Cada vegada s'ha de calcular
    {
      SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();
      seleccioUsuariForm.setTitol("selectflux.selectuser");
      seleccioUsuariForm.setUrlData(getContextWeb() + "/selecciousuarisjsonperflux");

      seleccioUsuariForm.setUsuarisFavorits(
          Utils.sortStringKeyValueList(SearchJSONController.favoritsToUsuariEntitat(users)));
      
      String javascript ="$('#consultaNifUsuariExtern').modal('show');";
      seleccioUsuariForm.setSecondaryButton(new AdditionalButton("icon-white fas fa-share-square fas fa-user",
          "afegir.usuariextern", javascript , "btn-info"));
      

      mav.addObject("seleccioUsuariForm", seleccioUsuariForm);
      request.getSession().setAttribute("seleccioUsuariForm", seleccioUsuariForm);
    }

    // FORMULARI SELECCIO USUARI-REVISOR
    {
      SeleccioUsuariForm seleccioUsuariRevisorForm = new SeleccioUsuariForm();
      seleccioUsuariRevisorForm.setTitol("selectflux.selectuserrevisor");
      seleccioUsuariRevisorForm.setUrlData("/common/json/usuarientitatrevisor");
      seleccioUsuariRevisorForm.setUsuarisFavorits(null);

      mav.addObject("seleccioUsuariRevisorForm", seleccioUsuariRevisorForm);
      request.getSession().setAttribute("seleccioUsuariRevisorForm",
          seleccioUsuariRevisorForm);
    }

    return form;
  }

  protected TransactionInfo getTransactionInfoOfRest(boolean __isView,
      HttpServletRequest request) throws I18NException {
    TransactionInfo restTransaction = null;
    if (isPlantillaRest()) {
      if (!__isView) {
        String transactionID = (String) request.getSession().getAttribute(
            PlantillaDeFluxDeFirmesRestController.SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST);
        if (transactionID == null) {
          restTransaction = null;
        } else {
          restTransaction = RestApiPlantillaFluxV1Controller.currentTransactions
              .get(transactionID);
        }

        if (restTransaction == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No s'ha trobat transacció de Flux de Firmes en la sessió ");
        }
      }
    }
    return restTransaction;
  }

  protected void procesaBackgroundColors(FluxDeFirmesJPA ff, PlantillaDeFluxDeFirmesForm form,
      boolean iniciat) {
    // final String GROC = "FFFFAA";
    final String BLAU = "C0F7FE"; // "BBBBFF";
    final String VERD = "BBFFBB";
    final String ROIG_B = "FAE7EC"; // "E37795"; //"FFA4A4"; // "FF6666";
    final String ROIG_F = "FF7575";
    final String ROIG_R = "FF5050";
    final String GRIS = "D0D0D0";
    final String VERMELL = "FF0000";

    Map<Long, String> backgroundColorsOfBloc = form.getBackgroundColorsOfBloc();
    Map<Long, String> backgroundColorsOfFirma = form.getBackgroundColorsOfFirma();
    Map<Long, String> backgroundColorsOfRevisor = form.getBackgroundColorsOfRevisor();

    final boolean isDebug = log.isDebugEnabled();

    if (isDebug) {
      log.info(" Entra dins processarBackgroundColors");
    }

    for (BlocDeFirmesJPA bloc : ff.getBlocDeFirmess()) {
      Set<FirmaJPA> firmes = bloc.getFirmas();
      if (iniciat) {

        if (isDebug) {
          log.info("===== COLORS: Bloc[" + bloc.getBlocDeFirmesID() + "]");
        }

        if (bloc.getDataFinalitzacio() == null) {
          // No iniciat o en progress

          // Esta en progres si les firmes tenen estats de firmes
          Set<EstatDeFirmaJPA> estats = null;
          for (FirmaJPA firmaJPA : firmes) {
            estats = firmaJPA.getEstatDeFirmas();
            break;
          }

          if (estats == null || estats.size() == 0) {
            // No iniciat
            break;
          } else {
            // En progres
            backgroundColorsOfBloc.put(bloc.getBlocDeFirmesID(), BLAU);
          }

        } else {
          // Finalitzat o rebutjat (si es rebutjat ho processarem dins les firmes)
          backgroundColorsOfBloc.put(bloc.getBlocDeFirmesID(), VERD);
        }
      }

      for (FirmaJPA firma : firmes) {

        if (!firma.getUsuariEntitat().isActiu()) {
          backgroundColorsOfFirma.put(firma.getFirmaID(), VERMELL);
        } else {
          if (iniciat) {
            if (firma.getFitxerFirmatID() == null) {
              if (bloc.getDataFinalitzacio() == null) {
                // En progres
                backgroundColorsOfFirma.put(firma.getFirmaID(), BLAU);
              } else {
                Set<EstatDeFirmaJPA> estats = firma.getEstatDeFirmas();
                boolean rebutjat = false;
                for (EstatDeFirmaJPA estat : estats) {
                  if (estat.getTipusEstatDeFirmaFinalID() == TIPUSESTATDEFIRMAFINAL_REBUTJAT) {
                    rebutjat = true;
                    break;
                  }
                }
                if (rebutjat) {
                  backgroundColorsOfFirma.put(firma.getFirmaID(), ROIG_F);
                  backgroundColorsOfBloc.put(bloc.getBlocDeFirmesID(), ROIG_B);
                } else {
                  boolean firmat = false;
                  for (EstatDeFirmaJPA estat : estats) {
                    if (estat.getTipusEstatDeFirmaFinalID() == TIPUSESTATDEFIRMAFINAL_FIRMAT) {
                      firmat = true;
                      break;
                    }
                  }
                  if (firmat) {
                    backgroundColorsOfFirma.put(firma.getFirmaID(), VERD);
                  } else {
                    // Descartat
                    backgroundColorsOfFirma.put(firma.getFirmaID(), GRIS);
                  }
                }
              }
            } else {
              // Firmat
              backgroundColorsOfFirma.put(firma.getFirmaID(), VERD);
            }

            // Afegir revisors
            for (RevisorDeFirmaJPA revisor: firma.getRevisorDeFirmas()) {
              for (EstatDeFirmaJPA estat : firma.getEstatDeFirmas()) {
                if (estat.getTipusEstatDeFirmaInicialID() == TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_REVISAR
                        && estat.getUsuariEntitatID().equals(revisor.getUsuariEntitatID())) {
                  String color = BLAU;
                  if (estat.getTipusEstatDeFirmaFinalID() != null) {
                    if (estat.getTipusEstatDeFirmaFinalID().equals(TIPUSESTATDEFIRMAFINAL_ACCEPTAT)) {
                      color = VERD;
                    } else {
                      if (estat.getTipusEstatDeFirmaFinalID().equals(TIPUSESTATDEFIRMAFINAL_REBUTJAT)) {
                        color = ROIG_R;
                      } else {
                        color = GRIS;
                      }
                    }
                  }
                  backgroundColorsOfRevisor.put(revisor.getRevisorDeFirmaID(), color);
                  break;
                }
              }
            }
          }
        }
        if (isDebug) {
          log.info("===== COLORS: Firma[" + firma.getFirmaID() + "] = "
              + backgroundColorsOfFirma.get(firma.getFirmaID()));
        }
      }
    }

  }

  /**
   * Mètode REST de consulta de si existeix USUARI EXTERN
   */
  @RequestMapping(value = "/consultaNifUsuariExtern", method = RequestMethod.GET)
  public void  consultaNifUsuariExtern(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      
      String entitatID;   
      
      if (isPlantillaRest()) {
        final boolean __isView = false;
        TransactionInfo restTransaction = getTransactionInfoOfRest(__isView, request);
        entitatID = restTransaction.getUsuariAplicacio().getEntitatID();
      } else {
        entitatID = LoginInfo.getInstance().getEntitatID();
      }

      
      String nif = request.getParameter("nif");
      

      UsuariEntitatJPA usuariEntitatExtern = usuariEntitatLogicaEjb.findUsuariEntitatExternByNif(entitatID, nif);
      
      
      String json;
      UsuariPersona usuariPersona;
      
      if (usuariEntitatExtern == null) {
        
        log.info(" XYZ ZZZ   NO EXISTEIX USUARI ENTITAT EXTERN AMB NIF ]" + nif + "[");

        // revisam si existeix l'UsuariPersona Externa
        usuariPersona = usuariEntitatLogicaEjb.findUsuariPersonaExternaByNif(nif);
        
        if (usuariPersona != null) {
          log.info(" XYZ ZZZ   SI EXISTEIX USUARI PERSONA EXTERNA AMB NIF ]" + nif + "[");
        }

      } else {
        
        usuariPersona = usuariEntitatExtern.getUsuariPersona();
        log.info(" XYZ ZZZ   SI EXISTESIX USUARI ENTITAT EXTERN AMB NIF ]"
        + nif + "[:<br/>   " +  usuariPersona.getEmail() + " | " +  usuariPersona.getNif() + " | " + usuariPersona.getNom() + " " + usuariPersona.getLlinatges() );

      }
      
      if (usuariPersona == null) {
        json ="{}";
      } else {
        String nom = usuariPersona.getNom();
        String llinatges = usuariPersona.getLlinatges();
        String email =usuariPersona.getEmail();
        String idioma =usuariPersona.getIdiomaID();
        String id = usuariEntitatExtern==null? null :  usuariEntitatExtern.getUsuariEntitatID();
        json = "{\r\n" + 
            (id == null? "": ("        \"id\":\"" + id+ "\",\n")) + 
            "        \"nif\":\"" + nif+ "\",\n" + 
            "        \"email\":\"" +email+ "\",\n" + 
            "        \"idioma\":\"" + idioma + "\",\n" + 
            "        \"nom\":\"" + nom + "\",\n" + 
            "        \"llinatges\":\"" + llinatges + "\"\n" +
            "}";
      }
      

      response.getWriter().write(json);
      
    } catch (I18NException e) {
      log.error(I18NUtils.getMessage(e), e);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    
    
  }

  /**
   * Mètode creació de usuari-extern
   */
  @RequestMapping(value = "/afegirFirmaUsuariExtern", method = RequestMethod.POST)
  public String  afegirFirmaUsuariExtern(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      HttpServletRequest request, HttpServletResponse response) {

    
    // String firmaid = request.getParameter("crearfirma_firmaid");
    try {
      
      String blocOrdreStr =request.getParameter("crearfirma_blocOrdre");
      String blocIDStr = request.getParameter("crearfirma_blocid");
      
      log.info(" \n\n XYZ ZZZ Bloc ORDRE STR => ]" + blocOrdreStr + "[");
      log.info("XYZ ZZZ Bloc ID STR => ]" + blocIDStr + "[");
      
      long blocID;
      
      
      String usuariEntitatID = request.getParameter("crearfirma_usuarientitatid");
      String nif = request.getParameter("crearfirma_nif");
      
      
      boolean creatBloc = false;
      Set<BlocDeFirmesJPA> blocs = null;
      BlocDeFirmesJPA bloc = null;
      
      if ("".equals(blocOrdreStr)) { 
         //  Afegir Firma a Bloc Existent
        blocID   = Integer.parseInt(blocIDStr);
      } else {
        
        // Nou Bloc + Firma
        
        int blocOrdre = Integer.parseInt(blocOrdreStr);

        FluxDeFirmesJPA flux = fluxDeFirmesForm.getFluxDeFirmes();
        long fluxID = flux.getFluxDeFirmesID();
        bloc = fluxDeFirmesLogicaEjb.afegirBlocDeFirmesAFlux(fluxID, blocOrdre);
        
        blocs = flux.getBlocDeFirmess();
        blocs.add(bloc);
        
        blocID = bloc.getBlocDeFirmesID();
        
        fluxDeFirmesLogicaEjb.regeneraOrdres(blocs);
        
        creatBloc = true;
      }

      UsuariExtern usuariExtern;
      {
  
        String nom = request.getParameter("crearfirma_nom");
        String llinatges = request.getParameter("crearfirma_llinatges");
        String email = request.getParameter("crearfirma_email").trim();
        String idioma = request.getParameter("crearfirma_idiomaID");
        
        usuariExtern = new UsuariExtern(nom, llinatges, email, idioma);
      }
      

    
      UsuariEntitatJPA usuariEntitat;
      
      if (usuariEntitatID != null && usuariEntitatID.trim().length() != 0) {

        usuariEntitat = usuariEntitatLogicaEjb
            .findByPrimaryKeyFull(usuariEntitatID);
        if (usuariEntitat == null) {
          HtmlUtils.saveMessageError(request,
              I18NUtils.tradueix("error.notfound",
                      I18NUtils.tradueix("usuariEntitat.usuariEntitat"),
                      I18NUtils.tradueix("usuariEntitat.usuariEntitatID"),
                      usuariEntitatID));
          return getTileForm();
        }
      } else {
        String entitatID;   
        
        if (isPlantillaRest()) {
          final boolean __isView = false;
          TransactionInfo restTransaction = getTransactionInfoOfRest(__isView, request);
          entitatID = restTransaction.getUsuariAplicacio().getEntitatID();
        } else {
          entitatID = LoginInfo.getInstance().getEntitatID();
        }
        
        // crear usuari entitat i usuari persona extern
        
        usuariEntitat = createUsuariExtern(entitatID,  nif, usuariExtern);

      }
      
      
      if (!afegirFirma(fluxDeFirmesForm, request, usuariEntitat, usuariExtern, blocID)) {
        
        if (creatBloc ) {
          blocs.remove(bloc);
          blocDeFirmesLogicaEjb.deleteFull(bloc.getBlocDeFirmesID());
        }
        
        return getTileForm();
      }

      // long fluxDeFirmesID = fluxDeFirmesForm.getFluxDeFirmes().getFluxDeFirmesID();
      afegitUsuariAlFlux(seleccioUsuariForm, usuariEntitatID);
    } catch(I18NValidationException ve) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(ve));
    } catch (I18NException e) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
    }

    return getTileForm();
    
    
  }
  
  protected UsuariEntitatJPA createUsuariExtern(String entitatID, String nif, UsuariExtern usuariExternInfo) 
    throws I18NException, I18NValidationException  {
    // Check camps 
    
    if (nif == null || nif.trim().length() == 0) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "El camp NIF de l'Usuari Extern val null o està buit");
    }
    
    // XYZ ZZZ ZZZ CHECK NIF
    if (nif.length() > 9) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.validation.sizeexceeds",// XYZ ZZZ TRA
        new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"),
        new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(9)));
    }
    
    // XYZ ZZZ TRA
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
    if (!p.matcher(nif).matches()) {
      throw new I18NException("genapp.validation.malformed",
         // XYZ ZZZ TRA
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString("NIF de l'Usuari Extern"));
    }
    
    UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findUsuariEntitatExternByNif(entitatID, nif);
    if (ue == null) {
      log.warn("No existeix cap usuari entitat extern amb NIF "
          + nif + ". El cream." );
      
      UsuariEntitatJPA ueExtern = new UsuariEntitatJPA();
      ueExtern.setActiu(true);
      ueExtern.setEntitatID(entitatID);

      // Revisar si existeix la Persona Externa (per exemple en un altre Entitat)
      UsuariPersonaJPA persona = usuariEntitatLogicaEjb.findUsuariPersonaExternaByNif(nif);
      
      if (persona == null) {
        // L'hem de crear persona
        persona = new UsuariPersonaJPA();
        persona.setEmail(usuariExternInfo.getEmail());
        persona.setIdiomaID(usuariExternInfo.getIdioma());
        persona.setLlinatges(usuariExternInfo.getLlinatges());
        persona.setNif(nif);
        persona.setNom(usuariExternInfo.getNom());
        persona.setUsuariIntern(false);
        
        ueExtern.setUsuariPersona(persona);
        ueExtern.setUsuariPersonaID(null);
      } else {
        // Ja existeix
        ueExtern.setUsuariPersona(null);
        ueExtern.setUsuariPersonaID(persona.getUsuariPersonaID());
      }
      
      log.info("Cridant a crear persona externa: " + usuariExternInfo.getNom() 
          + " " + usuariExternInfo.getLlinatges() + "[" + usuariExternInfo.getEmail()+ "] {" + nif + "}");

      ue = usuariEntitatLogicaEjb.createUsuariEntitatExtern(ueExtern, entitatID);
      
      ue.setUsuariPersona(persona);
      
    } else {
      // Usuari Entitat Extern existeix
      // Revisar si tots els camps de FirmaAsyncSimpleExternalSigner són correctes
      UsuariPersonaJPA persona = ue.getUsuariPersona();
      if (StringUtils.isBlank(usuariExternInfo.getEmail())) {
        usuariExternInfo.setEmail(persona.getEmail());
      }
      if (StringUtils.isBlank(usuariExternInfo.getIdioma())) {
        usuariExternInfo.setIdioma(persona.getIdiomaID());
      }
      if (StringUtils.isBlank(usuariExternInfo.getNom())) {
        usuariExternInfo.setNom(persona.getNom());
      }
      if (StringUtils.isBlank(usuariExternInfo.getLlinatges())) {
        usuariExternInfo.setLlinatges(persona.getLlinatges());
      }

    }
    
    return ue;
  }
  

  @RequestMapping(value = "/afegirRevisorDesDeModal", method = RequestMethod.POST)
  public String afegirRevisorDesDeModal(
      @ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      HttpServletRequest request) {

    String usuariEntitatID = seleccioUsuariForm.getId();
    String firmaIDStr = seleccioUsuariForm.getParam1();

    log.info("\n\n  --------- XYZ ZZZ afegirRevisorDesDeModal ----------");
    log.info("    usuariEntitatID = |" + usuariEntitatID + "|");
    log.info("    firmaIDStr = |" + firmaIDStr + "|");

    long firmaID = Integer.parseInt(firmaIDStr);
    try {
      // TODO Moure tot aquest mètode a EJB

      UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb
          .findByPrimaryKeyFull(usuariEntitatID);
      if (usuariEntitat == null) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.notfound",
                    I18NUtils.tradueix("usuariEntitat.usuariEntitat"),
                    I18NUtils.tradueix("usuariEntitat.usuariEntitatID"),
                    String.valueOf(usuariEntitatID)));
        return getTileForm();
      }

      Long count = roleUsuariEntitatEjb
          .count(Where.AND(RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_REVI),
              RoleUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID)));

      if (count == null || count != 1) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.noesrevisor", usuariEntitatID));
        return getTileForm();
      }

      Long blocID = firmaLogicaEjb.executeQueryOne(FirmaFields.BLOCDEFIRMAID,
          FirmaFields.FIRMAID.equal(firmaID));
      FirmaJPA firma = searchFirma(fluxDeFirmesForm, blocID, firmaID);

      // Revisar si firma existeix !!!
      if (firma == null) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.notfound",
                    I18NUtils.tradueix("firma.firma"),
                    I18NUtils.tradueix("firma.firmaID"), String.valueOf(firmaID)));
        return getTileForm();
      }

      // Cream el revisor
      RevisorDeFirmaJPA rev = new RevisorDeFirmaJPA();
      rev.setFirmaID(firmaID);
      rev.setObligatori(true);
      rev.setUsuariEntitatID(usuariEntitatID);
      rev = (RevisorDeFirmaJPA) revisorDeFirmaLogicaEjb.create(rev);

      // Per fer feina en local
      rev.setUsuariEntitat(usuariEntitat);
      rev.setFirma(firma);

      // Afegim el nou revisor a la firma
      firma.getRevisorDeFirmas().add(rev);

      // Recalcular minim de revisors de Firma!!!!
      saveMinimRevisorsFirma(firma);

    } catch (I18NException e) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
    }

    return getTileForm();
  }

  @RequestMapping(value = "/eliminarRevisor", method = RequestMethod.POST)
  public String eliminarRevisor(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      @RequestParam("blocID") long blocID, @RequestParam("firmaID") long firmaID,
      @RequestParam("revisorID") long revisorID, HttpServletRequest request)
      throws I18NException {

    FirmaJPA firma = searchFirma(fluxDeFirmesForm, blocID, firmaID);

    RevisorDeFirma rev = searchRevisor(firma, revisorID);

    if (rev == null) {
      htmlMessageNotFoundRevisor(revisorID, request);
      return getTileForm();
    }

    // Eliminam el revisor de la bbdd
    revisorDeFirmaLogicaEjb.delete(revisorID);

    // Eliminam el revisor de l'entitat local
    firma.getRevisorDeFirmas().remove(rev);

    // Recalcular minim de revisors de Firma!!!!
    saveMinimRevisorsFirma(firma);

    return getTileForm();

  }

  protected void htmlMessageNotFoundRevisor(long revisorID, HttpServletRequest request) {
    HtmlUtils.saveMessageError(request,
        I18NUtils.tradueix("error.notfound",
                I18NUtils.tradueix("revisorDeFirma.revisorDeFirma"),
                I18NUtils.tradueix("revisorDeFirma.revisorDeFirmaID"),
                String.valueOf(revisorID)));
  }

  @RequestMapping(value = "/esborrarmotiu", method = RequestMethod.POST)
  public String esborrarMotiu(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      @RequestParam("firmaID") long firmaID, HttpServletRequest request) throws I18NException {

    for (BlocDeFirmesJPA bloc : fluxDeFirmesForm.getFluxDeFirmes().getBlocDeFirmess()) {
      for (FirmaJPA firmaitem : bloc.getFirmas()) {
        if (firmaitem.getFirmaID() == firmaID) {
          firmaitem.setMotiu(null);
          firmaLogicaEjb.update(firmaitem);
          return getTileForm();
        }
      }
    }

    // XYZ ZZZ Passar a HTMlUtils.saveWarning ????
    log.warn("\n\nNo he trobat la firma amb ID " + firmaID
        + " per esborrar el motiu de la firma n\n");

    return getTileForm();
  }

  @RequestMapping(value = "/ferRevisorObligatori", method = RequestMethod.POST)
  public String ferRevisorObligatori(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @RequestParam("blocID") long blocID, @RequestParam("firmaID") long firmaID,
      @RequestParam("revisorID") long revisorID, HttpServletRequest request)
      throws I18NException {

    FirmaJPA firma = searchFirma(fluxDeFirmesForm, blocID, firmaID);

    if (firma == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("firma.firma"),
                  I18NUtils.tradueix("firma.firmaID"), String.valueOf(blocID)));
      return getTileForm();
    }


    RevisorDeFirmaJPA revisor = null;
    for (RevisorDeFirmaJPA f : firma.getRevisorDeFirmas()) {
      if (f.getRevisorDeFirmaID() == revisorID) {
        revisor = f;
      }
    }

    if (revisor == null) {
      htmlMessageNotFoundRevisor(firmaID, request);
      return getTileForm();
    }

    revisor.setObligatori(!revisor.isObligatori());
    revisorDeFirmaLogicaEjb.update(revisor);

    saveMinimRevisorsFirma(firma);

    return getTileForm();

  }

  @RequestMapping(value = "/changeMinimDeRevisors", method = RequestMethod.POST)
  public String changeMinimDeRevisors(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @RequestParam("blocID") long blocID, @RequestParam("firmaID") long firmaID,
      @RequestParam("minimDeFirmes") int minimDeRevisors, HttpServletRequest request)
      throws I18NException {

    FirmaJPA firma = searchFirma(fluxDeFirmesForm, blocID, firmaID);

    if (firma == null) {
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("firma.firma"),
                  I18NUtils.tradueix("firma.firmaID"), String.valueOf(firmaID)));
      return getTileForm();
    }

    firma.setMinimDeRevisors(minimDeRevisors);
    saveMinimRevisorsFirma(firma);

    return getTileForm();

  }

  @RequestMapping(value = "/definirmotiu", method = RequestMethod.POST)
  public String definirMotiu(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      @RequestParam("firmaID") long firmaID, @RequestParam("motiu") String motiu,
      HttpServletRequest request) throws I18NException {

    motiu = motiu.replace('\'', '´').replace('"', 'ʺ');

    for (BlocDeFirmesJPA bloc : fluxDeFirmesForm.getFluxDeFirmes().getBlocDeFirmess()) {
      for (FirmaJPA firmaitem : bloc.getFirmas()) {
        if (firmaitem.getFirmaID() == firmaID) {
          firmaitem.setMotiu(motiu);
          firmaLogicaEjb.update(firmaitem);
          return getTileForm();
        }
      }
    }

    // XYZ ZZZ Passar a HTMlUtils.saveWarning ????
    log.warn("\n\nNo he trobat la firma amb ID " + firmaID
        + " per modificar el motiu de la firma n\n");

    return getTileForm();
  }

  @RequestMapping(value = "/afegirFirmaDesDeModal", method = RequestMethod.POST)
  public String afegirFirmaDesDeModal(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      HttpServletRequest request) {

    String usuariEntitatID = seleccioUsuariForm.getId();
    long blocID = Integer.parseInt(seleccioUsuariForm.getParam1());
    try {
      
      UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb
          .findByPrimaryKeyFull(usuariEntitatID);
      if (usuariEntitat == null) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.notfound",
                    I18NUtils.tradueix("usuariEntitat.usuariEntitat"),
                    I18NUtils.tradueix("usuariEntitat.usuariEntitatID"),
                    String.valueOf(usuariEntitatID)));
        return getTileForm();
      }
      
      
      if (!afegirFirma(fluxDeFirmesForm, request, usuariEntitat, blocID)) {
        return getTileForm();
      }

      // long fluxDeFirmesID = fluxDeFirmesForm.getFluxDeFirmes().getFluxDeFirmesID();
      afegitUsuariAlFlux(seleccioUsuariForm, usuariEntitatID);
    } catch (I18NException e) {
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
    }

    return getTileForm();
  }

  protected boolean afegirFirma(FluxDeFirmesForm fluxDeFirmesForm, HttpServletRequest request,
      UsuariEntitatJPA usuariEntitat, long blocID) throws I18NException {
    
    return afegirFirma(fluxDeFirmesForm, request,
        usuariEntitat, null, blocID);
  }
  
  
  
  protected boolean afegirFirma(FluxDeFirmesForm fluxDeFirmesForm, HttpServletRequest request,
      UsuariEntitatJPA usuariEntitat, UsuariExtern usuariExtern, long blocID) throws I18NException {
    // TODO Moure tot aquest mètode a EJB

    BlocDeFirmesJPA bloc = searchBloc(fluxDeFirmesForm, blocID);

    if (bloc == null) {
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmes"),
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmesID"),
                  String.valueOf(blocID)));
      return false;
    }
    return  fluxDeFirmesLogicaEjb.afegirFirmaABloc(usuariEntitat, usuariExtern, bloc);
  }
  
  
  
  

  private void afegitUsuariAlFlux(SeleccioUsuariForm seleccioUsuariForm,
      String usuariEntitatID) {
    List<StringKeyValue> list = seleccioUsuariForm.getUsuarisFavorits();
    removeUsuariEntitatFromList(usuariEntitatID, list);
  }

  protected void removeUsuariEntitatFromList(String usuariEntitatID,
      List<StringKeyValue> list) {
    StringKeyValue toDelete = null;
    for (StringKeyValue stringKeyValue : list) {
      if (stringKeyValue.getKey().equals(usuariEntitatID)) {
        toDelete = stringKeyValue;
        break;
      }
    }
    list.remove(toDelete);
  }

  /**
   * Filtre usuaris-entitat de l'entitat actual.
   */
  @RequestMapping(value = "/selecciousuarisjsonperflux", method = RequestMethod.GET)
  public void seleccioUsuarisJsonPerFluxGet(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    seleccioUsuarisJsonPerFlux(request, response);
  }

  /**
   * Filtre usuaris-entitat de l'entitat actual.
   */
  @RequestMapping(value = "/selecciousuarisjsonperflux", method = RequestMethod.POST)
  public void seleccioUsuarisJsonPerFlux(HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    final String queryFull = request.getParameter("query");

    if (log.isDebugEnabled()) {
      log.debug("queryFull = |" + queryFull + "|");
    }

    PlantillaDeFluxDeFirmesForm form;
    form = (PlantillaDeFluxDeFirmesForm) request.getSession().getAttribute("fluxDeFirmesForm");

    List<String> aEliminar = new ArrayList<String>();

    Set<BlocDeFirmesJPA> blocs = form.getFluxDeFirmes().getBlocDeFirmess();
    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      Set<FirmaJPA> firmes = blocDeFirmesJPA.getFirmas();
      for (FirmaJPA firmaJPA : firmes) {
        aEliminar.add(firmaJPA.getUsuariEntitat().getUsuariEntitatID());
      }
    }

    String json = SearchJSONController.generaLlistatUsuarisCarrecsJson(queryFull, aEliminar,
        usuariEntitatLogicaEjb, usuariEntitatRefList, carrecRefList);

    PrintWriter pw = response.getWriter();

    pw.write(json);
    pw.flush();

  }

  @RequestMapping(value = "/afegirBlocDesDeModal", method = RequestMethod.POST)
  public String afegirBlocDesDeModal(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      SeleccioUsuariForm seleccioUsuariForm, HttpServletRequest request) {

    String usuariEntitatID = seleccioUsuariForm.getId();
    int blocOrdre = Integer.parseInt(seleccioUsuariForm.getParam1());

    if (log.isDebugEnabled()) {
      log.debug("afegirBlocDesDeModal(" + usuariEntitatID + "," + blocOrdre + ")");
    }

    try {
      FluxDeFirmesJPA flux = fluxDeFirmesForm.getFluxDeFirmes();
      long fluxID = flux.getFluxDeFirmesID();
      BlocDeFirmesJPA bloc = fluxDeFirmesLogicaEjb.afegirBlocDeFirmesAFlux(fluxID, blocOrdre);
      
      UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb
          .findByPrimaryKeyFull(usuariEntitatID);
      if (usuariEntitat == null) {
        HtmlUtils.saveMessageError(request,
            I18NUtils.tradueix("error.notfound",
                    I18NUtils.tradueix("usuariEntitat.usuariEntitat"),
                    I18NUtils.tradueix("usuariEntitat.usuariEntitatID"),
                    String.valueOf(usuariEntitatID)));
        return getTileForm();
      }
      
      Set<BlocDeFirmesJPA> blocs = flux.getBlocDeFirmess();
      blocs.add(bloc);
      
      if (!afegirFirma(fluxDeFirmesForm, request, usuariEntitat, bloc.getBlocDeFirmesID())) {
        blocs.remove(bloc);
        blocDeFirmesLogicaEjb.deleteFull(bloc.getBlocDeFirmesID());
        
        return getTileForm();
      }
      
      fluxDeFirmesLogicaEjb.regeneraOrdres(blocs);

      afegitUsuariAlFlux(seleccioUsuariForm, usuariEntitatID);
    } catch (I18NException e) {
      log.error("Error desconegut creant Bloc de Firmes: " + e.getMessage(), e); 
      HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
    }

    return getTileForm();

  }

  @RequestMapping(value = "/canviMinimDeFirmes", method = RequestMethod.POST)
  public String canviMinimDeFirmes(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @RequestParam("minimDeFirmes") int minimDeFirmes, @RequestParam("blocID") long blocID,
      HttpServletRequest request) throws I18NException {

    BlocDeFirmesJPA bloc = searchBloc(fluxDeFirmesForm, blocID);

    if (bloc == null) {
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmes"),
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmesID"),
                  String.valueOf(blocID)));
      return getTileForm();
    }

    bloc.setMinimDeFirmes(minimDeFirmes);
    saveMinimFirmesBloc(bloc);

    return getTileForm();

  }

  @RequestMapping(value = "/ferFirmaObligatoria", method = RequestMethod.POST)
  public String ferFirmaObligatoria(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @RequestParam("blocID") long blocID, @RequestParam("firmaID") long firmaID,
      HttpServletRequest request) throws I18NException {

    BlocDeFirmesJPA bloc = searchBloc(fluxDeFirmesForm, blocID);

    if (bloc == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      HtmlUtils.saveMessageError(request,
              I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmes"),
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmesID"),
                  String.valueOf(blocID)));
      return getTileForm();
    }

    FirmaJPA firma = null;
    for (FirmaJPA f : bloc.getFirmas()) {
      if (f.getFirmaID() == firmaID) {
        firma = f;
      }
    }

    if (firma == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("firma.firma"),
                  I18NUtils.tradueix("firma.firmaID"), String.valueOf(firmaID)));
      return getTileForm();
    }

    firma.setObligatori(!firma.isObligatori());
    firmaLogicaEjb.update(firma);

    saveMinimFirmesBloc(bloc);

    return getTileForm();
  }

  private void saveMinimFirmesBloc(BlocDeFirmesJPA bloc) throws I18NException {
    int minimBloc = BlocUtils.minimFirmes(bloc.getFirmas());
    if (bloc.getMinimDeFirmes() < minimBloc) {
      bloc.setMinimDeFirmes(minimBloc);
    } else {
      if (bloc.getMinimDeFirmes() > bloc.getFirmas().size()) {
        bloc.setMinimDeFirmes(bloc.getFirmas().size());
      }
    }
    blocDeFirmesLogicaEjb.update(bloc);
  }

  private void saveMinimRevisorsFirma(FirmaJPA firma) throws I18NException {
    int minimFirma = FirmaUtils.minimRevisors(firma.getRevisorDeFirmas());
    if (firma.getMinimDeRevisors() < minimFirma) {
      firma.setMinimDeRevisors(minimFirma);
    } else {
      if (firma.getMinimDeRevisors() > firma.getRevisorDeFirmas().size()) {
        firma.setMinimDeRevisors(firma.getRevisorDeFirmas().size());
      }
    }
    firmaLogicaEjb.update(firma);
  }

  @RequestMapping(value = "/eliminarFirma", method = RequestMethod.POST)
  public String eliminarFirma(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      @RequestParam("blocID") long blocID, @RequestParam("firmaID") long firmaID,
      HttpServletRequest request) throws I18NException {

    BlocDeFirmesJPA bloc = searchBloc(fluxDeFirmesForm, blocID);

    if (bloc == null) {
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmes"),
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmesID"),
                  String.valueOf(blocID)));
      return getTileForm();
    }

    FirmaJPA firma = null;
    for (FirmaJPA f : bloc.getFirmas()) {
      if (f.getFirmaID() == firmaID) {
        firma = f;
      }
    }

    if (firma == null) {
      // error.notfound=No s´ha trobat cap {0} amb {1} igual a {2}
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("firma.firma"),
                  I18NUtils.tradueix("firma.firmaID"), String.valueOf(firmaID)));
      return getTileForm();
    }

    // TODO s'ha de passar a EJB

    firmaLogicaEjb.deleteFull(firma);
    bloc.getFirmas().remove(firma);

    saveMinimFirmesBloc(bloc);

    UsuariEntitatJPA ue = firma.getUsuariEntitat();
    eliminatUsuariDelFlux(seleccioUsuariForm, ue);

    return getTileForm();
  }

  private void eliminatUsuariDelFlux(SeleccioUsuariForm seleccioUsuariForm,
      UsuariEntitatJPA ue) {

    List<StringKeyValue> list = seleccioUsuariForm.getUsuarisFavorits();
    list.add(SearchJSONController.getStringKeyValueFromUsuariEntitat(ue));
    Utils.sortStringKeyValueList(list);
  }

  @RequestMapping(value = "/eliminarBloc", method = RequestMethod.POST)
  public String eliminarBloc(@ModelAttribute @Valid FluxDeFirmesForm fluxDeFirmesForm,
      @ModelAttribute @Valid SeleccioUsuariForm seleccioUsuariForm,
      @RequestParam("blocID") long blocID, HttpServletRequest request) throws I18NException {

    BlocDeFirmesJPA bloc = searchBloc(fluxDeFirmesForm, blocID);

    if (bloc == null) {
      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix("error.notfound",
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmes"),
                  I18NUtils.tradueix("blocDeFirmes.blocDeFirmesID"),
                  String.valueOf(blocID)));
      return getTileForm();
    }

    blocDeFirmesLogicaEjb.deleteFull(blocID);

    fluxDeFirmesForm.getFluxDeFirmes().getBlocDeFirmess().remove(bloc);

    for (FirmaJPA firma : bloc.getFirmas()) {
      eliminatUsuariDelFlux(seleccioUsuariForm, firma.getUsuariEntitat());
    }

    return getTileForm();
  }

  protected BlocDeFirmesJPA searchBloc(FluxDeFirmesForm fluxDeFirmesForm, long blocID) {
    Set<BlocDeFirmesJPA> blocs = fluxDeFirmesForm.getFluxDeFirmes().getBlocDeFirmess();

    BlocDeFirmesJPA bloc = null;
    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      if (blocID == blocDeFirmesJPA.getBlocDeFirmesID()) {
        bloc = blocDeFirmesJPA;
        break;
      }
    }
    return bloc;
  }

  protected FirmaJPA searchFirma(FluxDeFirmesForm fluxDeFirmesForm, long blocID,
      long firmaID) {
    Set<BlocDeFirmesJPA> blocs = fluxDeFirmesForm.getFluxDeFirmes().getBlocDeFirmess();

    for (BlocDeFirmesJPA blocDeFirmesJPA : blocs) {
      if (blocID == blocDeFirmesJPA.getBlocDeFirmesID()) {
        for (FirmaJPA f : blocDeFirmesJPA.getFirmas()) {
          if (f.getFirmaID() == firmaID) {
            return f;
          }
        }
      }
    }
    return null;
  }

  protected RevisorDeFirmaJPA searchRevisor(FirmaJPA firma, long revisorID) {

    for (RevisorDeFirmaJPA f : firma.getRevisorDeFirmas()) {
      if (f.getRevisorDeFirmaID() == revisorID) {
        return f;
      }
    }
    return null;
  }

  @Override
  public void preValidate(HttpServletRequest request, FluxDeFirmesForm fluxDeFirmesForm2,
      BindingResult result) throws I18NException {

    final boolean isDebug = log.isDebugEnabled();
    if (isDebug) {
      log.debug("Entra a PreVALIDATE()" + result.getClass());
    }

    PlantillaDeFluxDeFirmesForm form = (PlantillaDeFluxDeFirmesForm) fluxDeFirmesForm2;
    
    // FluxDeFirmesJPA flux = fluxDeFirmesForm.getFluxDeFirmes();

    if (form.isNou()) {
      // Utilitzarem el prevalidate per carregar la primera firma dins el
      // fluxDeFirmes

      if (isDebug) {
        log.debug(" PreValidate():: Entra a NOU: " + result.hasErrors());
      }

      String usuariEntitat = form.getUsuariEntitatPrimeraFirma();

      if (usuariEntitat == null || usuariEntitat.trim().length() == 0) {
        final String name = PlantillaDeFluxDeFirmesForm.USUARI_ENTITAT_PRIMERA_FIRMA_FIELD;
        result.addError(new FieldError(name, name, null, false,
            new String[] { "genapp.validation.required" },
            new Object[] { I18NUtils.tradueix("usuarientitatprimerafirma") }, null));
      } else {

        if (isDebug) {
          log.debug(" PreValidate:: NOU --> usuari Entitat es " + usuariEntitat);
        }

        BlocDeFirmesJPA bloc = new BlocDeFirmesJPA();
        bloc.setMinimDeFirmes(1);

        FirmaJPA firma = new FirmaJPA();
        firma.setDestinatariID(usuariEntitat);
        firma.setObligatori(true);

        bloc.getFirmas().add(firma);

        form.getFluxDeFirmes().getBlocDeFirmess().clear();
        form.getFluxDeFirmes().getBlocDeFirmess().add(bloc);
      }
    }

    // TODO fullName
    final String modelPlantilla = PlantillaFluxDeFirmesFields._TABLE_MODEL;
    result.recordSuppressedField(
        modelPlantilla + "." + PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.javaName);

    PlantillaFluxDeFirmesJPA ffup = form.getPlantillaFluxDeFirmes();
    if (ffup != null) {
      BindingResult errors = new BeanPropertyBindingResult(form, "");
//     plantillaFluxDeFirmesValidator.validate(form, errors);
      fluxDeFirmesValidator.validate(form, errors);

      if (!isUsuariEntitat()) {
        if (ffup.getUsuariAplicacioID() == null) {
          final String modelApp = modelPlantilla + "."
              + PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.javaName;
          ValidationUtils.rejectIfEmptyOrWhitespace(errors, modelApp,
              "genapp.validation.required", new Object[] { I18NUtils.tradueix(modelApp) });
        }
      }

      processErrors(result, errors);
    }

    if (!isDebug) {
      log.debug("Surt de PreVALIDATE()");
    }

  }

  @Override
  public void postValidate(HttpServletRequest request, FluxDeFirmesForm fluxDeFirmesForm,
      BindingResult result) throws I18NException {

    if (result.hasErrors()) {

      List<ObjectError> list = result.getAllErrors();

      log.error(" postValidate HAS ERRORS : " + result.hasErrors());

      for (ObjectError oe1 : list) {

        FieldError oe = (FieldError) oe1;

        log.error("Error " + oe.getObjectName() + ": Camp " + oe.getField());
      }

    } else {
      PlantillaDeFluxDeFirmesForm form = (PlantillaDeFluxDeFirmesForm) fluxDeFirmesForm;
      FluxDeFirmesJPA flux = form.getFluxDeFirmes();
      flux.setPlantillaFluxDeFirmes(form.getPlantillaFluxDeFirmes());
    }
  }

  protected void processErrors(BindingResult result, BindingResult errors) {
    List<ObjectError> list = errors.getAllErrors();

    log.error(" processErrors 111 : " + errors.hasErrors());

    for (ObjectError oe1 : list) {
      FieldError oe = (FieldError) oe1;

      log.error("Error " + oe.getObjectName() + ": Camp " + oe.getField());

      // Ignorar fluxDeFirmesID
      if (oe.getField().endsWith("fluxDeFirmesID")) {
        continue;
      }

      result.addError(oe);
    }

    log.error(" processErrors 222: " + errors.hasErrors());
  }

  @Override
  public String getRedirectWhenCreated(HttpServletRequest request,
      FluxDeFirmesForm fluxDeFirmesForm) {
    return "redirect:" + getContextWeb() + "/"
        + fluxDeFirmesForm.getFluxDeFirmes().getFluxDeFirmesID() + "/edit";
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request,
      FluxDeFirmesForm fluxDeFirmesForm, Throwable __e) {

    PlantillaDeFluxDeFirmesForm form = (PlantillaDeFluxDeFirmesForm) fluxDeFirmesForm;

    String redirectOnModify = form.getRedirectOnModify();

    if (log.isDebugEnabled()) {
      log.debug("getRedirectWhenModified => " + redirectOnModify);
    }

    if (redirectOnModify == null) {
      return super.getRedirectWhenModified(request, fluxDeFirmesForm, __e);
    } else {
      return "redirect:" + redirectOnModify;
    }
  }

  @Override
  public FluxDeFirmesJPA create(HttpServletRequest request, FluxDeFirmesJPA fluxDeFirmes)
      throws I18NException, I18NValidationException {
    // El create ha de crear:
    // (1) El flux
    // (2) Plantilla de Usuari Entitat o Plantilla de Usuari
    // (3) Primera Firma del Flux (Bloc + Firma)

    if (log.isDebugEnabled()) {
      PlantillaFluxDeFirmes pff = fluxDeFirmes.getPlantillaFluxDeFirmes();
      if (pff != null) {
        if (pff.getUsuariEntitatID() != null) {
          log.info("Entra dins create (Person): " + pff);
          log.info("  User:  " + pff.getUsuariEntitatID());
          log.info("  Desc:  " + pff.getDescripcio());
        } else {
          log.info("Entra dins create (Aplic): " + pff);
          log.info("  UserApp:  " + pff.getUsuariAplicacioID());
          if (pff.getCompartir() == null) {
            pff.setCompartir(false);
          }

        }
      }
    }

    FluxDeFirmesJPA flux = fluxDeFirmesLogicaEjb.createFull(fluxDeFirmes);

    if (isPlantillaRest()) {

      TransactionInfo restTransaction = null;
      if (isPlantillaRest()) {
        String transactionID = (String) request.getSession().getAttribute(
            PlantillaDeFluxDeFirmesRestController.SESSION_TRANSACTION_ID_FLOW_TEMPLATE_REST);

        if (transactionID == null) {
          restTransaction = null;
        } else {
          restTransaction = RestApiPlantillaFluxV1Controller.currentTransactions
              .get(transactionID);
        }

        if (restTransaction == null) {
          throw new I18NException("genapp.comodi",
              "No s'ha trobat transacció de Flux de Firmes en la sessió ");
        }
      }

      restTransaction.setFluxDeFirmesID(flux.getFluxDeFirmesID());

    }

    return flux;
  }

  @Override
  public FluxDeFirmesJPA update(HttpServletRequest request, FluxDeFirmesJPA fluxDeFirmes)
      throws I18NException, I18NValidationException {

    PlantillaFluxDeFirmes pff = fluxDeFirmes.getPlantillaFluxDeFirmes();
    if (pff != null && pff.getUsuariAplicacioID() != null && pff.getCompartir() == null) {
      pff.setCompartir(false);
    }

    return fluxDeFirmesLogicaEjb.updateFullPlantillaFluxUsuari(fluxDeFirmes);
  }

  @Override
  public void delete(HttpServletRequest request, FluxDeFirmes fluxDeFirmes)
      throws I18NException {
    fluxDeFirmesLogicaEjb.deleteFull(fluxDeFirmes.getFluxDeFirmesID());
    // return true;
  }

  @Override
  public FluxDeFirmesJPA findByPrimaryKey(HttpServletRequest request,
      java.lang.Long fluxDeFirmesID) throws I18NException {

    FluxDeFirmesJPA fluxDeFirmes;
    fluxDeFirmes = fluxDeFirmesLogicaEjb.findByPrimaryKeyFullForPlantilla(fluxDeFirmesID);

    Set<BlocDeFirmesJPA> blocsUnordered = fluxDeFirmes.getBlocDeFirmess();

    if (blocsUnordered != null) {
      Set<BlocDeFirmesJPA> blocs = new TreeSet<BlocDeFirmesJPA>(
          new Comparator<BlocDeFirmesJPA>() {
            @Override
            public int compare(BlocDeFirmesJPA o1, BlocDeFirmesJPA o2) {
              return o1.getOrdre() - o2.getOrdre();
            }
          });

      blocs.addAll(blocsUnordered);
      fluxDeFirmesLogicaEjb.regeneraOrdres(blocs);

      fluxDeFirmes.setBlocDeFirmess(blocs);
    }

    return fluxDeFirmes;
  }

  public List<StringKeyValue> getReferenceListForUsuariAplicacioID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    return usuariAplicacioRefList.getReferenceList(UsuariAplicacioFields.USUARIAPLICACIOID,
        where);
  }

  @Override
  public String getEntityNameCode() {
    final String model = PlantillaFluxDeFirmesFields._TABLE_MODEL;
    return model + "." + model;
  }

  @Override
  public String getEntityNameCodePlural() {
    return getEntityNameCode() + ".plural";
  }

  @Override
  @InitBinder("fluxDeFirmesForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinderForm(binder);

    initDisallowedFields(binder,
            FLUXDEFIRMESID.fullName,
            PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.fullName);
  }

}

