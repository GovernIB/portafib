package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.common.SearchJSONController;
import es.caib.portafib.back.form.PeticionsDeFirmaDeDestinatariFilterForm;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaFilterForm;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.SeleccioUsuariValidator;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.ejb.FirmaService;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
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

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 */
@Controller
@RequestMapping(value = PeticioDeFirmaDeDestinatariAdenController.CONTEXT_WEB)
@SessionAttributes(types = { PeticionsDeFirmaDeDestinatariFilterForm.class, PeticioDeFirmaForm.class })
@MenuOption(
        group = Tab.MENU_ADEN,
        labelCode = "peticionsdefirma.destinatari",
        baseLink = "/aden/peticionsdedestinatari",
        relativeLink = "/selecciousuari",
        order = 220)
public class PeticioDeFirmaDeDestinatariAdenController extends AbstractPeticioDeFirmaAdenController {
    
    public static final String CONTEXT_WEB = "/aden/peticionsdedestinatari";

    public static final String USUARI_ENTITAT_ID_HOLDER = "PeticionsDeFirmaDeDestinatariAdenController_USUARI_ENTITAT_ID_HOLDER";

    @EJB(mappedName = FirmaService.JNDI_NAME)
    protected FirmaService firmaEjb;
    
    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @Autowired
    protected SeleccioUsuariValidator seleccioUsuariValidator;

    // ===== GESTIONA FORMULARI PREVI A ALTA-MODIFICACIO D'UN USUARI-ENTITAT

    public String getTileSeleccioUsuari() {
        return "seleccioUsuariForm_ADEN";
    }

    @RequestMapping(value = "/selecciousuari", method = RequestMethod.GET)
    public ModelAndView seleccioUsuariGet() throws Exception {

        ModelAndView mav = new ModelAndView(getTileSeleccioUsuari());

        SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

        seleccioUsuariForm.setTitol("peticionsdefirma.destinatari");
        seleccioUsuariForm.setSubtitol("peticionsdefirma.destinatari.nif.subtitol");
        seleccioUsuariForm.setCancelUrl("/canviarPipella/" + Constants.ROLE_ADEN);
        seleccioUsuariForm.setUrlData("/common/json/usuarientitat");

        try {
            seleccioUsuariForm.setUsuarisFavorits(SearchJSONController.favoritsToUsuariEntitat(
                    usuariEntitatLogicaEjb.selectFavorits(LoginInfo.getInstance().getUsuariEntitatID(), null, false)));
        } catch (I18NException e) {
            log.error("Error cercant favorits" + I18NUtils.getMessage(e), e);
        }

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

        String usuariEntitatID = seleccioUsuariForm.getId();

        // TODO Check si aquest usuari-entitat té el rol DESTINATARI

        request.getSession().setAttribute(USUARI_ENTITAT_ID_HOLDER, usuariEntitatID);
        mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        return mav;

    }

    // --------------------------------------------------------
    
    /**
     * AnnexAdenController conté aquesta ruta, si es vol canviar, cal canviar-la també allà
     */
    public String getAnnexPath() {
        return CONTEXT_WEB + "/gestioannexes" + "/list";
    }

    @Override
    public String getTileList() {
        return "PeticionsDeFirmaDeDestinatari";
    }

    @Override
    public TipusSolicitant getTipusSolicitant() {
        return TipusSolicitant.SOLICITANT_TOTS;
    }

    @Override
    public boolean addCreateButton() {
        return false;
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PeticionsDeFirmaDeDestinatari_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        Where wParent = super.getAdditionalCondition(request);

        PeticionsDeFirmaDeDestinatariFilterForm filterForm;
        filterForm = (PeticionsDeFirmaDeDestinatariFilterForm) request.getSession()
                .getAttribute(getSessionAttributeFilterForm());

        String usuariEntitatID = filterForm.getUsuariEntitatID();
        Where wPeticinsDeUsuariEntitat;
        wPeticinsDeUsuariEntitat = getPeticionsActivesDeUsuariEntitat2(firmaEjb, usuariEntitatID);

        return Where.AND(wParent, wPeticinsDeUsuariEntitat);
    }

    public static Where getPeticionsActivesDeUsuariEntitat2(FirmaService firmaEjb, String usuariEntitatID)
            throws I18NException {
        Where wPeticinsDeUsuariEntitat;
        FirmaQueryPath firmaQueryPath = new FirmaQueryPath();

        PeticioDeFirmaQueryPath peticio = firmaQueryPath.BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();

        // Seleccionam les peticions de firma que contenen alguna
        // firma activa d'un usuari-entitat associat al nif NIF
        // NOTA: Una firma que conté un càrrec, en el moment que s'activa,
        // es converteix l'usuari-carrec en usuari-entitat

        // Associam al usuariEntitatID destinatari
        Where w = firmaQueryPath.USUARIENTITAT().USUARIENTITATID().equal(usuariEntitatID);

        wPeticinsDeUsuariEntitat =  PETICIODEFIRMAID.in(firmaEjb.getSubQuery(peticio.PETICIODEFIRMAID(), w));
        //Where wPeticioActiva = PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES);
        return Where.AND(wPeticinsDeUsuariEntitat);
    }

    @Override
    public PeticioDeFirmaFilterForm getPeticioDeFirmaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {

        PeticioDeFirmaFilterForm peticioDeFirmaFilterForm;
        peticioDeFirmaFilterForm = super.getPeticioDeFirmaFilterForm(pagina, mav, request);

        // Check: Sempre ha d'haver passat per la pantalla de nif/username
        String usuariEntitatID;
        usuariEntitatID = (String) request.getSession().getAttribute(USUARI_ENTITAT_ID_HOLDER);

        if (usuariEntitatID == null || usuariEntitatID.trim().length() == 0) {
            mav.setView(new RedirectView(getContextWeb() + "/selecciousuari", true));
            return peticioDeFirmaFilterForm;
        }

        peticioDeFirmaFilterForm = new PeticionsDeFirmaDeDestinatariFilterForm(peticioDeFirmaFilterForm);

        ((PeticionsDeFirmaDeDestinatariFilterForm) peticioDeFirmaFilterForm).setUsuariEntitatID(usuariEntitatID);

        UsuariEntitatJPA ue;
        ue = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatID);

        peticioDeFirmaFilterForm.setSubTitleCode(
                "=" + I18NUtils.tradueix("peticionsdefirma.destinatari.subtitol", Utils.getNom(ue.getUsuariPersona())));

        if (peticioDeFirmaFilterForm.isNou()) {

            peticioDeFirmaFilterForm.setTitleCode("peticionsdefirma.destinatari");

            peticioDeFirmaFilterForm.setGroupBy(TIPUSESTATPETICIODEFIRMAID.javaName); // =
                                                                                      // tipusEstatPeticioDeFirmaID
            peticioDeFirmaFilterForm.setGroupValue(String.valueOf(TIPUSESTATPETICIODEFIRMA_ENPROCES));

        }
        return peticioDeFirmaFilterForm;
    }

    @Override
    public boolean isNomesConsulta() {
        return false;
    }
}
