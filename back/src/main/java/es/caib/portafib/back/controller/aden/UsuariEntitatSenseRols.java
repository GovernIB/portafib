package es.caib.portafib.back.controller.aden;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Select;

import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select4Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select4Values;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.portafib.back.controller.webdb.UsuariEntitatController;
import es.caib.portafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.UsuariEntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.ejb.FirmaService;
import es.caib.portafib.logic.ColaboracioDelegacioLogicaLocal;
import es.caib.portafib.logic.EstatDeFirmaLogicaLocal;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.model.entity.ColaboracioDelegacio;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.RevisorDeDestinatariFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.utils.Constants;

/**
 * Controlador per a la gestió de usuaris sense rols.
 * @author anadal
 * 13 feb 2025 12:02:29
 */
@MenuOption(labelCode = "usuarientitat.senserols.plural", order = 420, group = Constants.ROLE_ADEN)
@Controller
@RequestMapping(value = "/aden/usuarientitatsenserols")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class })
public class UsuariEntitatSenseRols extends UsuariEntitatController {

    @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    protected FirmaLogicaLocal firmaLogicaEjb;

    @EJB(mappedName = FirmaService.JNDI_NAME)
    protected FirmaService firmaEjb;

    @EJB(mappedName = EstatDeFirmaLogicaLocal.JNDI_NAME)
    protected EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

    @EJB(mappedName = ColaboracioDelegacioLogicaLocal.JNDI_NAME)
    protected ColaboracioDelegacioLogicaLocal colaboracioDelegacioLogicaEjb;

    protected static final int REVISOR_POS = 1;

    @Override
    public String getEntityNameCode() {
        return "usuarientitat.senserols";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "usuarientitat.senserols.plural";
    }

    @Override
    public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        UsuariEntitatFilterForm usuariEntitatFilterForm = super.getUsuariEntitatFilterForm(pagina, mav, request);

        if (usuariEntitatFilterForm.isNou()) {
            usuariEntitatFilterForm.setAddButtonVisible(false);
            usuariEntitatFilterForm.setDeleteButtonVisible(false);
            usuariEntitatFilterForm.setEditButtonVisible(false);

            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(UsuariEntitatFields.ALL_USUARIENTITAT_FIELDS));

            hiddenFields.remove(USUARIENTITATID);
            hiddenFields.remove(USUARIPERSONAID);
            hiddenFields.remove(EMAIL);

            usuariEntitatFilterForm.setHiddenFields(hiddenFields);

            AdditionalField<String, String> adfield4 = new AdditionalField<String, String>();
            adfield4.setCodeName("usuarientitat.senserols.info");
            adfield4.setPosition(REVISOR_POS);
            // Els valors s'ompliran al mètode postList()
            adfield4.setValueMap(new HashMap<String, String>());
            adfield4.setEscapeXml(false);
            usuariEntitatFilterForm.addAdditionalField(adfield4);

        }

        return usuariEntitatFilterForm;

    }

    @RequestMapping(value = "/desactivar/{usuariEntitatID:.+}", method = RequestMethod.GET)
    public ModelAndView desactivarUsuariEntitat(@PathVariable("usuariEntitatID")
    String usuariEntitatID, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            usuariEntitatLogicaEjb.desactivarUsuariEntitat(usuariEntitatID);
        } catch (I18NException i18ne) {
            String msg = I18NUtils.getMessage(i18ne);
            log.error(msg, i18ne);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = I18NUtils.tradueix("error.unknown", e.getMessage());
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        return mav;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariEntitatFilterForm usuariEntitatForm,
            List<UsuariEntitat> list) throws I18NException {

        usuariEntitatForm.getAdditionalButtonsByPK().clear();

        Map<String, String> map;
        map = (Map<String, String>) usuariEntitatForm.getAdditionalField(REVISOR_POS).getValueMap();
        map.clear();

        for (UsuariEntitat usuariEntitat : list) {
            StringBuilder htmlCode = new StringBuilder("<table class='table' border='1px'>");
            final String ueID = usuariEntitat.getUsuariEntitatID();

            // Desactivar Usuari
            usuariEntitatForm.addAdditionalButtonByPK(ueID, new AdditionalButton("fas fa-ban", "desactivar",
                    getContextWeb() + "/desactivar/" + ueID, AdditionalButtonStyle.WARNING));

            // Omplir columna de Revisor DE
            {
                List<String> destinataris = revisorDeDestinatariEjb.executeQuery(
                        RevisorDeDestinatariFields.DESTINATARIID, RevisorDeDestinatariFields.REVISORID.equal(ueID));

                if (destinataris != null && !destinataris.isEmpty()) {

                    for (String revisat : destinataris) {
                        // TODO  FALTA
                        htmlCode.append("<tr class=\"table-info\"><td>Revisor de: "
                                + revisat + "</td>" + "<td>" + crearBoto(request.getContextPath() + getContextWeb()
                                        + "/eliminarRevisor/" + revisat + "/" + ueID, "Eliminar Revisor")
                                + "</td></tr>");

                    }
                }

            }

            // Cercam si es col.laborador /delegat
            {
                Where w1 = ColaboracioDelegacioFields.ACTIVA.equal(true);
                Where w2 = ColaboracioDelegacioFields.COLABORADORDELEGATID.equal(ueID);
                Where w = Where.AND(w1, w2);

                List<ColaboracioDelegacio> coldel = colaboracioDelegacioLogicaEjb.select(w);

                for (ColaboracioDelegacio cd : coldel) {
                    htmlCode.append("<tr class=\"table-success\"><td>L´usuari " + ueID + " és "
                            + (cd.isEsDelegat() ? "delegat" : "col·laborador") + " de " + cd.getDestinatariID()
                            + "</td>" + "<td>"
                            + crearBoto(
                                    request.getContextPath() + getContextWeb() + "/eliminarDelegacioColaboracio/"
                                            + cd.getColaboracioDelegacioID(),
                                    "Eliminar " + (cd.isEsDelegat() ? "delegació" : "col·laboració"))
                            + "</td></tr>");
                }
            }

            // Cercam si estam en peticions de Firma en marxa
            {
                // Estats de Firma on està l'usuari
                Where w1 = Where.AND(EstatDeFirmaFields.USUARIENTITATID.equal(ueID),
                        EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull(),
                        EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                                .in(new Long[] { Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR,
                                        Constants.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR }));

                EstatDeFirmaQueryPath efqp = new EstatDeFirmaQueryPath();

                Where w2 = efqp.TIPUSESTATDEFIRMAFINALID().isNull();

                PeticioDeFirmaQueryPath pfqp = efqp.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();

                Select<Long> PET_ID = pfqp.PETICIODEFIRMAID().select;
                Select<String> PET_NOM = pfqp.TITOL().select;

                Where w3 = pfqp.TIPUSESTATPETICIODEFIRMAID()
                        .equal(Integer.valueOf(Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES));

                Select4Columns<Long, String, Long, Long> select;
                select = new Select4Columns<Long, String, Long, Long>(PET_ID, PET_NOM,
                        EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.select,
                        efqp.FIRMA().BLOCDEFIRMES().FLUXDEFIRMES().FLUXDEFIRMESID().select);

                Where w = Where.AND(w1, w2, w3);

                List<Select4Values<Long, String, Long, Long>> peticions = estatDeFirmaLogicaEjb.executeQuery(select, w);

                for (Select4Values<Long, String, Long, Long> peticio : peticions) {
                    // TODO 
                    htmlCode.append("<tr class=\"table-danger\"><td>"
                            + (peticio.getValue3().equals(Constants.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR)
                                    ? "Assignat per firmar"
                                    : " Assignat per validar")

                            + " en petició: " + peticio.getValue2() + "</td>" + "<td>"

                            + crearBotoView(
                                    request.getContextPath() + "/aden/fluxdefirmes/view/" + peticio.getValue4()
                                            + "?redirectOnModify=" + getContextWeb() + "/list&readOnly=true",
                                    "Flux de Firmes")

                            + crearBoto(request.getContextPath() + getContextWeb() + "/rebutjarPeticio/" + ueID + "/"
                                    + peticio.getValue1(), "Rebutjar Peticio")
                            + "</td></tr>");
                }
            }

            {
                // Cercam els carrecs que ocupa aquesta persona en la meva entitat
                Where w1 = UsuariEntitatFields.CARREC.isNotNull();
                Where w2 = UsuariEntitatFields.USUARIPERSONAID.equal(usuariEntitat.getUsuariPersonaID());
                Where w = Where.AND(w1, w2);

                List<UsuariEntitat> usuarisEntitat = usuariEntitatLogicaEjb.select(w);

                if (usuarisEntitat.size() != 0) {

                    StringBuilder sb = new StringBuilder();
                    for (UsuariEntitat ue : usuarisEntitat) {
                        sb.append("  - " + ue.getCarrec() + "[" + ue.getUsuariEntitatID()+ "]<br/>");
                    }

                    htmlCode.append("<tr class=\"table-success\"><td>Si vol desactivar aquest usuari "
                            + "abans ha de desactivar els següents càrrecs:</br> " + sb.toString() + "</td>&nbsp;<td>"
                            + "</td></tr>");
                }

            }

            htmlCode.append("</table>");
            map.put(ueID, htmlCode.toString());

        }
    }

    public String crearBoto(String action, String label) {
        return crearBotoIntern(action, label, "fas fa-trash icon-white", "btn-danger");
    }

    public String crearBotoView(String action, String label) {
        return crearBotoIntern(action, label, "fas fa-eye icon-white", "btn-info");

    }

    public String crearBotoIntern(String action, String label, String icon, String color) {
        return "<a title=\"" + label + "\"class=\"btn " + color + " btn-sm a_item\" "
                + "style=\"margin-bottom:5px;color: white;\" href=\"" + action + "\" onclick=\"\" >" + "<i class=\""
                + icon + "\"></i></a>";
    }

    public static Set<String> usuarisLDAPCache = new HashSet<String>();

    public static long lastUpdateUsuarisLDAP = 0;

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        // TODO FICAR DINS CACHE !!!!

        if ((lastUpdateUsuarisLDAP + 3600000) < System.currentTimeMillis()) {

            Set<String> usuaris = new HashSet<String>();
            try {

                long start = System.currentTimeMillis();
                IUserInformationPlugin plugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
                String[] rol_user = plugin.getUsernamesByRol(Constants.PFI_USER);

                String[] rol_admin = plugin.getUsernamesByRol(Constants.PFI_ADMIN);

                usuaris.addAll(Arrays.asList(rol_user));
                usuaris.addAll(Arrays.asList(rol_admin));

                log.info("Consultant usuaris amb rol PFI_USER i PFI_ADMIN: " + (System.currentTimeMillis() - start)
                        + " ms");

            } catch (Exception e) {
                String msg = "Error consultant els usuaris amb rol PFI_USER i PFi_ADMIN: " + e.getMessage();
                log.error(msg, e);
                throw new I18NException(e, "genapp.comodi", msg);
            }

            usuarisLDAPCache = usuaris;
            lastUpdateUsuarisLDAP = System.currentTimeMillis();

        }

        UsuariEntitatQueryPath ueqp = new UsuariEntitatQueryPath();
        return Where.AND(
                // Que estiguin Actius
                ACTIU.equal(true),
                // No és un càrrec
                CARREC.isNull(),
                // Nomes els usuaris interns
                ueqp.USUARIPERSONA().USUARIINTERN().equal(true),
                // De la nostra entitat
                ENTITATID.equal(LoginInfo.getInstance().getEntitatID()),
                // Qu eno tenguin rol PFI_USEr o PFI_ADMIN
                USUARIPERSONAID.notIn(usuarisLDAPCache));

    }

    @Override
    public String getTileForm() {
        return "usuariEntitatSenseRolsFormAden";
    }

    @Override
    public String getTileList() {
        return "usuariEntitatSenseRolsListAden";
    }

    @RequestMapping(value = "/eliminarRevisor/{revisatUsuariEntitatID}/{revisorUsuariEntitatID}")
    public String eliminarRevisor(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("revisatUsuariEntitatID")
            String revisatUsuariEntitatID, @PathVariable("revisorUsuariEntitatID")
            String revisorUsuariEntitatID) throws I18NException {

        try {

            Where w = Where.AND(RevisorDeDestinatariFields.REVISORID.equal(revisorUsuariEntitatID),
                    RevisorDeDestinatariFields.DESTINATARIID.equal(revisatUsuariEntitatID));
            revisorDeDestinatariEjb.delete(w);

        } catch (I18NException i18ne) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));

        }

        return getRedirectWhenDelete(request, null, null);

    }

    /**
     * 
     * @param request
     * @param response
     * @param delegacioColaboracioID
     * @return
     * @throws I18NException
     */
    @RequestMapping(value = "/eliminarDelegacioColaboracio/{delegacioColaboracioID}")
    public String eliminarDelegacioColaboracio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("delegacioColaboracioID")
            Long delegacioColaboracioID) throws I18NException {

        try {

            colaboracioDelegacioLogicaEjb
                    .deleteFull(colaboracioDelegacioLogicaEjb.findByPrimaryKey(delegacioColaboracioID));

        } catch (I18NException i18ne) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));

        }

        return getRedirectWhenDelete(request, null, null);

    }

    /**
     * Només per AdEN
     * @param request
     * @param response
     * @param peticioDeFirmaID
     * @return
     * @throws I18NException
     */
    @RequestMapping(value = "/rebutjar/{usuariEntitatID}/{peticioDeFirmaID}")
    public String rebutjar(HttpServletRequest request, HttpServletResponse response, @PathVariable("usuariEntitatID")
    String usuariEntitatID, @PathVariable("peticioDeFirmaID")
    Long peticioDeFirmaID) throws I18NException {

        try {

            PeticioDeFirmaJPA peticioDeFirma = peticioDeFirmaLogicaEjb.findByPrimaryKeyFull(peticioDeFirmaID);

            String motiuDeRebuig2 = "L´usuari " + usuariEntitatID
                    + " no té permisos per realitzar accions en aquesta petició. Es rebutja la petició de firma per a que no quedi per sempre en procés.";

            /*
                La bitàcola ja recull que és un administrador i el seu login.
                String motiuDeRebuig = I18NUtils.tradueix("peticionsdefirma.destinatari.motiurebuig",
                    Utils.getNom(LoginInfo.getInstance().getUsuariPersona()), motiuDeRebuig2);
                 */

            peticioDeFirmaLogicaEjb.rebutjarADEN(peticioDeFirma, LoginInfo.getInstance().getUsuariEntitatID(),
                    motiuDeRebuig2);

        } catch (I18NException i18ne) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(i18ne));

        }

        return getRedirectWhenDelete(request, null, null);

    }

}
