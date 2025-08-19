package es.caib.portafib.back.controller.soli;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.jboss.logging.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.AbstractPeticioDeFirmaByTipusSolicitant.TipusSolicitant;
import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.form.SeleccioFluxDeFirmesForm;
import es.caib.portafib.back.form.webdb.AnnexFilterForm;
import es.caib.portafib.back.form.webdb.AnnexForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.ejb.PlantillaFluxDeFirmesService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.model.entity.GrupEntitat;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import es.caib.portafib.model.entity.PermisGrupPlantilla;
import es.caib.portafib.model.entity.PermisUsuariPlantilla;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;
import es.caib.portafib.model.entity.UsuariAplicacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;
import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.persistence.BlocDeFirmesJPA;
import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.FluxDeFirmesJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@MenuOption(
        group = Tab.MENU_SOLI,
        labelCode = "peticiodefirma.crear",
        baseLink = PeticioDeFirmaCrearSoliController.CONTEXT_SOLI_CREAR_PETICIOFIRMA,
        relativeLink = "/selectflux",
        order = 10)
@RequestMapping(value = PeticioDeFirmaCrearSoliController.CONTEXT_SOLI_CREAR_PETICIOFIRMA)
@SessionAttributes(
        types = { SeleccioFluxDeFirmesForm.class, PeticioDeFirmaForm.class, PeticioDeFirmaFilterForm.class,
                AnnexFilterForm.class, AnnexForm.class })
@Controller
public class PeticioDeFirmaCrearSoliController implements ConstantsV2 {
    
    public static final String CONTEXT_SOLI_CREAR_PETICIOFIRMA = "/soli/crearpeticio";

    // --------------------------------------------------------------------
    // --------------------------------------------------------------------
    // ------------- SELECCIÓ DE FLUX DE LA PETICIÓ DE FIRMA ------------
    // --------------------------------------------------------------------
    // --------------------------------------------------------------------

    public static final String SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES = "SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES";

    public static final String SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO = "SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO";

    public static final String SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA = "SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA";

    public static final String SELECTFLUX_TILE = "seleccionaFluxDeFirmaForm";

    public static final Comparator<FluxDeFirmesJPA> FLUXCOMPARATOR = new Comparator<FluxDeFirmesJPA>() {

        @Override
        public int compare(FluxDeFirmesJPA o1, FluxDeFirmesJPA o2) {
            return o1.getNom().compareToIgnoreCase(o2.getNom()); // To change body of implemented
                                                                 // methods use File | Settings |
                                                                 // File Templates.
        }
    };
    
    protected Logger log = Logger.getLogger(this.getClass());
    
    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
    protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = PlantillaFluxDeFirmesService.JNDI_NAME)
    private PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;
    
    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME, beanName = "FirmaLogicaEJB")
    protected FirmaLogicaLocal firmaLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.GrupEntitatService grupEntitatEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaService.JNDI_NAME)
    protected es.caib.portafib.ejb.PermisUsuariPlantillaService permisUsuariPlantillaEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatService grupEntitatUsuariEntitatEjb;
    
    @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaService.JNDI_NAME)
    protected es.caib.portafib.ejb.PermisGrupPlantillaService permisGrupPlantillaEjb;

    /**
     * SELECCIO DE FLUX DE FIRMA
     * 
     */
    @RequestMapping(value = "/selectflux", method = RequestMethod.GET)
    public ModelAndView seleccionarFluxDeFirmaGet(HttpServletRequest request) throws I18NException {
        ModelAndView mav = new ModelAndView(getTileSeleccioFlux());

        log.debug("Entra dins seleccionarFluxDeFirmaGet");

        LoginInfo loginInfo = LoginInfo.getInstance();

        String entitatActualID = loginInfo.getEntitatID();

        request.getSession().removeAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES);

        request.getSession().removeAttribute(SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO);

        request.getSession().removeAttribute(SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA);

        SeleccioFluxDeFirmesForm seleccioFluxDeFirmesForm = new SeleccioFluxDeFirmesForm();

        // Favorits
        {
            // Si entram en mode UsuariAplicacio´, els usuaris que es veuran
            // seran els favorits de l'administrador d'entitat
            String usuariEntitatID = loginInfo.getUsuariEntitatID();

            List<UsuariEntitatJPA> usuarisFavorits;
            usuarisFavorits = usuariEntitatLogicaEjb.selectFavorits(usuariEntitatID, null, true);

            seleccioFluxDeFirmesForm.setUrlData("/common/json/usuarientitatcarrec");
            seleccioFluxDeFirmesForm.setUsuarisFavorits(
                    Utils.sortStringKeyValueList(SearchJSONController.favoritsToUsuariEntitat(usuarisFavorits)));

        }

        // Plantilles de l'usuari-persona
        {
            SubQuery<PlantillaFluxDeFirmes, Long> fluxosSubQuery;
            switch (getTipusSolicitant()) {

                case SOLICITANT_WEB: {
                    String usuariEntitatID = loginInfo.getUsuariEntitatID();
                    // log.info("     -usuariEntitatID = " + usuariEntitatID);
                    fluxosSubQuery = getFluxosDeUsuariEntitat(usuariEntitatID);

                    seleccioFluxDeFirmesForm.setSolicitantUsuariEntitat(true);
                }
                break;

                default:
                case SOLICITANT_TOTS:
                case SOLICITANT_APLICACIO: {
                    String usuariAplicacioID = request.getParameter("usuariAplicacioID");
                    if (log.isDebugEnabled()) {
                        log.debug("Request Parameter[usuariAplicacioID] = ]" + usuariAplicacioID + "[");
                    }

                    if (usuariAplicacioID == null) {
                        HtmlUtils.saveMessageWarning(request,
                                I18NUtils.tradueix("peticiodefirma.error.usuariaplicacionodefinit"));
                        return new ModelAndView(new RedirectView(getContextWebToListRequestMapping(), true));
                    }

                    fluxosSubQuery = getFluxosDeUsuariAplicacio(usuariAplicacioID);

                    seleccioFluxDeFirmesForm.setSolicitantUsuariEntitat(false);
                    seleccioFluxDeFirmesForm.setUsuariAplicacioID(usuariAplicacioID);

                    String origenPeticioDeFirmaStr = request.getParameter("origenPeticioDeFirma");

                    int origenPeticioDeFirma = Integer.parseInt(origenPeticioDeFirmaStr);
                    seleccioFluxDeFirmesForm.setOrigenPeticioDeFirma(origenPeticioDeFirma);

                }
                break;

            }

            Where w;
            w = FluxDeFirmesFields.FLUXDEFIRMESID.in(fluxosSubQuery);
            List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

            Collections.sort(fluxos, FLUXCOMPARATOR);

            seleccioFluxDeFirmesForm.setListOfFluxPlantillaUsuari(fluxos);

            if (fluxos == null || fluxos.size() == 0) {
                HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("selectflux.avisnoplantilles"));
            }

        }

        // Plantilles dels usuaris-persona de la mateixa entitat que ofereixen
        // les seves plantilles a tothom. Si entram en mode usuari-aplicacio llavors
        // es mostraran els que tengui permis l'administrador
        {
            Where w;
            w = FluxDeFirmesFields.FLUXDEFIRMESID.in(getFluxosCompartitsDeUsuaris(entitatActualID));
            List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

            Collections.sort(fluxos, FLUXCOMPARATOR);
            seleccioFluxDeFirmesForm.setListOfFluxPlantillaPersonaCompartit(fluxos);

        }

        {
            // Plantilles dels usuaris-aplicacio de la mateixa entitat que ofereixen
            // les seves plantilles a tothom
            Where w;
            w = FluxDeFirmesFields.FLUXDEFIRMESID.in(getFluxosCompartitsPerAplicacions(entitatActualID));

            List<FluxDeFirmesJPA> fluxos = fluxDeFirmesLogicaEjb.selectPlantilla(w);

            Collections.sort(fluxos, FLUXCOMPARATOR);
            seleccioFluxDeFirmesForm.setListOfFluxPlantillaAplicacioCompartit(fluxos);

        }

        seleccioFluxDeFirmesForm.setTipus(SeleccioFluxDeFirmesForm.TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX);

        seleccioFluxDeFirmesForm.setContexte(getContextWeb());

        mav.addObject("seleccioFluxDeFirmesForm", seleccioFluxDeFirmesForm);

        return mav;
    }
    
    
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }
    

    public String getTileSeleccioFlux() {
        return "seleccionaFluxDeFirmaForm";
    }
    
    
    public String getContextWebToNewRequestMapping() {
        return ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/new";
    }
    
    public String getContextWebToListRequestMapping() {
        return ConstantsV2.CONTEXT_SOLI_PETICIOFIRMA_ACTIVA + "/list";
    }
    
    
    public TipusSolicitant getTipusSolicitant() {
      return TipusSolicitant.SOLICITANT_WEB;
    }
    

    @RequestMapping(value = "/selectflux", method = RequestMethod.POST)
    public String seleccionarFluxDeFirmaPost(SeleccioFluxDeFirmesForm seleccioFluxDeFirmesForm, BindingResult result,
            HttpServletRequest request) {

        // Validar Nom i Tipus
        String nom = seleccioFluxDeFirmesForm.getNom();
        if (nom == null || nom.trim().length() == 0) {
            ValidationUtils.rejectIfEmptyOrWhitespace(result, "nom", "genapp.validation.required",
                    new Object[] { I18NUtils.tradueix("nom") });

            return getTileSeleccioFlux(); 
        }

        final boolean isDebug = log.isDebugEnabled();
        String usuariAplicacioID;
        int origenPeticioDeFirma;
        if (getTipusSolicitant() == TipusSolicitant.SOLICITANT_WEB) {
            usuariAplicacioID = null;
            origenPeticioDeFirma = ORIGEN_PETICIO_DE_FIRMA_SOLICITANT_WEB;
        } else {

            usuariAplicacioID = seleccioFluxDeFirmesForm.getUsuariAplicacioID();
            if (isDebug) {
                log.debug("Seleccionat usuariaplicacio = ]" + usuariAplicacioID + "[");
            }

            origenPeticioDeFirma = seleccioFluxDeFirmesForm.getOrigenPeticioDeFirma();
            if (isDebug) {
                log.debug("Seleccionat origenPeticioDeFirma = ]" + origenPeticioDeFirma + "[");
            }

        }

        int tipus = seleccioFluxDeFirmesForm.getTipus();

        if (isDebug) {
            log.info("POST: Nom és " + nom);
            log.info("POST: Tipus és " + tipus);
        }

        FluxDeFirmesJPA fluxDeFirmes;
        try {
            switch (tipus) {

                case SeleccioFluxDeFirmesForm.TIPUS_SELECT_PRIMER_USUARI_DEL_FLUX:

                    String usuariEntitatPrimeraFirma = seleccioFluxDeFirmesForm.getId();
                    if (usuariEntitatPrimeraFirma == null || usuariEntitatPrimeraFirma.trim().length() == 0) {

                        if (isDebug) {
                            log.info(" HTTP usuarisFavorits: "
                                    + Arrays.toString(request.getParameterValues("usuarisFlux")));
                        }
                        ValidationUtils.rejectIfEmpty(result, "id", "selectflux.elegirusuari", null, null);

                        return getTileSeleccioFlux(); // "redirect:" + getContextWeb() + "/selectflux";
                    }

                    if (isDebug) {
                        log.debug("usuariEntitatPrimeraFirma == " + usuariEntitatPrimeraFirma);
                    }

                    Set<BlocDeFirmesJPA> blocDeFirmes = new HashSet<BlocDeFirmesJPA>();
                    int ordre = 0;
                    // for (String usuari : usuarisFavorits) {
                    FirmaJPA firma = new FirmaJPA();
                    firma.setDestinatariID(usuariEntitatPrimeraFirma);
                    firma.setObligatori(true);

                    UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb
                            .findByPrimaryKeyFull(usuariEntitatPrimeraFirma);
                    UsuariPersonaJPA usuariPersona = usuariEntitat.getUsuariPersona();
                    if (!usuariPersona.isUsuariIntern()) {
                        firma.setUsuariExternEmail(usuariPersona.getEmail());
                        firma.setUsuariExternIdioma(usuariPersona.getIdiomaID());
                        firma.setUsuariExternLlinatges(usuariPersona.getLlinatges());
                        firma.setUsuariExternNom(usuariPersona.getNom());

                        firma.setUsuariExternNivellSeguretat(ConstantsV2.USUARIEXTERN_SECURITY_LEVEL_TOKEN);
                        firma.setUsuariExternToken(firmaLogicaEjb.getUniqueTokenForFirma());
                    }

                    Set<FirmaJPA> firmes = new HashSet<FirmaJPA>();
                    firmes.add(firma);

                    BlocDeFirmesJPA bloc = new BlocDeFirmesJPA();
                    bloc.setFirmas(firmes);
                    bloc.setMinimDeFirmes(1);
                    bloc.setOrdre(ordre);
                    blocDeFirmes.add(bloc);

                    fluxDeFirmes = new FluxDeFirmesJPA();
                    fluxDeFirmes.setNom(nom);
                    fluxDeFirmes.setBlocDeFirmess(blocDeFirmes);

                break;

                case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_APLICACIO_COMPARTIT: {
                    Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaAplicacioCompartit();
                    if (isDebug) {
                        log.info("TIPUS_PLANTILLA_APLICACIO_COMPARTIT " + idPlantilla);
                    }
                    fluxDeFirmes = clonarFlux(nom, idPlantilla);
                }
                break;
                case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_USUARI: {
                    Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaUsuari();
                    if (isDebug) {
                        log.info("TIPUS_PLANTILLA_USUARI " + idPlantilla);
                    }
                    fluxDeFirmes = clonarFlux(nom, idPlantilla);
                }
                break;

                case SeleccioFluxDeFirmesForm.TIPUS_PLANTILLA_USUARI_COMPARTIT: {
                    Long idPlantilla = seleccioFluxDeFirmesForm.getFluxPlantillaPersonaCompartit();
                    if (isDebug) {
                        log.info("TIPUS_PLANTILLA_USUARI_COMPARTIT " + idPlantilla);
                    }
                    fluxDeFirmes = clonarFlux(nom, idPlantilla);
                }
                break;

                default:
                    // TODO traduir
                    HtmlUtils.saveMessageError(request, "Tipus de flux de firmes desconegut " + tipus);
                    return "redirect:" + getContextWeb() + "/selectflux";

            }

        } catch (I18NException e) {
            // TODO XYZ ZZZ TRA traduir icatch de I18NException
            String msg = "Error creant flux de firmes " + I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
            return "redirect:" + getContextWeb() + "/selectflux";
        } catch (Exception e) {
            // TODO XYZ ZZZ TRA traduir icatch de I18NException
            String msg = "Error creant flux de firmes " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
            return "redirect:" + getContextWeb() + "/selectflux";
        }

        request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_DE_SELECT_FLUX_DE_FIRMES, fluxDeFirmes);

        request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_USUARI_APLICACIO, usuariAplicacioID);

        request.getSession().setAttribute(SESSION_FLUX_DE_FIRMES_ORIGEN_PETICIO_DE_FIRMA, origenPeticioDeFirma);

        return "redirect:" + getContextWebToNewRequestMapping();

    }

    private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosDeUsuariAplicacio(String usuariAplicacioID)
            throws I18NException {
        SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFU;
        {
            Where whereFFU = PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.equal(usuariAplicacioID);
            subQueryFFU = plantillaFluxDeFirmesEjb.getSubQuery(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFU);
        }
        return subQueryFFU;
    }

    private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosDeUsuariEntitat(String usuariEntitat) throws I18NException {
        SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFU;
        {
            Where whereFFU = PlantillaFluxDeFirmesFields.USUARIENTITATID.equal(usuariEntitat);
            subQueryFFU = plantillaFluxDeFirmesEjb.getSubQuery(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFU);
        }
        return subQueryFFU;
    }

    private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosCompartitsDeUsuaris(String entitatActual)
            throws I18NException {

        // Usuaris-Entitat de la mateixa entitat
        SubQuery<UsuariEntitat, String> usuarisDeLaMevaEntitat;
        usuarisDeLaMevaEntitat = usuariEntitatLogicaEjb.getSubQuery(UsuariEntitatFields.USUARIENTITATID,
                Where.AND(UsuariEntitatFields.ENTITATID.equal(entitatActual), UsuariEntitatFields.ACTIU.equal(true)));

        // Compartiris a Tothom
        Where whereFFPS_true;
        {

            // Fluxos disponibles dels anteriors usuaris-entitat amb
            // compartir = true
            whereFFPS_true = Where.AND(PlantillaFluxDeFirmesFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat),
                    PlantillaFluxDeFirmesFields.COMPARTIR.equal(true));
        }
        // Compartits amb permis d'usuari directe
        Where whereFFPS_null_usuaris;
        {
            // Farà ús de l'usuari administrador d'entitat que està loguejat si estam en usuaris-app
            // Fluxos disponibles dels anteriors usuaris-entitat amb
            // compartir = null (Segons permisos)
            String currentusuariEntitatId = LoginInfo.getInstance().getUsuariEntitatID();
            SubQuery<PermisUsuariPlantilla, Long> permis;
            permis = permisUsuariPlantillaEjb.getSubQuery(PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID,
                    PermisUsuariPlantillaFields.USUARIENTITATID.equal(currentusuariEntitatId));

            whereFFPS_null_usuaris = Where.AND(PlantillaFluxDeFirmesFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat),
                    PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(permis),
                    PlantillaFluxDeFirmesFields.COMPARTIR.isNull());
        }

        // Compartits amb permis de pertença a grup

        Where whereFFPS_null_grups;
        {
            // Farà ús de l'usuari administrador d'entitat que està loguejat si estam en usuaris-app
            // Fluxos disponibles dels anteriors usuaris-entitat que estan definits en algun grups
            // d'usuaris de la plantilla amb compartir = null (Segons permisos)

            // (a) Cercar ID's dels grups que contenen usuaris de la meva entitat
            SubQuery<GrupEntitatUsuariEntitat, Long> grupsDelsUsuaris;
            grupsDelsUsuaris = grupEntitatUsuariEntitatEjb.getSubQuery(GrupEntitatUsuariEntitatFields.GRUPENTITATID,
                    GrupEntitatUsuariEntitatFields.USUARIENTITATID.in(usuarisDeLaMevaEntitat));

            // (b) Cercar Grups que estan en el subquery anterior i a més l'entitat és la meva
            SubQuery<GrupEntitat, Long> grups;
            grups = grupEntitatEjb.getSubQuery(GrupEntitatFields.GRUPENTITATID,
                    Where.AND(GrupEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                            GrupEntitatFields.GRUPENTITATID.in(grupsDelsUsuaris)));

            SubQuery<PermisGrupPlantilla, Long> permis;
            permis = permisGrupPlantillaEjb.getSubQuery(PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID,
                    PermisGrupPlantillaFields.GRUPENTITATID.in(grups));

            whereFFPS_null_grups = Where.AND(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.in(permis),
                    PlantillaFluxDeFirmesFields.COMPARTIR.isNull());
        }

        // Juntar-ho tot
        SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFPS;
        subQueryFFPS = plantillaFluxDeFirmesEjb.getSubQuery(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID,
                Where.OR(whereFFPS_true, whereFFPS_null_usuaris, whereFFPS_null_grups));

        return subQueryFFPS;
    }

    private SubQuery<PlantillaFluxDeFirmes, Long> getFluxosCompartitsPerAplicacions(String entitatActual)
            throws I18NException {
        SubQuery<PlantillaFluxDeFirmes, Long> subQueryFFAS;
        {
            // Usuaris-Aplicacio de la mateixa entitat
            SubQuery<UsuariAplicacio, String> uae;
            uae = usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.USUARIAPLICACIOID,
                    UsuariAplicacioFields.ENTITATID.equal(entitatActual));
            // Fluxos disponibles dels anteriors usuaris aplicacio amb
            // compartir = true

            Where whereFFAS = Where.AND(PlantillaFluxDeFirmesFields.USUARIAPLICACIOID.in(uae),
                    PlantillaFluxDeFirmesFields.COMPARTIR.equal(true));
            subQueryFFAS = plantillaFluxDeFirmesEjb.getSubQuery(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID, whereFFAS);
        }
        return subQueryFFAS;
    }
    
    
    protected FluxDeFirmesJPA clonarFlux(String nom, Long plantillaFluxID) throws Exception {

        FluxDeFirmesJPA fluxPlantilla = fluxDeFirmesLogicaEjb.findByPrimaryKeyFull(plantillaFluxID);
        if (fluxPlantilla == null) {
            // NOT FOUND
            String[] args = new String[] { I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmes"),
                    I18NUtils.tradueix("fluxDeFirmes.fluxDeFirmesID"), String.valueOf(plantillaFluxID) };

            throw new Exception(I18NUtils.tradueix("error.notfound", args));
        }
        fluxPlantilla.setFluxDeFirmesID(-1);
        // TODO check max lenght de NOM
        fluxPlantilla.setNom(nom);

        fluxPlantilla.setPlantillaFluxDeFirmes(null);
        fluxPlantilla.setPeticioDeFirma(null);

        log.info("CANVIANT CODI TOKEN DE getUsuariExternToken !!!!!");
        Set<BlocDeFirmesJPA> blocsOrig = fluxPlantilla.getBlocDeFirmess();
        for (BlocDeFirmesJPA blocDeFirmesOrig : blocsOrig) {
            Set<FirmaJPA> firmes = blocDeFirmesOrig.getFirmas();
            for (FirmaJPA firmaOrig : firmes) {
                if (firmaOrig.getUsuariExternNom() != null) {
                    firmaOrig.setUsuariExternToken(firmaLogicaEjb.getUniqueTokenForFirma());
                }
            }
        }

        return fluxPlantilla;

    }
}
