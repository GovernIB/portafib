package es.caib.portafib.back.controller.soli;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.PlantillaFluxDeFirmesController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.PlantillaFluxDeFirmesFilterForm;
import es.caib.portafib.back.form.webdb.PlantillaFluxDeFirmesForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.PermisGrupPlantillaJPA;
import es.caib.portafib.jpa.PermisUsuariPlantillaJPA;
import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.PlantillaFluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/soli/permisosplantilla")
@SessionAttributes(types = { PlantillaFluxDeFirmesForm.class,
    PlantillaFluxDeFirmesFilterForm.class, SeleccioUsuariForm.class })
public class PermisosPlantillaDeFluxDeFirmesController extends PlantillaFluxDeFirmesController {

  @EJB(mappedName = PlantillaFluxDeFirmesLogicaLocal.JNDI_NAME)
  private PlantillaFluxDeFirmesLogicaLocal plantillaFluxDeFirmesLogicaEjb;

  @EJB(mappedName = "portafib/UsuariEntitatLogicaEJB/local")
  protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisUsuariPlantillaLocal permisUsuariPlantillaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaLocal permisGrupPlantillaEjb;
  
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;

  @Override
  protected ModelAndView editAndViewPlantillaFluxDeFirmesGet(
      @PathVariable("fluxDeFirmesID") java.lang.Long fluxDeFirmesID,
      HttpServletRequest request, HttpServletResponse response, boolean __isView)
      throws I18NException {

    if (!LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADMIN)
        && !LoginInfo.getInstance().hasRole(ConstantsV2.ROLE_ADEN)) {
      // Accés no autoritzat, farem que falli
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }

    return super.editAndViewPlantillaFluxDeFirmesGet(fluxDeFirmesID, request, response,
        __isView);
  }

  @Override
  public PlantillaFluxDeFirmesForm getPlantillaFluxDeFirmesForm(PlantillaFluxDeFirmesJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {

    PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm;

    plantillaFluxDeFirmesForm = super.getPlantillaFluxDeFirmesForm(_jpa, __isView, request,
        mav);

    plantillaFluxDeFirmesForm.getHiddenFields().addAll(
        Arrays.asList(PlantillaFluxDeFirmesFields.ALL_PLANTILLAFLUXDEFIRMES_FIELDS));

    plantillaFluxDeFirmesForm.setTitleCode("permisosplantilla.title");
    plantillaFluxDeFirmesForm.setTitleParam(_jpa.getFluxDeFirmes().getNom());

    plantillaFluxDeFirmesForm.setCancelButtonVisible(false);

    plantillaFluxDeFirmesForm.addAdditionalButton(new AdditionalButton(
        " icon-arrow-left icon-white", "tornar", getContextWeb() + "/tornar",
        "btn-primary"));
    

    refreshGrupsDisponibles(request, _jpa.getFluxDeFirmesID());
    
    
   // FORMULARI SELECCIO USUARI Cada vegada s'ha de calcular
    SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();
    seleccioUsuariForm.setTitol("permisosplantilla.adduser");
    seleccioUsuariForm.setUrlData("/common/json/usuarientitat");

    try {
      seleccioUsuariForm.setUsuarisFavorits(
          SearchJSONController.favoritsToUsuariEntitat(
              usuariEntitatLogicaEjb.selectFavorits(
                LoginInfo.getInstance().getUsuariEntitatID(), null, false)));
    } catch (I18NException e) {
      log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
    }
    
    mav.addObject(seleccioUsuariForm);
    

    return plantillaFluxDeFirmesForm;
  }

  /**
   * 
   * @param request
   * @param fluxDeFirmesID
   * @return
   * @throws I18NException
   */
  public Map<String, String> refreshGrupsDisponibles(HttpServletRequest request, Long fluxDeFirmesID)
      throws I18NException {
    SubQuery<PermisGrupPlantilla, Long> grupsActuals;
    grupsActuals = permisGrupPlantillaEjb.getSubQuery(PermisGrupPlantillaFields.GRUPENTITATID,
        PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID.equal(fluxDeFirmesID));

    List<GrupEntitat> skvlist;
    skvlist = grupEntitatEjb.select(
        Where.AND(
            GrupEntitatFields.GRUPENTITATID.notIn(grupsActuals),
            GrupEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID())
            ));

    Map<String, String> grupsDisponibles = new HashMap<String, String>();
    for (GrupEntitat grupEntitat : skvlist) {
      grupsDisponibles.put(String.valueOf(grupEntitat.getGrupEntitatID()), grupEntitat.getNom());
    }
    
    request.getSession().setAttribute("grups", grupsDisponibles);
    
    return grupsDisponibles;
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return true;
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long fluxDeFirmesID) {

    // TODO ha de retornar a la pàgina de llistat de plantilles
    return "redirect:" + getContextWeb() + "/list";
  }

  @Override
  public String getTileForm() {
    return "permisosPlantillaDeFluxDeFirmes";
  }

  @Override
  public PlantillaFluxDeFirmesJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long fluxDeFirmesID)
      throws I18NException {
    return (PlantillaFluxDeFirmesJPA) plantillaFluxDeFirmesLogicaEjb
        .findByPrimaryKeyFull(fluxDeFirmesID);
  }

  @RequestMapping(value = "/tornar", method = RequestMethod.GET)
  public String tornar(
  /*
   * @ModelAttribute("plantillaFluxDeFirmesForm") @Valid
   * PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm, HttpServletRequest
   * request, HttpServletResponse response
   */
  ) throws I18NException {

    return "redirect:/soli/plantilla/list/1";

  }

  @RequestMapping(value = "/allowUserToPlantilla", method = RequestMethod.POST)
  public String allowUserToPlantilla(
      @ModelAttribute("plantillaFluxDeFirmesForm") @Valid PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
      SeleccioUsuariForm seleccioUsuariForm,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    long plantillafluxDeFirmesID = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes()
        .getFluxDeFirmesID();

    {
 
      String usuariEntitatID = seleccioUsuariForm.getId();
      {

        PermisUsuariPlantillaJPA pup;
        pup = new PermisUsuariPlantillaJPA(usuariEntitatID, plantillafluxDeFirmesID);

        try {
          permisUsuariPlantillaEjb.create(pup);

          UsuariEntitatJPA ueJPA = usuariEntitatLogicaEjb
              .findByPrimaryKeyFull(usuariEntitatID);

          pup.setUsuariEntitat(ueJPA);

          plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getPermisUsuariPlantillas()
              .add(pup);

          if (log.isDebugEnabled()) {
            log.debug("Creat permis amb ID = " + pup.getPermisUsuariPlantillaID());
          }
        } catch (Exception e) {
          // TODO gestionar error Ja existeix o NIF es buit
        }

      }
    }
    
    // REFRESCAR LLISTA DE DISPONIBLES
    refreshGrupsDisponibles(request,plantillafluxDeFirmesID);

    return getTileForm();

  }

  @RequestMapping(value = "/deleteUserFromPlantilla", method = RequestMethod.POST)
  public String deleteUserFromPlantilla(
      @ModelAttribute("plantillaFluxDeFirmesForm") @Valid PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
      @RequestParam("userToDeleteFromPlantilla") String usuariEntitatID,
      HttpServletRequest request) throws I18NException {


    long plantillafluxDeFirmesID = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes()
        .getFluxDeFirmesID();

    PermisUsuariPlantillaJPA pup = null;

    Set<PermisUsuariPlantillaJPA> list;

    list = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getPermisUsuariPlantillas();

    for (PermisUsuariPlantillaJPA permisUsuariPlantillaJPA : list) {
      if (usuariEntitatID.equals(permisUsuariPlantillaJPA.getUsuariEntitatID())
          && plantillafluxDeFirmesID == permisUsuariPlantillaJPA.getPlantillaFluxDeFirmesID()) {
        pup = permisUsuariPlantillaJPA;
        break;
      }
    }

    if (pup != null) {
      permisUsuariPlantillaEjb.delete(pup); // Esborrar de la BBDD
      list.remove(pup); // Esborrar del formulari
    }
    
    // REFRESCAR LLISTA DE DISPONIBLES
    refreshGrupsDisponibles(request,plantillafluxDeFirmesID);

    return getTileForm();

  }
  
  
  
  
  
  
  // -------------------------------------------------------
  // -------------------- GRUPS  ---------------------------
  // -------------------------------------------------------
  
  
  @RequestMapping(value = "/allowGroupToPlantilla", method = RequestMethod.POST)
  public String allowGroupToPlantilla(
      @ModelAttribute("plantillaFluxDeFirmesForm") @Valid PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
      @RequestParam("groupToAdd") Long grupEntitatID, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    long plantillafluxDeFirmesID = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes()
    .getFluxDeFirmesID();
   

    if (grupEntitatID == null) {
      // No s´ha trobat cap {0} amb {1} igual a {2}
      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("error.notfound",
                 I18NUtils.tradueix("grupEntitat.grupEntitat"),
                 I18NUtils.tradueix("grupEntitat.grupEntitatID"),
                 String.valueOf(grupEntitatID)));
    } else {

      PermisGrupPlantillaJPA pgp;
      pgp = new PermisGrupPlantillaJPA(grupEntitatID, plantillafluxDeFirmesID);

      try {
        permisGrupPlantillaEjb.create(pgp);
        
        pgp.setGrupEntitat(grupEntitatEjb.findByPrimaryKey(grupEntitatID));

        plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getPermisGrupPlantillas()
            .add(pgp);

        if (log.isDebugEnabled()) {
          log.info("Creat permis de grup amb ID = " + pgp.getPermisGrupPlantillaID());
        }

      } catch (Exception e) {
        // TODO gestionar error  Ja existeix
      }

    }
    
    // REFRESCAR LLISTA DE DISPONIBLES
    refreshGrupsDisponibles(request,plantillafluxDeFirmesID);

    return getTileForm();

  }
  
  
  
  
  
  @RequestMapping(value = "/deleteGroupFromPlantilla", method = RequestMethod.POST)
  public String deleteGroupFromPlantilla(
      @ModelAttribute("plantillaFluxDeFirmesForm") @Valid PlantillaFluxDeFirmesForm plantillaFluxDeFirmesForm,
      @RequestParam("groupToDeleteFromPlantilla") Long grupEntitatID,
      HttpServletRequest request) throws I18NException {


    long plantillafluxDeFirmesID = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes()
        .getFluxDeFirmesID();

    PermisGrupPlantillaJPA pup = null;

    Set<PermisGrupPlantillaJPA> list;

    list = plantillaFluxDeFirmesForm.getPlantillaFluxDeFirmes().getPermisGrupPlantillas();

    for (PermisGrupPlantillaJPA permisGrupPlantillaJPA : list) {
      if (grupEntitatID.equals(permisGrupPlantillaJPA.getGrupEntitatID())
          && plantillafluxDeFirmesID == permisGrupPlantillaJPA.getPlantillaFluxDeFirmesID()) {
        pup = permisGrupPlantillaJPA;
        break;
      }
    }

    if (pup != null) {
      permisGrupPlantillaEjb.delete(pup); // Esborrar de la BBDD
      list.remove(pup); // Esborrar del formulari
    }
    
    // REFRESCAR LLISTA DE DISPONIBLES
    refreshGrupsDisponibles(request,plantillafluxDeFirmesID);

    return getTileForm();

  }
  
}
