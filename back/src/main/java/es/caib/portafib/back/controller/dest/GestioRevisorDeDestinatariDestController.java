package es.caib.portafib.back.controller.dest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.webdb.RevisorDeDestinatariController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.RevisorDeDestinatariFilterForm;
import es.caib.portafib.back.form.webdb.RevisorDeDestinatariForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatRefList;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.model.entity.RevisorDeDestinatari;
import es.caib.portafib.model.fields.RevisorDeDestinatariFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/dest/revisordedestinatari")
@SessionAttributes(
        types = { RevisorDeDestinatariForm.class, RevisorDeDestinatariFilterForm.class, SeleccioUsuariForm.class })
public class GestioRevisorDeDestinatariDestController extends RevisorDeDestinatariController
        implements RoleUsuariEntitatFields, RevisorDeDestinatariFields {

    @Autowired
    protected SeleccioUsuariValidator seleccioUsuariValidator;

    @EJB(mappedName = UsuariPersonaLogicaLocal.JNDI_NAME)
    protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.RevisorDeFirmaService revisorDeFirmaEjb;

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    protected FirmaLogicaLocal firmaLogicaEjb;

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariLogicaEjb;

    // ---------------------------------------------------------------------
    // == GESTIONA FORMULARI PREVI A ALTA-MODIFICACIO D'UN USUARI-ENTITAT ==
    // ---------------------------------------------------------------------

    protected String getTileSeleccioUsuari() {
        return "seleccioUsuariForm_DEST";
    }

    @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
    public ModelAndView seleccioUsuariGet(HttpServletRequest request) throws Exception {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());
        SeleccioUsuariForm seleccioUsuariForm = getSeleccioUsuariForm(request);
        mav.addObject(seleccioUsuariForm);

        return mav;
    }

    @RequestMapping(value = "/selecciousuari", method = RequestMethod.POST)
    public ModelAndView seleccioUsuariPost(SeleccioUsuariForm seleccioUsuariForm, BindingResult result,
            HttpServletRequest request) throws I18NException {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

        seleccioUsuariValidator.validate(seleccioUsuariForm, result);
        if (result.hasErrors()) {
            return mav;
        }

        String usuariPersonaID = seleccioUsuariForm.getId();
        UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.findByPrimaryKey(usuariPersonaID);

        // No necessaria ja que el formulari ja ho filtra
        if (usuariPersona == null) {
            result.rejectValue("id", "usuaripersona.noexisteix.username", new Object[] { usuariPersonaID }, null);
            return mav;
        }

        try {
            // Usuari loguejat
            LoginInfo login = LoginInfo.getInstance();
            String entitatActualID = login.getEntitatID();
            String destinatariID = login.getUsuariEntitatID();

            // Cercam si el revisor es troba en l'entitat actual

            UsuariEntitatJPA revisor = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatActualID,
                    usuariPersonaID);

            if (revisor == null) {
                throw new I18NException("usuarientitat.error.noexisteix", new I18NArgumentString(usuariPersonaID));
            }

            // TODO FALTA VERIFICAR SI JA ES REVISOR
            Long count = this.revisorDeDestinatariEjb.count(
                    Where.AND(DESTINATARIID.equal(destinatariID), REVISORID.equal(revisor.getUsuariEntitatID())));

            if (count != null && count == 0) {

                // Crear el RevisorDeDestinatari
                this.revisorDeDestinatariEjb.create(destinatariID, revisor.getUsuariEntitatID());

                HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("revisor.creat"));
            } else {
                HtmlUtils.saveMessageError(request, I18NUtils.tradueix("revisor.hasRole"));
            }

            return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));

        } catch (I18NException e) {
            log.warn(I18NUtils.getMessage(e), e);
            I18NTranslation trans = e.getTraduccio();
            String[] args = I18NUtils.tradueixArguments(trans.getArgs());
            result.rejectValue("id", trans.getCode(), args, null);
            return mav;
        }

    }

    protected SeleccioUsuariForm getSeleccioUsuariForm(HttpServletRequest request) {
        SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

        seleccioUsuariForm.setTitol("revisor.gestio");
        seleccioUsuariForm.setSubtitol("revisor.gestio.subtitol");
        seleccioUsuariForm.setCancelUrl(getContextWeb() + "/list");
        seleccioUsuariForm.setUrlData("/common/json/usuaripersonaentitatintern");

        seleccioUsuariForm.setUsuarisFavorits(null);

        return seleccioUsuariForm;
    }

    //=====  LLISTAT DE REVISORS DE DESTINATARI

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return RevisorDeDestinatariFields.DESTINATARIID.equal(LoginInfo.getInstance().getUsuariEntitatID());
    }

    @PostConstruct
    public void init() {

        UsuariPersonaQueryPath upqp = new UsuariEntitatQueryPath().USUARIPERSONA();
        this.usuariEntitatRefList = new UsuariEntitatRefList(this.usuariEntitatRefList);
        this.usuariEntitatRefList.setSelects(new Select<?>[] { upqp.LLINATGES().select, new SelectConstant(", "),
                upqp.NOM().select, new SelectConstant(" ("), upqp.NIF().select, new SelectConstant(")") });

    }

    @Override
    public String getTileList() {
        return "revisorDeDestinatariList_ROLE_DEST";
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public RevisorDeDestinatariFilterForm getRevisorDeDestinatariFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        RevisorDeDestinatariFilterForm filterForm = super.getRevisorDeDestinatariFilterForm(pagina, mav, request);

        if (filterForm.isNou()) {
            filterForm.setVisibleMultipleSelection(false);
            filterForm.setDeleteSelectedButtonVisible(false);
            filterForm.setEditButtonVisible(false);

            filterForm.addHiddenField(DESTINATARIID);
            filterForm.addHiddenField(REVISORDEDESTINATARIID);
        }

        return filterForm;
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearRevisorDeDestinatariGet(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/selecciousuari", true));

        return mav;
    }

    @Override
    public void delete(HttpServletRequest request, RevisorDeDestinatari revisorDeDestinatari) throws I18NException {

        String usuariEntitatID = revisorDeDestinatari.getRevisorID();

        I18NTranslation i18n = revisorDeDestinatariLogicaEjb.pucEsborrarRevisor(usuariEntitatID);

        if (i18n == null) {
            super.delete(request, revisorDeDestinatari);
        } else {
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix(i18n));
        }
    }

}
