package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.admin.GestioEntitatAdminController;
import es.caib.portafib.back.controller.common.ConfiguracioUsuariEntitatController;
import es.caib.portafib.back.controller.webdb.UsuariAplicacioController;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioForm;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.UsuariAplicacioWebLogicValidator;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsPortaFIB.POLITICA_CUSTODIA;
import es.caib.portafib.utils.ConstantsV2;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created 25/06/13 14:26
 *
 * @author mgonzalez
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = GestioUsuariAplicacioAdenController.CONTEXTWEB)
@SessionAttributes(types = { UsuariAplicacioForm.class, UsuariAplicacioFilterForm.class })
public class GestioUsuariAplicacioAdenController extends UsuariAplicacioController {

  static final String CONTEXTWEB = "/aden/usuariAplicacio";

  protected static final int PERFILS = 1;

  @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaService usuariAplicacioPerfilEjb;

  @Autowired
  private UsuariAplicacioWebLogicValidator usuariAplicacioWebLogicValidator;

  /**
   * Indica si es admin o aden
   * TODO: Té sentit que GestioUsuariAplicacioAdminController extengui d'aquí només per dir isAdmin false? Sembla que
   * seria més lògic, eliminar les consultes a isAdmin i fer override dels mètodes que calgui.
   * @return sempre retorna false
   */
  protected boolean isAdmin() {
    return false;
  }

  @Override
  public String getTileForm() {
    return "gestioUsuariAplicacioFormAden";
  }

  @Override
  public String getTileList() {
    return isAdmin() ? "gestioUsuariAplicacioListAdmin" : "gestioUsuariAplicacioListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return isAdmin() ? "UsuariAplicacio_Admin_FilterForm" : "UsuariAplicacio_Aden_FilterForm";
  }

  @Override
  public boolean isActiveFormNew() {
    return !isAdmin();
  }

  @Override
  public boolean isActiveFormEdit() {
    return !isAdmin();
  }

  @PostConstruct
  public void init() {
    this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
    setWebValidator(usuariAplicacioWebLogicValidator);
  }

  @Override
  public UsuariAplicacioForm getUsuariAplicacioForm(UsuariAplicacioJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    UsuariAplicacioForm usuariAplicacioForm = super.getUsuariAplicacioForm(_jpa, __isView,
        request, mav);

    // Establim l'entitat de login
    usuariAplicacioForm.getUsuariAplicacio().setEntitatID(
        LoginInfo.getInstance().getEntitatID());

    if (usuariAplicacioForm.isNou()) {
      // Fixam l'email de l'administrador si el formulari es nou
      usuariAplicacioForm.getUsuariAplicacio().setEmailAdmin(Utils.getLoggedUserEmail());

      UsuariAplicacioJPA aplicacio = usuariAplicacioForm.getUsuariAplicacio();
      aplicacio.setCallbackVersio(-1);
      aplicacio.setIdiomaID(Configuracio.getDefaultLanguage());
      aplicacio.setActiu(true);
      
      aplicacio.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);

    } else {
      usuariAplicacioForm.addReadOnlyField(USUARIAPLICACIOID);
      usuariAplicacioForm.addReadOnlyField(ACTIU);
    }

    usuariAplicacioForm.setAttachedAdditionalJspCode(true);

    // Ocultam camps
    usuariAplicacioForm.addHiddenField(ENTITATID);

    if (usuariAplicacioForm.getUsuariAplicacio().isActiu()) {
      usuariAplicacioForm.addAdditionalButton(new AdditionalButton("fas fa-ban",
          "desactivar", getContextWeb() + "/desactivar/{0}", "btn-warning"));
    } else {
      usuariAplicacioForm.addAdditionalButton(new AdditionalButton("fas fa-play", "activar",
          getContextWeb() + "/activar/{0}", "btn-success"));
    }

    usuariAplicacioForm.setAttachedAdditionalJspCode(true);

    return usuariAplicacioForm;
  }

  @Override
  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {

    if (isAdmin()) {
      return super.getReferenceListForEntitatID(request, mav, where);
    } else {
      List<StringKeyValue> lskvEntitat = new ArrayList<StringKeyValue>();
      LoginInfo loginInfo = LoginInfo.getInstance();
      EntitatJPA entitatJPA = loginInfo.getEntitat();
      StringKeyValue skvEntitat = new StringKeyValue(entitatJPA.getEntitatID(),
          entitatJPA.getNom());
      lskvEntitat.add(skvEntitat);
      return lskvEntitat;
    }

  }

  @Override
  public List<StringKeyValue> getReferenceListForCallbackVersio(HttpServletRequest request,
      ModelAndView mav, Where where) {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    // NOTA: Si és modifica aquest llistat, llavors s'ha de modificar també el fitxer
    // [portafib]/back/src/main/webapp/WEB-INF/jsp/webdbmodificable/usuariAplicacioFormModificable.jsp
    __tmp.add(new StringKeyValue("-1", "No Callback"));
    __tmp.add(new StringKeyValue("0", "Portafirmas CAIB"));
    __tmp.add(new StringKeyValue("1", "PortaFIB Callback WS v1.0"));
    __tmp.add(new StringKeyValue("2", "PortaFIB Callback REST v1.0"));
    return __tmp;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    if (isAdmin()) {
      return null;
    } else {
      return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }
  }

  @Override
  public UsuariAplicacioFilterForm getUsuariAplicacioFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    UsuariAplicacioFilterForm usuariAplicacioFilterForm;
    usuariAplicacioFilterForm = super.getUsuariAplicacioFilterForm(pagina, mav, request);

    if (usuariAplicacioFilterForm.isNou()) {

      Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
          Arrays
              .asList(UsuariAplicacioFields.ALL_USUARIAPLICACIO_FIELDS));
      
      hiddenFields.remove(USUARIAPLICACIOID);
      hiddenFields.remove(CALLBACKVERSIO);
      hiddenFields.remove(ACTIU);
      
      
      usuariAplicacioFilterForm.setHiddenFields(hiddenFields);

      usuariAplicacioFilterForm.addHiddenField(POLITICADEPLUGINFIRMAWEB);

      usuariAplicacioFilterForm.setTitleCode("usuariaplicacio.llistat");

      usuariAplicacioFilterForm.setDeleteSelectedButtonVisible(false);

      usuariAplicacioFilterForm.setVisibleMultipleSelection(false);

      usuariAplicacioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
          "far fa-check-square", "validar.urlcallback", getContextWeb() + "/validarurlcallback/{0}",
          "btn-info"));

      // Afegir botó per gestionar plugins web: #173
      usuariAplicacioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "fas fa-cog", "pluginfirmaweb.veure",
            PluginFirmaWebPerUsuariAplicacioAdenController.CONTEXT_WEB + "/seleccio/{0}",
            "btn-info"
      ));

      usuariAplicacioFilterForm.setActionsRenderer(UsuariAplicacioConfiguracioFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

      if (isAdmin()) {
        usuariAplicacioFilterForm.setDeleteButtonVisible(false);
        usuariAplicacioFilterForm.setEditButtonVisible(false);
        usuariAplicacioFilterForm.setAddButtonVisible(false);

        List<Field<?>> filterBy = new ArrayList<Field<?>>();
        filterBy.add(USUARIAPLICACIOID);
        usuariAplicacioFilterForm.setFilterByFields(filterBy);

        usuariAplicacioFilterForm.addGroupByField(ENTITATID);
        usuariAplicacioFilterForm.addGroupByField(ACTIU);

        usuariAplicacioFilterForm.addHiddenField(CALLBACKURL);
        usuariAplicacioFilterForm.addHiddenField(CALLBACKVERSIO);
      } else {
        usuariAplicacioFilterForm.addHiddenField(ENTITATID);

        AdditionalField<String, String> adfield4 = new AdditionalField<String, String>();
        adfield4.setCodeName("perfils");
        adfield4.setPosition(PERFILS);
        // Els valors s'ompliran al mètode postList()
        adfield4.setValueMap(new HashMap<String, String>());
        adfield4.setEscapeXml(false);
        usuariAplicacioFilterForm.addAdditionalField(adfield4);

        usuariAplicacioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
            "/img/config_add.png", "usuariaplicacio.config.new", getContextWeb()
                + "/newconfig/{0}", "btn-success"));

      }

    }

    usuariAplicacioFilterForm.setVisibleExportList(true);

    return usuariAplicacioFilterForm;
  }

  
  
  @Override
  public void postValidate(HttpServletRequest request,
      UsuariAplicacioForm usuariAplicacioForm, BindingResult result)
      throws I18NException {

    UsuariAplicacio ue = usuariAplicacioForm.getUsuariAplicacio();


    // Custodia
    int politicaCustodia = ue.getPoliticaCustodia();
    if (politicaCustodia == ConstantsV2.POLITICA_CUSTODIA_OBLIGATORI_PLANTILLA_DEFINIDA_A_CONTINUACIO) {

      Long custInfoID = ue.getCustodiaInfoID();
      if (custInfoID == null) {
        // El camp {0} és obligatori.
        result.rejectValue(get(CUSTODIAINFOID), "genapp.validation.required",
            new String[] { I18NUtils.tradueix(get(CUSTODIAINFOID)) }, null);
      }
    } else {
      ue.setCustodiaInfoID(null);
    }
  }
  
  
  
  @RequestMapping(value = "/validarurlcallback/{usuariAplicacioID}", method = RequestMethod.GET)
  public String validarURLCallBack(
      @PathVariable("usuariAplicacioID") java.lang.String usuariAplicacioID,
      HttpServletRequest request, HttpServletResponse response) {

    try {
      EjbManager.getNotificacioTimerEjb().testCallBackAPI(usuariAplicacioID);

      HtmlUtils.saveMessageSuccess(request, "Test OK");

    } catch (Throwable e) {

      UsuariAplicacioJPA usuariAplicacio = this.usuariAplicacioLogicaEjb
          .findByPrimaryKey(usuariAplicacioID);

      // Error provant la URL de CallBack({0}) de l'usuari aplicació {1} : {2}
      String error = I18NUtils.tradueix("validar.urlcallback.error",
          usuariAplicacio.getCallbackURL(), usuariAplicacio.getUsuariAplicacioID(),
          e.getMessage());

      HtmlUtils.saveMessageError(request, error + "<br/>"
          + ExceptionUtils.getStackTrace(e).replace("\n", "<br/>"));

      log.error(error, e);

    }

    return "redirect:" + getContextWeb() + "/list";

  }

  @RequestMapping(value = "/deleteperfilusrapp/{usrAppID}/{perfilID}", method = RequestMethod.GET)
  public String deleteUserAppPerfil(HttpServletRequest request, HttpServletResponse response,
      @PathVariable String usrAppID, @PathVariable Long perfilID) throws I18NException {

    perfilsPerUsuariAplicacioEjb.delete(Where.AND(
        PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usrAppID),
        PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID.equal(perfilID)));

    // request.getSession().setAttribute("USUARI_APLICAIO_PER_AFEGIR_PERFIL", usrappid);

    return "redirect:" + getContextWeb() + "/list";

  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      UsuariAplicacioFilterForm filterForm, List<UsuariAplicacio> list) throws I18NException {

    List<String> usuariAplicacioIds = new ArrayList<String>(list.size());
    for (UsuariAplicacio usuariAplicacio : list) {
      usuariAplicacioIds.add(usuariAplicacio.getUsuariAplicacioID());
    }

    // PERFILS
    if (!isAdmin()){
      Map<String, String> map;
      map = (Map<String, String>) filterForm.getAdditionalField(PERFILS).getValueMap();
      map.clear();

      for (UsuariAplicacio usuariAplicacio : list) {
        String key = usuariAplicacio.getUsuariAplicacioID();

        SubQuery<PerfilsPerUsuariAplicacio, Long> subquery;
        subquery = perfilsPerUsuariAplicacioEjb.getSubQuery(PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
             PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(key));
        
        
        //List<Long> perfilsID = perfilsPerUsuariAplicacioEjb.executeQuery(
        //    PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
        //    PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(key));

        List<PerfilDeFirma> perfils = usuariAplicacioPerfilEjb
            .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(subquery));

        if (perfils == null || perfils.size() == 0) {
          map.put(key, "");
        } else {

          StringBuilder str = new StringBuilder("<table>\n");
          for (PerfilDeFirma perfil : perfils) {
            str.append(
            // Edit -> Link Nom i Codi
            "<tr><td>\n"
                + "<a href=\"" + request.getContextPath()
                
                + PerfilDeFirmaAdenController.CONTEXT_WEB
                + "/view/"
                + perfil.getUsuariAplicacioPerfilID()
                + "\"> "
                + perfil.getNom()
                + " (<b>"
                + perfil.getCodi()
                + "</b>)</a>"
                + "</td><td>\n"
                // Delete => Icon trash
                + "<a style=\"padding: 0px; margin-bottom: 4px; margin-right: 4px\" href=\""
                + request.getContextPath()
                + getContextWeb()
                + "/deleteperfilusrapp/"
                + key
                + "/"
                + perfil.getUsuariAplicacioPerfilID()
                + "\" class=\"btn btn-sm btn-danger\" type=\"button\">"
                + "<i style=\"padding: 0px 4px 4px 0px; margin: 4px 0px 0px 3px \" class=\"fas fa-trash icon-white\"></i></a>\n"
                + "</td></tr>\n");
          }
          str.append("</table>");
          map.put(key, str.toString());
        }

      }

    }

  }

  @RequestMapping(value = "/newconfig/{usuariAplicacioID}", method = RequestMethod.GET)
  public String newConfiguracioUsuariAplicacio(
      @PathVariable("usuariAplicacioID") String usuariAplicacioID, HttpServletRequest request,
      HttpServletResponse response) {

    request.getSession().setAttribute(
        PerfilsDeUsuariAplicacioAdenController.SESSION_USUARIAPLICACIOID, usuariAplicacioID);
    return "redirect:" + PerfilsDeUsuariAplicacioAdenController.CONTEXT_WEB + "/new";
  }

  @Override
  public void delete(HttpServletRequest request, UsuariAplicacio usuariAplicacio)
      throws I18NException {
    Set<Long> fitxers = usuariAplicacioLogicaEjb.deleteFull(usuariAplicacio
        .getUsuariAplicacioID());
    this.borrarFitxers(fitxers);
  }

  @RequestMapping(value = "/activar/{usuariAplicacioID}", method = RequestMethod.GET)
  public ModelAndView activarUsuariEntitat(
      @PathVariable("usuariAplicacioID") String usuariAplicacioID, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    activarDesactivarUsauriApp(usuariAplicacioID, request, true);

    ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/"
        + usuariAplicacioID + "/edit", true));
    return mav;
  }

  @RequestMapping(value = "/desactivar/{usuariAplicacioID}", method = RequestMethod.GET)
  public ModelAndView desactivarUsuariEntitat(
      @PathVariable("usuariAplicacioID") String usuariAplicacioID, HttpServletRequest request,
      HttpServletResponse response) {

    activarDesactivarUsauriApp(usuariAplicacioID, request, false);

    ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/"
        + usuariAplicacioID + "/edit", true));
    return mav;
  }

  private void activarDesactivarUsauriApp(String usuariAplicacioID,
      HttpServletRequest request, boolean activar) {
    try {
      if (activar) {
        usuariAplicacioLogicaEjb.activar(usuariAplicacioID);
      } else {
        usuariAplicacioLogicaEjb.desactivar(usuariAplicacioID);
      }
    } catch (I18NException i18ne) {
      String msg = I18NUtils.getMessage(i18ne);
      log.error(msg, i18ne);
      HtmlUtils.saveMessageError(request, msg);
    } catch (Exception e) {
      String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }
  }

  @Override
  public UsuariAplicacioJPA create(HttpServletRequest request,
      UsuariAplicacioJPA usuariAplicacio) throws I18NException,
      I18NValidationException {
    return usuariAplicacioLogicaEjb.createFull(usuariAplicacio, LoginInfo.getInstance()
        .getEntitatID());
  }

  /**
   * #173
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaDePluginFirmaWeb(
      HttpServletRequest request, ModelAndView mav, Where where) {
    return ConfiguracioUsuariEntitatController
        .staticGetReferenceListForPoliticaDePluginFirmaWeb();
  }
  
  // #165
  @Override
  public List<StringKeyValue> getReferenceListForCustodiaInfoID(HttpServletRequest request,
      ModelAndView mav, UsuariAplicacioForm configuracioForm, Where where)
      throws I18NException {

    Where where2 = Where.AND(where,
        CustodiaInfoFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
        CustodiaInfoFields.NOMPLANTILLA.isNotNull());

    return super.getReferenceListForCustodiaInfoID(request, mav, configuracioForm, where2);
  }
  
  /**
   * #165
   */
  @Override
  public List<StringKeyValue> getReferenceListForPoliticaCustodia(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {

    return GestioEntitatAdminController
          .staticGetReferenceListForPoliticaCustodia(POLITICA_CUSTODIA.POLITICA_CUSTODIA_USUARI_APLICACIO);
  }
  

}
