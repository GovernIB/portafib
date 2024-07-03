package es.caib.portafib.back.controller.admin;

import es.caib.portafib.back.controller.aden.GestioUsuariEntitatAdenController;
import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.controller.webdb.UsuariPersonaController;
import es.caib.portafib.back.form.SeleccioNifForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaFilterForm;
import es.caib.portafib.back.form.webdb.UsuariPersonaForm;
import es.caib.portafib.back.reflist.IdiomaSuportatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.ejb.UsuariEntitatService;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
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
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author anadal
 * 
 */
@Controller
@RequestMapping(value = "/admin/usuariPersona")
@SessionAttributes(types = { UsuariPersonaForm.class, UsuariPersonaFilterForm.class, SeleccioNifForm.class })
public class GestioUsuariPersonaController extends UsuariPersonaController {

    @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
    protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

    @EJB(mappedName = UsuariEntitatService.JNDI_NAME)
    protected UsuariEntitatService usuariEntitatEjb;

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @Autowired
    protected SeleccioUsuariValidator seleccioUsuariValidator;

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public String getTileForm() {
        return "gestioUsuariPersonaForm";
    }

    @Override
    public String getTileList() {
        return "gestioUsuariPersonaList";
    }

    @PostConstruct
    public void init() {
        this.idiomaRefList = new IdiomaSuportatRefList(this.idiomaRefList);
    }

    // ================= GESTIONA FORMULARI PREVI A ALTA PERSONA

    public String getTileNif() {
        return "selectUsuariPersonaByNifUsername";
    }

    @RequestMapping(value = "/alta", method = RequestMethod.GET)
    public ModelAndView selectUsuaripersonaByNifGet() throws Exception {
        ModelAndView mav = new ModelAndView(getTileNif());
        SeleccioNifForm seleccioNifForm = new SeleccioNifForm();
        seleccioNifForm.setTitol("usuaripersona.alta");
        seleccioNifForm.setSubtitol("usuaripersona.alta.introduirnifousername");
        seleccioNifForm.setCancelUrl("/canviarPipella/" + ConstantsV2.ROLE_ADEN);
        mav.addObject(seleccioNifForm);
        return mav;
    }

    @RequestMapping(value = "/alta", method = RequestMethod.POST)
    public ModelAndView selectUsuaripersonaByNifPost(SeleccioNifForm seleccioNifForm, BindingResult result,
            HttpServletRequest request) throws I18NException {

        ModelAndView mav = new ModelAndView(getTileNif());

        String nifOrUsername = seleccioNifForm.getNif();

        if (result.hasErrors()) {
            log.debug("entramos aqui result con errores");
            return mav;
        }

        // Si no han introduit Nif
        if (nifOrUsername == null || nifOrUsername.trim().length() == 0) {
            result.rejectValue("nif", "genapp.validation.required", new Object[] { "nif" }, null);
            return mav;
        }

        // Verificar que no hi ha usuari ja creat amb aquest nif o username
        UsuariPersonaJPA up = usuariPersonaLogicaEjb.getUsuariPersonaIDByUsernameOrAdministrationID(nifOrUsername);
        if (up == null) { // No existeix
            // Obtenim la informació del usuari del sistema d'autenticació.
            UserInfo pfui;
            try {
                pfui = usuariPersonaLogicaEjb.checkUsernameInUserInformationPlugin(nifOrUsername);
            } catch (I18NException i18ne) {
                try {
                    pfui = usuariPersonaLogicaEjb.checkAdministrationIDInUserInformationPlugin(nifOrUsername);
                } catch (I18NException i18ne2) {
                    I18NTranslation traduccio = i18ne.getTraduccio();
                    result.rejectValue("nif", traduccio.getCode(), I18NUtils.tradueixArguments(traduccio.getArgs()),
                            null);
                    return mav;
                }
            }

            request.getSession().setAttribute("pfui", pfui);
            mav = new ModelAndView(new RedirectView(getContextWeb() + "/new", true));
            return mav;

        } else {
            mav = new ModelAndView(new RedirectView(getContextWeb() + "/" + up.getUsuariPersonaID() + "/edit", true));
            return mav;
        }

    }

    // ================= GESTIONA FORMULARI PREVI A MODIFICACIO PERSONA

    public List<StringKeyValue> getUsuarisFavorits() {

        if (isAden()) {
            try {
                return SearchJSONController.favoritsToUsuariPersona(usuariEntitatLogicaEjb
                        .selectFavorits(LoginInfo.getInstance().getUsuariEntitatID(), null, false));
            } catch (I18NException e) {
                log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
            }
        }
        return null;
    }

    public String getUrlDataJsonSearch() {
        return "/common/json/usuaripersona";
    }

    public String getTileSeleccioUsuari() {
        return "seleccioUsuariForm_" + (isAden() ? "ADEN" : "ADMIN");
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.GET)
    public ModelAndView seleccioUsuariGet() throws Exception {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

        SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

        seleccioUsuariForm.setTitol("usuaripersona.modificar");
        seleccioUsuariForm.setSubtitol("usuaripersona.modificar.selecciousuari");
        seleccioUsuariForm.setCancelUrl("/canviarPipella/" + ConstantsV2.ROLE_ADMIN);
        seleccioUsuariForm.setUrlData(getUrlDataJsonSearch());

        seleccioUsuariForm.setUsuarisFavorits(getUsuarisFavorits());

        mav.addObject(seleccioUsuariForm);

        return mav;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public ModelAndView seleccioUsuariPost(SeleccioUsuariForm seleccioUsuariForm, BindingResult result,
            HttpServletRequest request) throws I18NException {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

        seleccioUsuariValidator.validate(seleccioUsuariForm, result);
        if (result.hasErrors()) {
            return mav;
        }

        String usuariPersonaID = seleccioUsuariForm.getId();

        // Comprovam que no existesqui un usuarientitat ja per a aquest usuari persona.
        UsuariPersonaJPA up = usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);

        if (up != null) {
            mav = new ModelAndView(new RedirectView(getContextWeb() + "/" + up.getUsuariPersonaID() + "/edit", true));
            return mav;
        } else {
            log.error("No he trobat un usuari persona amb ID=]" + usuariPersonaID + "[");
            return mav;
        }

    }

    // ------------------------------------------------------------------------------

    @Override
    public UsuariPersonaForm getUsuariPersonaForm(UsuariPersonaJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        UsuariPersonaForm form = super.getUsuariPersonaForm(_jpa, __isView, request, mav);
        UserInfo pfui;
        pfui = (UserInfo) request.getSession().getAttribute("pfui");
        // Obtenim de la sessió les dades del PortaFIBUserInfo
        if (form.isNou()) {
            if (pfui == null) {
                mav.setView(new RedirectView(getContextWeb() + "/alta", true));
                return new UsuariPersonaForm();
            }

            // Omplim l'usuariPersona amb les dades del PortaFIBUserInfo
            UsuariPersona up = form.getUsuariPersona();
            up.setNif(pfui.getAdministrationID().toUpperCase());
            up.setUsuariPersonaID(pfui.getUsername());
            up.setEmail(pfui.getEmail());
            up.setIdiomaID(pfui.getLanguage() == null ? Configuracio.getDefaultLanguage() : pfui.getLanguage());

            up.setLlinatges(pfui.getSurname1() + ((pfui.getSurname2() == null) ? "" : (" " + pfui.getSurname2())));

            up.setNom(pfui.getName());

            String nomPersona = "";
            if (up.getNom() != null) {
                nomPersona = up.getNom();
            }
            if (up.getLlinatges() != null) {
                if (nomPersona.length() != 0) {
                    nomPersona = nomPersona + " ";
                }
                nomPersona = nomPersona + up.getLlinatges();
            }

            up.setUsuariIntern(true);

            HtmlUtils.saveMessageInfo(request,
                    I18NUtils.tradueix("usuaripersona.verificacio", nomPersona, up.getNif()));
            // eliminam de la sessió el PortaFIBUserInfo.
            request.getSession().removeAttribute("pfui");
        } else {
            if (isAden()) {

                UsuariPersona up = form.getUsuariPersona();
                Long count = usuariEntitatEjb
                        .count(Where.AND(UsuariEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                                UsuariEntitatFields.USUARIPERSONAID.equal(up.getUsuariPersonaID())));
                if (count == 0) {
                    // No té usuari entitat definit, llavors afegim boto per donar-lo
                    // d'alta com a usuari-entitat
                    form.addAdditionalButton(
                            new AdditionalButton("fas fa-plus-circle", "usuaripersona.crearusuarientitat",
                                    getContextWeb() + "/addpersontothisentity/" + up.getUsuariPersonaID(),
                                    AdditionalButtonStyle.SUCCESS));
                }
            }
        }

        form.addReadOnlyField(NIF);

        // Feim només de lectura el camp ID perque no els puguin modificar.
        form.addReadOnlyField(USUARIPERSONAID);
        form.addHiddenField(RUBRICAID);
        form.addReadOnlyField(USUARIINTERN);
        form.addHiddenField(CONTRASENYA);

        return form;
    }

    /**
     * NOMES PER ADEN !!!!
     * @param request
     * @param response
     * @param session
     * @param usuariPersonaID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addpersontothisentity/{usuariPersonaID:.+}", method = RequestMethod.GET)
    public String addPersonToThisEntity(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @PathVariable String usuariPersonaID) throws Exception {

        session.setAttribute(GestioUsuariEntitatAdenController.USUARI_PERSONA_ID_HOLDER, usuariPersonaID);

        return "redirect:" + GestioUsuariEntitatAdenController.CONTEXTWEB + "/new";

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, UsuariPersonaForm usuariPersonaForm) {
        return getRedirectWhenModified(request, usuariPersonaForm, null);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, UsuariPersonaForm usuariPersonaForm,
            Throwable __e) {
        String usuariPersonaID = usuariPersonaForm.getUsuariPersona().getUsuariPersonaID();
        if (__e == null) {
            return "redirect:" + getContextWeb() + "/" + usuariPersonaID + "/edit";
        } else {
            return getRedirectWhenCancel(request, usuariPersonaID);
        }
    }

    @Override
    public String getRedirectWhenDelete(HttpServletRequest request, java.lang.String usuariPersonaID, Throwable __e) {
        return getRedirectWhenCancel(request, usuariPersonaID);
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.String usuariPersonaID) {
        return "redirect:/canviarPipella/" + (isAden() ? ConstantsV2.ROLE_ADEN : ConstantsV2.ROLE_ADMIN);
    }

    @Override
    public void delete(HttpServletRequest request, UsuariPersona usuariPersona) throws I18NException {

        Set<Long> fitxers = usuariPersonaLogicaEjb.deleteFull(usuariPersona.getUsuariPersonaID());
        this.borrarFitxers(fitxers);

    }

    @Override
    public UsuariPersonaJPA create(HttpServletRequest request, UsuariPersonaJPA usuariPersona)
            throws I18NException, I18NValidationException {
        return (UsuariPersonaJPA) usuariPersonaLogicaEjb.createFull(usuariPersona);
    }

    public boolean isAden() {
        return false;
    }

}
