package es.caib.portafib.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.portafib.ejb.AnnexFirmatService;
import es.caib.portafib.ejb.AnnexService;
import es.caib.portafib.ejb.ColaboracioDelegacioService;
import es.caib.portafib.ejb.EntitatService;
import es.caib.portafib.ejb.FirmaService;
import es.caib.portafib.ejb.PeticioDeFirmaService;
import es.caib.portafib.ejb.PluginCridadaService;
import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.ejb.UsuariPersonaService;
import es.caib.portafib.logic.PluginLogicaLocal;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.form.webdb.FitxerForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PluginCridadaFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;

/**
 * Fitxer que es troben a BBDD però no tenen cap relació amb cap entitat de la BBDD. Per tant, es poden eliminar sense problemes.
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/fitxersorfesbbdd")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "fitxers.orfes.bbdd", order = 155)
public class FitxersOrfesDeBaseDeDadesAdminController extends FitxerController {

    @EJB(mappedName = AnnexService.JNDI_NAME)
    protected AnnexService annexEjb;

    @EJB(mappedName = ColaboracioDelegacioService.JNDI_NAME)
    protected ColaboracioDelegacioService colaboracioDelegacioEjb;

    @EJB(mappedName = PeticioDeFirmaService.JNDI_NAME)
    protected PeticioDeFirmaService peticioDeFirmaEjb;

    @EJB(mappedName = FirmaService.JNDI_NAME)
    protected FirmaService firmaEjb;

    @EJB(mappedName = AnnexFirmatService.JNDI_NAME)
    protected AnnexFirmatService annexFirmatEjb;

    @EJB(mappedName = EntitatService.JNDI_NAME)
    protected EntitatService entitatEjb;

    @EJB(mappedName = UsuariPersonaService.JNDI_NAME)
    protected UsuariPersonaService usuariPersonaEjb;

    // Plugin EJB    
    @EJB(mappedName = PluginLogicaLocal.JNDI_NAME)
    protected PluginLogicaLocal pluginEjb;

    @EJB(mappedName = PluginCridadaService.JNDI_NAME)
    protected PluginCridadaService pluginCridadaEjb;

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @Override
    public String getTileList() {
        return "fitxersOrfesList";
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
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        FitxerFilterForm fitxerFilterForm;
        fitxerFilterForm = (FitxerFilterForm) super.getFitxerFilterForm(pagina, mav, request);

        if (fitxerFilterForm.isNou()) {
            fitxerFilterForm.setTitleCode("fitxers.orfes.bbdd");
            fitxerFilterForm.setAddButtonVisible(false);
            fitxerFilterForm.setEditButtonVisible(false);
        }

        return fitxerFilterForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Where parent = super.getAdditionalCondition(request);

        Where w1 = FITXERID.notIn(annexEjb.getSubQuery(AnnexFields.FITXERID, AnnexFields.FITXERID.isNotNull()));

        Where w2 = FITXERID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.FITXERAFIRMARID,
                PeticioDeFirmaFields.FITXERAFIRMARID.isNotNull()));

        Where w3 = FITXERID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.FITXERADAPTATID,
                PeticioDeFirmaFields.FITXERADAPTATID.isNotNull()));

        Where w4 = FITXERID.notIn(peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID,
                PeticioDeFirmaFields.FIRMAORIGINALDETACHEDID.isNotNull()));

        Where w5 = FITXERID
                .notIn(firmaEjb.getSubQuery(FirmaFields.FITXERFIRMATID, FirmaFields.FITXERFIRMATID.isNotNull()));
        Where w6 = FITXERID
                .notIn(annexFirmatEjb.getSubQuery(AnnexFirmatFields.FITXERID, AnnexFirmatFields.FITXERID.isNotNull()));

        Where w7 = FITXERID.notIn(
                usuariPersonaEjb.getSubQuery(UsuariPersonaFields.RUBRICAID, UsuariPersonaFields.RUBRICAID.isNotNull()));

        Where w8 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.PDFAUTORITZACIODELEGACIOID,
                EntitatFields.PDFAUTORITZACIODELEGACIOID.isNotNull()));
        Where w9 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.FAVICONID, EntitatFields.FAVICONID.isNotNull()));
        Where w10 = FITXERID
                .notIn(entitatEjb.getSubQuery(EntitatFields.LOGOWEBID, EntitatFields.LOGOWEBID.isNotNull()));
        Where w11 = FITXERID
                .notIn(entitatEjb.getSubQuery(EntitatFields.LOGOWEBPEUID, EntitatFields.LOGOWEBPEUID.isNotNull()));
        Where w12 = FITXERID
                .notIn(entitatEjb.getSubQuery(EntitatFields.LOGOSEGELLID, EntitatFields.LOGOSEGELLID.isNotNull()));
        Where w13 = FITXERID.notIn(pluginEjb.getSubQuery(PluginFields.ICONAID, PluginFields.ICONAID.isNotNull()));

        Where w14 = FITXERID.notIn(pluginCridadaEjb.getSubQuery(PluginCridadaFields.RETORNFITXERID,
                PluginCridadaFields.RETORNFITXERID.isNotNull()));

        Where w15 = FITXERID.notIn(pluginCridadaEjb.getSubQuery(PluginCridadaFields.PARAMETRESFITXERID,
                PluginCridadaFields.PARAMETRESFITXERID.isNotNull()));

        Where w16 = FITXERID.notIn(colaboracioDelegacioEjb.getSubQuery(ColaboracioDelegacioFields.FITXERAUTORITZACIOID,
                ColaboracioDelegacioFields.FITXERAUTORITZACIOID.isNotNull()));

        Where w17 = FITXERID.notIn(usuariAplicacioEjb.getSubQuery(UsuariAplicacioFields.LOGOSEGELLID,
                UsuariAplicacioFields.LOGOSEGELLID.isNotNull()));

        return Where.AND(parent, w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13, w14, w15, w16, w17);
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "FitxersOrfes_FilterForm";
    }

}
