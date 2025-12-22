package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.RevisorDeDestinatariController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.RevisorDeDestinatariFilterForm;
import es.caib.portafib.back.form.webdb.RevisorDeDestinatariForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.model.fields.RevisorDeDestinatariFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.persistence.UsuariEntitatJPA;

/**
 * Controlador per a la gestió de revisors de destinatari dins del context ADEN (Administració Electrònica).
 * 
 * <p>Aquest controlador permet:</p>
 * <ul>
 *   <li>Seleccionar un usuari destinatari per visualitzar els seus revisors</li>
 *   <li>Llistar els revisors associats a un destinatari concret</li>
 *   <li>Utilitzar usuaris favorits per facilitar la cerca</li>
 * </ul>
 * 
 * <p>El flux de treball és:</p>
 * <ol>
 *   <li>L'usuari accedeix a la pantalla de selecció d'usuari destinatari</li>
 *   <li>Selecciona un usuari (des de favorits o mitjançant cerca)</li>
 *   <li>El sistema guarda l'ID de l'usuari seleccionat en sessió</li>
 *   <li>Es mostra el llistat de revisors filtrat per aquest destinatari</li>
 * </ol>
 * 
 * <p>La pantalla de llistat està en mode només lectura (sense botons d'afegir, editar o eliminar).</p>
 * 
 * @author anadal
 * @since 19 dic 2025 12:12:41
 */
@MenuOption(
        group = Tab.MENU_ADEN,
        labelCode = "revisor.de.destinatari",
        baseLink = RevisorsDeDestinatariAdenController.CONTEXT_WEB,
        relativeLink = "/selecciousuari",
        order = 110)
@Controller
@RequestMapping(value = RevisorsDeDestinatariAdenController.CONTEXT_WEB)
@SessionAttributes(types = { RevisorDeDestinatariForm.class, RevisorDeDestinatariFilterForm.class })
public class RevisorsDeDestinatariAdenController extends RevisorDeDestinatariController {

    /** Context web del controlador */
    public static final String CONTEXT_WEB = "/aden/revisorDeDestinatari";

    /** Clau de sessió per emmagatzemar l'ID de l'usuari entitat seleccionat */
    public static final String USUARI_ENTITAT_ID_HOLDER = "USUARI_ENTITAT_ID_HOLDER";

    /** Lògica de negoci per a la gestió d'usuaris entitat */
    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    /** Validador del formulari de selecció d'usuari */
    @Autowired
    protected SeleccioUsuariValidator seleccioUsuariValidator;

    // ------------------------------------------------
    // ========  Selecció usuari Destinatari ==========
    // ------------------------------------------------

    /**
     * Obté la llista d'usuaris favorits de l'usuari actual.
     * 
     * @return llista de parells clau-valor amb els usuaris favorits, o null si hi ha error
     */
    public List<StringKeyValue> getUsuarisFavorits() {
        try {
            return SearchJSONController.favoritsToUsuariPersona(
                    usuariEntitatLogicaEjb.selectFavorits(LoginInfo.getInstance().getUsuariEntitatID(), null, false));
        } catch (I18NException e) {
            log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
            return null;
        }
    }

    /**
     * Retorna la URL per a la cerca JSON d'usuaris entitat interns.
     * 
     * @return URL del servei de cerca
     */
    public String getUrlDataJsonSearch() {
        return "/common/json/usuarientitatintern";
    }

    /**
     * Retorna el nom del tile per a la pantalla de selecció d'usuari.
     * 
     * @return nom del tile
     */
    public String getTileSeleccioUsuari() {
        return "seleccioUsuariForm_ADEN";
    }

    /**
     * Mostra el formulari de selecció d'usuari destinatari (GET).
     * 
     * @param request petició HTTP
     * @return vista amb el formulari de selecció
     * @throws Exception si hi ha error en el processament
     */
    @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
    public ModelAndView seleccioUsuariGet(HttpServletRequest request) throws Exception {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

        SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

        seleccioUsuariForm.setTitol("revisor.de.destinatari");
        seleccioUsuariForm.setSubtitol("revisor.de.destinatari.seleccio");
        seleccioUsuariForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
        seleccioUsuariForm.setUrlData(getUrlDataJsonSearch());

        seleccioUsuariForm.setUsuarisFavorits(getUsuarisFavorits());

        mav.addObject(seleccioUsuariForm);

        request.getSession().removeAttribute(USUARI_ENTITAT_ID_HOLDER);

        return mav;
    }

    /**
     * Processa la selecció d'usuari destinatari (POST).
     * 
     * <p>Valida el formulari, comprova que l'usuari existeix i, si tot és correcte,
     * guarda l'ID en sessió i redirigeix al llistat de revisors.</p>
     * 
     * @param seleccioUsuariForm formulari amb les dades de selecció
     * @param result resultat de la validació
     * @param request petició HTTP
     * @return vista amb el llistat de revisors o el formulari amb errors
     * @throws I18NException si hi ha error d'internacionalització
     */
    @RequestMapping(value = "/selecciousuari", method = RequestMethod.POST)
    public ModelAndView seleccioUsuariPost(SeleccioUsuariForm seleccioUsuariForm, BindingResult result,
            HttpServletRequest request) throws I18NException {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

        seleccioUsuariValidator.validate(seleccioUsuariForm, result);
        if (result.hasErrors()) {
            return mav;
        }

        String usuariEntitatID = seleccioUsuariForm.getId();

        UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);

        if (ue == null) {

            HtmlUtils.saveMessageError(request, "L'usuari entitat " + usuariEntitatID + " no existeix.");

            mav.setView(new RedirectView(getContextWeb() + "/new", true));
        } else {

            request.getSession().setAttribute(USUARI_ENTITAT_ID_HOLDER, usuariEntitatID);
            mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        }
        return mav;

    }

    @Override
    public String getEntityNameCode() {
        return "revisor.de.destinatari";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "revisors.de.destinatari";
    }

    @Override
    public String getTileList() {
        return "revisorDeDestinatariListAden";
    }

    // ------------------------------------------------

    /**
     * Inicialitza el controlador configurant la llista de referències d'usuaris entitat.
     * 
     * <p>Configura els selects per mostrar: Llinatges, Nom (UsuariPersonaID)</p>
     */
    @PostConstruct
    public void init() {

        UsuariPersonaQueryPath upqp = new UsuariEntitatQueryPath().USUARIPERSONA();
        this.usuariEntitatRefList = new UsuariEntitatRefList(this.usuariEntitatRefList);
        this.usuariEntitatRefList.setSelects(new Select<?>[] { upqp.LLINATGES().select, new SelectConstant(", "),
                upqp.NOM().select, new SelectConstant(" ("), upqp.USUARIPERSONAID().select, new SelectConstant(")") });
    }

    /**
     * Afegeix una condició addicional per filtrar els revisors pel destinatari seleccionat.
     * 
     * <p>Utilitza l'ID de l'usuari entitat guardat en sessió per filtrar els resultats.</p>
     * 
     * @param request petició HTTP amb la sessió
     * @return condició de filtratge o null si no hi ha usuari seleccionat
     * @throws I18NException si hi ha error d'internacionalització
     */
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        String usuariEntitatID = (String) request.getSession().getAttribute(USUARI_ENTITAT_ID_HOLDER);
        if (usuariEntitatID != null) {
            return RevisorDeDestinatariFields.DESTINATARIID.equal(usuariEntitatID);
        } else {
            return null;
        }
    }

    /**
     * Personalitza el formulari de filtre del llistat de revisors.
     * 
     * <p>Configura el llistat en mode només lectura, sense botons d'edició
     * i amb un subtítol que mostra l'usuari destinatari seleccionat.</p>
     * 
     * @param pagina número de pàgina
     * @param mav model i vista
     * @param request petició HTTP
     * @return formulari de filtre personalitzat
     * @throws I18NException si hi ha error d'internacionalització
     */
    @Override
    public RevisorDeDestinatariFilterForm getRevisorDeDestinatariFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        RevisorDeDestinatariFilterForm revisorDeDestinatariFilterForm;
        revisorDeDestinatariFilterForm = super.getRevisorDeDestinatariFilterForm(pagina, mav, request);

        if (revisorDeDestinatariFilterForm.isNou()) {

            String usuariEntitatID = (String) request.getSession().getAttribute(USUARI_ENTITAT_ID_HOLDER);

            revisorDeDestinatariFilterForm.setTitleCode("revisor.de.destinatari");
            revisorDeDestinatariFilterForm
                    .setSubTitleCode("=" + I18NUtils.tradueix("revisor.de.destinatari.subtitol", usuariEntitatID));

            revisorDeDestinatariFilterForm.setAddButtonVisible(false);
            revisorDeDestinatariFilterForm.setDeleteButtonVisible(false);
            revisorDeDestinatariFilterForm.setEditButtonVisible(false);
            revisorDeDestinatariFilterForm.setVisibleMultipleSelection(false);
            revisorDeDestinatariFilterForm.setItemsPerPage(50);
            revisorDeDestinatariFilterForm.setDeleteSelectedButtonVisible(false);

            revisorDeDestinatariFilterForm.setFilterByFields(new ArrayList<>());

        }

        return revisorDeDestinatariFilterForm;

    }
}
