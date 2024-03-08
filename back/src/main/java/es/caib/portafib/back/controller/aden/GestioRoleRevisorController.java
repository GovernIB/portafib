package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.admin.AbstractGestioRoleUsuariEntitatController;
import es.caib.portafib.back.form.SeleccioUsuariForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatFilterForm;
import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.logic.FirmaLogicaLocal;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.FluxDeFirmesQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = "/aden/revisor")
@SessionAttributes(types = { RoleUsuariEntitatForm.class, RoleUsuariEntitatFilterForm.class, SeleccioUsuariForm.class })
public class GestioRoleRevisorController extends AbstractGestioRoleUsuariEntitatController {

    @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.RevisorDeFirmaService revisorDeFirmaEjb;

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    protected FirmaLogicaLocal firmaLogicaEjb;

    @Override
    public String getRoleGestionat() {
        return ConstantsV2.ROLE_REVI;
    }

    @Override
    public String getTileForm() {
        return "gestioRoleRevisorForm";
    }

    @Override
    public String getTileList() {
        // NO S'USARÀ, JA QUE NO HI HA LLISTAT
        return "gestioRoleRevisorList";
    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    protected String getTileSeleccioUsuari() {
        return "seleccioUsuariForm_ADEN";
    }

    @Override
    protected SeleccioUsuariForm getSeleccioUsuariForm(HttpServletRequest request) {
        SeleccioUsuariForm seleccioUsuariForm = new SeleccioUsuariForm();

        seleccioUsuariForm.setTitol("revisor.gestio");
        seleccioUsuariForm.setSubtitol("revisor.gestio.subtitol");
        seleccioUsuariForm.setCancelUrl("/canviarPipella/" + getRole());
        seleccioUsuariForm.setUrlData("/common/json/usuaripersonaentitatintern");

        seleccioUsuariForm.setUsuarisFavorits(null);

        return seleccioUsuariForm;
    }

    @Override
    protected String checksPostNif(HttpServletRequest request, UsuariPersona usuariPersona, String param1,
            String param2) throws I18NException {

        // Cercam l'usuari entitat de l'entitat actual associat a usuariPersona

        String entitatActualID = LoginInfo.getInstance().getEntitatID();
        String usuariPersonaID = usuariPersona.getUsuariPersonaID();
        UsuariEntitatJPA ue = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatActualID, usuariPersonaID);

        if (ue == null) {
            throw new I18NException("usuarientitat.error.noexisteix", new I18NArgumentString(usuariPersonaID));
        }

        // Esbrinam si aquest ususrientitat ja té el rol de solicitant o no
        Where w1 = ROLEID.equal(ConstantsV2.ROLE_REVI);
        Where w2 = USUARIENTITATID.equal(ue.getUsuariEntitatID());
        List<Long> list = roleUsuariEntitatEjb.executeQuery(ID, Where.AND(w1, w2));

        // TODO selectOne

        if (list.size() == 0) {
            request.getSession().setAttribute("UsuariEntitatRevisor", ue.getUsuariEntitatID());
            return getContextWeb() + "/new";
        } else {
            HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("revisor.hasRole"));
            return getContextWeb() + "/" + list.get(0) + "/edit";
        }

    }

    @Override
    public void initNewRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm, HttpServletRequest request,
            ModelAndView mav, UsuariPersona usuariPersona) throws I18NException {
        String _usuariEntitatID_ = (String) request.getSession().getAttribute("UsuariEntitatRevisor");
        roleUsuariEntitatForm.getRoleUsuariEntitat().setUsuariEntitatID(_usuariEntitatID_);
        roleUsuariEntitatForm.addReadOnlyField(USUARIENTITATID);

        String nomPersona = usuariPersona.getNom() + " " + usuariPersona.getLlinatges();
        HtmlUtils.saveMessageInfo(request,
                I18NUtils.tradueix("revisor.verificacio", nomPersona, usuariPersona.getNif()));

    }

    @Override
    public void initEditRoleForm(RoleUsuariEntitatForm roleUsuariEntitatForm, HttpServletRequest request,
            ModelAndView mav, UsuariPersona usuariPersona) throws I18NException {
        roleUsuariEntitatForm.addReadOnlyField(USUARIENTITATID);
        roleUsuariEntitatForm.setSaveButtonVisible(false);
    }

    @Override
    public List<StringKeyValue> getReferenceListForUsuariEntitatID(HttpServletRequest request, ModelAndView mav,
            RoleUsuariEntitatForm roleUsuariEntitatForm, Where _w) throws I18NException {

        String usuariEntitatID = roleUsuariEntitatForm.getRoleUsuariEntitat().getUsuariEntitatID();

        UsuariEntitatJPA usuariEntitatJPA = usuariEntitatLogicaEjb.findByPrimaryKeyFull(usuariEntitatID);

        List<StringKeyValue> nueva = new ArrayList<StringKeyValue>();

        nueva.add(new StringKeyValue(usuariEntitatJPA.getUsuariEntitatID(),
                Utils.getNom(usuariEntitatJPA.getUsuariPersona())));

        return nueva;
    }

    @Override
    public void delete(HttpServletRequest request, RoleUsuariEntitat roleUsuariEntitat) throws I18NException {

        // #169 Ho feim a SACO => Si apareix a alguna Peticio de Firma
        // com a revisor llavors no es pot esborrar

        List<RevisorDeFirma> revisions;
        revisions = revisorDeFirmaEjb
                .select(RevisorDeFirmaFields.USUARIENTITATID.equal(roleUsuariEntitat.getUsuariEntitatID()));

        if (revisions == null || revisions.size() == 0) {
            super.delete(request, roleUsuariEntitat);
        } else {

            List<Long> firmesID = new ArrayList<Long>();
            for (RevisorDeFirma revisorDeFirma : revisions) {
                firmesID.add(revisorDeFirma.getFirmaID());
            }

            // Revisors de Firma en Peticions de Firma
            {
                FirmaQueryPath fqp = new FirmaQueryPath();

                PeticioDeFirmaQueryPath pfqp = fqp.BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();

                SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(pfqp.PETICIODEFIRMAID().select,
                        pfqp.TITOL().select);

                List<StringKeyValue> skvList = firmaLogicaEjb.executeQuery(smskv, FirmaFields.FIRMAID.in(firmesID));

                if (skvList != null && skvList.size() != 0) {
                    StringBuffer str = new StringBuffer();
                    for (StringKeyValue skv : skvList) {
                        str.append(skv.getValue()).append(" (").append(skv.getKey()).append("), ");
                    }

                    // "El revisor amb usuari-entitat {0} no es pot esborrar ja que esta donat
                    // d´alta com a revisor en les següent peticions de firma: {1}
                    HtmlUtils.saveMessageError(request, I18NUtils.tradueix("revisor.error.apareixenpeticions",
                            roleUsuariEntitat.getUsuariEntitatID(), str.toString()));
                }
            }

            // Revisors de Firma en Plantilles de Flux de Firma
            {
                FirmaQueryPath fqp = new FirmaQueryPath();

                FluxDeFirmesQueryPath pfqp = fqp.BLOCDEFIRMES().FLUXDEFIRMES();

                SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(pfqp.FLUXDEFIRMESID().select,
                        pfqp.NOM().select);

                List<StringKeyValue> skvList = firmaLogicaEjb.executeQuery(smskv, FirmaFields.FIRMAID.in(firmesID));

                if (skvList != null && skvList.size() != 0) {
                    StringBuffer str = new StringBuffer();
                    for (StringKeyValue skv : skvList) {
                        str.append(skv.getValue()).append(" (").append(skv.getKey()).append("), ");
                    }

                    // "El revisor amb usuari-entitat {0} no es pot esborrar ja que esta donat
                    // d´alta com a revisor en les següent peticions de firma: {1}
                    HtmlUtils.saveMessageError(request, I18NUtils.tradueix("revisor.error.apareixenflux",
                            roleUsuariEntitat.getUsuariEntitatID(), str.toString()));
                }
            }

        }

    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long id) {
        return "redirect:/canviarPipella/" + getRole();
    }

    protected String getRole() {
        return ConstantsV2.ROLE_ADEN;
    }

    @Override
    public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long id, Throwable __e) {
        return getRedirectWhenCancel(request, id);
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, RoleUsuariEntitatForm roleUsuariEntitatForm) {
        return getRedirectWhenCancel(request, roleUsuariEntitatForm.getRoleUsuariEntitat().getId());
    }

}
