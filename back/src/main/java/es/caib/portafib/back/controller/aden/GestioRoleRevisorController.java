package es.caib.portafib.back.controller.aden;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
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
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
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

    @EJB(mappedName = RevisorDeDestinatariLogicaService.JNDI_NAME)
    protected RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;

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

        String usuariEntitatID = roleUsuariEntitat.getUsuariEntitatID();
        
        I18NTranslation i18n =  revisorDeDestinatariEjb.pucEsborrarRevisor(usuariEntitatID);
        
        if (i18n == null) {
          super.delete(request, roleUsuariEntitat);
        } else {
            HtmlUtils.saveMessageError(request,I18NUtils.tradueix(i18n));
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
